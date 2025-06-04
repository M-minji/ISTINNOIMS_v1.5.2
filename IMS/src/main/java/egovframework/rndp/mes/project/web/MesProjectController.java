package egovframework.rndp.mes.project.web;

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
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.rndp.admin.env.service.EnvService;
import egovframework.rndp.admin.env.service.EnvVO;
import egovframework.rndp.com.cmm.service.EgovProperties;
import egovframework.rndp.com.utl.JsonHelper;
import egovframework.rndp.mes.asset.service.MesAssetVO;
import egovframework.rndp.mes.bluprint.service.MesBlueprintVO;
import egovframework.rndp.mes.common.service.MesCommonService;
import egovframework.rndp.mes.gubun.service.MesGubunService;
import egovframework.rndp.mes.gubun.service.MesGubunVO;
import egovframework.rndp.mes.login.service.MesK_StaffVo;
import egovframework.rndp.mes.output.service.MesOutputService;
import egovframework.rndp.mes.output.service.MesOutputVO;
import egovframework.rndp.mes.project.service.MesProjectService;
import egovframework.rndp.mes.project.service.MesProjectVO;
import egovframework.rndp.mes.sign.service.MesSignService;
import egovframework.rndp.mes.sign.service.MesSignVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import net.sf.jxls.transformer.XLSTransformer;

@Controller
public class MesProjectController {
	
	/*공통사용 서비스 common 및  Egov 공용 */
	@Resource(name = "mesCommonService")
	private MesCommonService mesCommonService;
	
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;
	
	@Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;
	
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;
	/*공통사용 서비스 common 및  Egov 공용 */
	
	@Resource(name = "mesGubunService")
	private MesGubunService mesGubunService;
	
	@Resource(name = "mesProjectService")
	private MesProjectService mesProjectService; 
	 
	@Resource(name = "envService")
	private EnvService envService;

	@Resource(name = "mesSignService")
	private MesSignService mesSignService;
	
	@Resource(name = "mesOutputService")
	private MesOutputService mesOutputService; 
	 
