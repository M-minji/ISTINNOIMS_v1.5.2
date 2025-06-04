package egovframework.rndp.mes.equipment.service;

import egovframework.rndp.com.cmm.service.DefultVO;

public class MesEquipmentVO extends DefultVO{
	
	//기본 데이터
	private String eSearchWord					= "";
	private String eSearchWord1					= "";
	private String eSearchWord2					= "";
	private String eSearchWord3					= "";
	private String eSearchWord4					= "";
	private String eSearchWord5					= "";
	private String eSearchWord6					= "";
	private String eSearchWord7					= "";
	private String eSearchWord8					= "";
	private String eSearchWord9					= "";
	
	private String eSearchType					= "";	
	private String eTopStartDate					= "";	
	private String eTopEndDate					= "";	
	private String ePageInfo					= "";	

	private String eEquipmentNum					= "";	
	private String eEquipmentInNum					= "";	
	private String eEquipmentOutNum					= "";	
	private String eAssetNum					= "";	
	
	 
	private String eEquipmentInKey = "";
	private String eEntryImporter = "";
	private String eEntryImportDate = "";
	private String eEntryNote = "";
	private String eSignStatus = "";
	private String wDate = "";
	private String kStaffName = "";
	private String kStaffKey = "";
	private String eStatus = "";

	private String eEquipmentItemKey = "";
	private String eAssetPurpose = "";
	private String eAssetTypeName = "";
	private String eAssetType = "";
	private String eAssetName = "";
	private String eAssetMaker = "";
	private String eAssetModel = "";
	private String eAssetSNumber = "";
	private String eAssetNetworkType = "";
	private String eAssetHostName = "";
	private String eAssetIp = "";
	private String eAssetOs = "";
	private String eAssetDeviceType = "";
	
	private String eEquipmentOutKey = "";
	private String eExitExporter = "";
	private String eEntryExporterDate = "";
	private String eItemStatus = "";
	
	private String sSignPass = "";
	private String sSignStatus = "";
	private String sSignTableKey = "";
	private String sSignTableName = "";
	private String sSignStaffKey = "";
	private String sSignDecison = "";
	private String sSignContent = "";
	private String sSignStaffGubun = "";
	private String sSignProgress = "";
	private String oSignPass = "";
	private String sSignStaffName = "";
	private String sSignStaffPosition = "";
	private String eAssetWdate = "";
	private String eEntryLocation = "";
	private String eEntryImporterOrg = "";
	private String eEntryWdate = "";
	private String eViewGubun = "";
	
	private String viewDetail				= "";
	
