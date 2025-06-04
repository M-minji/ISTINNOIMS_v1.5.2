package egovframework.rndp.admin.position.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rndp.admin.position.service.PositionService;
import egovframework.rndp.admin.position.service.PositionVO;
import egovframework.rndp.admin.position.service.StaffVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("positionService")
public class PositionServiceImpl extends EgovAbstractServiceImpl implements PositionService{

	@Resource(name="positionDAO")
	private PositionDAO positionDAO;
	
	@Override
	public List positionList() throws Exception {
		// TODO Auto-generated method stub
		return positionDAO.positionList();
	}

	@Override
	public PositionVO positionInfoByRank(int rank) throws Exception {
		// TODO Auto-generated method stub
		return positionDAO.positionInfoByRank(rank);
	}

	@Override
	public List staffAddList(StaffVO vo) throws Exception {
		// TODO Auto-generated method stub
		return positionDAO.staffAddList(vo);
	}

}
