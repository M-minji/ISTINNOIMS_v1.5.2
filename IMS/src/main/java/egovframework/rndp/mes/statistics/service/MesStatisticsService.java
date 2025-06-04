package egovframework.rndp.mes.statistics.service;

import java.util.Date;
import java.util.List;

public interface MesStatisticsService {

	public List selectMesStatisticsList(MesStatisticsVO mesStatisticsVO) throws Exception;

	public int selectMesStatisticsListCnt(MesStatisticsVO mesStatisticsVO) throws Exception;

	public List selectMesMaintanceMemList(MesStatisticsVO mesStatisticsVO) throws Exception;

	public List selectMesMaintanceSrList(MesStatisticsVO mesStatisticsVO) throws Exception;

	public List selectAssetList(MesStatisticsVO mesStatisticsVO) throws Exception;

	public List selectAssetMcList(MesStatisticsVO mesStatisticsVO) throws Exception;

	public List selectIEList(MesStatisticsVO mesStatisticsVO) throws Exception;
	
	public List selectAssetUsList(MesStatisticsVO mesStatisticsVO) throws Exception;
	
	public List selectAssetUsMcList(MesStatisticsVO mesStatisticsVO) throws Exception;
	
	public List selectIEUsList(MesStatisticsVO mesStatisticsVO) throws Exception;

	public List selectOutputYsList(MesStatisticsVO mesStatisticsVO) throws Exception;

	public List selectOutputMsList(MesStatisticsVO mesStatisticsVO) throws Exception;

	public List selectOutputsMemList(MesStatisticsVO mesStatisticsVO) throws Exception;

	public List firstValuList(MesStatisticsVO mesStatisticsVO) throws Exception;

	public List secondMonthsValuList(MesStatisticsVO mesStatisticsVO) throws Exception;

	public List secondDaysValuList(MesStatisticsVO mesStatisticsVO) throws Exception;

	public List secondValuList(MesStatisticsVO mesStatisticsVO) throws Exception;

	public List eAssetMakerList(MesStatisticsVO mesStatisticsVO) throws Exception;

	public List eAssetTypeList(MesStatisticsVO mesStatisticsVO) throws Exception;

	public List selectOutputList(MesStatisticsVO mesStatisticsVO) throws Exception;

	public List selectProjectList(MesStatisticsVO mesStatisticsVO) throws Exception;
	
	public List eAssetTmpMakerList(MesStatisticsVO mesStatisticsVO) throws Exception;

	public List eAssetTmpTypeList(MesStatisticsVO mesStatisticsVO) throws Exception;

}
