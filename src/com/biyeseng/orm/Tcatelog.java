package com.biyeseng.orm;

/**
 * 商品类别Bean
 */
public class Tcatelog
{
	//类别编号
	private int id;
	//类别名称
	private String name;
	//是否删除
	private String del;
	
	public String getDel()
	{
		return del;
	}
	public void setDel(String del)
	{
		this.del = del;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
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

}
