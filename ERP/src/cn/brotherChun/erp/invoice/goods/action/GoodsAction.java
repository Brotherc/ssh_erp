package cn.brotherChun.erp.invoice.goods.action;

import java.util.List;

import cn.brotherChun.erp.invoice.goods.business.ebi.GoodsEbi;
import cn.brotherChun.erp.invoice.goods.vo.GoodsModel;
import cn.brotherChun.erp.invoice.goods.vo.GoodsQueryModel;
import cn.brotherChun.erp.invoice.goodstype.business.ebi.GoodsTypeEbi;
import cn.brotherChun.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.brotherChun.erp.invoice.supplier.business.ebi.SupplierEbi;
import cn.brotherChun.erp.invoice.supplier.vo.SupplierModel;
import cn.brotherChun.erp.util.base.BaseAction;

public class GoodsAction extends BaseAction{
	
	private GoodsEbi goodsEbi;
	private SupplierEbi supplierEbi;
	private GoodsTypeEbi goodsTypeEbi;

	public GoodsModel goods=new GoodsModel();
	public GoodsQueryModel gqm=new GoodsQueryModel();
	
	public void setGoodsTypeEbi(GoodsTypeEbi goodsTypeEbi) {
		this.goodsTypeEbi = goodsTypeEbi;
	}
	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}
	public void setGoodsEbi(GoodsEbi goodsEbi) {
		this.goodsEbi = goodsEbi;
	}
	
	//列表
	public String list(){
		//加载供应商全信息
		List<SupplierModel> supplierTemp=supplierEbi.getAll();
		put("supplierList", supplierTemp);
		System.out.println(gqm.getInPrice2());
		System.out.println(gqm.getOutPrice2());
		setDataTotal(goodsEbi.getCount(gqm));
		List<GoodsModel> temp=goodsEbi.getAll(gqm,pageNum,pageCount);
		put("goodsList", temp);
		return LIST;
		
	}
	//添加
	public String save(){
		if(goods.getUuid()==null){
			goodsEbi.save(goods);		
		}else {
			goodsEbi.update(goods);
		}

		return TO_LIST;
	}
	//到添加
	public String input(){
		//加载所有具有类别信息的供应商信息
		List<SupplierModel> supplierTemp = supplierEbi.getAllUnion();
		put("supplierList", supplierTemp);
		//加载第一个供应商对应的所有商品类别信息
		List<GoodsTypeModel> gtTemp=null;
		Long goodsSupplierUuid=null;

		if(goods.getUuid()!=null){
			goods=goodsEbi.get(goods.getUuid());
			goodsSupplierUuid=goods.getGtm().getSupplier().getUuid();
		}else{
			goodsSupplierUuid=supplierTemp.get(0).getUuid();
		}
		gtTemp=goodsTypeEbi.getAllBySupplier(goodsSupplierUuid);
		put("goodsTypeList", gtTemp);
		return INPUT;
	}
	//删除
	public String delete(){
		goodsEbi.delete(goods);
		return TO_LIST;
	}
}
