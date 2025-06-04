package egovframework.rndp.admin.env.service;

public class EnvVO {
	private String name;
	private String value;
	private String strNum;
	
	private String eAddFileId 						= "";
	private String eAddFileId1						= "";
	private String eAddFileId2 						= "";
	
	private String atchFileName 					= "";
	private String atchFileName1 					= "";
	private String atchFileName2					= "";
	private String atchFileName3 					= "";
	
	private String filePath							= "";
	private String eGubunPage      					= "";
	private String eIMGregName      				= "";
	
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String geteGubunPage() {
		return eGubunPage;
	}
	public void seteGubunPage(String eGubunPage) {
		this.eGubunPage = eGubunPage;
	}
	public String geteIMGregName() {
		return eIMGregName;
	}
	public void seteIMGregName(String eIMGregName) {
		this.eIMGregName = eIMGregName;
	}
	public String geteAddFileId() {
		return eAddFileId;
	}
	public void seteAddFileId(String eAddFileId) {
		this.eAddFileId = eAddFileId;
	}
	public String geteAddFileId1() {
		return eAddFileId1;
	}
	public void seteAddFileId1(String eAddFileId1) {
		this.eAddFileId1 = eAddFileId1;
	}
	public String geteAddFileId2() {
		return eAddFileId2;
	}
	public void seteAddFileId2(String eAddFileId2) {
		this.eAddFileId2 = eAddFileId2;
	}
	public String getAtchFileName() {
		return atchFileName;
	}
	public void setAtchFileName(String atchFileName) {
		this.atchFileName = atchFileName;
	}
	public String getAtchFileName1() {
		return atchFileName1;
	}
	public void setAtchFileName1(String atchFileName1) {
		this.atchFileName1 = atchFileName1;
	}
	public String getAtchFileName2() {
		return atchFileName2;
	}
	public void setAtchFileName2(String atchFileName2) {
		this.atchFileName2 = atchFileName2;
	}
	public String getAtchFileName3() {
		return atchFileName3;
	}
	public void setAtchFileName3(String atchFileName3) {
		this.atchFileName3 = atchFileName3;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getStrNum() {
		return strNum;
	}
	public void setStrNum(String strNum) {
		this.strNum = strNum;
	}
	
}
