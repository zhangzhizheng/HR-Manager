package cn.hr.dao;

import static org.hamcrest.CoreMatchers.nullValue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import cn.hr.model.History;
import cn.hr.model.Person;
import cn.hr.utils.DBUtils;

public class HistoryDao {
	/**
	 * 获取人员调动的所有数据，以二位数组的形式返回
	 * @return
	 */
	public static String[][] getAllByType(String FromAcc){
		//获取连接
		Connection conn = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<History> list=new LinkedList<History>();
	    String [][] data=null;
	    String newdept=null;
		//执行SQL语句
		String sql = "select JourNo,PersonID,OldInfo,NewInfo,ChgTime,RegDate from Histroy where FromAcc=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,FromAcc);
			rs = ps.executeQuery();
			while(rs.next()){
				History h=new History();
				h.setJourNo(String.valueOf(rs.getLong("JourNo")));
				h.setPersonID(PersonDao.getName(rs.getLong("PersonID")));//通过id转换成对应的人员的名字存到对象里
				//nameid=rs.getLong("PersonID");
				h.setOldInfo(DeptDao.getDeptsForId(Long.parseLong(rs.getString("OldInfo"))));
				//deptid1=rs.getLong("OldInfo");
				h.setNewInfo(DeptDao.getDeptsForId(Long.parseLong(rs.getString("NewInfo"))));
				//deptid2=rs.getLong("NewInfo");
				h.setChgTime(String.valueOf(rs.getLong("ChgTime")));
				h.setRegDate(rs.getString("RegDate"));
				//System.out.println(PersonDao.getName(rs.getLong("PersonID")));
				list.add(h);
				//System.out.println(list.toString());
				
			}
			data=new String [list.size()] [6];
			for(int i=0;i<list.size();i++) {
				data[i][0]=String.valueOf(list.get(i).getJourNo());
				//personname=PersonDao.getName(rs.getLong("PersonID"));
				//System.out.println(PersonDao.getName(rs.getLong("PersonID")));
				data[i][1]=String.valueOf(list.get(i).getPersonID());
				//System.out.println(personname);
			   // olddept=DeptDao.getDeptsForId(Long.parseLong(rs.getString("OldInfo")));
				data[i][2]=list.get(i).getOldInfo();
				//System.out.println(olddept);
				//newdept=DeptDao.getDeptsForId(rs.getString("NewInfo"));
				data[i][3]=list.get(i).getNewInfo();
				//System.out.println(newdept);
				data[i][4]=list.get(i).getChgTime();
				data[i][5]=list.get(i).getRegDate();
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "查询历史失败");
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
	 * 变动历史添加
	 * @param h
	 */
	public static void addHistory(History h){
		Connection conn=DBUtils.getConnection();
		String sql="insert into Histroy(JourNo,FromAcc,OldInfo,NewInfo,RegDate,ChgTime,PersonID) "
				+ "values(?,?,?,?,?,?,?)";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, h.getJourNo());
			ps.setString(2, h.getFromAcc());
			ps.setString(3,h.getOldInfo());
			ps.setString(4, h.getNewInfo());
			ps.setString(5, h.getRegDate());
			ps.setString(6, h.getChgTime());
			ps.setString(7, h.getPersonID());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("插入历史正常");
		}
		finally {
			//关闭连接
			DBUtils.close(rs);
			DBUtils.close(ps);
			DBUtils.close(conn);
		}
		
	}
	/**
	 * 考核历史查询
	 * @param FromAcc
	 * @return
	 */
	public static String[][] getAllAssess(String FromAcc){
		//获取连接
		Connection conn = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<History> list=new LinkedList<History>();
	    String [][] data=null;
		//执行SQL语句
		String sql = "select JourNo,PersonID,OldInfo,NewInfo,ChgTime,RegDate from Histroy where FromAcc=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,FromAcc);
			rs = ps.executeQuery();
			while(rs.next()){
				History h=new History();
				h.setJourNo(String.valueOf(rs.getLong("JourNo")));
				h.setPersonID(PersonDao.getName(rs.getLong("PersonID")));//通过id转换成对应的人员的名字存到对象里
				//nameid=rs.getLong("PersonID");
				h.setOldInfo(rs.getString("OldInfo"));
				//deptid1=rs.getLong("OldInfo");
				h.setNewInfo(rs.getString("NewInfo"));
				//deptid2=rs.getLong("NewInfo");
				h.setChgTime(String.valueOf(rs.getLong("ChgTime")));
				h.setRegDate(rs.getString("RegDate"));
				list.add(h);
				//System.out.println(list.toString());
				
			}
			data=new String [list.size()] [6];
			for(int i=0;i<list.size();i++) {
				data[i][0]=String.valueOf(list.get(i).getJourNo());
				//personname=PersonDao.getName(rs.getLong("PersonID"));
				//System.out.println(PersonDao.getName(rs.getLong("PersonID")));
				data[i][1]=String.valueOf(list.get(i).getPersonID());
				//System.out.println(personname);
			   // olddept=DeptDao.getDeptsForId(Long.parseLong(rs.getString("OldInfo")));
				data[i][2]=list.get(i).getOldInfo();
				//System.out.println(olddept);
				//newdept=DeptDao.getDeptsForId(rs.getString("NewInfo"));
				data[i][3]=list.get(i).getNewInfo();
				//System.out.println(newdept);
				data[i][4]=list.get(i).getChgTime();
				data[i][5]=list.get(i).getRegDate();
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "查询历史失败");
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
	 * 劳资分配历史查询
	 * @param FromAcc
	 * @return
	 */
	public static String[][] getAllSalary(String FromAcc){
		//获取连接
		Connection conn = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<History> list=new LinkedList<History>();
	    String [][] data=null;
		//执行SQL语句
		String sql = "select JourNo,PersonID,OldInfo,NewInfo,ChgTime,RegDate from Histroy where FromAcc=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,FromAcc);
			rs = ps.executeQuery();
			while(rs.next()){
				History h=new History();
				h.setJourNo(String.valueOf(rs.getLong("JourNo")));
				h.setPersonID(PersonDao.getName(rs.getLong("PersonID")));//通过id转换成对应的人员的名字存到对象里
				//nameid=rs.getLong("PersonID");
				h.setOldInfo(rs.getString("OldInfo"));
				//deptid1=rs.getLong("OldInfo");
				h.setNewInfo(rs.getString("NewInfo"));
				//deptid2=rs.getLong("NewInfo");
				h.setChgTime(String.valueOf(rs.getLong("ChgTime")));
				h.setRegDate(rs.getString("RegDate"));
				list.add(h);
				//System.out.println(list.toString());
				
			}
			data=new String [list.size()] [6];
			for(int i=0;i<list.size();i++) {
				data[i][0]=String.valueOf(list.get(i).getJourNo());
				//personname=PersonDao.getName(rs.getLong("PersonID"));
				//System.out.println(PersonDao.getName(rs.getLong("PersonID")));
				data[i][1]=String.valueOf(list.get(i).getPersonID());
				//System.out.println(personname);
			   // olddept=DeptDao.getDeptsForId(Long.parseLong(rs.getString("OldInfo")));
				data[i][2]=list.get(i).getOldInfo();
				//System.out.println(olddept);
				//newdept=DeptDao.getDeptsForId(rs.getString("NewInfo"));
				data[i][3]=list.get(i).getNewInfo();
				//System.out.println(newdept);
				data[i][4]=list.get(i).getChgTime();
				data[i][5]=list.get(i).getRegDate();
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "查询历史失败");
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
	 * 获取新的编号
	 * @return
	 */
	public static long getNextId(){
		long nextId=0;
		Connection conn=DBUtils.getConnection();
		String sql="select max(JourNo) as maxid from Histroy";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
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
	 * 获取某员工在某种变动类型上的变动次数（如查询张三在部门调动方面变动的次数）
	 * @param DeptID
	 * @param type
	 * @return
	 */
	public static long  getChangeCount(String type,long PersonID){
		//获取连接
		Connection conn = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		long ChgTime=0;
		//执行SQL语句
		String sql = "select ChgTime from Histroy where FromAcc=? and PersonID=?"; 
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, type);
			ps.setLong(2, PersonID);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//处理查询结果
		try {
			while(rs.next()) {
				ChgTime=rs.getLong("ChgTime");
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
		return ChgTime;
		
	}
	/**
	 * 判断某员工是否有变动（若有变动历史，则不能删除该员工，同时给与提示）
	 * @param DeptID
	 * @return
	 */
	public boolean hasData(long DeptID){
		return false;
		
	}
}
