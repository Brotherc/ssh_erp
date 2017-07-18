package cn.brotherChun.erp.invoice.goodstype.vo;

import cn.brotherChun.erp.invoice.supplier.vo.SupplierModel;

public class GoodsTypeModel {
	
	private Long uuid;
	private String name;
	private SupplierModel supplier;
	
	public GoodsTypeModel() {}
	
	public GoodsTypeModel(Long uuid, String name, SupplierModel supplier) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.supplier = supplier;
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
	public SupplierModel getSupplier() {
		return supplier;
	}
	public void setSupplier(SupplierModel supplier) {
		this.supplier = supplier;
	}
	
}
