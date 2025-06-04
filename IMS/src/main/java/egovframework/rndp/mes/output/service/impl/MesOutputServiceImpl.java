package egovframework.rndp.mes.output.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rndp.mes.common.service.impl.MesCommonDAO;
import egovframework.rndp.mes.maintance.service.MesMaintanceVO;
import egovframework.rndp.mes.output.service.MesOutputService;
import egovframework.rndp.mes.output.service.MesOutputVO;
import egovframework.rndp.mes.sign.service.MesSignService;

@Service("mesOutputService") 
public class MesOutputServiceImpl implements MesOutputService {
	
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
	
	@Resource(name = "mesOutputDAO")
	private MesOutputDAO mesOutputDAO;




	@Override
	public void insertMesOutput(MesOutputVO mesOutputVO) throws Exception {
		mesOutputDAO.insertMesOutput(mesOutputVO);
		if(mesOutputVO.getoOutputItemGubun() != null && !mesOutputVO.getoOutputItemGubun().equals("")){
			this.insertMesOutputItem(mesOutputVO);
		}
	}
	
	public void insertMesOutputItem(MesOutputVO mesOutputVO) throws Exception{
		
		String[] oOutputItemGubunList	= EgovStringUtil.split(mesOutputVO.getoOutputItemGubun(), ",");
		String[] oOutputItemNameList	= EgovStringUtil.split(mesOutputVO.getoOutputItemName(), ",");
		String[] oOutputItemEtcList		= EgovStringUtil.split(mesOutputVO.getoOutputItemEtc(), ",");
		String[] oOutputItemFileId		= EgovStringUtil.split(mesOutputVO.getoOutputItemFileId(), ",");
		
		MesOutputVO vo =  new MesOutputVO();
		vo.setOutputKey(mesOutputVO.getOutputKey());
		for(int i = 0; i < oOutputItemGubunList.length; i++){
			vo.setoOutputItemGubun(oOutputItemGubunList[i]); 
			vo.setoOutputItemName(oOutputItemNameList[i]); 
			vo.setoOutputItemEtc(oOutputItemEtcList[i]); 
			vo.setoOutputItemFileId(oOutputItemFileId[i]); 
			
			mesOutputDAO.insertMesOutputItem(vo);	
		}
	}


	@Override
	public List selectMesOutputList(MesOutputVO mesOutputVO) throws Exception {
		return mesOutputDAO.selectMesOutputList(mesOutputVO);
	}


	@Override
	public int selectMesOutputListCnt(MesOutputVO mesOutputVO) throws Exception {
		// TODO Auto-generated method stub
		return mesOutputDAO.selectMesOutputListCnt(mesOutputVO);
	}
	@Override
	public MesOutputVO selectOutInfo(MesOutputVO mesOutputVO) throws Exception {
		// TODO Auto-generated method stub
		return mesOutputDAO.selectOutInfo(mesOutputVO);
	}
	@Override
	public List selectOutInfoItemList(MesOutputVO mesOutputVO) throws Exception {
		// TODO Auto-generated method stub
		return mesOutputDAO.selectOutInfoItemList(mesOutputVO);
	}
	@Override
	public void deleteMesOutput(MesOutputVO mesOutputVO) throws Exception {
		 mesOutputDAO.deleteMesOutput(mesOutputVO);
		 mesOutputDAO.deleteMesOutputItem(mesOutputVO);
	}
	@Override
	public void updateMesOutput(MesOutputVO mesOutputVO) throws Exception {
		 mesOutputDAO.updateMesOutput(mesOutputVO);
		 mesOutputDAO.deleteMesOutputItem(mesOutputVO);
		 if(!mesOutputVO.getoSignPass().equals("Y")){
		 if(mesOutputVO.getsSignKey().equals("")){
//			 String sSignKey = mesSignService.insertSign(mesOutputVO.getOutputKey(), "O_OUTPUT", mesOutputVO.getsSignStaffKey(), mesOutputVO.getsSignStaffName());
//			 mesOutputVO.setsSignKey(sSignKey);
			 mesOutputDAO.updateMesOutputProcess(mesOutputVO);
		 	}else{
//		 	 mesSignService.updateSign(mesOutputVO.getsSignKey(), mesOutputVO.getsSignStaffKey(), mesOutputVO.getsSignStaffName());
		 	}
		 }
		 
		 if(mesOutputVO.getoOutputItemGubun() != null && !mesOutputVO.getoOutputItemGubun().equals("")){
				this.insertMesOutputItem(mesOutputVO);
			}
	}
//
//	@Override
//	public void updateOutputfile(MesOutputVO mesOutputVO) throws Exception {
//		mesOutputDAO.updateOutputfile(mesOutputVO);
//	}

