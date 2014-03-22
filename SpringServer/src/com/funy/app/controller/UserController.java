package com.funy.app.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.funy.app.pojo.User;
import com.funy.app.service.UserService;

@Controller()
public class UserController {
	
	@Resource(name = "userService")
	private UserService service;

	@RequestMapping(value = "/signup", method=RequestMethod.POST)
	public void signup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String req = IOUtils.toString(request.getInputStream());
		JSONObject json = JSONObject.fromObject(req);
		User user = (User) JSONObject.toBean(json, User.class);
		service.insertUser(user);
		JSONObject obj = new JSONObject();
		obj.put("errCode", 0);
		obj.put("message", "success");
		response.getWriter().write(obj.toString());

	}

	@RequestMapping(value = "/signin", method=RequestMethod.POST)
	public void signin(HttpServletRequest request, HttpServletResponse response) throws Exception {

	}
}
