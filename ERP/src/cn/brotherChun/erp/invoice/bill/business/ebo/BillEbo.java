package cn.brotherChun.erp.invoice.bill.business.ebo;

import java.util.List;

import cn.brotherChun.erp.invoice.bill.business.ebi.BillEbi;
import cn.brotherChun.erp.invoice.bill.dao.dao.BillDao;
import cn.brotherChun.erp.invoice.bill.vo.BillQueryModel;
import cn.brotherChun.erp.invoice.orderdetail.vo.OrderDetailModel;

public class BillEbo implements BillEbi{
	private BillDao billDao;

	public void setBillDao(BillDao billDao) {
		this.billDao = billDao;
	}

	public List<Object[]> getAll(BillQueryModel bqm) {
		return billDao.getAll(bqm);
	}

	public List<OrderDetailModel> getBillGoods(BillQueryModel bqm) {
		return billDao.getBillByOrderDetail(bqm);
	}
	
}
