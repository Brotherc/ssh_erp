package cn.brotherChun.erp.invoice.operdetail.business.ebo;

import java.io.Serializable;
import java.util.List;

import cn.brotherChun.erp.invoice.goods.dao.dao.GoodsDao;
import cn.brotherChun.erp.invoice.goods.vo.GoodsModel;
import cn.brotherChun.erp.invoice.operdetail.business.ebi.OperDetailEbi;
import cn.brotherChun.erp.invoice.operdetail.dao.dao.OperDetailDao;
import cn.brotherChun.erp.invoice.operdetail.vo.OperDetailModel;
import cn.brotherChun.erp.invoice.operdetail.vo.OperDetailQueryModel;

public class OperDetailEbo implements OperDetailEbi{
	private OperDetailDao operDetailDao;
	
	public void setOperDetailDao(OperDetailDao operDetailDao) {
		this.operDetailDao = operDetailDao;
	}

	public void save(OperDetailModel t) {
		operDetailDao.add(t);
	}

	public void delete(OperDetailModel t) {
		operDetailDao.delete(t);
	}

	public void update(OperDetailModel t) {
		operDetailDao.update(t);
	}

	public OperDetailModel get(Serializable uuid) {
		return operDetailDao.get(uuid);
	}

	public List<OperDetailModel> getAll() {
		return operDetailDao.getAll();
	}

	public List<OperDetailModel> getAll(OperDetailQueryModel q,
			Integer pageNum, Integer pageCount) {
		return operDetailDao.getAll(q, pageNum, pageCount);
	}

	public Integer getCount(OperDetailQueryModel q) {
		return operDetailDao.getCount(q);
	}

}
