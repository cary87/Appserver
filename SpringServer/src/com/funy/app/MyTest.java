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
	
	@Test
	public void testConnect() {
		HttpURLConnection urlConn = null;
		OutputStream os = null;
		InputStream is = null;
		try {
			URL url = new URL("http://localhost:8080/SpringServer/signup");
			urlConn = (HttpURLConnection) url.openConnection();

			urlConn.setDoOutput(true);

			urlConn.setDoInput(true);

			urlConn.setUseCaches(false);
			
			urlConn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

			urlConn.setRequestMethod("POST");

			urlConn.connect();

			os = urlConn.getOutputStream();
			User user = new User();
			user.setAge(88);
			user.setName("Kevin");
			user.setSex("boy");
			os.write(JSONObject.fromObject(user).toString().getBytes());
			os.flush();

			System.out.println("ResponseCode: " + urlConn.getResponseCode());
			is = urlConn.getInputStream();
			System.out.println("ResponseBody: " + IOUtils.toString(is));

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
