package cn.brotherChun.erp.invoice.goods.business.ebi;

import java.util.List;

import cn.brotherChun.erp.invoice.goods.vo.GoodsModel;
import cn.brotherChun.erp.invoice.goods.vo.GoodsQueryModel;
import cn.brotherChun.erp.util.base.BaseEbi;

public interface GoodsEbi extends BaseEbi<GoodsModel, GoodsQueryModel>{

	public List<GoodsModel> getAllByGoodsType(Long uuid);

}
