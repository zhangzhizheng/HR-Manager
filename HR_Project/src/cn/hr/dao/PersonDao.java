package cn.hr.dao;

<<<<<<< HEAD
import cn.hr.model.Person;

public class PersonDao {
	/**
	 * 查询所有员工信息
	 * @return
	 */
	public String[][] getAllForBasic(){
		return null;
	}
	/**
	 * 员工信息的增加
	 * @param person
	 */
	public void addPerson(Person person){
		
	}
	/**
	 * 员工信息的修改
	 * @param person
	 */
	public void updatePerson(Person person){
		
	}
	/**
	 * 员工信息的删除
	 * @param personID
	 */
	public void deletePerson(long PersonID){
		
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
		return null;
		
	}
	/**
	 * 根据员工编号查询当前部门编号
	 * @param PersonID
	 * @return
	 */
	public long getDeptId(long PersonID){
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
=======
public class PersonDao {

>>>>>>> 9a486f8a117b7afd7682fedf1a0ceadbfe126562
}
