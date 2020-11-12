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
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ReferenceRatesIT {
	@Test
	public void testRestTemplateWithCustomMessageConverter()
			throws JsonParseException, JsonMappingException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(Arrays.asList(new Utf16Jackson2HttpMessageConverter()));
		List<ExchangeRateInfo> exchangeRateInfos = restTemplate
				.exchange("https://api.boffsaopendata.fi/referencerates/v2/api/V2", HttpMethod.GET, null,
						new ParameterizedTypeReference<List<ExchangeRateInfo>>() {
						})
				.getBody();
		assertEquals(32, exchangeRateInfos.size());
		assertNotNull(exchangeRateInfos.get(0).getExchangeRates().get(0).getValue());
	}

	@Test
	public void testRestTemplateWithCustomStringMessageConverter()
			throws JsonParseException, JsonMappingException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(Arrays.asList(new Utf16HttpMessageConverter()));
		ResponseEntity<String> response = restTemplate
				.exchange("https://api.boffsaopendata.fi/referencerates/v2/api/V2", HttpMethod.GET, null, String.class);
		String json = response.getBody();
		log.trace(json);
		List<ExchangeRateInfo> exchangeRateInfos = parse(json);
		exchangeRateInfos.forEach(exchangeRateInfo -> {
			log.debug(exchangeRateInfo);
		});
		assertEquals(32, exchangeRateInfos.size());
	}

	@Test
	public void testRestTemplateWithByteArray() throws JsonParseException, JsonMappingException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<byte[]> response = restTemplate
				.exchange("https://api.boffsaopendata.fi/referencerates/v2/api/V2", HttpMethod.GET, null, byte[].class);
		String json = new String(response.getBody(), StandardCharsets.UTF_16LE);
		log.trace(json);
		List<ExchangeRateInfo> exchangeRateInfos = parse(json);
		assertEquals(32, exchangeRateInfos.size());
	}

	private List<ExchangeRateInfo> parse(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return objectMapper.readValue(json.getBytes(), new TypeReference<List<ExchangeRateInfo>>() {
		});
	}
}
