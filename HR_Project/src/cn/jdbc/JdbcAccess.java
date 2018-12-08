package cn.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class JdbcAccess {
	@Test
	public static void testQuery() {
		Connection conn = null;
		//获取连接
		try {
<<<<<<< HEAD
			
=======
>>>>>>> 9a486f8a117b7afd7682fedf1a0ceadbfe126562
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			conn = DriverManager.getConnection("jdbc:ucanaccess://Hr.accdb");
		} catch (Exception e) {
		}
		//执行SQL语句
		String sql = "select * from dept order by deptId";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//处理查询结果
		try {
			while(rs.next()) {
				long DeptID=rs.getLong("DeptID");
				String dept1Name=rs.getString("B_Dept");
				String dept2Name=rs.getString("D_Dept");
				System.out.println(DeptID+"-"+dept1Name+"-"+dept2Name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//关闭相关资源
				try {
					if(rs!=null) rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					if(ps!=null) ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					if(conn!=null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		
	}
}
