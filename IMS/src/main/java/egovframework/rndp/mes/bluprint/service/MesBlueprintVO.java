package egovframework.rndp.mes.bluprint.service;

import egovframework.rndp.com.cmm.service.DefultVO;

public class MesBlueprintVO extends DefultVO{

	private String searchWord				= "";
	private String searchType				= "";
	private String kStaffKey				= "";
	private String kStaffName				= "";
	private String topStartDate				= "";
	private String topEndDate				= "";
	private String excelFilePath     		= "";
	private String eViewGubun     		= "";
	
	// 파일첨부추가
	private String eFileID					= "";
	private String eFileName				= "";
	private String eFileExt					= "";
	private String ePageGubun					= "";
	
	
	private String  pkey			= "";
	private String 	blueprintKey 	= "";
	private String	blueprintName 	= "";
	private String	blueprintNumber	= "";
	private String  blueprintWdate 	= "";
	private String  blueprintUdate 	= "";
	private String  blueprintEtc 	= "";

	private String 	eItemKey 		= "";
	private String	eItemName 		= "";
	private String	eItemStandard	= "";
	private String  eItemCateKey 	= "";
	private String  eItemCatePath 	= "";
	private String  eItemCateName 	= "";
	private String  eItemCode 		= "";
	private String  eItemUnit		= "";
	private String  eItemUnitTxt	= "";

	private String sItemKey			= "";
	private String sItemName		= "";
	private String sItemUnit		= "";
	private String sItemUnitTxt		= "";
	private String sItemCode		= "";
	private String sItemStandard	= "";
	private String sItemCateKey		= "";
	private String sItemCateName	= "";
	private String sItemCatePath	= "";
	
	private String  blueprintItemKey		= "";
	private String 	blueprintItemCount	= "";
	private String	blueprintItemEtc		= "";
	private String	blueprintItemFileId		= "";
	private String	blueprintItemFileIdsn		= "";
	private String	blueprintItemOriginFileName		= "";
	private String	newPrint		= "";
	private String	pastImg		= "";
	private String	blueprintItemEtc_past		= "";
	private String	eItemGubun		= "";

	private String	eGubunPage						= "";
	private String  eIMGregName      				= "";
	private String  eAddFileId						= "";
	private String  eAddFileId1						= "";
	private String  eAddFileId7						= "";
	private String  AtchFileName					= "";
	private String  AtchFileName1					= "";
	private String  eBlueprintFileGubun				= "";
	private String  eBlueprintKey					= "";
	private String  eBlueprintFileKey				= "";
	
	private String	eBlueprintVersion				= "";
	private String	eBlueprintVersionCount				= "";
	private String	eBlueprintItemVersion				= "";
	private String	eBlueprintItemEtc				= "";
	private String	version				= "";
	
	// 결재관련
	private String sSignKey							= "";
	private String sSignStatus						= "";
	private String sSignDecison						= "";
	private String sSignContent						= "";
	private String sSignStaffKey					= "";
	private String sSignStaffName					= "";
	private String sSignSequence                 = "";
	private String eBlueprintProcessFile					= "";
	
	private String sSignItemKeyBlpi                  = "";
	private String sSignKeyBlpi                      = "";
	private String sSignSequenceBlpi                 = "";
	private String sSignTableKeyBlpi                 = "";
	private String sSignTableNameBlpi                = "";
	private String sSignStaffKeyBlpi                 = "";
	private String sSignStaffNameBlpi                = "";
	private String sSignDecisonBlpi                  = "";
	private String sSignContentBlpi                  = "";
	private String sSignStatusCount                  = "";
	private String sSignCount                  = "";
	private String sSignCountTot                  = "";
	private String gubun                  = "";
	private String oSignPass                  = "";
	
	private String eReqOrg                  = "";
	private String eReqDept                  = "";
	private String eRequester                  = "";
	private String eRequesterOrg                  = "";
	private String eReqDate                  = "";
	private String eReqContent                  = "";
	private String eWorkStart                  = "";
	private String eWorkEnd                  = "";
	private String eAffiliation                  = "";
	private String eWorkerName                  = "";
	private String eWorkDate                  = "";
	private String eIsInterrupted                  = "";
	private String eWorkDetails                  = "";
	private String eAssetKey                  = "";
	private String eChangeKey                  = "";
	private String eIssueKey                  = "";
	private String eStatus                  = "";
	
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
	
	
	private String  eIssueCause                        = "";        
	private String  eSolutionMethod                        = "";        
	
