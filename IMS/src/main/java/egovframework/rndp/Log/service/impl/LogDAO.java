package egovframework.rndp.Log.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rndp.Log.service.LogVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("LogDAO")
public class LogDAO extends EgovAbstractDAO{

	public void insertLog(LogVO VO) throws Exception{
		insert("LogDAO.insertLog",VO);
	}

	public List selectMenu(LogVO VO) throws Exception{
		return list("LogDAO.selectMenu", VO);
	}

	public List selectLogNum(LogVO VO) throws Exception{
		return list("LogDAO.selectLogNum", VO);
	}

	public void updateLog(LogVO VO) throws Exception{
		update("LogDAO.updateLog",VO);
	}

	public void updateLogMinutes(LogVO VO)throws Exception{
		update("LogDAO.updateLogMinutes",VO);
	}
	
	public List selectLogList(LogVO LogVO)throws Exception{
		return  list("LogDAO.selectLogList",LogVO);
	}
	
	public int selectLogListCnt(LogVO LogVO)throws Exception{
		return (int) select("LogDAO.selectLogListCnt",LogVO);
	}

	public List selectMenuZero(LogVO LogVO)throws Exception{
		return  list("LogDAO.selectMenuZero",LogVO);
	}

	public void updateLogMinutesSub(LogVO LogVO)throws Exception{
		update("LogDAO.updateLogMinutesSub",LogVO);
	}
	
	public List selectLogExcelList(LogVO LogVO)throws Exception{
		return  list("LogDAO.selectLogExcelList",LogVO);
	}
}
