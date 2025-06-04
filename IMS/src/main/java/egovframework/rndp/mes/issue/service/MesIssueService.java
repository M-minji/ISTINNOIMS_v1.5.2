package egovframework.rndp.mes.issue.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.service.FileVO;
import egovframework.rndp.mes.asset.service.MesAssetVO;
import egovframework.rndp.mes.gubun.service.MesGubunVO;
import egovframework.rndp.mes.issue.service.impl.MesIssueDAO;

public interface MesIssueService {
	
	public List eFileInfoList(MesIssueVO mesIssueVO) throws Exception;

	public void insertIssueInfo(MesIssueVO mesIssueVO) throws Exception;

	public List mesIssueInfoList(MesIssueVO mesIssueVO) throws Exception;

	public int mesIssueInfoListCnt(MesIssueVO mesIssueVO) throws Exception;

	public MesIssueVO eSelectIssueInfo(MesIssueVO mesIssueVO) throws Exception;

	public List eSelectIssueInfoAssetList(MesIssueVO mesIssueVO) throws Exception;

	public void updateIssueInfo(MesIssueVO mesIssueVO) throws Exception;

	public void deleteIssueInfo(MesIssueVO mesIssueVO) throws Exception;

	public void eIssueProcessUpdate(MesIssueVO mesIssueVO) throws Exception;

	public List eMainIssueInfoList(MesIssueVO mesIssueVO) throws Exception;

	public MesIssueVO eMainIssueInfo(MesIssueVO mesIssueVO) throws Exception;

	public MesIssueVO eMainTotalsInfo(MesIssueVO mesIssueVO) throws Exception;

	public void mesSignIssueStatus(MesIssueVO mesIssueVO) throws Exception;

	public void mesUpdateSignStatus(MesIssueVO mesIssueVO) throws Exception;

	public List mesIssueExcelInfoList(MesIssueVO mesIssueVO) throws Exception;

	public List eIssueHandlerInfoList(MesIssueVO mesIssueVO) throws Exception;
	
}
