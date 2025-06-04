package egovframework.rndp.admin.env.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rndp.admin.env.service.EnvHistoryVO;
import egovframework.rndp.admin.env.service.EnvPatentVO;
import egovframework.rndp.admin.env.service.EnvRecruitVO;
import egovframework.rndp.admin.env.service.EnvVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("envDAO")
public class EnvDAO extends EgovAbstractDAO {

	public List envList() throws Exception{
		return list("envDAO.envList", null);
	}
	
	public void updateEnv(EnvVO vo) throws Exception{
		update("envDAO.updateEnv", vo);
	}
	
	public List patentList() throws Exception{
		return list("envDAO.patentList", null);
	} 
	
	public int getMaxRank() throws Exception{
		return  (Integer) getSqlMapClientTemplate().queryForObject("envDAO.getMaxRank", null);
	}
	
	public void patentInsert(EnvPatentVO vo) throws Exception{
		update("envDAO.patentInsert", vo);
	}
	
	public EnvPatentVO envPatentByInfo(int key) throws Exception{
		return (EnvPatentVO) selectByPk("envDAO.envPatentByInfo", key);
	}
	
	public void envPatentUpt(EnvPatentVO vo) throws Exception{
		update("envDAO.envPatentUpt", vo);
	}
	
	public List patentOrderByRank() throws Exception{
		return list("envDAO.patentOrderByRank", null);
	}
	
	public EnvPatentVO patentByKeyTitle(EnvPatentVO	vo) throws Exception{
		return (EnvPatentVO) selectByPk("envDAO.patentByKeyTitle", vo);
	}
	
	public void movePatentUpt(EnvPatentVO	vo) throws Exception{
		update("envDAO.movePatentUpt", vo);
	}
	
	public void uptStep(EnvPatentVO	vo) throws Exception{
		update("envDAO.uptStep", vo);
	}
	
	public void envPatentDelete(int key) throws Exception{
		delete("envDAO.envPatentDelete", key);
	}
	
	public List envHistoryList() throws Exception{
		return list("envDAO.envHistoryList", null);
	}
	
	public EnvHistoryVO envHistoryInfo(int key) throws Exception{
		return (EnvHistoryVO) selectByPk("envDAO.envHistoryInfo", key);
	}
	
	public void insertEnvHistory(EnvHistoryVO vo) throws Exception{
		insert("envDAO.insertEnvHistory", vo);
	}
	
	public void updateEnvHistory(EnvHistoryVO vo) throws Exception{
		update("envDAO.updateEnvHistory", vo);
	}

	public void uptHistoryVisible(EnvHistoryVO vo) throws Exception{
		
		update("envDAO.uptHistoryVisible", vo);
	}

	public void deleteHistory(int key) throws Exception{
		delete("envDAO.deleteHistory", key);
	}
	
	public List envRecruitList() throws Exception{
		return list("envDAO.envRecruitList", null);
	}
	
	public void envRecruitDelete(int key) throws Exception{
		delete("envDAO.envRecruitDelete", key);
	}

	public void recruitActiveUpt(EnvRecruitVO vo) throws Exception {
		update("envDAO.recruitActiveUpt", vo);
	}
	
	public void reecruitInsert(EnvRecruitVO vo) throws Exception{
		insert("envDAO.reecruitInsert", vo);
	}
	
	public EnvRecruitVO recruitInfo(int key) throws Exception{
		return (EnvRecruitVO) selectByPk("envDAO.recruitInfo", key);
	}

	public void recruitUpdate(EnvRecruitVO vo) throws Exception{
		update("envDAO.recruitUpdate", vo);
	}

	public List hisVisibleInfo() throws Exception{
		return list("envDAO.hisVisibleInfo", null);
	}

	public List getYearList() throws Exception{
		return list("envDAO.getYearList", null);
	}

	public List getMmList() throws Exception{
		return list("envDAO.getMmList", null);
	}

	public String getFilePath(EnvVO vo) {
		return (String) select("envDAO.getFilePath", vo);
	}
}
