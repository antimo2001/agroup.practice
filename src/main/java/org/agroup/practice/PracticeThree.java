package org.agroup.practice;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class PracticeThree {
	private static final Logger LOG = LoggerFactory.getLogger(PracticeThree.class);
	private static final String F1 = "src/main/resources/data/rhdmresponse1.json";
	private static final String F2 = "src/main/resources/data/rhdmresponse2.json";
	private static final String F3 = "src/main/resources/data/rhdmresponse3.json";
	private static final String F4 = "src/main/resources/data/rhdmresponse4_FAILURE.json";

	/**
	 * practice Jackson's custom Deserializer and POJO with TuitionRhdmDeserializer;
	 * utilizes very good, (simulated) JSON data
	 */
	public static void pracReadRhdmResponse() {
		List<String> practiceFiles = Arrays.asList(F1,F2,F3,F4);
		for (String filename : practiceFiles) {
			pracReadTuition(filename);
		}
	}

	private static void pracReadTuition(String filename) {
		LOG.info("Begin method pracReadTuition: filename=={}", filename);

		try {
			File rawJson = new File(filename);
			RhdmReader<Tuition> rhdmReader = new RhdmReader<>();
			rhdmReader.prepareToRead(Tuition.class, new TuitionRhdmDeserializer());
			Tuition tuition = rhdmReader.read(rawJson).getData();

			if (tuition != null) {
				LOG.info("tuition.toString: {}", tuition.toString());
			} else {
				LOG.warn("failed to read filename: {}", filename);
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
			throw new RuntimeException(e);
		}
		LOG.info("About to leave method pracReadTuition");
	}
}
