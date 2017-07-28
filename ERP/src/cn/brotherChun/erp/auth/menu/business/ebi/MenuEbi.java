package cn.brotherChun.erp.auth.menu.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.brotherChun.erp.auth.menu.vo.MenuModel;
import cn.brotherChun.erp.auth.menu.vo.MenuQueryModel;
import cn.brotherChun.erp.util.base.BaseEbi;

@Transactional
public interface MenuEbi extends BaseEbi<MenuModel,MenuQueryModel>{

	/**
	 * 获取系统菜单和所有一级菜单
	 * @return
	 */
	public List<MenuModel> getAllOneLevel();

	public void save(MenuModel mm, Long[] roleUuids);

	public void update(MenuModel mm, Long[] roleUuids);

	/**
	 * 获取指定员工对应的所有可操作的一级菜单
	 * @param uuid 员工uuid
	 * @return
	 */
	public List<MenuModel> getAllOneLevel2(Long uuid);

	/**
	 * 获取指定员工对应的指定一级菜单可操作的二级菜单
	 * @param uuid 员工uuid
	 * @param puuid 一级菜单uuid
	 * @return
	 */
	public List<MenuModel> getAllTwoLevel(Long uuid, Long puuid);
}
