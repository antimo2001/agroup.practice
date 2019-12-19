package org.agroup.practice;

import java.io.IOException;
import java.math.BigDecimal;
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

		Tuition tuition = null;

		if (this.success.equalsIgnoreCase(RhdmDeserializer.SUCCESS)) {
			String rejReason = jnResult.get("rejReason").asText(RhdmDeserializer.SYSTEM_DEFAULT);
			String finAsst = jnResult.get("finAsst").asText(ZERO);
			String studOblg = jnResult.get("studOblg").asText(ZERO);
			String totalAsstRequested = jnResult.get("totalAsstRequested").asText(ZERO);
			String totalHoursRequested = jnResult.get("totalHoursRequested").asText(ZERO);
			String updatedPreDPHours = jnResult.get("updatedPreDPHours").asText(ZERO);
			String updatedUsedAsst = jnResult.get("updatedUsedAsst").asText(ZERO);
			String updatedUsedHours = jnResult.get("updatedUsedHours").asText(ZERO);
			String maxAsst = jnResult.get("maxAsst").asText(ZERO);
			String maxHourlyCost = jnResult.get("maxHourlyCost").asText(ZERO);
			String maxHours = jnResult.get("maxHours").asText(ZERO);
			String maxPreDPHours = jnResult.get("maxPreDPHours").asText(ZERO);
			boolean autoApproval = jnResult.get("autoApproval").asBoolean(false);
			String strLastUpdatedDate = jnResult.get("lastUpdatedDate").asText(ZERO);
			// SimpleDateFormat df = new SimpleDateFormat(RhdmDeserializer.DATE_PATTERN);
			long lLastUpdatedDate = Long.parseLong(strLastUpdatedDate);

			tuition = new Tuition();
			tuition.setRejReason(rejReason);
			tuition.setFinAsst(new BigDecimal(finAsst));
			tuition.setStudOblg(new BigDecimal(studOblg));
			tuition.setTotalAsstRequested(new BigDecimal(totalAsstRequested));
			tuition.setTotalHoursRequested(new BigDecimal(totalHoursRequested));
			tuition.setUpdatedPreDPHours(new BigDecimal(updatedPreDPHours));
			tuition.setUpdatedUsedAsst(new BigDecimal(updatedUsedAsst));
			tuition.setUpdatedUsedHours(new BigDecimal(updatedUsedHours));
			tuition.setMaxAsst(new BigDecimal(maxAsst));
			tuition.setMaxHourlyCost(new BigDecimal(maxHourlyCost));
			tuition.setMaxHours(new BigDecimal(maxHours));
			tuition.setMaxPreDPHours(new BigDecimal(maxPreDPHours));
			tuition.setAutoApproval(autoApproval);
			tuition.setLastUpdatedDate(new Date(lLastUpdatedDate));
		} else {
			LOG.info("no success!");
			LOG.info("success/msg=={}/{}", this.success, this.msg);
		}

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
