package org.flylib.jwt.bean;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = -8036219797322639507L;

	private String account;
	private Long userId;
	private Long roleId;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
}
