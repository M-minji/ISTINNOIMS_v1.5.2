package egovframework.rndp.mes.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.rndp.mes.common.service.MesCommonApprovalVO;
import egovframework.rndp.mes.common.service.MesCommonBaljuVO;
import egovframework.rndp.mes.common.service.MesCommonCompanyVO;
import egovframework.rndp.mes.common.service.MesCommonGubunVO;
import egovframework.rndp.mes.common.service.MesCommonItemVO;
import egovframework.rndp.mes.common.service.MesCommonProductionVO;
import egovframework.rndp.mes.common.service.MesCommonService;
 

@Service("mesCommonService")
public class MesCommonServiceImpl implements MesCommonService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MesCommonServiceImpl.class);

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
	
	@Override
	public List selectMesGubunCodeList(MesCommonGubunVO vo) throws Exception{
		return mesCommonDAO.selectMesGubunCodeList(vo);
	}


	@Override
	public List selectMesItemCateList(MesCommonItemVO vo) throws Exception{
		return mesCommonDAO.selectMesItemCateList(vo);
	}

	@Override
	public List selectPopupMesItemList(MesCommonItemVO vo) throws Exception{
		return mesCommonDAO.selectPopupMesItemList(vo);
	}
	
	@Override
	public int selectPopupMesItemListCnt(MesCommonItemVO vo) throws Exception{
		return mesCommonDAO.selectPopupMesItemListCnt(vo);
	}
	
		
	@Override
	public List selectMesCompanyList(MesCommonCompanyVO vo) throws Exception{
		return mesCommonDAO.selectMesCompanyList(vo);
	}
	
	@Override
	public int selectMesCompanyListCnt(MesCommonCompanyVO vo) throws Exception{
		return mesCommonDAO.selectMesCompanyListCnt(vo);
	}

	@Override
	public List selectApprovalStaffList(MesCommonBaljuVO vo) throws Exception {
		return mesCommonDAO.selectApprovalStaffList(vo);
	}

	@Override
	public List cateListSerachType(MesCommonItemVO vo) throws Exception{
		return mesCommonDAO.cateListSerachType(vo);
	}
	

	@Override
	public List envList() throws Exception {
		return mesCommonDAO.envList();
	}
 
	@Override
	public void deleteApproval(MesCommonApprovalVO vo) throws Exception { 
		mesCommonDAO.deleteApproval(vo);
	}

	@Override
	public List selectPositionList() throws Exception { 
		return mesCommonDAO.selectPositionList();
	}

	@Override
	public MesCommonProductionVO selectProductionInfo(MesCommonProductionVO vo) throws Exception {
		return mesCommonDAO.selectProductionInfo(vo);
	}

	@Override
	public List selectProductionItemInfo(MesCommonProductionVO vo) throws Exception {
		return mesCommonDAO.selectProductionItemInfo(vo);
	} 

	@Override
	public List inventoryListBacodePopList(MesCommonBaljuVO vo) throws Exception {
		return mesCommonDAO.inventoryListBacodePopList(vo);
	}
	
	@Override
	public int inventoryListBacodePopListCnt(MesCommonBaljuVO vo) throws Exception {
		return mesCommonDAO.inventoryListBacodePopListCnt(vo);
	}


	@Override
	public List selectCateList(MesCommonItemVO vo) throws Exception{
		return mesCommonDAO.selectCateList(vo);
	}

	@Override
	public List selectBomInfoList(MesCommonItemVO vo) throws Exception{
		return mesCommonDAO.selectBomInfoList(vo);
	}
	
	@Override
	public MesCommonItemVO selectMesItemInfo(MesCommonItemVO vo) throws Exception{
		return mesCommonDAO.selectMesItemInfo(vo);
	}

	@Override
	public List inventoryList(MesCommonBaljuVO vo) throws Exception {
		return mesCommonDAO.inventoryList(vo);
	}
	
	@Override
	public int inventoryListCnt(MesCommonBaljuVO vo) throws Exception {
		return mesCommonDAO.inventoryListCnt(vo);
	}
	 
}
