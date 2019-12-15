package org.agroup.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RhdmResponse<T> {
	private static final Logger LOG = LoggerFactory.getLogger(RhdmResponse.class);

	private boolean success;
	private String msg;
	private T data;

	public RhdmResponse() {
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		LOG.info("Entered method setData");
		LOG.info("Parameterized type class name=={}", data.getClass().getName());
		this.data = data;
		LOG.info("About to leave method setData");
	}
}
