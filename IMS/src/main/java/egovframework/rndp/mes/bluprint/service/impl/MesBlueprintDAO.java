package egovframework.rndp.mes.bluprint.service.impl;

import egovframework.rndp.mes.bluprint.service.MesBlueprintVO;
import egovframework.rndp.mes.issue.service.MesIssueVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("mesBlueprintDAO")
public class MesBlueprintDAO extends EgovAbstractDAO{

	public List selectMessItemList(MesBlueprintVO mesBlueprintVO) throws Exception{
		return list("mesBlueprintDAO.selectMessItemList", mesBlueprintVO);
	}

	public int selectMessItemListCnt(MesBlueprintVO mesBlueprintVO) throws Exception{
		return (int) select("mesBlueprintDAO.selectMessItemListCnt", mesBlueprintVO);
	}
	
	public String insertUpdateMesBlueprint(MesBlueprintVO mesBlueprintVO) throws Exception{
		return (String) insert("mesBlueprintDAO.insertUpdateMesBlueprint", mesBlueprintVO);
	}
	
	public void updateMesItem(MesBlueprintVO mesBlueprintVO) throws Exception{
		update("mesBlueprintDAO.updateMesItem", mesBlueprintVO);
	}
	
	public void updateDelMesItem(MesBlueprintVO mesBlueprintVO) throws Exception{
		update("mesBlueprintDAO.updateDelMesItem", mesBlueprintVO);
	}

	public void insertMesBlueprintItem(MesBlueprintVO mesBlueprintVO) throws Exception{
		insert("mesBlueprintDAO.insertMesBlueprintItem", mesBlueprintVO);
	}
	
	public void insertMesBlueprintItemUPPI(MesBlueprintVO mesBlueprintVO) throws Exception{
		insert("mesBlueprintDAO.insertMesBlueprintItemUPPI", mesBlueprintVO);
	}
	
	public List selectMesBlueprintList(MesBlueprintVO mesBlueprintVO) throws Exception{
		return list("mesBlueprintDAO.selectMesBlueprintList", mesBlueprintVO);
	}
	
	public int selectMesBlueprintListCnt(MesBlueprintVO mesBlueprintVO) throws Exception{
		return (int) select("mesBlueprintDAO.selectMesBlueprintListCnt", mesBlueprintVO);
	}
	
	public MesBlueprintVO selectBlueprintInfo(MesBlueprintVO mesBlueprintVO) throws Exception{
		return (MesBlueprintVO) select("mesBlueprintDAO.selectBlueprintInfo", mesBlueprintVO);
	}
	
	public MesBlueprintVO selectBlueprintItemInfo(MesBlueprintVO mesBlueprintVO) throws Exception{
		return (MesBlueprintVO)select("mesBlueprintDAO.selectBlueprintItemInfo", mesBlueprintVO);
	}
	
	public void deleteMesBlueprintItem(MesBlueprintVO mesBlueprintVO) throws Exception{
		delete("mesBlueprintDAO.deleteMesBlueprintItem", mesBlueprintVO);
	}
	
	public void deleteMesBlueprintItemBLPI(MesBlueprintVO mesBlueprintVO) throws Exception{
		delete("mesBlueprintDAO.deleteMesBlueprintItemBLPI", mesBlueprintVO);
	}
	
	public void deleteMesBlueprint(MesBlueprintVO mesBlueprintVO) throws Exception{
		delete("mesBlueprintDAO.deleteMesBlueprint", mesBlueprintVO);
	}

    public void updateOrderItemBlueprint(MesBlueprintVO mesBlueprintVO) throws Exception {
		update("mesBlueprintDAO.updateOrderItemBlueprint", mesBlueprintVO);
    }

    public void updatedMesBlueprintItem(MesBlueprintVO mesBlueprintVO) throws Exception {
		update("mesBlueprintDAO.updatedMesBlueprintItem", mesBlueprintVO);
    }

    public void mesBlueprintFile(MesBlueprintVO mesBlueprintVO) throws Exception{
		insert("mesBlueprintDAO.mesBlueprintFile", mesBlueprintVO);
	}
    
    public String mesBlueprintVersion(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		return (String)select("mesBlueprintDAO.mesBlueprintVersion", mesBlueprintVO);
	}
    
    public void mesBlueprintFile1(MesBlueprintVO mesBlueprintVO) throws Exception{
		insert("mesBlueprintDAO.mesBlueprintFile1", mesBlueprintVO);
	}
    
    public List selectMesBlueprintFile(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		return list("mesBlueprintDAO.selectMesBlueprintFile", mesBlueprintVO);
	}
    
    public List selectMesBlueprintFileListU(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		return list("mesBlueprintDAO.selectMesBlueprintFileListU", mesBlueprintVO);
	}
    
    public List selectMesBlueprintFileList(MesBlueprintVO mesBlueprintVO) throws Exception{
		return list("mesBlueprintDAO.selectMesBlueprintFileList", mesBlueprintVO);
	}

