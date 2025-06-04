package egovframework.rndp.mes.user.service;

import egovframework.rndp.com.cmm.service.DefultVO;

public class MesUserVO extends DefultVO{
	
	private String searchType						= "";
	private String searchWord						= "";
	private String eItemCateName						= "";
	

	private String mesUserKey 			=""; 
	private String mesUserRequestKey 	="";
	private String mesUserNum 			="";
	private String mesUserId 			="";
	private String mesUserName 			="";
	private String mesUserEmail 			="";
	private String mesUserComment 		="";
	private String mesUserStateFlag 		="";
	private String mesUserImagefile 		="";
	private String mesUserPassword 		="";	
	private String mesUserPassword1 		="";	
	private String mesUserPassword2		="";	
	private String mesUserJumin1 		="";	
	private String mesUserJumin2 		="";	
	private String mesUserBirthday 		="";		
	private String mesUserBirthdayFlag 	="";	
	private String mesUserMessenger 		="";	
	private String mesUserPost1 			="";		
	private String mesUserPost2 			="";			
	private String mesUserAddress1 		="";	
	private String mesUserAddress2 		="";	
	private String mesUserTelephone1 	="";	
	private String mesUserTelephone2 	="";	
	private String mesUserTelephone3 	="";	
	private String mesUserMobile1 		="";		
	private String mesUserMobile2 		="";		
	private String mesUserMobile3 		="";		
	private String mesUserHomepage 		="";	
	
	private String kSectorKey 			="";
	private String kSectorRank 			="";
	private String kSectorName 			="";
	
	private String kClassKey 			="";
	private String kClassRank 			="";
	private String kClassName 			="";
	private String sItemCateName 			="";
	private String eItemName 			="";
	private String eItemKey 			="";
	          
	private String kPositionKey 		="";
	private String kPositionRank 		="";
	private String kPositionName 		="";
	private String mesUserBirthdayN 		="";
	private String mesUserBirthdayFlagGubun 		="";
	private String useCnt 		="";

	private String mesUserSignImage 		="";	
	private String kMailUse 			="";		

	private String startNum 				="0";
	private String endNum					="0";
	private String envVal					="0";
	private String envName					="0";
	private String message				="";
	
	private String mesMenuKey				="";
	private String mesMenuRoot				="";
	private String mesMenuRef				="";
	private String mesMenuDepth				="";
	private String mesMenuStep				="";
	private String mesMenuGroupKey				="";
	private String mesMenuName				="";
	private String mesMenuAuthKey				="";
	private String mesMenuAuthFlag				="";
	private String mesMenuAuthC			="";
	private String mesMenuAuthM				="";
	private String mesMenuAuthD				="";
	private String mesMenuAuthW				="";
	private String mesMemberKey				="";
	private String mesMemberName				="";
	
	private String mesUserLevelRank				="";
	private String mesUserLevelSubject				="";
	private String allSelect				="";
	
	
	
	private String mesUserAuthConfirmFlag="F";
	private String mesUserAuthWriteFlag="F";
	private String mesUserAuthModifyFlag="F";
	private String mesUserAuthDelFlag="F";
	private String mesUserAuthCloseFlag="";
	private String Num1="";
	private String kStaffItemGubun="";
	
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
	private String program	="";
	private String eOfficerKey	="";
	private String eAllChk	="";
	
	private String atchFileId 		= ""; // 메뉴얼 파일ID
	private String orignName 			= ""; // 메뉴얼 파일명
	private String fileSize				= ""; // 메뉴얼 파일 크기
	private String uploadDate			= ""; // 업로드일자
	private String gbnCnt 				= ""; // 파일 업로그 개수
	

	
	

	private String mesMemberPassword	="";//현재 비밀번호
	private String chPassword			=""; //변경 비밀번호
	private String chPassword2			=""; //변경 비밀번호 확인
	private String eChPassword2			=""; //변경 비밀번호 확인
	
	private String count			=""; 
	
