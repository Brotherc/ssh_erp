package cn.brotherChun.erp.invoice.supplier.business.ebi;

import java.util.List;

import cn.brotherChun.erp.invoice.supplier.vo.SupplierModel;
import cn.brotherChun.erp.invoice.supplier.vo.SupplierQueryModel;
import cn.brotherChun.erp.util.base.BaseEbi;

public interface SupplierEbi extends BaseEbi<SupplierModel, SupplierQueryModel>{

	public List<SupplierModel> getAllUnion();

}
