package cn.hr.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import cn.hr.model.Dept;
import cn.hr.utils.DBUtils;

public class DeptDao {
	/**
	 * //以二维数据形式返回所有给出部门数据
	 * @return
	 */
	public  static String [][] getDeptDao() {
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
			JOptionPane.showMessageDialog(null, "查询异常");
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
	public static void add(Dept dept){
		//获取数据库连接
		Connection conn=DBUtils.getConnection();
		//更新
		PreparedStatement ps=null;
		String sql="insert into Dept(DeptID,B_Dept,S_Dept)values(?,?,?)";
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
	public static String[][] getDeptsFortable(){
		return null;
	}
	/**
	 * 向数据库添加一个数据库
	 * @param dept
	 */
	public static void addDept(Dept dept){
		
	}
	/**
	 * 修改特定部门编号的部门数据
	 * @param dept
	 */
	public static void updateDept(Dept dept){
		Connection conn=DBUtils.getConnection();
		//更新
		PreparedStatement ps=null;
		String sql = "update Dept set B_Dept=?,S_Dept=? where DeptID=?";
		try {
			ps=conn.prepareStatement(sql);
			//替换问号
			ps.setString(1,dept.getB_Dept());
			ps.setString(2, dept.getS_Dept());
			ps.setLong(3, dept.getDeptID());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//关闭相关资源
				finally {
					DBUtils.close(ps);
					DBUtils.close(conn);
					}
	}
	/**
	 * 根据部门编号删除某部门数据
	 * @param DeptID
	 */
	public static void deleteDept(long DeptID){
		Connection conn=DBUtils.getConnection();
		String sql="delete from Dept where DeptID=?";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setLong(1, DeptID);
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "删除异常");
		}
		//关闭相关资源
		finally {
			DBUtils.close(rs);
			DBUtils.close(ps);
			DBUtils.close(conn);
			}
		
	}
	/**
	 * 获取下一可用的部门
	 * @return
	 */
	public static long getNextId(){
		long nextId=0;
		Connection conn=DBUtils.getConnection();
		String sql="select max(DeptID) as maxid from Dept";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				nextId=rs.getLong("maxid");
			}
			nextId+=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			//关闭连接
			DBUtils.close(ps);
			DBUtils.close(conn);
		}
		return nextId;
		
	}
	/**
	 * 获取字符串形式的部门信息列表
	 * @return
	 */
	public static String[] getDeptsForSelect(){
		Connection conn = DBUtils.getConnection();
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		//存储数据用的集合类
	    List<Dept> list=new LinkedList<Dept>();
	    String [] data=null;
		//执行SQL语句
		String sql = "select DeptID,B_Dept,S_Dept from Dept";
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
				list.add(dept);
			}
			data=new String [list.size()];
			for(int i=0;i<list.size();i++) {
				String deptId=String.valueOf(list.get(i).getDeptID());
				String B_Dept=list.get(i).getB_Dept();
				String S_Dept=list.get(i).getS_Dept();
				data[i]=deptId+"-"+B_Dept+"-"+S_Dept;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "查询异常");
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
	 * 通过id查找部门组合
	 * @return
	 */
	public static String getDeptsForId(long DeptID){
		Connection conn = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
	    String  departString=null;
		//执行SQL语句
		String sql = "select B_Dept,S_Dept from Dept where DeptID=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, DeptID);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//处理查询结果
		try {
			while(rs.next()) {
				departString=rs.getString("B_Dept")+"-"+rs.getString("S_Dept");
				System.out.println(departString);
			}
//			data=new String [list.size()];
//			for(int i=0;i<list.size();i++) {
//				String B_Dept=list.get(i).getB_Dept();
//				String S_Dept=list.get(i).getS_Dept();
//				data[i]=B_Dept+"-"+S_Dept;
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "查询异常");
		}
		//关闭相关资源
		finally {
			DBUtils.close(rs);
			DBUtils.close(ps);
			DBUtils.close(conn);
		}
		return departString;
	}

}
