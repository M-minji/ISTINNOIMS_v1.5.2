package egovframework.rndp.admin.intra.staff.service;

import egovframework.rndp.com.cmm.service.DefultVO;

public class StaffIntraVO extends DefultVO{

	private String kStaffKey 			=""; 
	private String kStaffRequestKey 	="";
	private String kStaffNum 			="";
	private String kStaffId 			="";
	private String kStaffName 			="";
	private String kStaffEmail 			="";
	private String kStaffComment 		="";
	private String kStaffStateFlag 		="";
	private String kStaffImagefile 		="";
	private String kStaffPassword 		="";	
	private String kStaffJumin1 		="";	
	private String kStaffJumin2 		="";	
	private String kStaffBirthday 		="";		
	private String kStaffBirthdayFlag 	="";	
	private String kStaffMessenger 		="";	
	private String kStaffPost1 			="";		
	private String kStaffPost2 			="";			
	private String kStaffAddress1 		="";	
	private String kStaffAddress2 		="";	
	private String kStaffTelephone1 	="";	
	private String kStaffTelephone2 	="";	
	private String kStaffTelephone3 	="";	
	private String kStaffMobile1 		="";		
	private String kStaffMobile2 		="";		
	private String kStaffMobile3 		="";		
	private String kStaffHomepage 		="";	
	
	private String kSectorKey 			="";
	private String kSectorRank 			="";
	private String kSectorName 			="";
	
	private String kClassKey 			="";
	private String kClassRank 			="";
	private String kClassName 			="";
	          
	private String kPositionKey 		="";
	private String kPositionRank 		="";
	private String kPositionName 		="";

	private String kStaffSignImage 		="";	
	private String kMailUse 			="";		

	private int startNum 				=0;
	private int endNum					=0;
	private String message				="";
	
	private String kStaffAuthConfirmFlag 		="";		
	private String kStaffAuthWriteFlag 			="";		
	private String kStaffAuthModifyFlag 			="";		
	private String kStaffAuthDelFlag 				="";		
	private String kStaffAuthCloseFlag 				="";	
	
