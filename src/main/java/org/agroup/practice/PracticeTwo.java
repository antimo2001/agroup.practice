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
		pracReadCar();
		pracReadLoan();
	}

	/**
	 * practice Jackson's custom Deserializer and POJO with CarRhdmDeserializer;
	 * utilizes generic RhdmResponse
	 */
	public static void pracReadCar() {
		LOG.info("Begin method pracReadCar");
		String filename = "src/main/resources/CarRhdmResponse.json";

		try {
			File rawJson = new File(filename);
			RhdmResponse<Car> rhdmResponse = new RhdmResponse<Car>();
			rhdmResponse.setDeserializer(new CarRhdmDeserializer());
			Car car = rhdmResponse.read(rawJson, Car.class);

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
		LOG.info("About to leave method pracReadCar");
	}

	/**
	 * practice Jackson's custom Deserializer and POJO with LoanrRhdmDeserializer;
	 * utilizes generic RhdmResponse
	 */
	public static void pracReadLoan() {
		LOG.info("Begin method pracReadLoan");
		String filename = "src/main/resources/LoanRhdmResponse.json";

		try {
			File rawJson = new File(filename);
			RhdmResponse<Loan> rhdmResponse = new RhdmResponse<Loan>();
			rhdmResponse.setDeserializer(new LoanRhdmDeserializer());
			Loan loan = rhdmResponse.read(rawJson, Loan.class);

			if (loan != null) {
				LOG.info("loan.toString: {}", loan.toString());
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
			throw new RuntimeException(e);
		}
		LOG.info("About to leave method pracReadLoan");
	}
}
