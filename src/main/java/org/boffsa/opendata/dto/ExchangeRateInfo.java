package org.boffsa.opendata.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;

@Data
public class ExchangeRateInfo {
	@JsonAlias("ExchangeRates")
	private List<ExchangeRate> exchangeRates;
	@JsonAlias("Currency")
	private String currency;
	@JsonAlias("CurrencyDenom")
	private String currencyDenom;
	@JsonAlias("CurrencyNameFi")
	private String currencyNameFi;
	@JsonAlias("CurrencyNameSe")
	private String currencyNameSe;
	@JsonAlias("CurrencyNameEn")
	private String currencyNameEn;
	@JsonAlias("ECBPublished")
	private String ecbPublished;
}
