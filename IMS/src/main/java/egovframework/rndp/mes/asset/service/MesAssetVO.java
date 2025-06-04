package egovframework.rndp.mes.asset.service;

import egovframework.rndp.com.cmm.service.DefultVO;

public class MesAssetVO extends DefultVO{
	
	//기본 데이터
	private String searchWord					= "";
	private String searchType					= "";	
	private String topStartDate					= "";	
	private String topEndtDate					= "";	
	private String topEndDate					= "";	
	
	//파일 관련
	private String atchFileId					= "";   
	private String fileSn						= "";       
	private String fileStreCours				= "";
	private String streFileNm   				= "";
	private String orignlFileNm 				= "";
	private String fileExtsn    				= "";
	private String fileCn       				= "";
	private String fileSize     				= "";
	private String fileIndex    				= ""; 

	private String  eFileId                        = ""; 
	private String  eFileRowId                        = "";        
	private String  eFileRowName                        = "";        
	private String  eFileRowIndex                        = "";   
	
	private String  eAssetStatusList                        = "";        
	private String  eAssetTypeList                        = "";   
	
	
	// 자산정보
	private String aAssetKey					= "";
	private String aAssetNumber					= "";
	private String aAssetStatus					= "";
	private String aAssetType					= "";  //장비유형
	private String aAssetName					= "";
	private String aAssetMaker					= "";
	private String aAssetModel					= "";
	private String aAssetManufactureNumber		= "";
	private String aAssetForm					= "";
	private String aAssetIntroducer				= ""; //설치위치
	private String aAssetDate					= "";
	private String aAssetCost					= "";
	private String aAssetPurpose				= "";
	private String aAssetImage					= "";
	private String aAssetEtc					= "";
	private String eAssetWdate					= "";
	private String aAssetWdate					= "";
	
	//자산 사용현황
	private String aAssetUseKey					= "";
	private String aAssetUseHKey				= "";
	private String aAssetUseType				= "";
	private String aAssetRequestDate			= "";
	private String aAssetInstallPlace			= "";
	private String aAssetInstallPlaceDetail		= "";
	private String aAssetUseHost				= "";
	private String aAssetUseNet					= "";
	private String aAssetUseIp					= "";
	private String aAssetUseMac					= "";
	private String aAssetUseOS					= "";
	private String aAssetUseSector				= "";
	private String aAssetUseManager				= "";
	private String aAssetUsePhone				= "";
	
	private String aAssetUseDate				= ""; //이력작성일자
	private String aAssetUseStaff				= ""; //작성자
	private String aAssetUseItemCnt			= ""; //신청건수
	
	// 결재관련
	private String sSignKey						= "";
	private String sSignStatus					= "";
	private String sSignDecison					= "";
	private String sSignContent					= "";
	private String sSignStaffKey				= "";
	private String sSignStaffName				= "";
	private String sSignStaffPosition			= "";
	private String gubun				= "";
	private String oSignPass				= "";
	private String sSignStaffGubun				= "";
	private String sSignProgress				= "";
	private String eViewGubun				= "";
	private String kStaffKey				= "";
	
	
	private String viewDetail				= "";
	
	
	
	
	
	
	//SW 관리대장
	private String eSWRegisterKey = ""; // 테이블키
	private String eSWRegister = ""; //  테이블구분
	private String eSWRegisterSn = ""; // SN 아래 
	private String eManufacturer = ""; // 제조사
	private String eProductName = ""; // 제품명
	private String eVersion = ""; // 버전
	private String ePurchaseDate = ""; // 구매일
	private String eStartDate = ""; // 시작일
	private String eEndDate = ""; // 종료일
	private String eValidityPeriod = ""; // 유효기간
	private String eLicenseQuantity = ""; // 라이선스 수량
	private String eRemarks = ""; // 비고
	private String eLicenseKey = ""; // 라이선스 키
	private String eInstallationDate = ""; // 설치일
	private String eInstalledOn = ""; // 설치된 장비/위치
	private String eMaintenanceExpiry = ""; // 유지보수 만료일
	private String eCost = ""; // 비용
	private String eVendor = ""; // 공급업체
	private String eSupportContact = ""; // 지원 연락처
	private String eStatus = ""; // 상태: Active, Inactive 등
	private String eRenewalDate = ""; // 갱신일
	private String eRegistrationDate = ""; // 등록일
	private String eAuthor = ""; // 작성자
	private String eUsedLicenseQuantity = ""; // 사용된 수량
	
	private String maintanceSelect1 = "";  
	private String maintanceSelect2 = "";  
	private String maintanceSelect3 = "";  
	private String maintanceSelect4 = "";  
	private String eAssetTypeName = "";  
	private String eNetworkType = "";  
	private String eHostName = "";  
	private String eIp = "";  
	private String eOs = "";  
	private String eDeviceType = "";  
	private String eDeviceImg = "";  
	private String eDeviceImgId = "";  
	private String eDeviceImgName = "";  
	private String eDeviceImgExtension = "";  
	
