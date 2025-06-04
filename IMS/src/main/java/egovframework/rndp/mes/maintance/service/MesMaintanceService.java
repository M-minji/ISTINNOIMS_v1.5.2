package egovframework.rndp.mes.maintance.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.service.FileVO;
import egovframework.rndp.mes.asset.service.MesAssetVO;

public interface MesMaintanceService {

	public List selectMesMaintanceList(MesMaintanceVO mesMaintanceVO) throws Exception;

	public int selectMesMaintanceListCnt(MesMaintanceVO mesMaintanceVO) throws Exception;

	public void insertMesMaintance(MesMaintanceVO mesMaintanceVO, HttpServletRequest request) throws Exception;
	
	public MesMaintanceVO selectMaintanceInfo(MesMaintanceVO mesMaintanceVO) throws Exception;

	public void updateMesMaintance(MesMaintanceVO mesMaintanceVO, FileVO fileVO, HttpServletRequest request) throws Exception;

	public void deleteMesMaintance(MesMaintanceVO mesMaintanceVO) throws Exception;

	public void updateMesProcess(MesMaintanceVO mesMaintanceVO, FileVO fileVO, HttpServletRequest request) throws Exception;

	public void updateMesProcessNull(MesMaintanceVO mesMaintanceVO) throws Exception;

	public void insertMesProcess(MesMaintanceVO mesMaintanceVO, HttpServletRequest request) throws Exception;

	public void requestMaintance(MesMaintanceVO mesMaintanceVO) throws Exception;

	public void setSignMaintance(MesMaintanceVO mesMaintanceVO) throws Exception;
	
	public List<MesMaintanceVO> getMaintanceFile(MesMaintanceVO mesMaintanceVO) throws Exception;

	public List<MesMaintanceVO> getProcessFile(MesMaintanceVO mesMaintanceVO) throws Exception;

	public List selectMaintanceInfoList(MesMaintanceVO mesMaintanceVO) throws Exception;

	public List selectMesMaintancePopList(MesMaintanceVO mesMaintanceVO) throws Exception;

	public int selectMesMaintancePopListCnt(MesMaintanceVO mesMaintanceVO) throws Exception;
}
