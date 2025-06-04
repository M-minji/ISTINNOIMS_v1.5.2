package egovframework.rndp.mes.bluprint.service.impl;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rndp.com.cmm.service.DefultVO;
import egovframework.rndp.mes.bluprint.service.MesBlueprintService;
import egovframework.rndp.mes.bluprint.service.MesBlueprintVO;
import egovframework.rndp.mes.inspection.service.MesInspectionVO;
import egovframework.rndp.mes.issue.service.MesIssueVO;
import egovframework.rndp.mes.output.service.MesOutputVO;
import egovframework.rndp.mes.sign.service.MesSignService;
import egovframework.rndp.mes.sign.service.MesSignVO;
import egovframework.rndp.mes.sign.service.impl.MesSignDAO;

import org.apache.bcel.generic.NEW;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service("mesBlueprintService")
public class MesBlueprintServiceImpl implements MesBlueprintService {

	@Resource(name = "mesBlueprintDAO")
	private MesBlueprintDAO mesBlueprintDAO;


    @Resource(name="mesSignDAO")
    private MesSignDAO mesSignDAO;
    
	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;

	@Resource(name = "mesSignService")
	private MesSignService mesSignService;
	/*공통사용 DAO common 및  Egov 공용 */

	@Override
	public void insertMesBlueprint(HttpServletRequest request, MesBlueprintVO mesBlueprintVO) throws Exception{



		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> files = multipartRequest.getFileMap();
		List<FileVO> result = null;

		String atchFileId = "";

		if(!files.isEmpty()){
			result = fileUtil.parseFileInf(files, "Do_", 0, "", "bprintUploadPath");
			atchFileId = fileService.insertFileInfs(result);
			mesBlueprintVO.setBlueprintItemFileId(atchFileId);
		}



		String pk = mesBlueprintDAO.insertUpdateMesBlueprint(mesBlueprintVO);
		mesBlueprintVO.setBlueprintKey(pk);
		
		mesBlueprintVO.seteBlueprintVersion(mesBlueprintDAO.mesBlueprintVersion(mesBlueprintVO));
			
			this.mesBlueprintFile(mesBlueprintVO);
			this.insertMesBlueprintItem(mesBlueprintVO);

	}
	
		// 파일
		private void srFileInsert(MesBlueprintVO mesBlueprintVO) throws Exception {
			// TODO Auto-generated method stub
			 if(mesBlueprintVO.geteFileID() != null && !"".equals(mesBlueprintVO.geteFileID())) {
				  
		            String[] eFileID = EgovStringUtil.split(mesBlueprintVO.geteFileID(), ",");
		            String[] eFileName = EgovStringUtil.split(mesBlueprintVO.geteFileName(), ",");
		            String[] eFileExt = EgovStringUtil.split(mesBlueprintVO.geteFileExt(), ",");
		         
		            
		            MesBlueprintVO vo = new MesBlueprintVO();
		            for (int i = 0; i < eFileID.length; i++) {
		            	vo.seteIssueKey(mesBlueprintVO.geteIssueKey());
		            	vo.seteFileID(eFileID[i]);
		            	vo.seteFileName(eFileName[i]);
		            	vo.seteFileExt(eFileExt[i]);
		            	 
		            	mesBlueprintDAO.srFileInsert(vo);
		            }
		        }
		}

		@Override
		public List eFileInfoList(MesBlueprintVO mesBlueprintVO) throws Exception {
			// TODO Auto-generated method stub
			return mesBlueprintDAO.eFileInfoList(mesBlueprintVO);
		}
		

	public void mesBlueprintFile(MesBlueprintVO mesBlueprintVO ) throws Exception {
		MesBlueprintVO vo = new MesBlueprintVO();
		if(mesBlueprintVO.geteAddFileId1() != null && !mesBlueprintVO.geteAddFileId1().equals("")){
			String[] list  						= EgovStringUtil.split(mesBlueprintVO.geteAddFileId1(),",");
			String[] atchFileName1  			= EgovStringUtil.split(mesBlueprintVO.getAtchFileName1(),",");
			String[] eBlueprintItemEtcList		= EgovStringUtil.split(mesBlueprintVO.geteBlueprintItemEtc(),",");
			vo.setBlueprintEtc(mesBlueprintVO.getBlueprintEtc());
			vo.seteBlueprintVersion(mesBlueprintDAO.mesBlueprintVersion(mesBlueprintVO));
			for(int i=0; i<list.length; i++ ){
				vo.seteAddFileId(list[i]);
				vo.setAtchFileName(atchFileName1[i]);
				vo.seteBlueprintFileGubun("도면");
				vo.setBlueprintKey(mesBlueprintVO.getBlueprintKey());
				vo.seteBlueprintItemEtc(EgovStringUtil.getHtmlStrCnvr(eBlueprintItemEtcList[i]));
				mesBlueprintDAO.mesBlueprintFile(vo);
			}
		}
		
	}
	
	public void insertMesBlueprintItem(MesBlueprintVO mesBlueprintVO) throws Exception{
			mesBlueprintDAO.insertMesBlueprintItem(mesBlueprintVO);

	}

