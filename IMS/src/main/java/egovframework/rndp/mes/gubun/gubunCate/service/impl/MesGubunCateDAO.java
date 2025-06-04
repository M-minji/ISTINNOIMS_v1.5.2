package egovframework.rndp.mes.gubun.gubunCate.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;





//import egovframework.rndp.intra.sales.gubun.service.S_GubunVO;//
import egovframework.rndp.mes.gubun.gubunCate.service.MesGubunCateVO;
import egovframework.rndp.mes.common.service.MesCommonGubunVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;



@Repository("mesGubunCateDAO")
public class MesGubunCateDAO extends EgovAbstractDAO{

	// 안상현
	public int selectGubunCheck(MesGubunCateVO mesGubunCateVO) throws Exception{
		return (int) select("mesGubunCateDAO.selectGubunCheck", mesGubunCateVO);
	}
	
	public void insertMesGubunCate(MesGubunCateVO mesGubunCateVO) throws Exception{
		insert("mesgubunCateDAO.insertMesGubunCate", mesGubunCateVO);
	}
	
	public List selectMesGubunCateList(MesGubunCateVO mesGubunCateVO) throws Exception{
		return list("mesGubunCateDAO.selectMesGubunCateList", mesGubunCateVO);
	}
	
	public int selectMesGubunCateListCnt(MesGubunCateVO mesGubunCateVO) throws Exception{
		return (int) select("mesGubunCateDAO.selectMesGubunCateListCnt", mesGubunCateVO);
	}
	
	public MesGubunCateVO selectMesGubunCateInfo(MesGubunCateVO mesGubunCateVO) throws Exception{
		return (MesGubunCateVO) select("mesGubunCateDAO.selectMesGubunCateInfo", mesGubunCateVO);
	}
	
	public void deleteMesGubunCate(MesGubunCateVO mesGubunCateVO) throws Exception{
		delete("mesGubunCateDAO.deleteMesGubunCate", mesGubunCateVO);
	}
	
	public void updateMesGubunCate(MesGubunCateVO mesGubunCateVO) throws Exception{
		update("mesGubunCateDAO.updateMesGubunCate", mesGubunCateVO);
	}
}
