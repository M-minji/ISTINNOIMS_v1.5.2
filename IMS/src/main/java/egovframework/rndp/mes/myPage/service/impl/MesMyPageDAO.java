package egovframework.rndp.mes.myPage.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.rndp.mes.myPage.service.MesMyPageVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("mesMyPageDAO")
public class MesMyPageDAO extends EgovAbstractDAO{
	
	public MesMyPageVO selectMyPageInfo(MesMyPageVO vo) throws Exception{
		return (MesMyPageVO) select("mesMyPageDAO.selectMyPageInfo", vo);
	}
	
	public void updateMyPageInfo(MesMyPageVO vo) throws Exception{
		update("mesMyPageDAO.updateMyPageInfo", vo);
	}

}
