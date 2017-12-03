package org.flylib.jwt.bean;

public class UserData {
	private String account;
	private Long userId;
	private Long roleId;
	private String password;
	
	public final String getAccount() {
		return account;
	}
	public final void setAccount(String account) {
		this.account = account;
	}
	public final Long getUserId() {
		return userId;
	}
	public final void setUserId(Long userId) {
		this.userId = userId;
	}
	public final Long getRoleId() {
		return roleId;
	}
	public final void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public final String getPassword() {
		return password;
	}
	public final void setPassword(String password) {
		this.password = password;
	}
}
