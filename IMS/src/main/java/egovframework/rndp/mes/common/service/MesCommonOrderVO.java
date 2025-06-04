package egovframework.rndp.mes.common.service;

import egovframework.rndp.com.cmm.service.DefultVO;
import egovframework.rndp.com.utl.EgovStringUtil;

public class MesCommonOrderVO extends DefultVO{
	
	private String searchWord 						= "";
	private String searchType   					= ""; 
	private String topStartDate   					= ""; 
	private String topEndDate     					= ""; 	
	private String kStaffKey = "";
	private String kStaffName = "";
	private String eOutKey = "";
	private String eOutGubun = "";
	private String sItemSalePrice = "";
	private String eOutBarcode = "";
	private String eBuyIpgoItemPlaceKey = "";
	private String ePlaceKey = "";
//	
	private String eOrderKey = "";
	private String eOrderCnt = "";
	private String eOrderNum = "";
	private String eOrderWdate = "";
	private String eOrderUdate = "";
	private String eOrderDate= "";
	private String eOrderPdate = "";
	private String sComKey = "";
	private String sComName = "";
	private String eOrderPhone = "";
	private String eOrderAddress = "";
	private String eOrderAddress1 = "";
	private String eOrderAddress2 = "";
	private String eOrderPost = "";
	private String eOrderEtc = "";
	private String eOrderSend = "";
	private String eFlag = "";
	private String eItemCntTot = "";
	private String eItemKgTot = "";
	
