package cn.brotherChun.erp.invoice.bill.vo;

import cn.brotherChun.erp.invoice.order.vo.OrderModel;
import cn.brotherChun.erp.util.base.BaseQueryModel;

public class BillQueryModel implements BaseQueryModel{

	private Integer type=OrderModel.ORDER_TYPE_OF_BUY_BUYING;
	private Long supplierUuid;
	private Long goodsUuid;
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getSupplierUuid() {
		return supplierUuid;
	}
	public void setSupplierUuid(Long supplierUuid) {
		this.supplierUuid = supplierUuid;
	}
	public Long getGoodsUuid() {
		return goodsUuid;
	}
	public void setGoodsUuid(Long goodsUuid) {
		this.goodsUuid = goodsUuid;
	}

}
