package egovframework.rndp.mes.machine.service.impl;

import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rndp.mes.machine.service.MesMachineService;
import egovframework.rndp.mes.machine.service.MesMachineVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("mesMachineService")
public class MesMachineServiceImpl implements MesMachineService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MesMachineServiceImpl.class);
	
	@Resource(name = "mesMachineDAO")
	private MesMachineDAO mesMachineDAO;

	@Override
	public List selectMachineDev1List(MesMachineVO vo) throws Exception {
		return mesMachineDAO.selectMachineDev1List(vo);
	}

	@Override
	public List selectSideTapingList(MesMachineVO vo) throws Exception {
		
		
		String keyArray = "";
		List<MesMachineVO> list = mesMachineDAO.selectMachineDev1List(vo);
		for(int i = 0; i < list.size()-1; i++){
				if(Integer.parseInt(list.get(i).geteMachineTmp1()) > Integer.parseInt(list.get(i+1).geteMachineTmp1())){
					keyArray += list.get(i).geteMachineNo() + "_";
					System.out.println("keyArray :  " + keyArray);
				}
		}
		keyArray += list.get(list.size()-1).geteMachineNo();
		
		System.out.println("last key : " + keyArray);
		String[] key = EgovStringUtil.split(keyArray, "_");
		
		
		Map<String,Object> hmap = new HashMap<String,Object>();
		ArrayList<String> keyList = new ArrayList<String>();
		
		for(int i = 0; i < key.length; i++){
			keyList.add(key[i]);
		}
		
		hmap.put("keyList",keyList);
		hmap.put("firstIndex",vo.getFirstIndex());
		hmap.put("recordCountPerPage",vo.getRecordCountPerPage());
		
		return mesMachineDAO.selectSideTapingList(hmap);
	}

	@Override
	public List selectGyobangiLfList(MesMachineVO vo) throws Exception {
		return mesMachineDAO.selectGyobangiLfList(vo);
	}

	@Override
	public List selectGyobangiVfList(MesMachineVO vo) throws Exception {
		return mesMachineDAO.selectGyobangiVfList(vo);
	}

	@Override
	public int selectSideTapingListCnt() throws Exception {
		return mesMachineDAO.selectSideTapingListCnt();
	}

	@Override
	public int selectGyobangiLfListCnt() throws Exception {
		return mesMachineDAO.selectGyobangiLfListCnt();
	}

	@Override
	public List selectMachineList(MesMachineVO vo) throws Exception {
		return mesMachineDAO.selectMachineList(vo);
	}

	@Override
	public int selectMachineListCnt(MesMachineVO vo) throws Exception {
		return mesMachineDAO.selectMachineListCnt(vo);
	}

	@Override
	public List selectSnakGimList(MesMachineVO vo) throws Exception {
		return mesMachineDAO.selectSnakGimList(vo);
	}

	@Override
	public int selectSnakGimListCnt(MesMachineVO vo) throws Exception {
		return mesMachineDAO.selectSnakGimListCnt(vo);
	}

	@Override
	public List selectCanList(MesMachineVO vo) throws Exception {
		return mesMachineDAO.selectCanList(vo);
	}

	@Override
	public int selectCanListCnt(MesMachineVO vo) throws Exception {
		return mesMachineDAO.selectCanListCnt(vo);
	}

	@Override
	public List selectPojangList(MesMachineVO vo) throws Exception {
		return mesMachineDAO.selectPojangList(vo);
	}

	@Override
	public int selectPojangListCnt(MesMachineVO vo) throws Exception {
		return mesMachineDAO.selectPojangListCnt(vo);
	}

	@Override
	public List selectYonjupProd(MesMachineVO vo) {
		return mesMachineDAO.selectYonjupProd(vo);
	}

	@Override
	public int selectYonjupProdCnt(MesMachineVO vo) {
		return mesMachineDAO.selectYonjupProdCnt(vo);
	}

	@Override
	public List selectAfterprocessPerDay(MesMachineVO vo) {
		return mesMachineDAO.selectAfterprocessPerDay(vo);
	}

	@Override
	public List selectAfterprocessPerDay2(MesMachineVO vo) {
		return mesMachineDAO.selectAfterprocessPerDay2(vo);
	}
	@Override
	public List selectAfterprocessPerDay3(MesMachineVO vo) {
		return mesMachineDAO.selectAfterprocessPerDay3(vo);
	}


}
