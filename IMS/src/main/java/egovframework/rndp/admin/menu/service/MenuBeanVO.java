package egovframework.rndp.admin.menu.service;

import java.io.Serializable;

public class MenuBeanVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String adminId = "";
	private String inheritId = "";

	private int key = 0;
	private int root = 0;
	private int root2 = 0;
	private int ref = 0;
	private int depth = 0;
	private int step = 0;
	private int groupKey = 1;  
	private String name = "";
	private String titlefile = "";
	private String type = "C";                  // 운영 형태     C:일반페이지   , B:게시판  , P:프로그램 링크  ,  L: 사이트 링크
	private String exist = "T";					//페이지 유무     T:있음 , F:없음
	private String visible = "T";               //화면표시 여부  T:표시함 , F:숨김
	private String image1 = ""; 
	private String image2 = "";
	private String image3 = "";
	private String image4 = "";
	private String color = "";
	private int boardKey = 0;                   //게시판 키
	private String program = "";                //프로그램 링크
	private String link = "";                   //사이트 링크
	private int adminKey = 0;
	private String inherit = "F";               //최고관리자 상속  T:상속 ,  F: 비상속
	private String permit = "F";                //접근권한 설정     T:설정 ,  F: 해제
	private int level = 100;
	private String comment = "F";
	private String statistic = "F";
	private String description = "";
	private String kMenuAuthM = "F";
	private String kMenuAuthC = "F";
	private String kMenuAuthD = "F";
	private String kMenuAuthW = "F";
	private String kMenuAuthFlag = "F";
	private int menuKey = 0;
	private String url;
	private String target;
	private String content;
	private int rootKey;
	private String staffMenuFlag	="";

	/**
	 * 메일에서 사용하기 위해 추가
	 * */
	private int pageNum = 1;
	private String searchWord="";
	
	
	private int strRoot = 0;
	private int strRoot2 = 0;
	private int strStep = 0;
	private int strStep2 = 0;
	
	private String adminStr;                                               //게시판에 관리자가 여러 있음
	private String menuCategory;                                           //인트라넷 컨텐츠 상단 메뉴 title
	private String err;                                           //컨텐츠파일없을시
	
	private String leftTitle = "";
	private String appCnt = "";
	private int leftMenuKey = 0;
	
	private String screenId = ""; //화면ID
	private String screenHistory = ""; // 기존 , 신규 , 변경 
	
	
	private String kStaffKey = "";
	
	
	
	public String getkStaffKey() {
		return kStaffKey;
	}
	public void setkStaffKey(String kStaffKey) {
		this.kStaffKey = kStaffKey;
	}
	public String getScreenHistory() {
		return screenHistory;
	}
	public void setScreenHistory(String screenHistory) {
		this.screenHistory = screenHistory;
	}
	public String getScreenId() {
		return screenId;
	}
	public void setScreenId(String screenId) {
		this.screenId = screenId;
	}
	public String getAppCnt() {
		return appCnt;
	}
	public void setAppCnt(String appCnt) {
		this.appCnt = appCnt;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getkMenuAuthFlag() {
		return kMenuAuthFlag;
	}
	public void setkMenuAuthFlag(String kMenuAuthFlag) {
		this.kMenuAuthFlag = kMenuAuthFlag;
	}
	public String getkMenuAuthM() {
		return kMenuAuthM;
	}
	public void setkMenuAuthM(String kMenuAuthM) {
		this.kMenuAuthM = kMenuAuthM;
	}
	public String getkMenuAuthC() {
		return kMenuAuthC;
	}
	public void setkMenuAuthC(String kMenuAuthC) {
		this.kMenuAuthC = kMenuAuthC;
	}
	public String getkMenuAuthD() {
		return kMenuAuthD;
	}
	public void setkMenuAuthD(String kMenuAuthD) {
		this.kMenuAuthD = kMenuAuthD;
	}
	public String getkMenuAuthW() {
		return kMenuAuthW;
	}
	public void setkMenuAuthW(String kMenuAuthW) {
		this.kMenuAuthW = kMenuAuthW;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getInheritId() {
		return inheritId;
	}
	public void setInheritId(String inheritId) {
		this.inheritId = inheritId;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public int getRoot() {
		return root;
	}
	public void setRoot(int root) {
		this.root = root;
	}
	public int getRoot2() {
		return root2;
	}
	public void setRoot2(int root2) {
		this.root2 = root2;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getGroupKey() {
		return groupKey;
	}
	public void setGroupKey(int groupKey) {
		this.groupKey = groupKey;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitlefile() {
		return titlefile;
	}
	public void setTitlefile(String titlefile) {
		this.titlefile = titlefile;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getExist() {
		return exist;
	}
	public void setExist(String exist) {
		this.exist = exist;
	}
	public String getVisible() {
		return visible;
	}
	public void setVisible(String visible) {
		this.visible = visible;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getImage3() {
		return image3;
	}
	public void setImage3(String image3) {
		this.image3 = image3;
	}
	public String getImage4() {
		return image4;
	}
	public void setImage4(String image4) {
		this.image4 = image4;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getBoardKey() {
		return boardKey;
	}
	public void setBoardKey(int boardKey) {
		this.boardKey = boardKey;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getAdminKey() {
		return adminKey;
	}
	public void setAdminKey(int adminKey) {
		this.adminKey = adminKey;
	}
	public String getInherit() {
		return inherit;
	}
	public void setInherit(String inherit) {
		this.inherit = inherit;
	}
	public String getPermit() {
		return permit;
	}
	public void setPermit(String permit) {
		this.permit = permit;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getStatistic() {
		return statistic;
	}
	public void setStatistic(String statistic) {
		this.statistic = statistic;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getNameForm(){
		StringBuffer result = new StringBuffer();
		result.append(name);
		return result.toString();
	}
	public String getTypeForm(){
		if (type.equals("C")){
			return "컨텐츠";
		}else if (type.equals("B")){
			return "게시판";
		}else if (type.equals("L")){
			return "링크";
		}else if (type.equals("P")){
			return "프로그램";
		}else{
			return "<font color='red'>에러</font>";
		}
	}
	public String getVisibleForm(){
		if (visible.equals("T")){
			return "<font color='blue'>표시함</font>";
		}else{
			return "숨김";
		}
	}
	public String getDepthImg(){
		String result = "", blank = "&nbsp;&nbsp;&nbsp";
		for (int i = 1; i < depth; i++){
			result += blank;
		}

		if(depth > 0){
			result = result + "<img src='./../images/admin/2lastnode.gif' align='absmiddle'> ";
		}
		
		return result;
	}
	public int getStrRoot() {
		return strRoot;
	}
	public void setStrRoot(int strRoot) {
		this.strRoot = strRoot;
	}
	public int getStrRoot2() {
		return strRoot2;
	}
	public void setStrRoot2(int strRoot2) {
		this.strRoot2 = strRoot2;
	}
	public int getStrStep() {
		return strStep;
	}
	public void setStrStep(int strStep) {
		this.strStep = strStep;
	}
	public int getStrStep2() {
		return strStep2;
	}
	public void setStrStep2(int strStep2) {
		this.strStep2 = strStep2;
	}
	public boolean isAuth(){
		if ( "C".equals(this.getType())){
			return true;
		}else{
			return false;
		}
	}

	public boolean isAuth(String type){
		this.type = type;
		return this.isAuth();
	}
	public int getMenuKey() {
		return menuKey;
	}
	public void setMenuKey(int menuKey) {
		this.menuKey = menuKey;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getRootKey() {
		return rootKey;
	}
	public void setRootKey(int rootKey) {
		this.rootKey = rootKey;
	}
	public String getAdminStr() {
		return adminStr;
	}
	public void setAdminStr(String adminStr) {
		this.adminStr = adminStr;
	}
	public String getMenuCategory() {
		return menuCategory;
	}
	public void setMenuCategory(String menuCategory) {
		this.menuCategory = menuCategory;
	}
	public String getErr() {
		return err;
	}
	public void setErr(String err) {
		this.err = err;
	}
	public String getLeftTitle() {
		return leftTitle;
	}
	public void setLeftTitle(String leftTitle) {
		this.leftTitle = leftTitle;
	}
	public int getLeftMenuKey() {
		return leftMenuKey;
	}
	public void setLeftMenuKey(int leftMenuKey) {
		this.leftMenuKey = leftMenuKey;
	}
	public String getStaffMenuFlag() {
		return staffMenuFlag;
	}
	public void setStaffMenuFlag(String staffMenuFlag) {
		this.staffMenuFlag = staffMenuFlag;
	}
}
