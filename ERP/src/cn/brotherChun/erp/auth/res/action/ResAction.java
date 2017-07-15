package cn.brotherChun.erp.auth.res.action;

import java.util.List;

import cn.brotherChun.erp.auth.res.business.ebi.ResEbi;
import cn.brotherChun.erp.auth.res.vo.ResModel;
import cn.brotherChun.erp.auth.res.vo.ResQueryModel;
import cn.brotherChun.erp.util.base.BaseAction;

public class ResAction extends BaseAction{
	
	private ResEbi resEbi;
	public ResModel res=new ResModel();
	public ResQueryModel resQ=new ResQueryModel();

	public ResEbi getResEbi() {
		return resEbi;
	}

	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}
	public String list(){
		setDataTotal(resEbi.getCount(resQ));
		List<ResModel> temp = resEbi.getAll(resQ, maxPageNum, pageCount);
		put("resList", temp);
		return LIST;
	}
	public String input(){
		if(res.getUuid()!=null)
			res=resEbi.get(res.getUuid());
		return INPUT;
	}
	public String save(){
		if(res.getUuid()!=null)
			resEbi.update(res);
		else 
			resEbi.save(res);
		return TO_LIST;
	}
	public String delete(){
		resEbi.delete(res);
		return TO_LIST;
	}
}
