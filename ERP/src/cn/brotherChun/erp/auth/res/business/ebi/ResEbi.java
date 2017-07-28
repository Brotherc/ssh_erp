package cn.brotherChun.erp.auth.res.business.ebi;

import java.util.List;

import cn.brotherChun.erp.auth.res.vo.ResModel;
import cn.brotherChun.erp.auth.res.vo.ResQueryModel;
import cn.brotherChun.erp.util.base.BaseEbi;

public interface ResEbi extends BaseEbi<ResModel, ResQueryModel>{

	/**
	 * 获取指定员工所有可操作资源信息
	 * @param uuid 员工uuid
	 * @return
	 */
	public List<ResModel> getAllByEmpUuid(Long uuid);

}
