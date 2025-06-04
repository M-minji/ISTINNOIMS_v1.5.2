package egovframework.rndp.mes.contact.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rndp.mes.contact.service.MesContactVO;
import egovframework.rndp.mes.gubun.service.MesGubunVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;


@Repository("mesContactDAO")
public class MesContactDAO extends EgovAbstractDAO{

	public void mesContactInsert(MesContactVO mesContactVO) throws Exception{
		// TODO Auto-generated method stub
		insert("mesContactDAO.mesContactInsert", mesContactVO);
	}

	public List mesContactInfoList(MesContactVO mesContactVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesContactDAO.mesContactInfoList",mesContactVO);
	}

	public int mesContactInfoListCnt(MesContactVO mesContactVO) throws Exception{
		// TODO Auto-generated method stub
		return (int) select("mesContactDAO.mesContactInfoListCnt", mesContactVO);
	}

	public MesContactVO mesContactInfo(MesContactVO mesContactVO) throws Exception{
		// TODO Auto-generated method stub
		return (MesContactVO) select("mesContactDAO.mesContactInfo", mesContactVO);
	}

	public void mesContactDelete(MesContactVO mesContactVO) throws Exception{
		// TODO Auto-generated method stub
		delete("mesGubunDAO.mesContactDelete", mesContactVO);
	}

	public void mesContactUpdate(MesContactVO mesContactVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesGubunDAO.mesContactUpdate", mesContactVO);
	}

	public List mesContactInfoPopupList(MesContactVO mesContactVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesContactDAO.mesContactInfoList",mesContactVO);
	}
	public int mesContactInfoPopupListCnt(MesContactVO mesContactVO) throws Exception{
		// TODO Auto-generated method stub
		return (int) select("mesContactDAO.mesContactInfoListCnt", mesContactVO);
	}
	 
}
