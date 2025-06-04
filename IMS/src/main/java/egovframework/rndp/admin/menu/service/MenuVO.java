package egovframework.rndp.admin.menu.service;


public class MenuVO extends DefultVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int root2 = 0;
	private int step2 = 0;
	private int key = 0;
	private int root = 0;
	private int ref = 0;
	private int depth = 0;
	private int step = 0;
	private String name = "";
	private String url = "";
	private String target = "";
	private String depthImg = "";
	private int groupKey = 0;
	private String groupName = "공통";
	private int preRefKey = 0;
	private int adminKey;
	
	
	public int getAdminKey() {
		return adminKey;
	}
	public void setAdminKey(int adminKey) {
		this.adminKey = adminKey;
	}
	public int getRoot2() {
		return root2;
	}
	public void setRoot2(int root2) {
		this.root2 = root2;
	}
	public int getStep2() {
		return step2;
	}
	public void setStep2(int step2) {
		this.step2 = step2;
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
	public String getDepthImg() {
		return depthImg;
	}
	public void setDepthImg(String depthImg) {
		this.depthImg = depthImg;
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

	
}
