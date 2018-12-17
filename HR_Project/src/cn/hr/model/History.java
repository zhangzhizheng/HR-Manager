package cn.hr.model;

public class History {
	private String JourNo;//变动历史编号
	private String FromAcc;//变动类型
	private String OldInfo;//原类型
	private String NewInfo;//新类型
	private String RegDate;//变更时间
	private String ChgTime;//改变次数
	private long PersonID;//员工编号
	private String name;//姓名
	public History() {
		super();
		// TODO Auto-generated constructor stub
	}
	public History(String jourNo, String fromAcc, String oldInfo, String newInfo, String regDate, String chgTime,
			long personID, String name) {
		super();
		JourNo = jourNo;
		FromAcc = fromAcc;
		OldInfo = oldInfo;
		NewInfo = newInfo;
		RegDate = regDate;
		ChgTime = chgTime;
		PersonID = personID;
		this.name = name;
	}
	public String getJourNo() {
		return JourNo;
	}
	public void setJourNo(String jourNo) {
		JourNo = jourNo;
	}
	public String getFromAcc() {
		return FromAcc;
	}
	public void setFromAcc(String fromAcc) {
		FromAcc = fromAcc;
	}
	public String getOldInfo() {
		return OldInfo;
	}
	public void setOldInfo(String oldInfo) {
		OldInfo = oldInfo;
	}
	public String getNewInfo() {
		return NewInfo;
	}
	public void setNewInfo(String newInfo) {
		NewInfo = newInfo;
	}
	public String getRegDate() {
		return RegDate;
	}
	public void setRegDate(String regDate) {
		RegDate = regDate;
	}
	public String getChgTime() {
		return ChgTime;
	}
	public void setChgTime(String chgTime) {
		ChgTime = chgTime;
	}
	public long getPersonID() {
		return PersonID;
	}
	public void setPersonID(long personID) {
		PersonID = personID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