	public List selectMesBlueprintFileEtc(MesBlueprintVO mesBlueprintVO) throws Exception {
		return list("mesBlueprintDAO.selectMesBlueprintFileEtc", mesBlueprintVO);
	}

	public List selectBlueprintItemList(MesBlueprintVO mesBlueprintVO) throws Exception  {
		return list("mesBlueprintDAO.selectBlueprintItemList", mesBlueprintVO);
	}

	public String mesBlueprintVersionItem(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		return (String)select("mesBlueprintDAO.mesBlueprintVersionItem", mesBlueprintVO);
	}

	public void updateMesBlueprintItemBLPI(MesBlueprintVO mesBlueprintVO) throws Exception  {
		update("mesBlueprintDAO.updateMesBlueprintItemBLPI", mesBlueprintVO);
	}

	public void deleteMesBlueprintBLPFILE(MesBlueprintVO mesBlueprintVO) throws Exception  {
		delete("mesBlueprintDAO.deleteMesBlueprintBLPFILE", mesBlueprintVO);
	}

	public void updateMesBlueprintProcess(MesBlueprintVO mesBlueprintVO) throws Exception  {
		insert("mesBlueprintDAO.updateMesBlueprintProcess", mesBlueprintVO);
	}

	public void requesteBlueprintProcess(MesBlueprintVO mesBlueprintVO) throws Exception  {
		update("mesBlueprintDAO.requesteBlueprintProcess",mesBlueprintVO);
	}

	public void updateBlueprintStatus(MesBlueprintVO mesBlueprintVO) throws Exception  {
		update("mesBlueprintDAO.updateBlueprintStatus",mesBlueprintVO);
	}

	public void deleteMesBlueprintItemBLPIOne(MesBlueprintVO mesBlueprintVO) throws Exception  {
		delete("mesBlueprintDAO.deleteMesBlueprintItemBLPIOne", mesBlueprintVO);
	}

	public void deleteMesBlueprintBLPFILEOne(MesBlueprintVO mesBlueprintVO) throws Exception  {
		delete("mesBlueprintDAO.deleteMesBlueprintBLPFILEOne", mesBlueprintVO);
	}

	public List selectSignList(MesBlueprintVO mesBlueprintVO) throws Exception  {
		return list("mesBlueprintDAO.selectSignList", mesBlueprintVO);
	}

	public void eChangeManagementInfoInssert(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		insert("mesBlueprintDAO.eChangeManagementInfoInssert", mesBlueprintVO);
	}

	public void eAssetInfoInsert(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		insert("mesBlueprintDAO.eAssetInfoInsert", mesBlueprintVO);
	}

	public List selectChangeList(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		return list("mesBlueprintDAO.selectChangeList", mesBlueprintVO);
	}

	public int selectChangeListCnt(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		return (int) select("mesBlueprintDAO.selectChangeListCnt", mesBlueprintVO);
	}

	public MesBlueprintVO selectChangeInfo(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		return (MesBlueprintVO)select("mesBlueprintDAO.selectChangeInfo", mesBlueprintVO);
	}

	public List eSelectAssetInfoList(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		return list("mesBlueprintDAO.eSelectAssetInfoList", mesBlueprintVO);
	}

	public void eIssueInfoInssert(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		insert("mesBlueprintDAO.eIssueInfoInssert", mesBlueprintVO);
	}

	public void eIssueAssetInfoInsert(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		insert("mesBlueprintDAO.eIssueAssetInfoInsert", mesBlueprintVO);
	}

	public List selectIssueList(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		return list("mesBlueprintDAO.selectIssueList", mesBlueprintVO);
	}

	public int selectIssueListCnt(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		return (int) select("mesBlueprintDAO.selectIssueListCnt", mesBlueprintVO);
	}

	public MesBlueprintVO selectIssueInfo(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		return (MesBlueprintVO)select("mesBlueprintDAO.selectIssueInfo", mesBlueprintVO);
	}

	public List eSelectIssueAssetInfoList(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		return list("mesBlueprintDAO.eSelectIssueAssetInfoList", mesBlueprintVO);
	}

	public void deleteBlueprint(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		update("mesBlueprintDAO.deleteBlueprint", mesBlueprintVO);
	}

	public void eChangeManagementInfoUpdate(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		update("mesBlueprintDAO.eChangeManagementInfoUpdate", mesBlueprintVO);
	}

	public void eChangeAssetInfoDelete(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		delete("mesBlueprintDAO.eChangeAssetInfoDelete", mesBlueprintVO);
	}

	public void selectIssueDelete(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		delete("mesBlueprintDAO.selectIssueDelete", mesBlueprintVO);
	}

	public void updateIssueInfo(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		update("mesBlueprintDAO.updateIssueInfo", mesBlueprintVO);
	}

	public void eIssueAssetInfoDelete(MesBlueprintVO mesBlueprintVO) {
		// TODO Auto-generated method stub
		delete("mesBlueprintDAO.eIssueAssetInfoDelete", mesBlueprintVO);
	}

