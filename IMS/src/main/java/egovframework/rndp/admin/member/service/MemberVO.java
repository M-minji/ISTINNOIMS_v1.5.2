package egovframework.rndp.admin.member.service;

import java.text.SimpleDateFormat;

import egovframework.rndp.com.cmm.service.DefultVO;

public class MemberVO extends DefultVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분 ss초");

	private String level = "";

	private int key = 0;
	private int levelKey = 0;
	private String id = "";
	private String name = "";
	private String password = "";
	private String jumin1 = "";
	private String jumin2 = "";
	private String gender = "M";
	private String birth = "";
	private String birthFlag = "T";
	private String email = "";
	private String emailFlag = "T";
	private String post1 = "";
	private String post2 = "";
	private String address1 = "";
	private String address2 = "";
	private String telephone1 = "";
	private String telephone2 = "";
	private String telephone3 = "";
	private String mobile1 = "";
	private String mobile2 = "";
	private String mobile3 = "";
	private String job = "";
	private String hobby = "";
	private String speciality = "";
	private String homepage = "http://";
	private String comment = "";
	private String shareFlag = "F";
	private String joindate = "0000-01-01";
	private String lastdate = "0000-01-01";
	private String stateFlag = "1";
	private int point = 0;
	
	private int levelRank;

	private int countNow = 0;
	private int countMax = 0;
	
	private String searchType = "";                //검색조건
	private String searchWord = "";                //검색 keyword

	private String order = "";
	private String orderTp ="";
	private int page = 0;
	private int pageLength = 15;
	
	private String changeFlag="F";
	private String changePasswod="";
	
	public SimpleDateFormat getSdf() {
		return sdf;
	}
	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public int getLevelKey() {
		return levelKey;
	}
	public void setLevelKey(int levelKey) {
		this.levelKey = levelKey;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getJumin1() {
		return jumin1;
	}
	public void setJumin1(String jumin1) {
		this.jumin1 = jumin1;
	}
	public String getJumin2() {
		return jumin2;
	}
	public void setJumin2(String jumin2) {
		this.jumin2 = jumin2;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getBirthForm(){
		String result = birth;

		if (result.length() == 8){
			result = result.substring(0, 4) + "년 " + result.substring(4, 6) + "월 " + result.substring(6, 8) + "일";
		}

		return result;
	}
	public String getBirthFlag() {
		return birthFlag;
	}
	public void setBirthFlag(String birthFlag) {
		this.birthFlag = birthFlag;
	}
	public String getBirthFlagForm(){
		if (birthFlag.equals("T")){
			return "(양력)";
		}else{
			return "(음력)";
		}
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailFlag() {
		return emailFlag;
	}
	public void setEmailFlag(String emailFlag) {
		this.emailFlag = emailFlag;
	}
	public String getPost1() {
		return post1;
	}
	public void setPost1(String post1) {
		this.post1 = post1;
	}
	public String getPost2() {
		return post2;
	}
	public void setPost2(String post2) {
		this.post2 = post2;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getTelephone1() {
		return telephone1;
	}
	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}
	public String getTelephone2() {
		return telephone2;
	}
	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}
	public String getTelephone3() {
		return telephone3;
	}
	public void setTelephone3(String telephone3) {
		this.telephone3 = telephone3;
	}
	public String getTelephone(){
		String result = telephone1 + "-&nbsp" + telephone2 + "&nbsp-&nbsp" + telephone3;

		if (result.equals("--")){
			return "";
		}else{
			return result;
		}
	}
	public String getMobile1() {
		return mobile1;
	}
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	public String getMobile2() {
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	public String getMobile3() {
		return mobile3;
	}
	public void setMobile3(String mobile3) {
		this.mobile3 = mobile3;
	}
	public String getMobile(){
		String result = mobile1 + "&nbsp-&nbsp" + mobile2 + "&nbsp-&nbsp" + mobile3;

		if (result.equals("--")){
			return "";
		}else{
			return result;
		}
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getShareFlag() {
		return shareFlag;
	}
	public void setShareFlag(String shareFlag) {
		this.shareFlag = shareFlag;
	}
	public String getJoindate() {
		return joindate;
	}
	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}
	public String getLastdate() {
		return lastdate;
	}
	public void setLastdate(String lastdate) {
		this.lastdate = lastdate;
	}
	public String getStateFlag() {
		return stateFlag;
	}
	public void setStateFlag(String stateFlag) {
		this.stateFlag = stateFlag;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getCountNow() {
		return countNow;
	}
	public void setCountNow(int countNow) {
		this.countNow = countNow;
	}
	public int getCountMax() {
		return countMax;
	}
	public void setCountMax(int countMax) {
		this.countMax = countMax;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		if(searchType.equals("level")){
			order = "k_member_level_subject";
			orderTp = searchWord.trim();
		}else if(searchType.equals("name")){
			order = "k_member_name";
			orderTp = searchWord.trim();
		}else if(searchType.equals("memId")){
			order = "k_member_id";
			orderTp = searchWord.trim();
		}
		this.searchType = searchType;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getOrderTp() {
		return orderTp;
	}
	public void setOrderTp(String orderTp) {
		this.orderTp = orderTp;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageLength() {
		return pageLength;
	}
	public void setPageLength(int pageLength) {
		this.pageLength = pageLength;
	}
	public String getGenderForm(){
		if ("M".equals(gender)){
			return "남";
		}else{
			return "여";
		}
	}
	
	public String getStateFlagForm(){
		if ("1".equals(stateFlag)){
			return "활동";
		}else if ("2".equals(stateFlag)){
			return "대기";
		}else if ("3".equals(stateFlag)){
			return "중지";
		}else{
			return "탈퇴";
		}
	}
	
	public String getShareFlagForm(){
		if (shareFlag.equals("T")){
			return "공개";
		}else{
			return "비공개";
		}
	}
	
	public String getStrJoindate(){
		return joindate.substring(0, 10);
	}
	
	public String getStrLastdate(){
		return lastdate.substring(0, 10);
	}
	
	public String getEmailFlagForm(){
		if (emailFlag.equals("T"))
		{
			return "(수신)";
		}
		else
		{
			return "(수신거부)";
		}
	}
	public int getLevelRank() {
		return levelRank;
	}
	public void setLevelRank(int levelRank) {
		this.levelRank = levelRank;
	}
	public String getChangeFlag() {
		return changeFlag;
	}
	public void setChangeFlag(String changeFlag) {
		this.changeFlag = changeFlag;
	}
	public String getChangePasswod() {
		return changePasswod;
	}
	public void setChangePasswod(String changePasswod) {
		this.changePasswod = changePasswod;
	}
	

}
