package egovframework.rndp.mes.staff.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.rndp.mes.common.service.impl.MesCommonDAO;
import egovframework.rndp.mes.staff.service.MesStaffService;
import egovframework.rndp.mes.staff.service.MesStaffVO;


@Service("mesStaffService")
public class MesStaffServiceImpl implements MesStaffService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MesStaffServiceImpl.class);

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
	
	@Resource(name = "mesStaffDAO")
	private MesStaffDAO mesStaffDAO;
	
	 
	
	@Override
	public List selectStaffListByPos(MesStaffVO vo) throws Exception{
		return mesStaffDAO.selectStaffListByPos(vo);
	}
	
	
}
