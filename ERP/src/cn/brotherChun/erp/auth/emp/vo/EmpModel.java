package cn.brotherChun.erp.auth.emp.vo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.brotherChun.erp.auth.dep.vo.DepModel;
import cn.brotherChun.erp.auth.role.vo.RoleModel;
import cn.brotherChun.erp.util.format.FormatUtil;

public class EmpModel {
	
	public static final String EMP_OBJECT_NAME="loginEmp";
	private Long uuid;
	private String name;
	private String userName;
	private String pwd;
	private String email;
	private String tele;
	private String address;
	private Integer gender;
	private Long birthday;
	
	//多对一
	private DepModel dep;
	//角色多对多
	private Set<RoleModel> roles;
	
	private String birthdayView;
	private String genderView;
	private String lastLoginTimeView;
	
	private Long lastLoginTime;
	private String lastLoginIp;
	private Integer loginTimes;
	private String resAll;
	
	//数据结构思想应用
	public static final Integer EMP_GENDER_OF_MAN = 1;
	public static final Integer EMP_GENDER_OF_WOMAN = 0;
	
	public static final String EMP_GENDER_OF_MAN_VIEW = "男";
	public static final String EMP_GENDER_OF_WOMAN_VIEW = "女";
	
	public static final Map<Integer, String> genderMap = new HashMap<Integer, String>();
	static{
		genderMap.put(EMP_GENDER_OF_MAN, EMP_GENDER_OF_MAN_VIEW);
		genderMap.put(EMP_GENDER_OF_WOMAN, EMP_GENDER_OF_WOMAN_VIEW);
	}
	
	public EmpModel() {}

	public EmpModel(Long uuid, String name, String userName, String pwd,
			String email, String tele, String address, Integer gender,
			Long birthday, DepModel dep, Long lastLoginTime,
			String lastLoginIp, Integer loginTimes) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.userName = userName;
		this.pwd = pwd;
		this.email = email;
		this.tele = tele;
		this.address = address;
		this.gender = gender;
		this.birthday = birthday;
		this.dep = dep;
		this.lastLoginTime = lastLoginTime;
		this.lastLoginIp = lastLoginIp;
		this.loginTimes = loginTimes;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
		this.genderView=genderMap.get(gender);
	}

	public Long getBirthday() {
		return birthday;
	}

	public void setBirthday(Long birthday) {
		this.birthday = birthday;
		this.birthdayView=FormatUtil.formatDate(birthday);
	}

	public DepModel getDep() {
		return dep;
	}

	public void setDep(DepModel dep) {
		this.dep = dep;
	}

	public String getBirthdayView() {
		return birthdayView;
	}

	public String getGenderView() {
		return genderView;
	}

	public Long getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
		this.lastLoginTimeView=FormatUtil.formatDate(lastLoginTime);
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Integer getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	public String getLastLoginTimeView() {
		return lastLoginTimeView;
	}

	public Set<RoleModel> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
	}

	public String getResAll() {
		return resAll;
	}

	public void setResAll(String resAll) {
		this.resAll = resAll;
	}
	
}
