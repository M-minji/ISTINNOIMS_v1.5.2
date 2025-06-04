package egovframework.rndp.mes.common.service;

import egovframework.rndp.com.cmm.service.DefultVO;

public class MesCommonCheckVO extends DefultVO{
	private String eCheckKey        	= "";
	private String eCheckName        	= "";
	private String eCheckWdate       	= "";

	private String eCheckNo        		= "";
	private String eCheckItemKey      	= "";
	private String eCheckItemCheckKey   = "";
	private String eCheckItemName     	= "";
	private String eCheckContdition     	= "";
	private String eCheckGubn    	= "";

	private String eCheckPath        	= "";
	private String proChk       		= "";

	private String sGubunCateKey      	= "";
	private String sGubunKey      		= "";
	private String sGubunName   		= ""; 
	private String sGubunMemo     		= ""; 	
	
	private String searchWord      		= "";
	private String searchType      		= "";
	private String topStartDate   		= ""; 
	private String topEndDate     		= ""; 	
	
	
	
	//2020 07 31 모듈화를 위해  MesProductionVO 의 내용을 붙여넣음
	private int eStaffKey     					= 0; 	
	private String eStaffName     					= ""; 
	private String flagCheck     					= ""; 	
	private String flagCheck2     					= ""; 	
	private String eBarGubun     					= ""; 	
	private String pageGubun2     					= ""; 	
	private String barCnt     					= ""; 	
	private String jeagoChk     					= ""; 	
	private String officerGubun     					= ""; 	
	private String eOutGubunText     					= ""; 	
	private String eJeagoCnt     					= ""; 	
	private String eOrderNo     					= ""; 	
	private String eOutBarcode     					= ""; 	
	
	private String eBarcode     					= ""; 	
	private String eBarcodeSn     					= ""; 	
	private String prok     					= ""; 	
	private String	cateGubun     					= ""; 	
	private String 	eProdKey = "";
	private String 	eProdNo = "";
	private String 	eProdDate = "";
	private String 	eProdCdate = "";
	private String 	eItemKey = "";
	private String 	eItemName = "";
	private String 	eProcessKey = "";
	private String 	eProcessName = "";
	private String 	ePlaceKey = "";
	private String 	ePlaceName = "";
	private String 	eBomKey = "";
	private String 	eBomName = "";
	private String 	eProdCnt = "";
	private String 	eProdWdate = "";
	private String 	eProdUdate = "";
	private String 	eProdGubun = "";
	private String 	eFlag = "";
	private String 	sItemGubun1 = "";
	private String 	sItemGubun2 = "";
	private String 	sItemGubun = "";
	
	private String 	eProdItemKey = "";
	private String 	eProdItemCnt = "";
	private String 	eProdItemTotCnt = "";
	private String 	eProdItemSdate = "";
	private String 	eProdItemEdate = "";
	private String 	pageGubun = "";
	
	private String 	eProductionInNoTemp = "";
	private String 	eProcessPath = "";
	private String 	eSubItemKey = "";
	private String 	eSubItemName = "";
	private String 	eSubPlaceKey = "";
	private String 	eSubProcessKey = "";
	private String 	eSubFlag = "";
	private String 	eSubCheckKey = ""; 
	private String 	sItemCateKey = "";
	private String 	sItemCateName = "";
	private String 	eProdSn = "";
	private String 	sItemStandard = "";
	private String 	eItemPartNo = "";
	private String 	eItemCnt = "";
	private String 	eItemCntBox = "";
	private String 	eItemCntUnit = "";
	private String 	eInvenKey = "";
	private String 	eChulgoGubunKey = "";
	private String 	facGubun = "";
	private String 	eProcSn = "";
	private String 	eFailCnt = "";
	private String 	eProcessItemKey = "";
	private String 	eProcKey = "";
	private String 	eProcName = "";
	private String 	eProdSn2 = "";
	private String 	eProdItemProcKey = "";
	private String 	eFlag2 = "";
	private String 	eFlag3 = "";
	private String 	eFlag4 = "";
	private String 	gubun = "";
	private String 	eOutCnt = "";
	private String 	totCnt = "";
	private String 	sComKey = "";
	private String 	sComName = "";
	private String 	eOutDate = "";
	private String 	eOutEtc = "";
	private String 	eBuyIpgoWdate = "";
	
	private String 	eWorkKey = "";
	private String 	eWorkNo = "";
	private String 	eWorkDate = "";
	private String 	eWorkWdate = "";
	private String 	eWorkUdate = "";
	private String 	eWorkStaffName = "";
	private String 	eWorkStaffNum = "";
	private String 	eWorkCnt = "";
	private String 	eWorkEtc = "";
	private String 	eFailKey;
	private String 	eFailName = "";
	private String 	eFailPer = "";
	private String 	facGubunName = "";
	private String 	eProdStopTime = "";
	private String 	eProdStopGubun = "";
	private String 	eProdRestartTime = "";
	private String 	eProdStopEtc = "";
	private String 	eProdStopKind = "";
	private String 	eProdStopKey = "";
	private String 	eProdStopName = "";
	private String 	eProdStopGab = "";
	private String 	totTime = "";
	private String 	eFailItemCnt = "";
	
	private String 	eBuyChulgoNum = "";
	private String 	eBuyChulgoDate = "";
	private String 	eBuyChulgoEtc = "";
	private String 	eBuyChulgoTot = "";
	private String 	eChulgoGubun = "";
	private String 	eBuyChulgoKey = "";
	private String 	eBuyChulgoItemCnt = "";
	private String 	eBuyChulgoItemEtc = "";
	private String 	jeagoCnt = "";
	private String 	groupGubun = "";
	private String 	path = "";
	private String 	eSpeed = "";
	private String 	eTemp = "";
	private String 	eThread = "";
	
	private String 	eInKey = "";
	private String 	eInGubun = "";
	private String 	eOutKey = "";
	private String 	eOutGubun = "";
	private String 	jeagoGubun = "";
	
	private String 	eBuyIpgoFailKey = "";
	private String 	eBuyIpgoItemKey = "";
	private String 	eFailEtc = "";
	private String 	eBuyIpgoDate = "";
	private String 	eOutsoGubun = "";
	private String 	workCnt = "";
	private String 	eUnitPrice = "";
	private String 	sItemPrice = "";
	private String 	flagCnt = "";
	private String 	headFlag = "";
	private String 	eItemIndate = "";
	private String 	gubunDate = "";
	
	private String 	eItemOutdate = "";
	private String 	ePrice = "";
	private String 	sItemInven = "";
	private String 	sItemCode = "";
	private String 	sItemSalePrice = "";
	private String 	searchType2 = "";
	private String 	sItemUnit = "";
	private String 	eMakeCnt = "";
	private String 	eResCnt = "";
	private String 	eRunEQKey = "";
	private String 	eRunEQList = "";
	private String 	eRunEQName = "";
	private String 	sItemBox = "";
	
