package cn.com.wnkd;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class QuizDele extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		   req.setCharacterEncoding("GB18030");
		   resp.setCharacterEncoding("GB18030");
		
		   String s = req.getParameter("id"); //获取id并转换成整型
		   int id = Integer.parseInt(s);
		
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/justsql");
			Connection conn = ds.getConnection();//连接数据库连接池
			
			String sql2 = "delete from t_chioce where fk_quiz_choice=?";
			PreparedStatement ps1 = conn.prepareStatement(sql2);
			ps1.setInt(1, id);
			int c1 = ps1.executeUpdate();//删除t_chioce表中的数据
			
			String sql = "DELETE FROM t_quiz where q_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,id);
			int c = ps.executeUpdate(); //删除t_quiz表中的数据
			
			//判断是否有数据操作
			if((c!=0)&&(c1!=0))
			{
				resp.sendRedirect("true.html"); //跳转到操作成功界面
			}else{
				resp.sendRedirect("false.html"); //跳转到操作失败界面
			}
		    ps.close();
		    conn.close();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		   req.setCharacterEncoding("GB18030");
		   resp.setCharacterEncoding("GB18030");
		
		   String s = req.getParameter("id"); //获取id并转换成整型
		   int id = Integer.parseInt(s);
		
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/justsql");
			Connection conn = ds.getConnection();//连接数据库连接池
			
			String sql2 = "delete from t_chioce where fk_quiz_choice=?";
			PreparedStatement ps1 = conn.prepareStatement(sql2);
			ps1.setInt(1, id);
			int c1 = ps1.executeUpdate();//删除t_chioce表中的数据
			
			String sql = "DELETE FROM t_quiz where q_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,id);
			int c = ps.executeUpdate(); //删除t_quiz表中的数据
			
			//判断是否有数据操作
			if((c!=0)&&(c1!=0))
			{
				resp.sendRedirect("true.html"); //跳转到操作成功界面
			}else{
				resp.sendRedirect("false.html"); //跳转到操作失败界面
			}
		    ps.close();
		    conn.close();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
         
}