	private String eOrderItemKey = "";
	private String eItemKey = "";
	private String eItemCnt = "";
	private String eItemEtc = "";
	private String eItemName = "";
	private String eJeagoCnt = "";
	private String eOrderSendName = "";
	private String eChulgoGubun = "";
	private String eChulgoDate = "";
	private String pageGubun = "";
	private String eOrderPdateGubun = "";
	private String eDiffPdate = "";
	private String eOrderSendPrice = "";
	private String eItemPriceTot = "";
	private String eItemPrice = "";
	private String eMatePrice = "";
	private String ltGubun = "";
	private String ASGubun = "";
	private String eASKindKey = "";
	private String eApprovalChk = "";
	private String eOrderApprovalStatus = "";
	private String kApprovalStaffKey = "";
	private String kPositionKey = "";
	private String kPositionName = "";
	private String kClassKey = "";
	private String kClassName = "";
	private String eApprovalGubun = "";
	private String kApprovalStaffName = "";
	
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
	public String geteYYCnt() {
		return eYYCnt;
	}
	public void seteYYCnt(String eYYCnt) {
		this.eYYCnt = eYYCnt;
	}
	public String geteItemPdate() {
		return eItemPdate;
	}
	public void seteItemPdate(String eItemPdate) {
		this.eItemPdate = eItemPdate;
	}
	public String getSearchType2() {
		return searchType2;
	}
	public void setSearchType2(String searchType2) {
		this.searchType2 = searchType2;
	}
	public String geteReplaceBarcode() {
		return eReplaceBarcode;
	}
	public void seteReplaceBarcode(String eReplaceBarcode) {
		this.eReplaceBarcode = eReplaceBarcode;
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
	public String getePassGubun() {
		return ePassGubun;
	}
	public void setePassGubun(String ePassGubun) {
		this.ePassGubun = ePassGubun;
	}
	public String getePlaceKey() {
		return ePlaceKey;
	}
	public void setePlaceKey(String ePlaceKey) {
		this.ePlaceKey = ePlaceKey;
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
	public String geteItemCode() {
		return eItemCode;
	}
	public void seteItemCode(String eItemCode) {
		this.eItemCode = eItemCode;
	}
	public String geteOrderLine() {
		return eOrderLine;
	}
	public void seteOrderLine(String eOrderLine) {
		this.eOrderLine = eOrderLine;
	}
	public String geteItemPartNo() {
		return eItemPartNo;
	}
	public void seteItemPartNo(String eItemPartNo) {
		this.eItemPartNo = eItemPartNo;
	}
	public String getsItemRev() {
		return sItemRev;
	}
	public void setsItemRev(String sItemRev) {
		this.sItemRev = sItemRev;
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
	public String getsItemCateKey() {
		return sItemCateKey;
	}
	public void setsItemCateKey(String sItemCateKey) {
		this.sItemCateKey = sItemCateKey;
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
	public String geteOrderFileBefore() {
		return eOrderFileBefore;
	}
	public void seteOrderFileBefore(String eOrderFileBefore) {
		this.eOrderFileBefore = eOrderFileBefore;
	}
	public String geteOrderFileGubun() {
		return eOrderFileGubun;
	}
	public void seteOrderFileGubun(String eOrderFileGubun) {
		this.eOrderFileGubun = eOrderFileGubun;
	}
	public String geteUnitPrice() {
		return eUnitPrice;
	}
	public void seteUnitPrice(String eUnitPrice) {
		this.eUnitPrice = eUnitPrice;
	}
	public String geteInvenCnt() {
		return eInvenCnt;
	}
	public void seteInvenCnt(String eInvenCnt) {
		this.eInvenCnt = eInvenCnt;
	}
	public String geteOrderCountry() {
		return eOrderCountry;
	}
	public void seteOrderCountry(String eOrderCountry) {
		this.eOrderCountry = eOrderCountry;
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
	public String geteItemComPartNo() {
		return eItemComPartNo;
	}
	public void seteItemComPartNo(String eItemComPartNo) {
		this.eItemComPartNo = eItemComPartNo;
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
	public String getsItemPrice() {
		return sItemPrice;
	}
	public void setsItemPrice(String sItemPrice) {
		this.sItemPrice = sItemPrice;
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
	public String geteChulgoDate2() {
		return eChulgoDate2;
	}
	public void seteChulgoDate2(String eChulgoDate2) {
		this.eChulgoDate2 = eChulgoDate2;
	}
	public String geteTaxGubun() {
		return eTaxGubun;
	}
	public void seteTaxGubun(String eTaxGubun) {
		this.eTaxGubun = eTaxGubun;
	}
	public String getsItemSalePrice() {
		return sItemSalePrice;
	}
	public void setsItemSalePrice(String sItemSalePrice) {
		this.sItemSalePrice = sItemSalePrice;
	}
	public String geteASItemKey() {
		return eASItemKey;
	}
	public void seteASItemKey(String eASItemKey) {
		this.eASItemKey = eASItemKey;
	}
	public String geteGubun() {
		return eGubun;
	}
	public void seteGubun(String eGubun) {
		this.eGubun = eGubun;
	}
	public String geteItemKey2() {
		return eItemKey2;
	}
	public void seteItemKey2(String eItemKey2) {
		this.eItemKey2 = eItemKey2;
	}
	public String geteItemName2() {
		return EgovStringUtil.getHtmlStrCnvr(eItemName2);
	}
	public void seteItemName2(String eItemName2) {
		this.eItemName2 = eItemName2;
	}
	public String geteItemPlace() {
		return eItemPlace;
	}
	public void seteItemPlace(String eItemPlace) {
		this.eItemPlace = eItemPlace;
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
	public String geteItemKgTot() {
		return eItemKgTot;
	}
	public void seteItemKgTot(String eItemKgTot) {
		this.eItemKgTot = eItemKgTot;
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
	public String getsItemUnit() {
		return sItemUnit;
	}
	public void setsItemUnit(String sItemUnit) {
		this.sItemUnit = sItemUnit;
	}
	public String geteOrderCnt() {
		return eOrderCnt;
	}
	public void seteOrderCnt(String eOrderCnt) {
		this.eOrderCnt = eOrderCnt;
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
	public String geteOrderApprovalReason() {
		return eOrderApprovalReason;
	}
	public void seteOrderApprovalReason(String eOrderApprovalReason) {
		this.eOrderApprovalReason = eOrderApprovalReason;
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
	public String geteOrderStatusYCnt() {
		return eOrderStatusYCnt;
	}
	public void seteOrderStatusYCnt(String eOrderStatusYCnt) {
		this.eOrderStatusYCnt = eOrderStatusYCnt;
	}
	public String getkApprovalStaffName() {
		return kApprovalStaffName;
	}
	public void setkApprovalStaffName(String kApprovalStaffName) {
		this.kApprovalStaffName = kApprovalStaffName;
	}
	public String geteApprovalGubun() {
		return eApprovalGubun;
	}
	public void seteApprovalGubun(String eApprovalGubun) {
		this.eApprovalGubun = eApprovalGubun;
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
	public String geteOrderApprovalStatus() {
		return eOrderApprovalStatus;
	}
	public void seteOrderApprovalStatus(String eOrderApprovalStatus) {
		this.eOrderApprovalStatus = eOrderApprovalStatus;
	}
	public String geteApprovalChk() {
		return eApprovalChk;
	}
	public void seteApprovalChk(String eApprovalChk) {
		this.eApprovalChk = eApprovalChk;
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
	public String getLtGubun() {
		return ltGubun;
	}
	public void setLtGubun(String ltGubun) {
		this.ltGubun = ltGubun;
	}
	public String geteMatePrice() {
		return eMatePrice;
	}
	public void seteMatePrice(String eMatePrice) {
		this.eMatePrice = eMatePrice;
	}
	public String geteItemPrice() {
		return eItemPrice;
	}
	public void seteItemPrice(String eItemPrice) {
		this.eItemPrice = eItemPrice;
	}
	public String geteItemPriceTot() {
		return eItemPriceTot;
	}
	public void seteItemPriceTot(String eItemPriceTot) {
		this.eItemPriceTot = eItemPriceTot;
	}
	public String geteOrderSendPrice() {
		return eOrderSendPrice;
	}
	public void seteOrderSendPrice(String eOrderSendPrice) {
		this.eOrderSendPrice = eOrderSendPrice;
	}
	public String geteDiffPdate() {
		return eDiffPdate;
	}
	public void seteDiffPdate(String eDiffPdate) {
		this.eDiffPdate = eDiffPdate;
	}
	public String geteOrderPdateGubun() {
		return eOrderPdateGubun;
	}
	public void seteOrderPdateGubun(String eOrderPdateGubun) {
		this.eOrderPdateGubun = eOrderPdateGubun;
	}
	public String getPageGubun() {
		return pageGubun;
	}
	public void setPageGubun(String pageGubun) {
		this.pageGubun = pageGubun;
	}
	public String geteChulgoDate() {
		return eChulgoDate;
	}
	public void seteChulgoDate(String eChulgoDate) {
		this.eChulgoDate = eChulgoDate;
	}
	public String geteChulgoGubun() {
		return eChulgoGubun;
	}
	public void seteChulgoGubun(String eChulgoGubun) {
		this.eChulgoGubun = eChulgoGubun;
	}
	public String geteOrderSendName() {
		return eOrderSendName;
	}
	public void seteOrderSendName(String eOrderSendName) {
		this.eOrderSendName = eOrderSendName;
	}
	public String geteItemName() {
		return EgovStringUtil.getHtmlStrCnvr(eItemName);
	}
	public void seteItemName(String eItemName) {
		this.eItemName = eItemName;
	}
	public String geteJeagoCnt() {
		return eJeagoCnt;
	}
	public void seteJeagoCnt(String eJeagoCnt) {
		this.eJeagoCnt = eJeagoCnt;
	}
	public String geteItemCntTot() {
		return eItemCntTot;
	}
	public void seteItemCntTot(String eItemCntTot) {
		this.eItemCntTot = eItemCntTot;
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
	public String geteOrderItemKey() {
		return eOrderItemKey;
	}
	public void seteOrderItemKey(String eOrderItemKey) {
		this.eOrderItemKey = eOrderItemKey;
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
	public String geteItemEtc() {
		return eItemEtc;
	}
	public void seteItemEtc(String eItemEtc) {
		this.eItemEtc = eItemEtc;
	}
	public String geteOrderKey() {
		return eOrderKey;
	}
	public void seteOrderKey(String eOrderKey) {
		this.eOrderKey = eOrderKey;
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
	public String geteFlag() {
		return eFlag;
	}
	public void seteFlag(String eFlag) {
		this.eFlag = eFlag;
	}
	public String getSearchWord() {
		return EgovStringUtil.getHtmlStrCnvr(searchWord);
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
