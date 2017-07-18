package cn.brotherChun.erp.invoice.goodstype.dao.dao;

import java.util.List;

import cn.brotherChun.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.brotherChun.erp.util.base.BaseDao;

public interface GoodsTypeDao extends BaseDao<GoodsTypeModel>{

	public List<GoodsTypeModel> getAllBySupplierUuid(Long uuid);

}
