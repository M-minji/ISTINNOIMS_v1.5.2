package egovframework.rndp.mes.common.service;

import java.sql.Timestamp;

import egovframework.rndp.com.cmm.service.DefultVO;
import egovframework.rndp.com.utl.EgovStringUtil;

public class MesCommonBaljuVO extends DefultVO{
	
	private String searchType			= "";
	private String searchWord			= "";
	
	private String topStartDate   		= ""; 
	private String topEndDate     		= ""; 
	
	
	private String eState     		= ""; 
	private String eChulgoDate     		= ""; 
	private String eBaljuKey				= "";
	private String eBaljuOrderNo			= "";
	private String eBaljuNumber				= "";
	private String eBaljuChangeDate			= "";
	private String eBaljuOrderDate			= "";
	private String eBaljuUdate				= "";
	private String eBaljuWdate				= "";    //등록날짜
	private String kStaffKey				= "";
	private String kStaffName				= "";
	
	private String eBaljuTernsPayment		= "";
	private String eBaljuTotalSumprice			= "";
	private String eBaljuChangeDesc			= "";
	private String eBaljuAuth				= "";
	private String eBaljuShipping			= "";
	private String eBaljuAccount			= "";
	
	
	private String eBaljuTernsPrice			= "";
	private String eBaljuOrderConfirm		= "";
	private String eBaljuOrderComment		= "";
	private String eBaljuComName		= "";
	
	
	
	
	private String eBaljuComKey 					= "";
	private String eBaljuCompanyKey 				= "";
	private String eBaljuComConName 				= "";
	private String eBaljuComAddress 				= "";
	private String eBaljuComTel 					= "";
	private String eBaljuComEmail 					= "";

	
	
	private String eBaljuItemPkey 					= "";
	private String eBaljuItemKey 					= "";
	private String eBaljuItemUnit 					= "";
	private String eBaljuItemOrderqty 				= "";
	private String eBaljuItemUnitPrice 				= "";
	private String eBaljuItemTotalPrice 			= "";
	private String eBaljuComGubun 			= "";
	private String eBaljuChangDate 			= "";
	private String eBaljuItemDelivery 			= "";

	private String eApprovalStatus 			= "";
	private String kApprovalStaffKey 			= "";
	private String kPositionKey 			= "";
	private String kPositionName 			= "";
	private String kClassKey 			= "";
	private String kClassName 			= "";
	private String kStaffSn 			= "";
	
	private String eStaffName 			= "";
	private String eStaffTelephone1 			= "";
	private String eStaffTelephone2 			= "";
	private String eStaffTelephone3 			= "";
	private String eStaffMobile1 			= "";
	private String eStaffMobile2			= "";
	private String eStaffMobile3 			= "";
	private String eStaffEmail 			= "";
	private String eStaffKey 			= "";
	
	private String eMemo 			= "";
	private String pageGubun 			= "";
	
	private String eBaljuItemPartNo;
	private String eItemName;
	private String eItemStandard;
	private String eItemUnit;
	private String eBaljuDescription;
	private String eItemMaker;
	private String eItemDate;
	private String eApprovalChk;
	private String kApprovalStaffName;
	private String eFlag;
	private String eBaljuPriceGubun;
	private String eBaljuPrice;

	private String eBaljuApprovalStatus;
	private String eBaljuApprovalReason;
	private String eBaljuApprovalCheck;
	private String baljuCnt;
	private String eBaljuStatusYCnt;
	private String eBaljuItemCount1;
	private String eBaljuItemCount2;
	private String eBaljuItemCount3;
	
	private String eBuyreqItemPkey;
	private String eBuyreqKey;
	private String eBaljuAmt;
	
	
	
	
	
	
	
	
	
	private String eBuyIpgoKey;
	private String eBuyIpgoDate;
	private String eBuyIpgoEtc;
	private String eBuyIpgoTot;
	private String eBuyIpgoFTot;
	private String eBuyPriceGubun;
	
	private String eBuyIpgoItemKey;
	private String eBuyIpgoItemPlaceKey;
	private String eBuyIpgoItemCnt;
	private String eBuyIpgoItemEtc;
	private String eIpgoGubun;
	private String eIpgoGubunText;
	private String eCheckKey;
	private String eItemKey;
	private String eBuyIpgoItemNo;
	
	private String eFailKey;
	private String eFailItemCnt;
	private String eFailEtc;
	
	private String sComKey;
	
	private String eIpgoYGubun;
	private String eIpgoNGubun;
	private String eIpgoNullGubun;
	private String sComName;
	private String eItemPartNo;
	private String eBaljuItemCount;
	private String eBuyIpgoItemPlace;
	private String eCheckName;
	private String eCheckGubun;
	private String eFailCnt;
	
	private String eItemDescription;
	private String eCheckCnt;
	private String iKey;
	private String buyCnt;
	private String eInKey;
	private String eItemCnt;
	private String eInGubun;
	private String searchType2;
	private String sItemInven;
	private String sItemName;
	private String sItemDescription;
	private String sItemGubun;
	private String sItemCateKey;
	private String eItemIndate;
	private String eItemOutdate;
	private String eInvenKey;
	private String eOutKey;
	private String eOutGubun;
	private String eOutCnt;
	private String sItemProdGubun;
	private String eIpgoCnt;
	private String eItemVatGubun;
	
	
	

	private String searchWord2						= "";
	private String pageGubun2						= "";
	private String pageGubunName						= "";
	private String facGubun						= "";
	private String eItemInvenTotCm						= "";
	private String popupGubun						= "";
	
	private String eTemp;			//간이 변수
	
	private String sItemTestYn						= "";
	private String sItemCateSkey                    = "";
	private String sItemCateUkey                    = "";
	private String sItemCateUname                   = "";
	private String sItemCateName                    = "";
	private String sItemCateEngName                 = "";
	private String sItemCateWdate                   = "";
	private String sItemCateUdate                   = "";
	private String path								= "";

	private String useCnt1							= "";
	private String useCnt2							= "";
	
	private String sItemKey							= "";			
	private String sItemCode                        = "";
	private String sItemEngName                     = "";
	private String sItemAbbre                       = "";
	private String sItemWdate                       = "";
	private String sItemUdate                       = "";
	private String sItemUnit                        = "";
	private String sItemPlace						= "";
	
	private String sItemCustom					= "";
	private String sItemCustomName					= "";
	private String sItemPlaceKey					= "";
	private String sItemPrice					= "";
	
	private String sItemRev							= "";
	private String sItemPartNo						= "";
	private String sItemSupplier					= "";
	private String sItemMaker						= "";
	private String sItemStandard					= "";
	private String sItemStandardSize1					= "";
	private String sItemStandardSize2					= "";
	private String sItemStandardSize3					= "";
	private String sItemSize						= "";
	private String sItemLotNo						= "";
	private String sItemBox						= "";
	private String sItemMaterial						= "";
	private String sItemMatName						= "";
	private String sItemDiamter						= "";
	private String sItemCurve						= "";
	private String sItemBasecurve						= "";
	private String sItemColor						= "";
	private String sItemColorName						= "";
	private String sItemAdd						= "";
	private String sItemAddName						= "";
	private String sItemSph						= "";	
	private String sItemCyl						= "";	
	private String sItemGubn						= "";	 //자재 1, 제품 2
	private String eItemInvenTot				= "";
	private String eItemStorage   = "";
	private String eItemStorageName   = ""; 
	
	private String sItemMainGubun   = ""; 
	private String sItemDate   = ""; 
	private String sItemLabel   = ""; 
	
	private String operator								= "";
	
	private String 	eBomKey = "";
	private String 	eBomName = "";	
	private String 	eBomPrice = "";
	
	private String 	eBomItemKey = "";
	private String 	eSubItemKey = "";
	private String 	eSubItemName = "";
	private String 	eSubItemQuantity = "";
	private String 	eSubItemPrice = "";
	private String 	eSubItemSum = "";
	private String 	eSubItemTax = "";

	private String eBarcode;
	private String barcode = "";
	private String eBarcodeItemSn;
	private String eBarcodeItemFileName = "";
	
	private String sphMinCnt = "";
	private String cylMinCnt = "";
	private String sphPlusCnt = "";
	private String cylPlusCnt = "";
	private String addPlusCnt = "";
	
	private String renseGubnSph = "";	
	private String renseGubnCyl = "";
	private String renseGubnAdd = "";
	private String renseValueSph = "";	
	private String renseValueCyl = "";	
	private String eProcessKey;	
	private String eProcessPath = "";	
	private String eSubProcessKey = "";	
	private String eSubProcessName = "";	
	private String eSubProcess = "";	
	private String eFacKey = "";	
	private String eFacName = "";	
	private String eSubCheckKey = "";	
	private String cateGubun = "";	
	private String prok = "";	
	private String eProcessName = "";	
	
	
	private String level;
	
