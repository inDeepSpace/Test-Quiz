package cn.com.wnkd;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("GB18030");
		HttpSession session = req.getSession();
          String u = req.getParameter("username");
          String p = req.getParameter("password");
  
          if("tom".equals(u) && "123".equals(p)){
        	  session.setAttribute("login",true);
        	  resp.sendRedirect("select");
          }else{
        	  resp.sendRedirect("admin/login/login.html");
          }
          
	}
    
}
