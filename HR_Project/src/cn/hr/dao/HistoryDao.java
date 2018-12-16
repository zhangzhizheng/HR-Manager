package cn.hr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import cn.hr.model.History;
import cn.hr.model.Person;
import cn.hr.utils.DBUtils;

public class HistoryDao {
	/**
	 * 获取人员调动的所有数据，以二位数组的形式返回
	 * @return
	 */
	public String[][] getAllByType(){
		//获取连接
		Connection conn = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
	    String [][] data=null;
	    data=new String [35] [6];
	    int i=0;
		//执行SQL语句
		String sql = "select JourNo,FromAcc,Birth,Nat,Address,DeptID from Person";
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
	 * 变动历史添加
	 * @param h
	 */
	public void addHistory(History h){
		
	}
	/**
	 * 获取新的编号
	 * @return
	 */
	public long getNextId(){
		return 0;
		
	}
	/**
	 * 获取某员工在某种变动类型上的变动次数（如查询张三在部门调动方面变动的次数）
	 * @param DeptID
	 * @param type
	 * @return
	 */
	public static int getChangeCount(long DeptID,String type){
		//获取连接
		Connection conn = DBUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
	    String [][] data=null;
	    data=new String [35] [6];
	    int i=0;
		//执行SQL语句
		String sql = "select JourNo,FromAcc,Birth,Nat,Address,DeptID from Histroy where FromAcc=?"; 
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
		return 0;
		
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
