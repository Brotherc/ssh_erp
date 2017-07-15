package cn.brotherChun.erp.auth.menu.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.brotherChun.erp.auth.menu.dao.dao.MenuDao;
import cn.brotherChun.erp.auth.menu.vo.MenuModel;
import cn.brotherChun.erp.auth.menu.vo.MenuQueryModel;
import cn.brotherChun.erp.util.base.BaseDaoImpl;
import cn.brotherChun.erp.util.base.BaseQueryModel;

public class MenuDaoImpl extends BaseDaoImpl<MenuModel> implements MenuDao{
	@Override
	public void doCriteria(DetachedCriteria dc, BaseQueryModel bqm) {
		dc.add(Restrictions.not(Restrictions.eq("uuid", MenuModel.MENU_SYSTEM_MENU_UUID)));
		MenuModel menu=(MenuModel) bqm;
		if(menu.getUrl()!=null&&menu.getUrl().trim().length()>0)
			dc.add(Restrictions.eq("url", menu.getUrl().trim()));
		if(menu.getName()!=null&&menu.getName().trim().length()>0)
			dc.add(Restrictions.like("name", "%"+menu.getName().trim()+"%"));
		if(menu.getParent()!=null&&menu.getParent().getUuid()!=-1)
			dc.add(Restrictions.eq("parent.uuid", menu.getParent().getUuid()));
			
	}

	public List<MenuModel> getByPuuidIsOneOrZero() {
		String hql="from MenuModel where parent.uuid = ? or uuid = ?";
		return this.getHibernateTemplate().find(hql,MenuModel.MENU_SYSTEM_MENU_UUID,MenuModel.MENU_SYSTEM_MENU_UUID);
	}

}
