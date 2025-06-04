package egovframework.rndp.mes.maintance.service;

import egovframework.rndp.com.cmm.service.DefultVO;
import egovframework.rndp.com.utl.EgovStringUtil;

public class MesMaintanceVO extends DefultVO{
	
	// 안상현
	private String searchWord						= "";
	private String searchType						= "";
	private String kStaffKey						= "";
	private String kStaffName						= "";
	private String topStartDate						= "";
	private String topEndDate						= "";
	
	
	// 추가된 검색조건
	private String dateType							= "요청일자";
	private String mainTanceType					= "";
	

	//요청
	private String mMaintanceKey					= "";
	private String mMaintanceCateKey				= "";
	private String mMaintanceCateName				= "";
	private String mMaintanceCatePath				= "";
	private String mMaintanceStaffKey				= "";
	private String mMaintanceStaffName				= "";
	private String mMaintanceType					= "";
	private String mMaintanceIssueType				= "";
	private String mMaintanceDate					= "";
	
	private String mMaintanceModel					= "";
	private String mMaintanceSerial					= "";
	private String mMaintanceContent				= "";
	private String mMaintanceFile					= "";
	
	//처리
	private String mProcessType						= "";
	private String mProcessStaffKey					= "";
	private String mProcessStaffName				= "";
	private String mProcessResult					= "";
	private String mProcessDate						= "";
	private String mProcessContent					= "";
	private String mProcessFile						= "";
	
	// 결재관련
	private String sSignKey							= "";
	private String sSignStatus						= "";
	private String sSignDecison						= "";
	private String sSignContent						= "";
	private String sSignStaffKey					= "";
	private String sSignStaffName					= "";
	private String sSignStaffPosition					= "";
	
	// 경로
	private String kPositionKey					= "";
	private String kPositionName				= "";
	private String kPositionPath				= "";
	private String kPositionUpKey				= "0";
	private String kPositionUpKeySave			= "0";
	private String gubun			= "";
	private String oSignPass			= "";
	
	private String eFileNum		=	"";
	private String eAddFileId	=	"";
	private String atchFileName	=	"";
	private String eIMGregName	=	"";
	private String eGubunPage	=	"";
	
	private String mMaintanceNumber	=	""; //관리번호
	private String mMaintanceRequester	=	"";//요청자
	
	private String mAuthor	=	"";//작성자
	private String eMaintanceSelect1	=	"";//부서1
	private String eMaintanceSelect2	=	"";//부서2
	private String eMaintanceSelect3	=	"";//부서3
	private String eMaintanceSelect4	=	"";//부서4
	private String mHandler	=	"";//처리담당자
	private String mRequestDate	=	"";//요청일자
	private String mProcessingDate	=	"";//처리일자

	private String mIssueTypeName	=	"";//처리일자
	private String eAssetTypeName	=	"";//처리일자
	private String eAssetType	=	"";//처리일자
	private String mMaintanceTypeName	=	"";//처리일자
	private String mRequester	=	"";
	private String ePageInfo	=	"";
	private String viewGubun	=	"";
	private String eAssetKey	=	"";
	private String eRowMaintanceKey	=	"";
	
	
	
	
	//검색 조건
	private String eSearchWordA = "";
	private String eSearchWordB = "";
	private String eSearchWordC = "";
	private String eSearchWordD = "";
	private String eSearchWordE = "";
	private String eSearchWordF = "";
	private String eSearchWordG = "";
	private String eSearchWordH = "";
	private String eSearchWordI = "";
	private String eSearchWordJ = "";
	private String eSearchWordK = "";
	private String eSearchWordL = "";
	private String eSearchWordM = "";
	private String eSearchWordN = "";
	private String eSearchWordO = "";
	private String eSearchWordP = "";
	private String eSearchWordQ = "";
	private String eSearchWordR = "";
	private String eSearchWordS = "";
	private String eSearchWordT = "";
	private String eSearchWordU = "";
	private String eSearchWordV = "";
	private String eSearchWordW = "";
	private String eSearchWordX = "";
	private String eSearchWordY = "";
	private String eSearchWordZ = "";
	
	
	
