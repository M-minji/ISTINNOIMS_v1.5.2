package egovframework.rndp.admin.env.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rndp.admin.env.service.EnvPatentVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("envPatentDAO")
public class EnvPatentDAO extends EgovAbstractDAO{

	public List envPatentList() throws Exception{
		return list("envDAO.envPatentList", null);
	}
	
	public List moveList() throws Exception{
		return list("envDAO.moveList", null);
	}
	
	public EnvPatentVO envPatentInfo(EnvPatentVO vo) throws Exception{
		return (EnvPatentVO)selectByPk("envDAO.envPatentInfo", vo);
	}
	
	public EnvPatentVO envPatentInfoByRank(EnvPatentVO vo) throws Exception{
		return (EnvPatentVO)selectByPk("envDAO.envPatentInfoByRank", vo);
	}
	
	public void insertEnvPatent(EnvPatentVO vo) throws Exception{
		insert("envDAO.insertEnvPatent", vo);
	}
	
	public void updateEnvPatent(EnvPatentVO vo) throws Exception{
		update("envDAO.updateEnvPatent", vo);
	}
	
	public void deleteEnvPatent(EnvPatentVO vo) throws Exception{
		delete("envDAO.deleteEnvPatent", vo);
	}
	
	public void moveEnvPatent(EnvPatentVO vo) throws Exception{
		update("envDAO.moveEnvPatent", vo);
	}
	
	public int envMaxCount() throws Exception{
		return (Integer)getSqlMapClientTemplate().queryForObject("envDAO.envMaxCount", null);
	}
	
	public int envMaxRank() throws Exception{
		return (Integer)getSqlMapClientTemplate().queryForObject("envDAO.envMaxRank", null);
	}
	
	public void updateStep(EnvPatentVO vo) throws Exception{
		update("envDAO.updateStep", vo);
	}
	
	public void stepUp(EnvPatentVO vo) throws Exception{
		update("envDAO.stepUp", vo);
	}
}
