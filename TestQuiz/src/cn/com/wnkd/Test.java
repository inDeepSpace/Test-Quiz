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
/*
 * 用于前台测试题测试
 */
public class Test extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext ctx1 = getServletContext();
		List<Chioce> li = new ArrayList<Chioce>();   //选项列表对象
		QuizBean Quiz = new QuizBean();    //存放题目的id标题和内容
		DataSource ds = null;
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/justsql");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = ds.getConnection();
			int id = Integer.parseInt(req.getParameter("id")) ; //获取id来查询数据库
			PreparedStatement ps1 = conn.prepareStatement("select c_id,c_content,c_answer from t_chioce where fk_quiz_choice=?");
			PreparedStatement ps2 = conn.prepareStatement("select q_id,q_title,q_content from t_quiz where q_id=?");
			ps1.setInt(1, id);
			ps2.setInt(1, id);
			ResultSet rs1 = ps1.executeQuery();
			ResultSet rs2 = ps2.executeQuery();
			//
			while(rs1.next()){
				Chioce c = new Chioce();
				c.setId(rs1.getInt("c_id"));
				c.setContent(rs1.getString("c_content"));
				c.setAnswer(rs1.getString("c_answer"));
				li.add(c);
			}
		        while(rs2.next()){
			    Quiz.setId(rs2.getInt("q_id"));
				Quiz.setTitle(rs2.getString("q_title"));
				Quiz.setContent(rs2.getString("q_content"));
		        }
		        req.setAttribute("li", li);
				req.setAttribute("Quiz", Quiz);
				ServletContext ctx = getServletContext();
				RequestDispatcher dp = ctx.getRequestDispatcher("/Test.jsp");
				dp.forward(req, resp);
				
			ps1.close();
			ps2.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		    String s = req.getParameter("id");
		    int id = Integer.parseInt(s);  

		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/justsql");
			Connection conn = ds.getConnection(); //创建数据库连接池
			
			String sql = "select c_answer from t_chioce where c_id=?";
			java.sql.PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
		    ResultSet rs = ps.executeQuery(); //执行sql查询语句 
		   if(rs.next())
		   {
		       String result = rs.getString("c_answer");  //将结果存放在变量result 
		       req.setAttribute("result", result); //转发到显示结果的界面
		       ServletContext ctx1 = getServletContext();
				RequestDispatcher dp = ctx1.getRequestDispatcher("/result.jsp");
				dp.forward(req, resp);
		   }
		   conn.close();
	}catch (SQLException e) {
		e.printStackTrace();

	} catch (NamingException e) {
		e.printStackTrace();
	}
	}
}

