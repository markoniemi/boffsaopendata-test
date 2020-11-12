package org.boffsa.opendata.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;

@Data
public class ExchangeRate {
	@JsonAlias("ObservationDate")
	String observationDate;
	@JsonDeserialize(converter = StringToDoubleConverter.class)
	@JsonAlias("Value")
	Double value;
}