	private String 	ePackKey = "";
	private String 	ePackDate = "";
	private String 	ePackEtc = "";
	private String 	ePackItemKey = "";
	private String 	eBarcodeCnt = "";
	private String 	eBoxCnt = "";
	private String 	eUnitCnt = "";
	private String 	eSetDate = "";
	private String 	eSetEtc = "";
	private String 	eSetName = "";
	private String 	eItemKey2 = "";
	private String 	eSetItemName = "";
	private String 	eSetKey = "";
	private String 	eItemBarcode = "";
	private String 	eSetItemKey = "";
	
	private String 	eProdEtc = "";
	private String 	eApprovalChk = "";
	private String 	eOrderKey = "";
	private String 	eOrderItemKey = "";
	private String 	eOrderLine = "";
	private String 	eProdApprovalStatus = "";
	private String  eProdApprovalCheck = "";
	private String  eProdApprovalReason = "";
	
	private String kApprovalStaffKey = "";
	private String kPositionKey = "";
	private String kPositionName = "";
	private String kClassKey = "";
	private String kClassName = "";
	private String eApprovalGubun = "";
	private String kApprovalStaffName = "";
	private String kStaffKey = "";
	
	private String eItemDescription = "";
	private String eItemCode = "";
	private String eItemRev = "";
	private String eProdStatusYCnt = "";
	private String eItemGubun = "";
	private String eItemGubun2 = "";
	private String eOrderNum = "";
	private String eProcessSize = "";
	private String eStateGubun = "";
	private String eState = "";
	
	private String ePassGubun = "";
	private String eProdSdate = "";
	private String eProdEdate = "";
	private String nowProcess = "";
	private String nextProcess = "";
	private String ePassCnt = "";
	private String eStartTime = "";
	private String eEndTime = "";
	private String eProcessItemSn = "";
	private String eProdItemProcEtc = "";
	private String eFileName = "";
	private String eFileSn = "";
	private String eFileGubun = "";
	private String eGapTime = "";
	private String eStartTime2 = "";
	
	private String eProdTestKey = "";
	private String eTestKey = "";
	private String eTestValue = "";
	private String eTestName = "";
	
	private String eProdreqKey = "";
	private String eProdreqNo = "";
	private String eProdreqDate = "";
	private String eProdreqExpDate = "";
	private String eProdreqBrix = "";
	private String eProdreqCnt = "";
	private String eProdreqPh = "";
	private String eProdreqPack = "";
	private String eProdreqPsu = "";
	private String eProdreqItemKey = "";
	private String eProdreqItemMaker = "";
	private String eProdreqItemPer = "";
	private String eProdreqItemCnt = "";
	private String eProdreqItemSend = "";
	private String eProdreqItemEtc = "";
	private String sItemKey = "";
	private String eSubaljuKey = "";
	private String eSubaljuNum = "";
	
	private String eProdExpDate = "";
	private String eProdBrix = "";
	private String eProdPh = "";
	private String eProdPack = "";
	private String eProdPackCnt = "";
	private String eProdPsu = "";
	private String eProdItemMaker = "";
	private String eProdItemPer = "";
	private String eProdItemSend = "";
	private String eProdItemEtc = "";
	private String eInvenCnt = "";
	private String eProdProcState = "";
	private String eFirstCheckDate = "";
	private String eSecondCheckDate = "";
	private String eItemEtc = "";
	private String eIpgoCnt = "";
	private String eProdPackKey = "";
	private String eRealPackCnt = "";
	
	
	
