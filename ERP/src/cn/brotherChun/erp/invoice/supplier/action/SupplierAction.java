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
	public void setSm(SupplierModel sm) {
		this.sm = sm;
	}
	public void setSqm(SupplierQueryModel sqm) {
		this.sqm = sqm;
	}
	
	public String list(){
		
		setDataTotal(supplierEbi.getCount(sqm));
		List<SupplierModel> temp=supplierEbi.getAll(sqm,pageNum,pageCount);
		put("supplierList", temp);
		return LIST;
		
	}
	public String save(){
		if(sm.getUuid()==null){
			supplierEbi.save(sm);		
		}else {
			supplierEbi.update(sm);
		}

		return TO_LIST;
	}
	public String input(){
		if(sm.getUuid()!=null){
			sm=supplierEbi.get(sm.getUuid());
		}
		return INPUT;
	}
	public String delete(){
		supplierEbi.delete(sm);
		return TO_LIST;
	}
}
