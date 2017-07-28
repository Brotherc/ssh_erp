package cn.brotherChun.erp.invoice.operdetail.vo;

import java.util.HashMap;
import java.util.Map;

import cn.brotherChun.erp.auth.emp.vo.EmpModel;
import cn.brotherChun.erp.invoice.goods.vo.GoodsModel;
import cn.brotherChun.erp.invoice.store.vo.StoreModel;
import cn.brotherChun.erp.util.format.FormatUtil;

public class OperDetailModel {
	public static final Integer OPERDETAIL_OF_TYPE_IN=1;
	public static final Integer OPERDETAIL_OF_TYPE_OUT=2;
	
	public static final String OPERDETAIL_OF_TYPE_VIEW_IN="入库";
	public static final String OPERDETAIL_OF_TYPE_VIEW_OUT="出库";
	
	public static final Map<Integer, String> typeMap=new HashMap<Integer, String>();
	
	static{
		typeMap.put(OPERDETAIL_OF_TYPE_IN, OPERDETAIL_OF_TYPE_VIEW_IN);
		typeMap.put(OPERDETAIL_OF_TYPE_OUT, OPERDETAIL_OF_TYPE_VIEW_OUT);
	}
	
	private Long uuid;
	private Integer num;

	private Integer type;
	private Long operTime;
	
	private String typeView;
	private String operTimeView;
	
	private EmpModel emp;
	private GoodsModel goods;
	private StoreModel store;
	
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
		this.typeView=typeMap.get(type);
	}
	public Long getOperTime() {
		return operTime;
	}
	public void setOperTime(Long operTime) {
		this.operTime = operTime;
		this.operTimeView=FormatUtil.formatDate(operTime);
	}
	
	
	public String getTypeView() {
		return typeView;
	}
	public String getOperTimeView() {
		return operTimeView;
	}
	
	
	public EmpModel getEmp() {
		return emp;
	}
	public void setEmp(EmpModel emp) {
		this.emp = emp;
	}
	public GoodsModel getGoods() {
		return goods;
	}
	public void setGoods(GoodsModel goods) {
		this.goods = goods;
	}
	public StoreModel getStore() {
		return store;
	}
	public void setStore(StoreModel store) {
		this.store = store;
	}

}
