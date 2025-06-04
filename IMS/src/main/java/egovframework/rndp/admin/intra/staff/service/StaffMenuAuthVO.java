package egovframework.rndp.admin.intra.staff.service;

import egovframework.rndp.com.cmm.service.DefultVO;

public class StaffMenuAuthVO extends DefultVO{

	private String kStaffKey 			=""; 
	private String kStaffName 		=""; 		
	private String kMenuKey			=""; 	
	private String kMenuRoot		=""; 	
	private String kMenuRef			=""; 	
	private String kMenuDepth		=""; 	
	private String kMenuStep		=""; 	
	private String kMenuGroupKey	=""; 	
	private String kMenuName			=""; 	
	private String kMenuAuthKey	=""; 	
	private String kMenuAuthFlag	="F";
	private String kMenuAuthC	="F";
	private String kMenuAuthM	="F";
	private String kMenuAuthD	="F";
	private String kMenuAuthW	="F";
	
	private String screenId = ""; //화면ID(문서작업용)
	private String screenHistory = ""; // 신규,수정,기존
	
	
	
	public String getScreenHistory() {
		return screenHistory;
	}
	public void setScreenHistory(String screenHistory) {
		this.screenHistory = screenHistory;
	}
	public String getScreenId() {
		return screenId;
	}
	public void setScreenId(String screenId) {
		this.screenId = screenId;
	}
	public String getkMenuAuthC() {
		return kMenuAuthC;
	}
	public void setkMenuAuthC(String kMenuAuthC) {
		this.kMenuAuthC = kMenuAuthC;
	}
	public String getkMenuAuthM() {
		return kMenuAuthM;
	}
	public void setkMenuAuthM(String kMenuAuthM) {
		this.kMenuAuthM = kMenuAuthM;
	}
	public String getkMenuAuthD() {
		return kMenuAuthD;
	}
	public void setkMenuAuthD(String kMenuAuthD) {
		this.kMenuAuthD = kMenuAuthD;
	}
	public String getkMenuAuthW() {
		return kMenuAuthW;
	}
	public void setkMenuAuthW(String kMenuAuthW) {
		this.kMenuAuthW = kMenuAuthW;
	}
	public String getkStaffKey() {
		return kStaffKey;
	}
	public void setkStaffKey(String kStaffKey) {
		this.kStaffKey = kStaffKey;
	}
	public String getkStaffName() {
		return kStaffName;
	}
	public void setkStaffName(String kStaffName) {
		this.kStaffName = kStaffName;
	}
	public String getkMenuKey() {
		return kMenuKey;
	}
	public void setkMenuKey(String kMenuKey) {
		this.kMenuKey = kMenuKey;
	}
	public String getkMenuRoot() {
		return kMenuRoot;
	}
	public void setkMenuRoot(String kMenuRoot) {
		this.kMenuRoot = kMenuRoot;
	}
	public String getkMenuRef() {
		return kMenuRef;
	}
	public void setkMenuRef(String kMenuRef) {
		this.kMenuRef = kMenuRef;
	}
	public String getkMenuDepth() {
		return kMenuDepth;
	}
	public void setkMenuDepth(String kMenuDepth) {
		this.kMenuDepth = kMenuDepth;
	}
	public String getkMenuStep() {
		return kMenuStep;
	}
	public void setkMenuStep(String kMenuStep) {
		this.kMenuStep = kMenuStep;
	}
	public String getkMenuGroupKey() {
		return kMenuGroupKey;
	}
	public void setkMenuGroupKey(String kMenuGroupKey) {
		this.kMenuGroupKey = kMenuGroupKey;
	}
	public String getkMenuName() {
		return kMenuName;
	}
	public void setkMenuName(String kMenuName) {
		this.kMenuName = kMenuName;
	}
	public String getkMenuAuthKey() {
		return kMenuAuthKey;
	}
	public void setkMenuAuthKey(String kMenuAuthKey) {
		this.kMenuAuthKey = kMenuAuthKey;
	}
	public String getkMenuAuthFlag() {
		return kMenuAuthFlag;
	}
	public void setkMenuAuthFlag(String kMenuAuthFlag) {
		this.kMenuAuthFlag = kMenuAuthFlag;
	} 	
	
	

	
	
}
