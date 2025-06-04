package egovframework.rndp.mes.maintance.web;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.utl.sim.service.EgovNetworkState;
import egovframework.rndp.admin.env.service.EnvService;
import egovframework.rndp.com.utl.JsonHelper;
import egovframework.rndp.mes.common.service.MesCommonService;
import egovframework.rndp.mes.gubun.service.MesGubunService;
import egovframework.rndp.mes.gubun.service.MesGubunVO;
import egovframework.rndp.mes.login.service.MesK_StaffVo;
import egovframework.rndp.mes.maintance.service.MesMaintanceService;
import egovframework.rndp.mes.maintance.service.MesMaintanceVO;
import egovframework.rndp.mes.position.service.MesPositionService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rndp.mes.position.service.MesPositionVO;

import egovframework.rndp.mes.sign.service.MesSignService;

@Controller
public class MesMaintanceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MesMaintanceController.class);

	/*공통사용 서비스 common 및  Egov 공용 */
	@Resource(name = "mesCommonService")
	private MesCommonService mesCommonService;
	
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;
	
	@Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;
	/*공통사용 서비스 common 및  Egov 공용 */
	
	@Resource(name = "mesMaintanceService")
	private MesMaintanceService mesMaintanceService;

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
		
	// 안상현
	@RequestMapping(value = "/mes/maintance/kw_maintance_lf.do")
	public String mesKwMaintanceLf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesMaintanceVO") MesMaintanceVO mesMaintanceVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo);
		
		/* paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesMaintanceVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesMaintanceVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesMaintanceVO.getPageSize());

		mesMaintanceVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesMaintanceVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesMaintanceVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesMaintanceVO.getTopStartDate().equals("") || mesMaintanceVO.getTopStartDate() == null){
			String temp = String.valueOf(dateFormat.format(nowDate));
			temp = temp.substring(0,8);
			temp += "01";
			mesMaintanceVO.setTopStartDate(temp);
		}
		if(mesMaintanceVO.getTopEndDate().equals("") || mesMaintanceVO.getTopEndDate() == null){
			mesMaintanceVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		
		List maintanceList = mesMaintanceService.selectMesMaintanceList(mesMaintanceVO);
		model.addAttribute("maintanceList", maintanceList);
		
		int totCnt = mesMaintanceService.selectMesMaintanceListCnt(mesMaintanceVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("paginationInfo", paginationInfo);
		
		
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("33");
		model.addAttribute("gubun33List", mesGubunService.selectMesGubunCodeList(vo));
		 
		vo.setsGubunCateKey("34"); //장애
		model.addAttribute("gubun34List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		
		redirectAttributes.addFlashAttribute("mesMaintanceVO", mesMaintanceVO);
		
		return "mes/maintance/kw_maintance_lf.tiles";
	}
	
	
	@RequestMapping(value = "/mes/maintance/popup/mesIMGregAdd.do")
	public String mesIMGregAddPopup(HttpServletRequest request,
			@ModelAttribute("mesMaintanceVO") MesMaintanceVO mesMaintanceVO, ModelMap model) throws Exception {

		mesMaintanceVO.seteGubunPage("Y");
		
		return "mesPopup/mes/maintance/popup/kw_maintance_IMGreg";
	}
	
	// 이미지 업로드 팝업창
	@RequestMapping(value = "/mes/maintance/popup/mesIMGregAdd_i.do")
	public String mesIMGregAddInsert(HttpServletRequest request,
			@ModelAttribute("mesMaintanceVO") MesMaintanceVO mesMaintanceVO, ModelMap model) throws Exception {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> files = multipartRequest.getFileMap();

		List<FileVO> result = null;
		String atchFileId = "";
		if (!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, "maintance_", 0, "", "maintanceImagePath");
			atchFileId = fileMngService.insertFileInfs(result);
			// mesItemVO.seteIMGregName(atchFileId);

			// mesItemService.mesItemIMGfileInsert(mesItemVO);
		}
		// model.addAttribute("popClose", 1);
		model.addAttribute("atchFileId", atchFileId);
		model.addAttribute("atchFileName", mesMaintanceVO.geteIMGregName());
		mesMaintanceVO.seteGubunPage("N");
		return "mesPopup/mes/maintance/popup/kw_maintance_IMGreg";
	}
	
	// 이미지 업로드 팝업창
	@RequestMapping(value = "/mes/maintance/popup/mesIMGregAddAjax.do")
	public void mesIMGregAddInsertAjax(HttpServletRequest request,
			@ModelAttribute("mesMaintanceVO") MesMaintanceVO mesMaintanceVO
			, HttpServletResponse response
			, RedirectAttributes redirectAttributes
			, ModelMap model) throws Exception {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> files = multipartRequest.getFileMap();

		List<FileVO> result = null;
		String atchFileId = "";
		if (!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, "maintance_", 0, "", "maintanceImagePath");
			atchFileId = fileMngService.insertFileInfs(result);
		}

		JsonHelper helper = new JsonHelper();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", result);
		map.put("atchFileId", atchFileId);
		map.put("atchFileName", mesMaintanceVO.geteIMGregName());
		helper.printJsonObject(response, "result", map); 
		redirectAttributes.addFlashAttribute("mesMaintanceVO", mesMaintanceVO);
	}
	
	@RequestMapping(value = "/mes/maintance/kw_maintance_if.do")
	public String mesKwMaintanceIf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesMaintanceVO") MesMaintanceVO mesMaintanceVO
			, ModelMap model) throws Exception{
		     
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesMaintanceVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
		mesMaintanceVO.setkStaffName(String.valueOf(staffVo.getkStaffName()));
		model.addAttribute("staffVo", staffVo);
		
		
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("33"); //유형
		model.addAttribute("gubun33List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("34"); //장애
		model.addAttribute("gubun34List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		redirectAttributes.addFlashAttribute("mesMaintanceVO", mesMaintanceVO);
		
		return "mes/maintance/kw_maintance_if.tiles";
	}
	
	@RequestMapping(value = "/mes/maintance/kw_maintance_i.do")
	public String mesKwMaintanceI(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesMaintanceVO") MesMaintanceVO mesMaintanceVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesMaintanceVO.setkStaffKey(Integer.toString(staffVo.getkStaffKey()));
		mesMaintanceVO.setkStaffName(staffVo.getkStaffName());
		
		mesMaintanceService.insertMesMaintance(mesMaintanceVO, request);		
		
		redirectAttributes.addFlashAttribute("mesMaintanceVO", mesMaintanceVO);
		
		return "redirect:/mes/maintance/kw_maintance_lf.do";
	}
	
	@RequestMapping(value = "/mes/maintance/kw_maintance_vf.do")
	public String mesKwMaintanceVf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesMaintanceVO") MesMaintanceVO mesMaintanceVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo);
		
		MesMaintanceVO maintanceInfo = mesMaintanceService.selectMaintanceInfo(mesMaintanceVO);
		
		model.addAttribute("maintanceInfo", maintanceInfo);
		List<MesMaintanceVO> files = mesMaintanceService.getMaintanceFile(mesMaintanceVO);
		model.addAttribute("files", files);

		List<MesMaintanceVO> filesProcess = mesMaintanceService.getProcessFile(mesMaintanceVO);
		model.addAttribute("filesProcess", filesProcess);
		List signList = mesSignService.selectSignList(maintanceInfo.getsSignKey());
		model.addAttribute("signList", signList);
		
		// 유지 요청 파일 리스트
		try{
			FileVO fileVO = new FileVO();
			fileVO.setAtchFileId(maintanceInfo.getmMaintanceFile());
			
			List<FileVO> result = fileService.selectFileInfs(fileVO);
			model.addAttribute("fileMaintanceList", result);
		}catch(Error e){
			System.out.println(e);
		}

		// 유지 처리 파일 리스트
		try{
			FileVO fileVO = new FileVO();
			fileVO.setAtchFileId(maintanceInfo.getmProcessFile());
			
			List<FileVO> result = fileService.selectFileInfs(fileVO);
			model.addAttribute("fileProcessList", result);
		}catch(Error e){
			System.out.println(e);
		}
		
		redirectAttributes.addFlashAttribute("mesMaintanceVO", mesMaintanceVO);
		
		return "mes/maintance/kw_maintance_vf.tiles";
	}
	
	@RequestMapping(value = "/mes/maintance/kw_maintance_uf.do")
	public String mesKwMaintanceUf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesMaintanceVO") MesMaintanceVO mesMaintanceVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo);


		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("33"); //유형
		model.addAttribute("gubun33List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("34"); //장애
		model.addAttribute("gubun34List", mesGubunService.selectMesGubunCodeList(vo));
		
		EgovNetworkState newStatus = new EgovNetworkState();
		String testIPaddress = newStatus.getMyIPaddress();
		System.out.println(testIPaddress);
		
		MesMaintanceVO maintanceInfo = mesMaintanceService.selectMaintanceInfo(mesMaintanceVO);
		model.addAttribute("maintanceInfo", maintanceInfo);
		
		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(maintanceInfo.getmMaintanceFile());
		
		List<FileVO> result = fileService.selectFileInfs(fileVO);
		model.addAttribute("fileMaintanceList", result);
		
		List<MesMaintanceVO> files = mesMaintanceService.getMaintanceFile(mesMaintanceVO);
		model.addAttribute("files", files);
		
		redirectAttributes.addFlashAttribute("mesMaintanceVO", mesMaintanceVO);

		return "mes/maintance/kw_maintance_uf.tiles";
	}
	
	@RequestMapping(value = "/mes/maintance/kw_maintance_u.do")
	public String mesMaintanceU(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesMaintanceVO") MesMaintanceVO mesMaintanceVO
			, @ModelAttribute("fileVO") FileVO fileVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesMaintanceVO.setkStaffKey(Integer.toString(staffVo.getkStaffKey()));
		mesMaintanceVO.setkStaffName(staffVo.getkStaffName());
		
		mesMaintanceService.updateMesMaintance(mesMaintanceVO, fileVO, request);		
		
		redirectAttributes.addFlashAttribute("mesMaintanceVO", mesMaintanceVO);
		
		return "redirect:/mes/maintance/kw_maintance_lf.do";
	}

	
	@RequestMapping(value = "/mes/maintance/kw_maintance_d.do")
	public String mesCompanyKwCompanyD(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesMaintanceVO") MesMaintanceVO mesMaintanceVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo);
		
		mesMaintanceService.deleteMesMaintance(mesMaintanceVO);		
		
		redirectAttributes.addFlashAttribute("mesMaintanceVO", mesMaintanceVO);
		
		return "redirect:/mes/maintance/kw_maintance_lf.do";
	}
	

	
	@RequestMapping(value = "/mes/maintance/kw_process_if.do")
	public String mesKwProcessIf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesMaintanceVO") MesMaintanceVO mesMaintanceVO
			, ModelMap model) throws Exception{
		     
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo);
		
		MesMaintanceVO maintanceInfo = mesMaintanceService.selectMaintanceInfo(mesMaintanceVO);
		model.addAttribute("maintanceInfo", maintanceInfo);
		
		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(maintanceInfo.getmMaintanceFile());
		
		List<FileVO> result = fileService.selectFileInfs(fileVO);
		model.addAttribute("fileMaintanceList", result);

		List<MesMaintanceVO> files = mesMaintanceService.getMaintanceFile(mesMaintanceVO);
		model.addAttribute("files", files);
		
		redirectAttributes.addFlashAttribute("mesMaintanceVO", mesMaintanceVO);
		
		return "mes/maintance/kw_process_if.tiles";
	}
	
	@RequestMapping(value = "/mes/maintance/kw_process_i.do")
	public String mesKwProcessI(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesMaintanceVO") MesMaintanceVO mesMaintanceVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesMaintanceVO.setkStaffKey(Integer.toString(staffVo.getkStaffKey()));
		mesMaintanceVO.setkStaffName(staffVo.getkStaffName());
		
		mesMaintanceService.insertMesProcess(mesMaintanceVO, request);		
		
		redirectAttributes.addFlashAttribute("mesMaintanceVO", mesMaintanceVO);
		
		return "redirect:/mes/maintance/kw_maintance_lf.do";
	}


	@RequestMapping(value = "/mes/maintance/kw_maintance_r.do")
	public String mesKwMaintanceR(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesMaintanceVO") MesMaintanceVO mesMaintanceVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo);
		
		mesMaintanceService.requestMaintance(mesMaintanceVO);		
		
		redirectAttributes.addFlashAttribute("mesMaintanceVO", mesMaintanceVO);
		
		return "redirect:/mes/maintance/kw_maintance_lf.do";
	}


	@RequestMapping(value = "/mes/maintance/kw_maintance_rv.do")
	public String mesKwMaintanceRV(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesMaintanceVO") MesMaintanceVO mesMaintanceVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo);
		
		mesMaintanceService.setSignMaintance(mesMaintanceVO);		
		
		redirectAttributes.addFlashAttribute("mesMaintanceVO", mesMaintanceVO);
		
		return "redirect:/mes/maintance/kw_maintance_vf.do";
	}

	
	@RequestMapping(value = "/mes/maintance/kw_maintance_s.do")
	public String mesMaintanceS(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesMaintanceVO") MesMaintanceVO mesMaintanceVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo);
		
		mesMaintanceService.setSignMaintance(mesMaintanceVO);		
		
		return "redirect:/mes/maintance/kw_maintance_lf.do";
	}

	

	@RequestMapping(value = "/mes/maintance/kw_process_uf.do")
	public String mesKwProcessUf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesMaintanceVO") MesMaintanceVO mesMaintanceVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo);
		
		MesMaintanceVO maintanceInfo = mesMaintanceService.selectMaintanceInfo(mesMaintanceVO);
		model.addAttribute("maintanceInfo", maintanceInfo);

		List<MesMaintanceVO> files = mesMaintanceService.getMaintanceFile(mesMaintanceVO);
		model.addAttribute("files", files);
		
		List<MesMaintanceVO> filesProcess = mesMaintanceService.getProcessFile(mesMaintanceVO);
		model.addAttribute("filesProcess", filesProcess);
		
		List signList = mesSignService.selectSignList(maintanceInfo.getsSignKey());
		model.addAttribute("signList", signList);
		
		// 유지 요청 파일 리스트
		try{
			FileVO fileVO = new FileVO();
			fileVO.setAtchFileId(maintanceInfo.getmMaintanceFile());
			
			List<FileVO> result = fileService.selectFileInfs(fileVO);
			model.addAttribute("fileMaintanceList", result);
		}catch(Error e){
			System.out.println(e);
		}

		// 유지 처리 파일 리스트
		try{
			FileVO fileVO = new FileVO();
			fileVO.setAtchFileId(maintanceInfo.getmProcessFile());
			
			List<FileVO> result = fileService.selectFileInfs(fileVO);
			model.addAttribute("fileProcessList", result);
		}catch(Error e){
			System.out.println(e);
		}
		
		redirectAttributes.addFlashAttribute("mesMaintanceVO", mesMaintanceVO);

		return "mes/maintance/kw_process_uf.tiles";
	}
	
	@RequestMapping(value = "/mes/maintance/kw_process_u.do")
	public String mesProcessU(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesMaintanceVO") MesMaintanceVO mesMaintanceVO
			, @ModelAttribute("fileVO") FileVO fileVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesMaintanceVO.setkStaffKey(Integer.toString(staffVo.getkStaffKey()));
		mesMaintanceVO.setkStaffName(staffVo.getkStaffName());
		mesMaintanceVO.setkPositionName(staffVo.getkPositionName());
		
		mesMaintanceService.updateMesProcess(mesMaintanceVO, fileVO, request);		
		
		redirectAttributes.addFlashAttribute("mesMaintanceVO", mesMaintanceVO);
		
		return "redirect:/mes/maintance/kw_maintance_lf.do";
	}

	
	@RequestMapping(value = "/mes/maintance/kw_process_d.do")
	public String mesProcessD(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesMaintanceVO") MesMaintanceVO mesMaintanceVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo);
		
		mesMaintanceService.updateMesProcessNull(mesMaintanceVO);		
		
		redirectAttributes.addFlashAttribute("mesMaintanceVO", mesMaintanceVO);
		
		return "redirect:/mes/maintance/kw_maintance_lf.do";
	}


	@RequestMapping(value = "/mes/maintance/kw_getCateListAjax.do")
	public void getCateList(HttpServletRequest request
			, HttpServletResponse response
			, @ModelAttribute("mesMaintanceVO") MesMaintanceVO mesMaintanceVO
			, ModelMap model) throws Exception {
		
		String result = "success";
		List dataList = null;
		try {
			MesPositionVO mesPositionVO = new MesPositionVO();
			mesPositionVO.setkPositionUpKeySave(mesMaintanceVO.getkPositionUpKey());
			dataList = mesPositionService.selectUpdatePositionList(mesPositionVO);

		} catch (Exception e) {
			result = "error";
			e.printStackTrace();
		}

		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId("");

		JsonHelper helper = new JsonHelper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		map.put("dataList", dataList);
		helper.printJsonObject(response, "result", map);
	}
	
	
	@RequestMapping(value = "/mes/maintance/kw_maintance_box_lf.do")
	public String mesMaintanceInfoBoxPopup(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesMaintanceVO") MesMaintanceVO mesMaintanceVO
			, ModelMap model) throws Exception {
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo);
		
		/* paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesMaintanceVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesMaintanceVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesMaintanceVO.getPageSize());

		mesMaintanceVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesMaintanceVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesMaintanceVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesMaintanceVO.getTopStartDate().equals("") || mesMaintanceVO.getTopStartDate() == null){
			String temp = String.valueOf(dateFormat.format(nowDate));
			temp = temp.substring(0,8);
			temp += "01";
			mesMaintanceVO.setTopStartDate(temp);
		}
		if(mesMaintanceVO.getTopEndDate().equals("") || mesMaintanceVO.getTopEndDate() == null){
			mesMaintanceVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		
		List maintanceList = mesMaintanceService.selectMesMaintancePopList(mesMaintanceVO);
		model.addAttribute("maintanceList", maintanceList);
		
		int totCnt = mesMaintanceService.selectMesMaintancePopListCnt(mesMaintanceVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("paginationInfo", paginationInfo);
		
		
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("33"); //유형
		model.addAttribute("gubun33List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("34"); //장애
		model.addAttribute("gubun34List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		
		
		
		redirectAttributes.addFlashAttribute("mesMaintanceVO", mesMaintanceVO);
		return "mesPopup/mes/maintance/popup/kw_maintance_info_box";
	}
	
	@RequestMapping(value = "/mes/maintance/kw_getMaintancenfoList.do")
	public void getMaintanceInfoList(HttpServletRequest request
			, HttpServletResponse response
			, @ModelAttribute("mesMaintanceVO") MesMaintanceVO mesMaintanceVO
			, ModelMap model) throws Exception {
		String result = "success";
		List dataList = null;
		try {
			dataList = mesMaintanceService.selectMaintanceInfoList(mesMaintanceVO);

		} catch (Exception e) {
			result = "error";
			e.printStackTrace();
		}
 
		JsonHelper helper = new JsonHelper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataList", dataList);
		helper.printJsonObject(response, "result", map);
	}
	
	
}
