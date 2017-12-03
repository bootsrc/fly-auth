package org.flylib.jwt.util;

import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.flylib.jwt.bean.User;
import org.flylib.jwt.config.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	@Value("${spring.profiles.active}")
    private String profiles;
	
	/**
	 * 由字符串生成加密key
	 * @return
	 */
	public SecretKey generalKey(){
		String stringKey = profiles+Constant.JWT_SECRET;
		byte[] encodedKey = Base64.decodeBase64(stringKey);
	    SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
	    return key;
	}

	/**
	 * 创建jwt
	 * @param id
	 * @param subject
	 * @param ttlMillis
	 * @return
	 * @throws Exception
	 */
	public String createJWT(String id, String subject, long ttlMillis) throws Exception {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		SecretKey key = generalKey();
		JwtBuilder builder = Jwts.builder()
			.setId(id)
			.setIssuedAt(now)
			.setSubject(subject)
		    .signWith(signatureAlgorithm, key);
		if (ttlMillis >= 0) {
		    long expMillis = nowMillis + ttlMillis;
		    Date exp = new Date(expMillis);
		    builder.setExpiration(exp);
		}
		return builder.compact();
	}
	
	/**
	 * 解密jwt
	 * @param jwt
	 * @return
	 * @throws Exception
	 */
	public Claims parseJWT(String jwt) throws Exception{
		SecretKey key = generalKey();
		Claims claims = Jwts.parser()         
		   .setSigningKey(key)
		   .parseClaimsJws(jwt).getBody();
		return claims;
	}
	
	/**
	 * 生成subject信息, 去掉密码字段pwd
	 * @param user
	 * @return
	 */
	public static String generalSubject(User user){
		JSONObject jo = new JSONObject();
		jo.put("userId", user.getUserId());
		jo.put("roleId", user.getRoleId());
		return jo.toJSONString();
	}
}
