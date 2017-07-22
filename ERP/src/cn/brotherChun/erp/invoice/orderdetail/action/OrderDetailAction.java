package cn.brotherChun.erp.invoice.orderdetail.action;

import java.util.List;

import cn.brotherChun.erp.invoice.orderdetail.business.ebi.OrderDetailEbi;
import cn.brotherChun.erp.invoice.orderdetail.vo.OrderDetailModel;
import cn.brotherChun.erp.invoice.orderdetail.vo.OrderDetailQueryModel;
import cn.brotherChun.erp.invoice.supplier.vo.SupplierModel;
import cn.brotherChun.erp.util.base.BaseAction;

public class OrderDetailAction extends BaseAction{
	private OrderDetailEbi orderDetailEbi;
	
	public OrderDetailModel orderDetail=new OrderDetailModel();
	public OrderDetailQueryModel odqm=new OrderDetailQueryModel();
	
	public void setOrderDetailEbi(OrderDetailEbi orderDetailEbi) {
		this.orderDetailEbi = orderDetailEbi;
	}
	
	public OrderDetailModel getOrderDetail() {
		return orderDetail;
	}

	public String list(){
		
		setDataTotal(orderDetailEbi.getCount(odqm));
		List<OrderDetailModel> temp=orderDetailEbi.getAll(odqm,pageNum,pageCount);
		put("orderDetailList", temp);
		return LIST;
		
	}
	public String save(){
		if(orderDetail.getUuid()==null){
			orderDetailEbi.save(orderDetail);		
		}else {
			orderDetailEbi.update(orderDetail);
		}

		return TO_LIST;
	}
	public String input(){
		if(orderDetail.getUuid()!=null){
			orderDetail=orderDetailEbi.get(orderDetail.getUuid());
		}
		return INPUT;
	}
	public String delete(){
		orderDetailEbi.delete(orderDetail);
		return TO_LIST;
	}
	
	//ajax
	public String ajaxGetSurplus(){
		orderDetail=orderDetailEbi.get(orderDetail.getUuid());
		System.out.println(orderDetail.getSurplus()+"-----------------------");
		return "ajaxGetSurplus";
	}
}
