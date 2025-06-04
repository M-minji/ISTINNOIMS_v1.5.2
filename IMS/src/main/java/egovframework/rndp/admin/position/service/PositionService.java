package egovframework.rndp.admin.position.service;

import java.util.List;

public interface PositionService {

	public List positionList() throws Exception;
	public PositionVO positionInfoByRank(int rank) throws Exception;
	public List staffAddList(StaffVO vo) throws Exception;
}
