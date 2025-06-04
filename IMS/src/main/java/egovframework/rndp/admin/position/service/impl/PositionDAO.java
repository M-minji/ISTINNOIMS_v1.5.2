package egovframework.rndp.admin.position.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rndp.admin.position.service.PositionVO;
import egovframework.rndp.admin.position.service.StaffVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("positionDAO")
public class PositionDAO extends EgovAbstractDAO{

	public List positionList() throws Exception{
		return list("positionDAO.positionList", null);
	}
	
	public PositionVO positionInfoByRank(int rank) throws Exception{
		return (PositionVO)selectByPk("positionDAO.positionInfoByRank", rank);
	}
	
	public List staffAddList(StaffVO vo) throws Exception{
		return list("staffDAO.staffAddList", vo);
	}
}
