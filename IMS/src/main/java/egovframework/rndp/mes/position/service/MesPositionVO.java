package egovframework.rndp.mes.position.service;

import egovframework.rndp.com.cmm.service.DefultVO;

public class MesPositionVO extends DefultVO{

	private String searchType						= "";
	private String searchWord						= "";
	
	// 부서관리
	private String kPositionKey					= "";
	private String kPositionName				= "";
	private String kPositionPath				= "";
	private String kPositionUpKey				= "0";
	private String kPositionUpName				= "최상위";
	private String kPositionRank				= "";
	private String kPositionStaffName 			= "";
	private String kPositionStaffList 			= "";
	private String kPositionStaffCount			= "";
	
	private String kPositionUpKeySave			= "0";
	
	
	
	
	public String getkPositionPath() {
		return kPositionPath;
	}
	public void setkPositionPath(String kPositionPath) {
		this.kPositionPath = kPositionPath;
	}
	public String getkPositionUpKeySave() {
		return kPositionUpKeySave;
	}
	public void setkPositionUpKeySave(String kPositionUpKeySave) {
		this.kPositionUpKeySave = kPositionUpKeySave;
	}
	public String getkPositionUpName() {
		return kPositionUpName;
	}
	public void setkPositionUpName(String kPositionUpName) {
		this.kPositionUpName = kPositionUpName;
	}
	public String getkPositionUpKey() {
		return kPositionUpKey;
	}
	public void setkPositionUpKey(String kPositionUpKey) {
		this.kPositionUpKey = kPositionUpKey;
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
	public String getkPositionRank() {
		return kPositionRank;
	}
	public void setkPositionRank(String kPositionRank) {
		this.kPositionRank = kPositionRank;
	}
	public String getkPositionStaffName() {
		return kPositionStaffName;
	}
	public void setkPositionStaffName(String kPositionStaffName) {
		this.kPositionStaffName = kPositionStaffName;
	}
	public String getkPositionStaffList() {
		return kPositionStaffList;
	}
	public void setkPositionStaffList(String kPositionStaffList) {
		this.kPositionStaffList = kPositionStaffList;
	}
	public String getkPositionStaffCount() {
		return kPositionStaffCount;
	}
	public void setkPositionStaffCount(String kPositionStaffCount) {
		this.kPositionStaffCount = kPositionStaffCount;
	}
}
