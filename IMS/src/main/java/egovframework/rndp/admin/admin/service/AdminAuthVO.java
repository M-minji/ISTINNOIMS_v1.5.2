package egovframework.rndp.admin.admin.service;

import egovframework.rndp.admin.menu.service.AdminmenuVO;

public class AdminAuthVO extends AdminmenuVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int key = 0;
	private int adminKey = 0;
	private int menuKey = 0;
	private String menuName;
	private String flag = "F";
	
	private String strMenuKey;           
	
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public int getAdminKey() {
		return adminKey;
	}
	public void setAdminKey(int adminKey) {
		this.adminKey = adminKey;
	}
	public int getMenuKey() {
		return menuKey;
	}
	public void setMenuKey(int menuKey) {
		this.menuKey = menuKey;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getStrMenuKey() {
		return strMenuKey;
	}
	public void setStrMenuKey(String strMenuKey) {
		this.strMenuKey = strMenuKey;
	}
	public String getFlagForm()
	{
		if(flag == null){
			return "권한없음";
		}
		
		
		if (flag.equals("T"))
		{
			return "<font color='blue'>권한있음</font>";
		}
		else
		{
			return "권한없음";
		}
	}
}
