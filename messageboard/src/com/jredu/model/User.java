package com.jredu.model;

public class User {
	private String username;
	private String password;
	private String sexy;
	private String idcard;
	private String headimg;
	public User() {
		super();
	}
	public User(String username, String password, String idcard,String sexy) {
		super();
		this.username = username;
		this.password = password;
		this.idcard = idcard;
		this.sexy = sexy;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", sexy=" + sexy + ", idcard=" + idcard + "]";
	}
	public String getSexy() {
		return sexy;
	}
	public void setSexy(String sexy) {
		this.sexy = sexy;
	}
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	
	
}
