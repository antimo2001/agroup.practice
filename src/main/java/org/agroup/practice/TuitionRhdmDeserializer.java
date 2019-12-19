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

public class TuitionRhdmDeserializer extends RhdmDeserializer<Tuition> {
	private static final Logger LOG = LoggerFactory.getLogger(TuitionRhdmDeserializer.class);

	private static final long serialVersionUID = 1L;

	public TuitionRhdmDeserializer() {
		this(null);
	}

	public TuitionRhdmDeserializer(Class<?> clazz) {
		super(clazz);
	}

	@Override
	public Tuition deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		LOG.info("Enter method deserialize");
		final String ZERO = BigDecimal.ZERO.toString();

		JsonNode jnResult = super.getResultNode(parser);

		Tuition tuition = new Tuition();
		String rejReason = jnResult.get("rejReason").asText(ZERO);
		tuition.setRejReason(rejReason);
		String finAsst = jnResult.get("finAsst").asText(ZERO);
		tuition.setFinAsst(new BigDecimal(finAsst));
		String studOblg = jnResult.get("studOblg").asText(ZERO);
		tuition.setStudOblg(new BigDecimal(studOblg));
		String totalAsstRequested = jnResult.get("totalAsstRequested").asText(ZERO);
		tuition.setTotalAsstRequested(new BigDecimal(totalAsstRequested));
		String totalHoursRequested = jnResult.get("totalHoursRequested").asText(ZERO);
		tuition.setTotalHoursRequested(new BigDecimal(totalHoursRequested));
		String updatedPreDPHours = jnResult.get("updatedPreDPHours").asText(ZERO);
		tuition.setUpdatedPreDPHours(new BigDecimal(updatedPreDPHours));
		String updatedUsedAsst = jnResult.get("updatedUsedAsst").asText(ZERO);
		tuition.setUpdatedUsedAsst(new BigDecimal(updatedUsedAsst));
		String updatedUsedHours = jnResult.get("updatedUsedHours").asText(ZERO);
		tuition.setUpdatedUsedHours(new BigDecimal(updatedUsedHours));
		String maxAsst = jnResult.get("maxAsst").asText(ZERO);
		tuition.setMaxAsst(new BigDecimal(maxAsst));
		String maxHourlyCost = jnResult.get("maxHourlyCost").asText(ZERO);
		tuition.setMaxHourlyCost(new BigDecimal(maxHourlyCost));
		String maxHours = jnResult.get("maxHours").asText(ZERO);
		tuition.setMaxHours(new BigDecimal(maxHours));
		String maxPreDPHours = jnResult.get("maxPreDPHours").asText(ZERO);
		tuition.setMaxPreDPHours(new BigDecimal(maxPreDPHours));
		String autoApproval = jnResult.get("autoApproval").asText(ZERO);
		tuition.setAutoApproval(new BigDecimal(autoApproval));

		String strLastUpdatedDate = jnResult.get("lastUpdatedDate").asText(ZERO);
		SimpleDateFormat df = new SimpleDateFormat(RhdmDeserializer.DATE_PATTERN);
		Date lastUpdatedDate;
		try {
			lastUpdatedDate = df.parse(strLastUpdatedDate);
		} catch (ParseException e) {
			LOG.info("failed to parse date: {}", strLastUpdatedDate);
			lastUpdatedDate = null;
		}
		tuition.setLastUpdatedDate(lastUpdatedDate);

		LOG.info("About to leave method deserialize");
		return tuition;
	}

	@Override
	protected String getResponseFactName() {
		LOG.info("Entered method getResponseFactName");
		String responseFactName = "mil.mgae.rules.carloan.Tuition";
		LOG.info("About to leave method getResponseFactName");
		return responseFactName;
	}

}
