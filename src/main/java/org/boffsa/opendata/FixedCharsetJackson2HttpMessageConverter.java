package org.boffsa.opendata;

import java.nio.charset.Charset;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.lang.Nullable;

public class FixedCharsetJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
	private Charset charset;

	public FixedCharsetJackson2HttpMessageConverter(Charset charset) {
		this.charset = charset;
	}

	protected Charset getCharset(@Nullable MediaType contentType) {
		return charset;
	}
}
