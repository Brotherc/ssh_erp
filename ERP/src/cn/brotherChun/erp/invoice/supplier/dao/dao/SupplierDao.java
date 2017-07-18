package cn.brotherChun.erp.invoice.supplier.dao.dao;

import java.util.List;

import cn.brotherChun.erp.invoice.supplier.vo.SupplierModel;
import cn.brotherChun.erp.util.base.BaseDao;

public interface SupplierDao extends BaseDao<SupplierModel>{

	public List<SupplierModel> getAllUnion();

}
