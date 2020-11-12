package org.boffsa.opendata.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;

@Data
public class ExchangeRate {
	@JsonAlias("ObservationDate")
	String observationDate;
	@JsonAlias("Value")
	String value;
}
