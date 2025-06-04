package egovframework.rndp.com.cmm.service;

import egovframework.com.utl.fcc.service.EgovStringUtil;

import java.io.Serializable;

public class DefultVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 검색조건 */
    private String searchCondition = "";
    
    /** 검색Keyword */
    private String searchKeyword = "";
    
    /** 검색사용여부 */
    private String searchUseYn = "";
    
    /** 현재페이지 */
    private int pageIndex = 1;
    
    /** 페이지갯수 */
    private int pageUnit = 15;
    
    /** 페이지사이즈 */
    private int pageSize = 20;

    /** firstIndex */
    private int firstIndex = 0;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 20;
 
    /** 검색KeywordFrom */
    private String searchKeywordFrom = "";    

	/** 검색KeywordTo */
    private String searchKeywordTo = "";
    
    /** 검색 시작날짜 (7일전,한달전,세달전, ···) */
    private String searchStartDate = "";
    
    /** 스태프키 */
    private String kStaffKey = "0";
    
    
    /** 삭제flag */
    private String DFLAG = "N";
    
	private String searchWord						= "";
	private String searchType						= "";
	
	//유형지정
	private String searchTypeSet1					= "";
	private String searchTypeSet2					= "";
	private String searchTypeSet3					= "";
	private String searchTypeSet4					= "";
	private String searchTypeSet5					= "";
	private String searchTypeSet6					= "";
	private String searchTypeSet7					= "";
	private String searchTypeSet8					= "";
	private String searchTypeSet9					= "";
	private String searchTypeSet10					= "";
	
	
	private String kStaffName						= "";
	private String topStartDate						= "";
	private String topEndDate						= "";


	private String searchWordfront					= "";
	private String searchWordback					= "";
	private String searchcount						= "1";
	private int wordlength 							=0;

	private String csrfToken						= "";
	
	public String getCsrfToken() {
		return csrfToken;
	}


	public void setCsrfToken(String csrfToken) {
		this.csrfToken = csrfToken;
	}


	public static DefultVO getSearch(DefultVO vo){
		String[] search =(EgovStringUtil.split(vo.getSearchWord(), " "));
		if(search.length>1){
			vo.setSearchcount("2");
			vo.setSearchWordfront(search[0]);
			String back = search[1];
			vo.setWordlength(back.length());
			vo.setSearchWordback(back);
		}else {
			vo.setSearchcount("1");
		}
		return vo;
	}


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getSearchWordfront() {
		return searchWordfront;
	}

	public void setSearchWordfront(String searchWordfront) {
		this.searchWordfront = searchWordfront;
	}

	public String getSearchWordback() {
		return searchWordback;
	}

	public void setSearchWordback(String searchWordback) {
		this.searchWordback = searchWordback;
	}

	public String getSearchcount() {
		return searchcount;
	}

	public void setSearchcount(String searchcount) {
		this.searchcount = searchcount;
	}

	public int getWordlength() {
		return wordlength;
	}

	public void setWordlength(int wordlength) {
		this.wordlength = wordlength;
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

	public String getDFLAG() {
		return DFLAG;
	}

	public void setDFLAG(String dFLAG) {
		DFLAG = dFLAG;
	}

	public String getkStaffKey() {
		return kStaffKey;
	}

	public void setkStaffKey(String kStaffKey) {
		this.kStaffKey = kStaffKey;
	}

	public String getSearchStartDate() {
		return searchStartDate;
	}

	public void setSearchStartDate(String searchStartDate) {
		this.searchStartDate = searchStartDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public String getSearchUseYn() {
		return searchUseYn;
	}

	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public String getSearchKeywordFrom() {
		return searchKeywordFrom;
	}

	public void setSearchKeywordFrom(String searchKeywordFrom) {
		this.searchKeywordFrom = searchKeywordFrom;
	}

	public String getSearchKeywordTo() {
		return searchKeywordTo;
	}

	public void setSearchKeywordTo(String searchKeywordTo) {
		this.searchKeywordTo = searchKeywordTo;
	}


	public String getSearchTypeSet1() {
		return searchTypeSet1;
	}


	public void setSearchTypeSet1(String searchTypeSet1) {
		this.searchTypeSet1 = searchTypeSet1;
	}


	public String getSearchTypeSet2() {
		return searchTypeSet2;
	}


	public void setSearchTypeSet2(String searchTypeSet2) {
		this.searchTypeSet2 = searchTypeSet2;
	}


	public String getSearchTypeSet3() {
		return searchTypeSet3;
	}


	public void setSearchTypeSet3(String searchTypeSet3) {
		this.searchTypeSet3 = searchTypeSet3;
	}


	public String getSearchTypeSet4() {
		return searchTypeSet4;
	}


	public void setSearchTypeSet4(String searchTypeSet4) {
		this.searchTypeSet4 = searchTypeSet4;
	}


	public String getSearchTypeSet5() {
		return searchTypeSet5;
	}


	public void setSearchTypeSet5(String searchTypeSet5) {
		this.searchTypeSet5 = searchTypeSet5;
	}


	public String getSearchTypeSet6() {
		return searchTypeSet6;
	}


	public void setSearchTypeSet6(String searchTypeSet6) {
		this.searchTypeSet6 = searchTypeSet6;
	}


	public String getSearchTypeSet7() {
		return searchTypeSet7;
	}


	public void setSearchTypeSet7(String searchTypeSet7) {
		this.searchTypeSet7 = searchTypeSet7;
	}


	public String getSearchTypeSet8() {
		return searchTypeSet8;
	}


	public void setSearchTypeSet8(String searchTypeSet8) {
		this.searchTypeSet8 = searchTypeSet8;
	}


	public String getSearchTypeSet9() {
		return searchTypeSet9;
	}


	public void setSearchTypeSet9(String searchTypeSet9) {
		this.searchTypeSet9 = searchTypeSet9;
	}


	public String getSearchTypeSet10() {
		return searchTypeSet10;
	}


	public void setSearchTypeSet10(String searchTypeSet10) {
		this.searchTypeSet10 = searchTypeSet10;
	}


}
