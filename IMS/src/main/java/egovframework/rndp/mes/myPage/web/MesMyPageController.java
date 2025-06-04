package egovframework.rndp.mes.myPage.web;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.rndp.com.utl.JsonHelper;
import egovframework.rndp.mes.common.service.MesCommonService;
import egovframework.rndp.mes.myPage.service.MesMyPageService;
import egovframework.rndp.mes.myPage.service.MesMyPageVO;
import egovframework.rndp.mes.login.service.MesK_StaffVo;

@Controller
public class MesMyPageController {

	/*공통사용 서비스 common 및  Egov 공용 */
	@Resource(name = "mesCommonService")
	private MesCommonService mesCommonService;
	
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;
	
	@Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;
	/*공통사용 서비스 common 및  Egov 공용 */
	
	@Resource(name = "mesMyPageService")
	private MesMyPageService mesMyPageService;

	@RequestMapping(value = "/mes/myPage/kw_myPage_lf.do")
	public String mesKwMyPageLf( HttpServletRequest request
			, @ModelAttribute("mesMyPageVO") MesMyPageVO mesMyPageVO
			, ModelMap model
			, HttpServletResponse response) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo);
		
		mesMyPageVO.setShMemberId(staffVo.getkStaffId());
		
		MesMyPageVO vo = mesMyPageService.selectMyPageInfo(mesMyPageVO);
		model.addAttribute("vo", vo);
		
		return "mes/myPage/kw_myPage_lf.tiles";
	}
	
	// 비밀번호 암호화
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
	
	// 비밀번호 check
	@RequestMapping(value = "/mes/myPage/kw_myPageInfoCheck.do")
	public void mesMyPageInfoCheck(HttpServletRequest request
			, @ModelAttribute("mesMyPageVO") MesMyPageVO mesMyPageVO
			, ModelMap model
			, HttpServletResponse response) throws Exception{
		
		JsonHelper helper = new JsonHelper();
		Map<String,Object> map = new HashMap<String,Object>();
		String message;
		
		MesMyPageVO vo = mesMyPageService.selectMyPageInfo(mesMyPageVO);
		
		if(!pwEncryptionSha256(mesMyPageVO.getShMemberPassword()).equals(vo.getkStaffPw())){
			message = "비밀번호가 일치하지 않습니다.";
			map.put("idx", 0);
			map.put("message", message);
		}else{
			message = "비밀번호 성공";
			map.put("idx", 1);
			map.put("message", message);
		}
		
		helper.printJsonObject(response, "result", map);
		
	}
	
	@RequestMapping(value = "/mes/myPage/kw_myPage_uf.do")
	public String mesKwMyPageUf(HttpServletRequest request
			, @ModelAttribute("mesMyPageVO") MesMyPageVO mesMyPageVO
			, ModelMap model
			, HttpServletResponse response) throws Exception{
		
		MesMyPageVO vo = mesMyPageService.selectMyPageInfo(mesMyPageVO);
		model.addAttribute("vo", vo);
		
		return "mes/myPage/kw_myPage_uf.tiles";
	}
	
	// 회원정보 수정
	@RequestMapping(value = "/mes/myPage/kw_myPage_u.do")
	public String mesKwMyPageU(HttpServletRequest request
			, @ModelAttribute("mesMyPageVO") MesMyPageVO mesMyPageVO
			, ModelMap model
			, HttpServletResponse response) throws Exception{
		
		mesMyPageService.updateMyPageInfo(mesMyPageVO);
		
		return "mes/main.tiles";
	}
}