	@Override
	public void insertMesOutputProcess(MesOutputVO mesOutputVO, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub

				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				Map<String, MultipartFile> files = multipartRequest.getFileMap();
				List<FileVO> result = null;
				
				String atchFileId = "";
				
				if(!files.isEmpty()){
					result = fileUtil.parseFileInf(files, "SR_", 0, "", "SRUploadPath");
					atchFileId = fileService.insertFileInfs(result);
					mesOutputVO.setmOutputProcessFile(atchFileId);
				}
				if(!mesOutputVO.getoSignPass().equals("Y") ){
//				String sSignKey = mesSignService.insertSign(mesOutputVO.getOutputKey(), "O_OUTPUT", mesOutputVO.getsSignStaffKey(), mesOutputVO.getsSignStaffName());
//				mesOutputVO.setsSignKey(sSignKey);
				mesOutputDAO.updateMesOutputProcess(mesOutputVO);
				}
	}

	@Override
	public void requestOutputProcess(MesOutputVO mesOutputVO) throws Exception {
		if(mesOutputVO.getGubun().equals("취소")){
			mesOutputVO.setsSignStatus("처리등록");
			}else{
				mesOutputVO.setsSignStatus("승인요청");
			}
		mesOutputDAO.requestOutputProcess(mesOutputVO);
		MesOutputVO info = mesOutputDAO.selectOutInfo(mesOutputVO);
		mesSignService.resetSignStatusStart(info.getsSignKey());
	}

	@Override
	public void setSignOutput(MesOutputVO mesOutputVO) throws Exception {
		// TODO Auto-generated method stub

		mesSignService.updateSignStatus(mesOutputVO.getsSignKey(), mesOutputVO.getkStaffKey(), mesOutputVO.getsSignContent(), mesOutputVO.getsSignDecison());
		mesOutputDAO.updateOutputStatus(mesOutputVO);
	}

	@Override
	public List mesProjectInfoList(MesOutputVO mesOutputVO) throws Exception {
		// TODO Auto-generated method stub
		return mesOutputDAO.mesProjectInfoList(mesOutputVO);
	}

	@Override
	public int mesProjectInfoListCnt(MesOutputVO mesOutputVO) throws Exception {
		// TODO Auto-generated method stub
		return mesOutputDAO.mesProjectInfoListCnt(mesOutputVO);
	}

	@Override
	public void eInsertDeliverableInfo(MesOutputVO mesOutputVO) throws Exception {
		// TODO Auto-generated method stub
		//프로젝트 상태 업데이트
		mesOutputDAO.mesProjectStatusUpdate(mesOutputVO);
		
		//산출물정보 삭제
		mesOutputDAO.deleteDeliverableInfo(mesOutputVO);	
		//파일정보 삭제
		mesOutputDAO.deleteDeliverableFileInfo(mesOutputVO);
		
		//산출물정보 등록
		String[] eItemName	= EgovStringUtil.split(mesOutputVO.geteItemName(), ",");
		String[] eItemIndex	= EgovStringUtil.split(mesOutputVO.geteItemIndex(), ",");
		String[] eItemGubun	= EgovStringUtil.split(mesOutputVO.geteItemGubun(), ",");
		String[] eItemDate	= EgovStringUtil.split(mesOutputVO.geteItemDate(), ",");
		String[] eItemAffiliation	= EgovStringUtil.split(mesOutputVO.geteItemAffiliation(), ",");
		String[] eItemAuthor	= EgovStringUtil.split(mesOutputVO.geteItemAuthor(), ",");
		String[] eItemRemarks	= EgovStringUtil.split(mesOutputVO.geteItemRemarks(), ",");
	
		MesOutputVO vo =  new MesOutputVO();
		vo.seteProjectNum(mesOutputVO.geteProjectNum());
		for(int i = 0; i < eItemName.length; i++){
			vo.seteItemName(eItemName[i]); 
			vo.seteItemIndex(eItemIndex[i]); 
			vo.seteItemGubun(eItemGubun[i]); 
			vo.seteItemDate(eItemDate[i]); 
			vo.seteItemAffiliation(eItemAffiliation[i]); 
			vo.seteItemAuthor(eItemAuthor[i]); 
			vo.seteItemRemarks(eItemRemarks[i]); 
			
			mesOutputDAO.insertDeliverableInfo(vo);	
		}
		
		
		//파일정보 등록
		String[] eFileRowId	= EgovStringUtil.split(mesOutputVO.geteFileRowId(), ",");
		String[] eFileRowName	= EgovStringUtil.split(mesOutputVO.geteFileRowName(), ",");
		String[] eFileRowGubun	= EgovStringUtil.split(mesOutputVO.geteFileRowGubun(), ",");
		String[] eFileRowIndex	= EgovStringUtil.split(mesOutputVO.geteFileRowIndex(), ",");
		
		MesOutputVO fileInfo =  new MesOutputVO();
		fileInfo.seteProjectNum(mesOutputVO.geteProjectNum());
		for(int i = 0; i < eFileRowId.length; i++){
			fileInfo.seteFileRowId(eFileRowId[i]); 
			fileInfo.seteFileRowName(eFileRowName[i]); 
			fileInfo.seteFileRowGubun(eFileRowGubun[i]); 
			fileInfo.seteFileRowIndex(eFileRowIndex[i]); 
			
			mesOutputDAO.insertDeliverableFileInfo(fileInfo);	
		}
		
		
	}

