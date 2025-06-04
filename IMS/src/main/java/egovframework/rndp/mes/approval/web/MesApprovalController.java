
package egovframework.rndp.mes.approval.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.utl.sim.service.EgovNetworkState;
import egovframework.rndp.com.cmm.service.EgovProperties;
import egovframework.rndp.com.utl.JsonHelper;
import egovframework.rndp.mes.approval.service.MesApprovalService;
import egovframework.rndp.mes.approval.service.MesApprovalVO;
import egovframework.rndp.mes.asset.service.MesAssetService;
import egovframework.rndp.mes.asset.service.MesAssetVO;
import egovframework.rndp.mes.bluprint.service.MesBlueprintVO;
import egovframework.rndp.mes.gubun.service.MesGubunService;
import egovframework.rndp.mes.gubun.service.MesGubunVO;
import egovframework.rndp.mes.inspection.service.MesInspectionService;
import egovframework.rndp.mes.inspection.service.MesInspectionVO;
import egovframework.rndp.mes.issue.service.MesIssueVO;
import egovframework.rndp.mes.login.service.MesK_StaffVo;
import egovframework.rndp.mes.maintance.service.MesMaintanceVO;
import egovframework.rndp.mes.position.service.MesPositionVO;
import egovframework.rndp.mes.sign.service.MesSignService;
import egovframework.rndp.mes.sign.service.MesSignVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import net.sf.jxls.transformer.XLSTransformer;

@Controller
public class MesApprovalController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MesApprovalController.class);

	/** EgovSampleService */
	@Resource(name = "mesApprovalService")
	private MesApprovalService mesApprovalService;
	
	@Resource(name = "mesSignService")
	private MesSignService mesSignService;
	
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;
	
	@Resource(name = "mesGubunService")
	private MesGubunService mesGubunService;
	
	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	
	
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;
	
	
 
	@RequestMapping(value = "/mes/approval/kw_eApproval_lf.do")
	public String eApprovalList(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesApprovalVO") MesApprovalVO mesApprovalVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		mesApprovalVO.seteStaffKey(Integer.toString(staffVO.getkStaffKey()));
		// paging
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesApprovalVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesApprovalVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesApprovalVO.getLastIndex());
		
		mesApprovalVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesApprovalVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesApprovalVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());


		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesApprovalVO.geteTopStartDate().equals("") || mesApprovalVO.geteTopStartDate() == null){
			String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
			mesApprovalVO.seteTopStartDate(temp);
		}
		if(mesApprovalVO.geteTopEndDate().equals("") || mesApprovalVO.geteTopEndDate() == null){
			mesApprovalVO.seteTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		List nameList = mesApprovalService.mesApprovalNameList(mesApprovalVO);
		model.addAttribute("nameList", nameList );
		
		List infoList = mesApprovalService.mesApprovalList(mesApprovalVO);
		int totCnt = mesApprovalService.mesApprovalListCnt(mesApprovalVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("infoList", infoList);
		model.addAttribute("paginationInfo", paginationInfo);	
		
		
		return "mes/approval/kw_eApproval_lf.tiles";
	}
	 
}
