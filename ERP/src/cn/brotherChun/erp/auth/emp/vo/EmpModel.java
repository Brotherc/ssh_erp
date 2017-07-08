package cn.brotherChun.erp.auth.emp.vo;

public class EmpModel {
	
	public static final String EMP_OBJECT_NAME="loginEmp";
	private Long uuid;
	private String name;
	private String userName;
	private String pwd;
	
	public EmpModel() {}

	public EmpModel(Long uuid, String name, String userName, String pwd) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.userName = userName;
		this.pwd = pwd;
	}

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
