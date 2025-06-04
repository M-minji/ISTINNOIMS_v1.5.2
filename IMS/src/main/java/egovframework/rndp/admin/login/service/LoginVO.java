package egovframework.rndp.admin.login.service;

import java.io.Serializable;


public class LoginVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String id = "";
	protected String password = "";
	protected String type = "M";
	protected String staffYN = "";

	protected String name = "";
	protected String jumin1 = "";
	protected String jumin2 = "";
	protected String email = "";
	
	protected String authLocal = "";
	protected String localAddr = "";
	
	protected int mainGroup = 0; //admin login 화면에서  선택하고 들어온 그붋키

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStaffYN() {
		return staffYN;
	}

	public void setStaffYN(String staffYN) {
		this.staffYN = staffYN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthLocal() {
		return authLocal;
	}

	public void setAuthLocal(String authLocal) {
		this.authLocal = authLocal;
	}

	public String getLocalAddr() {
		return localAddr;
	}

	public void setLocalAddr(String localAddr) {
		this.localAddr = localAddr;
	}

	public int getMainGroup() {
		return mainGroup;
	}

	public void setMainGroup(int mainGroup) {
		this.mainGroup = mainGroup;
	}
	
	
}
