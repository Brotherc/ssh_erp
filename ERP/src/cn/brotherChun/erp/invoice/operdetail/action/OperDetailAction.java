package cn.brotherChun.erp.invoice.operdetail.action;

import java.util.List;

import cn.brotherChun.erp.invoice.operdetail.business.ebi.OperDetailEbi;
import cn.brotherChun.erp.invoice.operdetail.vo.OperDetailModel;
import cn.brotherChun.erp.invoice.operdetail.vo.OperDetailQueryModel;
import cn.brotherChun.erp.util.base.BaseAction;

public class OperDetailAction extends BaseAction{
	private OperDetailEbi operDetailEbi;
	
	public OperDetailModel operDetail=new OperDetailModel();
	public OperDetailQueryModel odqm=new OperDetailQueryModel();
	
	public void setOperDetailEbi(OperDetailEbi operDetailEbi) {
		this.operDetailEbi = operDetailEbi;
	}
	
	public String list(){
		
		setDataTotal(operDetailEbi.getCount(odqm));
		List<OperDetailModel> temp=operDetailEbi.getAll(odqm,pageNum,pageCount);
		put("operDetailList", temp);
		return LIST;
		
	}
	public String save(){
		if(operDetail.getUuid()==null){
			operDetailEbi.save(operDetail);		
		}else {
			operDetailEbi.update(operDetail);
		}

		return TO_LIST;
	}
	public String input(){
		if(operDetail.getUuid()!=null){
			operDetail=operDetailEbi.get(operDetail.getUuid());
		}
		return INPUT;
	}
	public String delete(){
		operDetailEbi.delete(operDetail);
		return TO_LIST;
	}
}