	public void insertMesBlueprintItem2(MesBlueprintVO mesBlueprintVO) throws Exception{
		mesBlueprintDAO.insertMesBlueprintItem(mesBlueprintVO);
		
	}

	@Override
	public List selectMesBlueprintList(MesBlueprintVO mesBlueprintVO) throws Exception {

		return mesBlueprintDAO.selectMesBlueprintList(mesBlueprintVO);
	}

	@Override
	public int selectMesBlueprintListCnt(MesBlueprintVO mesBlueprintVO) throws Exception {

		return mesBlueprintDAO.selectMesBlueprintListCnt(mesBlueprintVO);
	}
	
	@Override
	public MesBlueprintVO selectBlueprintInfo(MesBlueprintVO mesBlueprintVO) throws Exception{
		return mesBlueprintDAO.selectBlueprintInfo(mesBlueprintVO);
	}
	
	@Override
	public MesBlueprintVO selectBlueprintItemInfo(MesBlueprintVO mesBlueprintVO) throws Exception {
		return mesBlueprintDAO.selectBlueprintItemInfo(mesBlueprintVO);
	}
	
	@Override
	public void deleteMesBlueprint(MesBlueprintVO mesBlueprintVO) throws Exception{
//		mesBlueprintDAO.deleteMesBlueprintItem(mesBlueprintVO);
//		mesBlueprintDAO.deleteMesBlueprintItemBLPI(mesBlueprintVO);
//		mesBlueprintDAO.deleteMesBlueprint(mesBlueprintVO);
//		mesBlueprintDAO.deleteMesBlueprintBLPFILE(mesBlueprintVO);
		
		mesBlueprintDAO.deleteBlueprint(mesBlueprintVO);
		mesBlueprintVO.setsSignTableName("I_CHANGE");
		mesBlueprintVO.setsSignTableKey(mesBlueprintVO.geteChangeKey());
		//결제 정보 삭제  처리.
		mesSignService.deleteSignItemTwo(mesBlueprintVO.getsSignTableKey(),  mesBlueprintVO.getsSignTableName());
		
	}

	@Override
	public void deleteMesBlueprintitem(MesBlueprintVO mesBlueprintVO) throws Exception{
		mesBlueprintDAO.deleteMesBlueprintItemBLPIOne(mesBlueprintVO);
		mesBlueprintDAO.deleteMesBlueprintBLPFILEOne(mesBlueprintVO);
	}
	
	@Override
	public void updateMesBlueprint(HttpServletRequest request, FileVO fileVO, MesBlueprintVO mesBlueprintVO) throws Exception{
		mesBlueprintDAO.insertUpdateMesBlueprint(mesBlueprintVO);
		if(("new").equals(mesBlueprintVO.getNewPrint())) {
			String atchFileId = "";
			int sn = 0;

			// 파일수정
			if (fileVO.getAtchFileId() != null && !fileVO.getAtchFileId().equals("")) { // 등록되어있는 파일이 있는경우
				String[] idList = EgovStringUtil.split(fileVO.getAtchFileId(), ",");
				String[] snList = EgovStringUtil.split(fileVO.getFileSn(), ",");

				// comtnfiledetail table에서 동일한 file id 개수 가져오기
				fileVO.setAtchFileId(idList[0]);
				atchFileId = idList[0];
				sn = Integer.valueOf(snList[snList.length - 1]) + 1;

				List<FileVO> fileList = fileMngService.selFileList(fileVO);
				for (int i = 0; i < fileList.size(); i++) {
					String gbn = "N";
					for (int j = 0; j < idList.length; j++) {
						if ((fileList.get(i).getFileSn().equals(snList[j]))) {
							gbn = "Y";
						}
					}
					if (gbn.equals("N")) {
						FileVO vo = new FileVO();
						vo.setAtchFileId(fileList.get(i).getAtchFileId());
						vo.setFileSn(fileList.get(i).getFileSn());

						fileMngService.delFile(vo);
					}
				}
			}

			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> files = multipartRequest.getFileMap();
			List<FileVO> result = null;

			if (!files.isEmpty()) {
				if (fileVO.getAtchFileId() != null && !fileVO.getAtchFileId().equals("")) {
					fileMngService.delFileCom(atchFileId);
				}
				result = fileUtil.parseFileInf(files, "DO_", sn, atchFileId, "bprintUploadPath");
				atchFileId = fileMngService.insertFileInfs(result);
				mesBlueprintVO.setBlueprintItemFileId(atchFileId);
			}

//		mesBlueprintDAO.updateOrderItemBlueprint(mesBlueprintVO);
//		mesBlueprintDAO.deleteMesBlueprintItem(mesBlueprintVO);
		mesBlueprintDAO.updateMesBlueprintItemBLPI(mesBlueprintVO);
		if(!mesBlueprintVO.getoSignPass().equals("Y")){
			if(mesBlueprintVO.getsSignKey().equals("0")){
//				String sSignKey = mesSignService.insertSign(mesBlueprintVO.getBlueprintItemKey(), "BLPI", mesBlueprintVO.getsSignStaffKey(), mesBlueprintVO.getsSignStaffName());
//				mesBlueprintVO.setsSignKey(sSignKey);
			}else{
//				mesSignService.updateSign(mesBlueprintVO.getsSignKey(), mesBlueprintVO.getsSignStaffKey(), mesBlueprintVO.getsSignStaffName());
			}
			mesBlueprintVO.setsSignStatus("처리등록");
		}else{
			if(!mesBlueprintVO.getsSignKey().equals("0")){
			MesSignVO mesSignVO = new MesSignVO();
			mesSignVO.setsSignKey(mesBlueprintVO.getsSignKey());
			mesSignDAO.deleteSignItem(mesSignVO);
			}
			mesBlueprintVO.setsSignStatus("결제제외");
		}
			mesBlueprintDAO.updateMesBlueprintProcess(mesBlueprintVO);
			this.mesBlueprintFile2(mesBlueprintVO);
		}
		else{
			mesBlueprintVO.seteBlueprintVersion(mesBlueprintDAO.mesBlueprintVersion(mesBlueprintVO));
//			mesBlueprintDAO.updatedMesBlueprintItem(mesBlueprintVO);
			this.mesBlueprintFile1(mesBlueprintVO);
			this.insertMesBlueprintItem(mesBlueprintVO);
		}
	}
	
