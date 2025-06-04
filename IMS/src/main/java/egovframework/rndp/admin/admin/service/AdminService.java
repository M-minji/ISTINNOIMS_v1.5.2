package egovframework.rndp.admin.admin.service;

import java.util.List;

public interface AdminService {

	public List adminList() throws Exception;
	public void adminInsert(AdminVO vo) throws Exception;
	public AdminVO adminInfo(int key) throws Exception;
	public void adminUpdate(AdminVO vo) throws Exception;
	public void adminUpdatePass(AdminVO vo) throws Exception;
	public int adminCntMenu(int key) throws Exception;
	public void adminDelete(int key) throws Exception;
	public AdminVO adminInfoById(AdminVO vo) throws Exception;
	public int adminIdKey(String adminId) throws Exception;
}
