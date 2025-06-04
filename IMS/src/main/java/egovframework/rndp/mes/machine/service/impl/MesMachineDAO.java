package egovframework.rndp.mes.machine.service.impl;

import egovframework.rndp.mes.machine.service.MesMachineVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("mesMachineDAO")
public class MesMachineDAO extends EgovAbstractDAO{
	
	public List selectMachineDev1List (MesMachineVO vo) throws Exception{
		return list("mesMachineDAO.selectMachineDev1List", vo);
	}
	
	public List selectSideTapingList (Map<String, Object> hmap)throws Exception{
		return list("mesMachineDAO.selectSideTapingList", hmap);
	}

	public List selectGyobangiLfList (MesMachineVO vo)throws Exception{
		return list("mesMachineDAO.selectGyobangiLfList", vo);
	}
	
	public List selectGyobangiVfList (MesMachineVO vo)throws Exception{
		return list("mesMachineDAO.selectGyobangiVfList", vo);
	}
	
	public int selectSideTapingListCnt () throws Exception{
		return (int) select("mesMachineDAO.selectSideTapingListCnt");
	}
	public int selectGyobangiLfListCnt () throws Exception{
		return (int) select("mesMachineDAO.selectGyobangiLfListCnt");
	}
	
	public List selectMachineList (MesMachineVO vo)throws Exception{
		return list("mesMachineDAO.selectMachineList",vo);
	}
	
	public int selectMachineListCnt (MesMachineVO vo)throws Exception{
		return (int) select("mesMachineDAO.selectMachineListCnt",vo);
	}
	
	public List selectSnakGimList (MesMachineVO vo)throws Exception{
		return list("mesMachineDAO.selectSnakGimList",vo);
	}
	
	public int selectSnakGimListCnt (MesMachineVO vo)throws Exception{
		return (int) select("mesMachineDAO.selectSnakGimListCnt",vo);
	}
	
	public List selectCanList (MesMachineVO vo)throws Exception {
		return list("mesMachineDAO.selectCanList",vo);
	}
	
	public int selectCanListCnt (MesMachineVO vo)throws Exception{
		return (int) select("mesMachineDAO.selectCanListCnt",vo);
	}
	
	public List selectPojangList (MesMachineVO vo)throws Exception{
		return list("mesMachineDAO.selectPojangList",vo);
	}
	
	public int selectPojangListCnt (MesMachineVO vo)throws Exception{
		return (int) select("mesMachineDAO.selectPojangListCnt",vo);
	}

	public List selectYonjupProd(MesMachineVO vo) {
		return list("mesMachineDAO.selectYonjupProd",vo);
	}

	public int selectYonjupProdCnt(MesMachineVO vo) {
		return (int) select("mesMachineDAO.selectYonjupProdCnt",vo);
	}

	public List selectAfterprocessPerDay(MesMachineVO vo) {
		return list("mesMachineDAO.selectAfterprocessPerDay",vo);
	}

	public List selectAfterprocessPerDay2(MesMachineVO vo) {
		return list("mesMachineDAO.selectAfterprocessPerDay2",vo);
	}
	public List selectAfterprocessPerDay3(MesMachineVO vo) {
		return list("mesMachineDAO.selectAfterprocessPerDay3",vo);
	}
}
 