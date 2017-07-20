package cn.brotherChun.erp.invoice.order.dao.dao;

import java.util.List;

import cn.brotherChun.erp.invoice.order.vo.OrderModel;
import cn.brotherChun.erp.invoice.order.vo.OrderQueryModel;
import cn.brotherChun.erp.util.base.BaseDao;

public interface OrderDao extends BaseDao<OrderModel>{

	public Integer getCountBuyCheck(OrderQueryModel oqm, Integer[] orderType);

	public List<OrderModel> getAllBuyCheck(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount, Integer[] orderType);

	public Integer getCountTask(OrderQueryModel oqm, Integer[] taskType);

	public List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount, Integer[] taskType);

	public List<OrderModel> getAllTaskByCompleter(OrderQueryModel oqm,
			Integer pageNum, Integer pageCount, Integer[] tasksType);

	public Integer getCountTaskByCompleter(OrderQueryModel oqm,
			Integer[] tasksType);

}
