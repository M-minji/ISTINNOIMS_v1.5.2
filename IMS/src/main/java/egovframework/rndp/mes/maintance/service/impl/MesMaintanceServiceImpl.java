package egovframework.rndp.mes.maintance.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rndp.mes.asset.service.MesAssetVO;
import egovframework.rndp.mes.common.service.impl.MesCommonDAO;
import egovframework.rndp.mes.maintance.service.MesMaintanceService;
import egovframework.rndp.mes.maintance.service.MesMaintanceVO;

import egovframework.rndp.mes.sign.service.MesSignService;

@Service("mesMaintanceService")
public class MesMaintanceServiceImpl implements MesMaintanceService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MesMaintanceServiceImpl.class);

	/*공통사용 DAO common 및  Egov 공용 */
	@Resource(name = "mesCommonDAO")
	private MesCommonDAO mesCommonDAO; 
	
	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;
	
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;
	/*공통사용 DAO common 및  Egov 공용 */  
	
	@Resource(name = "mesSignService")
	private MesSignService mesSignService;
	
	@Resource(name = "mesMaintanceDAO")
	private MesMaintanceDAO mesMaintanceDAO;
	
	@Override
	public List selectMesMaintanceList(MesMaintanceVO mesMaintanceVO) throws Exception {
		// TODO Auto-generated method stub
		return mesMaintanceDAO.selectMesMaintanceList(mesMaintanceVO);
	}
	
	@Override
	public int selectMesMaintanceListCnt(MesMaintanceVO mesMaintanceVO) throws Exception {
		// TODO Auto-generated method stub
		return mesMaintanceDAO.selectMesMaintanceListCnt(mesMaintanceVO);
	}
	
	@Override
	public void insertMesMaintance(MesMaintanceVO mesMaintanceVO, HttpServletRequest request) throws Exception {
		
		mesMaintanceDAO.insertMesMaintance(mesMaintanceVO);
		
//		mesMaintanceVO.setmMaintanceKey(key);
		
//		this.fileHandler("insert", mesMaintanceVO);
		
		
		
		
		
		
	}
	
	@Override
	public List<MesMaintanceVO> getMaintanceFile(MesMaintanceVO mesMaintanceVO) throws Exception {
		return mesMaintanceDAO.getMaintanceFile(mesMaintanceVO);
	}
	
	public void fileHandler(String type, MesMaintanceVO vo) {
		
		String[] filenames = EgovStringUtil.split(vo.getAtchFileName(), ",");
		String[] fileids = EgovStringUtil.split(vo.geteAddFileId(), ",");
		
		if (type.equals("update") || type.equals("delete")) {
			mesMaintanceDAO.deleteMaintanceFile(vo);
		}
		
		if (type.equals("insert") || type.equals("update")) {
			for(int i = 0; i < fileids.length;  i++){
				MesMaintanceVO mesMaintanceVO = new MesMaintanceVO();
				mesMaintanceVO.setAtchFileName(filenames[i]);
				mesMaintanceVO.seteAddFileId(fileids[i]);
				mesMaintanceVO.setmMaintanceKey(vo.getmMaintanceKey());
				mesMaintanceVO.seteFileNum(vo.geteFileNum());
				
				mesMaintanceDAO.insertMaintanceFile(mesMaintanceVO);
				
			}

		}
	}

	@Override
	public MesMaintanceVO selectMaintanceInfo(MesMaintanceVO mesMaintanceVO) throws Exception {
		// TODO Auto-generated method stub
		return mesMaintanceDAO.selectMaintanceInfo(mesMaintanceVO);
	}
	
	@Override
	public void updateMesMaintance(MesMaintanceVO mesMaintanceVO, FileVO fileVO, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		String atchFileId = "";
		int sn = 0;
		
		// 파일수정
		if(fileVO.getAtchFileId() != null && !fileVO.getAtchFileId().equals("")){ // 등록되어있는 파일이 있는경우
			String[] idList = EgovStringUtil.split(fileVO.getAtchFileId(), ",");
			String[] snList = EgovStringUtil.split(fileVO.getFileSn(), ",");
			
			// comtnfiledetail table에서 동일한 file id 개수 가져오기
			fileVO.setAtchFileId(idList[0]);
			atchFileId = idList[0];
			sn = Integer.valueOf(snList[snList.length - 1]) + 1;
			
			List<FileVO> fileList = fileMngService.selFileList(fileVO);
			for(int i = 0; i < fileList.size(); i++){
				String gbn = "N";
				for(int j = 0; j < idList.length; j++){
					if((fileList.get(i).getFileSn().equals(snList[j]))){
						gbn = "Y";
					}
				}
				if(gbn.equals("N")){
					FileVO vo = new FileVO();
					vo.setAtchFileId(fileList.get(i).getAtchFileId());
					vo.setFileSn(fileList.get(i).getFileSn());
					
					fileMngService.delFile(vo);
				}
			}
		}
		
		
		this.fileHandler("update", mesMaintanceVO);
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> files = multipartRequest.getFileMap();
		List<FileVO> result = null;
		
		if(!files.isEmpty()){
			if(fileVO.getAtchFileId() != null && !fileVO.getAtchFileId().equals("")){
				fileMngService.delFileCom(atchFileId);
			}
			result = fileUtil.parseFileInf(files, "SR_", sn, atchFileId, "SRUploadPath");
			atchFileId = fileMngService.insertFileInfs(result);
		}
		
		mesMaintanceVO.setmMaintanceFile(atchFileId);
		
		mesMaintanceDAO.updateMesMaintance(mesMaintanceVO);
	}

	@Override
	public void deleteMesMaintance(MesMaintanceVO mesMaintanceVO) throws Exception {
		// TODO Auto-generated method stub
		this.fileHandler("delete", mesMaintanceVO);
		mesMaintanceDAO.deleteMesMaintance(mesMaintanceVO);
	}

	@Override
	public void insertMesProcess(MesMaintanceVO mesMaintanceVO, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub

//		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//		Map<String, MultipartFile> files = multipartRequest.getFileMap();
//		List<FileVO> result = null;
//		
//		String atchFileId = "";
//		
//		if(!files.isEmpty()){
//			result = fileUtil.parseFileInf(files, "SR_", 0, "", "SRUploadPath");
//			atchFileId = fileService.insertFileInfs(result);
//			mesMaintanceVO.setmProcessFile(atchFileId);
//		}
//
		if(!mesMaintanceVO.getoSignPass().equals("Y") ){
			String sSignKey = mesSignService.insertSign(mesMaintanceVO.getmMaintanceKey(), "M_MAINTANCE", mesMaintanceVO.getsSignStaffKey(), mesMaintanceVO.getsSignStaffName(),mesMaintanceVO.getsSignStaffPosition());
			mesMaintanceVO.setsSignKey(sSignKey);
			mesMaintanceVO.setsSignStatus("처리등록");
		}else{
			mesMaintanceVO.setsSignStatus("결제제외");
		}
		
		 mesMaintanceDAO.updateMesProcess(mesMaintanceVO);
		 
		 if(mesMaintanceVO.geteAssetKey() != null && !"".equals(mesMaintanceVO.geteAssetKey())) {
			  
	            String[] eAssetKey = EgovStringUtil.split(mesMaintanceVO.geteAssetKey(), ",");
	         
	            
	            MesMaintanceVO vo = new MesMaintanceVO();
	            for (int i = 0; i < eAssetKey.length; i++) {
	            	vo.setmMaintanceKey(mesMaintanceVO.getmMaintanceKey());
	            	vo.seteAssetKey(eAssetKey[i]);
	            	mesMaintanceDAO.insertAssetInfoItem(vo);
	            }
	        }
		 
		 if(mesMaintanceVO.getmIssueTypeName() == "복합장애") {
			 if(mesMaintanceVO.geteRowMaintanceKey() != null && !"".equals(mesMaintanceVO.geteRowMaintanceKey())) {
				  
		            String[] eRowMaintanceKey = EgovStringUtil.split(mesMaintanceVO.geteRowMaintanceKey(), ",");
		         
		            
		            MesMaintanceVO vo = new MesMaintanceVO();
		            for (int i = 0; i < eRowMaintanceKey.length; i++) {
		            	vo.setmMaintanceKey(mesMaintanceVO.getmMaintanceKey());
		            	vo.seteRowMaintanceKey(eRowMaintanceKey[i]);
		            	mesMaintanceDAO.insertMaintanceInfoItem(vo);
		            }
		        }
			 
	     }

//		this.fileHandlerProcess("insert", mesMaintanceVO);
	}

