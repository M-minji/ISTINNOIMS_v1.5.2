package egovframework.rndp.mes.position.service;

import java.util.List;

public interface MesPositionService {
	// 부서관리
	public List selectPositionList(MesPositionVO mesPositionVO) throws Exception;

	public String getMaxPositionKey(MesPositionVO mesPositionVO) throws Exception;

	public String getPositionPath(MesPositionVO mesPositionVO) throws Exception;

	public String savePositionAjax(MesPositionVO mesPositionVO) throws Exception;

	public void deletePositionAjax(MesPositionVO mesPositionVO) throws Exception;

	public List selectUpdatePositionList(MesPositionVO mesPositionVO) throws Exception;
}
