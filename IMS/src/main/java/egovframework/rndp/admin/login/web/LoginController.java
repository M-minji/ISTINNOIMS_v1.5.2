package egovframework.rndp.admin.login.web;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rndp.admin.admin.service.AdminService;
import egovframework.rndp.admin.admin.service.AdminVO;
import egovframework.rndp.admin.env.service.EnvService;
import egovframework.rndp.admin.env.service.EnvVO;
import egovframework.rndp.admin.menu.service.AdminmenuService;
import egovframework.rndp.admin.menu.service.AdminmenuVO;
import egovframework.rndp.com.utl.EgovStringUtil;
import egovframework.rndp.com.utl.MatrixUtil;
import egovframework.rndp.login.service.UserInfoVO;

@Controller
@SessionAttributes({"adminUser","adminGroupKey","adminGroupList","adminMenuTopList"}) 
public class LoginController {
    
    @Resource(name = "adminService")
    private AdminService adminService;
    
	@Resource(name = "adminmenuService")
    private AdminmenuService adminmenuService;
	
	@Resource(name = "envService")
	private EnvService envService;
	
	@Resource(name = "mUtil")
	private MatrixUtil mUtil;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(LoginController.class);
    
    /**
     * 관리자 로그인 화면 호출
     * @param model
     * @return
     * @throws Exception
     */
	@RequestMapping(value="/admin/loginfrm.do")
	public String login(ModelMap model 
			, HttpServletRequest request
			, SessionStatus sessionStatus
			, String error
			, String openerGb) throws Exception{
		
		//기업정보		
		List envList = envService.envList();
		HashMap<String, String> envMap = new HashMap<String, String>();
		EnvVO tmpVO = new EnvVO();
		String mainLogo = "";
		for(int i = 0; i< envList.size(); i++){
			tmpVO = (EnvVO)envList.get(i);
			envMap.put(tmpVO.getName(), tmpVO.getValue());
			if(tmpVO.getName().equals("mainLogImgName"))	mainLogo = tmpVO.getValue();
		}
		
		model.addAttribute("mainLogo", mainLogo); 
		
		/*관리자 그룹 리스트 세션에 저장 (@SessionAttributes)*/
		model.addAttribute("error", error);
		model.addAttribute("openerGb", openerGb);
		 
		return "admin/login/loginfrm.tiles";
	}
	
	/**
	 * 관리자 로그인 
	 * @param model
	 * @param adminVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/login.do")
	public String loginProc(ModelMap model
			, @ModelAttribute("adminVO") AdminVO adminVO
			, HttpServletRequest request
			, SessionStatus sessionStatus) throws Exception{
		/*
		 * 로그인 규칙  
		 * 로그인시 전에 로그인한 member 나 staff  가 있으면 자동 로그아웃 
		 * 로그아웃은 무조건 모든 세션 삭제  
		 * */

		adminVO.setPassword(pwEncryptionSha256(EgovStringUtil.removeWhitespace(adminVO.getPassword())));
		AdminVO resultVO2 = adminService.adminInfoById(adminVO);
		request.getSession().setAttribute("adminUser", resultVO2);
		
		/*
		LOGGER.debug("loginProc start");
		LOGGER.debug("adminUser = "+request.getSession().getAttribute("adminUser"));
		LOGGER.debug("id = "+ adminVO.getId());
		LOGGER.debug("pw = "+ adminVO.getPassword());
		 * */
		
		int mainGroup = adminVO.getMainGroup();
		
