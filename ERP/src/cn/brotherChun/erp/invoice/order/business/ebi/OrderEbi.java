package cn.brotherChun.erp.invoice.order.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.brotherChun.erp.auth.emp.vo.EmpModel;
import cn.brotherChun.erp.invoice.order.vo.OrderModel;
import cn.brotherChun.erp.invoice.order.vo.OrderQueryModel;
import cn.brotherChun.erp.invoice.orderdetail.vo.OrderDetailModel;
import cn.brotherChun.erp.util.base.BaseEbi;

@Transactional
public interface OrderEbi extends BaseEbi<OrderModel, OrderQueryModel>{

	public void saveBuy(OrderModel order, Long[] goodsUuids, Integer[] nums,
			Double[] prices, EmpModel login);

	public List<OrderModel> getAllBuy(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount);

	public Integer getCountBuyCheck(OrderQueryModel oqm);

	public List<OrderModel> getAllBuyCheck(OrderQueryModel oqm,
			Integer pageNum, Integer pageCount);

	public Integer getCountBuy(OrderQueryModel oqm);

	public void updateBuyCheckPass(Long uuid, EmpModel login);
	
	public void updateBuyCheckNoPass(Long uuid, EmpModel login);

	public Integer getCountTask(OrderQueryModel oqm);

	public List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount);

	public void assignTask(Long uuid, EmpModel completer);

	public List<OrderModel> getAllTasksByCompleter(OrderQueryModel oqm,
			Integer pageNum, Integer pageCount, EmpModel login);

	public Integer getCountTasksByCompleter(OrderQueryModel oqm, EmpModel login);

	public void endTasks(Long uuid);

	public Integer getCountStore(OrderQueryModel oqm);

	public List<OrderModel> getAllStore(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount);

	public OrderDetailModel inGoods(Long orderDetailUuid, Long storeUuid,
			Integer inStoreNum, EmpModel login);

}
