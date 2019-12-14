package org.agroup.practice;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;

public class CarRhdmDeserializer extends RhdmDeserializer<Car> {
	private static final Logger LOG = LoggerFactory.getLogger(CarRhdmDeserializer.class);

	private static final long serialVersionUID = 1L;

	public CarRhdmDeserializer() {
		this(null);
	}

	public CarRhdmDeserializer(Class<?> clazz) {
		super(clazz);
	}

	@Override
	public Car deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		LOG.info("Enter method deserialize");

		JsonNode jnResult = super.getResultNode(parser);

		Car car = new Car();
		car.setType(jnResult.get("type").asText());
		car.setColor(jnResult.get("color").asText());
		LOG.info("car.toString: {}", car.toString());

		LOG.info("About to leave method deserialize");
		return car;
	}

	@Override
	protected String getResponseFactName() {
		LOG.info("Entered method getResponseFactName");
		String responseFactName = "mil.mgae.rules.carloan.Car";
		LOG.info("About to leave method getResponseFactName");
		return responseFactName;
	}

}
