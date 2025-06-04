package egovframework.rndp.mes.user.web;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.utl.sim.service.EgovNetworkState;
import egovframework.rndp.admin.intra.staff.service.StaffMenuAuthVO;
import egovframework.rndp.com.utl.JsonHelper;
import egovframework.rndp.mes.common.service.MesCommonService;
import egovframework.rndp.mes.login.service.MesK_StaffVo;
import egovframework.rndp.mes.user.service.MesUserService;
import egovframework.rndp.mes.user.service.MesUserVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * MES 사용자 
 * @author rndp-21
 *
 */
@Controller
public class MesUserController {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MesUserController.class);

	/** EgovSampleService */
	@Resource(name = "mesUserService")
	private MesUserService mesUserService;

	/*공통사용 서비스 common 및  Egov 공용 */
	@Resource(name = "mesCommonService")
	private MesCommonService mesCommonService;
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;
	@Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;
	/*공통사용 서비스 common 및  Egov 공용 */
	 

	/**
	 * MES 사용자 계정관리 
	 * @param request
	 * @param mesUserVO
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mes/user/kw_user_lf.do")
	public String mesuUserLf(HttpServletRequest request
			, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		

		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo); 

		/*페이징*/
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesUserVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesUserVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesUserVO.getPageSize());

		mesUserVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesUserVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesUserVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		// rndp이외의 계정으로 로그인할 경우 사용자 목록에 rndp가 안 보이도록
		if("rndp".equals(staffVo.getkStaffId())){
			mesUserVO.setMesUserMaster("rndp");
		}
		if("innost".equals(staffVo.getkStaffId())){
			mesUserVO.setMesUserMaster("innost");
		}
		
		List mesUserList = mesUserService.selectUserffList(mesUserVO);
		int totCnt = mesUserService.selectUserCount(mesUserVO);
		
		model.addAttribute("mesUserList", mesUserList);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "mes/user/kw_user_lf.tiles";
	} 
	                          
	@RequestMapping(value = "/mes/user/kw_user_if.do")
	public String mesUserItemIf(HttpServletRequest request
			, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			, ModelMap model) throws Exception {
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo);
		
		return "mes/user/kw_user_if.tiles";
	}
	
	@RequestMapping(value = "/mes/user/kw_user_uf.do")
	public String mesUserItemUf(HttpServletRequest request
			, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			, ModelMap model) throws Exception {
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo);
		  

		List sectorList = mesUserService.selectSectorList();
		model.addAttribute("sectorList", sectorList);
		
		List classList = mesUserService.selectClassList();
		model.addAttribute("classList", classList);
		
		List positionList = mesCommonService.selectPositionList();
		model.addAttribute("positionList", positionList);
		
		MesUserVO mesUserInfo = mesUserService.selectUserInfo(mesUserVO);
		model.addAttribute("mesUserInfo", mesUserInfo);
		
		
		return "mes/user/kw_user_uf.tiles";
	}
	@RequestMapping(value = "/mes/kw_user_u.do")
	public String mesUserInfiUpdate(HttpServletRequest request
			, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		
		mesUserService.mesUserInfoUpdate(mesUserVO);
//		mesUserService.mesUserInfoUpdateNew(mesUserVO);
		return "redirect:/mes/user/kw_user_lf.do";
	}
	@RequestMapping(value = "/mes/kw_user_d.do")
	public String mesUserDelete(HttpServletRequest request
			, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		
		mesUserService.deleteUser(mesUserVO);
		return "redirect:/mes/user/kw_user_lf.do";
	}
	
	//메뉴설정
	@RequestMapping(value = "/mes/user/kw_userMenu_vf.do")
	public String mesUserMenuInfo(HttpServletRequest request
			, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			, ModelMap model) throws Exception {
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo);
		mesUserVO.setkStaffKey(mesUserVO.getMesUserKey());
		List mesUserMenuList = mesUserService.mesUserMenuList(mesUserVO);
		model.addAttribute("mesUserMenuList", mesUserMenuList);	
		return "mes/user/kw_userMenu_vf.tiles";
	}
	
	@RequestMapping(value = "/mes/user/kw_userMenu_user1_vf.do")
	public String mesUserMenuInfouser1(HttpServletRequest request
			, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			, ModelMap model) throws Exception {
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo);
		mesUserVO.setMesUserKey(String.valueOf(staffVo.getkStaffKey()));
		mesUserVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
		List mesUserMenuList = mesUserService.mesUserMenuList(mesUserVO);
		model.addAttribute("mesUserMenuList", mesUserMenuList);	
		return "mes/user/kw_userMenu_vf.tiles";
	}
	
	@RequestMapping(value = "/mes/kw_userMenu_u.do")
	public String mesUserMenuInfoUpdate(HttpServletRequest request
			, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			, ModelMap model)throws Exception {
		
		mesUserService.deleteUserMenuAuth(mesUserVO);
		
		for(int i=0; i<mesUserVO.getkMenuKey().split(",").length; i++){
			
			int max = mesUserService.mesUserMenuAuthMaxCnt();
			MesUserVO vo = new MesUserVO(); 
			StaffMenuAuthVO authVO = new StaffMenuAuthVO();
			
			vo.setkClassKey(mesUserVO.getkClassKey());
			
			vo.setkMenuAuthFlag(request.getParameter("flag"+mesUserVO.getkMenuKey().split(",")[i]));
			vo.setkMenuAuthC(request.getParameter("c"+mesUserVO.getkMenuKey().split(",")[i]));			
			vo.setkMenuAuthM(request.getParameter("m"+mesUserVO.getkMenuKey().split(",")[i]));			
			vo.setkMenuAuthD(request.getParameter("d"+mesUserVO.getkMenuKey().split(",")[i]));			
			vo.setkMenuAuthW(request.getParameter("w"+mesUserVO.getkMenuKey().split(",")[i]));			
			vo.setkMenuKey(mesUserVO.getkMenuKey().split(",")[i]);
			vo.setMesUserKey(mesUserVO.getMesUserKey());
			vo.setkMenuAuthKey(String.valueOf(max));

			mesUserService.mesUserMenuAuthUpdate(vo);
		}
		
		return "redirect:/mes/user/kw_user_lf.do";
	}	
	//가입신청 현황
	@RequestMapping(value = "/mes/user/kw_user_req_lf.do")
	public String mesUserRegList(HttpServletRequest request
			, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {


		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo);
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesUserVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesUserVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesUserVO.getPageSize());

		mesUserVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesUserVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesUserVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		
		List mesUserList = mesUserService.selectUseReuestList(mesUserVO);
		int totCnt = mesUserService.selectUseReuestListCount();
		
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("mesUserList", mesUserList);
		model.addAttribute("paginationInfo", paginationInfo);		
		
		return "mes/user/kw_user_req_lf.tiles";
	}
	@RequestMapping(value = "/mes/user/kw_user_req_d.do")
	public String mesUserRegDelete(HttpServletRequest request
			, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		
		mesUserService.mesUserRegDelete(mesUserVO);
		
		return "redirect:/mes/user/kw_user_req_lf.do";
	}
	@RequestMapping(value = "/mes/user/kw_user_req_i.do")
	public String mesUserRegApprovalInsert(HttpServletRequest request
			, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		//	mesUserService.mesUserRegDelete(mesUserVO);
		
		mesUserService.mesUserRegApprovalInsert(mesUserVO);
		return "redirect:/mes/user/kw_user_req_lf.do";
	}
	@RequestMapping(value = "/mes/user/kw_user_req_uf.do")
	public String mesUserRegUpdateForm(HttpServletRequest request
			, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		MesUserVO mesUserInfo = mesUserService.mesUserRegInfo(mesUserVO);
		//mesUserService.mesUserRegDelete(mesUserVO);
		model.addAttribute("mesUserInfo", mesUserInfo);
		return "mes/user/kw_user_req_uf.tiles";
	}
	
	
	//부서관리
	@RequestMapping(value = "/mes/user/kw_position_lf.do")
	public String mesUserGroupLf(HttpServletRequest request
			, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");	
		model.addAttribute("staffVo", staffVo);
		
		List positionList = mesCommonService.selectPositionList();
		model.addAttribute("positionList", positionList);
		
		return "mes/user/km_position_lf.tiles";
	}
	
	@RequestMapping(value = "/mes/user/kw_position_if.do")
	public String mesUserGroupIf(HttpServletRequest request
			, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		List positionList = mesCommonService.selectPositionList();
		model.addAttribute("positionList", positionList);
		int newRank = mesUserService.selectMaxRank();
		model.addAttribute("newRank", newRank);
		return "mes/user/km_position_if.tiles";
	}
	@RequestMapping(value = "/mes/user/kw_position_vf.do")
	public String mesUserGroupVf(HttpServletRequest request
			, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		mesUserVO = mesUserService.selectpositionInfo(mesUserVO);
		model.addAttribute("mesUserVO", mesUserVO);
		return "mes/user/km_position_uf.tiles";
	}
	@RequestMapping(value = "/mes/user/kw_position_uf.do")
	public String mesUserGroupUf(HttpServletRequest request
			, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		/*List positionList = mesUserService.selectPositionList();
		model.addAttribute("positionList", positionList);
		int newRank = mesUserService.selectMaxRank();
		model.addAttribute("newRank", newRank);*/
		return "mes/user/km_position_uf.tiles";
	}
	@RequestMapping(value = "/mes/user/kw_position_i.do")
	public String mesUserPositionInsert(HttpServletRequest request
			, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
			
			mesUserService.mesUserPositionInsert(mesUserVO);
				
			return "redirect:/mes/user/kw_position_lf.do";
	}
	@RequestMapping(value = "/mes/user/kw_position_u.do")
	public String mesUserPositionUpdate(HttpServletRequest request
			, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		
		mesUserService.mesUserPositionUpdate(mesUserVO);
		
		return "redirect:/mes/user/kw_position_lf.do";
	}
	@RequestMapping(value = "/mes/user/kw_position_d.do")
	public String mesUserPositionDelete(HttpServletRequest request
			, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		
		mesUserService.mesUserPositionDelete(mesUserVO);
		
		return "redirect:/mes/user/kw_position_lf.do";
	}
	//직급관리
	@RequestMapping(value = "/mes/user/kw_userLevel_lf.do")
	public String mesUserLevelLf(HttpServletRequest request
			, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo);
		List iclassrList = mesUserService.selectIclassList(mesUserVO);
		model.addAttribute("levelList", iclassrList);
		return "mes/user/kw_userLevel_lf.tiles";
	}
	@RequestMapping(value = "/mes/user/kw_userLevel_if.do")
	public String mesUserLevelIf(HttpServletRequest request
			, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		List iclassrList = mesUserService.selectIclassList(mesUserVO);
		model.addAttribute("levelList", iclassrList);
		return "mes/user/kw_userLevel_if.tiles";
	}

	@RequestMapping(value = "/mes/user/kw_userLevel_i.do")
	public String mesUserLevelInsert(HttpServletRequest request
			, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		mesUserService.mesUserLevelInsert(mesUserVO);
		return "redirect:/mes/user/kw_userLevel_lf.do";
	}
	@RequestMapping(value = "/mes/user/kw_userLevel_uf.do")
	public String mesUserLevelUf(HttpServletRequest request
			, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		MesUserVO classInfo =  mesUserService.selectIclassInfo(mesUserVO);
		model.addAttribute("classInfo", classInfo);
		return "mes/user/kw_userLevel_uf.tiles";
	}
	@RequestMapping(value = "/mes/user/kw_userLevel_u.do")
	public String mesUserLevelUpdate(HttpServletRequest request
			, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		mesUserService.mesUserLevelUpdate(mesUserVO);
		return "redirect:/mes/user/kw_userLevel_lf.do";
	}
	
	
	@RequestMapping(value = "/mes/user/kw_userLevel_d.do")
	public String mesUserLevelDelete(HttpServletRequest request
			, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		mesUserService.mesUserLevelDelete(mesUserVO);
		return "redirect:/mes/user/kw_userLevel_lf.do";
	}
	
	
	
	@RequestMapping(value = "/mes/user/env_List.do")
	public String mesgiuplLf(HttpServletRequest request
			, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		System.out.println("*******************************************************************");
		System.out.println(staffVo.getkStaffAuthWriteFlag());
		model.addAttribute("staffVo", staffVo);
		List envList = mesUserService.envList();
		
		for(int i=0; i<envList.size(); i++){
			MesUserVO eVO = (MesUserVO)envList.get(i);	
			if("companyName".equals(eVO.getEnvName() )){				// 회사명
				String companyName = eVO.getEnvVal();
				model.addAttribute("companyName", companyName);
			}else if("companyCeo".equals(eVO.getEnvName())){			// 대표자
				String companyCeo = eVO.getEnvVal();
				model.addAttribute("companyCeo", companyCeo);
			}else if("companyIncorpo".equals(eVO.getEnvName())){		// 설립일
				String companyIncorpo = eVO.getEnvVal();
				model.addAttribute("companyIncorpo", companyIncorpo);
			}else if("companyAddr".equals(eVO.getEnvName())){		 	// 주소
				String companyAddr = eVO.getEnvVal();
				model.addAttribute("companyAddr", companyAddr);
			}else if("companyTel".equals(eVO.getEnvName())){		 	// 대표전화
				String companyTel = eVO.getEnvVal();
				model.addAttribute("companyTel", companyTel);
			}else if("companyFax".equals(eVO.getEnvName())){		 	// 팩스
				String companyFax = eVO.getEnvVal();
				model.addAttribute("companyFax", companyFax);
			}else if("companyPost".equals(eVO.getEnvName())){		 	// 우편번호
				String companyPost = eVO.getEnvVal();
				model.addAttribute("companyPost", companyPost);
			}else if("companyNum".equals(eVO.getEnvName())){		 	// 사업자번호
				String companyNum = eVO.getEnvVal();
				model.addAttribute("companyNum", companyNum);
			}else if("sComType".equals(eVO.getEnvName())){		 	// 사업자번호
				String sComType = eVO.getEnvVal();
				model.addAttribute("sComType", sComType);
			}else if("sComCategory".equals(eVO.getEnvName())){		 	// 사업자번호
				String sComCategory = eVO.getEnvVal();
				model.addAttribute("sComCategory", sComCategory);
			}else if("sDomain".equals(eVO.getEnvName())){		 	// 시스템사용도메인
				String sComCategory = eVO.getEnvVal();
				model.addAttribute("sDomain", sComCategory);
			}else if("sPublicIp".equals(eVO.getEnvName())){		 	// 시스템사용IP 공인
				String sComCategory = eVO.getEnvVal();
				model.addAttribute("sPublicIp", sComCategory);
			}else if("sPrivateIp".equals(eVO.getEnvName())){		 	// 시스템사용IP 사설
				String sComCategory = eVO.getEnvVal();
				model.addAttribute("sPrivateIp", sComCategory);
			}
		}
		EgovNetworkState newStatus = new EgovNetworkState();
		String eIPaddress = newStatus.getMyIPaddress();
		model.addAttribute("eIPaddress", eIPaddress);
		model.addAttribute("envList", envList);
		
		return "mes/user/env_List.tiles";
	}
	@RequestMapping(value = "/mes/user/env_List_i.do")
	public String mesgiuplIf(HttpServletRequest request
			, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		
		if(mesUserVO.getEnvVal().split(",").length != mesUserVO.getEnvName().split(",").length) {
			mesUserVO.setEnvVal(mesUserVO.getEnvVal() + " ");
		}
		for(int i=0; i<mesUserVO.getEnvName().split(",").length; i++){
			MesUserVO vo = new MesUserVO();
			vo.setEnvName(mesUserVO.getEnvName().split(",")[i]);
			vo.setEnvVal(mesUserVO.getEnvVal().split(",")[i]);
			vo.setEnvVal(vo.getEnvVal().replaceAll(("@@"),","));

			mesUserService.updateEnv(vo);
		}
		
		return "redirect:/mes/user/env_List.do";
	}
	//직원등록 신청 팝업 intra에서 분리
	@RequestMapping(value="/popup/km_user_req_if.do")
	public String registrationPopup(ModelMap model
			, HttpServletRequest request) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo);

		List sectorList = mesUserService.selectSectorList();
		model.addAttribute("sectorList", sectorList);
		
		List classList = mesUserService.selectClassList();
		model.addAttribute("classList", classList);
		
		List positionList = mesCommonService.selectPositionList();
		model.addAttribute("positionList", positionList);
		
		return "mesPopup/mes/user/popup/km_user_req_if";
	}
	  @RequestMapping(method=RequestMethod.POST, value="/popup/kw_mesCheckId.do")
	  public void mesCheckId(HttpServletResponse response
	        , HttpServletRequest request
	        , @ModelAttribute("mesUserVO") MesUserVO mesUserVO) throws Exception{

		 int checkIdCnt = mesUserService.mesCheckId(mesUserVO.getMesUserId());

	     String resultFlag = "T";
	     
	     /*중복된 아이디가 있을때*/
	     if(checkIdCnt > 0){
	    	 resultFlag = "F";
	     }
	     
	     JsonHelper helper = new JsonHelper();
	     Map<String,Object> map = new HashMap<String,Object>();
	     map.put("resultFlag", resultFlag);
	     helper.printJsonObject(response, "result", map);
	  }
	  @RequestMapping(value="/popup/checmesUserId.do")
	  public String checkStaffInsert(HttpServletRequest request
			  , @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			  , ModelMap model) throws Exception{

			//256 암호화 적용
			String pass = mesUserVO.getMesUserPassword1();
			pass = pwEncryptionSha256(pass);
				
			// 값을 받아옴 컬럽 이외의 컬럽들 값 셋팅
			mesUserVO.setMesUserPassword(pass);
			mesUserVO.setMesUserJumin1("000000");
			mesUserVO.setMesUserJumin2("0000000");
			mesUserVO.setkMailUse("Y");
			mesUserVO.setkSectorKey("1");
			
			mesUserService.mesUserInsert(mesUserVO);
			model.addAttribute("closeValue", "1");
	  
			return "mesPopup/mes/user/popup/km_user_req_if";
	  }
	  
		public static String mesUserPWEncryption(String pwstr){
			
			MessageDigest md;
			String hashText = "";
			
			try {
				md = MessageDigest.getInstance("MD5");
				byte[] pwByte = md.digest(pwstr.getBytes());
				BigInteger num = new BigInteger(1, pwByte);
				hashText = num.toString(16);
				while(hashText.length() < 32){
					hashText = "0" + hashText;
				}
				
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return hashText;
		}
	  
		@RequestMapping(value = "/mes/user/kw_officer_if.do")
		public String kwOfficerIf(HttpServletRequest request
				, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
				, ModelMap model) throws Exception {
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			model.addAttribute("staffVo", staffVo);
			
			MesUserVO mesUserInfo = mesUserService.selectUserInfo(mesUserVO);
			model.addAttribute("mesUserInfo", mesUserInfo);
			
			mesUserVO.setkStaffKey(mesUserInfo.getMesUserKey());
			List officerItem = mesUserService.selectOfficerItemList(mesUserVO);
			model.addAttribute("officerItem", officerItem);
			
			return "mes/user/kw_officer_if.tiles";
		}
		
		@RequestMapping(value = "/mes/user/kw_officer_i.do")
		public String kwOfficerI(HttpServletRequest request
				, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
				, ModelMap model) throws Exception {
			
			mesUserService.insertOfficer(mesUserVO);
		
			return "redirect:/mes/user/kw_user_lf.do";
		}
	  
		public static String pwEncryptionSha256(String pwstr){
			String hashText = "";
			
			 try{
				 
		            MessageDigest digest = MessageDigest.getInstance("SHA-256");
		            byte[] hash = digest.digest(pwstr.getBytes("UTF-8"));
		            StringBuffer hexString = new StringBuffer();
		 
		            for (int i = 0; i < hash.length; i++) {
		                String hex = Integer.toHexString(0xff & hash[i]);
		                if(hex.length() == 1) hexString.append('0');
		                hexString.append(hex);
		            }
		 
		            //출력
		            hashText = hexString.toString();
		 
		        } catch(Exception ex){
		            throw new RuntimeException(ex);
		        }
			
			return hashText;
		}

		/**
		 * 비밀번호 변경
		 *  
		 *  mypage 비밀번호 번경에서 사용
		 *  시스템관리의 사용자설정에서 비빌번호 변경하기 사용
		 *  
		 * @param request
		 * @param mesUserVO
		 * @param model
		 * @param response
		 * @throws Exception
		 */
		@RequestMapping(value = "/mes/user/userInfoPwd_u.do")
		public void mesMyPageuserInfoPwdU(HttpServletRequest request, 
				 @ModelAttribute("mesUserVO") MesUserVO mesUserVO
				, ModelMap model , HttpServletResponse response) throws Exception{ 
			
						
			String message = "";
			String idx = "";
			
			MesUserVO vo = mesUserService.selectUserInfo(mesUserVO);
			
			if(mesUserVO.getMesMemberPassword().equals((vo.getMesUserPassword()))){
				
				mesUserService.updateUserInfoPwd(mesUserVO);
				
				idx = "1";
				message = "비밀번호가 변경되었습니다.";
				
			}else{
				
				idx = "0";
				message = "현재 비밀번호가 일치하지 않습니다.";
				
			}
			
			JsonHelper helper = new JsonHelper();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("message", message);
			map.put("idx", message);
			helper.printJsonObject(response, "result", map);
			
		}
		   
		/**
		 * 비밀번호 초기화
		 * 
		 * @param request
		 * @param mesUserVO
		 * @param model
		 * @param response
		 * @throws Exception
		 */
		@RequestMapping(value = "/mes/user/userInfoNewPwd_u.do")
		public void mesMyPageuserInfoNewPwdU(HttpServletRequest request, 
				 @ModelAttribute("mesUserVO") MesUserVO mesUserVO
				, ModelMap model , HttpServletResponse response) throws Exception{ 
			
			
			JsonHelper helper = new JsonHelper();
			Map<String,Object> map = new HashMap<String,Object>();
			String newPw ="";
			Random rnd =new Random();

			StringBuffer buf =new StringBuffer();

			for(int i=0;i<4;i++){
				
			    if(rnd.nextBoolean()){
			        buf.append((char)((int)(rnd.nextInt(26))+97));
			    }else{
			        buf.append((rnd.nextInt(10)));
			    }

			}
			newPw = buf.toString();
			mesUserVO.setChPassword2(pwEncryptionSha256(newPw));
			mesUserService.updateUserInfoPwd(mesUserVO);
			//message = "비밀번호를 변경하셨습니다.";
			//map.put("idx", 1);
			//map.put("message", message);
			map.put("newPw", newPw);
			helper.printJsonObject(response, "result", map);
			
		}
		
		
		@RequestMapping(value = "/mes/user/setCount.do")
		public void mesSetCountAjax(HttpServletRequest request
				, HttpServletResponse response
				, @ModelAttribute("mesUerVo") MesUserVO mesUserVO) throws Exception{

			mesUserService.updateSetCount(mesUserVO);
			
			JsonHelper helper = new JsonHelper();
			Map<String,Object> map = new HashMap<String,Object>();
			helper.printJsonObject(response, "result", map);
		}
		
		@RequestMapping(value = "/mes/user/setPass.do")
		public void mesSetPassAjax(HttpServletRequest request
				, HttpServletResponse response
				, @ModelAttribute("mesUerVo") MesUserVO mesUserVO) throws Exception{
			mesUserVO.setMesUserPassword(pwEncryptionSha256(mesUserVO.getMesUserPassword()));
			mesUserService.updateSetPass(mesUserVO);
			
			JsonHelper helper = new JsonHelper();
			Map<String,Object> map = new HashMap<String,Object>();
			helper.printJsonObject(response, "result", map);
		}
		
		@RequestMapping(value = "/mes/user/databaseAllTruncate.do")
		public void databaseAllTruncate(HttpServletRequest request, 
				 @ModelAttribute("mesUserVO") MesUserVO mesUserVO
				, ModelMap model , HttpServletResponse response) throws Exception{ 
			
			
			JsonHelper helper = new JsonHelper();
			Map<String,Object> map = new HashMap<String,Object>();
			String eText ="완료";

			mesUserService.databaseAllTruncate(mesUserVO);
			 
			 
			map.put("eText", eText);
			helper.printJsonObject(response, "result", map);
			
		}
		
		@RequestMapping(value="/mes/user/kw_user_list.do")
		public String userListPop(ModelMap model
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
				, HttpServletRequest request) throws Exception{
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			model.addAttribute("staffVo", staffVo);
			
			mesUserVO.setMesUserMaster(staffVo.getkStaffId());
		
			/* paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(mesUserVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(mesUserVO.getRecordCountPerPage());
			paginationInfo.setPageSize(mesUserVO.getPageSize());

			mesUserVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			mesUserVO.setLastIndex(paginationInfo.getLastRecordIndex());
			mesUserVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			 
			List infoList = mesUserService.selectUserffPopList(mesUserVO);
			int totCnt = mesUserService.selectUserPopCount(mesUserVO);
			model.addAttribute("infoList", infoList);
			paginationInfo.setTotalRecordCount(totCnt);
			
			model.addAttribute("paginationInfo", paginationInfo);
			

			redirectAttributes.addFlashAttribute("mesUserVO", mesUserVO);
			return "mesPopup/mes/user/popup/kw_info_list";
		}
		
		
}




