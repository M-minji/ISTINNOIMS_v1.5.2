package egovframework.rndp.admin.admin.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface AdminAuthService {

	public List adminMenuAuthList(int key) throws Exception;
	
	public int adminAuthMaxCount() throws Exception;
	
	public void adminAuthUpdate(AdminAuthVO vo) throws Exception;
	
	public void adminAuthDelete(int adminKey) throws Exception;
}