	private String  eRowIndex                        = "";        
	private String  eDepartment                        = "";        
	private String  eWorker                        = "";        
	private String  eDescription                        = "";        
	private String  eFileRowId                        = "";        
	private String  eFileRowName                        = "";        
	private String  eFileRowIndex                        = "";        
	
	private String  eRowDepartment                        = "";        
	private String  eRowWorker                        = "";        
	private String  eRowPosition                        = "";        
	private String  eRowContact                        = "";        
	private String  eRowNotes                        = "";        
	
	private String  sSignTableKey                        = "";        
	private String  sSignTableName                        = "";        
	private String  sSignProgress                        = "";        
	private String  sSignStaffGubun                        = "";        
	private String  sSignStaffPosition                        = "";        
	private String  eIsInterruptedName                        = "";        
	
	private String eTextSearchWord1				= "";
	private String eTextSearchWord2				= "";
	private String eTextSearchWord3				= "";
	private String eTextSearchWord4				= "";
	private String eTextSearchWord5				= "";
	private String eTextSearchWord6				= "";
	private String eTextSearchWord7				= "";
	private String eTextSearchWord8				= "";
	
	

	public String geteViewGubun() {
		return eViewGubun;
	}

	public void seteViewGubun(String eViewGubun) {
		this.eViewGubun = eViewGubun;
	}
	public String geteTextSearchWord1() {
		return eTextSearchWord1;
	}

	public void seteTextSearchWord1(String eTextSearchWord1) {
		this.eTextSearchWord1 = eTextSearchWord1;
	}

	public String geteTextSearchWord2() {
		return eTextSearchWord2;
	}

	public void seteTextSearchWord2(String eTextSearchWord2) {
		this.eTextSearchWord2 = eTextSearchWord2;
	}

	public String geteTextSearchWord3() {
		return eTextSearchWord3;
	}

	public void seteTextSearchWord3(String eTextSearchWord3) {
		this.eTextSearchWord3 = eTextSearchWord3;
	}

	public String geteTextSearchWord4() {
		return eTextSearchWord4;
	}

	public void seteTextSearchWord4(String eTextSearchWord4) {
		this.eTextSearchWord4 = eTextSearchWord4;
	}

	public String geteTextSearchWord5() {
		return eTextSearchWord5;
	}

	public void seteTextSearchWord5(String eTextSearchWord5) {
		this.eTextSearchWord5 = eTextSearchWord5;
	}

	public String geteTextSearchWord6() {
		return eTextSearchWord6;
	}

	public void seteTextSearchWord6(String eTextSearchWord6) {
		this.eTextSearchWord6 = eTextSearchWord6;
	}

	public String geteTextSearchWord7() {
		return eTextSearchWord7;
	}

	public void seteTextSearchWord7(String eTextSearchWord7) {
		this.eTextSearchWord7 = eTextSearchWord7;
	}

	public String geteTextSearchWord8() {
		return eTextSearchWord8;
	}

	public void seteTextSearchWord8(String eTextSearchWord8) {
		this.eTextSearchWord8 = eTextSearchWord8;
	}

	public String geteRequesterOrg() {
		return eRequesterOrg;
	}

	public void seteRequesterOrg(String eRequesterOrg) {
		this.eRequesterOrg = eRequesterOrg;
	}

	public String geteIsInterruptedName() {
		return eIsInterruptedName;
	}

