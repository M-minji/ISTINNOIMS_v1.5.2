package egovframework.rndp.mes.common.service;

import egovframework.rndp.com.cmm.service.DefultVO;

public class MesCommonItemVO extends DefultVO{
	
	
	private String searchWord						= "";
	private String searchWord2						= "";
	private String searchType						= "";
	private String searchType2						= "";
	private String pageGubun						= "";
	private String pageGubun2						= "";
	private String pageGubunName						= "";
	private String facGubun						= "";
	private String eItemInvenTotCm						= "";
	private String popupGubun						= "";
	
	private String eTemp;			//간이 변수
	
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
	private String 	eItemKey = "";
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
	private String itemKey					= ""; // s_sub_item pk
	private String itemCateKey				= ""; // s_item_cate pk
	private String itemCate					= ""; 
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
		
	
	private String itemGubunResult;			// 품목 구분값
	
	private String itemProdGubun			= "";
	private String sItemProdGubun			= ""; 
	private String sItemComPartNo			= ""; 
	private String sItemCateEtc			= ""; 
	private String eInvenCnt			= ""; 
	private String gbnCnt			= ""; 
	private String sItemCate			= ""; 
	
	
	
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
	public String getItemUnit() {
		return itemUnit;
	}
	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}
	public String getsItemCate() {
		return sItemCate;
	}
	public void setsItemCate(String sItemCate) {
		this.sItemCate = sItemCate;
	}
	public String getItemCate() {
		return itemCate;
	}
	public void setItemCate(String itemCate) {
		this.itemCate = itemCate;
	}
	public String getGbnCnt() {
		return gbnCnt;
	}
	public void setGbnCnt(String gbnCnt) {
		this.gbnCnt = gbnCnt;
	}
	public String geteInvenCnt() {
		return eInvenCnt;
	}
	public void seteInvenCnt(String eInvenCnt) {
		this.eInvenCnt = eInvenCnt;
	}
	public String getsItemCateEtc() {
		return sItemCateEtc;
	}
	public void setsItemCateEtc(String sItemCateEtc) {
		this.sItemCateEtc = sItemCateEtc;
	}
	public String getPopupGubun() {
		return popupGubun;
	}
	public void setPopupGubun(String popupGubun) {
		this.popupGubun = popupGubun;
	}
	public String getItemPriceText() {
		return itemPriceText;
	}
	public void setItemPriceText(String itemPriceText) {
		this.itemPriceText = itemPriceText;
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
	public String geteTemp() {
		return eTemp;
	}
	public void seteTemp(String eTemp) {
		this.eTemp = eTemp;
	}
	public String getsItemComPartNo() {
		return sItemComPartNo;
	}
	public void setsItemComPartNo(String sItemComPartNo) {
		this.sItemComPartNo = sItemComPartNo;
	}
	public String getsComKey() {
		return sComKey;
	}
	public void setsComKey(String sComKey) {
		this.sComKey = sComKey;
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
	public String getsItemProdGubun() {
		return sItemProdGubun;
	}
	public void setsItemProdGubun(String sItemProdGubun) {
		this.sItemProdGubun = sItemProdGubun;
	}
	public String getItemProdGubun() {
		return itemProdGubun;
	}
	public void setItemProdGubun(String itemProdGubun) {
		this.itemProdGubun = itemProdGubun;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
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
	public String getItemGubunResult() {
		return itemGubunResult;
	}
	public void setItemGubunResult(String itemGubunResult) {
		this.itemGubunResult = itemGubunResult;
	}
	public String getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getItemHeight() {
		return itemHeight;
	}
	public void setItemHeight(String itemHeight) {
		this.itemHeight = itemHeight;
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
	public String getItemCateKey() {
		return itemCateKey;
	}
	public void setItemCateKey(String itemCateKey) {
		this.itemCateKey = itemCateKey;
	}
	public String getItemStandard() {
		return itemStandard;
	}
	public void setItemStandard(String itemStandard) {
		this.itemStandard = itemStandard;
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
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String geteItemInvenTotCm() {
		return eItemInvenTotCm;
	}
	public void seteItemInvenTotCm(String eItemInvenTotCm) {
		this.eItemInvenTotCm = eItemInvenTotCm;
	}
	public String getFacGubun() {
		return facGubun;
	}
	public void setFacGubun(String facGubun) {
		this.facGubun = facGubun;
	}
	public String geteInvenKey() {
		return eInvenKey;
	}
	public void seteInvenKey(String eInvenKey) {
		this.eInvenKey = eInvenKey;
	}
	public String geteBuyIpgoItemPlace() {
		return eBuyIpgoItemPlace;
	}
	public void seteBuyIpgoItemPlace(String eBuyIpgoItemPlace) {
		this.eBuyIpgoItemPlace = eBuyIpgoItemPlace;
	}
	public String geteItemName() {
		return eItemName;
	}
	public void seteItemName(String eItemName) {
		this.eItemName = eItemName;
	}
	public String geteSubProcessName() {
		return eSubProcessName;
	}
	public void seteSubProcessName(String eSubProcessName) {
		this.eSubProcessName = eSubProcessName;
	}
	public String geteProcessName() {
		return eProcessName;
	}
	public void seteProcessName(String eProcessName) {
		this.eProcessName = eProcessName;
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
	public String geteSubCheckKey() {
		return eSubCheckKey;
	}
	public void seteSubCheckKey(String eSubCheckKey) {
		this.eSubCheckKey = eSubCheckKey;
	}
	public String geteFacName() {
		return eFacName;
	}
	public void seteFacName(String eFacName) {
		this.eFacName = eFacName;
	}
	public String geteFacKey() {
		return eFacKey;
	}
	public void seteFacKey(String eFacKey) {
		this.eFacKey = eFacKey;
	}
	public String geteSubProcess() {
		return eSubProcess;
	}
	public void seteSubProcess(String eSubProcess) {
		this.eSubProcess = eSubProcess;
	}
	public String geteCheckKey() {
		return eCheckKey;
	}
	public void seteCheckKey(String eCheckKey) {
		this.eCheckKey = eCheckKey;
	}
	public String geteItemCnt() {
		return eItemCnt;
	}
	public void seteItemCnt(String eItemCnt) {
		this.eItemCnt = eItemCnt;
	}
	public String geteSubProcessKey() {
		return eSubProcessKey;
	}
	public void seteSubProcessKey(String eSubProcessKey) {
		this.eSubProcessKey = eSubProcessKey;
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
	public String getsItemGubun() {
		return sItemGubun;
	}
	public void setsItemGubun(String sItemGubun) {
		this.sItemGubun = sItemGubun;
	}
	public String getPageGubun() {
		return pageGubun;
	}
	public void setPageGubun(String pageGubun) {
		this.pageGubun = pageGubun;
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
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
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
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchWord2() {
		return searchWord2;
	}
	public void setSearchWord2(String searchWord2) {
		this.searchWord2 = searchWord2;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getsItemStandard() {
		return sItemStandard;
	}
	public void setsItemStandard(String sItemStandard) {
		this.sItemStandard = sItemStandard;
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
	public String geteBomKey() {
		return eBomKey;
	}
	public void seteBomKey(String eBomKey) {
		this.eBomKey = eBomKey;
	}
	public String geteBomItemKey() {
		return eBomItemKey;
	}
	public void seteBomItemKey(String eBomItemKey) {
		this.eBomItemKey = eBomItemKey;
	}
	public String geteItemKey() {
		return eItemKey;
	}
	public void seteItemKey(String eItemKey) {
		this.eItemKey = eItemKey;
	}
	public String geteSubItemKey() {
		return eSubItemKey;
	}
	public void seteSubItemKey(String eSubItemKey) {
		this.eSubItemKey = eSubItemKey;
	}
	public String geteBomPrice() {
		return eBomPrice;
	}
	public void seteBomPrice(String eBomPrice) {
		this.eBomPrice = eBomPrice;
	}
	public String geteBomName() {
		return eBomName;
	}
	public void seteBomName(String eBomName) {
		this.eBomName = eBomName;
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
	public String getsItemBox() {
		return sItemBox;
	}
	public void setsItemBox(String sItemBox) {
		this.sItemBox = sItemBox;
	}
	public void seteSubItemTax(String eSubItemTax) {
		this.eSubItemTax = eSubItemTax;
	}
	public String getsItemMaterial() {
		return sItemMaterial;
	}
	public void setsItemMaterial(String sItemMaterial) {
		this.sItemMaterial = sItemMaterial;
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
	public String getsItemAdd() {
		return sItemAdd;
	}
	public void setsItemAdd(String sItemAdd) {
		this.sItemAdd = sItemAdd;
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
	public String getsItemMatName() {
		return sItemMatName;
	}
	public void setsItemMatName(String sItemMatName) {
		this.sItemMatName = sItemMatName;
	}
	public String getsItemColorName() {
		return sItemColorName;
	}
	public void setsItemColorName(String sItemColorName) {
		this.sItemColorName = sItemColorName;
	}
	public String geteItemInvenTot() {
		return eItemInvenTot;
	}
	public void seteItemInvenTot(String eItemInvenTot) {
		this.eItemInvenTot = eItemInvenTot;
	}
	
	
	
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String geteBarCode() {
		return eBarCode;
	}
	public void seteBarCode(String eBarCode) {
		this.eBarCode = eBarCode;
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
	public String getsItemAddName() {
		return sItemAddName;
	}
	public void setsItemAddName(String sItemAddName) {
		this.sItemAddName = sItemAddName;
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
	public String getRenseGubnAdd() {
		return renseGubnAdd;
	}
	public void setRenseGubnAdd(String renseGubnAdd) {
		this.renseGubnAdd = renseGubnAdd;
	}
	public String getSearchType2() {
		return searchType2;
	}
	public void setSearchType2(String searchType2) {
		this.searchType2 = searchType2;
	}
	public String getsItemPlace() {
		return sItemPlace;
	}
	public void setsItemPlace(String sItemPlace) {
		this.sItemPlace = sItemPlace;
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
	public String getItemWeight() {
		return itemWeight;
	}
	public void setItemWeight(String itemWeight) {
		this.itemWeight = itemWeight;
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
	public String getsItemPartNo() {
		return sItemPartNo;
	}
	public void setsItemPartNo(String sItemPartNo) {
		this.sItemPartNo = sItemPartNo;
	}
	public String getsItemRev() {
		return sItemRev;
	}
	public void setsItemRev(String sItemRev) {
		this.sItemRev = sItemRev;
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
	
	
	
	
	
	
}
