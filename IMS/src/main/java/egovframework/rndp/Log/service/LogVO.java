package egovframework.rndp.Log.service;

import egovframework.rndp.com.cmm.service.DefultVO;

public class LogVO{
	// 로그관련 VO
	private String logNum			= "";
	private String key				= ""; //키값
	private String staffKey 		= ""; //회원키
	private String staffName 		= ""; //회원이름
	private String staffId 			= ""; //회원이름
	private String startDate		= ""; //시작시간
	private String endDate			= ""; //종료시간
	private String url				= ""; //이동 페이지
	private String gubun			= ""; //입력, 조회 , 수정 , 삭제
	private String page				= ""; //페이지이름
	private String pageGubun		= ""; //페이지구분 mes intra
	private String minute			= ""; //접속 시간
	private String logIp			= ""; //ip주소
	
	//연동
	private String logAPI			= "";
	private String logDt			= "";
	private String logUseDate		= "";
	private String recptnDt			= "";
	private String recptnRsltCd		= "";
	private String recptnRslt		= "";
	
	
	//k_menu
	private String kMenuKey			= "";
	private String kMenuRoot		= "";
	private String kMenuRef			= "";
	private String kMenuName		= "";
	private String kMenuProgSrc		= "";
	
	
	private String searchWord						= "";
	private String searchType						= "";
	private String searchGubun						= "";
	private String kStaffKey						= "";
	private String kStaffName						= "";
	private String sGubunKey						= "";
	private String sGubunName						= "";
	
	
	
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
    private int pageSize = 10;

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
    
    /** 삭제flag */
    private String DFLAG = "N";
    
	private String topStartDate						= "";
	private String topEndDate						= "";
	
	
	
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
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
	public String getSearchStartDate() {
		return searchStartDate;
	}
	public void setSearchStartDate(String searchStartDate) {
		this.searchStartDate = searchStartDate;
	}
	public String getDFLAG() {
		return DFLAG;
	}
	public void setDFLAG(String dFLAG) {
		DFLAG = dFLAG;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getRecptnDt() {
		return recptnDt;
	}
	public void setRecptnDt(String recptnDt) {
		this.recptnDt = recptnDt;
	}
	public String getRecptnRsltCd() {
		return recptnRsltCd;
	}
	public void setRecptnRsltCd(String recptnRsltCd) {
		this.recptnRsltCd = recptnRsltCd;
	}
	public String getRecptnRslt() {
		return recptnRslt;
	}
	public void setRecptnRslt(String recptnRslt) {
		this.recptnRslt = recptnRslt;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getLogAPI() {
		return logAPI;
	}
	public void setLogAPI(String logAPI) {
		this.logAPI = logAPI;
	}
	public String getLogDt() {
		return logDt;
	}
	public void setLogDt(String logDt) {
		this.logDt = logDt;
	}
	public String getLogUseDate() {
		return logUseDate;
	}
	public void setLogUseDate(String logUseDate) {
		this.logUseDate = logUseDate;
	}
	public String getLogIp() {
		return logIp;
	}
	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}
	public String getLogNum() {
		return logNum;
	}
	public void setLogNum(String logNum) {
		this.logNum = logNum;
	}
	public String getPageGubun() {
		return pageGubun;
	}
	public void setPageGubun(String pageGubun) {
		this.pageGubun = pageGubun;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getkMenuKey() {
		return kMenuKey;
	}
	public void setkMenuKey(String kMenuKey) {
		this.kMenuKey = kMenuKey;
	}
	public String getkMenuRoot() {
		return kMenuRoot;
	}
	public void setkMenuRoot(String kMenuRoot) {
		this.kMenuRoot = kMenuRoot;
	}
	public String getkMenuRef() {
		return kMenuRef;
	}
	public void setkMenuRef(String kMenuRef) {
		this.kMenuRef = kMenuRef;
	}
	public String getkMenuName() {
		return kMenuName;
	}
	public void setkMenuName(String kMenuName) {
		this.kMenuName = kMenuName;
	}
	public String getkMenuProgSrc() {
		return kMenuProgSrc;
	}
	public void setkMenuProgSrc(String kMenuProgSrc) {
		this.kMenuProgSrc = kMenuProgSrc;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getStaffKey() {
		return staffKey;
	}
	public void setStaffKey(String staffKey) {
		this.staffKey = staffKey;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		if(url.contains("_i.")){
			this.gubun = "등록";
		}else if(url.contains("_u.")){
			this.gubun = "수정";
		}else if(url.contains("_d.")){
			this.gubun = "삭제";
		}else if(url.contains("_if.")){
			this.gubun = "입력페이지";
		}else if(url.contains("_uf.")){
			this.gubun = "수정페이지";
		}else if(url.contains("_vf.")){
			this.gubun = "상세페이지";
		}else if(url.contains("_i_")){
			this.gubun = "등록";
		}else if(url.contains("_u_")){
			this.gubun = "수정";
		}else if(url.contains("_d_")){
			this.gubun = "삭제";
		}else if(url.contains("_if_")){
			this.gubun = "입력페이지";
		}else if(url.contains("_uf_")){
			this.gubun = "수정페이지";
		}else if(url.contains("_vf_")){
			this.gubun = "상세페이지";
		}else{
			this.gubun = "조회";
		}
		this.url = url;
	}
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}
	public String getMinute() {
		return minute;
	}
	public void setMinute(String minute) {
		this.minute = minute;
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
	public String getSearchGubun() {
		return searchGubun;
	}
	public void setSearchGubun(String searchGubun) {
		this.searchGubun = searchGubun;
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
	
	
}