public void fileHandlerProcess(String type, MesMaintanceVO vo) {
		
		String fileid = vo.geteAddFileId();
		String filename = vo.getAtchFileName();
		String[] filenames = filename.split(",");
		String[] fileids = fileid.split(",");
		
		int i = 0;
		if (type.equals("update") || type.equals("delete")) {
			mesMaintanceDAO.deleteProcessFile(vo);
		}
		
		if (type.equals("insert") || type.equals("update")) {
			for (String id : fileids) {
				
				MesMaintanceVO mesMaintanceVO = new MesMaintanceVO();
				mesMaintanceVO.setAtchFileName(filenames[i]);
				mesMaintanceVO.seteAddFileId(id);
				mesMaintanceVO.setmMaintanceKey(vo.getmMaintanceKey());
				mesMaintanceVO.seteFileNum(vo.geteFileNum());
				
				
				
				mesMaintanceDAO.insertProcessFile(mesMaintanceVO);
				
				i++;
			}
			
		}
	}

	@Override
	public void updateMesProcess(MesMaintanceVO mesMaintanceVO, FileVO fileVO, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		String atchFileId = "";
		int sn = 0;
		
		// 파일수정
		if(fileVO.getAtchFileId() != null && !fileVO.getAtchFileId().equals("")){ // 등록되어있는 파일이 있는경우
			String[] idList = EgovStringUtil.split(fileVO.getAtchFileId(), ",");
			String[] snList = EgovStringUtil.split(fileVO.getFileSn(), ",");
			
			// comtnfiledetail table에서 동일한 file id 개수 가져오기
			fileVO.setAtchFileId(idList[0]);
			atchFileId = idList[0];
			sn = Integer.valueOf(snList[snList.length - 1]) + 1;
			
			List<FileVO> fileList = fileMngService.selFileList(fileVO);
			for(int i = 0; i < fileList.size(); i++){
				String gbn = "N";
				for(int j = 0; j < idList.length; j++){
					if((fileList.get(i).getFileSn().equals(snList[j]))){
						gbn = "Y";
					}
				}
				if(gbn.equals("N")){
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
		
		if(!files.isEmpty()){
			if(fileVO.getAtchFileId() != null && !fileVO.getAtchFileId().equals("")){
				fileMngService.delFileCom(atchFileId);
			}
			result = fileUtil.parseFileInf(files, "SR_", sn, atchFileId, "SRUploadPath");
			atchFileId = fileMngService.insertFileInfs(result);
		}
		
		mesMaintanceVO.setmProcessFile(atchFileId);
		 if(!mesMaintanceVO.getoSignPass().equals("Y")){
			 mesMaintanceVO.setsSignStatus("처리등록"); 
			 if(mesMaintanceVO.getsSignKey().equals("0")){
					String sSignKey = mesSignService.insertSign(mesMaintanceVO.getmMaintanceKey(), "M_MAINTANCE", mesMaintanceVO.getsSignStaffKey(), mesMaintanceVO.getsSignStaffName(), mesMaintanceVO.getsSignStaffPosition());
					mesMaintanceVO.setsSignKey(sSignKey);
			 	}else{
			 		String sSignKey = mesSignService.updateSign(mesMaintanceVO.getsSignKey(), mesMaintanceVO.getsSignStaffKey(), mesMaintanceVO.getsSignStaffName(), mesMaintanceVO.getsSignStaffPosition());
			 		mesMaintanceVO.setsSignKey(sSignKey);
			 	}
		 }else{
			 mesMaintanceVO.setsSignStatus("결제제외"); 
		 }
		mesMaintanceDAO.updateMesProcess(mesMaintanceVO);

		this.fileHandlerProcess("update", mesMaintanceVO);
	}

	@Override
	public void updateMesProcessNull(MesMaintanceVO mesMaintanceVO) throws Exception {
		// TODO Auto-generated method stub
		mesMaintanceDAO.updateMesProcessNull(mesMaintanceVO);
		mesSignService.deleteSign(mesMaintanceVO.getsSignKey());
	}

	@Override
	public void requestMaintance(MesMaintanceVO mesMaintanceVO) throws Exception {
		if(mesMaintanceVO.getGubun().equals("취소")){
			mesMaintanceVO.setsSignStatus("처리등록");
		}else{
			mesMaintanceVO.setsSignStatus("승인요청");
		}
		mesMaintanceDAO.updateMesSignDecison(mesMaintanceVO);
		MesMaintanceVO info = mesMaintanceDAO.selectMaintanceInfo(mesMaintanceVO);
		mesSignService.resetSignStatusStart(info.getsSignKey());
	}

	@Override
	public void setSignMaintance(MesMaintanceVO mesMaintanceVO) throws Exception {
		// TODO Auto-generated method stub
		
		mesSignService.updateSignStatus(mesMaintanceVO.getsSignKey(), mesMaintanceVO.getkStaffKey(), mesMaintanceVO.getsSignContent(), mesMaintanceVO.getsSignStaffPosition());
		mesMaintanceDAO.updateMaintanceStatus(mesMaintanceVO);
	}

	@Override
	public List<MesMaintanceVO> getProcessFile(MesMaintanceVO mesMaintanceVO) throws Exception {
		return mesMaintanceDAO.getProcessFile(mesMaintanceVO);
	}

	@Override
	public List selectMaintanceInfoList(MesMaintanceVO mesMaintanceVO) throws Exception {
		// TODO Auto-generated method stub
		String[] mMaintanceKeyList 		= EgovStringUtil.split(mesMaintanceVO.getmMaintanceKey(), "_");
		
		Map<String, Object> hmap = new HashMap<String, Object>();
		ArrayList<String> keyList = new ArrayList<String>();
		
		for(int i = 0; i < mMaintanceKeyList.length; i++){	
			keyList.add(mMaintanceKeyList[i]);		
		}
		hmap.put("keyList", keyList);
		return mesMaintanceDAO.selectMaintanceInfoList(hmap);
	}

	@Override
	public List selectMesMaintancePopList(MesMaintanceVO mesMaintanceVO) throws Exception {
		// TODO Auto-generated method stub
		return mesMaintanceDAO.selectMesMaintancePopList(mesMaintanceVO);
	}

	@Override
	public int selectMesMaintancePopListCnt(MesMaintanceVO mesMaintanceVO) throws Exception {
		// TODO Auto-generated method stub
		return mesMaintanceDAO.selectMesMaintancePopListCnt(mesMaintanceVO);
	}
}
