package org.agroup.practice;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;

public class LoanRhdmDeserializer extends RhdmDeserializer<Loan> {
	private static final Logger LOG = LoggerFactory.getLogger(LoanRhdmDeserializer.class);

	private static final long serialVersionUID = 1L;

	public LoanRhdmDeserializer() {
		this(null);
	}

	public LoanRhdmDeserializer(Class<?> clazz) {
		super(clazz);
	}

	@Override
	public Loan deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		LOG.info("Enter method deserialize");
		final String ZERO = BigDecimal.ZERO.toString();

		JsonNode jnResult = super.getResultNode(parser);

		BigDecimal amount = new BigDecimal(jnResult.get("amount").asText(ZERO));
		BigDecimal interestRate = new BigDecimal(jnResult.get("interestRate").asText(ZERO));
		int term = jnResult.get("term").asInt(0);
		String termUnits = jnResult.get("termUnits").asText("");
		String strExpireDate = jnResult.get("expireDate").asText();
		SimpleDateFormat df = new SimpleDateFormat(RhdmDeserializer.DATE_PATTERN);
		Date expireDate;
		try {
			expireDate = df.parse(strExpireDate);
		} catch (ParseException e) {
			LOG.info("failed to parse date: {}", strExpireDate);
			expireDate = null;
		}

		Loan loan = new Loan();
		loan.setAmount(amount);
		loan.setInterestRate(interestRate);
		loan.setTerm(term);
		loan.setTermUnits(termUnits);
		loan.setExpireDate(expireDate);
		LOG.info("loan.amount,loan.interestRate=={},{}", loan.getAmount(), loan.getInterestRate());
		LOG.info("loan.term,loan.termUnits=={},{}", loan.getTerm(), loan.getTermUnits());
		LOG.info("loan.expireDate=={}", loan.getExpireDate());

		LOG.info("About to leave method deserialize");
		return loan;
	}

	@Override
	protected String getResponseFactName() {
		LOG.info("Entered method getResponseFactName");
		String responseFactName = "mil.mgae.rules.carloan.Loan";
		LOG.info("About to leave method getResponseFactName");
		return responseFactName;
	}

}
