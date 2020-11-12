package org.boffsa.opendata;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.lang.Nullable;

public class Utf16Jackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
	protected Charset getCharset(@Nullable MediaType contentType) {
//		if (contentType != null && contentType.getCharset() != null) {
//			return contentType.getCharset();
//		} else {
//			return StandardCharsets.UTF_8;
//		}
		return StandardCharsets.UTF_16LE;
	}
}
