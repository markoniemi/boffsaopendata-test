package org.boffsa.opendata.config;

import java.nio.charset.StandardCharsets;

import org.boffsa.opendata.FixedCharsetJackson2HttpMessageConverter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;

import feign.codec.Decoder;

public class FeignConfig {
	@Bean
	public Decoder feignDecoder() {
		return new ResponseEntityDecoder(new SpringDecoder(feignHttpMessageConverter()));
	}

	public ObjectFactory<HttpMessageConverters> feignHttpMessageConverter() {
		final HttpMessageConverters httpMessageConverters = new HttpMessageConverters(
				new FixedCharsetJackson2HttpMessageConverter(StandardCharsets.UTF_16LE));
		return new ObjectFactory<HttpMessageConverters>() {
			@Override
			public HttpMessageConverters getObject() throws BeansException {
				return httpMessageConverters;
			}
		};
	}
}