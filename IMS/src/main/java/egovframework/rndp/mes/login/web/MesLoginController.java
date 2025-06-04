package egovframework.rndp.mes.login.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.rndp.admin.env.service.EnvService;
import egovframework.rndp.admin.env.service.EnvVO;
import egovframework.rndp.com.utl.JsonHelper;
import egovframework.rndp.login.service.UserInfoVO;
import egovframework.rndp.mes.common.service.MesCommonService;
import egovframework.rndp.mes.login.service.MesK_StaffVo;
import egovframework.rndp.mes.login.service.MesLoginService;
import egovframework.rndp.mes.staff.service.MesStaffVO;
import egovframework.rndp.mes.user.service.MesUserService;
import egovframework.rndp.mes.user.service.MesUserVO;


/**
 * MES 로그인  
 * 필수 컨트롤러이므로 모듈화 예외  
 * @author rndp-21
 *
 */
@Controller 
@SessionAttributes({ "staff" })
public class MesLoginController { 
	
	private static final Logger LOGGER = LoggerFactory .getLogger(MesLoginController.class);
	
	@Resource(name = "mesUserService")
	private MesUserService mesUserService;
	
	@Resource(name = "mesLoginService")
	private MesLoginService mesLoginService;

	@Resource(name = "envService")
	private EnvService envService;


	/*공통사용 서비스 common 및  Egov 공용 */
	@Resource(name = "mesCommonService")
	private MesCommonService mesCommonService;
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;
	@Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;
	/*공통사용 서비스 common 및  Egov 공용 */
	
	/**
	 *   로그인 폼 호출
	 * */	
	@RequestMapping(value="/mes/loginfrm.do")
	public String mesLoginfrm(HttpServletRequest request
			, ModelMap model)throws Exception {
		LOGGER.debug("mesLoginfrm log");
		
		String csrfToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("csrfToken", csrfToken);
		request.setAttribute("csrfToken", csrfToken);
		
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
		
		return "mes/login/loginfrm.tiles";
	}
	
	
	/**
	 *   로그인
	 * */
	@RequestMapping(value="/mes/login.do")
	public String salesLogin(HttpServletRequest request
			, MesK_StaffVo MesK_StaffVo 
			, @ModelAttribute("mesUserVO") MesUserVO mesUserVO
			, HttpServletResponse response
			, ModelMap model ) throws Exception { 	
		
		String sessionToken = (String) request.getSession().getAttribute("csrfToken");
        String requestToken = request.getParameter("csrfToken");

        if (sessionToken == null || !sessionToken.equals(requestToken)) {
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }else {
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
    		
    		String id = MesK_StaffVo.getkStaffId();
    		
    		int num = mesLoginService.selectMesLoginCount(MesK_StaffVo);
    		if(num < 5){
    			if(MesK_StaffVo == null || MesK_StaffVo.getkStaffId().isEmpty() || MesK_StaffVo.getkStaffPassword().isEmpty() ){	
    				return "mes/login/loginfrm.tiles";
    				
    			}else{
    				//256적용
    				String pass = MesK_StaffVo.getkStaffPassword();
    				pass = pwEncryptionSha256(pass);
    				MesK_StaffVo.setkStaffPassword(pass);	
    				
    				
    				//직원정보를 조회한다. 
    				LOGGER.debug("mesLogin select start");
    				MesK_StaffVo =  mesLoginService.selectMesLoginStaff(MesK_StaffVo);
    				LOGGER.debug("mesLogin select end");
    				
    				if(MesK_StaffVo != null){
    					MesK_StaffVo vo = new MesK_StaffVo();
    					vo.setkStaffId(id);
    					mesLoginService.updateMesLoginStaff2(vo);
    					//세션정보 저장 
    					request.getSession().setAttribute("mesStaff",MesK_StaffVo ); 
    					model.addAttribute("staff", MesK_StaffVo);
    					
    					//항상 생산관리이므로 fix
    					request.getSession().setAttribute("groupKey",1); 
    					 
    					UserInfoVO user = (UserInfoVO) request.getSession().getAttribute("user");
    					LOGGER.debug("user = "+user); 
    					
    					//만일 이미 관리자로 로그인하지 않았으면
    					if(user != null && user.getType().equals("A")){

    						//이미 관리자로 로그인햇으면 id,name 만 바꿈 (type A 는 남김)
    						user.setId(MesK_StaffVo.getkStaffId());
    						user.setName(MesK_StaffVo.getkStaffName());
    						request.getSession().setAttribute("user" ,  user);
    						
    					}else{
    						user = new UserInfoVO();
    						//이 사용자는 직원 (스탭)임
    						user.setId(MesK_StaffVo.getkStaffId());
    						user.setName(MesK_StaffVo.getkStaffName());
    						
    						user.setType("M"); 
    						user.setLevelRank(100); 
    						request.getSession().setAttribute("user" ,  user);
    					}

    					LOGGER.debug("redirect:/mes/main.do"); 
    					request.getSession().setAttribute("count" ,  0);
    					request.getSession().setAttribute("confirm" ,  null);
    					return "redirect:/mes/main.do";	
    				}else{
    					//로그인 실패
    					MesK_StaffVo vo = new MesK_StaffVo();
    					vo.setkStaffId(id);
    					mesLoginService.updateMesLoginStaff(vo);
    					int num1 = num+1; 
    					request.getSession().setAttribute("count" ,  num1);
    					request.getSession().setAttribute("confirm" ,  "O");
    					return "redirect:/mes/loginfrm.do";
    				}			
    			}
    		}else{
    			int num1 = num+1; 
    			request.getSession().setAttribute("count" ,  num1);
    			request.getSession().setAttribute("confirm" ,  "O");
    			return "redirect:/mes/loginfrm.do";
    		}
        }
        return null;
	}


