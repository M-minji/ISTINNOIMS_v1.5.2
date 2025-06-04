package egovframework.rndp.admin.admin.service;

import egovframework.rndp.com.utl.DefultVO;


public class AdminVO extends DefultVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int key;										//관리자 키
	private String password;								//비밀번호		
	private String newpassword;								//변경 비밀번호
	private String nppassword;								//비밀번호 확인
	private String group;									//그룹 명
	private String email;									//이메일
	private String telephone;									//전화번호
	private String memo;									//메모
	private String adminFlag  = "F";							//
	private int staffKey;										//직원 키
	private String name;									//직원 명
	private String staffClass;									//직원 직급
	private String staffPosition;								//직원 위치 
	
	
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
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
	public String getNppassword() {
		return nppassword;
	}
	public void setNppassword(String nppassword) {
		this.nppassword = nppassword;
	}
	
	public String getAdminFlag() {
		return adminFlag;
	}
	public void setAdminFlag(String adminFlag) {
		this.adminFlag = adminFlag;
	}
	public int getStaffKey() {
		return staffKey;
	}
	public void setStaffKey(int staffKey) {
		this.staffKey = staffKey;
	}
	public String getStaffClass() {
		return staffClass;
	}
	public void setStaffClass(String staffClass) {
		this.staffClass = staffClass;
	}
	public String getStaffPosition() {
		return staffPosition;
	}
	public void setStaffPosition(String staffPosition) {
		this.staffPosition = staffPosition;
	}
}
