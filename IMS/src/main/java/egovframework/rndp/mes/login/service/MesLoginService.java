package egovframework.rndp.mes.login.service;

import java.util.List;

import egovframework.rndp.admin.menu.service.MenuBeanVO;
import egovframework.rndp.mes.staff.service.MesStaffVO;


public interface MesLoginService {
	
	public MesK_StaffVo selectMesLoginStaff(MesK_StaffVo vo) throws Exception;
	
	public void mesLogInsert(MesK_StaffVo vo) throws Exception;
	
	public List inWorkerLog(MesK_StaffVo vo) throws Exception;

	public List inLogWorkerIdList(MesK_StaffVo staffVo) throws Exception;

	public List inLogWorkerMenuList(MesK_StaffVo staffVo)  throws Exception;

	public List inLogWorkerIdTextList(MesK_StaffVo staffVo) throws Exception;

	/**
	 * 본래 intra 에서 사용하였으나 모듈화로 mes 로 이동 
	 * 로그인시 직원을 조회한다.
	 * @param MesMesK_StaffVo - 직원  VO
	 * @return MesK_StaffVo - 직원  VO
	 */
	MesStaffVO selectKStaff_S(MesStaffVO Vo) throws Exception;
	
	public MesStaffVO selectShopMyPageInfo(MesStaffVO Vo) throws Exception;
	
	public void updateUserInfoPwd(MesStaffVO Vo) throws Exception;
	
	public List selectKClassNameList() throws Exception; 

	public List selectKSectorList() throws Exception;
	
	public List selectKPositionList() throws Exception;
	
	public int selectCheckId(String id) throws Exception;
	
	public void checkStaffInsert(MesStaffVO Vo)throws Exception;


    MesStaffVO selectMainCountInfo(MesStaffVO vo) throws Exception;

	public void updateMesLoginStaff(MesK_StaffVo vo) throws Exception;

	public int selectMesLoginCount(MesK_StaffVo mesK_StaffVo) throws Exception;

	public void updateMesLoginStaff2(MesK_StaffVo vo) throws Exception;

	public MenuBeanVO selectMesMenuAuth(MenuBeanVO menuBeanVO);

	public List eALLofMenuList() throws Exception;

	public List eMenuLogDataList(MesK_StaffVo staffVo)throws Exception;

	public List eMenuLogStaffSum(MesK_StaffVo staffVo) throws Exception;

	public List eALLofMenuAndSumList(MesK_StaffVo staffVo) throws Exception;

}
