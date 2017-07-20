package cn.brotherChun.erp.invoice.order.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.brotherChun.erp.auth.emp.vo.EmpModel;
import cn.brotherChun.erp.invoice.order.vo.OrderModel;
import cn.brotherChun.erp.invoice.order.vo.OrderQueryModel;
import cn.brotherChun.erp.util.base.BaseEbi;

@Transactional
public interface OrderEbi extends BaseEbi<OrderModel, OrderQueryModel>{

	public void saveBuy(OrderModel order, Long[] goodsUuids, Integer[] nums,
			Double[] prices, EmpModel login);

	public List<OrderModel> getAllBuy(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount);

}
