package com.biyeseng.action;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.biyeseng.dao.DB;
import com.biyeseng.orm.Tcatelog;

/**
 * 商品类型管理Action
 */
public class CatelogAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Action调用控制类
	 */
	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// 请求类型
		String type = req.getParameter("type");

		if (type.endsWith("catelogMana")) {
			// 类型查询
			catelogMana(req, res);
		}
		if (type.endsWith("catelogAdd")) {
			// 类型添加
			catelogAdd(req, res);
		}
		if (type.endsWith("catelogDel")) {
			// 类型删除
			catelogDel(req, res);
		}
		if (type.endsWith("catelogUpdate")) {
			// 类型操作
			catelogUpdate(req, res);
		}
	}

	/**
	 * 类型添加
	 * 
	 * @param req
	 * @param res
	 */
	public void catelogAdd(HttpServletRequest req, HttpServletResponse res) {
		String name = req.getParameter("name").trim();
		String del = "no";

		String sql = "insert into t_catelog(name,del) values(?,?)";
		Object[] params = { name, del };
		DB mydb = new DB();
		mydb.doPstm(sql, params);
		mydb.closed();

		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "catelog?type=catelogMana");

		String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}

	/**
	 * 类型更新
	 * 
	 * @param req
	 * @param res
	 */
	public void catelogUpdate(HttpServletRequest req, HttpServletResponse res) {

		String name = req.getParameter("name").trim();
		String id = req.getParameter("id");

		String sql = "update t_catelog set name=?  where id=?";
		Object[] params = { name, id };
		DB mydb = new DB();
		mydb.doPstm(sql, params);
		mydb.closed();

		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "catelog?type=catelogMana");

		String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);

	}

	/**
	 * 类型删除
	 * 
	 * @param req
	 * @param res
	 */
	public void catelogDel(HttpServletRequest req, HttpServletResponse res) {
		String qsql = "select * from t_goods where catelog_id="
				+ req.getParameter("id") + " and del='no'";

		Object[] params = {};
		DB mydbs = new DB();
		mydbs.doPstm(qsql, params);
		try {
			ResultSet rs = mydbs.getRs();
			if (rs.next()) {
				req.setAttribute("message", "该类别下有商品，请删除完所在类别下商品！");
				req.setAttribute("path", "catelog?type=catelogMana");
				String targetURL = "/common/success.jsp";
				dispatch(targetURL, req, res);
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mydbs.closed();
		}

		String sql = "delete from t_catelog  where id="
				+ Integer.parseInt(req.getParameter("id"));

		DB mydb = new DB();
		mydb.doPstm(sql, params);
		mydb.closed();

		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "catelog?type=catelogMana");

		String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}

	/**
	 * 商品类型查询
	 * 
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	public void catelogMana(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
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

		req.setAttribute("catelogList", catelogList);
		req.getRequestDispatcher("admin/catelog/catelogMana.jsp").forward(req,
				res);
	}

	/**
	 * 跳转服务工具方法
	 * 
	 * @param targetURI
	 * @param request
	 * @param response
	 */
	public void dispatch(String targetURI, HttpServletRequest request,
			HttpServletResponse response) {
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(
				targetURI);
		try {
			dispatch.forward(request, response);
			return;
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
