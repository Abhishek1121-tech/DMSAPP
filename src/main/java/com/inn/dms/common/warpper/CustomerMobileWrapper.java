package com.inn.dms.common.warpper;

public class CustomerMobileWrapper {

	public CustomerMobileWrapper()
	{}
	public CustomerMobileWrapper (String name,long mobile)
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

	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	@Override
	public String toString() {
		return "CustomerMobileWrapper [name=" + name + ", mobile=" + mobile + "]";
	}

	
}

