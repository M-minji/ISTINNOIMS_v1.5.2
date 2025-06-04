package egovframework.rndp.mes.login.service;

public class MesLoginVo {
	
	/*직원 등록 신청 팝업에 사용*/
	/* 2020 07 27 intra 에서 mes로 모듈화 위해 이동 */
	private int    kClassKey      	=0;		//부서 키
	private int    kClassRank    	=0;		
	private String kClassName     	="";	//부서명
	
	private int    kSectorKey     	=0;
	private int    kSectorRank    	=0;
	private String kSectorName    	="";	//사업명

	private int    kPositionKey   	=0;
	private int    kPositionRank  	=0;
	private String kPositionName  	="";	//직급명
	
	private String shMemberPassword = ""; //비밀번호
	private String shMemberId = "";       // 현재 비밀번호
	private String chPassword = "";       // 변경 비밀번호 1
	private String chPassword2 = "";      // 변경 비밀번호 2

	
	public String getShMemberPassword() {
		return shMemberPassword;
	}
	public void setShMemberPassword(String shMemberPassword) {
		this.shMemberPassword = shMemberPassword;
	}
	public String getShMemberId() {
		return shMemberId;
	}
	public void setShMemberId(String shMemberId) {
		this.shMemberId = shMemberId;
	}
	public String getChPassword() {
		return chPassword;
	}
	public void setChPassword(String chPassword) {
		this.chPassword = chPassword;
	}
	public String getChPassword2() {
		return chPassword2;
	}
	public void setChPassword2(String chPassword2) {
		this.chPassword2 = chPassword2;
	}
	public int getkClassKey() {
		return kClassKey;
	}
	public void setkClassKey(int kClassKey) {
		this.kClassKey = kClassKey;
	}
	public int getkClassRank() {
		return kClassRank;
	}
	public void setkClassRank(int kClassRank) {
		this.kClassRank = kClassRank;
	}
	public String getkClassName() {
		return kClassName;
	}
	public void setkClassName(String kClassName) {
		this.kClassName = kClassName;
	}
	public int getkSectorKey() {
		return kSectorKey;
	}
	public void setkSectorKey(int kSectorKey) {
		this.kSectorKey = kSectorKey;
	}
	public int getkSectorRank() {
		return kSectorRank;
	}
	public void setkSectorRank(int kSectorRank) {
		this.kSectorRank = kSectorRank;
	}
	public String getkSectorName() {
		return kSectorName;
	}
	public void setkSectorName(String kSectorName) {
		this.kSectorName = kSectorName;
	}
	public int getkPositionKey() {
		return kPositionKey;
	}
	public void setkPositionKey(int kPositionKey) {
		this.kPositionKey = kPositionKey;
	}
	public int getkPositionRank() {
		return kPositionRank;
	}
	public void setkPositionRank(int kPositionRank) {
		this.kPositionRank = kPositionRank;
	}
	public String getkPositionName() {
		return kPositionName;
	}
	public void setkPositionName(String kPositionName) {
		this.kPositionName = kPositionName;
	}

}
