package com.biyeseng.orm;

/**
 * 商品入库信息Bean
 */
public class Tjin {
	// 入库信息ID
	private int id;
	// 商品名称
	private String mingcheng;
	// 商品ID
	private int goodsid;
	// 操作人
	private String ren;
	// 操作日期
	private String date;

	private int count;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMingcheng() {
		return mingcheng;
	}

	public void setMingcheng(String mingcheng) {
		this.mingcheng = mingcheng;
	}

	public int getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}

	public String getRen() {
		return ren;
	}

	public void setRen(String ren) {
		this.ren = ren;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
