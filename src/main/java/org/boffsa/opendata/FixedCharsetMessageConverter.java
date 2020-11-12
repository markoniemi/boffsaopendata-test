package org.boffsa.opendata;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.StreamUtils;

public class FixedCharsetMessageConverter extends StringHttpMessageConverter {
	private Charset charset;

	public FixedCharsetMessageConverter(Charset charset) {
		this.charset = charset;
	}

	@Override
	protected String readInternal(Class<? extends String> clazz, HttpInputMessage inputMessage) throws IOException {
		return StreamUtils.copyToString(inputMessage.getBody(), charset);
	}
}
