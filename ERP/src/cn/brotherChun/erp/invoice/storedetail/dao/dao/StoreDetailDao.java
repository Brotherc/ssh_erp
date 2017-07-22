package cn.brotherChun.erp.invoice.storedetail.dao.dao;

import cn.brotherChun.erp.invoice.storedetail.vo.StoreDetailModel;
import cn.brotherChun.erp.util.base.BaseDao;

public interface StoreDetailDao extends BaseDao<StoreDetailModel>{

	StoreDetailModel getByGoodsAndStore(Long uuid, Long storeUuid);

}