	/**
	 *  로그아웃
	 * */
	@RequestMapping(value="/mes/logout.do")
	public String mesLogout(HttpServletRequest reuqest,SessionStatus session,HttpSession session1,ModelMap model )throws Exception 
	{ 
		LOGGER.debug("mesLogout log"); 
		session.setComplete();
		session1.removeAttribute("staff");
		session1.removeAttribute("groupKey");
		session1.removeAttribute("rootMenu");
		session1.removeAttribute("mesAllRefMenu");
		session1.removeAttribute("mesMenuInfo");
		session1.removeAttribute("mesTopMenu");
		session1.removeAttribute("mesLeftMenu");
		session1.removeAttribute("mesTabMenu");
		session1.removeAttribute("formatDate");
		session1.removeAttribute("key");
		session1.removeAttribute("root");
		
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
				
		return "mes/login/loginfrm.tiles";
	}
	
	
	/**
	 * <pre>
	 * 비밀번호 md5 로 암호화 (미사용)
	 * </pre>
	 * @param String pwstr
	 * @return String 
	 * * /
	public static String pwEncryption(String pwstr){
		
		MessageDigest md;
		String hashText = "";
		
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] pwByte 	= md.digest(pwstr.getBytes());
			BigInteger num 	= new BigInteger(1, pwByte);
			hashText 		= num.toString(16);
			
			while(hashText.length() < 32){
				hashText = "0" + hashText;
			}
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hashText;
	}	* */
	
	/**
	 * 비밀번호 Sha256 암호화(사용)
	 * @param pwstr
	 * @return
	 */
	public static String pwEncryptionSha256(String pwstr){
		String hashText = "";
		 try{
			 //암호화 종류 지정 
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
	 * <pre>
	 * 직원 등록 신청 팝업 2020 07 27모듈화로 intra 에서 mes 로 이전 
	 * </pre>
	 * @param model
	 * @param request
	 * @return "/popup/km_staff_req_if "
	 * */
	@RequestMapping(value="/popup/mes/km_staff_req_if.do")
	public String registrationPopup(ModelMap model
			, HttpServletRequest request) throws Exception{
		
		List ClassNameList = mesLoginService.selectKClassNameList();
		model.addAttribute("ClassNameList", ClassNameList);

		List SectorList = mesLoginService.selectKSectorList();
		model.addAttribute("SectorList", SectorList);
		
		List positionList = mesLoginService.selectKPositionList();
		model.addAttribute("positionList", positionList);

		return "mesPopup/mes/login/popup/km_staff_req_if";
	}

	/**
	 * <pre>
	 * 아이디 중복체크
	 * </pre>
	 * @param response
	 * @param request 
	 * @param kStaffVo
	 * */
  @RequestMapping(method=RequestMethod.POST, value="/popup/mes/kw_checkid.do")
  public void checkStaffId(HttpServletResponse response
        , HttpServletRequest request
        , MesK_StaffVo kStaffVo) throws Exception{
	  
	  LOGGER.debug("checkStaffId start ");
	  
	 int checkIdCnt = mesLoginService.selectCheckId(kStaffVo.getkStaffId());
	 
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
  

  /**
   * 직원등록신청 
	 * @param request
	 * @param kStaffVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/popup/mes/checkStaffId.do")
	public String checkStaffInsert(HttpServletRequest request
		        , MesStaffVO kStaffVo
		        , ModelMap model) throws Exception{

		LOGGER.debug(" kStaffVo.setkStaffPassword : "+  kStaffVo.getkStaffPassword());
		//256 암호화 적용
		String pass = request.getParameter("kStaffPassword"); 
		pass = pwEncryptionSha256(pass); 
		kStaffVo.setkStaffPassword(pass);
		kStaffVo.setkStaffHomepage("http://");
		kStaffVo.setkStaffComment("000");
		kStaffVo.setkStaffStateFlag("1");
		kStaffVo.setkStaffImagefile("000");
		kStaffVo.setkStaffSignImage("000");
		kStaffVo.setkStaffNum("00000");
		kStaffVo.setkStaffJumin1("000000");
		kStaffVo.setkStaffJumin2("0000000");
		kStaffVo.setkMailUse("Y");
		kStaffVo.setkSectorKey("1"); 
		
		mesLoginService.checkStaffInsert(kStaffVo);
		model.addAttribute("closeValue", "1"); 
		return "mesPopup/mes/login/popup/km_staff_req_if";
  }
}
