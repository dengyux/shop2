package com.biyeseng.orm;

/**
 * 管理员Bean
 */
public class Tadmin
{
	//用户ID
	private int userId;
	//账号
	private String userName;
	//密码
	private String userPw;
	//管理员类型
	private String userType;

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getUserPw()
	{
		return userPw;
	}

	public void setUserPw(String userPw)
	{
		this.userPw = userPw;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	

}
