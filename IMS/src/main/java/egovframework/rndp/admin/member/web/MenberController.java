package egovframework.rndp.admin.member.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.rndp.admin.member.service.MemberService;
import egovframework.rndp.admin.member.service.MemberVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class MenberController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MenberController.class);
    
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
	@Resource(name = "memberService")
    private MemberService memberService;
	
	@RequestMapping(value="/admin/adminMemberList.do")
	public String adminMemberList( HttpServletRequest request
			, @ModelAttribute("memberVO") MemberVO memberVO
			, ModelMap model) throws Exception{
		
		
//		memberVO.setPageUnit(propertiesService.getInt("pageUnit"));
//		memberVO.setPageSize(propertiesService.getInt("pageSize"));
		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(memberVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(memberVO.getPageUnit());
		paginationInfo.setPageSize(memberVO.getPageSize());

		memberVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		memberVO.setLastIndex(paginationInfo.getLastRecordIndex());
		memberVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		/** pageing end*/

		
		List memberList = memberService.memberList(memberVO);
		int totCnt = memberService.memberListCnt(memberVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		if(memberVO.getSearchType() == null || "".equals(memberVO.getSearchType())){
			memberVO.setSearchType("memId");
		}
		
		model.addAttribute("memberList", memberList);
		model.addAttribute("memberVO", memberVO);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "admin/member/list";
	}
	
	@RequestMapping(value="/admin/memberView.do")
	public String adminMemberView(HttpServletRequest request
			, @ModelAttribute("memberVO") MemberVO memberVO
			, ModelMap model) throws Exception{
		
		memberVO = memberService.memberView(memberVO.getKey());
		
		model.addAttribute("member", memberVO);
		
		return "admin/member/view";
	}
	
	@RequestMapping(value="/admin/memberUpdate.do")
	public String adminMemberUpdate(HttpServletRequest request
			, @ModelAttribute("memberVO") MemberVO memberVO) throws Exception{
		
		memberService.memberUpdate(memberVO);
		return "redirect:/admin/memberView.do?key="+memberVO.getKey();
	}
	
	@RequestMapping(value="/admin/memberDelete.do")
	public String adminMemberDelete(HttpServletRequest request
			, @ModelAttribute("memberVO") MemberVO memberVO) throws Exception{
		
		memberService.memberDelete(memberVO.getKey());
		return "redirect:/admin/adminMemberList.do";
	}
	
	
	
//	@RequestMapping(value="/admin/adminMemberList.do")
//	public String adminMemberDiteil( HttpServletRequest request
//			, @ModelAttribute("memberVO") MemberVO memberVO
//			, ModelMap model) throws Exception{
//		
//		
////		memberVO.setPageUnit(propertiesService.getInt("pageUnit"));
////		memberVO.setPageSize(propertiesService.getInt("pageSize"));
//		/** pageing */
//		PaginationInfo paginationInfo = new PaginationInfo();
//		paginationInfo.setCurrentPageNo(memberVO.getPageIndex());
//		paginationInfo.setRecordCountPerPage(memberVO.getPageUnit());
//		paginationInfo.setPageSize(memberVO.getPageSize());
//
//		memberVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
//		memberVO.setLastIndex(paginationInfo.getLastRecordIndex());
//		memberVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
//		/** pageing end*/
//
//		
//		List memberList = memberService.memberList(memberVO);
//		int totCnt = memberService.memberListCnt(memberVO);
//		paginationInfo.setTotalRecordCount(totCnt);
//		
//		if(memberVO.getSearchType() == null || "".equals(memberVO.getSearchType())){
//			memberVO.setSearchType("memId");
//		}
//		
//		model.addAttribute("memberList", memberList);
//		model.addAttribute("memberVO", memberVO);
//		model.addAttribute("paginationInfo", paginationInfo);
//		
//		return "admin/member/list";
//	}
	
	
	
}
