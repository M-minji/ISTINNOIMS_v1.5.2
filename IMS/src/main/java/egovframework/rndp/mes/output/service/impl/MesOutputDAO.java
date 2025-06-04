package egovframework.rndp.mes.output.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rndp.mes.output.service.MesOutputVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("mesOutputDAO")
public class MesOutputDAO extends EgovAbstractDAO {

	public List selectMesBaljuItemList(MesOutputVO mesOutputVO) throws Exception{
		return list("mesOutputDAO.selectMesBaljuItemList", mesOutputVO);
	}

	public void insertMesOutput(MesOutputVO mesOutputVO) throws Exception{
		insert("mesOutputDAO.insertMesOutput", mesOutputVO);
	}

	public void insertMesOutputItem(MesOutputVO vo)  throws Exception{
		insert("mesOutputDAO.insertMesOutputItem", vo);
	}

	public List selectMesOutputList(MesOutputVO mesOutputVO) throws Exception{
		return list("mesOutputDAO.selectMesOutputList",mesOutputVO);
	}

	public int selectMesOutputListCnt(MesOutputVO mesOutputVO) throws Exception{
		// TODO Auto-generated method stub
		return  (int) select("mesOutputDAO.selectMesOutputListCnt",mesOutputVO);
	}

	public MesOutputVO selectOutInfo(MesOutputVO mesOutputVO) throws Exception{
		return (MesOutputVO) select("mesOutputDAO.selectOutInfo", mesOutputVO);
	}

	public List selectOutInfoItemList(MesOutputVO mesOutputVO) throws Exception{
		return list("mesOutputDAO.selectOutInfoItemList",mesOutputVO);
	}

	public void deleteMesOutput(MesOutputVO mesOutputVO) throws Exception {
		delete("mesOutputDAO.deleteMesOutput",mesOutputVO);
		
	}

	public void deleteMesOutputItem(MesOutputVO mesOutputVO) throws Exception {
		delete("mesOutputDAO.deleteMesOutputItem",mesOutputVO);
	}

	public void updateMesOutput(MesOutputVO mesOutputVO) throws Exception {
		update("mesOutputDAO.updateMesOutput",mesOutputVO);
	}

	public void updateMesOutputProcess(MesOutputVO mesOutputVO) throws Exception {
		update("mesOutputDAO.updateMesOutputProcess",mesOutputVO);
	}

	public void requestOutputProcess(MesOutputVO mesOutputVO) {
		update("mesOutputDAO.requestOutputProcess",mesOutputVO);
	}

	public void updateOutputStatus(MesOutputVO mesOutputVO) {
		update("mesOutputDAO.updateOutputStatus",mesOutputVO);
	}

	public List mesProjectInfoList(MesOutputVO mesOutputVO) throws Exception {
		// TODO Auto-generated method stub
		return list("mesOutputDAO.mesProjectInfoList", mesOutputVO);
	}

	public int mesProjectInfoListCnt(MesOutputVO mesOutputVO) throws Exception {
		// TODO Auto-generated method stub
		return  (int) select("mesOutputDAO.mesProjectInfoListCnt",mesOutputVO);
	}

	public void mesProjectStatusUpdate(MesOutputVO mesOutputVO) throws Exception {
		// TODO Auto-generated method stub
		update("mesOutputDAO.mesProjectStatusUpdate",mesOutputVO);
	}

	public void insertDeliverableFileInfo(MesOutputVO mesOutputVO)  throws Exception {
		// TODO Auto-generated method stub
		insert("mesOutputDAO.insertDeliverableFileInfo",mesOutputVO);
	}

	public void insertDeliverableInfo(MesOutputVO mesOutputVO)  throws Exception {
		// TODO Auto-generated method stub
		insert("mesOutputDAO.insertDeliverableInfo",mesOutputVO);
	}

	public List insertDeliverableInfoList(MesOutputVO mesOutputVO) throws Exception {
		// TODO Auto-generated method stub
		return list("mesOutputDAO.insertDeliverableInfoList", mesOutputVO);
	}

	public List insertDeliverableFileInfoList(MesOutputVO mesOutputVO) throws Exception {
		// TODO Auto-generated method stub
		return list("mesOutputDAO.insertDeliverableFileInfoList", mesOutputVO);
	}

	public void deleteDeliverableInfo(MesOutputVO mesOutputVO) throws Exception {
		// TODO Auto-generated method stub
		delete("mesOutputDAO.deleteDeliverableInfo",mesOutputVO);
	}

	public void deleteDeliverableFileInfo(MesOutputVO mesOutputVO) throws Exception {
		// TODO Auto-generated method stub
		delete("mesOutputDAO.deleteDeliverableFileInfo",mesOutputVO);
	}

	public void deleteReportInfo(MesOutputVO mesOutputVO) throws Exception {
		// TODO Auto-generated method stub
		delete("mesOutputDAO.deleteReportInfo",mesOutputVO);
	}

	public void deleteReportFileInfo(MesOutputVO mesOutputVO) throws Exception {
		// TODO Auto-generated method stub
		delete("mesOutputDAO.deleteReportFileInfo",mesOutputVO);
	}

	public void insertReportInfo(MesOutputVO mesOutputVO) throws Exception {
		// TODO Auto-generated method stub
		insert("mesOutputDAO.insertReportInfo",mesOutputVO);
	}

	public void insertReportFileInfo(MesOutputVO mesOutputVO) throws Exception {
		// TODO Auto-generated method stub
		insert("mesOutputDAO.insertReportFileInfo",mesOutputVO);
	}

	public List insertReportInfoList(MesOutputVO mesOutputVO) throws Exception {
		// TODO Auto-generated method stub
		return list("mesOutputDAO.insertReportInfoList", mesOutputVO);
	}

	public List insertReportFileInfoList(MesOutputVO mesOutputVO) throws Exception {
		// TODO Auto-generated method stub
		return list("mesOutputDAO.insertReportFileInfoList", mesOutputVO);
	}

}
