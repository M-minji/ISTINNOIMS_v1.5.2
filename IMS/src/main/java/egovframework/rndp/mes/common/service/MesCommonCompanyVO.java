package egovframework.rndp.mes.common.service;

import egovframework.rndp.com.cmm.service.DefultVO;

public class MesCommonCompanyVO extends DefultVO{
	private String eItemCateName        			= "";
	private String eComItemPrice        			= "";
	private String eComKey        			= "";
	private String eComName                 = "";
	private String eComConKey               = "";
	private String eComMemo                 = "";
	private String eComGubun                = "";
	private String eComBrn                 = "";
	private String eComItemKey              = "";
	private String eComPhone               = "";
	private String eComFax                 = "";
	private String eComPost                = "";
	private String eComAddress             = "";
	private String eComWdate                = "";
	private String eComUdate                = "";
	private String eComSend                = "";
	private String eComAccount                = "";
	
	private String eComConName              = "";
	private String eComConPhone1            = "";
	private String eComConPhone2            = "";
	private String eComConEmail             = "";
	private String eComConMemo              = "";
	private String eComConWdate             = "";
	private String eComConUdate             = "";
	private String eComConDaepyo            = "";
	
	private String eComNum					= "";
	private String eGubunName               = "";
	private String eComConPhone             = "";
	
	private String eItemKey                 = "";
	private String eComItemName             = "";
	private String eComItemConcep           = "";
	private String eComItemWdate            = "";
	private String eComItemUdate            = "";
	private String eComItemDaepyo           = "";
	private String eComItemPartNo			= "";
	
	private String searchWord      					= "";
	private String searchType      					= "";
	private String topStartDate   					= ""; 
	private String topEndDate     					= ""; 	
	private String eComFileName				= "";
	private String eComCode;
	private String searchType1    	= "";
	
	
	private String ePersonKey			= "";	
	private String ePersonName			= "";	
	
	private String eBank			= "";	
	private String eBankNum			= "";	
	private String eBankPerson			= "";	
	private String eEmail			= "";	
	private String eEmailAddress			= "";	
	private String eComPerson			= "";	
	
	private String kStaffKey			= "";	
	private String kPositionKey			= "";	
	private String kPositionName			= "";	
	private String kClassKey			= "";	
	private String kClassName			= "";	
	private String kStaffName			= "";	
	private String pageGubun			= "";	
	private String eComCountry			= "";	
	
	// 거래처관리 품목 선택 table vo
	private String itemKey 					= ""; // s_item pk
	private String itemGubun 				= ""; // 품목분류
	private String itemName 				= ""; // 품목명
	private String itemPartNo				= ""; // Part No
	private String itemCode 				= ""; // 품목코드
	private String itemStandard				= ""; // 규격
	private String itemInven				= ""; // 적정재고
	private String itemCheck				= ""; // 수입검사 유무
	private String itemPrice				= ""; // 매입가
	private String itemGubunResult			= "";//품목 구분값
	private String eComSendKey			    = "";
	private String eTemp;                                            /* V , S  B 구분*/
	private String eFileUpdateChk;
	
	/*2020 07 28 MesItemVO 의 내용을 모듈화로 인해 삽입 */

	//private String searchWord						= "";
	private String searchWord2						= "";
	//private String searchType						= "";
	private String searchType2						= "";
	//private String pageGubun						= "";
	private String pageGubun2						= "";
	private String pageGubunName						= "";
	private String facGubun						= "";
	private String eItemInvenTotCm						= "";
	private String popupGubun						= "";
	//private String eTemp;			//간이 변수
	private String sItemTestYn						= "";
	private String sItemCateKey						= "";
	private String sItemCateSkey                    = "";
	private String sItemCateUkey                    = "";
	private String sItemCateUname                   = "";
	private String sItemCateName                    = "";
	private String sItemCateEngName                 = "";
	private String sItemCateWdate                   = "";
	private String sItemCateUdate                   = "";
	private String sItemGubun                 		= "";
	private String path								= "";

	private String useCnt1							= "";
	private String useCnt2							= "";
	
	private String sItemKey							= "";			
	private String sItemCode                        = "";
	private String sItemName                        = "";
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
	private String sItemDescription					= "";
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
	private String sItemInven						= "";	            //수량
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
	//private String 	eItemKey = "";
	private String 	eBomName = "";	
	private String 	eBomPrice = "";
	
