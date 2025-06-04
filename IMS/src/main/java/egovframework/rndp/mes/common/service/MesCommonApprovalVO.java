package egovframework.rndp.mes.common.service;

import java.util.List;

import egovframework.rndp.com.cmm.service.DefultVO;

public class MesCommonApprovalVO  extends DefultVO{
	
	//발주서와 견적서 쿼리를 union all 해야되므로 공통이 되는 컬럼들 VO
	private String pkKey;			// pk키
	private String number;			// 발주서 및 견적서 번호
	private String subject;			// 발주서 및 견적서 명
	private String sComName;		// 발주서 및 견적서 거래처명
	private String totalMoney;		// 발주서 및 견적서 총 금액
	private String wDate;			// 발주서 및 견적서 등록일
	private String kStaffKey;		// 발주서 및 견적서 직원키 
	private String kStaffName;		// 발주서 및 견적서 직원명 or 로그인한 직원명
	private String kStaffName2;		// 발주서 및 견적서 직원명 or 로그인한 직원명
	private String gubun;			// 발주서 견적서를 구분 지어주는 변수
	private String sn;				// 발주서 및 견적서 승인 순서
	private String gubunNumber;		// 발주서 및 견적서 구분숫자 (1:발주서 , 2:견적서)
	private String appStatus;		// 발주서 및 견적서 승인여부
	private String approvalKey;		// 승인권자 테이블 pk키
	private String totalStatus;		// 각자의 테이블 상태값 변경 변수
	private String appCnt;		
	private String priceGubun;		
	
	// 검색조건 VO
	private String searchWord;
	private String topStartDate;
	private String topEndDate;
	
	//발주서
	private String appStaffKey;		// 로그인한 직원 키
	private String eBaljuRemark;	// 비고사항
	private String eApprovalChk;	// 결재제외 유무
	private String sComKey;			// 회사 키
	private String sComRef;			// 참조
	private String eBaljuPaymentdate;// 납기일
	private String sComAddr;		//회사 주소
	private String sComPhone1;		//회사 전화번호 1
	private String sComPhone2;		//회사 전화번호 2
	private String sComPhone3;		//회사 전화번호 3
	private String sComFax1;		//회사 팩스1
	private String sComFax2;		//회사 팩스2
	private String sComFax3;		//회사 팩스3
	private String eBaljuFileName;	//첨부파일 이름
	private String eBaljuTotalAmt;	//총금액
	private String eBaljuTotalVat;	//총 세액
	private String eBaljuConfirm;	//확정 유무
	private String eBaljuMoneyTypeKey;//금액 타입
	private String eBaljuApprovalReason;// 반려 이유
	
	//견적서
	private String eEstimateKey;			//견적서 pk 키
	private String eEstimateRevisionNo;		//개정번호
	private String eEstimateRevisionDate;	//개정일자
	private String eEstimateRevisionReason;	//개정사유
	private String eEstimateNo;				//견적서번호
	private String eEstimateDate;			//견적일자
	private String eEstimateCompany;		
	private String eEstimateCompanyName;	
	private String eEstimateCompanyReference;
	private String eEstimateName;
	private String eEstimateDueDate;
	private String eEstimateValidDate;
	private String eEstimateDeliveryKey;
	private String eEstimateDeliveryName;
	private String eEstimateDeliveryPost;
	private String eEstimateDeliveryAddr;
	private String eEstimateDeliveryAddr2;
	private String eEstimatePaymentKey;
	private String eEstimatePaymentName;
	private String eEstimateSubTotal;
	private String eEstimateSubTwoTotal;
	private String eEstimateTotalPayment;
	private String eEstimateTotalSale;
	private String eEstimateWdate;
	private String eEstimateUdate;
	private String eEstimateProTotalPayment;
	private String eEstimateProEtc;
	private String eEstimatePlaTotalPayment;
	private String eEstimatePalteEtc;
	private String eEstimateFabTotalPayment;
	private String eEstimateFabEtc;
	private String eEstimateOtherTotalPayment;
	private String eEstimateOtherEtc;
	private String eEstimateOriginalKey;
	private String eEstimateStatus;
	private String eEstimateProPurchaseTotalPayment;
	private String itemByEestimate;
	private String itemByEestimateTwo;
	private String eEstimateTotalPaymentTwo;
	private String eEstimateReason;
	private String eBaljuCofirm;
	
	private String typeGubun;	//	 검색조건 셀렉트박스
	private String searchType;
	
	
	private String eSubaljuAppStatus		= "";
	private String eSubaljuAppReason		= "";
	
	private String eBuyreqAppStatus			= "";
	private String eBuyreqAppReason 		= "";
	private String finalAppStatus			= "";
	
	
	//2020 07 29 모듈화 위해 vo 통합시작 
	/*
	, @ModelAttribute("mesSujuVO") MesSujuVO mesSujuVO
	, @ModelAttribute("mesBaljuVO") MesBaljuVO mesBaljuVO 
	, @ModelAttribute("mesOrderVO") MesOrderVO mesOrderVO
	, @ModelAttribute("mesBuyReqVO") MesBuyReqVO mesBuyReqVO
	, @ModelAttribute("mesProductionVO") MesProductionVO mesProductionVO
	내용을 통합한다. 
	*/   	  	
	private String flagCheck     					= ""; 	
	private String flagCheck2     					= ""; 	
	private String eBarGubun     					= "";  	
	private String barCnt     					= ""; 	
	private String jeagoChk     					= ""; 	
	private String officerGubun     					= ""; 	
	private String eOutGubunText     					= ""; 	
	private String eOrderNo     					= ""; 	
	private String 	eProdKey = "";
	private String 	eProdDate = "";
	private String 	eProdCdate = "";
	private String 	ePlaceName = "";
	private String 	eProdCnt = "";
	private String 	eProdWdate = "";
	private String 	eProdUdate = "";
	private String 	eProdGubun = "";
	private String 	sItemGubun1 = "";
	private String 	sItemGubun2 = "";
	private String 	eProdItemKey = "";
	private String 	eProdItemCnt = "";
	private String 	eProdItemTotCnt = "";
	private String 	eProdItemSdate = "";
	private String 	eProdItemEdate = "";
	private String 	eProductionInNoTemp = "";
	private String 	eSubPlaceKey = "";
	private String 	eSubFlag = "";
	private String 	eProdSn = "";
	private String 	eItemCntBox = "";
	private String 	eItemCntUnit = "";
	private String 	eChulgoGubunKey = "";
	private String 	eProcSn = "";
	private String 	eProcessItemKey = "";
	private String 	eProcKey = "";
	private String 	eProcName = "";
	private String 	eProdSn2 = "";
	private String 	eProdItemProcKey = "";
	private String 	eFlag3 = "";
	private String 	eFlag4 = "";
	private String 	totCnt = "";
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
	private String 	eBuyChulgoTot = "";
	private String 	jeagoCnt = "";
	private String 	groupGubun = "";
	private String 	eSpeed = "";
	private String 	eThread = "";
	private String 	jeagoGubun = "";
	private String 	eBuyIpgoFailKey = "";
	private String 	workCnt = "";
	private String 	flagCnt = "";
	private String 	headFlag = "";
	private String 	gubunDate = "";
	private String 	ePrice = "";
	private String 	eMakeCnt = "";
	private String 	eResCnt = "";
	private String 	eRunEQKey = "";
	private String 	eRunEQList = "";
	private String 	eRunEQName = "";
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
	private String 	eSetItemName = "";
	private String 	eSetKey = "";
	private String 	eItemBarcode = "";
	private String 	eSetItemKey = "";
	private String 	eProdEtc = "";   
	private String 	eProdApprovalStatus = "";
	private String  eProdApprovalCheck = "";
	private String  eProdApprovalReason = "";
	private String eItemRev = "";
	private String eProdStatusYCnt = "";
	private String eItemGubun2 = "";
	private String eProcessSize = "";
	private String eStateGubun = "";
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
	private String eProdProcState = "";
	private String eFirstCheckDate = "";
	private String eSecondCheckDate = "";
	private String eProdPackKey = "";
	private String eRealPackCnt = "";
	private String eSubaljuCnt = "";
	private String eSubaljuWdate = "";
	private String eSubaljuUdate = "";
	private String eSubaljuPhone = "";
	private String eSubaljuAddress = "";
	private String eSubaljuPost = "";
	private String eSubaljuEtc = "";
	private String eSubaljuSend = "";
	private String eSubaljuSendName = "";
	private String eSubaljuPdateGubun = "";
	private String eSubaljuSendPrice = "";
	private String eSubaljuTerms = "";
	private String eSubaljuFile = "";
	private String eSubaljuFileSn = "";
	private String eSubaljuPeople = "";
	private String eSubaljuAccount = "";
	private String eSubaljuPriceGubunKey = "";
	private String eSubaljuSendKey = "";
	private String eSubaljuCountry = "";
	private String eSubaljuFileGubun = "";
	private String eSubaljuFileBefore = "";
	private String eSubaljuLine = "";
	private String eSubaljuReceiver = "";
	private String eItemBrix = "";
	private String eItemPh = "";
	private String eItemPsu = "";
	private String eItemAmt = "";
	private String eItemAmtTot = "";
	
