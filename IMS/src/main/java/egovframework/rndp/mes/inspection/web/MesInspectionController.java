
package egovframework.rndp.mes.inspection.web;

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
import egovframework.rndp.admin.env.service.EnvService;
import egovframework.rndp.com.cmm.service.EgovProperties;
import egovframework.rndp.com.utl.JsonHelper;
import egovframework.rndp.mes.asset.service.MesAssetService;
import egovframework.rndp.mes.asset.service.MesAssetVO;
import egovframework.rndp.mes.gubun.service.MesGubunService;
import egovframework.rndp.mes.gubun.service.MesGubunVO;
import egovframework.rndp.mes.inspection.service.MesInspectionService;
import egovframework.rndp.mes.inspection.service.MesInspectionVO;
import egovframework.rndp.mes.issue.service.MesIssueVO;
import egovframework.rndp.mes.login.service.MesK_StaffVo;
import egovframework.rndp.mes.sign.service.MesSignService;
import egovframework.rndp.mes.sign.service.MesSignVO;
import egovframework.rndp.mes.user.service.MesUserVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import net.sf.jxls.transformer.XLSTransformer;

@Controller
public class MesInspectionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MesInspectionController.class);

	/** EgovSampleService */
	@Resource(name = "mesInspectionService")
	private MesInspectionService mesInspectionService;
	
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
	
	@Resource(name = "mesAssetService")
	private MesAssetService mesAssetService;
	
	 
	@RequestMapping(value = "/mes/inspection/kw_inspection_lf.do")
	public String mesInspectiontLf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesInspectionVO") MesInspectionVO mesInspectionVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		mesInspectionVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
		
		// paging
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesInspectionVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesInspectionVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesInspectionVO.getPageSize());

		mesInspectionVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesInspectionVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesInspectionVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());


		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesInspectionVO.geteTopStartDate().equals("") || mesInspectionVO.geteTopStartDate() == null){
			String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
			mesInspectionVO.seteTopStartDate(temp);
		}
		if(mesInspectionVO.geteTopEndDate().equals("") || mesInspectionVO.geteTopEndDate() == null){
			mesInspectionVO.seteTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("38"); //점검구분
		model.addAttribute("gubun38List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("39");//점검주기
		model.addAttribute("gubun39List", mesGubunService.selectMesGubunCodeList(vo));
		
		
		List infoList = mesInspectionService.mesInspectiontList(mesInspectionVO);
		model.addAttribute("infoList", infoList);
		int totCnt = mesInspectionService.mesInspectiontListCnt(mesInspectionVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		
		model.addAttribute("paginationInfo", paginationInfo);
		
		redirectAttributes.addFlashAttribute("mesInspectionVO", mesInspectionVO);
		return "mes/inspection/kw_inspection_lf.tiles";
	}
	
	

	@RequestMapping(value = "/mes/inspection/kw_inspectionExcelListDwonload.do")
	public void inspectionExcelListDwonload(
			  HttpServletRequest request
			, HttpServletResponse response
			, @ModelAttribute("mesInspectionVO") MesInspectionVO mesInspectionVO
			, RedirectAttributes redirectAttributes
			, ModelMap card) throws Exception {
		
		  Map<String, List> beans = new HashMap<String, List>();
			
		    Date nowDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			if(mesInspectionVO.geteTopStartDate().equals("") || mesInspectionVO.geteTopStartDate() == null){
				String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
				mesInspectionVO.seteTopStartDate(temp);
			}
			if(mesInspectionVO.geteTopEndDate().equals("") || mesInspectionVO.geteTopEndDate() == null){
				mesInspectionVO.seteTopEndDate(String.valueOf(dateFormat.format(nowDate)));
			}
			
//			List infoList = mesIssueService.mesIssueExcelInfoList(mesIssueVO);
			List infoList = mesInspectionService.mesInspectiontExcelList(mesInspectionVO);
			
			// 장비
			ArrayList<MesInspectionVO> listForAsset = new ArrayList<>(infoList);
			for(MesInspectionVO info : listForAsset) {
				List assetselect = mesInspectionService.eResultInfoList(info);
				ArrayList<MesInspectionVO> assetList = new ArrayList<>(assetselect);
				if(assetList != null && assetList.size() > 1) {
					MesInspectionVO assetVo = assetList.get(0);
					String asset = String.format("%s 외 %d개", assetVo.geteAssetName(), assetList.size() - 1);
					info.seteAssetName(asset);
				}
			}
			
			ArrayList<String> list = new ArrayList<>(infoList);
			beans.put("list", list);
	
			String Specification =  "inspectionExcelList.xls";
		    String templatePath = EgovProperties.getProperty("salesExcelTemplatePath");		//cms.properties에서 가져오는 파일 경로
			String templateFileName = templatePath + Specification;
			
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ("yyyyMMdd", Locale.KOREA);
			Date currentTime = new Date ();
			String mTime = mSimpleDateFormat.format ( currentTime );
			String titleName = "점검관리_현황_";
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
	
	@RequestMapping(value = "/mes/inspection/kw_inspection_lfr.do")
	public String mesInspectiontLfr(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesInspectionVO") MesInspectionVO mesInspectionVO
			, ModelMap model) throws Exception{
		mesInspectionService.mesSignInspection(mesInspectionVO);
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		// paging
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesInspectionVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesInspectionVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesInspectionVO.getLastIndex());
		
		mesInspectionVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesInspectionVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesInspectionVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());


		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesInspectionVO.geteTopStartDate().equals("") || mesInspectionVO.geteTopStartDate() == null){
//			String temp = String.valueOf(dateFormat.format(nowDate));
//			temp = temp.substring(0,8);
//			temp += "01";
			String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
			mesInspectionVO.seteTopStartDate(temp);
		}
		if(mesInspectionVO.geteTopEndDate().equals("") || mesInspectionVO.geteTopEndDate() == null){
			mesInspectionVO.seteTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("38"); //점검구분
		model.addAttribute("gubun38List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("39");//점검주기
		model.addAttribute("gubun39List", mesGubunService.selectMesGubunCodeList(vo));
		
		
		List infoList = mesInspectionService.mesInspectiontList(mesInspectionVO);
		int totCnt = mesInspectionService.mesInspectiontListCnt(mesInspectionVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("infoList", infoList);
		model.addAttribute("paginationInfo", paginationInfo);		
		return "mes/inspection/kw_inspection_lf.tiles";
	}
	//등록
	@RequestMapping(value = "/mes/inspection/kw_inspection_if.do")
	public String MesInspectionIf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesInspectionVO") MesInspectionVO mesInspectionVO
			, ModelMap model) throws Exception{
		
		String csrfToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("csrfToken", csrfToken);
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("38"); //점검구분
		model.addAttribute("gubun38List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("39");//점검주기
		model.addAttribute("gubun39List", mesGubunService.selectMesGubunCodeList(vo));
		
		redirectAttributes.addFlashAttribute("mesInspectionVO", mesInspectionVO);
		
		return "mes/inspection/kw_inspection_if.tiles";
	}
	
	@RequestMapping(value = "/mes/inspection/kw_inspection_i.do")
	public String MesInspectionI(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, MultipartHttpServletRequest requestM
			, @ModelAttribute("mesInspectionVO") MesInspectionVO mesInspectionVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesInspectionVO.setkStaffKey(Integer.toString((staffVO.getkStaffKey())));
		mesInspectionVO.setkStaffName(staffVO.getkStaffName());
		mesInspectionService.eInspectionInfoInsert(mesInspectionVO);
		
		redirectAttributes.addFlashAttribute("mesInspectionVO", mesInspectionVO);		
		
		return "redirect:/mes/inspection/kw_inspection_lf.do";
	}
	
	

	@RequestMapping(value = "/mes/inspection/popup/kw_File_add.do")
	public String mesIMGregAddPopup(HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("mesInspectionVO") MesInspectionVO mesInspectionVO, ModelMap model) throws Exception {
		 String pageFileGubun = "N";
		 if (!"N".equals(mesInspectionVO.getePageGubun()) && !"Y".equals(mesInspectionVO.getePageGubun())) {
			 pageFileGubun = "N";
	        }else {
	        	pageFileGubun = mesInspectionVO.getePageGubun();
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
		return "mesPopup/mes/inspection/popup/kw_File_add";
	}
	
	@RequestMapping(value = "/mes/inspection/popup/kw_File_insert.do")
	public String mesIMGregAddInsert(HttpServletRequest request,
			@ModelAttribute("mesInspectionVO") MesInspectionVO mesInspectionVO, ModelMap model) throws Exception {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> files = multipartRequest.getFileMap();

		List<FileVO> result = null;
		String atchFileId = "";
		if (!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, "Ins_", 0, "", "assetUploadPath");
			atchFileId = fileMngService.insertFileInfs(result);
			mesInspectionVO.seteFileID(atchFileId);

		}
		 model.addAttribute("eGubunPage", "N");
			return "mesPopup/mes/inspection/popup/kw_File_add";
	}
	
	
	//상세페이지
	@RequestMapping(value = "/mes/inspection/kw_inspection_vf.do")
	public String mesInspectionVf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesInspectionVO") MesInspectionVO mesInspectionVO
			, ModelMap model) throws Exception{
		
		String csrfToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("csrfToken", csrfToken);
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		MesInspectionVO selInfo = 	mesInspectionService.eInspectionInfo(mesInspectionVO);
		model.addAttribute("selInfo", selInfo);

		MesInspectionVO selField = new MesInspectionVO();
		if (selInfo.geteFieldKey() == null || selInfo.geteFieldKey().equals("")) {
			selField.seteField1("필드1");
			selField.seteField2("필드2");
			selField.seteField3("필드3");
			selField.seteField4("필드4");
			selField.seteField5("필드5");
		} else {
			selField = 	mesInspectionService.selectFieldInfo(selInfo);
		}
		model.addAttribute("fieldInfo", selField);
		
		List eFileInfoList = mesInspectionService.eFileInfoList(mesInspectionVO);
		model.addAttribute("eFileInfoList", eFileInfoList);
		
		List eResultInfoList = mesInspectionService.eResultInfoList(mesInspectionVO);
		model.addAttribute("eResultInfoList", eResultInfoList);
		
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("38");
		model.addAttribute("gubun38List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("39");
		model.addAttribute("gubun39List", mesGubunService.selectMesGubunCodeList(vo));
		 
		MesSignVO mesSignVO = new MesSignVO();
		mesSignVO.setsSignTableKey(mesInspectionVO.geteInspectionKey());
		mesSignVO.setsSignTableName("INSPECTION");
		List signList = mesSignService.selectSignListTwo(mesSignVO);
		model.addAttribute("signList", signList);
		
		return "mes/inspection/kw_inspection_vf.tiles";
	}
	//상세페이지
	@RequestMapping(value = "/mes/inspection/kw_inspection_vfr.do")
	public String mesInspectionVfr(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesInspectionVO") MesInspectionVO mesInspectionVO
			, ModelMap model) throws Exception{
		mesInspectionService.mesSignInspection(mesInspectionVO);
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		MesInspectionVO selInfo = 	mesInspectionService.eInspectionInfo(mesInspectionVO);
		model.addAttribute("selInfo", selInfo);

		List eFileInfoList = mesInspectionService.eFileInfoList(mesInspectionVO);
		model.addAttribute("eFileInfoList", eFileInfoList);
		
		List eResultInfoList = mesInspectionService.eResultInfoList(mesInspectionVO);
		model.addAttribute("eResultInfoList", eResultInfoList);
		
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("38");
		model.addAttribute("gubun38List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("39");
		model.addAttribute("gubun39List", mesGubunService.selectMesGubunCodeList(vo));
		 
		MesSignVO mesSignVO = new MesSignVO();
		mesSignVO.setsSignTableKey(mesInspectionVO.geteInspectionKey());
		mesSignVO.setsSignTableName("INSPECTION");
		List signList = mesSignService.selectSignListTwo(mesSignVO);
		model.addAttribute("signList", signList);
		
		
		return "mes/inspection/kw_inspection_vf.tiles";
	}
	//상세페이지
	@RequestMapping(value = "/mes/inspection/kw_inspection_uf.do")
	public String mesInspectionUf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesInspectionVO") MesInspectionVO mesInspectionVO
			, ModelMap model) throws Exception{
		String csrfToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("csrfToken", csrfToken);
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		MesInspectionVO selInfo = 	mesInspectionService.eInspectionInfo(mesInspectionVO);
		model.addAttribute("selInfo", selInfo);
		
		MesInspectionVO selField = new MesInspectionVO();
		if (selInfo.geteFieldKey() == null || selInfo.geteFieldKey().equals("")) {
			selField.seteField1("필드1");
			selField.seteField2("필드2");
			selField.seteField3("필드3");
			selField.seteField4("필드4");
			selField.seteField5("필드5");
		} else {
			selField = 	mesInspectionService.selectFieldInfo(selInfo);
		}
		model.addAttribute("fieldInfo", selField);
		
		List eFileInfoList = mesInspectionService.eFileInfoList(mesInspectionVO);
		model.addAttribute("eFileInfoList", eFileInfoList);
		
		List eResultInfoList = mesInspectionService.eResultInfoList(mesInspectionVO);
		model.addAttribute("eResultInfoList", eResultInfoList);
		
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("38");
		model.addAttribute("gubun38List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("39");
		model.addAttribute("gubun39List", mesGubunService.selectMesGubunCodeList(vo));
		
		MesSignVO mesSignVO = new MesSignVO();
		mesSignVO.setsSignTableKey(mesInspectionVO.geteInspectionKey());
		mesSignVO.setsSignTableName("INSPECTION");
		List signList = mesSignService.selectSignListTwo(mesSignVO);
		model.addAttribute("signList", signList);
		
		return "mes/inspection/kw_inspection_uf.tiles";
	}
	@RequestMapping(value = "/mes/inspection/kw_inspection_Result.do")
	public String mesInspectionResult(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesInspectionVO") MesInspectionVO mesInspectionVO
			, ModelMap model) throws Exception{
		String csrfToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("csrfToken", csrfToken);
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		MesInspectionVO selInfo = 	mesInspectionService.eInspectionInfo(mesInspectionVO);
		model.addAttribute("selInfo", selInfo);
		
		List eFileInfoList = mesInspectionService.eFileInfoList(mesInspectionVO);
		model.addAttribute("eFileInfoList", eFileInfoList);
		
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("38");
		model.addAttribute("gubun38List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("39");
		model.addAttribute("gubun39List", mesGubunService.selectMesGubunCodeList(vo));
		
		
		return "mes/inspection/kw_inspection_Result.tiles";
	}
	
	@RequestMapping(value = "/mes/asset/kw_asset_box_inspection.do")
	public String mesAssetInfoBoxPopup(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, HttpServletResponse response
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception {
		
		String sessionToken = (String) request.getSession().getAttribute("csrfToken");
        String requestToken = request.getParameter("csrfToken");

        if (sessionToken == null || !sessionToken.equals(requestToken)) {
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
		
		
		// paging
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesAssetVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesAssetVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesAssetVO.getPageSize());
		mesAssetVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesAssetVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesAssetVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List assetList = mesAssetService.selectMesAssetInfoList(mesAssetVO);
		int totCnt = mesAssetService.selectMesAssetInfoListCnt(mesAssetVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		

		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		
		model.addAttribute("assetList", assetList);
		model.addAttribute("paginationInfo", paginationInfo);
		
		redirectAttributes.addFlashAttribute("mesAssetVO", mesAssetVO);

		return "mesPopup/mes/inspection/popup/kw_asset_info_box_inspection";
	}
	
	@RequestMapping(value = "/mes/inspection/kw_inspection_R_i.do")
	public String MesInspectionResultI(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, MultipartHttpServletRequest requestM
			, @ModelAttribute("mesInspectionVO") MesInspectionVO mesInspectionVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		
		mesInspectionService.eInspectionInfoResultInsert(mesInspectionVO);
		
		redirectAttributes.addFlashAttribute("mesInspectionVO", mesInspectionVO);		
		
		return "redirect:/mes/inspection/kw_inspection_lf.do";
	} 
	
	@RequestMapping(value = "/mes/inspection/kw_inspection_d.do")
	public String mesD(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesInspectionVO") MesInspectionVO mesInspectionVO
			, ModelMap model) throws Exception{
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo);
		
		mesInspectionService.eInspectionInfoDelete(mesInspectionVO);
		
		redirectAttributes.addFlashAttribute("mesInspectionVO", mesInspectionVO);
		
		return "redirect:/mes/inspection/kw_inspection_lf.do";
	}
	@RequestMapping(value = "/mes/inspection/kw_inspection_u.do")
	public String mesUpdate(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesInspectionVO") MesInspectionVO mesInspectionVO
			, ModelMap model) throws Exception{
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo);
		
		mesInspectionService.eInspectionInfoUpdate(mesInspectionVO);
		
		redirectAttributes.addFlashAttribute("mesInspectionVO", mesInspectionVO);
		
		return "redirect:/mes/inspection/kw_inspection_lf.do";
	}
	
  
		
	@RequestMapping(value = "/mes/issue/kw_uploadSignInspectionReasonAjax.do")
	public void mesSignStatusUpdate(HttpServletRequest request
			, @ModelAttribute("mesInspectionVO") MesInspectionVO mesInspectionVO
			, RedirectAttributes redirectAttributes
			, HttpServletResponse response
			, ModelMap model) throws Exception {

		mesInspectionService.mesUpdateSignStatus(mesInspectionVO);

		JsonHelper helper = new JsonHelper();
		Map<String,Object> map = new HashMap<String,Object>();
		helper.printJsonObject(response, "result", map);
	}
	
	
	
	// 상세보기 엑셀 다운
		@RequestMapping(value = "/mes/inspection/kw_inspection_download.do")
		public void mesExcelDownload(
			  HttpServletRequest request
			, HttpServletResponse response
			, @ModelAttribute("mesInspectionVO") MesInspectionVO mesInspectionVO
			, ModelMap model) throws Exception {
			
			Map<String, List> beans = new HashMap<String, List>();
			
			List<MesInspectionVO> info = new ArrayList<>();
			MesInspectionVO selInfo = 	mesInspectionService.eInspectionInfo(mesInspectionVO);
			info.add(selInfo);
			beans.put("info", info);
			
			
			List eFileInfoList = mesInspectionService.eFileInfoList(mesInspectionVO);
			
			if(eFileInfoList.size() == 0) {
				MesInspectionVO vo = new MesInspectionVO();
				vo.seteFileName("첨부파일이 없습니다.");
				eFileInfoList.add(vo);
			}
			ArrayList<String> file = new ArrayList<>(eFileInfoList);
			beans.put("file", file);
			
			
			List eResultInfoList = mesInspectionService.eResultInfoList(mesInspectionVO);
			
			if(eResultInfoList.size() == 0) {
				MesInspectionVO vo = new MesInspectionVO();
				vo.seteAssetType("  ");
				eResultInfoList.add(vo);
			}
			beans.put("asset", eResultInfoList);
			
		    String templatePath = EgovProperties.getProperty("salesExcelTemplatePath");
			String Specification = "inspectionDetail.xlsx";
			String templateFileName = templatePath + Specification;
			
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ("yyyyMMdd", Locale.KOREA);
			Date currentTime = new Date ();
			String mTime = mSimpleDateFormat.format ( currentTime );
		    
			String titleName = "점검관리_상세_";
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
		
		
		@RequestMapping(value = "/mes/inspection/kw_inspection_field_lf.do")
		public String mesInspectiontFieldLf(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("mesInspectionVO") MesInspectionVO mesInspectionVO
				, ModelMap model) throws Exception{

			
			MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			model.addAttribute("staffVO", staffVO);
			mesInspectionVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));

			/*페이징*/			
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(mesInspectionVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(mesInspectionVO.getRecordCountPerPage());
			paginationInfo.setPageSize(mesInspectionVO.getPageSize());
			mesInspectionVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			mesInspectionVO.setLastIndex(paginationInfo.getLastRecordIndex());
			mesInspectionVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
	
			List fieldList = mesInspectionService.selectFieldList(mesInspectionVO);
			int totCnt = mesInspectionService.selectFieldCount(mesInspectionVO);
			
			model.addAttribute("fieldList", fieldList);
			paginationInfo.setTotalRecordCount(totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			
			return "mes/inspection/kw_inspection_field_lf.tiles";
		}
		
		@RequestMapping(value = "/mes/inspection/kw_inspection_field_if.do")
		public String mesInspectiontFieldIf(HttpServletRequest request
				, @ModelAttribute("mesInspectionVO") MesInspectionVO mesInspectionVO
				, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
			MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			model.addAttribute("staffVO", staffVO);
			mesInspectionVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
			return "mes/inspection/kw_inspection_field_if.tiles";
		}
	
		@RequestMapping(value = "/mes/inspection/kw_inspection_field_i.do")
		public String mesInspectiontFieldInsert(HttpServletRequest request
				, @ModelAttribute("mesInspectionVO") MesInspectionVO mesInspectionVO
				, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
			MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			model.addAttribute("staffVO", staffVO);
			mesInspectionVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
			mesInspectionService.mesInspectionFieldInsert(mesInspectionVO);
			return "redirect:/mes/inspection/kw_inspection_field_lf.do";
		}
		
		@RequestMapping(value = "/mes/inspection/kw_inspection_field_uf.do")
		public String mesInspectiontFieldUf(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("mesInspectionVO") MesInspectionVO mesInspectionVO
				, ModelMap model) throws Exception{
			String csrfToken = UUID.randomUUID().toString();
			request.getSession().setAttribute("csrfToken", csrfToken);
			
			MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			model.addAttribute("staffVO", staffVO);
			
			MesInspectionVO fieldInfo = mesInspectionService.selectFieldInfo(mesInspectionVO);
			model.addAttribute("info", fieldInfo);
			
			return "mes/inspection/kw_inspection_field_uf.tiles";
		}
		
		@RequestMapping(value = "/mes/inspection/kw_inspection_field_u.do")
		public String mesInspectiontFieldUdate(HttpServletRequest request
				, @ModelAttribute("mesInspectionVO") MesInspectionVO mesInspectionVO
				, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
			MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			model.addAttribute("staffVO", staffVO);
			mesInspectionVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
			mesInspectionService.mesInspectionFieldUpdate(mesInspectionVO);
			return "redirect:/mes/inspection/kw_inspection_field_lf.do";
		}
		
		// 팝업
		@RequestMapping(value = "/mes/inspection/kw_inspection_field_box.do")
		public String mesFieldPop(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, HttpServletResponse response
				, @ModelAttribute("mesInspectionVO") MesInspectionVO mesInspectionVO
				, ModelMap model) throws Exception {
			
			String sessionToken = (String) request.getSession().getAttribute("csrfToken");
	        String requestToken = request.getParameter("csrfToken");

	        if (sessionToken == null || !sessionToken.equals(requestToken)) {
	            request.getRequestDispatcher("/error.jsp").forward(request, response);
	        }
			
			// paging
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(mesInspectionVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(mesInspectionVO.getRecordCountPerPage());
			paginationInfo.setPageSize(mesInspectionVO.getPageSize());
			mesInspectionVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			mesInspectionVO.setLastIndex(paginationInfo.getLastRecordIndex());
			mesInspectionVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			
			List fieldList = mesInspectionService.selectFieldList(mesInspectionVO);
			int totCnt = mesInspectionService.selectFieldCount(mesInspectionVO);
			paginationInfo.setTotalRecordCount(totCnt);
						
			
			model.addAttribute("fieldList", fieldList);
			model.addAttribute("paginationInfo", paginationInfo);
			
			redirectAttributes.addFlashAttribute("mesInspectionVO", mesInspectionVO);

			return "mesPopup/mes/inspection/popup/kw_inspection_result_field_box";
		}
}
