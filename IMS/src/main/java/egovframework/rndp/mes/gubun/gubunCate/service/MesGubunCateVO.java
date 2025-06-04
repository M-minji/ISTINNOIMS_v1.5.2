package egovframework.rndp.mes.gubun.gubunCate.service;
import egovframework.rndp.com.cmm.service.DefultVO;

public class MesGubunCateVO extends DefultVO{
	
	private String searchWord						= "";
	private String kStaffKey						= "";
	private String kStaffName						= "";
	
	private String sGubunCateKey					= "";
	private String sGubunCateName					= "";
	private String sGubunCateEtc					= "";
	private String gbnCnt							= "";
	
	public String getGbnCnt() {
		return gbnCnt;
	}
	public void setGbnCnt(String gbnCnt) {
		this.gbnCnt = gbnCnt;
	}
	public String getsGubunCateKey() {
		return sGubunCateKey;
	}
	public void setsGubunCateKey(String sGubunCateKey) {
		this.sGubunCateKey = sGubunCateKey;
	}
	public String getsGubunCateName() {
		return sGubunCateName;
	}
	public void setsGubunCateName(String sGubunCateName) {
		this.sGubunCateName = sGubunCateName;
	}
	public String getsGubunCateEtc() {
		return sGubunCateEtc;
	}
	public void setsGubunCateEtc(String sGubunCateEtc) {
		this.sGubunCateEtc = sGubunCateEtc;
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
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	
}