	public String getviewDetail() {
		return viewDetail;
	}
	public void setviewDetail(String viewDetail) {
		this.viewDetail = viewDetail;
	}
	public String geteViewGubun() {
		return eViewGubun;
	}
	public void seteViewGubun(String eViewGubun) {
		this.eViewGubun = eViewGubun;
	}
	public String geteEntryWdate() {
		return eEntryWdate;
	}
	public void seteEntryWdate(String eEntryWdate) {
		this.eEntryWdate = eEntryWdate;
	}
	public String geteEntryImporterOrg() {
		return eEntryImporterOrg;
	}
	public void seteEntryImporterOrg(String eEntryImporterOrg) {
		this.eEntryImporterOrg = eEntryImporterOrg;
	}
	public String geteAssetWdate() {
		return eAssetWdate;
	}
	public void seteAssetWdate(String eAssetWdate) {
		this.eAssetWdate = eAssetWdate;
	}
	public String geteEntryLocation() {
		return eEntryLocation;
	}
	public void seteEntryLocation(String eEntryLocation) {
		this.eEntryLocation = eEntryLocation;
	}
	public String getsSignStaffName() {
		return sSignStaffName;
	}
	public void setsSignStaffName(String sSignStaffName) {
		this.sSignStaffName = sSignStaffName;
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
	public String getsSignProgress() {
		return sSignProgress;
	}
	public void setsSignProgress(String sSignProgress) {
		this.sSignProgress = sSignProgress;
	}
	public String getsSignPass() {
		return sSignPass;
	}
	public void setsSignPass(String sSignPass) {
		this.sSignPass = sSignPass;
	}
	public String getsSignStatus() {
		return sSignStatus;
	}
	public void setsSignStatus(String sSignStatus) {
		this.sSignStatus = sSignStatus;
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
	public String getsSignStaffKey() {
		return sSignStaffKey;
	}
	public void setsSignStaffKey(String sSignStaffKey) {
		this.sSignStaffKey = sSignStaffKey;
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
	public String getsSignStaffGubun() {
		return sSignStaffGubun;
	}
	public void setsSignStaffGubun(String sSignStaffGubun) {
		this.sSignStaffGubun = sSignStaffGubun;
	}
	public String geteItemStatus() {
		return eItemStatus;
	}
	public void seteItemStatus(String eItemStatus) {
		this.eItemStatus = eItemStatus;
	}
	public String getePageInfo() {
		return ePageInfo;
	}
	public void setePageInfo(String ePageInfo) {
		this.ePageInfo = ePageInfo;
	}
	public String geteEquipmentInNum() {
		return eEquipmentInNum;
	}
	public void seteEquipmentInNum(String eEquipmentInNum) {
		this.eEquipmentInNum = eEquipmentInNum;
	}
	public String geteEquipmentOutNum() {
		return eEquipmentOutNum;
	}
	public void seteEquipmentOutNum(String eEquipmentOutNum) {
		this.eEquipmentOutNum = eEquipmentOutNum;
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
	public String geteSearchWord8() {
		return eSearchWord8;
	}
	public void seteSearchWord8(String eSearchWord8) {
		this.eSearchWord8 = eSearchWord8;
	}
	public String geteSearchWord9() {
		return eSearchWord9;
	}
	public void seteSearchWord9(String eSearchWord9) {
		this.eSearchWord9 = eSearchWord9;
	}
	public String geteSearchWord() {
		return eSearchWord;
	}
	public void seteSearchWord(String eSearchWord) {
		this.eSearchWord = eSearchWord;
	}
	public String geteSearchType() {
		return eSearchType;
	}
	public void seteSearchType(String eSearchType) {
		this.eSearchType = eSearchType;
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
	public String geteEquipmentNum() {
		return eEquipmentNum;
	}
	public void seteEquipmentNum(String eEquipmentNum) {
		this.eEquipmentNum = eEquipmentNum;
	}
	public String geteAssetNum() {
		return eAssetNum;
	}
	public void seteAssetNum(String eAssetNum) {
		this.eAssetNum = eAssetNum;
	}
	public String geteEquipmentInKey() {
		return eEquipmentInKey;
	}
	public void seteEquipmentInKey(String eEquipmentInKey) {
		this.eEquipmentInKey = eEquipmentInKey;
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
	public String geteSignStatus() {
		return eSignStatus;
	}
	public void seteSignStatus(String eSignStatus) {
		this.eSignStatus = eSignStatus;
	}
	public String getwDate() {
		return wDate;
	}
	public void setwDate(String wDate) {
		this.wDate = wDate;
	}
	public String getkStaffName() {
		return kStaffName;
	}
	public void setkStaffName(String kStaffName) {
		this.kStaffName = kStaffName;
	}
	public String getkStaffKey() {
		return kStaffKey;
	}
	public void setkStaffKey(String kStaffKey) {
		this.kStaffKey = kStaffKey;
	}
	public String geteStatus() {
		return eStatus;
	}
	public void seteStatus(String eStatus) {
		this.eStatus = eStatus;
	}
	public String geteEquipmentItemKey() {
		return eEquipmentItemKey;
	}
	public void seteEquipmentItemKey(String eEquipmentItemKey) {
		this.eEquipmentItemKey = eEquipmentItemKey;
	}
	public String geteAssetPurpose() {
		return eAssetPurpose;
	}
	public void seteAssetPurpose(String eAssetPurpose) {
		this.eAssetPurpose = eAssetPurpose;
	}
	public String geteAssetTypeName() {
		return eAssetTypeName;
	}
	public void seteAssetTypeName(String eAssetTypeName) {
		this.eAssetTypeName = eAssetTypeName;
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
	public String geteAssetSNumber() {
		return eAssetSNumber;
	}
	public void seteAssetSNumber(String eAssetSNumber) {
		this.eAssetSNumber = eAssetSNumber;
	}
	public String geteAssetNetworkType() {
		return eAssetNetworkType;
	}
	public void seteAssetNetworkType(String eAssetNetworkType) {
		this.eAssetNetworkType = eAssetNetworkType;
	}
	public String geteAssetHostName() {
		return eAssetHostName;
	}
	public void seteAssetHostName(String eAssetHostName) {
		this.eAssetHostName = eAssetHostName;
	}
	public String geteAssetIp() {
		return eAssetIp;
	}
	public void seteAssetIp(String eAssetIp) {
		this.eAssetIp = eAssetIp;
	}
	public String geteAssetOs() {
		return eAssetOs;
	}
	public void seteAssetOs(String eAssetOs) {
		this.eAssetOs = eAssetOs;
	}
	public String geteAssetDeviceType() {
		return eAssetDeviceType;
	}
	public void seteAssetDeviceType(String eAssetDeviceType) {
		this.eAssetDeviceType = eAssetDeviceType;
	}
	public String geteEquipmentOutKey() {
		return eEquipmentOutKey;
	}
	public void seteEquipmentOutKey(String eEquipmentOutKey) {
		this.eEquipmentOutKey = eEquipmentOutKey;
	}
	public String geteExitExporter() {
		return eExitExporter;
	}
	public void seteExitExporter(String eExitExporter) {
		this.eExitExporter = eExitExporter;
	}
	public String geteEntryExporterDate() {
		return eEntryExporterDate;
	}
	public void seteEntryExporterDate(String eEntryExporterDate) {
		this.eEntryExporterDate = eEntryExporterDate;
	}
	
	
	
	
	 
	
	 
}
