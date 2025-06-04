package egovframework.rndp.mes.position.web;

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
import egovframework.rndp.mes.position.service.MesPositionService;
import egovframework.rndp.mes.position.service.MesPositionVO;
import egovframework.rndp.mes.login.service.MesK_StaffVo;

@Controller
public class MesPositionController {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MesPositionController.class);

	@Resource(name = "mesPositionService")
	private MesPositionService mesPositionService;
	
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;
	
	@Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;
	
	//부서관리
	@RequestMapping(value = "/mes/position/kw_position_lf.do")
	public String mesPositionPositionUf(HttpServletRequest request
		, @ModelAttribute("mesPositionVO") MesPositionVO mesPositionVO
		, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesPositionVO.setkStaffKey(Integer.toString(staffVo.getkStaffKey()));
		mesPositionVO.setkStaffName(staffVo.getkStaffName());
		model.addAttribute("staffVo", staffVo);
		
		String maxKey = mesPositionService.getMaxPositionKey(mesPositionVO);
		model.addAttribute("maxKey", maxKey);
		
		List positionList = mesPositionService.selectPositionList(mesPositionVO);
		model.addAttribute("positionList", positionList);
		
		return "mes/position/kw_position_lf.tiles";
	}
	
	@RequestMapping(value = "/mes/position/kw_savePositionAjax.do")
	public void intraSavePosition(HttpServletRequest request
		, @ModelAttribute("mesPositionVO") MesPositionVO mesPositionVO
		, ModelMap model, RedirectAttributes redirectAttributes
		, HttpServletResponse response) throws Exception {

		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesPositionVO.setkStaffKey(Integer.toString(staffVo.getkStaffKey()));
		mesPositionVO.setkStaffName(staffVo.getkStaffName());
		model.addAttribute("staffVo", staffVo);

		String key = mesPositionService.savePositionAjax(mesPositionVO);
		
		String path = mesPositionService.getPositionPath(mesPositionVO);
		
		JsonHelper helper = new JsonHelper();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("key", key);
		map.put("path", path);
		helper.printJsonObject(response, "result", map);
	}
	
	@RequestMapping(value = "/mes/position/kw_deletePositionAjax.do")
	public void intraDeletePosition(HttpServletRequest request
		, @ModelAttribute("mesPositionVO") MesPositionVO mesPositionVO
		, ModelMap model, RedirectAttributes redirectAttributes
		, HttpServletResponse response) throws Exception {

		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesPositionVO.setkStaffKey(Integer.toString(staffVo.getkStaffKey()));
		mesPositionVO.setkStaffName(staffVo.getkStaffName());
		
		String result = "success";
		
		mesPositionService.deletePositionAjax(mesPositionVO);
		
		JsonHelper helper = new JsonHelper();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", result);
		helper.printJsonObject(response, "result", map);
	}
}




