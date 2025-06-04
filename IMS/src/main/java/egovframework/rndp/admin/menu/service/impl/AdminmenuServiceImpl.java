package egovframework.rndp.admin.menu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rndp.admin.menu.service.AdminmenuService;
import egovframework.rndp.admin.menu.service.AdminmenuVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("adminmenuService")
public class AdminmenuServiceImpl extends EgovAbstractServiceImpl implements AdminmenuService{

	@Resource(name="adminmenuDAO")
	private AdminmenuDAO adminmenuDAO;
	
	@Override
	public List adminmenuTopList(AdminmenuVO vo) throws Exception {
		// TODO Auto-generated method stub
		return adminmenuDAO.adminmenuTopList(vo);
	}

	@Override
	public List adminmenuLeftList(int root) throws Exception {
		// TODO Auto-generated method stub
		return adminmenuDAO.adminmenuLeftList(root);
	}

	@Override
	public List adminmenuList() throws Exception {
		// TODO Auto-generated method stub
		return adminmenuDAO.adminmenuList();
	}

	@Override
	public String category(int ref, String name) throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		if(ref != 0){
			AdminmenuVO vo = adminmenuDAO.category(ref);
			result = vo.getName() +" > " + result;
		}else{
			result += name + ">";
		}
		return result;
	}

	@Override
	public void adminmenuInsert(AdminmenuVO vo) throws Exception {
		// TODO Auto-generated method stub
		adminmenuDAO.adminmenuInsert(vo);
	}

	@Override
	public String category2(int ref) throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		if(ref != 0){
			AdminmenuVO vo = adminmenuDAO.category2(ref);
			result = vo.getName() +" > " + result;
		}
		return result;
	}

	@Override
	public AdminmenuVO adminmenuInfo1(int key) throws Exception {
		// TODO Auto-generated method stub
		return adminmenuDAO.adminmenuInfo1(key);
	}

	@Override
	public void adminmenuUpdate(AdminmenuVO vo) throws Exception {
		// TODO Auto-generated method stub
		adminmenuDAO.adminmenuUpdate(vo);
	}

	@Override
	public List adminmenuOrderList(int root) throws Exception {
		// TODO Auto-generated method stub
		return adminmenuDAO.adminmenuOrderList(root);
	}

	@Override
	public void adminmenuOrder(AdminmenuVO vo) throws Exception {
		// TODO Auto-generated method stub
		adminmenuDAO.adminmenuOrder(vo);
	}

	@Override
	public List adminmenuSubOrderList(AdminmenuVO vo) throws Exception {
		// TODO Auto-generated method stub
		return adminmenuDAO.adminmenuSubOrderList(vo);
	}

	@Override
	public void adminmenuSubOrder(AdminmenuVO vo) throws Exception {
		// TODO Auto-generated method stub
		adminmenuDAO.adminmenuSubOrder(vo);
	}

	@Override
	public void adminmenuDelete(int key) throws Exception {
		// TODO Auto-generated method stub
		adminmenuDAO.adminmenuDelete(key);
	}
	
	@Override
	public List adminMenuAllList() throws Exception {
		// TODO Auto-generated method stub
		return adminmenuDAO.adminMenuAllList();
	}

}