	// 부자재관리, 제품관리
	private String itemKey					= ""; // s_sub_item pk
	private String itemCateKey				= ""; // s_item_cate pk
	private String itemGubun 				= ""; // 품목분류
	private String itemCode 				= ""; // 품목코드
	private String itemName 				= ""; // 품목명
	private String itemWidth 				= "0"; // 가로
	private String itemDepth 				= "0"; // 세로
	private String itemHeight				= "0"; // 높이
	private String itemWeight				= "0"; // 무게
	private String itemStandard				= ""; // 규격
	private String itemAngle				= ""; // 화각
	private String itemColor				= ""; // 색깔
	private String itemInven 				= "0"; // 적정재고
	private String itemCheck 				= ""; // 품질검사
	private String itemNote 				= ""; // 비고
	private String itemPrice				= "0"; // 매입가
	private String itemMainGubun			= ""; // 주요부품 여부
	private String itemRev					= ""; // 리비전
	private String itemPartNo				= ""; // Part No.
	private String itemDescription			= ""; // 디스크립션
	private String itemMaker				= ""; // 제조사
	private String itemSupplier				= ""; // 공급사
	private String itemDate					= ""; // 납기
	private String itemLabel				= ""; // 라벨사이즈 키
	private String itemLabelName			= ""; // 라벨사이즈 이름
	private String itemLengthGubun			= ""; // 길이단위
	private String itemWeightGubun			= ""; // 무게단위
	private String itemPriceGubun			= ""; // 통화
	private String itemPriceText			= ""; // 통화
	
	
	// 제품관리 고객
	private String itemComKey				= ""; // s_item_company pk  
	private String itemComName				= ""; // 고객명
	private String itemComPartNo			= ""; // 고객 Part No.
	private String itemComItemNo			= ""; // 고객 Item No.
	private String itemComWdate				= ""; // 등록일
	private String itemComUdate				= ""; // 수정일
		
	
	private String itemGubunResult;			// 품목 구분값
	
	private String itemProdGubun			= "";
	private String sItemComPartNo			= ""; 
	private String ePlace			= ""; 
	private String ePlaceKey			= ""; 
	
	
	// 자재출고
	private String eBuyChulgoKey = "";
	private String eBuyChulgoItemKey = "";
	private String eBuyChulgoDate = "";
	private String eProdNo = "";
//	private String eItemKey = "";
//	private String eItemPartNo = "";
//	private String eItemName = "";
	private String eBuyChulgoItemCnt = "";
	private String eChulgoGubun = "";
//	private String eOutCnt = "";
	private String eBuyChulgoItemPlaceKey = "";
	private String eBuyChulgoItemEtc = "";
	private String eBuyChulgoNum = "";
//	private String eBuyChulgoDate = "";
	private String eBuyChulgoEtc ="";
	private String alertGubun ="";
	private String eItemCode ="";
	private String eOutBarcode ="";
	private String eOutsoGubun ="";
	
	private String eOutsoChulgoKey ="";
	private String eOutsoChulgoItemKey ="";
	private String eOutsoChulgoNum ="";
	private String eOutsoChulgoDate ="";
	private String eOutsoChulgoEtc ="";
	private String eOutsoChulgoTot ="";
	private String eOutsoChulgoItemCnt ="";
	
	private String chulgoCnt ="";
	
	private String eBaseIpgoKey ="";
	private String eBaseIpgoItemKey ="";
	private String eBaseIpgoDate ="";
	private String eBaseIpgoEtc ="";
	private String eBaseIpgoTotCnt ="";
	private String eItemGubun ="";
	private String eBaljuFile ="";
	private String eBaljuFileGubun ="";
	
	private String eInvenPageGubun ="";
	
	private String eSubaljuKey ="";
	private String eSubaljuGubun ="";
	private String eSubaljuNum ="";
	private String eSubaljuDate ="";
	private String eSubaljuPdate ="";
	private String eSubaljuComKey ="";
	private String eSubaljuComName ="";
	private String eSubaljuConKey ="";
	private String eSubaljuWstaff ="";
	private String eItemPriceTot ="";
	private String eSubaljuPriceGubun ="";
	private String eSubaljuItemKey ="";
	private String eUnitPrice ="";
	private String eItemPrice ="";
	private String eSubaljuApprovalStatus ="";
	private String eSubaljuApprovalCheck ="";
	private String eSubaljuApprovalReason ="";
	private String eSubaljuStatusYCnt ="";
	private String cntGubun ="";
	private String eApprovalCheck ="";
	
	private String eSubaljuConName ="";
	private String eSubaljuConPhone1 ="";
	private String eSubaljuConEmail ="";
	private String eComKey ="";
	private String sItemCate ="";
	private String eBarcodeSn;

	private String eApprovalGubunKey ="";
	private String eApprovalGubun 			= "";
	
	
	
	
	
	
	
	public String geteApprovalGubunKey() {
		return eApprovalGubunKey;
	}

	public void seteApprovalGubunKey(String eApprovalGubunKey) {
		this.eApprovalGubunKey = eApprovalGubunKey;
	}

	public String geteItemStandard() {
		return eItemStandard;
	}

	public void seteItemStandard(String eItemStandard) {
		this.eItemStandard = eItemStandard;
	}

	public String geteItemUnit() {
		return eItemUnit;
	}

	public void seteItemUnit(String eItemUnit) {
		this.eItemUnit = eItemUnit;
	}

	public String geteBarcodeSn() {
		return eBarcodeSn;
	}

	public void seteBarcodeSn(String eBarcodeSn) {
		this.eBarcodeSn = eBarcodeSn;
	}

	public String getsItemCate() {
		return sItemCate;
	}

	public void setsItemCate(String sItemCate) {
		this.sItemCate = sItemCate;
	}

	public String geteComKey() {
		return eComKey;
	}

	public void seteComKey(String eComKey) {
		this.eComKey = eComKey;
	}

	public String geteSubaljuConName() {
		return eSubaljuConName;
	}

	public void seteSubaljuConName(String eSubaljuConName) {
		this.eSubaljuConName = eSubaljuConName;
	}

	public String geteSubaljuConPhone1() {
		return eSubaljuConPhone1;
	}

	public void seteSubaljuConPhone1(String eSubaljuConPhone1) {
		this.eSubaljuConPhone1 = eSubaljuConPhone1;
	}

	public String geteSubaljuConEmail() {
		return eSubaljuConEmail;
	}

	public void seteSubaljuConEmail(String eSubaljuConEmail) {
		this.eSubaljuConEmail = eSubaljuConEmail;
	}

	public String geteIpgoCnt() {
		return eIpgoCnt;
	}

	public void seteIpgoCnt(String eIpgoCnt) {
		this.eIpgoCnt = eIpgoCnt;
	}

	public String geteItemVatGubun() {
		return eItemVatGubun;
	}

	public void seteItemVatGubun(String eItemVatGubun) {
		this.eItemVatGubun = eItemVatGubun;
	}

	public String geteApprovalCheck() {
		return eApprovalCheck;
	}

	public void seteApprovalCheck(String eApprovalCheck) {
		this.eApprovalCheck = eApprovalCheck;
	}

	public String geteSubaljuApprovalStatus() {
		return eSubaljuApprovalStatus;
	}

	public void seteSubaljuApprovalStatus(String eSubaljuApprovalStatus) {
		this.eSubaljuApprovalStatus = eSubaljuApprovalStatus;
	}

	public String geteSubaljuApprovalCheck() {
		return eSubaljuApprovalCheck;
	}

	public void seteSubaljuApprovalCheck(String eSubaljuApprovalCheck) {
		this.eSubaljuApprovalCheck = eSubaljuApprovalCheck;
	}

	public String geteSubaljuApprovalReason() {
		return eSubaljuApprovalReason;
	}

	public void seteSubaljuApprovalReason(String eSubaljuApprovalReason) {
		this.eSubaljuApprovalReason = eSubaljuApprovalReason;
	}

	public String geteSubaljuStatusYCnt() {
		return eSubaljuStatusYCnt;
	}

	public void seteSubaljuStatusYCnt(String eSubaljuStatusYCnt) {
		this.eSubaljuStatusYCnt = eSubaljuStatusYCnt;
	}

	public String getCntGubun() {
		return cntGubun;
	}

	public void setCntGubun(String cntGubun) {
		this.cntGubun = cntGubun;
	}

	public String geteSubaljuKey() {
		return eSubaljuKey;
	}

	public void seteSubaljuKey(String eSubaljuKey) {
		this.eSubaljuKey = eSubaljuKey;
	}

	public String geteSubaljuGubun() {
		return eSubaljuGubun;
	}

	public void seteSubaljuGubun(String eSubaljuGubun) {
		this.eSubaljuGubun = eSubaljuGubun;
	}

	public String geteSubaljuNum() {
		return eSubaljuNum;
	}

	public void seteSubaljuNum(String eSubaljuNum) {
		this.eSubaljuNum = eSubaljuNum;
	}

	public String geteSubaljuDate() {
		return eSubaljuDate;
	}

	public void seteSubaljuDate(String eSubaljuDate) {
		this.eSubaljuDate = eSubaljuDate;
	}

	public String geteSubaljuPdate() {
		return eSubaljuPdate;
	}

