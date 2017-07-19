package cn.brotherChun.erp.invoice.goodstype.business.ebi;

import java.util.List;

import cn.brotherChun.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.brotherChun.erp.invoice.goodstype.vo.GoodsTypeQueryModel;
import cn.brotherChun.erp.util.base.BaseEbi;

public interface GoodsTypeEbi extends BaseEbi<GoodsTypeModel, GoodsTypeQueryModel>{

	public List<GoodsTypeModel> getAllBySupplier(Long uuid);

	public List<GoodsTypeModel> getAllUnionTwoBySupplier(Long uuid);

}
