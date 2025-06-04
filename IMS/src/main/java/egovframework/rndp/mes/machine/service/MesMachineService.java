package egovframework.rndp.mes.machine.service;

import java.util.List;

public interface MesMachineService {
	
	public List selectMachineDev1List (MesMachineVO vo) throws Exception;
	public List selectSideTapingList (MesMachineVO vo)throws Exception;
	
	public List selectGyobangiLfList (MesMachineVO vo)throws Exception;
	public List selectGyobangiVfList (MesMachineVO vo)throws Exception;
	
	public int selectSideTapingListCnt () throws Exception;
	public int selectGyobangiLfListCnt () throws Exception;
	
	public List selectMachineList (MesMachineVO vo)throws Exception;
	public int selectMachineListCnt (MesMachineVO vo)throws Exception;
	
	public List selectSnakGimList (MesMachineVO vo)throws Exception;
	public int selectSnakGimListCnt (MesMachineVO vo)throws Exception;
	
	public List selectCanList (MesMachineVO vo)throws Exception;
	public int selectCanListCnt (MesMachineVO vo)throws Exception;
	public List selectPojangList (MesMachineVO vo)throws Exception;
	public int selectPojangListCnt (MesMachineVO vo)throws Exception;

	List selectYonjupProd(MesMachineVO mesMachineVO);

	int selectYonjupProdCnt(MesMachineVO mesMachineVO);

	List selectAfterprocessPerDay(MesMachineVO mesMachineVO);

	List selectAfterprocessPerDay2(MesMachineVO mesMachineVO);
	List selectAfterprocessPerDay3(MesMachineVO mesMachineVO);
}
