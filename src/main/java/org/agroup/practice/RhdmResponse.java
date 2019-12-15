package org.agroup.practice;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class RhdmResponse<T> {
	private static final Logger LOG = LoggerFactory.getLogger(RhdmResponse.class);

	private T data;
	private Class<T> clazz;
	private ObjectMapper objectMapper;
	private boolean isPreparedToRead;

	public RhdmResponse() {
		this.isPreparedToRead = false;
	}

	public T read(File rawJson) throws JsonParseException, JsonMappingException, IOException {
		LOG.info("Begin method read");

		if (this.isPreparedToRead) {
			this.data = this.objectMapper.readValue(rawJson, this.clazz);
		} else {
			LOG.info("cannot read without being prepared: {}", this.isPreparedToRead);
		}

		LOG.info("About to leave method read");
		return this.data;
	}

	public void prepareToRead(Class<T> clazz, RhdmDeserializer<T> deserializer) {
		LOG.info("Entered method prepareToRead");

		Version version = new Version(1, 0, 0, null, null, null);
		LOG.info("clazz.getName: {}", clazz.getName());
		SimpleModule module = new SimpleModule(clazz.getName(), version);
		module.addDeserializer(clazz, deserializer);

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(module);

		this.clazz = clazz;
		this.objectMapper = mapper;
		this.isPreparedToRead = true;
		LOG.info("About to leave method prepareToRead");
	}

	public T getData() {
		return data;
	}

}
