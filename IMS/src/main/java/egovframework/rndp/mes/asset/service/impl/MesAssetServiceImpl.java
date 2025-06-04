package egovframework.rndp.mes.asset.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.rndp.com.utl.EgovStringUtil;
import egovframework.rndp.mes.asset.service.MesAssetService;
import egovframework.rndp.mes.asset.service.MesAssetVO;
import egovframework.rndp.mes.bluprint.service.MesBlueprintVO;
import egovframework.rndp.mes.maintance.service.MesMaintanceVO;
import egovframework.rndp.mes.position.service.MesPositionVO;
import egovframework.rndp.mes.sign.service.MesSignService;
import egovframework.rndp.mes.sign.service.MesSignVO;

@Service("mesAssetService")
public class MesAssetServiceImpl implements MesAssetService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MesAssetServiceImpl.class);

	@Resource(name = "mesAssetDAO")
	private MesAssetDAO mesAssetDAO;
	
	@Resource(name = "mesSignService")
	private MesSignService mesSignService;
	
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;
	
	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	@Override
	public List selectMesAssetList(MesAssetVO mesAssetVO) throws Exception {
		return mesAssetDAO.selectMesAssetList(mesAssetVO);
	}

	@Override
	public int selectMesAssetListCnt(MesAssetVO mesAssetVO) throws Exception {
		return mesAssetDAO.selectMesAssetListCnt(mesAssetVO);
	}

	@Override
	public MesAssetVO selectMesAssetInfo(MesAssetVO mesAssetVO) throws Exception {
		return (MesAssetVO) mesAssetDAO.selectMesAssetInfo(mesAssetVO);
	}


	@Override
	public void insertMesAsset(MesAssetVO mesAssetVO, HttpServletRequest request) throws Exception {
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> file = multipartRequest.getFileMap();
		List<FileVO> result = null;
		String atchFileId = "";	//파일ID
		
		if (!file.isEmpty()) {
			result = fileUtil.parseFileInf(file, "Asset_", 0, "", "assetUploadPath");	// 지정된 경로에 파일 업로드 및 List<FileVO> result 값 셋팅
			atchFileId = fileService.insertFileInfs(result);		// DB에 파일 저장
			mesAssetVO.setaAssetImage(atchFileId);
		}
		mesAssetDAO.insertMesAsset(mesAssetVO);
	}


	@Override
	public void insertMesAssetAjax(MesAssetVO mesAssetVO) throws Exception {
		
		//결재 상태-엑셀 업로드임으로 기본 제외처리
		mesAssetVO.setsSignStatus("제외");
		mesAssetDAO.insertExcelInfoAsset(mesAssetVO);
		//결재정보 등록
		mesAssetVO.setsSignTableName("A_ASSET");
		this.insertSignInfo(mesAssetVO);
		
	}

	@Override
	public void updateMesAsset(MesAssetVO mesAssetVO, HttpServletRequest request, FileVO fileVO) throws Exception {
//		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//		Map<String, MultipartFile> files = multipartRequest.getFileMap();
//		List<FileVO> result = null;
//		
//		String atchFileId = fileVO.getAtchFileId();
//		int sn = 0;
//		if(!fileVO.getFileSn().equals("")){
//			sn = Integer.valueOf(fileVO.getFileSn());
//		}
//		
//		if(!files.isEmpty()){
//			if(atchFileId.equals("")){
//				result = fileUtil.parseFileInf(files, "Asset_", sn, atchFileId, "AssetProgramUploadPath");
//				atchFileId = fileService.insertFileInfs(result);
//			}
//		}
//		
//		mesAssetVO.setaAssetImage(atchFileId);
		//라이선스 정보 삭제
		mesAssetDAO.eDeleteAssetLicenseInfo(mesAssetVO);
		//결재정보 삭제처리
		
		mesSignService.deleteSignItemTwo(mesAssetVO.geteAssetKey(), "A_ASSET");
		
		String status = "";
		if(!mesAssetVO.getoSignPass().equals("Y") ){
			status="등록";
		}else {
			status="제외";
		}
		
		mesAssetVO.setsSignStatus(status);
		
		mesAssetDAO.updateMesAssetNew(mesAssetVO);
		
		//라이선스정보등록
		this.insertInfoAssetLicenseInfo(mesAssetVO);
		
		//결재정보 등록
		mesAssetVO.setsSignTableName("A_ASSET");
		this.insertSignInfo(mesAssetVO);
		
		this.eAssetModelUpdate(mesAssetVO);
		
	}

	@Override
	public void deleteMesAsset(MesAssetVO mesAssetVO) throws Exception {
		mesAssetDAO.deleteMesAsset(mesAssetVO);
		mesSignService.deleteSignItemTwo(mesAssetVO.geteAssetKey(), "A_ASSET");
	}

	@Override
	public List selectComtnFileDetail(MesAssetVO mesAssetVO) throws Exception {
		return mesAssetDAO.selectComtnFileDetail(mesAssetVO);
	}
	
	@Override
	public void insertMesAssetUse(MesAssetVO mesAssetVO) throws Exception {
		
		//use_h max key select
		mesAssetVO.setaAssetUseHKey(mesAssetDAO.selectMaxAssetUseKey(mesAssetVO));
		mesAssetVO.setaAssetUseItemCnt(String.valueOf(EgovStringUtil.split(mesAssetVO.getaAssetUseType(), ",").length));

		if(!mesAssetVO.getoSignPass().equals("Y") ){
			String sSignKey = mesSignService.insertSign(mesAssetVO.getaAssetUseHKey(), "A_ASSET", mesAssetVO.getsSignStaffKey(), mesAssetVO.getsSignStaffName(), mesAssetVO.getsSignStaffPosition());
			mesAssetVO.setsSignKey(sSignKey);
			mesAssetVO.setsSignStatus("등록");
		}else{
			mesAssetVO.setsSignStatus("결재제외");
		}
		
		//헤더 등록
		mesAssetDAO.insertMesAssetUseHeader(mesAssetVO);
		
		//리스트 등록
		this.insertMesAssetUseList(mesAssetVO);
		//자산상태 변경
		//mesAssetDAO.updateAssetStatus(mesAssetVO);
	}
	
	
	public void insertMesAssetUseList(MesAssetVO mesAssetVO) throws Exception {
		
		String[] aAssetUseTypeList				= EgovStringUtil.split(mesAssetVO.getaAssetUseType(), ",");
		String[] aAssetRequestDateList			= EgovStringUtil.split(mesAssetVO.getaAssetRequestDate(), ",");
		String[] aAssetInstallPlaceList			= EgovStringUtil.split(mesAssetVO.getaAssetInstallPlace(), ",");
		String[] aAssetInstallPlaceDetailList	= EgovStringUtil.split(mesAssetVO.getaAssetInstallPlaceDetail(), ",");		
		String[] aAssetUseHostList				= EgovStringUtil.split(mesAssetVO.getaAssetUseHost(), ",");
		String[] aAssetUseNetList				= EgovStringUtil.split(mesAssetVO.getaAssetUseNet(), ",");
		String[] aAssetUseIpList				= EgovStringUtil.split(mesAssetVO.getaAssetUseIp(), ",");
		String[] aAssetUseMacList				= EgovStringUtil.split(mesAssetVO.getaAssetUseMac(), ",");
		String[] aAssetUseOSList				= EgovStringUtil.split(mesAssetVO.getaAssetUseOS(), ",");
		String[] aAssetUseSectorList			= EgovStringUtil.split(mesAssetVO.getaAssetUseSector(), ",");
		String[] aAssetUseManagerList			= EgovStringUtil.split(mesAssetVO.getaAssetUseManager(), ",");
		String[] aAssetUsePhoneList				= EgovStringUtil.split(mesAssetVO.getaAssetUsePhone(), ",");
		
		MesAssetVO vo = new MesAssetVO();
		vo.setaAssetUseHKey(mesAssetVO.getaAssetUseHKey());
		vo.setaAssetKey(mesAssetVO.getaAssetKey());
		vo.setoSignPass(mesAssetVO.getoSignPass());
		vo.setsSignKey(mesAssetVO.getsSignKey());
		vo.setsSignStatus(mesAssetVO.getsSignStatus());
		
		for(int i = 0; i< aAssetUseTypeList.length; i++){
			
			vo.setaAssetUseType(aAssetUseTypeList[i]);				
			vo.setaAssetRequestDate(aAssetRequestDateList[i]);	
			vo.setaAssetInstallPlace(EgovStringUtil.getHtmlStrCnvr(aAssetInstallPlaceList[i]));			
			vo.setaAssetInstallPlaceDetail(EgovStringUtil.getHtmlStrCnvr(aAssetInstallPlaceDetailList[i]));	
			vo.setaAssetUseHost(EgovStringUtil.getHtmlStrCnvr(aAssetUseHostList[i]));				
			vo.setaAssetUseNet(EgovStringUtil.getHtmlStrCnvr(aAssetUseNetList[i]));				
			vo.setaAssetUseIp(EgovStringUtil.getHtmlStrCnvr(aAssetUseIpList[i]));				
			vo.setaAssetUseMac(EgovStringUtil.getHtmlStrCnvr(aAssetUseMacList[i]));			
			vo.setaAssetUseOS(EgovStringUtil.getHtmlStrCnvr(aAssetUseOSList[i]));				
			vo.setaAssetUseSector(EgovStringUtil.getHtmlStrCnvr(aAssetUseSectorList[i]));			
			vo.setaAssetUseManager(EgovStringUtil.getHtmlStrCnvr(aAssetUseManagerList[i]));			
			vo.setaAssetUsePhone(EgovStringUtil.getHtmlStrCnvr(aAssetUsePhoneList[i]));		
			
			//아이템 등록
			mesAssetDAO.insertMesAssetUse(vo);
			
		}
		
		
	}
	
	/**
	 * 장비이력현황
	 */
	@Override
	public List selectMesAssetUseList(MesAssetVO mesAssetVO) throws Exception {
		return mesAssetDAO.selectMesAssetUseList(mesAssetVO);
	}
	
	@Override
	public int selectMesAssetUseListCnt(MesAssetVO mesAssetVO) throws Exception {
		return mesAssetDAO.selectMesAssetUseListCnt(mesAssetVO);
	}
	
	

	@Override
	public MesAssetVO selectMesAssetUseInfo(MesAssetVO mesAssetVO) throws Exception {
		return mesAssetDAO.selectMesAssetUseInfo(mesAssetVO);
	}
	
	@Override
	public List selectMesAssetUseItemList(MesAssetVO mesAssetVO) throws Exception {
		return mesAssetDAO.selectMesAssetUseItemList(mesAssetVO);
	}
	
	@Override
	public void deleteMesAssetUse(MesAssetVO mesAssetVO) throws Exception {
		mesAssetDAO.deleteMesAssetUse(mesAssetVO);
		mesAssetDAO.deleteMesAssetUseHeader(mesAssetVO);
		mesSignService.deleteSign(mesAssetVO.getsSignKey());
	}
	
	@Override
	public void updateMesAssetUse(MesAssetVO mesAssetVO) throws Exception {
		
		mesAssetVO.setaAssetUseItemCnt(String.valueOf(EgovStringUtil.split(mesAssetVO.getaAssetUseType(), ",").length));
		
		 if(!mesAssetVO.getoSignPass().equals("Y")){
				mesAssetVO.setsSignStatus("등록");
			 if(mesAssetVO.getsSignKey().equals("0")){
				String sSignKey = mesSignService.insertSign(mesAssetVO.getaAssetUseKey(), "A_ASSET", mesAssetVO.getsSignStaffKey(), mesAssetVO.getsSignStaffName(), mesAssetVO.getsSignStaffPosition());
				mesAssetVO.setsSignKey(sSignKey);
			 }else{
				 String sSignKey = mesSignService.updateSign(mesAssetVO.getsSignKey(), mesAssetVO.getsSignStaffKey(), mesAssetVO.getsSignStaffName(), mesAssetVO.getsSignStaffPosition());
				mesAssetVO.setsSignKey(sSignKey);
			 	}
		 }else{
			mesAssetVO.setsSignStatus("결제제외");
		 }
		 
		 
		mesAssetDAO.updateMesAssetUseHeader(mesAssetVO);
		mesAssetDAO.deleteMesAssetUse(mesAssetVO);
		//리스트 등록
		this.insertMesAssetUseList(mesAssetVO);	
		
	}

	@Override
	public void requestMesAssetUse(MesAssetVO mesAssetVO) throws Exception {
		
		mesAssetDAO.updateMesSignDecison(mesAssetVO);		
		mesSignService.resetSignStatusStart(mesAssetVO.getsSignKey());
	}

	@Override
	public void setSignAsset(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub

		mesSignService.updateSignStatus(mesAssetVO.getsSignKey(), mesAssetVO.getkStaffKey(), mesAssetVO.getsSignContent(), mesAssetVO.getsSignDecison());
		mesAssetDAO.updateAssetSignStatus(mesAssetVO);
	}
	
	
	@Override
	public void updateNullFileInfo(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub

		mesAssetDAO.updateNullFileInfo(mesAssetVO);
	}
	
	
	/**
	 * 대상장비 조회 팝업
	 */
	@Override
	public List selectMesAssetInfoList(MesAssetVO mesAssetVO) throws Exception {
		return mesAssetDAO.selectMesAssetInfoList(mesAssetVO);
	}

	@Override
	public int selectMesAssetInfoListCnt(MesAssetVO mesAssetVO) throws Exception {
		return mesAssetDAO.selectMesAssetInfoListCnt(mesAssetVO);
	}

	@Override
	public void insertAssetSoftwareInfo(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		String status = "";
		if(!mesAssetVO.getoSignPass().equals("Y") ){
			status="등록";
		}else {
			status="제외";
		}
		
		
		  if(mesAssetVO.geteProductName() != null && !"".equals(mesAssetVO.geteProductName())) {
			  
	            String[] eManufacturer = EgovStringUtil.split(mesAssetVO.geteManufacturer(), ",");
	            String[] eProductName = EgovStringUtil.split(mesAssetVO.geteProductName(), ",");
	            String[] eVersion = EgovStringUtil.split(mesAssetVO.geteVersion(), ",");
	            String[] ePurchaseDate = EgovStringUtil.split(mesAssetVO.getePurchaseDate(), ",");
	            
	            String[] eStartDate = EgovStringUtil.split(mesAssetVO.geteStartDate(), ",");
	            String[] eEndDate = EgovStringUtil.split(mesAssetVO.geteEndDate(), ",");
	            String[] eLicenseQuantity = EgovStringUtil.split(mesAssetVO.geteLicenseQuantity(), ",");
//	            String[] eRemarks = EgovStringUtil.split(mesAssetVO.geteRemarks(), ",");
	            String[] eFileRowId = EgovStringUtil.split(mesAssetVO.geteFileRowId(), ",");
	            String[] eFileRowName = EgovStringUtil.split(mesAssetVO.geteFileRowName(), ",");
	            String[] eFileRowIndex = EgovStringUtil.split(mesAssetVO.geteFileRowIndex(), ",");
	            int idx = 0;
	            MesAssetVO vo = new MesAssetVO();
	            for (int i = 0; i < eManufacturer.length; i++) {
	            	vo.seteAuthor(mesAssetVO.geteAuthor());
	            	vo.setaAssetDate(mesAssetVO.getaAssetDate());
	            	
	            	vo.seteManufacturer(eManufacturer[i]);
	            	vo.seteProductName(eProductName[i]);
	            	vo.seteVersion(eVersion[i]);
	            	vo.setePurchaseDate(ePurchaseDate[i]);
	            	vo.seteStartDate(eStartDate[i]);
	            	vo.seteEndDate(eEndDate[i]);
	            	vo.seteLicenseQuantity(eLicenseQuantity[i]);
//	            	vo.seteRemarks(eRemarks[i]);
	            	vo.seteStatus(status);
	            	vo.setkStaffKey(mesAssetVO.getkStaffKey());
	            	vo.setkStaffName(mesAssetVO.getkStaffName());
	            	mesAssetDAO.insertAssetSoftwareInfo(vo);
	            	// 파일추가
	            	if(eFileRowIndex[idx] != null && !"".equals(eFileRowIndex[idx])){
		            	while((idx < eFileRowIndex.length) && (Integer.parseInt((eFileRowIndex[idx]))== i)) {
		            		vo.seteFileRowId(eFileRowId[idx]);
			            	vo.seteFileRowName(eFileRowName[idx]);
			            	vo.seteFileRowIndex(eFileRowIndex[idx++]);
			            	this.swFileInfo(vo);
		            	}
	            	}

	             
	                if(status.equals("등록")){
	        			String sSignKey = mesSignService.insertSignTwo(vo.geteSWRegisterKey(), "A_ASSET_SW", mesAssetVO.getsSignStaffKey(), mesAssetVO.getsSignStaffName(), mesAssetVO.getsSignStaffGubun(),mesAssetVO.getsSignStaffPosition());
	        		} 
	                
	            }
	        }
		  
	}
	
	// 소프트웨어 파일첨부
	private void swFileInfo(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		 if(mesAssetVO.geteFileRowId() != null && !"".equals(mesAssetVO.geteFileRowId())) {
			 mesAssetDAO.eInsertFileInfo(mesAssetVO);
	        }
	}
	@Override
	public List eSelectFileInfoList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.eSelectFileInfoList(mesAssetVO);
	}

	@Override
	public List mesSoftwareList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.mesSoftwareList(mesAssetVO);
	}

	@Override
	public int mesSoftwareListCnt(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.mesSoftwareListCnt(mesAssetVO);
	}

	@Override
	public List mesSoftwareExcelList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.mesSoftwareExcelList(mesAssetVO);
	}

	@Override
	public MesAssetVO mesSoftwareInfo(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return (MesAssetVO) mesAssetDAO.mesSoftwareInfo(mesAssetVO);
	}

	@Override
	public void mesSoftwareAssetDelete(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		  mesAssetDAO.mesSoftwareAssetDelete(mesAssetVO);
		  mesAssetDAO.mesSoftwareAssetLicenceDelete(mesAssetVO);
		  mesSignService.deleteSignItemTwo(mesAssetVO.geteSWRegisterKey(), "A_ASSET_SW");
		  
	}

	@Override
	public void mesSoftwareAssetUdate(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		String status = "";
		if(!mesAssetVO.getoSignPass().equals("Y") ){
			status="등록";
		}else {
			status="제외";
		}
		mesAssetVO.seteStatus(status);
		mesSignService.deleteSignItemTwo(mesAssetVO.geteSWRegisterKey(), "A_ASSET_SW");
		
		 mesAssetDAO.mesSoftwareAssetUdate(mesAssetVO);
		 
		 mesAssetDAO.eFileInfoDelete(mesAssetVO);	
		 
		//결재정보 등록
		mesAssetVO.setsSignTableName("A_ASSET_SW");
		mesAssetVO.seteAssetKey(mesAssetVO.geteSWRegisterKey());
		this.insertSignInfo(mesAssetVO);
		// 파일추가
        String[] eFileRowId = EgovStringUtil.split(mesAssetVO.geteFileRowId(), ",");
        String[] eFileRowName = EgovStringUtil.split(mesAssetVO.geteFileRowName(), ",");

        for(int i=0; i<eFileRowId.length; i++){
        	if(eFileRowId[i] != null && !"".equals(eFileRowId[i])){
        		mesAssetVO.seteFileRowId(eFileRowId[i]);
        		mesAssetVO.seteFileRowName(eFileRowName[i]);
        		mesAssetVO.seteFileRowIndex(String.valueOf(i));
            	this.swFileInfo(mesAssetVO);
        	}
    	}
	}

	@Override
	public void insertInfoAsset(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		String status = "";
		if(!mesAssetVO.getoSignPass().equals("Y") ){
			status="등록";
		}else {
			status="제외";
		}
		
		
		mesAssetVO.setsSignStatus(status);
		mesAssetDAO.insertInfoAsset(mesAssetVO);
		//라이선스정보등록
		this.insertInfoAssetLicenseInfo(mesAssetVO);
		//결재정보 등록
		mesAssetVO.setsSignTableName("A_ASSET");
		this.insertSignInfo(mesAssetVO);
		
		this.eAssetModelUpdate(mesAssetVO);
		
		
		
	}
	private void eAssetModelUpdate(MesAssetVO mesAssetVO) throws Exception {
		mesAssetDAO.eAssetModelUpdate(mesAssetVO);
		
	}

	public void insertInfoAssetLicenseInfo(MesAssetVO mesAssetVO) throws Exception {
		
		  if(mesAssetVO.geteSWRegisterKey() != null && !"".equals(mesAssetVO.geteSWRegisterKey())) {
			  
	            String[] eSWRegisterKey = EgovStringUtil.split(mesAssetVO.geteSWRegisterKey(), ",");
	            String[] eLicenseQuantity = EgovStringUtil.split(mesAssetVO.geteLicenseQuantity(), ",");
	         
	            
	            MesAssetVO vo = new MesAssetVO();
	            vo.seteAssetKey(mesAssetVO.geteAssetKey());
	            for (int i = 0; i < eSWRegisterKey.length; i++) {
	            	vo.seteSWRegisterKey(eSWRegisterKey[i]);
	            	vo.seteLicenseQuantity(eLicenseQuantity[i]);
	            	 
	            	mesAssetDAO.insertInfoAssetLicenseInfo(vo);
	            }
	        }
		
		
		
		
	}
	public void insertSignInfo(MesAssetVO mesAssetVO) throws Exception {
		if(mesAssetVO.getsSignStatus().equals("등록") ){
			String sSignKey = mesSignService.insertSignTwo(mesAssetVO.geteAssetKey(), mesAssetVO.getsSignTableName(), mesAssetVO.getsSignStaffKey(), mesAssetVO.getsSignStaffName(), mesAssetVO.getsSignStaffGubun(),mesAssetVO.getsSignStaffPosition());
		} 
	}
 
	@Override
	public List selectAssetInfoList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		String[] eAsseteyList 		= EgovStringUtil.split(mesAssetVO.geteAssetKey(), "_");
		
		Map<String, Object> hmap = new HashMap<String, Object>();
		ArrayList<String> keyList = new ArrayList<String>();
		for(int i = 0; i < eAsseteyList.length; i++){	
			keyList.add(eAsseteyList[i]);		
		}
		hmap.put("keyList", keyList);
		return mesAssetDAO.selectAssetInfoList(hmap);
	}

	@Override
	public void insertInfoCondition(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		
		String status = "";
		String eEntryStatus = "";
		if(!mesAssetVO.getoSignPass().equals("Y") ){
			status="등록";
			eEntryStatus="반출대기";
		}else {
			status="제외";
			eEntryStatus="반출완료";
		}
		mesAssetVO.setsSignStatus(status);
		
	
		
		mesAssetDAO.insertInfoCondition(mesAssetVO);
		  if(mesAssetVO.geteAssetKey() != null && !"".equals(mesAssetVO.geteAssetKey())) {
			  
	            String[] eAssetKey = EgovStringUtil.split(mesAssetVO.geteAssetKey(), ",");
	            MesAssetVO vo = new MesAssetVO();
	            for (int i = 0; i < eAssetKey.length; i++) {
	            	vo.seteEntryExitKey(mesAssetVO.geteEntryExitKey());
	            	vo.seteAssetKey(eAssetKey[i]);
	            	vo.setsSignStatus(status);
	            	 
	                mesAssetDAO.insertInfoConditionItem(vo);
	                //A_ASSET 테이블 상태값
	                vo.seteEntryStatus(eEntryStatus);
	                mesAssetDAO.updateAssetOutIn(vo);
	            }
	        }
		//결재정보 등록
		this.insertSignInfoCondition(mesAssetVO);
			
	}
	
	public void insertSignInfoCondition(MesAssetVO mesAssetVO) throws Exception {
		if(mesAssetVO.getsSignStatus().equals("등록") ){
			String sSignKey = mesSignService.insertSignTwo(mesAssetVO.geteEntryExitKey(), "E_ENTRY", mesAssetVO.getsSignStaffKey(), mesAssetVO.getsSignStaffName(), mesAssetVO.getsSignStaffGubun(),mesAssetVO.getsSignStaffPosition());
		} 
	}
 
	@Override
	public List selectConditionList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.selectConditionList(mesAssetVO);
	}

	@Override
	public int selectConditionCnt(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.selectConditionCnt(mesAssetVO);
	}

	@Override
	public void updateImportInfo(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		mesAssetDAO.updateImportInfo(mesAssetVO);
	}

	@Override
	public void insertInfoReplacement(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		String sSignStatus = "";
		if(!mesAssetVO.getoSignPass().equals("Y") ){
			sSignStatus="등록";
		}else {
			sSignStatus="제외";
		}
		mesAssetVO.setsSignStatus(sSignStatus);
		mesAssetDAO.insertInfoReplacement(mesAssetVO);
		 if(mesAssetVO.geteAssetKey() != null && !"".equals(mesAssetVO.geteAssetKey())) {
			  
	            String[] eAssetKey = EgovStringUtil.split(mesAssetVO.geteAssetKey(), ",");
	         
	            
	            MesAssetVO vo = new MesAssetVO();
	            for (int i = 0; i < eAssetKey.length; i++) {
	            	vo.seteReplacedKey(mesAssetVO.geteReplacedKey());
	            	vo.seteAssetKey(eAssetKey[i]);
	            	 
	                mesAssetDAO.insertInfoReplacementItem(vo);
	            }
	        }
		//결재정보 등록
			this.insertSignInfoReplacement(mesAssetVO);
	}

	private void insertSignInfoReplacement(MesAssetVO mesAssetVO)  throws Exception {
		// TODO Auto-generated method stub
		if(mesAssetVO.getsSignStatus().equals("등록") ){
			String sSignKey = mesSignService.insertSignTwo(mesAssetVO.geteReplacedKey(), "E_REPLACEMENT", mesAssetVO.getsSignStaffKey(), mesAssetVO.getsSignStaffName(), mesAssetVO.getsSignStaffGubun(),mesAssetVO.getsSignStaffPosition());
		} 
	}

	@Override
	public List selectReplacementList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.selectReplacementList(mesAssetVO);
	}

	@Override
	public int selectReplacementCnt(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.selectReplacementCnt(mesAssetVO);
	}

	@Override
	public MesAssetVO selectReplacementInfo(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return (MesAssetVO) mesAssetDAO.selectReplacementInfo(mesAssetVO);
	}

	@Override
	public List selectReplacementItemList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.selectReplacementItemList(mesAssetVO);
	}

	@Override
	public List selectConditionPopList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.selectConditionPopList(mesAssetVO);
	}

	@Override
	public int selectConditionPopCnt(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.selectConditionPopCnt(mesAssetVO);
	}

	@Override
	public List selectReplacementPopList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.selectReplacementPopList(mesAssetVO);
	}

	@Override
	public int selectReplacementPopCnt(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.selectReplacementPopCnt(mesAssetVO);
	}

	@Override
	public List selectMaintancePopList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.selectMaintancePopList(mesAssetVO);
	}

	@Override
	public int selectMaintancePopListCnt(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.selectMaintancePopListCnt(mesAssetVO);
	}

	@Override
	public void mesSoftwareDateUpdate(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		//갱신 이력 테이블에 저장,
		mesAssetDAO.mesSoftwareDateInsert(mesAssetVO);
		//라이선스테이블에 업데이트
		mesAssetDAO.mesSoftwareDateUpdate(mesAssetVO);
	}

	@Override
	public List mesSoftwareUpdateList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.mesSoftwareUpdateList(mesAssetVO);
	}

	@Override
	public List mesAssetSoftwareList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.mesAssetSoftwareList(mesAssetVO);
	}

	@Override
	public void mesSoftwareStatusUpdate(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		mesAssetDAO.mesSoftwareStatusUpdate(mesAssetVO);
	}

	@Override
	public List selectLicenseInfoList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		String[] eSWRegisterKeyList 		= EgovStringUtil.split(mesAssetVO.geteSWRegisterKey(), "_");
		
		Map<String, Object> hmap = new HashMap<String, Object>();
		ArrayList<String> keyList = new ArrayList<String>();
		for(int i = 0; i < eSWRegisterKeyList.length; i++){	
			keyList.add(eSWRegisterKeyList[i]);		
		}
		hmap.put("keyList", keyList);
		return mesAssetDAO.selectLicenseInfoList(hmap);
	}

	@Override
	public MesAssetVO eModelInfoCheck(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return (MesAssetVO) mesAssetDAO.eModelInfoCheck(mesAssetVO);
	}

	@Override
	public int eAssetSNumberInfoCheck(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.eAssetSNumberInfoCheck(mesAssetVO);
	}

	@Override
	public List selectMesAssetLicenseList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.selectMesAssetLicenseList(mesAssetVO);
	}

	@Override
	public void mesSignAsset(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
//		mesSignService.updateSignStatus(mesMaintanceVO.getsSignKey(), mesMaintanceVO.getkStaffKey(), mesMaintanceVO.getsSignContent(), mesMaintanceVO.getsSignStaffPosition());
//		mesMaintanceDAO.updateMaintanceStatus(mesMaintanceVO);
		String status = mesAssetVO.getsSignStatus();
		if ("Y".equals(status)) {
		    mesAssetVO.setsSignStatus("승인요청");
		} else if ("N".equals(status)) {
		    mesAssetVO.setsSignStatus("등록");
		}
		// S_SIGN 테이블 시작일 갱신처리
		if("Y".equals(status)) {
			mesSignService.updateSignStart(mesAssetVO.geteAssetKey(),"A_ASSET");
		}
		//주테이블의 상태값 갱신처리.
		mesAssetDAO.updateAssetStatus(mesAssetVO);
		
	}

	@Override
	public List selectMesAssetAndLicenseInfoList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.selectMesAssetAndLicenseInfoList(mesAssetVO);
	}

	@Override
	public int selectMesAssetAndLicenseInfoListCnt(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.selectMesAssetAndLicenseInfoListCnt(mesAssetVO);
	}

	@Override
	public void mesUpdateSignStatus(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		mesAssetVO.setsSignStatus("반려");
		mesAssetDAO.updateAssetStatus(mesAssetVO);
		
		mesSignService.insertSignRejectionReason(mesAssetVO.geteAssetKey(), "A_ASSET", mesAssetVO.getsSignStaffKey(),mesAssetVO.getsSignContent());
	}

	@Override
	public List selectConditionSingList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List selectConditionInfo(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.selectConditionInfo(mesAssetVO);
	}

	@Override
	public void eDeleteInfoCondition(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		mesAssetDAO.eDeleteInfoCondition(mesAssetVO);
		//아이템 테이블 상태값 업로드 처리
		
			List<MesAssetVO> info = mesAssetDAO.selectConditionInfo(mesAssetVO);
			MesAssetVO vo = new MesAssetVO();
	        for (int i = 0; i < info.size(); i++) {
	              vo = info.get(i);
	              vo.seteEntryStatus("반입완료");//반입완료 상태를 반출할려다 삭제했으니 
                  mesAssetDAO.updateAssetOutIn(vo);
	        }
	      //결재정보 삭제
	        mesSignService.deleteSignItemTwo(mesAssetVO.geteEntryExitKey(), "E_ENTRY");
		
		
	}

	@Override
	public void eUpdateInfoCondition(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub

		String status = "";
		String eEntryStatus = "";
		if(!mesAssetVO.getoSignPass().equals("Y") ){
			status="등록";
			eEntryStatus="반출대기";
		}else {
			status="제외";
			eEntryStatus="반출완료";
		}
		mesAssetVO.setsSignStatus(status);
		//아이템 삭제전에 상태값 변경처리
		List<MesAssetVO> info = mesAssetDAO.selectConditionInfo(mesAssetVO);
		MesAssetVO vo1 = new MesAssetVO();
        for (int i = 0; i < info.size(); i++) {
              vo1 = info.get(i);
              vo1.seteEntryStatus("반입완료");//반입완료 상태를 반출할려다 삭제했으니 
              mesAssetDAO.updateAssetOutIn(vo1);
        }
		
		//아이템삭제
		mesAssetDAO.eDeleteEntryItem(mesAssetVO);
		//헤더 업로드
		mesAssetDAO.updateInfoCondition(mesAssetVO);
		//결재정보 삭제
		mesSignService.deleteSignItemTwo(mesAssetVO.geteEntryExitKey(), "E_ENTRY");
		
		  if(mesAssetVO.geteAssetKey() != null && !"".equals(mesAssetVO.geteAssetKey())) {
			  
	            String[] eAssetKey = EgovStringUtil.split(mesAssetVO.geteAssetKey(), ",");
	            MesAssetVO vo = new MesAssetVO();
	            for (int i = 0; i < eAssetKey.length; i++) {
	            	vo.seteEntryExitKey(mesAssetVO.geteEntryExitKey());
	            	vo.seteAssetKey(eAssetKey[i]);
	            	vo.setsSignStatus(status);
	            	vo.seteEntryStaff(mesAssetVO.geteEntryStaff());
	            	vo.seteEntryExitDate(mesAssetVO.geteEntryExitDate());
	            	vo.seteEntryRequestReason(mesAssetVO.geteEntryRequestReason());
	            	 
	                mesAssetDAO.insertInfoConditionItem(vo);
	                //A_ASSET 테이블 상태값
	                vo.seteEntryStatus(eEntryStatus);
	                mesAssetDAO.updateAssetOutIn(vo);
	            }
	        }
		//결재정보 등록
		this.insertSignInfoCondition(mesAssetVO);
	}

	@Override
	public void insertInfoReceiving(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub

		String status = "";
		String eEntryStatus = "";
		if(!mesAssetVO.getoSignPass().equals("Y") ){
			status="등록";
			eEntryStatus="반입대기";
		}else {
			status="제외";
			eEntryStatus="반입완료";
		}
		mesAssetVO.setsSignStatus(status);
		
//		mesAssetDAO.insertInfoCondition(mesAssetVO);
		mesAssetDAO.insertInfoReceiving(mesAssetVO);
		  if(mesAssetVO.geteAssetKey() != null && !"".equals(mesAssetVO.geteAssetKey())) {
			  
	            String[] eAssetKey = EgovStringUtil.split(mesAssetVO.geteAssetKey(), ",");
	            MesAssetVO vo = new MesAssetVO();
	            for (int i = 0; i < eAssetKey.length; i++) {
	            	vo.seteEntryExitKey(mesAssetVO.geteEntryExitKey());
	            	vo.seteAssetKey(eAssetKey[i]);
	            	vo.setsSignStatus(status);
	            	 
//	                mesAssetDAO.insertInfoConditionItem(vo);
	                mesAssetDAO.insertInfoReceivingItem(vo);
	                //A_ASSET 테이블 상태값
	                vo.seteEntryStatus(eEntryStatus);
	                mesAssetDAO.updateAssetOutIn(vo);
	            }
	        }
		//결재정보 등록
		this.insertSignInfoReceiving(mesAssetVO);
	}
	public void insertSignInfoReceiving(MesAssetVO mesAssetVO) throws Exception {
		if(mesAssetVO.getsSignStatus().equals("등록") ){
			String sSignKey = mesSignService.insertSignTwo(mesAssetVO.geteEntryExitKey(), "E_IMPORT", mesAssetVO.getsSignStaffKey(), mesAssetVO.getsSignStaffName(), mesAssetVO.getsSignStaffGubun(),mesAssetVO.getsSignStaffPosition());
		} 
	}

	@Override
	public List selectReceivingList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.selectReceivingList(mesAssetVO);
	}

	@Override
	public void eDeleteInfoReceiving(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		mesAssetDAO.eDeleteInfoReceiving(mesAssetVO);
		//아이템 테이블 상태값 업로드 처리
		
			List<MesAssetVO> info = mesAssetDAO.selectReceivingList(mesAssetVO);
			MesAssetVO vo = new MesAssetVO();
	        for (int i = 0; i < info.size(); i++) {
	              vo = info.get(i);
	              vo.seteEntryStatus("반출완료");//반출완료 상태를 반입할려다 삭제했으니 
                  mesAssetDAO.updateAssetOutIn(vo);
	        }
	      //결재정보 삭제
		mesSignService.deleteSignItemTwo(mesAssetVO.geteEntryExitKey(), "E_IMPORT");
	}

	@Override
	public List selectReceivingInfoList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.selectReceivingInfoList(mesAssetVO);
	}

	@Override
	public int selectReceivingInfoListCnt(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.selectReceivingInfoListCnt(mesAssetVO);
	}

	@Override
	public void updateInfoReceiving(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub

		String status = "";
		String eEntryStatus = "";
		if(!mesAssetVO.getoSignPass().equals("Y") ){
			status="등록";
			eEntryStatus="반입대기";
		}else {
			status="제외";
			eEntryStatus="반입완료";
		}
		mesAssetVO.setsSignStatus(status);
		//아이템 삭제전에 상태값 변경처리
		List<MesAssetVO> info = mesAssetDAO.selectReceivingList(mesAssetVO);
		MesAssetVO vo1 = new MesAssetVO();
        for (int i = 0; i < info.size(); i++) {
              vo1 = info.get(i);
              vo1.seteEntryStatus("반출완료");//반입완료 상태를 반출할려다 삭제했으니 
              mesAssetDAO.updateAssetOutIn(vo1);
        }
		
		//아이템삭제
		mesAssetDAO.eDeleteImportItem(mesAssetVO);
		//헤더 업로드
		mesAssetDAO.updateInfoImport(mesAssetVO);
		//결재정보 삭제
		mesSignService.deleteSignItemTwo(mesAssetVO.geteEntryExitKey(), "E_IMPORT");
		
		  if(mesAssetVO.geteAssetKey() != null && !"".equals(mesAssetVO.geteAssetKey())) {
			  
	            String[] eAssetKey = EgovStringUtil.split(mesAssetVO.geteAssetKey(), ",");
	            MesAssetVO vo = new MesAssetVO();
	            for (int i = 0; i < eAssetKey.length; i++) {
	            	vo.seteEntryExitKey(mesAssetVO.geteEntryExitKey());
	            	vo.seteAssetKey(eAssetKey[i]);
	            	vo.setsSignStatus(status);
	            	 
//	                mesAssetDAO.insertInfoConditionItem(vo);
	                mesAssetDAO.insertInfoReceivingItem(vo);
	                //A_ASSET 테이블 상태값
	                vo.seteEntryStatus(eEntryStatus);
	                mesAssetDAO.updateAssetOutIn(vo);
	            }
	        }
		//결재정보 등록
		this.insertSignInfoReceiving(mesAssetVO);
	}

	@Override
	public List eAssetCountList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.eAssetCountList(mesAssetVO);
	}

	@Override
	public int eAssetNumberCheck(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.eAssetNumberCheck(mesAssetVO);
	}

	@Override
	public void deleteInfoReplacement(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		mesAssetDAO.deleteInfoReplacement(mesAssetVO);
		mesSignService.deleteSignItemTwo(mesAssetVO.geteReplacedKey(), "E_REPLACEMENT");
	}

	@Override
	public void updateInfoReplacement(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		String status = "";
		if(!mesAssetVO.getoSignPass().equals("Y") ){
			status="등록";
		}else {
			status="제외";
		}
		mesSignService.deleteSignItemTwo(mesAssetVO.geteReplacedKey(), "E_REPLACEMENT");
		mesAssetDAO.deleteInfoReplacementItem(mesAssetVO);
		
		mesAssetVO.setsSignStatus(status);
		mesAssetDAO.updateInfoReplacement(mesAssetVO);
		
//		mesAssetDAO.insertInfoReplacement(mesAssetVO);
		 if(mesAssetVO.geteAssetKey() != null && !"".equals(mesAssetVO.geteAssetKey())) {
			  
	            String[] eAssetKey = EgovStringUtil.split(mesAssetVO.geteAssetKey(), ",");
	         
	            
	            MesAssetVO vo = new MesAssetVO();
	            for (int i = 0; i < eAssetKey.length; i++) {
	            	vo.seteReplacedKey(mesAssetVO.geteReplacedKey());
	            	vo.seteAssetKey(eAssetKey[i]);
	            	 
	                mesAssetDAO.insertInfoReplacementItem(vo);
	            }
	        }
		 
		//결재정보 등록
			this.insertSignInfoReplacement(mesAssetVO);
		 
	}

	@Override
	public List eMainAssetMakerInfoList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.eMainAssetMakerInfoList(mesAssetVO);
	}
	@Override
	public List eMainAssetTypeInfoList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.eMainAssetTypeInfoList(mesAssetVO);
	}

	@Override
	public void updateProcessAssetInfo(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		mesAssetDAO.updateProcessAssetInfo(mesAssetVO);
	}

	@Override
	public List mainAssetEosList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.mainAssetEosList(mesAssetVO);
	}

	@Override
	public List mainAssetEolList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.mainAssetEolList(mesAssetVO);
	}

	@Override
	public List mainSoftwareList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.mainSoftwareList(mesAssetVO);
	}

	@Override
	public List mainAssetLifeStatusList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.mainAssetLifeStatusList(mesAssetVO);
	}

	@Override
	public void mesSignSoftwareAsset(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		String status = mesAssetVO.geteStatus();
		if ("Y".equals(status)) {
		    mesAssetVO.seteStatus("승인요청");
		} else if ("N".equals(status)) {
		    mesAssetVO.seteStatus("등록");
		}
		// S_SIGN 테이블 시작일 갱신처리
		if("Y".equals(status)) {
			mesSignService.updateSignStart(mesAssetVO.geteSWRegisterKey(),"A_ASSET_SW");
		}
		//주테이블의 상태값 갱신처리.
		mesAssetDAO.updateSoftwareStatus(mesAssetVO);
	}

	@Override
	public void mesUpdateAssetSWSignStatus(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		mesAssetVO.setsSignStatus("반려");
		mesAssetVO.seteStatus("반려");
		mesAssetVO.seteSWRegisterKey(mesAssetVO.geteAssetKey());
		mesAssetDAO.updateSoftwareStatus(mesAssetVO);
		
		mesSignService.insertSignRejectionReason(mesAssetVO.geteAssetKey(), "A_ASSET_SW", mesAssetVO.getsSignStaffKey(),mesAssetVO.getsSignContent());
	}

	@Override
	public void mesUpdateAssetUploadSignImgAjax(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		
		String sSignTableKey  =   mesAssetVO.getsSignTableKey();
		String sSignTableName =  mesAssetVO.getsSignTableName();
		String sSignStaffKey  =   mesAssetVO.getsSignStaffKey();
		String sSignDecison  =   mesAssetVO.getsSignDecison();
		String sSignContent  =   mesAssetVO.getsSignContent();
		String sSignStaffGubun  =   mesAssetVO.getsSignStaffGubun();
		
		MesSignVO eSingVo = new MesSignVO();
		
		eSingVo.setsSignTableKey(sSignTableKey);
		eSingVo.setsSignTableName(sSignTableName);
		eSingVo.setsSignStaffKey(sSignStaffKey);
		eSingVo.setsSignDecison(sSignDecison);
		eSingVo.setsSignContent(sSignContent);
		eSingVo.setsSignStaffGubun(sSignStaffGubun);
		
		//결재 정보 업데이트
		mesSignService.kwUploadSignImgAjax(eSingVo);
	}

	@Override
	public void mesSignCondition(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		String status = mesAssetVO.geteStatus();
		if ("Y".equals(status)) {
		    mesAssetVO.seteStatus("승인요청");
		} else if ("N".equals(status)) {
		    mesAssetVO.seteStatus("등록");
		}
		
		if("Y".equals(status)) {
			mesSignService.updateSignStart(mesAssetVO.geteEntryExitKey(),"E_ENTRY");
		}
		//주테이블의 상태값 갱신처리.
		mesAssetDAO.updateConditionStatus(mesAssetVO);
	}

	@Override
	public MesAssetVO selectConditionStatusInfo(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.selectConditionStatusInfo(mesAssetVO);
	}

	@Override
	public void mesSignConditionOut(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		String status = mesAssetVO.geteStatus();
		if ("Y".equals(status)) {
		    mesAssetVO.seteStatus("승인요청");
		} else if ("N".equals(status)) {
		    mesAssetVO.seteStatus("등록");
		}
		// S_SIGN 테이블 시작일 갱신처리
		if("Y".equals(status)) {
			mesSignService.updateSignStart(mesAssetVO.geteEntryExitKey(),"E_ENTRY");
		}
		//주테이블의 상태값 갱신처리.
		mesAssetDAO.updateConditionStatus(mesAssetVO);
	}

	@Override
	public void mesConditionSignStatus(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		mesAssetVO.setsSignStatus("반려");
		mesAssetVO.seteStatus("반려");
		mesAssetVO.seteEntryExitKey(mesAssetVO.geteAssetKey());
		mesAssetDAO.updateConditionStatus(mesAssetVO);
		
		mesSignService.insertSignRejectionReason(mesAssetVO.geteAssetKey(), "E_ENTRY", mesAssetVO.getsSignStaffKey(),mesAssetVO.getsSignContent());
	}

	@Override
	public void mesSignReplacement(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		String status = mesAssetVO.getsSignStatus();
		if ("Y".equals(status)) {
		    mesAssetVO.setsSignStatus("승인요청");
		} else if ("N".equals(status)) {
		    mesAssetVO.setsSignStatus("등록");
		}
		if("Y".equals(status)) {
			mesSignService.updateSignStart(mesAssetVO.geteReplacedKey(),"E_REPLACEMENT");
		}
		//주테이블의 상태값 갱신처리.
			mesAssetDAO.updateReplacementStatus(mesAssetVO);
	}

	@Override
	public void mesReplacedUpdateSignStatus(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		mesAssetVO.setsSignStatus("반려");
		mesAssetVO.seteReplacedKey(mesAssetVO.getsSignTableKey());
		mesAssetDAO.updateReplacementStatus(mesAssetVO);
		
		mesSignService.insertSignRejectionReason(mesAssetVO.getsSignTableKey(), mesAssetVO.getsSignTableName(), mesAssetVO.getsSignStaffKey(),mesAssetVO.getsSignContent());
	}

	@Override
	public List mainAssetLifeStatusNewList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.mainAssetLifeStatusNewList(mesAssetVO);
	}

	@Override
	public List selectMesAssetExcelList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.selectMesAssetExcelList(mesAssetVO);
	}

	@Override
	public List selectConditionExcelList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.selectConditionExcelList(mesAssetVO);
	}

	@Override
	public List selectReplacementExcelList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.selectReplacementExcelList(mesAssetVO);
	}

	@Override
	public List accessInfoList(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.accessInfoList(mesAssetVO);
	}
	
	@Override
	public List selectConditionDate(MesAssetVO mesAssetVO) throws Exception {
		// TODO Auto-generated method stub
		return mesAssetDAO.selectConditionDate(mesAssetVO);
	}
}
