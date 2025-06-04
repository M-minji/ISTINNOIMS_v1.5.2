package egovframework.rndp.admin.env.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.utl.sim.service.EgovFileTool;
import egovframework.rndp.admin.env.service.EnvHistoryVO;
import egovframework.rndp.admin.env.service.EnvPatentVO;
import egovframework.rndp.admin.env.service.EnvRecruitVO;
import egovframework.rndp.admin.env.service.EnvService;
import egovframework.rndp.admin.env.service.EnvVO;
import egovframework.rndp.com.cmm.service.EgovProperties;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("envService")
public class EnvServiceImpl extends EgovAbstractServiceImpl implements EnvService{

	@Resource(name="envDAO")
	private EnvDAO envDAO;
	
	@Override
	public List envList() throws Exception {
		return envDAO.envList();
	}

	@Override
	public void updateEnv(EnvVO vo) throws Exception {
		envDAO.updateEnv(vo);
	}

	@Override
	public void deleteRealPathFile(EnvVO vo) throws Exception {

		String filePath = EgovProperties.getProperty("fileStorePath");	
		filePath += envDAO.getFilePath(vo); 
		
		EgovFileTool.deleteFile(filePath);
		envDAO.updateEnv(vo);
	}
	
	@Override
	public List patentList() throws Exception {
		return envDAO.patentList();
	}

	@Override
	public List envHistoryList() throws Exception {
		return envDAO.envHistoryList();
	}

	@Override
	public List envRecruitList() throws Exception {
		return envDAO.envRecruitList();
	}

	@Override
	public void insertEnvHistory(EnvHistoryVO vo) throws Exception {
		envDAO.insertEnvHistory(vo);
	}

	@Override
	public void updateEnvHistory(EnvHistoryVO vo) throws Exception {
		envDAO.updateEnvHistory(vo);
	}

	@Override
	public EnvHistoryVO envHistoryInfo(int key) throws Exception {
		return envDAO.envHistoryInfo(key);
	}

	@Override
	public void uptHistoryVisible(EnvHistoryVO vo) throws Exception {
		envDAO.uptHistoryVisible(vo);
	}

	@Override
	public void deleteHistory(int key) throws Exception {
		envDAO.deleteHistory(key);
	}

	@Override
	public int getMaxRank() throws Exception {
		return envDAO.getMaxRank();
	}

	@Override
	public void patentInsert(EnvPatentVO vo) throws Exception {
		envDAO.patentInsert(vo);
	}

	@Override
	public EnvPatentVO envPatentByInfo(int key) throws Exception {
		return envDAO.envPatentByInfo(key);
	}

	@Override
	public void envPatentUpt(EnvPatentVO vo) throws Exception {
		envDAO.envPatentUpt(vo);
	}

	@Override
	public List patentOrderByRank() throws Exception {
		return envDAO.patentOrderByRank();
	}

	@Override
	public EnvPatentVO patentByKeyTitle(EnvPatentVO vo) throws Exception {
		return envDAO.patentByKeyTitle(vo);
	}

	@Override
	public void movePatentUpt(EnvPatentVO vo) throws Exception {
		EnvPatentVO pVO = new EnvPatentVO();
		pVO.setStrRank1(-1);
		pVO.setStrRank2(vo.getRank());
		envDAO.movePatentUpt(pVO);
		envDAO.uptStep(pVO);
		pVO.setStrRank1(vo.getRank2());
		pVO.setStrRank2(-1);
		envDAO.movePatentUpt(pVO);
		pVO.setStrRank1(1);
		pVO.setStrRank2(vo.getRank2());
		envDAO.uptStep(pVO);
		
	}

	@Override
	public void envPatentDelete(int key) throws Exception {
		envDAO.envPatentDelete(key);
	}

	@Override
	public void envRecruitDelete(int key) throws Exception {
		envDAO.envRecruitDelete(key);
	}

	@Override
	public void recruitActiveUpt(EnvRecruitVO vo) throws Exception {
		envDAO.recruitActiveUpt(vo);
	}

	@Override
	public EnvRecruitVO recruitInfo(int key) throws Exception {
		return envDAO.recruitInfo(key);
	}

	@Override
	public void recruitUpdate(EnvRecruitVO vo) throws Exception {
		envDAO.recruitUpdate(vo);
	}

	@Override
	public void reecruitInsert(EnvRecruitVO vo) throws Exception {
		envDAO.reecruitInsert(vo);
	}

	@Override
	public List hisVisibleInfo() throws Exception {
		
		return envDAO.hisVisibleInfo();
	}

	@Override
	public List getYearList() throws Exception {
		return envDAO.getYearList();
	}

	@Override
	public List getMmList() throws Exception {
		return envDAO.getMmList();
	}


}
