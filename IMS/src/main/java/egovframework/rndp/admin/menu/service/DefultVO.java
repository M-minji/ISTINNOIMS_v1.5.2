package egovframework.rndp.admin.menu.service;

import java.io.Serializable;

public class DefultVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private int mainGroup;
	private int countNow = 0;
	private int countMax = 0;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMainGroup() {
		return mainGroup;
	}
	public void setMainGroup(int mainGroup) {
		this.mainGroup = mainGroup;
	}
	public int getCountNow() {
		return countNow;
	}
	public void setCountNow(int countNow) {
		this.countNow = countNow;
	}
	public int getCountMax() {
		return countMax;
	}
	public void setCountMax(int countMax) {
		this.countMax = countMax;
	}
	
	
}
