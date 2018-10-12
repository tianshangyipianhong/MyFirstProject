package com.jredu.model;

public class UserMessage {
	private String umd;
	private String uname ;
	private String ucontent ;
	private String usexy ;
	private int uclick ;
	private String upubdate;
	private int canaddgood;
	private  String  uheadimg;
	
	public String getUheadimg() {
		return uheadimg;
	}
	public void setUheadimg(String uheadimg) {
		this.uheadimg = uheadimg;
	}
	public int getCanaddgood() {
		return canaddgood;
	}
	public void setCanaddgood(int canaddgood) {
		this.canaddgood = canaddgood;
	}
	public UserMessage() {
		super();
	}
	public UserMessage(String umd, String uname,  String ucontent, String usexy,int uclick,String upubdate) {
		super();
		this.umd = umd;
		this.uname = uname;
		this.ucontent = ucontent;
		this.usexy = usexy;
		this.uclick = uclick;
		this.upubdate = upubdate;
	}
	public String getUmd() {
		return umd;
	}
	public void setUmd(String umd) {
		this.umd = umd;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUcontent() {
		return ucontent;
	}
	public void setUcontent(String ucontent) {
		this.ucontent = ucontent;
	}
	public String getUsexy() {
		return usexy;
	}
	public void setUsexy(String usexy) {
		this.usexy = usexy;
	}
	public int getUclick() {
		return uclick;
	}
	public void setUclick(int uclick) {
		this.uclick = uclick;
	}
	public String getUpubdate() {
		return upubdate;
	}
	public void setUpubdate(String upubdate) {
		this.upubdate = upubdate;
	}
	
}