	@Override
	public List insertDeliverableInfoList(MesOutputVO mesOutputVO) throws Exception {
		// TODO Auto-generated method stub
		return mesOutputDAO.insertDeliverableInfoList(mesOutputVO);
	}

	@Override
	public List insertDeliverableFileInfoList(MesOutputVO mesOutputVO) throws Exception {
		// TODO Auto-generated method stub
		return mesOutputDAO.insertDeliverableFileInfoList(mesOutputVO);
	}

	@Override
	public void eDeleteDeliverableInfo(MesOutputVO mesOutputVO) throws Exception {
		// TODO Auto-generated method stub
		mesOutputDAO.deleteDeliverableInfo(mesOutputVO);
		mesOutputDAO.deleteDeliverableFileInfo(mesOutputVO);
	}

	@Override
	public void eInsertReportInfo(MesOutputVO mesOutputVO) throws Exception {
		// TODO Auto-generated method stub
		//프로젝트 상태 업데이트
				mesOutputDAO.mesProjectStatusUpdate(mesOutputVO);
				
				//산출물정보 삭제
//				mesOutputDAO.deleteDeliverableInfo(mesOutputVO);	
				mesOutputDAO.deleteReportInfo(mesOutputVO);	
				//파일정보 삭제
//				mesOutputDAO.deleteDeliverableFileInfo(mesOutputVO);
				mesOutputDAO.deleteReportFileInfo(mesOutputVO);
				
				//산출물정보 등록
				String[] eItemName	= EgovStringUtil.split(mesOutputVO.geteItemName(), ",");
				String[] eItemIndex	= EgovStringUtil.split(mesOutputVO.geteItemIndex(), ",");
				String[] eItemGubun	= EgovStringUtil.split(mesOutputVO.geteItemGubun(), ",");
				String[] eItemDate	= EgovStringUtil.split(mesOutputVO.geteItemDate(), ",");
				String[] eItemAffiliation	= EgovStringUtil.split(mesOutputVO.geteItemAffiliation(), ",");
				String[] eItemAuthor	= EgovStringUtil.split(mesOutputVO.geteItemAuthor(), ",");
				String[] eItemRemarks	= EgovStringUtil.split(mesOutputVO.geteItemRemarks(), ",");
			
				MesOutputVO vo =  new MesOutputVO();
				vo.seteProjectNum(mesOutputVO.geteProjectNum());
				for(int i = 0; i < eItemName.length; i++){
					vo.seteItemName(eItemName[i]); 
					vo.seteItemIndex(eItemIndex[i]); 
					vo.seteItemGubun(eItemGubun[i]); 
					vo.seteItemDate(eItemDate[i]); 
					vo.seteItemAffiliation(eItemAffiliation[i]); 
					vo.seteItemAuthor(eItemAuthor[i]); 
					vo.seteItemRemarks(eItemRemarks[i]); 
					
					mesOutputDAO.insertReportInfo(vo);	
//					mesOutputDAO.insertDeliverableInfo(vo);	
				}
				
				
				//파일정보 등록
				String[] eFileRowId	= EgovStringUtil.split(mesOutputVO.geteFileRowId(), ",");
				String[] eFileRowName	= EgovStringUtil.split(mesOutputVO.geteFileRowName(), ",");
				String[] eFileRowGubun	= EgovStringUtil.split(mesOutputVO.geteFileRowGubun(), ",");
				String[] eFileRowIndex	= EgovStringUtil.split(mesOutputVO.geteFileRowIndex(), ",");
				
				MesOutputVO fileInfo =  new MesOutputVO();
				fileInfo.seteProjectNum(mesOutputVO.geteProjectNum());
				for(int i = 0; i < eFileRowId.length; i++){
					fileInfo.seteFileRowId(eFileRowId[i]); 
					fileInfo.seteFileRowName(eFileRowName[i]); 
					fileInfo.seteFileRowGubun(eFileRowGubun[i]); 
					fileInfo.seteFileRowIndex(eFileRowIndex[i]); 
					
					mesOutputDAO.insertReportFileInfo(fileInfo);	
//					mesOutputDAO.insertDeliverableFileInfo(fileInfo);	
				}
	}

	@Override
	public List insertReportInfoList(MesOutputVO mesOutputVO) throws Exception {
		// TODO Auto-generated method stub
		return mesOutputDAO.insertReportInfoList(mesOutputVO);
	}

	@Override
	public List insertReportFileInfoList(MesOutputVO mesOutputVO) throws Exception {
		// TODO Auto-generated method stub
		return mesOutputDAO.insertReportFileInfoList(mesOutputVO);
	}


	
}
