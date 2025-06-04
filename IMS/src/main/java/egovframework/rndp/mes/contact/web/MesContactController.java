package egovframework.rndp.mes.contact.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.rndp.mes.common.service.MesCommonService;
import egovframework.rndp.mes.contact.service.MesContactService;
import egovframework.rndp.mes.contact.service.MesContactVO;
import egovframework.rndp.mes.gubun.service.MesGubunVO;
import egovframework.rndp.mes.login.service.MesK_StaffVo;
import egovframework.rndp.mes.maintance.service.MesMaintanceVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class MesContactController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MesContactController.class);
	
	@Resource(name = "mesContactService")
	private MesContactService mesContactService;
	/*공통사용 서비스 common 및  Egov 공용 */
	@Resource(name = "mesCommonService")
	private MesCommonService mesCommonService;
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;
	@Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;
	/*공통사용 서비스 common 및  Egov 공용 */

	@RequestMapping(value = "/mes/contact/kw_contact_lf.do")
	public String mesContactLf(HttpServletRequest request
			, @ModelAttribute("mesContactVO") MesContactVO mesContactVO
			, RedirectAttributes redirectAttributes
			, ModelMap model) throws Exception {
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");	
		model.addAttribute("staffVo", staffVo);
		
		/* paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesContactVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesContactVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesContactVO.getPageSize());
		
		mesContactVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesContactVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesContactVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List infoList = mesContactService.mesContactInfoList(mesContactVO);
		model.addAttribute("infoList", infoList );
		
		int totCnt = mesContactService.mesContactInfoListCnt(mesContactVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("paginationInfo", paginationInfo);
		
		redirectAttributes.addFlashAttribute("mesContactVO", mesContactVO);
		
		return "mes/contact/kw_contact_lf.tiles";
	}
	
	@RequestMapping(value = "/mes/contact/kw_contact_if.do")
	public String mesContactIf(HttpServletRequest request
			, @ModelAttribute("mesContactVO") MesContactVO mesContactVO
			, RedirectAttributes redirectAttributes
			, ModelMap model) throws Exception {
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");	
		model.addAttribute("staffVo", staffVo);
		
		
		redirectAttributes.addFlashAttribute("mesContactVO", mesContactVO);
		
		return "mes/contact/kw_contact_if.tiles";
	}

	
	
	@RequestMapping(value = "/mes/contact/kw_contact_i.do")
	public String mesContactI(HttpServletRequest request
			, @ModelAttribute("mesContactVO") MesContactVO mesContactVO
			, RedirectAttributes redirectAttributes
			, ModelMap model) throws Exception {
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesContactVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
		mesContactService.mesContactInsert(mesContactVO);
		redirectAttributes.addFlashAttribute("mesContactVO", mesContactVO);
	
		return "redirect:/mes/contact/kw_contact_lf.do";
	}
	
	
	@RequestMapping(value = "/mes/contact/kw_contact_uf.do")
	public String mesGubunUf(HttpServletRequest request
			, @ModelAttribute("mesContactVO") MesContactVO mesContactVO
			, RedirectAttributes redirectAttributes
			, ModelMap model) throws Exception {
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");	
		model.addAttribute("staffVo", staffVo);
		
		MesContactVO info = mesContactService.mesContactInfo(mesContactVO);
		model.addAttribute("info", info );
		
		redirectAttributes.addFlashAttribute("mesContactVO", mesContactVO);
		
		return "mes/contact/kw_contact_uf.tiles";
	}
	
	@RequestMapping(value = "/mes/contact/kw_contact_u.do")
	public String mesContactU(HttpServletRequest request
			, @ModelAttribute("mesContactVO") MesContactVO mesContactVO
			, RedirectAttributes redirectAttributes
			, ModelMap model) throws Exception {
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesContactVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
		mesContactService.mesContactUpdate(mesContactVO);
		redirectAttributes.addFlashAttribute("mesContactVO", mesContactVO);
	
		return "redirect:/mes/contact/kw_contact_lf.do";
	}
	@RequestMapping(value = "/mes/contact/kw_contact_d.do")
	public String mesContactD(HttpServletRequest request
			, @ModelAttribute("mesContactVO") MesContactVO mesContactVO
			, RedirectAttributes redirectAttributes
			, ModelMap model) throws Exception {
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesContactVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
		
		mesContactService.mesContactDelete(mesContactVO);
		
		redirectAttributes.addFlashAttribute("mesContactVO", mesContactVO);
		
		return "redirect:/mes/contact/kw_contact_lf.do";
	}
	

	@RequestMapping(value = "/mes/contact/kw_info_list.do")
	public String mesContactInfoPopup(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesContactVO") MesContactVO mesContactVO
			, ModelMap model) throws Exception {
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo);
		
		/* paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesContactVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesContactVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesContactVO.getPageSize());

		mesContactVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesContactVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesContactVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		 
		List infoList = mesContactService.mesContactInfoPopupList(mesContactVO);
		model.addAttribute("infoList", infoList);
		
		int totCnt = mesContactService.mesContactInfoPopupListCnt(mesContactVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("paginationInfo", paginationInfo);
		

		redirectAttributes.addFlashAttribute("mesContactVO", mesContactVO);
		 
		return "mesPopup/mes/contact/popup/kw_info_list";
	}
	
}
