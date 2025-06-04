package egovframework.rndp.mes.user.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rndp.admin.intra.staff.service.StaffIntraVO;
import egovframework.rndp.admin.intra.staff.service.StaffMenuAuthVO;
import egovframework.rndp.mes.user.service.MesUserVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("mesUserDAO")
public class MesUserDAO extends EgovAbstractDAO{

	public List selectUserffList(MesUserVO mesUserVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesUserDAO.selectUserffList", mesUserVO);
	}

	public int selectUserCount(MesUserVO mesUserVO) throws Exception{
		// TODO Auto-generated method stub
		return (Integer)getSqlMapClientTemplate().queryForObject("mesUserDAO.selectUserCount",mesUserVO);
	}

	public List mesUserMenuList(MesUserVO mesUserVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesUserDAO.mesUserMenuList", mesUserVO);
	}

	public List selectSectorList() throws Exception{
		// TODO Auto-generated method stub
		return list("mesUserDAO.selectSectorList");
	}

	public List selectClassList()  throws Exception{
		// TODO Auto-generated method stub
		return list("mesUserDAO.selectClassList");
	}
 

	public MesUserVO selectUserInfo(MesUserVO mesUserVO)  throws Exception{
		// TODO Auto-generated method stub
		return (MesUserVO) select("mesUserDAO.selectUserInfo", mesUserVO);
	}

	public List selectPositionIntraList(MesUserVO mesUserVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesUserDAO.selectPositionIntraList",mesUserVO);
	}

	public int selectMaxRank()throws Exception{
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject("mesUserDAO.selectMaxRank");
	}

	public MesUserVO selectpositionInfo(MesUserVO mesUserVO) throws Exception{
		// TODO Auto-generated method stub
		return (MesUserVO) select("mesUserDAO.selectpositionInfo", mesUserVO);
	}

	public List selectIclassList(MesUserVO mesUserVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesUserDAO.selectIclassList",mesUserVO);
	}

	public List envList()  throws Exception{
		// TODO Auto-generated method stub
		return list("mesUserDAO.envList");
	}

	public void updateEnv(MesUserVO vo)  throws Exception{
		// TODO Auto-generated method stub
		update("mesUserDAO.updateEnv", vo);
	}

	public void mesUserLevelInsert(MesUserVO mesUserVO)  throws Exception{
		// TODO Auto-generated method stub
		insert("mesUserDAO.mesUserLevelInsert", mesUserVO);
	}

	public MesUserVO selectIclassInfo(MesUserVO mesUserVO)   throws Exception{
		// TODO Auto-generated method stub
		return (MesUserVO) select("mesUserDAO.selectIclassInfo", mesUserVO);
	}

	public void mesUserLevelUpdate(MesUserVO mesUserVO)   throws Exception{
		// TODO Auto-generated method stub
		update("mesUserDAO.mesUserLevelUpdate", mesUserVO);
	}
	
	public void updateItemGubun(MesUserVO mesUserVO)   throws Exception{
		update("mesUserDAO.updateItemGubun", mesUserVO);
	}

	public void mesUserLevelDelete(MesUserVO mesUserVO)   throws Exception{
		// TODO Auto-generated method stub
		delete("mesUserDAO.mesUserLevelDelete" , mesUserVO);
	}
	public void deleteUser(MesUserVO mesUserVO)   throws Exception{
		// TODO Auto-generated method stub
		delete("mesUserDAO.deleteUser" , mesUserVO);
	}

	public void mesUserPositionInsert(MesUserVO mesUserVO) throws Exception{
		// TODO Auto-generated method stub
		insert("mesUserDAO.mesUserPositionInsert", mesUserVO);
	}

	public void mesUserPositionUpdate(MesUserVO mesUserVO)  throws Exception{
		// TODO Auto-generated method stub
		update("mesUserDAO.mesUserPositionUpdate", mesUserVO);
	}

	public void mesUserPositionDelete(MesUserVO mesUserVO)  throws Exception{
		// TODO Auto-generated method stub
		delete("mesUserDAO.mesUserPositionDelete" , mesUserVO);
	}

