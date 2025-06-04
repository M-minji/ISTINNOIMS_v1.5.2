package egovframework.rndp.admin.position.service;

import java.io.Serializable;

public class StaffVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String iclass = "";
	private String sector = "";
	private String position = "";

	private int staffKey = 0;
	private int classKey = 0;
	private int sectorKey = 0;
	private int positionKey = 0;
	private String id = "";
	private String name = "";
	private String password = "";
	private String newpassword ="";
	private String birth = "";
	private String birthFlag = "T";
	private String email = "";
	private String messenger = "";
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
	private String homepage = "http://";
	private String comment = "";
	private String stateFlag = "1";
	private String imagefile = "";
	private String signImage = "";
	private String[] signImageArr = new String[4];
	private String num = "";
	private String jumin1 = "";
	private String jumin2 = "";
	
	private String className = "";
	
	private String staWtPermit = "";
	private String staRdPermit = "";
	
	private String staffDupList; // 사용자 검색시 중복 제거용
	
	// 사용자 검색시 중복 제거용 2015 05 07 
	int[] staffKeyDupList = {};
	
	
	public int[] getStaffKeyDupList() {
		return staffKeyDupList;
	}
	public void setStaffKeyDupList(int[] staffKeyDupList) {
		this.staffKeyDupList = staffKeyDupList;
	}
	public String getIclass() {
		return iclass;
	}
	public void setIclass(String iclass) {
		this.iclass = iclass;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getStaffKey() {
		return staffKey;
	}
	public void setStaffKey(int staffKey) {
		this.staffKey = staffKey;
	}
	public int getClassKey() {
		return classKey;
	}
	public void setClassKey(int classKey) {
		this.classKey = classKey;
	}
	public int getSectorKey() {
		return sectorKey;
	}
	public void setSectorKey(int sectorKey) {
		this.sectorKey = sectorKey;
	}
	public int getPositionKey() {
		return positionKey;
	}
	public void setPositionKey(int positionKey) {
		this.positionKey = positionKey;
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
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getBirthFlag() {
		return birthFlag;
	}
	public void setBirthFlag(String birthFlag) {
		this.birthFlag = birthFlag;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessenger() {
		return messenger;
	}
	public void setMessenger(String messenger) {
		this.messenger = messenger;
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
	public String getStateFlag() {
		return stateFlag;
	}
	public void setStateFlag(String stateFlag) {
		this.stateFlag = stateFlag;
	}
	public String getImagefile() {
		return imagefile;
	}
	public void setImagefile(String imagefile) {
		this.imagefile = imagefile;
	}
	public String getSignImage() {
		return signImage;
	}
	public void setSignImage(String signImage) {
		this.signImage = signImage;
	}
	public String[] getSignImageArr() {
		return signImageArr;
	}
	public void setSignImageArr(String[] signImageArr) {
		this.signImageArr = signImageArr;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
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
	public String getStaffDupList() {
		return staffDupList;
	}
	public void setStaffDupList(String staffDupList) {
		this.staffDupList = staffDupList;
	}
	public String getMobile(){
		String result = mobile1 + "-" + mobile2 + "-" + mobile3;

		if (result.equals("--")){
			return "";
		}else{
			return result;
		}
	}
	
	public String getStateFlagForm(){
		if (stateFlag.equals("1")){
			return "재직";
		}else if (stateFlag.equals("2")){
			return "휴직";
		}else if (stateFlag.equals("3")){
			return "정직";
		}else{
			return "퇴직";
		}
	}

	public String getTelephone(){
		String result = telephone1 + "-" + telephone2 + "-" + telephone3;

		if (result.equals("--")){
			return "";
		}else{
			return result;
		}
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getStaWtPermit() {
		return staWtPermit;
	}
	public void setStaWtPermit(String staWtPermit) {
		this.staWtPermit = staWtPermit;
	}
	public String getStaRdPermit() {
		return staRdPermit;
	}
	public void setStaRdPermit(String staRdPermit) {
		this.staRdPermit = staRdPermit;
	}
	
}
