package egovframework.rndp.mes.inspection.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.service.FileVO;
import egovframework.rndp.mes.asset.service.MesAssetVO;
import egovframework.rndp.mes.issue.service.MesIssueVO;
import egovframework.rndp.mes.position.service.MesPositionVO;


public interface MesInspectionService {

	public void eInspectionInfoInsert(MesInspectionVO mesInspectionVO) throws Exception;

	public List mesInspectiontList(MesInspectionVO mesInspectionVO) throws Exception;

	public int mesInspectiontListCnt(MesInspectionVO mesInspectionVO) throws Exception;

	public MesInspectionVO eInspectionInfo(MesInspectionVO mesInspectionVO) throws Exception;

	public List eFileInfoList(MesInspectionVO mesInspectionVO) throws Exception;

	public void eInspectionInfoResultInsert(MesInspectionVO mesInspectionVO) throws Exception;

	public List eResultInfoList(MesInspectionVO mesInspectionVO) throws Exception;

	public void eInspectionInfoDelete(MesInspectionVO mesInspectionVO) throws Exception;

	public void eInspectionInfoUpdate(MesInspectionVO mesInspectionVO) throws Exception;

	public void mesSignInspection(MesInspectionVO mesInspectionVO) throws Exception;

	public void mesUpdateSignStatus(MesInspectionVO mesInspectionVO) throws Exception;

	public List mesInspectiontExcelList(MesInspectionVO mesInspectionVO) throws Exception;
	
	public int selectFieldCount(MesInspectionVO mesInspectionVO) throws Exception;
	
	public List selectFieldList(MesInspectionVO mesInspectionVO) throws Exception;
	
	public void mesInspectionFieldInsert(MesInspectionVO mesInspectionVO) throws Exception;
	
	public MesInspectionVO selectFieldInfo(MesInspectionVO mesInspectionVO) throws Exception;
	
	public void mesInspectionFieldUpdate(MesInspectionVO mesInspectionVO) throws Exception;
}
