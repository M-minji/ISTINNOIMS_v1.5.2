package egovframework.rndp.mes.project.service;

import egovframework.rndp.com.cmm.service.DefultVO;

public class MesProjectVO extends DefultVO{

	private String pProjectkey   					= "";
	private String pProjectItemkey   					= "";
	private String pComName  						= "";
	private String pStartDate  						= "";
	private String pEndDate   						= "";
	private String pItroCom    						= "";
	private String pGubunStep     					= "";
	private String pGubun     						= "";
	private String pTilte      						= "";
	private String pContent      					= "";
	private String pRequest      					= "";
	private String pReDate       					= "";
	private String pWdate        					= "";
	

	private String sSignStatus        					= "";
	private String sSignKey        					= "";
	private String sSignStaffKey        					= "";
	private String sSignStaffName        					= "";
	private String mProjectProcessFile        					= "";
	private String sSignContent        					= "";
	private String sSignDecison        					= "";
	private String gubun        					= "";

	private String	eGubunPage						= "";
	private String  eIMGregName      				= "";
	private String  eAddFileId						= "";
	private String  eAddFileId1						= "";
	private String  eAddFileId7						= "";
	private String  AtchFileName					= "";
	private String  AtchFileName1					= "";
	private String  pProjectItemEtc					= "";
	private String  atchFileId					= "";
	private String  fileSn					= "";

	private String	projectItemFileId		= "";
	
	
	
	private String	eProjectNum		= "";
	private String	eProjectName		= "";
	private String	eProjectPeriod		= "";
	private String	eStartDate		= "";
	private String	eEndDate		= "";
	private String	eManager		= "";
	private String	eManagerContact		= "";
	private String	eManagerEmail		= "";
	private String	eMainContractor		= "";
	private String	eProjectPM		= "";
	private String	ePMContact		= "";
	private String	ePMEmail		= "";
	private String	eRemarks		= "";
	private String	eProjectStatus		= "";
	
	private String	eSearchWord1		= "";
	private String	eSearchWord2		= "";
	private String	eSearchWord3		= "";
	private String	eSearchWord4		= "";
	private String	eSearchWord5		= "";
	private String	eSearchWord6		= "";
	private String	eSearchWord7		= "";
	
	private String  sSignTableKey                        = "";        
	private String  sSignTableName                        = "";        
	private String  sSignProgress                        = "";        
	private String  sSignStaffGubun                        = "";        
	private String  sSignStaffPosition                        = "";   
	private String  oSignPass                        = "";   
	
	private String  kStaffKey                        = "";   
	private String  eViewGubun                        = "";   
	
	
	
