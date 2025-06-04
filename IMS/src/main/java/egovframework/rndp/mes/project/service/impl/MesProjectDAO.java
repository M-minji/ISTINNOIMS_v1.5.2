package egovframework.rndp.mes.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rndp.mes.project.service.MesProjectVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("mesProjectDAO")
public class MesProjectDAO extends EgovAbstractDAO {

	public List selectMesBaljuItemList(MesProjectVO mesProjectVO) throws Exception{
		return list("mesProjectDAO.selectMesBaljuItemList", mesProjectVO);
	}

	public String insertMesProject(MesProjectVO mesProjectVO) {
		return (String) insert("mesProjectDAO.insertMesProject", mesProjectVO);
		
	}

	public List selectMesProjectList(MesProjectVO mesProjectVO) {
		return list("mesProjectDAO.selectMesProjectList",mesProjectVO);
	}

	public int selectMesProjectListCnt(MesProjectVO mesProjectVO) {
		return  (int) select("mesProjectDAO.selectMesProjectListCnt",mesProjectVO);
	}

	public MesProjectVO selectProInfo(MesProjectVO mesProjectVO) {
		return (MesProjectVO) select("mesProjectDAO.selectProInfo", mesProjectVO);
	}

	public void deleteMesProject(MesProjectVO mesProjectVO) {
		delete("mesProjectDAO.deleteMesProject",mesProjectVO);
	}

	public void updateMesProject(MesProjectVO mesProjectVO) {
		update("mesProjectDAO.updateMesProject",mesProjectVO);
	}

	public void updateMesProjectProcess(MesProjectVO mesProjectVO) {
		update("mesProjectDAO.updateMesProjectProcess",mesProjectVO);
	}

	public void requestProjectProcess(MesProjectVO mesProjectVO) {
		update("mesProjectDAO.requestProjectProcess",mesProjectVO);
	}

	public void updateProjectStatus(MesProjectVO mesProjectVO) {
		update("mesProjectDAO.updateProjectStatus",mesProjectVO);
	}

	public void mesProjectFile(MesProjectVO mesProjectVO) {
		insert("mesProjectDAO.mesProjectFile", mesProjectVO);
	}

	public List selectMesProjectFile(MesProjectVO mesProjectVO) {
		return list("mesProjectDAO.selectMesProjectFile", mesProjectVO);
	}

	public void deleteMesProjectItem(MesProjectVO mesProjectVO) {
		delete("mesProjectDAO.deleteMesProjectItem",mesProjectVO);
	}

	public void eMesInsertProjectInfo(MesProjectVO mesProjectVO) throws Exception{
		// TODO Auto-generated method stub
		insert("mesProjectDAO.eMesInsertProjectInfo", mesProjectVO);
	}

	public List mesProjectInfoList(MesProjectVO mesProjectVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesProjectDAO.mesProjectInfoList",mesProjectVO);
	}

	public int mesProjectInfoListCnt(MesProjectVO mesProjectVO) throws Exception{
		// TODO Auto-generated method stub
		return  (int) select("mesProjectDAO.mesProjectInfoListCnt",mesProjectVO);
	}

	public MesProjectVO selectProjectInfo(MesProjectVO mesProjectVO) {
		// TODO Auto-generated method stub
		return (MesProjectVO) select("mesProjectDAO.selectProjectInfo", mesProjectVO);
	}

 
	public void statusProjectInfo(MesProjectVO mesProjectVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesProjectDAO.statusProjectInfo", mesProjectVO);
	}

	public void updateProjectInfo(MesProjectVO mesProjectVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesProjectDAO.updateProjectInfo", mesProjectVO);
	}

	public void updateProjectSingStatus(MesProjectVO mesProjectVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesProjectDAO.updateProjectSingStatus", mesProjectVO);
	}

	public List mesProjectExcelInfoList(MesProjectVO mesProjectVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesProjectDAO.mesProjectExcelInfoList",mesProjectVO);
	}

}
