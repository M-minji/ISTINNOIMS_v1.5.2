package egovframework.rndp.mes.output.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import egovframework.rndp.admin.env.service.EnvService;
import egovframework.rndp.admin.env.service.EnvVO;
import egovframework.rndp.com.utl.JsonHelper;
import egovframework.rndp.mes.bluprint.service.MesBlueprintVO;
import egovframework.rndp.mes.common.service.MesCommonService;
import egovframework.rndp.mes.gubun.service.MesGubunService;
import egovframework.rndp.mes.gubun.service.MesGubunVO;
import egovframework.rndp.mes.login.service.MesK_StaffVo;
import egovframework.rndp.mes.maintance.service.MesMaintanceVO;
import egovframework.rndp.mes.output.service.MesOutputService;
import egovframework.rndp.mes.output.service.MesOutputVO;
import egovframework.rndp.mes.project.service.MesProjectService;
import egovframework.rndp.mes.project.service.MesProjectVO;
import egovframework.rndp.mes.sign.service.MesSignService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class MesOutputController {
	
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
	
	@Resource(name = "mesOutputService")
	private MesOutputService mesOutputService; 
	 
	@Resource(name = "envService")
	private EnvService envService;

	@Resource(name = "mesSignService")
	private MesSignService mesSignService;
	
	@Resource(name = "mesProjectService")
	private MesProjectService mesProjectService; 
	 
	@RequestMapping(value = "/mes/output/kw_output_lf.do")
	public String mesIncomLf(HttpServletRequest request
			, @ModelAttribute("mesOutputVO") MesOutputVO mesOutputVO
			, RedirectAttributes redirectAttributes
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
		model.addAttribute("staffVO", staffVo);
		mesOutputVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
		mesOutputVO.setkStaffName(staffVo.getkStaffName());
		/* paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesOutputVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesOutputVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesOutputVO.getPageSize());

		mesOutputVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesOutputVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesOutputVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesOutputVO.geteStartDate().equals("") || mesOutputVO.geteStartDate() == null){
//			String temp = String.valueOf(dateFormat.format(nowDate));
//			temp = temp.substring(0,8);
//			temp += "01";
			String temp = dateFormat.format(nowDate).substring(0, 4);
			mesOutputVO.seteStartDate(temp);
		}
		if(mesOutputVO.geteEndDate().equals("") || mesOutputVO.geteEndDate() == null){
			mesOutputVO.seteEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		List outputList = mesOutputService.mesProjectInfoList(mesOutputVO);
		model.addAttribute("outputList", outputList);
		
		int totCnt = mesOutputService.mesProjectInfoListCnt(mesOutputVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("paginationInfo", paginationInfo);
		
		redirectAttributes.addFlashAttribute("mesOutputVO", mesOutputVO);
		            
		return "mes/output/kw_output_lf.tiles";
	}
	
	
	@RequestMapping(value = "/mes/output/kw_output_if.do")
	public String mesProductionWorkerIf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesOutputVO") MesOutputVO mesOutputVO
			, ModelMap model) throws Exception{
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesOutputVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
		mesOutputVO.setkStaffName(String.valueOf(staffVo.getkStaffName()));
		
		model.addAttribute("staffVo", staffVo);
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
		vo.setsGubunCateKey("30");
		model.addAttribute("gubunList", mesGubunService.selectMesGubunCodeList(vo));	
		
		redirectAttributes.addFlashAttribute("mesOutputVO", mesOutputVO);
		
		return "mes/output/kw_output_if.tiles";
	}
	
	@RequestMapping(value = "/mes/output/popup/kw_output_IMGreg_lf.do")
	public String mesIMGregAddPopup(HttpServletRequest request
			, @ModelAttribute("mesOutputVO") MesOutputVO mesOutputVO
			, RedirectAttributes redirectAttributes
			, ModelMap model) throws Exception {

		mesOutputVO.seteGubunPage("Y");

		 model.addAttribute("MesProjecttVO", mesOutputVO);
		 model.addAttribute("eGubunPage",  mesOutputVO.geteGubunPage());
		return "mesPopup/mes/output/popup/kw_output_IMGreg_lf";
	}
	
	// 이미지 업로드 팝업창                  
		@RequestMapping(value = "/mes/output/popup/kw_output_IMGreg_i.do")
		public String mesIMGregAddInsert(HttpServletRequest request
				, @ModelAttribute("mesOutputVO") MesOutputVO mesOutputVO
				, RedirectAttributes redirectAttributes
				, ModelMap model) throws Exception  {

			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> files = multipartRequest.getFileMap();
	    
			List<FileVO> result = null;
			String atchFileId = "";
			if (!files.isEmpty()) {
				result = fileUtil.parseFileInf(files, "output_", 0, "", "outputImagePath");
				atchFileId = fileMngService.insertFileInfs(result);
				mesOutputVO.setoOutputItemFileId(atchFileId);
			}
			mesOutputVO.seteGubunPage("N");
			
			return "mesPopup/mes/output/popup/kw_output_IMGreg_lf";
		}
		
		// 이미지 업로드 팝업창
		@RequestMapping(value = "/mes/output/popup/mesIMGregAddAjax.do")
		public void mesIMGregAddInsertAjax(HttpServletRequest request,
				@ModelAttribute("mesOutputVO") MesOutputVO mesOutputVO
				, HttpServletResponse response
				, RedirectAttributes redirectAttributes
				, ModelMap model) throws Exception {

			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> files = multipartRequest.getFileMap();

			List<FileVO> result = null;
			String atchFileId = "";
			if (!files.isEmpty()) {
				result = fileUtil.parseFileInf(files, "output_", 0, "", "outputImagePath");
				atchFileId = fileMngService.insertFileInfs(result);
			}

			JsonHelper helper = new JsonHelper();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("result", result);
			map.put("atchFileId", atchFileId);
			map.put("atchFileName", mesOutputVO.geteIMGregName());
			helper.printJsonObject(response, "result", map); 
			redirectAttributes.addFlashAttribute("mesOutputVO", mesOutputVO);
		}
		
		
		@RequestMapping(value = "/mes/output/kw_output_i.do")
		public String mesOutputI(HttpServletRequest request
				, @ModelAttribute("mesOutputVO") MesOutputVO mesOutputVO
				, RedirectAttributes redirectAttributes
				, ModelMap model) throws Exception{
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			mesOutputVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
			mesOutputVO.setkStaffName(String.valueOf(staffVo.getkStaffName()));
			
			List outputList = mesOutputService.selectMesOutputList(mesOutputVO);
			model.addAttribute("outputList", outputList);
			
			mesOutputService.insertMesOutput(mesOutputVO);
			
			mesOutputService.insertMesOutputProcess(mesOutputVO, request);
			
			redirectAttributes.addFlashAttribute("mesOutputVO", mesOutputVO);
			
			return "redirect:/mes/output/kw_output_lf.do";
		}

		@RequestMapping(value = "/mes/output/kw_output_process_if.do")
		public String mesProductionWorkerProcessIf(HttpServletRequest request
				, @ModelAttribute("mesOutputVO") MesOutputVO mesOutputVO
				, RedirectAttributes redirectAttributes
				, ModelMap model) throws Exception{
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
			model.addAttribute("staffVo", staffVo);

			MesOutputVO outputInfo = mesOutputService.selectOutInfo(mesOutputVO);
			model.addAttribute("outputInfo", outputInfo);

			List outputItemList = mesOutputService.selectOutInfoItemList(mesOutputVO);
			model.addAttribute("outputItemList", outputItemList);
			
			redirectAttributes.addFlashAttribute("mesOutputVO", mesOutputVO);
			
			return "mes/output/kw_output_process_if.tiles";
		}
		
		@RequestMapping(value = "/mes/output/kw_output_process_i.do")
		public String mesOutputProcessI(HttpServletRequest request
				, @ModelAttribute("mesOutputVO") MesOutputVO mesOutputVO
				, RedirectAttributes redirectAttributes
				, ModelMap model) throws Exception{
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			mesOutputVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
			mesOutputVO.setkStaffName(String.valueOf(staffVo.getkStaffName()));
			
			
			mesOutputService.insertMesOutputProcess(mesOutputVO, request);
			
			redirectAttributes.addFlashAttribute("mesOutputVO", mesOutputVO);
			
			return "redirect:/mes/output/kw_output_lf.do";
		}
		
		@RequestMapping(value = "/mes/output/kw_output_process_r.do")
		public String mesOutputProcessR(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("mesMaintanceVO") MesOutputVO mesOutputVO
				, ModelMap model) throws Exception{
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			model.addAttribute("staffVo", staffVo);
			
			mesOutputService.requestOutputProcess(mesOutputVO);		
			
			redirectAttributes.addFlashAttribute("mesOutputVO", mesOutputVO);
			
			return "redirect:/mes/output/kw_output_lf.do";
		}

		@RequestMapping(value = "/mes/output/kw_output_s.do")
		public String mesOutputS(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("mesMaintanceVO") MesOutputVO mesOutputVO
				, ModelMap model) throws Exception{
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			model.addAttribute("staffVo", staffVo);
			
			mesOutputService.setSignOutput(mesOutputVO);		
			
			return "redirect:/mes/output/kw_output_lf.do";
		}
		
		@RequestMapping(value = "/mes/output/kw_output_vf.do")
		public String mesIncomVf(HttpServletRequest request
				, @ModelAttribute("mesOutputVO") MesOutputVO mesOutputVO
				, RedirectAttributes redirectAttributes
				, ModelMap model) throws Exception{
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
			model.addAttribute("staffVo", staffVo);

			MesOutputVO outputInfo = mesOutputService.selectOutInfo(mesOutputVO);
			model.addAttribute("outputInfo", outputInfo);

			List outputItemList = mesOutputService.selectOutInfoItemList(mesOutputVO);
			model.addAttribute("outputItemList", outputItemList);
			
			List signList = mesSignService.selectSignList(outputInfo.getsSignKey());
			model.addAttribute("signList", signList);
			
			redirectAttributes.addFlashAttribute("mesOutputVO", mesOutputVO);
			
			return "mes/output/kw_output_vf.tiles";
		}
		
		@RequestMapping(value = "/mes/output/kw_output_d.do")
		public String mesIncomD(HttpServletRequest request
				, @ModelAttribute("mesOutputVO") MesOutputVO mesOutputVO
				, RedirectAttributes redirectAttributes
				, ModelMap model) throws Exception{
			
			mesOutputService.deleteMesOutput(mesOutputVO);
			
			redirectAttributes.addFlashAttribute("mesOutputVO", mesOutputVO);
			
			return "redirect:/mes/output/kw_output_lf.do";
		}
		
		
		@RequestMapping(value = "/mes/output/kw_output_uf.do")
		public String mesIncomUf(HttpServletRequest request
				, @ModelAttribute("mesOutputVO") MesOutputVO mesOutputVO
				, RedirectAttributes redirectAttributes
				, ModelMap model) throws Exception{
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
			model.addAttribute("staffVo", staffVo);
			
			MesOutputVO outputInfo = mesOutputService.selectOutInfo(mesOutputVO);
			model.addAttribute("outputInfo", outputInfo);

			List outputItemList = mesOutputService.selectOutInfoItemList(mesOutputVO);
			model.addAttribute("outputItemList", outputItemList);

			List signList = mesSignService.selectSignList(outputInfo.getsSignKey());
			model.addAttribute("signList", signList);
			
			MesGubunVO vo = new MesGubunVO();
			vo.setsGubunCateKey("30");
			model.addAttribute("gubunList", mesGubunService.selectMesGubunCodeList(vo));	
			
			redirectAttributes.addFlashAttribute("mesOutputVO", mesOutputVO);
			
			return "mes/output/kw_output_uf.tiles";
		}

		@RequestMapping(value = "/mes/output/kw_output_u.do")
		public String mesOutputU(HttpServletRequest request
				, @ModelAttribute("mesOutputVO") MesOutputVO mesOutputVO
				, RedirectAttributes redirectAttributes
				, ModelMap model) throws Exception{
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			mesOutputVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
			mesOutputVO.setkStaffName(String.valueOf(staffVo.getkStaffName()));
			
			mesOutputService.updateMesOutput(mesOutputVO);
			
			redirectAttributes.addFlashAttribute("mesOutputVO", mesOutputVO);
			
			return "redirect:/mes/output/kw_output_lf.do";
		}
			
		
		

		@RequestMapping(value = "/mes/output/kw_eDeliverable_insert.do")
		public String mesDeliverableIf(HttpServletRequest request
				, RedirectAttributes redirectAttributes  
				, HttpServletResponse response
				, @ModelAttribute("mesOutputVO") MesOutputVO mesOutputVO
				, ModelMap model) throws Exception{
			
			String csrfToken = UUID.randomUUID().toString();
			request.getSession().setAttribute("csrfToken", csrfToken);			
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			mesOutputVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
			mesOutputVO.setkStaffName(String.valueOf(staffVo.getkStaffName()));
			model.addAttribute("staffVo", staffVo);
			//프로젝트 정보 가져오기
			MesProjectVO mesProjectVO = new MesProjectVO();
			mesProjectVO.seteProjectNum(mesOutputVO.geteProjectNum());
					
			MesProjectVO projectInfo = mesProjectService.selectProjectInfo(mesProjectVO);
			model.addAttribute("projectInfo", projectInfo);
			
			//산출물 등록 정보 가져오기. 제어의 편의성상 구분자로 나눠서 가져옴.
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
		
			
			redirectAttributes.addFlashAttribute("mesOutputVO", mesOutputVO);
			
			return "mes/output/kw_eDeliverable_insert.tiles";
		}
		
		@RequestMapping(value = "/mes/output/kw_eDeliverable_i.do")
		public String mesDeliverableI(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("mesOutputVO") MesOutputVO mesOutputVO
				, ModelMap model) throws Exception{
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			mesOutputVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
			mesOutputVO.setkStaffName(String.valueOf(staffVo.getkStaffName()));
			model.addAttribute("staffVo", staffVo);
	 
			mesOutputService.eInsertDeliverableInfo(mesOutputVO);
			
			redirectAttributes.addFlashAttribute("mesOutputVO", mesOutputVO);
			
			return "redirect:/mes/output/kw_output_lf.do";
		} 
		@RequestMapping(value = "/mes/output/kw_eDeliverable_d.do")
		public String mesDeliverableD(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("mesOutputVO") MesOutputVO mesOutputVO
				, ModelMap model) throws Exception{
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			mesOutputVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
			mesOutputVO.setkStaffName(String.valueOf(staffVo.getkStaffName()));
			model.addAttribute("staffVo", staffVo);
			
			mesOutputService.eDeleteDeliverableInfo(mesOutputVO);
			
			redirectAttributes.addFlashAttribute("mesOutputVO", mesOutputVO);
			
			return "redirect:/mes/output/kw_output_lf.do";
		} 
		
		@RequestMapping(value = "/mes/output/kw_eReport_insert.do")
		public String mesReportIf(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("mesOutputVO") MesOutputVO mesOutputVO
				, ModelMap model) throws Exception{
			String csrfToken = UUID.randomUUID().toString();
			request.getSession().setAttribute("csrfToken", csrfToken);
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			mesOutputVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
			mesOutputVO.setkStaffName(String.valueOf(staffVo.getkStaffName()));
			model.addAttribute("staffVo", staffVo);
			
			//프로젝트 정보 가져오기
			MesProjectVO mesProjectVO = new MesProjectVO();
			mesProjectVO.seteProjectNum(mesOutputVO.geteProjectNum());
					
			MesProjectVO projectInfo = mesProjectService.selectProjectInfo(mesProjectVO);
			model.addAttribute("projectInfo", projectInfo);
			
			//산출물 등록 정보 가져오기. 제어의 편의성상 구분자로 나눠서 가져옴.
			mesOutputVO.seteItemGubun("A"); //착수
			List infoAList = mesOutputService.insertReportInfoList(mesOutputVO);
			model.addAttribute("infoAList", infoAList);
			//첨부파일 등록정 가져오기.
			List fileAList = mesOutputService.insertReportFileInfoList(mesOutputVO);
			model.addAttribute("fileAList", fileAList);
			
			mesOutputVO.seteItemGubun("B"); //수행
			List infoBList = mesOutputService.insertReportInfoList(mesOutputVO);
			model.addAttribute("infoBList", infoBList);
			List fileBList = mesOutputService.insertReportFileInfoList(mesOutputVO);
			model.addAttribute("fileBList", fileBList);
			
			mesOutputVO.seteItemGubun("C"); //완료
			List infoCList = mesOutputService.insertReportInfoList(mesOutputVO);
			model.addAttribute("infoCList", infoCList);
			List fileCList = mesOutputService.insertReportFileInfoList(mesOutputVO);
			model.addAttribute("fileCList", fileCList);
			
			mesOutputVO.seteItemGubun("D"); //완료
			List infoDList = mesOutputService.insertReportInfoList(mesOutputVO);
			model.addAttribute("infoDList", infoDList);
			List fileDList = mesOutputService.insertReportFileInfoList(mesOutputVO);
			model.addAttribute("fileDList", fileDList);
			
			
			redirectAttributes.addFlashAttribute("mesOutputVO", mesOutputVO);
			
			return "mes/output/kw_eReport_insert.tiles";
		}

		@RequestMapping(value = "/mes/output/kw_eReport_i.do")
		public String mesReportI(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("mesOutputVO") MesOutputVO mesOutputVO
				, ModelMap model) throws Exception{
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			mesOutputVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
			mesOutputVO.setkStaffName(String.valueOf(staffVo.getkStaffName()));
			model.addAttribute("staffVo", staffVo);
	 
			mesOutputService.eInsertReportInfo(mesOutputVO);
			
			redirectAttributes.addFlashAttribute("mesOutputVO", mesOutputVO);
			
			return "redirect:/mes/output/kw_output_lf.do";
		} 
		
		@RequestMapping(value = "/mes/output/kw_eReport_d.do")
		public String mesReportD(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("mesOutputVO") MesOutputVO mesOutputVO
				, ModelMap model) throws Exception{
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			mesOutputVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
			mesOutputVO.setkStaffName(String.valueOf(staffVo.getkStaffName()));
			model.addAttribute("staffVo", staffVo);
			
			mesOutputService.eDeleteDeliverableInfo(mesOutputVO);
			
			redirectAttributes.addFlashAttribute("mesOutputVO", mesOutputVO);
			
			return "redirect:/mes/output/kw_output_lf.do";
		} 
		

}
