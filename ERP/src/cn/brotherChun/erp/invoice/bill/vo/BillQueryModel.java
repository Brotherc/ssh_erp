package cn.brotherChun.erp.invoice.bill.vo;

import cn.brotherChun.erp.invoice.order.vo.OrderModel;
import cn.brotherChun.erp.util.base.BaseQueryModel;
import cn.brotherChun.erp.util.format.FormatUtil;

public class BillQueryModel implements BaseQueryModel{

	private Integer type=OrderModel.ORDER_TYPE_OF_BUY_BUYING;
	
	private Long supplierUuid;
	private Long goodsUuid;
	private Long startTime;
	private Long endTime;
	
	private String startTimeView;
	private String endTimeView;
	
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
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
		this.startTimeView=FormatUtil.formatDate(startTime);
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
		this.endTimeView=FormatUtil.formatDate(endTime);
	}
	public String getStartTimeView() {
		return startTimeView;
	}
	public String getEndTimeView() {
		return endTimeView;
	}

}
