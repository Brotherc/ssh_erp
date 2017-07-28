package cn.brotherChun.erp.util.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{

	protected Class<T> entityClass=null;
	
	//将entityClass初始化
	public BaseDaoImpl(){
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
		entityClass=(Class)params[0];
		
	}
	
	public void add(T t) {
		this.getHibernateTemplate().save(t);
	}

	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	public T get(Serializable uuid) {
		return this.getHibernateTemplate().get(entityClass, uuid);
	}

	public List<T> getAll() {
		DetachedCriteria criteria=DetachedCriteria.forClass(entityClass);
		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	public List<T> getAll(BaseQueryModel bqm, Integer pageNum, Integer pageCount) {
		DetachedCriteria dc=DetachedCriteria.forClass(entityClass);
		doCriteria(dc, bqm);
		return this.getHibernateTemplate().findByCriteria(dc,(pageNum-1)*pageCount,pageCount);
	}

	public Integer getCount(BaseQueryModel bqm) {
		DetachedCriteria dc=DetachedCriteria.forClass(entityClass);
		dc.setProjection(Projections.rowCount());
		doCriteria(dc, bqm);
		List<Long> count=this.getHibernateTemplate().findByCriteria(dc);
		return count.get(0).intValue();
	}

	public abstract void doCriteria(DetachedCriteria dc,BaseQueryModel bqm) ;
	
	
}
