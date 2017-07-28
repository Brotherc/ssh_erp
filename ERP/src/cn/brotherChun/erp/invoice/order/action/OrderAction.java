package cn.brotherChun.erp.invoice.order.action;

import java.util.List;

import cn.brotherChun.erp.auth.emp.business.ebi.EmpEbi;
import cn.brotherChun.erp.auth.emp.vo.EmpModel;
import cn.brotherChun.erp.invoice.goods.business.ebi.GoodsEbi;
import cn.brotherChun.erp.invoice.goods.vo.GoodsModel;
import cn.brotherChun.erp.invoice.goodstype.business.ebi.GoodsTypeEbi;
import cn.brotherChun.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.brotherChun.erp.invoice.order.business.ebi.OrderEbi;
import cn.brotherChun.erp.invoice.order.vo.OrderModel;
import cn.brotherChun.erp.invoice.order.vo.OrderQueryModel;
import cn.brotherChun.erp.invoice.orderdetail.vo.OrderDetailModel;
import cn.brotherChun.erp.invoice.store.business.ebi.StoreEbi;
import cn.brotherChun.erp.invoice.store.vo.StoreModel;
import cn.brotherChun.erp.invoice.supplier.business.ebi.SupplierEbi;
import cn.brotherChun.erp.invoice.supplier.vo.SupplierModel;
import cn.brotherChun.erp.util.base.BaseAction;

public class OrderAction extends BaseAction{
	private OrderEbi orderEbi;
	private SupplierEbi supplierEbi;
	private GoodsTypeEbi goodsTypeEbi;
	private GoodsEbi goodsEbi;
	private EmpEbi empEbi;
	private StoreEbi storeEbi;
	

	public Long goodsTypeUuid;
	public Long goodsUuid;

	
	public OrderModel order=new OrderModel();
	public OrderQueryModel oqm=new OrderQueryModel();
	
