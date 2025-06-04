package egovframework.rndp.mes.output.service;

import egovframework.rndp.com.cmm.service.DefultVO;

public class MesOutputVO extends DefultVO{

	private String outputKey  						= "";
	private String oComName 						= "";
	private String oStartDate 						= "";
	private String oEndDate  						= "";
	private String oBusMoney 						= "";
	private String oIntoCom 						= "";
	private String oIntroAdress 						= "";
	private String oDetailAdress 						= "";
	private String oIntroDepart  						= "";
	private String oIntroManager					= "";
	private String oManagerPhone 						= "";
	private String oManagerEmail 						= "";
	private String oPmName  						= "";
	private String oPmPhone  						= "";
	private String oPmEmail  						= "";
	private String kStaffName  						= "";

	private String eGubunPage  						= "";
	private String fileSn  						= "";
	private String atchFileId  						= "";
	
	
	
	private String oOutputIemKey  						= "";
	private String oOutputItemGubun   						= "";
	private String oOutputItemName     						= "";
	private String oOutputItemFileId    						= "";
	private String eIMGregName    						= "";
	private String oOutputItemEtc     						= "";

	private String sSignStatus        					= "";
	private String sSignKey        					= "";
	private String sSignStaffKey        					= "";
	private String sSignStaffName        					= "";
	private String mOutputProcessFile        					= "";
	private String sSignContent        					= "";
	private String sSignDecison        					= "";
	private String gubun        					= "";
	private String oSignPass        					= "";
	
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
	
	private String	eDeliverableNUM		= "";
	private String	eItemName		= "";
	private String	eItemIndex		= "";
	private String	eItemGubun		= "";
	private String	eItemDate		= "";
	private String	eItemAffiliation		= "";
	private String	eItemAuthor		= "";
	private String	eItemRemarks		= "";
	private String	eFileRowId		= "";
	private String	eFileRowName		= "";
	private String	eFileRowGubun		= "";
	private String	eFileRowIndex		= "";
	private String	eDeliverableFileNUM		= "";
	private String	eReportNUM		= "";
	private String	eReportFileNUM		= "";
	private String	eProjectKey		= "";
	private String	sSignProgress		= "";

	
	
	
	
