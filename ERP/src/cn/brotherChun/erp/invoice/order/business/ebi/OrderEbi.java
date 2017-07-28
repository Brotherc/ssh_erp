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

	/**
	 * 保存采购订单
	 * @param order 订单数据模型（封装了供应商uuid）
	 * @param goodsUuids 商品UUID数组
	 * @param nums	数量数组
	 * @param prices 单价数组
	 * @param login 制单人
	 */
	public void saveBuy(OrderModel order, Long[] goodsUuids, Integer[] nums,
			Double[] prices, EmpModel login);

	/**
	 * 获取所有采购订单数据
	 * @param oqm
	 * @param pageNum
	 * @param pageCount
	 * @return
	 */
	public List<OrderModel> getAllBuy(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount);

	public Integer getCountBuyCheck(OrderQueryModel oqm);

	public List<OrderModel> getAllBuyCheck(OrderQueryModel oqm,
			Integer pageNum, Integer pageCount);

	public Integer getCountBuy(OrderQueryModel oqm);

	/**
	 * 采购审核通过
	 * @param uuid 被审核订单uuid
	 * @param login 审核人
	 */
	public void updateBuyCheckPass(Long uuid, EmpModel login);
	
	public void updateBuyCheckNoPass(Long uuid, EmpModel login);

	public Integer getCountTask(OrderQueryModel oqm);

	public List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount);

	/**
	 * 指派运输任务
	 * @param uuid 订单uuid
	 * @param completer 跟单人
	 */
	public void assignTask(Long uuid, EmpModel completer);

	public List<OrderModel> getAllTasksByCompleter(OrderQueryModel oqm,
			Integer pageNum, Integer pageCount, EmpModel login);

	public Integer getCountTasksByCompleter(OrderQueryModel oqm, EmpModel login);

	public void endTasks(Long uuid);

	public Integer getCountStore(OrderQueryModel oqm);

	public List<OrderModel> getAllStore(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount);


	/**
	 * 进行商品入库操作
	 * @param orderDetailUuid 对应入库商品的订单详情的uuid
	 * @param storeUuid 准备入库的仓库uuid
	 * @param inStoreNum 对应商品的入库量
	 * @param login 仓库操作人员
	 * @return
	 */
	public OrderDetailModel inGoods(Long orderDetailUuid, Long storeUuid,
			Integer inStoreNum, EmpModel login);

}
