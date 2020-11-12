package org.boffsa.opendata;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.StreamUtils;

public class Utf16HttpMessageConverter extends StringHttpMessageConverter {
	@Override
	protected String readInternal(Class<? extends String> clazz, HttpInputMessage inputMessage) throws IOException {
		Charset charset = Charset.forName("UTF-16LE");
		return StreamUtils.copyToString(inputMessage.getBody(), charset);
	}

}
