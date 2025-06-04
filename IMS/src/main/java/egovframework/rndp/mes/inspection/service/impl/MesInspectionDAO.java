package egovframework.rndp.mes.inspection.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rndp.mes.asset.service.MesAssetVO;
import egovframework.rndp.mes.inspection.service.MesInspectionVO;
import egovframework.rndp.mes.user.service.MesUserVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("mesInspectionDAO")
public class MesInspectionDAO extends EgovAbstractDAO{

	public void eInspectionInfoInsert(MesInspectionVO mesInspectionVO) throws Exception{
		// TODO Auto-generated method stub
		insert("mesInspectionDAO.eInspectionInfoInsert", mesInspectionVO);
	}

	public void eInspectionFileInsert(MesInspectionVO mesInspectionVO) throws Exception{
		// TODO Auto-generated method stub
		insert("mesInspectionDAO.eInspectionFileInsert", mesInspectionVO);
	}

	public List mesInspectiontList(MesInspectionVO mesInspectionVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesInspectionDAO.mesInspectiontList", mesInspectionVO);
	}

	public int mesInspectiontListCnt(MesInspectionVO mesInspectionVO) throws Exception{
		// TODO Auto-generated method stub
		return (int) select("mesInspectionDAO.mesInspectiontListCnt", mesInspectionVO);
	}

	public MesInspectionVO eInspectionInfo(MesInspectionVO mesInspectionVO) throws Exception{
		// TODO Auto-generated method stub
		return (MesInspectionVO) select("mesInspectionDAO.eInspectionInfo", mesInspectionVO);
	}

	public List eFileInfoList(MesInspectionVO mesInspectionVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesInspectionDAO.eFileInfoList", mesInspectionVO);
	}

	public void eInspectionUpdate(MesInspectionVO mesInspectionVO) throws Exception{
		// TODO Auto-generated method stub
		insert("mesInspectionDAO.eInspectionUpdate", mesInspectionVO);
	}

	public void eInspectionResultInsert(MesInspectionVO mesInspectionVO) throws Exception{
		// TODO Auto-generated method stub
		insert("mesInspectionDAO.eInspectionResultInsert", mesInspectionVO);
	}

	public List eResultInfoList(MesInspectionVO mesInspectionVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesInspectionDAO.eResultInfoList", mesInspectionVO);
	}

	public void eInspectionInfoDelete(MesInspectionVO mesInspectionVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesInspectionDAO.eInspectionInfoDelete", mesInspectionVO);
	}

	public void eInspectionInfoUpdate(MesInspectionVO mesInspectionVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesInspectionDAO.eInspectionInfoUpdate", mesInspectionVO);
	}

	public void eInspectionFileDelete(MesInspectionVO mesInspectionVO) throws Exception{
		// TODO Auto-generated method stub
		delete("mesInspectionDAO.eInspectionFileDelete", mesInspectionVO);
	}

	public void eInspectionAssetDelete(MesInspectionVO mesInspectionVO) throws Exception{
		// TODO Auto-generated method stub
		delete("mesInspectionDAO.eInspectionAssetDelete", mesInspectionVO);
	}

	public void updateInspectionSingStatus(MesInspectionVO mesInspectionVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesInspectionDAO.updateInspectionSingStatus", mesInspectionVO);
	}

	public List mesInspectiontExcelList(MesInspectionVO mesInspectionVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesInspectionDAO.mesInspectiontExcelList", mesInspectionVO);
	}

	public int selectFieldCount(MesInspectionVO mesInspectionVO) throws Exception{
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject("mesInspectionDAO.selectFieldCount", mesInspectionVO);
	}
	
	public List selectFieldList(MesInspectionVO mesInspectionVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesInspectionDAO.selectFieldList", mesInspectionVO);
	}
	
	public void mesInspectionFieldInsert(MesInspectionVO mesInspectionVO) throws Exception{
		// TODO Auto-generated method stub
		insert("mesInspectionDAO.mesInspectionFieldInsert", mesInspectionVO);
	}
	  
	public void mesInspectionFieldUpdate(MesInspectionVO mesInspectionVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesInspectionDAO.mesInspectionFieldUpdate", mesInspectionVO);
	}
	
	public MesInspectionVO selectFieldInfo(MesInspectionVO mesInspectionVO) throws Exception{
		// TODO Auto-generated method stub
		return (MesInspectionVO) select("mesInspectionDAO.selectFieldInfo", mesInspectionVO);
	}
}
