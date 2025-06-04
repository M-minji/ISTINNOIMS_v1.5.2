package egovframework.rndp.mes.sign.service;

import egovframework.rndp.com.cmm.service.DefultVO;

/**
 * @author rndp
 *
 */
public class MesSignVO extends DefultVO{

	private String searchWord      					= "";
	private String searchType      					= "";
	private String searchType2      				= "";
	private String searchType3    					= "";
	private String topStartDate   					= "";
	private String topEndDate     					= "";
	
	// S_SIGN
	private String sSignKey							= "";
	private String sSignTableKey					= "";
	private String sSignTableName					= "";
	private String sSignStartDate					= "";
	private String sSignEndDate						= "";
	private String sSignProgress					= "";
	
	// S_SIGN_ITEM
	private String sSignItemKey						= "";
	private String sSignSequence					= "";
	private String sSignStaffKey					= "";
	private String sSignStaffName					= "";
	private String sSignStaffPosition					= "";
	private String sSignDecison						= "";
	private String sSignContent						= "";
	private String sSignStaffGubun						= "";
	

	private String kPositionKey						= "";
	private String kPositionName					= "";
	private String kStaffKey1						= "";
	private String preKStaffKey						= "";
	
	private String sSingPathNum					= "";
	private String sSingPathKey						= "";
	private String sSingPathName						= "";
	private String sSingPathGubun						= "";
	private String sSingPathSubject						= "";
	private String sSignSubSequence						= "";
	
	private String kStaffNum						= "";
	private String kStaffId						= "";
	private String kStaffName						= "";
	private String kClassName						= "";
	private String kMenuKey						= "";
	
	private String gubun1						= "";
	private String kPos						= "";
	
	
	
	
	public String getkPos() {
		return kPos;
	}
	public void setkPos(String kPos) {
		this.kPos = kPos;
	}
	public String getGubun1() {
		return gubun1;
	}
	public void setGubun1(String gubun1) {
		this.gubun1 = gubun1;
	}
	public String getkMenuKey() {
		return kMenuKey;
	}
	public void setkMenuKey(String kMenuKey) {
		this.kMenuKey = kMenuKey;
	}
	public String getsSignStaffGubun() {
		return sSignStaffGubun;
	}
	public void setsSignStaffGubun(String sSignStaffGubun) {
		this.sSignStaffGubun = sSignStaffGubun;
	}
	public String getkStaffNum() {
		return kStaffNum;
	}
	public void setkStaffNum(String kStaffNum) {
		this.kStaffNum = kStaffNum;
	}
	public String getkStaffId() {
		return kStaffId;
	}
	public void setkStaffId(String kStaffId) {
		this.kStaffId = kStaffId;
	}
	public String getkStaffName() {
		return kStaffName;
	}
	public void setkStaffName(String kStaffName) {
		this.kStaffName = kStaffName;
	}
	public String getkClassName() {
		return kClassName;
	}
	public void setkClassName(String kClassName) {
		this.kClassName = kClassName;
	}
	public String getsSignSubSequence() {
		return sSignSubSequence;
	}
	public void setsSignSubSequence(String sSignSubSequence) {
		this.sSignSubSequence = sSignSubSequence;
	}
	public String getsSingPathNum() {
		return sSingPathNum;
	}
	public void setsSingPathNum(String sSingPathNum) {
		this.sSingPathNum = sSingPathNum;
	}
	public String getsSingPathKey() {
		return sSingPathKey;
	}
	public void setsSingPathKey(String sSingPathKey) {
		this.sSingPathKey = sSingPathKey;
	}
	public String getsSingPathName() {
		return sSingPathName;
	}
	public void setsSingPathName(String sSingPathName) {
		this.sSingPathName = sSingPathName;
	}
	public String getsSingPathGubun() {
		return sSingPathGubun;
	}
	public void setsSingPathGubun(String sSingPathGubun) {
		this.sSingPathGubun = sSingPathGubun;
	}
	public String getsSingPathSubject() {
		return sSingPathSubject;
	}
	public void setsSingPathSubject(String sSingPathSubject) {
		this.sSingPathSubject = sSingPathSubject;
	}
	public String getPreKStaffKey() {
		return preKStaffKey;
	}
	public void setPreKStaffKey(String preKStaffKey) {
		this.preKStaffKey = preKStaffKey;
	}
	public String getkStaffKey1() {
		return kStaffKey1;
	}
	public void setkStaffKey1(String kStaffKey1) {
		this.kStaffKey1 = kStaffKey1;
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
	public String getSearchType2() {
		return searchType2;
	}
	public void setSearchType2(String searchType2) {
		this.searchType2 = searchType2;
	}
	public String getSearchType3() {
		return searchType3;
	}
	public void setSearchType3(String searchType3) {
		this.searchType3 = searchType3;
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
	public String getsSignKey() {
		return sSignKey;
	}
	public void setsSignKey(String sSignKey) {
		this.sSignKey = sSignKey;
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
	public String getsSignStartDate() {
		return sSignStartDate;
	}
	public void setsSignStartDate(String sSignStartDate) {
		this.sSignStartDate = sSignStartDate;
	}
	public String getsSignEndDate() {
		return sSignEndDate;
	}
	public void setsSignEndDate(String sSignEndDate) {
		this.sSignEndDate = sSignEndDate;
	}
	public String getsSignProgress() {
		return sSignProgress;
	}
	public void setsSignProgress(String sSignProgress) {
		this.sSignProgress = sSignProgress;
	}
	public String getsSignItemKey() {
		return sSignItemKey;
	}
	public void setsSignItemKey(String sSignItemKey) {
		this.sSignItemKey = sSignItemKey;
	}
	public String getsSignSequence() {
		return sSignSequence;
	}
	public void setsSignSequence(String sSignSequence) {
		this.sSignSequence = sSignSequence;
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
	public String getsSignStaffPosition() {
		return sSignStaffPosition;
	}
	public void setsSignStaffPosition(String sSignStaffPosition) {
		this.sSignStaffPosition = sSignStaffPosition;
	}

}
