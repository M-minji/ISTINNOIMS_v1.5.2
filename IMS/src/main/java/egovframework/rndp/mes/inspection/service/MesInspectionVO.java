package egovframework.rndp.mes.inspection.service;

import egovframework.rndp.com.cmm.service.DefultVO;

/**
 * @author R-PC
 *
 */
public class MesInspectionVO extends DefultVO{
	 
	private String eTopStartDate					= "";
	private String eTopEndDate					= "";
	
	private String eSearchTypeSet1					= "";
	private String eSearchTypeSet2					= "";
	private String eSearchTypeSet3					= "";
	private String eSearchTypeSet4					= "";
	private String eSearchTypeSet5					= "";
	private String eSearchTypeSet6					= "";
	private String eSearchTypeSet7					= "";
	private String eSearchTypeSet8					= "";
	private String eSearchTypeSet9					= "";
	

	private String searchType					= "";
	private String searchWord					= "";
	

	private String eViewGubun					= "";
	private String kStaffKey					= "";
	private String eStaffName					= "";
	private String eStaffKey					= "";
	private String eAssetKey					= "";
	private String eInspectionKey					= "";
	
	
	private String eRegistrant					= ""; //작성자
	private String eRegistrationDate					= ""; //작성일
	private String eInspectionDate					= "";
	private String eInspectionTypeName					= "";
	private String eInspectionType					= "";
	private String eInspectionCycleName					= "";
	private String eInspectionCycle					= "";
	private String eRequester					= "";//요청자
	private String eOrganization					= "";//기관
	private String eDepartment					= "";//부서
	private String eInspector					= ""; //점검자
	private String eInspectorOrg					= ""; //점검자
	private String eInspectionResult					= "";//결과
	private String eRemark					= "";//결과
	private String eOther					= "";//결과
	
	private String eFileID					= "";//파일 id
	private String eFileName					= "";//파일명
	private String eFileExt					= "";//확장자
	
	private String eField1					= "";
	private String eField2					= "";
	private String eField3					= "";
	private String eField4					= "";
	private String eField5					= "";
	private String eField6					= ""; 	
	private String eField7					= "";	
	private String eField8					= "";	
	private String eField9					= "";

	private String eFieldKey					= "";
	private String eFieldName					= "";
	private String eFieldStatus					= "";
	
	
	private String eItemRemark					= "";
	private String eItemOther					= "";
	private String eItemInspectionDate					= "";
	private String eStatus					= "";
	
	private String eAssetName					= "";
	private String eAssetMaker					= "";
	private String eAssetModel					= "";
	private String eAssetManufactureNumber					= "";
	private String eAssetForm					= "";
	private String eAssetIntroducer					= "";
	private String eAssetDate					= "";
	private String eAssetCost					= "";
	private String eAssetImage					= "";
	private String eAssetEtc					= "";
	private String ePositionName1					= "";
	private String eDeviceType					= "";
	private String eAssetPurpose					= "";
	private String eNetworkType					= "";
	private String eHostName					= "";
	private String eIp					= "";
	private String eOs					= "";
	private String eAssetType					= "";
	
	
	private String  oSignPass                        = "";        
	private String  sSignTableKey                        = "";        
	private String  sSignTableName                        = "";        
	private String  sSignDecison                        = "";        
	private String  sSignContent                        = "";        
	private String  sSignProgress                        = "";        
	private String  sSignStaffKey                        = "";        
	private String  sSignStaffName                        = "";        
	private String  sSignStaffGubun                        = "";        
	private String  sSignStaffPosition                        = "";        
	private String  sSignStatus                        = "";        
	private String  ePageGubun                        = "";     
	private String  ePageInfo                        = "";            
	 
	
	