	public List selectUseReuestList(MesUserVO mesUserVO)   throws Exception{
		// TODO Auto-generated method stub
		return list("mesUserDAO.selectUseReuestList",mesUserVO);
	}
	
	public List selectOfficerItemList(MesUserVO mesUserVO)   throws Exception{
		// TODO Auto-generated method stub
		return list("mesUserDAO.selectOfficerItemList",mesUserVO);
	}

	public int selectUseReuestListCount()   throws Exception{
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject("mesUserDAO.selectUseReuestListCount");
	}
	
	public int selectOfficerItemListCnt(MesUserVO mesUserVO)   throws Exception{
		return (Integer) select("mesUserDAO.selectOfficerItemListCnt");
	}

	public void mesUserRegDelete(MesUserVO mesUserVO) throws Exception{
		// TODO Auto-generated method stub
		delete("mesUserDAO.mesUserRegDelete" , mesUserVO);
	}
	
	public void deleteOfficer(MesUserVO mesUserVO) throws Exception{
		// TODO Auto-generated method stub
		delete("mesUserDAO.deleteOfficer" , mesUserVO);
	}

	public MesUserVO mesUserRegInfo(MesUserVO mesUserVO) throws Exception{
		// TODO Auto-generated method stub
		return (MesUserVO) select("mesUserDAO.mesUserRegInfo", mesUserVO);
	}

	public void mesUserRegApprovalInsert(MesUserVO mesUserVO) throws Exception{
		// TODO Auto-generated method stub
		insert("mesUserDAO.mesUserRegApprovalInsert", mesUserVO);
	}

	public int mesCheckId(String id) throws Exception{
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject("mesUserDAO.mesCheckId",id);
	}

	public void mesUserInsert(MesUserVO mesUserVO)  throws Exception{
		// TODO Auto-generated method stub
		insert("mesUserDAO.mesUserInsert", mesUserVO);
	}

	public void mesUserInfoUpdate(MesUserVO mesUserVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesUserDAO.mesUserInfoUpdate", mesUserVO);
	}

	public void deleteUserMenuAuth(MesUserVO mesUserVO)  throws Exception{
		// TODO Auto-generated method stub
		delete("mesUserDAO.deleteUserMenuAuth", mesUserVO);
//		delete("mesUserDAO.deleteUserMenuAuth222", mesUserVO);
	}

	public int mesUserMenuAuthMaxCnt()  throws Exception{
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject("mesUserDAO.mesUserMenuAuthMaxCnt");
	}

	public void mesUserMenuAuthUpdate(MesUserVO vo)   throws Exception{
		// TODO Auto-generated method stub
		insert("mesUserDAO.mesUserMenuAuthUpdate", vo);
//		insert("mesUserDAO.mesUserMenuAuthUpdate222", vo);
	}
 
	public void insertOfficer(MesUserVO mesUserVO) throws Exception{
		insert("mesUserDAO.insertOfficer", mesUserVO);
	}


	/**
	 * 비밀번호 변경 
	 * @param mesUserVO
	 * @throws Exception
	 */
	public void updateUserInfoPwd(MesUserVO mesUserVO)   throws Exception{
		// TODO Auto-generated method stub
		update("mesUserDAO.updateUserInfoPwd", mesUserVO);
	}

	public void deleteMenual(MesUserVO mesUserVO) {
		// TODO Auto-generated method stub
		delete("mesUserDAO.deleteMenual", mesUserVO);
		
	}

	public void insertMenual(MesUserVO mesUserVO) {
		// TODO Auto-generated method stub
		insert("mesUserDAO.insertMenual", mesUserVO);
		
	}

	public void updateSetCount(MesUserVO mesUserVO) {
		update("mesUserDAO.updateSetCount", mesUserVO);
	}

	public void updateSetPass(MesUserVO mesUserVO) {
		update("mesUserDAO.updateSetPass", mesUserVO);
	}

	public void mesUserInfoUpdateNew(MesUserVO mesUserVO) {
		// TODO Auto-generated method stub
		update("mesUserDAO.mesUserInfoUpdateNew", mesUserVO);
	}

	public void databaseAllTruncate(MesUserVO mesUserVO) {
		// TODO Auto-generated method stub
		delete("mesUserDAO.databaseAllTruncate", mesUserVO);
	}

