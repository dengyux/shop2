package com.biyeseng.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.biyeseng.dao.DB;
import com.biyeseng.orm.Tcatelog;
import com.biyeseng.orm.Tgoods;
import com.biyeseng.orm.Torder;
import com.biyeseng.orm.TorderItem;
import com.biyeseng.orm.TpingItem;

/**
 * 购物服务类
 */
public class ShopService {
	/**
	 * 获得商品类别
	 * 
	 * @return 商品类别
	 */
	public static List<Tcatelog> catelogList() {
		List<Tcatelog> catelogList = new ArrayList<Tcatelog>();
		String sql = "select * from t_catelog where del='no'";
		Object[] params = {};
		DB mydb = new DB();
		try {
			mydb.doPstm(sql, params);
			ResultSet rs = mydb.getRs();
			while (rs.next()) {
				Tcatelog catelog = new Tcatelog();
				catelog.setId(rs.getInt("id"));
				catelog.setName(rs.getString("name"));
				catelogList.add(catelog);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mydb.closed();
		return catelogList;
	}

	/**
	 * 获得会员账号
	 * 
	 * @param id
	 *            会员ID
	 * @return 会员账号
	 */
	public static String getUserName(String id) {
		String name = "";

		String sql = "select * from t_user where id=?";
		Object[] params = { id };
		DB mydb = new DB();
		try {
			mydb.doPstm(sql, params);
			ResultSet rs = mydb.getRs();
			if (rs.next()) {
				name = rs.getString("loginname");
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mydb.closed();
		return name;
	}

	/**
	 * 获得商品信息
	 * 
	 * @param id
	 *            商品ID
	 * @return 商品信息
	 */
	public static Tgoods getGoods(int id) {
		Tgoods goods = new Tgoods();

		String sql = "select * from t_goods where id=?";
		Object[] params = { id };
		DB mydb = new DB();
		try {
			mydb.doPstm(sql, params);
			ResultSet rs = mydb.getRs();
			rs.next();

			goods.setId(rs.getInt("id"));
			goods.setCatelog_id(rs.getInt("catelog_id"));
			goods.setBianhao(rs.getString("bianhao"));

			goods.setMingcheng(rs.getString("mingcheng"));
			goods.setJieshao(rs.getString("jieshao"));
			goods.setPinpai(rs.getString("pinpai"));
			goods.setFujian(rs.getString("fujian"));

			goods.setJiage(rs.getInt("jiage"));
			goods.setKucun(rs.getInt("kucun"));
			goods.setDel(rs.getString("del"));

			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mydb.closed();
		return goods;
	}

	/**
	 * 获得最新商品
	 * 
	 * @return 最新商品
	 */
	public static List<Tgoods> goodsNew() {
		List<Tgoods> goodsList = new ArrayList<Tgoods>();
		String sql = "select * from t_goods where del='no' order by id desc";
		Object[] params = {};
		DB mydb = new DB();
		try {
			mydb.doPstm(sql, params);
			ResultSet rs = mydb.getRs();
			while (rs.next()) {
				Tgoods goods = new Tgoods();

				goods.setId(rs.getInt("id"));
				goods.setCatelog_id(rs.getInt("catelog_id"));
				goods.setBianhao(rs.getString("bianhao"));

				goods.setMingcheng(rs.getString("mingcheng"));
				goods.setJieshao(rs.getString("jieshao"));
				goods.setPinpai(rs.getString("pinpai"));
				goods.setFujian(rs.getString("fujian"));

				goods.setJiage(rs.getInt("jiage"));
				goods.setKucun(rs.getInt("kucun"));
				goods.setDel(rs.getString("del"));

				goodsList.add(goods);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mydb.closed();

		if (goodsList.size() > 8) {
			goodsList = goodsList.subList(0, 8);
		}
		return goodsList;
	}

	/**
	 * 获得热卖商品
	 * 
	 * @return
	 */
	public static List<Tgoods> goodsPaihang4() {
		List<Tgoods> goodsList = new ArrayList<Tgoods>();
		String sql = "select sum(goods_quantity),goods_id from t_orderitem group by goods_id order by sum(goods_quantity) desc";
		Object[] params = {};
		DB mydb = new DB();
		try {
			mydb.doPstm(sql, params);
			ResultSet rs = mydb.getRs();
			while (rs.next()) {
				goodsList.add(getGoods(rs.getInt(2)));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mydb.closed();
		if (goodsList.size() > 4) {
			goodsList = goodsList.subList(0, 4);
		}
		return goodsList;
	}

	/**
	 * 根据类别获得商品
	 * 
	 * @param catelog_id
	 *            商品类别ID
	 * @return 商品
	 */
	public static List<Tgoods> goodsByCatelog(int catelog_id) {
		List<Tgoods> goodsList = new ArrayList<Tgoods>();
		String sql = "select * from t_goods where del='no' and catelog_id=? order by id desc";
		Object[] params = { catelog_id };
		DB mydb = new DB();
		try {
			mydb.doPstm(sql, params);
			ResultSet rs = mydb.getRs();
			while (rs.next()) {
				Tgoods goods = new Tgoods();

				goods.setId(rs.getInt("id"));
				goods.setCatelog_id(rs.getInt("catelog_id"));
				goods.setBianhao(rs.getString("bianhao"));

				goods.setMingcheng(rs.getString("mingcheng"));
				goods.setJieshao(rs.getString("jieshao"));
				goods.setPinpai(rs.getString("pinpai"));
				goods.setFujian(rs.getString("fujian"));

				goods.setJiage(rs.getInt("jiage"));
				goods.setKucun(rs.getInt("kucun"));
				goods.setDel(rs.getString("del"));

				goodsList.add(goods);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mydb.closed();
		return goodsList;
	}

	/**
	 * 根据关键字获得商品信息
	 * 
	 * @param key
	 *            关键字
	 * @return 商品
	 */
	public static List<Tgoods> goodsByKey(String key) {
		List<Tgoods> goodsList = new ArrayList<Tgoods>();
		String sql = "select * from t_goods where del='no' and mingcheng like '%"
				+ key + "%' order by id desc";
		System.out.println(key);
		DB mydb = new DB();
		try {
			mydb.doPstm(sql, null);
			ResultSet rs = mydb.getRs();
			while (rs.next()) {
				Tgoods goods = new Tgoods();

				goods.setId(rs.getInt("id"));
				goods.setCatelog_id(rs.getInt("catelog_id"));
				goods.setBianhao(rs.getString("bianhao"));

				goods.setMingcheng(rs.getString("mingcheng"));
				goods.setJieshao(rs.getString("jieshao"));
				goods.setPinpai(rs.getString("pinpai"));
				goods.setFujian(rs.getString("fujian"));

				goods.setJiage(rs.getInt("jiage"));
				goods.setKucun(rs.getInt("kucun"));
				goods.setDel(rs.getString("del"));

				goodsList.add(goods);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mydb.closed();
		return goodsList;
	}

	/**
	 * 保存订单
	 * 
	 * @param order
	 *            订单信息
	 */
	public static void saveOrder(Torder order) {
		String sql = "insert into t_order(id,bianhao,shijian,zhuangtai,songhuodizhi,fukuanfangshi,jine,user_id) values(?,?,?,?,?,?,?,?)";
		Object[] params = { order.getId(), order.getBianhao(),
				order.getShijian(), order.getZhuangtai(),
				order.getSonghuodizhi(), order.getFukuanfangshi(),
				order.getJine(), order.getUser_id() };
		DB mydb = new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
	}

	/**
	 * 保存订单明细
	 * 
	 * @param id
	 *            ID
	 * @param order_id
	 *            订单ID
	 * @param goods_id
	 *            商品ID
	 * @param goods_quantity
	 *            商品数量
	 */
	public static void saveOrderItem(String id, String order_id, int goods_id,
			int goods_quantity) {
		String sql = "insert into t_orderitem(id,order_id,goods_id,goods_quantity) values(?,?,?,?)";
		Object[] params = { id, order_id, goods_id, goods_quantity };
		DB mydb = new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
	}

	/**
	 * 更新商品库存信息
	 * 
	 * @param goods_id
	 *            商品ID
	 * @param goods_quantity
	 *            数量
	 */
	public static void updateGoodsKucun(int goods_id, int goods_quantity) {
		String sql = "update t_goods set kucun=kucun-? where id=?";
		Object[] params = { goods_quantity, goods_id };
		DB mydb = new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
	}

	/**
	 * 获得订单信息
	 * 
	 * @param user_id
	 *            用户ID
	 * @param name
	 *            商品编号
	 * @return
	 */
	public static List<Torder> orderList(String user_id, String name) {
		List<Torder> orderList = new ArrayList<Torder>();
		String sql = "select * from t_order where user_id=" + user_id;
		if (name != null && name.trim().length() > 0) {
			sql = sql + " and bianhao like '%" + name + "%'";
		}

		Object[] params = null;
		DB mydb = new DB();
		try {
			mydb.doPstm(sql, params);
			ResultSet rs = mydb.getRs();
			while (rs.next()) {
				Torder order = new Torder();

				order.setId(rs.getString("id"));
				order.setBianhao(rs.getString("bianhao"));
				order.setShijian(rs.getString("shijian"));
				order.setZhuangtai(rs.getString("zhuangtai"));
				order.setSonghuodizhi(rs.getString("songhuodizhi"));
				order.setFukuanfangshi(rs.getString("fukuanfangshi"));
				order.setJine(rs.getInt("jine"));
				order.setUser_id(rs.getString("user_id"));

				orderList.add(order);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mydb.closed();
		return orderList;
	}

	/**
	 * 获得商品评价信息
	 * 
	 * @param order_id
	 *            订单ID
	 * @return
	 */
	public static List<TpingItem> pingItemList(String order_id) {
		List<TpingItem> pingitemList = new ArrayList<TpingItem>();
		String sql = "select * from t_ping where order_id=?";
		Object[] params = { order_id };
		DB mydb = new DB();

		try {
			mydb.doPstm(sql, params);
			ResultSet rs = mydb.getRs();
			while (rs.next()) {
				TpingItem pingItem = new TpingItem();
				pingItem.setId(rs.getInt("id"));
				pingItem.setOrder_id(rs.getString("order_id"));
				pingItem.setOrderitem_id(rs.getString("orderitem_id"));
				pingItem.setGoods_id(rs.getInt("goods_id"));
				pingItem.setMingcheng(rs.getString("mingcheng"));
				pingItem.setUser_id(rs.getString("user_id"));
				pingItem.setLoginname(rs.getString("loginname"));
				pingItem.setInfo(rs.getString("info"));
				pingItem.setAdddate(rs.getString("adddate"));
				pingItem.setGoods(getGoods(rs.getInt("goods_id")));
				pingitemList.add(pingItem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mydb.closed();
		return pingitemList;
	}

	/**
	 * 获得商品评价信息
	 * 
	 * @param goodsid
	 *            商品ID
	 * @return
	 */
	public static List<TpingItem> pingList(int goodsid) {
		List<TpingItem> pingitemList = new ArrayList<TpingItem>();
		String sql = "select * from t_ping where goods_id=? order by id desc";
		Object[] params = { goodsid };
		DB mydb = new DB();

		try {
			mydb.doPstm(sql, params);
			ResultSet rs = mydb.getRs();
			while (rs.next()) {
				TpingItem pingItem = new TpingItem();
				pingItem.setId(rs.getInt("id"));
				pingItem.setOrder_id(rs.getString("order_id"));
				pingItem.setOrderitem_id(rs.getString("orderitem_id"));
				pingItem.setGoods_id(rs.getInt("goods_id"));
				pingItem.setMingcheng(rs.getString("mingcheng"));
				pingItem.setUser_id(rs.getString("user_id"));
				pingItem.setLoginname(rs.getString("loginname"));
				pingItem.setInfo(rs.getString("info"));
				pingItem.setAdddate(rs.getString("adddate"));
				pingItem.setGoods(getGoods(rs.getInt("goods_id")));
				pingItem.setReply(rs.getString("reply"));
				pingItem.setReplydate(rs.getString("replydate"));
				pingitemList.add(pingItem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mydb.closed();
		return pingitemList;
	}

	/**
	 * 获得订单明细
	 * 
	 * @param order_id
	 *            订单ID
	 * @return 订单明细
	 */
	public static List<TorderItem> orderItemList(String order_id) {
		List<TorderItem> orderitemList = new ArrayList<TorderItem>();
		String sql = "select * from t_orderitem where order_id=?";
		Object[] params = { order_id };
		DB mydb = new DB();
		try {
			mydb.doPstm(sql, params);
			ResultSet rs = mydb.getRs();
			while (rs.next()) {
				TorderItem orderItem = new TorderItem();

				orderItem.setId(rs.getString("id"));
				orderItem.setGoods(getGoods(rs.getInt("goods_id")));
				orderItem.setGoods_quantity(rs.getInt("goods_quantity"));
				orderItem.setGoods_state(rs.getString("goods_state"));

				orderitemList.add(orderItem);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mydb.closed();
		return orderitemList;
	}
}
