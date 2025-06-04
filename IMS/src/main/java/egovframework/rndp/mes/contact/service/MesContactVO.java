package egovframework.rndp.mes.contact.service;

import egovframework.rndp.com.cmm.service.DefultVO;

public class MesContactVO extends DefultVO{
	
	private String searchWord						= "";
	private String searchGubun						= "";
	private String kStaffKey						= "";
	private String kStaffName						= "";
	
	private String eContactNum                 	= "";
	private String eAgencyName                 	= "";
	private String eDepartmentName                	= "";
	private String eContactName	            	= "";
	private String ePhoneNumber           	= "";
	private String eEmail          	= "";
	private String eNotes            	= "";
	
	
	
	
	public String geteContactNum() {
		return eContactNum;
	}
	public void seteContactNum(String eContactNum) {
		this.eContactNum = eContactNum;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
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
	public String geteAgencyName() {
		return eAgencyName;
	}
	public void seteAgencyName(String eAgencyName) {
		this.eAgencyName = eAgencyName;
	}
	public String geteDepartmentName() {
		return eDepartmentName;
	}
	public void seteDepartmentName(String eDepartmentName) {
		this.eDepartmentName = eDepartmentName;
	}
	public String geteContactName() {
		return eContactName;
	}
	public void seteContactName(String eContactName) {
		this.eContactName = eContactName;
	}
	public String getePhoneNumber() {
		return ePhoneNumber;
	}
	public void setePhoneNumber(String ePhoneNumber) {
		this.ePhoneNumber = ePhoneNumber;
	}
	public String geteEmail() {
		return eEmail;
	}
	public void seteEmail(String eEmail) {
		this.eEmail = eEmail;
	}
	public String geteNotes() {
		return eNotes;
	}
	public void seteNotes(String eNotes) {
		this.eNotes = eNotes;
	}
	
	
 
}
