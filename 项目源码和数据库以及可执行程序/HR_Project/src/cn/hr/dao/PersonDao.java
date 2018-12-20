package cn.hr.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import cn.hr.model.Person;
import cn.hr.utils.DBUtils;
/**
 * 人员层操作
 * @author Administrator
 *
 */
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
			    List<Person> list=new LinkedList<Person>();
			    int i=0;
				//执行SQL语句
				String sql = "select PersonID,Name,Birth,Nat,Address,B_Dept&'-'&S_Dept as deptName from Person,Dept where Dept.DeptID=Person.DeptID";
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
						person.setBirth(rs.getString("Birth"));
						person.setNat(rs.getString("Nat"));
						person.setAddress(rs.getString("Address"));
						person.setDeptName(rs.getString("deptName"));
						list.add(person);
						
					}
					data=new String[list.size()][6];
					for(int j=0;j<list.size();j++){
						data[j][0]=String.valueOf(list.get(j).getPersonID());
						data[j][1]=list.get(j).getName();
						data[j][2]=list.get(j).getBirth();
						data[j][3]=list.get(j).getNat();
						data[j][4]=list.get(j).getAddress();
						data[j][5]=list.get(j).getDeptName();
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
			ps.setString(1,person.getName());
			ps.setString(2, person.getSex());
			ps.setString(3, person.getBirth());
			ps.setString(4, person.getNat());
			ps.setString(5, person.getAddress());
			ps.setLong(6, person.getDeptID());
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
			if(HistoryDao.hasData(PersonID)==false){
				JOptionPane.showMessageDialog(null, "已有信息关联拒绝删除");
				return ;
			}
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
	 * 人员信息的查询
	 * @return
	 */
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
				data[i][2]=rs.getString("Sex");
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
					}
				}
				
				i++;
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
	 * 根据员工编号查询当前部门编号
	 * @param PersonID
	 * @return
	 */
	public static long getDeptId(long PersonID){
		Connection conn=DBUtils.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		long DeptID=0;
		String sql2="select DeptID from Person where PersonID=?";
		try {
			ps=conn.prepareStatement(sql2);
			ps.setLong(1,PersonID);
			rs=ps.executeQuery();
			while(rs.next()){
				DeptID=rs.getLong("DeptID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DeptID;
	}
	/**
	 * 通过id获取姓名
	 * @param PersonID
	 * @return
	 */
	public static String getName(long PersonID){
		Connection conn=DBUtils.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String Name=null;
		String sql2="select Name from Person where PersonID=?";
		try {
			ps=conn.prepareStatement(sql2);
			ps.setLong(1,PersonID);
			rs=ps.executeQuery();
			while(rs.next()){
				Name=rs.getString("Name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Name;
	}
	/**
	 * 通过部门编号获取部门组合
	 * @param DeptID
	 * @return
	 */
	public static String getDepts(long DeptID){
		Connection conn=DBUtils.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String data=null;
		String sql2="select B_Dept,S_Dept from Dept where DeptID=?";
		try {
			ps=conn.prepareStatement(sql2);
			ps.setLong(1,DeptID);
			rs=ps.executeQuery();
			ResultSet rs2=ps.executeQuery();
			if(rs2!=null){
				if(rs2.next()){
						data=rs2.getString("B_Dept")+"-"+rs2.getString("S_Dept");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	/**
	 * 人员调动之更新部门
	 * @param PersonID
	 * @param DeptID
	 */
	public static void  updateChangeDept(long PersonID,long DeptID ){
		Connection conn=DBUtils.getConnection();
		String sql="update Person set DeptID=?"
				+ "where PersonID=?";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setLong(1,DeptID);
			ps.setLong(2, PersonID);
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
	 * 更新考核结果
	 * @param PersonID
	 * @param Assess
	 */
    public static void updateAssess(long PersonID,String Assess){
    	Connection conn=DBUtils.getConnection();
		String sql="update Person set Assess=?"
				+ "where PersonID=?";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,Assess);
			ps.setLong(2, PersonID);
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
     * 更新薪资
     * @param PersonID
     * @param Salary
     */
    public static void updateSalary(long PersonID,long Salary){
    	Connection conn=DBUtils.getConnection();
		String sql="update Person set Salary=?"
				+ "where PersonID=?";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setLong(1,Salary);
			ps.setLong(2, PersonID);
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
}
