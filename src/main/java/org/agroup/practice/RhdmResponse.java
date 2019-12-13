package org.agroup.practice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.liferay.portal.kernel.json.JSONFactoryUtil;
//import com.liferay.portal.kernel.log.Log;
//import com.liferay.portal.kernel.log.LogFactoryUtil;

public class RhdmResponse<T> {
    private static final Logger LOG = LoggerFactory.getLogger(RhdmResponse.class);

	private List<T> results;
	private String msg;
	private boolean success;

	public RhdmResponse() {
		this.results = new ArrayList<T>();
		this.success = false;
	}

	public List<T> getData() {
		return results;
	}

	public void add(T elem) {
		this.results.add(elem);
	}

	public void addAll(Collection<T> elems) {
		this.results.addAll(elems);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