	public void mesBlueprintFile1(MesBlueprintVO mesBlueprintVO ) throws Exception {
		MesBlueprintVO vo = new MesBlueprintVO();
		if(mesBlueprintVO.geteAddFileId1() != null && !mesBlueprintVO.geteAddFileId1().equals("")){
			String[] list  						= EgovStringUtil.split(mesBlueprintVO.geteAddFileId1(),",");
			String[] atchFileName1  			= EgovStringUtil.split(mesBlueprintVO.getAtchFileName1(),",");
			String[] eBlueprintItemEtcList		= EgovStringUtil.split(mesBlueprintVO.geteBlueprintItemEtc(),",");
			
			vo.seteBlueprintVersion(mesBlueprintDAO.mesBlueprintVersion(mesBlueprintVO));
			vo.setBlueprintEtc(mesBlueprintVO.getBlueprintEtc());
			for(int i=0; i<list.length; i++ ){
				vo.seteAddFileId(list[i]);
				vo.setAtchFileName(atchFileName1[i]);
				vo.seteBlueprintFileGubun("도면");
				vo.setBlueprintKey(mesBlueprintVO.getBlueprintKey());
				vo.seteBlueprintItemEtc(EgovStringUtil.getHtmlStrCnvr(eBlueprintItemEtcList[i]));
				mesBlueprintDAO.mesBlueprintFile1(vo);
			}
		}
	}

	public void mesBlueprintFile2(MesBlueprintVO mesBlueprintVO ) throws Exception {
		mesBlueprintDAO.deleteMesBlueprintBLPFILE(mesBlueprintVO);
		MesBlueprintVO vo = new MesBlueprintVO();
		if(mesBlueprintVO.geteAddFileId1() != null && !mesBlueprintVO.geteAddFileId1().equals("")){
			String[] list  						= EgovStringUtil.split(mesBlueprintVO.geteAddFileId1(),",");
			String[] atchFileName1  			= EgovStringUtil.split(mesBlueprintVO.getAtchFileName1(),",");
			String[] eBlueprintItemEtcList		= EgovStringUtil.split(mesBlueprintVO.geteBlueprintItemEtc(),",");
			
			vo.seteBlueprintVersion(mesBlueprintVO.getVersion());
			vo.setBlueprintEtc(mesBlueprintVO.getBlueprintEtc());
			for(int i=0; i<list.length; i++ ){
				vo.seteAddFileId(list[i]);
				vo.setAtchFileName(atchFileName1[i]);
				vo.seteBlueprintFileGubun("도면");
				vo.setBlueprintKey(mesBlueprintVO.getBlueprintKey());
				vo.seteBlueprintItemEtc(EgovStringUtil.getHtmlStrCnvr(eBlueprintItemEtcList[i]));
				mesBlueprintDAO.mesBlueprintFile1(vo);
			}
		}
	}
	
	@Override
	public List selectMesBlueprintFile(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		return mesBlueprintDAO.selectMesBlueprintFile(mesBlueprintVO);
	}

	@Override
	public List selectMesBlueprintFileListU(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		return mesBlueprintDAO.selectMesBlueprintFileListU(mesBlueprintVO);
	}
	
	@Override
	public List selectMesBlueprintFileList(MesBlueprintVO mesBlueprintVO) throws Exception {
		return mesBlueprintDAO.selectMesBlueprintFileList(mesBlueprintVO);
	}

	@Override
	public List selectMesBlueprintFileEtc(MesBlueprintVO mesBlueprintVO) throws Exception {
		return mesBlueprintDAO.selectMesBlueprintFileEtc(mesBlueprintVO);
	}

	@Override
	public List selectBlueprintItemList(MesBlueprintVO mesBlueprintVO) throws Exception {
		return mesBlueprintDAO.selectBlueprintItemList(mesBlueprintVO);
	}

