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
		final String SUCCESS = "SUCCESS";
		final String DEFAULT = "SystemDefault";
		final String KEY = this.getResponseFactName();

		ObjectCodec codec = parser.getCodec();
		JsonNode jnRoot = codec.readTree(parser);

		String success = jnRoot.get("type").asText(DEFAULT);
		String msg = jnRoot.get("msg").asText(DEFAULT);

		LOG.info("success/msg=={}/{}", success, msg);

		JsonNode retVal = jnRoot;

		if (success.equals(SUCCESS)) {
			retVal = jnRoot.get("result").get("execution-results");

			if (retVal.has("results")) {
				JsonNode r = retVal.get("results");
				retVal = r.get(0).get("value").get(KEY);
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
