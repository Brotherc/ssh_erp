package cn.brotherChun.erp.invoice.order.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.brotherChun.erp.auth.emp.vo.EmpModel;
import cn.brotherChun.erp.invoice.goods.vo.GoodsModel;
import cn.brotherChun.erp.invoice.operdetail.dao.dao.OperDetailDao;
import cn.brotherChun.erp.invoice.operdetail.vo.OperDetailModel;
import cn.brotherChun.erp.invoice.order.business.ebi.OrderEbi;
import cn.brotherChun.erp.invoice.order.dao.dao.OrderDao;
import cn.brotherChun.erp.invoice.order.vo.OrderModel;
import cn.brotherChun.erp.invoice.order.vo.OrderQueryModel;
import cn.brotherChun.erp.invoice.orderdetail.dao.dao.OrderDetailDao;
import cn.brotherChun.erp.invoice.orderdetail.vo.OrderDetailModel;
import cn.brotherChun.erp.invoice.store.dao.dao.StoreDao;
import cn.brotherChun.erp.invoice.store.vo.StoreModel;
import cn.brotherChun.erp.invoice.storedetail.dao.dao.StoreDetailDao;
import cn.brotherChun.erp.invoice.storedetail.vo.StoreDetailModel;
import cn.brotherChun.erp.util.exception.AppException;
import cn.brotherChun.erp.util.num.NumUtil;

public class OrderEbo implements OrderEbi{
	private OrderDao orderDao;
	private OrderDetailDao orderDetailDao;
	private StoreDetailDao storeDetailDao;
	private OperDetailDao operDetailDao;

	public void setOperDetailDao(OperDetailDao operDetailDao) {
		this.operDetailDao = operDetailDao;
	}

