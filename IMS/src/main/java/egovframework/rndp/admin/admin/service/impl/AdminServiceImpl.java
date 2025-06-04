package egovframework.rndp.admin.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rndp.admin.admin.service.AdminService;
import egovframework.rndp.admin.admin.service.AdminVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("adminService")
public class AdminServiceImpl extends EgovAbstractServiceImpl implements AdminService{

	@Resource(name="adminDAO")
	private AdminDAO adminDAO;
	
	@Override
	public List adminList() throws Exception {
		// TODO Auto-generated method stub
		return adminDAO.adminList();
	}

	@Override
	public void adminInsert(AdminVO vo) throws Exception {
		// TODO Auto-generated method stub
		int max = adminDAO.adminMaxCount();
		vo.setKey(max);
		adminDAO.adminInsert(vo);
	}

	@Override
	public AdminVO adminInfo(int key) throws Exception {
		// TODO Auto-generated method stub
		return adminDAO.adminInfo(key);
	}

	@Override
	public void adminUpdate(AdminVO vo) throws Exception {
		// TODO Auto-generated method stub
		adminDAO.adminUpdate(vo);
	}

	@Override
	public void adminUpdatePass(AdminVO vo) throws Exception {
		// TODO Auto-generated method stub
		adminDAO.adminUpdatePass(vo);
	}

	@Override
	public int adminCntMenu(int key) throws Exception {
		// TODO Auto-generated method stub
		return adminDAO.adminCntMenu(key);
	}

	@Override
	public void adminDelete(int key) throws Exception {
		// TODO Auto-generated method stub
		adminDAO.adminDelete(key);
	}

	@Override
	public AdminVO adminInfoById(AdminVO vo) throws Exception {
		// TODO Auto-generated method stub
		return adminDAO.adminInfoById(vo);
	}

	@Override
	public int adminIdKey(String adminId) throws Exception {
		// TODO Auto-generated method stub
		return adminDAO.adminIdKey(adminId);
	}

}