	public void seteSubaljuPdate(String eSubaljuPdate) {
		this.eSubaljuPdate = eSubaljuPdate;
	}

	public String geteSubaljuComKey() {
		return eSubaljuComKey;
	}

	public void seteSubaljuComKey(String eSubaljuComKey) {
		this.eSubaljuComKey = eSubaljuComKey;
	}

	public String geteSubaljuComName() {
		return eSubaljuComName;
	}

	public void seteSubaljuComName(String eSubaljuComName) {
		this.eSubaljuComName = eSubaljuComName;
	}

	public String geteSubaljuConKey() {
		return eSubaljuConKey;
	}

	public void seteSubaljuConKey(String eSubaljuConKey) {
		this.eSubaljuConKey = eSubaljuConKey;
	}

	public String geteSubaljuWstaff() {
		return eSubaljuWstaff;
	}

	public void seteSubaljuWstaff(String eSubaljuWstaff) {
		this.eSubaljuWstaff = eSubaljuWstaff;
	}

	public String geteItemPriceTot() {
		return eItemPriceTot;
	}

	public void seteItemPriceTot(String eItemPriceTot) {
		this.eItemPriceTot = eItemPriceTot;
	}

	public String geteSubaljuPriceGubun() {
		return eSubaljuPriceGubun;
	}

	public void seteSubaljuPriceGubun(String eSubaljuPriceGubun) {
		this.eSubaljuPriceGubun = eSubaljuPriceGubun;
	}

	public String geteSubaljuItemKey() {
		return eSubaljuItemKey;
	}

	public void seteSubaljuItemKey(String eSubaljuItemKey) {
		this.eSubaljuItemKey = eSubaljuItemKey;
	}

	public String geteUnitPrice() {
		return eUnitPrice;
	}

	public void seteUnitPrice(String eUnitPrice) {
		this.eUnitPrice = eUnitPrice;
	}

	public String geteItemPrice() {
		return eItemPrice;
	}

	public void seteItemPrice(String eItemPrice) {
		this.eItemPrice = eItemPrice;
	}

	public String geteInvenPageGubun() {
		return eInvenPageGubun;
	}

	public void seteInvenPageGubun(String eInvenPageGubun) {
		this.eInvenPageGubun = eInvenPageGubun;
	}

	public String geteBaljuFileGubun() {
		return eBaljuFileGubun;
	}

	public void seteBaljuFileGubun(String eBaljuFileGubun) {
		this.eBaljuFileGubun = eBaljuFileGubun;
	}

	public String geteBaljuFile() {
		return eBaljuFile;
	}

	public void seteBaljuFile(String eBaljuFile) {
		this.eBaljuFile = eBaljuFile;
	}

	public String getePlace() {
		return ePlace;
	}

	public void setePlace(String ePlace) {
		this.ePlace = ePlace;
	}

	public String geteBaseIpgoKey() {
		return eBaseIpgoKey;
	}

	public void seteBaseIpgoKey(String eBaseIpgoKey) {
		this.eBaseIpgoKey = eBaseIpgoKey;
	}

	public String geteBaseIpgoItemKey() {
		return eBaseIpgoItemKey;
	}

	public void seteBaseIpgoItemKey(String eBaseIpgoItemKey) {
		this.eBaseIpgoItemKey = eBaseIpgoItemKey;
	}

	public String geteBaseIpgoDate() {
		return eBaseIpgoDate;
	}

	public void seteBaseIpgoDate(String eBaseIpgoDate) {
		this.eBaseIpgoDate = eBaseIpgoDate;
	}

	public String geteBaseIpgoEtc() {
		return eBaseIpgoEtc;
	}

	public void seteBaseIpgoEtc(String eBaseIpgoEtc) {
		this.eBaseIpgoEtc = eBaseIpgoEtc;
	}

	public String geteBaseIpgoTotCnt() {
		return eBaseIpgoTotCnt;
	}

	public void seteBaseIpgoTotCnt(String eBaseIpgoTotCnt) {
		this.eBaseIpgoTotCnt = eBaseIpgoTotCnt;
	}

	public String geteItemGubun() {
		return eItemGubun;
	}

	public void seteItemGubun(String eItemGubun) {
		this.eItemGubun = eItemGubun;
	}

	public String geteBaljuItemDelivery() {
		return eBaljuItemDelivery;
	}

	public void seteBaljuItemDelivery(String eBaljuItemDelivery) {
		this.eBaljuItemDelivery = eBaljuItemDelivery;
	}

	public String geteState() {
		return eState;
	}

	public void seteState(String eState) {
		this.eState = eState;
	}

	public String geteChulgoDate() {
		return eChulgoDate;
	}

	public void seteChulgoDate(String eChulgoDate) {
		this.eChulgoDate = eChulgoDate;
	}

	public String getChulgoCnt() {
		return chulgoCnt;
	}

	public void setChulgoCnt(String chulgoCnt) {
		this.chulgoCnt = chulgoCnt;
	}

	public String geteOutsoChulgoKey() {
		return eOutsoChulgoKey;
	}

	public void seteOutsoChulgoKey(String eOutsoChulgoKey) {
		this.eOutsoChulgoKey = eOutsoChulgoKey;
	}

	public String geteOutsoChulgoItemKey() {
		return eOutsoChulgoItemKey;
	}

	public void seteOutsoChulgoItemKey(String eOutsoChulgoItemKey) {
		this.eOutsoChulgoItemKey = eOutsoChulgoItemKey;
	}

	public String geteOutsoChulgoNum() {
		return eOutsoChulgoNum;
	}

	public void seteOutsoChulgoNum(String eOutsoChulgoNum) {
		this.eOutsoChulgoNum = eOutsoChulgoNum;
	}

	public String geteOutsoChulgoDate() {
		return eOutsoChulgoDate;
	}

	public void seteOutsoChulgoDate(String eOutsoChulgoDate) {
		this.eOutsoChulgoDate = eOutsoChulgoDate;
	}

	public String geteOutsoChulgoEtc() {
		return eOutsoChulgoEtc;
	}

	public void seteOutsoChulgoEtc(String eOutsoChulgoEtc) {
		this.eOutsoChulgoEtc = eOutsoChulgoEtc;
	}

	public String geteOutsoChulgoTot() {
		return eOutsoChulgoTot;
	}

	public void seteOutsoChulgoTot(String eOutsoChulgoTot) {
		this.eOutsoChulgoTot = eOutsoChulgoTot;
	}

	public String geteOutsoChulgoItemCnt() {
		return eOutsoChulgoItemCnt;
	}

	public void seteOutsoChulgoItemCnt(String eOutsoChulgoItemCnt) {
		this.eOutsoChulgoItemCnt = eOutsoChulgoItemCnt;
	}

	public String geteOutsoGubun() {
		return eOutsoGubun;
	}

	public void seteOutsoGubun(String eOutsoGubun) {
		this.eOutsoGubun = eOutsoGubun;
	}

	public String geteOutBarcode() {
		return eOutBarcode;
	}

	public void seteOutBarcode(String eOutBarcode) {
		this.eOutBarcode = eOutBarcode;
	}

	public String geteItemCode() {
		return eItemCode;
	}

	public void seteItemCode(String eItemCode) {
		this.eItemCode = eItemCode;
	}

	public String getePlaceKey() {
		return ePlaceKey;
	}

	public String getAlertGubun() {
		return alertGubun;
	}

	public void setAlertGubun(String alertGubun) {
		this.alertGubun = alertGubun;
	}

	public void setePlaceKey(String ePlaceKey) {
		this.ePlaceKey = ePlaceKey;
	}

	public String geteBarcode() {
		return eBarcode;
	}

	public void seteBarcode(String eBarcode) {
		this.eBarcode = eBarcode;
	}

	public String getSearchWord2() {
		return searchWord2;
	}

	public void setSearchWord2(String searchWord2) {
		this.searchWord2 = searchWord2;
	}

	public String getPageGubun2() {
		return pageGubun2;
	}

	public void setPageGubun2(String pageGubun2) {
		this.pageGubun2 = pageGubun2;
	}

	public String getPageGubunName() {
		return pageGubunName;
	}

	public void setPageGubunName(String pageGubunName) {
		this.pageGubunName = pageGubunName;
	}

	public String getFacGubun() {
		return facGubun;
	}

	public void setFacGubun(String facGubun) {
		this.facGubun = facGubun;
	}

	public String geteItemInvenTotCm() {
		return eItemInvenTotCm;
	}

	public void seteItemInvenTotCm(String eItemInvenTotCm) {
		this.eItemInvenTotCm = eItemInvenTotCm;
	}

	public String getPopupGubun() {
		return popupGubun;
	}

	public void setPopupGubun(String popupGubun) {
		this.popupGubun = popupGubun;
	}

	public String geteTemp() {
		return eTemp;
	}

	public void seteTemp(String eTemp) {
		this.eTemp = eTemp;
	}

	public String getsItemTestYn() {
		return sItemTestYn;
	}

