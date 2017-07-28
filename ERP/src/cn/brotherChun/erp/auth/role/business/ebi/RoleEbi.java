package cn.brotherChun.erp.auth.role.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import cn.brotherChun.erp.auth.role.vo.RoleModel;
import cn.brotherChun.erp.auth.role.vo.RoleQueryModel;
import cn.brotherChun.erp.util.base.BaseEbi;

@Transactional
public interface RoleEbi extends BaseEbi<RoleModel, RoleQueryModel>{
	public void save(RoleModel role,Long[] reses);
	public void update(RoleModel role,Long[] reses);
	
	/**
	 * 修改角色信息
	 * @param role 角色信息数据模型
	 * @param reses 角色关联资源uuid数组
	 * @param menus 角色关联菜单uuid数组
	 */
	public void update(RoleModel role, Long[] reses, Long[] menus);
	
	/**
	 * 保存角色信息
	 * @param role 角色信息数据模型
	 * @param reses 角色关联资源uuid数组
	 * @param menus 角色关联菜单uuid数组
	 */
	public void save(RoleModel role, Long[] reses, Long[] menus);
}
