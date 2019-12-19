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

public class RhdmReader<T> {
	private static final Logger LOG = LoggerFactory.getLogger(RhdmReader.class);

	private RhdmResponse<T> rhdmResponse;
	private RhdmDeserializer<T> rhdmDeserializer;
	private Class<T> clazz;
	private ObjectMapper objectMapper;
	private boolean isPreparedToRead;

	public RhdmReader() {
		this.isPreparedToRead = false;
	}

	public RhdmResponse<T> read(File rawJson) throws JsonParseException, JsonMappingException, IOException {
		LOG.info("Begin method read");
		RhdmResponse<T> rhdmResponse = new RhdmResponse<>();

		if (this.isPreparedToRead) {
			LOG.info("Wrap rawData with RhdmResponse<{}>", this.clazz.getName());

			T rawData = this.objectMapper.readValue(rawJson, this.clazz);
			rhdmResponse.setData(rawData);

			String msg = this.rhdmDeserializer.getMsg();
			String success = this.rhdmDeserializer.getSuccess();
			boolean successFlag = success.equalsIgnoreCase(RhdmDeserializer.SUCCESS) && rawData != null;
			rhdmResponse.setMsg(msg);
			rhdmResponse.setSuccess(successFlag);
			LOG.info("rhdmResponse.getMsg=={}", msg);
			LOG.info("rhdmResponse.isSuccess? {}", success);
		} else {
			rhdmResponse.setSuccess(false);
			LOG.info("cannot read without being prepared: {}", this.isPreparedToRead);
		}

		this.rhdmResponse = rhdmResponse;

		LOG.info("About to leave method read");
		return this.rhdmResponse;
	}

	public void prepareToRead(Class<T> clazz, RhdmDeserializer<T> deserializer) {
		LOG.info("Entered method prepareToRead");

		Version version = new Version(1, 0, 0, null, null, null);
		SimpleModule module = new SimpleModule(clazz.getName(), version);
		module.addDeserializer(clazz, deserializer);

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(module);

		this.clazz = clazz;
		this.rhdmDeserializer = deserializer;
		this.objectMapper = mapper;
		this.isPreparedToRead = true;
		LOG.info("About to leave method prepareToRead");
	}
}
