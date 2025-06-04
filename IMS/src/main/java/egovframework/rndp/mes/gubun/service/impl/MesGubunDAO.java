package egovframework.rndp.mes.gubun.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
 
import egovframework.rndp.mes.gubun.service.MesGubunVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;


@Repository("mesGubunDAO")
public class MesGubunDAO extends EgovAbstractDAO{
	
	// 안상현
	public List selectMesGubunCateList() throws Exception {
        return list("mesGubunDAO.selectMesGubunCateList");
    }
	
	public void insertMesGubun(MesGubunVO mesGubunVO) throws Exception{
		insert("mesGubunDAO.insertMesGubun", mesGubunVO);
	}
	
	public List selectMesGubunList(MesGubunVO mesGubunVO) throws Exception{
		return list("mesGubunDAO.selectMesGubunList", mesGubunVO);
	}
	
	public int selectMesGubunListCnt(MesGubunVO mesGubunVO) throws Exception{
		return (int) select("mesGubunDAO.selectMesGubunListCnt", mesGubunVO);
	}
	
	public MesGubunVO selectMesGubunInfo(MesGubunVO mesGubunVO) throws Exception{
		return (MesGubunVO) select("mesGubunDAO.selectMesGubunInfo", mesGubunVO);
	}
	
	public void deleteMesGubun(MesGubunVO mesGubunVO) throws Exception{
		delete("mesGubunDAO.deleteMesGubun", mesGubunVO);
	}
	
	public void updateMesSGubun(MesGubunVO mesGubunVO) throws Exception{
		update("mesGubunDAO.updateMesSGubun", mesGubunVO);
	}
	
	public List selectMesGubunCodeList(MesGubunVO mesGubunVO) throws Exception{
		return list("mesGubunDAO.selectMesGubunCodeList", mesGubunVO);
	}
}
