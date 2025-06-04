package egovframework.rndp.admin.position.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rndp.admin.position.service.StaffService;
import egovframework.rndp.admin.position.service.StaffVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("staffService")
public class StaffServiceImpl extends EgovAbstractServiceImpl implements StaffService{
	
	@Resource(name="staffDAO")
	private StaffDAO staffDAO;
	@Override
	public List staffListByPos(int positionKey) throws Exception {
		// TODO Auto-generated method stub
		return staffDAO.staffListByPos(positionKey);
	}
	@Override
	public StaffVO staffInfoForView(int staffKey) throws Exception {
		// TODO Auto-generated method stub
		return staffDAO.staffInfoForView(staffKey);
	}

}
