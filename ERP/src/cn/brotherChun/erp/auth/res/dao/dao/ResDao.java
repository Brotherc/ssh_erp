package cn.brotherChun.erp.auth.res.dao.dao;

import java.util.List;

import cn.brotherChun.erp.auth.res.vo.ResModel;
import cn.brotherChun.erp.util.base.BaseDao;

public interface ResDao extends BaseDao<ResModel>{

	public List<ResModel> getAllByEmpUuid(Long uuid);

}
