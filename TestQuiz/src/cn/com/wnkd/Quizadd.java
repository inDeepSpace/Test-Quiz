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

public class Quizadd extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("GB18030");
		resp.setCharacterEncoding("GB18030");
		
		try {
			QuizDB quizdb = new QuizDB();
		     int count = quizdb.getQuizCount(); //调用QuizDB中的getQuizCount()方法，統計数据库里的题目总数
		     count++;
		     
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/justsql");
			Connection conn = ds.getConnection();  //建立数据库连接池
			
			String sql = "INSERT INTO t_quiz(q_id,q_title,q_content) values (?,?,?)";
			PreparedStatement ps =  conn.prepareStatement(sql);
			ps.setInt(1, count);
			ps.setString(2, req.getParameter("title"));
		    ps.setString(3, req.getParameter("content"));
		    int c = ps.executeUpdate(); //将标题和内容插入数据库
		    
			String sql2 = "INSERT INTO t_chioce(c_content,c_order,fk_quiz_choice,c_answer) values (?,?,?,?)";
			PreparedStatement ps1 =  conn.prepareStatement(sql2);
			 //插入第1个选项
			 ps1.setString(1, req.getParameter("chioceA"));
		     ps1.setInt(2, 1);
		     ps1.setInt(3, count);
		     ps1.setString(4, req.getParameter("resultA"));
		     int c1 = ps1.executeUpdate();
		     //插入第2个选项
		     ps1.setString(1, req.getParameter("chioceB"));
		     ps1.setInt(2, 2);
		     ps1.setInt(3, count);
		     ps1.setString(4, req.getParameter("resultB"));
		     int c2 = ps1.executeUpdate();
		     //插入第3个选项
		     ps1.setString(1, req.getParameter("chioceC"));
		     ps1.setInt(2, 3);
		     ps1.setInt(3, count);
		     ps1.setString(4, req.getParameter("resultC"));
		     int c3 = ps1.executeUpdate();
		     //插入第4个选项
		     ps1.setString(1, req.getParameter("chioceD"));
		     ps1.setInt(2, 4);
		     ps1.setInt(3, count);
		     ps1.setString(4, req.getParameter("resultD"));
		     int c4 = ps1.executeUpdate();
		     
		     //判断是否有数据操作
			if((c!=0)&&(c1!=0)&&(c2!=0)&&(c3!=0)&&(c4!=0))
			{
				resp.sendRedirect("true.html");  //跳转到操作成功界面
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