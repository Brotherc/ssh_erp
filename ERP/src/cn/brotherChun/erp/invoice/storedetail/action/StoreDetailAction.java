package cn.brotherChun.erp.invoice.storedetail.action;

import java.util.List;

import cn.brotherChun.erp.invoice.storedetail.business.ebi.StoreDetailEbi;
import cn.brotherChun.erp.invoice.storedetail.vo.StoreDetailModel;
import cn.brotherChun.erp.invoice.storedetail.vo.StoreDetailQueryModel;
import cn.brotherChun.erp.invoice.supplier.vo.SupplierModel;
import cn.brotherChun.erp.util.base.BaseAction;

public class StoreDetailAction extends BaseAction{
	private StoreDetailEbi storeDetailEbi;
	
	public StoreDetailModel storeDetail=new StoreDetailModel();
	public StoreDetailQueryModel sdqm=new StoreDetailQueryModel();
	
	public void setStoreDetailEbi(StoreDetailEbi storeDetailEbi) {
		this.storeDetailEbi = storeDetailEbi;
	}
	public String list(){
		
		setDataTotal(storeDetailEbi.getCount(sdqm));
		List<StoreDetailModel> temp=storeDetailEbi.getAll(sdqm,pageNum,pageCount);
		put("storeDetailList", temp);
		return LIST;
		
	}
	public String save(){
		if(storeDetail.getUuid()==null){
			storeDetailEbi.save(storeDetail);		
		}else {
			storeDetailEbi.update(storeDetail);
		}

		return TO_LIST;
	}
	public String input(){
		if(storeDetail.getUuid()!=null){
			storeDetail=storeDetailEbi.get(storeDetail.getUuid());
		}
		return INPUT;
	}
	public String delete(){
		storeDetailEbi.delete(storeDetail);
		return TO_LIST;
	}
	
}
