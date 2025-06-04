package egovframework.rndp.mes.user.service.impl;

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
import egovframework.rndp.admin.intra.staff.service.StaffIntraVO;
import egovframework.rndp.admin.intra.staff.service.StaffMenuAuthVO;
import egovframework.rndp.mes.common.service.impl.MesCommonDAO;
import egovframework.rndp.mes.user.service.MesUserService;
import egovframework.rndp.mes.user.service.MesUserVO;

@Service("mesUserService")
public class MesUserServiceImpl implements MesUserService{

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
	
	@Resource(name="mesUserDAO")
	private MesUserDAO mesUserDAO;
	
	
	@Override
	public List selectUserffList(MesUserVO mesUserVO) throws Exception {
		// TODO Auto-generated method stub
		return mesUserDAO.selectUserffList(mesUserVO);
	}

	@Override
	public int selectUserCount(MesUserVO mesUserVO) throws Exception {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			res =mesUserDAO.selectUserCount(mesUserVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List mesUserMenuList(MesUserVO mesUserVO) throws Exception {
		// TODO Auto-generated method stub
		return mesUserDAO.mesUserMenuList(mesUserVO);
	}

	@Override
	public List selectSectorList() throws Exception {
		// TODO Auto-generated method stub
		return mesUserDAO.selectSectorList();
	}

	@Override
	public List selectClassList() throws Exception {
		// TODO Auto-generated method stub
		return mesUserDAO.selectClassList();
	}


	@Override
	public MesUserVO selectUserInfo(MesUserVO mesUserVO) throws Exception {
		// TODO Auto-generated method stub
		return mesUserDAO.selectUserInfo(mesUserVO);
	}

	@Override
	public int selectMaxRank() throws Exception {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			res = mesUserDAO.selectMaxRank();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public MesUserVO selectpositionInfo(MesUserVO mesUserVO) throws Exception {
		// TODO Auto-generated method stub
		return mesUserDAO.selectpositionInfo(mesUserVO);
	}

	@Override
	public List selectIclassList(MesUserVO mesUserVO) throws Exception {
		// TODO Auto-generated method stub
		return mesUserDAO.selectIclassList(mesUserVO);
	}

	@Override
	public List envList() throws Exception {
		// TODO Auto-generated method stub
		return mesUserDAO.envList();
	}

	@Override
	public void updateEnv(MesUserVO vo) throws Exception {
		// TODO Auto-generated method stub
		mesUserDAO.updateEnv(vo);
	}

	@Override
	public void mesUserLevelInsert(MesUserVO mesUserVO) throws Exception {
		// TODO Auto-generated method stub
		mesUserDAO.mesUserLevelInsert(mesUserVO);
	}

	@Override
	public MesUserVO selectIclassInfo(MesUserVO mesUserVO) throws Exception {
		// TODO Auto-generated method stub
		return mesUserDAO.selectIclassInfo(mesUserVO);
	}

	@Override
	public void mesUserLevelUpdate(MesUserVO mesUserVO) throws Exception {
		// TODO Auto-generated method stub
		mesUserDAO.mesUserLevelUpdate(mesUserVO);
	}

	@Override
	public void mesUserLevelDelete(MesUserVO mesUserVO) throws Exception {
		// TODO Auto-generated method stub
		mesUserDAO.mesUserLevelDelete(mesUserVO);
	}
	@Override
	public void deleteUser(MesUserVO mesUserVO) throws Exception {
		// TODO Auto-generated method stub
		mesUserDAO.deleteUser(mesUserVO);
	}

	@Override
	public void mesUserPositionInsert(MesUserVO mesUserVO) throws Exception {
		// TODO Auto-generated method stub
		mesUserDAO.mesUserPositionInsert(mesUserVO);
	}

	@Override
	public void mesUserPositionUpdate(MesUserVO mesUserVO) throws Exception {
		// TODO Auto-generated method stub
		mesUserDAO.mesUserPositionUpdate(mesUserVO);
	}

	@Override
	public void mesUserPositionDelete(MesUserVO mesUserVO) throws Exception {
		// TODO Auto-generated method stub
		mesUserDAO.mesUserPositionDelete(mesUserVO);
	}

	public void updateRank(MesUserVO vo)  throws Exception{
		/*
		String oidNum = vo.getkPositionRank();
		String newNum = vo.getSelectRank();
		
		vo.setNum1(-1);
		vo.setkPositionRank(oidNum);
		mesUserDAO.updattRank1(vo);
		
		mesUserDAO.updattRank2(vo);
		
		vo.setNum1(1);
		vo.setkPositionRank(newNum);
		mesUserDAO.updattRank2(vo);
		
		vo.setNum1(Integer.parseInt(newNum));
		vo.setkPositionRank(Integer.toString(-1));
		mesUserDAO.updattRank1(vo);*/
	}

	@Override
	public List selectUseReuestList(MesUserVO mesUserVO) throws Exception {
		// TODO Auto-generated method stub
		return mesUserDAO.selectUseReuestList(mesUserVO);
	}

	@Override
	public int selectUseReuestListCount() throws Exception {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			res = mesUserDAO.selectUseReuestListCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public void mesUserRegDelete(MesUserVO mesUserVO) throws Exception {
		// TODO Auto-generated method stub
		mesUserDAO.mesUserRegDelete(mesUserVO);
	}

	@Override
	public MesUserVO mesUserRegInfo(MesUserVO mesUserVO) throws Exception {
		// TODO Auto-generated method stub
		return mesUserDAO.mesUserRegInfo(mesUserVO);
	}

	@Override
	public void mesUserRegApprovalInsert(MesUserVO mesUserVO) throws Exception {
		// TODO Auto-generated method stub
		MesUserVO userinfo = new MesUserVO();
		 userinfo = mesUserDAO.mesUserRegInfo(mesUserVO);
		mesUserDAO.mesUserRegApprovalInsert(userinfo);
		mesUserDAO.mesUserRegDelete(userinfo);
	}

	@Override
	public int mesCheckId(String id) throws Exception {
		// TODO Auto-generated method stub
		
		int res = 0;
		try {
			res =  mesUserDAO.mesCheckId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public void mesUserInsert(MesUserVO mesUserVO) throws Exception {
		mesUserDAO.mesUserInsert(mesUserVO);
	}

	@Override
	public void mesUserInfoUpdate(MesUserVO mesUserVO) throws Exception {
		// TODO Auto-generated method stub
		mesUserDAO.mesUserInfoUpdate(mesUserVO);
	}

	@Override
	public void deleteUserMenuAuth(MesUserVO mesUserVO) throws Exception {
		// TODO Auto-generated method stub
		mesUserDAO.deleteUserMenuAuth(mesUserVO);
	}

	@Override
	public int mesUserMenuAuthMaxCnt() throws Exception {
		// TODO Auto-generated method stub
		
		int res = 0;
		try {
			res =  mesUserDAO.mesUserMenuAuthMaxCnt();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public void mesUserMenuAuthUpdate(MesUserVO vo) throws Exception {
		// TODO Auto-generated method stub
		
		
		mesUserDAO.mesUserMenuAuthUpdate(vo);
	}

	@Override
	public void insertOfficer(MesUserVO mesUserVO) throws Exception {
		
		//품목 다 볼꺼야!
		if(mesUserVO.geteAllChk().equals("Y")){
			System.out.println(mesUserVO.geteAllChk()+":mesUserVO.geteAllChk()Y");
			mesUserDAO.updateItemGubun(mesUserVO);
			
		// 골라볼꺼야!
		}else{
			mesUserDAO.deleteOfficer(mesUserVO);
			// 품목리스트가 있을 경우
			
			mesUserVO.seteAllChk("N");
			mesUserDAO.updateItemGubun(mesUserVO);
			if(mesUserVO.geteItemKey() != null && !"".equals(mesUserVO.geteItemKey())){
				String[] sItemCateName = EgovStringUtil.split(mesUserVO.getsItemCateName(), ",");
				String[] eItemName = EgovStringUtil.split(mesUserVO.geteItemName(), ",");
				String[] eItemKey = EgovStringUtil.split(mesUserVO.geteItemKey(), ",");
				
				for(int i = 0; i < sItemCateName.length; i++){
					MesUserVO vo = new MesUserVO();
					vo.seteItemKey(eItemKey[i]);
					vo.seteItemName(eItemName[i]);
					vo.setkStaffKey(mesUserVO.getkStaffKey());
					vo.setkStaffName(mesUserVO.getkStaffName());
					mesUserDAO.insertOfficer(vo);
				}
			}
		}
		
		
	}

	@Override
	public List selectOfficerItemList(MesUserVO mesUserVO) throws Exception {
		return mesUserDAO.selectOfficerItemList(mesUserVO);
	}


	/**
	 * 비밀번호 변경 
	 * @param mesUserVO
	 * @throws Exception
	 */
	@Override
	public void updateUserInfoPwd(MesUserVO mesUserVO) throws Exception {
		// TODO Auto-generated method stub
		mesUserDAO.updateUserInfoPwd(mesUserVO);
	}
	@Override
	public void insertMenual(HttpServletRequest request, MesUserVO mesUserVO) throws Exception{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> files = multipartRequest.getFileMap();
		List<FileVO> result = null;
		
		String atchFileId = "";
		
		if(!files.isEmpty()){
			result = fileUtil.parseFileInf(files, "Menual_", 0, "", "menualUploadPath");
			atchFileId = fileService.insertFileInfs(result);
			System.out.println("파일 : " + atchFileId);
			mesUserVO.setAtchFileId(atchFileId);
		}

		mesUserDAO.deleteMenual(mesUserVO);
		mesUserDAO.insertMenual(mesUserVO);
	}

	@Override
	public void updateSetCount(MesUserVO mesUserVO) {
		mesUserDAO.updateSetCount(mesUserVO);
	}

	@Override
	public void updateSetPass(MesUserVO mesUserVO) {
		mesUserDAO.updateSetPass(mesUserVO);
	}

	@Override
	public void mesUserInfoUpdateNew(MesUserVO mesUserVO) throws Exception {
		// TODO Auto-generated method stub
		mesUserDAO.mesUserInfoUpdateNew(mesUserVO);
	}

	@Override
	public void databaseAllTruncate(MesUserVO mesUserVO) {
		// TODO Auto-generated method stub
		// 여기서 자꾸 트랜잭션 오류가 나서 나눠서 확인해봄
		mesUserDAO.databaseAllTruncate1(mesUserVO);
		mesUserDAO.databaseAllTruncate2(mesUserVO);
		mesUserDAO.databaseAllTruncate3(mesUserVO);
		mesUserDAO.databaseAllTruncate4(mesUserVO);
		mesUserDAO.databaseAllTruncate5(mesUserVO);
		mesUserDAO.databaseAllTruncate6(mesUserVO);
		mesUserDAO.databaseAllTruncate7(mesUserVO);
		mesUserDAO.databaseAllTruncate8(mesUserVO);
		mesUserDAO.databaseAllTruncate9(mesUserVO);
		mesUserDAO.databaseAllTruncate10(mesUserVO);
		mesUserDAO.databaseAllTruncate11(mesUserVO);
		mesUserDAO.databaseAllTruncate12(mesUserVO);
		mesUserDAO.databaseAllTruncate13(mesUserVO);
		mesUserDAO.databaseAllTruncate14(mesUserVO);
		mesUserDAO.databaseAllTruncate15(mesUserVO);
		mesUserDAO.databaseAllTruncate16(mesUserVO);
		mesUserDAO.databaseAllTruncate17(mesUserVO);
		mesUserDAO.databaseAllTruncate18(mesUserVO);
		mesUserDAO.databaseAllTruncate19(mesUserVO);
	}

	@Override
	public List selectUserffPopList(MesUserVO mesUserVO) throws Exception {
		// TODO Auto-generated method stub
		return mesUserDAO.selectUserffPopList(mesUserVO);
	}

	@Override
	public int selectUserPopCount(MesUserVO mesUserVO) throws Exception {
		// TODO Auto-generated method stub
		return mesUserDAO.selectUserPopCount(mesUserVO);
	}
}
