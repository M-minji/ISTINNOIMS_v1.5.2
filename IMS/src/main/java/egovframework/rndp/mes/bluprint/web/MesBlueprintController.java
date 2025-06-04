package egovframework.rndp.mes.bluprint.web;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.rndp.admin.env.service.EnvService;
import egovframework.rndp.com.cmm.service.DefultVO;
import egovframework.rndp.com.cmm.service.EgovProperties;
import egovframework.rndp.com.utl.JsonHelper;
import egovframework.rndp.mes.bluprint.service.MesBlueprintService;
import egovframework.rndp.mes.bluprint.service.MesBlueprintVO;
import egovframework.rndp.mes.gubun.service.MesGubunService;
import egovframework.rndp.mes.gubun.service.MesGubunVO;
import egovframework.rndp.mes.inspection.service.MesInspectionVO;
import egovframework.rndp.mes.issue.service.MesIssueVO;
import egovframework.rndp.mes.login.service.MesK_StaffVo;
import egovframework.rndp.mes.maintance.service.MesMaintanceVO;
import egovframework.rndp.mes.sign.service.MesSignService;
import egovframework.rndp.mes.sign.service.MesSignVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

@Controller
public class MesBlueprintController {

	@Resource(name = "mesBlueprintService")
	private MesBlueprintService mesBlueprintService;
	
	@Resource(name = "mesGubunService")
	private MesGubunService mesGubunService;

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;
	
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;

	@Resource(name = "envService")
	private EnvService envService;

	@Resource(name = "mesSignService")
	private MesSignService mesSignService;

	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	
	@RequestMapping(value = "/mes/blueprint/kw_blueprint_lf.do")
	public String mesBlueprintLf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
		model.addAttribute("staffVO", staffVo);
		mesBlueprintVO.setkStaffKey(Integer.toString(staffVo.getkStaffKey()));
		
