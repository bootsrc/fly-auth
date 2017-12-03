package org.flylib.jwt.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.flylib.jwt.bean.User;
import org.flylib.jwt.bean.UserData;
import org.flylib.jwt.config.Constant;
import org.flylib.jwt.util.JwtUtil;
import org.flylib.jwt.util.ResponseUtil;
import org.flylib.jwt.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

@Component
public class UserService {
	
	private static final Logger logger = LogManager.getLogger(UserService.class);
	
	@Autowired
	private JwtUtil jwt;

	public ResponseEntity<String> login(HttpServletRequest request, HttpServletResponse response, UserData userData){
		try {
			if(!"admin".equals(userData.getAccount()) || !"123456".equals(userData.getPassword())){
				return ResponseUtil.exception("账号或者密码错误");
			}
			userData.setRoleId(1L);
			User user = UserUtil.userDataToUser(userData);
			String subject = JwtUtil.generalSubject(user);
			String token = jwt.createJWT(Constant.JWT_ID, subject, Constant.JWT_TTL);
			String refreshToken = jwt.createJWT(Constant.JWT_ID, subject, Constant.JWT_REFRESH_TTL);
			JSONObject jo = new JSONObject();
			jo.put("token", token);
			jo.put("refreshToken", refreshToken);
			return ResponseUtil.success(jo);
		} catch (Exception e) {
			logger.error(e);
			return ResponseUtil.unKonwException();
		}
	}
}