	public String getkStaffKey() {
		return kStaffKey;
	}
	public void setkStaffKey(String kStaffKey) {
		this.kStaffKey = kStaffKey;
	}
	public String geteViewGubun() {
		return eViewGubun;
	}
	public void seteViewGubun(String eViewGubun) {
		this.eViewGubun = eViewGubun;
	}
	public String getoSignPass() {
		return oSignPass;
	}
	public void setoSignPass(String oSignPass) {
		this.oSignPass = oSignPass;
	}
	public String getsSignTableKey() {
		return sSignTableKey;
	}
	public void setsSignTableKey(String sSignTableKey) {
		this.sSignTableKey = sSignTableKey;
	}
	public String getsSignTableName() {
		return sSignTableName;
	}
	public void setsSignTableName(String sSignTableName) {
		this.sSignTableName = sSignTableName;
	}
	public String getsSignProgress() {
		return sSignProgress;
	}
	public void setsSignProgress(String sSignProgress) {
		this.sSignProgress = sSignProgress;
	}
	public String getsSignStaffGubun() {
		return sSignStaffGubun;
	}
	public void setsSignStaffGubun(String sSignStaffGubun) {
		this.sSignStaffGubun = sSignStaffGubun;
	}
	public String getsSignStaffPosition() {
		return sSignStaffPosition;
	}
	public void setsSignStaffPosition(String sSignStaffPosition) {
		this.sSignStaffPosition = sSignStaffPosition;
	}
	public String geteSearchWord1() {
		return eSearchWord1;
	}
	public void seteSearchWord1(String eSearchWord1) {
		this.eSearchWord1 = eSearchWord1;
	}
	public String geteSearchWord2() {
		return eSearchWord2;
	}
	public void seteSearchWord2(String eSearchWord2) {
		this.eSearchWord2 = eSearchWord2;
	}
	public String geteSearchWord3() {
		return eSearchWord3;
	}
	public void seteSearchWord3(String eSearchWord3) {
		this.eSearchWord3 = eSearchWord3;
	}
	public String geteSearchWord4() {
		return eSearchWord4;
	}
	public void seteSearchWord4(String eSearchWord4) {
		this.eSearchWord4 = eSearchWord4;
	}
	public String geteSearchWord5() {
		return eSearchWord5;
	}
	public void seteSearchWord5(String eSearchWord5) {
		this.eSearchWord5 = eSearchWord5;
	}
	public String geteSearchWord6() {
		return eSearchWord6;
	}
	public void seteSearchWord6(String eSearchWord6) {
		this.eSearchWord6 = eSearchWord6;
	}
	public String geteSearchWord7() {
		return eSearchWord7;
	}
	public void seteSearchWord7(String eSearchWord7) {
		this.eSearchWord7 = eSearchWord7;
	}
	public String geteProjectNum() {
		return eProjectNum;
	}
	public void seteProjectNum(String eProjectNum) {
		this.eProjectNum = eProjectNum;
	}
	public String geteStartDate() {
		return eStartDate;
	}
	public void seteStartDate(String eStartDate) {
		this.eStartDate = eStartDate;
	}
	public String geteEndDate() {
		return eEndDate;
	}
	public void seteEndDate(String eEndDate) {
		this.eEndDate = eEndDate;
	}
	public String geteProjectName() {
		return eProjectName;
	}
	public void seteProjectName(String eProjectName) {
		this.eProjectName = eProjectName;
	}
	public String geteProjectPeriod() {
		return eProjectPeriod;
	}
	public void seteProjectPeriod(String eProjectPeriod) {
		this.eProjectPeriod = eProjectPeriod;
	}
 