	private String 	eBomItemKey = "";
	private String 	eSubItemKey = "";
	private String 	eSubItemName = "";
	private String 	eSubItemQuantity = "";
	private String 	eSubItemPrice = "";
	private String 	eSubItemSum = "";
	private String 	eSubItemTax = "";

	private String eBarCode = "";
	private String barcode = "";
	private String eBarcodeItemSn = "";
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
	private String eItemCnt = "";	
	private String eCheckKey = "";	
	private String eSubProcess = "";	
	private String eFacKey = "";	
	private String eFacName = "";	
	private String eSubCheckKey = "";	
	private String cateGubun = "";	
	private String prok = "";	
	private String eProcessName = "";	
	
	private String eItemName = "";	
	private String eBuyIpgoItemPlace = "";	
	private String eInvenKey = "";	
	
	
	private String level;
	
	// 부자재관리, 제품관리
	//private String itemKey					= ""; // s_sub_item pk
	private String itemCateKey				= ""; // s_item_cate pk
	private String itemCate					= ""; 
	//private String itemGubun 				= ""; // 품목분류
	//private String itemCode 				= ""; // 품목코드
	//private String itemName 				= ""; // 품목명
	private String itemWidth 				= "0"; // 가로
	private String itemDepth 				= "0"; // 세로
	private String itemHeight				= "0"; // 높이
	private String itemWeight				= "0"; // 무게
	//private String itemStandard				= ""; // 규격
	private String itemAngle				= ""; // 화각
	private String itemColor				= ""; // 색깔
	//private String itemInven 				= "0"; // 적정재고
	//private String itemCheck 				= ""; // 품질검사
	private String itemNote 				= ""; // 비고
	//private String itemPrice				= "0"; // 매입가
	private String itemMainGubun			= ""; // 주요부품 여부
	private String itemRev					= ""; // 리비전
	//private String itemPartNo				= ""; // Part No.
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
	private String itemUnit					= ""; // 유닛
	private String itemBrix					= ""; // 브릭스
	private String itemPh					= ""; // 산도
	private String itemPsu					= ""; // 염도
	// 제품관리 고객
	private String itemComKey				= ""; // s_item_company pk  
	private String itemComName				= ""; // 고객명
	private String itemComPartNo			= ""; // 고객 Part No.
	private String itemComItemNo			= ""; // 고객 Item No.
	private String itemComWdate				= ""; // 등록일
	private String itemComUdate				= ""; // 수정일
	private String sComKey				= ""; // 수정일
	private String itemProdGubun			= "";
	private String sItemProdGubun			= ""; 
	private String sItemComPartNo			= ""; 
	private String sItemCateEtc			= ""; 
	private String eInvenCnt			= ""; 
	private String gbnCnt			= ""; 
	private String sItemCate			= ""; 
	
	
	
