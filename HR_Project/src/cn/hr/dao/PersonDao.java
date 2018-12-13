package cn.hr.dao;

import static org.hamcrest.CoreMatchers.nullValue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import cn.hr.model.Dept;
import cn.hr.model.Person;
import cn.hr.utils.DBUtils;

public class PersonDao {
	/**
	 * 查询所有员工信息
	 * @return
	 */
	public static String[][] getAllForBasic(){
		//获取连接
				Connection conn = DBUtils.getConnection();
				PreparedStatement ps = null;
				ResultSet rs = null;
			    String [][] data=null;
			    int rowCount = 0; 
//			    int row=0;
//			    try {
//					rs.last();
//					  row=rs.getRow();
//					    rs.beforeFirst();
//
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
			    data=new String [35] [6];
			    int i=0;
				//执行SQL语句
				String sql = "select PersonID,Name,Birth,Nat,Address,DeptID from Person";
				try {
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				//处理查询结果
				try {
//					 if(rs.last()) {
//							row = rs.getRow();
//						}
//					 if(row==0){
//						 data[0][0]= "	";
//							data[0][1]= "	";
//							data[0][2]= "	";
//							data[0][3]= "	";
//							data[0][4]= "	";
//							data[0][5]= "	";
//					 }
					while(rs.next()) {
						//rowCount++;
						Person person=new Person();
						data[i][0]=String.valueOf(rs.getLong("PersonID"));
						data[i][1]=rs.getString("Name");
						data[i][2]=rs.getString("Birth");
						data[i][3]=rs.getString("Nat");
						data[i][4]=rs.getString("Address");
						data[i][5]=String.valueOf(rs.getString("DeptID"));
						if (data[i][5].equals("0")) {
							data[i][5]="未分配部门";
						}
						else {
							String sql2="select B_Dept,S_Dept from Dept where DeptId=?";
							ps=conn.prepareStatement(sql2);
							ps.setLong(1, Long.parseLong(rs.getString("DeptID")));
							ResultSet rs2=ps.executeQuery();
							if(rs2!=null){
								if(rs2.next()){
									data[i][5]=rs2.getString("B_Dept")+"-"+rs2.getString("S_Dept");
								}
								//data[i][5]=rs2.getString("B_Dept")+"-"+rs2.getString("S_Dept");
							}
						}
						
						i++;
					}
					 // data=new String [rowCount] [6];
					
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
	 * 员工信息的增加
	 * @param person
	 */
	public static void addPerson(Person person){
		Connection conn=DBUtils.getConnection();
		String sql="insert into Person(PersonID,Name,Sex,Birth,Nat,Address,DeptID,Assess,Other) "
				+ "values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setLong(1, person.getPersonID());
			ps.setString(2,person.getName());
			ps.setString(3, person.getSex());
			ps.setString(4, person.getBirth());
			ps.setString(5, person.getNat());
			ps.setString(6, person.getAddress());
			ps.setLong(7, person.getDeptID());
			ps.setString(8, person.getAssess());
			ps.setString(9, person.getOther());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			//关闭连接
			DBUtils.close(rs);
			DBUtils.close(ps);
			DBUtils.close(conn);
		}
		
	}
	/**
	 * 员工信息的修改
	 * @param person
	 */
	public static void updatePerson(Person person){
		Connection conn=DBUtils.getConnection();
		String sql="update Person set Name=?,Sex=?,Birth=?,Nat=?,Address=?,DeptID=?,Other=? "
				+ "where PersonID=?";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(sql);
			//ps.setLong(1, person.getPersonID());
			ps.setString(1,person.getName());
			ps.setString(2, person.getSex());
			ps.setString(3, person.getBirth());
			ps.setString(4, person.getNat());
			ps.setString(5, person.getAddress());
			ps.setLong(6, person.getDeptID());
			//ps.setString(7, person.getAssess());
			ps.setString(7, person.getOther());
			ps.setLong(8, person.getPersonID());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			//关闭连接
			DBUtils.close(rs);
			DBUtils.close(ps);
			DBUtils.close(conn);
		}
		
	}
	/**
	 * 员工信息的删除
	 * @param personID
	 */
	public static void deletePerson(long PersonID){
		Connection conn=DBUtils.getConnection();
		String sql="delete from Person where PersonID=?";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setLong(1, PersonID);
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//关闭相关资源
		finally {
			DBUtils.close(rs);
			DBUtils.close(ps);
			DBUtils.close(conn);
			}
		
	}
	/**
	 * 获取下一个可用编号
	 * @return
	 */
	public static long getNextId(){
		long nextId=0;
		Connection conn=DBUtils.getConnection();
		String sql="select max(PersonID) as maxid from Person";
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
		System.out.println(nextId);
		return nextId;
	}
	/**
	 * 以“编号”、”姓名“的形式来查询所有员工的信息
	 * @return
	 */
	public static String[] getNamewithId(){
		Connection conn = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		//存储数据用的集合类
	    List<Person> list=new LinkedList<Person>();
	    String [] data=null;
		//执行SQL语句
		String sql = "select PersonID,Name from Person";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//处理查询结果
		try {
			while(rs.next()) {
				Person person=new Person();
				person.setPersonID(rs.getLong("PersonID"));
				person.setName(rs.getString("Name"));
				list.add(person);
			}
			data=new String [list.size()];
			for(int i=0;i<list.size();i++) {
				String PersonId=String.valueOf(list.get(i).getPersonID());
				String name=list.get(i).getName();
				data[i]=PersonId+"-"+name;
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
	 * 通过id查看指定信息
	 * @return
	 */
	public static String[] getInfoById(long PersonID){
		Connection conn = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		//存储数据用的集合类
	    String [] data=new String [15];
		//执行SQL语句
		String sql = "select PersonID,Name,Sex,Birth,Nat,Address,Other from Person where PersonID=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, PersonID);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//处理查询结果
		try {
			while(rs.next()) {
				Person person=new Person();
				data[0]=String.valueOf(rs.getLong("PersonID"));
				data[1]=rs.getString("Name");
				data[2]=rs.getString("Sex");
				data[3]=rs.getString("Birth");
				data[4]=rs.getString("Nat");
				data[5]=rs.getString("Address");
				data[6]=rs.getString("Other");
			}
//			data=new String [list.size()];
//			for(int i=0;i<list.size();i++) {
//				String PersonId=String.valueOf(list.get(i).getPersonID());
//				String name=list.get(i).getName();
//				data[i]=PersonId+"-"+name;
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
		return data;
	}
	public static String[][] getPersonChange(){
		//获取连接
		Connection conn = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
	    String [][] data=null;
	    data=new String [35] [6];
	    int i=0;
		//执行SQL语句
		String sql = "select PersonID,Name,Sex,Address,DeptID,Salary,Assess from Person";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//处理查询结果
		try {
			while(rs.next()) {
				//rowCount++;
				Person person=new Person();
				data[i][0]=String.valueOf(rs.getLong("PersonID"));
				data[i][1]=rs.getString("Name");
				data[i][2]=rs.getString("Birth");
				data[i][3]=rs.getString("DeptID");
				data[i][4]=rs.getString("Salary");
				data[i][5]=String.valueOf(rs.getString("Assess"));
				if (data[i][3].equals("0")) {
					data[i][3]="未分配部门";
				}
				else {
					String sql2="select B_Dept,S_Dept from Dept where DeptId=?";
					ps=conn.prepareStatement(sql2);
					ps.setLong(1, Long.parseLong(rs.getString("DeptID")));
					ResultSet rs2=ps.executeQuery();
					if(rs2!=null){
						if(rs2.next()){
							data[i][3]=rs2.getString("B_Dept")+"-"+rs2.getString("S_Dept");
						}
						//data[i][5]=rs2.getString("B_Dept")+"-"+rs2.getString("S_Dept");
					}
				}
				
				i++;
			}
			 // data=new String [rowCount] [6];
			
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
	 * 根据员工编号查询当前部门编号
	 * @param PersonID
	 * @return
	 */
	public static long getDeptId(long PersonID){
		return 0;
		
	}
	
	/**
	 * 更新考核结果
	 * @param PersonID
	 * @param Assess
	 */
    public static void updateAssess(long PersonID,String Assess){
		
	}
    /**
     * 更新薪资
     * @param PersonID
     * @param Salary
     */
    public static void updateSalary(long PersonID,long Salary){
		
   	}
    /**
     * 查询所有员工的部门、考核信息和劳资信息
     * @return
     */
    public static String[][] getAllForHistory(){
		return null;
    	
    }
}
