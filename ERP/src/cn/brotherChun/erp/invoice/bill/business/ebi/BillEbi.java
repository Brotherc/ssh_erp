package cn.brotherChun.erp.invoice.bill.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.brotherChun.erp.invoice.bill.vo.BillQueryModel;
import cn.brotherChun.erp.invoice.orderdetail.vo.OrderDetailModel;

@Transactional
public interface BillEbi {

	public List<Object[]> getAll(BillQueryModel bqm);

	public List<OrderDetailModel> getBillGoods(BillQueryModel bqm);
}
