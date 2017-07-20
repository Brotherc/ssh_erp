package cn.brotherChun.erp.invoice.order.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.brotherChun.erp.auth.emp.vo.EmpModel;
import cn.brotherChun.erp.invoice.goods.vo.GoodsModel;
import cn.brotherChun.erp.invoice.order.business.ebi.OrderEbi;
import cn.brotherChun.erp.invoice.order.dao.dao.OrderDao;
import cn.brotherChun.erp.invoice.order.vo.OrderModel;
import cn.brotherChun.erp.invoice.order.vo.OrderQueryModel;
import cn.brotherChun.erp.invoice.orderdetail.vo.OrderDetailModel;
import cn.brotherChun.erp.util.num.NumUtil;

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

	public void saveBuy(OrderModel order, Long[] goodsUuids, Integer[] nums,
			Double[] prices, EmpModel creater) {

		order.setOrderNum(NumUtil.generatorOrderNum());
		
		order.setType(OrderModel.ORDER_TYPE_OF_BUY_NO_CHECK);
		order.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_BUY);
		order.setCreateTime(System.currentTimeMillis());
		order.setCreater(creater);
		
		Integer totalNum=0;
		Double totalPrice=0.0d;
		
		Set<OrderDetailModel> set=new HashSet<OrderDetailModel>();
		for(int i=0;i<goodsUuids.length;i++){
			OrderDetailModel odm=new OrderDetailModel();
			GoodsModel goods=new GoodsModel();
			goods.setUuid(goodsUuids[i]);
			odm.setGoods(goods);
			odm.setNum(nums[i]);
			odm.setPrice(prices[i]);
			odm.setOrder(order);
			set.add(odm);
			totalNum+=nums[i];
			totalPrice+=nums[i]*prices[i];
		}
		order.setOrderDetails(set);
		order.setTotalPrice(totalPrice);
		order.setTotalNum(totalNum);

		orderDao.add(order);
	}

	public List<OrderModel> getAllBuy(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount) {
		oqm.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_BUY);
		return orderDao.getAll(oqm, pageNum, pageCount);
	}

}
