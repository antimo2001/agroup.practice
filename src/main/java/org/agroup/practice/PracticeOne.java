package org.agroup.practice;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PracticeOne {
	private static final Logger LOG = LoggerFactory.getLogger(PracticeOne.class);

	/**
	 * first example of serializing a Java Object into JSON using the writeValue
	 * method of ObjectMapper class:
	 */
	public static void pracWriteJson() {
		LOG.info("Enter method pracWriteJson");
		ObjectMapper objectMapper = new ObjectMapper();
		Car car = new Car("yellow", "renault");
		try {
			objectMapper.writeValue(new File("target/car.json"), car);
		} catch (JsonGenerationException e) {
			LOG.info("{}", e.getMessage());
		} catch (JsonMappingException e) {
			LOG.info("{}", e.getMessage());
		} catch (IOException e) {
			LOG.info("{}", e.getMessage());
		}
		LOG.info("About to leave method pracWriteJson");
	}

	/**
	 * a simple example of converting a JSON String to a Java object using the
	 * ObjectMapper class:
	 */
	public static void pracReadJson() {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
		try {
			Car car = objectMapper.readValue(json, Car.class);
			LOG.info("car.color is Black? {}", ("Black".equals(car.getColor())));
			LOG.info("car.type is BMW? {}", ("BMW".equals(car.getType())));
		} catch (JsonGenerationException e) {
			LOG.info("{}", e.getMessage());
		} catch (JsonMappingException e) {
			LOG.info("{}", e.getMessage());
		} catch (IOException e) {
			LOG.info("{}", e.getMessage());
		}
	}

	/**
	 * a simple example of converting a JSON String to a Java object using the
	 * ObjectMapper class: The readValue() function also accepts other forms of
	 * input like a file containing JSON string:
	 */
	public static void pracReadJsonFile() {
		ObjectMapper objectMapper = new ObjectMapper();
		String filename = "src/main/resources/car2read.json";
		try {
			Car car = objectMapper.readValue(new File(filename), Car.class);
			LOG.info("car.color is Red? {}", ("Red".equals(car.getColor())));
			LOG.info("car.type is GM? {}", ("GM".equals(car.getType())));
		} catch (JsonGenerationException e) {
			LOG.info("JsonGenerationException");
			LOG.info("{}", e.getMessage());
		} catch (JsonMappingException e) {
			LOG.info("JsonMappingException");
			LOG.info("{}", e.getMessage());
		} catch (IOException e) {
			LOG.error("unexpected IOException");
			LOG.info("{}", e.getMessage());
		}
	}

	public static void pracReadRhdmResponse() {
		ObjectMapper objectMapper = new ObjectMapper();
		String filename = "src/main/resources/CarRhdmResponse.json";
		final String KEY = "mil.mgae.rules.carloan.Car";

		try {
			File file = new File(filename);
			JsonNode jn = objectMapper.readTree(file);
			String success = jn.get("type").asText("");
			String msg = jn.get("msg").asText("");
			JsonNode jnExecutionResults = jn.get("result").get("execution-results");
			boolean hasResults = jnExecutionResults.has("results");
			Car car = new Car();

			if (hasResults) {
				JsonNode a = jnExecutionResults.get("results");
				JsonNode b = a.get(0).get("value").get(KEY);
				car.setType(b.get("type").asText(""));
				car.setColor(b.get("color").asText(""));
			} else {
				LOG.info("execution-results has no results");
				car = null;
			}

			LOG.info("success/msg: {}/{}", success, msg);
			LOG.info("hasResults: {}", hasResults);

			if (car != null) {
				LOG.info("car.toString: {}", car.toString());
			}
		} catch (JsonGenerationException e) {
			LOG.info("JsonGenerationException");
			LOG.info("{}", e.getMessage());
		} catch (JsonMappingException e) {
			LOG.info("JsonMappingException");
			LOG.info("{}", e.getMessage());
		} catch (IOException e) {
			LOG.error("unexpected IOException");
			LOG.info("{}", e.getMessage());
		}
	}
}
