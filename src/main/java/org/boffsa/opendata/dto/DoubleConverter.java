package org.boffsa.opendata.dto;

import org.springframework.core.convert.ConverterNotFoundException;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

public class DoubleConverter implements Converter<String, Double> {

	@Override
	public Double convert(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JavaType getInputType(TypeFactory arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JavaType getOutputType(TypeFactory arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
