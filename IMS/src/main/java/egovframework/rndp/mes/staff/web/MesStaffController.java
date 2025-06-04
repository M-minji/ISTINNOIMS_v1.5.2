package egovframework.rndp.mes.staff.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.rndp.mes.common.service.MesCommonService;
import egovframework.rndp.mes.staff.service.MesStaffService;
import egovframework.rndp.mes.staff.service.MesStaffVO;


/**
 * MES 직원 
 * @author rndp-21
 *
 */
@Controller
public class MesStaffController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MesStaffController.class);
	
	/** EgovSampleService */
	@Resource(name = "mesStaffService")
	private MesStaffService mesStaffService;

	/*공통사용 서비스 common 및  Egov 공용 */
	@Resource(name = "mesCommonService")
	private MesCommonService mesCommonService;
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;
	@Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;
	/*공통사용 서비스 common 및  Egov 공용 */
	
	@RequestMapping(value="/mes/staff/kw_staff.do")
	public String intraSignKwStaff(HttpServletRequest request
			, @ModelAttribute("kSignVo") MesStaffVO kSignVo
			, ModelMap model ) throws Exception {
		
		model.addAttribute("positionList", mesCommonService.selectPositionList());
		
		MesStaffVO kStaffVo = new MesStaffVO();
		if(kSignVo.getkPositionKey() != null
		&& !"".equals(kSignVo.getkPositionKey())){
			kStaffVo.setkPositionKey(Integer.parseInt(kSignVo.getkPositionKey())+"");
		} 
		kStaffVo.setkStaffStateFlag("1");
		
		model.addAttribute("staffListByPos", mesStaffService.selectStaffListByPos(kSignVo));
		
		return "mesPopup/mes/staff/kw_staff";
	}

}
