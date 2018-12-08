package cn.hr.dao;

import cn.hr.model.History;

public class HistoryDao {
	/**
	 * 获取某种变动类型的所有数据，以二位数组的形式返回
	 * @param type
	 * @return
	 */
	public String[][] getAllByType(String ChgTime){
		return null;
		
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
	public int getChangeCount(long DeptID,String ChgTime){
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
