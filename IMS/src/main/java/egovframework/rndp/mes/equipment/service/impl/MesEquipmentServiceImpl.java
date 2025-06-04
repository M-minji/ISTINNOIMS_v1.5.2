package egovframework.rndp.mes.equipment.service.impl;

import java.util.ArrayList;
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
import egovframework.rndp.mes.equipment.service.MesEquipmentService;
import egovframework.rndp.mes.equipment.service.MesEquipmentVO;
import egovframework.rndp.mes.maintance.service.MesMaintanceVO;
import egovframework.rndp.mes.position.service.MesPositionVO;
import egovframework.rndp.mes.sign.service.MesSignService;

@Service("mesEquipmentService")
public class MesEquipmentServiceImpl implements MesEquipmentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MesEquipmentServiceImpl.class);

	@Resource(name = "mesEquipmentDAO")
	private MesEquipmentDAO mesEquipmentDAO;
	
	@Resource(name = "mesSignService")
	private MesSignService mesSignService;
	
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;
	
	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	@Override
	public void insertInfoEquipment(MesEquipmentVO mesEquipmentVO) throws Exception {
		// TODO Auto-generated method stub
		String sSignPass = "";
		if(!mesEquipmentVO.getoSignPass().equals("Y") ){
			sSignPass="등록";
		}else {
			sSignPass="제외";
		}
		
		
		// 미승인은 반입등록> 승인 및 제외처리 반입완료 > 반출등록 > 반출완료 
		mesEquipmentVO.seteStatus("반입완료");
		mesEquipmentVO.setsSignStatus(sSignPass);
		mesEquipmentDAO.insertInfoEquipment(mesEquipmentVO);
		
		this.insertEquipmentRowInfo(mesEquipmentVO);
		//결재정보 등록
		this.insertSignInfoEquipment(mesEquipmentVO);
	}

	private void insertSignInfoEquipment(MesEquipmentVO mesEquipmentVO) throws Exception {
		// TODO Auto-generated method stub
		if(mesEquipmentVO.getsSignStatus().equals("등록") ){
			String sSignKey = mesSignService.insertSignTwo(mesEquipmentVO.geteEquipmentInKey(), "E_EQUIPMENT_IN", mesEquipmentVO.getsSignStaffKey(), mesEquipmentVO.getsSignStaffName(), mesEquipmentVO.getsSignStaffGubun(),mesEquipmentVO.getsSignStaffPosition());
		} 
	}

	private void insertEquipmentRowInfo(MesEquipmentVO mesEquipmentVO) throws Exception {
		// TODO Auto-generated method stub
		  if(mesEquipmentVO.geteAssetTypeName() != null && !"".equals(mesEquipmentVO.geteAssetTypeName())) {
			  
	            String[] eAssetTypeName = EgovStringUtil.split(mesEquipmentVO.geteAssetTypeName(), ",");
	            String[] eAssetName = EgovStringUtil.split(mesEquipmentVO.geteAssetName(), ",");
	            String[] eAssetMaker = EgovStringUtil.split(mesEquipmentVO.geteAssetMaker(), ",");
	            String[] eAssetSNumber = EgovStringUtil.split(mesEquipmentVO.geteAssetSNumber(), ",");
	            String[] eAssetModel = EgovStringUtil.split(mesEquipmentVO.geteAssetModel(), ",");
	            String[] eAssetNetworkType = EgovStringUtil.split(mesEquipmentVO.geteAssetNetworkType(), ",");
	            String[] eAssetHostName = EgovStringUtil.split(mesEquipmentVO.geteAssetHostName(), ",");
	            String[] eAssetIp = EgovStringUtil.split(mesEquipmentVO.geteAssetIp(), ",");
	            String[] eAssetOs = EgovStringUtil.split(mesEquipmentVO.geteAssetOs(), ",");
	            
	            String[] eAssetPurpose = EgovStringUtil.split(mesEquipmentVO.geteAssetPurpose(), ",");
	            String[] eAssetType = EgovStringUtil.split(mesEquipmentVO.geteAssetType(), ",");
	            String[] eAssetDeviceType = EgovStringUtil.split(mesEquipmentVO.geteAssetDeviceType(), ",");
	            
	            MesEquipmentVO vo = new MesEquipmentVO();
	            
	            
	            
	            for (int i = 0; i < eAssetTypeName.length; i++) {
	            	vo.seteEquipmentInKey(mesEquipmentVO.geteEquipmentInKey());
	            	vo.seteAssetTypeName(eAssetTypeName[i]);
	            	vo.seteAssetName(eAssetName[i]);
	            	vo.seteAssetMaker(eAssetMaker[i]);
	            	vo.seteAssetSNumber(eAssetSNumber[i]);
	            	vo.seteAssetModel(eAssetModel[i]);
	            	vo.seteAssetNetworkType(eAssetNetworkType[i]);
	            	vo.seteAssetHostName(eAssetHostName[i]);
	            	vo.seteAssetIp(eAssetIp[i]);
	            	vo.seteAssetOs(eAssetOs[i]);
	            	
	            	vo.seteAssetPurpose(eAssetPurpose[i]);
	            	vo.seteAssetType(eAssetType[i]);
	            	vo.seteAssetDeviceType(eAssetDeviceType[i]);
	            	
	            	vo.seteStatus(mesEquipmentVO.geteStatus());
	            	vo.seteSignStatus(mesEquipmentVO.geteSignStatus());
	            	mesEquipmentDAO.insertEquipmentRowInfo(vo);
	            }
	        }
	}

	@Override
	public List selectEquipmentList(MesEquipmentVO mesEquipmentVO) throws Exception {
		// TODO Auto-generated method stub
		return mesEquipmentDAO.selectEquipmentList(mesEquipmentVO);
	}

	@Override
	public int selectEquipmentListCnt(MesEquipmentVO mesEquipmentVO) throws Exception {
		// TODO Auto-generated method stub
		return mesEquipmentDAO.selectEquipmentListCnt(mesEquipmentVO);
	}

	@Override
	public MesEquipmentVO selectEquipmentInfo(MesEquipmentVO mesEquipmentVO) throws Exception {
		// TODO Auto-generated method stub
		return (MesEquipmentVO) mesEquipmentDAO.selectEquipmentInfo(mesEquipmentVO);
	}

	@Override
	public List selectEquipmentRowItemList(MesEquipmentVO mesEquipmentVO) throws Exception {
		// TODO Auto-generated method stub
		return mesEquipmentDAO.selectEquipmentRowItemList(mesEquipmentVO);
	}

	@Override
	public void updateEquipmentStatus(MesEquipmentVO mesEquipmentVO) throws Exception {
		// TODO Auto-generated method stub
		mesEquipmentVO.seteStatus("삭제");
		mesEquipmentDAO.updateEquipmentStatus(mesEquipmentVO);
		
		mesSignService.deleteSignItemTwo(mesEquipmentVO.geteEquipmentInKey(), "E_EQUIPMENT_IN");
	}

	@Override
	public void updateEquipmentInfo(MesEquipmentVO mesEquipmentVO) throws Exception {
		// TODO Auto-generated method stub
		String sSignPass = "";
		if(!mesEquipmentVO.getoSignPass().equals("Y") ){
			sSignPass="등록";
		}else {
			sSignPass="제외";
		}
		mesEquipmentVO.seteStatus("반입완료");
		mesEquipmentVO.setsSignStatus(sSignPass);
		mesEquipmentDAO.deleteEquipmentRowInfo(mesEquipmentVO);
		//결재정보 삭제
		mesSignService.deleteSignItemTwo(mesEquipmentVO.geteEquipmentInKey(), "E_EQUIPMENT_IN");
		mesEquipmentDAO.updateEquipmentInfo(mesEquipmentVO);
		this.insertEquipmentRowInfo(mesEquipmentVO);
		//결재정보 등록
		this.insertSignInfoEquipment(mesEquipmentVO);
	}

	@Override
	public List selectEquipmentPopList(MesEquipmentVO mesEquipmentVO) throws Exception {
		// TODO Auto-generated method stub
		return mesEquipmentDAO.selectEquipmentPopList(mesEquipmentVO);
	}

	@Override
	public int selectEquipmentPopListCnt(MesEquipmentVO mesEquipmentVO) throws Exception {
		// TODO Auto-generated method stub
		return mesEquipmentDAO.selectEquipmentPopListCnt(mesEquipmentVO);
	}

	@Override
	public List selectEquipmentInfoList(MesEquipmentVO mesEquipmentVO) throws Exception {
		// TODO Auto-generated method stub
		String[] eEquipmentItemKeyArray 		= EgovStringUtil.split(mesEquipmentVO.geteEquipmentItemKey(), "_");
		
		Map<String, Object> hmap = new HashMap<String, Object>();
		ArrayList<String> keyList = new ArrayList<String>();
		for(int i = 0; i < eEquipmentItemKeyArray.length; i++){	
			keyList.add(eEquipmentItemKeyArray[i]);		
		}
		hmap.put("keyList", keyList);
		return mesEquipmentDAO.selectEquipmentInfoList(hmap);
	}

	@Override
	public void insertOutInfoEquipment(MesEquipmentVO mesEquipmentVO) throws Exception {
		// TODO Auto-generated method stub
		mesEquipmentVO.seteStatus("반출등록완료");
		mesEquipmentDAO.insertEquipmentOutInfo(mesEquipmentVO);
		
		this.updateOutInfoEquipmentItem(mesEquipmentVO);
	}

	private void updateOutInfoEquipmentItem(MesEquipmentVO mesEquipmentVO) throws Exception {
		// TODO Auto-generated method stub
		  if(mesEquipmentVO.geteEquipmentItemKey() != null && !"".equals(mesEquipmentVO.geteEquipmentItemKey())) {
			  
	            String[] eEquipmentItemKey = EgovStringUtil.split(mesEquipmentVO.geteEquipmentItemKey(), ",");
	            
	            MesEquipmentVO vo = new MesEquipmentVO();
	            
	            for (int i = 0; i < eEquipmentItemKey.length; i++) {
	            	  if (eEquipmentItemKey[i] == null || eEquipmentItemKey[i].trim().isEmpty()) {
	            	        // 빈값 또는 공백이 포함된 경우 continue로 스킵
	            	        continue;
	            	    }
            	    String trimmedKey = eEquipmentItemKey[i].trim();
	            	vo.seteEquipmentItemKey(trimmedKey);
	            	vo.seteEquipmentOutKey(mesEquipmentVO.geteEquipmentOutKey());
	            	vo.seteEntryExporterDate(mesEquipmentVO.geteEntryExporterDate());
	            	vo.seteExitExporter(mesEquipmentVO.geteExitExporter());
	            	vo.seteStatus(mesEquipmentVO.geteStatus());
	            	vo.seteSignStatus(mesEquipmentVO.geteSignStatus());
	            	
	            	mesEquipmentDAO.updateOutInfoEquipmentItem(vo);
	            }
	        }
	}

	@Override
	public List selectEquipmentOutList(MesEquipmentVO mesEquipmentVO) throws Exception {
		// TODO Auto-generated method stub
		return mesEquipmentDAO.selectEquipmentOutList(mesEquipmentVO);
	}

	@Override
	public int selectEquipmentOutListCnt(MesEquipmentVO mesEquipmentVO) throws Exception {
		// TODO Auto-generated method stub
		return mesEquipmentDAO.selectEquipmentOutListCnt(mesEquipmentVO);
	}

	@Override
	public MesEquipmentVO selectEquipmentOutInfo(MesEquipmentVO mesEquipmentVO) throws Exception {
		// TODO Auto-generated method stub
		return (MesEquipmentVO) mesEquipmentDAO.selectEquipmentOutInfo(mesEquipmentVO);
	}

	@Override
	public List selectEquipmentOutRowItemList(MesEquipmentVO mesEquipmentVO) throws Exception {
		// TODO Auto-generated method stub
		return mesEquipmentDAO.selectEquipmentOutRowItemList(mesEquipmentVO);
	}

	@Override
	public void updateEquipmentOutStatus(MesEquipmentVO mesEquipmentVO) throws Exception {
		// TODO Auto-generated method stub
		mesEquipmentVO.seteStatus("삭제");
		mesEquipmentDAO.updateEquipmentOutStatus(mesEquipmentVO);
		mesEquipmentDAO.updateEquipmentItemOutStatus(mesEquipmentVO);
		
		
	}

	@Override
	public void updateEquipmentOutInfo(MesEquipmentVO mesEquipmentVO) throws Exception {
		// TODO Auto-generated method stub
		mesEquipmentVO.seteStatus("반출등록완료");
		//기존 등록되어 있는 아이템값 처리
		mesEquipmentDAO.updateEquipmentItemOutStatus(mesEquipmentVO);
		
		mesEquipmentDAO.updateEquipmentOutInfo(mesEquipmentVO);
		
		this.updateOutInfoEquipmentItem(mesEquipmentVO);
	}

	@Override
	public void updateImportInfo(MesEquipmentVO mesEquipmentVO) throws Exception {
		// TODO Auto-generated method stub
		mesEquipmentVO.seteStatus("반출등록완료");
		mesEquipmentDAO.updateImportInfo(mesEquipmentVO);
	}

	@Override
	public void mesSignEquipment(MesEquipmentVO mesEquipmentVO) throws Exception {
		// TODO Auto-generated method stub
		String status = mesEquipmentVO.geteStatus();
		if ("Y".equals(status)) {
			mesEquipmentVO.setsSignStatus("승인요청");
		} else if ("N".equals(status)) {
			mesEquipmentVO.setsSignStatus("등록");
		}
		// S_SIGN 테이블 시작일 갱신처리
		if("Y".equals(status)) {
			mesSignService.updateSignStart(mesEquipmentVO.geteEquipmentInKey(),"E_EQUIPMENT_IN");
		}
		
		
		//주테이블의 상태값 갱신처리
		mesEquipmentDAO.updateEquipmentSingStatus(mesEquipmentVO);
		
	}

	@Override
	public void mesUpdateSignStatus(MesEquipmentVO mesEquipmentVO) throws Exception {
		// TODO Auto-generated method stub
		mesEquipmentVO.setsSignStatus("반려");
		mesEquipmentVO.seteEquipmentInKey(mesEquipmentVO.getsSignTableKey());
		mesEquipmentDAO.updateEquipmentSingStatus(mesEquipmentVO);
		
		mesSignService.insertSignRejectionReason(mesEquipmentVO.getsSignTableKey(), mesEquipmentVO.getsSignTableName(), mesEquipmentVO.getsSignStaffKey(),mesEquipmentVO.getsSignContent());
	}

	@Override
	public List selectEquipmentExcelList(MesEquipmentVO mesEquipmentVO) throws Exception {
		// TODO Auto-generated method stub
		return mesEquipmentDAO.selectEquipmentExcelList(mesEquipmentVO);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	 

}
