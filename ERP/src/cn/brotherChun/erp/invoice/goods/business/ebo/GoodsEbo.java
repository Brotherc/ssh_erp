package cn.brotherChun.erp.invoice.goods.business.ebo;

import java.io.Serializable;
import java.util.List;

import cn.brotherChun.erp.invoice.goods.business.ebi.GoodsEbi;
import cn.brotherChun.erp.invoice.goods.dao.dao.GoodsDao;
import cn.brotherChun.erp.invoice.goods.vo.GoodsModel;
import cn.brotherChun.erp.invoice.goods.vo.GoodsQueryModel;
import cn.brotherChun.erp.invoice.goodstype.vo.GoodsTypeModel;

public class GoodsEbo implements GoodsEbi{
	
	private GoodsDao goodsDao;

	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	public void save(GoodsModel t) {
		t.setUseNum(0);
		t.setMinNum(15);
		t.setMaxNum(100);
		goodsDao.add(t);
	}

	public void delete(GoodsModel t) {
		goodsDao.delete(t);
	}

	public void update(GoodsModel t) {
		GoodsModel temp = goodsDao.get(t.getUuid());
		if(t.getGtm()!=null&&t.getGtm().getSupplier()!=null&&t.getGtm().getSupplier().getUuid()!=null){
			GoodsTypeModel gtm = temp.getGtm();
			gtm.setSupplier(t.getGtm().getSupplier());
			temp.setGtm(gtm);
		}
		if(t.getGtm()!=null&&t.getGtm().getUuid()!=null)
			temp.setGtm(t.getGtm());
		if(t.getInPrice()!=null)
			temp.setInPrice(t.getInPrice());
		if(t.getOutPrice()!=null)
			temp.setOutPrice(t.getOutPrice());
		if(t.getName()!=null)
			temp.setName(t.getName());
		if(t.getOrigin()!=null)
			temp.setOrigin(t.getOrigin());

		if(t.getProducer()!=null)
			temp.setProducer(t.getProducer());
		if(t.getUnit()!=null)
			temp.setUnit(t.getUnit());
		if(t.getUseNum()!=null)
			temp.setUseNum(t.getUseNum());
		if(t.getMaxNum()!=null)
			temp.setMaxNum(t.getMaxNum());
		if(t.getMinNum()!=null)
			temp.setMinNum(t.getMinNum());
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

	public void goodsUseNumUpdate() {
		goodsDao.goodsUseNumUpdate();
	}

	public List<Object[]> getWarmInfo() {
		return goodsDao.getWarmInfo();
	}

}