	public void setsItemTestYn(String sItemTestYn) {
		this.sItemTestYn = sItemTestYn;
	}

	public String getsItemCateSkey() {
		return sItemCateSkey;
	}

	public void setsItemCateSkey(String sItemCateSkey) {
		this.sItemCateSkey = sItemCateSkey;
	}

	public String getsItemCateUkey() {
		return sItemCateUkey;
	}

	public void setsItemCateUkey(String sItemCateUkey) {
		this.sItemCateUkey = sItemCateUkey;
	}

	public String getsItemCateUname() {
		return sItemCateUname;
	}

	public void setsItemCateUname(String sItemCateUname) {
		this.sItemCateUname = sItemCateUname;
	}

	public String getsItemCateName() {
		return sItemCateName;
	}

	public void setsItemCateName(String sItemCateName) {
		this.sItemCateName = sItemCateName;
	}

	public String getsItemCateEngName() {
		return sItemCateEngName;
	}

	public void setsItemCateEngName(String sItemCateEngName) {
		this.sItemCateEngName = sItemCateEngName;
	}

	public String getsItemCateWdate() {
		return sItemCateWdate;
	}

	public void setsItemCateWdate(String sItemCateWdate) {
		this.sItemCateWdate = sItemCateWdate;
	}

	public String getsItemCateUdate() {
		return sItemCateUdate;
	}

