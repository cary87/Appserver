package com.funy.app.service;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funy.app.SessionManeger;
import com.funy.app.dao.UserDao;
import com.funy.app.pojo.ClientSession;
import com.funy.app.pojo.User;
@Service("userService")
public class UserService {
	
	@Resource(name="userDao")
	private UserDao dao;
	
	public UserDao getDao() {
		return dao;
	}
	public void setDao(UserDao dao) {
		this.dao = dao;
	}
	@Transactional
	public List<User> findByLimit(int limit){
		return dao.findByLimit(limit);
	}
	@Transactional
	public boolean insertUser(User user){
		dao.inseartUser(user);
		return true;
	}
	@Transactional
	public boolean deleteById(long id){
		dao.deleteById(id);
		return true;
	}
	@Transactional
	public boolean  updateUser(User user){
		dao.updateUser(user);
		return true;
	}
	@Transactional
	public List<User> findByName(String name){
		return dao.findByName(name);
	}
	@Transactional
	public User findById(long id){
		return dao.findById(id);
	}
	@Transactional
	public User login(User user) {
		return dao.findWithEmailAndPassword(user);
	}
	@Scheduled(cron="0/60 * *  * * ? ")   //每60秒执行一次  
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