	public String geteChPassword2() {
		return eChPassword2;
	}
	public void seteChPassword2(String eChPassword2) {
		this.eChPassword2 = eChPassword2;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getGbnCnt() {
		return gbnCnt;
	}
	public void setGbnCnt(String gbnCnt) {
		this.gbnCnt = gbnCnt;
	}
	
	public String getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getOrignName() {
		return orignName;
	}
	public void setOrignName(String orignName) {
		this.orignName = orignName;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}


	private String mesUserMaster = ""; 

	public String getMesMemberPassword() {
		return mesMemberPassword;
	}
	public void setMesMemberPassword(String mesMemberPassword) {
		this.mesMemberPassword = mesMemberPassword;
	}
	public String getChPassword() {
		return chPassword;
	}
	public void setChPassword(String chPassword) {
		this.chPassword = chPassword;
	}
	public String getChPassword2() {
		return chPassword2;
	}
	public void setChPassword2(String chPassword2) {
		this.chPassword2 = chPassword2;
	}
	public String geteAllChk() {
		return eAllChk;
	}
	public void seteAllChk(String eAllChk) {
		this.eAllChk = eAllChk;
	}
	public String getkStaffItemGubun() {
		return kStaffItemGubun;
	}
	public void setkStaffItemGubun(String kStaffItemGubun) {
		this.kStaffItemGubun = kStaffItemGubun;
	}
	public String geteOfficerKey() {
		return eOfficerKey;
	}
	public void seteOfficerKey(String eOfficerKey) {
		this.eOfficerKey = eOfficerKey;
	}
	public String geteItemCateName() {
		return eItemCateName;
	}
	public void seteItemCateName(String eItemCateName) {
		this.eItemCateName = eItemCateName;
	}
	public String getsItemCateName() {
		return sItemCateName;
	}
	public void setsItemCateName(String sItemCateName) {
		this.sItemCateName = sItemCateName;
	}
	public String geteItemName() {
		return eItemName;
	}
	public void seteItemName(String eItemName) {
		this.eItemName = eItemName;
	}
	public String geteItemKey() {
		return eItemKey;
	}
	public void seteItemKey(String eItemKey) {
		this.eItemKey = eItemKey;
	}
	public String getAllSelect() {
		return allSelect;
	}
	public void setAllSelect(String allSelect) {
		this.allSelect = allSelect;
	}
	public String getMesMenuAuthC() {
		return mesMenuAuthC;
	}
	public void setMesMenuAuthC(String mesMenuAuthC) {
		this.mesMenuAuthC = mesMenuAuthC;
	}
	public String getMesMenuAuthM() {
		return mesMenuAuthM;
	}
	public void setMesMenuAuthM(String mesMenuAuthM) {
		this.mesMenuAuthM = mesMenuAuthM;
	}
	public String getMesMenuAuthD() {
		return mesMenuAuthD;
	}
	public void setMesMenuAuthD(String mesMenuAuthD) {
		this.mesMenuAuthD = mesMenuAuthD;
	}
	public String getMesMenuAuthW() {
		return mesMenuAuthW;
	}
	public void setMesMenuAuthW(String mesMenuAuthW) {
		this.mesMenuAuthW = mesMenuAuthW;
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
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getMesUserPassword1() {
		return mesUserPassword1;
	}
	public void setMesUserPassword1(String mesUserPassword1) {
		this.mesUserPassword1 = mesUserPassword1;
	}
	public String getMesUserPassword2() {
		return mesUserPassword2;
	}
	public void setMesUserPassword2(String mesUserPassword2) {
		this.mesUserPassword2 = mesUserPassword2;
	}
	public String getMesUserLevelRank() {
		return mesUserLevelRank;
	}
	public void setMesUserLevelRank(String mesUserLevelRank) {
		this.mesUserLevelRank = mesUserLevelRank;
	}
	public String getMesUserLevelSubject() {
		return mesUserLevelSubject;
	}
	public void setMesUserLevelSubject(String mesUserLevelSubject) {
		this.mesUserLevelSubject = mesUserLevelSubject;
	}
	public String getEnvVal() {
		return envVal;
	}
	public void setEnvVal(String envVal) {
		this.envVal = envVal;
	}
	public String getEnvName() {
		return envName;
	}
	public void setEnvName(String envName) {
		this.envName = envName;
	}
	public String getMesUserAuthConfirmFlag() {
		return mesUserAuthConfirmFlag;
	}
	public void setMesUserAuthConfirmFlag(String mesUserAuthConfirmFlag) {
		this.mesUserAuthConfirmFlag = mesUserAuthConfirmFlag;
	}
	public String getMesUserAuthWriteFlag() {
		return mesUserAuthWriteFlag;
	}
	public void setMesUserAuthWriteFlag(String mesUserAuthWriteFlag) {
		this.mesUserAuthWriteFlag = mesUserAuthWriteFlag;
	}
	public String getMesUserAuthModifyFlag() {
		return mesUserAuthModifyFlag;
	}
	public void setMesUserAuthModifyFlag(String mesUserAuthModifyFlag) {
		this.mesUserAuthModifyFlag = mesUserAuthModifyFlag;
	}
	public String getMesUserAuthDelFlag() {
		return mesUserAuthDelFlag;
	}
	public void setMesUserAuthDelFlag(String mesUserAuthDelFlag) {
		this.mesUserAuthDelFlag = mesUserAuthDelFlag;
	}
	public String getMesUserAuthCloseFlag() {
		return mesUserAuthCloseFlag;
	}
	public void setMesUserAuthCloseFlag(String mesUserAuthCloseFlag) {
		this.mesUserAuthCloseFlag = mesUserAuthCloseFlag;
	}
	public String getMesMenuKey() {
		return mesMenuKey;
	}
	public void setMesMenuKey(String mesMenuKey) {
		this.mesMenuKey = mesMenuKey;
	}
	public String getMesMenuRoot() {
		return mesMenuRoot;
	}
	public void setMesMenuRoot(String mesMenuRoot) {
		this.mesMenuRoot = mesMenuRoot;
	}
	public String getMesMenuRef() {
		return mesMenuRef;
	}
	public void setMesMenuRef(String mesMenuRef) {
		this.mesMenuRef = mesMenuRef;
	}
	public String getMesMenuDepth() {
		return mesMenuDepth;
	}
	public void setMesMenuDepth(String mesMenuDepth) {
		this.mesMenuDepth = mesMenuDepth;
	}
	public String getMesMenuStep() {
		return mesMenuStep;
	}
	public void setMesMenuStep(String mesMenuStep) {
		this.mesMenuStep = mesMenuStep;
	}
	public String getMesMenuGroupKey() {
		return mesMenuGroupKey;
	}
	public void setMesMenuGroupKey(String mesMenuGroupKey) {
		this.mesMenuGroupKey = mesMenuGroupKey;
	}
	public String getMesMenuName() {
		return mesMenuName;
	}
	public void setMesMenuName(String mesMenuName) {
		this.mesMenuName = mesMenuName;
	}
	public String getMesMenuAuthKey() {
		return mesMenuAuthKey;
	}
	public void setMesMenuAuthKey(String mesMenuAuthKey) {
		this.mesMenuAuthKey = mesMenuAuthKey;
	}
	public String getMesMenuAuthFlag() {
		return mesMenuAuthFlag;
	}
	public void setMesMenuAuthFlag(String mesMenuAuthFlag) {
		this.mesMenuAuthFlag = mesMenuAuthFlag;
	}
	public String getMesMemberKey() {
		return mesMemberKey;
	}
	public void setMesMemberKey(String mesMemberKey) {
		this.mesMemberKey = mesMemberKey;
	}
	public String getMesMemberName() {
		return mesMemberName;
	}
	public void setMesMemberName(String mesMemberName) {
		this.mesMemberName = mesMemberName;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getMesUserKey() {
		return mesUserKey;
	}
	public void setMesUserKey(String mesUserKey) {
		this.mesUserKey = mesUserKey;
	}
	public String getMesUserRequestKey() {
		return mesUserRequestKey;
	}
	public void setMesUserRequestKey(String mesUserRequestKey) {
		this.mesUserRequestKey = mesUserRequestKey;
	}
	public String getMesUserNum() {
		return mesUserNum;
	}
	public void setMesUserNum(String mesUserNum) {
		this.mesUserNum = mesUserNum;
	}
	public String getMesUserId() {
		return mesUserId;
	}
	public void setMesUserId(String mesUserId) {
		this.mesUserId = mesUserId;
	}
	public String getMesUserName() {
		return mesUserName;
	}
	public void setMesUserName(String mesUserName) {
		this.mesUserName = mesUserName;
	}
	public String getMesUserEmail() {
		return mesUserEmail;
	}
	public void setMesUserEmail(String mesUserEmail) {
		this.mesUserEmail = mesUserEmail;
	}
	public String getMesUserComment() {
		return mesUserComment;
	}
	public void setMesUserComment(String mesUserComment) {
		this.mesUserComment = mesUserComment;
	}
	public String getMesUserStateFlag() {
		return mesUserStateFlag;
	}
	public void setMesUserStateFlag(String mesUserStateFlag) {
		this.mesUserStateFlag = mesUserStateFlag;
	}
	public String getMesUserImagefile() {
		return mesUserImagefile;
	}
	public void setMesUserImagefile(String mesUserImagefile) {
		this.mesUserImagefile = mesUserImagefile;
	}
	public String getMesUserPassword() {
		return mesUserPassword;
	}
	public void setMesUserPassword(String mesUserPassword) {
		this.mesUserPassword = mesUserPassword;
	}
	public String getMesUserJumin1() {
		return mesUserJumin1;
	}
	public void setMesUserJumin1(String mesUserJumin1) {
		this.mesUserJumin1 = mesUserJumin1;
	}
	public String getMesUserJumin2() {
		return mesUserJumin2;
	}
	public void setMesUserJumin2(String mesUserJumin2) {
		this.mesUserJumin2 = mesUserJumin2;
	}
	public String getMesUserBirthday() {
		return mesUserBirthday;
	}
	public void setMesUserBirthday(String mesUserBirthday) {
		this.mesUserBirthday = mesUserBirthday;
	}
	public String getMesUserBirthdayFlag() {
		return mesUserBirthdayFlag;
	}
	public void setMesUserBirthdayFlag(String mesUserBirthdayFlag) {
		this.mesUserBirthdayFlag = mesUserBirthdayFlag;
	}
	public String getMesUserMessenger() {
		return mesUserMessenger;
	}
	public void setMesUserMessenger(String mesUserMessenger) {
		this.mesUserMessenger = mesUserMessenger;
	}
	public String getMesUserPost1() {
		return mesUserPost1;
	}
	public void setMesUserPost1(String mesUserPost1) {
		this.mesUserPost1 = mesUserPost1;
	}
	public String getMesUserPost2() {
		return mesUserPost2;
	}
	public void setMesUserPost2(String mesUserPost2) {
		this.mesUserPost2 = mesUserPost2;
	}
	public String getMesUserAddress1() {
		return mesUserAddress1;
	}
	public void setMesUserAddress1(String mesUserAddress1) {
		this.mesUserAddress1 = mesUserAddress1;
	}
	public String getMesUserAddress2() {
		return mesUserAddress2;
	}
	public void setMesUserAddress2(String mesUserAddress2) {
		this.mesUserAddress2 = mesUserAddress2;
	}
	public String getMesUserTelephone1() {
		return mesUserTelephone1;
	}
	public void setMesUserTelephone1(String mesUserTelephone1) {
		this.mesUserTelephone1 = mesUserTelephone1;
	}
	public String getMesUserTelephone2() {
		return mesUserTelephone2;
	}
	public void setMesUserTelephone2(String mesUserTelephone2) {
		this.mesUserTelephone2 = mesUserTelephone2;
	}
	public String getMesUserTelephone3() {
		return mesUserTelephone3;
	}
	public void setMesUserTelephone3(String mesUserTelephone3) {
		this.mesUserTelephone3 = mesUserTelephone3;
	}
	public String getMesUserMobile1() {
		return mesUserMobile1;
	}
	public void setMesUserMobile1(String mesUserMobile1) {
		this.mesUserMobile1 = mesUserMobile1;
	}
	public String getMesUserMobile2() {
		return mesUserMobile2;
	}
	public void setMesUserMobile2(String mesUserMobile2) {
		this.mesUserMobile2 = mesUserMobile2;
	}
	public String getMesUserMobile3() {
		return mesUserMobile3;
	}
	public void setMesUserMobile3(String mesUserMobile3) {
		this.mesUserMobile3 = mesUserMobile3;
	}
	public String getMesUserHomepage() {
		return mesUserHomepage;
	}
	public void setMesUserHomepage(String mesUserHomepage) {
		this.mesUserHomepage = mesUserHomepage;
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
	public String getMesUserSignImage() {
		return mesUserSignImage;
	}
	public void setMesUserSignImage(String mesUserSignImage) {
		this.mesUserSignImage = mesUserSignImage;
	}
	public String getkMailUse() {
		return kMailUse;
	}
	public void setkMailUse(String kMailUse) {
		this.kMailUse = kMailUse;
	}
	public String getStartNum() {
		return startNum;
	}
	public void setStartNum(String startNum) {
		this.startNum = startNum;
	}
	public String getEndNum() {
		return endNum;
	}
	public void setEndNum(String endNum) {
		this.endNum = endNum;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUseCnt() {
		return useCnt;
	}
	public void setUseCnt(String useCnt) {
		this.useCnt = useCnt;
	}
	public String getNum1() {
		return Num1;
	}
	public void setNum1(String num1) {
		Num1 = num1;
	}
	public String getMesUserBirthdayN() {
		return mesUserBirthdayN;
	}
	public void setMesUserBirthdayN(String mesUserBirthdayN) {
		this.mesUserBirthdayN = mesUserBirthdayN;
	}
	public String getMesUserBirthdayFlagGubun() {
		return mesUserBirthdayFlagGubun;
	}
	public void setMesUserBirthdayFlagGubun(String mesUserBirthdayFlagGubun) {
		this.mesUserBirthdayFlagGubun = mesUserBirthdayFlagGubun;
	}
	public String getMesUserMaster() {
		return mesUserMaster;
	}
	public void setMesUserMaster(String mesUserMaster) {
		this.mesUserMaster = mesUserMaster;
	}
	
	
	
	
	
	
	 
	
	
	
	
	
	
	
	
}
