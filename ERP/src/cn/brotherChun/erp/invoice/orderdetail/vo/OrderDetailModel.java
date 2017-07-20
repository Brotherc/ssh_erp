package cn.brotherChun.erp.invoice.orderdetail.vo;

import cn.brotherChun.erp.invoice.goods.vo.GoodsModel;
import cn.brotherChun.erp.invoice.order.vo.OrderModel;
import cn.brotherChun.erp.util.format.FormatUtil;

public class OrderDetailModel {
	private Long uuid;
	
	private Integer num;
	
	private Double price;
	
	private String priceView;
	private String totalPriceView;
	
	private OrderModel order;
	private GoodsModel goods;
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
		this.totalPriceView=FormatUtil.formatMoney(price*num);
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
		this.priceView=FormatUtil.formatMoney(price);
	}
	public String getPriceView() {
		return priceView;
	}
	public String getTotalPriceView() {
		return totalPriceView;
	}
	public OrderModel getOrder() {
		return order;
	}
	public void setOrder(OrderModel order) {
		this.order = order;
	}
	public GoodsModel getGoods() {
		return goods;
	}
	public void setGoods(GoodsModel goods) {
		this.goods = goods;
	}
	
	
}
