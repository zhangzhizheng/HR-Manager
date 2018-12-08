package cn.hr.model;

public class Dept {
	private long DeptID;
	private String B_Dept;
	private String  S_Dept;
	
	public Dept() {
		super();
	}
	public Dept(long deptID, String b_Dept, String s_Dept) {
		super();
		DeptID = deptID;
		B_Dept = b_Dept;
		S_Dept = s_Dept;
	}
	public long getDeptID() {
		return DeptID;
	}
	public void setDeptID(long deptID) {
		DeptID = deptID;
	}
	public String getB_Dept() {
		return B_Dept;
	}
	public void setB_Dept(String b_Dept) {
		B_Dept = b_Dept;
	}
	public String getS_Dept() {
		return S_Dept;
	}
	public void setS_Dept(String s_Dept) {
		S_Dept = s_Dept;
	}
	
}