	@Override
	public void insertMesBlueprintProcess(MesBlueprintVO mesBlueprintVO, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> files = multipartRequest.getFileMap();
		List<FileVO> result = null;
		
		String atchFileId = "";
		
		if(!files.isEmpty()){
			result = fileUtil.parseFileInf(files, "SR_", 0, "", "SRUploadPath");
			atchFileId = fileService.insertFileInfs(result);
			mesBlueprintVO.seteBlueprintProcessFile(atchFileId);
		}

		if(!mesBlueprintVO.getoSignPass().equals("Y") ){
//		String sSignKey = mesSignService.insertSign(mesBlueprintVO.getBlueprintItemKey(), "BLPI", mesBlueprintVO.getsSignStaffKey(), mesBlueprintVO.getsSignStaffName());
//		mesBlueprintVO.setsSignKey(sSignKey);
		mesBlueprintVO.setsSignStatus("처리등록");
		}else{
			mesBlueprintVO.setsSignStatus("결제제외");
		}
		mesBlueprintDAO.updateMesBlueprintProcess(mesBlueprintVO);
	}
	
	@Override
	public void requestMesBlueprintProcess(MesBlueprintVO mesBlueprintVO) throws Exception {
		if(mesBlueprintVO.getGubun().equals("취소")){
		mesBlueprintVO.setsSignStatus("처리등록");
		}else{
			mesBlueprintVO.setsSignStatus("승인요청");
		}
		mesBlueprintDAO.requesteBlueprintProcess(mesBlueprintVO);
		MesBlueprintVO info = mesBlueprintDAO.selectBlueprintItemInfo(mesBlueprintVO);
		mesSignService.resetSignStatusStart(info.getsSignKey());
	}

	@Override
	public void setBlueprintProcess(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub

		mesSignService.updateSignStatus(mesBlueprintVO.getsSignKey(), mesBlueprintVO.getkStaffKey(), mesBlueprintVO.getsSignContent(), mesBlueprintVO.getsSignDecison());
		mesBlueprintDAO.updateBlueprintStatus(mesBlueprintVO);
	}

	@Override
	public List selectSignList(String blueprintKey) throws Exception {

		MesBlueprintVO mesBlueprintVO = new MesBlueprintVO();
		
		mesBlueprintVO.setBlueprintKey(blueprintKey);
		
		return mesBlueprintDAO.selectSignList(mesBlueprintVO);
	}

	@Override
	public void eChangeManagementInfoInssert(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		String sSignStatus = "";
		
		if(!"Y".equals(mesBlueprintVO.getoSignPass())){
			sSignStatus="등록";
		}else {
			sSignStatus="제외";
		}
		mesBlueprintVO.seteStatus("등록");
		mesBlueprintVO.setsSignStatus(sSignStatus);
		mesBlueprintVO.setsSignTableName("I_CHANGE");
		
		mesBlueprintDAO.eChangeManagementInfoInssert(mesBlueprintVO);
		
		mesBlueprintVO.setsSignTableKey(mesBlueprintVO.geteChangeKey());
 
		//상세내역 등록 추가.
		this.eChangeManagementDetailInfo(mesBlueprintVO);
		this.eChangeManagementFileInfo(mesBlueprintVO);
		this.eChangeManagementAssetInfo(mesBlueprintVO);
		
		//결재정보 등록
		this.insertSignInfoChange(mesBlueprintVO);
		
	}
	
	private void insertSignInfoChange(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		if(mesBlueprintVO.getsSignStatus().equals("등록") ){
			String sSignKey = mesSignService.insertSignTwo(mesBlueprintVO.getsSignTableKey(), mesBlueprintVO.getsSignTableName(), mesBlueprintVO.getsSignStaffKey(), mesBlueprintVO.getsSignStaffName(), mesBlueprintVO.getsSignStaffGubun(),mesBlueprintVO.getsSignStaffPosition());
		} 
	}

	private void eChangeManagementFileInfo(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		 if(mesBlueprintVO.geteFileRowId() != null && !"".equals(mesBlueprintVO.geteFileRowId())) {
			  
	            String[] eFileRowId = EgovStringUtil.split(mesBlueprintVO.geteFileRowId(), ",");
	            String[] eFileRowName = EgovStringUtil.split(mesBlueprintVO.geteFileRowName(), ",");
	            String[] eFileRowIndex = EgovStringUtil.split(mesBlueprintVO.geteFileRowIndex(), ",");
	            MesBlueprintVO vo = new MesBlueprintVO();
	            for (int i = 0; i < eFileRowId.length; i++) {
	            	vo.seteChangeKey(mesBlueprintVO.geteChangeKey());
	            	vo.seteFileRowId(eFileRowId[i]);
	            	vo.seteFileRowName(eFileRowName[i]);
	            	vo.seteFileRowIndex(eFileRowIndex[i]);
	            	mesBlueprintDAO.eInsertFileInfo(vo);
	                
	            }
	        }
	}
	private void eChangeManagementDetailInfo(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		if(mesBlueprintVO.geteWorker() != null && !"".equals(mesBlueprintVO.geteWorker())) {
			
			String[] eDepartment = EgovStringUtil.split(mesBlueprintVO.geteDepartment(), ",");
			String[] eRowIndex = EgovStringUtil.split(mesBlueprintVO.geteRowIndex(), ",");
			String[] eWorker = EgovStringUtil.split(mesBlueprintVO.geteWorker(), ",");
			String[] eWorkDate = EgovStringUtil.split(mesBlueprintVO.geteWorkDate(), ",");
			String[] eDescription = EgovStringUtil.split(mesBlueprintVO.geteDescription(), ",");
			MesBlueprintVO vo = new MesBlueprintVO();
			for (int i = 0; i < eDepartment.length; i++) {
				vo.seteChangeKey(mesBlueprintVO.geteChangeKey());
				vo.seteRowIndex(eRowIndex[i]);
				vo.seteDepartment(eDepartment[i]);
				vo.seteWorker(eWorker[i]);
				vo.seteWorkDate(eWorkDate[i]);
				vo.seteDescription(eDescription[i]);
				mesBlueprintDAO.eInsertDetailInfo(vo);
				
			}
		}
	}

	
	private void eChangeManagementAssetInfo(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		 if(mesBlueprintVO.geteAssetKey() != null && !"".equals(mesBlueprintVO.geteAssetKey())) {
			  
	            String[] eAssetKey = EgovStringUtil.split(mesBlueprintVO.geteAssetKey(), ",");
	            MesBlueprintVO vo = new MesBlueprintVO();
	            for (int i = 0; i < eAssetKey.length; i++) {
	            	vo.seteChangeKey(mesBlueprintVO.geteChangeKey());
	            	vo.seteAssetKey(eAssetKey[i]);
	            	 
	            	mesBlueprintDAO.eAssetInfoInsert(vo);
	                
	            }
	        }
	}

