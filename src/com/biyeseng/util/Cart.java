package com.biyeseng.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.biyeseng.orm.Tgoods;
import com.biyeseng.orm.TorderItem;


public class Cart {

	protected Map<Integer, TorderItem> items;

	public Cart() {
		if (items == null) {
			items = new HashMap<Integer, TorderItem>();
		}
	}

	/**
	 * 添加商品
	 * 
	 * @param goodsId
	 * @param orderItem
	 */
	public void addGoods(Integer goodsId, TorderItem orderItem) {

		if (items.containsKey(goodsId)) {

			TorderItem _orderitem = items.get(goodsId);
			_orderitem.setGoods_quantity(_orderitem.getGoods_quantity()
					+ orderItem.getGoods_quantity());
			items.put(goodsId, _orderitem);
		} else {

			items.put(goodsId, orderItem);
		}
	}

	/**
	 * 删除商品
	 * 
	 * @param goodsId
	 */
	public void delGoods(Integer goodsId) {
		items.remove(goodsId);
	}

	/**
	 * 更新购物车商品数量
	 * 
	 * @param goodsId
	 *            商品ID
	 * @param quantity
	 *            数量
	 */
	public void updateCart(Integer goodsId, int quantity) {

		TorderItem orderItem = items.get(goodsId);
		orderItem.setGoods_quantity(quantity);
		items.put(goodsId, orderItem);
	}

	/**
	 * 计算购物车总价格
	 * 
	 * @return 总价格
	 */
	public int getTotalPrice() {

		int totalPrice = 0;
		for (Iterator<TorderItem> it = items.values().iterator(); it.hasNext();) {

			TorderItem orderItem = it.next();
			Tgoods goods = orderItem.getGoods();
			int quantity = orderItem.getGoods_quantity();
			totalPrice += goods.getJiage() * quantity;
		}
		return totalPrice;
	}

	public Map<Integer, TorderItem> getItems() {
		return items;
	}

}
