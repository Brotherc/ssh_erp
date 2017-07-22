package cn.brotherChun.erp.invoice.storedetail.business.ebo;

import java.io.Serializable;
import java.util.List;

import cn.brotherChun.erp.invoice.storedetail.business.ebi.StoreDetailEbi;
import cn.brotherChun.erp.invoice.storedetail.dao.dao.StoreDetailDao;
import cn.brotherChun.erp.invoice.storedetail.vo.StoreDetailModel;
import cn.brotherChun.erp.invoice.storedetail.vo.StoreDetailQueryModel;

public class StoreDetailEbo implements StoreDetailEbi{
	private StoreDetailDao storeDetailDao;

	public void setStoreDetailDao(StoreDetailDao storeDetailDao) {
		this.storeDetailDao = storeDetailDao;
	}

	public void save(StoreDetailModel t) {
		storeDetailDao.add(t);
	}

	public void delete(StoreDetailModel t) {
		storeDetailDao.delete(t);
	}

	public void update(StoreDetailModel t) {
		storeDetailDao.update(t);
	}

	public StoreDetailModel get(Serializable uuid) {
		return storeDetailDao.get(uuid);
	}

	public List<StoreDetailModel> getAll() {
		return storeDetailDao.getAll();
	}

	public List<StoreDetailModel> getAll(StoreDetailQueryModel q,
			Integer pageNum, Integer pageCount) {
		return storeDetailDao.getAll(q, pageNum, pageCount);
	}

	public Integer getCount(StoreDetailQueryModel q) {
		return storeDetailDao.getCount(q);
	}

}
