package cn.brotherChun.erp.invoice.store.business.ebo;

import java.io.Serializable;
import java.util.List;

import cn.brotherChun.erp.invoice.store.business.ebi.StoreEbi;
import cn.brotherChun.erp.invoice.store.dao.dao.StoreDao;
import cn.brotherChun.erp.invoice.store.vo.StoreModel;
import cn.brotherChun.erp.invoice.store.vo.StoreQueryModel;

public class StoreEbo implements StoreEbi{
	private StoreDao storeDao;

	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
	}

	public void save(StoreModel t) {
		storeDao.add(t);
	}

	public void delete(StoreModel t) {
		storeDao.delete(t);
	}

	public void update(StoreModel t) {
		storeDao.update(t);
	}

	public StoreModel get(Serializable uuid) {
		return storeDao.get(uuid);
	}

	public List<StoreModel> getAll() {
		return storeDao.getAll();
	}

	public List<StoreModel> getAll(StoreQueryModel q, Integer pageNum,
			Integer pageCount) {
		return storeDao.getAll(q, pageNum, pageCount);
	}

	public Integer getCount(StoreQueryModel q) {
		return storeDao.getCount(q);
	}

}