	private String eAssetType = "";  
	private String eAssetNumber = "";  
	private String eAssetName = "";  
	private String eMaintanceSelect1 = "";  
	private String eMaintanceSelect2 = "";  
	private String eMaintanceSelect3 = "";  
	private String eMaintanceSelect4 = "";  
	private String eAssetMaker = "";  
	private String eAssetModel = "";  
	private String eAssetManufactureNumber = "";  
	private String eAssetSNumber = "";  
	private String eAssetStatusName = "";  
	private String eAssetStatus = "";  
	private String eAssetCost = "";  
	private String eAssetDate = "";  
	private String eAssetPurpose = "";
	
	private String eAssetImageId = "";  
	private String eAssetImageName = "";  
	private String eAssetImageExt = "";  
	private String aAssetTypeMax = "";  
	
	private String eAssetEtc = "";  
	private String eEosDate = "";  
	private String eEolDate = "";  
	private String eAssetKey = "";  
	
	private String ePositionName1 = "";  
	private String ePositionName2 = "";  
	private String ePositionName3 = "";  
	private String ePositionName4 = "";  
	private String eLifespan = "";  
	private String eLifeType				= "";
	
	private String searchTypeSet1				= "";
	private String searchTypeSet2				= "";
	private String searchTypeSet3				= "";
	private String searchTypeSet4				= "";
	private String searchTypeSet5				= "";
	private String searchTypeSet6				= "";
	private String searchTypeSet7				= "";
	private String searchTypeSet8				= "";
	private String searchTypeSet9				= "";
	private String searchDate1				= "";
	private String searchDate2				= "";
	
	private String ePageInfo				= "";
	private String eEntryExitKey				= "";
	private String eEntryExitItemKey				= "";
	private String eEntryExitDate				= "";
	private String eEntryStaff				= "";
	private String eEntryWStaff				= "";
	private String eEntryWStaffKey				= "";
	private String eEntryRequestReason				= "";

	private String eEntryImporter				= "";
	private String eEntryImportDate				= "";
	private String eEntryNote				= "";
	private String eEntryStatus				= "";
	
	private String eReplacementRequestDate				= "";
	private String eReplacementRequester				= "";
	private String ePartReplacementReason				= "";
	private String eReplacementDate				= "";
	private String eReplacedBy				= "";
	private String ePartReplacementItem				= "";
	private String eReqContent				= "";
	private String eReplacedByOrg				= "";  // 교체자 소속 바꿈
	
	private String eReplacedKey				= "";
	private String eReplacedItemKey				= "";
	private String eAssetKeyList				= "";
	
	private String mMaintanceNumber				= "";
	private String mIssueTypeName				= "";
	private String mAuthor				= "";
	private String mMaintanceCateName				= "";
	private String mMaintanceType				= "";
	private String mMaintanceDate				= "";
	private String mMaintanceModel				= "";
	private String mMaintanceSerial				= "";
	private String mMaintanceContent				= "";
	private String mMaintanceTypeName				= "";
	private String mRequestDate				= "";
	private String mProcessingDate				= "";
	private String mProcessDate				= "";
	private String mHandler				= "";
	
	
	private String mProcessType       				= "";
	private String mProcessStaffKey   				= "";
	private String mProcessStaffName  				= "";
	private String mProcessResult     				= "";
	private String mProcessContent    = "";
	
	private String eOldExpiryDate    = "";
	private String eNewExpiryDate    = "";
	private String eSWRegisterLogKey    = "";
	private String eSWRegisterLogWDate    = "";
 
	private String eAuthorKey    = "";
	private String eIssueCate    = "";
	private String eCreationDate    = "";
	private String eIssueSelect1    = "";
	private String eIssueSelect2    = "";
	private String eIssueSelect3    = "";
	private String eIssueSelect4    = "";
	
	private String eIssueType    = "";
	private String eIssueTypeName    = "";
	private String eProcessingType    = "";
	private String eProcessingTypeName    = "";
	private String eRequester    = "";
	private String eRequestDate    = "";
	
	private String eHandler    = "";
	private String eProcessingDate    = "";
	private String eIssueContent    = "";
	private String eIssueStatus    = "";
	private String eActualWorkDate    = "";
	private String eActualWorker    = "";
	
