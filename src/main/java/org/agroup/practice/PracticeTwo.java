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
		String filename = "src/main/resources/data/CarRhdmResponse.json";

		try {
			File rawJson = new File(filename);
			RhdmReader<Car> rhdmReader = new RhdmReader<>();
			rhdmReader.prepareToRead(Car.class, new CarRhdmDeserializer());
			RhdmResponse<Car> rhdmResponse = rhdmReader.read(rawJson);
			Car car = rhdmResponse.getData();

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
		LOG.info("About to leave method pracReadCar");
	}

	/**
	 * practice Jackson's custom Deserializer and POJO with LoanrRhdmDeserializer;
	 * utilizes generic RhdmResponse
	 */
	public static void pracReadLoan() {
		LOG.info("Begin method pracReadLoan");
		String filename = "src/main/resources/data/LoanRhdmResponse.json";

		try {
			File rawJson = new File(filename);
			RhdmReader<Loan> rhdmReader = new RhdmReader<>();
			rhdmReader.prepareToRead(Loan.class, new LoanRhdmDeserializer());
			Loan loan = rhdmReader.read(rawJson).getData();

			if (loan != null) {
				LOG.info("loan.toString: {}", loan.toString());
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
		LOG.info("About to leave method pracReadLoan");
	}
}
