package org.agroup.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
	private static final Logger LOG = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		final String practiceToggle = "PracticeTwo";
		LOG.info("practiceToggle: {}", practiceToggle);

		if (practiceToggle.equals("PracticeOne")) {
			PracticeOne.pracWriteJson();
			PracticeOne.pracReadJson();
			PracticeOne.pracReadJsonFile();
			PracticeOne.pracReadRhdmResponse();
		}

		if (practiceToggle.equals("PracticeTwo")) {
			PracticeTwo.pracReadRhdmResponse();
		}
	}
}
