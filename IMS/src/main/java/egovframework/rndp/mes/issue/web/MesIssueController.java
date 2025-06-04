package egovframework.rndp.mes.issue.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

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
import egovframework.rndp.admin.env.service.EnvService;
import egovframework.rndp.com.cmm.service.EgovProperties;
import egovframework.rndp.com.utl.JsonHelper;
import egovframework.rndp.mes.asset.service.MesAssetVO;
import egovframework.rndp.mes.common.service.MesCommonService;
import egovframework.rndp.mes.gubun.service.MesGubunService;
import egovframework.rndp.mes.gubun.service.MesGubunVO;
import egovframework.rndp.mes.inspection.service.MesInspectionVO;
import egovframework.rndp.mes.issue.service.MesIssueService;
import egovframework.rndp.mes.issue.service.MesIssueVO;
import egovframework.rndp.mes.login.service.MesK_StaffVo;
import egovframework.rndp.mes.maintance.service.MesMaintanceService;
import egovframework.rndp.mes.maintance.service.MesMaintanceVO;
import egovframework.rndp.mes.position.service.MesPositionService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import net.sf.jxls.transformer.XLSTransformer;
import egovframework.rndp.mes.position.service.MesPositionVO;

import egovframework.rndp.mes.sign.service.MesSignService;
import egovframework.rndp.mes.sign.service.MesSignVO;

