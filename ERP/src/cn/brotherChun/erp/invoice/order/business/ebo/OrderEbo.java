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
import cn.brotherChun.erp.util.exception.AppException;
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

	public Integer getCountBuy(OrderQueryModel oqm) {
		oqm.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_BUY);
		return orderDao.getCount(oqm);
	}
	
	public List<OrderModel> getAllBuy(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount) {
		oqm.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_BUY);
		return orderDao.getAll(oqm, pageNum, pageCount);
	}

	private Integer[] orderType=new Integer[]{OrderModel.ORDER_ORDERTYPE_OF_BUY,OrderModel.ORDER_ORDERTYPE_OF_RETURN_BUY};
	
	public Integer getCountBuyCheck(OrderQueryModel oqm) {
		return orderDao.getCountBuyCheck(oqm,orderType);
	}

	public List<OrderModel> getAllBuyCheck(OrderQueryModel oqm,
			Integer pageNum, Integer pageCount) {
		return orderDao.getAllBuyCheck(oqm,pageNum,pageCount,orderType);
	}

	public void updateBuyCheckPass(Long uuid, EmpModel checker) {
		OrderModel temp = orderDao.get(uuid);
		
		//逻辑校验
		if(!temp.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS)){
			throw new AppException("对不起，请不要进行非法操作！");
		}
		
		temp.setChecker(checker);
		temp.setCheckTime(System.currentTimeMillis());
		temp.setType(OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS);
	}

	public void updateBuyCheckNoPass(Long uuid, EmpModel checker) {
		OrderModel temp = orderDao.get(uuid);
		
		//逻辑校验
		if(!temp.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_CHECK_NO_PASS)){
			throw new AppException("对不起，请不要进行非法操作！");
		}
		
		temp.setChecker(checker);
		temp.setCheckTime(System.currentTimeMillis());
		temp.setType(OrderModel.ORDER_TYPE_OF_BUY_CHECK_NO_PASS);
	}

	private Integer[] taskType=new Integer[]{
			OrderModel.ORDER_TYPE_OF_BUY_BUYING,
			OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS,
			OrderModel.ORDER_TYPE_OF_BUY_COMPLETE,
			OrderModel.ORDER_TYPE_OF_BUY_IN_STORE,
	};
	
	public Integer getCountTask(OrderQueryModel oqm) {
		return orderDao.getCountTask(oqm,taskType);
	}

	public List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount) {
		return orderDao.getAllTask(oqm,pageNum,pageCount,taskType);
	}

	public void assignTask(Long uuid, EmpModel completer) {
		OrderModel order=orderDao.get(uuid);
		if(!order.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS))
			throw new AppException("对不起，请不要进行非法操作！");
		order.setCompleter(completer);
		order.setType(OrderModel.ORDER_TYPE_OF_BUY_BUYING);
	}

	private Integer[] tasksType=new Integer[]{
			OrderModel.ORDER_TYPE_OF_BUY_BUYING,
			OrderModel.ORDER_TYPE_OF_BUY_COMPLETE,
			OrderModel.ORDER_TYPE_OF_BUY_IN_STORE,
	};
	
	public List<OrderModel> getAllTasksByCompleter(OrderQueryModel oqm,
			Integer pageNum, Integer pageCount, EmpModel completer) {
		oqm.setCompleter(completer);
		return orderDao.getAllTaskByCompleter(oqm,pageNum,pageCount,tasksType);
	}

	public Integer getCountTasksByCompleter(OrderQueryModel oqm, EmpModel completer) {
		oqm.setCompleter(completer);
		return orderDao.getCountTaskByCompleter(oqm,tasksType);
	}

	public void endTasks(Long uuid) {
		OrderModel order = orderDao.get(uuid);
		if(!order.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_BUYING))
			throw new AppException("对不起，请不要进行非法操作！");
		order.setType(OrderModel.ORDER_TYPE_OF_BUY_IN_STORE);
	}


}