	@Override
	public List selectChangeList(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		return mesBlueprintDAO.selectChangeList(mesBlueprintVO);
	}

	@Override
	public int selectChangeListCnt(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		return mesBlueprintDAO.selectChangeListCnt(mesBlueprintVO);
	}

	@Override
	public MesBlueprintVO selectChangeInfo(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		return mesBlueprintDAO.selectChangeInfo(mesBlueprintVO);
	}

	@Override
	public List eSelectAssetInfoList(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		return mesBlueprintDAO.eSelectAssetInfoList(mesBlueprintVO);
	}

	@Override
	public void eIssueInfoInssert(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		String sSignStatus = "";
		
		if(!"Y".equals(mesBlueprintVO.getoSignPass())){
			sSignStatus="등록";
		}else {
			sSignStatus="제외";
		}
		
		mesBlueprintVO.seteStatus("등록");
		mesBlueprintVO.setsSignStatus(sSignStatus);
		mesBlueprintVO.setsSignTableName("I_BLUE_ISSUE");
		
		mesBlueprintDAO.eIssueInfoInssert(mesBlueprintVO);
		mesBlueprintVO.setsSignTableKey(mesBlueprintVO.geteIssueKey());
		
		this.eIssueAssetInfo(mesBlueprintVO);
		
		this.eIssueNotesListInfo(mesBlueprintVO);
		this.eIssueDetailListInfo(mesBlueprintVO);
		this.eIssueFileListInfo(mesBlueprintVO);
		//결재정보 등록
		this.insertSignInfoChange(mesBlueprintVO);
	}
 

	private void eIssueFileListInfo(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		 if(mesBlueprintVO.geteFileRowId() != null && !"".equals(mesBlueprintVO.geteFileRowId())) {
			  
	            String[] eFileRowId = EgovStringUtil.split(mesBlueprintVO.geteFileRowId(), ",");
	            String[] eFileRowName = EgovStringUtil.split(mesBlueprintVO.geteFileRowName(), ",");
	            String[] eFileRowIndex = EgovStringUtil.split(mesBlueprintVO.geteFileRowIndex(), ",");
	            MesBlueprintVO vo = new MesBlueprintVO();
	            for (int i = 0; i < eFileRowId.length; i++) {
	            	vo.seteIssueKey(mesBlueprintVO.geteIssueKey());
	            	vo.seteFileRowId(eFileRowId[i]);
	            	vo.seteFileRowName(eFileRowName[i]);
	            	vo.seteFileRowIndex(eFileRowIndex[i]);
	            	mesBlueprintDAO.insertIssueFileListInfo(vo);
	                
	            }
	        }
	}
	
	private void eIssueDetailListInfo(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		 if(mesBlueprintVO.geteWorker() != null && !"".equals(mesBlueprintVO.geteWorker())) {
			  
	            String[] eWorker = EgovStringUtil.split(mesBlueprintVO.geteWorker(), ",");
	            String[] eDepartment = EgovStringUtil.split(mesBlueprintVO.geteDepartment(), ",");
	            String[] eRowIndex = EgovStringUtil.split(mesBlueprintVO.geteRowIndex(), ",");
	            String[] eWorkDate = EgovStringUtil.split(mesBlueprintVO.geteWorkDate(), ",");
	            String[] eDescription = EgovStringUtil.split(mesBlueprintVO.geteDescription(), ",");
	            MesBlueprintVO vo = new MesBlueprintVO();
	            for (int i = 0; i < eWorker.length; i++) {
	            	vo.seteIssueKey(mesBlueprintVO.geteIssueKey());
	            	vo.seteWorker(eWorker[i]);
	            	vo.seteDepartment(eDepartment[i]);
	            	vo.seteRowIndex(eRowIndex[i]);
	            	vo.seteWorkDate(eWorkDate[i]);
	            	vo.seteDescription(eDescription[i]);
	            
	            	mesBlueprintDAO.insertIssueDetailListInfo(vo);
	            }
	        }
	}
	
