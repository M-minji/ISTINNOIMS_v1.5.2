package egovframework.rndp.admin.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rndp.admin.admin.service.AdminAuthService;
import egovframework.rndp.admin.admin.service.AdminAuthVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("adminAuthService")
public class AdminAuthServiceImpl extends EgovAbstractServiceImpl implements AdminAuthService{
	
	@Resource(name="adminAuthDAO")
	private AdminAuthDAO adminAuthDAO;
	
	@Override
	public List adminMenuAuthList(int key) throws Exception {
		// TODO Auto-generated method stub
		return adminAuthDAO.adminMenuAuthList(key);
	}

	@Override
	public void adminAuthUpdate(AdminAuthVO vo) throws Exception {
		// TODO Auto-generated method stub
		adminAuthDAO.adminAuthInsert(vo);
	}

	@Override
	public void adminAuthDelete(int adminKey) throws Exception {
		// TODO Auto-generated method stub
		adminAuthDAO.adminAuthDelete(adminKey);
	}

	@Override
	public int adminAuthMaxCount() throws Exception {
		// TODO Auto-generated method stub
		return adminAuthDAO.adminAuthMaxCount();
	}

}