@Controller
public class MesIssueController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MesIssueController.class);

	/*공통사용 서비스 common 및  Egov 공용 */
	@Resource(name = "mesCommonService")
	private MesCommonService mesCommonService;
	
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;
	
	@Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;
	/*공통사용 서비스 common 및  Egov 공용 */
	
	@Resource(name = "mesIssueService")
	private MesIssueService mesIssueService;

	@Resource(name = "mesPositionService")
	private MesPositionService mesPositionService;

	@Resource(name = "mesSignService")
	private MesSignService mesSignService;
	
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;
	
	@Resource(name = "envService")
	private EnvService envService;
	
	@Resource(name = "mesGubunService")
	private MesGubunService mesGubunService;
		
	@RequestMapping(value = "/mes/issue/kw_issue_lf.do")
	public String mesIssueLf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesIssueVO") MesIssueVO mesIssueVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVo);
		mesIssueVO.setkStaffKey(Integer.toString(staffVo.getkStaffKey()));
		/* paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesIssueVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesIssueVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesIssueVO.getPageSize());

		mesIssueVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesIssueVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesIssueVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesIssueVO.getTopStartDate().equals("") || mesIssueVO.getTopStartDate() == null){
//			String temp = String.valueOf(dateFormat.format(nowDate));
//			temp = temp.substring(0,4);
//			temp += "0101";
			String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
			mesIssueVO.setTopStartDate(temp);
		}
		if(mesIssueVO.getTopEndDate().equals("") || mesIssueVO.getTopEndDate() == null){
			mesIssueVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		
		List<MesIssueVO> infoList = mesIssueService.mesIssueInfoList(mesIssueVO);

		model.addAttribute("infoList", infoList);
		
		int totCnt = mesIssueService.mesIssueInfoListCnt(mesIssueVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("paginationInfo", paginationInfo);
		
		
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("33");
		model.addAttribute("gubun33List", mesGubunService.selectMesGubunCodeList(vo));
		 
		vo.setsGubunCateKey("34"); //장애
		model.addAttribute("gubun34List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		
		redirectAttributes.addFlashAttribute("mesIssueVO", mesIssueVO);
		
		return "mes/issue/kw_issue_lf.tiles";
	}
	

	@RequestMapping(value = "/mes/issue/kw_issueExcelListDwonload.do")
	public void issueExcelListDwonload(
			  HttpServletRequest request
			, HttpServletResponse response
			, @ModelAttribute("mesIssueVO") MesIssueVO mesIssueVO
			, RedirectAttributes redirectAttributes
			, ModelMap card) throws Exception {
		
		  Map<String, List> beans = new HashMap<String, List>();
			
		    Date nowDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			if(mesIssueVO.getTopStartDate().equals("") || mesIssueVO.getTopStartDate() == null){
				String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
				mesIssueVO.setTopStartDate(temp);
			}
			if(mesIssueVO.getTopEndDate().equals("") || mesIssueVO.getTopEndDate() == null){
				mesIssueVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
			}
			
			List infoList = mesIssueService.mesIssueExcelInfoList(mesIssueVO);
			
			// 처리자
			ArrayList<MesIssueVO> listForHandler = new ArrayList<>(infoList);
			for(MesIssueVO info : listForHandler) {
				List eHandlerList = mesIssueService.eIssueHandlerInfoList(info);
				if(eHandlerList != null && eHandlerList.size() > 1) {
					String handler = String.format("%s 외 %d명", info.geteHandler(), eHandlerList.size() - 1);
					info.seteHandler(handler);
				}
			}
			
			// 장비
			for(MesIssueVO info : listForHandler) {
				List assetselect = mesIssueService.eSelectIssueInfoAssetList(info);
				ArrayList<MesIssueVO> assetList = new ArrayList<>(assetselect);
				if(assetList != null && assetList.size() > 1) {
					MesIssueVO assetVo = assetList.get(0);
					String asset = String.format("%s 외 %d개", assetVo.getaAssetName(), assetList.size() - 1);
					info.setaAssetName(asset);
				}
			}
			
			ArrayList<String> list = new ArrayList<>(infoList);
			beans.put("list", list);
	
			String Specification =  "issueExcelList.xls";
		    String templatePath = EgovProperties.getProperty("salesExcelTemplatePath");		//cms.properties에서 가져오는 파일 경로
			String templateFileName = templatePath + Specification;
			
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ("yyyyMMdd", Locale.KOREA);
			Date currentTime = new Date ();
			String mTime = mSimpleDateFormat.format ( currentTime );
			String titleName = "장애관리_현황_";
		    String destFileName = titleName + mTime + ".xls";
		    response.setContentType("application/vnd.ms-excel");
		    response.setHeader("Content-Disposition", "attachment; filename="+ java.net.URLEncoder.encode(destFileName, "UTF-8") + ";");
		  
		    try {
		    	   
	          XLSTransformer transformer = new XLSTransformer();           
	          Workbook resultWorkbook = transformer.transformXLS(new FileInputStream(templateFileName), beans);            
	          OutputStream os = response.getOutputStream();
	          
	          resultWorkbook.write(os);
	      } catch (IOException e) {
	          e.printStackTrace();
	      }
	}
	
	

	@RequestMapping(value = "/mes/issue/kw_issue_lfr.do")
	public String mesIssueLfr(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesIssueVO") MesIssueVO mesIssueVO
			, ModelMap model) throws Exception{
		mesIssueService.mesSignIssueStatus(mesIssueVO);	
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVo);
		
		/* paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesIssueVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesIssueVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesIssueVO.getPageSize());

		mesIssueVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesIssueVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesIssueVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesIssueVO.getTopStartDate().equals("") || mesIssueVO.getTopStartDate() == null){
			String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
			mesIssueVO.setTopStartDate(temp);
		}
		if(mesIssueVO.getTopEndDate().equals("") || mesIssueVO.getTopEndDate() == null){
			mesIssueVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		
		List infoList = mesIssueService.mesIssueInfoList(mesIssueVO);
		model.addAttribute("infoList", infoList);
		
		int totCnt = mesIssueService.mesIssueInfoListCnt(mesIssueVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("paginationInfo", paginationInfo);
		
		
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("33");
		model.addAttribute("gubun33List", mesGubunService.selectMesGubunCodeList(vo));
		 
		vo.setsGubunCateKey("34"); //장애
		model.addAttribute("gubun34List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		
		redirectAttributes.addFlashAttribute("mesIssueVO", mesIssueVO);
		
		return "mes/issue/kw_issue_lf.tiles";
	}
	
	
	
	@RequestMapping(value = "/mes/issue/kw_issue_if.do")
	public String mesIssueIf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesIssueVO") MesIssueVO mesIssueVO
			, ModelMap model) throws Exception{
		
		String csrfToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("csrfToken", csrfToken);
		
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesIssueVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
		mesIssueVO.setkStaffName(String.valueOf(staffVo.getkStaffName()));
		model.addAttribute("staffVo", staffVo);
		
		
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("33"); //유형
		model.addAttribute("gubun33List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("34"); //장애
		model.addAttribute("gubun34List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		redirectAttributes.addFlashAttribute("mesIssueVO", mesIssueVO);
		
		return "mes/issue/kw_issue_if.tiles";
	}
	
	
	@RequestMapping(value = "/mes/issue/kw_issue_i.do")
	public String mesIssueI(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			,  @ModelAttribute("mesIssueVO") MesIssueVO mesIssueVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesIssueVO.setkStaffKey(Integer.toString(staffVo.getkStaffKey()));
		mesIssueVO.setkStaffName(staffVo.getkStaffName());
		
		mesIssueService.insertIssueInfo(mesIssueVO);		
		
		redirectAttributes.addFlashAttribute("mesIssueVO", mesIssueVO);
		
		return "redirect:/mes/issue/kw_issue_lf.do";
	}
	
	
	@RequestMapping(value = "/mes/issue/kw_issue_vf.do")
	public String mesIssueViewPage(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			,  @ModelAttribute("mesIssueVO") MesIssueVO mesIssueVO
			, ModelMap model) throws Exception{
		
		String csrfToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("csrfToken", csrfToken);
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesIssueVO.setkStaffKey(Integer.toString(staffVo.getkStaffKey()));
		mesIssueVO.setkStaffName(staffVo.getkStaffName());
		model.addAttribute("staffVO", staffVo);
		MesIssueVO issueInfo = mesIssueService.eSelectIssueInfo(mesIssueVO);		
		model.addAttribute("issueInfo", issueInfo);
		List assetList = mesIssueService.eSelectIssueInfoAssetList(mesIssueVO);
		model.addAttribute("assetList", assetList);
		
		// 파일
		List eFileInfoList = mesIssueService.eFileInfoList(mesIssueVO);
		model.addAttribute("eFileInfoList", eFileInfoList);
		
		
		List eHandlerList = mesIssueService.eIssueHandlerInfoList(mesIssueVO);
		model.addAttribute("eHandlerList", eHandlerList);
		
		MesSignVO mesSignVO = new MesSignVO();
		mesSignVO.setsSignTableKey(mesIssueVO.geteIssueKey());
		mesSignVO.setsSignTableName("I_ISSUE");
		
		List signList = mesSignService.selectSignListTwo(mesSignVO);
		model.addAttribute("signList", signList);
		
		
		redirectAttributes.addFlashAttribute("mesIssueVO", mesIssueVO);
		return "mes/issue/kw_issue_vf.tiles";
	}
	@RequestMapping(value = "/mes/issue/kw_issue_vfr.do")
	public String mesIssueViewRPage(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			,  @ModelAttribute("mesIssueVO") MesIssueVO mesIssueVO
			, ModelMap model) throws Exception{
		mesIssueService.mesSignIssueStatus(mesIssueVO);	
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesIssueVO.setkStaffKey(Integer.toString(staffVo.getkStaffKey()));
		mesIssueVO.setkStaffName(staffVo.getkStaffName());
		model.addAttribute("staffVO", staffVo);
		MesIssueVO issueInfo = mesIssueService.eSelectIssueInfo(mesIssueVO);		
		model.addAttribute("issueInfo", issueInfo);
		List assetList = mesIssueService.eSelectIssueInfoAssetList(mesIssueVO);
		model.addAttribute("assetList", assetList);

		List eHandlerList = mesIssueService.eIssueHandlerInfoList(mesIssueVO);
		model.addAttribute("eHandlerList", eHandlerList);
		
		MesSignVO mesSignVO = new MesSignVO();
		mesSignVO.setsSignTableKey(mesIssueVO.geteIssueKey());
		mesSignVO.setsSignTableName("I_ISSUE");
		
		List signList = mesSignService.selectSignListTwo(mesSignVO);
		model.addAttribute("signList", signList);
		
		
		redirectAttributes.addFlashAttribute("mesIssueVO", mesIssueVO);
		return "mes/issue/kw_issue_vf.tiles";
	}
	
	
	@RequestMapping(value = "/mes/issue/kw_issue_uf.do")
	public String mesIssueUpdatepage(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			,  @ModelAttribute("mesIssueVO") MesIssueVO mesIssueVO
			, ModelMap model) throws Exception{
		String csrfToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("csrfToken", csrfToken);
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesIssueVO.setkStaffKey(Integer.toString(staffVo.getkStaffKey()));
		mesIssueVO.setkStaffName(staffVo.getkStaffName());
		model.addAttribute("staffVo", staffVo);
		
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("33"); //유형
		model.addAttribute("gubun33List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("34"); //장애
		model.addAttribute("gubun34List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		MesIssueVO issueInfo = mesIssueService.eSelectIssueInfo(mesIssueVO);		
		model.addAttribute("issueInfo", issueInfo);
		List assetList = mesIssueService.eSelectIssueInfoAssetList(mesIssueVO);
		model.addAttribute("assetList", assetList);

		List eHandlerList = mesIssueService.eIssueHandlerInfoList(mesIssueVO);
		model.addAttribute("eHandlerList", eHandlerList);
		
		List eFileInfoList = mesIssueService.eFileInfoList(mesIssueVO);
		model.addAttribute("eFileInfoList", eFileInfoList);
		
		

		MesSignVO mesSignVO = new MesSignVO();
		mesSignVO.setsSignTableKey(mesIssueVO.geteIssueKey());
		mesSignVO.setsSignTableName("I_ISSUE");
		
		List signList = mesSignService.selectSignListTwo(mesSignVO);
		model.addAttribute("signList", signList);
		
		
		redirectAttributes.addFlashAttribute("mesIssueVO", mesIssueVO);
		return "mes/issue/kw_issue_uf.tiles";
	}

	@RequestMapping(value = "/mes/issue/kw_issue_u.do")
	public String mesIssueUpdate(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			,  @ModelAttribute("mesIssueVO") MesIssueVO mesIssueVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesIssueVO.setkStaffKey(Integer.toString(staffVo.getkStaffKey()));
		mesIssueVO.setkStaffName(staffVo.getkStaffName());
		
		mesIssueService.updateIssueInfo(mesIssueVO);		
		
		redirectAttributes.addFlashAttribute("mesIssueVO", mesIssueVO);
		
		return "redirect:/mes/issue/kw_issue_lf.do";
	}
	@RequestMapping(value = "/mes/issue/kw_issue_d.do")
	public String mesIssueDelete(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			,  @ModelAttribute("mesIssueVO") MesIssueVO mesIssueVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesIssueVO.setkStaffKey(Integer.toString(staffVo.getkStaffKey()));
		mesIssueVO.setkStaffName(staffVo.getkStaffName());
		
		mesIssueService.deleteIssueInfo(mesIssueVO);		
		
		redirectAttributes.addFlashAttribute("mesIssueVO", mesIssueVO);
		
		return "redirect:/mes/issue/kw_issue_lf.do";
	}
	
	@RequestMapping(value = "/mes/issue/kw_process_uf.do")
	public String mesIssueViewProcessUpdate(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			,  @ModelAttribute("mesIssueVO") MesIssueVO mesIssueVO
			, ModelMap model) throws Exception{
		
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesIssueVO.setkStaffKey(Integer.toString(staffVo.getkStaffKey()));
		mesIssueVO.setkStaffName(staffVo.getkStaffName());
		
				
		mesIssueService.eIssueProcessUpdate(mesIssueVO);
		
		model.addAttribute("staffVo", staffVo);
		
		
		MesIssueVO issueInfo = mesIssueService.eSelectIssueInfo(mesIssueVO);		
		model.addAttribute("issueInfo", issueInfo);
		List assetList = mesIssueService.eSelectIssueInfoAssetList(mesIssueVO);
		model.addAttribute("assetList", assetList);
		
		redirectAttributes.addFlashAttribute("mesIssueVO", mesIssueVO);
		return "mes/issue/kw_issue_vf.tiles";
	}
	 
	@RequestMapping(value = "/mes/issue/kw_uploadSignIssueReasonAjax.do")
	public void mesAssetSignUpdate(HttpServletRequest request
			, @ModelAttribute("mesIssueVO") MesIssueVO mesIssueVO
			, RedirectAttributes redirectAttributes
			, HttpServletResponse response
			, ModelMap model) throws Exception {

		mesIssueService.mesUpdateSignStatus(mesIssueVO);

		JsonHelper helper = new JsonHelper();
		Map<String,Object> map = new HashMap<String,Object>();
		helper.printJsonObject(response, "result", map);
	}
	
	// 상세보기 엑셀 다운
	@RequestMapping(value = "/mes/issue/kw_issue_download.do")
	public void mesExcelDownload(
		  HttpServletRequest request
		, HttpServletResponse response
		, @ModelAttribute("mesIssueVO") MesIssueVO mesIssueVO
		, ModelMap model) throws Exception {
			
		
	    Map<String, List> beans = new HashMap<String, List>();

		MesIssueVO detailInfo = mesIssueService.eSelectIssueInfo(mesIssueVO);	
		
		List<MesIssueVO> detail = new ArrayList<>();
		detail.add(detailInfo);
		beans.put("issueInfo", detail);
		
		List handler = mesIssueService.eIssueHandlerInfoList(mesIssueVO);
		if(handler.size() == 0) {
			MesIssueVO vo = new MesIssueVO();
			vo.seteRowWorker("");
			handler.add(vo);
		}
		beans.put("handler", handler);	
		
		List assetList = mesIssueService.eSelectIssueInfoAssetList(mesIssueVO);
		if(assetList.size() == 0) {
			MesIssueVO vo = new MesIssueVO();
			vo.setaAssetType("");
			assetList.add(vo);
		}
		beans.put("asset", assetList);
		
		List eFileInfoList = mesIssueService.eFileInfoList(mesIssueVO);
		if(eFileInfoList.size() == 0) {
			MesIssueVO vo = new MesIssueVO();
			vo.seteFileName("첨부파일이 없습니다.");
			eFileInfoList.add(vo);
		}
		beans.put("file", eFileInfoList);
		
			
	    String templatePath = EgovProperties.getProperty("salesExcelTemplatePath");		//cms.properties에서 가져오는 파일 경로
		String Specification = "issueDetail.xlsx"; //가져올 엑셀 파일명과 확장자
		String templateFileName = templatePath + Specification; //최종 경로의 파일명
		
		//다운로드파일 날짜표기할때 사용.
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ("yyyyMMdd", Locale.KOREA);
		Date currentTime = new Date ();
		String mTime = mSimpleDateFormat.format ( currentTime );
	    
	
		String titleName = "장애관리_상세_";
	    String destFileName = titleName + mTime + ".xlsx"; //최종 파일 명
		// 브라우저가 이 응답을 엑셀 파일로 인식하도록 MIME 타입을 설정
	    response.setContentType("application/vnd.ms-excel");
		// 클라이언트로 전송할 파일의 이름과 인코딩 방식 설정
	    // "Content-Disposition" 헤더를 통해 브라우저가 파일을 다운로드하도록 지정
	    response.setHeader("Content-Disposition", "attachment; filename="+ java.net.URLEncoder.encode(destFileName, "UTF-8") + ";");
	  
	    try {
	    	// XLSTransformer 객체를 생성하여 템플릿 엑셀 파일과 데이터를 결합하는 역할 수행
	        XLSTransformer transformer = new XLSTransformer();           
			// transformXLS 메서드를 사용해 템플릿 파일(templateFileName)과 데이터(beans)를 결합하여 최종 엑셀 파일 생성
			// FileInputStream을 통해 템플릿 파일을 읽고, beans 맵에 주입된 데이터를 엑셀 템플릿에 적용
	        Workbook resultWorkbook = transformer.transformXLS(new FileInputStream(templateFileName), beans); 
			// 클라이언트로 데이터를 내보내기 위해 응답의 출력 스트림을 선언
	        OutputStream os = response.getOutputStream();
	        // 완성된 엑셀 데이터를 출력 스트림으로 작성
	        // 이 단계에서 클라이언트(사용자 브라우저)로 엑셀 파일이 전송됨
	        resultWorkbook.write(os);
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	
	// 첨부파일
	@RequestMapping(value = "/mes/issue/popup/kw_File_add.do")
	public String mesIMGregAddPopup(HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("mesIssueVO") MesIssueVO mesIssueVO, ModelMap model) throws Exception {
		 String pageFileGubun = "N";
		 if (!"N".equals(mesIssueVO.getePageGubun()) && !"Y".equals(mesIssueVO.getePageGubun())) {
//			 pageFileGubun = "N";
			 pageFileGubun = "Y";
	        }else {
//	        	pageFileGubun = mesIssueVO.getePageGubun();
	        	pageFileGubun = "Y";
			}
		 
		String sessionToken = (String) request.getSession().getAttribute("csrfToken");
        String requestToken = request.getParameter("csrfToken");
       
       
        if (sessionToken == null || !sessionToken.equals(requestToken)) {
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("staff");
		model.addAttribute("staffVo", staffVo);

		model.addAttribute("eGubunPage", "Y");
		model.addAttribute("pageFileGubun", pageFileGubun);
		return "mesPopup/mes/issue/popup/kw_File_add";
	}
	
	@RequestMapping(value = "/mes/issue/popup/kw_File_insert.do")
	public String mesIMGregAddInsert(HttpServletRequest request,
			@ModelAttribute("mesIssueVO") MesIssueVO mesIssueVO, ModelMap model) throws Exception {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> files = multipartRequest.getFileMap();

		List<FileVO> result = null;
		String atchFileId = "";
		if (!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, "Ins_", 0, "", "assetUploadPath");
			atchFileId = fileMngService.insertFileInfs(result);
			mesIssueVO.seteFileID(atchFileId);

		}
		 model.addAttribute("eGubunPage", "N");
			return "mesPopup/mes/issue/popup/kw_File_add";
	}
	
}
