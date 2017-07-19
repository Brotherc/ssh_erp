package cn.brotherChun.erp.invoice.goods.business.ebo;

import java.io.Serializable;
import java.util.List;

import cn.brotherChun.erp.invoice.goods.business.ebi.GoodsEbi;
import cn.brotherChun.erp.invoice.goods.dao.dao.GoodsDao;
import cn.brotherChun.erp.invoice.goods.vo.GoodsModel;
import cn.brotherChun.erp.invoice.goods.vo.GoodsQueryModel;

public class GoodsEbo implements GoodsEbi{
	
	private GoodsDao goodsDao;

	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	public void save(GoodsModel t) {
		goodsDao.add(t);
	}

	public void delete(GoodsModel t) {
		goodsDao.delete(t);
	}

	public void update(GoodsModel t) {
		goodsDao.update(t);
	}

	public GoodsModel get(Serializable uuid) {
		return goodsDao.get(uuid);
	}

	public List<GoodsModel> getAll() {
		return goodsDao.getAll();
	}

	public List<GoodsModel> getAll(GoodsQueryModel q, Integer pageNum,
			Integer pageCount) {
		return goodsDao.getAll(q, pageNum, pageCount);
	}

	public Integer getCount(GoodsQueryModel q) {
		return goodsDao.getCount(q);
	}

	public List<GoodsModel> getAllByGoodsType(Long uuid) {
		return goodsDao.getAllByGoodsTypeUuid(uuid);
	}

}
