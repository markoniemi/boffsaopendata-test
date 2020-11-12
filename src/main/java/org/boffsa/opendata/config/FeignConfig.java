package org.boffsa.opendata.config;

import java.util.ArrayList;
import java.util.List;

import org.boffsa.opendata.Utf16Jackson2HttpMessageConverter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import feign.Logger;
import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;

@Configuration
public class FeignConfig {

//    @Bean
//    Logger.Level feignLoggerLevel() {
//        return Logger.Level.FULL;
//    }


//	@Bean
//	public Decoder feignDecoder() {
//		
////        final HttpMessageConverters httpMessageConverters = new HttpMessageConverters(new PhpMappingJackson2HttpMessageConverter());
//		return new ResponseEntityDecoder(new SpringDecoder(new Utf16Jackson2HttpMessageConverter()));
//	}
    @Bean
    public Decoder feignDecoder() {
        return new ResponseEntityDecoder(new SpringDecoder(feignHttpMessageConverter()));
    }

    public ObjectFactory<HttpMessageConverters> feignHttpMessageConverter() {
        final HttpMessageConverters httpMessageConverters = new HttpMessageConverters(new Utf16Jackson2HttpMessageConverter());
        return new ObjectFactory<HttpMessageConverters>() {
            @Override
            public HttpMessageConverters getObject() throws BeansException {
                return httpMessageConverters;
            }
        };
    }
//
//    public class PhpMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
//        PhpMappingJackson2HttpMessageConverter(){
//            List<MediaType> mediaTypes = new ArrayList<>();
//            mediaTypes.add(MediaType.valueOf(MediaType.TEXT_HTML_VALUE + ";charset=UTF-8"));
//            setSupportedMediaTypes(mediaTypes);
//        }
//    }
}