package cn.brotherChun.erp.invoice.supplier.business.ebi;

import java.util.List;

import cn.brotherChun.erp.invoice.supplier.vo.SupplierModel;
import cn.brotherChun.erp.invoice.supplier.vo.SupplierQueryModel;
import cn.brotherChun.erp.util.base.BaseEbi;

public interface SupplierEbi extends BaseEbi<SupplierModel, SupplierQueryModel>{

	/**
	 * 获取具有商品类别信息的供应商信息
	 * @return
	 */
	public List<SupplierModel> getAllUnion();

	public List<SupplierModel> getAllUnionTwo();

}