	private void eIssueNotesListInfo(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		if(mesBlueprintVO.geteRowWorker() != null && !"".equals(mesBlueprintVO.geteRowWorker())) {
				
				String[] eRowWorker = EgovStringUtil.split(mesBlueprintVO.geteRowWorker(), ",");
				String[] eRowDepartment = EgovStringUtil.split(mesBlueprintVO.geteRowDepartment(), ",");
				String[] eRowPosition = EgovStringUtil.split(mesBlueprintVO.geteRowPosition(), ",");
				String[] eRowContact = EgovStringUtil.split(mesBlueprintVO.geteRowContact(), ",");
				String[] eRowNotes = EgovStringUtil.split(mesBlueprintVO.geteRowNotes(), ",");
				
				MesBlueprintVO vo = new MesBlueprintVO();
				for (int i = 0; i < eRowWorker.length; i++) {
					vo.seteIssueKey(mesBlueprintVO.geteIssueKey());
					vo.seteRowWorker(eRowWorker[i]);
					vo.seteRowDepartment(eRowDepartment[i]);
					vo.seteRowPosition(eRowPosition[i]);
					vo.seteRowContact(eRowContact[i]);
					vo.seteRowNotes(eRowNotes[i]);
					mesBlueprintDAO.insertIssueNotesListInfo(vo);
				}
			}
	}
	
	private void eIssueAssetInfo(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		 if(mesBlueprintVO.geteAssetKey() != null && !"".equals(mesBlueprintVO.geteAssetKey())) {
			  
	            String[] eAssetKey = EgovStringUtil.split(mesBlueprintVO.geteAssetKey(), ",");
	            MesBlueprintVO vo = new MesBlueprintVO();
	            for (int i = 0; i < eAssetKey.length; i++) {
	            	vo.seteIssueKey(mesBlueprintVO.geteIssueKey());
	            	vo.seteAssetKey(eAssetKey[i]);
	            	 
	            	mesBlueprintDAO.eIssueAssetInfoInsert(vo);
	                
	            }
	        }
	}
	
	@Override
	public List selectIssueList(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		return mesBlueprintDAO.selectIssueList(mesBlueprintVO);
	}

	@Override
	public int selectIssueListCnt(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		return mesBlueprintDAO.selectIssueListCnt(mesBlueprintVO);
	}

	@Override
	public MesBlueprintVO selectIssueInfo(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		return mesBlueprintDAO.selectIssueInfo(mesBlueprintVO);
	}

	@Override
	public List eSelectIssueAssetInfoList(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		return mesBlueprintDAO.eSelectIssueAssetInfoList(mesBlueprintVO);
	}

	@Override
	public void updateChangeInfo(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		String sSignStatus = "";
		
		if(!"Y".equals(mesBlueprintVO.getoSignPass())){
			sSignStatus="등록";
		}else {
			sSignStatus="제외";
		}
		
		mesBlueprintVO.seteStatus("등록");
		mesBlueprintVO.setsSignStatus(sSignStatus);
		mesBlueprintVO.setsSignTableName("I_CHANGE");
		mesBlueprintVO.setsSignTableKey(mesBlueprintVO.geteChangeKey());
		
		mesBlueprintDAO.eChangeManagementInfoUpdate(mesBlueprintVO);
		
		mesBlueprintDAO.eChangeAssetInfoDelete(mesBlueprintVO);	
		mesBlueprintDAO.eDetailInfoInfoDelete(mesBlueprintVO);	
		mesBlueprintDAO.eFileInfoDelete(mesBlueprintVO);	
		//결제 정보 삭제  처리.
		mesSignService.deleteSignItemTwo(mesBlueprintVO.getsSignTableKey(),  mesBlueprintVO.getsSignTableName());
		//상세내역 등록 추가.
		this.eChangeManagementDetailInfo(mesBlueprintVO);
		this.eChangeManagementFileInfo(mesBlueprintVO);
		
		this.eChangeManagementAssetInfo(mesBlueprintVO);
		//결재정보 등록
		this.insertSignInfoChange(mesBlueprintVO);
	}

	@Override
	public void selectIssueDelete(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		mesBlueprintDAO.selectIssueDelete(mesBlueprintVO);
		
		mesBlueprintVO.setsSignTableName("I_BLUE_ISSUE");
		mesBlueprintVO.setsSignTableKey(mesBlueprintVO.geteIssueKey());
	 
		//결제 정보 삭제  처리.
		mesSignService.deleteSignItemTwo(mesBlueprintVO.getsSignTableKey(),  mesBlueprintVO.getsSignTableName());
		
		
	}