	public int geteStaffKey() {
		return eStaffKey;
	}
	public void seteStaffKey(int eStaffKey) {
		this.eStaffKey = eStaffKey;
	}
	public String geteStaffName() {
		return eStaffName;
	}
	public void seteStaffName(String eStaffName) {
		this.eStaffName = eStaffName;
	}
	public String getFlagCheck() {
		return flagCheck;
	}
	public void setFlagCheck(String flagCheck) {
		this.flagCheck = flagCheck;
	}
	public String getFlagCheck2() {
		return flagCheck2;
	}
	public void setFlagCheck2(String flagCheck2) {
		this.flagCheck2 = flagCheck2;
	}
	public String geteBarGubun() {
		return eBarGubun;
	}
	public void seteBarGubun(String eBarGubun) {
		this.eBarGubun = eBarGubun;
	}
	public String getPageGubun2() {
		return pageGubun2;
	}
	public void setPageGubun2(String pageGubun2) {
		this.pageGubun2 = pageGubun2;
	}
	public String getBarCnt() {
		return barCnt;
	}
	public void setBarCnt(String barCnt) {
		this.barCnt = barCnt;
	}
	public String getJeagoChk() {
		return jeagoChk;
	}
	public void setJeagoChk(String jeagoChk) {
		this.jeagoChk = jeagoChk;
	}
	public String getOfficerGubun() {
		return officerGubun;
	}
	public void setOfficerGubun(String officerGubun) {
		this.officerGubun = officerGubun;
	}
	public String geteOutGubunText() {
		return eOutGubunText;
	}
	public void seteOutGubunText(String eOutGubunText) {
		this.eOutGubunText = eOutGubunText;
	}
	public String geteJeagoCnt() {
		return eJeagoCnt;
	}
	public void seteJeagoCnt(String eJeagoCnt) {
		this.eJeagoCnt = eJeagoCnt;
	}
	public String geteOrderNo() {
		return eOrderNo;
	}
	public void seteOrderNo(String eOrderNo) {
		this.eOrderNo = eOrderNo;
	}
	public String geteOutBarcode() {
		return eOutBarcode;
	}
	public void seteOutBarcode(String eOutBarcode) {
		this.eOutBarcode = eOutBarcode;
	}
	public String geteBarcode() {
		return eBarcode;
	}
	public void seteBarcode(String eBarcode) {
		this.eBarcode = eBarcode;
	}
	public String geteBarcodeSn() {
		return eBarcodeSn;
	}
	public void seteBarcodeSn(String eBarcodeSn) {
		this.eBarcodeSn = eBarcodeSn;
	}
	public String getProk() {
		return prok;
	}
	public void setProk(String prok) {
		this.prok = prok;
	}
	public String getCateGubun() {
		return cateGubun;
	}
	public void setCateGubun(String cateGubun) {
		this.cateGubun = cateGubun;
	}
	public String geteProdKey() {
		return eProdKey;
	}
	public void seteProdKey(String eProdKey) {
		this.eProdKey = eProdKey;
	}
	public String geteProdNo() {
		return eProdNo;
	}
	public void seteProdNo(String eProdNo) {
		this.eProdNo = eProdNo;
	}
	public String geteProdDate() {
		return eProdDate;
	}
	public void seteProdDate(String eProdDate) {
		this.eProdDate = eProdDate;
	}
	public String geteProdCdate() {
		return eProdCdate;
	}
	public void seteProdCdate(String eProdCdate) {
		this.eProdCdate = eProdCdate;
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
	public String geteProcessKey() {
		return eProcessKey;
	}
	public void seteProcessKey(String eProcessKey) {
		this.eProcessKey = eProcessKey;
	}
	public String geteProcessName() {
		return eProcessName;
	}
	public void seteProcessName(String eProcessName) {
		this.eProcessName = eProcessName;
	}
	public String getePlaceKey() {
		return ePlaceKey;
	}
	public void setePlaceKey(String ePlaceKey) {
		this.ePlaceKey = ePlaceKey;
	}
	public String getePlaceName() {
		return ePlaceName;
	}
	public void setePlaceName(String ePlaceName) {
		this.ePlaceName = ePlaceName;
	}
	public String geteBomKey() {
		return eBomKey;
	}
	public void seteBomKey(String eBomKey) {
		this.eBomKey = eBomKey;
	}
	public String geteBomName() {
		return eBomName;
	}
	public void seteBomName(String eBomName) {
		this.eBomName = eBomName;
	}
	public String geteProdCnt() {
		return eProdCnt;
	}
	public void seteProdCnt(String eProdCnt) {
		this.eProdCnt = eProdCnt;
	}
	public String geteProdWdate() {
		return eProdWdate;
	}
	public void seteProdWdate(String eProdWdate) {
		this.eProdWdate = eProdWdate;
	}
	public String geteProdUdate() {
		return eProdUdate;
	}
	public void seteProdUdate(String eProdUdate) {
		this.eProdUdate = eProdUdate;
	}
	public String geteProdGubun() {
		return eProdGubun;
	}
	public void seteProdGubun(String eProdGubun) {
		this.eProdGubun = eProdGubun;
	}
	public String geteFlag() {
		return eFlag;
	}
	public void seteFlag(String eFlag) {
		this.eFlag = eFlag;
	}
	public String getsItemGubun1() {
		return sItemGubun1;
	}
	public void setsItemGubun1(String sItemGubun1) {
		this.sItemGubun1 = sItemGubun1;
	}
	public String getsItemGubun2() {
		return sItemGubun2;
	}
	public void setsItemGubun2(String sItemGubun2) {
		this.sItemGubun2 = sItemGubun2;
	}
	public String getsItemGubun() {
		return sItemGubun;
	}
	public void setsItemGubun(String sItemGubun) {
		this.sItemGubun = sItemGubun;
	}
	public String geteProdItemKey() {
		return eProdItemKey;
	}
	public void seteProdItemKey(String eProdItemKey) {
		this.eProdItemKey = eProdItemKey;
	}
	public String geteProdItemCnt() {
		return eProdItemCnt;
	}
	public void seteProdItemCnt(String eProdItemCnt) {
		this.eProdItemCnt = eProdItemCnt;
	}
	public String geteProdItemTotCnt() {
		return eProdItemTotCnt;
	}
	public void seteProdItemTotCnt(String eProdItemTotCnt) {
		this.eProdItemTotCnt = eProdItemTotCnt;
	}
	public String geteProdItemSdate() {
		return eProdItemSdate;
	}
	public void seteProdItemSdate(String eProdItemSdate) {
		this.eProdItemSdate = eProdItemSdate;
	}
	public String geteProdItemEdate() {
		return eProdItemEdate;
	}
	public void seteProdItemEdate(String eProdItemEdate) {
		this.eProdItemEdate = eProdItemEdate;
	}
	public String getPageGubun() {
		return pageGubun;
	}
	public void setPageGubun(String pageGubun) {
		this.pageGubun = pageGubun;
	}
	public String geteProductionInNoTemp() {
		return eProductionInNoTemp;
	}
	public void seteProductionInNoTemp(String eProductionInNoTemp) {
		this.eProductionInNoTemp = eProductionInNoTemp;
	}
	public String geteProcessPath() {
		return eProcessPath;
	}
	public void seteProcessPath(String eProcessPath) {
		this.eProcessPath = eProcessPath;
	}
	public String geteSubItemKey() {
		return eSubItemKey;
	}
	public void seteSubItemKey(String eSubItemKey) {
		this.eSubItemKey = eSubItemKey;
	}
	public String geteSubItemName() {
		return eSubItemName;
	}
	public void seteSubItemName(String eSubItemName) {
		this.eSubItemName = eSubItemName;
	}
	public String geteSubPlaceKey() {
		return eSubPlaceKey;
	}
	public void seteSubPlaceKey(String eSubPlaceKey) {
		this.eSubPlaceKey = eSubPlaceKey;
	}
	public String geteSubProcessKey() {
		return eSubProcessKey;
	}
	public void seteSubProcessKey(String eSubProcessKey) {
		this.eSubProcessKey = eSubProcessKey;
	}
	public String geteSubFlag() {
		return eSubFlag;
	}
	public void seteSubFlag(String eSubFlag) {
		this.eSubFlag = eSubFlag;
	}
	public String geteSubCheckKey() {
		return eSubCheckKey;
	}
	public void seteSubCheckKey(String eSubCheckKey) {
		this.eSubCheckKey = eSubCheckKey;
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
	public String geteProdSn() {
		return eProdSn;
	}
	public void seteProdSn(String eProdSn) {
		this.eProdSn = eProdSn;
	}
	public String getsItemStandard() {
		return sItemStandard;
	}
	public void setsItemStandard(String sItemStandard) {
		this.sItemStandard = sItemStandard;
	}
	public String geteItemPartNo() {
		return eItemPartNo;
	}
	public void seteItemPartNo(String eItemPartNo) {
		this.eItemPartNo = eItemPartNo;
	}
	public String geteItemCnt() {
		return eItemCnt;
	}
	public void seteItemCnt(String eItemCnt) {
		this.eItemCnt = eItemCnt;
	}
	public String geteItemCntBox() {
		return eItemCntBox;
	}
	public void seteItemCntBox(String eItemCntBox) {
		this.eItemCntBox = eItemCntBox;
	}
	public String geteItemCntUnit() {
		return eItemCntUnit;
	}
	public void seteItemCntUnit(String eItemCntUnit) {
		this.eItemCntUnit = eItemCntUnit;
	}
	public String geteInvenKey() {
		return eInvenKey;
	}
	public void seteInvenKey(String eInvenKey) {
		this.eInvenKey = eInvenKey;
	}
	public String geteChulgoGubunKey() {
		return eChulgoGubunKey;
	}
	public void seteChulgoGubunKey(String eChulgoGubunKey) {
		this.eChulgoGubunKey = eChulgoGubunKey;
	}
	public String getFacGubun() {
		return facGubun;
	}
	public void setFacGubun(String facGubun) {
		this.facGubun = facGubun;
	}
	public String geteProcSn() {
		return eProcSn;
	}
	public void seteProcSn(String eProcSn) {
		this.eProcSn = eProcSn;
	}
	public String geteFailCnt() {
		return eFailCnt;
	}
	public void seteFailCnt(String eFailCnt) {
		this.eFailCnt = eFailCnt;
	}
	public String geteProcessItemKey() {
		return eProcessItemKey;
	}
	public void seteProcessItemKey(String eProcessItemKey) {
		this.eProcessItemKey = eProcessItemKey;
	}
	public String geteProcKey() {
		return eProcKey;
	}
	public void seteProcKey(String eProcKey) {
		this.eProcKey = eProcKey;
	}
	public String geteProcName() {
		return eProcName;
	}
	public void seteProcName(String eProcName) {
		this.eProcName = eProcName;
	}
	public String geteProdSn2() {
		return eProdSn2;
	}
	public void seteProdSn2(String eProdSn2) {
		this.eProdSn2 = eProdSn2;
	}
	public String geteProdItemProcKey() {
		return eProdItemProcKey;
	}
	public void seteProdItemProcKey(String eProdItemProcKey) {
		this.eProdItemProcKey = eProdItemProcKey;
	}
	public String geteFlag2() {
		return eFlag2;
	}
	public void seteFlag2(String eFlag2) {
		this.eFlag2 = eFlag2;
	}
	public String geteFlag3() {
		return eFlag3;
	}
	public void seteFlag3(String eFlag3) {
		this.eFlag3 = eFlag3;
	}
	public String geteFlag4() {
		return eFlag4;
	}
	public void seteFlag4(String eFlag4) {
		this.eFlag4 = eFlag4;
	}
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}
	public String geteOutCnt() {
		return eOutCnt;
	}
	public void seteOutCnt(String eOutCnt) {
		this.eOutCnt = eOutCnt;
	}
	public String getTotCnt() {
		return totCnt;
	}
	public void setTotCnt(String totCnt) {
		this.totCnt = totCnt;
	}
	public String getsComKey() {
		return sComKey;
	}
	public void setsComKey(String sComKey) {
		this.sComKey = sComKey;
	}
	public String getsComName() {
		return sComName;
	}
	public void setsComName(String sComName) {
		this.sComName = sComName;
	}
	public String geteOutDate() {
		return eOutDate;
	}
	public void seteOutDate(String eOutDate) {
		this.eOutDate = eOutDate;
	}
	public String geteOutEtc() {
		return eOutEtc;
	}
	public void seteOutEtc(String eOutEtc) {
		this.eOutEtc = eOutEtc;
	}
	public String geteBuyIpgoWdate() {
		return eBuyIpgoWdate;
	}
	public void seteBuyIpgoWdate(String eBuyIpgoWdate) {
		this.eBuyIpgoWdate = eBuyIpgoWdate;
	}
	public String geteWorkKey() {
		return eWorkKey;
	}
	public void seteWorkKey(String eWorkKey) {
		this.eWorkKey = eWorkKey;
	}
	public String geteWorkNo() {
		return eWorkNo;
	}
	public void seteWorkNo(String eWorkNo) {
		this.eWorkNo = eWorkNo;
	}
	public String geteWorkDate() {
		return eWorkDate;
	}
	public void seteWorkDate(String eWorkDate) {
		this.eWorkDate = eWorkDate;
	}
	public String geteWorkWdate() {
		return eWorkWdate;
	}
	public void seteWorkWdate(String eWorkWdate) {
		this.eWorkWdate = eWorkWdate;
	}
	public String geteWorkUdate() {
		return eWorkUdate;
	}
	public void seteWorkUdate(String eWorkUdate) {
		this.eWorkUdate = eWorkUdate;
	}
	public String geteWorkStaffName() {
		return eWorkStaffName;
	}
	public void seteWorkStaffName(String eWorkStaffName) {
		this.eWorkStaffName = eWorkStaffName;
	}
	public String geteWorkStaffNum() {
		return eWorkStaffNum;
	}
	public void seteWorkStaffNum(String eWorkStaffNum) {
		this.eWorkStaffNum = eWorkStaffNum;
	}
	public String geteWorkCnt() {
		return eWorkCnt;
	}
	public void seteWorkCnt(String eWorkCnt) {
		this.eWorkCnt = eWorkCnt;
	}
	public String geteWorkEtc() {
		return eWorkEtc;
	}
	public void seteWorkEtc(String eWorkEtc) {
		this.eWorkEtc = eWorkEtc;
	}
	public String geteFailKey() {
		return eFailKey;
	}
	public void seteFailKey(String eFailKey) {
		this.eFailKey = eFailKey;
	}
	public String geteFailName() {
		return eFailName;
	}
	public void seteFailName(String eFailName) {
		this.eFailName = eFailName;
	}
	public String geteFailPer() {
		return eFailPer;
	}
	public void seteFailPer(String eFailPer) {
		this.eFailPer = eFailPer;
	}
	public String getFacGubunName() {
		return facGubunName;
	}
	public void setFacGubunName(String facGubunName) {
		this.facGubunName = facGubunName;
	}
	public String geteProdStopTime() {
		return eProdStopTime;
	}
	public void seteProdStopTime(String eProdStopTime) {
		this.eProdStopTime = eProdStopTime;
	}
	public String geteProdStopGubun() {
		return eProdStopGubun;
	}
	public void seteProdStopGubun(String eProdStopGubun) {
		this.eProdStopGubun = eProdStopGubun;
	}
	public String geteProdRestartTime() {
		return eProdRestartTime;
	}
	public void seteProdRestartTime(String eProdRestartTime) {
		this.eProdRestartTime = eProdRestartTime;
	}
	public String geteProdStopEtc() {
		return eProdStopEtc;
	}
	public void seteProdStopEtc(String eProdStopEtc) {
		this.eProdStopEtc = eProdStopEtc;
	}
	public String geteProdStopKind() {
		return eProdStopKind;
	}
	public void seteProdStopKind(String eProdStopKind) {
		this.eProdStopKind = eProdStopKind;
	}
	public String geteProdStopKey() {
		return eProdStopKey;
	}
	public void seteProdStopKey(String eProdStopKey) {
		this.eProdStopKey = eProdStopKey;
	}
	public String geteProdStopName() {
		return eProdStopName;
	}
	public void seteProdStopName(String eProdStopName) {
		this.eProdStopName = eProdStopName;
	}
	public String geteProdStopGab() {
		return eProdStopGab;
	}
	public void seteProdStopGab(String eProdStopGab) {
		this.eProdStopGab = eProdStopGab;
	}
	public String getTotTime() {
		return totTime;
	}
	public void setTotTime(String totTime) {
		this.totTime = totTime;
	}
	public String geteFailItemCnt() {
		return eFailItemCnt;
	}
	public void seteFailItemCnt(String eFailItemCnt) {
		this.eFailItemCnt = eFailItemCnt;
	}
	public String geteBuyChulgoNum() {
		return eBuyChulgoNum;
	}
	public void seteBuyChulgoNum(String eBuyChulgoNum) {
		this.eBuyChulgoNum = eBuyChulgoNum;
	}
	public String geteBuyChulgoDate() {
		return eBuyChulgoDate;
	}
	public void seteBuyChulgoDate(String eBuyChulgoDate) {
		this.eBuyChulgoDate = eBuyChulgoDate;
	}
	public String geteBuyChulgoEtc() {
		return eBuyChulgoEtc;
	}
	public void seteBuyChulgoEtc(String eBuyChulgoEtc) {
		this.eBuyChulgoEtc = eBuyChulgoEtc;
	}
	public String geteBuyChulgoTot() {
		return eBuyChulgoTot;
	}
	public void seteBuyChulgoTot(String eBuyChulgoTot) {
		this.eBuyChulgoTot = eBuyChulgoTot;
	}
	public String geteChulgoGubun() {
		return eChulgoGubun;
	}
	public void seteChulgoGubun(String eChulgoGubun) {
		this.eChulgoGubun = eChulgoGubun;
	}
	public String geteBuyChulgoKey() {
		return eBuyChulgoKey;
	}
	public void seteBuyChulgoKey(String eBuyChulgoKey) {
		this.eBuyChulgoKey = eBuyChulgoKey;
	}
	public String geteBuyChulgoItemCnt() {
		return eBuyChulgoItemCnt;
	}
	public void seteBuyChulgoItemCnt(String eBuyChulgoItemCnt) {
		this.eBuyChulgoItemCnt = eBuyChulgoItemCnt;
	}
	public String geteBuyChulgoItemEtc() {
		return eBuyChulgoItemEtc;
	}
	public void seteBuyChulgoItemEtc(String eBuyChulgoItemEtc) {
		this.eBuyChulgoItemEtc = eBuyChulgoItemEtc;
	}
	public String getJeagoCnt() {
		return jeagoCnt;
	}
	public void setJeagoCnt(String jeagoCnt) {
		this.jeagoCnt = jeagoCnt;
	}
	public String getGroupGubun() {
		return groupGubun;
	}
	public void setGroupGubun(String groupGubun) {
		this.groupGubun = groupGubun;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String geteSpeed() {
		return eSpeed;
	}
	public void seteSpeed(String eSpeed) {
		this.eSpeed = eSpeed;
	}
	public String geteTemp() {
		return eTemp;
	}
	public void seteTemp(String eTemp) {
		this.eTemp = eTemp;
	}
	public String geteThread() {
		return eThread;
	}
	public void seteThread(String eThread) {
		this.eThread = eThread;
	}
	public String geteInKey() {
		return eInKey;
	}
	public void seteInKey(String eInKey) {
		this.eInKey = eInKey;
	}
	public String geteInGubun() {
		return eInGubun;
	}
	public void seteInGubun(String eInGubun) {
		this.eInGubun = eInGubun;
	}
	public String geteOutKey() {
		return eOutKey;
	}
	public void seteOutKey(String eOutKey) {
		this.eOutKey = eOutKey;
	}
	public String geteOutGubun() {
		return eOutGubun;
	}
	public void seteOutGubun(String eOutGubun) {
		this.eOutGubun = eOutGubun;
	}
	public String getJeagoGubun() {
		return jeagoGubun;
	}
	public void setJeagoGubun(String jeagoGubun) {
		this.jeagoGubun = jeagoGubun;
	}
	public String geteBuyIpgoFailKey() {
		return eBuyIpgoFailKey;
	}
	public void seteBuyIpgoFailKey(String eBuyIpgoFailKey) {
		this.eBuyIpgoFailKey = eBuyIpgoFailKey;
	}
	public String geteBuyIpgoItemKey() {
		return eBuyIpgoItemKey;
	}
	public void seteBuyIpgoItemKey(String eBuyIpgoItemKey) {
		this.eBuyIpgoItemKey = eBuyIpgoItemKey;
	}
	public String geteFailEtc() {
		return eFailEtc;
	}
	public void seteFailEtc(String eFailEtc) {
		this.eFailEtc = eFailEtc;
	}
	public String geteBuyIpgoDate() {
		return eBuyIpgoDate;
	}
	public void seteBuyIpgoDate(String eBuyIpgoDate) {
		this.eBuyIpgoDate = eBuyIpgoDate;
	}
	public String geteOutsoGubun() {
		return eOutsoGubun;
	}
	public void seteOutsoGubun(String eOutsoGubun) {
		this.eOutsoGubun = eOutsoGubun;
	}
	public String getWorkCnt() {
		return workCnt;
	}
	public void setWorkCnt(String workCnt) {
		this.workCnt = workCnt;
	}
	public String geteUnitPrice() {
		return eUnitPrice;
	}
	public void seteUnitPrice(String eUnitPrice) {
		this.eUnitPrice = eUnitPrice;
	}
	public String getsItemPrice() {
		return sItemPrice;
	}
	public void setsItemPrice(String sItemPrice) {
		this.sItemPrice = sItemPrice;
	}
	public String getFlagCnt() {
		return flagCnt;
	}
	public void setFlagCnt(String flagCnt) {
		this.flagCnt = flagCnt;
	}
	public String getHeadFlag() {
		return headFlag;
	}
	public void setHeadFlag(String headFlag) {
		this.headFlag = headFlag;
	}
	public String geteItemIndate() {
		return eItemIndate;
	}
	public void seteItemIndate(String eItemIndate) {
		this.eItemIndate = eItemIndate;
	}
	public String getGubunDate() {
		return gubunDate;
	}
	public void setGubunDate(String gubunDate) {
		this.gubunDate = gubunDate;
	}
	public String geteItemOutdate() {
		return eItemOutdate;
	}
	public void seteItemOutdate(String eItemOutdate) {
		this.eItemOutdate = eItemOutdate;
	}
	public String getePrice() {
		return ePrice;
	}
	public void setePrice(String ePrice) {
		this.ePrice = ePrice;
	}
	public String getsItemInven() {
		return sItemInven;
	}
	public void setsItemInven(String sItemInven) {
		this.sItemInven = sItemInven;
	}
	public String getsItemCode() {
		return sItemCode;
	}
	public void setsItemCode(String sItemCode) {
		this.sItemCode = sItemCode;
	}
	public String getsItemSalePrice() {
		return sItemSalePrice;
	}
	public void setsItemSalePrice(String sItemSalePrice) {
		this.sItemSalePrice = sItemSalePrice;
	}
	public String getSearchType2() {
		return searchType2;
	}
	public void setSearchType2(String searchType2) {
		this.searchType2 = searchType2;
	}
	public String getsItemUnit() {
		return sItemUnit;
	}
	public void setsItemUnit(String sItemUnit) {
		this.sItemUnit = sItemUnit;
	}
	public String geteMakeCnt() {
		return eMakeCnt;
	}
	public void seteMakeCnt(String eMakeCnt) {
		this.eMakeCnt = eMakeCnt;
	}
	public String geteResCnt() {
		return eResCnt;
	}
	public void seteResCnt(String eResCnt) {
		this.eResCnt = eResCnt;
	}
	public String geteRunEQKey() {
		return eRunEQKey;
	}
	public void seteRunEQKey(String eRunEQKey) {
		this.eRunEQKey = eRunEQKey;
	}
	public String geteRunEQList() {
		return eRunEQList;
	}
	public void seteRunEQList(String eRunEQList) {
		this.eRunEQList = eRunEQList;
	}
	public String geteRunEQName() {
		return eRunEQName;
	}
	public void seteRunEQName(String eRunEQName) {
		this.eRunEQName = eRunEQName;
	}
	public String getsItemBox() {
		return sItemBox;
	}
	public void setsItemBox(String sItemBox) {
		this.sItemBox = sItemBox;
	}
	public String getePackKey() {
		return ePackKey;
	}
	public void setePackKey(String ePackKey) {
		this.ePackKey = ePackKey;
	}
	public String getePackDate() {
		return ePackDate;
	}
	public void setePackDate(String ePackDate) {
		this.ePackDate = ePackDate;
	}
	public String getePackEtc() {
		return ePackEtc;
	}
	public void setePackEtc(String ePackEtc) {
		this.ePackEtc = ePackEtc;
	}
	public String getePackItemKey() {
		return ePackItemKey;
	}
	public void setePackItemKey(String ePackItemKey) {
		this.ePackItemKey = ePackItemKey;
	}
	public String geteBarcodeCnt() {
		return eBarcodeCnt;
	}
	public void seteBarcodeCnt(String eBarcodeCnt) {
		this.eBarcodeCnt = eBarcodeCnt;
	}
	public String geteBoxCnt() {
		return eBoxCnt;
	}
	public void seteBoxCnt(String eBoxCnt) {
		this.eBoxCnt = eBoxCnt;
	}
	public String geteUnitCnt() {
		return eUnitCnt;
	}
	public void seteUnitCnt(String eUnitCnt) {
		this.eUnitCnt = eUnitCnt;
	}
	public String geteSetDate() {
		return eSetDate;
	}
	public void seteSetDate(String eSetDate) {
		this.eSetDate = eSetDate;
	}
	public String geteSetEtc() {
		return eSetEtc;
	}
	public void seteSetEtc(String eSetEtc) {
		this.eSetEtc = eSetEtc;
	}
	public String geteSetName() {
		return eSetName;
	}
	public void seteSetName(String eSetName) {
		this.eSetName = eSetName;
	}
	public String geteItemKey2() {
		return eItemKey2;
	}
	public void seteItemKey2(String eItemKey2) {
		this.eItemKey2 = eItemKey2;
	}
	public String geteSetItemName() {
		return eSetItemName;
	}
	public void seteSetItemName(String eSetItemName) {
		this.eSetItemName = eSetItemName;
	}
	public String geteSetKey() {
		return eSetKey;
	}
	public void seteSetKey(String eSetKey) {
		this.eSetKey = eSetKey;
	}
	public String geteItemBarcode() {
		return eItemBarcode;
	}
	public void seteItemBarcode(String eItemBarcode) {
		this.eItemBarcode = eItemBarcode;
	}
	public String geteSetItemKey() {
		return eSetItemKey;
	}
	public void seteSetItemKey(String eSetItemKey) {
		this.eSetItemKey = eSetItemKey;
	}
	public String geteProdEtc() {
		return eProdEtc;
	}
	public void seteProdEtc(String eProdEtc) {
		this.eProdEtc = eProdEtc;
	}
	public String geteApprovalChk() {
		return eApprovalChk;
	}
	public void seteApprovalChk(String eApprovalChk) {
		this.eApprovalChk = eApprovalChk;
	}
	public String geteOrderKey() {
		return eOrderKey;
	}
	public void seteOrderKey(String eOrderKey) {
		this.eOrderKey = eOrderKey;
	}
	public String geteOrderItemKey() {
		return eOrderItemKey;
	}
	public void seteOrderItemKey(String eOrderItemKey) {
		this.eOrderItemKey = eOrderItemKey;
	}
	public String geteOrderLine() {
		return eOrderLine;
	}
	public void seteOrderLine(String eOrderLine) {
		this.eOrderLine = eOrderLine;
	}
	public String geteProdApprovalStatus() {
		return eProdApprovalStatus;
	}
	public void seteProdApprovalStatus(String eProdApprovalStatus) {
		this.eProdApprovalStatus = eProdApprovalStatus;
	}
	public String geteProdApprovalCheck() {
		return eProdApprovalCheck;
	}
	public void seteProdApprovalCheck(String eProdApprovalCheck) {
		this.eProdApprovalCheck = eProdApprovalCheck;
	}
	public String geteProdApprovalReason() {
		return eProdApprovalReason;
	}
	public void seteProdApprovalReason(String eProdApprovalReason) {
		this.eProdApprovalReason = eProdApprovalReason;
	}
	public String getkApprovalStaffKey() {
		return kApprovalStaffKey;
	}
	public void setkApprovalStaffKey(String kApprovalStaffKey) {
		this.kApprovalStaffKey = kApprovalStaffKey;
	}
	public String getkPositionKey() {
		return kPositionKey;
	}
	public void setkPositionKey(String kPositionKey) {
		this.kPositionKey = kPositionKey;
	}
	public String getkPositionName() {
		return kPositionName;
	}
	public void setkPositionName(String kPositionName) {
		this.kPositionName = kPositionName;
	}
	public String getkClassKey() {
		return kClassKey;
	}
	public void setkClassKey(String kClassKey) {
		this.kClassKey = kClassKey;
	}
	public String getkClassName() {
		return kClassName;
	}
	public void setkClassName(String kClassName) {
		this.kClassName = kClassName;
	}
	public String geteApprovalGubun() {
		return eApprovalGubun;
	}
	public void seteApprovalGubun(String eApprovalGubun) {
		this.eApprovalGubun = eApprovalGubun;
	}
	public String getkApprovalStaffName() {
		return kApprovalStaffName;
	}
	public void setkApprovalStaffName(String kApprovalStaffName) {
		this.kApprovalStaffName = kApprovalStaffName;
	}
	public String getkStaffKey() {
		return kStaffKey;
	}
	public void setkStaffKey(String kStaffKey) {
		this.kStaffKey = kStaffKey;
	}
	public String geteItemDescription() {
		return eItemDescription;
	}
	public void seteItemDescription(String eItemDescription) {
		this.eItemDescription = eItemDescription;
	}
	public String geteItemCode() {
		return eItemCode;
	}
	public void seteItemCode(String eItemCode) {
		this.eItemCode = eItemCode;
	}
	public String geteItemRev() {
		return eItemRev;
	}
	public void seteItemRev(String eItemRev) {
		this.eItemRev = eItemRev;
	}
	public String geteProdStatusYCnt() {
		return eProdStatusYCnt;
	}
	public void seteProdStatusYCnt(String eProdStatusYCnt) {
		this.eProdStatusYCnt = eProdStatusYCnt;
	}
	public String geteItemGubun() {
		return eItemGubun;
	}
	public void seteItemGubun(String eItemGubun) {
		this.eItemGubun = eItemGubun;
	}
	public String geteItemGubun2() {
		return eItemGubun2;
	}
	public void seteItemGubun2(String eItemGubun2) {
		this.eItemGubun2 = eItemGubun2;
	}
	public String geteOrderNum() {
		return eOrderNum;
	}
	public void seteOrderNum(String eOrderNum) {
		this.eOrderNum = eOrderNum;
	}
	public String geteProcessSize() {
		return eProcessSize;
	}
	public void seteProcessSize(String eProcessSize) {
		this.eProcessSize = eProcessSize;
	}
	public String geteStateGubun() {
		return eStateGubun;
	}
	public void seteStateGubun(String eStateGubun) {
		this.eStateGubun = eStateGubun;
	}
	public String geteState() {
		return eState;
	}
	public void seteState(String eState) {
		this.eState = eState;
	}
	public String getePassGubun() {
		return ePassGubun;
	}
	public void setePassGubun(String ePassGubun) {
		this.ePassGubun = ePassGubun;
	}
	public String geteProdSdate() {
		return eProdSdate;
	}
	public void seteProdSdate(String eProdSdate) {
		this.eProdSdate = eProdSdate;
	}
	public String geteProdEdate() {
		return eProdEdate;
	}
	public void seteProdEdate(String eProdEdate) {
		this.eProdEdate = eProdEdate;
	}
	public String getNowProcess() {
		return nowProcess;
	}
	public void setNowProcess(String nowProcess) {
		this.nowProcess = nowProcess;
	}
	public String getNextProcess() {
		return nextProcess;
	}
	public void setNextProcess(String nextProcess) {
		this.nextProcess = nextProcess;
	}
	public String getePassCnt() {
		return ePassCnt;
	}
	public void setePassCnt(String ePassCnt) {
		this.ePassCnt = ePassCnt;
	}
	public String geteStartTime() {
		return eStartTime;
	}
	public void seteStartTime(String eStartTime) {
		this.eStartTime = eStartTime;
	}
	public String geteEndTime() {
		return eEndTime;
	}
	public void seteEndTime(String eEndTime) {
		this.eEndTime = eEndTime;
	}
	public String geteProcessItemSn() {
		return eProcessItemSn;
	}
	public void seteProcessItemSn(String eProcessItemSn) {
		this.eProcessItemSn = eProcessItemSn;
	}
	public String geteProdItemProcEtc() {
		return eProdItemProcEtc;
	}
	public void seteProdItemProcEtc(String eProdItemProcEtc) {
		this.eProdItemProcEtc = eProdItemProcEtc;
	}
	public String geteFileName() {
		return eFileName;
	}
	public void seteFileName(String eFileName) {
		this.eFileName = eFileName;
	}
	public String geteFileSn() {
		return eFileSn;
	}
	public void seteFileSn(String eFileSn) {
		this.eFileSn = eFileSn;
	}
	public String geteFileGubun() {
		return eFileGubun;
	}
	public void seteFileGubun(String eFileGubun) {
		this.eFileGubun = eFileGubun;
	}
	public String geteGapTime() {
		return eGapTime;
	}
	public void seteGapTime(String eGapTime) {
		this.eGapTime = eGapTime;
	}
	public String geteStartTime2() {
		return eStartTime2;
	}
	public void seteStartTime2(String eStartTime2) {
		this.eStartTime2 = eStartTime2;
	}
	public String geteProdTestKey() {
		return eProdTestKey;
	}
	public void seteProdTestKey(String eProdTestKey) {
		this.eProdTestKey = eProdTestKey;
	}
	public String geteTestKey() {
		return eTestKey;
	}
	public void seteTestKey(String eTestKey) {
		this.eTestKey = eTestKey;
	}
	public String geteTestValue() {
		return eTestValue;
	}
	public void seteTestValue(String eTestValue) {
		this.eTestValue = eTestValue;
	}
	public String geteTestName() {
		return eTestName;
	}
	public void seteTestName(String eTestName) {
		this.eTestName = eTestName;
	}
	public String geteProdreqKey() {
		return eProdreqKey;
	}
	public void seteProdreqKey(String eProdreqKey) {
		this.eProdreqKey = eProdreqKey;
	}
	public String geteProdreqNo() {
		return eProdreqNo;
	}
	public void seteProdreqNo(String eProdreqNo) {
		this.eProdreqNo = eProdreqNo;
	}
	public String geteProdreqDate() {
		return eProdreqDate;
	}
	public void seteProdreqDate(String eProdreqDate) {
		this.eProdreqDate = eProdreqDate;
	}
	public String geteProdreqExpDate() {
		return eProdreqExpDate;
	}
	public void seteProdreqExpDate(String eProdreqExpDate) {
		this.eProdreqExpDate = eProdreqExpDate;
	}
	public String geteProdreqBrix() {
		return eProdreqBrix;
	}
	public void seteProdreqBrix(String eProdreqBrix) {
		this.eProdreqBrix = eProdreqBrix;
	}
	public String geteProdreqCnt() {
		return eProdreqCnt;
	}
	public void seteProdreqCnt(String eProdreqCnt) {
		this.eProdreqCnt = eProdreqCnt;
	}
	public String geteProdreqPh() {
		return eProdreqPh;
	}
	public void seteProdreqPh(String eProdreqPh) {
		this.eProdreqPh = eProdreqPh;
	}
	public String geteProdreqPack() {
		return eProdreqPack;
	}
	public void seteProdreqPack(String eProdreqPack) {
		this.eProdreqPack = eProdreqPack;
	}
	public String geteProdreqPsu() {
		return eProdreqPsu;
	}
	public void seteProdreqPsu(String eProdreqPsu) {
		this.eProdreqPsu = eProdreqPsu;
	}
	public String geteProdreqItemKey() {
		return eProdreqItemKey;
	}
	public void seteProdreqItemKey(String eProdreqItemKey) {
		this.eProdreqItemKey = eProdreqItemKey;
	}
	public String geteProdreqItemMaker() {
		return eProdreqItemMaker;
	}
	public void seteProdreqItemMaker(String eProdreqItemMaker) {
		this.eProdreqItemMaker = eProdreqItemMaker;
	}
	public String geteProdreqItemPer() {
		return eProdreqItemPer;
	}
	public void seteProdreqItemPer(String eProdreqItemPer) {
		this.eProdreqItemPer = eProdreqItemPer;
	}
	public String geteProdreqItemCnt() {
		return eProdreqItemCnt;
	}
	public void seteProdreqItemCnt(String eProdreqItemCnt) {
		this.eProdreqItemCnt = eProdreqItemCnt;
	}
	public String geteProdreqItemSend() {
		return eProdreqItemSend;
	}
	public void seteProdreqItemSend(String eProdreqItemSend) {
		this.eProdreqItemSend = eProdreqItemSend;
	}
	public String geteProdreqItemEtc() {
		return eProdreqItemEtc;
	}
	public void seteProdreqItemEtc(String eProdreqItemEtc) {
		this.eProdreqItemEtc = eProdreqItemEtc;
	}
	public String getsItemKey() {
		return sItemKey;
	}
	public void setsItemKey(String sItemKey) {
		this.sItemKey = sItemKey;
	}
	public String geteSubaljuKey() {
		return eSubaljuKey;
	}
	public void seteSubaljuKey(String eSubaljuKey) {
		this.eSubaljuKey = eSubaljuKey;
	}
	public String geteSubaljuNum() {
		return eSubaljuNum;
	}
	public void seteSubaljuNum(String eSubaljuNum) {
		this.eSubaljuNum = eSubaljuNum;
	}
	public String geteProdExpDate() {
		return eProdExpDate;
	}
	public void seteProdExpDate(String eProdExpDate) {
		this.eProdExpDate = eProdExpDate;
	}
	public String geteProdBrix() {
		return eProdBrix;
	}
	public void seteProdBrix(String eProdBrix) {
		this.eProdBrix = eProdBrix;
	}
	public String geteProdPh() {
		return eProdPh;
	}
	public void seteProdPh(String eProdPh) {
		this.eProdPh = eProdPh;
	}
	public String geteProdPack() {
		return eProdPack;
	}
	public void seteProdPack(String eProdPack) {
		this.eProdPack = eProdPack;
	}
	public String geteProdPackCnt() {
		return eProdPackCnt;
	}
	public void seteProdPackCnt(String eProdPackCnt) {
		this.eProdPackCnt = eProdPackCnt;
	}
	public String geteProdPsu() {
		return eProdPsu;
	}
	public void seteProdPsu(String eProdPsu) {
		this.eProdPsu = eProdPsu;
	}
	public String geteProdItemMaker() {
		return eProdItemMaker;
	}
	public void seteProdItemMaker(String eProdItemMaker) {
		this.eProdItemMaker = eProdItemMaker;
	}
	public String geteProdItemPer() {
		return eProdItemPer;
	}
	public void seteProdItemPer(String eProdItemPer) {
		this.eProdItemPer = eProdItemPer;
	}
	public String geteProdItemSend() {
		return eProdItemSend;
	}
	public void seteProdItemSend(String eProdItemSend) {
		this.eProdItemSend = eProdItemSend;
	}
	public String geteProdItemEtc() {
		return eProdItemEtc;
	}
	public void seteProdItemEtc(String eProdItemEtc) {
		this.eProdItemEtc = eProdItemEtc;
	}
	public String geteInvenCnt() {
		return eInvenCnt;
	}
	public void seteInvenCnt(String eInvenCnt) {
		this.eInvenCnt = eInvenCnt;
	}
	public String geteProdProcState() {
		return eProdProcState;
	}
	public void seteProdProcState(String eProdProcState) {
		this.eProdProcState = eProdProcState;
	}
	public String geteFirstCheckDate() {
		return eFirstCheckDate;
	}
	public void seteFirstCheckDate(String eFirstCheckDate) {
		this.eFirstCheckDate = eFirstCheckDate;
	}
	public String geteSecondCheckDate() {
		return eSecondCheckDate;
	}
	public void seteSecondCheckDate(String eSecondCheckDate) {
		this.eSecondCheckDate = eSecondCheckDate;
	}
	public String geteItemEtc() {
		return eItemEtc;
	}
	public void seteItemEtc(String eItemEtc) {
		this.eItemEtc = eItemEtc;
	}
	public String geteIpgoCnt() {
		return eIpgoCnt;
	}
	public void seteIpgoCnt(String eIpgoCnt) {
		this.eIpgoCnt = eIpgoCnt;
	}
	public String geteProdPackKey() {
		return eProdPackKey;
	}
	public void seteProdPackKey(String eProdPackKey) {
		this.eProdPackKey = eProdPackKey;
	}
	public String geteRealPackCnt() {
		return eRealPackCnt;
	}
	public void seteRealPackCnt(String eRealPackCnt) {
		this.eRealPackCnt = eRealPackCnt;
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
	public String getTopEndDate() {
		return topEndDate;
	}
	public void setTopEndDate(String topEndDate) {
		this.topEndDate = topEndDate;
	}
	
	public String geteCheckName() {
		return eCheckName;
	}
	public void seteCheckName(String eCheckName) {
		this.eCheckName = eCheckName;
	}
	public String geteCheckKey() {
		return eCheckKey;
	}
	public void seteCheckKey(String eCheckKey) {
		this.eCheckKey = eCheckKey;
	}
	public String geteCheckWdate() {
		return eCheckWdate;
	}
	public void seteCheckWdate(String eCheckWdate) {
		this.eCheckWdate = eCheckWdate;
	}

	public String geteCheckNo() {
		return eCheckNo;
	}
	public void seteCheckNo(String eCheckNo) {
		this.eCheckNo = eCheckNo;
	}
	public String geteCheckItemKey() {
		return eCheckItemKey;
	}
	public void seteCheckItemKey(String eCheckItemKey) {
		this.eCheckItemKey = eCheckItemKey;
	}
	
	public String geteCheckPath() {
		return eCheckPath;
	}
	public void seteCheckPath(String eCheckPath) {
		this.eCheckPath = eCheckPath;
	}
	public String geteCheckItemName() {
		return eCheckItemName;
	}
	public void seteCheckItemName(String eCheckItemName) {
		this.eCheckItemName = eCheckItemName;
	}
	public String geteCheckItemCheckKey() {
		return eCheckItemCheckKey;
	}
	public void seteCheckItemCheckKey(String eCheckItemCheckKey) {
		this.eCheckItemCheckKey = eCheckItemCheckKey;
	}

	public String getsGubunCateKey() {
		return sGubunCateKey;
	}
	public void setsGubunCateKey(String sGubunCateKey) {
		this.sGubunCateKey = sGubunCateKey;
	}
	public String getsGubunKey() {
		return sGubunKey;
	}
	public void setsGubunKey(String sGubunKey) {
		this.sGubunKey = sGubunKey;
	}
	public String getsGubunName() {
		return sGubunName;
	}
	public void setsGubunName(String sGubunName) {
		this.sGubunName = sGubunName;
	}
	public String getsGubunMemo() {
		return sGubunMemo;
	}
	public void setsGubunMemo(String sGubunMemo) {
		this.sGubunMemo = sGubunMemo;
	}
	public String geteCheckContdition() {
		return eCheckContdition;
	}
	public void seteCheckContdition(String eCheckContdition) {
		this.eCheckContdition = eCheckContdition;
	}
	public String getProChk() {
		return proChk;
	}
	public void setProChk(String proChk) {
		this.proChk = proChk;
	}
	public String geteCheckGubn() {
		return eCheckGubn;
	}
	public void seteCheckGubn(String eCheckGubn) {
		this.eCheckGubn = eCheckGubn;
	}


}
