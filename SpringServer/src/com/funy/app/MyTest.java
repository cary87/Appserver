package com.funy.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.funy.app.pojo.User;


public class MyTest {
	//private static final String SERVER_URL = "http://ccserver.sinaapp.com/";
	private static final String SERVER_URL = "http://127.0.0.1:8080/SpringServer/";
	private static final String EMAIL = "test@qq.com";
	private String token = null;
	
	@Test
	public void signup() {
		User user = new User();
		user.setAge(88);
		user.setName("Devin");
		user.setSex("boy");
		user.setEmail(EMAIL);
		user.setPassword("test123456");
		String json = JSONObject.fromObject(user).toString();
		request("signup", json);
		
	}
	
	@Test
	public void signin() {
		User user = new User();
		user.setEmail(EMAIL);
		user.setPassword("test123456");
		String json = JSONObject.fromObject(user).toString();
		request("signin", json);
	}
	
	@Test
	public void signout() {
		User user = new User();
		user.setEmail(EMAIL);
		String json = JSONObject.fromObject(user).toString();
		request("signout", json);
	}
	
	@Test 
	public void updateToken() {
		User user = new User();
		user.setEmail(EMAIL);
		String json = JSONObject.fromObject(user).toString();
		request("updateToken", json);
	}
	
	public void request(String action, String json) {
		HttpURLConnection urlConn = null;
		OutputStream os = null;
		InputStream is = null;
		try {
			URL url = new URL(SERVER_URL + action);
			urlConn = (HttpURLConnection) url.openConnection();
			urlConn.setDoOutput(true);
			urlConn.setDoInput(true);
			urlConn.setUseCaches(false);
			urlConn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			urlConn.setRequestMethod("POST");
			urlConn.setConnectTimeout(100000);
			urlConn.connect();
			os = urlConn.getOutputStream();
			os.write(json.getBytes());
			os.flush();
			System.out.println("ResponseCode: " + urlConn.getResponseCode());
			is = urlConn.getInputStream();
			String res = IOUtils.toString(is);
			if(action.equals("signin")) {
				token = JSONObject.fromObject(res).optString("token");
				System.out.println("token:" + token);
			}
			System.out.println("ResponseBody: " + res);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(urlConn != null) {
				urlConn.disconnect();
			}
			if(os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