	public void setsItemCateUdate(String sItemCateUdate) {
		this.sItemCateUdate = sItemCateUdate;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUseCnt1() {
		return useCnt1;
	}

	public void setUseCnt1(String useCnt1) {
		this.useCnt1 = useCnt1;
	}

	public String getUseCnt2() {
		return useCnt2;
	}

	public void setUseCnt2(String useCnt2) {
		this.useCnt2 = useCnt2;
	}

	public String getsItemKey() {
		return sItemKey;
	}

	public void setsItemKey(String sItemKey) {
		this.sItemKey = sItemKey;
	}

	public String getsItemCode() {
		return sItemCode;
	}

	public void setsItemCode(String sItemCode) {
		this.sItemCode = sItemCode;
	}

	public String getsItemEngName() {
		return sItemEngName;
	}

	public void setsItemEngName(String sItemEngName) {
		this.sItemEngName = sItemEngName;
	}

	public String getsItemAbbre() {
		return sItemAbbre;
	}

	public void setsItemAbbre(String sItemAbbre) {
		this.sItemAbbre = sItemAbbre;
	}

	public String getsItemWdate() {
		return sItemWdate;
	}

	public void setsItemWdate(String sItemWdate) {
		this.sItemWdate = sItemWdate;
	}

	public String getsItemUdate() {
		return sItemUdate;
	}

	public void setsItemUdate(String sItemUdate) {
		this.sItemUdate = sItemUdate;
	}

	public String getsItemUnit() {
		return sItemUnit;
	}

	public void setsItemUnit(String sItemUnit) {
		this.sItemUnit = sItemUnit;
	}

	public String getsItemPlace() {
		return sItemPlace;
	}

	public void setsItemPlace(String sItemPlace) {
		this.sItemPlace = sItemPlace;
	}

	public String getsItemCustom() {
		return sItemCustom;
	}

	public void setsItemCustom(String sItemCustom) {
		this.sItemCustom = sItemCustom;
	}

	public String getsItemCustomName() {
		return sItemCustomName;
	}

	public void setsItemCustomName(String sItemCustomName) {
		this.sItemCustomName = sItemCustomName;
	}

	public String getsItemPlaceKey() {
		return sItemPlaceKey;
	}

	public void setsItemPlaceKey(String sItemPlaceKey) {
		this.sItemPlaceKey = sItemPlaceKey;
	}

	public String getsItemPrice() {
		return sItemPrice;
	}

	public void setsItemPrice(String sItemPrice) {
		this.sItemPrice = sItemPrice;
	}

	public String getsItemRev() {
		return sItemRev;
	}

	public void setsItemRev(String sItemRev) {
		this.sItemRev = sItemRev;
	}

	public String getsItemPartNo() {
		return sItemPartNo;
	}

	public void setsItemPartNo(String sItemPartNo) {
		this.sItemPartNo = sItemPartNo;
	}

	public String getsItemSupplier() {
		return sItemSupplier;
	}

	public void setsItemSupplier(String sItemSupplier) {
		this.sItemSupplier = sItemSupplier;
	}

	public String getsItemMaker() {
		return sItemMaker;
	}

	public void setsItemMaker(String sItemMaker) {
		this.sItemMaker = sItemMaker;
	}

	public String getsItemStandard() {
		return sItemStandard;
	}

	public void setsItemStandard(String sItemStandard) {
		this.sItemStandard = sItemStandard;
	}

	public String getsItemStandardSize1() {
		return sItemStandardSize1;
	}

	public void setsItemStandardSize1(String sItemStandardSize1) {
		this.sItemStandardSize1 = sItemStandardSize1;
	}

	public String getsItemStandardSize2() {
		return sItemStandardSize2;
	}

	public void setsItemStandardSize2(String sItemStandardSize2) {
		this.sItemStandardSize2 = sItemStandardSize2;
	}

	public String getsItemStandardSize3() {
		return sItemStandardSize3;
	}

	public void setsItemStandardSize3(String sItemStandardSize3) {
		this.sItemStandardSize3 = sItemStandardSize3;
	}

	public String getsItemSize() {
		return sItemSize;
	}

	public void setsItemSize(String sItemSize) {
		this.sItemSize = sItemSize;
	}

	public String getsItemLotNo() {
		return sItemLotNo;
	}

	public void setsItemLotNo(String sItemLotNo) {
		this.sItemLotNo = sItemLotNo;
	}

	public String getsItemBox() {
		return sItemBox;
	}

	public void setsItemBox(String sItemBox) {
		this.sItemBox = sItemBox;
	}

	public String getsItemMaterial() {
		return sItemMaterial;
	}

	public void setsItemMaterial(String sItemMaterial) {
		this.sItemMaterial = sItemMaterial;
	}

	public String getsItemMatName() {
		return sItemMatName;
	}

	public void setsItemMatName(String sItemMatName) {
		this.sItemMatName = sItemMatName;
	}

	public String getsItemDiamter() {
		return sItemDiamter;
	}

	public void setsItemDiamter(String sItemDiamter) {
		this.sItemDiamter = sItemDiamter;
	}

	public String getsItemCurve() {
		return sItemCurve;
	}

	public void setsItemCurve(String sItemCurve) {
		this.sItemCurve = sItemCurve;
	}

	public String getsItemBasecurve() {
		return sItemBasecurve;
	}

	public void setsItemBasecurve(String sItemBasecurve) {
		this.sItemBasecurve = sItemBasecurve;
	}

	public String getsItemColor() {
		return sItemColor;
	}

	public void setsItemColor(String sItemColor) {
		this.sItemColor = sItemColor;
	}

	public String getsItemColorName() {
		return sItemColorName;
	}

	public void setsItemColorName(String sItemColorName) {
		this.sItemColorName = sItemColorName;
	}

	public String getsItemAdd() {
		return sItemAdd;
	}

	public void setsItemAdd(String sItemAdd) {
		this.sItemAdd = sItemAdd;
	}

	public String getsItemAddName() {
		return sItemAddName;
	}

	public void setsItemAddName(String sItemAddName) {
		this.sItemAddName = sItemAddName;
	}

	public String getsItemSph() {
		return sItemSph;
	}

	public void setsItemSph(String sItemSph) {
		this.sItemSph = sItemSph;
	}

	public String getsItemCyl() {
		return sItemCyl;
	}

	public void setsItemCyl(String sItemCyl) {
		this.sItemCyl = sItemCyl;
	}

	public String getsItemGubn() {
		return sItemGubn;
	}

	public void setsItemGubn(String sItemGubn) {
		this.sItemGubn = sItemGubn;
	}

	public String geteItemInvenTot() {
		return eItemInvenTot;
	}

	public void seteItemInvenTot(String eItemInvenTot) {
		this.eItemInvenTot = eItemInvenTot;
	}

	public String geteItemStorage() {
		return eItemStorage;
	}

	public void seteItemStorage(String eItemStorage) {
		this.eItemStorage = eItemStorage;
	}

	public String geteItemStorageName() {
		return eItemStorageName;
	}

	public void seteItemStorageName(String eItemStorageName) {
		this.eItemStorageName = eItemStorageName;
	}

	public String getsItemMainGubun() {
		return sItemMainGubun;
	}

	public void setsItemMainGubun(String sItemMainGubun) {
		this.sItemMainGubun = sItemMainGubun;
	}

	public String getsItemDate() {
		return sItemDate;
	}

	public void setsItemDate(String sItemDate) {
		this.sItemDate = sItemDate;
	}

	public String getsItemLabel() {
		return sItemLabel;
	}

	public void setsItemLabel(String sItemLabel) {
		this.sItemLabel = sItemLabel;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
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

	public String geteBomPrice() {
		return eBomPrice;
	}

	public void seteBomPrice(String eBomPrice) {
		this.eBomPrice = eBomPrice;
	}

	public String geteBomItemKey() {
		return eBomItemKey;
	}

	public void seteBomItemKey(String eBomItemKey) {
		this.eBomItemKey = eBomItemKey;
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

	public String geteSubItemQuantity() {
		return eSubItemQuantity;
	}

	public void seteSubItemQuantity(String eSubItemQuantity) {
		this.eSubItemQuantity = eSubItemQuantity;
	}

	public String geteSubItemPrice() {
		return eSubItemPrice;
	}

	public void seteSubItemPrice(String eSubItemPrice) {
		this.eSubItemPrice = eSubItemPrice;
	}

	public String geteSubItemSum() {
		return eSubItemSum;
	}

	public void seteSubItemSum(String eSubItemSum) {
		this.eSubItemSum = eSubItemSum;
	}

	public String geteSubItemTax() {
		return eSubItemTax;
	}

	public void seteSubItemTax(String eSubItemTax) {
		this.eSubItemTax = eSubItemTax;
	}


	public String getBarcode() {
		return barcode;
	}


	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String geteBarcodeItemSn() {
		return eBarcodeItemSn;
	}

	public void seteBarcodeItemSn(String eBarcodeItemSn) {
		this.eBarcodeItemSn = eBarcodeItemSn;
	}

	public String geteBarcodeItemFileName() {
		return eBarcodeItemFileName;
	}

	public void seteBarcodeItemFileName(String eBarcodeItemFileName) {
		this.eBarcodeItemFileName = eBarcodeItemFileName;
	}

	public String getSphMinCnt() {
		return sphMinCnt;
	}

	public void setSphMinCnt(String sphMinCnt) {
		this.sphMinCnt = sphMinCnt;
	}

	public String getCylMinCnt() {
		return cylMinCnt;
	}

	public void setCylMinCnt(String cylMinCnt) {
		this.cylMinCnt = cylMinCnt;
	}

	public String getSphPlusCnt() {
		return sphPlusCnt;
	}

	public void setSphPlusCnt(String sphPlusCnt) {
		this.sphPlusCnt = sphPlusCnt;
	}

	public String getCylPlusCnt() {
		return cylPlusCnt;
	}

	public void setCylPlusCnt(String cylPlusCnt) {
		this.cylPlusCnt = cylPlusCnt;
	}

	public String getAddPlusCnt() {
		return addPlusCnt;
	}

	public void setAddPlusCnt(String addPlusCnt) {
		this.addPlusCnt = addPlusCnt;
	}

	public String getRenseGubnSph() {
		return renseGubnSph;
	}

	public void setRenseGubnSph(String renseGubnSph) {
		this.renseGubnSph = renseGubnSph;
	}

	public String getRenseGubnCyl() {
		return renseGubnCyl;
	}

	public void setRenseGubnCyl(String renseGubnCyl) {
		this.renseGubnCyl = renseGubnCyl;
	}

	public String getRenseGubnAdd() {
		return renseGubnAdd;
	}

	public void setRenseGubnAdd(String renseGubnAdd) {
		this.renseGubnAdd = renseGubnAdd;
	}

	public String getRenseValueSph() {
		return renseValueSph;
	}

	public void setRenseValueSph(String renseValueSph) {
		this.renseValueSph = renseValueSph;
	}

	public String getRenseValueCyl() {
		return renseValueCyl;
	}

	public void setRenseValueCyl(String renseValueCyl) {
		this.renseValueCyl = renseValueCyl;
	}

	public String geteProcessKey() {
		return eProcessKey;
	}

	public void seteProcessKey(String eProcessKey) {
		this.eProcessKey = eProcessKey;
	}

	public String geteProcessPath() {
		return eProcessPath;
	}

	public void seteProcessPath(String eProcessPath) {
		this.eProcessPath = eProcessPath;
	}

	public String geteSubProcessKey() {
		return eSubProcessKey;
	}

	public void seteSubProcessKey(String eSubProcessKey) {
		this.eSubProcessKey = eSubProcessKey;
	}

	public String geteSubProcessName() {
		return eSubProcessName;
	}

	public void seteSubProcessName(String eSubProcessName) {
		this.eSubProcessName = eSubProcessName;
	}

	public String geteSubProcess() {
		return eSubProcess;
	}

	public void seteSubProcess(String eSubProcess) {
		this.eSubProcess = eSubProcess;
	}

	public String geteFacKey() {
		return eFacKey;
	}

	public void seteFacKey(String eFacKey) {
		this.eFacKey = eFacKey;
	}

	public String geteFacName() {
		return eFacName;
	}

	public void seteFacName(String eFacName) {
		this.eFacName = eFacName;
	}

	public String geteSubCheckKey() {
		return eSubCheckKey;
	}

	public void seteSubCheckKey(String eSubCheckKey) {
		this.eSubCheckKey = eSubCheckKey;
	}

	public String getCateGubun() {
		return cateGubun;
	}

	public void setCateGubun(String cateGubun) {
		this.cateGubun = cateGubun;
	}

	public String getProk() {
		return prok;
	}

	public void setProk(String prok) {
		this.prok = prok;
	}

	public String geteProcessName() {
		return eProcessName;
	}

	public void seteProcessName(String eProcessName) {
		this.eProcessName = eProcessName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getItemKey() {
		return itemKey;
	}

	public void setItemKey(String itemKey) {
		this.itemKey = itemKey;
	}

	public String getItemCateKey() {
		return itemCateKey;
	}

	public void setItemCateKey(String itemCateKey) {
		this.itemCateKey = itemCateKey;
	}

	public String getItemGubun() {
		return itemGubun;
	}

	public void setItemGubun(String itemGubun) {
		this.itemGubun = itemGubun;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemWidth() {
		return itemWidth;
	}

	public void setItemWidth(String itemWidth) {
		this.itemWidth = itemWidth;
	}

	public String getItemDepth() {
		return itemDepth;
	}

	public void setItemDepth(String itemDepth) {
		this.itemDepth = itemDepth;
	}

	public String getItemHeight() {
		return itemHeight;
	}

	public void setItemHeight(String itemHeight) {
		this.itemHeight = itemHeight;
	}

	public String getItemWeight() {
		return itemWeight;
	}

	public void setItemWeight(String itemWeight) {
		this.itemWeight = itemWeight;
	}

	public String getItemStandard() {
		return itemStandard;
	}

	public void setItemStandard(String itemStandard) {
		this.itemStandard = itemStandard;
	}

	public String getItemAngle() {
		return itemAngle;
	}

	public void setItemAngle(String itemAngle) {
		this.itemAngle = itemAngle;
	}

	public String getItemColor() {
		return itemColor;
	}

	public void setItemColor(String itemColor) {
		this.itemColor = itemColor;
	}

	public String getItemInven() {
		return itemInven;
	}

	public void setItemInven(String itemInven) {
		this.itemInven = itemInven;
	}

	public String getItemCheck() {
		return itemCheck;
	}

	public void setItemCheck(String itemCheck) {
		this.itemCheck = itemCheck;
	}

	public String getItemNote() {
		return itemNote;
	}

	public void setItemNote(String itemNote) {
		this.itemNote = itemNote;
	}

	public String getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemMainGubun() {
		return itemMainGubun;
	}

	public void setItemMainGubun(String itemMainGubun) {
		this.itemMainGubun = itemMainGubun;
	}

	public String getItemRev() {
		return itemRev;
	}

	public void setItemRev(String itemRev) {
		this.itemRev = itemRev;
	}

	public String getItemPartNo() {
		return itemPartNo;
	}

	public void setItemPartNo(String itemPartNo) {
		this.itemPartNo = itemPartNo;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getItemMaker() {
		return itemMaker;
	}

	public void setItemMaker(String itemMaker) {
		this.itemMaker = itemMaker;
	}

	public String getItemSupplier() {
		return itemSupplier;
	}

	public void setItemSupplier(String itemSupplier) {
		this.itemSupplier = itemSupplier;
	}

	public String getItemDate() {
		return itemDate;
	}

	public void setItemDate(String itemDate) {
		this.itemDate = itemDate;
	}

	public String getItemLabel() {
		return itemLabel;
	}

	public void setItemLabel(String itemLabel) {
		this.itemLabel = itemLabel;
	}

	public String getItemLabelName() {
		return itemLabelName;
	}

	public void setItemLabelName(String itemLabelName) {
		this.itemLabelName = itemLabelName;
	}

	public String getItemLengthGubun() {
		return itemLengthGubun;
	}

	public void setItemLengthGubun(String itemLengthGubun) {
		this.itemLengthGubun = itemLengthGubun;
	}

	public String getItemWeightGubun() {
		return itemWeightGubun;
	}

	public void setItemWeightGubun(String itemWeightGubun) {
		this.itemWeightGubun = itemWeightGubun;
	}

	public String getItemPriceGubun() {
		return itemPriceGubun;
	}

	public void setItemPriceGubun(String itemPriceGubun) {
		this.itemPriceGubun = itemPriceGubun;
	}

	public String getItemPriceText() {
		return itemPriceText;
	}

	public void setItemPriceText(String itemPriceText) {
		this.itemPriceText = itemPriceText;
	}

	public String getItemComKey() {
		return itemComKey;
	}

	public void setItemComKey(String itemComKey) {
		this.itemComKey = itemComKey;
	}

	public String getItemComName() {
		return itemComName;
	}

	public void setItemComName(String itemComName) {
		this.itemComName = itemComName;
	}

	public String getItemComPartNo() {
		return itemComPartNo;
	}

	public void setItemComPartNo(String itemComPartNo) {
		this.itemComPartNo = itemComPartNo;
	}

	public String getItemComItemNo() {
		return itemComItemNo;
	}

	public void setItemComItemNo(String itemComItemNo) {
		this.itemComItemNo = itemComItemNo;
	}

	public String getItemComWdate() {
		return itemComWdate;
	}

	public void setItemComWdate(String itemComWdate) {
		this.itemComWdate = itemComWdate;
	}

	public String getItemComUdate() {
		return itemComUdate;
	}

	public void setItemComUdate(String itemComUdate) {
		this.itemComUdate = itemComUdate;
	}

	public String getItemGubunResult() {
		return itemGubunResult;
	}

	public void setItemGubunResult(String itemGubunResult) {
		this.itemGubunResult = itemGubunResult;
	}

	public String getItemProdGubun() {
		return itemProdGubun;
	}

	public void setItemProdGubun(String itemProdGubun) {
		this.itemProdGubun = itemProdGubun;
	}

	public String getsItemComPartNo() {
		return sItemComPartNo;
	}

	public void setsItemComPartNo(String sItemComPartNo) {
		this.sItemComPartNo = sItemComPartNo;
	}

	public String getsItemProdGubun() {
		return sItemProdGubun;
	}

	public void setsItemProdGubun(String sItemProdGubun) {
		this.sItemProdGubun = sItemProdGubun;
	}

	public String getsItemInven() {
		return sItemInven;
	}

	public void setsItemInven(String sItemInven) {
		this.sItemInven = sItemInven;
	}

	public String getsItemName() {
		return sItemName;
	}

	public void setsItemName(String sItemName) {
		this.sItemName = sItemName;
	}

	public String getsItemDescription() {
		return sItemDescription;
	}

	public void setsItemDescription(String sItemDescription) {
		this.sItemDescription = sItemDescription;
	}

	public String getsItemGubun() {
		return sItemGubun;
	}

	public void setsItemGubun(String sItemGubun) {
		this.sItemGubun = sItemGubun;
	}

	public String getsItemCateKey() {
		return sItemCateKey;
	}

	public void setsItemCateKey(String sItemCateKey) {
		this.sItemCateKey = sItemCateKey;
	}

	public String geteItemIndate() {
		return eItemIndate;
	}

	public void seteItemIndate(String eItemIndate) {
		this.eItemIndate = eItemIndate;
	}

	public String geteItemOutdate() {
		return eItemOutdate;
	}

	public void seteItemOutdate(String eItemOutdate) {
		this.eItemOutdate = eItemOutdate;
	}

	public String geteInvenKey() {
		return eInvenKey;
	}

	public void seteInvenKey(String eInvenKey) {
		this.eInvenKey = eInvenKey;
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

	public String geteOutCnt() {
		return eOutCnt;
	}

	public void seteOutCnt(String eOutCnt) {
		this.eOutCnt = eOutCnt;
	}

	public String getSearchType2() {
		return searchType2;
	}

	public void setSearchType2(String searchType2) {
		this.searchType2 = searchType2;
	}

	public String geteInKey() {
		return eInKey;
	}

	public void seteInKey(String eInKey) {
		this.eInKey = eInKey;
	}

	public String geteItemCnt() {
		return eItemCnt;
	}

	public void seteItemCnt(String eItemCnt) {
		this.eItemCnt = eItemCnt;
	}

	public String geteInGubun() {
		return eInGubun;
	}

	public void seteInGubun(String eInGubun) {
		this.eInGubun = eInGubun;
	}

	public String getBuyCnt() {
		return buyCnt;
	}

	public void setBuyCnt(String buyCnt) {
		this.buyCnt = buyCnt;
	}

	public String getiKey() {
		return iKey;
	}

	public void setiKey(String iKey) {
		this.iKey = iKey;
	}

	public String geteCheckCnt() {
		return eCheckCnt;
	}

	public void seteCheckCnt(String eCheckCnt) {
		this.eCheckCnt = eCheckCnt;
	}

	public String geteItemDescription() {
		return eItemDescription;
	}

	public void seteItemDescription(String eItemDescription) {
		this.eItemDescription = eItemDescription;
	}

	public String geteIpgoGubunText() {
		return eIpgoGubunText;
	}

	public void seteIpgoGubunText(String eIpgoGubunText) {
		this.eIpgoGubunText = eIpgoGubunText;
	}

	public String geteBaljuAmt() {
		return eBaljuAmt;
	}

	public void seteBaljuAmt(String eBaljuAmt) {
		this.eBaljuAmt = eBaljuAmt;
	}

	public String geteFailCnt() {
		return eFailCnt;
	}

	public void seteFailCnt(String eFailCnt) {
		this.eFailCnt = eFailCnt;
	}

	public String geteCheckGubun() {
		return eCheckGubun;
	}

	public void seteCheckGubun(String eCheckGubun) {
		this.eCheckGubun = eCheckGubun;
	}

	public String geteIpgoYGubun() {
		return eIpgoYGubun;
	}

	public void seteIpgoYGubun(String eIpgoYGubun) {
		this.eIpgoYGubun = eIpgoYGubun;
	}

	public String geteIpgoNGubun() {
		return eIpgoNGubun;
	}

	public void seteIpgoNGubun(String eIpgoNGubun) {
		this.eIpgoNGubun = eIpgoNGubun;
	}

	public String geteIpgoNullGubun() {
		return eIpgoNullGubun;
	}

	public void seteIpgoNullGubun(String eIpgoNullGubun) {
		this.eIpgoNullGubun = eIpgoNullGubun;
	}

	public String getsComName() {
		return sComName;
	}

	public void setsComName(String sComName) {
		this.sComName = sComName;
	}

	public String geteItemPartNo() {
		return eItemPartNo;
	}

	public void seteItemPartNo(String eItemPartNo) {
		this.eItemPartNo = eItemPartNo;
	}

	public String geteBaljuItemCount() {
		return eBaljuItemCount;
	}

	public void seteBaljuItemCount(String eBaljuItemCount) {
		this.eBaljuItemCount = eBaljuItemCount;
	}

	public String geteBuyIpgoItemPlace() {
		return eBuyIpgoItemPlace;
	}

	public void seteBuyIpgoItemPlace(String eBuyIpgoItemPlace) {
		this.eBuyIpgoItemPlace = eBuyIpgoItemPlace;
	}

	public String geteCheckName() {
		return eCheckName;
	}

	public void seteCheckName(String eCheckName) {
		this.eCheckName = eCheckName;
	}

	public String getsComKey() {
		return sComKey;
	}

	public void setsComKey(String sComKey) {
		this.sComKey = sComKey;
	}

	public String geteFailKey() {
		return eFailKey;
	}

	public void seteFailKey(String eFailKey) {
		this.eFailKey = eFailKey;
	}

	public String geteFailItemCnt() {
		return eFailItemCnt;
	}

	public void seteFailItemCnt(String eFailItemCnt) {
		this.eFailItemCnt = eFailItemCnt;
	}

	public String geteFailEtc() {
		return eFailEtc;
	}

	public void seteFailEtc(String eFailEtc) {
		this.eFailEtc = eFailEtc;
	}

	public String geteBuyIpgoItemKey() {
		return eBuyIpgoItemKey;
	}

	public void seteBuyIpgoItemKey(String eBuyIpgoItemKey) {
		this.eBuyIpgoItemKey = eBuyIpgoItemKey;
	}

	public String geteBuyIpgoItemPlaceKey() {
		return eBuyIpgoItemPlaceKey;
	}

	public void seteBuyIpgoItemPlaceKey(String eBuyIpgoItemPlaceKey) {
		this.eBuyIpgoItemPlaceKey = eBuyIpgoItemPlaceKey;
	}

	public String geteBuyIpgoItemCnt() {
		return eBuyIpgoItemCnt;
	}

	public void seteBuyIpgoItemCnt(String eBuyIpgoItemCnt) {
		this.eBuyIpgoItemCnt = eBuyIpgoItemCnt;
	}

	public String geteBuyIpgoItemEtc() {
		return eBuyIpgoItemEtc;
	}

	public void seteBuyIpgoItemEtc(String eBuyIpgoItemEtc) {
		this.eBuyIpgoItemEtc = eBuyIpgoItemEtc;
	}

	public String geteIpgoGubun() {
		return eIpgoGubun;
	}

	public void seteIpgoGubun(String eIpgoGubun) {
		this.eIpgoGubun = eIpgoGubun;
	}

	public String geteCheckKey() {
		return eCheckKey;
	}

	public void seteCheckKey(String eCheckKey) {
		this.eCheckKey = eCheckKey;
	}

	public String geteItemKey() {
		return eItemKey;
	}

	public void seteItemKey(String eItemKey) {
		this.eItemKey = eItemKey;
	}

	public String geteBuyIpgoItemNo() {
		return eBuyIpgoItemNo;
	}

	public void seteBuyIpgoItemNo(String eBuyIpgoItemNo) {
		this.eBuyIpgoItemNo = eBuyIpgoItemNo;
	}

	public String geteBuyIpgoKey() {
		return eBuyIpgoKey;
	}

	public void seteBuyIpgoKey(String eBuyIpgoKey) {
		this.eBuyIpgoKey = eBuyIpgoKey;
	}

	public String geteBuyIpgoDate() {
		return eBuyIpgoDate;
	}

	public void seteBuyIpgoDate(String eBuyIpgoDate) {
		this.eBuyIpgoDate = eBuyIpgoDate;
	}

	public String geteBuyIpgoEtc() {
		return eBuyIpgoEtc;
	}

	public void seteBuyIpgoEtc(String eBuyIpgoEtc) {
		this.eBuyIpgoEtc = eBuyIpgoEtc;
	}

	public String geteBuyIpgoTot() {
		return eBuyIpgoTot;
	}

	public void seteBuyIpgoTot(String eBuyIpgoTot) {
		this.eBuyIpgoTot = eBuyIpgoTot;
	}

	public String geteBuyIpgoFTot() {
		return eBuyIpgoFTot;
	}

	public void seteBuyIpgoFTot(String eBuyIpgoFTot) {
		this.eBuyIpgoFTot = eBuyIpgoFTot;
	}

	public String geteBuyPriceGubun() {
		return eBuyPriceGubun;
	}

	public void seteBuyPriceGubun(String eBuyPriceGubun) {
		this.eBuyPriceGubun = eBuyPriceGubun;
	}

	public String geteBaljuPrice() {
		return eBaljuPrice;
	}

	public void seteBaljuPrice(String eBaljuPrice) {
		this.eBaljuPrice = eBaljuPrice;
	}

	public String geteBaljuItemCount1() {
		return eBaljuItemCount1;
	}

	public void seteBaljuItemCount1(String eBaljuItemCount1) {
		this.eBaljuItemCount1 = eBaljuItemCount1;
	}

	public String geteBaljuItemCount2() {
		return eBaljuItemCount2;
	}

	public void seteBaljuItemCount2(String eBaljuItemCount2) {
		this.eBaljuItemCount2 = eBaljuItemCount2;
	}

	public String geteBaljuItemCount3() {
		return eBaljuItemCount3;
	}

	public void seteBaljuItemCount3(String eBaljuItemCount3) {
		this.eBaljuItemCount3 = eBaljuItemCount3;
	}

	public String geteBaljuApprovalStatus() {
		return eBaljuApprovalStatus;
	}

	public void seteBaljuApprovalStatus(String eBaljuApprovalStatus) {
		this.eBaljuApprovalStatus = eBaljuApprovalStatus;
	}

	public String geteBaljuApprovalReason() {
		return eBaljuApprovalReason;
	}

	public void seteBaljuApprovalReason(String eBaljuApprovalReason) {
		this.eBaljuApprovalReason = eBaljuApprovalReason;
	}

	public String geteBaljuApprovalCheck() {
		return eBaljuApprovalCheck;
	}

	public void seteBaljuApprovalCheck(String eBaljuApprovalCheck) {
		this.eBaljuApprovalCheck = eBaljuApprovalCheck;
	}

	public String getBaljuCnt() {
		return baljuCnt;
	}

	public void setBaljuCnt(String baljuCnt) {
		this.baljuCnt = baljuCnt;
	}

	public String geteBaljuStatusYCnt() {
		return eBaljuStatusYCnt;
	}

	public void seteBaljuStatusYCnt(String eBaljuStatusYCnt) {
		this.eBaljuStatusYCnt = eBaljuStatusYCnt;
	}

	public String geteBaljuPriceGubun() {
		return eBaljuPriceGubun;
	}

	public void seteBaljuPriceGubun(String eBaljuPriceGubun) {
		this.eBaljuPriceGubun = eBaljuPriceGubun;
	}

	public String geteFlag() {
		return eFlag;
	}

	public void seteFlag(String eFlag) {
		this.eFlag = eFlag;
	}

	public String geteApprovalChk() {
		return eApprovalChk;
	}

	public void seteApprovalChk(String eApprovalChk) {
		this.eApprovalChk = eApprovalChk;
	}

	public String getkApprovalStaffName() {
		return kApprovalStaffName;
	}

	public void setkApprovalStaffName(String kApprovalStaffName) {
		this.kApprovalStaffName = kApprovalStaffName;
	}

	public String geteBaljuItemPartNo() {
		return eBaljuItemPartNo;
	}

	public void seteBaljuItemPartNo(String eBaljuItemPartNo) {
		this.eBaljuItemPartNo = eBaljuItemPartNo;
	}

	public String geteItemName() {
		return eItemName;
	}

	public void seteItemName(String eItemName) {
		this.eItemName = eItemName;
	}

	public String geteBaljuDescription() {
		return eBaljuDescription;
	}

	public void seteBaljuDescription(String eBaljuDescription) {
		this.eBaljuDescription = eBaljuDescription;
	}

	public String geteItemMaker() {
		return eItemMaker;
	}

	public void seteItemMaker(String eItemMaker) {
		this.eItemMaker = eItemMaker;
	}

	public String geteItemDate() {
		return eItemDate;
	}

	public void seteItemDate(String eItemDate) {
		this.eItemDate = eItemDate;
	}

	public String getPageGubun() {
		return pageGubun;
	}

	public void setPageGubun(String pageGubun) {
		this.pageGubun = pageGubun;
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

	public String geteBaljuKey() {
		return eBaljuKey;
	}

	public void seteBaljuKey(String eBaljuKey) {
		this.eBaljuKey = eBaljuKey;
	}

	public String geteBaljuOrderNo() {
		return eBaljuOrderNo;
	}

	public void seteBaljuOrderNo(String eBaljuOrderNo) {
		this.eBaljuOrderNo = eBaljuOrderNo;
	}

	public String geteBaljuNumber() {
		return eBaljuNumber;
	}

	public void seteBaljuNumber(String eBaljuNumber) {
		this.eBaljuNumber = eBaljuNumber;
	}

	public String geteBaljuChangeDate() {
		return eBaljuChangeDate;
	}

	public void seteBaljuChangeDate(String eBaljuChangeDate) {
		this.eBaljuChangeDate = eBaljuChangeDate;
	}

	public String geteBaljuOrderDate() {
		return eBaljuOrderDate;
	}

	public void seteBaljuOrderDate(String eBaljuOrderDate) {
		this.eBaljuOrderDate = eBaljuOrderDate;
	}

	public String geteBaljuUdate() {
		return eBaljuUdate;
	}

	public void seteBaljuUdate(String eBaljuUdate) {
		this.eBaljuUdate = eBaljuUdate;
	}

	public String geteBaljuWdate() {
		return eBaljuWdate;
	}

	public void seteBaljuWdate(String eBaljuWdate) {
		this.eBaljuWdate = eBaljuWdate;
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

	public String geteBaljuTernsPayment() {
		return eBaljuTernsPayment;
	}

	public void seteBaljuTernsPayment(String eBaljuTernsPayment) {
		this.eBaljuTernsPayment = eBaljuTernsPayment;
	}

	public String geteBaljuTotalSumprice() {
		return eBaljuTotalSumprice;
	}

	public void seteBaljuTotalSumprice(String eBaljuTotalSumprice) {
		this.eBaljuTotalSumprice = eBaljuTotalSumprice;
	}

	public String geteBaljuChangeDesc() {
		return eBaljuChangeDesc;
	}

	public void seteBaljuChangeDesc(String eBaljuChangeDesc) {
		this.eBaljuChangeDesc = eBaljuChangeDesc;
	}

	public String geteBaljuAuth() {
		return eBaljuAuth;
	}

	public void seteBaljuAuth(String eBaljuAuth) {
		this.eBaljuAuth = eBaljuAuth;
	}

	public String geteBaljuShipping() {
		return eBaljuShipping;
	}

	public void seteBaljuShipping(String eBaljuShipping) {
		this.eBaljuShipping = eBaljuShipping;
	}

	public String geteBaljuAccount() {
		return eBaljuAccount;
	}

	public void seteBaljuAccount(String eBaljuAccount) {
		this.eBaljuAccount = eBaljuAccount;
	}

	public String geteBaljuTernsPrice() {
		return eBaljuTernsPrice;
	}

	public void seteBaljuTernsPrice(String eBaljuTernsPrice) {
		this.eBaljuTernsPrice = eBaljuTernsPrice;
	}

	public String geteBaljuOrderConfirm() {
		return eBaljuOrderConfirm;
	}

	public void seteBaljuOrderConfirm(String eBaljuOrderConfirm) {
		this.eBaljuOrderConfirm = eBaljuOrderConfirm;
	}

	public String geteBaljuOrderComment() {
		return eBaljuOrderComment;
	}

	public void seteBaljuOrderComment(String eBaljuOrderComment) {
		this.eBaljuOrderComment = eBaljuOrderComment;
	}

	public String geteBaljuComName() {
		return eBaljuComName;
	}

	public void seteBaljuComName(String eBaljuComName) {
		this.eBaljuComName = eBaljuComName;
	}

	public String geteBaljuComKey() {
		return eBaljuComKey;
	}

	public void seteBaljuComKey(String eBaljuComKey) {
		this.eBaljuComKey = eBaljuComKey;
	}

	public String geteBaljuCompanyKey() {
		return eBaljuCompanyKey;
	}

	public void seteBaljuCompanyKey(String eBaljuCompanyKey) {
		this.eBaljuCompanyKey = eBaljuCompanyKey;
	}

	public String geteBaljuComConName() {
		return eBaljuComConName;
	}

	public void seteBaljuComConName(String eBaljuComConName) {
		this.eBaljuComConName = eBaljuComConName;
	}

	public String geteBaljuComAddress() {
		return eBaljuComAddress;
	}

	public void seteBaljuComAddress(String eBaljuComAddress) {
		this.eBaljuComAddress = eBaljuComAddress;
	}

	public String geteBaljuComTel() {
		return eBaljuComTel;
	}

	public void seteBaljuComTel(String eBaljuComTel) {
		this.eBaljuComTel = eBaljuComTel;
	}

	public String geteBaljuComEmail() {
		return eBaljuComEmail;
	}

	public void seteBaljuComEmail(String eBaljuComEmail) {
		this.eBaljuComEmail = eBaljuComEmail;
	}

	public String geteBaljuItemPkey() {
		return eBaljuItemPkey;
	}

	public void seteBaljuItemPkey(String eBaljuItemPkey) {
		this.eBaljuItemPkey = eBaljuItemPkey;
	}

	public String geteBaljuItemKey() {
		return eBaljuItemKey;
	}

	public void seteBaljuItemKey(String eBaljuItemKey) {
		this.eBaljuItemKey = eBaljuItemKey;
	}

	public String geteBaljuItemUnit() {
		return eBaljuItemUnit;
	}

	public void seteBaljuItemUnit(String eBaljuItemUnit) {
		this.eBaljuItemUnit = eBaljuItemUnit;
	}

	public String geteBaljuItemOrderqty() {
		return eBaljuItemOrderqty;
	}

	public void seteBaljuItemOrderqty(String eBaljuItemOrderqty) {
		this.eBaljuItemOrderqty = eBaljuItemOrderqty;
	}

	public String geteBaljuItemUnitPrice() {
		return eBaljuItemUnitPrice;
	}

	public void seteBaljuItemUnitPrice(String eBaljuItemUnitPrice) {
		this.eBaljuItemUnitPrice = eBaljuItemUnitPrice;
	}

	public String geteBaljuItemTotalPrice() {
		return eBaljuItemTotalPrice;
	}

	public void seteBaljuItemTotalPrice(String eBaljuItemTotalPrice) {
		this.eBaljuItemTotalPrice = eBaljuItemTotalPrice;
	}

	public String geteBaljuComGubun() {
		return eBaljuComGubun;
	}

	public void seteBaljuComGubun(String eBaljuComGubun) {
		this.eBaljuComGubun = eBaljuComGubun;
	}

	public String geteBaljuChangDate() {
		return eBaljuChangDate;
	}

	public void seteBaljuChangDate(String eBaljuChangDate) {
		this.eBaljuChangDate = eBaljuChangDate;
	}

	public String geteApprovalGubun() {
		return eApprovalGubun;
	}

	public void seteApprovalGubun(String eApprovalGubun) {
		this.eApprovalGubun = eApprovalGubun;
	}

	public String geteApprovalStatus() {
		return eApprovalStatus;
	}

	public void seteApprovalStatus(String eApprovalStatus) {
		this.eApprovalStatus = eApprovalStatus;
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

	public String getkStaffSn() {
		return kStaffSn;
	}

	public void setkStaffSn(String kStaffSn) {
		this.kStaffSn = kStaffSn;
	}

	public String geteStaffName() {
		return eStaffName;
	}

	public void seteStaffName(String eStaffName) {
		this.eStaffName = eStaffName;
	}

	public String geteStaffTelephone1() {
		return eStaffTelephone1;
	}

	public void seteStaffTelephone1(String eStaffTelephone1) {
		this.eStaffTelephone1 = eStaffTelephone1;
	}

	public String geteStaffTelephone2() {
		return eStaffTelephone2;
	}

	public void seteStaffTelephone2(String eStaffTelephone2) {
		this.eStaffTelephone2 = eStaffTelephone2;
	}

	public String geteStaffTelephone3() {
		return eStaffTelephone3;
	}

	public void seteStaffTelephone3(String eStaffTelephone3) {
		this.eStaffTelephone3 = eStaffTelephone3;
	}

	public String geteStaffMobile1() {
		return eStaffMobile1;
	}

	public void seteStaffMobile1(String eStaffMobile1) {
		this.eStaffMobile1 = eStaffMobile1;
	}

	public String geteStaffMobile2() {
		return eStaffMobile2;
	}

	public void seteStaffMobile2(String eStaffMobile2) {
		this.eStaffMobile2 = eStaffMobile2;
	}

	public String geteStaffMobile3() {
		return eStaffMobile3;
	}

	public void seteStaffMobile3(String eStaffMobile3) {
		this.eStaffMobile3 = eStaffMobile3;
	}

	public String geteStaffEmail() {
		return eStaffEmail;
	}

	public void seteStaffEmail(String eStaffEmail) {
		this.eStaffEmail = eStaffEmail;
	}

	public String geteStaffKey() {
		return eStaffKey;
	}

	public void seteStaffKey(String eStaffKey) {
		this.eStaffKey = eStaffKey;
	}

	public String geteMemo() {
		return eMemo;
	}

	public void seteMemo(String eMemo) {
		this.eMemo = eMemo;
	}

	public String geteBuyreqItemPkey() {
		return eBuyreqItemPkey;
	}

	public void seteBuyreqItemPkey(String eBuyreqItemPkey) {
		this.eBuyreqItemPkey = eBuyreqItemPkey;
	}

	public String geteBuyreqKey() {
		return eBuyreqKey;
	}

	public void seteBuyreqKey(String eBuyreqKey) {
		this.eBuyreqKey = eBuyreqKey;
	}

	public String geteBuyChulgoKey() {
		return eBuyChulgoKey;
	}

	public void seteBuyChulgoKey(String eBuyChulgoKey) {
		this.eBuyChulgoKey = eBuyChulgoKey;
	}

	public String geteBuyChulgoItemKey() {
		return eBuyChulgoItemKey;
	}

	public void seteBuyChulgoItemKey(String eBuyChulgoItemKey) {
		this.eBuyChulgoItemKey = eBuyChulgoItemKey;
	}

	public String geteBuyChulgoDate() {
		return eBuyChulgoDate;
	}

	public void seteBuyChulgoDate(String eBuyChulgoDate) {
		this.eBuyChulgoDate = eBuyChulgoDate;
	}

	public String geteProdNo() {
		return eProdNo;
	}

	public void seteProdNo(String eProdNo) {
		this.eProdNo = eProdNo;
	}

	public String geteBuyChulgoItemCnt() {
		return eBuyChulgoItemCnt;
	}

	public void seteBuyChulgoItemCnt(String eBuyChulgoItemCnt) {
		this.eBuyChulgoItemCnt = eBuyChulgoItemCnt;
	}

	public String geteChulgoGubun() {
		return eChulgoGubun;
	}

	public void seteChulgoGubun(String eChulgoGubun) {
		this.eChulgoGubun = eChulgoGubun;
	}

	public String geteBuyChulgoItemPlaceKey() {
		return eBuyChulgoItemPlaceKey;
	}

	public void seteBuyChulgoItemPlaceKey(String eBuyChulgoItemPlaceKey) {
		this.eBuyChulgoItemPlaceKey = eBuyChulgoItemPlaceKey;
	}

	public String geteBuyChulgoItemEtc() {
		return eBuyChulgoItemEtc;
	}

	public void seteBuyChulgoItemEtc(String eBuyChulgoItemEtc) {
		this.eBuyChulgoItemEtc = eBuyChulgoItemEtc;
	}

	public String geteBuyChulgoNum() {
		return eBuyChulgoNum;
	}

	public void seteBuyChulgoNum(String eBuyChulgoNum) {
		this.eBuyChulgoNum = eBuyChulgoNum;
	}

	public String geteBuyChulgoEtc() {
		return eBuyChulgoEtc;
	}

	public void seteBuyChulgoEtc(String eBuyChulgoEtc) {
		this.eBuyChulgoEtc = eBuyChulgoEtc;
	}
	
	


	

}
