package egovframework.rndp.mes.login.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.rndp.admin.menu.service.MenuBeanVO;
import egovframework.rndp.mes.common.service.impl.MesCommonDAO;
import egovframework.rndp.mes.login.service.MesK_StaffVo;
import egovframework.rndp.mes.login.service.MesLoginService;
import egovframework.rndp.mes.staff.service.MesStaffVO;


@Service("mesLoginService")
public class MesLoginServiceImpl implements MesLoginService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MesLoginServiceImpl.class);

	/*공통사용 DAO common 및  Egov 공용 */
	@Resource(name = "mesCommonDAO")
	private MesCommonDAO mesCommonDAO; 
	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;
	/*공통사용 DAO common 및  Egov 공용 */ 
	
	@Resource(name = "mesLoginDAO")
	private MesLoginDAO mesLoginDAO;

	@Resource(name = "mesK_StaffDAO")
	private MesK_StaffDAO mesK_StaffDAO;
	
	@Override
	public MesK_StaffVo selectMesLoginStaff(MesK_StaffVo vo) throws Exception {
		return mesLoginDAO.selectMesLoginStaff(vo);
	}
	
	@Override
	public void mesLogInsert(MesK_StaffVo vo) throws Exception{
		
		mesLoginDAO.mesLogInsert(vo);
		
	}
	
	@Override
	public List inWorkerLog(MesK_StaffVo vo) throws Exception {
		// TODO Auto-generated method stub
		return mesLoginDAO.inWorkerLog(vo);
	}


	@Override
	public List inLogWorkerIdList(MesK_StaffVo staffVo) throws Exception {
		// TODO Auto-generated method stub
		return mesLoginDAO.inLogWorkerIdList(staffVo);
	}


	@Override
	public List inLogWorkerMenuList(MesK_StaffVo staffVo) throws Exception {
		// TODO Auto-generated method stub
		return mesLoginDAO.inLogWorkerMenuList(staffVo);
	}


	@Override
	public List inLogWorkerIdTextList(MesK_StaffVo staffVo) throws Exception {
		// TODO Auto-generated method stub
		return mesLoginDAO.inLogWorkerIdTextList(staffVo);
	}


	
	/**
	 * 2020 07 27 모둘화로 인해 intra에서  mes로 이전 
	 * 로그인시 직원을 조회한다.
	 * @param MesMesK_StaffVo - 직원  VO
	 * @return MesK_StaffVo - 직원  VO
	 */
	@Override
	public MesStaffVO selectKStaff_S(MesStaffVO Vo) throws Exception {
		LOGGER.debug(Vo.toString()); 
		MesStaffVO staff = new MesStaffVO();
		staff = mesK_StaffDAO.selectKStaff_S(Vo);
		LOGGER.debug(Vo.toString());
		return staff;
	}

	/**
	 * 2020 07 27 모둘화로 인해 intra에서  mes로 이전 
	 * 로그인시 직원을 조회한다. 
	 * mesLoginVo
	 * 
	 */
	@Override
	public List selectKClassNameList() throws Exception {
		// TODO Auto-generated method stub
		return mesLoginDAO.selectKClassNameList();
	}
	
	public List selectKSectorList() throws Exception {
		// TODO Auto-generated method stub
		return mesLoginDAO.selectKSectorList();
	}
	
	public List selectKPositionList() throws Exception {
		// TODO Auto-generated method stub
		return mesLoginDAO.selectKPositionList();
	}
	
	public int selectCheckId(String id) throws Exception {
		// TODO Auto-generated method stub
		return mesLoginDAO.selectCheckId(id);
	}

	public void checkStaffInsert(MesStaffVO Vo)throws Exception {
		mesLoginDAO.checkStaffInsert(Vo);
	}
	
	@Override
	public void updateUserInfoPwd(MesStaffVO Vo) throws Exception {
		mesLoginDAO.updateUserInfoPwd(Vo);
	}
	
	@Override
	public MesStaffVO selectShopMyPageInfo(MesStaffVO Vo) throws Exception {
		return mesLoginDAO.selectShopMyPageInfo(Vo);
	}
	@Override
	public MesStaffVO selectMainCountInfo(MesStaffVO vo) throws Exception {
		return mesLoginDAO.selectMainCountInfo(vo);
	}

	@Override
	public void updateMesLoginStaff(MesK_StaffVo mesK_StaffVo) throws Exception{
		mesLoginDAO.updateMesLoginStaff(mesK_StaffVo);
	}
	
	@Override
	public void updateMesLoginStaff2(MesK_StaffVo mesK_StaffVo) throws Exception{
		mesLoginDAO.updateMesLoginStaff2(mesK_StaffVo);
	}
	
	@Override
	public int selectMesLoginCount(MesK_StaffVo mesK_StaffVo) throws Exception{
		return mesLoginDAO.selectMesLoginCount(mesK_StaffVo);
	}

	@Override
	public MenuBeanVO selectMesMenuAuth(MenuBeanVO menuBeanVO) {
		// TODO Auto-generated method stub
		return mesLoginDAO.selectMesMenuAuth(menuBeanVO);
	}

	@Override
	public List eALLofMenuList() throws Exception {
		// TODO Auto-generated method stub
		return mesLoginDAO.eALLofMenuList();
	}

	@Override
	public List eMenuLogDataList(MesK_StaffVo staffVo) throws Exception {
		// TODO Auto-generated method stub
		return mesLoginDAO.eMenuLogDataList(staffVo);
	}

	@Override
	public List eMenuLogStaffSum(MesK_StaffVo staffVo) throws Exception {
		// TODO Auto-generated method stub
		return mesLoginDAO.eMenuLogStaffSum(staffVo);
	}

	@Override
	public List eALLofMenuAndSumList(MesK_StaffVo staffVo) throws Exception {
		// TODO Auto-generated method stub
		return mesLoginDAO.eALLofMenuAndSumList(staffVo);
	}

}
