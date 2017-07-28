package cn.brotherChun.erp.invoice.goodstype.business.ebi;

import java.util.List;

import cn.brotherChun.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.brotherChun.erp.invoice.goodstype.vo.GoodsTypeQueryModel;
import cn.brotherChun.erp.util.base.BaseEbi;

public interface GoodsTypeEbi extends BaseEbi<GoodsTypeModel, GoodsTypeQueryModel>{

	/**
	 * 获取指定供应商商品类别信息
	 * @param uuid 供应商uuid
	 * @return
	 */
	public List<GoodsTypeModel> getAllBySupplier(Long uuid);

	/**
	 * 获取指定供应商对应的具有商品信息的类别信息
	 * @param uuid 供应商uuid
	 * @return
	 */
	public List<GoodsTypeModel> getAllUnionTwoBySupplier(Long uuid);

}
