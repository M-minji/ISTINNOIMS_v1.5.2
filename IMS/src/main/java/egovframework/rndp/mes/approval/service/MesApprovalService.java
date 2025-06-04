package egovframework.rndp.mes.approval.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;



public interface MesApprovalService {

	public List mesApprovalList(MesApprovalVO mesApprovalVO) throws Exception;

	public int mesApprovalListCnt(MesApprovalVO mesApprovalVO) throws Exception;

	public List mesApprovalNameList(MesApprovalVO mesApprovalVO) throws Exception; 
	 
	
	
}
