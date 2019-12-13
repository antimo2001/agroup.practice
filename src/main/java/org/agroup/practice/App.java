package org.agroup.practice;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
	private static final Logger LOG = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		pracWriteJson();
		pracReadJson();
		pracReadJsonFile();
	}

	/**
	 * first example of serializing a Java Object into JSON using the writeValue
	 * method of ObjectMapper class:
	 */
	private static void pracWriteJson() {
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
	 * a simple example of converting a JSON String to a Java object using the ObjectMapper class:
	 */
	private static void pracReadJson() {
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
	 * a simple example of converting a JSON String to a Java object using the ObjectMapper class:
	 * The readValue() function also accepts other forms of input like a file containing JSON string:
	 */
	private static void pracReadJsonFile() {
		ObjectMapper objectMapper = new ObjectMapper();
		String filename = "src/main/resources/car2read.json";
		try {
			Car car = objectMapper.readValue(new File(filename), Car.class);
			LOG.info("car.color is Red? {}", ("Red".equals(car.getColor())));
			LOG.info("car.type is GM? {}", ("GM".equals(car.getType())));
		} catch (JsonGenerationException e) {
			LOG.debug("JsonGenerationException");
			LOG.info("{}", e.getMessage());
		} catch (JsonMappingException e) {
			LOG.debug("JsonMappingException");
			LOG.info("{}", e.getMessage());
		} catch (IOException e) {
			LOG.error("unexpected IOException");
			LOG.info("{}", e.getMessage());
		}  
	}
	
	private static void pracReadRhdmResponse() {
		ObjectMapper objectMapper = new ObjectMapper();
		String filename = "src/main/resources/rhdmresponse.json";
		try {
			Car car = objectMapper.readValue(new File(filename), Car.class);
			LOG.info("car.color is Red? {}", ("Red".equals(car.getColor())));
			LOG.info("car.type is GM? {}", ("GM".equals(car.getType())));
		} catch (JsonGenerationException e) {
			LOG.debug("JsonGenerationException");
			LOG.info("{}", e.getMessage());
		} catch (JsonMappingException e) {
			LOG.debug("JsonMappingException");
			LOG.info("{}", e.getMessage());
		} catch (IOException e) {
			LOG.error("unexpected IOException");
			LOG.info("{}", e.getMessage());
		}  
	}
}
