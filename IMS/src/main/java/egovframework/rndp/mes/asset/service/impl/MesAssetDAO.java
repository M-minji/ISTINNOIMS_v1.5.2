package egovframework.rndp.mes.asset.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rndp.mes.asset.service.MesAssetVO;
import egovframework.rndp.mes.bluprint.service.MesBlueprintVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("mesAssetDAO")
public class MesAssetDAO extends EgovAbstractDAO{
	
	public List selectMesAssetList(MesAssetVO mesAssetVO) throws Exception{
		return list("mesAssetDAO.selectMesAssetList", mesAssetVO);
	}
	
	public int selectMesAssetListCnt(MesAssetVO mesAssetVO) throws Exception{
		return (int) select("mesAssetDAO.selectMesAssetListCnt", mesAssetVO);
	}
	
	public MesAssetVO selectMesAssetInfo(MesAssetVO mesAssetVO) throws Exception{
		return (MesAssetVO) select("mesAssetDAO.selectMesAssetInfo", mesAssetVO);
	}
	
	public void insertMesAsset(MesAssetVO mesAssetVO) throws Exception{
		insert("mesAssetDAO.insertMesAsset", mesAssetVO);
	}
	
	public void updateMesAsset(MesAssetVO mesAssetVO) throws Exception{
		update("mesAssetDAO.updateMesAsset", mesAssetVO);
	}
	
	public void deleteMesAsset(MesAssetVO mesAssetVO) throws Exception{
//		delete("mesAssetDAO.deleteMesAsset", mesAssetVO);
		update("mesAssetDAO.deleteAssetUpdate", mesAssetVO);
	}
	
	public List selectComtnFileDetail(MesAssetVO mesAssetVO) throws Exception{
		return list("mesProgramDAO.selectComtnFileDetail");
	}
	
	
	
	
	/**
	 * 대상장비 등록
	 * @param mesAssetVO
	 * @throws Exception
	 */
	public void insertMesAssetUseHeader(MesAssetVO mesAssetVO) throws Exception{
		insert("mesAssetDAO.insertMesAssetUseHeader", mesAssetVO);
	}
	
	public void insertMesAssetUse(MesAssetVO mesAssetVO) throws Exception{
		insert("mesAssetDAO.insertMesAssetUse", mesAssetVO);
	}
	
	
	public List selectMesAssetUseList(MesAssetVO mesAssetVO) throws Exception{
		return list("mesAssetDAO.selectMesAssetUseList", mesAssetVO);
	}
	
	public int selectMesAssetUseListCnt(MesAssetVO mesAssetVO) throws Exception{
		return (int) select("mesAssetDAO.selectMesAssetUseListCnt", mesAssetVO);
	}
	
	
	public MesAssetVO selectMesAssetUseInfo(MesAssetVO mesAssetVO) throws Exception{
		return (MesAssetVO) select("mesAssetDAO.selectMesAssetUseInfo", mesAssetVO);
	}

	
	
	public List selectMesAssetUseItemList(MesAssetVO mesAssetVO) throws Exception{
		return list("mesAssetDAO.selectMesAssetUseItemList", mesAssetVO);
	}
	
	
	public void deleteMesAssetUseHeader(MesAssetVO mesAssetVO) throws Exception{
		delete("mesAssetDAO.deleteMesAssetUseHeader", mesAssetVO);
	}
	
	public void deleteMesAssetUse(MesAssetVO mesAssetVO) throws Exception{
		delete("mesAssetDAO.deleteMesAssetUse", mesAssetVO);
	}
	
	public void updateMesAssetUseHeader(MesAssetVO mesAssetVO) throws Exception{
		update("mesAssetDAO.updateMesAssetUseHeader", mesAssetVO);
	}

	public String selectMaxAssetUseKey(MesAssetVO mesAssetVO) {
		// TODO Auto-generated method stub
		return (String) select("mesAssetDAO.selectMaxAssetUseKey", mesAssetVO);
	}

	public void updateAssetStatus(MesAssetVO mesAssetVO) {
		// TODO Auto-generated method stub
		update("mesAssetDAO.updateAssetSignStatusTwo", mesAssetVO);
	}

