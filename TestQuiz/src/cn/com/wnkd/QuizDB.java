package cn.com.wnkd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class QuizDB {
	private DataSource ds = null;
	public QuizDB() {
		Context ctx;
		try {
			ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/justsql");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取数据库中测试题的总数
	 * @return 总数
	 */
	public int getQuizCount() {
		int count = 0;
		try {
			Connection 	conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement("select count(*) as c from t_quiz");
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
            	count=rs.getInt("c");
            }
            conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
					
		}
		return count;
	}
}
