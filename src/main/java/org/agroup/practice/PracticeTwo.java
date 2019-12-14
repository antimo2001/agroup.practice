package org.agroup.practice;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PracticeTwo {
	private static final Logger LOG = LoggerFactory.getLogger(PracticeTwo.class);

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
