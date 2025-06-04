package egovframework.rndp.admin.menu.service;

import java.util.List;

public interface MenuService {
	
	public List menuListOwn() throws Exception;
	public List menuOwnList(AdminmenuVO vo) throws Exception;
	public List menuListTwo(AdminmenuVO vo) throws Exception;
	public List moveMenuList(AdminmenuVO vo) throws Exception;
	public List moveSubMenuList(AdminmenuVO vo) throws Exception;
	
	public void deleteMenu(AdminmenuVO vo) throws Exception;
	public void moveMenuOwn(AdminmenuVO vo) throws Exception;
	public void moveSubMenuOwn(AdminmenuVO vo) throws Exception;
	
	public boolean isLastNode(AdminmenuVO vo) throws Exception;
	
	public List menuLeft(AdminmenuVO vo) throws Exception;
	
	
	
	public MenuBeanVO getMenuMiniInfo(int key) throws Exception;
	public MenuBeanVO getMenuInfo(int key) throws Exception;
	public List menuInfoList(int groupKey) throws Exception;
	public List contentList(MenuBeanVO vo) throws Exception;
	public List inheritId(int key) throws Exception;
	public String inheritName(int menuKey) throws Exception;
	public int menuMaxCount() throws Exception;
	public String category(int ref) throws Exception;
	public String category2(int ref) throws Exception;
	public void menuInsert(MenuBeanVO vo) throws Exception;
	public MenuBeanVO menuInfo(int key) throws Exception;
	public void menuUpdate(MenuBeanVO vo) throws Exception;
	public void menuVisibleUpdat(MenuBeanVO vo) throws Exception;
	public void menuStatisticUpdate(MenuBeanVO vo) throws Exception;
	public List menuOrderList(MenuBeanVO vo) throws Exception;
	public void menuOrderUpdate(MenuBeanVO vo) throws Exception;
	public List menuSubOrderList(MenuBeanVO vo) throws Exception;
	public void menuSubOrderUpdate(MenuBeanVO vo) throws Exception;
	public void menuDelete(MenuBeanVO vo) throws Exception;
	
	public List getAllMenuList() throws Exception;
}