	public void setStoreDetailDao(StoreDetailDao storeDetailDao) {
		this.storeDetailDao = storeDetailDao;
	}

	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

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
		//保存订单
		//设置订单号:订单号唯一
		order.setOrderNum(NumUtil.generatorOrderNum());
		//新保存的订单的状态是未审核
		order.setType(OrderModel.ORDER_TYPE_OF_BUY_NO_CHECK);
		//当前保存的是采购订单
		order.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_BUY);
		//订单创建时间是当前系统时间
		order.setCreateTime(System.currentTimeMillis());
		//制单人
		order.setCreater(creater);
		
		Integer totalNum=0;
		Double totalPrice=0.0d;
		
		Set<OrderDetailModel> set=new HashSet<OrderDetailModel>();
		for(int i=0;i<goodsUuids.length;i++){
			//创建订单明细的对象并添加到集合中
			OrderDetailModel odm=new OrderDetailModel();
			//设置订单明细的商品
			GoodsModel goods=new GoodsModel();
			goods.setUuid(goodsUuids[i]);
			odm.setGoods(goods);
			//设置订单明细数量
			odm.setNum(nums[i]);
			//设置订单明细单价
			odm.setPrice(prices[i]);
			//设置所属的订单
			odm.setOrder(order);
			//设置订单剩余未入库数量值
			odm.setSurplus(nums[i]);
			//将明细对象加入集合
			set.add(odm);
			totalNum+=nums[i];
			totalPrice+=nums[i]*prices[i];
		}
		//设置订单中对应的所有明细数据
		order.setOrderDetails(set);
		//设置订单总价值
		order.setTotalPrice(totalPrice);
		//设置订单总数量 
		order.setTotalNum(totalNum);

		orderDao.add(order);
	}

	public Integer getCountBuy(OrderQueryModel oqm) {
		oqm.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_BUY);
		return orderDao.getCount(oqm);
	}
	
	public List<OrderModel> getAllBuy(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount) {
		//设置一个固定的条件，订单类别为采购
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
		//快照更新
		OrderModel temp = orderDao.get(uuid);
		
		//逻辑校验：比对的数据必须是从数据库中取出的数据，而不能使用页面传递的数据
		if(!temp.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS)){
			throw new AppException("对不起，请不要进行非法操作！");
		}
		
		//审核人
		temp.setChecker(checker);
		//审核时间
		temp.setCheckTime(System.currentTimeMillis());
		//type
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
		//运输任务必须是已经审核通过的
		return orderDao.getAllTask(oqm,pageNum,pageCount,taskType);
	}

	public void assignTask(Long uuid, EmpModel completer) {
		OrderModel order=orderDao.get(uuid);
		//逻辑校验(集合包含性判定)
		if(!order.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS))
			throw new AppException("对不起，请不要进行非法操作！");
		//设置跟单人
		order.setCompleter(completer);
		//设置状态
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
	//快照
	public void endTasks(Long uuid) {
		OrderModel order = orderDao.get(uuid);
		//逻辑校验(集合包含性判定)
		if(!order.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_BUYING))
			throw new AppException("对不起，请不要进行非法操作！");
		//设置状态为入库中
		order.setType(OrderModel.ORDER_TYPE_OF_BUY_IN_STORE);
	}

	public Integer getCountStore(OrderQueryModel oqm) {
		oqm.setType(OrderModel.ORDER_TYPE_OF_BUY_IN_STORE);
		return orderDao.getCount(oqm);
	}

	public List<OrderModel> getAllStore(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount) {
		oqm.setType(OrderModel.ORDER_TYPE_OF_BUY_IN_STORE);
		return orderDao.getAll(oqm, pageNum, pageCount);
	}

	public OrderDetailModel inGoods(Long orderDetailUuid, Long storeUuid,
			Integer inStoreNum, EmpModel login) {
		//入库
		//订单明细中的剩余数量要更新
		OrderDetailModel orderDetail = orderDetailDao.get(orderDetailUuid);
		OrderModel order = orderDetail.getOrder();
		if(!order.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_IN_STORE)){
			throw new AppException("请不要非法操作！");
		}
		if(orderDetail.getSurplus()<inStoreNum){
			throw new AppException("请不要非法操作！");
		}
		//更新订单明细的剩余数量
		orderDetail.setSurplus(orderDetail.getSurplus()-inStoreNum);
		//库存数量要发生变化
		//使用快照更新数量
		//查询按照仓库与货物查询
		StoreDetailModel storeDetail=storeDetailDao.getByGoodsAndStore(orderDetail.getGoods().getUuid(),storeUuid);
		//判断该货物在指定仓库中有没有存储过
		if(storeDetail!=null){
			//如果存储过，快照更新
			//修改当前库存数量
			storeDetail.setNum(storeDetail.getNum()+inStoreNum);
		}else{
			//如果没有存储过，新增数据
			StoreDetailModel storeDetailTemp=new StoreDetailModel();
			GoodsModel goods=new GoodsModel();
			goods.setUuid(orderDetail.getGoods().getUuid());
			storeDetailTemp.setGoods(goods);
			StoreModel store=new StoreModel();
			store.setUuid(storeUuid);
			storeDetailTemp.setStore(store);
			storeDetailTemp.setNum(inStoreNum);
			storeDetailDao.add(storeDetailTemp);
		}
		//数据要求可追踪，记录操作日志
		OperDetailModel operDetail=new OperDetailModel();
		operDetail.setEmp(login);
		operDetail.setOperTime(System.currentTimeMillis());
		operDetail.setType(OperDetailModel.OPERDETAIL_OF_TYPE_IN);
		operDetail.setGoods(orderDetail.getGoods());
		StoreModel store=new StoreModel();
		store.setUuid(storeUuid);
		operDetail.setStore(store);
		operDetail.setNum(inStoreNum);
		operDetailDao.add(operDetail);

		//设置订单的状态为入库完毕
		int num=0;
		for(OrderDetailModel od:order.getOrderDetails()){
			num+=od.getSurplus();
		}
		if(num==0){
			//全部入库完毕
			order.setType(OrderModel.ORDER_TYPE_OF_BUY_COMPLETE);
			order.setEndTime(System.currentTimeMillis());
		}
		
		return orderDetail;
	}


}
