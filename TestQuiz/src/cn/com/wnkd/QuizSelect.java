package cn.com.wnkd;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class QuizSelect extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("GB18030");
		resp.setCharacterEncoding("GB18030");
		
		String s = req.getParameter("id"); //获取id来查询数据库
		int id = Integer.parseInt(s); 
	   
		try {
			Context ctx = new InitialContext();
			DataSource ds1 = (DataSource) ctx.lookup("java:comp/env/justsql");
			Connection conn = ds1.getConnection();  //创建数据库连接池
			
			String sql = "SELECT * FROM t_quiz where q_id=?";  //执行查询语句并获取结果
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				QuizBean Quiz = new QuizBean();
				Quiz.setTitle(rs.getString("q_title"));
				Quiz.setContent(rs.getString("q_content"));
				String sql2="select * from t_chioce where fk_quiz_choice=?";
				PreparedStatement ps2 = conn.prepareStatement(sql2);
				ps2.setInt(1, id);
				ResultSet rs2 = ps2.executeQuery();
				while(rs2.next()){
					Chioce ch = new Chioce();
					ch.setId(rs2.getInt("c_id"));
					ch.setOrder(rs2.getInt("c_order"));
					ch.setContent(rs2.getString("c_content"));
					ch.setAnswer(rs2.getString("c_answer"));
					Quiz.getChoices().add(ch);
				}
				req.setAttribute("select",Quiz);
			}
			
			//转发到显示查询题目的jsp页面
//			resp.sendRedirect("admin/select/select.jsp");
			RequestDispatcher dp = getServletContext().getRequestDispatcher("/admin/select/select.jsp");
			dp.forward(req, resp);
		  ps.close();
		  conn.close();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("GB18030");
		resp.setCharacterEncoding("GB18030");
		
		String s = req.getParameter("id"); //获取id来查询数据库
		int id = Integer.parseInt(s); 
	   
		try {
			Context ctx = new InitialContext();
			DataSource ds1 = (DataSource) ctx.lookup("java:comp/env/justsql");
			Connection conn = ds1.getConnection();  //创建数据库连接池
			
			String sql = "SELECT * FROM t_quiz where q_id=?";  //执行查询语句并获取结果
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				QuizBean Quiz = new QuizBean();
				Quiz.setTitle(rs.getString("q_title"));
				Quiz.setContent(rs.getString("q_content"));
				String sql2="select * from t_chioce where fk_quiz_choice=?";
				PreparedStatement ps2 = conn.prepareStatement(sql2);
				ps2.setInt(1, id);
				ResultSet rs2 = ps2.executeQuery();
				while(rs2.next()){
					Chioce ch = new Chioce();
					ch.setId(rs2.getInt("c_id"));
					ch.setOrder(rs2.getInt("c_order"));
					ch.setContent(rs2.getString("c_content"));
					ch.setAnswer(rs2.getString("c_answer"));
					Quiz.getChoices().add(ch);
				}
				req.setAttribute("select",Quiz);
			}
			
			//转发到显示查询题目的jsp页面
//			resp.sendRedirect("admin/select/select.jsp");
			RequestDispatcher dp = getServletContext().getRequestDispatcher("/admin/select/select.jsp");
			dp.forward(req, resp);
		  ps.close();
		  rs.close();
		  conn.close();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
