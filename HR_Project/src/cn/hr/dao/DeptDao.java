package cn.hr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import org.junit.Test;

import cn.hr.model.Dept;
import cn.hr.utils.DBUtils;

public class DeptDao {
	//以二维数据形式返回所有给出部门数据
	@Test
	public  String [][] getDeptDao() {
		//获取连接
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//存储数据用的集合类
	    List<Dept> list=new LinkedList<Dept>();
	    String [][] data=null;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			conn = DriverManager.getConnection("jdbc:ucanaccess://Hr.accdb");
		} catch (Exception e) {
		}
		//执行SQL语句
		String sql = "select * from Dept order by DeptID";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//处理查询结果
		try {
			while(rs.next()) {
				Dept dept=new Dept();
				dept.setDeptID(rs.getLong("DeptID"));
				dept.setB_Dept(rs.getString("B_Dept"));
				dept.setS_Dept(rs.getString("D_Dept"));
				//System.out.println(DeptID+"-"+dept1Name+"-"+dept2Name);
				list.add(dept);
			}
			data=new String [list.size()] [3];
			for(int i=0;i<list.size();i++) {
				data[i][0]=String.valueOf(list.get(i).getDeptID());
				data[i][1]=list.get(i).getB_Dept();
				data[i][2]=list.get(i).getS_Dept();
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		//关闭相关资源
		finally {
			DBUtils.close(rs);
			DBUtils.close(ps);
			DBUtils.close(conn);
		}
		return data;
		
	}
	public static void main(String []args) {
		DeptDao deptDao=new DeptDao();
		String [][]data=deptDao.getDeptDao();
		for(int i=0;i<data.length;i++) {
			for(int j=0;i<data[0].length;j++) {
				System.out.println(data[0][j]);
			}
		}
	}

}
