package egovframework.rndp.mes.login.service;  

import java.io.Serializable;

public class MesK_Staff  implements Serializable
{
	 
	
	/**
	 * 2020 07 27 모듈화로 인해 intra에서 mes 로 이전 
	 */
	private static final long serialVersionUID = -941964982784536730L;
	
	private int     kStaffKey                 = 0;
	private int     kClassKey                 = 0;
	private int     kPositionKey              = 0;
	private String  kStaffId                  ="";
	private String  kStaffName                ="";
	private String  kStaffPassword            ="";
	private String  kStaffBirthday            ="";
	private String  kStaffBirthdayFlag        ="";
	private String  kStaffEmail               ="";
	private String  kStaffMessenger           ="";
	private String  kStaffPost1               ="";
	private String  kStaffPost2               ="";
	private String  kStaffAddress1            ="";
	private String  kStaffAddress2            ="";
	private String  kStaffTelephone1          ="";
	private String  kStaffTelephone2          ="";
	private String  kStaffTelephone3          ="";
	private String  kStaffMobile1             ="";
	private String  kStaffMobile2             ="";
	private String  kStaffMobile3             ="";
	private String  kStaffHomepage            ="";
	private String  kStaffComment             ="";
	private String  kStaffStateFlag           ="";
	private String  kStaffImagefile           ="";
	private String  kStaffSignImage           ="";
	private String  kStaffNum                 ="";
	private String  kStaffJumin1              ="";
	private String  kStaffJumin2              ="";
	private String  kMailUse                  ="";
	private int     kSectorKey                = 0;
	private int     appCnt                = 0;
	
	
	//직원별 
	private String kStaffAuthConfirmFlag 		="";		//확정권한
	private String kStaffAuthWriteFlag 			="";		//쓰기권한
	private String kStaffAuthModifyFlag 		="";		//수정권한
	private String kStaffAuthDelFlag 			="";		//삭제권한
	private String kStaffAuthCloseFlag 			="";		//마감권한
	private String kAdminAuth 			="";		//관리자 권한
	
	
	public String getkAdminAuth() {
		return kAdminAuth;
	}
	public void setkAdminAuth(String kAdminAuth) {
		this.kAdminAuth = kAdminAuth;
	}
	public int getAppCnt() {
		return appCnt;
	}
	public void setAppCnt(int appCnt) {
		this.appCnt = appCnt;
	}
	public int getkStaffKey() {
		return kStaffKey;
	}
	public void setkStaffKey(int kStaffKey) {
		this.kStaffKey = kStaffKey;
	}
	public int getkClassKey() {
		return kClassKey;
	}
	public void setkClassKey(int kClassKey) {
		this.kClassKey = kClassKey;
	}
	public int getkPositionKey() {
		return kPositionKey;
	}
	public void setkPositionKey(int kPositionKey) {
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
	public String getkStaffBirthdayFlag() {
		return kStaffBirthdayFlag;
	}
	public void setkStaffBirthdayFlag(String kStaffBirthdayFlag) {
		this.kStaffBirthdayFlag = kStaffBirthdayFlag;
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
	public int getkSectorKey() {
		return kSectorKey;
	}
	public void setkSectorKey(int kSectorKey) {
		this.kSectorKey = kSectorKey;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getStateFlagForm(){
		if (kStaffStateFlag.equals("1")){
			return "재직";
		}else if (kStaffStateFlag.equals("2")){
			return "휴직";
		}else if (kStaffStateFlag.equals("3")){
			return "정직";
		}else{
			return "퇴직";
		}
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
	

	/*
 
	public void setImagefile(String imagefile)
	{
		this.imagefile = Common.checkNull(imagefile);
	}
 

	public void setSignImage(String signImage)
	{
		this.signImage = Common.checkNull(signImage);
	}
  
	public String getBirthForm()
	{
		String result = birth;

		if (result.length() == 8)
		{
			result = result.substring(0, 4) + "년 " + result.substring(4, 6) + "월 " + result.substring(6, 8) + "일";
		}

		return result;
	}

	public String getBirthFlagForm()
	{
		if (birthFlag.equals("T"))
		{
			return "(양력)";
		}
		else
		{
			return "(음력)";
		}
	}
 
	public String getMobile()
	{
		String result = mobile1 + "-" + mobile2 + "-" + mobile3;

		if (result.equals("--"))
		{
			return "";
		}
		else
		{
			return result;
		}
	}


	public String getStateFlagForm()
	{
		if (stateFlag.equals("1"))
		{
			return "재직";
		}
		else if (stateFlag.equals("2"))
		{
			return "휴직";
		}
		else if (stateFlag.equals("3"))
		{
			return "정직";
		}
		else
		{
			return "퇴직";
		}
	}

	public String getTelephone()
	{
		String result = telephone1 + "-" + telephone2 + "-" + telephone3;

		if (result.equals("--"))
		{
			return "";
		}
		else
		{
			return result;
		}
	}

	public String getImagefileForm()
	{
		//System.out.println(this.getImagefile());
			
		if (!imagefile.equals(""))
		{
			return "<img src=" + Common.getPath("staff") + imagefile + " width=132 height=162 border=0 />";
		}

		return imagefile;
	}
	
	public String getSignfileForm()
	{
		//System.out.println(this.getSignImage());
			
		if (!signImage.equals(""))
		{
			return "<img src=" + Common.getPath("staff") + signImage + " width=132 height=100 border=0 />";
		}

		return signImage;
	}

	public String[] getSignImageArr() {
		return signImageArr;
	}

	public void setSignImageArr(String[] signImageArr) {
		this.signImageArr = signImageArr;
	}
 

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}*/
	
	 
	 
    

	
	
}
