package cn.brotherChun.erp.invoice.supplier.business.ebo;

import java.io.Serializable;
import java.util.List;

import cn.brotherChun.erp.invoice.supplier.business.ebi.SupplierEbi;
import cn.brotherChun.erp.invoice.supplier.dao.dao.SupplierDao;
import cn.brotherChun.erp.invoice.supplier.vo.SupplierModel;
import cn.brotherChun.erp.invoice.supplier.vo.SupplierQueryModel;

public class SupplierEbo implements SupplierEbi{

	private SupplierDao supplierDao;
	
	public void setSupplierDao(SupplierDao supplierDao) {
		this.supplierDao = supplierDao;
	}

	public void save(SupplierModel t) {
		supplierDao.add(t);
	}

	public void delete(SupplierModel t) {
		supplierDao.delete(t);
	}

	public void update(SupplierModel t) {
		supplierDao.update(t);
	}

	public SupplierModel get(Serializable uuid) {
		return supplierDao.get(uuid);
	}

	public List<SupplierModel> getAll() {
		return supplierDao.getAll();
	}

	public List<SupplierModel> getAll(SupplierQueryModel q, Integer pageNum,
			Integer pageCount) {
		return supplierDao.getAll(q, pageNum, pageCount);
	}

	public Integer getCount(SupplierQueryModel q) {
		return supplierDao.getCount(q);
	}

	public List<SupplierModel> getAllUnion() {
		return supplierDao.getAllUnion();
	}

}
