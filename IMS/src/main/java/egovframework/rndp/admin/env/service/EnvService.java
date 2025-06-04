package egovframework.rndp.admin.env.service;

import java.util.List;
import java.util.Map;

public interface EnvService {
	/**
	 * 기본정보, 기타관리 ,기타문의관리 List
	 * @return
	 * @throws Exception
	 */
	public List envList() throws Exception;
	
	/**
	 * 기본정보,기타관리, 기타문의관리 수정(update)
	 * @param envVO
	 * @throws Exception
	 */
	public void updateEnv(EnvVO envVO) throws Exception;

// 특허관리 ================================================================
	/**
	 * 특허관리 List
	 * @return
	 * @throws Exception
	 */
	public List patentList() throws Exception;
	
	/**
	 * 특허관리 getMaxRank
	 * @return
	 * @throws Exception
	 */
	public int getMaxRank() throws Exception;
	
	/**
	 * 특허관리 Insert
	 * @param vo
	 * @throws Exception
	 */
	public void patentInsert(EnvPatentVO vo) throws Exception;
	
	/**
	 * 특허관리 List By Pk
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public EnvPatentVO envPatentByInfo(int key) throws Exception;
	
	/**
	 * 특허관리 Update(E.수정)
	 * @param vo
	 * @throws Exception
	 */
	public void envPatentUpt(EnvPatentVO vo) throws Exception;
	
	/**
	 * 특허관리 Order By Rank
	 * @return
	 * @throws Exception
	 */
	public List patentOrderByRank() throws Exception;
	
	/**
	 * 특허관리 List(pk, title)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public EnvPatentVO patentByKeyTitle(EnvPatentVO	vo) throws Exception;
	
	/**
	 * 특허관리 Update(순서)
	 * @param rank
	 * @throws Exception
	 */
	public void movePatentUpt(EnvPatentVO	vo) throws Exception;
	
	/**
	 * 특허관리 Delete
	 * @param key
	 * @throws Exception
	 */
	public void envPatentDelete(int key) throws Exception;
	
// 연혁관리 ================================================================
	
	/**
	 * 연혁관리 List
	 * @return
	 * @throws Exception
	 */
	public List envHistoryList() throws Exception;
	
	/**
	 * 연혁관리 insert
	 * @param vo
	 * @throws Exception
	 */
	public void insertEnvHistory(EnvHistoryVO vo) throws Exception;
	
	/**
	 * 연혁관리 list by PK
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public EnvHistoryVO envHistoryInfo(int key) throws Exception;
	
	/**
	 * 연혁관리 update(E.수정)
	 * @param vo
	 * @throws Exception
	 */
	public void updateEnvHistory(EnvHistoryVO vo) throws Exception;

	/**
	 * 연혁관리 update(visible)
	 * @param vo
	 * @throws Exception
	 */
	public void uptHistoryVisible(EnvHistoryVO vo) throws Exception;
	
	/**
	 * 연혁관리 delete
	 * @param key
	 * @throws Exception
	 */
	public void deleteHistory(int key) throws Exception;
	
	/**
	 * visible select
	 * @return
	 * @throws Exception
	 */
	public List hisVisibleInfo() throws Exception;
	
	/**
	 * distinct Year
	 * @return
	 * @throws Exception
	 */
	public List getYearList() throws Exception;
	
	/**
	 * year mm
	 * @return
	 * @throws Exception
	 */
	public List getMmList() throws Exception;
	
// 채용정보 ================================================================
	
	/**
	 * 채용정보 List
	 * @return
	 * @throws Exception
	 */
	public List envRecruitList() throws Exception;
	
	/**
	 * 채용정보 Update(visible)
	 * @param vo
	 * @throws Exception
	 */
	public void recruitActiveUpt(EnvRecruitVO vo) throws Exception;

	/**
	 * 채용정보 Delete
	 * @param key
	 * @throws Exception
	 */
	public void envRecruitDelete(int key) throws Exception;

	/**
	 * 채용정보 List by Pk
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public EnvRecruitVO recruitInfo(int key) throws Exception;

	/**
	 * 채용정보 Update
	 * @param vo
	 * @throws Exception
	 */
	public void recruitUpdate(EnvRecruitVO vo) throws Exception;

	public void reecruitInsert(EnvRecruitVO vo) throws Exception;

	public void deleteRealPathFile(EnvVO envVO) throws Exception;


	

}
