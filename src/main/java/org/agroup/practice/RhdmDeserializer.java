package org.agroup.practice;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public abstract class RhdmDeserializer<T> extends StdDeserializer<T> {

	private static final long serialVersionUID = 1L;

	protected String responseFactName;

	public RhdmDeserializer() {
		this(null);
	}

	public RhdmDeserializer(Class<?> clazz) {
		super(clazz);
	}

	@Override
	abstract public T deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException;

}
