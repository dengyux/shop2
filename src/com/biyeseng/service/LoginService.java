package com.biyeseng.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import com.biyeseng.dao.DB;
import com.biyeseng.orm.Tadmin;
import com.biyeseng.orm.Tcatelog;
import com.biyeseng.orm.Tgoods;
import com.biyeseng.orm.Tuser;
import com.biyeseng.util.Cart;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

/**
 * 登录服务类
 */
public class LoginService {
	/**
	 * 用户登录
	 * @param userName 账号
	 * @param userPw 密码
	 * @param userType 类型
	 * @return
	 */
	public String login(String userName, String userPw, int userType) {

		String result = "no";
		//管理员登录
		if (userType == 0) {
			String sql = "select * from t_admin where userName=? and userPw=?";
			Object[] params = { userName, userPw };
			DB mydb = new DB();
			mydb.doPstm(sql, params);
			try {
				ResultSet rs = mydb.getRs();
				boolean mark = (rs == null || !rs.next() ? false : true);
				if (mark == false) {
					result = "no";
				} else {
					result = "yes";
					Tadmin admin = new Tadmin();
					admin.setUserId(rs.getInt("userId"));
					admin.setUserName(rs.getString("userName"));
					admin.setUserPw(rs.getString("userPw"));
					admin.setUserType(rs.getString("userType"));
					WebContext ctx = WebContextFactory.get();
					HttpSession session = ctx.getSession();

					session.setAttribute("admin", admin);
				}
				rs.close();
			} catch (SQLException e) {
				System.out.println("登录失败！");
				e.printStackTrace();
			} finally {
				mydb.closed();
			}

		}
		//用户登录
		if (userType == 1) {
			String sql = "select * from t_user where loginname=? and loginpw=? and del='no'";
			Object[] params = { userName, userPw };
			DB mydb = new DB();
			try {
				mydb.doPstm(sql, params);
				ResultSet rs = mydb.getRs();
				boolean mark = (rs == null || !rs.next() ? false : true);
				if (mark == false) {
					result = "no";
				}
				if (mark == true) {
					result = "yes";

					Tuser user = new Tuser();
					user.setId(rs.getString("id"));
					user.setLoginname(rs.getString("loginname"));
					user.setLoginpw(rs.getString("loginpw"));
					user.setLoginpw(rs.getString("loginpw"));
					user.setName(rs.getString("name"));
					user.setSex(rs.getString("sex"));
					user.setAge(rs.getString("age"));
					user.setAddress(rs.getString("address"));
					user.setTel(rs.getString("tel"));
					user.setEmail(rs.getString("email"));
					user.setQq(rs.getString("qq"));

					WebContext ctx = WebContextFactory.get();
					HttpSession session = ctx.getSession();

					session.setAttribute("userType", 1);
					session.setAttribute("user", user);

					Cart cart = new Cart();
					session.setAttribute("cart", cart);
				}
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			mydb.closed();
		}
		if (userType == 2) {

		}
		return result;
	}

	/**
	 * 管理员密码修改
	 * @param userPwNew 新密码
	 * @return
	 */
	public String adminPwEdit(String userPwNew) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebContext ctx = WebContextFactory.get();
		HttpSession session = ctx.getSession();
		Tadmin admin = (Tadmin) session.getAttribute("admin");

		String sql = "update t_admin set userPw=? where userId=?";
		Object[] params = { userPwNew, admin.getUserId() };
		DB mydb = new DB();
		mydb.doPstm(sql, params);

		return "yes";
	}

	/**
	 * 获得类型信息
	 * @return
	 */
	public List<Tcatelog> catelogAll() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	 * 获得商品信息
	 * @return
	 */
	public List<Tgoods> goodsAll() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Tgoods> goodsList = new ArrayList<Tgoods>();
		String sql = "select * from t_goods where del='no'";
		Object[] params = {};
		DB mydb = new DB();
		try {
			mydb.doPstm(sql, params);
			ResultSet rs = mydb.getRs();
			while (rs.next()) {
				Tgoods goods = new Tgoods();
				goods.setBianhao(rs.getInt("id") + ":"
						+ rs.getString("mingcheng"));
				goods.setMingcheng(rs.getString("mingcheng"));
				goodsList.add(goods);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mydb.closed();
		return goodsList;
	}
}
