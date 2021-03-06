/*
 * #%L
 * de-metas-camel-shipping
 * %%
 * Copyright (C) 2020 metas GmbH
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

package de.metas.camel.shipping;

import de.metas.common.filemaker.RESULTSET;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.apache.camel.spi.PropertiesComponent;

import java.util.Map;

@Value
@Builder
public class ProcessXmlToJsonRequest
{
	@NonNull
	RESULTSET resultset;

	@NonNull
	Map<String, Integer> name2Index;

	@NonNull
	PropertiesComponent propertiesComponent;
}
