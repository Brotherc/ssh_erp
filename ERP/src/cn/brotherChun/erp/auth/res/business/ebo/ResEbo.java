package cn.brotherChun.erp.auth.res.business.ebo;

import java.io.Serializable;
import java.util.List;

import cn.brotherChun.erp.auth.res.business.ebi.ResEbi;
import cn.brotherChun.erp.auth.res.dao.dao.ResDao;
import cn.brotherChun.erp.auth.res.vo.ResModel;
import cn.brotherChun.erp.auth.res.vo.ResQueryModel;

public class ResEbo implements ResEbi{
	
	private ResDao resDao;
	
	public ResDao getResDao() {
		return resDao;
	}

	public void setResDao(ResDao resDao) {
		this.resDao = resDao;
	}

	public void save(ResModel t) {
		resDao.add(t);
	}

	public void delete(ResModel t) {
		resDao.delete(t);
	}

	public void update(ResModel t) {
		ResModel res = resDao.get(t.getUuid());
		res.setName(t.getName());
		res.setUrl(t.getUrl());
	}

	public ResModel get(Serializable uuid) {
		return resDao.get(uuid);
	}

	public List<ResModel> getAll() {
		return resDao.getAll();
	}

	public List<ResModel> getAll(ResQueryModel q, Integer pageNum,
			Integer pageCount) {
		return resDao.getAll(q, pageNum, pageCount);
	}

	public Integer getCount(ResQueryModel q) {
		return resDao.getCount(q);
	}

	public List<ResModel> getAllByEmpUuid(Long uuid) {
		return resDao.getAllByEmpUuid(uuid);
	}

}
