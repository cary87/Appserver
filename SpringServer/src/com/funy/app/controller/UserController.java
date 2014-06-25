package com.funy.app.controller;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.funy.app.SessionManeger;
import com.funy.app.pojo.ClientSession;
import com.funy.app.pojo.User;
import com.funy.app.service.UserService;

@Controller()
public class UserController {
	
	@Resource(name = "userService")
	private UserService service;
	
	@RequestMapping("/")
	public ModelAndView welcome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("welcome");
		return modelAndView;
	}

	@RequestMapping(value = "/signup", method=RequestMethod.GET)
	public void signup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String req = IOUtils.toString(request.getInputStream());
		JSONObject json = JSONObject.fromObject(req);
		User user = (User) JSONObject.toBean(json, User.class);
		JSONObject obj = new JSONObject();
		try {
			service.insertUser(user);
			obj.put("errCode", 0);
			obj.put("message", "success");
		} catch (Exception e) {
			obj.put("errCode", -1);
			obj.put("message", "email exist");
		}
		
		response.getWriter().write(obj.toString());

	}

	@RequestMapping(value = "/signin", method=RequestMethod.POST)
	public void signin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String req = IOUtils.toString(request.getInputStream());
		JSONObject json = JSONObject.fromObject(req);
		User user = (User) JSONObject.toBean(json, User.class);
		User dbUser = service.login(user);
		JSONObject obj = new JSONObject();
		if(dbUser != null) {
			String token = UUID.randomUUID().toString();
			ClientSession session = new ClientSession(dbUser.getEmail(), token);
			SessionManeger.clientSessions.add(session);
			obj.put("errCode", 0);
			obj.put("token", token);
			obj.put("message", "success");
		} else {
			obj.put("errCode", -1);
			obj.put("message", "error password or not exist the account");
		}
		response.getWriter().write(obj.toString());
	}
	
	@RequestMapping(value = "/signout", method=RequestMethod.POST)
	public void signout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String req = IOUtils.toString(request.getInputStream());
		JSONObject json = JSONObject.fromObject(req);
		String email = json.optString("email","");
		String token = json.optString("token","");
		boolean logout = false;
		for (ClientSession item : SessionManeger.clientSessions) {
			if(email.equals(item.getKey()) && token.equals(item.getToken())) {
				SessionManeger.clientSessions.remove(item);
				logout = true;
				break;
			}
		}
		JSONObject obj = new JSONObject();
		if(logout) {
			obj.put("errCode", 0);
			obj.put("message", "logout success");
		} else {
			obj.put("errCode", -1);
			obj.put("message", "error");
		}
		response.getWriter().write(obj.toString());
	}
	
	@RequestMapping(value = "/updateToken", method=RequestMethod.POST)
	public void updateToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String req = IOUtils.toString(request.getInputStream());
		JSONObject json = JSONObject.fromObject(req);
		String email = json.optString("email","");
		String token = json.optString("token","");
		boolean updateTokenRes = false;
		for (ClientSession item : SessionManeger.clientSessions) {
			if(email.equals(item.getKey()) && token.equals(item.getToken())) {
				SessionManeger.clientSessions.remove(item);
				SessionManeger.clientSessions.add(new ClientSession(item.getKey(), UUID.randomUUID().toString()));
				updateTokenRes = true;
				break;
			}
		}
		JSONObject obj = new JSONObject();
		if(updateTokenRes) {
			obj.put("errCode", 0);
			obj.put("message", "updateToken success");
		} else {
			obj.put("errCode", -1);
			obj.put("message", "update token fail");
		}
		response.getWriter().write(obj.toString());
	}
}
