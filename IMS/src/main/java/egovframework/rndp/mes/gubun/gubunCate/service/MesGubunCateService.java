package egovframework.rndp.mes.gubun.gubunCate.service;


import java.util.List;
//import egovframework.rndp.intra.sales.gubun.service.S_GubunVO;


public interface MesGubunCateService {
	// 안상현 
	public int selectGubunCheck(MesGubunCateVO mesGubunCateVO) throws Exception;
	
	public void insertMesGubunCate(MesGubunCateVO mesGubunCateVO) throws Exception;
	
	public List selectMesGubunCateList(MesGubunCateVO mesGubunCateVO) throws Exception;
	
	public int selectMesGubunCateListCnt(MesGubunCateVO mesGubunCateVO) throws Exception;
	
	public MesGubunCateVO selectMesGubunCateInfo(MesGubunCateVO mesGubunCateVO) throws Exception;
	
	public void deleteMesGubunCate(MesGubunCateVO mesGubunCateVO) throws Exception;
	
	public void updateMesGubunCate(MesGubunCateVO mesGubunCateVO) throws Exception;
	
}