	public List selectSRList(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		return list("mesBlueprintDAO.selectSRList", mesBlueprintVO);
	}

	public int selectSRListCnt(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		return (int) select("mesBlueprintDAO.selectSRListCnt", mesBlueprintVO);
	}

	public void eSRInfoInssert(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		insert("mesBlueprintDAO.eSRInfoInssert", mesBlueprintVO);
	}

	public void eSRAssetInfoInsert(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		insert("mesBlueprintDAO.eSRAssetInfoInsert", mesBlueprintVO);
	}

	public MesBlueprintVO selectSRInfo(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		return (MesBlueprintVO)select("mesBlueprintDAO.selectSRInfo", mesBlueprintVO);
	}

	public List eSelectSRAssetInfoList(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		return list("mesBlueprintDAO.eSelectSRAssetInfoList", mesBlueprintVO);
	}

	public void eSRAssetInfoDelete(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		delete("mesBlueprintDAO.eSRAssetInfoDelete", mesBlueprintVO);
	}

	public void selectSRDelete(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		update("mesBlueprintDAO.selectSRDelete", mesBlueprintVO);
	}

	public void eSRInfoUpdate(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		update("mesBlueprintDAO.eSRInfoUpdate", mesBlueprintVO);
	}

	public void eInsertFileInfo(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		insert("mesBlueprintDAO.eInsertFileInfo", mesBlueprintVO);
	}

	public void eInsertDetailInfo(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		insert("mesBlueprintDAO.eInsertDetailInfo", mesBlueprintVO);
	}

	public List eSelectDeteliInfoList(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		return list("mesBlueprintDAO.eSelectDeteliInfoList", mesBlueprintVO);
	}

	public List eSelectFileInfoList(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		return list("mesBlueprintDAO.eSelectFileInfoList", mesBlueprintVO);
	}

	public void eDetailInfoInfoDelete(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		delete("mesBlueprintDAO.eDetailInfoInfoDelete", mesBlueprintVO);
	}

	public void eFileInfoDelete(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		delete("mesBlueprintDAO.eFileInfoDelete", mesBlueprintVO);
	}

	public void insertIssueFileListInfo(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		insert("mesBlueprintDAO.insertIssueFileListInfo", mesBlueprintVO);
	}

	public void insertIssueDetailListInfo(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		insert("mesBlueprintDAO.insertIssueDetailListInfo", mesBlueprintVO);
	}

	public void insertIssueNotesListInfo(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		insert("mesBlueprintDAO.insertIssueNotesListInfo", mesBlueprintVO);
	}

	public List eSelectIssueDeteliInfoList(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		return list("mesBlueprintDAO.eSelectIssueDeteliInfoList", mesBlueprintVO);
	}

	public List eSelectIssueFileInfoList(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		return list("mesBlueprintDAO.eSelectIssueFileInfoList", mesBlueprintVO);
	}

	public List eSelectIssueNotesInfoList(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		return list("mesBlueprintDAO.eSelectIssueNotesInfoList", mesBlueprintVO);
	}

	public void eIssueNotesInfoDelete(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		delete("mesBlueprintDAO.eIssueNotesInfoDelete", mesBlueprintVO);
	}

	public void eIssueDetailInfoDelete(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		delete("mesBlueprintDAO.eIssueDetailInfoDelete", mesBlueprintVO);
	}

	public void eIssueFileInfoDelete(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		delete("mesBlueprintDAO.eIssueFileInfoDelete", mesBlueprintVO);
	}

	public void mesSignBlueprint(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		update("mesInspectionDAO.mesSignBlueprint", mesBlueprintVO);
	}

	public void mesSignBlueIssue(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		update("mesInspectionDAO.mesSignBlueIssue", mesBlueprintVO);
	}

	public void mesSignBlueSr(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		update("mesInspectionDAO.mesSignBlueSr", mesBlueprintVO);
	}

	public List selectChangeExcelList(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		return list("mesBlueprintDAO.selectChangeExcelList", mesBlueprintVO);
	}

	public List selectIssueExcelList(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		return list("mesBlueprintDAO.selectIssueExcelList", mesBlueprintVO);
	}

	public List selectSRExcelList(MesBlueprintVO mesBlueprintVO) throws Exception  {
		// TODO Auto-generated method stub
		return list("mesBlueprintDAO.selectSRExcelList", mesBlueprintVO);
	}

	public void srFileInsert(MesBlueprintVO mesBlueprintVO) throws Exception{
		// TODO Auto-generated method stub
		insert("mesBlueprintDAO.srFileInsert", mesBlueprintVO);
	}
	
	public List eFileInfoList(MesBlueprintVO mesBlueprintVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesBlueprintDAO.eFileInfoList", mesBlueprintVO);
	}
	
	public void srFileDelete(MesBlueprintVO mesBlueprintVO) throws Exception{
		// TODO Auto-generated method stub
		delete("mesBlueprintDAO.srFileDelete", mesBlueprintVO);
	}
	 
}
