package org.agroup.practice;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;

public class CarRhdmDeserializer extends RhdmDeserializer<Car> {
	private static final Logger LOG = LoggerFactory.getLogger(CarRhdmDeserializer.class);

	private static final long serialVersionUID = 1L;

	public CarRhdmDeserializer() {
		this(null);
		this.responseFactName = "mil.mgae.rules.carloan.Car";
	}

	public CarRhdmDeserializer(Class<?> clazz) {
		super(clazz);
		this.responseFactName = "mil.mgae.rules.carloan.Car";
	}

	@Override
	public Car deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		LOG.info("Enter method deserialize");
		Car car = new Car();
		ObjectCodec codec = parser.getCodec();
		JsonNode node = codec.readTree(parser);

		// try catch block
		final String SUCCESS = "SUCCESS";
		final String DEFAULT = "Default";
		final String KEY = this.responseFactName;
		String success = node.get("type").asText(DEFAULT);
		String msg = node.get("msg").asText(DEFAULT);
		
		LOG.info("success/msg=={}/{}", success, msg);

		if (success.equals(SUCCESS)) {
			JsonNode jnResults = node.get("result").get("execution-results");

			if (jnResults.has("results")) {
				JsonNode r = jnResults.get("results");
				JsonNode rr = r.get(0).get("value").get(KEY);
				car.setType(rr.get("type").asText(DEFAULT));
				car.setColor(rr.get("color").asText(DEFAULT));
				LOG.info("car.toString: {}", car.toString());
			}
		} else {
			LOG.info("no success; nothing to do");
		}

		LOG.info("About to leave method deserialize");
		return car;
	}

}
