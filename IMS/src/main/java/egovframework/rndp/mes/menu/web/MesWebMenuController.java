package egovframework.rndp.mes.menu.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.rndp.admin.intra.staff.service.StaffIntraService;
import egovframework.rndp.admin.intra.staff.service.StaffMenuAuthVO;
import egovframework.rndp.admin.menu.service.MenuBeanVO;
import egovframework.rndp.com.utl.MatrixUtil;
import egovframework.rndp.mes.common.service.MesCommonService;
import egovframework.rndp.mes.login.service.MesK_StaffVo;
import egovframework.rndp.mes.login.service.MesLoginService;
import egovframework.rndp.mes.user.service.MesUserService;
import egovframework.rndp.mes.user.service.MesUserVO;

/**
 * MES 메뉴 
 * 필수 컨트롤러이므로 모듈화 예외
 * @author rndp-21
 *
 */
@Controller
@SessionAttributes({ "mesMenuInfo", "mesTopMenu", "mesLeftMenu", "mesTabMenu", "formatDate", "key", "root" ,"appCnt" , "screenId" , "screenHistory"})
public class MesWebMenuController {
//
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MesWebMenuController.class);


	/*공통사용 서비스 common 및  Egov 공용 */
	@Resource(name = "mesCommonService")
	private MesCommonService mesCommonService;
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;
	@Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;
	@Resource(name = "mesUserService")
	private MesUserService mesUserService;
	/*공통사용 서비스 common 및  Egov 공용 */
	
	@Resource(name = "mUtil")
	private MatrixUtil mUtil;
	
	@Resource(name="staffIntraService")
	private StaffIntraService staffIntraService;
	
	@Resource(name="mesLoginService")
	private MesLoginService mesLoginService;
	

	/**
	 * 인트라넷 전용 웹메뉴 제작기 로그인과 메인을 제외한 모든 요청은 이 컨트롤러를 통해 메뉴을 세팅한 뒤 리다이렉션 된다.
	 * 2020 07 27 MES시스템 에서 모듈화 해 사용하기위해 K_StaffVo 에서 MesK_StaffVo 로 변경함  
	 * @param key
	 * 
	 * */
	@RequestMapping(value = "/mes/webMenu.do")
	public String mesWebMenu(HttpServletRequest request
			, @RequestParam(value="key", required=false) int key
			, @RequestParam(value="root", required=false) int root
			, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			, ModelMap model) throws Exception {
		
		LOGGER.debug("mesWebMenu key = " + key);
		LOGGER.debug("mesWebMenu root = " + root);
			if (key == 0) {
				// 키값이 없다면 메인으로 리다이렉트
				
			}
        
		// 메뉴세팅시 로그인 메뉴권한 설정 
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("staff");
		//메뉴클릭시 권한 문제 및 세션정보 loss 문제로 기존 세션정보를 가져오지않고 다시 쿼리정보를 세팅해줌.
		///mes/webMenu.do는 로그인 검증이 끝났기 때문에 k_staff의  k_staff_key 와 k_menu_auth 의  k_menu_key
		if(staffVo.getkStaffKey() == 0){
			request.getSession().invalidate();
			return "redirect:mes/loginfrm.do";
		}
		
		request.getSession().setAttribute("staff", staffVo);
		//로그인 로그 저장
		staffVo.setkLogGubun("menu");
		staffVo.setkMenuKey(Integer.toString(key));
		staffVo.setkMenuRoot(Integer.toString(root));
		staffVo.setkLogIp(request.getRemoteAddr());
		mesLoginService.mesLogInsert(staffVo);
		
		StaffMenuAuthVO staffMenuAuthVO = new StaffMenuAuthVO();
		staffMenuAuthVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
		staffMenuAuthVO.setkMenuGroupKey(String.valueOf(1));
		staffMenuAuthVO.setkMenuKey(String.valueOf(key));
		//List staffMenuList = staffIntraService.selectStaffMenuList(staffMenuAuthVO);
		List staffMenuList2 = staffIntraService.selectStaffTopMenuList2(staffMenuAuthVO);
		// 결재 대기 건수
		/*
		mesApprovalVO.setkStaffKey(Integer.toString(staffVo.getkStaffKey()));
		int appCnt = mesApprovalService.selectHeaderAppCnt(mesApprovalVO);
		model.addAttribute("appCnt", appCnt);
		 */
		// 키값이 있으므로 원하는 곳이 어딘지 알수 있음
		MenuBeanVO menuBeanVO = mUtil.getMenuInfo(key);
		menuBeanVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
		menuBeanVO.setKey(key);
		
		//해당 메뉴 권한 다시 확인
		MenuBeanVO menuAuth = mesLoginService.selectMesMenuAuth(menuBeanVO);

		menuBeanVO.setkMenuAuthC(menuAuth.getkMenuAuthC());
		menuBeanVO.setkMenuAuthM(menuAuth.getkMenuAuthM());
		menuBeanVO.setkMenuAuthD(menuAuth.getkMenuAuthD());
		menuBeanVO.setkMenuAuthW(menuAuth.getkMenuAuthW());
		menuBeanVO.setkMenuAuthFlag(menuAuth.getkMenuAuthFlag());
				
		model.addAttribute("screenId", menuBeanVO.getScreenId());
		model.addAttribute("screenHistory", menuBeanVO.getScreenHistory());
		model.addAttribute("kStaffId", staffVo.getkStaffId());
		
		menuBeanVO.setKey(key);
		//기본정보 세팅
		model.addAttribute("key", key);
		model.addAttribute("root", root);
		model.addAttribute("groupKey", menuBeanVO.getGroupKey() ); 
		
		int menuDepth = menuBeanVO.getDepth();
		
		//만일 k_menu_page_exist_flag 가 F 라면 같은 k_menu_root 중 가장 낮은 k_menu_step 을 가진 것으로 key 재 세팅 
		if (menuBeanVO.getExist().equals("F")) {
			menuBeanVO = mUtil.getMenuInfoEx(key, staffMenuList2);
			//key = menuBeanVO.getKey();
		}
		
		if(menuBeanVO.getExist().equals("F") && (menuBeanVO.getProgram() == null || "".equals(menuBeanVO.getProgram()))){
			MenuBeanVO tempMenuBeanVO = mUtil.getMenuInfoEx(menuBeanVO.getKey(), staffMenuList2);
			menuBeanVO.setProgram(tempMenuBeanVO.getProgram());
		}
		
		if(menuBeanVO.getDepth() == 0){
			menuBeanVO.setLeftTitle(menuBeanVO.getName());
			menuBeanVO.setLeftMenuKey(menuBeanVO.getKey());
		}
		if(menuBeanVO.getDepth() == 1){
			menuBeanVO.setLeftTitle(mUtil.getMenuInfo(menuBeanVO.getRef()).getName());
			menuBeanVO.setLeftMenuKey(menuBeanVO.getKey());
		}
		if(menuBeanVO.getDepth() == 2){
			menuBeanVO.setLeftTitle(mUtil.getMenuInfo(mUtil.getMenuInfo(menuBeanVO.getRef()).getRef()).getName());
			menuBeanVO.setLeftMenuKey(menuBeanVO.getKey());
		}
		
		// 메뉴별 권한을 staffVo 추가
		staffVo.setkStaffAuthModifyFlag(menuBeanVO.getkMenuAuthM());
		staffVo.setkStaffAuthConfirmFlag(menuBeanVO.getkMenuAuthC());
		staffVo.setkStaffAuthDelFlag(menuBeanVO.getkMenuAuthD());
		staffVo.setkStaffAuthWriteFlag(menuBeanVO.getkMenuAuthW());
 
		//인트라넷 상단 메뉴 title 설정 
		String mc = mUtil.getMenuCategory(menuBeanVO.getKey());
		LOGGER.debug("mc = " + mc);
		menuBeanVO.setMenuCategory(mc);
		request.getSession().setAttribute("mesMenuInfo", menuBeanVO);
		model.addAttribute("mesMenuInfo", menuBeanVO);
		LOGGER.debug("mesMenuInfo.getMenuCategory = " + menuBeanVO.getKey());

		// 탑메뉴 검증
		ArrayList<MenuBeanVO> mesTopMenu = new ArrayList<MenuBeanVO>();
		mesTopMenu = (ArrayList<MenuBeanVO>) request.getSession().getAttribute("mesTopMenu");
		model.addAttribute("mesTopMenu", mesTopMenu);
		LOGGER.debug("mesTopMenu size = " + mesTopMenu.size());
 
		
		// 선택한 메뉴의 ref 를 가져온다.
		int ref = menuBeanVO.getRef();
		int depth = menuBeanVO.getDepth();
		
		LOGGER.debug("ref = " + ref);
		
		/* menuBeanVO.getGroupKey() 는 무조건 2 */
		/* menuBeanVO.getStep() 이 출력순서 */
		/* 원하는 곳의 타일즈 주소를 어떻게 구하지? */
		LOGGER.debug("menuBeanVO getProgram = " + menuBeanVO.getProgram());
		LOGGER.debug("menuBeanVO getRef = " + menuBeanVO.getRef());
		LOGGER.debug("menuBeanVO getStep = " + menuBeanVO.getStep());
		
		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		String formatDate = dateFormat.format(nowDate); 
		model.addAttribute("formatDate", formatDate);
		
		
		// Program src 가 있다면 리다이렉트
		if (menuBeanVO != null && menuBeanVO.getProgram() != null && !menuBeanVO.getProgram().trim().isEmpty()) {
			String nextUrl = "forward:" + menuBeanVO.getProgram().trim();
			return nextUrl;
		}
		 
		return "redirect:/mes/main.do";
	}

}