	private String sSignTableKey    = "";
	private String sSignTableName    = "";
	private String eEntryStaffOrg    = "";
	private String eExportLocation    = "";
	private String ePageGubun    = "";
	
	
 
	 
	public String geteViewGubun() {
		return eViewGubun;
	}
	public void seteViewGubun(String eViewGubun) {
		this.eViewGubun = eViewGubun;
	}
	public String getkStaffKey() {
		return kStaffKey;
	}
	public void setkStaffKey(String kStaffKey) {
		this.kStaffKey = kStaffKey;
	}
	public String getePageGubun() {
		return ePageGubun;
	}
	public void setePageGubun(String ePageGubun) {
		this.ePageGubun = ePageGubun;
	}
	public String geteEntryStaffOrg() {
		return eEntryStaffOrg;
	}
	public void seteEntryStaffOrg(String eEntryStaffOrg) {
		this.eEntryStaffOrg = eEntryStaffOrg;
	}
	public String geteExportLocation() {
		return eExportLocation;
	}
	public void seteExportLocation(String eExportLocation) {
		this.eExportLocation = eExportLocation;
	}
	public String geteAssetWdate() {
		return eAssetWdate;
	}
	public void seteAssetWdate(String eAssetWdate) {
		this.eAssetWdate = eAssetWdate;
	}
	public String getaAssetWdate() {
		return aAssetWdate;
	}
	public void setaAssetWdate(String aAssetWdate) {
		this.aAssetWdate = aAssetWdate;
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
	public String geteAuthorKey() {
		return eAuthorKey;
	}
	public void seteAuthorKey(String eAuthorKey) {
		this.eAuthorKey = eAuthorKey;
	}
	public String geteIssueCate() {
		return eIssueCate;
	}
	public void seteIssueCate(String eIssueCate) {
		this.eIssueCate = eIssueCate;
	}
	public String geteCreationDate() {
		return eCreationDate;
	}
	public void seteCreationDate(String eCreationDate) {
		this.eCreationDate = eCreationDate;
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
	public String geteRequester() {
		return eRequester;
	}
	public void seteRequester(String eRequester) {
		this.eRequester = eRequester;
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
	public String geteIssueStatus() {
		return eIssueStatus;
	}
	public void seteIssueStatus(String eIssueStatus) {
		this.eIssueStatus = eIssueStatus;
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
	public String getePartReplacementItem() {
		return ePartReplacementItem;
	}
	public void setePartReplacementItem(String ePartReplacementItem) {
		this.ePartReplacementItem = ePartReplacementItem;
	}
	public String geteReqContent() {
		return eReqContent;
	}
	public void seteReqContent(String eReqContent) {
		this.eReqContent = eReqContent;
	}
	public String getaAssetTypeMax() {
		return aAssetTypeMax;
	}
	public void setaAssetTypeMax(String aAssetTypeMax) {
		this.aAssetTypeMax = aAssetTypeMax;
	}
	public String geteEntryStatus() {
		return eEntryStatus;
	}
	public void seteEntryStatus(String eEntryStatus) {
		this.eEntryStatus = eEntryStatus;
	}
	public String getsSignProgress() {
		return sSignProgress;
	}
	public void setsSignProgress(String sSignProgress) {
		this.sSignProgress = sSignProgress;
	}
	public String geteUsedLicenseQuantity() {
		return eUsedLicenseQuantity;
	}
	public void seteUsedLicenseQuantity(String eUsedLicenseQuantity) {
		this.eUsedLicenseQuantity = eUsedLicenseQuantity;
	}
	public String getsSignStaffGubun() {
		return sSignStaffGubun;
	}
	public void setsSignStaffGubun(String sSignStaffGubun) {
		this.sSignStaffGubun = sSignStaffGubun;
	}
	public String geteSWRegisterLogWDate() {
		return eSWRegisterLogWDate;
	}
	public void seteSWRegisterLogWDate(String eSWRegisterLogWDate) {
		this.eSWRegisterLogWDate = eSWRegisterLogWDate;
	}
	public String geteOldExpiryDate() {
		return eOldExpiryDate;
	}
	public void seteOldExpiryDate(String eOldExpiryDate) {
		this.eOldExpiryDate = eOldExpiryDate;
	}
	public String geteNewExpiryDate() {
		return eNewExpiryDate;
	}
	public void seteNewExpiryDate(String eNewExpiryDate) {
		this.eNewExpiryDate = eNewExpiryDate;
	}
	public String geteSWRegisterLogKey() {
		return eSWRegisterLogKey;
	}
	public void seteSWRegisterLogKey(String eSWRegisterLogKey) {
		this.eSWRegisterLogKey = eSWRegisterLogKey;
	}
	public String getmMaintanceNumber() {
		return mMaintanceNumber;
	}
	public void setmMaintanceNumber(String mMaintanceNumber) {
		this.mMaintanceNumber = mMaintanceNumber;
	}
	public String getmIssueTypeName() {
		return mIssueTypeName;
	}
	public void setmIssueTypeName(String mIssueTypeName) {
		this.mIssueTypeName = mIssueTypeName;
	}
	public String getmAuthor() {
		return mAuthor;
	}
	public void setmAuthor(String mAuthor) {
		this.mAuthor = mAuthor;
	}
	public String getmMaintanceCateName() {
		return mMaintanceCateName;
	}
	public void setmMaintanceCateName(String mMaintanceCateName) {
		this.mMaintanceCateName = mMaintanceCateName;
	}
	public String getmMaintanceType() {
		return mMaintanceType;
	}
	public void setmMaintanceType(String mMaintanceType) {
		this.mMaintanceType = mMaintanceType;
	}
	public String getmMaintanceDate() {
		return mMaintanceDate;
	}
	public void setmMaintanceDate(String mMaintanceDate) {
		this.mMaintanceDate = mMaintanceDate;
	}
	public String getmMaintanceModel() {
		return mMaintanceModel;
	}
	public void setmMaintanceModel(String mMaintanceModel) {
		this.mMaintanceModel = mMaintanceModel;
	}
	public String getmMaintanceSerial() {
		return mMaintanceSerial;
	}
	public void setmMaintanceSerial(String mMaintanceSerial) {
		this.mMaintanceSerial = mMaintanceSerial;
	}
	public String getmMaintanceContent() {
		return mMaintanceContent;
	}
	public void setmMaintanceContent(String mMaintanceContent) {
		this.mMaintanceContent = mMaintanceContent;
	}
	public String getmMaintanceTypeName() {
		return mMaintanceTypeName;
	}
	public void setmMaintanceTypeName(String mMaintanceTypeName) {
		this.mMaintanceTypeName = mMaintanceTypeName;
	}
	public String getmRequestDate() {
		return mRequestDate;
	}
	public void setmRequestDate(String mRequestDate) {
		this.mRequestDate = mRequestDate;
	}
	public String getmProcessingDate() {
		return mProcessingDate;
	}
	public void setmProcessingDate(String mProcessingDate) {
		this.mProcessingDate = mProcessingDate;
	}
	public String getmProcessDate() {
		return mProcessDate;
	}
	public void setmProcessDate(String mProcessDate) {
		this.mProcessDate = mProcessDate;
	}
	public String getmHandler() {
		return mHandler;
	}
	public void setmHandler(String mHandler) {
		this.mHandler = mHandler;
	}
	public String getmProcessType() {
		return mProcessType;
	}
	public void setmProcessType(String mProcessType) {
		this.mProcessType = mProcessType;
	}
	public String getmProcessStaffKey() {
		return mProcessStaffKey;
	}
	public void setmProcessStaffKey(String mProcessStaffKey) {
		this.mProcessStaffKey = mProcessStaffKey;
	}
	public String getmProcessStaffName() {
		return mProcessStaffName;
	}
	public void setmProcessStaffName(String mProcessStaffName) {
		this.mProcessStaffName = mProcessStaffName;
	}
	public String getmProcessResult() {
		return mProcessResult;
	}
	public void setmProcessResult(String mProcessResult) {
		this.mProcessResult = mProcessResult;
	}
	public String getmProcessContent() {
		return mProcessContent;
	}
	public void setmProcessContent(String mProcessContent) {
		this.mProcessContent = mProcessContent;
	}
	public String geteAssetKeyList() {
		return eAssetKeyList;
	}
	public void seteAssetKeyList(String eAssetKeyList) {
		this.eAssetKeyList = eAssetKeyList;
	}
	public String geteReplacedKey() {
		return eReplacedKey;
	}
	public void seteReplacedKey(String eReplacedKey) {
		this.eReplacedKey = eReplacedKey;
	}
	public String geteReplacedItemKey() {
		return eReplacedItemKey;
	}
	public void seteReplacedItemKey(String eReplacedItemKey) {
		this.eReplacedItemKey = eReplacedItemKey;
	}
	public String geteReplacementRequestDate() {
		return eReplacementRequestDate;
	}
	public void seteReplacementRequestDate(String eReplacementRequestDate) {
		this.eReplacementRequestDate = eReplacementRequestDate;
	}
	public String geteReplacementRequester() {
		return eReplacementRequester;
	}
	public void seteReplacementRequester(String eReplacementRequester) {
		this.eReplacementRequester = eReplacementRequester;
	}
	public String getePartReplacementReason() {
		return ePartReplacementReason;
	}
	public void setePartReplacementReason(String ePartReplacementReason) {
		this.ePartReplacementReason = ePartReplacementReason;
	}
	public String geteReplacementDate() {
		return eReplacementDate;
	}
	public void seteReplacementDate(String eReplacementDate) {
		this.eReplacementDate = eReplacementDate;
	}
	public String geteReplacedBy() {
		return eReplacedBy;
	}
	public void seteReplacedBy(String eReplacedBy) {
		this.eReplacedBy = eReplacedBy;
	}
	public String geteEntryRequestReason() {
		return eEntryRequestReason;
	}
	public void seteEntryRequestReason(String eEntryRequestReason) {
		this.eEntryRequestReason = eEntryRequestReason;
	}
	public String geteEntryImporter() {
		return eEntryImporter;
	}
	public void seteEntryImporter(String eEntryImporter) {
		this.eEntryImporter = eEntryImporter;
	}
	public String geteEntryImportDate() {
		return eEntryImportDate;
	}
	public void seteEntryImportDate(String eEntryImportDate) {
		this.eEntryImportDate = eEntryImportDate;
	}
	public String geteEntryNote() {
		return eEntryNote;
	}
	public void seteEntryNote(String eEntryNote) {
		this.eEntryNote = eEntryNote;
	}
	public String geteEntryExitKey() {
		return eEntryExitKey;
	}
	public void seteEntryExitKey(String eEntryExitKey) {
		this.eEntryExitKey = eEntryExitKey;
	}
	public String geteEntryExitItemKey() {
		return eEntryExitItemKey;
	}
	public void seteEntryExitItemKey(String eEntryExitItemKey) {
		this.eEntryExitItemKey = eEntryExitItemKey;
	}
	public String geteEntryExitDate() {
		return eEntryExitDate;
	}
	public void seteEntryExitDate(String eEntryExitDate) {
		this.eEntryExitDate = eEntryExitDate;
	}
	public String geteEntryStaff() {
		return eEntryStaff;
	}
	public void seteEntryStaff(String eEntryStaff) {
		this.eEntryStaff = eEntryStaff;
	}
	public String geteEntryWStaff() {
		return eEntryWStaff;
	}
	public void seteEntryWStaff(String eEntryWStaff) {
		this.eEntryWStaff = eEntryWStaff;
	}
	public String geteEntryWStaffKey() {
		return eEntryWStaffKey;
	}
	public void seteEntryWStaffKey(String eEntryWStaffKey) {
		this.eEntryWStaffKey = eEntryWStaffKey;
	}
	public String getePageInfo() {
		return ePageInfo;
	}
	public void setePageInfo(String ePageInfo) {
		this.ePageInfo = ePageInfo;
	}
	public String getSearchDate1() {
		return searchDate1;
	}
	public void setSearchDate1(String searchDate1) {
		this.searchDate1 = searchDate1;
	}
	public String getSearchDate2() {
		return searchDate2;
	}
	public void setSearchDate2(String searchDate2) {
		this.searchDate2 = searchDate2;
	}
	public String getSearchTypeSet9() {
		return searchTypeSet9;
	}
	public void setSearchTypeSet9(String searchTypeSet9) {
		this.searchTypeSet9 = searchTypeSet9;
	}
	public String getSearchTypeSet1() {
		return searchTypeSet1;
	}
	public void setSearchTypeSet1(String searchTypeSet1) {
		this.searchTypeSet1 = searchTypeSet1;
	}
	public String getSearchTypeSet2() {
		return searchTypeSet2;
	}
	public void setSearchTypeSet2(String searchTypeSet2) {
		this.searchTypeSet2 = searchTypeSet2;
	}
	public String getSearchTypeSet3() {
		return searchTypeSet3;
	}
	public void setSearchTypeSet3(String searchTypeSet3) {
		this.searchTypeSet3 = searchTypeSet3;
	}
	public String getSearchTypeSet4() {
		return searchTypeSet4;
	}
	public void setSearchTypeSet4(String searchTypeSet4) {
		this.searchTypeSet4 = searchTypeSet4;
	}
	public String getSearchTypeSet5() {
		return searchTypeSet5;
	}
	public void setSearchTypeSet5(String searchTypeSet5) {
		this.searchTypeSet5 = searchTypeSet5;
	}
	public String getSearchTypeSet6() {
		return searchTypeSet6;
	}
	public void setSearchTypeSet6(String searchTypeSet6) {
		this.searchTypeSet6 = searchTypeSet6;
	}
	public String getSearchTypeSet7() {
		return searchTypeSet7;
	}
	public void setSearchTypeSet7(String searchTypeSet7) {
		this.searchTypeSet7 = searchTypeSet7;
	}
	public String getSearchTypeSet8() {
		return searchTypeSet8;
	}
	public void setSearchTypeSet8(String searchTypeSet8) {
		this.searchTypeSet8 = searchTypeSet8;
	}
	public String geteLifeType() {
		return eLifeType;
	}
	public void seteLifeType(String eLifeType) {
		this.eLifeType = eLifeType;
	}
	public String geteLifespan() {
		return eLifespan;
	}
	public void seteLifespan(String eLifespan) {
		this.eLifespan = eLifespan;
	}
	public String getePositionName1() {
		return ePositionName1;
	}
	public void setePositionName1(String ePositionName1) {
		this.ePositionName1 = ePositionName1;
	}
	public String getePositionName2() {
		return ePositionName2;
	}
	public void setePositionName2(String ePositionName2) {
		this.ePositionName2 = ePositionName2;
	}
	public String getePositionName3() {
		return ePositionName3;
	}
	public void setePositionName3(String ePositionName3) {
		this.ePositionName3 = ePositionName3;
	}
	public String getePositionName4() {
		return ePositionName4;
	}
	public void setePositionName4(String ePositionName4) {
		this.ePositionName4 = ePositionName4;
	}
	public String geteAssetKey() {
		return eAssetKey;
	}
	public void seteAssetKey(String eAssetKey) {
		this.eAssetKey = eAssetKey;
	}
	public String geteEosDate() {
		return eEosDate;
	}
	public void seteEosDate(String eEosDate) {
		this.eEosDate = eEosDate;
	}
	public String geteEolDate() {
		return eEolDate;
	}
	public void seteEolDate(String eEolDate) {
		this.eEolDate = eEolDate;
	}
	public String geteAssetEtc() {
		return eAssetEtc;
	}
	public void seteAssetEtc(String eAssetEtc) {
		this.eAssetEtc = eAssetEtc;
	}
	public String geteAssetStatus() {
		return eAssetStatus;
	}
	public void seteAssetStatus(String eAssetStatus) {
		this.eAssetStatus = eAssetStatus;
	}
	public String geteAssetImageId() {
		return eAssetImageId;
	}
	public void seteAssetImageId(String eAssetImageId) {
		this.eAssetImageId = eAssetImageId;
	}
	public String geteAssetImageName() {
		return eAssetImageName;
	}
	public void seteAssetImageName(String eAssetImageName) {
		this.eAssetImageName = eAssetImageName;
	}
	public String geteAssetImageExt() {
		return eAssetImageExt;
	}
	public void seteAssetImageExt(String eAssetImageExt) {
		this.eAssetImageExt = eAssetImageExt;
	}
	public String geteAssetSNumber() {
		return eAssetSNumber;
	}
	public void seteAssetSNumber(String eAssetSNumber) {
		this.eAssetSNumber = eAssetSNumber;
	}
	public String geteAssetType() {
		return eAssetType;
	}
	public void seteAssetType(String eAssetType) {
		this.eAssetType = eAssetType;
	}
	public String geteAssetNumber() {
		return eAssetNumber;
	}
	public void seteAssetNumber(String eAssetNumber) {
		this.eAssetNumber = eAssetNumber;
	}
	public String geteAssetName() {
		return eAssetName;
	}
	public void seteAssetName(String eAssetName) {
		this.eAssetName = eAssetName;
	}
	public String geteMaintanceSelect1() {
		return eMaintanceSelect1;
	}
	public void seteMaintanceSelect1(String eMaintanceSelect1) {
		this.eMaintanceSelect1 = eMaintanceSelect1;
	}
	public String geteMaintanceSelect2() {
		return eMaintanceSelect2;
	}
	public void seteMaintanceSelect2(String eMaintanceSelect2) {
		this.eMaintanceSelect2 = eMaintanceSelect2;
	}
	public String geteMaintanceSelect3() {
		return eMaintanceSelect3;
	}
	public void seteMaintanceSelect3(String eMaintanceSelect3) {
		this.eMaintanceSelect3 = eMaintanceSelect3;
	}
	public String geteMaintanceSelect4() {
		return eMaintanceSelect4;
	}
	public void seteMaintanceSelect4(String eMaintanceSelect4) {
		this.eMaintanceSelect4 = eMaintanceSelect4;
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
	public String geteAssetStatusName() {
		return eAssetStatusName;
	}
	public void seteAssetStatusName(String eAssetStatusName) {
		this.eAssetStatusName = eAssetStatusName;
	}
	public String geteAssetCost() {
		return eAssetCost;
	}
	public void seteAssetCost(String eAssetCost) {
		this.eAssetCost = eAssetCost;
	}
	public String geteAssetDate() {
		return eAssetDate;
	}
	public void seteAssetDate(String eAssetDate) {
		this.eAssetDate = eAssetDate;
	}
	public String geteAssetPurpose() {
		return eAssetPurpose;
	}
	public void seteAssetPurpose(String eAssetPurpose) {
		this.eAssetPurpose = eAssetPurpose;
	}
	public String geteDeviceImgId() {
		return eDeviceImgId;
	}
	public void seteDeviceImgId(String eDeviceImgId) {
		this.eDeviceImgId = eDeviceImgId;
	}
	public String geteDeviceImgName() {
		return eDeviceImgName;
	}
	public void seteDeviceImgName(String eDeviceImgName) {
		this.eDeviceImgName = eDeviceImgName;
	}
	public String geteDeviceImgExtension() {
		return eDeviceImgExtension;
	}
	public void seteDeviceImgExtension(String eDeviceImgExtension) {
		this.eDeviceImgExtension = eDeviceImgExtension;
	}
	public String geteDeviceImg() {
		return eDeviceImg;
	}
	public void seteDeviceImg(String eDeviceImg) {
		this.eDeviceImg = eDeviceImg;
	}
	public String geteAssetTypeName() {
		return eAssetTypeName;
	}
	public void seteAssetTypeName(String eAssetTypeName) {
		this.eAssetTypeName = eAssetTypeName;
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
	public String geteDeviceType() {
		return eDeviceType;
	}
	public void seteDeviceType(String eDeviceType) {
		this.eDeviceType = eDeviceType;
	}
	public String getMaintanceSelect1() {
		return maintanceSelect1;
	}
	public void setMaintanceSelect1(String maintanceSelect1) {
		this.maintanceSelect1 = maintanceSelect1;
	}
	public String getMaintanceSelect2() {
		return maintanceSelect2;
	}
	public void setMaintanceSelect2(String maintanceSelect2) {
		this.maintanceSelect2 = maintanceSelect2;
	}
	public String getMaintanceSelect3() {
		return maintanceSelect3;
	}
	public void setMaintanceSelect3(String maintanceSelect3) {
		this.maintanceSelect3 = maintanceSelect3;
	}
	public String getMaintanceSelect4() {
		return maintanceSelect4;
	}
	public void setMaintanceSelect4(String maintanceSelect4) {
		this.maintanceSelect4 = maintanceSelect4;
	}
	public String getTopEndDate() {
		return topEndDate;
	}
	public void setTopEndDate(String topEndDate) {
		this.topEndDate = topEndDate;
	}
	public String geteSWRegisterSn() {
		return eSWRegisterSn;
	}
	public void seteSWRegisterSn(String eSWRegisterSn) {
		this.eSWRegisterSn = eSWRegisterSn;
	}
	public String geteSWRegisterKey() {
		return eSWRegisterKey;
	}
	public void seteSWRegisterKey(String eSWRegisterKey) {
		this.eSWRegisterKey = eSWRegisterKey;
	}
	public String geteSWRegister() {
		return eSWRegister;
	}
	public void seteSWRegister(String eSWRegister) {
		this.eSWRegister = eSWRegister;
	}
	public String geteManufacturer() {
		return eManufacturer;
	}
	public void seteManufacturer(String eManufacturer) {
		this.eManufacturer = eManufacturer;
	}
	public String geteProductName() {
		return eProductName;
	}
	public void seteProductName(String eProductName) {
		this.eProductName = eProductName;
	}
	public String geteVersion() {
		return eVersion;
	}
	public void seteVersion(String eVersion) {
		this.eVersion = eVersion;
	}
	public String getePurchaseDate() {
		return ePurchaseDate;
	}
	public void setePurchaseDate(String ePurchaseDate) {
		this.ePurchaseDate = ePurchaseDate;
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
	public String geteValidityPeriod() {
		return eValidityPeriod;
	}
	public void seteValidityPeriod(String eValidityPeriod) {
		this.eValidityPeriod = eValidityPeriod;
	}
	public String geteLicenseQuantity() {
		return eLicenseQuantity;
	}
	public void seteLicenseQuantity(String eLicenseQuantity) {
		this.eLicenseQuantity = eLicenseQuantity;
	}
	public String geteRemarks() {
		return eRemarks;
	}
	public void seteRemarks(String eRemarks) {
		this.eRemarks = eRemarks;
	}
	public String geteLicenseKey() {
		return eLicenseKey;
	}
	public void seteLicenseKey(String eLicenseKey) {
		this.eLicenseKey = eLicenseKey;
	}
	public String geteInstallationDate() {
		return eInstallationDate;
	}
	public void seteInstallationDate(String eInstallationDate) {
		this.eInstallationDate = eInstallationDate;
	}
	public String geteInstalledOn() {
		return eInstalledOn;
	}
	public void seteInstalledOn(String eInstalledOn) {
		this.eInstalledOn = eInstalledOn;
	}
	public String geteMaintenanceExpiry() {
		return eMaintenanceExpiry;
	}
	public void seteMaintenanceExpiry(String eMaintenanceExpiry) {
		this.eMaintenanceExpiry = eMaintenanceExpiry;
	}
	public String geteCost() {
		return eCost;
	}
	public void seteCost(String eCost) {
		this.eCost = eCost;
	}
	public String geteVendor() {
		return eVendor;
	}
	public void seteVendor(String eVendor) {
		this.eVendor = eVendor;
	}
	public String geteSupportContact() {
		return eSupportContact;
	}
	public void seteSupportContact(String eSupportContact) {
		this.eSupportContact = eSupportContact;
	}
	public String geteStatus() {
		return eStatus;
	}
	public void seteStatus(String eStatus) {
		this.eStatus = eStatus;
	}
	public String geteRenewalDate() {
		return eRenewalDate;
	}
	public void seteRenewalDate(String eRenewalDate) {
		this.eRenewalDate = eRenewalDate;
	}
	public String geteRegistrationDate() {
		return eRegistrationDate;
	}
	public void seteRegistrationDate(String eRegistrationDate) {
		this.eRegistrationDate = eRegistrationDate;
	}
	public String geteAuthor() {
		return eAuthor;
	}
	public void seteAuthor(String eAuthor) {
		this.eAuthor = eAuthor;
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
	public String getSearchWord() {
		return searchWord;
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
	public String getTopStartDate() {
		return topStartDate;
	}
	public void setTopStartDate(String topStartDate) {
		this.topStartDate = topStartDate;
	}
	public String getTopEndtDate() {
		return topEndtDate;
	}
	public void setTopEndtDate(String topEndtDate) {
		this.topEndtDate = topEndtDate;
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
	public String getFileStreCours() {
		return fileStreCours;
	}
	public void setFileStreCours(String fileStreCours) {
		this.fileStreCours = fileStreCours;
	}
	public String getStreFileNm() {
		return streFileNm;
	}
	public void setStreFileNm(String streFileNm) {
		this.streFileNm = streFileNm;
	}
	public String getOrignlFileNm() {
		return orignlFileNm;
	}
	public void setOrignlFileNm(String orignlFileNm) {
		this.orignlFileNm = orignlFileNm;
	}
	public String getFileExtsn() {
		return fileExtsn;
	}
	public void setFileExtsn(String fileExtsn) {
		this.fileExtsn = fileExtsn;
	}
	public String getFileCn() {
		return fileCn;
	}
	public void setFileCn(String fileCn) {
		this.fileCn = fileCn;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileIndex() {
		return fileIndex;
	}
	public void setFileIndex(String fileIndex) {
		this.fileIndex = fileIndex;
	}
	public String getaAssetKey() {
		return aAssetKey;
	}
	public void setaAssetKey(String aAssetKey) {
		this.aAssetKey = aAssetKey;
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
	public String getaAssetPurpose() {
		return aAssetPurpose;
	}
	public void setaAssetPurpose(String aAssetPurpose) {
		this.aAssetPurpose = aAssetPurpose;
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
	public String getaAssetUseKey() {
		return aAssetUseKey;
	}
	public void setaAssetUseKey(String aAssetUseKey) {
		this.aAssetUseKey = aAssetUseKey;
	}
	public String getaAssetUseType() {
		return aAssetUseType;
	}
	public void setaAssetUseType(String aAssetUseType) {
		this.aAssetUseType = aAssetUseType;
	}
	public String getaAssetRequestDate() {
		return aAssetRequestDate;
	}
	public void setaAssetRequestDate(String aAssetRequestDate) {
		this.aAssetRequestDate = aAssetRequestDate;
	}
	public String getaAssetInstallPlace() {
		return aAssetInstallPlace;
	}
	public void setaAssetInstallPlace(String aAssetInstallPlace) {
		this.aAssetInstallPlace = aAssetInstallPlace;
	}
	public String getaAssetInstallPlaceDetail() {
		return aAssetInstallPlaceDetail;
	}
	public void setaAssetInstallPlaceDetail(String aAssetInstallPlaceDetail) {
		this.aAssetInstallPlaceDetail = aAssetInstallPlaceDetail;
	}
	public String getaAssetUseHost() {
		return aAssetUseHost;
	}
	public void setaAssetUseHost(String aAssetUseHost) {
		this.aAssetUseHost = aAssetUseHost;
	}
	public String getaAssetUseNet() {
		return aAssetUseNet;
	}
	public void setaAssetUseNet(String aAssetUseNet) {
		this.aAssetUseNet = aAssetUseNet;
	}
	public String getaAssetUseIp() {
		return aAssetUseIp;
	}
	public void setaAssetUseIp(String aAssetUseIp) {
		this.aAssetUseIp = aAssetUseIp;
	}
	public String getaAssetUseMac() {
		return aAssetUseMac;
	}
	public void setaAssetUseMac(String aAssetUseMac) {
		this.aAssetUseMac = aAssetUseMac;
	}
	public String getaAssetUseOS() {
		return aAssetUseOS;
	}
	public void setaAssetUseOS(String aAssetUseOS) {
		this.aAssetUseOS = aAssetUseOS;
	}
	public String getaAssetUseSector() {
		return aAssetUseSector;
	}
	public void setaAssetUseSector(String aAssetUseSector) {
		this.aAssetUseSector = aAssetUseSector;
	}
	public String getaAssetUseManager() {
		return aAssetUseManager;
	}
	public void setaAssetUseManager(String aAssetUseManager) {
		this.aAssetUseManager = aAssetUseManager;
	}
	public String getaAssetUsePhone() {
		return aAssetUsePhone;
	}
	public void setaAssetUsePhone(String aAssetUsePhone) {
		this.aAssetUsePhone = aAssetUsePhone;
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
	public String getaAssetUseDate() {
		return aAssetUseDate;
	}
	public void setaAssetUseDate(String aAssetUseDate) {
		this.aAssetUseDate = aAssetUseDate;
	}
	public String getaAssetUseStaff() {
		return aAssetUseStaff;
	}
	public void setaAssetUseStaff(String aAssetUseStaff) {
		this.aAssetUseStaff = aAssetUseStaff;
	}
	public String getaAssetUseItemCnt() {
		return aAssetUseItemCnt;
	}
	public void setaAssetUseItemCnt(String aAssetUseItemCnt) {
		this.aAssetUseItemCnt = aAssetUseItemCnt;
	}
	public String getaAssetUseHKey() {
		return aAssetUseHKey;
	}
	public void setaAssetUseHKey(String aAssetUseHKey) {
		this.aAssetUseHKey = aAssetUseHKey;
	}
	public String getsSignStaffPosition() {
		return sSignStaffPosition;
	}
	public void setsSignStaffPosition(String sSignStaffPosition) {
		this.sSignStaffPosition = sSignStaffPosition;
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
	public String geteFileId() {
		return eFileId;
	}
	public void seteFileId(String eFileId) {
		this.eFileId = eFileId;
	}
	public String geteAssetStatusList() {
		return eAssetStatusList;
	}
	public void seteAssetStatusList(String eAssetStatusList) {
		this.eAssetStatusList = eAssetStatusList;
	}
	public String geteAssetTypeList() {
		return eAssetTypeList;
	}
	public void seteAssetTypeList(String eAssetTypeList) {
		this.eAssetTypeList = eAssetTypeList;
	}
	public String geteReplacedByOrg() {
		return eReplacedByOrg;
	}
	public void seteReplacedByOrg(String eReplacedByOrg) {
		this.eReplacedByOrg = eReplacedByOrg;
	}
	public String getviewDetail() {
		return viewDetail;
	}
	public void setviewDetail(String viewDetail) {
		this.viewDetail = viewDetail;
	}
}
