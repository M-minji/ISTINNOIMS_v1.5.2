package egovframework.rndp.admin.intra.staff.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rndp.admin.intra.staff.service.StaffIntraVO;
import egovframework.rndp.admin.intra.staff.service.StaffMenuAuthVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("staffIntraDAO")
public class StaffIntraDAO extends EgovAbstractDAO{

	public List selectStaffReuestList(StaffIntraVO vo) throws Exception{
		return list("staffIntraDAO.selectStaffReuestList", vo);
	}	
	
	public int selectCount() throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("staffIntraDAO.selectCount");
	}
	
	public StaffIntraVO selectStaffInfoForView(int kStaffRequestKey) throws Exception {
		return (StaffIntraVO) select("staffIntraDAO.selectStaffInfoForView", kStaffRequestKey);
	}
	
	public void updateStaffNum(StaffIntraVO vo) throws Exception{
		update("staffIntraDAO.updateStaffNum", vo);
	}
	
	public void deleteStaffReuest(StaffIntraVO vo) throws Exception{
		delete("staffIntraDAO.deleteStaffReuest", vo);
	}
	
	public void insertStaffApp(StaffIntraVO vo) throws Exception{
		update("staffIntraDAO.insertStaffApp", vo);
	}

	public List selectStaffList(StaffIntraVO vo) throws Exception{
		return list("staffIntraDAO.selectStaffList", vo);
	}

	public List selectSectorList() throws Exception{
		return list("staffIntraDAO.selectSectorList");
	}
	
	public List selectClassList() throws Exception{
		return list("staffIntraDAO.selectClassList");
	}
	
	public List selectPositionList() throws Exception{
		return list("staffIntraDAO.selectPositionList");
	}
	
	public StaffIntraVO selectStaffInfo(StaffIntraVO vo) throws Exception{
		return (StaffIntraVO) select("staffIntraDAO.selectStaffInfo", vo);
	}

	public void insertStaff(StaffIntraVO vo) throws Exception{
		update("staffIntraDAO.insertStaff", vo);
	}
	
	public void updateStaff(StaffIntraVO vo) throws Exception{
		update("staffIntraDAO.updateStaff", vo);
	}

	public void deleteStaff(StaffIntraVO vo) throws Exception{
		delete("staffIntraDAO.deleteStaff", vo);
	}
	
	public int selectCountBusinessNote(StaffIntraVO vo) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("staffIntraDAO.selectCountBusinessNote", vo);
	}
	
	public StaffIntraVO selectStaffView2(StaffIntraVO vo) throws Exception{
		return (StaffIntraVO) select("staffIntraDAO.selectStaffView2", vo);
	}
	
	public List selectStaffMenuList(StaffMenuAuthVO vo) throws Exception{
		return list("staffIntraDAO.selectStaffMenuList", vo);
	}	
	
	public List selectStaffTopMenuList(StaffMenuAuthVO vo) throws Exception{
		return list("staffIntraDAO.selectStaffTopMenuList", vo);
	}
	
	public List selectStaffTopMenuList2(StaffMenuAuthVO vo) throws Exception{
		return list("staffIntraDAO.selectStaffTopMenuList2", vo);
	}
	
	public StaffMenuAuthVO selectStaffMenu(StaffMenuAuthVO vo) throws Exception{
		return (StaffMenuAuthVO) select("staffIntraDAO.selectStaffMenu", vo);
	}
	
	public void deleteStaffMenuAuth(StaffMenuAuthVO vo) throws Exception{
		delete("staffIntraDAO.deleteStaffMenuAuth", vo);
	}
	
	public int staffMenuAuthMaxCnt() throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("staffIntraDAO.staffMenuAuthMaxCnt");
	}
	
	public void staffMenuAuthUpdate(StaffMenuAuthVO vo) throws Exception{
		delete("staffIntraDAO.staffMenuAuthUpdate", vo);
	}

	 
	public int updateStaffImageFile(StaffIntraVO vo) throws Exception{
		return update("staffIntraDAO.updateStaffImageFile", vo);
	}
 
}
