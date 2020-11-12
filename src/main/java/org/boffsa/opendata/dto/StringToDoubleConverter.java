package org.boffsa.opendata.dto;

import com.fasterxml.jackson.databind.util.StdConverter;

public class StringToDoubleConverter extends StdConverter<String, Double> {

	@Override
	public Double convert(String value) {
		return Double.parseDouble(value.replace(",", "."));
	}
}
