package cn.brotherChun.erp.auth.menu.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.brotherChun.erp.auth.menu.vo.MenuModel;
import cn.brotherChun.erp.auth.menu.vo.MenuQueryModel;
import cn.brotherChun.erp.util.base.BaseEbi;

@Transactional
public interface MenuEbi extends BaseEbi<MenuModel,MenuQueryModel>{

	public List<MenuModel> getAllOneLevel();
}
