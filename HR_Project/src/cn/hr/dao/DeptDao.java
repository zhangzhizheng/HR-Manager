package cn.hr.dao;

import static org.hamcrest.CoreMatchers.nullValue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.text.Position;

import org.junit.Test;

import cn.hr.model.Dept;
import cn.hr.utils.DBUtils;

public class DeptDao {
	
	
	/**
	 * //以二维数据形式返回所有给出部门数据
	 * @return
	 */
	@Test
	public  String [][] getDeptDao() {
		//获取连接
		Connection conn = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		//存储数据用的集合类
	    List<Dept> list=new LinkedList<Dept>();
	    String [][] data=null;
		//执行SQL语句
		String sql = "select * from Dept";
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
				dept.setS_Dept(rs.getString("S_Dept"));
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
	/**
	 * //插入信息
	 */
	
	public void add(){
		Dept dept=new Dept(14,"1","2");
		//获取数据库连接
		Connection conn=DBUtils.getConnection();
		//更新
		PreparedStatement ps=null;
		String sql="insert into Dept values(?,?,?)";
		try {
			ps=conn.prepareStatement(sql);
			//替换问号
			ps.setLong(1, dept.getDeptID());
			ps.setString(2,dept.getB_Dept());
			ps.setString(3, dept.getS_Dept());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//关闭连接
			DBUtils.close(ps);
			DBUtils.close(conn);
		}
	
	}
	/**
	 * 以二维数组形式返回所有部门数据
	 * @return
	 */
	public String[][] getDeptsFortable(){
		return null;
		
	}
	/**
	 * 向数据库添加一个数据库
	 * @param dept
	 */
	public void addDept(Dept dept){
		
	}
	/**
	 * 修改特定部门编号的部门数据
	 * @param dept
	 */
	public void updateDept(Dept dept){
		
	}
	/**
	 * 根据部门编号删除某部门数据
	 * @param DeptID
	 */
	public void deleteDept(long DeptID){
		
	}
	/**
	 * 获取下一可用的部门
	 * @return
	 */
	public long getNextId(){
		return 0;
		
	}
	/**
	 * 获取字符串形式的部门信息列表
	 * @return
	 */
	public String[] getDeptsForSelect(){
		return null;
		
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
