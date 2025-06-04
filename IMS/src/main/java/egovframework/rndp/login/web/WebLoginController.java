package egovframework.rndp.login.web;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.rndp.admin.admin.service.AdminService;
import egovframework.rndp.admin.admin.service.AdminVO;
import egovframework.rndp.admin.member.service.MemberService;
import egovframework.rndp.admin.member.service.MemberVO;
import egovframework.rndp.com.utl.EgovStringUtil;
import egovframework.rndp.login.service.UserInfoVO;

@Controller
public class WebLoginController {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebLoginController.class);
	
	
	@Resource(name = "adminService")
    private AdminService adminService;
	
	@Resource(name = "memberService")
	private MemberService memberService;
	

	@RequestMapping(value="/login.do")
	public String webLogin(HttpServletRequest request
			, int menuKey
			, @ModelAttribute("userInfoVO") UserInfoVO user
			, ModelMap model) throws Exception{
		
		String strUrl = "";
		LOGGER.debug("WebLoginController::::::::");
		UserInfoVO sessionUser = (UserInfoVO) request.getSession().getAttribute("user");
		
		if("M".equals(sessionUser.getType()) || "A".equals(sessionUser.getType())){					//세션에 값이 있으면 메인 화면 호출
			return "redirect:/main.do";
		}
		
		if("A".equals(user.getType())){                                         //관리자 로그인
			AdminVO adminVO = new AdminVO();
			adminVO.setId(user.getId());
			adminVO.setPassword(pwEncryption(EgovStringUtil.removeWhitespace(user.getPassword())));
			adminVO = adminService.adminInfoById(adminVO);
			if(adminVO != null){
				if(adminVO.getKey() != 0){                                       
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
					request.getSession().setAttribute("user", user);                //세션에 값 저장
					adminVO.setId(EgovStringUtil.removeWhitespace(adminVO.getId()));
					request.getSession().setAttribute("adminUser", adminVO);                //세션에 값 저장
					request.getSession().setAttribute("groupKey", adminVO.getGroup());
					strUrl="redirect:/main.do";
				}else{ 																//로그인 정보가 틀리면
					model.addAttribute("error", "flag"); 
					strUrl = "forward:/webMenu.do?key="+menuKey+"&error=flag";  
				}
			}else{
				model.addAttribute("error", "flag"); 
				strUrl = "forward:/webMenu.do?key="+menuKey+"&error=flag";  
			}
		}else{                                                                  //회원 로그인
			MemberVO vo = new MemberVO();
			vo.setId(user.getId());
			vo.setPassword(user.getPassword());
			vo = memberService.getMemberInfo(vo);
			if(vo != null){
				if("1".equals(vo.getStateFlag())){
					user.setKey(vo.getKey());
					user.setLevelRank(vo.getLevelRank());
					user.setLevel(vo.getLevel());
					user.setId(vo.getId());
					user.setName(vo.getName());
					user.setEmail(vo.getEmail());
					user.setType("M");
					request.getSession().setAttribute("user", user);
					memberService.updateLastdate(vo.getKey());                    //세션에 값 저장
					strUrl = "redirect:/main.do";
				}else{
					strUrl = "forward:/webMenu.do?key="+menuKey+"&error=flag";  
				}
			}else{                                                              //로그인 정보가 틀리면
				model.addAttribute("error", "flag"); 
				strUrl = "forward:/webMenu.do?key="+menuKey+"&error=flag";                                            
			}
		}
		return strUrl;
	}
	
	@RequestMapping(value="/logout.do")
	public String webLogOut(HttpServletRequest request) throws Exception{
		request.getSession().setAttribute("user", null);
		request.getSession().setAttribute("adminUser", null);
		return "redirect:/main.do";
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
}
