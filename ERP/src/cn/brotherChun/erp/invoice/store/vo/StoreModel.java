package cn.brotherChun.erp.invoice.store.vo;

import cn.brotherChun.erp.auth.emp.vo.EmpModel;

public class StoreModel {
	private Long uuid;
	private String name;
	private String address;
	
	private EmpModel emp;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public EmpModel getEmp() {
		return emp;
	}

	public void setEmp(EmpModel emp) {
		this.emp = emp;
	}
	
}
