package org.flylib.jwt.util;

import org.flylib.jwt.config.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.alibaba.fastjson.JSON;

public class ResponseUtil {
	/**
	 * 请求返回数据处理
	 * @param res
	 * @return
	 */
	public static ResponseEntity<String> general(CommonResponse res){
		return new ResponseEntity<String>(JSON.toJSONString(res), HttpStatus.OK);
	}
	
	/**
	 * 成功请求
	 * @param data
	 * @return
	 */
	public static ResponseEntity<String> success(Object data){
		CommonResponse res = new CommonResponse();
		res.setCode(Constant.RESCODE_SUCCESS);
		res.setData(data);
		return general(res);
	}
	
	public static ResponseEntity<String> success(){
		CommonResponse res = new CommonResponse();
		res.setCode(Constant.RESCODE_SUCCESS);
		return general(res);
	}
	
	public static ResponseEntity<String> success(String msg){
		CommonResponse res = new CommonResponse();
		res.setCode(Constant.RESCODE_SUCCESS_MSG);
		res.setMsg(msg);
		return general(res);
	}
	
	/**
	 * 请求抛出异常
	 * @param msg
	 * @return
	 */
	public static ResponseEntity<String> exception(String msg){
		CommonResponse res = new CommonResponse();
		res.setCode(Constant.RESCODE_EXCEPTION);
		res.setMsg(msg);
		return general(res);
	}
	
	public static ResponseEntity<String> unKonwException(){
		CommonResponse res = new CommonResponse();
		res.setCode(Constant.RESCODE_EXCEPTION);
		res.setMsg("请稍后再试");
		return general(res);
	}
	
	/**
	 * 自定义
	 * @param code
	 * @param msg
	 * @return
	 */
	public static ResponseEntity<String> custom(Integer code, String msg){
		CommonResponse res = new CommonResponse();
		res.setCode(code);
		res.setMsg(msg);
		return general(res);
	}
}
