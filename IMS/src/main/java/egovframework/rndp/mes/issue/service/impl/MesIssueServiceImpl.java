package egovframework.rndp.mes.issue.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.rndp.com.utl.EgovStringUtil;
import egovframework.rndp.mes.asset.service.MesAssetVO;
import egovframework.rndp.mes.common.service.impl.MesCommonDAO;
import egovframework.rndp.mes.gubun.service.MesGubunVO;
import egovframework.rndp.mes.inspection.service.MesInspectionVO;
import egovframework.rndp.mes.issue.service.MesIssueService;
import egovframework.rndp.mes.issue.service.MesIssueVO;
import egovframework.rndp.mes.sign.service.MesSignService;

@Service("mesIssueService")
public class MesIssueServiceImpl implements MesIssueService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MesIssueServiceImpl.class);

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
	
	@Resource(name = "mesIssueDAO")
	private MesIssueDAO mesIssueDAO;

	@Override
	public void insertIssueInfo(MesIssueVO mesIssueVO) throws Exception {
		// TODO Auto-generated method stub
		String sSignStatus = "";
		if(!"Y".equals(mesIssueVO.getoSignPass())){
			sSignStatus="등록";
		}else {
			sSignStatus="제외";
		}

		mesIssueVO.setsSignStatus(sSignStatus);
		mesIssueDAO.insertIssueInfo(mesIssueVO);
		mesIssueVO.setsSignTableName("I_ISSUE");
		
		this.issueFileInsert(mesIssueVO);  // 파일
		mesIssueVO.setsSignTableKey(mesIssueVO.geteIssueKey());		
		
		//처리자 추가 10월 24일 요청사항.
		this.insertIssueHandlerInfo(mesIssueVO);
		this.insertIssueAssetInfo(mesIssueVO);
		//결재정보 등록
		this.insertSignInfoIssue(mesIssueVO);
		
	}
	
	// 파일
	private void issueFileInsert(MesIssueVO mesIssueVO) throws Exception {
		// TODO Auto-generated method stub
		 if(mesIssueVO.geteFileID() != null && !"".equals(mesIssueVO.geteFileID())) {
			  
	            String[] eFileID = EgovStringUtil.split(mesIssueVO.geteFileID(), ",");
	            String[] eFileName = EgovStringUtil.split(mesIssueVO.geteFileName(), ",");
	            String[] eFileExt = EgovStringUtil.split(mesIssueVO.geteFileExt(), ",");
	         
	            
	            MesIssueVO vo = new MesIssueVO();
	            for (int i = 0; i < eFileID.length; i++) {
	            	vo.seteIssueKey(mesIssueVO.geteIssueKey());
	            	vo.seteFileID(eFileID[i]);
	            	vo.seteFileName(eFileName[i]);
	            	vo.seteFileExt(eFileExt[i]);
	            	 
	            	mesIssueDAO.issueFileInsert(vo);
	            }
	        }
	}
	@Override
	public List eFileInfoList(MesIssueVO mesIssueVO) throws Exception {
		// TODO Auto-generated method stub
		return mesIssueDAO.eFileInfoList(mesIssueVO);
	}

	private void insertIssueHandlerInfo(MesIssueVO mesIssueVO) throws Exception {
		// TODO Auto-generated method stub
		 if(mesIssueVO.geteRowWorker() != null && !"".equals(mesIssueVO.geteRowWorker())) {
			  
	            String[] eRowWorker = EgovStringUtil.split(mesIssueVO.geteRowWorker(), ",");
	            String[] eRowDepartment = EgovStringUtil.split(mesIssueVO.geteRowDepartment(), ",");
	            String[] eRowPosition = EgovStringUtil.split(mesIssueVO.geteRowPosition(), ",");
	            String[] eRowContact = EgovStringUtil.split(mesIssueVO.geteRowContact(), ",");
	            String[] eRowNotes = EgovStringUtil.split(mesIssueVO.geteRowNotes(), ",");
	            
	            MesIssueVO vo = new MesIssueVO();
	            for (int i = 0; i < eRowWorker.length; i++) {
	            	vo.seteIssueKey(mesIssueVO.geteIssueKey());
	            	vo.seteRowWorker(eRowWorker[i]);
	            	vo.seteRowDepartment(eRowDepartment[i]);
	            	vo.seteRowPosition(eRowPosition[i]);
	            	vo.seteRowContact(eRowContact[i]);
	            	vo.seteRowNotes(eRowNotes[i]);
	            	 
	            	mesIssueDAO.insertIssueHandlerInfo(vo);
	                
	            }
	        }
	}

	private void insertSignInfoIssue(MesIssueVO mesIssueVO) throws Exception {
		// TODO Auto-generated method stub
		if(mesIssueVO.getsSignStatus().equals("등록") ){
			String sSignKey = mesSignService.insertSignTwo(mesIssueVO.getsSignTableKey(), mesIssueVO.getsSignTableName(), mesIssueVO.getsSignStaffKey(), mesIssueVO.getsSignStaffName(), mesIssueVO.getsSignStaffGubun(),mesIssueVO.getsSignStaffPosition());
		} 
	}

	private void insertIssueAssetInfo(MesIssueVO mesIssueVO) throws Exception {
		// TODO Auto-generated method stub
		 if(mesIssueVO.geteAssetKey() != null && !"".equals(mesIssueVO.geteAssetKey())) {
			  
	            String[] eAssetKey = EgovStringUtil.split(mesIssueVO.geteAssetKey(), ",");
	            MesIssueVO vo = new MesIssueVO();
	            for (int i = 0; i < eAssetKey.length; i++) {
	            	vo.seteIssueKey(mesIssueVO.geteIssueKey());
	            	vo.seteAssetKey(eAssetKey[i]);
	            	 
	            	mesIssueDAO.insertIssueAssetInfo(vo);
	                
	            }
	        }
	}

	@Override
	public List mesIssueInfoList(MesIssueVO mesIssueVO) throws Exception {
		// TODO Auto-generated method stub
		return mesIssueDAO.mesIssueInfoList(mesIssueVO);
	}

	@Override
	public int mesIssueInfoListCnt(MesIssueVO mesIssueVO) throws Exception {
		// TODO Auto-generated method stub
		return mesIssueDAO.mesIssueInfoListCnt(mesIssueVO);
	}

	@Override
	public MesIssueVO eSelectIssueInfo(MesIssueVO mesIssueVO) throws Exception {
		// TODO Auto-generated method stub
		return mesIssueDAO.eSelectIssueInfo(mesIssueVO); 
	}

	@Override
	public List eSelectIssueInfoAssetList(MesIssueVO mesIssueVO) throws Exception {
		// TODO Auto-generated method stub
		return mesIssueDAO.eSelectIssueInfoAssetList(mesIssueVO);
	}

	@Override
	public void updateIssueInfo(MesIssueVO mesIssueVO) throws Exception {
		// TODO Auto-generated method stub
		String sSignStatus = "";
		if(!"Y".equals(mesIssueVO.getoSignPass())){
			sSignStatus="등록";
		}else {
			sSignStatus="제외";
		}
		mesIssueVO.setsSignStatus(sSignStatus);
		mesIssueVO.setsSignTableName("I_ISSUE");
		mesIssueVO.setsSignTableKey(mesIssueVO.geteIssueKey());
		mesSignService.deleteSignItemTwo(mesIssueVO.getsSignTableKey(), mesIssueVO.getsSignTableName());
		
		mesIssueDAO.deleteIssueAssetInfo(mesIssueVO);
		
		mesIssueDAO.issueFileDelete(mesIssueVO);  // 파일
		
		mesIssueDAO.eIssueHandlerInfoDelete(mesIssueVO);
		
		mesIssueDAO.eIssueInfoUpdate(mesIssueVO);
		
		this.issueFileInsert(mesIssueVO);  // 파일
		
		this.insertIssueAssetInfo(mesIssueVO);
		
		this.insertIssueHandlerInfo(mesIssueVO);
		//결재정보 등록
		this.insertSignInfoIssue(mesIssueVO);
	}

	@Override
	public void deleteIssueInfo(MesIssueVO mesIssueVO) throws Exception {
		// TODO Auto-generated method stub
		mesIssueVO.seteIssueStatus("삭제");
		mesIssueDAO.eIssueStatusUpdate(mesIssueVO);
		mesIssueVO.setsSignTableName("I_ISSUE");
		mesIssueVO.setsSignTableKey(mesIssueVO.geteIssueKey());
		mesSignService.deleteSignItemTwo(mesIssueVO.getsSignTableKey(), mesIssueVO.getsSignTableName());
	}

	@Override
	public void eIssueProcessUpdate(MesIssueVO mesIssueVO) throws Exception {
		// TODO Auto-generated method stub
		mesIssueVO.seteIssueStatus("처리등록");
		mesIssueDAO.eIssueProcessUpdate(mesIssueVO);
		mesIssueDAO.deleteIssueAssetInfo(mesIssueVO);
		
		this.insertIssueAssetInfo(mesIssueVO);
		
	}

	@Override
	public List eMainIssueInfoList(MesIssueVO mesIssueVO) throws Exception {
		// TODO Auto-generated method stub
		return mesIssueDAO.eMainIssueInfoList(mesIssueVO); 
	}

	@Override
	public MesIssueVO eMainIssueInfo(MesIssueVO mesIssueVO) throws Exception {
		// TODO Auto-generated method stub
		return mesIssueDAO.eMainIssueInfo(mesIssueVO); 
	}

	@Override
	public MesIssueVO eMainTotalsInfo(MesIssueVO mesIssueVO) throws Exception {
		// TODO Auto-generated method stub
		return mesIssueDAO.eMainTotalsInfo(mesIssueVO); 
	}

	@Override
	public void mesSignIssueStatus(MesIssueVO mesIssueVO) throws Exception {
		// TODO Auto-generated method stub
		String status = mesIssueVO.getsSignStatus();
		if ("Y".equals(status)) {
			mesIssueVO.setsSignStatus("승인요청");
		} else if ("N".equals(status)) {
			mesIssueVO.setsSignStatus("등록");
		}
		mesIssueVO.setsSignTableName("I_ISSUE");
		
		if("Y".equals(status)) {
			mesSignService.updateSignStart(mesIssueVO.geteIssueKey(),mesIssueVO.getsSignTableName());
		}
		//주테이블의 상태값 갱신처리.
		mesIssueDAO.mesSignIssueStatus(mesIssueVO);
	}

	@Override
	public void mesUpdateSignStatus(MesIssueVO mesIssueVO) throws Exception {
		// TODO Auto-generated method stub
		mesIssueVO.setsSignStatus("반려");
		mesSignService.insertSignRejectionReason(mesIssueVO.getsSignTableKey(), mesIssueVO.getsSignTableName(), mesIssueVO.getsSignStaffKey(),mesIssueVO.getsSignContent());
		
		if("I_ISSUE".equals(mesIssueVO.getsSignTableName())){
			mesIssueVO.seteIssueKey(mesIssueVO.getsSignTableKey());
			
			mesIssueDAO.mesSignIssueStatus(mesIssueVO);
		}
	}

	@Override
	public List mesIssueExcelInfoList(MesIssueVO mesIssueVO) throws Exception {
		// TODO Auto-generated method stub
		return mesIssueDAO.mesIssueExcelInfoList(mesIssueVO); 
	}

	@Override
	public List eIssueHandlerInfoList(MesIssueVO mesIssueVO) throws Exception {
		// TODO Auto-generated method stub
		return mesIssueDAO.eIssueHandlerInfoList(mesIssueVO); 
	}
	 
}
