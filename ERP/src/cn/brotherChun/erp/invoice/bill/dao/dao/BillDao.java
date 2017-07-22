package cn.brotherChun.erp.invoice.bill.dao.dao;

import java.util.List;

import cn.brotherChun.erp.invoice.bill.vo.BillQueryModel;
import cn.brotherChun.erp.invoice.orderdetail.vo.OrderDetailModel;


public interface BillDao {

	public List<Object[]> getAll(BillQueryModel bqm);

	public List<OrderDetailModel> getBillByOrderDetail(BillQueryModel bqm);

}
