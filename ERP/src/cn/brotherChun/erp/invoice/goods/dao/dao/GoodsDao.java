package cn.brotherChun.erp.invoice.goods.dao.dao;

import java.util.List;

import cn.brotherChun.erp.invoice.goods.vo.GoodsModel;
import cn.brotherChun.erp.util.base.BaseDao;

public interface GoodsDao extends BaseDao<GoodsModel>{

	public List<GoodsModel> getAllByGoodsTypeUuid(Long uuid);

}
