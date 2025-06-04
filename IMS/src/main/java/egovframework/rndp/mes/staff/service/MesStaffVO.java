package egovframework.rndp.mes.staff.service;

import egovframework.rndp.com.cmm.service.DefultVO;

public class MesStaffVO extends DefultVO{
	
	private String kStaffKey			= "";
	private String kClassKey			= "";
	private String kClassName			= "";
	private String kPositionKey			= "";
	private String kPositionName		= "";
	private String kStaffId				= "";
	private String kStaffName			= "";
	private String kStaffPassword		= "";
	private String kStaffBirthday		= "";


	private String kStaffBirthday_flag	= "";
	private String kStaffEmail			= "";
	private String kStaffMessenger		= "";
	private String kStaffPost1			= "";
	private String kStaffPost2			= "";
	private String kStaffAddress1		= "";
	private String kStaffAddress2		= "";
	private String kStaffTelephone1		= "";
	private String kStaffTelephone2		= "";
	private String kStaffTelephone3		= "";
	private String kStaffMobile1		= "";
	private String kStaffMobile2		= "";
	private String kStaffMobile3		= "";
	private String kStaffHomepage		= "";
	private String kStaffComment		= "";
	private String kStaffState_flag		= "";
	private String kStaffImagefile		= "";
	private String kStaffSignImage		= "";
	private String kStaffNum			= "";
	private String kStaffJumin1			= "";
	private String kStaffJumin2			= "";
	private String kMailUse				= "";
	private String kSector_key			= "";
	private String kStaffStateFlag		= "";
	private String sendEndCount		= "";


	private String index 				= "";

	private String shMemberPassword = ""; //비밀번호
	private String shMemberId = "";       // 현재 비밀번호
	private String chPassword = "";       // 변경 비밀번호 1
	private String chPassword2 = "";      // 변경 비밀번호 2
	 

	private String kStaffRequestKey 	="";
	private String kStaffBirthdayFlag 	="";
	private String kSectorKey 			="";
	private String kSectorRank 			="";
	private String kSectorName 			="";
	private String kClassRank 			="";
	private String kPositionRank 		="";
	private int startNum 				=0;
	private int endNum					=0;
	private String message				="";
	private String kStaffAuthConfirmFlag 		="";		
	private String kStaffAuthWriteFlag 			="";		
	private String kStaffAuthModifyFlag 			="";		
	private String kStaffAuthDelFlag 				="";		
	private String kStaffAuthCloseFlag 				="";	

	//2020 07 27 intra staff 용 mes 이전 
	private String iclass = "";
	private String sector = "";
	private String position = "";

	private int staffKey = 0;
	private int classKey = 0; 
	private int sectorKey = 0; 
	private int positionKey = 0;
	private String id = "";
	private String name = "";
	private String password = "";
	private String newpassword ="";
	private String birth = "";
	private String birthFlag = "T";
	private String email = "";
	private String messenger = "";
	private String post1 = "";
	private String post2 = "";
	private String address1 = "";
	private String address2 = "";	
	private String telephone1 = "";
	private String telephone2 = "";
	private String telephone3 = "";	
	private String mobile1 = "";
	private String mobile2 = "";
	private String mobile3 = "";
	private String homepage = "http://";
	private String comment = "";
	private String stateFlag = "1";
	private String imagefile = "";
	private String signImage = "";
	private String[] signImageArr = new String[4];
	private String num = "";
	private String jumin1 = "";
	private String jumin2 = "";
	private String className = "";
	private String staWtPermit = "";
	private String staRdPermit = "";
	private String staffDupList; // 사용자 검색시 중복 제거용
	private String orderProcessCount;
	private String sujuNotCompleteCount;
	private String outbaljuCount;
	private String outbaljuIncomCount;
	private String sendCount;
	private String sujuCount;

	// 사용자 검색시 중복 제거용 2015 05 07 
	int[] staffKeyDupList = {};

	public String getSendCount() {
		return sendCount;
	}

	public void setSendCount(String sendCount) {
		this.sendCount = sendCount;
	}

	public String getSujuCount() {
		return sujuCount;
	}

	public void setSujuCount(String sujuCount) {
		this.sujuCount = sujuCount;
	}

