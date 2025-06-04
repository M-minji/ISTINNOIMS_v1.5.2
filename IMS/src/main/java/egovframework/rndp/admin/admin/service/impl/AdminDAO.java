package egovframework.rndp.admin.admin.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rndp.admin.admin.service.AdminVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("adminDAO")
public class AdminDAO extends EgovAbstractDAO{

	public List adminList() throws Exception{
		return list("adminDAO.adminList", null);
	}
	
	public void adminInsert(AdminVO vo) throws Exception{
		insert("adminDAO.adminInsert", vo );
	}
	
	public int adminMaxCount() throws Exception{
		return (Integer)getSqlMapClientTemplate().queryForObject("adminDAO.adminMaxCount", null);
	}
	
	public AdminVO adminInfo(int key) throws Exception{
		return (AdminVO)selectByPk("adminDAO.adminInfo", key);
	}
	
	public void adminUpdate(AdminVO vo) throws Exception{
		update("adminDAO.adminUpdate", vo);
	}
	
	public void adminUpdatePass(AdminVO vo) throws Exception{
		update("adminDAO.adminUpdatePass", vo);
	}
	
	public int adminCntMenu(int key) throws Exception{
		return (Integer)getSqlMapClientTemplate().queryForObject("adminDAO.adminCntMenu", key);
	}
	
	public void adminDelete(int key) throws Exception{
		delete("adminDAO.adminDelete", key);
	}
	
	public AdminVO adminInfoById(AdminVO vo) throws Exception{
		return (AdminVO)selectByPk("adminDAO.adminInfoById", vo);
	}
	
	public int adminIdKey(String adminId) throws Exception{
		return (Integer)getSqlMapClientTemplate().queryForObject("adminDAO.adminIdKey", adminId);
	}
}
