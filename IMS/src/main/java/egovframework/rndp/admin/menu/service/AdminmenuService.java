package egovframework.rndp.admin.menu.service;

import java.util.List;

public interface AdminmenuService {

	public List adminmenuTopList(AdminmenuVO vo) throws Exception;
	public List adminmenuLeftList(int root) throws Exception;
	public List adminmenuList() throws Exception;
	public String category(int ref, String name) throws Exception;
	public String category2(int ref) throws Exception;
	public void adminmenuInsert(AdminmenuVO vo) throws Exception;
	public AdminmenuVO adminmenuInfo1(int key) throws Exception;
	public void adminmenuUpdate(AdminmenuVO vo) throws Exception;
	public List adminmenuOrderList(int root) throws Exception;
	public void adminmenuOrder(AdminmenuVO vo) throws Exception;
	public List adminmenuSubOrderList(AdminmenuVO vo) throws Exception;
	public void adminmenuSubOrder(AdminmenuVO vo) throws Exception;
	public void adminmenuDelete(int key) throws Exception;
	public List adminMenuAllList() throws Exception;
}
