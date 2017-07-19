package cn.brotherChun.erp.invoice.goods.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.brotherChun.erp.invoice.goods.dao.dao.GoodsDao;
import cn.brotherChun.erp.invoice.goods.vo.GoodsModel;
import cn.brotherChun.erp.invoice.goods.vo.GoodsQueryModel;
import cn.brotherChun.erp.util.base.BaseDaoImpl;
import cn.brotherChun.erp.util.base.BaseQueryModel;

public class GoodsDaoImpl extends BaseDaoImpl<GoodsModel> implements GoodsDao{

	@Override
	public void doCriteria(DetachedCriteria dc, BaseQueryModel bqm) {
		GoodsQueryModel goods=(GoodsQueryModel) bqm;
		if(goods.getGtm()!=null&&goods.getGtm().getSupplier()!=null&&goods.getGtm().getSupplier().getUuid()!=null&&goods.getGtm().getSupplier().getUuid()!=-1){
			dc.createAlias("gtm", "g");
			dc.createAlias("g.supplier", "s");
			dc.add(Restrictions.eq("s.uuid",goods.getGtm().getSupplier().getUuid() ));
		}

		if(goods.getName()!=null&&goods.getName().trim().length()>0)
			dc.add(Restrictions.like("name", "%"+goods.getName().trim()+"%"));
		if(goods.getProducer()!=null&&goods.getProducer().trim().length()>0)
			dc.add(Restrictions.like("producer", "%"+goods.getProducer().trim()+"%"));
		if(goods.getUnit()!=null&&goods.getUnit().trim().length()>0)
			dc.add(Restrictions.like("unit", "%"+goods.getUnit().trim()+"%"));
		if(goods.getInPrice()!=null&&goods.getInPrice()>0)
			dc.add(Restrictions.ge("inPrice", goods.getInPrice()));
		if(goods.getInPrice2()!=null&&goods.getInPrice2()>0)
			dc.add(Restrictions.le("inPrice", goods.getInPrice2()));
		if(goods.getOutPrice()!=null&&goods.getOutPrice()>0)
			dc.add(Restrictions.ge("outPrice", goods.getOutPrice()));
		if(goods.getOutPrice2()!=null&&goods.getOutPrice2()>0)
			dc.add(Restrictions.le("outPrice", goods.getOutPrice2()));
	}

	public List<GoodsModel> getAllByGoodsTypeUuid(Long uuid) {
		String hql="from GoodsModel where gtm.uuid = ?";
		return this.getHibernateTemplate().find(hql,uuid);
	}

}
