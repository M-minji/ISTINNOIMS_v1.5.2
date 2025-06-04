package egovframework.rndp.admin.menu.service;

import egovframework.rndp.com.utl.DefultVO;

public class AdminmenuVO extends DefultVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int key;
	private int root;
	private int root2;
	private int ref;
	private int depth;
	private int step;
	private int step2;
	private String name;
	private String url;
	private String target;
	//private String depthImg;
	private int groupKey = 0;
	private String groupName = "공통";
	private int preRefKey = 0;
	private int adminKey;
	
	private int strRoot1;
	private int strRoot2;
	private int strStep1;
	private int strStep2;
	
	
	public int getAdminKey() {
		return adminKey;
	}
	public void setAdminKey(int adminKey) {
		this.adminKey = adminKey;
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
	public int getStep2() {
		return step2;
	}
	public void setStep2(int step2) {
		this.step2 = step2;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getTargetForm(){
		String result = "오른쪽";

		if (target.equals("_top")){
			result = "전체";
		}

		return result;
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
	public int getGroupKey() {
		return groupKey;
	}
	public void setGroupKey(int groupKey) {
		this.groupKey = groupKey;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getPreRefKey() {
		return preRefKey;
	}
	public void setPreRefKey(int preRefKey) {
		this.preRefKey = preRefKey;
	}
	public int getStrRoot1() {
		return strRoot1;
	}
	public void setStrRoot1(int strRoot1) {
		this.strRoot1 = strRoot1;
	}
	public int getStrRoot2() {
		return strRoot2;
	}
	public void setStrRoot2(int strRoot2) {
		this.strRoot2 = strRoot2;
	}
	public int getStrStep1() {
		return strStep1;
	}
	public void setStrStep1(int strStep1) {
		this.strStep1 = strStep1;
	}
	public int getStrStep2() {
		return strStep2;
	}
	public void setStrStep2(int strStep2) {
		this.strStep2 = strStep2;
	}
	
}
