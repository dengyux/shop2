package com.biyeseng.orm;

/**
 * 商品订单ID
 */
public class Torder implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// 商品订单ID
	private String id;
	// 商品编号
	private String bianhao;
	// 下单时间
	private String shijian;
	// 订单状态
	private String zhuangtai;
	// 收货地址
	private String songhuodizhi;
	// 付款方式
	private String fukuanfangshi;
	// 商品金额
	private int jine = 0;
	// 会员ID
	private String user_id;

	public Torder() {
	}

	public String getBianhao() {
		return bianhao;
	}

	public void setBianhao(String bianhao) {
		this.bianhao = bianhao;
	}

	public String getFukuanfangshi() {
		return fukuanfangshi;
	}

	public void setFukuanfangshi(String fukuanfangshi) {
		this.fukuanfangshi = fukuanfangshi;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getJine() {
		return jine;
	}

	public void setJine(int jine) {
		this.jine = jine;
	}

	public String getShijian() {
		return shijian;
	}

	public void setShijian(String shijian) {
		this.shijian = shijian;
	}

	public String getSonghuodizhi() {
		return songhuodizhi;
	}

	public void setSonghuodizhi(String songhuodizhi) {
		this.songhuodizhi = songhuodizhi;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getZhuangtai() {
		return zhuangtai;
	}

	public void setZhuangtai(String zhuangtai) {
		this.zhuangtai = zhuangtai;
	}

}