package egovframework.rndp.mes.sign.service;

import java.util.List;

import egovframework.rndp.mes.asset.service.MesAssetVO;

public interface MesSignService {
	
	public List selectPositionList(MesSignVO mesSignVO) throws Exception;
	
	public List selectStaffList(MesSignVO mesSignVO) throws Exception;

	public List choiceStaffListSe(MesSignVO mesSignVO) throws Exception;

	public List selectPathList(MesSignVO mesSignVO) throws Exception;
	


	public String insertSign(String tableNum, String tableName, String kStaffKey, String kStaffName, String kStaffPosition) throws Exception;
	
	public String updateSign(String sSignKey, String kStaffKey, String kStaffName, String kStaffPosition) throws Exception;

	public void updateSignStatus(String sSignKey, String kStaffKey, String updateContent, String updateType) throws Exception;

	public void deleteSign(String sSignKey) throws Exception;

	public List selectSignList(String sSignKey) throws Exception;

	public void kwUploadSignImgAjax(MesSignVO mesSignVO) throws Exception;

	public void resetSignStatusStart(String sSignKey) throws Exception;

	public void signSubInsertPath(MesSignVO mesSignVO) throws Exception;

	public List seleteSignSelectPath(MesSignVO mesSignVO) throws Exception;

	public void signDeletePath(MesSignVO mesSignVO) throws Exception;

	public List selectSignListTwo(MesSignVO mesSignVO) throws Exception;

	public String insertSignTwo(String tableNum, String tableName, String kStaffKey, String kStaffName, String sSignStaffGubun, String kStaffPosition) throws Exception;

	public String updateSignTwo(String sSignKey, String kStaffKey, String kStaffName, String sSignStaffGubun, String kStaffPosition) throws Exception;

	public void deleteSignItemTwo(String tableNum, String tableName) throws Exception;

	public void updateSignStart(String tableNum, String tableName) throws Exception;

	public void insertSignRejectionReason(String geteAssetKey, String string, String kStaffKey, String sSignContent) throws Exception;

}