	public String getOutbaljuIncomCount() {
		return outbaljuIncomCount;
	}

	public void setOutbaljuIncomCount(String outbaljuIncomCount) {
		this.outbaljuIncomCount = outbaljuIncomCount;
	}

	public String getOutbaljuCount() {
		return outbaljuCount;
	}

	public void setOutbaljuCount(String outbaljuCount) {
		this.outbaljuCount = outbaljuCount;
	}

	public String getSujuNotCompleteCount() {
		return sujuNotCompleteCount;
	}

	public void setSujuNotCompleteCount(String sujuNotCompleteCount) {
		this.sujuNotCompleteCount = sujuNotCompleteCount;
	}

	public String getOrderProcessCount() {
		return orderProcessCount;
	}

	public void setOrderProcessCount(String orderProcessCount) {
		this.orderProcessCount = orderProcessCount;
	}

	public String getSendEndCount() {
		return sendEndCount;
	}

	public void setSendEndCount(String sendEndCount) {
		this.sendEndCount = sendEndCount;
	}

	public String getIclass() {
		return iclass;
	}
	public void setIclass(String iclass) {
		this.iclass = iclass;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getStaffKey() {
		return staffKey;
	}
	public void setStaffKey(int staffKey) {
		this.staffKey = staffKey;
	}
	public int getClassKey() {
		return classKey;
	}
	public void setClassKey(int classKey) {
		this.classKey = classKey;
	}
	public int getSectorKey() {
		return sectorKey;
	}
	public void setSectorKey(int sectorKey) {
		this.sectorKey = sectorKey;
	}
	public int getPositionKey() {
		return positionKey;
	}
	public void setPositionKey(int positionKey) {
		this.positionKey = positionKey;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getBirthFlag() {
		return birthFlag;
	}
	public void setBirthFlag(String birthFlag) {
		this.birthFlag = birthFlag;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessenger() {
		return messenger;
	}
	public void setMessenger(String messenger) {
		this.messenger = messenger;
	}
	public String getPost1() {
		return post1;
	}
	public void setPost1(String post1) {
		this.post1 = post1;
	}
	public String getPost2() {
		return post2;
	}
	public void setPost2(String post2) {
		this.post2 = post2;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getTelephone1() {
		return telephone1;
	}
	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}
	public String getTelephone2() {
		return telephone2;
	}
	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}
	public String getTelephone3() {
		return telephone3;
	}
	public void setTelephone3(String telephone3) {
		this.telephone3 = telephone3;
	}
	public String getMobile1() {
		return mobile1;
	}
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	public String getMobile2() {
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	public String getMobile3() {
		return mobile3;
	}
	public void setMobile3(String mobile3) {
		this.mobile3 = mobile3;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getStateFlag() {
		return stateFlag;
	}
	public void setStateFlag(String stateFlag) {
		this.stateFlag = stateFlag;
	}
	public String getImagefile() {
		return imagefile;
	}
	public void setImagefile(String imagefile) {
		this.imagefile = imagefile;
	}
	public String getSignImage() {
		return signImage;
	}
	public void setSignImage(String signImage) {
		this.signImage = signImage;
	}
	public String[] getSignImageArr() {
		return signImageArr;
	}
	public void setSignImageArr(String[] signImageArr) {
		this.signImageArr = signImageArr;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getJumin1() {
		return jumin1;
	}
	public void setJumin1(String jumin1) {
		this.jumin1 = jumin1;
	}
	public String getJumin2() {
		return jumin2;
	}
	public void setJumin2(String jumin2) {
		this.jumin2 = jumin2;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getStaWtPermit() {
		return staWtPermit;
	}
	public void setStaWtPermit(String staWtPermit) {
		this.staWtPermit = staWtPermit;
	}
	public String getStaRdPermit() {
		return staRdPermit;
	}
	public void setStaRdPermit(String staRdPermit) {
		this.staRdPermit = staRdPermit;
	}
	public String getStaffDupList() {
		return staffDupList;
	}
	public void setStaffDupList(String staffDupList) {
		this.staffDupList = staffDupList;
	}
	public int[] getStaffKeyDupList() {
		return staffKeyDupList;
	}
	public void setStaffKeyDupList(int[] staffKeyDupList) {
		this.staffKeyDupList = staffKeyDupList;
	}
	public String getkStaffRequestKey() {
		return kStaffRequestKey;
	}
	public void setkStaffRequestKey(String kStaffRequestKey) {
		this.kStaffRequestKey = kStaffRequestKey;
	}
	public String getkStaffBirthdayFlag() {
		return kStaffBirthdayFlag;
	}
	public void setkStaffBirthdayFlag(String kStaffBirthdayFlag) {
		this.kStaffBirthdayFlag = kStaffBirthdayFlag;
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
	public String getkClassRank() {
		return kClassRank;
	}
	public void setkClassRank(String kClassRank) {
		this.kClassRank = kClassRank;
	}
	public String getkPositionRank() {
		return kPositionRank;
	}
	public void setkPositionRank(String kPositionRank) {
		this.kPositionRank = kPositionRank;
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
	public String getShMemberPassword() {
		return shMemberPassword;
	}
	public void setShMemberPassword(String shMemberPassword) {
		this.shMemberPassword = shMemberPassword;
	}
	public String getShMemberId() {
		return shMemberId;
	}
	public void setShMemberId(String shMemberId) {
		this.shMemberId = shMemberId;
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
	public String getkStaffKey() {
		return kStaffKey;
	}
	public void setkStaffKey(String kStaffKey) {
		this.kStaffKey = kStaffKey;
	}
	public String getkClassKey() {
		return kClassKey;
	}
	public void setkClassKey(String kClassKey) {
		this.kClassKey = kClassKey;
	}
	public String getkPositionKey() {
		return kPositionKey;
	}
	public void setkPositionKey(String kPositionKey) {
		this.kPositionKey = kPositionKey;
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
	public String getkStaffPassword() {
		return kStaffPassword;
	}
	public void setkStaffPassword(String kStaffPassword) {
		this.kStaffPassword = kStaffPassword;
	}
	public String getkStaffBirthday() {
		return kStaffBirthday;
	}
	public void setkStaffBirthday(String kStaffBirthday) {
		this.kStaffBirthday = kStaffBirthday;
	}
	public String getkStaffBirthday_flag() {
		return kStaffBirthday_flag;
	}
	public void setkStaffBirthday_flag(String kStaffBirthday_flag) {
		this.kStaffBirthday_flag = kStaffBirthday_flag;
	}
	public String getkStaffEmail() {
		return kStaffEmail;
	}
	public void setkStaffEmail(String kStaffEmail) {
		this.kStaffEmail = kStaffEmail;
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
	public String getkStaffComment() {
		return kStaffComment;
	}
	public void setkStaffComment(String kStaffComment) {
		this.kStaffComment = kStaffComment;
	}
	public String getkStaffState_flag() {
		return kStaffState_flag;
	}
	public void setkStaffState_flag(String kStaffState_flag) {
		this.kStaffState_flag = kStaffState_flag;
	}
	public String getkStaffImagefile() {
		return kStaffImagefile;
	}
	public void setkStaffImagefile(String kStaffImagefile) {
		this.kStaffImagefile = kStaffImagefile;
	}
	public String getkStaffSignImage() {
		return kStaffSignImage;
	}
	public void setkStaffSignImage(String kStaffSignImage) {
		this.kStaffSignImage = kStaffSignImage;
	}
	public String getkStaffNum() {
		return kStaffNum;
	}
	public void setkStaffNum(String kStaffNum) {
		this.kStaffNum = kStaffNum;
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
	public String getkMailUse() {
		return kMailUse;
	}
	public void setkMailUse(String kMailUse) {
		this.kMailUse = kMailUse;
	}
	public String getkSector_key() {
		return kSector_key;
	}
	public void setkSector_key(String kSector_key) {
		this.kSector_key = kSector_key;
	}
	public String getkPositionName() {
		return kPositionName;
	}
	public void setkPositionName(String kPositionName) {
		this.kPositionName = kPositionName;
	}
	public String getkStaffStateFlag() {
		return kStaffStateFlag;
	}
	public void setkStaffStateFlag(String kStaffStateFlag) {
		this.kStaffStateFlag = kStaffStateFlag;
	}
	public String getkClassName() {
		return kClassName;
	}
	public void setkClassName(String kClassName) {
		this.kClassName = kClassName;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	
}
