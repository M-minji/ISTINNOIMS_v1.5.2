package egovframework.rndp.mes.issue.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rndp.mes.gubun.service.MesGubunVO;
import egovframework.rndp.mes.issue.service.MesIssueVO;
import egovframework.rndp.mes.maintance.service.MesMaintanceVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("mesIssueDAO")
public class MesIssueDAO extends EgovAbstractDAO{

	public void insertIssueInfo(MesIssueVO mesIssueVO) throws Exception{
		// TODO Auto-generated method stub
		insert("mesIssueDAO.insertIssueInfo", mesIssueVO);
	}

	public void insertIssueAssetInfo(MesIssueVO mesIssueVO) throws Exception{
		// TODO Auto-generated method stub
		insert("mesIssueDAO.insertIssueAssetInfo", mesIssueVO);
	}

	public List mesIssueInfoList(MesIssueVO mesIssueVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesIssueDAO.mesIssueInfoList", mesIssueVO);
	}

	public int mesIssueInfoListCnt(MesIssueVO mesIssueVO) throws Exception{
		// TODO Auto-generated method stub
		return (int) select("mesIssueDAO.mesIssueInfoListCnt", mesIssueVO);
	}

	public MesIssueVO eSelectIssueInfo(MesIssueVO mesIssueVO) throws Exception{
		// TODO Auto-generated method stub
		return (MesIssueVO) select("mesIssueDAO.eSelectIssueInfo", mesIssueVO);
	}

	public List eSelectIssueInfoAssetList(MesIssueVO mesIssueVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesIssueDAO.eSelectIssueInfoAssetList", mesIssueVO);
	}

	public void eIssueStatusUpdate(MesIssueVO mesIssueVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesIssueDAO.eIssueStatusUpdate", mesIssueVO);
	}

	public void deleteIssueAssetInfo(MesIssueVO mesIssueVO) throws Exception{
		// TODO Auto-generated method stub
		delete("mesIssueDAO.deleteIssueAssetInfo", mesIssueVO);
	}

	public void eIssueProcessUpdate(MesIssueVO mesIssueVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesIssueDAO.eIssueProcessUpdate", mesIssueVO);
	}

	public void eIssueInfoUpdate(MesIssueVO mesIssueVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesIssueDAO.eIssueInfoUpdate", mesIssueVO);
	}

	public List eMainIssueInfoList(MesIssueVO mesIssueVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesIssueDAO.eMainIssueInfoList", mesIssueVO);
	}

	public MesIssueVO eMainIssueInfo(MesIssueVO mesIssueVO) throws Exception{
		// TODO Auto-generated method stub
		return (MesIssueVO) select("mesIssueDAO.eMainIssueInfo", mesIssueVO);
	}

	public MesIssueVO eMainTotalsInfo(MesIssueVO mesIssueVO) throws Exception{
		// TODO Auto-generated method stub
		return (MesIssueVO) select("mesIssueDAO.eMainTotalsInfo", mesIssueVO);
	}

	public void mesSignIssueStatus(MesIssueVO mesIssueVO)  throws Exception{
		// TODO Auto-generated method stub
		update("mesIssueDAO.mesSignIssueStatus", mesIssueVO);
	}

	public List mesIssueExcelInfoList(MesIssueVO mesIssueVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesIssueDAO.mesIssueExcelInfoList", mesIssueVO);
	}

	public void insertIssueHandlerInfo(MesIssueVO mesIssueVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesIssueDAO.insertIssueHandlerInfo", mesIssueVO);
	}

	public List eIssueHandlerInfoList(MesIssueVO mesIssueVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesIssueDAO.eIssueHandlerInfoList", mesIssueVO);
	}

	public void eIssueHandlerInfoDelete(MesIssueVO mesIssueVO) throws Exception{
		// TODO Auto-generated method stub
		delete("mesIssueDAO.eIssueHandlerInfoDelete", mesIssueVO);
	}
	
	public void issueFileInsert(MesIssueVO mesIssueVO) throws Exception{
		// TODO Auto-generated method stub
		insert("mesIssueDAO.IssueFileInsert", mesIssueVO);
	}
	
	public List eFileInfoList(MesIssueVO mesIssueVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesIssueDAO.eFileInfoList", mesIssueVO);
	}
	
	public void issueFileDelete(MesIssueVO mesIssueVO) throws Exception{
		// TODO Auto-generated method stub
		delete("mesIssueDAO.IssueFileDelete", mesIssueVO);
	}

}