	private String eState     		= ""; 
	private String eBaljuKey				= "";
	private String eBaljuOrderNo			= "";
	private String eBaljuNumber				= "";
	private String eBaljuChangeDate			= "";
	private String eBaljuOrderDate			= "";
	private String eBaljuUdate				= "";
	private String eBaljuWdate				= "";    //등록날짜
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
	private String eBaljuItemPartNo;
	private String eBaljuDescription;
	private String eBaljuPriceGubun;
	private String eBaljuPrice;
	private String eBaljuApprovalStatus;
	private String eBaljuApprovalCheck;
	private String baljuCnt;
	private String eBaljuStatusYCnt;
	private String eBaljuItemCount1;
	private String eBaljuItemCount2;
	private String eBaljuItemCount3;
	private String eBaljuAmt;
	private String eBuyIpgoKey;
	private String eBuyIpgoDate;
	private String eBuyIpgoEtc;
	private String eBuyIpgoTot;
	private String eBuyIpgoFTot;
	private String eBuyPriceGubun;
	private String eBuyIpgoItemKey;
	private String eBuyIpgoItemCnt;
	private String eBuyIpgoItemEtc;
	private String eIpgoGubun;
	private String eIpgoGubunText;
	private String eCheckKey;
	private String eBuyIpgoItemNo;
	private String eFailItemCnt;
	private String eFailEtc;
	private String eIpgoYGubun;
	private String eIpgoNGubun;
	private String eIpgoNullGubun;
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
	private String eInGubun;
	private String eItemIndate;
	private String eItemOutdate;
	private String eInvenKey;
	private String eOutCnt;
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
	private String sItemEngName                     = "";
	private String sItemAbbre                       = "";
	private String sItemWdate                       = "";
	private String sItemUdate                       = "";
	private String sItemPlace						= "";
	private String sItemCustom					= "";
	private String sItemCustomName					= "";
	private String sItemPlaceKey					= "";
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
	private String operator								= "";
	private String eBomKey = "";
	private String eBomName = "";	
	private String eBomPrice = "";
	private String eBomItemKey = "";
	private String eSubItemKey = "";
	private String eSubItemName = "";
	private String eSubItemQuantity = "";
	private String eSubItemPrice = "";
	private String eSubItemSum = "";
	private String eSubItemTax = "";
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
	private String itemWidth 				= "0"; // 가로
	private String itemDepth 				= "0"; // 세로
	private String itemHeight				= "0"; // 높이
	private String itemWeight				= "0"; // 무게
	private String itemAngle				= ""; // 화각
	private String itemColor				= ""; // 색깔
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
	private String itemProdGubun			= ""; 
	// 자재출고
	private String eBuyChulgoKey = "";
	private String eBuyChulgoItemKey = "";
	private String eBuyChulgoDate = "";
	private String eProdNo = "";
	private String eBuyChulgoItemCnt = "";
	private String eBuyChulgoItemPlaceKey = "";
	private String eBuyChulgoItemEtc = "";
	private String eBuyChulgoNum = "";
	private String eBuyChulgoEtc ="";
	private String alertGubun ="";
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
	private String eSubaljuPriceGubun ="";
	private String eSubaljuItemKey ="";
	private String eSubaljuApprovalStatus ="";
	private String eSubaljuApprovalCheck ="";
	private String eSubaljuApprovalReason ="";
	private String eSubaljuStatusYCnt ="";
	private String eApprovalCheck ="";
	private String eSubaljuConName ="";
	private String eSubaljuConPhone1 ="";
	private String eSubaljuConEmail ="";
	private String eComKey ="";
	private String sItemCate ="";
	private String eBarcodeSn;
	private String eOutKey = "";
	private String eOutGubun = "";
	private String sItemSalePrice = "";
	private String eOutBarcode = "";
	private String eBuyIpgoItemPlaceKey = "";
	private String ePlaceKey = "";
	private String eOrderKey = "";
	private String eOrderCnt = "";
	private String eOrderNum = "";
	private String eOrderWdate = "";
	private String eOrderUdate = "";
	private String eOrderDate= "";
	private String eOrderPdate = "";
	private String eOrderPhone = "";
	private String eOrderAddress = "";
	private String eOrderAddress1 = "";
	private String eOrderAddress2 = "";
	private String eOrderPost = "";
	private String eOrderEtc = "";
	private String eOrderSend = "";
	private String eItemCntTot = "";
	private String eItemKgTot = "";
	private String eOrderItemKey = "";
	private String eJeagoCnt = "";
	private String eOrderSendName = "";
	private String eChulgoGubun = "";
	private String eChulgoDate = "";
	private String pageGubun = "";
	private String eOrderPdateGubun = "";
	private String eDiffPdate = "";
	private String eOrderSendPrice = "";
	private String eItemPriceTot = "";
	private String eMatePrice = "";
	private String ltGubun = "";
	private String ASGubun = "";
	private String eASKindKey = "";
	private String eOrderApprovalStatus = "";
	private String eApprovalGubun = "";
	private String eFlag2 = "";
	private String eOrderApprovalCheck = "";
	private String eOrderApprovalReason = "";
	private String eOrderStatusYCnt = "";
	private String eBarcode = "";
	private String eChulgoKey = "";
	private String eChulgoWdate = "";
	private String eChulgoUdate = "";
	private String eChulgoNum = "";
	private String eChulgoChulhajang = "";
	private String eChulgoSangcha = "";
	private String eChulgoNum2 = "";
	private String eChulgoTrade = "";
	private String eChulgoNabji = "";
	private String eChulgoInsu = "";
	private String eChulgoCountry = "";
	private String eChulgoCard = "";
	private String eChulgoCarNum = "";
	private String eChulgoBulk = "";
	private String eChulgoLotno = "";
	private String eChulgoBulk1 = "";
	private String eChulgoBulk2 = "";
	private String eChulgoKg = "";
	private String eChulgoPallet1 = "";
	private String eChulgoPalletCnt1 = "";
	private String eChulgoPallet2 = "";
	private String eChulgoPalletCnt2 = "";
	private String eChulgoNext = "";
	private String eChulgoRest = "";
	private String eChulgoChulhaone = "";
	private String eChulgoInsuja = "";
	private String eChulgoUnbanone = "";
	private String eChulgoItemKey = "";
	private String sItemUnit = "";
	private String eItemLotno = "";
	private String ePlace = "";
	private String eItemKg = "";
	private String eItemTotCnt = "";
	private String eItemTotKg = "";
	private String eItemPlace = "";
	private String eItemKey2 = "";
	private String eItemName2 = "";
	private String eGubun = "";
	private String eASItemKey = "";
	private String eTaxGubun = "";
	private String eChulgoDate2 = "";
	private String eChulgoYear = "";
	private String eChulgoMonth = "";
	private String sItemKey = "";
	private String sItemName = "";
	private String sItemCode = "";
	private String sItemPartNo = "";
	private String sItemComPartNo = "";
	private String eItemComPartNo = "";
	private String sItemPrice = "";
	private String sItemGubun = "";
	private String sItemDescription = "";
	private String sItemCateKey = "";
	private String sItemInven = "";
	private String sItemMainGubun = "";
	private String sItemProdGubun = "";
	private String sItemMaker = "";
	private String sItemSupplier = "";
	private String sItemDate = "";
	private String sItemLabel = "";
	private String sItemRev = "";
	private String eItemPartNo = "";
	private String eItemCode = "";
	private String eOrderPriceGubun = "";
	private String eOrderTerms = "";
	private String eOrderFile = "";
	private String eOrderFileSn = "";
	private String eOrderPeople = "";
	private String eOrderAccount = "";
	private String cntGubun = "";
	private String eOrderPriceGubunKey = "";
	private String eOrderSendKey = "";
	private String eOrderCountry = "";
	private String eInvenCnt = "";
	private String eUnitPrice = "";
	private String eOrderFileGubun = "";
	private String eOrderFileBefore = "";
	private String eOrderLine = "";
	private String ePassGubun = "";
	private String eFailKey = "";
	private String eOutCheckKey = "";
	private String eOutCheckEtc = "";
	private String eReplaceBarcode = "";
	private String searchType2 = "";
	private String eItemPdate = "";
	private String eYYCnt = "";
	private String eDelayGubun = "";
	private String eDelayDay = "";
	private String eBuyreqKey				= ""; // e_buyreq pk
	private String eBuyreqItemKey			= ""; // e_buyreq_item pk
	private String eBuyreqNum				= ""; // 구매요청번호
	private String eBuyreqSdate				= ""; // 구매요청일
	private String eBuyreqRdate				= ""; // 완료요청일
	private String eBuyreqRemark			= ""; // 사유
	private String eBuyreqFileName			= ""; // 파일명
	private String eBuyreqConfirm			= ""; // 구매요청확정 유무
	private String kClassKey				= ""; // 부서 key
	private String kClassName				= ""; // 부서 명
	private String kPositionKey				= ""; // 직급 key
	private String kPositionName			= ""; // 직급 명
	private String itemKey					= ""; // s_item pk
	private String itemGubun				= ""; // 품목경로
	private String itemName					= ""; // 품목명
	private String itemCode					= ""; // 품목코드
	private String itemStandard				= ""; // 규격
	private String itemPrice				= ""; // 매입가
	private String itemCnt					= ""; // 수량
	private String itemSum					= ""; // 금액
	private String itemNote					= ""; // 비고
	private String itemFlag					= ""; // 리스트 페이지 하나만 출력
	private String itemPriceComma			= ""; // 매입단가 단위구분
	private String itemCntComma				= ""; // 수량 단위구분
	private String itemSumComma				= ""; // 금액 단위구분
	// 거래처 팝업
	private String sComCode					= ""; // 거래처코드
	private String itemInven				= ""; // 적정재고
	private String itemCheck				= ""; // 수입검사 유무
	// 승인권자
	private String eApprovalKey				= ""; // e_approval pk 
	private String kApprovalStaffKey		= ""; // 승인권자 k_staff_key
	private String kApprovalStatus			= ""; // 승인권자 결재 순서 구분 	ex) W, I
	private String kStaffSn					= ""; // 승인권자 결재 순서 		ex) 1, 2
	private String kApprovalGubunKey		= ""; // e_buyreq_key
	private String kApprovalGubun			= ""; // 거래처 1, 구매요청 2
	private String sApprovalChk				= ""; // 결재제외 N , 승인 Y
	private String eBuyreqApprovalStatus	= ""; // 결재 마무리
	private String eBuyreqApprovalReason	= ""; // 반려 사유
	private String eBuyreqApprovalCheck		= ""; // 승인 선택
	private String eBuyreqStatusYCnt		= ""; // 
	private String fileGubun				= ""; // 파일 구분값
	private String itemGubunResult			= ""; // s_item, s_sub_item 구분값
	private String buyreqCnt				= ""; // 구매요청서 품목개수
	private String approvalStatusCnt		= ""; // 승인한 개수
	private String itemCateKey				= ""; // 품목분류 pk키
	private String cnt 						= "";
	private String eUsePlace;			//사용부서
	private String eBuyreqName;			//프로젝트,제품명
	private String eBuyreqCode;			//코드,pot No.
	private String eBuyreqGubun;		//견적서 구분
	private String eBuyreqItemGubun;	//완제품구분
	private String eBuyreqEtc;			//비고란
	private String eBuyreqComKey;      	//공급사 키
	private String eBuyreqComName;		//공급사 이름
	private String eBuyreqTotalPrice;   //총합계금액
	private String eBuyreqItemPkey;         //제품가져올때 아이템 키
	private String eBuyreqiPartno;			//품목 PartNO
	private String eBuyreqiModel;			//제품 모델명
	private String eBuyreqiSpec;			//제품 및 사양
	private String eBuyreqiCnt;				//수량
	private String eBuyreqiUnitPrice;		//단가
	private String eBuyreqiVat;				//세액
	private String eBuyreqiVatGubun;		//부가세 포함 여부
	private String eBuyreqiRdate;		
	private String eBuyreqPriceGubun;		
	private String eBuyreqPrice;
	private String eBuyreqWdate  		= "";	//날짜
	private String eBuyreqUdate  		= "";	//날짜
	private String kApprovalStaffName  	= "";
	private String eFlag  		= "";	
	private String eBuyreqiPrice;
	private String sComConKey;
	private String sComConName;
	private String sComAddress1;
	private String sComConPhone2;
	private String sComConEmail;
	private String eItemMaker;
	private String eItemDate;
	private String eItemKey;
	private String eItemCnt;
	private String eItemAmount;
	private String eItemEtc;
	private String eItemName;
	private String eItemStandard;
	private String eItemUnit;
	private String eItemPrice;
	private String eBuyreqPosition;
	private String eBuyreqPositionName;
	private String eBuyreqWriter;
	private String eBuyreqDate;

