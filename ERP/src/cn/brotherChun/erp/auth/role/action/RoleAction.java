package cn.brotherChun.erp.auth.role.action;

import java.util.List;
import java.util.Set;

import cn.brotherChun.erp.auth.res.business.ebi.ResEbi;
import cn.brotherChun.erp.auth.res.vo.ResModel;
import cn.brotherChun.erp.auth.role.business.ebi.RoleEbi;
import cn.brotherChun.erp.auth.role.vo.RoleModel;
import cn.brotherChun.erp.auth.role.vo.RoleQueryModel;
import cn.brotherChun.erp.util.base.BaseAction;

public class RoleAction extends BaseAction {
	
	private ResEbi resEbi;
	private RoleEbi roleEbi;
	public RoleModel role=new RoleModel();
	public RoleQueryModel roleQ=new RoleQueryModel();
	
	public Long[] reses;

	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}

	public void setRoleEbi(RoleEbi roleEbi) {
		this.roleEbi = roleEbi;
	}
	public String list(){
		setDataTotal(roleEbi.getCount(roleQ));
		List<RoleModel> temp = roleEbi.getAll(roleQ, maxPageNum, pageCount);
		put("roleList", temp);
		return LIST;
	}
	public String input(){
		List<ResModel> temp = resEbi.getAll();
		put("resList", temp);
		if(role.getUuid()!=null){
			role=roleEbi.get(role.getUuid());
			Set<ResModel> setRes = role.getReses();
			reses=new Long[setRes.size()];
			int i=0;
			for(ResModel res : setRes){
				reses[i++]=res.getUuid();
			}
		}	
		return INPUT;
	}
	public String save(){
		if(role.getUuid()!=null)
			roleEbi.update(role,reses);
		else 
			roleEbi.save(role,reses);
		return TO_LIST;
	}
	public String delete(){
		roleEbi.delete(role);
		return TO_LIST;
	}
	public Long[] getReses(){
		return reses;
	}
}
