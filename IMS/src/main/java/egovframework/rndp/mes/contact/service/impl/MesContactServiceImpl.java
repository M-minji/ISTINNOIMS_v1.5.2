package egovframework.rndp.mes.contact.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rndp.mes.common.service.impl.MesCommonDAO;
import egovframework.rndp.mes.contact.service.MesContactService;
import egovframework.rndp.mes.contact.service.MesContactVO; 


@Service("mesContactService")
public class MesContactServiceImpl implements MesContactService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MesContactServiceImpl.class);

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
	
	@Resource(name = "mesContactDAO")
	private MesContactDAO mesContactDAO;

	@Override
	public void mesContactInsert(MesContactVO mesContactVO) throws Exception {
		// TODO Auto-generated method stub
		if(mesContactVO.geteContactName() != null && !("").equals(mesContactVO.geteContactName()) ){
			String[] eAgencyName 		= EgovStringUtil.split(mesContactVO.geteAgencyName(), ",");
			String[] eDepartmentName 		= EgovStringUtil.split(mesContactVO.geteDepartmentName(), ",");
			String[] eContactName 			= EgovStringUtil.split(mesContactVO.geteContactName(), ",");
			String[] ePhoneNumber 			= EgovStringUtil.split(mesContactVO.getePhoneNumber(), ",");
			String[] eEmail 			= EgovStringUtil.split(mesContactVO.geteEmail(), ",");
			String[] eNotes 			= EgovStringUtil.split(mesContactVO.geteNotes(), ",");
			
			
			MesContactVO vo = new MesContactVO();
			for(int i = 0; i < eAgencyName.length; i++){
				vo.seteAgencyName(eAgencyName[i]);
				vo.seteDepartmentName(eDepartmentName[i]);
				vo.seteContactName(eContactName[i]);
				vo.setePhoneNumber(ePhoneNumber[i]);
				vo.seteEmail(eEmail[i]);
				vo.seteNotes(eNotes[i]);
				
				mesContactDAO.mesContactInsert(vo);
			}
			
			
		}
		
	}

	@Override
	public List mesContactInfoList(MesContactVO mesContactVO) throws Exception {
		// TODO Auto-generated method stub
		return mesContactDAO.mesContactInfoList(mesContactVO);
	}

	@Override
	public int mesContactInfoListCnt(MesContactVO mesContactVO) throws Exception {
		// TODO Auto-generated method stub
		return mesContactDAO.mesContactInfoListCnt(mesContactVO);
	}

	@Override
	public MesContactVO mesContactInfo(MesContactVO mesContactVO) throws Exception {
		// TODO Auto-generated method stub
		return mesContactDAO.mesContactInfo(mesContactVO);
	}

	@Override
	public void mesContactDelete(MesContactVO mesContactVO) throws Exception {
		// TODO Auto-generated method stub
		mesContactDAO.mesContactDelete(mesContactVO);
	}

	@Override
	public void mesContactUpdate(MesContactVO mesContactVO) throws Exception {
		// TODO Auto-generated method stub
		mesContactDAO.mesContactUpdate(mesContactVO);
	}

	@Override
	public List mesContactInfoPopupList(MesContactVO mesContactVO) throws Exception {
		// TODO Auto-generated method stub
		return mesContactDAO.mesContactInfoPopupList(mesContactVO);
	}

	@Override
	public int mesContactInfoPopupListCnt(MesContactVO mesContactVO) throws Exception {
		// TODO Auto-generated method stub
		return mesContactDAO.mesContactInfoPopupListCnt(mesContactVO);
	}
	
	
	
	 
}
