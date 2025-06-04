package egovframework.rndp.admin.admin.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rndp.admin.admin.service.AdminAuthService;
import egovframework.rndp.admin.admin.service.AdminAuthVO;
import egovframework.rndp.admin.admin.service.AdminService;
import egovframework.rndp.admin.admin.service.AdminVO;
import egovframework.rndp.admin.login.web.LoginController;
import egovframework.rndp.admin.position.service.PositionService;
import egovframework.rndp.admin.position.service.StaffService;
import egovframework.rndp.admin.position.service.StaffVO;
import egovframework.rndp.com.utl.EgovStringUtil;

@Controller
public class AdminController {
	
	@Autowired
	private DefaultBeanValidator beanValidator;

	@Resource(name = "adminService")
	private AdminService adminService;
	
	@Resource(name = "positionService")
	private PositionService positionService;
	
	@Resource(name = "staffService")
	private StaffService staffService;
	
	@Resource(name="adminAuthService")
	private AdminAuthService adminAuthService;
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(LoginController.class);
	
	/**
	 * 관리자 계정 관리
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/adminList.do")
	public String adminList(HttpServletRequest request
			, ModelMap model) throws Exception{
		LOGGER.debug("adminList start");
		List result = adminService.adminList();
		model.addAttribute("result", result);
		
		return "admin/admin/list";
	}
	
	@RequestMapping(value="/admin/adminInsertfrm.do")
	public String adminInsertfrm(HttpServletRequest request
			, ModelMap model) throws Exception{

		return "admin/admin/insert";
	}
	
	
	@RequestMapping(value="/admin/adminInsert.do")
	public String adminInsert(HttpServletRequest request
			, @ModelAttribute("adminVO") AdminVO adminVO
			, BindingResult bindingResult
			, ModelMap model) throws Exception{
		beanValidator.validate(adminVO, bindingResult);
		if (bindingResult.hasErrors()){
			return "admin/admin/insert";
		}
		adminVO.setPassword(EgovStringUtil.pwEncryptionSha256(adminVO.getPassword()));
		adminService.adminInsert(adminVO);
		return "redirect:/admin/adminList.do";
	}
	
	/**
	 * 
	 * @param request
	 * @param staffVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/adminStaff.do")
	public String adminStaff(HttpServletRequest request
			, String positionKey
			, String staffKey
			, String num
			, String gubun
			, ModelMap model) throws Exception{
		List positionList = positionService.positionList();
		if(positionKey == null || "".equals(positionKey)){
			positionKey = "0";
		}
		List staffList = staffService.staffListByPos(Integer.parseInt(positionKey));

		StaffVO vo = new StaffVO();
		if(staffKey == null || "".equals(staffKey)){
			staffKey = "0";
		}
		if(!"0".equals(Integer.parseInt(staffKey))){
			vo = staffService.staffInfoForView(Integer.parseInt(staffKey));
		}
		
		model.addAttribute("positionList", positionList);
		model.addAttribute("staffList", staffList);
		model.addAttribute("vo", vo);
		 model.addAttribute("positionKey", positionKey);
		model.addAttribute("staffKey", staffKey);
		model.addAttribute("num", num);
		model.addAttribute("gubun", gubun);

		return "admin/admin/staff";
	}

	
	/**
	 * 비밀번호 변경 폼
	 * @param request
	 * @param adminVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/adminPwdChangefrm.do")
	public String adminPwdChangeForm(HttpServletRequest request
			, int key
			, ModelMap model) throws Exception{
		AdminVO result = adminService.adminInfo(key);
		result.setKey(key);
		model.addAttribute("result", result);
		
		return "admin/admin/pwdUpdate";
	}
	
	@RequestMapping(value="/admin/adminPwdChange.do")
	public String adminPwdChange(HttpServletRequest request
			, @ModelAttribute("adminVO") AdminVO adminVO
			, ModelMap model) throws Exception{
		
		AdminVO result = adminService.adminInfo(adminVO.getKey());
		
		if(!adminVO.getNppassword().equals(adminVO.getNewpassword())){
			return "redirect:/admin/adminPwdChangefrm.do?key="+adminVO.getKey();
		}else if(!EgovStringUtil.pwEncryption(adminVO.getPassword()).equals(result.getPassword())){
			return "redirect:/admin/adminPwdChangefrm.do?key="+adminVO.getKey();
		}
		adminVO.setNewpassword(EgovStringUtil.pwEncryption(adminVO.getNewpassword()));
		adminVO.setPassword(EgovStringUtil.pwEncryption(adminVO.getPassword()));
		adminService.adminUpdatePass(adminVO);
		return "redirect:/admin/adminList.do";
	}
	
	/**
	 * 관리자 수정 폼
	 * 
	 * @param request
	 * @param adminVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/adminUpdatefrm.do")
	public String adminUpdateForm(HttpServletRequest request,
			@RequestParam(value = "key", required = false) int key,
			ModelMap model) throws Exception {
		AdminVO result = adminService.adminInfo(key);
		result.setKey(key);
		model.addAttribute("result", result);

		return "admin/admin/update";
	}

	@RequestMapping(value="/admin/adminUpdate.do")
	public String adminUpdate(HttpServletRequest request
			, @ModelAttribute("adminVO") AdminVO adminVO
			, BindingResult bindingResult
			, ModelMap model) throws Exception{
			
		beanValidator.validate(adminVO, bindingResult);
		if (bindingResult.hasErrors()){
			AdminVO result = adminService.adminInfo(adminVO.getKey());
			result.setKey(adminVO.getKey());
			model.addAttribute("result", result);
			return "admin/admin/update";
		}

		adminService.adminUpdate(adminVO);
		
		return "redirect:/admin/adminList.do";
//		return "redirect:/admin/adminUpdatefrm.do?key="+adminVO.getKey();
	}
	
	@RequestMapping(value="/admin/adminDelete.do")
	public String adminDelete(int key) throws Exception{
		adminService.adminDelete(key);
		return "redirect:/admin/adminList.do";
	}
	
	/**
	 * 계정별권한관리
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/adminAuthList.do")
	public String adminAuthList(HttpServletRequest request
			, String key
			, ModelMap model) throws Exception{
		
		AdminVO vo = (AdminVO) request.getSession().getAttribute("adminUser");
		List adminList = adminService.adminList();
		AdminAuthVO authVO = new AdminAuthVO();
		if(key == null || "".equals(key)){
			authVO.setKey(vo.getKey());
		}else{
			authVO.setKey(Integer.parseInt(key));
		}
		List adminMenuList = adminAuthService.adminMenuAuthList(authVO.getKey());
		
		model.addAttribute("admin", adminList);
		model.addAttribute("menu", adminMenuList);
		model.addAttribute("key", authVO.getKey());
		return "admin/auth/list";
	}
	
	/**
	 * 계정별 권한 관리 수정
	 * @param request
	 * @param key
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/adminAuthUpdatefrm.do")
	public String adminAuthUpdatefrm(HttpServletRequest request
			, String key
			, ModelMap model) throws Exception{
		
		AdminVO vo = (AdminVO) request.getSession().getAttribute("adminUser");
		List adminList = adminService.adminList();
		AdminAuthVO authVO = new AdminAuthVO();
		if(null == key || "".equals(key)){
			authVO.setKey(vo.getKey());
		}else{
			authVO.setKey(Integer.parseInt(key));
		}
		List adminMenuList = adminAuthService.adminMenuAuthList(authVO.getKey());
		model.addAttribute("admin", adminList);
		model.addAttribute("menu", adminMenuList);
		model.addAttribute("key", authVO.getKey());
		return "admin/auth/update";
	}
	
	@RequestMapping(value="/admin/adminAuthUpdate.do")
	public String adminAuthUpdate(HttpServletRequest req
			, @ModelAttribute("adminAuthVO") AdminAuthVO adminAuthVO) throws Exception{
		adminAuthService.adminAuthDelete(adminAuthVO.getAdminKey());
		for(int i=0; i<adminAuthVO.getStrMenuKey().split(",").length; i++){
			
			int max = adminAuthService.adminAuthMaxCount();
			
			AdminAuthVO authVO = new AdminAuthVO();
			authVO.setFlag(req.getParameter("flag"+adminAuthVO.getStrMenuKey().split(",")[i]));
			authVO.setMenuKey(Integer.parseInt(adminAuthVO.getStrMenuKey().split(",")[i]));
			authVO.setAdminKey(adminAuthVO.getAdminKey());
			authVO.setKey(max);

			adminAuthService.adminAuthUpdate(authVO);
		}
		return "redirect:/admin/adminAuthList.do?key="+adminAuthVO.getAdminKey();
	}
	
	
	
}
