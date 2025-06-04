package egovframework.rndp.Log.service;

import java.util.List;

public interface LogService {
	//로그등록
	public void insertLog(LogVO vO) throws Exception;
	
	public List selectLogList(LogVO logVO) throws Exception;
	public List selectLogExcelList(LogVO logVO) throws Exception;
	public int selectLogListCnt(LogVO logVO) throws Exception;
}
