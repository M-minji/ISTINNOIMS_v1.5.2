package egovframework.rndp.admin.admin.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rndp.admin.admin.service.AdminAuthVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("adminAuthDAO")
public class AdminAuthDAO extends EgovAbstractDAO{
	
	public List adminMenuAuthList(int key) throws Exception{
		return list("adminAuthDAO.adminMenuAuthList", key);
	}
	
	public int adminAuthMaxCount() throws Exception{
		return (Integer) select("adminAuthDAO.adminAuthMaxCount");
	}
	
	public void adminAuthInsert(AdminAuthVO vo) throws Exception{
		insert("adminAuthDAO.adminAuthInsert", vo );
	}
	
	public void adminAuthDelete(int adminKey) throws Exception{
		delete("adminAuthDAO.adminAuthDelete", adminKey);
	}

}
