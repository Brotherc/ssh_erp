package cn.brotherChun.erp.invoice.goods.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.brotherChun.erp.invoice.goods.vo.GoodsModel;
import cn.brotherChun.erp.invoice.goods.vo.GoodsQueryModel;
import cn.brotherChun.erp.util.base.BaseEbi;

@Transactional
public interface GoodsEbi extends BaseEbi<GoodsModel, GoodsQueryModel>{

	/**
	 * 获取指定商品类别对应的所有商品信息
	 * @param uuid 类别uuid
	 * @return
	 */
	public List<GoodsModel> getAllByGoodsType(Long uuid);

	/**
	 * 更新商品使用频度
	 */
	public void goodsUseNumUpdate();

	/**
	 * 获取超出上限或低于下限的商品信息
	 * @return
	 */
	public List<Object[]> getWarmInfo();

}
