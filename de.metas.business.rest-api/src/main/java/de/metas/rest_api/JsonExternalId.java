package de.metas.rest_api;

import static de.metas.util.Check.assumeNotEmpty;
import static de.metas.util.Check.isEmpty;

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.NonNull;
import lombok.Value;

/*
 * #%L
 * de.metas.business.rest-api
 * %%
 * Copyright (C) 2019 metas GmbH
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

@Value
public class JsonExternalId
{
	String value;

	@JsonCreator
	public static JsonExternalId of(@NonNull final String value)
	{
		return new JsonExternalId(value);
	}

	public static JsonExternalId ofOrNull(@Nullable final String value)
	{
		if (isEmpty(value, true))
		{
			return null;
		}
		return new JsonExternalId(value);
	}

	private JsonExternalId(@NonNull final String value)
	{
		this.value = assumeNotEmpty(value, "Param value={} may not be empty", value);
	}

	@JsonValue
	public String getValue()
	{
		return value;
	}
}
