package org.boffsa.opendata;

import java.util.List;

import org.boffsa.opendata.config.FeignConfig;
import org.boffsa.opendata.dto.ExchangeRateInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "referenceRates", url = "https://api.boffsaopendata.fi/referencerates/v2",configuration = FeignConfig.class)
public interface ReferenceRates {

	// TODO return a list of ExchangeRateInfo
//	byte[] findAll();
//	String findAll();
	@GetMapping(value = "/api/V2")
	List<ExchangeRateInfo> findAll();
}
