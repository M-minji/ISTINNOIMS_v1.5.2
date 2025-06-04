package egovframework.rndp.mes.statistics.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rndp.mes.statistics.service.MesStatisticsVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("mesStatisticsDAO")
public class MesStatisticsDAO extends EgovAbstractDAO{

	public List selectMesStatisticsList(MesStatisticsVO mesStatisticsVO) throws Exception {
		return list("mesStatisticsDAO.selectMesStatisticsList", mesStatisticsVO);
	}

	public int selectMesStatisticsListCnt(MesStatisticsVO mesStatisticsVO) throws Exception {
		return (int) select("mesStatisticsDAO.selectMesStatisticsListCnt", mesStatisticsVO);
	}

	public List selectMesMaintanceMemList(MesStatisticsVO mesStatisticsVO) {
		return list("mesStatisticsDAO.selectMesMaintanceMemList", mesStatisticsVO);
	}

	public List selectMesMaintanceSrList(MesStatisticsVO mesStatisticsVO) {
		return list("mesStatisticsDAO.selectMesMaintanceSrList", mesStatisticsVO);
	}

	public List selectAssetList(MesStatisticsVO mesStatisticsVO) {
		return list("mesStatisticsDAO.selectAssetList", mesStatisticsVO);
	}

	public List selectAssetMcList(MesStatisticsVO mesStatisticsVO) {
		return list("mesStatisticsDAO.selectAssetMcList", mesStatisticsVO);
	}

	public List selectIEList(MesStatisticsVO mesStatisticsVO) {
		return list("mesStatisticsDAO.selectIEList", mesStatisticsVO);
	}
	
	public List selectAssetUsList(MesStatisticsVO mesStatisticsVO) {
		return list("mesStatisticsDAO.selectAssetUsList", mesStatisticsVO);
	}
	
	public List selectAssetUsMcList(MesStatisticsVO mesStatisticsVO) {
		return list("mesStatisticsDAO.selectAssetUsMcList", mesStatisticsVO);
	}
	
	public List selectIEUsList(MesStatisticsVO mesStatisticsVO) {
		return list("mesStatisticsDAO.selectIEUsList", mesStatisticsVO);
	}

	public List selectOutputYsList(MesStatisticsVO mesStatisticsVO) {
		return list("mesStatisticsDAO.selectOutputYsList", mesStatisticsVO);
	}

	public List selectOutputMsList(MesStatisticsVO mesStatisticsVO) {
		return list("mesStatisticsDAO.selectOutputMsList", mesStatisticsVO);
	}

	public List selectOutputsMemList(MesStatisticsVO mesStatisticsVO) {
		return list("mesStatisticsDAO.selectOutputsMemList", mesStatisticsVO);
	}

	public List firstValuList(MesStatisticsVO mesStatisticsVO) throws Exception {
		// TODO Auto-generated method stub
		return list("mesStatisticsDAO.firstValuList", mesStatisticsVO);
	}

	public List secondMonthsValuList(MesStatisticsVO mesStatisticsVO) throws Exception {
		// TODO Auto-generated method stub
		return list("mesStatisticsDAO.secondMonthsValuList", mesStatisticsVO);
	}

	public List secondDaysValuList(MesStatisticsVO mesStatisticsVO) throws Exception {
		// TODO Auto-generated method stub
		return list("mesStatisticsDAO.secondDaysValuList", mesStatisticsVO);
	}

	public List secondValuList(MesStatisticsVO mesStatisticsVO) throws Exception {
		// TODO Auto-generated method stub
		return list("mesStatisticsDAO.secondValuList", mesStatisticsVO);
	}

	public List eAssetMakerList(MesStatisticsVO mesStatisticsVO) throws Exception {
		// TODO Auto-generated method stub
		return list("mesStatisticsDAO.eAssetMakerList", mesStatisticsVO);
	}

	public List eAssetTypeList(MesStatisticsVO mesStatisticsVO) throws Exception {
		// TODO Auto-generated method stub
		return list("mesStatisticsDAO.eAssetTypeList", mesStatisticsVO);
	}

	public List selectOutputList(MesStatisticsVO mesStatisticsVO) throws Exception {
		// TODO Auto-generated method stub
		return list("mesStatisticsDAO.selectOutputList", mesStatisticsVO);
	}

	public List selectProjectList(MesStatisticsVO mesStatisticsVO) throws Exception {
		// TODO Auto-generated method stub
		return list("mesStatisticsDAO.selectProjectList", mesStatisticsVO);
	}

	public List eAssetTmpMakerList(MesStatisticsVO mesStatisticsVO) throws Exception {
		// TODO Auto-generated method stub
		return list("mesStatisticsDAO.eAssetTmpMakerList", mesStatisticsVO);
	}

	public List eAssetTmpTypeList(MesStatisticsVO mesStatisticsVO) throws Exception {
		// TODO Auto-generated method stub
		return list("mesStatisticsDAO.eAssetTmpTypeList", mesStatisticsVO);
	}

}
