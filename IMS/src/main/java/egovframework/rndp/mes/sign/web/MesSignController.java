package egovframework.rndp.mes.sign.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.rndp.com.utl.JsonHelper;
import egovframework.rndp.mes.login.service.MesK_StaffVo;
import egovframework.rndp.mes.sign.service.MesSignService;
import egovframework.rndp.mes.sign.service.MesSignVO;

@Controller
public class MesSignController {

	/** intraSignService */
	@Resource(name = "mesSignService")
	private MesSignService mesSignService;

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;

	
	
	//결재자 팝업창
	@RequestMapping(value = "/mes/sign/popup/kw_signStaff_lf.do")
	public String mesSignStaffLf(HttpServletRequest request
			, @ModelAttribute("mesSignVO") MesSignVO mesSignVO
			, HttpServletResponse response
			, RedirectAttributes redirectAttributes
			, ModelMap model) throws Exception {

		String sessionToken = (String) request.getSession().getAttribute("csrfToken");
        String requestToken = request.getParameter("csrfToken");

        if (sessionToken == null || !sessionToken.equals(requestToken)) {
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesSignVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));

		model.addAttribute("staffVO", staffVO);
	
		model.addAttribute("positionList", mesSignService.selectPositionList(mesSignVO));

		model.addAttribute("list", mesSignService.selectStaffList(mesSignVO));
		model.addAttribute("chList", mesSignService.choiceStaffListSe(mesSignVO));
		model.addAttribute("pathList", mesSignService.selectPathList(mesSignVO));
		
		redirectAttributes.addFlashAttribute("mesSignVO", mesSignVO);

		return "mesPopup/mes/sign/popup/kw_signStaff_lf";
	}

	
	@RequestMapping(value = "/mes/sign/kw_getPositoinAjax.do")
	public void kwReferPosition(HttpServletRequest request
			, @ModelAttribute("mesSignVO") MesSignVO mesSignVO
			, RedirectAttributes redirectAttributes
			, HttpServletResponse response
			, ModelMap model) throws Exception {

		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesSignVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));

		List tt = mesSignService.selectStaffList(mesSignVO);

		JsonHelper helper = new JsonHelper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tt", tt);
		helper.printJsonObject(response, "haha", map);
	}

	@RequestMapping(value = "/mes/sign/kw_reCir_add.do")
	public void mesSignReCirAdd(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("mesSignVO") MesSignVO mesSignVO) throws Exception {
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesSignVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));

		mesSignVO.setsSingPathKey(request.getParameter("sSingPathKey"));
		mesSignVO.setsSingPathSubject(request.getParameter("sSingPathSubject"));
		mesSignVO.setsSingPathGubun(request.getParameter("sSingPathGubun"));
		mesSignService.signSubInsertPath(mesSignVO);
		
	}
	
	@RequestMapping(value = "/mes/sign/kw_sign_select_path.do")
	public void mesSignSelectPath(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("mesSignVO") MesSignVO mesSignVO) throws Exception {
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesSignVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
		mesSignVO.setsSingPathNum(request.getParameter("sSingPathNum"));
		List tt = mesSignService.seleteSignSelectPath(mesSignVO);

		JsonHelper helper = new JsonHelper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tt", tt);
		helper.printJsonObject(response, "haha", map);
	}
	
	@RequestMapping(value = "/mes/sign/kw_sign_delete_path.do")
	public void mesSignDeletePath(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("mesSignVO") MesSignVO mesSignVO) throws Exception {
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesSignVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));

		mesSignVO.setsSingPathNum(request.getParameter("sSingPathNum"));
		mesSignService.signDeletePath(mesSignVO);

		JsonHelper helper = new JsonHelper();
		Map<String, Object> map = new HashMap<String, Object>();
		helper.printJsonObject(response, "haha", map);
	}

	@RequestMapping(value = "/mes/sign/kw_uploadSignImgAjax.do")
	public void kwUploadSignImgAjax(HttpServletRequest request
			, @ModelAttribute("mesSignVO") MesSignVO mesSignVO
			, RedirectAttributes redirectAttributes
			, HttpServletResponse response
			, ModelMap model) throws Exception {

		mesSignService.kwUploadSignImgAjax(mesSignVO);

		JsonHelper helper = new JsonHelper();
		Map<String,Object> map = new HashMap<String,Object>();
		helper.printJsonObject(response, "result", map);
	}
}
