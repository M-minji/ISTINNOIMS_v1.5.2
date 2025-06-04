package egovframework.rndp.mes.statistics.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rndp.mes.statistics.service.MesStatisticsService;
import egovframework.rndp.mes.statistics.service.MesStatisticsVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("mesStatisticsService")
public class MesStatisticsServiceImpl implements MesStatisticsService  {
	

    @Resource(name="mesStatisticsDAO")
    private MesStatisticsDAO mesStatisticsDAO;

	@Override
	public List selectMesStatisticsList(MesStatisticsVO mesStatisticsVO) throws Exception {
		return mesStatisticsDAO.selectMesStatisticsList(mesStatisticsVO);
	}

	@Override
	public int selectMesStatisticsListCnt(MesStatisticsVO mesStatisticsVO) throws Exception {
		return mesStatisticsDAO.selectMesStatisticsListCnt(mesStatisticsVO);
	}

	@Override
	public List selectMesMaintanceMemList(MesStatisticsVO mesStatisticsVO) throws Exception {
		return mesStatisticsDAO.selectMesMaintanceMemList(mesStatisticsVO);
	}

	@Override
	public List selectMesMaintanceSrList(MesStatisticsVO mesStatisticsVO) throws Exception {
		return mesStatisticsDAO.selectMesMaintanceSrList(mesStatisticsVO);
	}

	@Override
	public List selectAssetList(MesStatisticsVO mesStatisticsVO) throws Exception {
		return mesStatisticsDAO.selectAssetList(mesStatisticsVO);
	}

	@Override
	public List selectAssetMcList(MesStatisticsVO mesStatisticsVO) throws Exception {
		return mesStatisticsDAO.selectAssetMcList(mesStatisticsVO);
	}

	@Override
	public List selectIEList(MesStatisticsVO mesStatisticsVO) throws Exception {
		return mesStatisticsDAO.selectIEList(mesStatisticsVO);
	}
	
	@Override
	public List selectAssetUsList(MesStatisticsVO mesStatisticsVO) throws Exception {
		return mesStatisticsDAO.selectAssetUsList(mesStatisticsVO);
	}
	
	@Override
	public List selectAssetUsMcList(MesStatisticsVO mesStatisticsVO) throws Exception {
		return mesStatisticsDAO.selectAssetUsMcList(mesStatisticsVO);
	}
	
	@Override
	public List selectIEUsList(MesStatisticsVO mesStatisticsVO) throws Exception {
		return mesStatisticsDAO.selectIEUsList(mesStatisticsVO);
	}

	@Override
	public List selectOutputYsList(MesStatisticsVO mesStatisticsVO) throws Exception {
		return mesStatisticsDAO.selectOutputYsList(mesStatisticsVO);
	}

	@Override
	public List selectOutputMsList(MesStatisticsVO mesStatisticsVO) throws Exception {
		return mesStatisticsDAO.selectOutputMsList(mesStatisticsVO);
	}

	@Override
	public List selectOutputsMemList(MesStatisticsVO mesStatisticsVO) throws Exception {
		return mesStatisticsDAO.selectOutputsMemList(mesStatisticsVO);
	}

	@Override
	public List firstValuList(MesStatisticsVO mesStatisticsVO) throws Exception {
		// TODO Auto-generated method stub
		return mesStatisticsDAO.firstValuList(mesStatisticsVO);
	}

	@Override
	public List secondMonthsValuList(MesStatisticsVO mesStatisticsVO) throws Exception {
		// TODO Auto-generated method stub
		return mesStatisticsDAO.secondMonthsValuList(mesStatisticsVO);
	}

	@Override
	public List secondDaysValuList(MesStatisticsVO mesStatisticsVO) throws Exception {
		// TODO Auto-generated method stub
		return mesStatisticsDAO.secondDaysValuList(mesStatisticsVO);
	}

	@Override
	public List secondValuList(MesStatisticsVO mesStatisticsVO) throws Exception {
		// TODO Auto-generated method stub
		return mesStatisticsDAO.secondValuList(mesStatisticsVO);
	}

	@Override
	public List eAssetMakerList(MesStatisticsVO mesStatisticsVO) throws Exception {
		// TODO Auto-generated method stub
		return mesStatisticsDAO.eAssetMakerList(mesStatisticsVO);
	}

	@Override
	public List eAssetTypeList(MesStatisticsVO mesStatisticsVO) throws Exception {
		// TODO Auto-generated method stub
		return mesStatisticsDAO.eAssetTypeList(mesStatisticsVO);
	}

	@Override
	public List selectOutputList(MesStatisticsVO mesStatisticsVO) throws Exception {
		// TODO Auto-generated method stub
		return mesStatisticsDAO.selectOutputList(mesStatisticsVO);
	}

	@Override
	public List selectProjectList(MesStatisticsVO mesStatisticsVO) throws Exception {
		// TODO Auto-generated method stub
		return mesStatisticsDAO.selectProjectList(mesStatisticsVO);
	}
	
	@Override
	public List eAssetTmpMakerList(MesStatisticsVO mesStatisticsVO) throws Exception {
		// TODO Auto-generated method stub
		return mesStatisticsDAO.eAssetTmpMakerList(mesStatisticsVO);
	}

	@Override
	public List eAssetTmpTypeList(MesStatisticsVO mesStatisticsVO) throws Exception {
		// TODO Auto-generated method stub
		return mesStatisticsDAO.eAssetTmpTypeList(mesStatisticsVO);
	}

}
