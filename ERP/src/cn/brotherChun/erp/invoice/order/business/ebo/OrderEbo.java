package cn.brotherChun.erp.invoice.order.business.ebo;

import java.io.Serializable;
import java.util.List;

import cn.brotherChun.erp.invoice.order.business.ebi.OrderEbi;
import cn.brotherChun.erp.invoice.order.dao.dao.OrderDao;
import cn.brotherChun.erp.invoice.order.vo.OrderModel;
import cn.brotherChun.erp.invoice.order.vo.OrderQueryModel;

public class OrderEbo implements OrderEbi{
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void save(OrderModel t) {
		orderDao.add(t);
	}

	public void delete(OrderModel t) {
		orderDao.delete(t);
	}

	public void update(OrderModel t) {
		orderDao.update(t);
	}

	public OrderModel get(Serializable uuid) {
		return orderDao.get(uuid);
	}

	public List<OrderModel> getAll() {
		return orderDao.getAll();
	}

	public List<OrderModel> getAll(OrderQueryModel q, Integer pageNum,
			Integer pageCount) {
		return orderDao.getAll(q, pageNum, pageCount);
	}

	public Integer getCount(OrderQueryModel q) {
		return orderDao.getCount(q);
	}

}