	public String getsSignProgress() {
		return sSignProgress;
	}
	public void setsSignProgress(String sSignProgress) {
		this.sSignProgress = sSignProgress;
	}
	public String geteProjectKey() {
		return eProjectKey;
	}
	public void seteProjectKey(String eProjectKey) {
		this.eProjectKey = eProjectKey;
	}
	public String geteReportNUM() {
		return eReportNUM;
	}
	public void seteReportNUM(String eReportNUM) {
		this.eReportNUM = eReportNUM;
	}
	public String geteReportFileNUM() {
		return eReportFileNUM;
	}
	public void seteReportFileNUM(String eReportFileNUM) {
		this.eReportFileNUM = eReportFileNUM;
	}
	public String geteDeliverableNUM() {
		return eDeliverableNUM;
	}
	public void seteDeliverableNUM(String eDeliverableNUM) {
		this.eDeliverableNUM = eDeliverableNUM;
	}
	public String geteItemName() {
		return eItemName;
	}
	public void seteItemName(String eItemName) {
		this.eItemName = eItemName;
	}
	public String geteItemIndex() {
		return eItemIndex;
	}
	public void seteItemIndex(String eItemIndex) {
		this.eItemIndex = eItemIndex;
	}
	public String geteItemGubun() {
		return eItemGubun;
	}
	public void seteItemGubun(String eItemGubun) {
		this.eItemGubun = eItemGubun;
	}
	public String geteItemDate() {
		return eItemDate;
	}
	public void seteItemDate(String eItemDate) {
		this.eItemDate = eItemDate;
	}
	public String geteItemAffiliation() {
		return eItemAffiliation;
	}
	public void seteItemAffiliation(String eItemAffiliation) {
		this.eItemAffiliation = eItemAffiliation;
	}
	public String geteItemAuthor() {
		return eItemAuthor;
	}
	public void seteItemAuthor(String eItemAuthor) {
		this.eItemAuthor = eItemAuthor;
	}
	public String geteItemRemarks() {
		return eItemRemarks;
	}
	public void seteItemRemarks(String eItemRemarks) {
		this.eItemRemarks = eItemRemarks;
	}
	public String geteFileRowId() {
		return eFileRowId;
	}
	public void seteFileRowId(String eFileRowId) {
		this.eFileRowId = eFileRowId;
	}
	public String geteFileRowName() {
		return eFileRowName;
	}
	public void seteFileRowName(String eFileRowName) {
		this.eFileRowName = eFileRowName;
	}
	public String geteFileRowGubun() {
		return eFileRowGubun;
	}
	public void seteFileRowGubun(String eFileRowGubun) {
		this.eFileRowGubun = eFileRowGubun;
	}
	public String geteFileRowIndex() {
		return eFileRowIndex;
	}
	public void seteFileRowIndex(String eFileRowIndex) {
		this.eFileRowIndex = eFileRowIndex;
	}
	public String geteDeliverableFileNUM() {
		return eDeliverableFileNUM;
	}
	public void seteDeliverableFileNUM(String eDeliverableFileNUM) {
		this.eDeliverableFileNUM = eDeliverableFileNUM;
	}
	public String geteProjectNum() {
		return eProjectNum;
	}
	public void seteProjectNum(String eProjectNum) {
		this.eProjectNum = eProjectNum;
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
	public String getoSignPass() {
		return oSignPass;
	}
	public void setoSignPass(String oSignPass) {
		this.oSignPass = oSignPass;
	}
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
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
	public String getmOutputProcessFile() {
		return mOutputProcessFile;
	}
	public void setmOutputProcessFile(String mOutputProcessFile) {
		this.mOutputProcessFile = mOutputProcessFile;
	}
	public String getsSignStaffName() {
		return sSignStaffName;
	}
	public void setsSignStaffName(String sSignStaffName) {
		this.sSignStaffName = sSignStaffName;
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
	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	public String getFileSn() {
		return fileSn;
	}
	public void setFileSn(String fileSn) {
		this.fileSn = fileSn;
	}
	public String getkStaffName() {
		return kStaffName;
	}
	public void setkStaffName(String kStaffName) {
		this.kStaffName = kStaffName;
	}
	public String geteIMGregName() {
		return eIMGregName;
	}
	public void seteIMGregName(String eIMGregName) {
		this.eIMGregName = eIMGregName;
	}
	public String geteGubunPage() {
		return eGubunPage;
	}
	public void seteGubunPage(String eGubunPage) {
		this.eGubunPage = eGubunPage;
	}
	public String getOutputKey() {
		return outputKey;
	}
	public void setOutputKey(String outputKey) {
		this.outputKey = outputKey;
	}
	public String getoComName() {
		return oComName;
	}
	public void setoComName(String oComName) {
		this.oComName = oComName;
	}
	public String getoStartDate() {
		return oStartDate;
	}
	public void setoStartDate(String oStartDate) {
		this.oStartDate = oStartDate;
	}
	
	public String getoEndDate() {
		return oEndDate;
	}
	public void setoEndDate(String oEndDate) {
		this.oEndDate = oEndDate;
	}
	public String getoBusMoney() {
		return oBusMoney;
	}
	public void setoBusMoney(String oBusMoney) {
		this.oBusMoney = oBusMoney;
	}
	public String getoIntoCom() {
		return oIntoCom;
	}
	public void setoIntoCom(String oIntoCom) {
		this.oIntoCom = oIntoCom;
	}
	public String getoIntroAdress() {
		return oIntroAdress;
	}
	public void setoIntroAdress(String oIntroAdress) {
		this.oIntroAdress = oIntroAdress;
	}
	public String getoDetailAdress() {
		return oDetailAdress;
	}
	public void setoDetailAdress(String oDetailAdress) {
		this.oDetailAdress = oDetailAdress;
	}
	public String getoIntroDepart() {
		return oIntroDepart;
	}
	public void setoIntroDepart(String oIntroDepart) {
		this.oIntroDepart = oIntroDepart;
	}
	public String getoIntroManager() {
		return oIntroManager;
	}
	public void setoIntroManager(String oIntroManager) {
		this.oIntroManager = oIntroManager;
	}
	
	public String getoManagerPhone() {
		return oManagerPhone;
	}
	public void setoManagerPhone(String oManagerPhone) {
		this.oManagerPhone = oManagerPhone;
	}
	public String getoManagerEmail() {
		return oManagerEmail;
	}
	public void setoManagerEmail(String oManagerEmail) {
		this.oManagerEmail = oManagerEmail;
	}
	public String getoPmName() {
		return oPmName;
	}
	public void setoPmName(String oPmName) {
		this.oPmName = oPmName;
	}
	public String getoPmPhone() {
		return oPmPhone;
	}
	public void setoPmPhone(String oPmPhone) {
		this.oPmPhone = oPmPhone;
	}
	public String getoPmEmail() {
		return oPmEmail;
	}
	public void setoPmEmail(String oPmEmail) {
		this.oPmEmail = oPmEmail;
	}
	public String getoOutputIemKey() {
		return oOutputIemKey;
	}
	public void setoOutputIemKey(String oOutputIemKey) {
		this.oOutputIemKey = oOutputIemKey;
	}
	public String getoOutputItemGubun() {
		return oOutputItemGubun;
	}
	public void setoOutputItemGubun(String oOutputItemGubun) {
		this.oOutputItemGubun = oOutputItemGubun;
	}
	public String getoOutputItemName() {
		return oOutputItemName;
	}
	public void setoOutputItemName(String oOutputItemName) {
		this.oOutputItemName = oOutputItemName;
	}

	public String getoOutputItemFileId() {
		return oOutputItemFileId;
	}
	public void setoOutputItemFileId(String oOutputItemFileId) {
		this.oOutputItemFileId = oOutputItemFileId;
	}
	public String getoOutputItemEtc() {
		return oOutputItemEtc;
	}
	public void setoOutputItemEtc(String oOutputItemEtc) {
		this.oOutputItemEtc = oOutputItemEtc;
	}

	
	
	
}






