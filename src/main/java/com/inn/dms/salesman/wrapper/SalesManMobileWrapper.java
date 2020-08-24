package com.inn.dms.salesman.wrapper;

public class SalesManMobileWrapper {
	
	public SalesManMobileWrapper()
	{}
	public SalesManMobileWrapper (String name,long mobile)
	{
		this.name=name;
		this.mobile=mobile;
	}
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private long mobile;
	@Override
	public String toString() {
		return "SalesManMobileWrapper [name=" + name + ", mobile=" + mobile + "]";
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

}
