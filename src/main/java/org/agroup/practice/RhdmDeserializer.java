package org.agroup.practice;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public abstract class RhdmDeserializer<T> extends StdDeserializer<T> {
	private static final Logger LOG = LoggerFactory.getLogger(RhdmDeserializer.class);
	private static final long serialVersionUID = 1L;
	protected static final String DATE_PATTERN = "dd-MM-yyyy hh:mm:ss";
	protected static final String SUCCESS = "SUCCESS";
	protected static final String DEFAULT = "SystemDefault";

	protected String success;
	protected String msg;

	public RhdmDeserializer() {
		this(null);
	}

	public RhdmDeserializer(Class<?> clazz) {
		super(clazz);
	}

	@Override
	abstract public T deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException;

	abstract protected String getResponseFactName();

	protected JsonNode getResultNode(JsonParser parser) throws IOException {
		LOG.info("Entered method getResultNode");

		ObjectCodec codec = parser.getCodec();
		JsonNode jnRoot = codec.readTree(parser);

		String success = jnRoot.get("type").asText(DEFAULT);
		String msg = jnRoot.get("msg").asText(DEFAULT);

		LOG.info("success/msg=={}/{}", success, msg);

		String responseFactName = this.getResponseFactName();
		JsonNode retVal = jnRoot;

		if (success.equals(SUCCESS)) {
			JsonNode jnExecution = jnRoot.get("result").get("execution-results");

			if (jnExecution.has("results")) {
				JsonNode r = jnExecution.get("results");
				retVal = r.get(0).get("value").get(responseFactName);
			} else {
				LOG.info("no jnResults; nothing to do");
			}
		} else {
			LOG.info("no success; nothing to do");
		}

		this.success = success;
		this.msg = msg;

		LOG.info("About to leave method getResultNode");
		return retVal;
	}
}
