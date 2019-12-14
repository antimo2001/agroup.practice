package org.agroup.practice;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class PracticeTwo {
	private static final Logger LOG = LoggerFactory.getLogger(PracticeTwo.class);

	public static void pracReadRhdmResponse() {
		String filename = "src/main/resources/CarRhdmResponse.json";

		try {
			File file = new File(filename);
			CarRhdmDeserializer deserializer = new CarRhdmDeserializer();
			RhdmResponse<Car> rhdmResponse = new RhdmResponse<Car>(deserializer);
			Car car = rhdmResponse.read(file, Car.class);

			if (car != null) {
				LOG.info("car.toString: {}", car.toString());
			}
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
