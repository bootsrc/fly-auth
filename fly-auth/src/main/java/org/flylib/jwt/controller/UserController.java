package org.flylib.jwt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.flylib.jwt.bean.User;
import org.flylib.jwt.bean.UserData;
import org.flylib.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * 登录
	 */
	@RequestMapping (value = "login", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	public ResponseEntity<String> login(HttpServletRequest request, HttpServletResponse response, @ModelAttribute UserData userData){
		return userService.login(request, response, userData);
	}
}
