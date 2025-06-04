package egovframework.rndp.mes.equipment.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.service.FileVO;
import egovframework.rndp.mes.maintance.service.MesMaintanceVO;
import egovframework.rndp.mes.position.service.MesPositionVO;


public interface MesEquipmentService {

	public void insertInfoEquipment(MesEquipmentVO mesEquipmentVO) throws Exception;

	public List selectEquipmentList(MesEquipmentVO mesEquipmentVO) throws Exception;

	public int selectEquipmentListCnt(MesEquipmentVO mesEquipmentVO) throws Exception;

	public MesEquipmentVO selectEquipmentInfo(MesEquipmentVO mesEquipmentVO) throws Exception;

	public List selectEquipmentRowItemList(MesEquipmentVO mesEquipmentVO) throws Exception;

	public void updateEquipmentStatus(MesEquipmentVO mesEquipmentVO) throws Exception;

	public void updateEquipmentInfo(MesEquipmentVO mesEquipmentVO) throws Exception;

	public List selectEquipmentPopList(MesEquipmentVO mesEquipmentVO) throws Exception;

	public int selectEquipmentPopListCnt(MesEquipmentVO mesEquipmentVO) throws Exception;

	public List selectEquipmentInfoList(MesEquipmentVO mesEquipmentVO) throws Exception;

	public void insertOutInfoEquipment(MesEquipmentVO mesEquipmentVO) throws Exception;

	public List selectEquipmentOutList(MesEquipmentVO mesEquipmentVO) throws Exception;

	public int selectEquipmentOutListCnt(MesEquipmentVO mesEquipmentVO) throws Exception;

	public MesEquipmentVO selectEquipmentOutInfo(MesEquipmentVO mesEquipmentVO) throws Exception;

	public List selectEquipmentOutRowItemList(MesEquipmentVO mesEquipmentVO) throws Exception;

	public void updateEquipmentOutStatus(MesEquipmentVO mesEquipmentVO) throws Exception;

	public void updateEquipmentOutInfo(MesEquipmentVO mesEquipmentVO) throws Exception;

	public void updateImportInfo(MesEquipmentVO mesEquipmentVO) throws Exception;

	public void mesSignEquipment(MesEquipmentVO mesEquipmentVO) throws Exception;

	public void mesUpdateSignStatus(MesEquipmentVO mesEquipmentVO) throws Exception;

	public List selectEquipmentExcelList(MesEquipmentVO mesEquipmentVO) throws Exception;

	
	
	
	
	
	
	
	
	 
	
	
	
	
}
