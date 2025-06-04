package egovframework.rndp.mes.position.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import egovframework.rndp.mes.position.service.MesPositionVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;


@Repository("mesPositionDAO")
public class MesPositionDAO extends EgovAbstractDAO{

	
	public List selectPositionList (MesPositionVO mesPositionVO) throws Exception{
		return list("mesPositionDAO.selectPositionList", mesPositionVO);
	}

	public List selectUpdatePositionList(MesPositionVO mesPositionVO) {
		// TODO Auto-generated method stub
		return list("mesPositionDAO.selectUpdatePositionList", mesPositionVO);
	}
	
	public String getMaxPositionKey(MesPositionVO mesPositionVO) {
		// TODO Auto-generated method stub
		return (String) select("mesPositionDAO.getMaxPositionKey", mesPositionVO);
	}

	public String getPositionPath(MesPositionVO mesPositionVO) {
		// TODO Auto-generated method stub
		return (String) select("mesPositionDAO.getPositionPath", mesPositionVO);
	}

	public void deleteTPositionAjax(MesPositionVO mesPositionVO) {
		// TODO Auto-generated method stub
		delete("mesPositionDAO.deleteTPositionAjax", mesPositionVO);
	}

	public void savePositionAjax(MesPositionVO mesPositionVO) {
		// TODO Auto-generated method stub
		insert("mesPositionDAO.savePositionAjax", mesPositionVO);
	}

	public void deletePositionAjax(MesPositionVO mesPositionVO) {
		// TODO Auto-generated method stub
		delete("mesPositionDAO.deletePositionAjax", mesPositionVO);
	}

	public void deletePositionHighAjax(MesPositionVO mesPositionVO) {
		// TODO Auto-generated method stub
		delete("mesPositionDAO.deletePositionHighAjax", mesPositionVO);
	}
}