	public void updateMesSignDecison(MesAssetVO mesAssetVO) {
		// TODO Auto-generated method stub
		update("mesAssetDAO.updateMesSignDecison", mesAssetVO);
	}

	public void updateAssetSignStatus(MesAssetVO mesAssetVO) {
		// TODO Auto-generated method stub
		update("mesAssetDAO.updateAssetSignStatus", mesAssetVO);
	}
	
	
	public void updateNullFileInfo(MesAssetVO mesAssetVO) {
		// TODO Auto-generated method stub
		update("mesAssetDAO.updateNullFileInfo", mesAssetVO);
	}
	
	/**
	 * 대상장비 조회 팝업
	 * @param mesAssetVO
	 * @return
	 * @throws Exception
	 */
	public List selectMesAssetInfoList(MesAssetVO mesAssetVO) throws Exception{
		return list("mesAssetDAO.selectMesAssetInfoList", mesAssetVO);
	}
	
	public int selectMesAssetInfoListCnt(MesAssetVO mesAssetVO) throws Exception{
		return (int) select("mesAssetDAO.selectMesAssetInfoListCnt", mesAssetVO);
	}

	public void insertAssetSoftwareInfo(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		insert("mesAssetDAO.insertAssetSoftwareInfo", mesAssetVO);
	}

	public List mesSoftwareList(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesAssetDAO.mesSoftwareList", mesAssetVO);
	}

	public int mesSoftwareListCnt(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return (int) select("mesAssetDAO.mesSoftwareListCnt", mesAssetVO);
	}

	public List mesSoftwareExcelList(MesAssetVO mesAssetVO)  throws Exception{
		// TODO Auto-generated method stub
		return list("mesAssetDAO.mesSoftwareExcelList", mesAssetVO);
	}

	public MesAssetVO mesSoftwareInfo(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return (MesAssetVO) select("mesAssetDAO.mesSoftwareInfo", mesAssetVO);
	}

	public void mesSoftwareAssetDelete(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		delete("mesAssetDAO.mesSoftwareAssetDelete", mesAssetVO);
	}
	
	public void mesSoftwareAssetLicenceDelete(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		delete("mesAssetDAO.mesSoftwareAssetLicenceDelete", mesAssetVO);
	}

	public void mesSoftwareAssetUdate(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesAssetDAO.mesSoftwareAssetUdate", mesAssetVO);
	}

	public void insertInfoAsset(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		insert("mesAssetDAO.insertInfoAsset", mesAssetVO);
	}

	public void updateMesAssetNew(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesAssetDAO.updateMesAssetNew", mesAssetVO);
	}

	public List selectAssetInfoList(Map<String, Object>  keyList) throws Exception{
		// TODO Auto-generated method stub
		return list("mesAssetDAO.selectAssetInfoList",keyList);
	}

	public void insertInfoCondition(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		insert("mesAssetDAO.insertInfoCondition", mesAssetVO);
	}

	public void insertInfoConditionItem(MesAssetVO mesAssetVO)  throws Exception{
		// TODO Auto-generated method stub
		insert("mesAssetDAO.insertInfoConditionItem", mesAssetVO);
	}

	public List selectConditionList(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesAssetDAO.selectConditionList",mesAssetVO);
	}

	public int selectConditionCnt(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return (int) select("mesAssetDAO.selectConditionCnt", mesAssetVO);
	}

	public void updateImportInfo(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesAssetDAO.updateImportInfo", mesAssetVO);
	}

	public void insertInfoReplacement(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		insert("mesAssetDAO.insertInfoReplacement", mesAssetVO);
	}

	public void insertInfoReplacementItem(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		insert("mesAssetDAO.insertInfoReplacementItem", mesAssetVO);
	}

	public List selectReplacementList(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesAssetDAO.selectReplacementList",mesAssetVO);
	}

	public int selectReplacementCnt(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return (int) select("mesAssetDAO.selectReplacementCnt", mesAssetVO);
	}

	public MesAssetVO selectReplacementInfo(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return (MesAssetVO) select("mesAssetDAO.selectReplacementInfo", mesAssetVO);
	}

	public List selectReplacementItemList(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesAssetDAO.selectReplacementItemList",mesAssetVO);
	}

