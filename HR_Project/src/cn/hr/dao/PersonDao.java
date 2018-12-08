package cn.hr.dao;

import static org.hamcrest.CoreMatchers.nullValue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.management.Query;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.hr.model.Dept;
import cn.hr.model.Person;
import cn.hr.utils.DBUtils;
import cn.hr.utils.JdbcUtils;


public class PersonDao {
	/**
	 * 查询所有员工信息
	 * @return
	 */
	public List<Person> getAllForBasic(){
		  QueryRunner quer=new QueryRunner(JdbcUtils.getDataSource());
		  String sql="select * from Person";
		  try {
			//quer.update(sql);
			  return (List<Person>)quer.query(sql, new BeanListHandler(Person.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	/**
	 * 员工信息的增加
	 * @param person
	 */
	public void addPerson(Person person) {
		/**
		 * 引用工具类queryRunner类(org.apache.commons.dbutils.QueryRunner) 显著的简化了SQL查询，
		 * 并与ResultSetHandler协同工作将使编码量大为减少。
		 * 使用commons-dbutils的jar用来操作数据库（增删改查）的工具包
		 * mchang-commons
		 */
        QueryRunner quer=new QueryRunner(JdbcUtils.getDataSource());
		String sql="insert into Person values(?,?,?,?,?,?,?,?,?,?)";
		Object params[] ={person.getPersonID(),person.getName(),person.getSex(),person.getBirth(),person.getNat(),person.getAddress(),person.getDeptID(),
				         person.getSalary(),person.getAssess(),person.getOther()};
		try {
			quer.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 员工信息的修改
	 * @param person
	 */
	public void updatePerson(Person person){
		 QueryRunner quer=new QueryRunner(JdbcUtils.getDataSource());
			String sql="update Person set PersonID=?,Name=?,Sex=?,Birth=?,Nat=?,Address=?,DeptID=?,Salary=?,Assess=?,Other=? where PersonID=?";
			Object params[] ={person.getPersonID(),person.getName(),person.getSex(),person.getBirth(),person.getNat(),person.getAddress(),person.getDeptID(),
			         person.getSalary(),person.getAssess(),person.getOther(),person.getPersonID()};
			try {
				quer.update(sql, params);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			}
			
	}
	/**
	 * 员工信息的删除
	 * @param personID
	 */
	public void deletePerson(long PersonID){
		 QueryRunner quer=new QueryRunner(JdbcUtils.getDataSource());
			String sql="delete from Person where PersonID=?";
			Object params[] ={PersonID};
			try {
				quer.update(sql, params);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			}
	}
	/**
	 * 获取下一个可用编号
	 * @return
	 */
	public long getNextId(){
		
		return 0;
		
	}
	/**
	 * 以“编号”、”姓名“的形式来查询所有员工的信息
	 * @return
	 */
	public String[] getNamewithId(){
		List<Person> list=new LinkedList<Person>();
	    String []data=null;
		Person person=new Person();
	    QueryRunner quer=new QueryRunner(JdbcUtils.getDataSource());
		String sql="select * from Person where PersonID=? and Name=?";
	
		Object params[] ={person.getPersonID(),person.getName()};
	    try {
			quer.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		return null;
		
	}
	/**
	 * 根据员工编号查询当前部门编号
	 * @param PersonID
	 * @return
	 */
	public long getDeptId(long PersonID){
		 QueryRunner quer=new QueryRunner(JdbcUtils.getDataSource());
			String sql="select DeptID from Person where PersonID=?";
			Object params[] ={PersonID};
		return 0;
	}
	/**
	 * 更新部门
	 * @param PersonID
	 * @param DeptID
	 */
	public void updateDept(long PersonID,long DeptID ){
		
	}
	/**
	 * 更新考核结果
	 * @param PersonID
	 * @param Assess
	 */
    public void updateAssess(long PersonID,String Assess){
		
	}
    /**
     * 更新薪资
     * @param PersonID
     * @param Salary
     */
    public void updateSalary(long PersonID,long Salary){
		
   	}
    /**
     * 查询所有员工的部门、考核信息和劳资信息
     * @return
     */
    public String[][] getAllForHistory(){
		return null;
    	
    }
}