	private String eApprovalGubunKey ="";
	
	
	
	
	
	
	
	
	public String geteApprovalGubunKey() {
		return eApprovalGubunKey;
	}
	public void seteApprovalGubunKey(String eApprovalGubunKey) {
		this.eApprovalGubunKey = eApprovalGubunKey;
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
	public String geteOrderNo() {
		return eOrderNo;
	}
	public void seteOrderNo(String eOrderNo) {
		this.eOrderNo = eOrderNo;
	}
	public String geteProdKey() {
		return eProdKey;
	}
	public void seteProdKey(String eProdKey) {
		this.eProdKey = eProdKey;
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
	public String getePlaceName() {
		return ePlaceName;
	}
	public void setePlaceName(String ePlaceName) {
		this.ePlaceName = ePlaceName;
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
	public String geteProductionInNoTemp() {
		return eProductionInNoTemp;
	}
	public void seteProductionInNoTemp(String eProductionInNoTemp) {
		this.eProductionInNoTemp = eProductionInNoTemp;
	}
	public String geteSubPlaceKey() {
		return eSubPlaceKey;
	}
	public void seteSubPlaceKey(String eSubPlaceKey) {
		this.eSubPlaceKey = eSubPlaceKey;
	}
	public String geteSubFlag() {
		return eSubFlag;
	}
	public void seteSubFlag(String eSubFlag) {
		this.eSubFlag = eSubFlag;
	}
	public String geteProdSn() {
		return eProdSn;
	}
	public void seteProdSn(String eProdSn) {
		this.eProdSn = eProdSn;
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
	public String geteChulgoGubunKey() {
		return eChulgoGubunKey;
	}
	public void seteChulgoGubunKey(String eChulgoGubunKey) {
		this.eChulgoGubunKey = eChulgoGubunKey;
	}
	public String geteProcSn() {
		return eProcSn;
	}
	public void seteProcSn(String eProcSn) {
		this.eProcSn = eProcSn;
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
	public String getTotCnt() {
		return totCnt;
	}
	public void setTotCnt(String totCnt) {
		this.totCnt = totCnt;
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
	public String geteBuyChulgoTot() {
		return eBuyChulgoTot;
	}
	public void seteBuyChulgoTot(String eBuyChulgoTot) {
		this.eBuyChulgoTot = eBuyChulgoTot;
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
	public String geteSpeed() {
		return eSpeed;
	}
	public void seteSpeed(String eSpeed) {
		this.eSpeed = eSpeed;
	}
	public String geteThread() {
		return eThread;
	}
	public void seteThread(String eThread) {
		this.eThread = eThread;
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
	public String getWorkCnt() {
		return workCnt;
	}
	public void setWorkCnt(String workCnt) {
		this.workCnt = workCnt;
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
	public String getGubunDate() {
		return gubunDate;
	}
	public void setGubunDate(String gubunDate) {
		this.gubunDate = gubunDate;
	}
	public String getePrice() {
		return ePrice;
	}
	public void setePrice(String ePrice) {
		this.ePrice = ePrice;
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
	public String geteItemGubun2() {
		return eItemGubun2;
	}
	public void seteItemGubun2(String eItemGubun2) {
		this.eItemGubun2 = eItemGubun2;
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
	public String geteSubaljuCnt() {
		return eSubaljuCnt;
	}
	public void seteSubaljuCnt(String eSubaljuCnt) {
		this.eSubaljuCnt = eSubaljuCnt;
	}
	public String geteSubaljuWdate() {
		return eSubaljuWdate;
	}
	public void seteSubaljuWdate(String eSubaljuWdate) {
		this.eSubaljuWdate = eSubaljuWdate;
	}
	public String geteSubaljuUdate() {
		return eSubaljuUdate;
	}
	public void seteSubaljuUdate(String eSubaljuUdate) {
		this.eSubaljuUdate = eSubaljuUdate;
	}
	public String geteSubaljuPhone() {
		return eSubaljuPhone;
	}
	public void seteSubaljuPhone(String eSubaljuPhone) {
		this.eSubaljuPhone = eSubaljuPhone;
	}
	public String geteSubaljuAddress() {
		return eSubaljuAddress;
	}
	public void seteSubaljuAddress(String eSubaljuAddress) {
		this.eSubaljuAddress = eSubaljuAddress;
	}
	public String geteSubaljuPost() {
		return eSubaljuPost;
	}
	public void seteSubaljuPost(String eSubaljuPost) {
		this.eSubaljuPost = eSubaljuPost;
	}
	public String geteSubaljuEtc() {
		return eSubaljuEtc;
	}
	public void seteSubaljuEtc(String eSubaljuEtc) {
		this.eSubaljuEtc = eSubaljuEtc;
	}
	public String geteSubaljuSend() {
		return eSubaljuSend;
	}
	public void seteSubaljuSend(String eSubaljuSend) {
		this.eSubaljuSend = eSubaljuSend;
	}
	public String geteSubaljuSendName() {
		return eSubaljuSendName;
	}
	public void seteSubaljuSendName(String eSubaljuSendName) {
		this.eSubaljuSendName = eSubaljuSendName;
	}
	public String geteSubaljuPdateGubun() {
		return eSubaljuPdateGubun;
	}
	public void seteSubaljuPdateGubun(String eSubaljuPdateGubun) {
		this.eSubaljuPdateGubun = eSubaljuPdateGubun;
	}
	public String geteSubaljuSendPrice() {
		return eSubaljuSendPrice;
	}
	public void seteSubaljuSendPrice(String eSubaljuSendPrice) {
		this.eSubaljuSendPrice = eSubaljuSendPrice;
	}
	public String geteSubaljuTerms() {
		return eSubaljuTerms;
	}
	public void seteSubaljuTerms(String eSubaljuTerms) {
		this.eSubaljuTerms = eSubaljuTerms;
	}
	public String geteSubaljuFile() {
		return eSubaljuFile;
	}
	public void seteSubaljuFile(String eSubaljuFile) {
		this.eSubaljuFile = eSubaljuFile;
	}
	public String geteSubaljuFileSn() {
		return eSubaljuFileSn;
	}
	public void seteSubaljuFileSn(String eSubaljuFileSn) {
		this.eSubaljuFileSn = eSubaljuFileSn;
	}
	public String geteSubaljuPeople() {
		return eSubaljuPeople;
	}
	public void seteSubaljuPeople(String eSubaljuPeople) {
		this.eSubaljuPeople = eSubaljuPeople;
	}
	public String geteSubaljuAccount() {
		return eSubaljuAccount;
	}
	public void seteSubaljuAccount(String eSubaljuAccount) {
		this.eSubaljuAccount = eSubaljuAccount;
	}
	public String geteSubaljuPriceGubunKey() {
		return eSubaljuPriceGubunKey;
	}
	public void seteSubaljuPriceGubunKey(String eSubaljuPriceGubunKey) {
		this.eSubaljuPriceGubunKey = eSubaljuPriceGubunKey;
	}
	public String geteSubaljuSendKey() {
		return eSubaljuSendKey;
	}
	public void seteSubaljuSendKey(String eSubaljuSendKey) {
		this.eSubaljuSendKey = eSubaljuSendKey;
	}
	public String geteSubaljuCountry() {
		return eSubaljuCountry;
	}
	public void seteSubaljuCountry(String eSubaljuCountry) {
		this.eSubaljuCountry = eSubaljuCountry;
	}
	public String geteSubaljuFileGubun() {
		return eSubaljuFileGubun;
	}
	public void seteSubaljuFileGubun(String eSubaljuFileGubun) {
		this.eSubaljuFileGubun = eSubaljuFileGubun;
	}
	public String geteSubaljuFileBefore() {
		return eSubaljuFileBefore;
	}
	public void seteSubaljuFileBefore(String eSubaljuFileBefore) {
		this.eSubaljuFileBefore = eSubaljuFileBefore;
	}
	public String geteSubaljuLine() {
		return eSubaljuLine;
	}
	public void seteSubaljuLine(String eSubaljuLine) {
		this.eSubaljuLine = eSubaljuLine;
	}
	public String geteSubaljuReceiver() {
		return eSubaljuReceiver;
	}
	public void seteSubaljuReceiver(String eSubaljuReceiver) {
		this.eSubaljuReceiver = eSubaljuReceiver;
	}
	public String geteItemBrix() {
		return eItemBrix;
	}
	public void seteItemBrix(String eItemBrix) {
		this.eItemBrix = eItemBrix;
	}
	public String geteItemPh() {
		return eItemPh;
	}
	public void seteItemPh(String eItemPh) {
		this.eItemPh = eItemPh;
	}
	public String geteItemPsu() {
		return eItemPsu;
	}
	public void seteItemPsu(String eItemPsu) {
		this.eItemPsu = eItemPsu;
	}
	public String geteItemAmt() {
		return eItemAmt;
	}
	public void seteItemAmt(String eItemAmt) {
		this.eItemAmt = eItemAmt;
	}
	public String geteItemAmtTot() {
		return eItemAmtTot;
	}
	public void seteItemAmtTot(String eItemAmtTot) {
		this.eItemAmtTot = eItemAmtTot;
	}
	public String geteState() {
		return eState;
	}
	public void seteState(String eState) {
		this.eState = eState;
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
	public String geteBaljuItemDelivery() {
		return eBaljuItemDelivery;
	}
	public void seteBaljuItemDelivery(String eBaljuItemDelivery) {
		this.eBaljuItemDelivery = eBaljuItemDelivery;
	}
	public String geteApprovalStatus() {
		return eApprovalStatus;
	}
	public void seteApprovalStatus(String eApprovalStatus) {
		this.eApprovalStatus = eApprovalStatus;
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
	public String geteBaljuItemPartNo() {
		return eBaljuItemPartNo;
	}
	public void seteBaljuItemPartNo(String eBaljuItemPartNo) {
		this.eBaljuItemPartNo = eBaljuItemPartNo;
	}
	public String geteBaljuDescription() {
		return eBaljuDescription;
	}
	public void seteBaljuDescription(String eBaljuDescription) {
		this.eBaljuDescription = eBaljuDescription;
	}
	public String geteBaljuPriceGubun() {
		return eBaljuPriceGubun;
	}
	public void seteBaljuPriceGubun(String eBaljuPriceGubun) {
		this.eBaljuPriceGubun = eBaljuPriceGubun;
	}
	public String geteBaljuPrice() {
		return eBaljuPrice;
	}
	public void seteBaljuPrice(String eBaljuPrice) {
		this.eBaljuPrice = eBaljuPrice;
	}
	public String geteBaljuApprovalStatus() {
		return eBaljuApprovalStatus;
	}
	public void seteBaljuApprovalStatus(String eBaljuApprovalStatus) {
		this.eBaljuApprovalStatus = eBaljuApprovalStatus;
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
	public String geteBaljuAmt() {
		return eBaljuAmt;
	}
	public void seteBaljuAmt(String eBaljuAmt) {
		this.eBaljuAmt = eBaljuAmt;
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
	public String geteBuyIpgoItemKey() {
		return eBuyIpgoItemKey;
	}
	public void seteBuyIpgoItemKey(String eBuyIpgoItemKey) {
		this.eBuyIpgoItemKey = eBuyIpgoItemKey;
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
	public String geteIpgoGubunText() {
		return eIpgoGubunText;
	}
	public void seteIpgoGubunText(String eIpgoGubunText) {
		this.eIpgoGubunText = eIpgoGubunText;
	}
	public String geteCheckKey() {
		return eCheckKey;
	}
	public void seteCheckKey(String eCheckKey) {
		this.eCheckKey = eCheckKey;
	}
	public String geteBuyIpgoItemNo() {
		return eBuyIpgoItemNo;
	}
	public void seteBuyIpgoItemNo(String eBuyIpgoItemNo) {
		this.eBuyIpgoItemNo = eBuyIpgoItemNo;
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
	public String geteCheckGubun() {
		return eCheckGubun;
	}
	public void seteCheckGubun(String eCheckGubun) {
		this.eCheckGubun = eCheckGubun;
	}
	public String geteFailCnt() {
		return eFailCnt;
	}
	public void seteFailCnt(String eFailCnt) {
		this.eFailCnt = eFailCnt;
	}
	public String geteItemDescription() {
		return eItemDescription;
	}
	public void seteItemDescription(String eItemDescription) {
		this.eItemDescription = eItemDescription;
	}
	public String geteCheckCnt() {
		return eCheckCnt;
	}
	public void seteCheckCnt(String eCheckCnt) {
		this.eCheckCnt = eCheckCnt;
	}
	public String getiKey() {
		return iKey;
	}
	public void setiKey(String iKey) {
		this.iKey = iKey;
	}
	public String getBuyCnt() {
		return buyCnt;
	}
	public void setBuyCnt(String buyCnt) {
		this.buyCnt = buyCnt;
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
	public String geteOutCnt() {
		return eOutCnt;
	}
	public void seteOutCnt(String eOutCnt) {
		this.eOutCnt = eOutCnt;
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
	public String getItemProdGubun() {
		return itemProdGubun;
	}
	public void setItemProdGubun(String itemProdGubun) {
		this.itemProdGubun = itemProdGubun;
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
	public String getAlertGubun() {
		return alertGubun;
	}
	public void setAlertGubun(String alertGubun) {
		this.alertGubun = alertGubun;
	}
	public String geteOutsoGubun() {
		return eOutsoGubun;
	}
	public void seteOutsoGubun(String eOutsoGubun) {
		this.eOutsoGubun = eOutsoGubun;
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
	public String getChulgoCnt() {
		return chulgoCnt;
	}
	public void setChulgoCnt(String chulgoCnt) {
		this.chulgoCnt = chulgoCnt;
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
	public String geteBaljuFile() {
		return eBaljuFile;
	}
	public void seteBaljuFile(String eBaljuFile) {
		this.eBaljuFile = eBaljuFile;
	}
	public String geteBaljuFileGubun() {
		return eBaljuFileGubun;
	}
	public void seteBaljuFileGubun(String eBaljuFileGubun) {
		this.eBaljuFileGubun = eBaljuFileGubun;
	}
	public String geteInvenPageGubun() {
		return eInvenPageGubun;
	}
	public void seteInvenPageGubun(String eInvenPageGubun) {
		this.eInvenPageGubun = eInvenPageGubun;
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
	public String geteApprovalCheck() {
		return eApprovalCheck;
	}
	public void seteApprovalCheck(String eApprovalCheck) {
		this.eApprovalCheck = eApprovalCheck;
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
	public String geteComKey() {
		return eComKey;
	}
	public void seteComKey(String eComKey) {
		this.eComKey = eComKey;
	}
	public String getsItemCate() {
		return sItemCate;
	}
	public void setsItemCate(String sItemCate) {
		this.sItemCate = sItemCate;
	}
	public String geteBarcodeSn() {
		return eBarcodeSn;
	}
	public void seteBarcodeSn(String eBarcodeSn) {
		this.eBarcodeSn = eBarcodeSn;
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
	public String getsItemSalePrice() {
		return sItemSalePrice;
	}
	public void setsItemSalePrice(String sItemSalePrice) {
		this.sItemSalePrice = sItemSalePrice;
	}
	public String geteOutBarcode() {
		return eOutBarcode;
	}
	public void seteOutBarcode(String eOutBarcode) {
		this.eOutBarcode = eOutBarcode;
	}
	public String geteBuyIpgoItemPlaceKey() {
		return eBuyIpgoItemPlaceKey;
	}
	public void seteBuyIpgoItemPlaceKey(String eBuyIpgoItemPlaceKey) {
		this.eBuyIpgoItemPlaceKey = eBuyIpgoItemPlaceKey;
	}
	public String getePlaceKey() {
		return ePlaceKey;
	}
	public void setePlaceKey(String ePlaceKey) {
		this.ePlaceKey = ePlaceKey;
	}
	public String geteOrderKey() {
		return eOrderKey;
	}
	public void seteOrderKey(String eOrderKey) {
		this.eOrderKey = eOrderKey;
	}
	public String geteOrderCnt() {
		return eOrderCnt;
	}
	public void seteOrderCnt(String eOrderCnt) {
		this.eOrderCnt = eOrderCnt;
	}
	public String geteOrderNum() {
		return eOrderNum;
	}
	public void seteOrderNum(String eOrderNum) {
		this.eOrderNum = eOrderNum;
	}
	public String geteOrderWdate() {
		return eOrderWdate;
	}
	public void seteOrderWdate(String eOrderWdate) {
		this.eOrderWdate = eOrderWdate;
	}
	public String geteOrderUdate() {
		return eOrderUdate;
	}
	public void seteOrderUdate(String eOrderUdate) {
		this.eOrderUdate = eOrderUdate;
	}
	public String geteOrderDate() {
		return eOrderDate;
	}
	public void seteOrderDate(String eOrderDate) {
		this.eOrderDate = eOrderDate;
	}
	public String geteOrderPdate() {
		return eOrderPdate;
	}
	public void seteOrderPdate(String eOrderPdate) {
		this.eOrderPdate = eOrderPdate;
	}
	public String geteOrderPhone() {
		return eOrderPhone;
	}
	public void seteOrderPhone(String eOrderPhone) {
		this.eOrderPhone = eOrderPhone;
	}
	public String geteOrderAddress() {
		return eOrderAddress;
	}
	public void seteOrderAddress(String eOrderAddress) {
		this.eOrderAddress = eOrderAddress;
	}
	public String geteOrderAddress1() {
		return eOrderAddress1;
	}
	public void seteOrderAddress1(String eOrderAddress1) {
		this.eOrderAddress1 = eOrderAddress1;
	}
	public String geteOrderAddress2() {
		return eOrderAddress2;
	}
	public void seteOrderAddress2(String eOrderAddress2) {
		this.eOrderAddress2 = eOrderAddress2;
	}
	public String geteOrderPost() {
		return eOrderPost;
	}
	public void seteOrderPost(String eOrderPost) {
		this.eOrderPost = eOrderPost;
	}
	public String geteOrderEtc() {
		return eOrderEtc;
	}
	public void seteOrderEtc(String eOrderEtc) {
		this.eOrderEtc = eOrderEtc;
	}
	public String geteOrderSend() {
		return eOrderSend;
	}
	public void seteOrderSend(String eOrderSend) {
		this.eOrderSend = eOrderSend;
	}
	public String geteItemCntTot() {
		return eItemCntTot;
	}
	public void seteItemCntTot(String eItemCntTot) {
		this.eItemCntTot = eItemCntTot;
	}
	public String geteItemKgTot() {
		return eItemKgTot;
	}
	public void seteItemKgTot(String eItemKgTot) {
		this.eItemKgTot = eItemKgTot;
	}
	public String geteOrderItemKey() {
		return eOrderItemKey;
	}
	public void seteOrderItemKey(String eOrderItemKey) {
		this.eOrderItemKey = eOrderItemKey;
	}
	public String geteJeagoCnt() {
		return eJeagoCnt;
	}
	public void seteJeagoCnt(String eJeagoCnt) {
		this.eJeagoCnt = eJeagoCnt;
	}
	public String geteOrderSendName() {
		return eOrderSendName;
	}
	public void seteOrderSendName(String eOrderSendName) {
		this.eOrderSendName = eOrderSendName;
	}
	public String geteChulgoGubun() {
		return eChulgoGubun;
	}
	public void seteChulgoGubun(String eChulgoGubun) {
		this.eChulgoGubun = eChulgoGubun;
	}
	public String geteChulgoDate() {
		return eChulgoDate;
	}
	public void seteChulgoDate(String eChulgoDate) {
		this.eChulgoDate = eChulgoDate;
	}
	public String getPageGubun() {
		return pageGubun;
	}
	public void setPageGubun(String pageGubun) {
		this.pageGubun = pageGubun;
	}
	public String geteOrderPdateGubun() {
		return eOrderPdateGubun;
	}
	public void seteOrderPdateGubun(String eOrderPdateGubun) {
		this.eOrderPdateGubun = eOrderPdateGubun;
	}
	public String geteDiffPdate() {
		return eDiffPdate;
	}
	public void seteDiffPdate(String eDiffPdate) {
		this.eDiffPdate = eDiffPdate;
	}
	public String geteOrderSendPrice() {
		return eOrderSendPrice;
	}
	public void seteOrderSendPrice(String eOrderSendPrice) {
		this.eOrderSendPrice = eOrderSendPrice;
	}
	public String geteItemPriceTot() {
		return eItemPriceTot;
	}
	public void seteItemPriceTot(String eItemPriceTot) {
		this.eItemPriceTot = eItemPriceTot;
	}
	public String geteMatePrice() {
		return eMatePrice;
	}
	public void seteMatePrice(String eMatePrice) {
		this.eMatePrice = eMatePrice;
	}
	public String getLtGubun() {
		return ltGubun;
	}
	public void setLtGubun(String ltGubun) {
		this.ltGubun = ltGubun;
	}
	public String getASGubun() {
		return ASGubun;
	}
	public void setASGubun(String aSGubun) {
		ASGubun = aSGubun;
	}
	public String geteASKindKey() {
		return eASKindKey;
	}
	public void seteASKindKey(String eASKindKey) {
		this.eASKindKey = eASKindKey;
	}
	public String geteOrderApprovalStatus() {
		return eOrderApprovalStatus;
	}
	public void seteOrderApprovalStatus(String eOrderApprovalStatus) {
		this.eOrderApprovalStatus = eOrderApprovalStatus;
	}
	public String geteApprovalGubun() {
		return eApprovalGubun;
	}
	public void seteApprovalGubun(String eApprovalGubun) {
		this.eApprovalGubun = eApprovalGubun;
	}
	public String geteFlag2() {
		return eFlag2;
	}
	public void seteFlag2(String eFlag2) {
		this.eFlag2 = eFlag2;
	}
	public String geteOrderApprovalCheck() {
		return eOrderApprovalCheck;
	}
	public void seteOrderApprovalCheck(String eOrderApprovalCheck) {
		this.eOrderApprovalCheck = eOrderApprovalCheck;
	}
	public String geteOrderApprovalReason() {
		return eOrderApprovalReason;
	}
	public void seteOrderApprovalReason(String eOrderApprovalReason) {
		this.eOrderApprovalReason = eOrderApprovalReason;
	}
	public String geteOrderStatusYCnt() {
		return eOrderStatusYCnt;
	}
	public void seteOrderStatusYCnt(String eOrderStatusYCnt) {
		this.eOrderStatusYCnt = eOrderStatusYCnt;
	}
	public String geteBarcode() {
		return eBarcode;
	}
	public void seteBarcode(String eBarcode) {
		this.eBarcode = eBarcode;
	}
	public String geteChulgoKey() {
		return eChulgoKey;
	}
	public void seteChulgoKey(String eChulgoKey) {
		this.eChulgoKey = eChulgoKey;
	}
	public String geteChulgoWdate() {
		return eChulgoWdate;
	}
	public void seteChulgoWdate(String eChulgoWdate) {
		this.eChulgoWdate = eChulgoWdate;
	}
	public String geteChulgoUdate() {
		return eChulgoUdate;
	}
	public void seteChulgoUdate(String eChulgoUdate) {
		this.eChulgoUdate = eChulgoUdate;
	}
	public String geteChulgoNum() {
		return eChulgoNum;
	}
	public void seteChulgoNum(String eChulgoNum) {
		this.eChulgoNum = eChulgoNum;
	}
	public String geteChulgoChulhajang() {
		return eChulgoChulhajang;
	}
	public void seteChulgoChulhajang(String eChulgoChulhajang) {
		this.eChulgoChulhajang = eChulgoChulhajang;
	}
	public String geteChulgoSangcha() {
		return eChulgoSangcha;
	}
	public void seteChulgoSangcha(String eChulgoSangcha) {
		this.eChulgoSangcha = eChulgoSangcha;
	}
	public String geteChulgoNum2() {
		return eChulgoNum2;
	}
	public void seteChulgoNum2(String eChulgoNum2) {
		this.eChulgoNum2 = eChulgoNum2;
	}
	public String geteChulgoTrade() {
		return eChulgoTrade;
	}
	public void seteChulgoTrade(String eChulgoTrade) {
		this.eChulgoTrade = eChulgoTrade;
	}
	public String geteChulgoNabji() {
		return eChulgoNabji;
	}
	public void seteChulgoNabji(String eChulgoNabji) {
		this.eChulgoNabji = eChulgoNabji;
	}
	public String geteChulgoInsu() {
		return eChulgoInsu;
	}
	public void seteChulgoInsu(String eChulgoInsu) {
		this.eChulgoInsu = eChulgoInsu;
	}
	public String geteChulgoCountry() {
		return eChulgoCountry;
	}
	public void seteChulgoCountry(String eChulgoCountry) {
		this.eChulgoCountry = eChulgoCountry;
	}
	public String geteChulgoCard() {
		return eChulgoCard;
	}
	public void seteChulgoCard(String eChulgoCard) {
		this.eChulgoCard = eChulgoCard;
	}
	public String geteChulgoCarNum() {
		return eChulgoCarNum;
	}
	public void seteChulgoCarNum(String eChulgoCarNum) {
		this.eChulgoCarNum = eChulgoCarNum;
	}
	public String geteChulgoBulk() {
		return eChulgoBulk;
	}
	public void seteChulgoBulk(String eChulgoBulk) {
		this.eChulgoBulk = eChulgoBulk;
	}
	public String geteChulgoLotno() {
		return eChulgoLotno;
	}
	public void seteChulgoLotno(String eChulgoLotno) {
		this.eChulgoLotno = eChulgoLotno;
	}
	public String geteChulgoBulk1() {
		return eChulgoBulk1;
	}
	public void seteChulgoBulk1(String eChulgoBulk1) {
		this.eChulgoBulk1 = eChulgoBulk1;
	}
	public String geteChulgoBulk2() {
		return eChulgoBulk2;
	}
	public void seteChulgoBulk2(String eChulgoBulk2) {
		this.eChulgoBulk2 = eChulgoBulk2;
	}
	public String geteChulgoKg() {
		return eChulgoKg;
	}
	public void seteChulgoKg(String eChulgoKg) {
		this.eChulgoKg = eChulgoKg;
	}
	public String geteChulgoPallet1() {
		return eChulgoPallet1;
	}
	public void seteChulgoPallet1(String eChulgoPallet1) {
		this.eChulgoPallet1 = eChulgoPallet1;
	}
	public String geteChulgoPalletCnt1() {
		return eChulgoPalletCnt1;
	}
	public void seteChulgoPalletCnt1(String eChulgoPalletCnt1) {
		this.eChulgoPalletCnt1 = eChulgoPalletCnt1;
	}
	public String geteChulgoPallet2() {
		return eChulgoPallet2;
	}
	public void seteChulgoPallet2(String eChulgoPallet2) {
		this.eChulgoPallet2 = eChulgoPallet2;
	}
	public String geteChulgoPalletCnt2() {
		return eChulgoPalletCnt2;
	}
	public void seteChulgoPalletCnt2(String eChulgoPalletCnt2) {
		this.eChulgoPalletCnt2 = eChulgoPalletCnt2;
	}
	public String geteChulgoNext() {
		return eChulgoNext;
	}
	public void seteChulgoNext(String eChulgoNext) {
		this.eChulgoNext = eChulgoNext;
	}
	public String geteChulgoRest() {
		return eChulgoRest;
	}
	public void seteChulgoRest(String eChulgoRest) {
		this.eChulgoRest = eChulgoRest;
	}
	public String geteChulgoChulhaone() {
		return eChulgoChulhaone;
	}
	public void seteChulgoChulhaone(String eChulgoChulhaone) {
		this.eChulgoChulhaone = eChulgoChulhaone;
	}
	public String geteChulgoInsuja() {
		return eChulgoInsuja;
	}
	public void seteChulgoInsuja(String eChulgoInsuja) {
		this.eChulgoInsuja = eChulgoInsuja;
	}
	public String geteChulgoUnbanone() {
		return eChulgoUnbanone;
	}
	public void seteChulgoUnbanone(String eChulgoUnbanone) {
		this.eChulgoUnbanone = eChulgoUnbanone;
	}
	public String geteChulgoItemKey() {
		return eChulgoItemKey;
	}
	public void seteChulgoItemKey(String eChulgoItemKey) {
		this.eChulgoItemKey = eChulgoItemKey;
	}
	public String getsItemUnit() {
		return sItemUnit;
	}
	public void setsItemUnit(String sItemUnit) {
		this.sItemUnit = sItemUnit;
	}
	public String geteItemLotno() {
		return eItemLotno;
	}
	public void seteItemLotno(String eItemLotno) {
		this.eItemLotno = eItemLotno;
	}
	public String getePlace() {
		return ePlace;
	}
	public void setePlace(String ePlace) {
		this.ePlace = ePlace;
	}
	public String geteItemKg() {
		return eItemKg;
	}
	public void seteItemKg(String eItemKg) {
		this.eItemKg = eItemKg;
	}
	public String geteItemTotCnt() {
		return eItemTotCnt;
	}
	public void seteItemTotCnt(String eItemTotCnt) {
		this.eItemTotCnt = eItemTotCnt;
	}
	public String geteItemTotKg() {
		return eItemTotKg;
	}
	public void seteItemTotKg(String eItemTotKg) {
		this.eItemTotKg = eItemTotKg;
	}
	public String geteItemPlace() {
		return eItemPlace;
	}
	public void seteItemPlace(String eItemPlace) {
		this.eItemPlace = eItemPlace;
	}
	public String geteItemKey2() {
		return eItemKey2;
	}
	public void seteItemKey2(String eItemKey2) {
		this.eItemKey2 = eItemKey2;
	}
	public String geteItemName2() {
		return eItemName2;
	}
	public void seteItemName2(String eItemName2) {
		this.eItemName2 = eItemName2;
	}
	public String geteGubun() {
		return eGubun;
	}
	public void seteGubun(String eGubun) {
		this.eGubun = eGubun;
	}
	public String geteASItemKey() {
		return eASItemKey;
	}
	public void seteASItemKey(String eASItemKey) {
		this.eASItemKey = eASItemKey;
	}
	public String geteTaxGubun() {
		return eTaxGubun;
	}
	public void seteTaxGubun(String eTaxGubun) {
		this.eTaxGubun = eTaxGubun;
	}
	public String geteChulgoDate2() {
		return eChulgoDate2;
	}
	public void seteChulgoDate2(String eChulgoDate2) {
		this.eChulgoDate2 = eChulgoDate2;
	}
	public String geteChulgoYear() {
		return eChulgoYear;
	}
	public void seteChulgoYear(String eChulgoYear) {
		this.eChulgoYear = eChulgoYear;
	}
	public String geteChulgoMonth() {
		return eChulgoMonth;
	}
	public void seteChulgoMonth(String eChulgoMonth) {
		this.eChulgoMonth = eChulgoMonth;
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
	public String getsItemCode() {
		return sItemCode;
	}
	public void setsItemCode(String sItemCode) {
		this.sItemCode = sItemCode;
	}
	public String getsItemPartNo() {
		return sItemPartNo;
	}
	public void setsItemPartNo(String sItemPartNo) {
		this.sItemPartNo = sItemPartNo;
	}
	public String getsItemComPartNo() {
		return sItemComPartNo;
	}
	public void setsItemComPartNo(String sItemComPartNo) {
		this.sItemComPartNo = sItemComPartNo;
	}
	public String geteItemComPartNo() {
		return eItemComPartNo;
	}
	public void seteItemComPartNo(String eItemComPartNo) {
		this.eItemComPartNo = eItemComPartNo;
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
	public String getsItemDescription() {
		return sItemDescription;
	}
	public void setsItemDescription(String sItemDescription) {
		this.sItemDescription = sItemDescription;
	}
	public String getsItemCateKey() {
		return sItemCateKey;
	}
	public void setsItemCateKey(String sItemCateKey) {
		this.sItemCateKey = sItemCateKey;
	}
	public String getsItemInven() {
		return sItemInven;
	}
	public void setsItemInven(String sItemInven) {
		this.sItemInven = sItemInven;
	}
	public String getsItemMainGubun() {
		return sItemMainGubun;
	}
	public void setsItemMainGubun(String sItemMainGubun) {
		this.sItemMainGubun = sItemMainGubun;
	}
	public String getsItemProdGubun() {
		return sItemProdGubun;
	}
	public void setsItemProdGubun(String sItemProdGubun) {
		this.sItemProdGubun = sItemProdGubun;
	}
	public String getsItemMaker() {
		return sItemMaker;
	}
	public void setsItemMaker(String sItemMaker) {
		this.sItemMaker = sItemMaker;
	}
	public String getsItemSupplier() {
		return sItemSupplier;
	}
	public void setsItemSupplier(String sItemSupplier) {
		this.sItemSupplier = sItemSupplier;
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
	public String getsItemRev() {
		return sItemRev;
	}
	public void setsItemRev(String sItemRev) {
		this.sItemRev = sItemRev;
	}
	public String geteItemPartNo() {
		return eItemPartNo;
	}
	public void seteItemPartNo(String eItemPartNo) {
		this.eItemPartNo = eItemPartNo;
	}
	public String geteItemCode() {
		return eItemCode;
	}
	public void seteItemCode(String eItemCode) {
		this.eItemCode = eItemCode;
	}
	public String geteOrderPriceGubun() {
		return eOrderPriceGubun;
	}
	public void seteOrderPriceGubun(String eOrderPriceGubun) {
		this.eOrderPriceGubun = eOrderPriceGubun;
	}
	public String geteOrderTerms() {
		return eOrderTerms;
	}
	public void seteOrderTerms(String eOrderTerms) {
		this.eOrderTerms = eOrderTerms;
	}
	public String geteOrderFile() {
		return eOrderFile;
	}
	public void seteOrderFile(String eOrderFile) {
		this.eOrderFile = eOrderFile;
	}
	public String geteOrderFileSn() {
		return eOrderFileSn;
	}
	public void seteOrderFileSn(String eOrderFileSn) {
		this.eOrderFileSn = eOrderFileSn;
	}
	public String geteOrderPeople() {
		return eOrderPeople;
	}
	public void seteOrderPeople(String eOrderPeople) {
		this.eOrderPeople = eOrderPeople;
	}
	public String geteOrderAccount() {
		return eOrderAccount;
	}
	public void seteOrderAccount(String eOrderAccount) {
		this.eOrderAccount = eOrderAccount;
	}
	public String getCntGubun() {
		return cntGubun;
	}
	public void setCntGubun(String cntGubun) {
		this.cntGubun = cntGubun;
	}
	public String geteOrderPriceGubunKey() {
		return eOrderPriceGubunKey;
	}
	public void seteOrderPriceGubunKey(String eOrderPriceGubunKey) {
		this.eOrderPriceGubunKey = eOrderPriceGubunKey;
	}
	public String geteOrderSendKey() {
		return eOrderSendKey;
	}
	public void seteOrderSendKey(String eOrderSendKey) {
		this.eOrderSendKey = eOrderSendKey;
	}
	public String geteOrderCountry() {
		return eOrderCountry;
	}
	public void seteOrderCountry(String eOrderCountry) {
		this.eOrderCountry = eOrderCountry;
	}
	public String geteInvenCnt() {
		return eInvenCnt;
	}
	public void seteInvenCnt(String eInvenCnt) {
		this.eInvenCnt = eInvenCnt;
	}
	public String geteUnitPrice() {
		return eUnitPrice;
	}
	public void seteUnitPrice(String eUnitPrice) {
		this.eUnitPrice = eUnitPrice;
	}
	public String geteOrderFileGubun() {
		return eOrderFileGubun;
	}
	public void seteOrderFileGubun(String eOrderFileGubun) {
		this.eOrderFileGubun = eOrderFileGubun;
	}
	public String geteOrderFileBefore() {
		return eOrderFileBefore;
	}
	public void seteOrderFileBefore(String eOrderFileBefore) {
		this.eOrderFileBefore = eOrderFileBefore;
	}
	public String geteOrderLine() {
		return eOrderLine;
	}
	public void seteOrderLine(String eOrderLine) {
		this.eOrderLine = eOrderLine;
	}
	public String getePassGubun() {
		return ePassGubun;
	}
	public void setePassGubun(String ePassGubun) {
		this.ePassGubun = ePassGubun;
	}
	public String geteFailKey() {
		return eFailKey;
	}
	public void seteFailKey(String eFailKey) {
		this.eFailKey = eFailKey;
	}
	public String geteOutCheckKey() {
		return eOutCheckKey;
	}
	public void seteOutCheckKey(String eOutCheckKey) {
		this.eOutCheckKey = eOutCheckKey;
	}
	public String geteOutCheckEtc() {
		return eOutCheckEtc;
	}
	public void seteOutCheckEtc(String eOutCheckEtc) {
		this.eOutCheckEtc = eOutCheckEtc;
	}
	public String geteReplaceBarcode() {
		return eReplaceBarcode;
	}
	public void seteReplaceBarcode(String eReplaceBarcode) {
		this.eReplaceBarcode = eReplaceBarcode;
	}
	public String getSearchType2() {
		return searchType2;
	}
	public void setSearchType2(String searchType2) {
		this.searchType2 = searchType2;
	}
	public String geteItemPdate() {
		return eItemPdate;
	}
	public void seteItemPdate(String eItemPdate) {
		this.eItemPdate = eItemPdate;
	}
	public String geteYYCnt() {
		return eYYCnt;
	}
	public void seteYYCnt(String eYYCnt) {
		this.eYYCnt = eYYCnt;
	}
	public String geteDelayGubun() {
		return eDelayGubun;
	}
	public void seteDelayGubun(String eDelayGubun) {
		this.eDelayGubun = eDelayGubun;
	}
	public String geteDelayDay() {
		return eDelayDay;
	}
	public void seteDelayDay(String eDelayDay) {
		this.eDelayDay = eDelayDay;
	}
	public String geteBuyreqKey() {
		return eBuyreqKey;
	}
	public void seteBuyreqKey(String eBuyreqKey) {
		this.eBuyreqKey = eBuyreqKey;
	}
	public String geteBuyreqItemKey() {
		return eBuyreqItemKey;
	}
	public void seteBuyreqItemKey(String eBuyreqItemKey) {
		this.eBuyreqItemKey = eBuyreqItemKey;
	}
	public String geteBuyreqNum() {
		return eBuyreqNum;
	}
	public void seteBuyreqNum(String eBuyreqNum) {
		this.eBuyreqNum = eBuyreqNum;
	}
	public String geteBuyreqSdate() {
		return eBuyreqSdate;
	}
	public void seteBuyreqSdate(String eBuyreqSdate) {
		this.eBuyreqSdate = eBuyreqSdate;
	}
	public String geteBuyreqRdate() {
		return eBuyreqRdate;
	}
	public void seteBuyreqRdate(String eBuyreqRdate) {
		this.eBuyreqRdate = eBuyreqRdate;
	}
	public String geteBuyreqRemark() {
		return eBuyreqRemark;
	}
	public void seteBuyreqRemark(String eBuyreqRemark) {
		this.eBuyreqRemark = eBuyreqRemark;
	}
	public String geteBuyreqFileName() {
		return eBuyreqFileName;
	}
	public void seteBuyreqFileName(String eBuyreqFileName) {
		this.eBuyreqFileName = eBuyreqFileName;
	}
	public String geteBuyreqConfirm() {
		return eBuyreqConfirm;
	}
	public void seteBuyreqConfirm(String eBuyreqConfirm) {
		this.eBuyreqConfirm = eBuyreqConfirm;
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
	public String getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getItemCnt() {
		return itemCnt;
	}
	public void setItemCnt(String itemCnt) {
		this.itemCnt = itemCnt;
	}
	public String getItemSum() {
		return itemSum;
	}
	public void setItemSum(String itemSum) {
		this.itemSum = itemSum;
	}
	public String getItemNote() {
		return itemNote;
	}
	public void setItemNote(String itemNote) {
		this.itemNote = itemNote;
	}
	public String getItemFlag() {
		return itemFlag;
	}
	public void setItemFlag(String itemFlag) {
		this.itemFlag = itemFlag;
	}
	public String getItemPriceComma() {
		return itemPriceComma;
	}
	public void setItemPriceComma(String itemPriceComma) {
		this.itemPriceComma = itemPriceComma;
	}
	public String getItemCntComma() {
		return itemCntComma;
	}
	public void setItemCntComma(String itemCntComma) {
		this.itemCntComma = itemCntComma;
	}
	public String getItemSumComma() {
		return itemSumComma;
	}
	public void setItemSumComma(String itemSumComma) {
		this.itemSumComma = itemSumComma;
	}
	public String getsComCode() {
		return sComCode;
	}
	public void setsComCode(String sComCode) {
		this.sComCode = sComCode;
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
	public String geteApprovalKey() {
		return eApprovalKey;
	}
	public void seteApprovalKey(String eApprovalKey) {
		this.eApprovalKey = eApprovalKey;
	}
	public String getkApprovalStaffKey() {
		return kApprovalStaffKey;
	}
	public void setkApprovalStaffKey(String kApprovalStaffKey) {
		this.kApprovalStaffKey = kApprovalStaffKey;
	}
	public String getkApprovalStatus() {
		return kApprovalStatus;
	}
	public void setkApprovalStatus(String kApprovalStatus) {
		this.kApprovalStatus = kApprovalStatus;
	}
	public String getkStaffSn() {
		return kStaffSn;
	}
	public void setkStaffSn(String kStaffSn) {
		this.kStaffSn = kStaffSn;
	}
	public String getkApprovalGubunKey() {
		return kApprovalGubunKey;
	}
	public void setkApprovalGubunKey(String kApprovalGubunKey) {
		this.kApprovalGubunKey = kApprovalGubunKey;
	}
	public String getkApprovalGubun() {
		return kApprovalGubun;
	}
	public void setkApprovalGubun(String kApprovalGubun) {
		this.kApprovalGubun = kApprovalGubun;
	}
	public String getsApprovalChk() {
		return sApprovalChk;
	}
	public void setsApprovalChk(String sApprovalChk) {
		this.sApprovalChk = sApprovalChk;
	}
	public String geteBuyreqApprovalStatus() {
		return eBuyreqApprovalStatus;
	}
	public void seteBuyreqApprovalStatus(String eBuyreqApprovalStatus) {
		this.eBuyreqApprovalStatus = eBuyreqApprovalStatus;
	}
	public String geteBuyreqApprovalReason() {
		return eBuyreqApprovalReason;
	}
	public void seteBuyreqApprovalReason(String eBuyreqApprovalReason) {
		this.eBuyreqApprovalReason = eBuyreqApprovalReason;
	}
	public String geteBuyreqApprovalCheck() {
		return eBuyreqApprovalCheck;
	}
	public void seteBuyreqApprovalCheck(String eBuyreqApprovalCheck) {
		this.eBuyreqApprovalCheck = eBuyreqApprovalCheck;
	}
	public String geteBuyreqStatusYCnt() {
		return eBuyreqStatusYCnt;
	}
	public void seteBuyreqStatusYCnt(String eBuyreqStatusYCnt) {
		this.eBuyreqStatusYCnt = eBuyreqStatusYCnt;
	}
	public String getFileGubun() {
		return fileGubun;
	}
	public void setFileGubun(String fileGubun) {
		this.fileGubun = fileGubun;
	}
	public String getItemGubunResult() {
		return itemGubunResult;
	}
	public void setItemGubunResult(String itemGubunResult) {
		this.itemGubunResult = itemGubunResult;
	}
	public String getBuyreqCnt() {
		return buyreqCnt;
	}
	public void setBuyreqCnt(String buyreqCnt) {
		this.buyreqCnt = buyreqCnt;
	}
	public String getApprovalStatusCnt() {
		return approvalStatusCnt;
	}
	public void setApprovalStatusCnt(String approvalStatusCnt) {
		this.approvalStatusCnt = approvalStatusCnt;
	}
	public String getItemCateKey() {
		return itemCateKey;
	}
	public void setItemCateKey(String itemCateKey) {
		this.itemCateKey = itemCateKey;
	}
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	public String geteUsePlace() {
		return eUsePlace;
	}
	public void seteUsePlace(String eUsePlace) {
		this.eUsePlace = eUsePlace;
	}
	public String geteBuyreqName() {
		return eBuyreqName;
	}
	public void seteBuyreqName(String eBuyreqName) {
		this.eBuyreqName = eBuyreqName;
	}
	public String geteBuyreqCode() {
		return eBuyreqCode;
	}
	public void seteBuyreqCode(String eBuyreqCode) {
		this.eBuyreqCode = eBuyreqCode;
	}
	public String geteBuyreqGubun() {
		return eBuyreqGubun;
	}
	public void seteBuyreqGubun(String eBuyreqGubun) {
		this.eBuyreqGubun = eBuyreqGubun;
	}
	public String geteBuyreqItemGubun() {
		return eBuyreqItemGubun;
	}
	public void seteBuyreqItemGubun(String eBuyreqItemGubun) {
		this.eBuyreqItemGubun = eBuyreqItemGubun;
	}
	public String geteBuyreqEtc() {
		return eBuyreqEtc;
	}
	public void seteBuyreqEtc(String eBuyreqEtc) {
		this.eBuyreqEtc = eBuyreqEtc;
	}
	public String geteBuyreqComKey() {
		return eBuyreqComKey;
	}
	public void seteBuyreqComKey(String eBuyreqComKey) {
		this.eBuyreqComKey = eBuyreqComKey;
	}
	public String geteBuyreqComName() {
		return eBuyreqComName;
	}
	public void seteBuyreqComName(String eBuyreqComName) {
		this.eBuyreqComName = eBuyreqComName;
	}
	public String geteBuyreqTotalPrice() {
		return eBuyreqTotalPrice;
	}
	public void seteBuyreqTotalPrice(String eBuyreqTotalPrice) {
		this.eBuyreqTotalPrice = eBuyreqTotalPrice;
	}
	public String geteBuyreqItemPkey() {
		return eBuyreqItemPkey;
	}
	public void seteBuyreqItemPkey(String eBuyreqItemPkey) {
		this.eBuyreqItemPkey = eBuyreqItemPkey;
	}
	public String geteBuyreqiPartno() {
		return eBuyreqiPartno;
	}
	public void seteBuyreqiPartno(String eBuyreqiPartno) {
		this.eBuyreqiPartno = eBuyreqiPartno;
	}
	public String geteBuyreqiModel() {
		return eBuyreqiModel;
	}
	public void seteBuyreqiModel(String eBuyreqiModel) {
		this.eBuyreqiModel = eBuyreqiModel;
	}
	public String geteBuyreqiSpec() {
		return eBuyreqiSpec;
	}
	public void seteBuyreqiSpec(String eBuyreqiSpec) {
		this.eBuyreqiSpec = eBuyreqiSpec;
	}
	public String geteBuyreqiCnt() {
		return eBuyreqiCnt;
	}
	public void seteBuyreqiCnt(String eBuyreqiCnt) {
		this.eBuyreqiCnt = eBuyreqiCnt;
	}
	public String geteBuyreqiUnitPrice() {
		return eBuyreqiUnitPrice;
	}
	public void seteBuyreqiUnitPrice(String eBuyreqiUnitPrice) {
		this.eBuyreqiUnitPrice = eBuyreqiUnitPrice;
	}
	public String geteBuyreqiVat() {
		return eBuyreqiVat;
	}
	public void seteBuyreqiVat(String eBuyreqiVat) {
		this.eBuyreqiVat = eBuyreqiVat;
	}
	public String geteBuyreqiVatGubun() {
		return eBuyreqiVatGubun;
	}
	public void seteBuyreqiVatGubun(String eBuyreqiVatGubun) {
		this.eBuyreqiVatGubun = eBuyreqiVatGubun;
	}
	public String geteBuyreqiRdate() {
		return eBuyreqiRdate;
	}
	public void seteBuyreqiRdate(String eBuyreqiRdate) {
		this.eBuyreqiRdate = eBuyreqiRdate;
	}
	public String geteBuyreqPriceGubun() {
		return eBuyreqPriceGubun;
	}
	public void seteBuyreqPriceGubun(String eBuyreqPriceGubun) {
		this.eBuyreqPriceGubun = eBuyreqPriceGubun;
	}
	public String geteBuyreqPrice() {
		return eBuyreqPrice;
	}
	public void seteBuyreqPrice(String eBuyreqPrice) {
		this.eBuyreqPrice = eBuyreqPrice;
	}
	public String geteBuyreqWdate() {
		return eBuyreqWdate;
	}
	public void seteBuyreqWdate(String eBuyreqWdate) {
		this.eBuyreqWdate = eBuyreqWdate;
	}
	public String geteBuyreqUdate() {
		return eBuyreqUdate;
	}
	public void seteBuyreqUdate(String eBuyreqUdate) {
		this.eBuyreqUdate = eBuyreqUdate;
	}
	public String getkApprovalStaffName() {
		return kApprovalStaffName;
	}
	public void setkApprovalStaffName(String kApprovalStaffName) {
		this.kApprovalStaffName = kApprovalStaffName;
	}
	public String geteFlag() {
		return eFlag;
	}
	public void seteFlag(String eFlag) {
		this.eFlag = eFlag;
	}
	public String geteBuyreqiPrice() {
		return eBuyreqiPrice;
	}
	public void seteBuyreqiPrice(String eBuyreqiPrice) {
		this.eBuyreqiPrice = eBuyreqiPrice;
	}
	public String getsComConKey() {
		return sComConKey;
	}
	public void setsComConKey(String sComConKey) {
		this.sComConKey = sComConKey;
	}
	public String getsComConName() {
		return sComConName;
	}
	public void setsComConName(String sComConName) {
		this.sComConName = sComConName;
	}
	public String getsComAddress1() {
		return sComAddress1;
	}
	public void setsComAddress1(String sComAddress1) {
		this.sComAddress1 = sComAddress1;
	}
	public String getsComConPhone2() {
		return sComConPhone2;
	}
	public void setsComConPhone2(String sComConPhone2) {
		this.sComConPhone2 = sComConPhone2;
	}
	public String getsComConEmail() {
		return sComConEmail;
	}
	public void setsComConEmail(String sComConEmail) {
		this.sComConEmail = sComConEmail;
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
	public String geteItemKey() {
		return eItemKey;
	}
	public void seteItemKey(String eItemKey) {
		this.eItemKey = eItemKey;
	}
	public String geteItemCnt() {
		return eItemCnt;
	}
	public void seteItemCnt(String eItemCnt) {
		this.eItemCnt = eItemCnt;
	}
	public String geteItemAmount() {
		return eItemAmount;
	}
	public void seteItemAmount(String eItemAmount) {
		this.eItemAmount = eItemAmount;
	}
	public String geteItemEtc() {
		return eItemEtc;
	}
	public void seteItemEtc(String eItemEtc) {
		this.eItemEtc = eItemEtc;
	}
	public String geteItemName() {
		return eItemName;
	}
	public void seteItemName(String eItemName) {
		this.eItemName = eItemName;
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
	public String geteItemPrice() {
		return eItemPrice;
	}
	public void seteItemPrice(String eItemPrice) {
		this.eItemPrice = eItemPrice;
	}
	public String geteBuyreqPosition() {
		return eBuyreqPosition;
	}
	public void seteBuyreqPosition(String eBuyreqPosition) {
		this.eBuyreqPosition = eBuyreqPosition;
	}
	public String geteBuyreqPositionName() {
		return eBuyreqPositionName;
	}
	public void seteBuyreqPositionName(String eBuyreqPositionName) {
		this.eBuyreqPositionName = eBuyreqPositionName;
	}
	public String geteBuyreqWriter() {
		return eBuyreqWriter;
	}
	public void seteBuyreqWriter(String eBuyreqWriter) {
		this.eBuyreqWriter = eBuyreqWriter;
	}
	public String geteBuyreqDate() {
		return eBuyreqDate;
	}
	public void seteBuyreqDate(String eBuyreqDate) {
		this.eBuyreqDate = eBuyreqDate;
	}
	public String getFinalAppStatus() {
		return finalAppStatus;
	}
	public void setFinalAppStatus(String finalAppStatus) {
		this.finalAppStatus = finalAppStatus;
	}
	public String geteBuyreqAppStatus() {
		return eBuyreqAppStatus;
	}
	public void seteBuyreqAppStatus(String eBuyreqAppStatus) {
		this.eBuyreqAppStatus = eBuyreqAppStatus;
	}
	public String geteBuyreqAppReason() {
		return eBuyreqAppReason;
	}
	public void seteBuyreqAppReason(String eBuyreqAppReason) {
		this.eBuyreqAppReason = eBuyreqAppReason;
	}
	public String geteSubaljuAppReason() {
		return eSubaljuAppReason;
	}
	public void seteSubaljuAppReason(String eSubaljuAppReason) {
		this.eSubaljuAppReason = eSubaljuAppReason;
	}
	public String geteSubaljuAppStatus() {
		return eSubaljuAppStatus;
	}
	public void seteSubaljuAppStatus(String eSubaljuAppStatus) {
		this.eSubaljuAppStatus = eSubaljuAppStatus;
	}
	public String getPriceGubun() {
		return priceGubun;
	}
	public void setPriceGubun(String priceGubun) {
		this.priceGubun = priceGubun;
	}
	public String getAppCnt() {
		return appCnt;
	}
	public void setAppCnt(String appCnt) {
		this.appCnt = appCnt;
	}
	public String geteBaljuCofirm() {
		return eBaljuCofirm;
	}
	public void seteBaljuCofirm(String eBaljuCofirm) {
		this.eBaljuCofirm = eBaljuCofirm;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getTypeGubun() {
		return typeGubun;
	}
	public void setTypeGubun(String typeGubun) {
		this.typeGubun = typeGubun;
	}
	public String geteEstimateReason() {
		return eEstimateReason;
	}
	public void seteEstimateReason(String eEstimateReason) {
		this.eEstimateReason = eEstimateReason;
	}
	public String geteEstimateKey() {
		return eEstimateKey;
	}
	public void seteEstimateKey(String eEstimateKey) {
		this.eEstimateKey = eEstimateKey;
	}
	public String geteEstimateRevisionNo() {
		return eEstimateRevisionNo;
	}
	public void seteEstimateRevisionNo(String eEstimateRevisionNo) {
		this.eEstimateRevisionNo = eEstimateRevisionNo;
	}
	public String geteEstimateRevisionDate() {
		return eEstimateRevisionDate;
	}
	public void seteEstimateRevisionDate(String eEstimateRevisionDate) {
		this.eEstimateRevisionDate = eEstimateRevisionDate;
	}
	public String geteEstimateRevisionReason() {
		return eEstimateRevisionReason;
	}
	public void seteEstimateRevisionReason(String eEstimateRevisionReason) {
		this.eEstimateRevisionReason = eEstimateRevisionReason;
	}
	public String geteEstimateNo() {
		return eEstimateNo;
	}
	public void seteEstimateNo(String eEstimateNo) {
		this.eEstimateNo = eEstimateNo;
	}
	public String geteEstimateDate() {
		return eEstimateDate;
	}
	public void seteEstimateDate(String eEstimateDate) {
		this.eEstimateDate = eEstimateDate;
	}
	public String geteEstimateCompany() {
		return eEstimateCompany;
	}
	public void seteEstimateCompany(String eEstimateCompany) {
		this.eEstimateCompany = eEstimateCompany;
	}
	public String geteEstimateCompanyName() {
		return eEstimateCompanyName;
	}
	public void seteEstimateCompanyName(String eEstimateCompanyName) {
		this.eEstimateCompanyName = eEstimateCompanyName;
	}
	public String geteEstimateCompanyReference() {
		return eEstimateCompanyReference;
	}
	public void seteEstimateCompanyReference(String eEstimateCompanyReference) {
		this.eEstimateCompanyReference = eEstimateCompanyReference;
	}
	public String geteEstimateName() {
		return eEstimateName;
	}
	public void seteEstimateName(String eEstimateName) {
		this.eEstimateName = eEstimateName;
	}
	public String geteEstimateDueDate() {
		return eEstimateDueDate;
	}
	public void seteEstimateDueDate(String eEstimateDueDate) {
		this.eEstimateDueDate = eEstimateDueDate;
	}
	public String geteEstimateValidDate() {
		return eEstimateValidDate;
	}
	public void seteEstimateValidDate(String eEstimateValidDate) {
		this.eEstimateValidDate = eEstimateValidDate;
	}
	public String geteEstimateDeliveryKey() {
		return eEstimateDeliveryKey;
	}
	public void seteEstimateDeliveryKey(String eEstimateDeliveryKey) {
		this.eEstimateDeliveryKey = eEstimateDeliveryKey;
	}
	public String geteEstimateDeliveryName() {
		return eEstimateDeliveryName;
	}
	public void seteEstimateDeliveryName(String eEstimateDeliveryName) {
		this.eEstimateDeliveryName = eEstimateDeliveryName;
	}
	public String geteEstimateDeliveryPost() {
		return eEstimateDeliveryPost;
	}
	public void seteEstimateDeliveryPost(String eEstimateDeliveryPost) {
		this.eEstimateDeliveryPost = eEstimateDeliveryPost;
	}
	public String geteEstimateDeliveryAddr() {
		return eEstimateDeliveryAddr;
	}
	public void seteEstimateDeliveryAddr(String eEstimateDeliveryAddr) {
		this.eEstimateDeliveryAddr = eEstimateDeliveryAddr;
	}
	public String geteEstimateDeliveryAddr2() {
		return eEstimateDeliveryAddr2;
	}
	public void seteEstimateDeliveryAddr2(String eEstimateDeliveryAddr2) {
		this.eEstimateDeliveryAddr2 = eEstimateDeliveryAddr2;
	}
	public String geteEstimatePaymentKey() {
		return eEstimatePaymentKey;
	}
	public void seteEstimatePaymentKey(String eEstimatePaymentKey) {
		this.eEstimatePaymentKey = eEstimatePaymentKey;
	}
	public String geteEstimatePaymentName() {
		return eEstimatePaymentName;
	}
	public void seteEstimatePaymentName(String eEstimatePaymentName) {
		this.eEstimatePaymentName = eEstimatePaymentName;
	}
	public String geteEstimateSubTotal() {
		return eEstimateSubTotal;
	}
	public void seteEstimateSubTotal(String eEstimateSubTotal) {
		this.eEstimateSubTotal = eEstimateSubTotal;
	}
	public String geteEstimateSubTwoTotal() {
		return eEstimateSubTwoTotal;
	}
	public void seteEstimateSubTwoTotal(String eEstimateSubTwoTotal) {
		this.eEstimateSubTwoTotal = eEstimateSubTwoTotal;
	}
	public String geteEstimateTotalPayment() {
		return eEstimateTotalPayment;
	}
	public void seteEstimateTotalPayment(String eEstimateTotalPayment) {
		this.eEstimateTotalPayment = eEstimateTotalPayment;
	}
	public String geteEstimateTotalSale() {
		return eEstimateTotalSale;
	}
	public void seteEstimateTotalSale(String eEstimateTotalSale) {
		this.eEstimateTotalSale = eEstimateTotalSale;
	}
	public String geteEstimateWdate() {
		return eEstimateWdate;
	}
	public void seteEstimateWdate(String eEstimateWdate) {
		this.eEstimateWdate = eEstimateWdate;
	}
	public String geteEstimateUdate() {
		return eEstimateUdate;
	}
	public void seteEstimateUdate(String eEstimateUdate) {
		this.eEstimateUdate = eEstimateUdate;
	}
	public String geteEstimateProTotalPayment() {
		return eEstimateProTotalPayment;
	}
	public void seteEstimateProTotalPayment(String eEstimateProTotalPayment) {
		this.eEstimateProTotalPayment = eEstimateProTotalPayment;
	}
	public String geteEstimateProEtc() {
		return eEstimateProEtc;
	}
	public void seteEstimateProEtc(String eEstimateProEtc) {
		this.eEstimateProEtc = eEstimateProEtc;
	}
	public String geteEstimatePlaTotalPayment() {
		return eEstimatePlaTotalPayment;
	}
	public void seteEstimatePlaTotalPayment(String eEstimatePlaTotalPayment) {
		this.eEstimatePlaTotalPayment = eEstimatePlaTotalPayment;
	}
	public String geteEstimatePalteEtc() {
		return eEstimatePalteEtc;
	}
	public void seteEstimatePalteEtc(String eEstimatePalteEtc) {
		this.eEstimatePalteEtc = eEstimatePalteEtc;
	}
	public String geteEstimateFabTotalPayment() {
		return eEstimateFabTotalPayment;
	}
	public void seteEstimateFabTotalPayment(String eEstimateFabTotalPayment) {
		this.eEstimateFabTotalPayment = eEstimateFabTotalPayment;
	}
	public String geteEstimateFabEtc() {
		return eEstimateFabEtc;
	}
	public void seteEstimateFabEtc(String eEstimateFabEtc) {
		this.eEstimateFabEtc = eEstimateFabEtc;
	}
	public String geteEstimateOtherTotalPayment() {
		return eEstimateOtherTotalPayment;
	}
	public void seteEstimateOtherTotalPayment(String eEstimateOtherTotalPayment) {
		this.eEstimateOtherTotalPayment = eEstimateOtherTotalPayment;
	}
	public String geteEstimateOtherEtc() {
		return eEstimateOtherEtc;
	}
	public void seteEstimateOtherEtc(String eEstimateOtherEtc) {
		this.eEstimateOtherEtc = eEstimateOtherEtc;
	}
	public String geteEstimateOriginalKey() {
		return eEstimateOriginalKey;
	}
	public void seteEstimateOriginalKey(String eEstimateOriginalKey) {
		this.eEstimateOriginalKey = eEstimateOriginalKey;
	}
	public String geteEstimateStatus() {
		return eEstimateStatus;
	}
	public void seteEstimateStatus(String eEstimateStatus) {
		this.eEstimateStatus = eEstimateStatus;
	}
	public String geteEstimateProPurchaseTotalPayment() {
		return eEstimateProPurchaseTotalPayment;
	}
	public void seteEstimateProPurchaseTotalPayment(String eEstimateProPurchaseTotalPayment) {
		this.eEstimateProPurchaseTotalPayment = eEstimateProPurchaseTotalPayment;
	}
	public String getItemByEestimate() {
		return itemByEestimate;
	}
	public void setItemByEestimate(String itemByEestimate) {
		this.itemByEestimate = itemByEestimate;
	}
	public String getItemByEestimateTwo() {
		return itemByEestimateTwo;
	}
	public void setItemByEestimateTwo(String itemByEestimateTwo) {
		this.itemByEestimateTwo = itemByEestimateTwo;
	}
	public String geteEstimateTotalPaymentTwo() {
		return eEstimateTotalPaymentTwo;
	}
	public void seteEstimateTotalPaymentTwo(String eEstimateTotalPaymentTwo) {
		this.eEstimateTotalPaymentTwo = eEstimateTotalPaymentTwo;
	}
	public String geteBaljuApprovalReason() {
		return eBaljuApprovalReason;
	}
	public void seteBaljuApprovalReason(String eBaljuApprovalReason) {
		this.eBaljuApprovalReason = eBaljuApprovalReason;
	}
	public String geteBaljuRemark() {
		return eBaljuRemark;
	}
	public void seteBaljuRemark(String eBaljuRemark) {
		this.eBaljuRemark = eBaljuRemark;
	}
	public String geteApprovalChk() {
		return eApprovalChk;
	}
	public void seteApprovalChk(String eApprovalChk) {
		this.eApprovalChk = eApprovalChk;
	}
	public String getsComKey() {
		return sComKey;
	}
	public void setsComKey(String sComKey) {
		this.sComKey = sComKey;
	}
	public String getsComRef() {
		return sComRef;
	}
	public void setsComRef(String sComRef) {
		this.sComRef = sComRef;
	}
	public String geteBaljuPaymentdate() {
		return eBaljuPaymentdate;
	}
	public void seteBaljuPaymentdate(String eBaljuPaymentdate) {
		this.eBaljuPaymentdate = eBaljuPaymentdate;
	}
	public String getsComAddr() {
		return sComAddr;
	}
	public void setsComAddr(String sComAddr) {
		this.sComAddr = sComAddr;
	}
	public String getsComPhone1() {
		return sComPhone1;
	}
	public void setsComPhone1(String sComPhone1) {
		this.sComPhone1 = sComPhone1;
	}
	public String getsComPhone2() {
		return sComPhone2;
	}
	public void setsComPhone2(String sComPhone2) {
		this.sComPhone2 = sComPhone2;
	}
	public String getsComPhone3() {
		return sComPhone3;
	}
	public void setsComPhone3(String sComPhone3) {
		this.sComPhone3 = sComPhone3;
	}
	public String getsComFax1() {
		return sComFax1;
	}
	public void setsComFax1(String sComFax1) {
		this.sComFax1 = sComFax1;
	}
	public String getsComFax2() {
		return sComFax2;
	}
	public void setsComFax2(String sComFax2) {
		this.sComFax2 = sComFax2;
	}
	public String getsComFax3() {
		return sComFax3;
	}
	public void setsComFax3(String sComFax3) {
		this.sComFax3 = sComFax3;
	}
	public String geteBaljuFileName() {
		return eBaljuFileName;
	}
	public void seteBaljuFileName(String eBaljuFileName) {
		this.eBaljuFileName = eBaljuFileName;
	}
	public String geteBaljuTotalAmt() {
		return eBaljuTotalAmt;
	}
	public void seteBaljuTotalAmt(String eBaljuTotalAmt) {
		this.eBaljuTotalAmt = eBaljuTotalAmt;
	}
	public String geteBaljuTotalVat() {
		return eBaljuTotalVat;
	}
	public void seteBaljuTotalVat(String eBaljuTotalVat) {
		this.eBaljuTotalVat = eBaljuTotalVat;
	}
	public String geteBaljuConfirm() {
		return eBaljuConfirm;
	}
	public void seteBaljuConfirm(String eBaljuConfirm) {
		this.eBaljuConfirm = eBaljuConfirm;
	}
	public String geteBaljuMoneyTypeKey() {
		return eBaljuMoneyTypeKey;
	}
	public void seteBaljuMoneyTypeKey(String eBaljuMoneyTypeKey) {
		this.eBaljuMoneyTypeKey = eBaljuMoneyTypeKey;
	}
	public String getAppStaffKey() {
		return appStaffKey;
	}
	public void setAppStaffKey(String appStaffKey) {
		this.appStaffKey = appStaffKey;
	}
	public String getTotalStatus() {
		return totalStatus;
	}
	public void setTotalStatus(String totalStatus) {
		this.totalStatus = totalStatus;
	}
	public String getApprovalKey() {
		return approvalKey;
	}
	public void setApprovalKey(String approvalKey) {
		this.approvalKey = approvalKey;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public String getGubunNumber() {
		return gubunNumber;
	}
	public void setGubunNumber(String gubunNumber) {
		this.gubunNumber = gubunNumber;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
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
	public String getPkKey() {
		return pkKey;
	}
	public void setPkKey(String pkKey) {
		this.pkKey = pkKey;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getsComName() {
		return sComName;
	}
	public void setsComName(String sComName) {
		this.sComName = sComName;
	}
	public String getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getwDate() {
		return wDate;
	}
	public void setwDate(String wDate) {
		this.wDate = wDate;
	}
	public String getkStaffKey() {
		return kStaffKey;
	}
	public void setkStaffKey(String kStaffKey) {
		this.kStaffKey = kStaffKey;
	}
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}
	public String getkStaffName() {
		return kStaffName;
	}
	public void setkStaffName(String kStaffName) {
		this.kStaffName = kStaffName;
	}
	public String getkStaffName2() {
		return kStaffName2;
	}
	public void setkStaffName2(String kStaffName2) {
		this.kStaffName2 = kStaffName2;
	}
	
}