	public String geteManager() {
		return eManager;
	}
	public void seteManager(String eManager) {
		this.eManager = eManager;
	}
	public String geteManagerContact() {
		return eManagerContact;
	}
	public void seteManagerContact(String eManagerContact) {
		this.eManagerContact = eManagerContact;
	}
	public String geteManagerEmail() {
		return eManagerEmail;
	}
	public void seteManagerEmail(String eManagerEmail) {
		this.eManagerEmail = eManagerEmail;
	}
	public String geteMainContractor() {
		return eMainContractor;
	}
	public void seteMainContractor(String eMainContractor) {
		this.eMainContractor = eMainContractor;
	}
	public String geteProjectPM() {
		return eProjectPM;
	}
	public void seteProjectPM(String eProjectPM) {
		this.eProjectPM = eProjectPM;
	}
	public String getePMContact() {
		return ePMContact;
	}
	public void setePMContact(String ePMContact) {
		this.ePMContact = ePMContact;
	}
	public String getePMEmail() {
		return ePMEmail;
	}
	public void setePMEmail(String ePMEmail) {
		this.ePMEmail = ePMEmail;
	}
	public String geteRemarks() {
		return eRemarks;
	}
	public void seteRemarks(String eRemarks) {
		this.eRemarks = eRemarks;
	}
	public String geteProjectStatus() {
		return eProjectStatus;
	}
	public void seteProjectStatus(String eProjectStatus) {
		this.eProjectStatus = eProjectStatus;
	}
	public String getFileSn() {
		return fileSn;
	}
	public void setFileSn(String fileSn) {
		this.fileSn = fileSn;
	}
	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	public String getpProjectItemkey() {
		return pProjectItemkey;
	}
	public void setpProjectItemkey(String pProjectItemkey) {
		this.pProjectItemkey = pProjectItemkey;
	}
	public String getpProjectItemEtc() {
		return pProjectItemEtc;
	}
	public void setpProjectItemEtc(String pProjectItemEtc) {
		this.pProjectItemEtc = pProjectItemEtc;
	}
	public String geteGubunPage() {
		return eGubunPage;
	}
	public void seteGubunPage(String eGubunPage) {
		this.eGubunPage = eGubunPage;
	}
	public String geteIMGregName() {
		return eIMGregName;
	}
	public void seteIMGregName(String eIMGregName) {
		this.eIMGregName = eIMGregName;
	}
	public String geteAddFileId() {
		return eAddFileId;
	}
	public void seteAddFileId(String eAddFileId) {
		this.eAddFileId = eAddFileId;
	}
	public String geteAddFileId1() {
		return eAddFileId1;
	}
	public void seteAddFileId1(String eAddFileId1) {
		this.eAddFileId1 = eAddFileId1;
	}
	public String geteAddFileId7() {
		return eAddFileId7;
	}
	public void seteAddFileId7(String eAddFileId7) {
		this.eAddFileId7 = eAddFileId7;
	}
	public String getAtchFileName() {
		return AtchFileName;
	}
	public void setAtchFileName(String atchFileName) {
		AtchFileName = atchFileName;
	}
	public String getAtchFileName1() {
		return AtchFileName1;
	}
	public void setAtchFileName1(String atchFileName1) {
		AtchFileName1 = atchFileName1;
	}
	public String getProjectItemFileId() {
		return projectItemFileId;
	}
	public void setProjectItemFileId(String projectItemFileId) {
		this.projectItemFileId = projectItemFileId;
	}
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}
	public String getsSignStatus() {
		return sSignStatus;
	}
	public void setsSignStatus(String sSignStatus) {
		this.sSignStatus = sSignStatus;
	}
	public String getsSignKey() {
		return sSignKey;
	}
	public void setsSignKey(String sSignKey) {
		this.sSignKey = sSignKey;
	}
	public String getsSignStaffKey() {
		return sSignStaffKey;
	}
	public void setsSignStaffKey(String sSignStaffKey) {
		this.sSignStaffKey = sSignStaffKey;
	}
	public String getsSignStaffName() {
		return sSignStaffName;
	}
	public void setsSignStaffName(String sSignStaffName) {
		this.sSignStaffName = sSignStaffName;
	}
	public String getmProjectProcessFile() {
		return mProjectProcessFile;
	}
	public void setmProjectProcessFile(String mProjectProcessFile) {
		this.mProjectProcessFile = mProjectProcessFile;
	}
	public String getsSignContent() {
		return sSignContent;
	}
	public void setsSignContent(String sSignContent) {
		this.sSignContent = sSignContent;
	}
	public String getsSignDecison() {
		return sSignDecison;
	}
	public void setsSignDecison(String sSignDecison) {
		this.sSignDecison = sSignDecison;
	}
	public String getpProjectkey() {
		return pProjectkey;
	}
	public void setpProjectkey(String pProjectkey) {
		this.pProjectkey = pProjectkey;
	}
	public String getpComName() {
		return pComName;
	}
	public void setpComName(String pComName) {
		this.pComName = pComName;
	}
	public String getpStartDate() {
		return pStartDate;
	}
	public void setpStartDate(String pStartDate) {
		this.pStartDate = pStartDate;
	}
	public String getpEndDate() {
		return pEndDate;
	}
	public void setpEndDate(String pEndDate) {
		this.pEndDate = pEndDate;
	}
	public String getpItroCom() {
		return pItroCom;
	}
	public void setpItroCom(String pItroCom) {
		this.pItroCom = pItroCom;
	}
	public String getpGubunStep() {
		return pGubunStep;
	}
	public void setpGubunStep(String pGubunStep) {
		this.pGubunStep = pGubunStep;
	}
	public String getpGubun() {
		return pGubun;
	}
	public void setpGubun(String pGubun) {
		this.pGubun = pGubun;
	}
	public String getpTilte() {
		return pTilte;
	}
	public void setpTilte(String pTilte) {
		this.pTilte = pTilte;
	}
	public String getpContent() {
		return pContent;
	}
	public void setpContent(String pContent) {
		this.pContent = pContent;
	}
	public String getpRequest() {
		return pRequest;
	}
	public void setpRequest(String pRequest) {
		this.pRequest = pRequest;
	}
	public String getpReDate() {
		return pReDate;
	}
	public void setpReDate(String pReDate) {
		this.pReDate = pReDate;
	}
	public String getpWdate() {
		return pWdate;
	}
	public void setpWdate(String pWdate) {
		this.pWdate = pWdate;
	}

	
	
}






