package egovframework.rndp.mes.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import egovframework.rndp.admin.intra.staff.service.StaffIntraVO;
import egovframework.rndp.admin.intra.staff.service.StaffMenuAuthVO;


public interface MesUserService {

	public List selectUserffList(MesUserVO mesUserVO)  throws Exception;

	public int selectUserCount(MesUserVO mesUserVO)  throws Exception;

	public List mesUserMenuList(MesUserVO mesUserVO)  throws Exception;

	public List selectSectorList() throws Exception;

	public List selectClassList() throws Exception;
 

	public MesUserVO selectUserInfo(MesUserVO mesUserVO) throws Exception;

	public int selectMaxRank() throws Exception;

	public MesUserVO selectpositionInfo(MesUserVO mesUserVO)throws Exception;
	
	public List selectOfficerItemList(MesUserVO mesUserVO)   throws Exception;
	
	public List selectIclassList(MesUserVO mesUserVO) throws Exception;

	public List envList() throws Exception;

	public void updateEnv(MesUserVO vo) throws Exception;

	public void mesUserLevelInsert(MesUserVO mesUserVO) throws Exception;

	public MesUserVO selectIclassInfo(MesUserVO mesUserVO) throws Exception;

	public void mesUserLevelUpdate(MesUserVO mesUserVO) throws Exception;

	public void mesUserLevelDelete(MesUserVO mesUserVO) throws Exception;

	public void deleteUser(MesUserVO mesUserVO) throws Exception;

	public void mesUserPositionInsert(MesUserVO mesUserVO) throws Exception;

	public void mesUserPositionUpdate(MesUserVO mesUserVO) throws Exception;

	public void mesUserPositionDelete(MesUserVO mesUserVO) throws Exception;

	public List selectUseReuestList(MesUserVO mesUserVO)  throws Exception;

	public int selectUseReuestListCount()  throws Exception;

	public void mesUserRegDelete(MesUserVO mesUserVO) throws Exception;

	public MesUserVO mesUserRegInfo(MesUserVO mesUserVO)  throws Exception;

	public void mesUserRegApprovalInsert(MesUserVO mesUserVO) throws Exception;

	public int mesCheckId(String mesUserId) throws Exception;

	public void mesUserInsert(MesUserVO mesUserVO) throws Exception;

	public void mesUserInfoUpdate(MesUserVO mesUserVO) throws Exception;

	public void deleteUserMenuAuth(MesUserVO mesUserVO) throws Exception;

	public int mesUserMenuAuthMaxCnt() throws Exception;

	public void mesUserMenuAuthUpdate(MesUserVO vo) throws Exception;

	public void insertOfficer(MesUserVO mesUserVO) throws Exception;



	public void updateUserInfoPwd(MesUserVO mesUserVO) throws Exception;
	
	public void insertMenual(HttpServletRequest request, MesUserVO mesUservo) throws Exception;


	public void updateSetCount(MesUserVO mesUserVO);

	public void updateSetPass(MesUserVO mesUserVO);

	public void mesUserInfoUpdateNew(MesUserVO mesUserVO) throws Exception;

	public void databaseAllTruncate(MesUserVO mesUserVO);

	public List selectUserffPopList(MesUserVO mesUserVO) throws Exception;

	public int selectUserPopCount(MesUserVO mesUserVO) throws Exception;


}
