package egovframework.rndp.mes.staff.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rndp.mes.staff.service.MesStaffVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;


@Repository("mesStaffDAO")
public class MesStaffDAO extends EgovAbstractDAO{
	 
	public List selectStaffListByPos(MesStaffVO vo) throws Exception{
		return list("mesStaffDAO.selectStaffListByPos", vo);
	}
	

}
