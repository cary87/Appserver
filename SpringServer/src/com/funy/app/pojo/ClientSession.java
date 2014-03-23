package com.funy.app.pojo;

import com.funy.app.ServerConstant;

public class ClientSession {
	private String key;
	
	private String token;
	
	private long createTime;
	
	public ClientSession(String key, String token) {
		this.key = key;
		this.token = token;
		this.createTime = System.currentTimeMillis();
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public boolean isValid() {
		return System.currentTimeMillis() - createTime < ServerConstant.TOKEN_EFFECTIVE_TIME;
	}

}
