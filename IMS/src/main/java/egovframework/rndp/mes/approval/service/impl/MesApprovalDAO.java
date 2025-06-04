package egovframework.rndp.mes.approval.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rndp.mes.approval.service.MesApprovalVO;
import egovframework.rndp.mes.asset.service.MesAssetVO;
import egovframework.rndp.mes.inspection.service.MesInspectionVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("mesApprovalDAO")
public class MesApprovalDAO extends EgovAbstractDAO{

	public List mesApprovalList(MesApprovalVO mesApprovalVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesApprovalDAO.mesApprovalList", mesApprovalVO);
	}

	public int mesApprovalListCnt(MesApprovalVO mesApprovalVO) throws Exception{
		// TODO Auto-generated method stub
		return (int) select("mesApprovalDAO.mesApprovalListCnt", mesApprovalVO);
	}

	public List mesApprovalNameList(MesApprovalVO mesApprovalVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesApprovalDAO.mesApprovalNameList", mesApprovalVO);
	}
 
	
	  
}