	@Override
	public void updateIssueInfo(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		String sSignStatus = "";
		
		if(!"Y".equals(mesBlueprintVO.getoSignPass())){
			sSignStatus="등록";
		}else {
			sSignStatus="제외";
		}
		
		mesBlueprintVO.seteStatus("등록");
		mesBlueprintVO.setsSignStatus(sSignStatus);
		mesBlueprintVO.setsSignTableName("I_BLUE_ISSUE");
		mesBlueprintVO.setsSignTableKey(mesBlueprintVO.geteIssueKey());
		
		mesBlueprintDAO.updateIssueInfo(mesBlueprintVO);
		
		mesBlueprintDAO.eIssueAssetInfoDelete(mesBlueprintVO);
		mesBlueprintDAO.eIssueNotesInfoDelete(mesBlueprintVO);
		mesBlueprintDAO.eIssueDetailInfoDelete(mesBlueprintVO);
		mesBlueprintDAO.eIssueFileInfoDelete(mesBlueprintVO);
		//결제 정보 삭제  처리.
		mesSignService.deleteSignItemTwo(mesBlueprintVO.getsSignTableKey(),  mesBlueprintVO.getsSignTableName());
		
		this.eIssueAssetInfo(mesBlueprintVO);
		this.eIssueNotesListInfo(mesBlueprintVO);
		this.eIssueDetailListInfo(mesBlueprintVO);
		this.eIssueFileListInfo(mesBlueprintVO);
		
		//결재정보 등록
		this.insertSignInfoChange(mesBlueprintVO);
	}

	@Override
	public List selectSRList(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		return mesBlueprintDAO.selectSRList(mesBlueprintVO);
	}

	@Override
	public int selectSRListCnt(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		return mesBlueprintDAO.selectSRListCnt(mesBlueprintVO);
	}

	@Override
	public void eSRInfoInssert(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		String sSignStatus = "";
		
		if(!"Y".equals(mesBlueprintVO.getoSignPass())){
			sSignStatus="등록";
		}else {
			sSignStatus="제외";
		}
		mesBlueprintVO.seteStatus("등록");
		mesBlueprintVO.setsSignStatus(sSignStatus);
		mesBlueprintVO.setsSignTableName("I_BLUE_SR");
		mesBlueprintDAO.eSRInfoInssert(mesBlueprintVO);
		mesBlueprintVO.setsSignTableKey(mesBlueprintVO.geteIssueKey());
		
		this.srFileInsert(mesBlueprintVO);  // 파일
		this.eSRAssetInfo(mesBlueprintVO);
		//결재정보 등록
		this.insertSignInfoChange(mesBlueprintVO);
	}
	
	private void eSRAssetInfo(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		 if(mesBlueprintVO.geteAssetKey() != null && !"".equals(mesBlueprintVO.geteAssetKey())) {
			  
	            String[] eAssetKey = EgovStringUtil.split(mesBlueprintVO.geteAssetKey(), ",");
	            MesBlueprintVO vo = new MesBlueprintVO();
	            for (int i = 0; i < eAssetKey.length; i++) {
	            	vo.seteIssueKey(mesBlueprintVO.geteIssueKey());
	            	vo.seteAssetKey(eAssetKey[i]);
	            	 
	            	mesBlueprintDAO.eSRAssetInfoInsert(vo);
	                
	            }
	        }
	}

	@Override
	public MesBlueprintVO selectSRInfo(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		return mesBlueprintDAO.selectSRInfo(mesBlueprintVO);
	}

	@Override
	public List eSelectSRAssetInfoList(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		return mesBlueprintDAO.eSelectSRAssetInfoList(mesBlueprintVO);
	}

	@Override
	public void selectSRDelete(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		mesBlueprintDAO.selectSRDelete(mesBlueprintVO);
		
		mesBlueprintVO.setsSignTableName("I_BLUE_SR");
		mesBlueprintVO.setsSignTableKey(mesBlueprintVO.geteIssueKey());
		//결제 정보 삭제  처리.
		mesSignService.deleteSignItemTwo(mesBlueprintVO.getsSignTableKey(),  mesBlueprintVO.getsSignTableName());
	}

	@Override
	public void updateSRInfo(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		String sSignStatus = "";
		
		if(!"Y".equals(mesBlueprintVO.getoSignPass())){
			sSignStatus="등록";
		}else {
			sSignStatus="제외";
		}
		
		mesBlueprintVO.seteStatus("등록");
		mesBlueprintVO.setsSignStatus(sSignStatus);
		mesBlueprintVO.setsSignTableName("I_BLUE_SR");
		mesBlueprintVO.setsSignTableKey(mesBlueprintVO.geteIssueKey());
		mesBlueprintDAO.eSRInfoUpdate(mesBlueprintVO);
		mesBlueprintDAO.eSRAssetInfoDelete(mesBlueprintVO);
		
		mesBlueprintDAO.srFileDelete(mesBlueprintVO);  // 파일
		//결제 정보 삭제  처리.
		mesSignService.deleteSignItemTwo(mesBlueprintVO.getsSignTableKey(),  mesBlueprintVO.getsSignTableName());
		
		this.srFileInsert(mesBlueprintVO);  // 파일
		this.eSRAssetInfo(mesBlueprintVO);
		
		//결재정보 등록
		this.insertSignInfoChange(mesBlueprintVO);
	}

	@Override
	public List eSelectDeteliInfoList(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		return mesBlueprintDAO.eSelectDeteliInfoList(mesBlueprintVO);
	}

