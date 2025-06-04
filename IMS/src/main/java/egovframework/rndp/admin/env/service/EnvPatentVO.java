package egovframework.rndp.admin.env.service;

import egovframework.rndp.admin.menu.service.DefultVO;

public class EnvPatentVO extends DefultVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int key = 0;
	private int rank = 0;
	private String title = "";
	private String number = "";
	private String date = "";
	private String nb = "";
	private int rank2 = 0;
	
	private int strRank1= 0;
	private int strRank2 = 0;
	
	
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNb() {
		return nb;
	}
	public void setNb(String nb) {
		this.nb = nb;
	}
	public int getRank2() {
		return rank2;
	}
	public void setRank2(int rank2) {
		this.rank2 = rank2;
	}
	public int getStrRank1() {
		return strRank1;
	}
	public void setStrRank1(int strRank1) {
		this.strRank1 = strRank1;
	}
	public int getStrRank2() {
		return strRank2;
	}
	public void setStrRank2(int strRank2) {
		this.strRank2 = strRank2;
	}
	
	
}
