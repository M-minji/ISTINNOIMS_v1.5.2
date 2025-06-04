package egovframework.rndp.mes.position.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.rndp.mes.position.service.MesPositionService;
import egovframework.rndp.mes.position.service.MesPositionVO;

@Service("mesPositionService")
public class MesPositionServiceImpl implements MesPositionService{
	
	@Resource(name = "mesPositionDAO")
	private MesPositionDAO mesPositionDAO;
	
	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;


	@Override
	public List selectPositionList(MesPositionVO mesPositionVO) throws Exception {
		return mesPositionDAO.selectPositionList(mesPositionVO);
	}

	@Override
	public List selectUpdatePositionList(MesPositionVO mesPositionVO) {
		// TODO Auto-generated method stub
		return mesPositionDAO.selectUpdatePositionList(mesPositionVO);
	}

	@Override
	public String getMaxPositionKey(MesPositionVO mesPositionVO) throws Exception {
		// TODO Auto-generated method stub
		return mesPositionDAO.getMaxPositionKey(mesPositionVO);
	}

	@Override
	public String getPositionPath(MesPositionVO mesPositionVO) throws Exception {
		// TODO Auto-generated method stub
		return mesPositionDAO.getPositionPath(mesPositionVO);
	}

	@Override
	public String savePositionAjax(MesPositionVO mesPositionVO) throws Exception {
		// TODO Auto-generated method stub
		mesPositionDAO.deletePositionAjax(mesPositionVO);
		

		mesPositionVO.setkPositionKey(mesPositionDAO.getMaxPositionKey(mesPositionVO));
		mesPositionDAO.savePositionAjax(mesPositionVO);
		
		return mesPositionVO.getkPositionKey();
	}

	@Override
	public void deletePositionAjax(MesPositionVO mesPositionVO) throws Exception {
		// TODO Auto-generated method stub
		mesPositionDAO.deletePositionAjax(mesPositionVO);
		mesPositionDAO.deletePositionHighAjax(mesPositionVO);
		mesPositionDAO.deletePositionHighAjax(mesPositionVO);
	}
}
