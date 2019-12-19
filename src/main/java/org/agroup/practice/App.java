package org.agroup.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
	private static final Logger LOG = LoggerFactory.getLogger(App.class);
	private static final String PRACTICE_TOGGLE = "PracticeThree";

	public static void main(String[] args) {
		LOG.info("PRACTICE_TOGGLE: {}", PRACTICE_TOGGLE);

		if (PRACTICE_TOGGLE.equals("PracticeOne")) {
			PracticeOne.pracWriteJson();
			PracticeOne.pracReadJson();
			PracticeOne.pracReadJsonFile();
			PracticeOne.pracReadRhdmResponse();
		}

		if (PRACTICE_TOGGLE.equals("PracticeTwo")) {
			PracticeTwo.pracReadRhdmResponse();
		}

		if (PRACTICE_TOGGLE.equals("PracticeThree")) {
			PracticeThree.pracReadRhdmResponse();
		}
	}
}
