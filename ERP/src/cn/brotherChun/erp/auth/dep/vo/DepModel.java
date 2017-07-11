package cn.brotherChun.erp.auth.dep.vo;

public class DepModel {
	
	private Long uuid;
	private String name;
	private String tele;
	
	public DepModel() {}

	public DepModel(Long uuid, String name, String tele) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.tele = tele;
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

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}
	
}
