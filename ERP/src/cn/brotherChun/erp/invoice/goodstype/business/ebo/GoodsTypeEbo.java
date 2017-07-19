package cn.brotherChun.erp.invoice.goodstype.business.ebo;

import java.io.Serializable;
import java.util.List;

import cn.brotherChun.erp.invoice.goodstype.business.ebi.GoodsTypeEbi;
import cn.brotherChun.erp.invoice.goodstype.dao.dao.GoodsTypeDao;
import cn.brotherChun.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.brotherChun.erp.invoice.goodstype.vo.GoodsTypeQueryModel;

public class GoodsTypeEbo implements GoodsTypeEbi{

	private GoodsTypeDao goodsTypeDao;
	
	public void setGoodsTypeDao(GoodsTypeDao goodsTypeDao) {
		this.goodsTypeDao = goodsTypeDao;
	}

	public void save(GoodsTypeModel t) {
		goodsTypeDao.add(t);
	}

	public void delete(GoodsTypeModel t) {
		goodsTypeDao.delete(t);
	}

	public void update(GoodsTypeModel t) {
		goodsTypeDao.update(t);
	}

	public GoodsTypeModel get(Serializable uuid) {
		return goodsTypeDao.get(uuid);
	}

	public List<GoodsTypeModel> getAll() {
		return goodsTypeDao.getAll();
	}

	public List<GoodsTypeModel> getAll(GoodsTypeQueryModel q, Integer pageNum,
			Integer pageCount) {
		return goodsTypeDao.getAll(q, pageNum, pageCount);
	}

	public Integer getCount(GoodsTypeQueryModel q) {
		return goodsTypeDao.getCount(q);
	}

	public List<GoodsTypeModel> getAllBySupplier(Long uuid) {
		return goodsTypeDao.getAllBySupplierUuid(uuid);
	}

	public List<GoodsTypeModel> getAllUnionTwoBySupplier(Long uuid) {
		return goodsTypeDao.getAllUnionTwoBySupplier(uuid);
	}

}
