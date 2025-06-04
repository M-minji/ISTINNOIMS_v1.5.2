package egovframework.rndp.admin.env.service;

import java.util.Date;

public class EnvRecruitVO {
	
  	private int key;			
    private String recruit1; 		
	private String recruit2; 		
	private String recruit3; 		
	private String handlingInfo; 
	private String comEmail; 		
	private String comTel; 		
	private String comFax; 		
	private String comAddr; 		
	private String startDate; 		
	private String endDate; 			
	private Date writeDate; 		
	private String fileName; 		
	private String visible;			
	private String eveyFlag;
	
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public String getRecruit1() {
		return recruit1;
	}
	public void setRecruit1(String recruit1) {
		this.recruit1 = recruit1;
	}
	public String getRecruit2() {
		return recruit2;
	}
	public void setRecruit2(String recruit2) {
		this.recruit2 = recruit2;
	}
	public String getRecruit3() {
		return recruit3;
	}
	public void setRecruit3(String recruit3) {
		this.recruit3 = recruit3;
	}
	public String getHandlingInfo() {
		return handlingInfo;
	}
	public void setHandlingInfo(String handlingInfo) {
		this.handlingInfo = handlingInfo;
	}
	public String getComEmail() {
		return comEmail;
	}
	public void setComEmail(String comEmail) {
		this.comEmail = comEmail;
	}
	public String getComTel() {
		return comTel;
	}
	public void setComTel(String comTel) {
		this.comTel = comTel;
	}
	public String getComFax() {
		return comFax;
	}
	public void setComFax(String comFax) {
		this.comFax = comFax;
	}
	public String getComAddr() {
		return comAddr;
	}
	public void setComAddr(String comAddr) {
		this.comAddr = comAddr;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getVisible() {
		return visible;
	}
	public void setVisible(String visible) {
		this.visible = visible;
	}
	public String getEveyFlag() {
		return eveyFlag;
	}
	public void setEveyFlag(String eveyFlag) {
		this.eveyFlag = eveyFlag;
	}

}
