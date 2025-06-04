package egovframework.rndp.mes.maintance.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rndp.mes.maintance.service.MesMaintanceVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("mesMaintanceDAO")
public class MesMaintanceDAO extends EgovAbstractDAO{

	public List selectMesMaintanceList(MesMaintanceVO mesMaintanceVO) {
		// TODO Auto-generated method stub
		return list("mesMaintanceDAO.selectMesMaintanceList", mesMaintanceVO);
	}

	public int selectMesMaintanceListCnt(MesMaintanceVO mesMaintanceVO) {
		// TODO Auto-generated method stub
		return (int) select("mesMaintanceDAO.selectMesMaintanceListCnt", mesMaintanceVO);
	}

	public void insertMesMaintance(MesMaintanceVO mesMaintanceVO) {
		// TODO Auto-generated method stub
		insert("mesMaintanceDAO.insertMesMaintance", mesMaintanceVO);
	}

	public MesMaintanceVO selectMaintanceInfo(MesMaintanceVO mesMaintanceVO) {
		// TODO Auto-generated method stub
		return (MesMaintanceVO) select("mesMaintanceDAO.selectMaintanceInfo", mesMaintanceVO);
	}

	public void updateMesMaintance(MesMaintanceVO mesMaintanceVO) {
		// TODO Auto-generated method stub
		update("mesMaintanceDAO.updateMesMaintance", mesMaintanceVO);
	}

	public void updateMesProcess(MesMaintanceVO mesMaintanceVO) {
		// TODO Auto-generated method stub
		update("mesMaintanceDAO.updateMesProcess", mesMaintanceVO);
	}

	public void updateMesProcessNull(MesMaintanceVO mesMaintanceVO) {
		// TODO Auto-generated method stub
		update("mesMaintanceDAO.updateMesProcessNull", mesMaintanceVO);
	}

	public void updateMesSign(MesMaintanceVO mesMaintanceVO) {
		// TODO Auto-generated method stub
		update("mesMaintanceDAO.updateMesSign", mesMaintanceVO);
	}

	public void updateMesSignNull(MesMaintanceVO mesMaintanceVO) {
		// TODO Auto-generated method stub
		update("mesMaintanceDAO.updateMesSignNull", mesMaintanceVO);
	}

	public void updateMesSignDecison(MesMaintanceVO mesMaintanceVO) {
		// TODO Auto-generated method stub
		update("mesMaintanceDAO.updateMesSignDecison", mesMaintanceVO);
	}
	
	public void deleteMesMaintance(MesMaintanceVO mesMaintanceVO) {
		// TODO Auto-generated method stub
		delete("mesMaintanceDAO.deleteMesMaintance", mesMaintanceVO);
	}

	public String getMaintanceMaxKey(MesMaintanceVO mesMaintanceVO) {
		// TODO Auto-generated method stub
		return (String) select("mesMaintanceDAO.getMaintanceMaxKey", mesMaintanceVO);
	}

	public void updateMaintanceStatus(MesMaintanceVO mesMaintanceVO) {
		// TODO Auto-generated method stub
		update("mesMaintanceDAO.updateMaintanceStatus", mesMaintanceVO);
	}
	
	
	public void insertMaintanceFile(MesMaintanceVO mesMaintanceVO) {
		insert("mesMaintanceDAO.insertMaintanceFile", mesMaintanceVO);
	}
	
	public void deleteMaintanceFile(MesMaintanceVO mesMaintanceVO) {
		delete("mesMaintanceDAO.deleteMaintanceFile", mesMaintanceVO);
	}
	
	@SuppressWarnings("unchecked")
	public List<MesMaintanceVO> getMaintanceFile(MesMaintanceVO mesMaintanceVO) {
		return (List<MesMaintanceVO>) list("mesMaintanceDAO.getMaintanceFile", mesMaintanceVO);
	}

	public void insertProcessFile(MesMaintanceVO mesMaintanceVO) {
		insert("mesMaintanceDAO.insertProcessFile", mesMaintanceVO);
	}

	@SuppressWarnings("unchecked")
	public List<MesMaintanceVO> getProcessFile(MesMaintanceVO mesMaintanceVO) {
		return (List<MesMaintanceVO>) list("mesMaintanceDAO.getProcessFile", mesMaintanceVO);
	}

	public void deleteProcessFile(MesMaintanceVO mesMaintanceVO) {
		delete("mesMaintanceDAO.deleteProcessFile", mesMaintanceVO);
	}

	public List selectMaintanceInfoList(Map keyList) throws Exception {
		// TODO Auto-generated method stub
		return list("mesMaintanceDAO.selectMaintanceInfoList",keyList);
	}

	public List selectMesMaintancePopList(MesMaintanceVO mesMaintanceVO) throws Exception{
		// TODO Auto-generated method stub
//		return list("mesMaintanceDAO.selectMesMaintanceList", mesMaintanceVO);
		return list("mesMaintanceDAO.selectMesMaintancePopList", mesMaintanceVO);
	}

	public int selectMesMaintancePopListCnt(MesMaintanceVO mesMaintanceVO) throws Exception{
		// TODO Auto-generated method stub
		return (int) select("mesMaintanceDAO.selectMesMaintancePopListCnt", mesMaintanceVO);
	}

	public void insertAssetInfoItem(MesMaintanceVO mesMaintanceVO) throws Exception{
		// TODO Auto-generated method stub
		insert("mesMaintanceDAO.insertAssetInfoItem", mesMaintanceVO);
	}

	public void insertMaintanceInfoItem(MesMaintanceVO mesMaintanceVO) throws Exception{
		// TODO Auto-generated method stub
		insert("mesMaintanceDAO.insertMaintanceInfoItem", mesMaintanceVO);
	}
}