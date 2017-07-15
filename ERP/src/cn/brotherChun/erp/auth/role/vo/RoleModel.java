package cn.brotherChun.erp.auth.role.vo;

import java.util.Set;

import cn.brotherChun.erp.auth.res.vo.ResModel;

public class RoleModel {
	
	private Long uuid;
	private String name;
	private String code;
	
	private Set<ResModel> reses;
	
	public RoleModel() {}
	
	public RoleModel(Long uuid, String name, String code) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.code = code;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public Set<ResModel> getReses() {
		return reses;
	}

	public void setReses(Set<ResModel> reses) {
		this.reses = reses;
	}
	
}