	@Override
	public List eSelectFileInfoList(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		return mesBlueprintDAO.eSelectFileInfoList(mesBlueprintVO);
	}

	@Override
	public List eSelectIssueDeteliInfoList(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		return mesBlueprintDAO.eSelectIssueDeteliInfoList(mesBlueprintVO);
	}

	@Override
	public List eSelectIssueFileInfoList(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		return mesBlueprintDAO.eSelectIssueFileInfoList(mesBlueprintVO);
	}

	@Override
	public List eSelectIssueNotesInfoList(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		return mesBlueprintDAO.eSelectIssueNotesInfoList(mesBlueprintVO);
	}

	@Override
	public void mesSignBlueprint(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		String status = mesBlueprintVO.getsSignStatus();
		if ("Y".equals(status)) {
			mesBlueprintVO.setsSignStatus("승인요청");
		} else if ("N".equals(status)) {
			mesBlueprintVO.setsSignStatus("등록");
		}
		mesBlueprintVO.setsSignTableKey(mesBlueprintVO.geteChangeKey());
		mesBlueprintVO.setsSignTableName("I_CHANGE");
		// S_SIGN 테이블 시작일 갱신처리
		if("Y".equals(status)) {
			mesSignService.updateSignStart(mesBlueprintVO.getsSignTableKey(),mesBlueprintVO.getsSignTableName());
		}
		//주테이블의 상태값 갱신처리.
		mesBlueprintDAO.mesSignBlueprint(mesBlueprintVO);
	}

	@Override
	public void mesUpdateSignStatus(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		mesBlueprintVO.setsSignStatus("반려");
		mesSignService.insertSignRejectionReason(mesBlueprintVO.getsSignTableKey(), mesBlueprintVO.getsSignTableName(), mesBlueprintVO.getsSignStaffKey(), mesBlueprintVO.getsSignContent());
		
		if("I_CHANGE".equals(mesBlueprintVO.getsSignTableName())){
			mesBlueprintVO.seteChangeKey(mesBlueprintVO.getsSignTableKey());
			//I_CHANGE테이블의 상태값 갱신처리.
			mesBlueprintDAO.mesSignBlueprint(mesBlueprintVO);
		}
		
		if("I_BLUE_ISSUE".equals(mesBlueprintVO.getsSignTableName())){
			mesBlueprintVO.seteIssueKey(mesBlueprintVO.getsSignTableKey());
			//I_BLUE_ISSUE주테이블의 상태값 갱신처리.
			mesBlueprintDAO.mesSignBlueIssue(mesBlueprintVO);
		}
		
		if("I_BLUE_SR".equals(mesBlueprintVO.getsSignTableName())){
			mesBlueprintVO.seteIssueKey(mesBlueprintVO.getsSignTableKey());
			//I_BLUE_ISSUE주테이블의 상태값 갱신처리.
			mesBlueprintDAO.mesSignBlueSr(mesBlueprintVO);
		}
	}

	@Override
	public void mesSignBlueIssue(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		String status = mesBlueprintVO.getsSignStatus();
		if ("Y".equals(status)) {
			mesBlueprintVO.setsSignStatus("승인요청");
		} else if ("N".equals(status)) {
			mesBlueprintVO.setsSignStatus("등록");
		}
		mesBlueprintVO.setsSignTableKey(mesBlueprintVO.geteIssueKey());
		mesBlueprintVO.setsSignTableName("I_BLUE_ISSUE");
		// S_SIGN 테이블 시작일 갱신처리
		if("Y".equals(status)) {
			mesSignService.updateSignStart(mesBlueprintVO.getsSignTableKey(),mesBlueprintVO.getsSignTableName());
		}
		//주테이블의 상태값 갱신처리.
		mesBlueprintDAO.mesSignBlueIssue(mesBlueprintVO);
	}

	@Override
	public void mesSignBlueSr(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		String status = mesBlueprintVO.getsSignStatus();
		if ("Y".equals(status)) {
			mesBlueprintVO.setsSignStatus("승인요청");
		} else if ("N".equals(status)) {
			mesBlueprintVO.setsSignStatus("등록");
		}
		mesBlueprintVO.setsSignTableKey(mesBlueprintVO.geteIssueKey());
		mesBlueprintVO.setsSignTableName("I_BLUE_SR");
		// S_SIGN 테이블 시작일 갱신처리
		if("Y".equals(status)) {
			mesSignService.updateSignStart(mesBlueprintVO.getsSignTableKey(),mesBlueprintVO.getsSignTableName());
		}
		//주테이블의 상태값 갱신처리.
		mesBlueprintDAO.mesSignBlueSr(mesBlueprintVO);
	}

	@Override
	public List selectChangeExcelList(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		return mesBlueprintDAO.selectChangeExcelList(mesBlueprintVO);
	}

	@Override
	public List selectIssueExcelList(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		return mesBlueprintDAO.selectIssueExcelList(mesBlueprintVO);
	}

	@Override
	public List selectSRExcelList(MesBlueprintVO mesBlueprintVO) throws Exception {
		// TODO Auto-generated method stub
		return mesBlueprintDAO.selectSRExcelList(mesBlueprintVO);
	}
}
