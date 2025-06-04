package egovframework.rndp.mes.login.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rndp.admin.menu.service.MenuBeanVO;
import egovframework.rndp.mes.login.service.MesK_StaffVo;
import egovframework.rndp.mes.staff.service.MesStaffVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;


@Repository("mesLoginDAO")
public class MesLoginDAO extends EgovAbstractDAO{
	
	public MesK_StaffVo selectMesLoginStaff(MesK_StaffVo vo) throws Exception {
        return (MesK_StaffVo) select("mesLoginDAO.selectMesLoginStaff", vo);
    }
	
	public void mesLogInsert(MesK_StaffVo vo) throws Exception{
		insert("mesLoginDAO.mesLogInsert", vo);
	}

	public List inWorkerLog(MesK_StaffVo vo) throws Exception{
		// TODO Auto-generated method stub
		return list("mesLoginDAO.inLogWorkerIdList", vo);
	}

	public List inLogWorkerIdList(MesK_StaffVo vo)  throws Exception{
		// TODO Auto-generated method stub
		return list("mesLoginDAO.inLogWorkerIdList", vo);
	}

	public List inLogWorkerMenuList(MesK_StaffVo vo) throws Exception{
		// TODO Auto-generated method stub
		return list("mesLoginDAO.inLogWorkerMenuList", vo);
	}

	public List inLogWorkerIdTextList(MesK_StaffVo vo)  throws Exception{
		// TODO Auto-generated method stub
		return list("mesLoginDAO.inLogWorkerIdTextList", vo);
	}

	
	/**
	 * 2020 07 27 모듈화 작업으로 인해 intra 에서 mes 로 이전
	 * (IntraLoginVo 에서 MesK_StaffVo 로 변경)
	 * */
	public List selectKClassNameList() throws Exception{
		return list("mesLoginDAO.selectKClassNameList");
	}
	
	public List selectKSectorList() throws Exception{
		return list("mesLoginDAO.selectKSectorList");
	}
	
	public List selectKPositionList() throws Exception{
		return list("mesLoginDAO.selectKPositionList");
	}
	
	public int selectCheckId(String id) throws Exception{
		return (Integer) select("mesLoginDAO.selectCheckId", id);
	}
	
	public void checkStaffInsert(MesStaffVO Vo)throws Exception{
		insert("mesStaffDAO.checkStaffInsert", Vo);
	}
	
	public void updateUserInfoPwd(MesStaffVO Vo) throws Exception{
		update("mesStaffDAO.updateUserInfoPwd",Vo);
	}
	
	public MesStaffVO selectShopMyPageInfo(MesStaffVO Vo) throws Exception{
		return (MesStaffVO) select("mesStaffDAO.selectShopMyPageInfo",Vo);
	}



	public MesStaffVO selectMainCountInfo (MesStaffVO vo) throws Exception{
		return (MesStaffVO) select("mesLoginDAO.selectMainCountInfo", vo);
	}

	public void updateMesLoginStaff(MesK_StaffVo mesK_StaffVo) throws Exception{
		update("mesLoginDAO.updateMesLoginStaff",mesK_StaffVo);
	}

	public int selectMesLoginCount(MesK_StaffVo mesK_StaffVo) throws Exception{
		 return (int) select("mesLoginDAO.selectMesLoginCount", mesK_StaffVo);
	}

	public void updateMesLoginStaff2(MesK_StaffVo mesK_StaffVo) throws Exception{
		update("mesLoginDAO.updateMesLoginStaff2",mesK_StaffVo);
	}

	public MenuBeanVO selectMesMenuAuth(MenuBeanVO menuBeanVO) {
		// TODO Auto-generated method stub
		return (MenuBeanVO) select("mesStaffDAO.selectMesMenuAuth", menuBeanVO);
	}

	public List eALLofMenuList() throws Exception{
		return list("mesLoginDAO.eALLofMenuList");
	}

	public List eMenuLogDataList(MesK_StaffVo staffVo) throws Exception{
		return list("mesLoginDAO.eMenuLogDataList",staffVo);
	}

	public List eMenuLogStaffSum(MesK_StaffVo staffVo) throws Exception{
		return list("mesLoginDAO.eMenuLogStaffSum",staffVo);
		
	}
	public List eALLofMenuAndSumList(MesK_StaffVo staffVo) throws Exception{
		return list("mesLoginDAO.eALLofMenuAndSumList",staffVo);
		
	}
}
