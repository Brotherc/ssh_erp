package cn.brotherChun.erp.invoice.supplier.action;

import java.util.List;

import cn.brotherChun.erp.invoice.supplier.business.ebi.SupplierEbi;
import cn.brotherChun.erp.invoice.supplier.vo.SupplierModel;
import cn.brotherChun.erp.invoice.supplier.vo.SupplierQueryModel;
import cn.brotherChun.erp.util.base.BaseAction;

public class SupplierAction extends BaseAction{
	
	private SupplierEbi supplierEbi;
	
	public SupplierModel sm=new SupplierModel();
	public SupplierQueryModel sqm=new SupplierQueryModel();
	
	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}
	//列表
	public String list(){
		
		setDataTotal(supplierEbi.getCount(sqm));
		List<SupplierModel> temp=supplierEbi.getAll(sqm,pageNum,pageCount);
		put("supplierList", temp);
		return LIST;
		
	}
	//添加
	public String save(){
		if(sm.getUuid()==null){
			supplierEbi.save(sm);		
		}else {
			supplierEbi.update(sm);
		}

		return TO_LIST;
	}
	//到添加
	public String input(){
		if(sm.getUuid()!=null){
			sm=supplierEbi.get(sm.getUuid());
		}
		return INPUT;
	}
	//删除
	public String delete(){
		supplierEbi.delete(sm);
		return TO_LIST;
	}
}
