package cn.brotherChun.erp.invoice.store.action;

import java.util.List;

import cn.brotherChun.erp.invoice.store.business.ebi.StoreEbi;
import cn.brotherChun.erp.invoice.store.vo.StoreModel;
import cn.brotherChun.erp.invoice.store.vo.StoreQueryModel;
import cn.brotherChun.erp.util.base.BaseAction;

public class StoreAction extends BaseAction{
	private StoreEbi storeEbi;
	
	public StoreModel store=new StoreModel();
	public StoreQueryModel sqm=new StoreQueryModel();
	
	public void setStoreEbi(StoreEbi storeEbi) {
		this.storeEbi = storeEbi;
	}
	//列表
	public String list(){
		
		setDataTotal(storeEbi.getCount(sqm));
		List<StoreModel> temp=storeEbi.getAll(sqm,pageNum,pageCount);
		put("storeList", temp);
		return LIST;
		
	}
	//添加
	public String save(){
		if(store.getUuid()==null){
			storeEbi.save(store);		
		}else {
			storeEbi.update(store);
		}

		return TO_LIST;
	}
	//到添加
	public String input(){
		if(store.getUuid()!=null){
			store=storeEbi.get(store.getUuid());
		}
		return INPUT;
	}
	//删除
	public String delete(){
		storeEbi.delete(store);
		return TO_LIST;
	}

}
