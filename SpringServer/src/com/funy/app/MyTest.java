package com.funy.app;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.junit.Test;


public class MyTest {
	
	@Test
	public void testConnect() {
		HttpURLConnection urlConn;
		try {
			URL url = new URL("http://localhost:8080/SpringServer/search");
			urlConn = (HttpURLConnection) url.openConnection();

			urlConn.setDoOutput(true);

			urlConn.setDoInput(true);

			urlConn.setUseCaches(false);

			urlConn.setRequestMethod("POST");

			urlConn.connect();

			OutputStream outStrm = urlConn.getOutputStream();
			
			ObjectOutputStream oos = new ObjectOutputStream(outStrm);

			oos.writeObject(new String("2222"));

			oos.flush();

			oos.close();
			
			System.out.println("ResponseCode: " + urlConn.getResponseCode());
			System.out.println("ResponseBody: " + IOUtils.toString(urlConn.getInputStream()));

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		
	}

}