	public String getePageInfo() {
		return ePageInfo;
	}
	public void setePageInfo(String ePageInfo) {
		this.ePageInfo = ePageInfo;
	}
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
	public String getePageGubun() {
		return ePageGubun;
	}
	public void setePageGubun(String ePageGubun) {
		this.ePageGubun = ePageGubun;
	}
	public String geteInspectorOrg() {
		return eInspectorOrg;
	}
	public void seteInspectorOrg(String eInspectorOrg) {
		this.eInspectorOrg = eInspectorOrg;
	}
	public String getsSignStaffKey() {
		return sSignStaffKey;
	}
	public void setsSignStaffKey(String sSignStaffKey) {
		this.sSignStaffKey = sSignStaffKey;
	}
	public String getsSignStatus() {
		return sSignStatus;
	}
	public void setsSignStatus(String sSignStatus) {
		this.sSignStatus = sSignStatus;
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
	public String geteAssetType() {
		return eAssetType;
	}
	public void seteAssetType(String eAssetType) {
		this.eAssetType = eAssetType;
	}
	public String geteAssetName() {
		return eAssetName;
	}
	public void seteAssetName(String eAssetName) {
		this.eAssetName = eAssetName;
	}
	public String geteAssetMaker() {
		return eAssetMaker;
	}
	public void seteAssetMaker(String eAssetMaker) {
		this.eAssetMaker = eAssetMaker;
	}
	public String geteAssetModel() {
		return eAssetModel;
	}
	public void seteAssetModel(String eAssetModel) {
		this.eAssetModel = eAssetModel;
	}
	public String geteAssetManufactureNumber() {
		return eAssetManufactureNumber;
	}
	public void seteAssetManufactureNumber(String eAssetManufactureNumber) {
		this.eAssetManufactureNumber = eAssetManufactureNumber;
	}
	public String geteAssetForm() {
		return eAssetForm;
	}
	public void seteAssetForm(String eAssetForm) {
		this.eAssetForm = eAssetForm;
	}
	public String geteAssetIntroducer() {
		return eAssetIntroducer;
	}
	public void seteAssetIntroducer(String eAssetIntroducer) {
		this.eAssetIntroducer = eAssetIntroducer;
	}
	public String geteAssetDate() {
		return eAssetDate;
	}
	public void seteAssetDate(String eAssetDate) {
		this.eAssetDate = eAssetDate;
	}
	public String geteAssetCost() {
		return eAssetCost;
	}
	public void seteAssetCost(String eAssetCost) {
		this.eAssetCost = eAssetCost;
	}
	public String geteAssetImage() {
		return eAssetImage;
	}
	public void seteAssetImage(String eAssetImage) {
		this.eAssetImage = eAssetImage;
	}
	public String geteAssetEtc() {
		return eAssetEtc;
	}
	public void seteAssetEtc(String eAssetEtc) {
		this.eAssetEtc = eAssetEtc;
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
	public String geteStatus() {
		return eStatus;
	}
	public void seteStatus(String eStatus) {
		this.eStatus = eStatus;
	}
	public String geteItemInspectionDate() {
		return eItemInspectionDate;
	}
	public void seteItemInspectionDate(String eItemInspectionDate) {
		this.eItemInspectionDate = eItemInspectionDate;
	}
	public String geteItemRemark() {
		return eItemRemark;
	}
	public void seteItemRemark(String eItemRemark) {
		this.eItemRemark = eItemRemark;
	}
	public String geteItemOther() {
		return eItemOther;
	}
	public void seteItemOther(String eItemOther) {
		this.eItemOther = eItemOther;
	}
	public String geteField1() {
		return eField1;
	}
	public void seteField1(String eField1) {
		this.eField1 = eField1;
	}
	public String geteField2() {
		return eField2;
	}
	public void seteField2(String eField2) {
		this.eField2 = eField2;
	}
	public String geteField3() {
		return eField3;
	}
	public void seteField3(String eField3) {
		this.eField3 = eField3;
	}
	public String geteField4() {
		return eField4;
	}
	public void seteField4(String eField4) {
		this.eField4 = eField4;
	}
	public String geteField5() {
		return eField5;
	}
	public void seteField5(String eField5) {
		this.eField5 = eField5;
	}
	public String geteField6() {
		return eField6;
	}
	public void seteField6(String eField6) {
		this.eField6 = eField6;
	}
	public String geteField7() {
		return eField7;
	}
	public void seteField7(String eField7) {
		this.eField7 = eField7;
	}
	public String geteField8() {
		return eField8;
	}
	public void seteField8(String eField8) {
		this.eField8 = eField8;
	}
	public String geteField9() {
		return eField9;
	}
	public void seteField9(String eField9) {
		this.eField9 = eField9;
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
	public String geteRegistrant() {
		return eRegistrant;
	}
	public void seteRegistrant(String eRegistrant) {
		this.eRegistrant = eRegistrant;
	}
	public String geteRegistrationDate() {
		return eRegistrationDate;
	}
	public void seteRegistrationDate(String eRegistrationDate) {
		this.eRegistrationDate = eRegistrationDate;
	}
	public String geteInspectionDate() {
		return eInspectionDate;
	}
	public void seteInspectionDate(String eInspectionDate) {
		this.eInspectionDate = eInspectionDate;
	}
	public String geteInspectionTypeName() {
		return eInspectionTypeName;
	}
	public void seteInspectionTypeName(String eInspectionTypeName) {
		this.eInspectionTypeName = eInspectionTypeName;
	}
	public String geteInspectionType() {
		return eInspectionType;
	}
	public void seteInspectionType(String eInspectionType) {
		this.eInspectionType = eInspectionType;
	}
	public String geteInspectionCycleName() {
		return eInspectionCycleName;
	}
	public void seteInspectionCycleName(String eInspectionCycleName) {
		this.eInspectionCycleName = eInspectionCycleName;
	}
	public String geteInspectionCycle() {
		return eInspectionCycle;
	}
	public void seteInspectionCycle(String eInspectionCycle) {
		this.eInspectionCycle = eInspectionCycle;
	}
	public String geteRequester() {
		return eRequester;
	}
	public void seteRequester(String eRequester) {
		this.eRequester = eRequester;
	}
	public String geteOrganization() {
		return eOrganization;
	}
	public void seteOrganization(String eOrganization) {
		this.eOrganization = eOrganization;
	}
	public String geteDepartment() {
		return eDepartment;
	}
	public void seteDepartment(String eDepartment) {
		this.eDepartment = eDepartment;
	}
	public String geteInspector() {
		return eInspector;
	}
	public void seteInspector(String eInspector) {
		this.eInspector = eInspector;
	}
	public String geteInspectionResult() {
		return eInspectionResult;
	}
	public void seteInspectionResult(String eInspectionResult) {
		this.eInspectionResult = eInspectionResult;
	}
	public String geteRemark() {
		return eRemark;
	}
	public void seteRemark(String eRemark) {
		this.eRemark = eRemark;
	}
	public String geteOther() {
		return eOther;
	}
	public void seteOther(String eOther) {
		this.eOther = eOther;
	}
	public String geteSearchTypeSet1() {
		return eSearchTypeSet1;
	}
	public void seteSearchTypeSet1(String eSearchTypeSet1) {
		this.eSearchTypeSet1 = eSearchTypeSet1;
	}
	public String geteSearchTypeSet2() {
		return eSearchTypeSet2;
	}
	public void seteSearchTypeSet2(String eSearchTypeSet2) {
		this.eSearchTypeSet2 = eSearchTypeSet2;
	}
	public String geteSearchTypeSet3() {
		return eSearchTypeSet3;
	}
	public void seteSearchTypeSet3(String eSearchTypeSet3) {
		this.eSearchTypeSet3 = eSearchTypeSet3;
	}
	public String geteSearchTypeSet4() {
		return eSearchTypeSet4;
	}
	public void seteSearchTypeSet4(String eSearchTypeSet4) {
		this.eSearchTypeSet4 = eSearchTypeSet4;
	}
	public String geteSearchTypeSet5() {
		return eSearchTypeSet5;
	}
	public void seteSearchTypeSet5(String eSearchTypeSet5) {
		this.eSearchTypeSet5 = eSearchTypeSet5;
	}
	public String geteSearchTypeSet6() {
		return eSearchTypeSet6;
	}
	public void seteSearchTypeSet6(String eSearchTypeSet6) {
		this.eSearchTypeSet6 = eSearchTypeSet6;
	}
	public String geteSearchTypeSet7() {
		return eSearchTypeSet7;
	}
	public void seteSearchTypeSet7(String eSearchTypeSet7) {
		this.eSearchTypeSet7 = eSearchTypeSet7;
	}
	public String geteSearchTypeSet8() {
		return eSearchTypeSet8;
	}
	public void seteSearchTypeSet8(String eSearchTypeSet8) {
		this.eSearchTypeSet8 = eSearchTypeSet8;
	}
	public String geteSearchTypeSet9() {
		return eSearchTypeSet9;
	}
	public void seteSearchTypeSet9(String eSearchTypeSet9) {
		this.eSearchTypeSet9 = eSearchTypeSet9;
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
	public String geteStaffName() {
		return eStaffName;
	}
	public void seteStaffName(String eStaffName) {
		this.eStaffName = eStaffName;
	}
	public String geteStaffKey() {
		return eStaffKey;
	}
	public void seteStaffKey(String eStaffKey) {
		this.eStaffKey = eStaffKey;
	}
	public String geteAssetKey() {
		return eAssetKey;
	}
	public void seteAssetKey(String eAssetKey) {
		this.eAssetKey = eAssetKey;
	}
	public String geteInspectionKey() {
		return eInspectionKey;
	}
	public void seteInspectionKey(String eInspectionKey) {
		this.eInspectionKey = eInspectionKey;
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
	public String geteFieldKey() {
		return eFieldKey;
	}
	public void seteFieldKey(String eFieldKey) {
		this.eFieldKey = eFieldKey;
	}
	public String geteFieldName() {
		return eFieldName;
	}
	public void seteFieldName(String eFieldName) {
		this.eFieldName = eFieldName;
	}
	public String geteFieldStatus() {
		return eFieldStatus;
	}
	public void seteFieldStatus(String eFieldStatus) {
		this.eFieldStatus = eFieldStatus;
	}
	
	
	
}
