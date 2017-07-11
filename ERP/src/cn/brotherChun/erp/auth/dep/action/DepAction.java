package cn.brotherChun.erp.auth.dep.action;

import java.util.List;

import cn.brotherChun.erp.auth.dep.business.ebi.DepEbi;
import cn.brotherChun.erp.auth.dep.vo.DepModel;
import cn.brotherChun.erp.auth.dep.vo.DepQueryModel;
import cn.brotherChun.erp.util.base.BaseAction;


public class DepAction extends BaseAction {
	
	public DepModel dep=new DepModel();
	public DepQueryModel depQ=new DepQueryModel();
	private DepEbi depEbi;
	
	public void setDepEbi(DepEbi depEbi) {
		this.depEbi = depEbi;
	}
	//查询功能
	public String list(){
		System.out.println("list");
		setDataTotal(depEbi.getCount(depQ));
		//根据查询条件获取数据(查询条件封装在depQ对象中)
		List<DepModel> temp=depEbi.getAll(depQ,pageNum,pageCount);
		//放入指定范围
		put("depList", temp);
		//跳转页面
		return LIST;
	}
	
	//跳转到添加页面
	public String input(){
		if(dep.getUuid()!=null){
			dep=depEbi.get(dep.getUuid());
		}
		return INPUT;
	}
	//添加功能
	public String save(){
		//根据页面传递的参数判断当前操作时添加还是修改，依据是否提供dep.uuid
		if(dep.getUuid()==null){
			//添加功能
			//将收集的值传递到业务层，完成保存功能
			depEbi.save(dep);		
		}else {
			//修改功能
			depEbi.update(dep);
		}

		return TO_LIST;
	}
	public String delete(){
		depEbi.delete(dep);
		return TO_LIST;
	}

}