	public List selectConditionPopList(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesAssetDAO.selectConditionPopList",mesAssetVO);
	}

	public int selectConditionPopCnt(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return (int) select("mesAssetDAO.selectConditionPopCnt", mesAssetVO);
	}

	public List selectReplacementPopList(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesAssetDAO.selectReplacementPopList",mesAssetVO);
	}

	public int selectReplacementPopCnt(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return (int) select("mesAssetDAO.selectReplacementPopCnt", mesAssetVO);
	}

	public List selectMaintancePopList(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesAssetDAO.selectMaintancePopList",mesAssetVO);
	}

	public int selectMaintancePopListCnt(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return (int) select("mesAssetDAO.selectMaintancePopListCnt", mesAssetVO);
	}

	public void mesSoftwareDateInsert(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		insert("mesAssetDAO.mesSoftwareDateInsert", mesAssetVO);
	}

	public void mesSoftwareDateUpdate(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesAssetDAO.mesSoftwareDateUpdate", mesAssetVO);
	}

	public List mesSoftwareUpdateList(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesAssetDAO.mesSoftwareUpdateList",mesAssetVO);
	}

	public List mesAssetSoftwareList(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	public void mesSoftwareStatusUpdate(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesAssetDAO.mesSoftwareStatusUpdate", mesAssetVO);
	}

	public List selectLicenseInfoList(Map keyList) throws Exception{
		// TODO Auto-generated method stub
		return list("mesAssetDAO.selectLicenseInfoList",keyList);
	}

	public MesAssetVO eModelInfoCheck(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return (MesAssetVO) select("mesAssetDAO.eModelInfoCheck",mesAssetVO);
	}

	public int eAssetSNumberInfoCheck(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return (int) select("mesAssetDAO.eAssetSNumberInfoCheck", mesAssetVO);
	}

	public void insertInfoAssetLicenseInfo(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesAssetDAO.insertInfoAssetLicenseInfo", mesAssetVO);
	}

	public List selectMesAssetLicenseList(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesAssetDAO.selectMesAssetLicenseList",mesAssetVO);
	}

	public void eDeleteAssetLicenseInfo(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		delete("mesAssetDAO.eDeleteAssetLicenseInfo", mesAssetVO);
	}

	public List selectMesAssetAndLicenseInfoList(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesAssetDAO.selectMesAssetAndLicenseInfoList",mesAssetVO);
	}

	public int selectMesAssetAndLicenseInfoListCnt(MesAssetVO mesAssetVO) {
		// TODO Auto-generated method stub
		return (int) select("mesAssetDAO.selectMesAssetAndLicenseInfoListCnt", mesAssetVO);
	}

	public void updateAssetOutIn(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesAssetDAO.updateAssetOutIn", mesAssetVO);
	}

	public List selectConditionInfo(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesAssetDAO.selectConditionInfo",mesAssetVO);
	}

	public void eDeleteInfoCondition(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesAssetDAO.eDeleteInfoCondition", mesAssetVO);
	}

	public void eDeleteEntryItem(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		delete("mesAssetDAO.eDeleteEntryItem", mesAssetVO);
	}

	public void updateInfoCondition(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesAssetDAO.updateInfoCondition", mesAssetVO);
	}

	public void insertInfoReceiving(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		insert("mesAssetDAO.insertInfoReceiving", mesAssetVO);
	}

	public void insertInfoReceivingItem(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		insert("mesAssetDAO.insertInfoReceivingItem", mesAssetVO);
	}

	public List selectReceivingList(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesAssetDAO.selectReceivingList",mesAssetVO);
	}

	public void eDeleteInfoReceiving(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesAssetDAO.eDeleteInfoReceiving", mesAssetVO);
	}

	public List selectReceivingInfoList(MesAssetVO mesAssetVO)  throws Exception{
		// TODO Auto-generated method stub
		return list("mesAssetDAO.selectReceivingInfoList",mesAssetVO);
	}

	public int selectReceivingInfoListCnt(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return (int) select("mesAssetDAO.selectReceivingInfoListCnt", mesAssetVO);
	}

	public void eDeleteImportItem(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		delete("mesAssetDAO.eDeleteImportItem", mesAssetVO);
	}

	public void updateInfoImport(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesAssetDAO.updateInfoImport", mesAssetVO);
	}

	public List eAssetCountList(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return  list("mesAssetDAO.eAssetCountList", mesAssetVO);
	}

	public int eAssetNumberCheck(MesAssetVO mesAssetVO) {
		// TODO Auto-generated method stub
		return (int) select("mesAssetDAO.eAssetNumberCheck", mesAssetVO);
	}

	public void deleteInfoReplacement(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesAssetDAO.deleteInfoReplacement", mesAssetVO);
	}

	public void updateInfoReplacement(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesAssetDAO.updateInfoReplacement", mesAssetVO);
	}

	public void deleteInfoReplacementItem(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesAssetDAO.deleteInfoReplacementItem", mesAssetVO);
	}

	public List eMainAssetMakerInfoList(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return  list("mesAssetDAO.eMainAssetMakerInfoList", mesAssetVO);
	}

	public List eMainAssetTypeInfoList(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return  list("mesAssetDAO.eMainAssetTypeInfoList", mesAssetVO);
	}

	public void updateProcessAssetInfo(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesAssetDAO.updateProcessAssetInfo", mesAssetVO);
	}

	public List mainAssetEolList(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return  list("mesAssetDAO.mainAssetEolList", mesAssetVO);
	}

	public List mainAssetEosList(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return  list("mesAssetDAO.mainAssetEosList", mesAssetVO);
	}

	public List mainSoftwareList(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return  list("mesAssetDAO.mainSoftwareList", mesAssetVO);
	}

	public List mainAssetLifeStatusList(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return  list("mesAssetDAO.mainAssetLifeStatusList", mesAssetVO);
	}

	public void updateSoftwareStatus(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
//		update("mesAssetDAO.updateAssetSignStatusTwo", mesAssetVO);
		update("mesAssetDAO.updateSoftwareStatus", mesAssetVO);
	}

	public void updateConditionStatus(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesAssetDAO.updateConditionStatus", mesAssetVO);
	}

	public MesAssetVO selectConditionStatusInfo(MesAssetVO mesAssetVO)  throws Exception{
		// TODO Auto-generated method stub
		return (MesAssetVO) select("mesAssetDAO.selectConditionStatusInfo",mesAssetVO);
	}

	public void updateConditionOutStatus(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesAssetDAO.updateConditionOutStatus", mesAssetVO);
	}

	public void updateReplacementStatus(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesAssetDAO.updateReplacementStatus", mesAssetVO);
	}

	public List mainAssetLifeStatusNewList(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return  list("mesAssetDAO.mainAssetLifeStatusNewList", mesAssetVO);
	}

	public List selectMesAssetExcelList(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return  list("mesAssetDAO.selectMesAssetExcelList", mesAssetVO);
	}

	public List selectConditionExcelList(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return  list("mesAssetDAO.selectConditionExcelList", mesAssetVO);
	}

	public List selectReplacementExcelList(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return  list("mesAssetDAO.selectReplacementExcelList", mesAssetVO);
	}

	public void eAssetModelUpdate(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesAssetDAO.eAssetModelUpdate", mesAssetVO);
	}

	public List accessInfoList(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		return  list("mesAssetDAO.accessInfoList", mesAssetVO);
	}

	public void insertExcelInfoAsset(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		insert("mesAssetDAO.insertExcelInfoAsset", mesAssetVO);
	}
	
	public void eInsertFileInfo(MesAssetVO mesAssetVO) throws Exception{
		// TODO Auto-generated method stub
		insert("mesAssetDAO.eInsertFileInfo", mesAssetVO);
	}
 
	public List eSelectFileInfoList(MesAssetVO mesAssetVO) throws Exception  {
		// TODO Auto-generated method stub
		return list("mesAssetDAO.eSelectFileInfoList", mesAssetVO);
	}
	
	public void eFileInfoDelete(MesAssetVO mesAssetVO) throws Exception  {
		// TODO Auto-generated method stub
		delete("mesAssetDAO.eFileInfoDelete", mesAssetVO);
	}
	
	public List selectConditionDate(MesAssetVO mesAssetVO) throws Exception  {
		// TODO Auto-generated method stub
		return list("mesAssetDAO.selectConditionDate", mesAssetVO);
	}


}
