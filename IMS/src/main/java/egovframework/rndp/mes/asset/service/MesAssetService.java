package egovframework.rndp.mes.asset.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.service.FileVO;
import egovframework.rndp.mes.maintance.service.MesMaintanceVO;
import egovframework.rndp.mes.position.service.MesPositionVO;


public interface MesAssetService {
	
	public List eSelectFileInfoList(MesAssetVO mesAssetVO) throws Exception;
	
	public List selectMesAssetList(MesAssetVO mesAssetVO) throws Exception;
	
	public int selectMesAssetListCnt(MesAssetVO mesAssetVO) throws Exception;
	
	public MesAssetVO selectMesAssetInfo(MesAssetVO mesAssetVO) throws Exception;
	
	public void insertMesAsset(MesAssetVO mesAssetVO, HttpServletRequest request) throws Exception;
	
	public void updateMesAsset(MesAssetVO mesAssetVO, HttpServletRequest request, FileVO fileVO) throws Exception;
	
	public void deleteMesAsset(MesAssetVO mesAssetVO) throws Exception;
	
	public List selectComtnFileDetail(MesAssetVO mesAssetVO) throws Exception;
	
	public void insertMesAssetUse(MesAssetVO mesAssetVO) throws Exception;
	
	public MesAssetVO selectMesAssetUseInfo(MesAssetVO mesAssetVO) throws Exception;
	
	//장비이력현황
	public List selectMesAssetUseList(MesAssetVO mesAssetVO) throws Exception;
	
	public int selectMesAssetUseListCnt(MesAssetVO mesAssetVO) throws Exception;
	
	//장비아이템별 현황
	public List selectMesAssetUseItemList(MesAssetVO mesAssetVO) throws Exception;
	
	public void deleteMesAssetUse(MesAssetVO mesAssetVO) throws Exception;
	
	public void updateMesAssetUse(MesAssetVO mesAssetVO) throws Exception;

	public void requestMesAssetUse(MesAssetVO mesAssetVO) throws Exception;

	public void setSignAsset(MesAssetVO mesAssetVO) throws Exception;

	public void insertMesAssetAjax(MesAssetVO mesAssetVO) throws Exception;
	
	
	/**
	 * 파일 삭제시 널처리
	 * @param mesAssetVO
	 * @throws Exception
	 */
	public void updateNullFileInfo(MesAssetVO mesAssetVO) throws Exception;
	
	
	
	/**
	 * 대상장비 조회 팝업
	 * @param mesAssetVO
	 * @return
	 * @throws Exception
	 */
	public List selectMesAssetInfoList(MesAssetVO mesAssetVO) throws Exception;
	
	public int selectMesAssetInfoListCnt(MesAssetVO mesAssetVO) throws Exception;

	public void insertAssetSoftwareInfo(MesAssetVO mesAssetVO) throws Exception;

	public List mesSoftwareList(MesAssetVO mesAssetVO) throws Exception;

	public int mesSoftwareListCnt(MesAssetVO mesAssetVO) throws Exception;

	public List mesSoftwareExcelList(MesAssetVO mesAssetVO)throws Exception;

	public MesAssetVO mesSoftwareInfo(MesAssetVO mesAssetVO) throws Exception;

	public void mesSoftwareAssetDelete(MesAssetVO mesAssetVO) throws Exception;

	public void mesSoftwareAssetUdate(MesAssetVO mesAssetVO) throws Exception;

	public void insertInfoAsset(MesAssetVO mesAssetVO) throws Exception;

	public List selectAssetInfoList(MesAssetVO mesAssetVO) throws Exception;

	public void insertInfoCondition(MesAssetVO mesAssetVO) throws Exception;

	public List selectConditionList(MesAssetVO mesAssetVO) throws Exception;

	public int selectConditionCnt(MesAssetVO mesAssetVO) throws Exception;

	public void updateImportInfo(MesAssetVO mesAssetVO) throws Exception;

	public void insertInfoReplacement(MesAssetVO mesAssetVO) throws Exception;

	public List selectReplacementList(MesAssetVO mesAssetVO) throws Exception;

	public int selectReplacementCnt(MesAssetVO mesAssetVO) throws Exception;

	public MesAssetVO selectReplacementInfo(MesAssetVO mesAssetVO) throws Exception;

	public List selectReplacementItemList(MesAssetVO mesAssetVO) throws Exception;

	public List selectConditionPopList(MesAssetVO mesAssetVO) throws Exception;

	public int selectConditionPopCnt(MesAssetVO mesAssetVO) throws Exception;

	public List selectReplacementPopList(MesAssetVO mesAssetVO) throws Exception;

	public int selectReplacementPopCnt(MesAssetVO mesAssetVO) throws Exception;

