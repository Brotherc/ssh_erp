package cn.brotherChun.erp.invoice.bill.action;

import java.util.List;

import cn.brotherChun.erp.invoice.bill.business.ebi.BillEbi;
import cn.brotherChun.erp.invoice.bill.vo.BillQueryModel;
import cn.brotherChun.erp.invoice.order.vo.OrderModel;
import cn.brotherChun.erp.invoice.orderdetail.vo.OrderDetailModel;
import cn.brotherChun.erp.invoice.supplier.business.ebi.SupplierEbi;
import cn.brotherChun.erp.invoice.supplier.vo.SupplierModel;
import cn.brotherChun.erp.util.base.BaseAction;

public class BillAction extends BaseAction{
	private BillEbi billEbi;
	private SupplierEbi supplierEbi;
	
	public BillQueryModel bqm=new BillQueryModel();

	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}

	public void setBillEbi(BillEbi billEbi) {
		this.billEbi = billEbi;
	}
	
	public String buyBillList(){
		List<SupplierModel> temp = supplierEbi.getAll();
		put("supplierList", temp);
		
		List<Object[]> billTemp=billEbi.getAll(bqm);
		put("billList", billTemp);
		
		return "buyBillList";
	}
	
	//-----------ajax---------
	
	private List<OrderDetailModel> billGoodsList;
	
	public List<OrderDetailModel> getBillGoodsList() {
		return billGoodsList;
	}

	public String ajaxGetBillGoodsDetail(){
		billGoodsList=billEbi.getBillGoods(bqm);
		System.out.println(billGoodsList.size());
		return "ajaxGetBillGoodsDetail";
	}
}
