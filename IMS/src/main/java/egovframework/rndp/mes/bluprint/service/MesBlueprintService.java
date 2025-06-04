package egovframework.rndp.mes.bluprint.service;

import egovframework.com.cmm.service.FileVO;
import egovframework.rndp.mes.inspection.service.MesInspectionVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MesBlueprintService {
	
	public List eFileInfoList(MesBlueprintVO mesBlueprintVO) throws Exception;

	public void insertMesBlueprint(HttpServletRequest request, MesBlueprintVO mesBlueprintVO) throws Exception;
	
	public List selectMesBlueprintList(MesBlueprintVO mesBlueprintVO) throws Exception;

	public int selectMesBlueprintListCnt(MesBlueprintVO mesBlueprintVO) throws Exception;
	
	public MesBlueprintVO selectBlueprintInfo(MesBlueprintVO mesBlueprintVO) throws Exception;

	public MesBlueprintVO selectBlueprintItemInfo(MesBlueprintVO mesBlueprintVO) throws Exception;
	
	public void deleteMesBlueprint(MesBlueprintVO mesBlueprintVO) throws Exception;
	
	public void updateMesBlueprint(HttpServletRequest request,FileVO fileVO, MesBlueprintVO mesBlueprintVO) throws Exception;

	public List selectMesBlueprintFile(MesBlueprintVO mesBlueprintVO) throws Exception;

	public List selectMesBlueprintFileListU(MesBlueprintVO mesBlueprintVO) throws Exception;

	public List selectMesBlueprintFileList(MesBlueprintVO mesBlueprintVO) throws Exception;

	public List selectMesBlueprintFileEtc(MesBlueprintVO mesBlueprintVO) throws Exception;

	public List selectBlueprintItemList(MesBlueprintVO mesBlueprintVO) throws Exception;

	public void insertMesBlueprintProcess(MesBlueprintVO mesBlueprintVO, HttpServletRequest request) throws Exception;

	public void requestMesBlueprintProcess(MesBlueprintVO mesBlueprintVO) throws Exception;

	public void setBlueprintProcess(MesBlueprintVO mesBlueprintVO) throws Exception;

	public void deleteMesBlueprintitem(MesBlueprintVO mesBlueprintVO) throws Exception;

	public List selectSignList(String getsSignKey) throws Exception;

	public void eChangeManagementInfoInssert(MesBlueprintVO mesBlueprintVO) throws Exception;

	public List selectChangeList(MesBlueprintVO mesBlueprintVO) throws Exception;

	public int selectChangeListCnt(MesBlueprintVO mesBlueprintVO) throws Exception;

	public MesBlueprintVO selectChangeInfo(MesBlueprintVO mesBlueprintVO) throws Exception;

	public List eSelectAssetInfoList(MesBlueprintVO mesBlueprintVO) throws Exception;

	public void eIssueInfoInssert(MesBlueprintVO mesBlueprintVO) throws Exception;

	public List selectIssueList(MesBlueprintVO mesBlueprintVO) throws Exception;

	public int selectIssueListCnt(MesBlueprintVO mesBlueprintVO) throws Exception;

	public MesBlueprintVO selectIssueInfo(MesBlueprintVO mesBlueprintVO) throws Exception;

	public List eSelectIssueAssetInfoList(MesBlueprintVO mesBlueprintVO) throws Exception;

	public void updateChangeInfo(MesBlueprintVO mesBlueprintVO) throws Exception;

	public void selectIssueDelete(MesBlueprintVO mesBlueprintVO) throws Exception;

	public void updateIssueInfo(MesBlueprintVO mesBlueprintVO) throws Exception;

	public List selectSRList(MesBlueprintVO mesBlueprintVO) throws Exception;

	public int selectSRListCnt(MesBlueprintVO mesBlueprintVO) throws Exception;

	public void eSRInfoInssert(MesBlueprintVO mesBlueprintVO) throws Exception;

	public MesBlueprintVO selectSRInfo(MesBlueprintVO mesBlueprintVO) throws Exception;

	public List eSelectSRAssetInfoList(MesBlueprintVO mesBlueprintVO) throws Exception;

	public void selectSRDelete(MesBlueprintVO mesBlueprintVO) throws Exception;

	public void updateSRInfo(MesBlueprintVO mesBlueprintVO) throws Exception;

	public List eSelectDeteliInfoList(MesBlueprintVO mesBlueprintVO) throws Exception;

	public List eSelectFileInfoList(MesBlueprintVO mesBlueprintVO) throws Exception;

	public List eSelectIssueDeteliInfoList(MesBlueprintVO mesBlueprintVO) throws Exception;

	public List eSelectIssueFileInfoList(MesBlueprintVO mesBlueprintVO) throws Exception;

	public List eSelectIssueNotesInfoList(MesBlueprintVO mesBlueprintVO) throws Exception;

	public void mesSignBlueprint(MesBlueprintVO mesBlueprintVO) throws Exception;

	public void mesUpdateSignStatus(MesBlueprintVO mesBlueprintVO) throws Exception;

	public void mesSignBlueIssue(MesBlueprintVO mesBlueprintVO) throws Exception;

	public void mesSignBlueSr(MesBlueprintVO mesBlueprintVO) throws Exception;

	public List selectChangeExcelList(MesBlueprintVO mesBlueprintVO) throws Exception;

	public List selectIssueExcelList(MesBlueprintVO mesBlueprintVO) throws Exception;

	public List selectSRExcelList(MesBlueprintVO mesBlueprintVO) throws Exception;
}
