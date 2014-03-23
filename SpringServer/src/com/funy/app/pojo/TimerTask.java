package com.funy.app.pojo;

import java.util.Iterator;

import com.funy.app.SessionManeger;

public class TimerTask {
	
	public void checkSessionValid() {
		Iterator<ClientSession> it = SessionManeger.clientSessions.iterator();
		while(it.hasNext()) {
			ClientSession session = it.next();
			if(!session.isValid()) {
				it.remove();
			}
		}
	}

}
