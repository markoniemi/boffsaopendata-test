package org.boffsa.opendata;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.boffsa.opendata.dto.ExchangeRateInfo;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReferenceRatesIT {
	private static final String URL = "https://api.boffsaopendata.fi/referencerates/v2/api/V2";

	@Test
	public void testRestTemplateWithCustomMessageConverter()
			throws JsonParseException, JsonMappingException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(
				Arrays.asList(new FixedCharsetJackson2HttpMessageConverter(StandardCharsets.UTF_16LE)));
		List<ExchangeRateInfo> exchangeRateInfos = restTemplate
				.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<ExchangeRateInfo>>() {
				}).getBody();
		assertEquals(32, exchangeRateInfos.size());
		assertNotNull(exchangeRateInfos.get(0).getExchangeRates().get(0).getValue());
	}

	@Test
	public void testRestTemplateWithCustomStringMessageConverter()
			throws JsonParseException, JsonMappingException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(Arrays.asList(new FixedCharsetMessageConverter(StandardCharsets.UTF_16LE)));
		String data = restTemplate.exchange(URL, HttpMethod.GET, null, String.class).getBody();
		List<ExchangeRateInfo> exchangeRateInfos = parse(data);
		assertEquals(32, exchangeRateInfos.size());
		assertNotNull(exchangeRateInfos.get(0).getExchangeRates().get(0).getValue());
	}

	@Test
	public void testRestTemplateWithByteArray() throws JsonParseException, JsonMappingException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		byte[] data = restTemplate.exchange(URL, HttpMethod.GET, null, byte[].class).getBody();
		List<ExchangeRateInfo> exchangeRateInfos = parse(new String(data, StandardCharsets.UTF_16LE));
		assertEquals(32, exchangeRateInfos.size());
	}

	public static List<ExchangeRateInfo> parse(String json)
			throws JsonParseException, JsonMappingException, IOException {
		return new ObjectMapper().readValue(json.getBytes(), new TypeReference<List<ExchangeRateInfo>>() {
		});
	}
}
