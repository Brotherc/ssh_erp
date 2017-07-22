package cn.brotherChun.erp.invoice.goods.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.brotherChun.erp.invoice.goods.vo.GoodsModel;
import cn.brotherChun.erp.invoice.goods.vo.GoodsQueryModel;
import cn.brotherChun.erp.util.base.BaseEbi;

@Transactional
public interface GoodsEbi extends BaseEbi<GoodsModel, GoodsQueryModel>{

	public List<GoodsModel> getAllByGoodsType(Long uuid);

	public void goodsUseNumUpdate();

	public List<Object[]> getWarmInfo();

}
