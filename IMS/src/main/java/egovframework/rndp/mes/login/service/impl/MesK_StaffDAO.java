package egovframework.rndp.mes.login.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.rndp.mes.staff.service.MesStaffVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
 

@Repository("mesK_StaffDAO")
public class MesK_StaffDAO extends EgovAbstractDAO {
 
    /**
	 * 직원 을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MesK_StaffDAO
	 * @return 조회한 글
	 * @exception Exception
	 */
    public MesStaffVO selectKStaff_S(MesStaffVO vo) throws Exception {
        return (MesStaffVO) select("mesStaffDAO.selectKStaff_S", vo);
    }
 
}
