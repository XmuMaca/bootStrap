package com.server.bean;

public class Account 
{
	private String id;
	
	private String easemobId;
	
	private String name;
	
	private String password;
	
	private String icon;
	
	private String gender;
	
	private String location;
	
	private String email;
	
	private String phone;
	
	private String identity;
	
	private String credit;
	
	private int isBaned; 
	
	private String isPublic;
	
	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}
	
	public String getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public int getIsBaned() {
		return isBaned;
	}

	public void setIsBaned(int isBaned) {
		this.isBaned = isBaned;
	}
	
	public String getEasemobId() {
		return easemobId;
	}

	public void setEasemobId(String easemobId) {
		this.easemobId = easemobId;
	}

	public Account()
	{
		
	}

}
