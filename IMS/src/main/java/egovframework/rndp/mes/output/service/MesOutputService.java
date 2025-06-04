package egovframework.rndp.mes.output.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.service.FileVO;

public interface MesOutputService {



	public void insertMesOutput(MesOutputVO mesOutputVO) throws Exception;


	public List selectMesOutputList(MesOutputVO mesOutputVO)throws Exception;


	public int selectMesOutputListCnt(MesOutputVO mesOutputVO)throws Exception;


	public MesOutputVO selectOutInfo(MesOutputVO mesOutputVO)throws Exception;


	public List selectOutInfoItemList(MesOutputVO mesOutputVO)throws Exception;


	public void deleteMesOutput(MesOutputVO mesOutputVO)throws Exception;


	public void updateMesOutput(MesOutputVO mesOutputVO)throws Exception;


	public void insertMesOutputProcess(MesOutputVO mesOutputVO, HttpServletRequest request)throws Exception;


	public void requestOutputProcess(MesOutputVO mesOutputVO)throws Exception;


	public void setSignOutput(MesOutputVO mesOutputVO)throws Exception;


	public List mesProjectInfoList(MesOutputVO mesOutputVO) throws Exception;


	public int mesProjectInfoListCnt(MesOutputVO mesOutputVO) throws Exception;


	public void eInsertDeliverableInfo(MesOutputVO mesOutputVO) throws Exception;


	public List insertDeliverableInfoList(MesOutputVO mesOutputVO) throws Exception;


	public List insertDeliverableFileInfoList(MesOutputVO mesOutputVO) throws Exception;


	public void eDeleteDeliverableInfo(MesOutputVO mesOutputVO) throws Exception;


	public void eInsertReportInfo(MesOutputVO mesOutputVO) throws Exception;


	public List insertReportInfoList(MesOutputVO mesOutputVO) throws Exception;


	public List insertReportFileInfoList(MesOutputVO mesOutputVO) throws Exception;


//	public void updateOutputfile(MesOutputVO mesOutputVO)throws Exception;


}