	//2020 08 04 모듈화를 위해 추가 
	//비밀번호 변경
	private String newPassword            =""; 
	private String kParamImage			= "";
	private String kMenuKey			= "";
	private String kMenuRoot			= "";
	private String kLogGubun			= "";
	private String kLogGubunKey			= "";
	private String kLogIp			= "";
	private String eTopStartDate			= "";
	private String eTopEndDate			= "";
	private String ePageGubun			= "";
	private String eLogCnt			= "";
	private String eLogSum			= "";
	private String eLogData			= "";
	private String kMenuName			= "";
	
	
	
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getkParamImage() {
		return kParamImage;
	}
	public void setkParamImage(String kParamImage) {
		this.kParamImage = kParamImage;
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
	public String getkLogGubun() {
		return kLogGubun;
	}
	public void setkLogGubun(String kLogGubun) {
		this.kLogGubun = kLogGubun;
	}
	public String getkLogGubunKey() {
		return kLogGubunKey;
	}
	public void setkLogGubunKey(String kLogGubunKey) {
		this.kLogGubunKey = kLogGubunKey;
	}
	public String getkLogIp() {
		return kLogIp;
	}
	public void setkLogIp(String kLogIp) {
		this.kLogIp = kLogIp;
	}
	public String geteTopStartDate() {
		return eTopStartDate;
	}
	public void seteTopStartDate(String eTopStartDate) {
		this.eTopStartDate = eTopStartDate;
	}
	public String geteTopEndDate() {
		return eTopEndDate;
	}
	public void seteTopEndDate(String eTopEndDate) {
		this.eTopEndDate = eTopEndDate;
	}
	public String getePageGubun() {
		return ePageGubun;
	}
	public void setePageGubun(String ePageGubun) {
		this.ePageGubun = ePageGubun;
	}
	public String geteLogCnt() {
		return eLogCnt;
	}
	public void seteLogCnt(String eLogCnt) {
		this.eLogCnt = eLogCnt;
	}
	public String geteLogSum() {
		return eLogSum;
	}
	public void seteLogSum(String eLogSum) {
		this.eLogSum = eLogSum;
	}
	public String geteLogData() {
		return eLogData;
	}
	public void seteLogData(String eLogData) {
		this.eLogData = eLogData;
	}
	public String getkMenuName() {
		return kMenuName;
	}
	public void setkMenuName(String kMenuName) {
		this.kMenuName = kMenuName;
	}
	public String getkStaffKey() {
		return kStaffKey;
	}
	public void setkStaffKey(String kStaffKey) {
		this.kStaffKey = kStaffKey;
	}
	public String getkStaffRequestKey() {
		return kStaffRequestKey;
	}
	public void setkStaffRequestKey(String kStaffRequestKey) {
		this.kStaffRequestKey = kStaffRequestKey;
	}
	public String getkStaffNum() {
		return kStaffNum;
	}
	public void setkStaffNum(String kStaffNum) {
		this.kStaffNum = kStaffNum;
	}
	public String getkStaffId() {
		return kStaffId;
	}
	public void setkStaffId(String kStaffId) {
		this.kStaffId = kStaffId;
	}
	public String getkStaffName() {
		return kStaffName;
	}
	public void setkStaffName(String kStaffName) {
		this.kStaffName = kStaffName;
	}
	public String getkStaffEmail() {
		return kStaffEmail;
	}
	public void setkStaffEmail(String kStaffEmail) {
		this.kStaffEmail = kStaffEmail;
	}
	public String getkStaffComment() {
		return kStaffComment;
	}
	public void setkStaffComment(String kStaffComment) {
		this.kStaffComment = kStaffComment;
	}
	public String getkStaffStateFlag() {
		return kStaffStateFlag;
	}
	public void setkStaffStateFlag(String kStaffStateFlag) {
		this.kStaffStateFlag = kStaffStateFlag;
	}
	public String getkStaffImagefile() {
		return kStaffImagefile;
	}
	public void setkStaffImagefile(String kStaffImagefile) {
		this.kStaffImagefile = kStaffImagefile;
	}
	public String getkStaffPassword() {
		return kStaffPassword;
	}
	public void setkStaffPassword(String kStaffPassword) {
		this.kStaffPassword = kStaffPassword;
	}
	public String getkStaffJumin1() {
		return kStaffJumin1;
	}
	public void setkStaffJumin1(String kStaffJumin1) {
		this.kStaffJumin1 = kStaffJumin1;
	}
	public String getkStaffJumin2() {
		return kStaffJumin2;
	}
	public void setkStaffJumin2(String kStaffJumin2) {
		this.kStaffJumin2 = kStaffJumin2;
	}
	public String getkStaffBirthday() {
		return kStaffBirthday;
	}
	public void setkStaffBirthday(String kStaffBirthday) {
		this.kStaffBirthday = kStaffBirthday;
	}
	public String getkStaffBirthdayFlag() {
		return kStaffBirthdayFlag;
	}
	public void setkStaffBirthdayFlag(String kStaffBirthdayFlag) {
		this.kStaffBirthdayFlag = kStaffBirthdayFlag;
	}
	public String getkStaffMessenger() {
		return kStaffMessenger;
	}
	public void setkStaffMessenger(String kStaffMessenger) {
		this.kStaffMessenger = kStaffMessenger;
	}
	public String getkStaffPost1() {
		return kStaffPost1;
	}
	public void setkStaffPost1(String kStaffPost1) {
		this.kStaffPost1 = kStaffPost1;
	}
	public String getkStaffPost2() {
		return kStaffPost2;
	}
	public void setkStaffPost2(String kStaffPost2) {
		this.kStaffPost2 = kStaffPost2;
	}
	public String getkStaffAddress1() {
		return kStaffAddress1;
	}
	public void setkStaffAddress1(String kStaffAddress1) {
		this.kStaffAddress1 = kStaffAddress1;
	}
	public String getkStaffAddress2() {
		return kStaffAddress2;
	}
	public void setkStaffAddress2(String kStaffAddress2) {
		this.kStaffAddress2 = kStaffAddress2;
	}
	public String getkStaffTelephone1() {
		return kStaffTelephone1;
	}
	public void setkStaffTelephone1(String kStaffTelephone1) {
		this.kStaffTelephone1 = kStaffTelephone1;
	}
	public String getkStaffTelephone2() {
		return kStaffTelephone2;
	}
	public void setkStaffTelephone2(String kStaffTelephone2) {
		this.kStaffTelephone2 = kStaffTelephone2;
	}
	public String getkStaffTelephone3() {
		return kStaffTelephone3;
	}
	public void setkStaffTelephone3(String kStaffTelephone3) {
		this.kStaffTelephone3 = kStaffTelephone3;
	}
	public String getkStaffMobile1() {
		return kStaffMobile1;
	}
	public void setkStaffMobile1(String kStaffMobile1) {
		this.kStaffMobile1 = kStaffMobile1;
	}
	public String getkStaffMobile2() {
		return kStaffMobile2;
	}
	public void setkStaffMobile2(String kStaffMobile2) {
		this.kStaffMobile2 = kStaffMobile2;
	}
	public String getkStaffMobile3() {
		return kStaffMobile3;
	}
	public void setkStaffMobile3(String kStaffMobile3) {
		this.kStaffMobile3 = kStaffMobile3;
	}
	public String getkStaffHomepage() {
		return kStaffHomepage;
	}
	public void setkStaffHomepage(String kStaffHomepage) {
		this.kStaffHomepage = kStaffHomepage;
	}
	public String getkSectorKey() {
		return kSectorKey;
	}
	public void setkSectorKey(String kSectorKey) {
		this.kSectorKey = kSectorKey;
	}
	public String getkSectorRank() {
		return kSectorRank;
	}
	public void setkSectorRank(String kSectorRank) {
		this.kSectorRank = kSectorRank;
	}
	public String getkSectorName() {
		return kSectorName;
	}
	public void setkSectorName(String kSectorName) {
		this.kSectorName = kSectorName;
	}
	public String getkClassKey() {
		return kClassKey;
	}
	public void setkClassKey(String kClassKey) {
		this.kClassKey = kClassKey;
	}
	public String getkClassRank() {
		return kClassRank;
	}
	public void setkClassRank(String kClassRank) {
		this.kClassRank = kClassRank;
	}
	public String getkClassName() {
		return kClassName;
	}
	public void setkClassName(String kClassName) {
		this.kClassName = kClassName;
	}
	public String getkPositionKey() {
		return kPositionKey;
	}
	public void setkPositionKey(String kPositionKey) {
		this.kPositionKey = kPositionKey;
	}
	public String getkPositionRank() {
		return kPositionRank;
	}
	public void setkPositionRank(String kPositionRank) {
		this.kPositionRank = kPositionRank;
	}
	public String getkPositionName() {
		return kPositionName;
	}
	public void setkPositionName(String kPositionName) {
		this.kPositionName = kPositionName;
	}
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	public int getEndNum() {
		return endNum;
	}
	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}
	public String getkStaffSignImage() {
		return kStaffSignImage;
	}
	public void setkStaffSignImage(String kStaffSignImage) {
		this.kStaffSignImage = kStaffSignImage;
	}
	public String getkMailUse() {
		return kMailUse;
	}
	public void setkMailUse(String kMailUse) {
		this.kMailUse = kMailUse;
	}
	
