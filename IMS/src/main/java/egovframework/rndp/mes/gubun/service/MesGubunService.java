package egovframework.rndp.mes.gubun.service;

import java.util.List;
import java.util.Map;

public interface MesGubunService {
	
	// 안상현
	public List selectMesGubunCateList() throws Exception;
	
	public void insertMesGubun(MesGubunVO mesGubunVO) throws Exception;
	
	public List selectMesGubunList(MesGubunVO mesGubunVO) throws Exception;
	
	public int selectMesGubunListCnt(MesGubunVO mesGubunVO) throws Exception;
	
	public MesGubunVO selectMesGubunInfo(MesGubunVO mesGubunVO) throws Exception;
	
	public void deleteMesGubun(MesGubunVO mesGubunVO) throws Exception;
	
	public void updateMesSGubun(MesGubunVO mesGubunVO) throws Exception;
	
	public List selectMesGubunCodeList(MesGubunVO mesGubunVO) throws Exception;
}
