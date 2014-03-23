package com.funy.app;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.funy.app.pojo.ClientSession;

public class SessionManeger {
	
	public static List<ClientSession> clientSessions =  Collections.synchronizedList(new LinkedList<ClientSession>()); 
	
}
