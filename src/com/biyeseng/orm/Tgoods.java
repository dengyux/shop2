package com.biyeseng.orm;

/**
 * 商品信息Bean
 */
public class Tgoods {
	//商品ID
	private int id;
	//商品类别ID
	private int catelog_id;
	//商品编号
	private String bianhao;
	//商品名称
	private String mingcheng;
	//商品介绍
	private String jieshao;
	//商品品牌
	private String pinpai;
	//商品图片附件
	private String fujian;
	//商品价格
	private int jiage;
	//商品库存数量
	private int kucun;
	//是否删除
	private String del;

	public String getBianhao() {
		return bianhao;
	}

	public void setBianhao(String bianhao) {
		this.bianhao = bianhao;
	}

	public int getCatelog_id() {
		return catelog_id;
	}

	public void setCatelog_id(int catelog_id) {
		this.catelog_id = catelog_id;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public String getFujian() {
		return fujian;
	}

	public void setFujian(String fujian) {
		this.fujian = fujian;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJieshao() {
		return jieshao;
	}

	public void setJieshao(String jieshao) {
		this.jieshao = jieshao;
	}

	public int getKucun() {
		return kucun;
	}

	public void setKucun(int kucun) {
		this.kucun = kucun;
	}

	public String getMingcheng() {
		return mingcheng;
	}

	public void setMingcheng(String mingcheng) {
		this.mingcheng = mingcheng;
	}

	

	public String getPinpai() {
		return pinpai;
	}

	public void setPinpai(String pinpai) {
		this.pinpai = pinpai;
	}

	public int getJiage() {
		return jiage;
	}

	public void setJiage(int jiage) {
		this.jiage = jiage;
	}

}
