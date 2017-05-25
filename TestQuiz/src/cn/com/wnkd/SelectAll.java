package cn.com.wnkd;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class SelectAll extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		  req.setCharacterEncoding("GB18030");
		  List<QuizBean> li = new ArrayList<QuizBean>();

		try {
			Context ctx = new InitialContext();
			DataSource ds1 = (DataSource) ctx.lookup("java:comp/env/justsql");
			Connection conn = ds1.getConnection(); //建立数据库连接池
			
			String sql = "SELECT q_id,q_title FROM t_quiz";  //查询数据库中的所有题目
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				{
					QuizBean Quiz = new QuizBean();
					Quiz.setTitle(rs.getString("q_title"));
					Quiz.setId(rs.getInt("q_id"));
					li .add(Quiz);   //讲查询结果放在list集合对象li中
				}
			  ps.close();
			  conn.close();
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
			    e.printStackTrace();
			}
		//转发到后台管理界面index2.jsp
		req.setAttribute("li", li);  
		ServletContext ctx = getServletContext();
		RequestDispatcher dp =ctx.getRequestDispatcher("/admin/index2/index2.jsp");
		dp.forward(req, resp);
	}
}