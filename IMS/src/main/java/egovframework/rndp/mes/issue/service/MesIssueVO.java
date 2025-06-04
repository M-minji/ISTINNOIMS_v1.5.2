package egovframework.rndp.mes.issue.service;

import java.util.List;

import egovframework.rndp.com.cmm.service.DefultVO;
import egovframework.rndp.com.utl.EgovStringUtil;

public class MesIssueVO extends DefultVO{
	
	private String searchType						= "";
	private String kStaffKey						= "";
	private String kStaffName						= "";
	private String topStartDate						= "";
	private String topEndDate						= "";
	private String eViewGubun						= "";
	private String ePageGubun						= "";
	
	
	// 추가된 검색조건
	private String dateType							= "요청일자";
	private String mainTanceType					= "";
	
 
	private String eIssueStaffKey = "";
	private String eIssueStaffName = "";
	private String eProcessingDate = "";
	private String eIssueContent = "";
	private String eRequestDate = "";
	private String eHandler = "";
	private String eRequester = "";
	private String eIssueType = "";
	private String eIssueTypeName = "";
	private String eAssetType = "";
	private String eAssetTypeName = "";
	private String eProcessingType = "";
	private String eProcessingTypeName = "";
	private String eCreationDate = "";
	private String eAuthor = "";
	
	private String eIssueSelect1 = "";
	private String eIssueSelect2 = "";
	private String eIssueSelect3 = "";
	private String eIssueSelect4 = "";
	private String eAssetKey = "";
	private String eIssueKey = "";
	private String eIssueAssetKey = "";
	
	private String sSignStatus = "";
	private String sSignStaffKey = "";
	private String eIssueCate = "";
	private String eIssueStatus = "";
	
	// 파일
	private String eFileID					= "";
	private String eFileName				= "";
	private String eFileExt					= "";
	
	//검색 조건
	private String eSearchWordA = "";
	private String eSearchWordB = "";
	private String eSearchWordC = "";
	private String eSearchWordD = "";
	private String eSearchWordE = "";
	private String eSearchWordF = "";
	private String eSearchWordG = "";
	private String eSearchWordH = "";
	private String eSearchWordI = "";
	private String eSearchWordJ = "";
	private String eSearchWordK = "";
	private String eSearchWordL = "";
	private String eSearchWordM = "";
	private String eSearchWordN = "";
	private String eSearchWordO = "";
	private String eSearchWordP = "";
	private String eSearchWordQ = "";
	private String eSearchWordR = "";
	private String eSearchWordS = "";
	private String eSearchWordT = "";
	private String eSearchWordU = "";
	private String eSearchWordV = "";
	private String eSearchWordW = "";
	private String eSearchWordX = "";
	private String eSearchWordY = "";
	private String eSearchWordZ = "";
	
	private String  aAssetNumber               = "";
	private String  aAssetStatus               = "";
	private String  aAssetType                 = "";
	private String  aAssetName                 = "";
	private String  aAssetMaker                = "";
	private String  aAssetModel                = "";
	private String  aAssetManufactureNumber    = "";
	private String  aAssetForm                 = "";
	private String  aAssetIntroducer           = "";
	private String  aAssetDate                 = "";
	private String  aAssetCost                 = "";
	private String  aAssetImage                = "";
	private String  aAssetEtc                  = "";
	private String  ePositionName1             = "";      
	private String  eDeviceType                = "";      
	private String  eAssetPurpose              = "";      
	private String  eNetworkType               = "";      
	private String  eHostName                  = "";      
	private String  eIp                        = "";        
	private String  eOs                        = "";        
	
	private String  eActualWorkDate                        = "";        
	private String  eActualWorker                        = "";        
	private String  eActualDetails                        = "";        
	
	private String  oSignPass                        = "";        
	private String  sSignTableKey                        = "";        
	private String  sSignTableName                        = "";        
	private String  sSignDecison                        = "";        
	private String  sSignContent                        = "";        
	private String  sSignProgress                        = "";        
	private String  sSignStaffName                        = "";        
	private String  sSignStaffGubun                        = "";        
	private String  sSignStaffPosition                        = ""; 
	
	private String  eRequesterOrg                        = "";        
	private String  eHandlerOrg                        = "";
	
