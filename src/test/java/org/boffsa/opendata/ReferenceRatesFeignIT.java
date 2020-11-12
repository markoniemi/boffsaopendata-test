package org.boffsa.opendata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.boffsa.opendata.config.FeignConfig;
import org.boffsa.opendata.config.IntegrationTestConfig;
import org.boffsa.opendata.dto.ExchangeRateInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.extern.log4j.Log4j2;

@EnableFeignClients(clients = ReferenceRates.class)
@RestController
@Configuration
@EnableAutoConfiguration
@Log4j2
@RunWith(SpringRunner.class)
@ContextHierarchy(@ContextConfiguration(classes = { IntegrationTestConfig.class, FeignConfig.class }))
@ImportAutoConfiguration({ FeignAutoConfiguration.class })
public class ReferenceRatesFeignIT {
	@Resource
	ReferenceRates referenceRates;
	@Test
	public void testFeignClient() throws JsonParseException, JsonMappingException, IOException {
//		byte[] data = referenceRates.findAll();
//		String json = new String(data, StandardCharsets.UTF_16LE);
//		String json = referenceRates.findAll();
//		log.trace(json);
//		List<ExchangeRateInfo> exchangeRateInfos = parse(json);
		List<ExchangeRateInfo> exchangeRateInfos = referenceRates.findAll();
		exchangeRateInfos.forEach(exchangeRateInfo -> {
			log.debug(exchangeRateInfo);
		});
		assertEquals(32, exchangeRateInfos.size());
	}

}
