package cn.brotherChun.erp.auth.dep.business.ebo;

import java.io.Serializable;
import java.util.List;

import cn.brotherChun.erp.auth.dep.business.ebi.DepEbi;
import cn.brotherChun.erp.auth.dep.dao.dao.DepDao;
import cn.brotherChun.erp.auth.dep.vo.DepModel;
import cn.brotherChun.erp.auth.dep.vo.DepQueryModel;

public class DepEbo implements DepEbi {
	private DepDao depDao;

	public void setDepDao(DepDao depDao) {
		this.depDao = depDao;
	}

	public void save(DepModel dep) {
		//调用数据层持久化方法
		depDao.add(dep);
	}

	public void update(DepModel dep) {
		depDao.update(dep); 
	}

	public void delete(DepModel dep) {
		depDao.delete(dep);
	}

	public Integer getCount(DepQueryModel depQ) {
		return depDao.getCount(depQ);
	}

	public DepModel get(Serializable uuid) {
		return depDao.get(uuid);
	}

	public List<DepModel> getAll() {
		return depDao.getAll();
	}

	public List<DepModel> getAll(DepQueryModel q, Integer pageNum,Integer pageCount) {
		return depDao.getAll(q,pageNum,pageCount);
	}

}