	public String geteAssetKey() {
		return eAssetKey;
	}
	public void seteAssetKey(String eAssetKey) {
		this.eAssetKey = eAssetKey;
	}
	public String geteRowMaintanceKey() {
		return eRowMaintanceKey;
	}
	public void seteRowMaintanceKey(String eRowMaintanceKey) {
		this.eRowMaintanceKey = eRowMaintanceKey;
	}
	public String getViewGubun() {
		return viewGubun;
	}
	public void setViewGubun(String viewGubun) {
		this.viewGubun = viewGubun;
	}
	public String geteSearchWordA() {
		return eSearchWordA;
	}
	public void seteSearchWordA(String eSearchWordA) {
		this.eSearchWordA = eSearchWordA;
	}
	public String geteSearchWordB() {
		return eSearchWordB;
	}
	public void seteSearchWordB(String eSearchWordB) {
		this.eSearchWordB = eSearchWordB;
	}
	public String geteSearchWordC() {
		return eSearchWordC;
	}
	public void seteSearchWordC(String eSearchWordC) {
		this.eSearchWordC = eSearchWordC;
	}
	public String geteSearchWordD() {
		return eSearchWordD;
	}
	public void seteSearchWordD(String eSearchWordD) {
		this.eSearchWordD = eSearchWordD;
	}
	public String geteSearchWordE() {
		return eSearchWordE;
	}
	public void seteSearchWordE(String eSearchWordE) {
		this.eSearchWordE = eSearchWordE;
	}
	public String geteSearchWordF() {
		return eSearchWordF;
	}
	public void seteSearchWordF(String eSearchWordF) {
		this.eSearchWordF = eSearchWordF;
	}
	public String geteSearchWordG() {
		return eSearchWordG;
	}
	public void seteSearchWordG(String eSearchWordG) {
		this.eSearchWordG = eSearchWordG;
	}
	public String geteSearchWordH() {
		return eSearchWordH;
	}
	public void seteSearchWordH(String eSearchWordH) {
		this.eSearchWordH = eSearchWordH;
	}
	public String geteSearchWordI() {
		return eSearchWordI;
	}
	public void seteSearchWordI(String eSearchWordI) {
		this.eSearchWordI = eSearchWordI;
	}
	public String geteSearchWordJ() {
		return eSearchWordJ;
	}
	public void seteSearchWordJ(String eSearchWordJ) {
		this.eSearchWordJ = eSearchWordJ;
	}
	public String geteSearchWordK() {
		return eSearchWordK;
	}
	public void seteSearchWordK(String eSearchWordK) {
		this.eSearchWordK = eSearchWordK;
	}
	public String geteSearchWordL() {
		return eSearchWordL;
	}
	public void seteSearchWordL(String eSearchWordL) {
		this.eSearchWordL = eSearchWordL;
	}
	public String geteSearchWordM() {
		return eSearchWordM;
	}
	public void seteSearchWordM(String eSearchWordM) {
		this.eSearchWordM = eSearchWordM;
	}
	public String geteSearchWordN() {
		return eSearchWordN;
	}
	public void seteSearchWordN(String eSearchWordN) {
		this.eSearchWordN = eSearchWordN;
	}
	public String geteSearchWordO() {
		return eSearchWordO;
	}
	public void seteSearchWordO(String eSearchWordO) {
		this.eSearchWordO = eSearchWordO;
	}
	public String geteSearchWordP() {
		return eSearchWordP;
	}
	public void seteSearchWordP(String eSearchWordP) {
		this.eSearchWordP = eSearchWordP;
	}
	public String geteSearchWordQ() {
		return eSearchWordQ;
	}
	public void seteSearchWordQ(String eSearchWordQ) {
		this.eSearchWordQ = eSearchWordQ;
	}
	public String geteSearchWordR() {
		return eSearchWordR;
	}
	public void seteSearchWordR(String eSearchWordR) {
		this.eSearchWordR = eSearchWordR;
	}
	public String geteSearchWordS() {
		return eSearchWordS;
	}
	public void seteSearchWordS(String eSearchWordS) {
		this.eSearchWordS = eSearchWordS;
	}
	public String geteSearchWordT() {
		return eSearchWordT;
	}
	public void seteSearchWordT(String eSearchWordT) {
		this.eSearchWordT = eSearchWordT;
	}
	public String geteSearchWordU() {
		return eSearchWordU;
	}
	public void seteSearchWordU(String eSearchWordU) {
		this.eSearchWordU = eSearchWordU;
	}
	public String geteSearchWordV() {
		return eSearchWordV;
	}
	public void seteSearchWordV(String eSearchWordV) {
		this.eSearchWordV = eSearchWordV;
	}
	public String geteSearchWordW() {
		return eSearchWordW;
	}
	public void seteSearchWordW(String eSearchWordW) {
		this.eSearchWordW = eSearchWordW;
	}
	public String geteSearchWordX() {
		return eSearchWordX;
	}
	public void seteSearchWordX(String eSearchWordX) {
		this.eSearchWordX = eSearchWordX;
	}
	public String geteSearchWordY() {
		return eSearchWordY;
	}
	public void seteSearchWordY(String eSearchWordY) {
		this.eSearchWordY = eSearchWordY;
	}
	public String geteSearchWordZ() {
		return eSearchWordZ;
	}
	public void seteSearchWordZ(String eSearchWordZ) {
		this.eSearchWordZ = eSearchWordZ;
	}
	public String getePageInfo() {
		return ePageInfo;
	}
	public void setePageInfo(String ePageInfo) {
		this.ePageInfo = ePageInfo;
	}
	public String getmRequester() {
		return mRequester;
	}
	public void setmRequester(String mRequester) {
		this.mRequester = mRequester;
	}
	public String getmIssueTypeName() {
		return mIssueTypeName;
	}
	public void setmIssueTypeName(String mIssueTypeName) {
		this.mIssueTypeName = mIssueTypeName;
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
	public String getmMaintanceTypeName() {
		return mMaintanceTypeName;
	}
	public void setmMaintanceTypeName(String mMaintanceTypeName) {
		this.mMaintanceTypeName = mMaintanceTypeName;
	}
	public String getmAuthor() {
		return mAuthor;
	}
	public void setmAuthor(String mAuthor) {
		this.mAuthor = mAuthor;
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
	public String getmHandler() {
		return mHandler;
	}
	public void setmHandler(String mHandler) {
		this.mHandler = mHandler;
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
	public String getmMaintanceRequester() {
		return mMaintanceRequester;
	}
	public void setmMaintanceRequester(String mMaintanceRequester) {
		this.mMaintanceRequester = mMaintanceRequester;
	}
	public String getmMaintanceNumber() {
		return mMaintanceNumber;
	}
	public void setmMaintanceNumber(String mMaintanceNumber) {
		this.mMaintanceNumber = mMaintanceNumber;
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
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}
	public String getsSignKey() {
		return sSignKey;
	}
	public void setsSignKey(String sSignKey) {
		this.sSignKey = sSignKey;
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
	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	public String getMainTanceType() {
		return mainTanceType;
	}
	public void setMainTanceType(String mainTanceType) {
		this.mainTanceType = mainTanceType;
	}
	public String getmMaintanceKey() {
		return mMaintanceKey;
	}
	public void setmMaintanceKey(String mMaintanceKey) {
		this.mMaintanceKey = mMaintanceKey;
	}
	public String getmMaintanceCateKey() {
		return mMaintanceCateKey;
	}
	public void setmMaintanceCateKey(String mMaintanceCateKey) {
		this.mMaintanceCateKey = mMaintanceCateKey;
	}
	public String getmMaintanceCateName() {
		return mMaintanceCateName;
	}
	public void setmMaintanceCateName(String mMaintanceCateName) {
		this.mMaintanceCateName = mMaintanceCateName;
	}
	public String getmMaintanceCatePath() {
		return mMaintanceCatePath;
	}
	public void setmMaintanceCatePath(String mMaintanceCatePath) {
		this.mMaintanceCatePath = mMaintanceCatePath;
	}
	public String getmMaintanceStaffKey() {
		return mMaintanceStaffKey;
	}
	public void setmMaintanceStaffKey(String mMaintanceStaffKey) {
		this.mMaintanceStaffKey = mMaintanceStaffKey;
	}
	public String getmMaintanceStaffName() {
		return mMaintanceStaffName;
	}
	public void setmMaintanceStaffName(String mMaintanceStaffName) {
		this.mMaintanceStaffName = mMaintanceStaffName;
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
	public String getmMaintanceFile() {
		return mMaintanceFile;
	}
	public void setmMaintanceFile(String mMaintanceFile) {
		this.mMaintanceFile = mMaintanceFile;
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
	public String getmProcessDate() {
		return mProcessDate;
	}
	public void setmProcessDate(String mProcessDate) {
		this.mProcessDate = mProcessDate;
	}
	public String getmProcessContent() {
		return mProcessContent;
	}
	public void setmProcessContent(String mProcessContent) {
		this.mProcessContent = mProcessContent;
	}
	public String getmProcessFile() {
		return mProcessFile;
	}
	public void setmProcessFile(String mProcessFile) {
		this.mProcessFile = mProcessFile;
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
	public String getkPositionPath() {
		return kPositionPath;
	}
	public void setkPositionPath(String kPositionPath) {
		this.kPositionPath = kPositionPath;
	}
	public String getkPositionUpKey() {
		return kPositionUpKey;
	}
	public void setkPositionUpKey(String kPositionUpKey) {
		this.kPositionUpKey = kPositionUpKey;
	}
	public String getkPositionUpKeySave() {
		return kPositionUpKeySave;
	}
	public void setkPositionUpKeySave(String kPositionUpKeySave) {
		this.kPositionUpKeySave = kPositionUpKeySave;
	}
	public String geteIMGregName() {
		return eIMGregName;
	}
	public void seteIMGregName(String eIMGregName) {
		this.eIMGregName = eIMGregName;
	}
	public String geteGubunPage() {
		return eGubunPage;
	}
	public void seteGubunPage(String eGubunPage) {
		this.eGubunPage = eGubunPage;
	}
	public String geteAddFileId() {
		return eAddFileId;
	}
	public void seteAddFileId(String eAddFileId) {
		this.eAddFileId = eAddFileId;
	}
	public String getAtchFileName() {
		return atchFileName;
	}
	public void setAtchFileName(String atchFileName) {
		this.atchFileName = atchFileName;
	}
	public String geteFileNum() {
		return eFileNum;
	}
	public void seteFileNum(String eFileNum) {
		this.eFileNum = eFileNum;
	}
	public String getmMaintanceIssueType() {
		return mMaintanceIssueType;
	}
	public void setmMaintanceIssueType(String mMaintanceIssueType) {
		this.mMaintanceIssueType = mMaintanceIssueType;
	}
}
