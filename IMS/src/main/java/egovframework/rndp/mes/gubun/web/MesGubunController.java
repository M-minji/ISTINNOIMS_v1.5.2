package egovframework.rndp.mes.gubun.web;

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

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.rndp.com.utl.JsonHelper;
import egovframework.rndp.mes.common.service.MesCommonService;
import egovframework.rndp.mes.gubun.service.MesGubunService;
import egovframework.rndp.mes.gubun.service.MesGubunVO;
import egovframework.rndp.mes.login.service.MesK_StaffVo;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class MesGubunController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MesGubunController.class);
	
	@Resource(name = "mesGubunService")
	private MesGubunService mesGubunService;
	/*공통사용 서비스 common 및  Egov 공용 */
	@Resource(name = "mesCommonService")
	private MesCommonService mesCommonService;
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;
	@Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;
	/*공통사용 서비스 common 및  Egov 공용 */
	
	// 안상현
	@RequestMapping(value = "/mes/gubun/kw_gubun_if.do")
	public String mesGubunIf(HttpServletRequest request
			, @ModelAttribute("mesGubunVO") MesGubunVO mesGubunVO
			, RedirectAttributes redirectAttributes
			, ModelMap model) throws Exception {
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");	
		model.addAttribute("staffVo", staffVo);
		
		model.addAttribute("gubunCateList", mesGubunService.selectMesGubunCateList());
		
		redirectAttributes.addFlashAttribute("mesGubunVO", mesGubunVO);
		
		return "mes/gubun/kw_gubun_if.tiles";
	}
	
	@RequestMapping(value = "/mes/gubun/kw_gubun_i.do")
	public String mesGubunI(HttpServletRequest request
			, @ModelAttribute("mesGubunVO") MesGubunVO mesGubunVO
			, RedirectAttributes redirectAttributes
			, ModelMap model) throws Exception {
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesGubunVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
		redirectAttributes.addFlashAttribute("staffVo", staffVo);
		
		mesGubunService.insertMesGubun(mesGubunVO);
		
		redirectAttributes.addFlashAttribute("mesGubunVO", mesGubunVO);
	
		return "redirect:/mes/gubun/kw_gubun_lf.do";
	}
	
	@RequestMapping(value = "/mes/gubun/kw_gubun_lf.do")
	public String mesGubunLf(HttpServletRequest request
			, @ModelAttribute("mesGubunVO") MesGubunVO mesGubunVO
			, RedirectAttributes redirectAttributes
			, ModelMap model) throws Exception {
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");	
		model.addAttribute("staffVo", staffVo);
		
		/* paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesGubunVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesGubunVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesGubunVO.getPageSize());

		mesGubunVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesGubunVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesGubunVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List gubunList = mesGubunService.selectMesGubunList(mesGubunVO);
		model.addAttribute("gubunList", gubunList);
		
		int totCnt = mesGubunService.selectMesGubunListCnt(mesGubunVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		model.addAttribute("gubunCateList", mesGubunService.selectMesGubunCateList());
		
		redirectAttributes.addFlashAttribute("mesGubunVO", mesGubunVO);
		
		return "mes/gubun/kw_gubun_lf.tiles";
	}
	
	@RequestMapping(value = "/mes/gubun/kw_gubun_uf.do")
	public String mesGubunUf(HttpServletRequest request
			, @ModelAttribute("mesGubunVO") MesGubunVO mesGubunVO
			, RedirectAttributes redirectAttributes
			, ModelMap model) throws Exception {
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");	
		model.addAttribute("staffVo", staffVo);
		
		MesGubunVO gubunInfo = mesGubunService.selectMesGubunInfo(mesGubunVO);
		model.addAttribute("gubunInfo", gubunInfo);
		
		model.addAttribute("gubunCateList", mesGubunService.selectMesGubunCateList());
		
		redirectAttributes.addFlashAttribute("mesGubunVO", mesGubunVO);
		
		return "mes/gubun/kw_gubun_uf.tiles";
	}
	
	@RequestMapping(value = "/mes/gubun/kw_gubun_d.do")
	public String mesGubunD(HttpServletRequest request
			, @ModelAttribute("mesGubunVO") MesGubunVO mesGubunVO
			, RedirectAttributes redirectAttributes
			, ModelMap model) throws Exception {
		
		mesGubunService.deleteMesGubun(mesGubunVO);
		
		redirectAttributes.addFlashAttribute("mesGubunVO", mesGubunVO);
		
		return "redirect:/mes/gubun/kw_gubun_lf.do";
	}
	
	@RequestMapping(value = "/mes/gubun/kw_gubun_u.do")
	public String mesGubunU(HttpServletRequest request
			, @ModelAttribute("mesGubunVO") MesGubunVO mesGubunVO
			, RedirectAttributes redirectAttributes
			, ModelMap model) throws Exception {
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesGubunVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
		
		mesGubunService.updateMesSGubun(mesGubunVO);
		
		redirectAttributes.addFlashAttribute("mesGubunVO", mesGubunVO);
		
		return "redirect:/mes/gubun/kw_gubun_lf.do";
	}
}
