package egovframework.rndp.login.service;

import java.io.Serializable;

public class UserInfoVO implements Serializable{

	private int key = 0;
	private String id = "guest";
	private String linkCode = "";
	private String linkDb = "";
	private String password;
	private int levelRank = 100;
	private String level = "";
	private String name = "손님";
	private String email = "";
	private String type = "M";
	private int groupKey = 1;
	private int menuKey = 0;
	private int boardKey = 0;
	private int page = 0;
	private String searchWord = "";
	private String searchWordCom = "";
	private String searchType = "";
	private String url = "";
	private boolean counted = false;
	
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
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
	public int getLevelRank() {
		return levelRank;
	}
	public void setLevelRank(int levelRank) {
		this.levelRank = levelRank;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getGroupKey() {
		return groupKey;
	}
	public void setGroupKey(int groupKey) {
		this.groupKey = groupKey;
	}
	public int getMenuKey() {
		return menuKey;
	}
	public void setMenuKey(int menuKey) {
		this.menuKey = menuKey;
	}
	public int getBoardKey() {
		return boardKey;
	}
	public void setBoardKey(int boardKey) {
		this.boardKey = boardKey;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getSearchWordCom() {
		return searchWordCom;
	}
	public void setSearchWordCom(String searchWordCom) {
		this.searchWordCom = searchWordCom;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isCounted() {
		return counted;
	}
	public void setCounted(boolean counted) {
		this.counted = counted;
	}
	public String getLinkCode() {
		return linkCode;
	}
	public void setLinkCode(String linkCode) {
		this.linkCode = linkCode;
	}
	public String getLinkDb() {
		return linkDb;
	}
	public void setLinkDb(String linkDb) {
		this.linkDb = linkDb;
	}
}
