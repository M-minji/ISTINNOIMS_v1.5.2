package egovframework.rndp.mes.login.service;  

import java.io.Serializable;

public class MesK_StaffVo  extends MesK_Staff implements Serializable {

	/**
	 * 2020 07 27 모듈화로 인해 intra에서 mes 로 이전 
	 */
	private static final long serialVersionUID = 3124846009135230517L;
 
	//비밀번호 변경
	private String newPassword    = "";
	private String kClassName     = "";//부서명
	private String kPositionName  = "";//직급명
	private String kSectorName	  = "";//사업명
	private String kParamImage	  = "";
	private String kMenuKey		  = "";
	private String kMenuRoot	  = "";
	private String kLogGubun	  = "";
	private String kLogGubunKey	  = "";
	private String kLogIp		  = "";
	private String eTopStartDate  = "";
	private String eTopEndDate	  = "";
	private String ePageGubun	  = "";
	private String eLogCnt		  = "";
	private String eLogSum		  = "";
	private String eLogData		  = "";
	private String kMenuName	  = "";

	/*직원 등록 신청 팝업에 사용*/
	private int    kClassKey      	=0;		//부서 키
	private int    kClassRank    	=0;		 
	private int    kSectorKey     	=0;
	private int    kSectorRank    	=0; 
	private int    kPositionKey   	=0;
	private int    kPositionRank  	=0; 	
	private String shMemberPassword = ""; //비밀번호
	private String shMemberId       = ""; // 현재 비밀번호
	private String chPassword       = "";
	private String chPassword2      = "";
	
	private String count      = "";
	private String confirm      = "";

	private String comLogImg		= "";
	private String mainLogImg		= "";
	


	public String getComLogImg() {
		return comLogImg;
	}

	public void setComLogImg(String comLogImg) {
		this.comLogImg = comLogImg;
	}

	public String getMainLogImg() {
		return mainLogImg;
	}

	public void setMainLogImg(String mainLogImg) {
		this.mainLogImg = mainLogImg;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public int getkClassKey() {
		return kClassKey;
	}

	public void setkClassKey(int kClassKey) {
		this.kClassKey = kClassKey;
	}

	public int getkClassRank() {
		return kClassRank;
	}

	public void setkClassRank(int kClassRank) {
		this.kClassRank = kClassRank;
	}

	public int getkSectorKey() {
		return kSectorKey;
	}

	public void setkSectorKey(int kSectorKey) {
		this.kSectorKey = kSectorKey;
	}

	public int getkSectorRank() {
		return kSectorRank;
	}

	public void setkSectorRank(int kSectorRank) {
		this.kSectorRank = kSectorRank;
	}

	public int getkPositionKey() {
		return kPositionKey;
	}

	public void setkPositionKey(int kPositionKey) {
		this.kPositionKey = kPositionKey;
	}

	public int getkPositionRank() {
		return kPositionRank;
	}

	public void setkPositionRank(int kPositionRank) {
		this.kPositionRank = kPositionRank;
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

	public String getkClassName() {
		return kClassName;
	}

	public void setkClassName(String kClassName) {
		this.kClassName = kClassName;
	}

	public String getkPositionName() {
		return kPositionName;
	}

	public void setkPositionName(String kPositionName) {
		this.kPositionName = kPositionName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getkSectorName() {
		return kSectorName;
	}

	public void setkSectorName(String kSectorName) {
		this.kSectorName = kSectorName;
	}

	public String getkParamImage() {
		return kParamImage;
	}

	public void setkParamImage(String kParamImage) {
		this.kParamImage = kParamImage;
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
