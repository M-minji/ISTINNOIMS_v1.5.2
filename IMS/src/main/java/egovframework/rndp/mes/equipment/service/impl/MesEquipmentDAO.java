package egovframework.rndp.mes.equipment.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rndp.mes.asset.service.MesAssetVO;
import egovframework.rndp.mes.equipment.service.MesEquipmentVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("mesEquipmentDAO")
public class MesEquipmentDAO extends EgovAbstractDAO{

	public void insertInfoEquipment(MesEquipmentVO mesEquipmentVO) throws Exception{
		// TODO Auto-generated method stub
		insert("mesEquipmentDAO.insertInfoEquipment", mesEquipmentVO);
	}

	public void insertEquipmentRowInfo(MesEquipmentVO mesEquipmentVO) throws Exception{
		// TODO Auto-generated method stub
		insert("mesEquipmentDAO.insertEquipmentRowInfo", mesEquipmentVO);
	}

	public List selectEquipmentList(MesEquipmentVO mesEquipmentVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesEquipmentDAO.selectEquipmentList", mesEquipmentVO);
	}

	public int selectEquipmentListCnt(MesEquipmentVO mesEquipmentVO) throws Exception{
		// TODO Auto-generated method stub
		return (int) select("mesEquipmentDAO.selectEquipmentListCnt", mesEquipmentVO);
	}

	public MesEquipmentVO selectEquipmentInfo(MesEquipmentVO mesEquipmentVO) throws Exception{
		// TODO Auto-generated method stub
		return (MesEquipmentVO) select("mesEquipmentDAO.selectEquipmentInfo", mesEquipmentVO);
	}

	public List selectEquipmentRowItemList(MesEquipmentVO mesEquipmentVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesEquipmentDAO.selectEquipmentRowItemList", mesEquipmentVO);
	}

	public void updateEquipmentStatus(MesEquipmentVO mesEquipmentVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesEquipmentDAO.updateEquipmentStatus", mesEquipmentVO);
	}

	public void updateEquipmentInfo(MesEquipmentVO mesEquipmentVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesEquipmentDAO.updateEquipmentInfo", mesEquipmentVO);
	}

	public void deleteEquipmentRowInfo(MesEquipmentVO mesEquipmentVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesEquipmentDAO.deleteEquipmentRowInfo", mesEquipmentVO);
	}

	public int selectEquipmentPopListCnt(MesEquipmentVO mesEquipmentVO) throws Exception{
		// TODO Auto-generated method stub
		return (int) select("mesEquipmentDAO.selectEquipmentPopListCnt", mesEquipmentVO);
	}

	public List selectEquipmentPopList(MesEquipmentVO mesEquipmentVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesEquipmentDAO.selectEquipmentPopList", mesEquipmentVO);
	}

	public List selectEquipmentInfoList(Map<String, Object> keyList) throws Exception{
		// TODO Auto-generated method stub
		return list("mesEquipmentDAO.selectEquipmentInfoList",keyList);
	}

	public void insertEquipmentOutInfo(MesEquipmentVO mesEquipmentVO) throws Exception{ 
		// TODO Auto-generated method stub
		insert("mesEquipmentDAO.insertEquipmentOutInfo", mesEquipmentVO);
	}

	public void updateOutInfoEquipmentItem(MesEquipmentVO mesEquipmentVO) throws Exception{ 
		// TODO Auto-generated method stub
		update("mesEquipmentDAO.updateOutInfoEquipmentItem", mesEquipmentVO);
	}

	public List selectEquipmentOutList(MesEquipmentVO mesEquipmentVO) throws Exception{ 
		// TODO Auto-generated method stub
		return list("mesEquipmentDAO.selectEquipmentOutList", mesEquipmentVO);
	}

	public int selectEquipmentOutListCnt(MesEquipmentVO mesEquipmentVO) throws Exception{ 
		// TODO Auto-generated method stub
		return (int) select("mesEquipmentDAO.selectEquipmentOutListCnt", mesEquipmentVO);
	}

	public MesEquipmentVO selectEquipmentOutInfo(MesEquipmentVO mesEquipmentVO) throws Exception{
		// TODO Auto-generated method stub
		return (MesEquipmentVO) select("mesEquipmentDAO.selectEquipmentOutInfo", mesEquipmentVO);
	}

	public List selectEquipmentOutRowItemList(MesEquipmentVO mesEquipmentVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesEquipmentDAO.selectEquipmentOutRowItemList", mesEquipmentVO);
	}

	public void updateEquipmentOutStatus(MesEquipmentVO mesEquipmentVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesEquipmentDAO.updateEquipmentOutStatus", mesEquipmentVO);
	}

	public void updateEquipmentItemOutStatus(MesEquipmentVO mesEquipmentVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesEquipmentDAO.updateEquipmentItemOutStatus", mesEquipmentVO);
	}

	public void updateEquipmentOutInfo(MesEquipmentVO mesEquipmentVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesEquipmentDAO.updateEquipmentOutInfo", mesEquipmentVO);
	}

	public void updateImportInfo(MesEquipmentVO mesEquipmentVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesEquipmentDAO.updateImportInfo", mesEquipmentVO);
	}

	public void updateEquipmentSingStatus(MesEquipmentVO mesEquipmentVO) throws Exception{
		// TODO Auto-generated method stub
		update("mesEquipmentDAO.updateEquipmentSingStatus", mesEquipmentVO);
	}

	public List selectEquipmentExcelList(MesEquipmentVO mesEquipmentVO) throws Exception{
		// TODO Auto-generated method stub
		return list("mesEquipmentDAO.selectEquipmentExcelList", mesEquipmentVO);
	}
	    

}
