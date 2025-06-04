package egovframework.rndp.mes.sign.service.impl;

import java.util.List;

import org.apache.bcel.generic.NEW;
import org.springframework.stereotype.Repository;

import egovframework.rndp.mes.login.service.MesK_StaffVo;
import egovframework.rndp.mes.sign.service.MesSignVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("mesSignDAO")
public class MesSignDAO extends EgovAbstractDAO{

	public List selectPositionList(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		return list("mesSignDAO.selectPositionList", mesSignVO);
	}

	public List selectStaffList(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		return list("mesSignDAO.selectStaffList", mesSignVO);
	}

	public List choiceStaffListSe(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		return list("mesSignDAO.choiceStaffListSe", mesSignVO);
	}

	public List selectPathList(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		return list("mesSignDAO.selectPathList", mesSignVO);
	}

	public String getMaxSignKey(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		return (String) select("mesSignDAO.getMaxSignKey", mesSignVO);
	}

	public String getSignCount(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		return (String) select("mesSignDAO.getSignCount", mesSignVO);
	}


	public void insertSign(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		insert("mesSignDAO.insertSign", mesSignVO);
	}

	public void insertSignItem(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		insert("mesSignDAO.insertSignItem", mesSignVO);
	}

	public void updateSignStatus(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		update("mesSignDAO.updateSignStatus", mesSignVO);
	}

	public void resetSignStatus(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		update("mesSignDAO.resetSignStatus", mesSignVO);
	}
	
	public void updateSignProgress(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		update("mesSignDAO.updateSignProgress", mesSignVO);
	}

	public void deleteSign(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		delete("mesSignDAO.deleteSign", mesSignVO);
	}

	public void deleteSignItem(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		delete("mesSignDAO.deleteSignItem", mesSignVO);
	}

	public void updateSign(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		update("mesSignDAO.updateSign", mesSignVO);
	}

	public List selectSignList(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		return list("mesSignDAO.selectSignList", mesSignVO);
	}

	public void resetSignStatusStart(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		update("mesSignDAO.resetSignStatusStart", mesSignVO);
	}

	public MesSignVO selectSignInfo(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		return (MesSignVO) select("mesSignDAO.selectSignInfo", mesSignVO);
	}

	public int selectSignSubNum(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		return (int) select("mesSignDAO.selectSignSubNum",mesSignVO);
	}

	public void signSubInsertPath(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		insert("mesSignDAO.signSubInsertPath", mesSignVO);
	}

	public List seleteSignSelectPath(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		return list("mesSignDAO.seleteSignSelectPath", mesSignVO);
	}

	public void signDeletePath(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		insert("mesSignDAO.signDeletePath", mesSignVO);
	}

	public List selectSignListTwo(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		return list("mesSignDAO.selectSignListTwo", mesSignVO);
	}

	public void deleteSignItemTwo(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		delete("mesSignDAO.deleteSignItemTwo", mesSignVO);
	}

	public void deleteSignTwo(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		delete("mesSignDAO.deleteSignTwo", mesSignVO);
	}

	public void updateSignStart(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		update("mesSignDAO.updateSignStart", mesSignVO);
	}

	public void updateSignStatusTwo(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		update("mesSignDAO.updateSignStatusTwo", mesSignVO);
	}

	public String getSignCountTwo(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		return (String)select("mesSignDAO.getSignCountTwo", mesSignVO);
	}

	public void updateSignProgressTwo(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		update("mesSignDAO.updateSignProgressTwo", mesSignVO);
	}

	public void updateSignRejectionReason(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		update("mesSignDAO.updateSignRejectionReason", mesSignVO);
	}

	public void eFinalApprovalUpdate(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		update("mesSignDAO.eFinalApprovalUpdate", mesSignVO);
	}

	public String sSignStaffGubunCheck(MesSignVO mesSignVO) {
		// TODO Auto-generated method stub
		return (String)select("mesSignDAO.sSignStaffGubunCheck", mesSignVO);
	}

	 
}