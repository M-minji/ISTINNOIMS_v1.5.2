package egovframework.rndp.member.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rndp.admin.member.service.MemberService;
import egovframework.rndp.admin.member.service.MemberVO;

@Controller
public class WebMemberController {

	@Autowired
	private DefaultBeanValidator beanValidator;

	@Resource(name = "memberService")
	private MemberService memberService;
	
	@RequestMapping(value="/checkId.do")
	public String checkIdController(HttpServletRequest request
			, String id
			, ModelMap model) throws Exception{
		boolean result = false;
		if(id == null || "".equals(id)){
			id = "";
		}else{
			String checkId = memberService.webCheckid(id);
			if(checkId == null || "".equals(checkId)){
				result = true;
			}
		}
		model.addAttribute("result", result);
		model.addAttribute("id", id);
		return "member/kw_checkid";
	}
	
	@RequestMapping(value="/post.do")
	public String postController(HttpServletRequest request
			, String searchWord
			, ModelMap model) throws Exception{
		
		List result = null;
		if(searchWord != null && !"".equals(searchWord)){
			result = memberService.postList(searchWord);
		}
		
		model.addAttribute("result", result);
		return "member/kw_post";
	}
	
	@RequestMapping(value="/webMemberInsert.do")
	public String webMemberInsert(HttpServletRequest request
			, int menuKey
			, @ModelAttribute("memberVO") MemberVO memberVO
			, BindingResult bindingResult) throws Exception{
		
		beanValidator.validate(memberVO, bindingResult);
		if (bindingResult.hasErrors()){
			return "forward:/webMenu.do?key="+menuKey;
		}
		memberService.memberInsert(memberVO);

		return "redirect:/main.do";
	}
}
