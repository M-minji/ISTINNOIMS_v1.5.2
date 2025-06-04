package egovframework.rndp.mes.project.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.service.FileVO;
import egovframework.rndp.mes.output.service.MesOutputVO;

public interface MesProjectService {

	public void insertMesProject(HttpServletRequest request,MesProjectVO mesProjectVO)throws Exception;

	public List selectMesProjectList(MesProjectVO mesProjectVO)throws Exception;

	public int selectMesProjectListCnt(MesProjectVO mesProjectVO)throws Exception;

	public MesProjectVO selectProInfo(MesProjectVO mesProjectVO)throws Exception;

	public void deleteMesProject(MesProjectVO mesProjectVO)throws Exception;

	public void updateMesProject(MesProjectVO mesProjectVO)throws Exception;

	public void insertMesProjectrocess(MesProjectVO mesProjectVO, HttpServletRequest request)throws Exception;

	public void requestProjectProcess(MesProjectVO mesProjectVO)throws Exception;

	public void setSignProject(MesProjectVO mesProjectVO)throws Exception;

	public void eMesInsertProjectInfo(MesProjectVO mesProjectVO) throws Exception;

	public List mesProjectInfoList(MesProjectVO mesProjectVO) throws Exception;

	public int mesProjectInfoListCnt(MesProjectVO mesProjectVO) throws Exception;

	public MesProjectVO selectProjectInfo(MesProjectVO mesProjectVO) throws Exception;

	public void updateProjectInfo(MesProjectVO mesProjectVO) throws Exception;

	public void mesSignProject(MesProjectVO mesProjectVO) throws Exception;

	public void mesUpdateSignStatus(MesProjectVO mesProjectVO) throws Exception;

	public List mesProjectExcelInfoList(MesProjectVO mesProjectVO) throws Exception;



}
