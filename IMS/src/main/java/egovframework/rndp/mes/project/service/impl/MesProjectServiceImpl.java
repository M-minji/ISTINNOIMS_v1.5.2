package egovframework.rndp.mes.project.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rndp.mes.common.service.impl.MesCommonDAO;
import egovframework.rndp.mes.output.service.MesOutputVO;
import egovframework.rndp.mes.project.service.MesProjectService;
import egovframework.rndp.mes.project.service.MesProjectVO;
import egovframework.rndp.mes.sign.service.MesSignService;

@Service("mesProjectService") 
public class MesProjectServiceImpl implements MesProjectService {
	
	/*공통사용 DAO common 및  Egov 공용 */
	@Resource(name = "mesCommonDAO")
	private MesCommonDAO mesCommonDAO; 
	
	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;
	
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;

	@Resource(name = "mesSignService")
	private MesSignService mesSignService;
	
	
	/*공통사용 DAO common 및  Egov 공용 */
	
	@Resource(name = "mesProjectDAO")
	private MesProjectDAO mesProjectDAO;


	@Override
	public void insertMesProject(HttpServletRequest request,MesProjectVO mesProjectVO) throws Exception {
		String pk = mesProjectDAO.insertMesProject(mesProjectVO);
		mesProjectVO.setpProjectkey(pk);
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> files = multipartRequest.getFileMap();
		List<FileVO> result = null;

		String atchFileId = "";

		if(!files.isEmpty()){
			result = fileUtil.parseFileInf(files, "Do_", 0, "", "bprintUploadPath");
			atchFileId = fileService.insertFileInfs(result);
			mesProjectVO.setProjectItemFileId(atchFileId);
		}
		

		this.mesProjectFile(mesProjectVO);
	}

	public void mesProjectFile(MesProjectVO mesProjectVO ) throws Exception {
		MesProjectVO vo = new MesProjectVO();
		if(mesProjectVO.geteAddFileId1() != null && !mesProjectVO.geteAddFileId1().equals("")){
			String[] list  						= EgovStringUtil.split(mesProjectVO.geteAddFileId1(),",");
			String[] atchFileName1  			= EgovStringUtil.split(mesProjectVO.getAtchFileName1(),",");
			String[] pProjectItemEtcList		= EgovStringUtil.split(mesProjectVO.getpProjectItemEtc(),",");
			for(int i=0; i<list.length; i++ ){
				vo.seteAddFileId(list[i]);
				vo.setAtchFileName(atchFileName1[i]);
				vo.setpProjectkey(mesProjectVO.getpProjectkey());
				vo.setpProjectItemEtc(pProjectItemEtcList[i]);
				mesProjectDAO.mesProjectFile(vo);
			}
		}
		
	}
	@Override
	public List selectMesProjectList(MesProjectVO mesProjectVO) throws Exception {
		return mesProjectDAO.selectMesProjectList(mesProjectVO);
	}


	@Override
	public int selectMesProjectListCnt(MesProjectVO mesProjectVO) throws Exception {
		return mesProjectDAO.selectMesProjectListCnt(mesProjectVO);
	}


	@Override
	public MesProjectVO selectProInfo(MesProjectVO mesProjectVO) throws Exception {
		return mesProjectDAO.selectProInfo(mesProjectVO);
	}


	@Override
	public void deleteMesProject(MesProjectVO mesProjectVO) throws Exception {
		//결제 정보 삭제  처리.
		mesProjectVO.setsSignTableKey(mesProjectVO.geteProjectNum());
		mesProjectVO.setsSignTableName("P_PROJECT");
		mesSignService.deleteSignItemTwo(mesProjectVO.getsSignTableKey(),  mesProjectVO.getsSignTableName());
		mesProjectVO.seteProjectStatus("삭제");
		mesProjectDAO.statusProjectInfo(mesProjectVO);
//		mesProjectDAO.deleteMesProject(mesProjectVO);
//		mesProjectDAO.deleteMesProjectItem(mesProjectVO);
	}


	@Override
	public void updateMesProject(MesProjectVO mesProjectVO) throws Exception {
		mesProjectDAO.updateMesProject(mesProjectVO);
		mesProjectDAO.deleteMesProjectItem(mesProjectVO);
//		mesSignService.updateSign(mesProjectVO.getsSignKey(), mesProjectVO.getsSignStaffKey(), mesProjectVO.getsSignStaffName());
		
		this.mesProjectFile(mesProjectVO);
	}


	@Override
	public void insertMesProjectrocess(MesProjectVO mesProjectVO, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> files = multipartRequest.getFileMap();
		List<FileVO> result = null;
		
		String atchFileId = "";
		
		if(!files.isEmpty()){
			result = fileUtil.parseFileInf(files, "SR_", 0, "", "SRUploadPath");
			atchFileId = fileService.insertFileInfs(result);
			mesProjectVO.setmProjectProcessFile(atchFileId);
		}
		
//		String sSignKey = mesSignService.insertSign(mesProjectVO.getpProjectkey(), "P_PROJECT", mesProjectVO.getsSignStaffKey(), mesProjectVO.getsSignStaffName());
//		mesProjectVO.setsSignKey(sSignKey);
		
		mesProjectDAO.updateMesProjectProcess(mesProjectVO);
		
	}


