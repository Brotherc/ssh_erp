package cn.brotherChun.erp.invoice.goodstype.action;

import java.util.List;

import cn.brotherChun.erp.invoice.goodstype.business.ebi.GoodsTypeEbi;
import cn.brotherChun.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.brotherChun.erp.invoice.goodstype.vo.GoodsTypeQueryModel;
import cn.brotherChun.erp.invoice.supplier.business.ebi.SupplierEbi;
import cn.brotherChun.erp.invoice.supplier.vo.SupplierModel;
import cn.brotherChun.erp.util.base.BaseAction;

public class GoodsTypeAction extends BaseAction{
	
	private GoodsTypeEbi goodsTypeEbi;
	private SupplierEbi supplierEbi;
	
	public GoodsTypeModel gtm=new GoodsTypeModel();
	public GoodsTypeQueryModel gtqm=new GoodsTypeQueryModel();

	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}
	public void setGoodsTypeEbi(GoodsTypeEbi goodsTypeEbi) {
		this.goodsTypeEbi = goodsTypeEbi;
	}

	//列表
	public String list(){
		
		setDataTotal(goodsTypeEbi.getCount(gtqm));
		List<SupplierModel> supplierTemp = supplierEbi.getAll();
		List<GoodsTypeModel> temp=goodsTypeEbi.getAll(gtqm,pageNum,pageCount);
		put("goodsTypeList", temp);
		put("supplierList", supplierTemp);
		return LIST;
		
	}
	//添加
	public String save(){
		if(gtm.getUuid()==null){
			goodsTypeEbi.save(gtm);		
		}else {
			goodsTypeEbi.update(gtm);
		}

		return TO_LIST;
	}
	//到添加
	public String input(){
		List<SupplierModel> supplierTemp = supplierEbi.getAll();

		put("supplierList", supplierTemp);
		if(gtm.getUuid()!=null){
			gtm=goodsTypeEbi.get(gtm.getUuid());
		}
		return INPUT;
	}
	//删除
	public String delete(){
		goodsTypeEbi.delete(gtm);
		return TO_LIST;
	}
	
	//----AJAX-----------------------------------------------
	//----AJAX-----------------------------------------------
	
	private List<GoodsTypeModel> goodsTypeList;
	
	public List<GoodsTypeModel> getGoodsTypeList() {
		return goodsTypeList;
	}
	//ajax获取供应商对应的类别信息
	public String ajaxGetAllBySupplier(){
		//根据供应商的uuid获取对应的类别信息
		goodsTypeList = goodsTypeEbi.getAllBySupplier(gtm.getSupplier().getUuid());
		return "ajaxGetAllBySupplier";
	}
}