	public void seteIsInterruptedName(String eIsInterruptedName) {
		this.eIsInterruptedName = eIsInterruptedName;
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

	public String geteRowDepartment() {
		return eRowDepartment;
	}

	public void seteRowDepartment(String eRowDepartment) {
		this.eRowDepartment = eRowDepartment;
	}

	public String geteRowWorker() {
		return eRowWorker;
	}

	public void seteRowWorker(String eRowWorker) {
		this.eRowWorker = eRowWorker;
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

	public String geteRowIndex() {
		return eRowIndex;
	}

	public void seteRowIndex(String eRowIndex) {
		this.eRowIndex = eRowIndex;
	}

	public String geteDepartment() {
		return eDepartment;
	}

	public void seteDepartment(String eDepartment) {
		this.eDepartment = eDepartment;
	}

	public String geteWorker() {
		return eWorker;
	}

	public void seteWorker(String eWorker) {
		this.eWorker = eWorker;
	}

	public String geteDescription() {
		return eDescription;
	}

	public void seteDescription(String eDescription) {
		this.eDescription = eDescription;
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

	public String geteFileRowIndex() {
		return eFileRowIndex;
	}

	public void seteFileRowIndex(String eFileRowIndex) {
		this.eFileRowIndex = eFileRowIndex;
	}

	public String geteIssueCause() {
		return eIssueCause;
	}

	public void seteIssueCause(String eIssueCause) {
		this.eIssueCause = eIssueCause;
	}

	public String geteSolutionMethod() {
		return eSolutionMethod;
	}

	public void seteSolutionMethod(String eSolutionMethod) {
		this.eSolutionMethod = eSolutionMethod;
	}

	public String geteIssueKey() {
		return eIssueKey;
	}

	public void seteIssueKey(String eIssueKey) {
		this.eIssueKey = eIssueKey;
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

	public String geteStatus() {
		return eStatus;
	}

	public void seteStatus(String eStatus) {
		this.eStatus = eStatus;
	}

	public String geteChangeKey() {
		return eChangeKey;
	}

	public void seteChangeKey(String eChangeKey) {
		this.eChangeKey = eChangeKey;
	}

	public String geteAssetKey() {
		return eAssetKey;
	}

	public void seteAssetKey(String eAssetKey) {
		this.eAssetKey = eAssetKey;
	}

	public String geteReqOrg() {
		return eReqOrg;
	}

	public void seteReqOrg(String eReqOrg) {
		this.eReqOrg = eReqOrg;
	}

	public String geteReqDept() {
		return eReqDept;
	}

	public void seteReqDept(String eReqDept) {
		this.eReqDept = eReqDept;
	}

	public String geteRequester() {
		return eRequester;
	}

	public void seteRequester(String eRequester) {
		this.eRequester = eRequester;
	}

	public String geteReqDate() {
		return eReqDate;
	}

	public void seteReqDate(String eReqDate) {
		this.eReqDate = eReqDate;
	}

	public String geteReqContent() {
		return eReqContent;
	}

	public void seteReqContent(String eReqContent) {
		this.eReqContent = eReqContent;
	}

	public String geteWorkStart() {
		return eWorkStart;
	}

	public void seteWorkStart(String eWorkStart) {
		this.eWorkStart = eWorkStart;
	}

	public String geteWorkEnd() {
		return eWorkEnd;
	}

	public void seteWorkEnd(String eWorkEnd) {
		this.eWorkEnd = eWorkEnd;
	}

	public String geteAffiliation() {
		return eAffiliation;
	}

	public void seteAffiliation(String eAffiliation) {
		this.eAffiliation = eAffiliation;
	}

	public String geteWorkerName() {
		return eWorkerName;
	}

	public void seteWorkerName(String eWorkerName) {
		this.eWorkerName = eWorkerName;
	}

	public String geteWorkDate() {
		return eWorkDate;
	}

	public void seteWorkDate(String eWorkDate) {
		this.eWorkDate = eWorkDate;
	}

	public String geteIsInterrupted() {
		return eIsInterrupted;
	}

	public void seteIsInterrupted(String eIsInterrupted) {
		this.eIsInterrupted = eIsInterrupted;
	}

	public String geteWorkDetails() {
		return eWorkDetails;
	}

	public void seteWorkDetails(String eWorkDetails) {
		this.eWorkDetails = eWorkDetails;
	}

	public String getoSignPass() {
		return oSignPass;
	}

	public void setoSignPass(String oSignPass) {
		this.oSignPass = oSignPass;
	}

	public String getsSignCountTot() {
		return sSignCountTot;
	}

	public void setsSignCountTot(String sSignCountTot) {
		this.sSignCountTot = sSignCountTot;
	}

	public String getsSignCount() {
		return sSignCount;
	}

	public void setsSignCount(String sSignCount) {
		this.sSignCount = sSignCount;
	}

	public String getGubun() {
		return gubun;
	}

	public void setGubun(String gubun) {
		this.gubun = gubun;
	}

	public String getsSignStatusCount() {
		return sSignStatusCount;
	}

	public void setsSignStatusCount(String sSignStatusCount) {
		this.sSignStatusCount = sSignStatusCount;
	}

	public String getsSignSequence() {
		return sSignSequence;
	}

	public void setsSignSequence(String sSignSequence) {
		this.sSignSequence = sSignSequence;
	}

	public String getsSignItemKeyBlpi() {
		return sSignItemKeyBlpi;
	}

	public void setsSignItemKeyBlpi(String sSignItemKeyBlpi) {
		this.sSignItemKeyBlpi = sSignItemKeyBlpi;
	}

	public String getsSignKeyBlpi() {
		return sSignKeyBlpi;
	}

	public void setsSignKeyBlpi(String sSignKeyBlpi) {
		this.sSignKeyBlpi = sSignKeyBlpi;
	}

	public String getsSignSequenceBlpi() {
		return sSignSequenceBlpi;
	}

	public void setsSignSequenceBlpi(String sSignSequenceBlpi) {
		this.sSignSequenceBlpi = sSignSequenceBlpi;
	}

	public String getsSignTableKeyBlpi() {
		return sSignTableKeyBlpi;
	}

	public void setsSignTableKeyBlpi(String sSignTableKeyBlpi) {
		this.sSignTableKeyBlpi = sSignTableKeyBlpi;
	}

	public String getsSignTableNameBlpi() {
		return sSignTableNameBlpi;
	}

	public void setsSignTableNameBlpi(String sSignTableNameBlpi) {
		this.sSignTableNameBlpi = sSignTableNameBlpi;
	}

	public String getsSignStaffKeyBlpi() {
		return sSignStaffKeyBlpi;
	}

	public void setsSignStaffKeyBlpi(String sSignStaffKeyBlpi) {
		this.sSignStaffKeyBlpi = sSignStaffKeyBlpi;
	}

	public String getsSignStaffNameBlpi() {
		return sSignStaffNameBlpi;
	}

	public void setsSignStaffNameBlpi(String sSignStaffNameBlpi) {
		this.sSignStaffNameBlpi = sSignStaffNameBlpi;
	}

	public String getsSignDecisonBlpi() {
		return sSignDecisonBlpi;
	}

	public void setsSignDecisonBlpi(String sSignDecisonBlpi) {
		this.sSignDecisonBlpi = sSignDecisonBlpi;
	}

	public String getsSignContentBlpi() {
		return sSignContentBlpi;
	}

	public void setsSignContentBlpi(String sSignContentBlpi) {
		this.sSignContentBlpi = sSignContentBlpi;
	}

	public String geteBlueprintProcessFile() {
		return eBlueprintProcessFile;
	}

	public void seteBlueprintProcessFile(String eBlueprintProcessFile) {
		this.eBlueprintProcessFile = eBlueprintProcessFile;
	}

	public String getsSignKey() {
		return sSignKey;
	}

	public void setsSignKey(String sSignKey) {
		this.sSignKey = sSignKey;
	}

	public String getsSignStatus() {
		return sSignStatus;
	}

	public void setsSignStatus(String sSignStatus) {
		this.sSignStatus = sSignStatus;
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String geteBlueprintItemVersion() {
		return eBlueprintItemVersion;
	}

	public void seteBlueprintItemVersion(String eBlueprintItemVersion) {
		this.eBlueprintItemVersion = eBlueprintItemVersion;
	}

	public String geteBlueprintVersionCount() {
		return eBlueprintVersionCount;
	}

	public void seteBlueprintVersionCount(String eBlueprintVersionCount) {
		this.eBlueprintVersionCount = eBlueprintVersionCount;
	}

	public String getBlueprintUdate() {
		return blueprintUdate;
	}

	public void setBlueprintUdate(String blueprintUdate) {
		this.blueprintUdate = blueprintUdate;
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

	public String geteBlueprintFileGubun() {
		return eBlueprintFileGubun;
	}

	public void seteBlueprintFileGubun(String eBlueprintFileGubun) {
		this.eBlueprintFileGubun = eBlueprintFileGubun;
	}

	public String geteBlueprintKey() {
		return eBlueprintKey;
	}

	public void seteBlueprintKey(String eBlueprintKey) {
		this.eBlueprintKey = eBlueprintKey;
	}

	public String geteBlueprintFileKey() {
		return eBlueprintFileKey;
	}

	public void seteBlueprintFileKey(String eBlueprintFileKey) {
		this.eBlueprintFileKey = eBlueprintFileKey;
	}

	public String geteBlueprintItemEtc() {
		return eBlueprintItemEtc;
	}

	public void seteBlueprintItemEtc(String eBlueprintItemEtc) {
		this.eBlueprintItemEtc = eBlueprintItemEtc;
	}

	public String geteAddFileId1() {
		return eAddFileId1;
	}

	public void seteAddFileId1(String eAddFileId1) {
		this.eAddFileId1 = eAddFileId1;
	}

	public String geteBlueprintVersion() {
		return eBlueprintVersion;
	}

	public void seteBlueprintVersion(String eBlueprintVersion) {
		this.eBlueprintVersion = eBlueprintVersion;
	}

	public String geteItemGubun() {
		return eItemGubun;
	}

	public void seteItemGubun(String eItemGubun) {
		this.eItemGubun = eItemGubun;
	}

	public String getBlueprintItemEtc_past() {
		return blueprintItemEtc_past;
	}

	public void setBlueprintItemEtc_past(String blueprintItemEtc_past) {
		this.blueprintItemEtc_past = blueprintItemEtc_past;
	}

	public String getPastImg() {
		return pastImg;
	}

	public void setPastImg(String pastImg) {
		this.pastImg = pastImg;
	}

	public String getNewPrint() {
		return newPrint;
	}

	public void setNewPrint(String newPrint) {
		this.newPrint = newPrint;
	}

	public String getBlueprintItemOriginFileName() {
		return blueprintItemOriginFileName;
	}

	public void setBlueprintItemOriginFileName(String blueprintItemOriginFileName) {
		this.blueprintItemOriginFileName = blueprintItemOriginFileName;
	}

	public String getBlueprintItemFileIdsn() {
		return blueprintItemFileIdsn;
	}

	public void setBlueprintItemFileIdsn(String blueprintItemFileIdsn) {
		this.blueprintItemFileIdsn = blueprintItemFileIdsn;
	}

	public String getBlueprintItemFileId() {
		return blueprintItemFileId;
	}

	public void setBlueprintItemFileId(String blueprintItemFileId) {
		this.blueprintItemFileId = blueprintItemFileId;
	}

	public String getBlueprintNumber() {
		return blueprintNumber;
	}

	public void setBlueprintNumber(String blueprintNumber) {
		this.blueprintNumber = blueprintNumber;
	}

	public String geteItemUnit() {
		return eItemUnit;
	}

	public void seteItemUnit(String eItemUnit) {
		this.eItemUnit = eItemUnit;
	}

	public String getSearchWord() {
		return searchWord;
	}
	public String getPkey() {
		return pkey;
	}
	public void setPkey(String pkey) {
		this.pkey = pkey;
	}
	public String getBlueprintItemKey() {
		return blueprintItemKey;
	}
	public void setBlueprintItemKey(String BlueprintItemKey) {
		this.blueprintItemKey = BlueprintItemKey;
	}
	public String getBlueprintItemCount() {
		return blueprintItemCount;
	}
	public void setBlueprintItemCount(String BlueprintItemCount) {
		this.blueprintItemCount = BlueprintItemCount;
	}
	public String getBlueprintItemEtc() {
		return blueprintItemEtc;
	}
	public void setBlueprintItemEtc(String BlueprintItemEtc) {
		this.blueprintItemEtc = BlueprintItemEtc;
	}
	public String getsItemKey() {
		return sItemKey;
	}
	public void setsItemKey(String sItemKey) {
		this.sItemKey = sItemKey;
	}
	public String getsItemName() {
		return sItemName;
	}
	public void setsItemName(String sItemName) {
		this.sItemName = sItemName;
	}
	public String getsItemUnit() {
		return sItemUnit;
	}
	public void setsItemUnit(String sItemUnit) {
		this.sItemUnit = sItemUnit;
	}
	public String getsItemUnitTxt() {
		return sItemUnitTxt;
	}
	public void setsItemUnitTxt(String sItemUnitTxt) {
		this.sItemUnitTxt = sItemUnitTxt;
	}
	public String getsItemCode() {
		return sItemCode;
	}
	public void setsItemCode(String sItemCode) {
		this.sItemCode = sItemCode;
	}
	public String getsItemStandard() {
		return sItemStandard;
	}
	public void setsItemStandard(String sItemStandard) {
		this.sItemStandard = sItemStandard;
	}
	public String getsItemCateKey() {
		return sItemCateKey;
	}
	public void setsItemCateKey(String sItemCateKey) {
		this.sItemCateKey = sItemCateKey;
	}
	public String getsItemCateName() {
		return sItemCateName;
	}
	public void setsItemCateName(String sItemCateName) {
		this.sItemCateName = sItemCateName;
	}
	public String getsItemCatePath() {
		return sItemCatePath;
	}
	public void setsItemCatePath(String sItemCatePath) {
		this.sItemCatePath = sItemCatePath;
	}
	public String geteItemStandard() {
		return eItemStandard;
	}
	public void seteItemStandard(String eItemStandard) {
		this.eItemStandard = eItemStandard;
	}
	public String geteItemCatePath() {
		return eItemCatePath;
	}
	public void seteItemCatePath(String eItemCatePath) {
		this.eItemCatePath = eItemCatePath;
	}
	public String geteItemCateName() {
		return eItemCateName;
	}
	public void seteItemCateName(String eItemCateName) {
		this.eItemCateName = eItemCateName;
	}
	public String geteItemCode() {
		return eItemCode;
	}
	public void seteItemCode(String eItemCode) {
		this.eItemCode = eItemCode;
	}
	public String geteItemUnitTxt() {
		return eItemUnitTxt;
	}
	public void seteItemUnitTxt(String eItemUnitTxt) {
		this.eItemUnitTxt = eItemUnitTxt;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
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
	public String getExcelFilePath() {
		return excelFilePath;
	}
	public void setExcelFilePath(String excelFilePath) {
		this.excelFilePath = excelFilePath;
	}
	public String getBlueprintKey() {
		return blueprintKey;
	}
	public void setBlueprintKey(String BlueprintKey) {
		this.blueprintKey = BlueprintKey;
	}
	public String getBlueprintName() {
		return blueprintName;
	}
	public void setBlueprintName(String BlueprintName) {
		this.blueprintName = BlueprintName;
	}
	public String getBlueprintWdate() {
		return blueprintWdate;
	}
	public void setBlueprintWdate(String BlueprintWdate) {
		this.blueprintWdate = BlueprintWdate;
	}
	public String getBlueprintEtc() {
		return blueprintEtc;
	}
	public void setBlueprintEtc(String BlueprintEtc) {
		this.blueprintEtc = BlueprintEtc;
	}
	public String geteItemKey() {
		return eItemKey;
	}
	public void seteItemKey(String eItemKey) {
		this.eItemKey = eItemKey;
	}
	public String geteItemName() {
		return eItemName;
	}
	public void seteItemName(String eItemName) {
		this.eItemName = eItemName;
	}
	public String geteItemCateKey() {
		return eItemCateKey;
	}
	public void seteItemCateKey(String eItemCateKey) {
		this.eItemCateKey = eItemCateKey;
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

	public String getePageGubun() {
		return ePageGubun;
	}

	public void setePageGubun(String ePageGubun) {
		this.ePageGubun = ePageGubun;
	}

	
}
