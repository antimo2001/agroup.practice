package org.agroup.practice;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.liferay.portal.kernel.json.JSONFactoryUtil;
//import com.liferay.portal.kernel.log.Log;
//import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class RhdmResponse<T> {
	private static final Logger LOG = LoggerFactory.getLogger(RhdmResponse.class);

	private T data;

	// TODO @Reference RhdmDeserializer
	private RhdmDeserializer<T> deserializer;

	public RhdmResponse() {
		this.data = null;
	}

	public T read(File rawJson, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
		LOG.info("Begin method read");
		ObjectMapper mapper = new ObjectMapper();
		Version version = new Version(1, 0, 0, null, null, null);
		LOG.info("clazz.getName: {}", clazz.getName());
		SimpleModule module = new SimpleModule(clazz.getName(), version);

		module.addDeserializer(clazz, deserializer);
		mapper.registerModule(module);

		this.data = mapper.readValue(rawJson, clazz);

		LOG.info("About to leave method read");
		return this.data;
	}

	public void setDeserializer(RhdmDeserializer<T> deserializer) {
		this.deserializer = deserializer;
	}

	public T getData() {
		return data;
	}

}
