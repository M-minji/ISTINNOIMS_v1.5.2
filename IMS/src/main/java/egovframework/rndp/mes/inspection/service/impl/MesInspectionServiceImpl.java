package egovframework.rndp.mes.inspection.service.impl;

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
import egovframework.rndp.mes.inspection.service.MesInspectionService;
import egovframework.rndp.mes.inspection.service.MesInspectionVO;
import egovframework.rndp.mes.issue.service.MesIssueVO;
import egovframework.rndp.mes.position.service.MesPositionVO;
import egovframework.rndp.mes.sign.service.MesSignService;
import egovframework.rndp.mes.user.service.MesUserVO;

@Service("mesInspectionService")
public class MesInspectionServiceImpl implements MesInspectionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MesInspectionServiceImpl.class);

	@Resource(name = "mesInspectionDAO")
	private MesInspectionDAO mesInspectionDAO;
	
	@Resource(name = "mesSignService")
	private MesSignService mesSignService;
	
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;
	
	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	@Override
	public void eInspectionInfoInsert(MesInspectionVO mesInspectionVO) throws Exception {
		// TODO Auto-generated method stub
		String sSignStatus = "";
		
		if(!"Y".equals(mesInspectionVO.getoSignPass())){
			sSignStatus="등록";
		}else {
			sSignStatus="제외";
			mesInspectionVO.setoSignPass("N");
		}
		mesInspectionVO.setsSignStatus(sSignStatus);
		mesInspectionDAO.eInspectionInfoInsert(mesInspectionVO);
		mesInspectionVO.setsSignTableName("INSPECTION");
		this.eInspectionFileInsert(mesInspectionVO);
		mesInspectionVO.setsSignTableKey(mesInspectionVO.geteInspectionKey());
		//결재정보 등록
		this.insertSignInfoInspection(mesInspectionVO);
	}

	private void insertSignInfoInspection(MesInspectionVO mesInspectionVO) throws Exception {
		// TODO Auto-generated method stub
		if(mesInspectionVO.getsSignStatus().equals("등록") ){
			String sSignKey = mesSignService.insertSignTwo(mesInspectionVO.getsSignTableKey(), mesInspectionVO.getsSignTableName(), mesInspectionVO.getsSignStaffKey(), mesInspectionVO.getsSignStaffName(), mesInspectionVO.getsSignStaffGubun(),mesInspectionVO.getsSignStaffPosition());
		} 
	}

	private void eInspectionFileInsert(MesInspectionVO mesInspectionVO) throws Exception {
		// TODO Auto-generated method stub
		 if(mesInspectionVO.geteFileID() != null && !"".equals(mesInspectionVO.geteFileID())) {
			  
	            String[] eFileID = EgovStringUtil.split(mesInspectionVO.geteFileID(), ",");
	            String[] eFileName = EgovStringUtil.split(mesInspectionVO.geteFileName(), ",");
	            String[] eFileExt = EgovStringUtil.split(mesInspectionVO.geteFileExt(), ",");
	         
	            
	            MesInspectionVO vo = new MesInspectionVO();
	            for (int i = 0; i < eFileID.length; i++) {
	            	vo.seteInspectionKey(mesInspectionVO.geteInspectionKey());
	            	vo.seteFileID(eFileID[i]);
	            	vo.seteFileName(eFileName[i]);
	            	vo.seteFileExt(eFileExt[i]);
	            	 
	            	mesInspectionDAO.eInspectionFileInsert(vo);
	            }
	        }
	}

	@Override
	public List mesInspectiontList(MesInspectionVO mesInspectionVO) throws Exception {
		// TODO Auto-generated method stub
		return mesInspectionDAO.mesInspectiontList(mesInspectionVO);
	}

	@Override
	public int mesInspectiontListCnt(MesInspectionVO mesInspectionVO) throws Exception {
		// TODO Auto-generated method stub
		return mesInspectionDAO.mesInspectiontListCnt(mesInspectionVO);
	}

	@Override
	public MesInspectionVO eInspectionInfo(MesInspectionVO mesInspectionVO) throws Exception {
		// TODO Auto-generated method stub
		return (MesInspectionVO) mesInspectionDAO.eInspectionInfo(mesInspectionVO);
	}

	@Override
	public List eFileInfoList(MesInspectionVO mesInspectionVO) throws Exception {
		// TODO Auto-generated method stub
		return mesInspectionDAO.eFileInfoList(mesInspectionVO);
	}

	@Override
	public void eInspectionInfoResultInsert(MesInspectionVO mesInspectionVO) throws Exception {
		// TODO Auto-generated method stub
		mesInspectionVO.seteStatus("점검결과등록");
		mesInspectionDAO.eInspectionUpdate(mesInspectionVO);
		
		this.eAssetInfoInsert(mesInspectionVO); 
	}

	private void eAssetInfoInsert(MesInspectionVO mesInspectionVO) throws Exception {
		// TODO Auto-generated method stub
		 if(mesInspectionVO.geteAssetKey() != null && !"".equals(mesInspectionVO.geteAssetKey())) {
			  
	            String[] eAssetKey = EgovStringUtil.split(mesInspectionVO.geteAssetKey(), ",");
	            String[] eField1 = EgovStringUtil.split(mesInspectionVO.geteField1(), ",");
	            String[] eField2 = EgovStringUtil.split(mesInspectionVO.geteField2(), ",");
	            String[] eField3 = EgovStringUtil.split(mesInspectionVO.geteField3(), ",");
	            String[] eField4 = EgovStringUtil.split(mesInspectionVO.geteField4(), ",");
	            String[] eField5 = EgovStringUtil.split(mesInspectionVO.geteField5(), ",");
	            String[] eItemRemark = EgovStringUtil.split(mesInspectionVO.geteItemRemark(), ",");
	            String[] eItemOther = EgovStringUtil.split(mesInspectionVO.geteItemOther(), ",");
	            String[] eItemInspectionDate = EgovStringUtil.split(mesInspectionVO.geteItemInspectionDate(), ",");
	         
	            
	            MesInspectionVO vo = new MesInspectionVO();
	            for (int i = 0; i < eAssetKey.length; i++) {
	            	vo.seteInspectionKey(mesInspectionVO.geteInspectionKey());
	            	vo.seteAssetKey(eAssetKey[i]);
	            	vo.seteField1(eField1[i]);
	            	vo.seteField2(eField2[i]);
	            	vo.seteField3(eField3[i]);
	            	vo.seteField4(eField4[i]);
	            	vo.seteField5(eField5[i]);
	            	vo.seteItemRemark(eItemRemark[i]);
	            	vo.seteItemOther(eItemOther[i]);
	            	vo.seteItemInspectionDate(eItemInspectionDate[i]);
	            	 
	            	mesInspectionDAO.eInspectionResultInsert(vo);
	            }
	        }
	}

	@Override
	public List eResultInfoList(MesInspectionVO mesInspectionVO) throws Exception {
		// TODO Auto-generated method stub
		return mesInspectionDAO.eResultInfoList(mesInspectionVO);
	}

	@Override
	public void eInspectionInfoDelete(MesInspectionVO mesInspectionVO) throws Exception {
		// TODO Auto-generated method stub
		mesInspectionVO.seteStatus("삭제");
		mesInspectionDAO.eInspectionInfoDelete(mesInspectionVO);
		mesInspectionVO.setsSignTableKey(mesInspectionVO.geteInspectionKey());
		mesInspectionVO.setsSignTableName("INSPECTION");
		//결제 정보 삭제  처리.
		mesSignService.deleteSignItemTwo(mesInspectionVO.getsSignTableKey(),  mesInspectionVO.getsSignTableName());
	}

	@Override
	public void eInspectionInfoUpdate(MesInspectionVO mesInspectionVO) throws Exception {
		// TODO Auto-generated method stub
		String sSignStatus = "";
		if(!"Y".equals(mesInspectionVO.getoSignPass())){
			sSignStatus="등록";
		}else {
			sSignStatus="제외";
		}
		
		mesInspectionVO.setsSignStatus(sSignStatus);
		mesInspectionVO.setsSignTableKey(mesInspectionVO.geteInspectionKey());
		mesInspectionVO.setsSignTableName("INSPECTION");
		//결제 정보 삭제  처리.
		mesSignService.deleteSignItemTwo(mesInspectionVO.getsSignTableKey(),  mesInspectionVO.getsSignTableName());
		
		//update 
		mesInspectionDAO.eInspectionInfoUpdate(mesInspectionVO);
		//파일 삭제
		mesInspectionDAO.eInspectionFileDelete(mesInspectionVO);
		//자산정보 삭제
		mesInspectionDAO.eInspectionAssetDelete(mesInspectionVO);
		
		this.eInspectionFileInsert(mesInspectionVO);
		this.eAssetInfoInsert(mesInspectionVO); 
		
		//결재정보 등록
		this.insertSignInfoInspection(mesInspectionVO);
	}

	@Override
	public void mesSignInspection(MesInspectionVO mesInspectionVO) throws Exception {
		// TODO Auto-generated method stub
		String status = mesInspectionVO.getsSignStatus();
		if ("Y".equals(status)) {
			mesInspectionVO.setsSignStatus("승인요청");
		} else if ("N".equals(status)) {
			mesInspectionVO.setsSignStatus("등록");
		}
		mesInspectionVO.setsSignTableKey(mesInspectionVO.geteInspectionKey());
		mesInspectionVO.setsSignTableName("INSPECTION");
		// S_SIGN 테이블 시작일 갱신처리
		if("Y".equals(status)) {
			mesSignService.updateSignStart(mesInspectionVO.getsSignTableKey(),mesInspectionVO.getsSignTableName());
		}
		//주테이블의 상태값 갱신처리.
		mesInspectionDAO.updateInspectionSingStatus(mesInspectionVO);
				
	}

	   
	@Override
	public void mesUpdateSignStatus(MesInspectionVO mesInspectionVO) throws Exception {
		// TODO Auto-generated method stub
		mesInspectionVO.setsSignStatus("반려");
		mesSignService.insertSignRejectionReason(mesInspectionVO.getsSignTableKey(), mesInspectionVO.getsSignTableName(), mesInspectionVO.getsSignStaffKey(), mesInspectionVO.getsSignContent());
		
		if("INSPECTION".equals(mesInspectionVO.getsSignTableName())){
			mesInspectionVO.seteInspectionKey(mesInspectionVO.getsSignTableKey());
			//주테이블의 상태값 갱신처리.
			mesInspectionDAO.updateInspectionSingStatus(mesInspectionVO);
		}
	}

	@Override
	public List mesInspectiontExcelList(MesInspectionVO mesInspectionVO) throws Exception {
		// TODO Auto-generated method stub
		return mesInspectionDAO.mesInspectiontExcelList(mesInspectionVO);
	}
	
	@Override
	public int selectFieldCount(MesInspectionVO mesInspectionVO) throws Exception {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			res =mesInspectionDAO.selectFieldCount(mesInspectionVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@Override
	public List selectFieldList(MesInspectionVO mesInspectionVO) throws Exception {
		// TODO Auto-generated method stub
		return mesInspectionDAO.selectFieldList(mesInspectionVO);
	}
	
	@Override
	public void mesInspectionFieldInsert(MesInspectionVO mesInspectionVO) throws Exception {
		// TODO Auto-generated method stub
		mesInspectionDAO.mesInspectionFieldInsert(mesInspectionVO);
	}
	
	@Override
	public MesInspectionVO selectFieldInfo(MesInspectionVO mesInspectionVO) throws Exception {
		// TODO Auto-generated method stub
		return mesInspectionDAO.selectFieldInfo(mesInspectionVO);
	}
	
	@Override
	public void mesInspectionFieldUpdate(MesInspectionVO mesInspectionVO) throws Exception {
		// TODO Auto-generated method stub
		mesInspectionDAO.mesInspectionFieldUpdate(mesInspectionVO);
	}
	
}