	private String  eRowWorker                        = "";        
	private String  eRowDepartment                        = "";        
	private String  eRowPosition                        = "";        
	private String  eRowContact                        = "";        
	private String  eRowNotes                        = "";      
	
	
	
	public String geteRowWorker() {
		return eRowWorker;
	}
	public void seteRowWorker(String eRowWorker) {
		this.eRowWorker = eRowWorker;
	}
	public String geteRowDepartment() {
		return eRowDepartment;
	}
	public void seteRowDepartment(String eRowDepartment) {
		this.eRowDepartment = eRowDepartment;
	}
	public String geteRowPosition() {
		return eRowPosition;
	}
	public void seteRowPosition(String eRowPosition) {
		this.eRowPosition = eRowPosition;
	}
	public String geteRowContact() {
		return eRowContact;
	}
	public void seteRowContact(String eRowContact) {
		this.eRowContact = eRowContact;
	}
	public String geteRowNotes() {
		return eRowNotes;
	}
	public void seteRowNotes(String eRowNotes) {
		this.eRowNotes = eRowNotes;
	}
	public String geteRequesterOrg() {
		return eRequesterOrg;
	}
	public void seteRequesterOrg(String eRequesterOrg) {
		this.eRequesterOrg = eRequesterOrg;
	}
	public String geteHandlerOrg() {
		return eHandlerOrg;
	}
	public void seteHandlerOrg(String eHandlerOrg) {
		this.eHandlerOrg = eHandlerOrg;
	}
	public String getsSignStaffName() {
		return sSignStaffName;
	}
	public void setsSignStaffName(String sSignStaffName) {
		this.sSignStaffName = sSignStaffName;
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
	public String getsSignDecison() {
		return sSignDecison;
	}
	public void setsSignDecison(String sSignDecison) {
		this.sSignDecison = sSignDecison;
	}
	public String getsSignContent() {
		return sSignContent;
	}
	public void setsSignContent(String sSignContent) {
		this.sSignContent = sSignContent;
	}
	public String getsSignProgress() {
		return sSignProgress;
	}
	public void setsSignProgress(String sSignProgress) {
		this.sSignProgress = sSignProgress;
	}
	public String geteActualWorkDate() {
		return eActualWorkDate;
	}
	public void seteActualWorkDate(String eActualWorkDate) {
		this.eActualWorkDate = eActualWorkDate;
	}
	public String geteActualWorker() {
		return eActualWorker;
	}
	public void seteActualWorker(String eActualWorker) {
		this.eActualWorker = eActualWorker;
	}
	public String geteActualDetails() {
		return eActualDetails;
	}
	public void seteActualDetails(String eActualDetails) {
		this.eActualDetails = eActualDetails;
	}
	public String getePageGubun() {
		return ePageGubun;
	}
	public void setePageGubun(String ePageGubun) {
		this.ePageGubun = ePageGubun;
	}
	public String geteIssueStatus() {
		return eIssueStatus;
	}
	public void seteIssueStatus(String eIssueStatus) {
		this.eIssueStatus = eIssueStatus;
	}
	public String getaAssetNumber() {
		return aAssetNumber;
	}
	public void setaAssetNumber(String aAssetNumber) {
		this.aAssetNumber = aAssetNumber;
	}
	public String getaAssetStatus() {
		return aAssetStatus;
	}
	public void setaAssetStatus(String aAssetStatus) {
		this.aAssetStatus = aAssetStatus;
	}
	public String getaAssetType() {
		return aAssetType;
	}
	public void setaAssetType(String aAssetType) {
		this.aAssetType = aAssetType;
	}
	public String getaAssetName() {
		return aAssetName;
	}
	public void setaAssetName(String aAssetName) {
		this.aAssetName = aAssetName;
	}
	public String getaAssetMaker() {
		return aAssetMaker;
	}
	public void setaAssetMaker(String aAssetMaker) {
		this.aAssetMaker = aAssetMaker;
	}
	public String getaAssetModel() {
		return aAssetModel;
	}
	public void setaAssetModel(String aAssetModel) {
		this.aAssetModel = aAssetModel;
	}
	public String getaAssetManufactureNumber() {
		return aAssetManufactureNumber;
	}
	public void setaAssetManufactureNumber(String aAssetManufactureNumber) {
		this.aAssetManufactureNumber = aAssetManufactureNumber;
	}
	public String getaAssetForm() {
		return aAssetForm;
	}
	public void setaAssetForm(String aAssetForm) {
		this.aAssetForm = aAssetForm;
	}
	public String getaAssetIntroducer() {
		return aAssetIntroducer;
	}
	public void setaAssetIntroducer(String aAssetIntroducer) {
		this.aAssetIntroducer = aAssetIntroducer;
	}
	public String getaAssetDate() {
		return aAssetDate;
	}
	public void setaAssetDate(String aAssetDate) {
		this.aAssetDate = aAssetDate;
	}
	public String getaAssetCost() {
		return aAssetCost;
	}
	public void setaAssetCost(String aAssetCost) {
		this.aAssetCost = aAssetCost;
	}
	public String getaAssetImage() {
		return aAssetImage;
	}
	public void setaAssetImage(String aAssetImage) {
		this.aAssetImage = aAssetImage;
	}
	public String getaAssetEtc() {
		return aAssetEtc;
	}
	public void setaAssetEtc(String aAssetEtc) {
		this.aAssetEtc = aAssetEtc;
	}
	public String getePositionName1() {
		return ePositionName1;
	}
	public void setePositionName1(String ePositionName1) {
		this.ePositionName1 = ePositionName1;
	}
	public String geteDeviceType() {
		return eDeviceType;
	}
	public void seteDeviceType(String eDeviceType) {
		this.eDeviceType = eDeviceType;
	}
	public String geteAssetPurpose() {
		return eAssetPurpose;
	}
	public void seteAssetPurpose(String eAssetPurpose) {
		this.eAssetPurpose = eAssetPurpose;
	}
	public String geteNetworkType() {
		return eNetworkType;
	}
	public void seteNetworkType(String eNetworkType) {
		this.eNetworkType = eNetworkType;
	}
	public String geteHostName() {
		return eHostName;
	}
	public void seteHostName(String eHostName) {
		this.eHostName = eHostName;
	}
	public String geteIp() {
		return eIp;
	}
	public void seteIp(String eIp) {
		this.eIp = eIp;
	}
	public String geteOs() {
		return eOs;
	}
	public void seteOs(String eOs) {
		this.eOs = eOs;
	}
	public String geteViewGubun() {
		return eViewGubun;
	}
	public void seteViewGubun(String eViewGubun) {
		this.eViewGubun = eViewGubun;
	}
	public String geteIssueCate() {
		return eIssueCate;
	}
	public void seteIssueCate(String eIssueCate) {
		this.eIssueCate = eIssueCate;
	}
	public String getsSignStatus() {
		return sSignStatus;
	}
	public void setsSignStatus(String sSignStatus) {
		this.sSignStatus = sSignStatus;
	}
	public String getsSignStaffKey() {
		return sSignStaffKey;
	}
	public void setsSignStaffKey(String sSignStaffKey) {
		this.sSignStaffKey = sSignStaffKey;
	}
	public String geteAssetKey() {
		return eAssetKey;
	}
	public void seteAssetKey(String eAssetKey) {
		this.eAssetKey = eAssetKey;
	}
	public String geteIssueKey() {
		return eIssueKey;
	}
	public void seteIssueKey(String eIssueKey) {
		this.eIssueKey = eIssueKey;
	}
	public String geteIssueAssetKey() {
		return eIssueAssetKey;
	}
	public void seteIssueAssetKey(String eIssueAssetKey) {
		this.eIssueAssetKey = eIssueAssetKey;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
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
	public String getTopStartDate() {
		return topStartDate;
	}
	public void setTopStartDate(String topStartDate) {
		this.topStartDate = topStartDate;
	}
	public String getTopEndDate() {
		return topEndDate;
	}
	public void setTopEndDate(String topEndDate) {
		this.topEndDate = topEndDate;
	}
	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	public String getMainTanceType() {
		return mainTanceType;
	}
	public void setMainTanceType(String mainTanceType) {
		this.mainTanceType = mainTanceType;
	}
	public String geteIssueStaffKey() {
		return eIssueStaffKey;
	}
	public void seteIssueStaffKey(String eIssueStaffKey) {
		this.eIssueStaffKey = eIssueStaffKey;
	}
	public String geteIssueStaffName() {
		return eIssueStaffName;
	}
	public void seteIssueStaffName(String eIssueStaffName) {
		this.eIssueStaffName = eIssueStaffName;
	}
	public String geteProcessingDate() {
		return eProcessingDate;
	}
	public void seteProcessingDate(String eProcessingDate) {
		this.eProcessingDate = eProcessingDate;
	}
	public String geteIssueContent() {
		return eIssueContent;
	}
	public void seteIssueContent(String eIssueContent) {
		this.eIssueContent = eIssueContent;
	}
	public String geteRequestDate() {
		return eRequestDate;
	}
	public void seteRequestDate(String eRequestDate) {
		this.eRequestDate = eRequestDate;
	}
	public String geteHandler() {
		return eHandler;
	}
	public void seteHandler(String eHandler) {
		this.eHandler = eHandler;
	}
	public String geteRequester() {
		return eRequester;
	}
	public void seteRequester(String eRequester) {
		this.eRequester = eRequester;
	}
	public String geteIssueType() {
		return eIssueType;
	}
	public void seteIssueType(String eIssueType) {
		this.eIssueType = eIssueType;
	}
	public String geteIssueTypeName() {
		return eIssueTypeName;
	}
	public void seteIssueTypeName(String eIssueTypeName) {
		this.eIssueTypeName = eIssueTypeName;
	}
	public String geteAssetType() {
		return eAssetType;
	}
	public void seteAssetType(String eAssetType) {
		this.eAssetType = eAssetType;
	}
	public String geteAssetTypeName() {
		return eAssetTypeName;
	}
	public void seteAssetTypeName(String eAssetTypeName) {
		this.eAssetTypeName = eAssetTypeName;
	}
	public String geteProcessingType() {
		return eProcessingType;
	}
	public void seteProcessingType(String eProcessingType) {
		this.eProcessingType = eProcessingType;
	}
	public String geteProcessingTypeName() {
		return eProcessingTypeName;
	}
	public void seteProcessingTypeName(String eProcessingTypeName) {
		this.eProcessingTypeName = eProcessingTypeName;
	}
	public String geteCreationDate() {
		return eCreationDate;
	}
	public void seteCreationDate(String eCreationDate) {
		this.eCreationDate = eCreationDate;
	}
	public String geteAuthor() {
		return eAuthor;
	}
	public void seteAuthor(String eAuthor) {
		this.eAuthor = eAuthor;
	}
	public String geteIssueSelect1() {
		return eIssueSelect1;
	}
	public void seteIssueSelect1(String eIssueSelect1) {
		this.eIssueSelect1 = eIssueSelect1;
	}
	public String geteIssueSelect2() {
		return eIssueSelect2;
	}
	public void seteIssueSelect2(String eIssueSelect2) {
		this.eIssueSelect2 = eIssueSelect2;
	}
	public String geteIssueSelect3() {
		return eIssueSelect3;
	}
	public void seteIssueSelect3(String eIssueSelect3) {
		this.eIssueSelect3 = eIssueSelect3;
	}
	public String geteIssueSelect4() {
		return eIssueSelect4;
	}
	public void seteIssueSelect4(String eIssueSelect4) {
		this.eIssueSelect4 = eIssueSelect4;
	}
	public String geteSearchWordA() {
		return eSearchWordA;
	}
	public void seteSearchWordA(String eSearchWordA) {
		this.eSearchWordA = eSearchWordA;
	}
	public String geteSearchWordB() {
		return eSearchWordB;
	}
	public void seteSearchWordB(String eSearchWordB) {
		this.eSearchWordB = eSearchWordB;
	}
	public String geteSearchWordC() {
		return eSearchWordC;
	}
	public void seteSearchWordC(String eSearchWordC) {
		this.eSearchWordC = eSearchWordC;
	}
	public String geteSearchWordD() {
		return eSearchWordD;
	}
	public void seteSearchWordD(String eSearchWordD) {
		this.eSearchWordD = eSearchWordD;
	}
	public String geteSearchWordE() {
		return eSearchWordE;
	}
	public void seteSearchWordE(String eSearchWordE) {
		this.eSearchWordE = eSearchWordE;
	}
	public String geteSearchWordF() {
		return eSearchWordF;
	}
	public void seteSearchWordF(String eSearchWordF) {
		this.eSearchWordF = eSearchWordF;
	}
	public String geteSearchWordG() {
		return eSearchWordG;
	}
	public void seteSearchWordG(String eSearchWordG) {
		this.eSearchWordG = eSearchWordG;
	}
	public String geteSearchWordH() {
		return eSearchWordH;
	}
	public void seteSearchWordH(String eSearchWordH) {
		this.eSearchWordH = eSearchWordH;
	}
	public String geteSearchWordI() {
		return eSearchWordI;
	}
	public void seteSearchWordI(String eSearchWordI) {
		this.eSearchWordI = eSearchWordI;
	}
	public String geteSearchWordJ() {
		return eSearchWordJ;
	}
	public void seteSearchWordJ(String eSearchWordJ) {
		this.eSearchWordJ = eSearchWordJ;
	}
	public String geteSearchWordK() {
		return eSearchWordK;
	}
	public void seteSearchWordK(String eSearchWordK) {
		this.eSearchWordK = eSearchWordK;
	}
	public String geteSearchWordL() {
		return eSearchWordL;
	}
	public void seteSearchWordL(String eSearchWordL) {
		this.eSearchWordL = eSearchWordL;
	}
	public String geteSearchWordM() {
		return eSearchWordM;
	}
	public void seteSearchWordM(String eSearchWordM) {
		this.eSearchWordM = eSearchWordM;
	}
	public String geteSearchWordN() {
		return eSearchWordN;
	}
	public void seteSearchWordN(String eSearchWordN) {
		this.eSearchWordN = eSearchWordN;
	}
	public String geteSearchWordO() {
		return eSearchWordO;
	}
	public void seteSearchWordO(String eSearchWordO) {
		this.eSearchWordO = eSearchWordO;
	}
	public String geteSearchWordP() {
		return eSearchWordP;
	}
	public void seteSearchWordP(String eSearchWordP) {
		this.eSearchWordP = eSearchWordP;
	}
	public String geteSearchWordQ() {
		return eSearchWordQ;
	}
	public void seteSearchWordQ(String eSearchWordQ) {
		this.eSearchWordQ = eSearchWordQ;
	}
	public String geteSearchWordR() {
		return eSearchWordR;
	}
	public void seteSearchWordR(String eSearchWordR) {
		this.eSearchWordR = eSearchWordR;
	}
	public String geteSearchWordS() {
		return eSearchWordS;
	}
	public void seteSearchWordS(String eSearchWordS) {
		this.eSearchWordS = eSearchWordS;
	}
	public String geteSearchWordT() {
		return eSearchWordT;
	}
	public void seteSearchWordT(String eSearchWordT) {
		this.eSearchWordT = eSearchWordT;
	}
	public String geteSearchWordU() {
		return eSearchWordU;
	}
	public void seteSearchWordU(String eSearchWordU) {
		this.eSearchWordU = eSearchWordU;
	}
	public String geteSearchWordV() {
		return eSearchWordV;
	}
	public void seteSearchWordV(String eSearchWordV) {
		this.eSearchWordV = eSearchWordV;
	}
	public String geteSearchWordW() {
		return eSearchWordW;
	}
	public void seteSearchWordW(String eSearchWordW) {
		this.eSearchWordW = eSearchWordW;
	}
	public String geteSearchWordX() {
		return eSearchWordX;
	}
	public void seteSearchWordX(String eSearchWordX) {
		this.eSearchWordX = eSearchWordX;
	}
	public String geteSearchWordY() {
		return eSearchWordY;
	}
	public void seteSearchWordY(String eSearchWordY) {
		this.eSearchWordY = eSearchWordY;
	}
	public String geteSearchWordZ() {
		return eSearchWordZ;
	}
	public void seteSearchWordZ(String eSearchWordZ) {
		this.eSearchWordZ = eSearchWordZ;
	}
	public String geteFileID() {
		return eFileID;
	}
	public void seteFileID(String eFileID) {
		this.eFileID = eFileID;
	}
	public String geteFileName() {
		return eFileName;
	}
	public void seteFileName(String eFileName) {
		this.eFileName = eFileName;
	}
	public String geteFileExt() {
		return eFileExt;
	}
	public void seteFileExt(String eFileExt) {
		this.eFileExt = eFileExt;
	}
	  
	 
}
