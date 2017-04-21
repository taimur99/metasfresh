package de.metas.ui.web.view;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.adempiere.util.lang.impl.TableRecordReference;
import org.compiere.util.Evaluatee;

import com.google.common.collect.ImmutableList;

import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.process.view.ViewActionDescriptorsList;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.model.DocumentQueryOrderBy;
import de.metas.ui.web.window.model.filters.DocumentFilter;

/*
 * #%L
 * metasfresh-webui-api
 * %%
 * Copyright (C) 2016 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

public interface IDocumentViewSelection
{
	ViewId getViewId();

	/** @return table name or null */
	String getTableName();

	/** @return parent viewId or null */
	ViewId getParentViewId();

	default boolean isIncludedView()
	{
		return getParentViewId() != null;
	}

	long size();

	void close();

	int getQueryLimit();

	boolean isQueryLimitHit();

	DocumentViewResult getPage(int firstRow, int pageLength, List<DocumentQueryOrderBy> orderBys);

	default DocumentViewResult getPage(final int firstRow, final int pageLength, final String orderBysListStr)
	{
		final List<DocumentQueryOrderBy> orderBys = DocumentQueryOrderBy.parseOrderBysList(orderBysListStr);
		return getPage(firstRow, pageLength, orderBys);
	}

	IDocumentView getById(DocumentId documentId) throws EntityNotFoundException;

	default List<? extends IDocumentView> getByIds(final Set<DocumentId> documentIds)
	{
		return streamByIds(documentIds).collect(ImmutableList.toImmutableList());
	}

	LookupValuesList getFilterParameterDropdown(String filterId, String filterParameterName, Evaluatee ctx);

	LookupValuesList getFilterParameterTypeahead(String filterId, String filterParameterName, String query, Evaluatee ctx);

	List<DocumentFilter> getStickyFilters();

	/**
	 * @return active filters
	 */
	List<DocumentFilter> getFilters();

	List<DocumentQueryOrderBy> getDefaultOrderBys();

	String getSqlWhereClause(Collection<DocumentId> viewDocumentIds);

	boolean hasAttributesSupport();

	<T> List<T> retrieveModelsByIds(Collection<DocumentId> documentIds, Class<T> modelClass);

	/**
	 * @return a stream which contains only the {@link IDocumentView}s which given <code>documentId</code>s.
	 *         If a {@link IDocumentView} was not found for given ID, this method simply ignores it.
	 */
	Stream<? extends IDocumentView> streamByIds(Collection<DocumentId> documentIds);

	/**
	 * Notify the view that given record(s) has changed.
	 */
	void notifyRecordsChanged(Set<TableRecordReference> recordRefs);

	/** @return actions which were registered particularly for this view instance */
	default ViewActionDescriptorsList getActions()
	{
		return ViewActionDescriptorsList.EMPTY;
	}
}
