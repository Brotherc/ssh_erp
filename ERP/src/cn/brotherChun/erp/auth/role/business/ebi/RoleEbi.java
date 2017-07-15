package cn.brotherChun.erp.auth.role.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import cn.brotherChun.erp.auth.role.vo.RoleModel;
import cn.brotherChun.erp.auth.role.vo.RoleQueryModel;
import cn.brotherChun.erp.util.base.BaseEbi;

@Transactional
public interface RoleEbi extends BaseEbi<RoleModel, RoleQueryModel>{
	public void save(RoleModel role,Long[] reses);
	public void update(RoleModel role,Long[] reses);
}