	@RequestMapping(value = "/mes/project/kw_project_lf.do")
	public String mesIncomLf(HttpServletRequest request
			, @ModelAttribute("mesProjectVO") MesProjectVO mesProjectVO
			, RedirectAttributes redirectAttributes
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
		model.addAttribute("staffVO", staffVo);
		mesProjectVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
		mesProjectVO.setkStaffName(staffVo.getkStaffName());
		
		/* paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesProjectVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesProjectVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesProjectVO.getPageSize());

		mesProjectVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesProjectVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesProjectVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesProjectVO.geteStartDate().equals("") || mesProjectVO.geteStartDate() == null){
			String temp = dateFormat.format(nowDate).substring(0, 4);
			mesProjectVO.seteStartDate(temp);
		}else {
			String temp = mesProjectVO.geteStartDate().substring(0, 4);
			mesProjectVO.seteStartDate(temp);
		}
		if(mesProjectVO.geteEndDate().equals("") || mesProjectVO.geteEndDate() == null){
			mesProjectVO.seteEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		
		List projectList = mesProjectService.mesProjectInfoList(mesProjectVO);
		model.addAttribute("projectList", projectList);
		
		int totCnt = mesProjectService.mesProjectInfoListCnt(mesProjectVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("paginationInfo", paginationInfo);
		
		redirectAttributes.addFlashAttribute("mesProjectVO", mesProjectVO);
		            
		return "mes/project/kw_project_lf.tiles";
	}
	

	@RequestMapping(value = "/mes/project/kw_projectExcelListDwonload.do")
	public void projectExcelListDwonload(
			  HttpServletRequest request
			, HttpServletResponse response
			, @ModelAttribute("mesProjectVO") MesProjectVO mesProjectVO
			, RedirectAttributes redirectAttributes
			, ModelMap card) throws Exception {
		
		  Map<String, List> beans = new HashMap<String, List>();
			 
		  	Date nowDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			if(mesProjectVO.geteStartDate().equals("") || mesProjectVO.geteStartDate() == null){
				String temp = dateFormat.format(nowDate).substring(0, 4) ;
				mesProjectVO.seteStartDate(temp);
			}
			if(mesProjectVO.geteEndDate().equals("") || mesProjectVO.geteEndDate() == null){
				mesProjectVO.seteEndDate(String.valueOf(dateFormat.format(nowDate)));
			}
			List infolist = mesProjectService.mesProjectExcelInfoList(mesProjectVO);
			
			ArrayList<String> list = new ArrayList<>(infolist);
			beans.put("list", list);
	
			String Specification =  "procejtExcelList.xls";
		    String templatePath = EgovProperties.getProperty("salesExcelTemplatePath");		//cms.properties에서 가져오는 파일 경로
			String templateFileName = templatePath + Specification;
			
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ("yyyyMMdd", Locale.KOREA);
			Date currentTime = new Date ();
			String mTime = mSimpleDateFormat.format ( currentTime );
			String titleName = "프로젝트관리_";
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
	
	
	@RequestMapping(value = "/mes/project/kw_project_lfr.do")
	public String mesIncomLfr(HttpServletRequest request
			, @ModelAttribute("mesProjectVO") MesProjectVO mesProjectVO
			, RedirectAttributes redirectAttributes
			, ModelMap model) throws Exception{
		
		mesProjectService.mesSignProject(mesProjectVO);
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
		model.addAttribute("staffVO", staffVo);
		mesProjectVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
		mesProjectVO.setkStaffName(staffVo.getkStaffName());
		
		/* paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesProjectVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesProjectVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesProjectVO.getPageSize());
		
		mesProjectVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesProjectVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesProjectVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesProjectVO.geteStartDate().equals("") || mesProjectVO.geteStartDate() == null){
			String temp = dateFormat.format(nowDate).substring(0, 4);
			mesProjectVO.seteStartDate(temp);
		}
		if(mesProjectVO.geteEndDate().equals("") || mesProjectVO.geteEndDate() == null){
			mesProjectVO.seteEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		
		List projectList = mesProjectService.mesProjectInfoList(mesProjectVO);
		model.addAttribute("projectList", projectList);
		
		int totCnt = mesProjectService.mesProjectInfoListCnt(mesProjectVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("paginationInfo", paginationInfo);
		
		redirectAttributes.addFlashAttribute("mesProjectVO", mesProjectVO);
		
		return "mes/project/kw_project_lf.tiles";
	}
	
	
	@RequestMapping(value = "/mes/project/kw_project_if.do")
	public String mesProductionWorkerIf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesProjectVO") MesProjectVO mesProjectVO
			, ModelMap model) throws Exception{
		String csrfToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("csrfToken", csrfToken);
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo);
		mesProjectVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
		mesProjectVO.setkStaffName(staffVo.getkStaffName());
		
		List envList = envService.envList();
		for(int i=0; i<envList.size(); i++){
			EnvVO eVO = (EnvVO)envList.get(i);	
			if("companyName".equals(eVO.getName())){				// 회사명
				String companyName = eVO.getValue();
				model.addAttribute("companyName", companyName);
			}
		} 
		model.addAttribute("envList", envList);
		
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("31");
		model.addAttribute("gubunList", mesGubunService.selectMesGubunCodeList(vo));	
		
		vo.setsGubunCateKey("32");
		model.addAttribute("gubunList32", mesGubunService.selectMesGubunCodeList(vo));	
		
		return "mes/project/kw_project_if.tiles";
	}

		@RequestMapping(value = "/mes/project/kw_project_i.do")
		public String mesProjectI(HttpServletRequest request
				, @ModelAttribute("mesProjectVO") MesProjectVO mesProjectVO
				, RedirectAttributes redirectAttributes
				, ModelMap model) throws Exception{
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			mesProjectVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
			mesProjectVO.setkStaffName(String.valueOf(staffVo.getkStaffName()));
			
			mesProjectService.eMesInsertProjectInfo(mesProjectVO);
			
			redirectAttributes.addFlashAttribute("mesProjectVO", mesProjectVO);
			
			return "redirect:/mes/project/kw_project_lf.do";
		}
		
		@RequestMapping(value = "/mes/project/kw_project_vf.do")
		public String mesIncomVf(HttpServletRequest request
				, @ModelAttribute("mesProjectVO") MesProjectVO mesProjectVO
				, RedirectAttributes redirectAttributes
				, ModelMap model) throws Exception{
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
			model.addAttribute("staffVO", staffVo);

			MesProjectVO projectInfo = mesProjectService.selectProjectInfo(mesProjectVO);
			model.addAttribute("projectInfo", projectInfo);
			

			MesSignVO mesSignVO = new MesSignVO();
			mesSignVO.setsSignTableKey(mesProjectVO.geteProjectNum());
			mesSignVO.setsSignTableName("P_PROJECT");
			List signList = mesSignService.selectSignListTwo(mesSignVO);
			model.addAttribute("signList", signList); 
			
			
			MesOutputVO mesOutputVO = new MesOutputVO();
			//산출물 등록 정보 가져오기. 제어의 편의성상 구분자로 나눠서 가져옴.
			mesOutputVO.seteProjectNum(mesProjectVO.geteProjectNum());
			mesOutputVO.seteItemGubun("A"); //착수
			List infoAList = mesOutputService.insertDeliverableInfoList(mesOutputVO);
			model.addAttribute("infoAList", infoAList);
			//첨부파일 등록정 가져오기.
			List fileAList = mesOutputService.insertDeliverableFileInfoList(mesOutputVO);
			model.addAttribute("fileAList", fileAList);
			
			mesOutputVO.seteItemGubun("B"); //수행
			List infoBList = mesOutputService.insertDeliverableInfoList(mesOutputVO);
			model.addAttribute("infoBList", infoBList);
			List fileBList = mesOutputService.insertDeliverableFileInfoList(mesOutputVO);
			model.addAttribute("fileBList", fileBList);
			
			mesOutputVO.seteItemGubun("C"); //완료
			List infoCList = mesOutputService.insertDeliverableInfoList(mesOutputVO);
			model.addAttribute("infoCList", infoCList);
			List fileCList = mesOutputService.insertDeliverableFileInfoList(mesOutputVO);
			model.addAttribute("fileCList", fileCList);
			
			//산출물 등록 정보 가져오기. 제어의 편의성상 구분자로 나눠서 가져옴.
			mesOutputVO.seteItemGubun("A"); //착수
			List infoAAList = mesOutputService.insertReportInfoList(mesOutputVO);
			model.addAttribute("infoAAList", infoAAList);
			//첨부파일 등록정 가져오기.
			List fileAAList = mesOutputService.insertReportFileInfoList(mesOutputVO);
			model.addAttribute("fileAAList", fileAAList);
			
			mesOutputVO.seteItemGubun("B"); //수행
			List infoBBList = mesOutputService.insertReportInfoList(mesOutputVO);
			model.addAttribute("infoBBList", infoBBList);
			List fileBBList = mesOutputService.insertReportFileInfoList(mesOutputVO);
			model.addAttribute("fileBBList", fileBBList);
			
			mesOutputVO.seteItemGubun("C"); //완료
			List infoCCList = mesOutputService.insertReportInfoList(mesOutputVO);
			model.addAttribute("infoCCList", infoCCList);
			List fileCCList = mesOutputService.insertReportFileInfoList(mesOutputVO);
			model.addAttribute("fileCCList", fileCCList);
			
			mesOutputVO.seteItemGubun("D"); //완료
			List infoDDList = mesOutputService.insertReportInfoList(mesOutputVO);
			model.addAttribute("infoDDList", infoDDList);
			List fileDDList = mesOutputService.insertReportFileInfoList(mesOutputVO);
			model.addAttribute("fileDDList", fileDDList);
			 
			
			redirectAttributes.addFlashAttribute("mesProjectVO", mesProjectVO);
			
			return "mes/project/kw_project_vf.tiles";
		}
		

		@RequestMapping(value = "/mes/project/kw_project_vfr.do")
		public String mesIncomVfr(HttpServletRequest request
				, @ModelAttribute("mesProjectVO") MesProjectVO mesProjectVO
				, RedirectAttributes redirectAttributes
				, ModelMap model) throws Exception{
			
			mesProjectService.mesSignProject(mesProjectVO);
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
			model.addAttribute("staffVO", staffVo);

			MesProjectVO projectInfo = mesProjectService.selectProjectInfo(mesProjectVO);
			model.addAttribute("projectInfo", projectInfo);
			

			MesSignVO mesSignVO = new MesSignVO();
			mesSignVO.setsSignTableKey(mesProjectVO.geteProjectNum());
			mesSignVO.setsSignTableName("P_PROJECT");
			List signList = mesSignService.selectSignListTwo(mesSignVO);
			model.addAttribute("signList", signList); 
			
			
			MesOutputVO mesOutputVO = new MesOutputVO();
			//산출물 등록 정보 가져오기. 제어의 편의성상 구분자로 나눠서 가져옴.
			mesOutputVO.seteProjectNum(mesProjectVO.geteProjectNum());
			mesOutputVO.seteItemGubun("A"); //착수
			List infoAList = mesOutputService.insertDeliverableInfoList(mesOutputVO);
			model.addAttribute("infoAList", infoAList);
			//첨부파일 등록정 가져오기.
			List fileAList = mesOutputService.insertDeliverableFileInfoList(mesOutputVO);
			model.addAttribute("fileAList", fileAList);
			
			mesOutputVO.seteItemGubun("B"); //수행
			List infoBList = mesOutputService.insertDeliverableInfoList(mesOutputVO);
			model.addAttribute("infoBList", infoBList);
			List fileBList = mesOutputService.insertDeliverableFileInfoList(mesOutputVO);
			model.addAttribute("fileBList", fileBList);
			
			mesOutputVO.seteItemGubun("C"); //완료
			List infoCList = mesOutputService.insertDeliverableInfoList(mesOutputVO);
			model.addAttribute("infoCList", infoCList);
			List fileCList = mesOutputService.insertDeliverableFileInfoList(mesOutputVO);
			model.addAttribute("fileCList", fileCList);
			
			//산출물 등록 정보 가져오기. 제어의 편의성상 구분자로 나눠서 가져옴.
			mesOutputVO.seteItemGubun("A"); //착수
			List infoAAList = mesOutputService.insertReportInfoList(mesOutputVO);
			model.addAttribute("infoAAList", infoAAList);
			//첨부파일 등록정 가져오기.
			List fileAAList = mesOutputService.insertReportFileInfoList(mesOutputVO);
			model.addAttribute("fileAAList", fileAAList);
			
			mesOutputVO.seteItemGubun("B"); //수행
			List infoBBList = mesOutputService.insertReportInfoList(mesOutputVO);
			model.addAttribute("infoBBList", infoBBList);
			List fileBBList = mesOutputService.insertReportFileInfoList(mesOutputVO);
			model.addAttribute("fileBBList", fileBBList);
			
			mesOutputVO.seteItemGubun("C"); //완료
			List infoCCList = mesOutputService.insertReportInfoList(mesOutputVO);
			model.addAttribute("infoCCList", infoCCList);
			List fileCCList = mesOutputService.insertReportFileInfoList(mesOutputVO);
			model.addAttribute("fileCCList", fileCCList);
			
			mesOutputVO.seteItemGubun("D"); //완료
			List infoDDList = mesOutputService.insertReportInfoList(mesOutputVO);
			model.addAttribute("infoDDList", infoDDList);
			List fileDDList = mesOutputService.insertReportFileInfoList(mesOutputVO);
			model.addAttribute("fileDDList", fileDDList);
			 
			
			redirectAttributes.addFlashAttribute("mesProjectVO", mesProjectVO);
			
			return "mes/project/kw_project_vf.tiles";
		}
		
		
		@RequestMapping(value = "/mes/project/kw_project_d.do")
		public String mesIncomD(HttpServletRequest request
				, @ModelAttribute("mesProjectVO") MesProjectVO mesProjectVO
				, RedirectAttributes redirectAttributes
				, ModelMap model) throws Exception{
			
			mesProjectService.deleteMesProject(mesProjectVO);
			
			redirectAttributes.addFlashAttribute("mesProjectVO", mesProjectVO);
			
			return "redirect:/mes/project/kw_project_lf.do";
		}
		
		
		@RequestMapping(value = "/mes/project/kw_project_uf.do")
		public String mesIncomUf(HttpServletRequest request
				, @ModelAttribute("mesProjectVO") MesProjectVO mesProjectVO
				, RedirectAttributes redirectAttributes
				, ModelMap model) throws Exception{
			String csrfToken = UUID.randomUUID().toString();
			request.getSession().setAttribute("csrfToken", csrfToken);
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
			model.addAttribute("staffVo", staffVo);
			
			MesProjectVO projectInfo = mesProjectService.selectProjectInfo(mesProjectVO);
			model.addAttribute("projectInfo", projectInfo);


			MesGubunVO vo = new MesGubunVO();
			vo.setsGubunCateKey("31");
			model.addAttribute("gubunList", mesGubunService.selectMesGubunCodeList(vo));	
			
			vo.setsGubunCateKey("32");
			model.addAttribute("gubunList32", mesGubunService.selectMesGubunCodeList(vo));	
			

			MesSignVO mesSignVO = new MesSignVO();
			mesSignVO.setsSignTableKey(mesProjectVO.geteProjectNum());
			mesSignVO.setsSignTableName("P_PROJECT");
			List signList = mesSignService.selectSignListTwo(mesSignVO);
			model.addAttribute("signList", signList); 
			
			
			redirectAttributes.addFlashAttribute("mesProjectVO", mesProjectVO);
			
			return "mes/project/kw_project_uf.tiles";
		}

		@RequestMapping(value = "/mes/project/kw_project_u.do")
		public String mesProjectU(HttpServletRequest request
				, @ModelAttribute("mesProjectVO") MesProjectVO mesProjectVO
				, RedirectAttributes redirectAttributes
				, ModelMap model) throws Exception{
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			mesProjectVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
			mesProjectVO.setkStaffName(String.valueOf(staffVo.getkStaffName()));
			
			mesProjectService.updateProjectInfo(mesProjectVO);
			
			redirectAttributes.addFlashAttribute("mesProjectVO", mesProjectVO);
			
			return "redirect:/mes/project/kw_project_lf.do";
		}

		@RequestMapping(value = "/mes/project/kw_project_process_if.do")
		public String mesProjectProcessIf(HttpServletRequest request
				, @ModelAttribute("mesOutputVO") MesProjectVO mesProjectVO
				, RedirectAttributes redirectAttributes
				, ModelMap model) throws Exception{
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
			model.addAttribute("staffVo", staffVo);

			MesProjectVO projectInfo = mesProjectService.selectProInfo(mesProjectVO);
			model.addAttribute("projectInfo", projectInfo);

			
			redirectAttributes.addFlashAttribute("mesProjectVO", mesProjectVO);
			
			return "mes/project/kw_project_process_if.tiles";
		}
		
		@RequestMapping(value = "/mes/project/kw_project_process_i.do")
		public String mesProjectProcessI(HttpServletRequest request
				, @ModelAttribute("mesProjectVO") MesProjectVO mesProjectVO
				, RedirectAttributes redirectAttributes
				, ModelMap model) throws Exception{
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			mesProjectVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
			mesProjectVO.setkStaffName(String.valueOf(staffVo.getkStaffName()));
			
			mesProjectService.insertMesProjectrocess(mesProjectVO, request);
			
			redirectAttributes.addFlashAttribute("mesProjectVO", mesProjectVO);
			
			return "redirect:/mes/project/kw_project_lf.do";
		}
		
		@RequestMapping(value = "/mes/project/kw_project_process_r.do")
		public String mesProjectProcessR(HttpServletRequest request
				, @ModelAttribute("mesProjectVO") MesProjectVO mesProjectVO
				, RedirectAttributes redirectAttributes
				, ModelMap model) throws Exception{
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			model.addAttribute("staffVo", staffVo);
			
			mesProjectService.requestProjectProcess(mesProjectVO);
			
			redirectAttributes.addFlashAttribute("mesProjectVO", mesProjectVO);
			
			return "redirect:/mes/project/kw_project_lf.do";
		}

		@RequestMapping(value = "/mes/project/kw_project_process_s.do")
		public String mesProjectProcessS(HttpServletRequest request
				, @ModelAttribute("mesProjectVO") MesProjectVO mesProjectVO
				, RedirectAttributes redirectAttributes
				, ModelMap model) throws Exception{
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			model.addAttribute("staffVo", staffVo);
			
			mesProjectService.setSignProject(mesProjectVO);
			
			redirectAttributes.addFlashAttribute("mesProjectVO", mesProjectVO);
			
			return "redirect:/mes/project/kw_project_lf.do";
		}
		

		@RequestMapping(value = "/mes/issue/kw_uploadSignProjectReasonAjax.do")
		public void mesSignStatusUpdate(HttpServletRequest request
				, @ModelAttribute("mesProjectVO") MesProjectVO mesProjectVO
				, RedirectAttributes redirectAttributes
				, HttpServletResponse response
				, ModelMap model) throws Exception {

			mesProjectService.mesUpdateSignStatus(mesProjectVO);

			JsonHelper helper = new JsonHelper();
			Map<String,Object> map = new HashMap<String,Object>();
			helper.printJsonObject(response, "result", map);
		}
		
}