		/* paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesBlueprintVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesBlueprintVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesBlueprintVO.getPageSize());

		mesBlueprintVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesBlueprintVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesBlueprintVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		mesBlueprintVO = (MesBlueprintVO) DefultVO.getSearch(mesBlueprintVO);
		
		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesBlueprintVO.getTopStartDate().equals("") || mesBlueprintVO.getTopStartDate() == null){
			String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
			mesBlueprintVO.setTopStartDate(temp);
		}
		if(mesBlueprintVO.getTopEndDate().equals("") || mesBlueprintVO.getTopEndDate() == null){
			mesBlueprintVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		
		List infoList = mesBlueprintService.selectChangeList(mesBlueprintVO);
		model.addAttribute("infoList", infoList);
		
		int totCnt = mesBlueprintService.selectChangeListCnt(mesBlueprintVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("paginationInfo", paginationInfo);
		
		redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);
		
		return "mes/blueprint/kw_blueprint_lf.tiles";
	}
	


	@RequestMapping(value = "/mes/blueprint/kw_blueprintExcelListDwonload.do")
	public void blueprintExcelListDwonload(
			  HttpServletRequest request
			, HttpServletResponse response
			, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
			, RedirectAttributes redirectAttributes
			, ModelMap card) throws Exception {
		
		  Map<String, List> beans = new HashMap<String, List>();
			
		    Date nowDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			if(mesBlueprintVO.getTopStartDate().equals("") || mesBlueprintVO.getTopStartDate() == null){
				String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
				mesBlueprintVO.setTopStartDate(temp);
			}
			if(mesBlueprintVO.getTopEndDate().equals("") || mesBlueprintVO.getTopEndDate() == null){
				mesBlueprintVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
			}
			
			List infoList = mesBlueprintService.selectChangeExcelList(mesBlueprintVO);
					
			
			// 작업자 & 장비
			ArrayList<MesBlueprintVO> listForExcel = new ArrayList<>(infoList);
			for(MesBlueprintVO info : listForExcel) {
				List<MesBlueprintVO> workerList = mesBlueprintService.eSelectDeteliInfoList(info);
				List<MesBlueprintVO> assetList = mesBlueprintService.eSelectAssetInfoList(info);
				if(workerList != null && workerList.size() > 1) {
					MesBlueprintVO vo = workerList.get(0);
					String worker = String.format("%s 외 %d명", vo.geteWorker(), workerList.size() - 1);
					info.seteWorker(worker);
				}
				if(assetList != null && assetList.size() > 1) {
					MesBlueprintVO vo = assetList.get(0);
					String asset = String.format("%s 외 %d개", vo.getaAssetName(), assetList.size() - 1);
					info.setaAssetName(asset);
				}
			}
			
			
			ArrayList<String> list = new ArrayList<>(infoList);
			beans.put("list", list);
	
			String Specification =  "blueprintExcelList.xls";
		    String templatePath = EgovProperties.getProperty("salesExcelTemplatePath");		//cms.properties에서 가져오는 파일 경로
			String templateFileName = templatePath + Specification;
			
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ("yyyyMMdd", Locale.KOREA);
			Date currentTime = new Date ();
			String mTime = mSimpleDateFormat.format ( currentTime );
			String titleName = "변경관리_현황_";
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
	
	
	
	@RequestMapping(value = "/mes/blueprint/kw_blueprint_lfr.do")
	public String mesBlueprintLfr(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
			, ModelMap model) throws Exception{
		
		mesBlueprintService.mesSignBlueprint(mesBlueprintVO);
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
		model.addAttribute("staffVO", staffVo);
		
		/* paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesBlueprintVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesBlueprintVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesBlueprintVO.getPageSize());

		mesBlueprintVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesBlueprintVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesBlueprintVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		mesBlueprintVO = (MesBlueprintVO) DefultVO.getSearch(mesBlueprintVO);
		
		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesBlueprintVO.getTopStartDate().equals("") || mesBlueprintVO.getTopStartDate() == null){
			String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
			mesBlueprintVO.setTopStartDate(temp);
		}
		if(mesBlueprintVO.getTopEndDate().equals("") || mesBlueprintVO.getTopEndDate() == null){
			mesBlueprintVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		
		List infoList = mesBlueprintService.selectChangeList(mesBlueprintVO);
		model.addAttribute("infoList", infoList);
		
		int totCnt = mesBlueprintService.selectChangeListCnt(mesBlueprintVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("paginationInfo", paginationInfo);
		
		redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);
		
		return "mes/blueprint/kw_blueprint_lf.tiles";
	}
	
	@RequestMapping(value = "/mes/blueprint/kw_blueprint_if.do")
	public String mesBlueprintIf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
			, ModelMap model) throws Exception{
		String csrfToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("csrfToken", csrfToken);
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
		model.addAttribute("staffVo", staffVo);
		
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("40");
		model.addAttribute("gubun40List", mesGubunService.selectMesGubunCodeList(vo));
		
		
		return "mes/blueprint/kw_blueprint_if.tiles";
	}
	
	@RequestMapping(value = "/mes/blueprint/kw_blueprint_i.do")
	public String mesBlueprintI(HttpServletRequest request
			, RedirectAttributes redirectAttributes

			, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
		mesBlueprintVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
		mesBlueprintVO.setkStaffName(String.valueOf(staffVo.getkStaffName()));

		mesBlueprintService.eChangeManagementInfoInssert(mesBlueprintVO);

		
		redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);
		
		return "redirect:/mes/blueprint/kw_blueprint_lf.do";
	}
	
	@RequestMapping(value = "/mes/blueprint/popup/kw_s_item_lf.do")
	public String mesBlueprintPopupsItemLf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
			, ModelMap model) throws Exception{
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo);
		
		/* paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesBlueprintVO.getPageIndex()); //1
		paginationInfo.setRecordCountPerPage(mesBlueprintVO.getRecordCountPerPage()); //20
		paginationInfo.setPageSize(mesBlueprintVO.getPageSize()); //10

		mesBlueprintVO.setFirstIndex(paginationInfo.getFirstRecordIndex()); //0
		mesBlueprintVO.setLastIndex(paginationInfo.getLastRecordIndex()); //20
		mesBlueprintVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage()); //20
		

		model.addAttribute("paginationInfo", paginationInfo);
		
		redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);
		
		return "mesPopup/mes/blueprint/popup/kw_s_item_lf";
	}

	@RequestMapping(value = "/mes/blueprint/kw_blueprint_vf.do")
	public String mesBlueprintVf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
		model.addAttribute("staffVO", staffVo);
		
		MesBlueprintVO info = mesBlueprintService.selectChangeInfo(mesBlueprintVO);
		model.addAttribute("info", info);
		
		List assetList = mesBlueprintService.eSelectAssetInfoList(mesBlueprintVO);
		model.addAttribute("assetList", assetList);
		//상세정보
		List aDeteliList = mesBlueprintService.eSelectDeteliInfoList(mesBlueprintVO);
		model.addAttribute("aDeteliList", aDeteliList);
		//파일정보
		List aFileList = mesBlueprintService.eSelectFileInfoList(mesBlueprintVO);
		model.addAttribute("aFileList", aFileList);
		
		 
		MesSignVO mesSignVO = new MesSignVO();
		mesSignVO.setsSignTableKey(mesBlueprintVO.geteChangeKey());
		mesSignVO.setsSignTableName("I_CHANGE");
		List signList = mesSignService.selectSignListTwo(mesSignVO);
		model.addAttribute("signList", signList);
		
		redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);
		return "mes/blueprint/kw_blueprint_vf.tiles";
	}
	
	@RequestMapping(value = "/mes/blueprint/kw_blueprint_vfr.do")
	public String mesBlueprintVfr(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
			, ModelMap model) throws Exception{
		
		mesBlueprintService.mesSignBlueprint(mesBlueprintVO);
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
		model.addAttribute("staffVO", staffVo);
		
		MesBlueprintVO info = mesBlueprintService.selectChangeInfo(mesBlueprintVO);
		model.addAttribute("info", info);
		
		List assetList = mesBlueprintService.eSelectAssetInfoList(mesBlueprintVO);
		model.addAttribute("assetList", assetList);
		//상세정보
		List aDeteliList = mesBlueprintService.eSelectDeteliInfoList(mesBlueprintVO);
		model.addAttribute("aDeteliList", aDeteliList);
		//파일정보
		List aFileList = mesBlueprintService.eSelectFileInfoList(mesBlueprintVO);
		model.addAttribute("aFileList", aFileList);
		
		 
		MesSignVO mesSignVO = new MesSignVO();
		mesSignVO.setsSignTableKey(mesBlueprintVO.geteChangeKey());
		mesSignVO.setsSignTableName("I_CHANGE");
		List signList = mesSignService.selectSignListTwo(mesSignVO);
		model.addAttribute("signList", signList);
		
		redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);
		return "mes/blueprint/kw_blueprint_vf.tiles";
	}
	
	@RequestMapping(value = "/mes/blueprint/kw_blueprint_uf.do")
	public String mesBlueprintUf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
			, ModelMap model) throws Exception{
		
		String csrfToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("csrfToken", csrfToken);
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVo);
		
		//상세정보
		List aDeteliList = mesBlueprintService.eSelectDeteliInfoList(mesBlueprintVO);
		model.addAttribute("aDeteliList", aDeteliList);
		//파일정보
		List aFileList = mesBlueprintService.eSelectFileInfoList(mesBlueprintVO);
		model.addAttribute("aFileList", aFileList);
		
		MesBlueprintVO info = mesBlueprintService.selectChangeInfo(mesBlueprintVO);
		model.addAttribute("info", info);
		
		List assetList = mesBlueprintService.eSelectAssetInfoList(mesBlueprintVO);
		model.addAttribute("assetList", assetList);
		
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("40");
		model.addAttribute("gubun40List", mesGubunService.selectMesGubunCodeList(vo));
		
		
		MesSignVO mesSignVO = new MesSignVO();
		mesSignVO.setsSignTableKey(mesBlueprintVO.geteChangeKey());
		mesSignVO.setsSignTableName("I_CHANGE");
		List signList = mesSignService.selectSignListTwo(mesSignVO);
		model.addAttribute("signList", signList);
		
		redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);
		return "mes/blueprint/kw_blueprint_uf.tiles";
	}

	@RequestMapping(value = "/mes/blueprint/kw_blueprint_process_if.do")
	public String mesBlueprintProcessUf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo);
		
		MesBlueprintVO blueprintInfo = mesBlueprintService.selectBlueprintInfo(mesBlueprintVO);
		model.addAttribute("blueprintInfo", blueprintInfo);
		
		List blueprintItemList = mesBlueprintService.selectBlueprintItemList(mesBlueprintVO);
		model.addAttribute("blueprintItemList", blueprintItemList);
		
		List fileList = mesBlueprintService.selectMesBlueprintFile(mesBlueprintVO);
		model.addAttribute("fileList", fileList);
		
		// 파일새끼들
		List fileList2 = mesBlueprintService.selectMesBlueprintFileListU(mesBlueprintVO);
		model.addAttribute("fileList2", fileList2);
		
		redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);
		
		return "mes/blueprint/kw_blueprint_process_if.tiles";
	}

	@RequestMapping(value = "/mes/blueprint/kw_blueprint_process_vf.do")
	public String mesBlueprintProcessVf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo);
		
		MesBlueprintVO blueprintInfo = mesBlueprintService.selectBlueprintInfo(mesBlueprintVO);
		model.addAttribute("blueprintInfo", blueprintInfo);
		
		MesBlueprintVO blueprintItemInfo = mesBlueprintService.selectBlueprintItemInfo(mesBlueprintVO);
		model.addAttribute("blueprintItemInfo", blueprintItemInfo);
		
		List fileList2 = mesBlueprintService.selectMesBlueprintFileListU(mesBlueprintVO);
		model.addAttribute("fileList2", fileList2);
		
		List signList = mesSignService.selectSignList(blueprintItemInfo.getsSignKey());
		model.addAttribute("signList", signList);
		
		redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);
		
		return "mes/blueprint/kw_blueprint_process_vf.tiles";
	}
	
	@RequestMapping(value = "/mes/blueprint/kw_blueprint_process_i.do")
	public String mesBlueprintProcessI(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("fileVO") FileVO fileVO
			, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesBlueprintVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
		mesBlueprintVO.setkStaffName(String.valueOf(staffVo.getkStaffName()));
		
		mesBlueprintService.insertMesBlueprintProcess(mesBlueprintVO,request);
		
		redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);
		
		return "redirect:/mes/blueprint/kw_blueprint_lf.do";
	}

	@RequestMapping(value = "/mes/blueprint/kw_blueprint_process_r.do")
	public String mesBlueprintProcessR(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("fileVO") FileVO fileVO
			, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesBlueprintVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
		mesBlueprintVO.setkStaffName(String.valueOf(staffVo.getkStaffName()));
		
		mesBlueprintService.requestMesBlueprintProcess(mesBlueprintVO);
		
		redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);
		
		return "redirect:/mes/blueprint/kw_blueprint_lf.do";
	}

	@RequestMapping(value = "/mes/blueprint/kw_blueprint_process_s.do")
	public String mesBlueprintProcessS(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("fileVO") FileVO fileVO
			, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesBlueprintVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
		mesBlueprintVO.setkStaffName(String.valueOf(staffVo.getkStaffName()));
		model.addAttribute("staffVo", staffVo);
		
		mesBlueprintService.setBlueprintProcess(mesBlueprintVO);
		
		redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);
		
		return "redirect:/mes/blueprint/kw_blueprint_lf.do";
	}

	@RequestMapping(value = "/mes/blueprint/kw_blueprint_u.do")
	public String mesBlueprintU(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("fileVO") FileVO fileVO
			, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesBlueprintVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
		mesBlueprintVO.setkStaffName(String.valueOf(staffVo.getkStaffName()));
		
		mesBlueprintService.updateChangeInfo(mesBlueprintVO);
		
		redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);
		
		return "redirect:/mes/blueprint/kw_blueprint_lf.do";
	}
	
	
	@RequestMapping(value = "/mes/blueprint/kw_blueprint_d.do")
	public String mesBlueprintD(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
			, ModelMap model) throws Exception{
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo);
		
		mesBlueprintService.deleteMesBlueprint(mesBlueprintVO);
		
		redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);
		
		return "redirect:/mes/blueprint/kw_blueprint_lf.do";
	}

	@RequestMapping(value = "/mes/blueprint/kw_blueprint_item_d.do")
	public String mesBlueprintItemD(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
			, ModelMap model) throws Exception{
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo);
		
		mesBlueprintService.deleteMesBlueprintitem(mesBlueprintVO);
		
		redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);
		
		return "redirect:/mes/blueprint/kw_blueprint_vf.do";
	}
	
	@RequestMapping(value = "/mes/blueprint/popup/mesIMGregAdd.do")
	public String mesIMGregAddPopup(HttpServletRequest request,
			@ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO, ModelMap model) throws Exception {

		mesBlueprintVO.seteGubunPage("Y");

		return "mesPopup/mes/blueprint/popup/kw_blueprint_IMGreg";
	}
	
	// 이미지 업로드 팝업창
		@RequestMapping(value = "/mes/blueprint/popup/mesIMGregAdd_i.do")
		public String mesIMGregAddInsert(HttpServletRequest request,
				@ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO, ModelMap model) throws Exception {

			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> files = multipartRequest.getFileMap();

			List<FileVO> result = null;
			String atchFileId = "";
			if (!files.isEmpty()) {
				result = fileUtil.parseFileInf(files, "blueprint_", 0, "", "blueprintImagePath");
				atchFileId = fileMngService.insertFileInfs(result);
				// mesItemVO.seteIMGregName(atchFileId);

				// mesItemService.mesItemIMGfileInsert(mesItemVO);
			}
			// model.addAttribute("popClose", 1);
			model.addAttribute("atchFileId", atchFileId);
			model.addAttribute("atchFileName", mesBlueprintVO.geteIMGregName());
			mesBlueprintVO.seteGubunPage("N");
			return "mesPopup/mes/blueprint/popup/kw_blueprint_IMGreg";
		}
		
		

		// 이미지 업로드 팝업창
		@RequestMapping(value = "/mes/blueprint/popup/mesIMGregAddAjax.do")
		public void mesIMGregAddInsertAjax(HttpServletRequest request,
				@ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
				, HttpServletResponse response
				, RedirectAttributes redirectAttributes
				, ModelMap model) throws Exception {

			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> files = multipartRequest.getFileMap();

			List<FileVO> result = null;
			String atchFileId = "";
			if (!files.isEmpty()) {
				result = fileUtil.parseFileInf(files, "blueprint_", 0, "", "blueprintImagePath");
				atchFileId = fileMngService.insertFileInfs(result);
			}

			JsonHelper helper = new JsonHelper();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("result", result);
			map.put("atchFileId", atchFileId);
			map.put("atchFileName", mesBlueprintVO.geteIMGregName());
			helper.printJsonObject(response, "result", map); 
			redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);
		}
		
		
		
		

		@RequestMapping(value = "/mes/blueprint/kw_issue_lf.do")
		public String mesIssueLf(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
				, ModelMap model) throws Exception{
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
			model.addAttribute("staffVO", staffVo);
			mesBlueprintVO.setkStaffKey(Integer.toString(staffVo.getkStaffKey()));
			
			/* paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(mesBlueprintVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(mesBlueprintVO.getRecordCountPerPage());
			paginationInfo.setPageSize(mesBlueprintVO.getPageSize());

			mesBlueprintVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			mesBlueprintVO.setLastIndex(paginationInfo.getLastRecordIndex());
			mesBlueprintVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			mesBlueprintVO = (MesBlueprintVO) DefultVO.getSearch(mesBlueprintVO);
			
			Date nowDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			if(mesBlueprintVO.getTopStartDate().equals("") || mesBlueprintVO.getTopStartDate() == null){
				String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
				mesBlueprintVO.setTopStartDate(temp);
			}
			if(mesBlueprintVO.getTopEndDate().equals("") || mesBlueprintVO.getTopEndDate() == null){
				mesBlueprintVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
			}
			
			List infoList = mesBlueprintService.selectIssueList(mesBlueprintVO);
			model.addAttribute("infoList", infoList);
			
			int totCnt = mesBlueprintService.selectIssueListCnt(mesBlueprintVO);
			paginationInfo.setTotalRecordCount(totCnt);
			
			model.addAttribute("paginationInfo", paginationInfo);
			
			redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);
			
			return "mes/blueprint/kw_issue_lf.tiles";
		}
		

		@RequestMapping(value = "/mes/blueprint/kw_issueExcelListDwonload.do")
		public void blueprintIssueExcelListDwonload(
				  HttpServletRequest request
				, HttpServletResponse response
				, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
				, RedirectAttributes redirectAttributes
				, ModelMap card) throws Exception {
			
			  Map<String, List> beans = new HashMap<String, List>();
				
			    Date nowDate = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				if(mesBlueprintVO.getTopStartDate().equals("") || mesBlueprintVO.getTopStartDate() == null){
					String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
					mesBlueprintVO.setTopStartDate(temp);
				}
				if(mesBlueprintVO.getTopEndDate().equals("") || mesBlueprintVO.getTopEndDate() == null){
					mesBlueprintVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
				}
				
				List infoList = mesBlueprintService.selectIssueExcelList(mesBlueprintVO);
				
								
				// 회의참여자 & 작업자 & 장비
				ArrayList<MesBlueprintVO> listForExcel = new ArrayList<>(infoList);
				for(MesBlueprintVO info : listForExcel) {
					List<MesBlueprintVO> noteList = mesBlueprintService.eSelectIssueNotesInfoList(info);
					List<MesBlueprintVO> workerList = mesBlueprintService.eSelectIssueDeteliInfoList(info);
					List<MesBlueprintVO> assetList = mesBlueprintService.eSelectIssueAssetInfoList(info);
					if(noteList != null && noteList.size() > 1) {
						MesBlueprintVO vo = noteList.get(0);
						String meet = String.format("%s 외 %d명", vo.geteRowWorker(), noteList.size() - 1);
						info.seteRowWorker(meet);
					}
					if(workerList != null && workerList.size() > 1) {
						MesBlueprintVO vo = workerList.get(0);
						String worker = String.format("%s 외 %d명", vo.geteWorker(), workerList.size() - 1);
						info.seteWorker(worker);
					}
					if(assetList != null && assetList.size() > 1) {
						MesBlueprintVO vo = assetList.get(0);
						String asset = String.format("%s 외 %d개", vo.getaAssetName(), assetList.size() - 1);
						info.setaAssetName(asset);
					}
				}
				
				
				
				ArrayList<String> list = new ArrayList<>(infoList);
				beans.put("list", list);
		
				String Specification =  "blueprintIssueExcelList.xls";
			    String templatePath = EgovProperties.getProperty("salesExcelTemplatePath");		//cms.properties에서 가져오는 파일 경로
				String templateFileName = templatePath + Specification;
				
				SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ("yyyyMMdd", Locale.KOREA);
				Date currentTime = new Date ();
				String mTime = mSimpleDateFormat.format ( currentTime );
				String titleName = "문제관리_현황_";
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
		

		@RequestMapping(value = "/mes/blueprint/kw_issue_lfr.do")
		public String mesIssueLfr(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
				, ModelMap model) throws Exception{
			
			mesBlueprintService.mesSignBlueIssue(mesBlueprintVO);
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
			model.addAttribute("staffVO", staffVo);
			
			/* paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(mesBlueprintVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(mesBlueprintVO.getRecordCountPerPage());
			paginationInfo.setPageSize(mesBlueprintVO.getPageSize());

			mesBlueprintVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			mesBlueprintVO.setLastIndex(paginationInfo.getLastRecordIndex());
			mesBlueprintVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			mesBlueprintVO = (MesBlueprintVO) DefultVO.getSearch(mesBlueprintVO);
			
			Date nowDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			if(mesBlueprintVO.getTopStartDate().equals("") || mesBlueprintVO.getTopStartDate() == null){
				String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
				mesBlueprintVO.setTopStartDate(temp);
			}
			if(mesBlueprintVO.getTopEndDate().equals("") || mesBlueprintVO.getTopEndDate() == null){
				mesBlueprintVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
			}
			
			List infoList = mesBlueprintService.selectIssueList(mesBlueprintVO);
			model.addAttribute("infoList", infoList);
			
			int totCnt = mesBlueprintService.selectIssueListCnt(mesBlueprintVO);
			paginationInfo.setTotalRecordCount(totCnt);
			
			model.addAttribute("paginationInfo", paginationInfo);
			
			redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);
			
			return "mes/blueprint/kw_issue_lf.tiles";
		}
		
		
		@RequestMapping(value = "/mes/blueprint/kw_issue_if.do")
		public String mesIssueIf(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
				, ModelMap model) throws Exception{
			
			String csrfToken = UUID.randomUUID().toString();
			request.getSession().setAttribute("csrfToken", csrfToken);
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
			model.addAttribute("staffVo", staffVo);
			
			return "mes/blueprint/kw_issue_if.tiles";
		}
		
		@RequestMapping(value = "/mes/blueprint/kw_issue_i.do")
		public String mesIssueI(HttpServletRequest request
				, RedirectAttributes redirectAttributes

				, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
				, ModelMap model) throws Exception{
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
			mesBlueprintVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
			mesBlueprintVO.setkStaffName(String.valueOf(staffVo.getkStaffName()));

			mesBlueprintService.eIssueInfoInssert(mesBlueprintVO);

			redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);
			
			return "redirect:/mes/blueprint/kw_issue_lf.do";
		}
		
		@RequestMapping(value = "/mes/blueprint/kw_issue_vf.do")
		public String mesIssueVf(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
				, ModelMap model) throws Exception{
			String csrfToken = UUID.randomUUID().toString();
			request.getSession().setAttribute("csrfToken", csrfToken);
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
			model.addAttribute("staffVO", staffVo);
			
			MesBlueprintVO info = mesBlueprintService.selectIssueInfo(mesBlueprintVO);
			model.addAttribute("info", info);
			
			List assetList = mesBlueprintService.eSelectIssueAssetInfoList(mesBlueprintVO);
			model.addAttribute("assetList", assetList);
			 
			List aDeteliList = mesBlueprintService.eSelectIssueDeteliInfoList(mesBlueprintVO);
			model.addAttribute("aDeteliList", aDeteliList);
			 
			List aFileList = mesBlueprintService.eSelectIssueFileInfoList(mesBlueprintVO);
			model.addAttribute("aFileList", aFileList);
			 
			List aNotesList = mesBlueprintService.eSelectIssueNotesInfoList(mesBlueprintVO);
			model.addAttribute("aNotesList", aNotesList);
			 
			MesSignVO mesSignVO = new MesSignVO();
			mesSignVO.setsSignTableKey(mesBlueprintVO.geteIssueKey());
			mesSignVO.setsSignTableName("I_BLUE_ISSUE");
			List signList = mesSignService.selectSignListTwo(mesSignVO);
			model.addAttribute("signList", signList); 
			
			redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);

			return "mes/blueprint/kw_issue_vf.tiles";
		}
		@RequestMapping(value = "/mes/blueprint/kw_issue_vfr.do")
		public String mesIssueVfr(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
				, ModelMap model) throws Exception{
			
			mesBlueprintService.mesSignBlueIssue(mesBlueprintVO);
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
			model.addAttribute("staffVO", staffVo);
			
			MesBlueprintVO info = mesBlueprintService.selectIssueInfo(mesBlueprintVO);
			model.addAttribute("info", info);
			
			List assetList = mesBlueprintService.eSelectIssueAssetInfoList(mesBlueprintVO);
			model.addAttribute("assetList", assetList);
			
			List aDeteliList = mesBlueprintService.eSelectIssueDeteliInfoList(mesBlueprintVO);
			model.addAttribute("aDeteliList", aDeteliList);
			
			List aFileList = mesBlueprintService.eSelectIssueFileInfoList(mesBlueprintVO);
			model.addAttribute("aFileList", aFileList);
			
			List aNotesList = mesBlueprintService.eSelectIssueNotesInfoList(mesBlueprintVO);
			model.addAttribute("aNotesList", aNotesList);
			
			MesSignVO mesSignVO = new MesSignVO();
			mesSignVO.setsSignTableKey(mesBlueprintVO.geteIssueKey());
			mesSignVO.setsSignTableName("I_BLUE_ISSUE");
			List signList = mesSignService.selectSignListTwo(mesSignVO);
			model.addAttribute("signList", signList); 
			
			redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);
			
			return "mes/blueprint/kw_issue_vf.tiles";
		}
		
		
		@RequestMapping(value = "/mes/blueprint/kw_issue_uf.do")
		public String mesIssueUf(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
				, ModelMap model) throws Exception{
			String csrfToken = UUID.randomUUID().toString();
			request.getSession().setAttribute("csrfToken", csrfToken);
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
			model.addAttribute("staffVO", staffVo);
			
			MesBlueprintVO info = mesBlueprintService.selectIssueInfo(mesBlueprintVO);
			model.addAttribute("info", info);
			
			List assetList = mesBlueprintService.eSelectIssueAssetInfoList(mesBlueprintVO);
			model.addAttribute("assetList", assetList);

			List aDeteliList = mesBlueprintService.eSelectIssueDeteliInfoList(mesBlueprintVO);
			model.addAttribute("aDeteliList", aDeteliList);
			 
			List aFileList = mesBlueprintService.eSelectIssueFileInfoList(mesBlueprintVO);
			model.addAttribute("aFileList", aFileList);
			 
			List aNotesList = mesBlueprintService.eSelectIssueNotesInfoList(mesBlueprintVO);
			model.addAttribute("aNotesList", aNotesList);
			
			MesSignVO mesSignVO = new MesSignVO();
			mesSignVO.setsSignTableKey(mesBlueprintVO.geteIssueKey());
			mesSignVO.setsSignTableName("I_BLUE_ISSUE");
			List signList = mesSignService.selectSignListTwo(mesSignVO);
			model.addAttribute("signList", signList); 
			
			redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);
			
			return "mes/blueprint/kw_issue_uf.tiles";
		}
		
		@RequestMapping(value = "/mes/blueprint/kw_issue_d.do")
		public String mesIssueD(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
				, ModelMap model) throws Exception{
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			model.addAttribute("staffVo", staffVo);
			
			mesBlueprintService.selectIssueDelete(mesBlueprintVO);
			
			redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);
			
			return "redirect:/mes/blueprint/kw_issue_lf.do";
		}
		

		@RequestMapping(value = "/mes/blueprint/kw_issue_u.do")
		public String mesIssueU(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("fileVO") FileVO fileVO
				, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
				, ModelMap model) throws Exception{
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			mesBlueprintVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
			mesBlueprintVO.setkStaffName(String.valueOf(staffVo.getkStaffName()));
			
			mesBlueprintService.updateIssueInfo(mesBlueprintVO);
			
			redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);
			
			return "redirect:/mes/blueprint/kw_issue_lf.do";
		}
		
		

		@RequestMapping(value = "/mes/blueprint/kw_sr_lf.do")
		public String mesSRLf(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
				, ModelMap model) throws Exception{
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
			model.addAttribute("staffVO", staffVo);
			mesBlueprintVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
			
			/* paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(mesBlueprintVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(mesBlueprintVO.getRecordCountPerPage());
			paginationInfo.setPageSize(mesBlueprintVO.getPageSize());

			mesBlueprintVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			mesBlueprintVO.setLastIndex(paginationInfo.getLastRecordIndex());
			mesBlueprintVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			mesBlueprintVO = (MesBlueprintVO) DefultVO.getSearch(mesBlueprintVO);
			
			Date nowDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			if(mesBlueprintVO.getTopStartDate().equals("") || mesBlueprintVO.getTopStartDate() == null){
				String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
				mesBlueprintVO.setTopStartDate(temp);
			}
			if(mesBlueprintVO.getTopEndDate().equals("") || mesBlueprintVO.getTopEndDate() == null){
				mesBlueprintVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
			}
			
			List infoList = mesBlueprintService.selectSRList(mesBlueprintVO);
			model.addAttribute("infoList", infoList);
			
			int totCnt = mesBlueprintService.selectSRListCnt(mesBlueprintVO);
			paginationInfo.setTotalRecordCount(totCnt);
			
			model.addAttribute("paginationInfo", paginationInfo);
			
			redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);
			
			return "mes/blueprint/kw_sr_lf.tiles";
		}
		


		@RequestMapping(value = "/mes/blueprint/kw_srExcelListDwonload.do")
		public void blueprintsrExcelListDwonload(
				  HttpServletRequest request
				, HttpServletResponse response
				, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
				, RedirectAttributes redirectAttributes
				, ModelMap card) throws Exception {
			
			  Map<String, List> beans = new HashMap<String, List>();
				
			    Date nowDate = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				if(mesBlueprintVO.getTopStartDate().equals("") || mesBlueprintVO.getTopStartDate() == null){
					String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
					mesBlueprintVO.setTopStartDate(temp);
				}
				if(mesBlueprintVO.getTopEndDate().equals("") || mesBlueprintVO.getTopEndDate() == null){
					mesBlueprintVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
				}
				
				List infoList = mesBlueprintService.selectSRExcelList(mesBlueprintVO);
				
				// 장비
				ArrayList<MesBlueprintVO> listForExcel = new ArrayList<>(infoList);
				for(MesBlueprintVO info : listForExcel) {
					List<MesBlueprintVO> assetList = mesBlueprintService.eSelectSRAssetInfoList(info);
					if(assetList != null && assetList.size() > 1) {
						MesBlueprintVO vo = assetList.get(0);
						String asset = String.format("%s 외 %d개", vo.getaAssetName(), assetList.size() - 1);
						info.setaAssetName(asset);
					}
				}
				
				ArrayList<String> list = new ArrayList<>(infoList);
				beans.put("list", list);
		
				String Specification =  "blueprintSRExcelList.xls";
			    String templatePath = EgovProperties.getProperty("salesExcelTemplatePath");		//cms.properties에서 가져오는 파일 경로
				String templateFileName = templatePath + Specification;
				
				SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ("yyyyMMdd", Locale.KOREA);
				Date currentTime = new Date ();
				String mTime = mSimpleDateFormat.format ( currentTime );
				String titleName = "SR관리_현황_";
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
		

		
		@RequestMapping(value = "/mes/blueprint/kw_sr_lfr.do")
		public String mesSRLfr(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
				, ModelMap model) throws Exception{
			
			mesBlueprintService.mesSignBlueSr(mesBlueprintVO);
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
			model.addAttribute("staffVO", staffVo);
			
			/* paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(mesBlueprintVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(mesBlueprintVO.getRecordCountPerPage());
			paginationInfo.setPageSize(mesBlueprintVO.getPageSize());

			mesBlueprintVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			mesBlueprintVO.setLastIndex(paginationInfo.getLastRecordIndex());
			mesBlueprintVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			mesBlueprintVO = (MesBlueprintVO) DefultVO.getSearch(mesBlueprintVO);
			
			Date nowDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			if(mesBlueprintVO.getTopStartDate().equals("") || mesBlueprintVO.getTopStartDate() == null){
				String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
				mesBlueprintVO.setTopStartDate(temp);
			}
			if(mesBlueprintVO.getTopEndDate().equals("") || mesBlueprintVO.getTopEndDate() == null){
				mesBlueprintVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
			}
			
			List infoList = mesBlueprintService.selectSRList(mesBlueprintVO);
			model.addAttribute("infoList", infoList);
			
			int totCnt = mesBlueprintService.selectSRListCnt(mesBlueprintVO);
			paginationInfo.setTotalRecordCount(totCnt);
			
			model.addAttribute("paginationInfo", paginationInfo);
			
			redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);
			
			return "mes/blueprint/kw_sr_lf.tiles";
		}
		@RequestMapping(value = "/mes/blueprint/kw_sr_if.do")
		public String mesSRIf(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
				, ModelMap model) throws Exception{
			String csrfToken = UUID.randomUUID().toString();
			request.getSession().setAttribute("csrfToken", csrfToken);
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
			model.addAttribute("staffVo", staffVo);
			
			return "mes/blueprint/kw_sr_if.tiles";
		}
		
		@RequestMapping(value = "/mes/blueprint/kw_sr_i.do")
		public String mesSRI(HttpServletRequest request
				, RedirectAttributes redirectAttributes

				, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
				, ModelMap model) throws Exception{
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
			mesBlueprintVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
			mesBlueprintVO.setkStaffName(String.valueOf(staffVo.getkStaffName()));

			mesBlueprintService.eSRInfoInssert(mesBlueprintVO);

			redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);
			
			return "redirect:/mes/blueprint/kw_sr_lf.do";
		}
		
		@RequestMapping(value = "/mes/blueprint/kw_sr_vf.do")
		public String mesSRVf(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
				, ModelMap model) throws Exception{
			String csrfToken = UUID.randomUUID().toString();
			request.getSession().setAttribute("csrfToken", csrfToken);
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
			model.addAttribute("staffVO", staffVo);
			
			MesBlueprintVO info = mesBlueprintService.selectSRInfo(mesBlueprintVO);
			model.addAttribute("info", info);
			
			List assetList = mesBlueprintService.eSelectSRAssetInfoList(mesBlueprintVO);
			model.addAttribute("assetList", assetList);
			 
			// 파일
			List eFileInfoList = mesBlueprintService.eFileInfoList(mesBlueprintVO);
			model.addAttribute("eFileInfoList", eFileInfoList);

			MesSignVO mesSignVO = new MesSignVO();
			mesSignVO.setsSignTableKey(mesBlueprintVO.geteIssueKey());
			mesSignVO.setsSignTableName("I_BLUE_SR");
			List signList = mesSignService.selectSignListTwo(mesSignVO);
			model.addAttribute("signList", signList); 
			
			redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);

			return "mes/blueprint/kw_sr_vf.tiles";
		}
		
		@RequestMapping(value = "/mes/blueprint/kw_sr_vfr.do")
		public String mesSRVfr(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
				, ModelMap model) throws Exception{
			
			mesBlueprintService.mesSignBlueSr(mesBlueprintVO);
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
			model.addAttribute("staffVO", staffVo);
			
			MesBlueprintVO info = mesBlueprintService.selectSRInfo(mesBlueprintVO);
			model.addAttribute("info", info);
			
			List assetList = mesBlueprintService.eSelectSRAssetInfoList(mesBlueprintVO);
			model.addAttribute("assetList", assetList);
			 

			MesSignVO mesSignVO = new MesSignVO();
			mesSignVO.setsSignTableKey(mesBlueprintVO.geteIssueKey());
			mesSignVO.setsSignTableName("I_BLUE_SR");
			List signList = mesSignService.selectSignListTwo(mesSignVO);
			model.addAttribute("signList", signList); 
			
			redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);

			return "mes/blueprint/kw_sr_vf.tiles";
		}
		
		@RequestMapping(value = "/mes/blueprint/kw_sr_uf.do")
		public String mesSRUf(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
				, ModelMap model) throws Exception{
			String csrfToken = UUID.randomUUID().toString();
			request.getSession().setAttribute("csrfToken", csrfToken);
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
			model.addAttribute("staffVo", staffVo);
			
			MesBlueprintVO info = mesBlueprintService.selectSRInfo(mesBlueprintVO);
			model.addAttribute("info", info);
			
			List assetList = mesBlueprintService.eSelectSRAssetInfoList(mesBlueprintVO);
			model.addAttribute("assetList", assetList);
			

			MesSignVO mesSignVO = new MesSignVO();
			mesSignVO.setsSignTableKey(mesBlueprintVO.geteIssueKey());
			mesSignVO.setsSignTableName("I_BLUE_SR");
			List signList = mesSignService.selectSignListTwo(mesSignVO);
			model.addAttribute("signList", signList); 
			
			List eFileInfoList = mesBlueprintService.eFileInfoList(mesBlueprintVO);
			model.addAttribute("eFileInfoList", eFileInfoList);
			
			redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);
			
			return "mes/blueprint/kw_sr_uf.tiles";
		}
		
		@RequestMapping(value = "/mes/blueprint/kw_sr_d.do")
		public String mesSRD(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
				, ModelMap model) throws Exception{
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			model.addAttribute("staffVo", staffVo);
			
			mesBlueprintService.selectSRDelete(mesBlueprintVO);
			
			redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);
			
			return "redirect:/mes/blueprint/kw_sr_lf.do";
		}
		

		@RequestMapping(value = "/mes/blueprint/kw_sr_u.do")
		public String mesSRU(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("fileVO") FileVO fileVO
				, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
				, ModelMap model) throws Exception{
			
			MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			mesBlueprintVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
			mesBlueprintVO.setkStaffName(String.valueOf(staffVo.getkStaffName()));
			
			mesBlueprintService.updateSRInfo(mesBlueprintVO);
			
			redirectAttributes.addFlashAttribute("mesBlueprintVO", mesBlueprintVO);
			
			return "redirect:/mes/blueprint/kw_sr_lf.do";
		}
		

		@RequestMapping(value = "/mes/issue/kw_uploadSignblueprintReasonAjax.do")
		public void mesSignStatusUpdate(HttpServletRequest request
				, @ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO
				, RedirectAttributes redirectAttributes
				, HttpServletResponse response
				, ModelMap model) throws Exception {

			mesBlueprintService.mesUpdateSignStatus(mesBlueprintVO);

			JsonHelper helper = new JsonHelper();
			Map<String,Object> map = new HashMap<String,Object>();
			helper.printJsonObject(response, "result", map);
		}
		
		// 첨부파일
		@RequestMapping(value = "/mes/blueprint/popup/kw_sr_File_add.do")
		public String mesIMGregAddPopup(HttpServletRequest request,
				HttpServletResponse response,
				@ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO, ModelMap model) throws Exception {
			 String pageFileGubun = "N";
			 if (!"N".equals(mesBlueprintVO.getePageGubun()) && !"Y".equals(mesBlueprintVO.getePageGubun())) {
//				 pageFileGubun = "N";
				 pageFileGubun = "Y";
		        }else {
//		        	pageFileGubun = mesBlueprintVO.getePageGubun();
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
			return "mesPopup/mes/blueprint/popup/kw_sr_File_add";
		}
		
		@RequestMapping(value = "/mes/blueprint/popup/kw_sr_File_insert.do")
		public String mesFileregAddInsert(HttpServletRequest request,
				@ModelAttribute("mesBlueprintVO") MesBlueprintVO mesBlueprintVO, ModelMap model) throws Exception {

			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> files = multipartRequest.getFileMap();

			List<FileVO> result = null;
			String atchFileId = "";
			if (!files.isEmpty()) {
				result = fileUtil.parseFileInf(files, "Ins_", 0, "", "assetUploadPath");
				atchFileId = fileMngService.insertFileInfs(result);
				mesBlueprintVO.seteFileID(atchFileId);

			}
			 model.addAttribute("eGubunPage", "N");
				return "mesPopup/mes/blueprint/popup/kw_sr_File_add";
		}
		
		// 상세보기 엑셀 다운 - 변경관리
		@RequestMapping(value = "/mes/blueprint/kw_blueprint_download.do")
		public void mesExcelDownload(
			  HttpServletRequest request
			, HttpServletResponse response
			, @ModelAttribute("mesInspectionVO") MesBlueprintVO mesBlueprintVO
			, ModelMap model) throws Exception {
			
			Map<String, List> beans = new HashMap<String, List>();
			
			MesBlueprintVO info = mesBlueprintService.selectChangeInfo(mesBlueprintVO);
			List<MesBlueprintVO> infoList = new ArrayList<MesBlueprintVO>();
			infoList.add(info);
			beans.put("info", infoList);
			
			//상세정보
			List<MesBlueprintVO> aDeteliList = mesBlueprintService.eSelectDeteliInfoList(mesBlueprintVO);
			if(aDeteliList.size() == 0) {
				MesBlueprintVO vo = new MesBlueprintVO();
				vo.seteWorkerName("");
				aDeteliList.add(vo);
			} else {
				// 파일 추가
				List<MesBlueprintVO> aFileList = mesBlueprintService.eSelectFileInfoList(mesBlueprintVO);		
				for(int i=0; i<aFileList.size(); i++) {
					MesBlueprintVO file = aFileList.get(i);
					int fileIndex = Integer.parseInt(file.geteFileRowIndex());
					MesBlueprintVO vo = aDeteliList.get(fileIndex);
					if(vo.geteFileRowName() != null && vo.geteFileRowName() != "") {
						String files = vo.geteFileRowName() + ", " + file.geteFileRowName();
						file.seteFileRowName(files);
					}
					vo.seteFileRowName(file.geteFileRowName());
				}
			}
			beans.put("detail", aDeteliList);
			
			// 장비
			List assetList = mesBlueprintService.eSelectAssetInfoList(mesBlueprintVO);
			if(assetList.size() == 0) {
				MesBlueprintVO vo = new MesBlueprintVO();
				vo.setaAssetType("");
				assetList.add(vo);
			}
			beans.put("asset", assetList);
	
			
		    String templatePath = EgovProperties.getProperty("salesExcelTemplatePath");
			String Specification = "blueprintDetail.xlsx";
			String templateFileName = templatePath + Specification;
			
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ("yyyyMMdd", Locale.KOREA);
			Date currentTime = new Date ();
			String mTime = mSimpleDateFormat.format ( currentTime );
		    
			String titleName = "변경관리_상세_";
		    String destFileName = titleName + mTime + ".xlsx"; 
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
		
		// 상세보기 엑셀 다운 - 문제관리
				@RequestMapping(value = "mes/blueprint/kw_blueprint_issue_download.do")
				public void mesIssueExcelDownload(
					  HttpServletRequest request
					, HttpServletResponse response
					, @ModelAttribute("mesInspectionVO") MesBlueprintVO mesBlueprintVO
					, ModelMap model) throws Exception {
					
					Map<String, List> beans = new HashMap<String, List>();
					
					// 상단
					MesBlueprintVO info = mesBlueprintService.selectIssueInfo(mesBlueprintVO);
					List<MesBlueprintVO> infoList = new ArrayList<MesBlueprintVO>();
					infoList.add(info);
					beans.put("info", infoList);
					
					
					// 회의록
					List aNotesList = mesBlueprintService.eSelectIssueNotesInfoList(mesBlueprintVO);
					if(aNotesList.size() == 0) {
						MesBlueprintVO vo = new MesBlueprintVO();
						vo.seteRowWorker("");
						aNotesList.add(vo);
					} 
					beans.put("meeting", aNotesList);
					
					
					// 상세내역
					List<MesBlueprintVO> aDeteliList = mesBlueprintService.eSelectIssueDeteliInfoList(mesBlueprintVO);
					if(aDeteliList.size() == 0) {
						MesBlueprintVO vo = new MesBlueprintVO();
						vo.seteWorkerName("");
						aDeteliList.add(vo);
					} else {
						// 파일 추가
						List<MesBlueprintVO> aFileList = mesBlueprintService.eSelectIssueFileInfoList(mesBlueprintVO);
						for(int i=0; i<aFileList.size(); i++) {
							MesBlueprintVO file = aFileList.get(i);
							int fileIndex = Integer.parseInt(file.geteFileRowIndex());
							MesBlueprintVO vo = aDeteliList.get(fileIndex);
							if(vo.geteFileRowName() != null && vo.geteFileRowName() != "") {
								String files = vo.geteFileRowName() + ", " + file.geteFileRowName();
								file.seteFileRowName(files);
							}
							vo.seteFileRowName(file.geteFileRowName());
						}
					}
					beans.put("detail", aDeteliList);
					
					
					// 장비
					List assetList = mesBlueprintService.eSelectIssueAssetInfoList(mesBlueprintVO);
					if(assetList.size() == 0) {
						MesBlueprintVO vo = new MesBlueprintVO();
						vo.setaAssetType("");
						assetList.add(vo);
					} 
					beans.put("asset", assetList);
					
					
					
				    String templatePath = EgovProperties.getProperty("salesExcelTemplatePath");
					String Specification = "blueIssueDetail.xlsx";
					String templateFileName = templatePath + Specification;
					
					SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ("yyyyMMdd", Locale.KOREA);
					Date currentTime = new Date ();
					String mTime = mSimpleDateFormat.format ( currentTime );
				    
					String titleName = "문제관리_상세_";
				    String destFileName = titleName + mTime + ".xlsx"; 
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
				
				
				
				// 상세보기 엑셀 다운 - SR관리
				@RequestMapping(value = "mes/blueprint/kw_blueprint_sr_download.do")
				public void mesSRExcelDownload(
					  HttpServletRequest request
					, HttpServletResponse response
					, @ModelAttribute("mesInspectionVO") MesBlueprintVO mesBlueprintVO
					, ModelMap model) throws Exception {
					
					Map<String, List> beans = new HashMap<String, List>();
					
					// 상세
					MesBlueprintVO info = mesBlueprintService.selectSRInfo(mesBlueprintVO);
					List<MesBlueprintVO> infoList = new ArrayList<MesBlueprintVO>();
					infoList.add(info);
					beans.put("info", infoList);
					
					// 첨부파일
					List eFileInfoList = mesBlueprintService.eFileInfoList(mesBlueprintVO);
					if(eFileInfoList.size() == 0) {
						MesIssueVO vo = new MesIssueVO();
						vo.seteFileName("첨부파일이 없습니다.");
						eFileInfoList.add(vo);
					}
					beans.put("file", eFileInfoList);
					
					// 장비
					List assetList = mesBlueprintService.eSelectSRAssetInfoList(mesBlueprintVO);
					if(assetList.size() == 0) {
						MesBlueprintVO vo = new MesBlueprintVO();
						vo.setaAssetType("");
						assetList.add(vo);
					} 
					beans.put("asset", assetList);
					
					
					
				    String templatePath = EgovProperties.getProperty("salesExcelTemplatePath");
					String Specification = "SRDetail.xlsx";
					String templateFileName = templatePath + Specification;
					
					SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ("yyyyMMdd", Locale.KOREA);
					Date currentTime = new Date ();
					String mTime = mSimpleDateFormat.format ( currentTime );
				    
					String titleName = "SR관리_상세_";
				    String destFileName = titleName + mTime + ".xlsx"; 
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
		
		
}
