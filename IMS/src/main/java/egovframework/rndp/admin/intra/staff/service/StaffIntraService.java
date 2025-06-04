package egovframework.rndp.admin.intra.staff.service;

import java.util.List;

import egovframework.rndp.mes.asset.service.MesAssetVO;

public interface StaffIntraService {
	
	public List selectStaffReuestList(StaffIntraVO vo) throws Exception;
	
	public int selectCount() throws Exception;
	
	public StaffIntraVO selectStaffInfoForView(int kStaffRequestKe) throws Exception;
	
	public void updateStaffNum(StaffIntraVO vo) throws Exception;
	
	public void deleteStaffReuest(StaffIntraVO vo)  throws Exception;
	
	public List selectStaffList(StaffIntraVO vo) throws Exception;
	
	public List selectSectorList() throws Exception;

	public List selectClassList() throws Exception;
	
	public List selectPositionList() throws Exception;
	
	public StaffIntraVO selectStaffInfo(StaffIntraVO vo) throws Exception;
	
	public void insertStaff(StaffIntraVO vo) throws Exception;
	
	public void updateStaff(StaffIntraVO vo) throws Exception;
	
	public String deleteStaff(StaffIntraVO vo) throws Exception;
	
	public StaffIntraVO selectStaffView2(StaffIntraVO vo) throws Exception;
	
	public List selectStaffMenuList(StaffMenuAuthVO vo) throws Exception;
	
	public List selectStaffTopMenuList2(StaffMenuAuthVO vo)throws Exception;
	
	public List selectStaffTopMenuList(StaffMenuAuthVO vo) throws Exception;
	
	public StaffMenuAuthVO selectStaffMenu(StaffMenuAuthVO vo) throws Exception;
	
	public void deleteStaffMenuAuth(StaffMenuAuthVO vo)  throws Exception;
	
	public int staffMenuAuthMaxCnt() throws Exception;
	
	public void staffMenuAuthUpdate(StaffMenuAuthVO vo) throws Exception;
	

	public int updateStaffImageFile(StaffIntraVO vo) throws Exception;


	
}
