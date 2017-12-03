package org.flylib.jwt.config;

import io.jsonwebtoken.Claims;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.flylib.jwt.bean.User;
import org.flylib.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author Frank Liu(Liu Shaoming)(liushaomingdev@163.com)
 *
 */
@WebServlet("/refreshToken" )
public class RefreshToken extends HttpServlet{

	private Logger logger = LogManager.getLogger(RefreshToken.class);
	
	private static final long serialVersionUID = 2573245614706073195L;
	
	@Autowired
	private JwtUtil jwt;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
    }

	/**
	 * 利用HTML5的EventSource来实现服务器推送数据到浏览器客户端
	 * response.setContentType( "text/event-stream;charset=UTF-8" );
     *       response.setHeader( "Cache-Control", "no-cache" );
     *       response.setHeader( "Connection", "keep-alive" );
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	response.setContentType( "text/event-stream;charset=UTF-8" );
            response.setHeader( "Cache-Control", "no-cache" );
            response.setHeader( "Connection", "keep-alive" );
            PrintWriter out = response.getWriter();
        	String token = request.getParameter("token");
        	Claims claims = jwt.parseJWT(token);
     		String json = claims.getSubject();
     		User user = JSONObject.parseObject(json, User.class);
     		String subject = JwtUtil.generalSubject(user);
     		String refreshToken = jwt.createJWT(Constant.JWT_ID, subject, Constant.JWT_TTL);
     		// retry:表示每间隔多长数据，服务器推送一次event到浏览器，如果retry设置了，就只能推送重复的event.
     		// data：表示推送的数据
            out.print("retry: "+Constant.JWT_REFRESH_INTERVAL+ "\n" );
     		out.print("data: "+refreshToken+"\n\n" );
            out.flush();
          } catch (Exception e) {
        	  logger.error(e);
          }
     }

}
