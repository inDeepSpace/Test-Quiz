package cn.com.wnkd;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

public class QuizUpdate extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		    req.setCharacterEncoding("GB18030");
		    resp.setCharacterEncoding("GB18030");
		
		    String s = req.getParameter("id"); //获取id并转换成整型
		    int id = Integer.parseInt(s);
		    ArrayList<Chioce> li = new ArrayList<Chioce>(); //创建一个List集合，用来多个选项值
		    QuizBean Quiz = new QuizBean();   //创建一个QuizBean对象，用来存放q_title和q_content
		
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/justsql");
			Connection conn = ds.getConnection(); //建立数据库连接池
			
			String sql = "SELECT q_title,q_content FROM t_quiz where q_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				
				Quiz.setTitle(rs.getString("q_title"));
				Quiz.setContent(rs.getString("q_content"));
				
				String sql2="select * from t_chioce where fk_quiz_choice=?"; //通过外键来查询选项表
				PreparedStatement ps2 = conn.prepareStatement(sql2);
				ps2.setInt(1, id);
				ResultSet rs1 = ps2.executeQuery();
				while(rs1.next()){
					Chioce ch = new Chioce();  //创建Chioce对象，来存放选项表中的内容
					ch.setContent(rs1.getString("c_content"));
					ch.setId(rs1.getInt("c_id"));
					ch.setOrder(rs1.getInt("c_order"));
					ch.setAnswer(rs1.getString("c_answer"));
					li.add(ch);
					
				   }
				  rs1.close();
				  rs.close();
				  ps.close();
				  conn.close();
			 }
				} catch (NamingException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		//转发到修改界面
		req.setAttribute("Quiz", Quiz);
		req.setAttribute("li", li);
		ServletContext ctx1 = getServletContext();
		RequestDispatcher dp = ctx1.getRequestDispatcher("/admin/update/update.jsp");
		dp.forward(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		    req.setCharacterEncoding("GB18030");
		    resp.setCharacterEncoding("GB18030");
		    
		    HttpSession session = req.getSession();
		    int id = (int) session.getAttribute("id");
		    
			try {
				
				Context ctx = new InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/justsql");
				Connection conn = ds.getConnection();  //建立数据库连接池
				
				//用于查询选项开始处的索引，即c_id
				String sql3 = "select c_id  from t_chioce where fk_quiz_choice=? and c_order = 1";
				java.sql.PreparedStatement ps1 = conn.prepareStatement(sql3);
				ps1.setInt(1, id);
				ResultSet rs = ps1.executeQuery();
				rs.next();
				int p = rs.getInt("c_id");
				
				//用于查询数据库中c_id=id的那道题共有多少个选项
				String sql4 = "select count(*) as sum from t_chioce where fk_quiz_choice=?";
				java.sql.PreparedStatement ps2 = conn.prepareStatement(sql4);
				ps2.setInt(1, id);
				ResultSet rs1 = ps2.executeQuery(); //得到数据库q_id题有多少个选项
				rs1.next();
				int count = rs1.getInt("sum"); //总的选项数
				
				//用于修改t_quiz表中的q_title和q_content
				String sql5 = "update t_quiz set q_title=?,q_content=? where q_id=?";
				PreparedStatement ps3= conn.prepareStatement(sql5);
				
				//用于修改t_chioce表中的选项,后面两个条件可以唯一决定将被改变的那条记录
				String sql6 = "update t_chioce set c_content=? where c_id=?";
				PreparedStatement ps4= conn.prepareStatement(sql6);
				
				int updatechioce = 0;
				for(int i=p;i<=count+p;i++)  //因为有count条选项，所以需要改变count次
				{
					String str = Integer.toString(i); //将int型变量i转化成String类型,根据name读取个选项的值
					ps4.setString(1, req.getParameter(str));    //在update.jsp中去读取req，每一个选项都有一个name
					ps4.setInt(2, i);
					updatechioce += ps2.executeUpdate();
				}	
				
				//修改t_quiz表中的值
				String title = req.getParameter("title");
				String content = req.getParameter("content");
				ps3.setString(1, title);
				ps3.setString(2, content);
				ps3.setInt(3, id);
				int d =  ps3.executeUpdate(); //用变量d来判断
				
				//判断变量d是否有数据改动
				if((updatechioce!=0)&&(d !=0))
				{
					resp.sendRedirect("true.html");
				}else{
					resp.sendRedirect("false.html");
				}
				  ps1.close();
				  conn.close();
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		} 
	}
		



		
        
