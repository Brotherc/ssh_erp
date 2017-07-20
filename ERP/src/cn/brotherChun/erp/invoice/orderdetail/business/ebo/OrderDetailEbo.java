package cn.brotherChun.erp.invoice.orderdetail.business.ebo;

import java.io.Serializable;
import java.util.List;

import cn.brotherChun.erp.invoice.orderdetail.business.ebi.OrderDetailEbi;
import cn.brotherChun.erp.invoice.orderdetail.dao.dao.OrderDetailDao;
import cn.brotherChun.erp.invoice.orderdetail.vo.OrderDetailModel;
import cn.brotherChun.erp.invoice.orderdetail.vo.OrderDetailQueryModel;

public class OrderDetailEbo implements OrderDetailEbi{
	private OrderDetailDao orderDetailDao;

	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	public void save(OrderDetailModel t) {
		orderDetailDao.add(t);
	}

	public void delete(OrderDetailModel t) {
		orderDetailDao.delete(t);
	}

	public void update(OrderDetailModel t) {
		orderDetailDao.update(t);
	}

	public OrderDetailModel get(Serializable uuid) {
		return orderDetailDao.get(uuid);
	}

	public List<OrderDetailModel> getAll() {
		return orderDetailDao.getAll();
	}

	public List<OrderDetailModel> getAll(OrderDetailQueryModel q,
			Integer pageNum, Integer pageCount) {
		return orderDetailDao.getAll(q, pageNum, pageCount);
	}

	public Integer getCount(OrderDetailQueryModel q) {
		return orderDetailDao.getCount(q);
	}

}
