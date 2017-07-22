package cn.brotherChun.erp.invoice.storedetail.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.brotherChun.erp.invoice.storedetail.dao.dao.StoreDetailDao;
import cn.brotherChun.erp.invoice.storedetail.vo.StoreDetailModel;
import cn.brotherChun.erp.util.base.BaseDaoImpl;
import cn.brotherChun.erp.util.base.BaseQueryModel;

public class StoreDetailDaoImpl extends BaseDaoImpl<StoreDetailModel> implements StoreDetailDao{

	@Override
	public void doCriteria(DetachedCriteria dc, BaseQueryModel bqm) {
		
	}

	public StoreDetailModel getByGoodsAndStore(Long uuid, Long storeUuid) {
		String hql="from StoreDetailModel where goods.uuid = ? and store.uuid = ?";
		List<StoreDetailModel> temp = this.getHibernateTemplate().find(hql,uuid,storeUuid);
		return temp.size()>0?temp.get(0):null;
	}

}