	public List selectUserffPopList(MesUserVO mesUserVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesUserDAO.selectUserffPopList", mesUserVO);
	}

	public int selectUserPopCount(MesUserVO mesUserVO) throws Exception{
		// TODO Auto-generated method stub
		return (int) select("mesUserDAO.selectUserPopCount", mesUserVO);
	}
	
	public void databaseAllTruncate1(MesUserVO mesUserVO) {
		// TODO Auto-generated method stub
		delete("mesUserDAO.databaseAllTruncate1", mesUserVO);
	}
	
	public void databaseAllTruncate2(MesUserVO mesUserVO) {
		// TODO Auto-generated method stub
		delete("mesUserDAO.databaseAllTruncate2", mesUserVO);
	}
	public void databaseAllTruncate3(MesUserVO mesUserVO) {
		// TODO Auto-generated method stub
		delete("mesUserDAO.databaseAllTruncate3", mesUserVO);
	}
	public void databaseAllTruncate4(MesUserVO mesUserVO) {
		// TODO Auto-generated method stub
		delete("mesUserDAO.databaseAllTruncate4", mesUserVO);
	}
	public void databaseAllTruncate5(MesUserVO mesUserVO) {
		// TODO Auto-generated method stub
		delete("mesUserDAO.databaseAllTruncate5", mesUserVO);
	}
	public void databaseAllTruncate6(MesUserVO mesUserVO) {
		// TODO Auto-generated method stub
		delete("mesUserDAO.databaseAllTruncate6", mesUserVO);
	}
	public void databaseAllTruncate7(MesUserVO mesUserVO) {
		// TODO Auto-generated method stub
		delete("mesUserDAO.databaseAllTruncate7", mesUserVO);
	}
	public void databaseAllTruncate8(MesUserVO mesUserVO) {
		// TODO Auto-generated method stub
		delete("mesUserDAO.databaseAllTruncate8", mesUserVO);
	}
	public void databaseAllTruncate9(MesUserVO mesUserVO) {
		// TODO Auto-generated method stub
		delete("mesUserDAO.databaseAllTruncate9", mesUserVO);
	}
	public void databaseAllTruncate10(MesUserVO mesUserVO) {
		// TODO Auto-generated method stub
		delete("mesUserDAO.databaseAllTruncate10", mesUserVO);
	}
	public void databaseAllTruncate11(MesUserVO mesUserVO) {
		// TODO Auto-generated method stub
		delete("mesUserDAO.databaseAllTruncate11", mesUserVO);
	}
	public void databaseAllTruncate12(MesUserVO mesUserVO) {
		// TODO Auto-generated method stub
		delete("mesUserDAO.databaseAllTruncate12", mesUserVO);
	}
	public void databaseAllTruncate13(MesUserVO mesUserVO) {
		// TODO Auto-generated method stub
		delete("mesUserDAO.databaseAllTruncate13", mesUserVO);
	}
	public void databaseAllTruncate14(MesUserVO mesUserVO) {
		// TODO Auto-generated method stub
		delete("mesUserDAO.databaseAllTruncate14", mesUserVO);
	}
	public void databaseAllTruncate15(MesUserVO mesUserVO) {
		// TODO Auto-generated method stub
		delete("mesUserDAO.databaseAllTruncate15", mesUserVO);
	}
	public void databaseAllTruncate16(MesUserVO mesUserVO) {
		// TODO Auto-generated method stub
		delete("mesUserDAO.databaseAllTruncate16", mesUserVO);
	}
	public void databaseAllTruncate17(MesUserVO mesUserVO) {
		// TODO Auto-generated method stub
		delete("mesUserDAO.databaseAllTruncate17", mesUserVO);
	}
	public void databaseAllTruncate18(MesUserVO mesUserVO) {
		// TODO Auto-generated method stub
		delete("mesUserDAO.databaseAllTruncate18", mesUserVO);
	}
	public void databaseAllTruncate19(MesUserVO mesUserVO) {
		// TODO Auto-generated method stub
		delete("mesUserDAO.databaseAllTruncate19", mesUserVO);
	}
}
