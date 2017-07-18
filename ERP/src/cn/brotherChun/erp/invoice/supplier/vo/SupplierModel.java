package cn.brotherChun.erp.invoice.supplier.vo;

import java.util.HashMap;
import java.util.Map;

public class SupplierModel {
	
	private static final Integer SUPPLIER_NEEDS_IS_YES=1;
	private static final Integer SUPPLIER_NEDDS_IS_NO=0;
	
	private static final String SUPPLIER_NEEDS_IS_YES_VIEW="送货";
	private static final String SUPPLIER_NEDDS_IS_NO_VIEW="自提";
	
	public static final Map<Integer, String> needsMap=new HashMap<Integer, String>();
	static{
		needsMap.put(SUPPLIER_NEEDS_IS_YES, SUPPLIER_NEEDS_IS_YES_VIEW);
		needsMap.put(SUPPLIER_NEDDS_IS_NO, SUPPLIER_NEDDS_IS_NO_VIEW);
	}
	
	private Long uuid;
	private String name;
	private String address;
	private String contact;
	private String tele;
	private Integer needs;
	
	private String needsView;
	
	public SupplierModel() {}
	
	public SupplierModel(Long uuid, String name, String address,
			String contact, String tele, Integer needs) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.address = address;
		this.contact = contact;
		this.tele = tele;
		this.needs = needs;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getTele() {
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
	}
	public Integer getNeeds() {
		return needs;
	}
	public void setNeeds(Integer needs) {
		this.needs = needs;
		this.needsView=needsMap.get(needs);
	}

	public String getNeedsView() {
		return needsView;
	}
	
}