	public String getCheckBth(String flag)
	{
		if (flag.equals(this.kStaffBirthdayFlag))
		{
			return " checked";
		}
		else
		{
			return "";
		}
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getkStaffAuthConfirmFlag() {
		return kStaffAuthConfirmFlag;
	}
	public void setkStaffAuthConfirmFlag(String kStaffAuthConfirmFlag) {
		this.kStaffAuthConfirmFlag = kStaffAuthConfirmFlag;
	}
	public String getkStaffAuthWriteFlag() {
		return kStaffAuthWriteFlag;
	}
	public void setkStaffAuthWriteFlag(String kStaffAuthWriteFlag) {
		this.kStaffAuthWriteFlag = kStaffAuthWriteFlag;
	}
	public String getkStaffAuthModifyFlag() {
		return kStaffAuthModifyFlag;
	}
	public void setkStaffAuthModifyFlag(String kStaffAuthModifyFlag) {
		this.kStaffAuthModifyFlag = kStaffAuthModifyFlag;
	}
	public String getkStaffAuthDelFlag() {
		return kStaffAuthDelFlag;
	}
	public void setkStaffAuthDelFlag(String kStaffAuthDelFlag) {
		this.kStaffAuthDelFlag = kStaffAuthDelFlag;
	}
	public String getkStaffAuthCloseFlag() {
		return kStaffAuthCloseFlag;
	}
	public void setkStaffAuthCloseFlag(String kStaffAuthCloseFlag) {
		this.kStaffAuthCloseFlag = kStaffAuthCloseFlag;
	}
	
	
	
}
