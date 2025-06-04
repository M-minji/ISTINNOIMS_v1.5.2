package egovframework.rndp.mes.gubun.gubunCate.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.rndp.com.utl.JsonHelper;
import egovframework.rndp.mes.gubun.gubunCate.service.MesGubunCateService;
import egovframework.rndp.mes.gubun.gubunCate.service.MesGubunCateVO;
import egovframework.rndp.mes.login.service.MesK_StaffVo;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class MesGubunCateController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MesGubunCateController.class);
	
	@Resource(name = "mesGubunCateService")
	private MesGubunCateService mesGubunCateService;
	
	// 안상현 수정
	@RequestMapping(value = "/mes/gubun/gubunCate/kw_gubunCate_lf.do")
	public String mesGubunCateLf(HttpServletRequest request
			, @ModelAttribute("mesGubunCateVO") MesGubunCateVO mesGubunCateVO
			, RedirectAttributes redirectAttributes
			, ModelMap model) throws Exception {
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");	
		model.addAttribute("staffVo", staffVo);
		
		/* paging */
		PaginationInfo paginationInfo = new PaginationInfo();											
		paginationInfo.setCurrentPageNo(mesGubunCateVO.getPageIndex());									
		paginationInfo.setRecordCountPerPage(mesGubunCateVO.getRecordCountPerPage());					
		paginationInfo.setPageSize(mesGubunCateVO.getPageSize());										

		mesGubunCateVO.setFirstIndex(paginationInfo.getFirstRecordIndex());												
		mesGubunCateVO.setLastIndex(paginationInfo.getLastRecordIndex());								
		mesGubunCateVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());					

		List gubunCateList = mesGubunCateService.selectMesGubunCateList(mesGubunCateVO);
		model.addAttribute("gubunCateList", gubunCateList);
		
		int totCnt = mesGubunCateService.selectMesGubunCateListCnt(mesGubunCateVO);									
		paginationInfo.setTotalRecordCount(totCnt);		
		
		model.addAttribute("paginationInfo", paginationInfo);
		
		redirectAttributes.addFlashAttribute("mesGubunCateVO", mesGubunCateVO);
		
		return "mes/gubun/gubunCate/kw_gubunCate_lf.tiles";
	}
	
	@RequestMapping(value = "/mes/gubun/gubunCate/kw_gubunCate_if.do")
	public String mesGubunCateIf(HttpServletRequest request
			, @ModelAttribute("mesGubunCateVO") MesGubunCateVO mesGubunCateVO
			, RedirectAttributes redirectAttributes
			, ModelMap model) throws Exception {
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");	
		model.addAttribute("staffVo", staffVo);
		
		redirectAttributes.addFlashAttribute("mesGubunCateVO", mesGubunCateVO);
		
		return "mes/gubun/gubunCate/kw_gubunCate_if.tiles";
	}
	
	@RequestMapping(value = "/mes/gubun/kw_gubunCheckAjax.do")
	public void mesGubunCheckAjax(HttpServletRequest request
			, @ModelAttribute("mesGubunCateVO") MesGubunCateVO mesGubunCateVO
			, HttpServletResponse response) throws Exception{
		
		int resultCnt = mesGubunCateService.selectGubunCheck(mesGubunCateVO);					
		
		JsonHelper helper = new JsonHelper();											
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("resultCnt", resultCnt);
		helper.printJsonObject(response, "result", map);	
	}
	
	@RequestMapping(value = "/mes/gubun/gubunCate/kw_gubunCate_i.do")
	public String mesGubunCateI(HttpServletRequest request
			, @ModelAttribute("mesGubunCateVO") MesGubunCateVO mesGubunCateVO
			, RedirectAttributes redirectAttributes
			, ModelMap model) throws Exception {
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesGubunCateVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
		
		mesGubunCateService.insertMesGubunCate(mesGubunCateVO);
		
		redirectAttributes.addFlashAttribute("mesGubunCateVO", mesGubunCateVO);
																			
		return "redirect:/mes/gubun/gubunCate/kw_gubunCate_lf.do";
	}
	
	@RequestMapping(value = "/mes/gubun/gubunCate/kw_gubunCate_uf.do")
	public String mesGubunCateUf(HttpServletRequest request
			, @ModelAttribute("mesGubunCateVO") MesGubunCateVO mesGubunCateVO
			, RedirectAttributes redirectAttributes
			, ModelMap model) throws Exception {
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");	
		model.addAttribute("staffVo", staffVo);
		
		MesGubunCateVO cateInfo = mesGubunCateService.selectMesGubunCateInfo(mesGubunCateVO);
		model.addAttribute("cateInfo", cateInfo);
		
		redirectAttributes.addFlashAttribute("mesGubunCateVO", mesGubunCateVO);

		return "mes/gubun/gubunCate/kw_gubunCate_uf.tiles";
	}
	
	@RequestMapping(value = "/mes/gubun/gubunCate/kw_gubunCate_d.do")
	public String mesGubunCateD(HttpServletRequest request
			, @ModelAttribute("mesGubunVO") MesGubunCateVO mesGubunCateVO
			, RedirectAttributes redirectAttributes
			, ModelMap model) throws Exception {
		
		mesGubunCateService.deleteMesGubunCate(mesGubunCateVO);
		
		redirectAttributes.addFlashAttribute("mesGubunCateVO", mesGubunCateVO);
		
		return "redirect:/mes/gubun/gubunCate/kw_gubunCate_lf.do";
	}
	
	@RequestMapping(value = "/mes/gubun/gubunCate/kw_gubunCate_u.do")
	public String mesGubunCateU(HttpServletRequest request
			, @ModelAttribute("mesGubunCateVO") MesGubunCateVO mesGubunCateVO
			, RedirectAttributes redirectAttributes
			, ModelMap model) throws Exception {
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");	
		mesGubunCateVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
		
		mesGubunCateService.updateMesGubunCate(mesGubunCateVO);
		
		redirectAttributes.addFlashAttribute("mesGubunCateVO", mesGubunCateVO);
		
		return "redirect:/mes/gubun/gubunCate/kw_gubunCate_lf.do";
	}
}