	@Override
	public void requestProjectProcess(MesProjectVO mesProjectVO) throws Exception {
		if(mesProjectVO.getGubun().equals("취소")){
			mesProjectVO.setsSignStatus("처리등록");
			}else{
				mesProjectVO.setsSignStatus("승인요청");
			}
		mesProjectDAO.requestProjectProcess(mesProjectVO);
		MesProjectVO info = mesProjectDAO.selectProInfo(mesProjectVO);
		mesSignService.resetSignStatusStart(info.getsSignKey());
	}


	@Override
	public void setSignProject(MesProjectVO mesProjectVO) throws Exception {
		// TODO Auto-generated method stub

		mesSignService.updateSignStatus(mesProjectVO.getsSignKey(), mesProjectVO.getkStaffKey(), mesProjectVO.getsSignContent(), mesProjectVO.getsSignDecison());
		mesProjectDAO.updateProjectStatus(mesProjectVO);
	}

	@Override
	public void eMesInsertProjectInfo(MesProjectVO mesProjectVO) throws Exception {
		// TODO Auto-generated method stub
		String sSignStatus = "";
		
		if(!"Y".equals(mesProjectVO.getoSignPass())){
			sSignStatus="등록";
		}else {
			sSignStatus="제외";
		}
		mesProjectVO.setsSignStatus(sSignStatus);
		mesProjectVO.setsSignTableName("P_PROJECT");
		mesProjectVO.seteProjectStatus("등록");
		mesProjectDAO.eMesInsertProjectInfo(mesProjectVO);
		mesProjectVO.setsSignTableKey(mesProjectVO.geteProjectNum());
		//결재정보 등록
		this.insertSignInfoProject(mesProjectVO);
	}

	private void insertSignInfoProject(MesProjectVO mesProjectVO) throws Exception {
		// TODO Auto-generated method stub
		if(mesProjectVO.getsSignStatus().equals("등록") ){
			String sSignKey = mesSignService.insertSignTwo(mesProjectVO.getsSignTableKey(), mesProjectVO.getsSignTableName(), mesProjectVO.getsSignStaffKey(), mesProjectVO.getsSignStaffName(), mesProjectVO.getsSignStaffGubun(), mesProjectVO.getsSignStaffPosition());
		} 
	}

	@Override
	public List mesProjectInfoList(MesProjectVO mesProjectVO) throws Exception {
		// TODO Auto-generated method stub
		return mesProjectDAO.mesProjectInfoList(mesProjectVO);
	}

	@Override
	public int mesProjectInfoListCnt(MesProjectVO mesProjectVO) throws Exception {
		// TODO Auto-generated method stub
		return mesProjectDAO.mesProjectInfoListCnt(mesProjectVO);
	}

	@Override
	public MesProjectVO selectProjectInfo(MesProjectVO mesProjectVO) throws Exception {
		// TODO Auto-generated method stub
		return mesProjectDAO.selectProjectInfo(mesProjectVO);
	}

	@Override
	public void updateProjectInfo(MesProjectVO mesProjectVO) throws Exception {
		// TODO Auto-generated method stub
		String sSignStatus = "";
		
		if(!"Y".equals(mesProjectVO.getoSignPass())){
			sSignStatus="등록";
		}else {
			sSignStatus="제외";
		}
		mesProjectVO.setsSignStatus(sSignStatus);
		mesProjectVO.setsSignTableName("P_PROJECT");
		mesProjectVO.seteProjectStatus("등록");
		mesProjectVO.setsSignTableKey(mesProjectVO.geteProjectNum());
		//결제 정보 삭제  처리.
		mesSignService.deleteSignItemTwo(mesProjectVO.getsSignTableKey(),  mesProjectVO.getsSignTableName());
				
		mesProjectDAO.updateProjectInfo(mesProjectVO);
		
		//결재정보 등록
		this.insertSignInfoProject(mesProjectVO);
	}

	@Override
	public void mesSignProject(MesProjectVO mesProjectVO) throws Exception {
		// TODO Auto-generated method stub
		String status = mesProjectVO.getsSignStatus();
		if ("Y".equals(status)) {
			mesProjectVO.setsSignStatus("승인요청");
		} else if ("N".equals(status)) {
			mesProjectVO.setsSignStatus("등록");
		}
		mesProjectVO.setsSignTableKey(mesProjectVO.geteProjectNum());
		mesProjectVO.setsSignTableName("P_PROJECT");
		// S_SIGN 테이블 시작일 갱신처리
		if("Y".equals(status)) {
			mesSignService.updateSignStart(mesProjectVO.getsSignTableKey(),mesProjectVO.getsSignTableName());
		}
		//주테이블의 상태값 갱신처리.
		mesProjectDAO.updateProjectSingStatus(mesProjectVO);
	}

	@Override
	public void mesUpdateSignStatus(MesProjectVO mesProjectVO) throws Exception {
		// TODO Auto-generated method stub
		mesProjectVO.setsSignStatus("반려");
		mesSignService.insertSignRejectionReason(mesProjectVO.getsSignTableKey(), mesProjectVO.getsSignTableName(), mesProjectVO.getsSignStaffKey(), mesProjectVO.getsSignContent());
		if("P_PROJECT".equals(mesProjectVO.getsSignTableName())){
			mesProjectVO.seteProjectNum(mesProjectVO.getsSignTableKey());
			//주테이블의 상태값 갱신처리.
			mesProjectDAO.updateProjectSingStatus(mesProjectVO);
		}
	}

	@Override
	public List mesProjectExcelInfoList(MesProjectVO mesProjectVO) throws Exception {
		// TODO Auto-generated method stub
		return mesProjectDAO.mesProjectExcelInfoList(mesProjectVO);
	}
 
 

	
}
