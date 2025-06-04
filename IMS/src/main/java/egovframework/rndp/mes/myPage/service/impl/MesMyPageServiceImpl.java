package egovframework.rndp.mes.myPage.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.rndp.mes.common.service.impl.MesCommonDAO;
import egovframework.rndp.mes.myPage.service.MesMyPageService;
import egovframework.rndp.mes.myPage.service.MesMyPageVO;

@Service("mesMyPageService")
public class MesMyPageServiceImpl implements MesMyPageService{

	/*공통사용 DAO common 및  Egov 공용 */
	@Resource(name = "mesCommonDAO")
	private MesCommonDAO mesCommonDAO; 
	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;
	/*공통사용 DAO common 및  Egov 공용 */ 
	@Resource(name = "mesMyPageDAO")
	private MesMyPageDAO mesMyPageDAO;
	
	@Override
	public MesMyPageVO selectMyPageInfo(MesMyPageVO vo) throws Exception{
		return mesMyPageDAO.selectMyPageInfo(vo);
	}
	
	public void updateMyPageInfo(MesMyPageVO vo) throws Exception{
		mesMyPageDAO.updateMyPageInfo(vo);
	}
}
