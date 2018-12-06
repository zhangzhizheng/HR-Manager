package cn.hr.model;

public class Person {
	private long PersonID;
	private String Name;
	private String Sex;
	private String Birth;
	private String Nat;
	private String Address;
	private long DeptID;
	private long Salary;
	private String Assess;
	private String Other;
	//补充属性
	private String B_Dept;
	public long getPersonID() {
		return PersonID;
	}
	public void setPersonID(long personID) {
		PersonID = personID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getBirth() {
		return Birth;
	}
	public void setBirth(String birth) {
		Birth = birth;
	}
	public String getNat() {
		return Nat;
	}
	public void setNat(String nat) {
		Nat = nat;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public long getDeptID() {
		return DeptID;
	}
	public void setDeptID(long deptID) {
		DeptID = deptID;
	}
	public long getSalary() {
		return Salary;
	}
	public void setSalary(long salary) {
		Salary = salary;
	}
	public String getAssess() {
		return Assess;
	}
	public void setAssess(String assess) {
		Assess = assess;
	}
	public String getOther() {
		return Other;
	}
	public void setOther(String other) {
		Other = other;
	}
	public String getB_Dept() {
		return B_Dept;
	}
	public void setB_Dept(String b_Dept) {
		B_Dept = b_Dept;
	}
}
