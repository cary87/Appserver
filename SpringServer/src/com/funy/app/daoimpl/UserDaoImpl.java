package com.funy.app.daoimpl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.funy.app.dao.SuperDao;
import com.funy.app.dao.UserDao;
import com.funy.app.pojo.User;
@Component("userDao")
public class UserDaoImpl extends SuperDao implements UserDao {

	@Override
	public void deleteById(long id) {
		this.template.delete("User.deleteById", id);
	}

	@Override
	public void inseartUser(User user) {
		this.template.insert("User.saveUser", user);
	}

	@Override
	public void updateUser(User user) {
		this.template.update("User.updateUser", user);
	}

	@Override
	public List<User> findByLimit(int limit) {
		return this.template.selectList("User.findByLimit", limit);
		
	}

	@Override
	public List<User> findByName(String name) {
		return this.template.selectList("User.findByName", name);
	}

	@Override
	public User findById(long id) {
		return this.template.selectOne("User.findById", id);
	}

	@Override
	public User findWithEmailAndPassword(User user) {
		return this.template.selectOne("User.findWithEmailAndPassword", user);
	}
	
}