	public void setStoreEbi(StoreEbi storeEbi) {
		this.storeEbi = storeEbi;
	}
	public void setEmpEbi(EmpEbi empEbi) {
		this.empEbi = empEbi;
	}
	public void setGoodsEbi(GoodsEbi goodsEbi) {
		this.goodsEbi = goodsEbi;
	}
	public void setGoodsTypeEbi(GoodsTypeEbi goodsTypeEbi) {
		this.goodsTypeEbi = goodsTypeEbi;
	}
	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}
	public void setOrderEbi(OrderEbi orderEbi) {
		this.orderEbi = orderEbi;
	}
	//列表
	public String list(){
		
		setDataTotal(orderEbi.getCount(oqm));
		List<OrderModel> temp=orderEbi.getAll(oqm,pageNum,pageCount);
		put("orderList", temp);
		return LIST;
		
	}
	//添加
	public String save(){
		if(order.getUuid()==null){
			orderEbi.save(order);		
		}else {
			orderEbi.update(order);
		}

		return TO_LIST;
	}
	//到添加
	public String input(){
		if(order.getUuid()!=null){
			order=orderEbi.get(order.getUuid());
		}
		return INPUT;
	}
	//删除
	public String delete(){
		orderEbi.delete(order);
		return TO_LIST;
	}
	
	//-----------------采购相关-------------------------
	public String buyList(){
		setDataTotal(orderEbi.getCountBuy(oqm));
		List<OrderModel> temp=orderEbi.getAllBuy(oqm,pageNum,pageCount);
		put("orderList", temp);
		return "buyList";
	}
	//进入采购订单
	public String buyInput(){
		//加载具有类别信息的供应商信息，类别必须具有商品信息
		List<SupplierModel> supplierTemp=supplierEbi.getAllUnionTwo();
		put("supplierList", supplierTemp);
		//第一个供应商的具有商品的类别数据
		List<GoodsTypeModel> goodsTypeTemp=goodsTypeEbi.getAllUnionTwoBySupplier(supplierTemp.get(0).getUuid());
		put("goodsTypeList", goodsTypeTemp);
		//第一个类别的商品
		List<GoodsModel> goodsTemp=goodsEbi.getAllByGoodsType(goodsTypeTemp.get(0).getUuid());
		put("goodsList", goodsTemp);
		//第一个商品的价格
		return "buyInput";
	}
	
	
	
	private List<GoodsTypeModel> goodsTypeList;
	private List<GoodsModel> goodsList;
	private GoodsModel goods;
	
	public GoodsModel getGoods() {
		return goods;
	}
	public List<GoodsTypeModel> getGoodsTypeList() {
		return goodsTypeList;
	}
	public List<GoodsModel> getGoodsList() {
		return goodsList;
	}
	
	public Long[] goodsUuids;
	public Integer[] nums;
	public Double[] prices;
	//保存采购订单
	public String buySave(){
		orderEbi.saveBuy(order,goodsUuids,nums,prices,getLogin());
		return "toBuyList";
	}
	//查看采购订单明细
	public String buyDetail(){
		//根据order.uuid获取对应信息，加载到详情页
		order=orderEbi.get(order.getUuid());
		return "buyDetail";
	}
	
	//-----------------------------------------------------
	//-----------------------------------------------------
	//----------------采购审核相关开始---------------------
	//-----------------------------------------------------
	//-----------------------------------------------------
	
	public String buyCheckList(){
		//要加载订单数据列表
		setDataTotal(orderEbi.getCountBuyCheck(oqm));
		List<OrderModel> temp=orderEbi.getAllBuyCheck(oqm,pageNum,pageCount);
		put("orderList", temp);
		return "buyCheckList";
	}

	public String buyCheckDetail(){
		//根据order.uuid查询order，页面显示
		order=orderEbi.get(order.getUuid());
		return "buyCheckDetail";
	}
	//采购审核通过
	public String buyCheckPass(){
		//业务层
		orderEbi.updateBuyCheckPass(order.getUuid(),getLogin());
		//跳页面
		return "toBuyCheckPass";
	}
	//采购审核驳回
	public String buyCheckNoPass(){
		//业务层
		orderEbi.updateBuyCheckNoPass(order.getUuid(),getLogin());
		//跳页面
		return "toBuyCheckNoPass";
	}
	
	//----------------任务指派-------------------
	public String taskList(){
		List<SupplierModel> supplierTemp=supplierEbi.getAll();
		put("supplierList", supplierTemp);
		setDataTotal(orderEbi.getCountTask(oqm));
		List<OrderModel> temp=orderEbi.getAllTask(oqm,pageNum,pageCount);
		put("orderList", temp);
		
		return "taskList";
	}
	public String taskDetail(){
		//加载运输部门的所有员工信息
		List<EmpModel>empList=empEbi.getAllByDep(getLogin().getDep().getUuid());
		put("empList", empList);
		order=orderEbi.get(order.getUuid());
		return "taskDetail";
	}
	//指派任务
	public String assignTask(){
		orderEbi.assignTask(order.getUuid(),order.getCompleter());
		return "toTaskList";
	}
	
	//查询当前登录人运输任务
	public String tasks(){
		setDataTotal(orderEbi.getCountTasksByCompleter(oqm,getLogin()));
		List<OrderModel> temp=orderEbi.getAllTasksByCompleter(oqm,pageNum,pageCount,getLogin());
		put("orderList", temp);
		return "tasks";
	}
	public String tasksDetail(){
		order=orderEbi.get(order.getUuid());
		return "tasksDetail";
	}
	//运输任务完成
	public String endTasks(){
		orderEbi.endTasks(order.getUuid());
		return "toTasks";
	}
	//--------------------入库-------------------
	public String inStoreList(){
		setDataTotal(orderEbi.getCountStore(oqm));
		List<OrderModel> temp=orderEbi.getAllStore(oqm,pageNum,pageCount);
		put("orderList", temp);
		return "inStoreList";
	}
	
	//进入入库操作明细页
	public String inStoreDetail(){
		//加载所有仓库信息
		List<StoreModel> temp = storeEbi.getAll();
		put("storeList", temp);
		System.out.println(temp.size());
		order=orderEbi.get(order.getUuid());
		return "inStoreDetail";
	}
	
	//-----------------ajax---------------
	//-----------------ajax---------------
	//-----------------ajax---------------
	public Long supplierUuid;
	//已经使用过的，需要过滤的商品uuid
	public String usedGoodsUuid;
	//ajax根据供应商的uuid获取类别和商品信息(具有数据过滤功能)
	public String ajaxGetGtmAndGm(){
		goodsTypeList=goodsTypeEbi.getAllUnionTwoBySupplier(supplierUuid);
		if(usedGoodsUuid!=null){
			//过滤掉所有商品已经使用完毕的商品类别
			goodsType:
				for(int i=goodsTypeList.size()-1;i>=0;i--){	
					List<GoodsModel> goodsTemp=goodsEbi.getAllByGoodsType(goodsTypeList.get(i).getUuid());
					for(GoodsModel goods:goodsTemp){
						if(!usedGoodsUuid.contains(""+goods.getUuid()+"")){
							//保留该类别，直接判断下一个类别
							continue goodsType;
						}
					}
					//循环执行完毕，执行到的此处，上述循环没有进入if语句，里面的商品都使用过
					goodsTypeList.remove(i);
				}
		//上述集合中的类别一定都存在没有使用过的商品
		}
		goodsList=goodsEbi.getAllByGoodsType(goodsTypeList.get(0).getUuid());
		if(usedGoodsUuid!=null){
			//当前获取的商品信息的uuid具有重复的，要对其进行过滤
			//对集合进行迭代删除，必须逆向进行
			//从goodsList中取出所有的元素，挨个迭代，与本次传递过来的usedGoodsUuid进行比对，比对完发现重复的，删除掉（逆序进行）
			for(int i=goodsList.size()-1;i>=0;i--){
				//判断该uuid是否出现在usedGoodsUuid中
				if(usedGoodsUuid.contains(""+goodsList.get(i).getUuid()+"")){
					goodsList.remove(i);
				}
			}
		}
		goods=goodsList.get(0);
		return "ajaxGetGtmAndGm";
	}
	
	//ajax根据商品类别uuid获取商品信息
	public String ajaxGetGm(){
		goodsList=goodsEbi.getAllByGoodsType(goodsTypeUuid);
		//过滤数据
		if(usedGoodsUuid!=null){
			for(int i=goodsList.size()-1;i>=0;i--){
				//判断该uuid是否出现在usedGoodsUuid中
				if(usedGoodsUuid.contains(""+goodsList.get(i).getUuid()+"")){
					goodsList.remove(i);
				}
			}
		}
		
		goods=goodsList.get(0);
		return "ajaxGetGm";
	}
	//ajax根据商品uuid获取商品价格信息
	public String ajaxGetGmPrice(){
		goods = goodsEbi.get(goodsUuid);
		return "ajaxGetGmPrice";
	}
	
	public Long orderDetailUuid;
	public Integer inStoreNum;
	public Long storeUuid;
	
	private OrderDetailModel orderDetail;
	
	public OrderDetailModel getOrderDetail() {
		return orderDetail;
	}
	public String ajaxInGoods(){
		orderDetail = orderEbi.inGoods(orderDetailUuid,storeUuid,inStoreNum,getLogin());
		return "ajaxInGoods";
	}
}