	public List selectMaintancePopList(MesAssetVO mesAssetVO) throws Exception;

	public int selectMaintancePopListCnt(MesAssetVO mesAssetVO) throws Exception;

	public void mesSoftwareDateUpdate(MesAssetVO mesAssetVO) throws Exception;

	public List mesSoftwareUpdateList(MesAssetVO mesAssetVO) throws Exception;

	public List mesAssetSoftwareList(MesAssetVO mesAssetVO) throws Exception;

	public void mesSoftwareStatusUpdate(MesAssetVO mesAssetVO) throws Exception;

	public List selectLicenseInfoList(MesAssetVO mesAssetVO) throws Exception;

	public MesAssetVO eModelInfoCheck(MesAssetVO mesAssetVO) throws Exception;

	public int eAssetSNumberInfoCheck(MesAssetVO mesAssetVO) throws Exception;

	public List selectMesAssetLicenseList(MesAssetVO mesAssetVO) throws Exception;

	public void mesSignAsset(MesAssetVO mesAssetVO) throws Exception;

	public List selectMesAssetAndLicenseInfoList(MesAssetVO mesAssetVO) throws Exception;

	public int selectMesAssetAndLicenseInfoListCnt(MesAssetVO mesAssetVO) throws Exception;

	public void mesUpdateSignStatus(MesAssetVO mesAssetVO) throws Exception;

	public List selectConditionSingList(MesAssetVO mesAssetVO) throws Exception;

	public List selectConditionInfo(MesAssetVO mesAssetVO) throws Exception;

	public void eDeleteInfoCondition(MesAssetVO mesAssetVO) throws Exception;

	public void eUpdateInfoCondition(MesAssetVO mesAssetVO) throws Exception;

	public void insertInfoReceiving(MesAssetVO mesAssetVO) throws Exception;

	public List selectReceivingList(MesAssetVO mesAssetVO) throws Exception;

	public void eDeleteInfoReceiving(MesAssetVO mesAssetVO) throws Exception;

	public List selectReceivingInfoList(MesAssetVO mesAssetVO) throws Exception;

	public int selectReceivingInfoListCnt(MesAssetVO mesAssetVO) throws Exception;

	public void updateInfoReceiving(MesAssetVO mesAssetVO) throws Exception;

	public List eAssetCountList(MesAssetVO mesAssetVO) throws Exception;

	public int eAssetNumberCheck(MesAssetVO mesAssetVO) throws Exception;

	public void deleteInfoReplacement(MesAssetVO mesAssetVO) throws Exception;

	public void updateInfoReplacement(MesAssetVO mesAssetVO) throws Exception;

	public List eMainAssetMakerInfoList(MesAssetVO mesAssetVO) throws Exception;

	public List eMainAssetTypeInfoList(MesAssetVO mesAssetVO) throws Exception;

	public void updateProcessAssetInfo(MesAssetVO mesAssetVO) throws Exception;

	public List mainAssetEosList(MesAssetVO mesAssetVO) throws Exception;

	public List mainAssetEolList(MesAssetVO mesAssetVO) throws Exception;

	public List mainSoftwareList(MesAssetVO mesAssetVO) throws Exception;

	public List mainAssetLifeStatusList(MesAssetVO mesAssetVO) throws Exception;

	public void mesSignSoftwareAsset(MesAssetVO mesAssetVO) throws Exception;

	public void mesUpdateAssetSWSignStatus(MesAssetVO mesAssetVO) throws Exception;

	public void mesUpdateAssetUploadSignImgAjax(MesAssetVO mesAssetVO) throws Exception;

	public void mesSignCondition(MesAssetVO mesAssetVO) throws Exception;

	public MesAssetVO selectConditionStatusInfo(MesAssetVO mesAssetVO) throws Exception;

	public void mesSignConditionOut(MesAssetVO mesAssetVO) throws Exception;

	public void mesConditionSignStatus(MesAssetVO mesAssetVO) throws Exception;

	public void mesSignReplacement(MesAssetVO mesAssetVO) throws Exception;

	public void mesReplacedUpdateSignStatus(MesAssetVO mesAssetVO) throws Exception;

	public List mainAssetLifeStatusNewList(MesAssetVO mesAssetVO) throws Exception;

	public List selectMesAssetExcelList(MesAssetVO mesAssetVO) throws Exception;

	public List selectConditionExcelList(MesAssetVO mesAssetVO) throws Exception;

	public List selectReplacementExcelList(MesAssetVO mesAssetVO) throws Exception;

	public List accessInfoList(MesAssetVO mesAssetVO) throws Exception;

	public List selectConditionDate(MesAssetVO mesAssetVO) throws Exception;
	
	
	
}
