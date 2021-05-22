package com.biyeseng.service;

import javax.servlet.http.HttpSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import com.biyeseng.util.Cart;

/**
 * 购物车服务类
 */
public class CartService {
	/**
	 * 修改购物车商品数量
	 * @param goodsId
	 * @param quantity
	 * @return
	 */
	public String modiNum(int goodsId, int quantity) {
		String result = "";
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebContext ctx = WebContextFactory.get();
		HttpSession session = ctx.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		cart.updateCart(goodsId, quantity);
		session.setAttribute("cart", cart);

		result = "yes";

		return result;
	}

	/**
	 * 删除购物车商品
	 * @param goodsId
	 * @return
	 */
	public String delGoodsFromCart(int goodsId) {
		WebContext ctx = WebContextFactory.get();
		HttpSession session = ctx.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		cart.delGoods(goodsId);
		session.setAttribute("cart", cart);
		return "yes";
	}

	/**
	 * 清空购物车
	 * @return
	 */
	public String clearCart() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebContext ctx = WebContextFactory.get();
		HttpSession session = ctx.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		cart.getItems().clear();
		session.setAttribute("cart", cart);
		return "yes";
	}

}
