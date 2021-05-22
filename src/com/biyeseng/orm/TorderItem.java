package com.biyeseng.orm;

/**
 * 商品订单明细Bean
 */
public class TorderItem {
	// 商品订单明细ID
	private String id;
	// 订单ID
	private String order_id;
	// 商品ID
	private int goods_id;
	// 商品数量
	private int goods_quantity;
	// 商品状态
	private String goods_state;
	// 商品信息Bean
	private Tgoods goods;

	public int getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public int getGoods_quantity() {
		return goods_quantity;
	}

	public void setGoods_quantity(int goods_quantity) {
		this.goods_quantity = goods_quantity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public Tgoods getGoods() {
		return goods;
	}

	public void setGoods(Tgoods goods) {
		this.goods = goods;
	}

	public String getGoods_state() {
		return goods_state;
	}

	public void setGoods_state(String goodsState) {
		goods_state = goodsState;
	}

}
