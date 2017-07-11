package cn.brotherChun.erp.util.base;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
	//添加
	public void add(T t);
	//删除
	public void delete(T t);
	//修改
	public void update(T t);
	//查询单一数据
	public T get(Serializable uuid);
	//查询全部数据
	public List<T> getAll();
	//按条件分页查询
	public List<T> getAll(BaseQueryModel bqm,Integer pageNum,Integer pageCount);
	//按条件获取数据总量
	public Integer getCount(BaseQueryModel bqm);
}