	public String getSearchWord2() {
		return searchWord2;
	}
	public void setSearchWord2(String searchWord2) {
		this.searchWord2 = searchWord2;
	}
	public String getSearchType2() {
		return searchType2;
	}
	public void setSearchType2(String searchType2) {
		this.searchType2 = searchType2;
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
	public String getsItemTestYn() {
		return sItemTestYn;
	}
	public void setsItemTestYn(String sItemTestYn) {
		this.sItemTestYn = sItemTestYn;
	}
	public String getsItemCateKey() {
		return sItemCateKey;
	}
	public void setsItemCateKey(String sItemCateKey) {
		this.sItemCateKey = sItemCateKey;
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
	public String getsItemGubun() {
		return sItemGubun;
	}
	public void setsItemGubun(String sItemGubun) {
		this.sItemGubun = sItemGubun;
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
	public String getsItemName() {
		return sItemName;
	}
	public void setsItemName(String sItemName) {
		this.sItemName = sItemName;
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
	public String getsItemDescription() {
		return sItemDescription;
	}
	public void setsItemDescription(String sItemDescription) {
		this.sItemDescription = sItemDescription;
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
	public String getsItemInven() {
		return sItemInven;
	}
	public void setsItemInven(String sItemInven) {
		this.sItemInven = sItemInven;
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
	public String geteBarCode() {
		return eBarCode;
	}
	public void seteBarCode(String eBarCode) {
		this.eBarCode = eBarCode;
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
	public String geteItemCnt() {
		return eItemCnt;
	}
	public void seteItemCnt(String eItemCnt) {
		this.eItemCnt = eItemCnt;
	}
	public String geteCheckKey() {
		return eCheckKey;
	}
	public void seteCheckKey(String eCheckKey) {
		this.eCheckKey = eCheckKey;
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
	public String geteItemName() {
		return eItemName;
	}
	public void seteItemName(String eItemName) {
		this.eItemName = eItemName;
	}
	public String geteBuyIpgoItemPlace() {
		return eBuyIpgoItemPlace;
	}
	public void seteBuyIpgoItemPlace(String eBuyIpgoItemPlace) {
		this.eBuyIpgoItemPlace = eBuyIpgoItemPlace;
	}
	public String geteInvenKey() {
		return eInvenKey;
	}
	public void seteInvenKey(String eInvenKey) {
		this.eInvenKey = eInvenKey;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getItemCateKey() {
		return itemCateKey;
	}
	public void setItemCateKey(String itemCateKey) {
		this.itemCateKey = itemCateKey;
	}
	public String getItemCate() {
		return itemCate;
	}
	public void setItemCate(String itemCate) {
		this.itemCate = itemCate;
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
	public String getItemNote() {
		return itemNote;
	}
	public void setItemNote(String itemNote) {
		this.itemNote = itemNote;
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
	public String getItemUnit() {
		return itemUnit;
	}
	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}
	public String getItemBrix() {
		return itemBrix;
	}
	public void setItemBrix(String itemBrix) {
		this.itemBrix = itemBrix;
	}
	public String getItemPh() {
		return itemPh;
	}
	public void setItemPh(String itemPh) {
		this.itemPh = itemPh;
	}
	public String getItemPsu() {
		return itemPsu;
	}
	public void setItemPsu(String itemPsu) {
		this.itemPsu = itemPsu;
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
	public String getsComKey() {
		return sComKey;
	}
	public void setsComKey(String sComKey) {
		this.sComKey = sComKey;
	}
	public String getItemProdGubun() {
		return itemProdGubun;
	}
	public void setItemProdGubun(String itemProdGubun) {
		this.itemProdGubun = itemProdGubun;
	}
	public String getsItemProdGubun() {
		return sItemProdGubun;
	}
	public void setsItemProdGubun(String sItemProdGubun) {
		this.sItemProdGubun = sItemProdGubun;
	}
	public String getsItemComPartNo() {
		return sItemComPartNo;
	}
	public void setsItemComPartNo(String sItemComPartNo) {
		this.sItemComPartNo = sItemComPartNo;
	}
	public String getsItemCateEtc() {
		return sItemCateEtc;
	}
	public void setsItemCateEtc(String sItemCateEtc) {
		this.sItemCateEtc = sItemCateEtc;
	}
	public String geteInvenCnt() {
		return eInvenCnt;
	}
	public void seteInvenCnt(String eInvenCnt) {
		this.eInvenCnt = eInvenCnt;
	}
	public String getGbnCnt() {
		return gbnCnt;
	}
	public void setGbnCnt(String gbnCnt) {
		this.gbnCnt = gbnCnt;
	}
	public String getsItemCate() {
		return sItemCate;
	}
	public void setsItemCate(String sItemCate) {
		this.sItemCate = sItemCate;
	}
	public String geteItemCateName() {
		return eItemCateName;
	}
	public void seteItemCateName(String eItemCateName) {
		this.eItemCateName = eItemCateName;
	}
	public String geteComItemPrice() {
		return eComItemPrice;
	}
	public void seteComItemPrice(String eComItemPrice) {
		this.eComItemPrice = eComItemPrice;
	}
	public String geteComKey() {
		return eComKey;
	}
	public void seteComKey(String eComKey) {
		this.eComKey = eComKey;
	}
	public String geteComName() {
		return eComName;
	}
	public void seteComName(String eComName) {
		this.eComName = eComName;
	}
	public String geteComConKey() {
		return eComConKey;
	}
	public void seteComConKey(String eComConKey) {
		this.eComConKey = eComConKey;
	}
	public String geteComMemo() {
		return eComMemo;
	}
	public void seteComMemo(String eComMemo) {
		this.eComMemo = eComMemo;
	}
	public String geteComGubun() {
		return eComGubun;
	}
	public void seteComGubun(String eComGubun) {
		this.eComGubun = eComGubun;
	}
	public String geteComBrn() {
		return eComBrn;
	}
	public void seteComBrn(String eComBrn) {
		this.eComBrn = eComBrn;
	}
	public String geteComItemKey() {
		return eComItemKey;
	}
	public void seteComItemKey(String eComItemKey) {
		this.eComItemKey = eComItemKey;
	}
	public String geteComPhone() {
		return eComPhone;
	}
	public void seteComPhone(String eComPhone) {
		this.eComPhone = eComPhone;
	}
	public String geteComFax() {
		return eComFax;
	}
	public void seteComFax(String eComFax) {
		this.eComFax = eComFax;
	}
	public String geteComPost() {
		return eComPost;
	}
	public void seteComPost(String eComPost) {
		this.eComPost = eComPost;
	}
	public String geteComAddress() {
		return eComAddress;
	}
	public void seteComAddress(String eComAddress) {
		this.eComAddress = eComAddress;
	}
	public String geteComWdate() {
		return eComWdate;
	}
	public void seteComWdate(String eComWdate) {
		this.eComWdate = eComWdate;
	}
	public String geteComUdate() {
		return eComUdate;
	}
	public void seteComUdate(String eComUdate) {
		this.eComUdate = eComUdate;
	}
	public String geteComSend() {
		return eComSend;
	}
	public void seteComSend(String eComSend) {
		this.eComSend = eComSend;
	}
	public String geteComAccount() {
		return eComAccount;
	}
	public void seteComAccount(String eComAccount) {
		this.eComAccount = eComAccount;
	}
	public String geteComConName() {
		return eComConName;
	}
	public void seteComConName(String eComConName) {
		this.eComConName = eComConName;
	}
	public String geteComConPhone1() {
		return eComConPhone1;
	}
	public void seteComConPhone1(String eComConPhone1) {
		this.eComConPhone1 = eComConPhone1;
	}
	public String geteComConPhone2() {
		return eComConPhone2;
	}
	public void seteComConPhone2(String eComConPhone2) {
		this.eComConPhone2 = eComConPhone2;
	}
	public String geteComConEmail() {
		return eComConEmail;
	}
	public void seteComConEmail(String eComConEmail) {
		this.eComConEmail = eComConEmail;
	}
	public String geteComConMemo() {
		return eComConMemo;
	}
	public void seteComConMemo(String eComConMemo) {
		this.eComConMemo = eComConMemo;
	}
	public String geteComConWdate() {
		return eComConWdate;
	}
	public void seteComConWdate(String eComConWdate) {
		this.eComConWdate = eComConWdate;
	}
	public String geteComConUdate() {
		return eComConUdate;
	}
	public void seteComConUdate(String eComConUdate) {
		this.eComConUdate = eComConUdate;
	}
	public String geteComConDaepyo() {
		return eComConDaepyo;
	}
	public void seteComConDaepyo(String eComConDaepyo) {
		this.eComConDaepyo = eComConDaepyo;
	}
	public String geteComNum() {
		return eComNum;
	}
	public void seteComNum(String eComNum) {
		this.eComNum = eComNum;
	}
	public String geteGubunName() {
		return eGubunName;
	}
	public void seteGubunName(String eGubunName) {
		this.eGubunName = eGubunName;
	}
	public String geteComConPhone() {
		return eComConPhone;
	}
	public void seteComConPhone(String eComConPhone) {
		this.eComConPhone = eComConPhone;
	}
	public String geteItemKey() {
		return eItemKey;
	}
	public void seteItemKey(String eItemKey) {
		this.eItemKey = eItemKey;
	}
	public String geteComItemName() {
		return eComItemName;
	}
	public void seteComItemName(String eComItemName) {
		this.eComItemName = eComItemName;
	}
	public String geteComItemConcep() {
		return eComItemConcep;
	}
	public void seteComItemConcep(String eComItemConcep) {
		this.eComItemConcep = eComItemConcep;
	}
	public String geteComItemWdate() {
		return eComItemWdate;
	}
	public void seteComItemWdate(String eComItemWdate) {
		this.eComItemWdate = eComItemWdate;
	}
	public String geteComItemUdate() {
		return eComItemUdate;
	}
	public void seteComItemUdate(String eComItemUdate) {
		this.eComItemUdate = eComItemUdate;
	}
	public String geteComItemDaepyo() {
		return eComItemDaepyo;
	}
	public void seteComItemDaepyo(String eComItemDaepyo) {
		this.eComItemDaepyo = eComItemDaepyo;
	}
	public String geteComItemPartNo() {
		return eComItemPartNo;
	}
	public void seteComItemPartNo(String eComItemPartNo) {
		this.eComItemPartNo = eComItemPartNo;
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
	public String geteComFileName() {
		return eComFileName;
	}
	public void seteComFileName(String eComFileName) {
		this.eComFileName = eComFileName;
	}
	public String geteComCode() {
		return eComCode;
	}
	public void seteComCode(String eComCode) {
		this.eComCode = eComCode;
	}
	public String getSearchType1() {
		return searchType1;
	}
	public void setSearchType1(String searchType1) {
		this.searchType1 = searchType1;
	}
	public String getePersonKey() {
		return ePersonKey;
	}
	public void setePersonKey(String ePersonKey) {
		this.ePersonKey = ePersonKey;
	}
	public String getePersonName() {
		return ePersonName;
	}
	public void setePersonName(String ePersonName) {
		this.ePersonName = ePersonName;
	}
	public String geteBank() {
		return eBank;
	}
	public void seteBank(String eBank) {
		this.eBank = eBank;
	}
	public String geteBankNum() {
		return eBankNum;
	}
	public void seteBankNum(String eBankNum) {
		this.eBankNum = eBankNum;
	}
	public String geteBankPerson() {
		return eBankPerson;
	}
	public void seteBankPerson(String eBankPerson) {
		this.eBankPerson = eBankPerson;
	}
	public String geteEmail() {
		return eEmail;
	}
	public void seteEmail(String eEmail) {
		this.eEmail = eEmail;
	}
	public String geteEmailAddress() {
		return eEmailAddress;
	}
	public void seteEmailAddress(String eEmailAddress) {
		this.eEmailAddress = eEmailAddress;
	}
	public String geteComPerson() {
		return eComPerson;
	}
	public void seteComPerson(String eComPerson) {
		this.eComPerson = eComPerson;
	}
	public String getkStaffKey() {
		return kStaffKey;
	}
	public void setkStaffKey(String kStaffKey) {
		this.kStaffKey = kStaffKey;
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
	public String getkStaffName() {
		return kStaffName;
	}
	public void setkStaffName(String kStaffName) {
		this.kStaffName = kStaffName;
	}
	public String getPageGubun() {
		return pageGubun;
	}
	public void setPageGubun(String pageGubun) {
		this.pageGubun = pageGubun;
	}
	public String geteComCountry() {
		return eComCountry;
	}
	public void seteComCountry(String eComCountry) {
		this.eComCountry = eComCountry;
	}
	public String getItemKey() {
		return itemKey;
	}
	public void setItemKey(String itemKey) {
		this.itemKey = itemKey;
	}
	public String getItemGubun() {
		return itemGubun;
	}
	public void setItemGubun(String itemGubun) {
		this.itemGubun = itemGubun;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemPartNo() {
		return itemPartNo;
	}
	public void setItemPartNo(String itemPartNo) {
		this.itemPartNo = itemPartNo;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemStandard() {
		return itemStandard;
	}
	public void setItemStandard(String itemStandard) {
		this.itemStandard = itemStandard;
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
	public String getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getItemGubunResult() {
		return itemGubunResult;
	}
	public void setItemGubunResult(String itemGubunResult) {
		this.itemGubunResult = itemGubunResult;
	}
	public String geteComSendKey() {
		return eComSendKey;
	}
	public void seteComSendKey(String eComSendKey) {
		this.eComSendKey = eComSendKey;
	}
	public String geteTemp() {
		return eTemp;
	}
	public void seteTemp(String eTemp) {
		this.eTemp = eTemp;
	}
	public String geteFileUpdateChk() {
		return eFileUpdateChk;
	}
	public void seteFileUpdateChk(String eFileUpdateChk) {
		this.eFileUpdateChk = eFileUpdateChk;
	}                                  
	
	
	
	

}
