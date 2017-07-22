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
import cn.brotherChun.erp.invoice.orderdetail.business.ebi.OrderDetailEbi;
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
	
	public Long supplierUuid;
	public Long goodsTypeUuid;
	public Long goodsUuid;
	public String usedGoodsUuid;
	
	public Long[] goodsUuids;
	public Integer[] nums;
	public Double[] prices;
	
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
	public void setOrder(OrderModel order) {
		this.order = order;
	}
	public void setOqm(OrderQueryModel oqm) {
		this.oqm = oqm;
	}
	public void setOrderEbi(OrderEbi orderEbi) {
		this.orderEbi = orderEbi;
	}
	public String list(){
		
		setDataTotal(orderEbi.getCount(oqm));
		List<OrderModel> temp=orderEbi.getAll(oqm,pageNum,pageCount);
		put("orderList", temp);
		return LIST;
		
	}
	public String save(){
		if(order.getUuid()==null){
			orderEbi.save(order);		
		}else {
			orderEbi.update(order);
		}

		return TO_LIST;
	}
	public String input(){
		if(order.getUuid()!=null){
			order=orderEbi.get(order.getUuid());
		}
		return INPUT;
	}
	public String delete(){
		orderEbi.delete(order);
		return TO_LIST;
	}
	public String buyList(){
		setDataTotal(orderEbi.getCountBuy(oqm));
		List<OrderModel> temp=orderEbi.getAllBuy(oqm,pageNum,pageCount);
		put("orderList", temp);
		return "buyList";
	}
	public String buyInput(){
		List<SupplierModel> supplierTemp=supplierEbi.getAllUnionTwo();
		put("supplierList", supplierTemp);
		List<GoodsTypeModel> goodsTypeTemp=goodsTypeEbi.getAllUnionTwoBySupplier(supplierTemp.get(0).getUuid());
		put("goodsTypeList", goodsTypeTemp);
		List<GoodsModel> goodsTemp=goodsEbi.getAllByGoodsType(goodsTypeTemp.get(0).getUuid());
		put("goodsList", goodsTemp);
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
	public String buySave(){
		orderEbi.saveBuy(order,goodsUuids,nums,prices,getLogin());
		return "toBuyList";
	}
	public String buyDetail(){
		order=orderEbi.get(order.getUuid());
		return "buyDetail";
	}
	public String buyCheckList(){
		setDataTotal(orderEbi.getCountBuyCheck(oqm));
		List<OrderModel> temp=orderEbi.getAllBuyCheck(oqm,pageNum,pageCount);
		put("orderList", temp);
		return "buyCheckList";
	}
	public String buyCheckDetail(){
		order=orderEbi.get(order.getUuid());
		return "buyCheckDetail";
	}
	public String buyCheckPass(){
		orderEbi.updateBuyCheckPass(order.getUuid(),getLogin());
		return "toBuyCheckPass";
	}
	public String buyCheckNoPass(){
		orderEbi.updateBuyCheckNoPass(order.getUuid(),getLogin());
		return "toBuyCheckNoPass";
	}
	
	//任务指派
	public String taskList(){
		List<SupplierModel> supplierTemp=supplierEbi.getAll();
		put("supplierList", supplierTemp);
		setDataTotal(orderEbi.getCountTask(oqm));
		List<OrderModel> temp=orderEbi.getAllTask(oqm,pageNum,pageCount);
		put("orderList", temp);
		
		return "taskList";
	}
	public String taskDetail(){
		List<EmpModel>empList=empEbi.getAllByDep(getLogin().getDep().getUuid());
		put("empList", empList);
		order=orderEbi.get(order.getUuid());
		return "taskDetail";
	}
	public String assignTask(){
		orderEbi.assignTask(order.getUuid(),order.getCompleter());
		return "toTaskList";
	}
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
	public String endTasks(){
		orderEbi.endTasks(order.getUuid());
		return "toTasks";
	}
	//入库
	public String inStoreList(){
		setDataTotal(orderEbi.getCountStore(oqm));
		List<OrderModel> temp=orderEbi.getAllStore(oqm,pageNum,pageCount);
		put("orderList", temp);
		return "inStoreList";
	}
	public String inStoreDetail(){
		List<StoreModel> temp = storeEbi.getAll();
		put("storeList", temp);
		System.out.println(temp.size());
		order=orderEbi.get(order.getUuid());
		return "inStoreDetail";
	}
	
	//-----------ajax---------
	public String ajaxGetGtmAndGm(){
		
		goodsTypeList=goodsTypeEbi.getAllUnionTwoBySupplier(supplierUuid);
		
		if(usedGoodsUuid!=null){
			goodsType:
				for(int i=goodsTypeList.size()-1;i>=0;i--){	
					List<GoodsModel> goodsTemp=goodsEbi.getAllByGoodsType(goodsTypeList.get(i).getUuid());
					for(GoodsModel goods:goodsTemp){
						if(!usedGoodsUuid.contains(""+goods.getUuid()+"")){
							continue goodsType;
						}
					}
					goodsTypeList.remove(i);
				}
		}

		goodsList=goodsEbi.getAllByGoodsType(goodsTypeList.get(0).getUuid());
		
		if(usedGoodsUuid!=null){
			for(int i=goodsList.size()-1;i>=0;i--){
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
		
		if(usedGoodsUuid!=null){
			for(int i=goodsList.size()-1;i>=0;i--){
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
	
	private OrderDetailModel orderDetail;//入库后返回的订单数据，页面修改对应的剩余量与入库量
	
	public OrderDetailModel getOrderDetail() {
		return orderDetail;
	}
	public String ajaxInGoods(){
		orderDetail = orderEbi.inGoods(orderDetailUuid,storeUuid,inStoreNum,getLogin());
		return "ajaxInGoods";
	}
}
