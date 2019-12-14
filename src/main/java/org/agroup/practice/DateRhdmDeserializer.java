package org.agroup.practice;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class DateRhdmDeserializer extends StdDeserializer<Date> {
	private static final Logger LOG = LoggerFactory.getLogger(DateRhdmDeserializer.class);

	private static final long serialVersionUID = 1L;
	
	private static final String DATE_PATTERN = "dd-MM-yyyy hh:mm:ss";

	public DateRhdmDeserializer() {
		this(null);
	}

	public DateRhdmDeserializer(Class<?> clazz) {
		super(clazz);
	}

	@Override
	public Date deserialize(JsonParser jsonparser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		LOG.info("Begin method deserialize");

		SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN);
		String strDate = jsonparser.getText();

		Date retVal;
		try {
			retVal = formatter.parse(strDate);
		} catch (ParseException e) {
			LOG.info("{}", e.getMessage());
			throw new RuntimeException(e);
		}
		LOG.info("About to leave method deserialize");
		return retVal;
	}
}
