package org.agroup.practice;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class PracticeThree {
	private static final Logger LOG = LoggerFactory.getLogger(PracticeThree.class);

	/**
	 * practice Jackson's custom Deserializer and POJO with TuitionRhdmDeserializer;
	 * utilizes very good, (simulated) JSON data
	 */
	public static void pracReadRhdmResponse() {
    String rhdmresponse1 = "src/main/resources/data/rhdmresponse1.json";
		pracReadTuition(rhdmresponse1);
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