		List adminGroupList = null;
		if(request.getSession().getAttribute("adminUser") != null){
			LOGGER.debug("adminUser exist");
			adminVO = (AdminVO) request.getSession().getAttribute("adminUser");
			LOGGER.debug("adminVO key = "+adminVO.getKey());
			adminVO.setMainGroup(mainGroup);
			//세션에 값이 있으면 메인 화면 호출
			model.addAttribute("adminUser", adminVO);
			/** 최고관리자일 경우 모든 그룹
			 * 일반관리자일 경우 권한이 있는 그룹 리스트  
			 */
			
			//그룹키 설정
			int groupKey = 1;

			LOGGER.debug("adminVO.getMainGroup() = "+adminVO.getMainGroup());
			mainGroup = adminVO.getMainGroup();
			if( mainGroup == 0){
				//무조건 첫번째 그룹키를 가져온다.
				LOGGER.debug("groupKey = "+groupKey);
				model.addAttribute("adminGroupKey", groupKey);	
				request.getSession().setAttribute("groupKey", groupKey);
			}else{
				LOGGER.debug("mainGroup = "+mainGroup);
				model.addAttribute("adminGroupKey", mainGroup );
				request.getSession().setAttribute("groupKey", mainGroup);
			}
		
			//이미 로그인했을 경우의 처리 종료
			return "redirect:/admin/index.do";
		}
		
		
		//비밀번호 암호화
		adminVO.setPassword(pwEncryptionSha256(EgovStringUtil.removeWhitespace(adminVO.getPassword())));
		//로그인
		AdminVO resultVO = adminService.adminInfoById(adminVO);
		if(resultVO != null && !"".equals(resultVO)){
			//로그인 성공 
			UserInfoVO user = new UserInfoVO();
			user.setKey(adminVO.getKey());
			if("T".equals(adminVO.getAdminFlag())){
				user.setLevelRank(0);
			}else{
				user.setLevelRank(1);
			}
			user.setLevel("관리자");
			user.setId(adminVO.getId());
			user.setName("관리자");
			user.setEmail(adminVO.getEmail());
			user.setType("A");
			
			
			
			

			/*관리자 정보, 그룹키 세션에 저장 (@SessionAttributes)*/ 
			resultVO.setId(EgovStringUtil.removeWhitespace(adminVO.getId()));
			request.getSession().setAttribute("adminUser", resultVO);
			request.getSession().setAttribute("user", user); 

			

			LOGGER.debug("resultVO.getKey() = "+resultVO.getKey());
			LOGGER.debug("resultVO.getAdminFlag() = "+resultVO.getAdminFlag());
			
			/** 최고관리자일 경우 모든 그룹
			 * 일반관리자일 경우 권한이 있는 그룹 리스트  
			 */
			

			/*
			 * 2015 05 13 이차장님 지시
			 * 일부 메뉴에 그룹 변경해 조회하는 것은 규칙에맞지않는다.  
			 * 무조건 로그인시 권한이 있는 메뉴에 따라 그룹이 조회되어야 하고
			 * 권한이 한 그룹에만 있을 경우 셀렉트 박스 없어야 한다.
			 * 권한이 변경됨에 따라 셀렉트 박스가 생기거나 없어지며
			 * 현재 컨텐츠에 존재하는 그룹변경 기능은 전부 숨겨지고
			 * 무조건 탑메뉴에 존재하는 그룹셀렉트 박스에 따라 
			 * 컨텐츠가 조회되며 
			 * 
			 * 만일 탑메뉴의 셀렉트박스가 변경될때 같은 메뉴의 권한이 있으면 
			 * index 로 가지 않고 같은 메뉴로 자동 세팅되어야 한다.
			 * 
			 * 만일 권한이 하나도 없다면 메시지를 뿌리고 로그인 화면으로 가야 한다.
			 * 
			 * */
			 

			LOGGER.debug("redirect:/admin/index.do");
			return "redirect:/admin/index.do";
		}
		LOGGER.debug("forward:/admin/loginfrm.do?error=flag");
		return "forward:/admin/loginfrm.do?error=flag";
	}
	
	
	/**
	 * 관리자 로그 아웃
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/logout.do")
	public String logout(ModelMap model, HttpServletRequest request, SessionStatus sessionStatus)
			throws Exception {
		
		request.getSession().removeAttribute("adminUser");
		UserInfoVO user = (UserInfoVO) request.getSession().getAttribute("user");
		if(user != null){
			user.setType("");
			request.getSession().setAttribute("user",user);
		}
		
		//request.getSession().removeAttribute("user");
		//request.getSession().removeAttribute("groupKey"); 
		
		/**
		 * 세션에 저장된 정보를 모두 제거한다.
		 * 뭘 넣었던 간에 무조건 자동 전체 삭제해줌 
		 * 즉 , 인트라넷 로그아웃해도 어드민 세션은 살아야 함.
		 * 어드민 로그아웃시 인트라넷 세션은 살아야 함.
		 * */ 
		sessionStatus.setComplete();
		
		LOGGER.debug("admin logout end");
		return "redirect:/admin/loginfrm.do";
	}
	
	
	/**
	 * 관리자 메인 화면 호출
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/index.do")
	public String index(ModelMap model
			, HttpServletRequest request) throws Exception{

		LOGGER.debug("adminUser = "+request.getSession().getAttribute("adminUser"));
		
		if(request.getSession().getAttribute("adminUser") != null
				&& !"".equals(request.getSession().getAttribute("adminUser"))){					//세션에 값이 있으면 메인 화면 호출

			/**
			 * 2015 05 12 
			 * 상단 메뉴세팅, 좌측메뉴 삭제
			 * */
			AdminVO adminUser = (AdminVO) request.getSession().getAttribute("adminUser");
			int groupkey = (Integer) request.getSession().getAttribute("groupKey");
			LOGGER.debug("groupkey = "+groupkey);
			
			AdminmenuVO vo = new AdminmenuVO();
			vo.setId(adminUser.getId());
			vo.setGroupKey(groupkey);
			
			List adminMenuTopList = adminmenuService.adminmenuTopList(vo);
			List leftMenuResult = new ArrayList<String>();
			model.addAttribute("adminMenuTopList", adminMenuTopList);
			model.addAttribute("leftMenuResult", leftMenuResult);
			model.addAttribute("groupKey", groupkey);
			return "admin/index";
		}
		
		return "forward:/admin/loginfrm.do";
		
	}
	
	public static String pwEncryption(String pwstr){
		
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
     * 홈페이지 관리자 로그인 화면 호출
     * @param model
     * @return
     * @throws Exception
     */
	@RequestMapping(value="/jsp/loginfrm.do")
	public String mainLogin(ModelMap model 
			, HttpServletRequest request
			, SessionStatus sessionStatus
			, String error
			, String openerGb) throws Exception{
		
		/*관리자 그룹 리스트 세션에 저장 (@SessionAttributes)*/
		model.addAttribute("error", error);
		model.addAttribute("openerGb", openerGb);
		 
		
		return "rndp/main/login.tiles";
	}
	
	
	
	/**
	 * 홈페이지 관리자 로그인 
	 * @param model
	 * @param adminVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/jsp/login.do")
	public String mainLoginProc(ModelMap model
			, @ModelAttribute("adminVO") AdminVO adminVO
			, HttpServletRequest request
			, SessionStatus sessionStatus) throws Exception{
		/*
		 * 2015 04 28
		 * 로그인 규칙  
		 * 로그인시 전에 로그인한 member 나 staff  가 있으면 자동 로그아웃 
		 * 로그아웃은 무조건 모든 세션 삭제  
		 * 
		 * 2015 05 20 변경 다른 세션 삭제하지 않는다.

		LOGGER.debug("loginProc start");
		LOGGER.debug("adminUser = "+request.getSession().getAttribute("adminUser"));
		LOGGER.debug("id = "+ adminVO.getId());
		LOGGER.debug("pw = "+ adminVO.getPassword());
		 * */
		
		int mainGroup = adminVO.getMainGroup();
		
		List adminGroupList = null;
		if(request.getSession().getAttribute("adminUser") != null){
			//LOGGER.debug("adminUser exist");
			adminVO = (AdminVO) request.getSession().getAttribute("adminUser");
			//LOGGER.debug("adminVO key = "+adminVO.getKey());
			adminVO.setMainGroup(mainGroup);
			//세션에 값이 있으면 메인 화면 호출

			model.addAttribute("adminUser", adminVO);
			/** 최고관리자일 경우 모든 그룹
			 * 일반관리자일 경우 권한이 있는 그룹 리스트  
			 */
			model.addAttribute("adminGroupList", adminGroupList);
			if(adminGroupList == null || 0 == adminGroupList.size() ){
				//아무런 권한이 없는 관리자일 경우 로그인 페이지로 리다이렉트 
				/**
				 * 세션에 저장된 정보를 모두 제거한다.
				 * 뭘 넣었던 간에 무조건 자동 전체 삭제해줌 
				 * 
				 * */ 
				sessionStatus.setComplete();
				LOGGER.debug("forward:/jsp/loginfrm.do?error=noAuth");
				return "redirect:/jsp/loginfrm.do?error=noAuth";
			} 
			
			//그룹키 설정
			int groupKey = 0;

			LOGGER.debug("adminVO.getMainGroup() = "+adminVO.getMainGroup());
			mainGroup = adminVO.getMainGroup();
		
			//이미 로그인했을 경우의 처리 종료
			return "redirect:/main.do";
		}
		/**
		 * 2015 05 18 이차장님이 그룹별로
		 *  서로 다른 사이트로 취급한다고 정의함 
		 *  따라서 세션이 삭제되어선 안됨
		 * 
		 * */
		/*if(request.getSession().getAttribute("user") != null
				||request.getSession().getAttribute("staff") != null 
				){					
			//세션에 다른 값이 있으면 삭제
			request.getSession().setAttribute("user",null);
			request.getSession().setAttribute("staff",null); 
		}*/
		
		
		//비밀번호 암호화
		adminVO.setPassword(pwEncryptionSha256(EgovStringUtil.removeWhitespace(adminVO.getPassword())));
		//로그인
		AdminVO resultVO = adminService.adminInfoById(adminVO);
		if(resultVO != null && !"".equals(resultVO)){
			//로그인 성공 
			UserInfoVO user = new UserInfoVO();
			user.setKey(adminVO.getKey());
			if("T".equals(adminVO.getAdminFlag())){
				user.setLevelRank(0);
			}else{
				user.setLevelRank(1);
			}
			user.setLevel("관리자");
			user.setId(adminVO.getId());
			user.setName("관리자");
			user.setEmail(adminVO.getEmail());
			user.setType("A");
			

			/*관리자 정보, 그룹키 세션에 저장 (@SessionAttributes)*/ 
			resultVO.setId(EgovStringUtil.removeWhitespace(adminVO.getId()));
			request.getSession().setAttribute("adminUser", resultVO);
			request.getSession().setAttribute("user", user); 

			

			LOGGER.debug("resultVO.getKey() = "+resultVO.getKey());
			LOGGER.debug("resultVO.getAdminFlag() = "+resultVO.getAdminFlag());
			
			/** 최고관리자일 경우 모든 그룹
			 * 일반관리자일 경우 권한이 있는 그룹 리스트  
			 */
			
			if(adminGroupList == null || 0 == adminGroupList.size() ){
				//아무런 권한이 없는 관리자일 경우 로그인 페이지로 리다이렉트
				/**
				 * 세션에 저장된 정보를 모두 제거한다.
				 * 뭘 넣었던 간에 무조건 자동 전체 삭제해줌 
				 * 
				 * */ 
				sessionStatus.setComplete();
				LOGGER.debug("forward:/jsp/loginfrm.do?error=noAuth");
				return "redirect:/jsp/loginfrm.do?error=noAuth";
			}
			
			//무조건 첫번째 그룹키를 가져온다.
			model.addAttribute("adminGroupList", adminGroupList);

			 

			LOGGER.debug("redirect:/main.do");
			return "redirect:/main.do";
		}
		LOGGER.debug("forward:/jsp/loginfrm.do?error=flag");
		return "forward:/jsp/loginfrm.do?error=flag";
	}
	
	
	
	
	/**
	 * 홈페이지 관리자 로그 아웃
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/jsp/logout.do")
	public String mainLogout(ModelMap model, HttpServletRequest request, SessionStatus sessionStatus)
			throws Exception {
		
		request.getSession().removeAttribute("adminUser");
		request.getSession().removeAttribute("user");

		
		LOGGER.debug("admin logout end");
		return "redirect:/main.do";
	}
	
	
	
	
}
