package com.biyeseng.action;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.biyeseng.dao.DB;
import com.biyeseng.orm.Tuser;

/**
 * 会员操作Action
 */
public class UserAction extends HttpServlet
{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Action调用控制类
	 */
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
		// 请求类型
        String type=req.getParameter("type");
		
		if(type.endsWith("userReg"))
		{
			//用户注册
			userReg(req, res);
		}
		if(type.endsWith("userLogout"))
		{
			//用户退出
			userLogout(req, res);
		}
		if(type.endsWith("userEdit"))
		{
			//用户编辑
			userEdit(req, res);
		}
		if(type.endsWith("userMana"))
		{
			//会员列表
			userMana(req, res);
		}
		if(type.endsWith("userDel"))
		{
			//会员删除
			userDel(req, res);
		}
		if(type.endsWith("userXinxi"))
		{
			//会员信息
			userXinxi(req, res);
		}
		if(type.endsWith("getPwd"))
		{
			//获取密码
			getPwd(req, res);
		}
	}
	
	/**
	 * 会员注册
	 * @param req
	 * @param res
	 */
	public void userReg(HttpServletRequest req,HttpServletResponse res)
	{
		String id=String.valueOf(new Date().getTime());
		String loginname=req.getParameter("loginname");
		String loginpw=req.getParameter("loginpw");
		String name=req.getParameter("name");
		String sex=req.getParameter("sex");
		String age=req.getParameter("age");
		String address=req.getParameter("address");
		String tel=req.getParameter("tel");
		String email=req.getParameter("email");
		String qq=req.getParameter("qq");
		String del="no";
		
		String sql="insert into t_user values(?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params={id,loginname,loginpw,name,sex,age,address,tel,email,qq,del};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
        String targetURL = "/common/add_success.jsp";
		dispatch(targetURL, req, res);
	}
	
	
	/**
	 * 会员退出登录
	 * @param req
	 * @param res
	 */
	public void userLogout(HttpServletRequest req,HttpServletResponse res)
	{
		req.getSession().removeAttribute("user");
		String targetURL = "/qiantai/default.jsp";
		dispatch(targetURL, req, res);		
	}
	
	/**
	 * 会员信息修改
	 * @param req
	 * @param res
	 */
	public void userEdit(HttpServletRequest req,HttpServletResponse res)
	{
		String id=req.getParameter("id");
		String loginname=req.getParameter("loginname");
		String loginpw=req.getParameter("loginpw");
		String name=req.getParameter("name");
		String sex=req.getParameter("sex");
		String age=req.getParameter("age");
		String address=req.getParameter("address");
		String tel=req.getParameter("tel");
		String email=req.getParameter("email");
		String qq=req.getParameter("qq");
		
		String sql="update t_user set loginpw=?,name=?,sex=?,age=?,address=?,tel=?,email=?,qq=? where id=?";
		Object[] params={loginpw,name,sex,age,address,tel,email,qq,id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "操作成功");
		Tuser user=new Tuser();
		user.setId(id);
		user.setLoginname(loginname);
		user.setLoginpw(loginpw);
		
		user.setName(name);
		user.setSex(sex);
		user.setAge(age);
		user.setAddress(address);
		user.setTel(tel);
		user.setEmail(email);
		user.setQq(qq);
		req.getSession().setAttribute("user", user);
		String targetURL = "/common/add_success.jsp";
		dispatch(targetURL, req, res);
	}
	
	/**
	 * 会员删除
	 * @param req
	 * @param res
	 */
	public void userDel(HttpServletRequest req,HttpServletResponse res)
	{
		String id=req.getParameter("id");
		
		String sql="delete from t_user where id=?";
		Object[] params={id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "user?type=userMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	
	/**
	 * 获取密码
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getPwd(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		String loginname=req.getParameter("loginname");
		String email=req.getParameter("email");
		String sql="select * from t_user where del='no' and loginname='"+loginname+"' and email='"+email+"'";
		String pwd="";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			if(rs.next())
			{
				 pwd=rs.getString("loginpw");
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		if(pwd.length()==0){
			req.setAttribute("message", "输入有误 请重新输入！");
		}else{
			req.setAttribute("message", "找回密码成功，该会员密码为："+pwd);
		}
		
		
		
		String targetURL = "/common/pwd_success.jsp";
		dispatch(targetURL, req, res);
	}

	/**
	 * 会员信息列表,后台使用
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	public void userMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List<Tuser> userList=new ArrayList<Tuser>();
		String sql="select * from t_user where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tuser user=new Tuser();
				
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
				
				userList.add(user);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("userList", userList);
		req.getRequestDispatcher("admin/user/userMana.jsp").forward(req, res);
	}
	
	/**
	 * 会员详细信息,后台使用
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	public void userXinxi(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		String id=req.getParameter("user_id");
		
		List<Tuser> userList=new ArrayList<Tuser>();
		String sql="select * from t_user where del='no' and id=?";
		Object[] params={id};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tuser user=new Tuser();
				
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
				
				userList.add(user);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("userList", userList);
		req.getRequestDispatcher("admin/order/userXinxi.jsp").forward(req, res);
	}
	
	/**
	 * 跳转服务工具方法
	 * 
	 * @param targetURI
	 * @param request
	 * @param response
	 */
	public void dispatch(String targetURI,HttpServletRequest request,HttpServletResponse response) 
	{
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(targetURI);
		try 
		{
		    dispatch.forward(request, response);
		    return;
		} 
		catch (ServletException e) 
		{
                    e.printStackTrace();
		} 
		catch (IOException e) 
		{
			
		    e.printStackTrace();
		}
	}
 
}
