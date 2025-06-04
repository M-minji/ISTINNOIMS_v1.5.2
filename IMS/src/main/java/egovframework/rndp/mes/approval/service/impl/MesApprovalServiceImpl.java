package egovframework.rndp.mes.approval.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.rndp.com.utl.EgovStringUtil;
import egovframework.rndp.mes.approval.service.MesApprovalService;
import egovframework.rndp.mes.approval.service.MesApprovalVO;
import egovframework.rndp.mes.asset.service.MesAssetService;
import egovframework.rndp.mes.asset.service.MesAssetVO;
import egovframework.rndp.mes.inspection.service.MesInspectionService;
import egovframework.rndp.mes.inspection.service.MesInspectionVO;
import egovframework.rndp.mes.issue.service.MesIssueVO;
import egovframework.rndp.mes.position.service.MesPositionVO;
import egovframework.rndp.mes.sign.service.MesSignService;

@Service("mesApprovalService")
public class MesApprovalServiceImpl implements MesApprovalService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MesApprovalServiceImpl.class);

	@Resource(name = "mesApprovalDAO")
	private MesApprovalDAO mesApprovalDAO;
	
	@Resource(name = "mesSignService")
	private MesSignService mesSignService;
	
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;
	
	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	@Override
	public List mesApprovalList(MesApprovalVO mesApprovalVO) throws Exception {
		// TODO Auto-generated method stub
		return mesApprovalDAO.mesApprovalList(mesApprovalVO);
	}

	@Override
	public int mesApprovalListCnt(MesApprovalVO mesApprovalVO) throws Exception {
		// TODO Auto-generated method stub
		return mesApprovalDAO.mesApprovalListCnt(mesApprovalVO);
	}

	@Override
	public List mesApprovalNameList(MesApprovalVO mesApprovalVO) throws Exception {
		// TODO Auto-generated method stub
		return mesApprovalDAO.mesApprovalNameList(mesApprovalVO);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 
	
	
}
