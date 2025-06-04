
package egovframework.rndp.mes.equipment.web;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.rndp.com.cmm.service.EgovProperties;
import egovframework.rndp.com.utl.JsonHelper;
import egovframework.rndp.mes.equipment.service.MesEquipmentService;
import egovframework.rndp.mes.equipment.service.MesEquipmentVO;
import egovframework.rndp.mes.gubun.service.MesGubunService;
import egovframework.rndp.mes.gubun.service.MesGubunVO;
import egovframework.rndp.mes.login.service.MesK_StaffVo;
import egovframework.rndp.mes.sign.service.MesSignService;
import egovframework.rndp.mes.sign.service.MesSignVO;
import egovframework.rndp.mes.user.service.MesUserService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import net.sf.jxls.transformer.XLSTransformer;

@Controller
public class MesEquipmentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MesEquipmentController.class);

	/** EgovSampleService */
	@Resource(name = "mesEquipmentService")
	private MesEquipmentService mesEquipmentService;
	
	@Resource(name = "mesSignService")
	private MesSignService mesSignService;
	
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;
	
	@Resource(name = "mesGubunService")
	private MesGubunService mesGubunService;
	
	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	
	/** EgovSampleService */
	@Resource(name = "mesUserService")
	private MesUserService mesUserService;
	
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;
	
	@RequestMapping(value = "/mes/equipment/kw_equipment_in_lf.do")
	public String mesEquipmentInLf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesEquipmentVO") MesEquipmentVO mesEquipmentVO
			, ModelMap model) throws Exception{

		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		mesEquipmentVO.setkStaffKey(String.valueOf(staffVO.getkStaffKey()));
		
		// paging
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesEquipmentVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesEquipmentVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesEquipmentVO.getPageSize());
		
		mesEquipmentVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesEquipmentVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesEquipmentVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());


		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesEquipmentVO.geteTopStartDate().equals("") || mesEquipmentVO.geteTopStartDate() == null){
//			String temp = String.valueOf(dateFormat.format(nowDate));
//			temp = temp.substring(0,8);
//			temp += "01";
			String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
			mesEquipmentVO.seteTopStartDate(temp);
		}
		if(mesEquipmentVO.geteTopEndDate().equals("") || mesEquipmentVO.geteTopEndDate() == null){
			mesEquipmentVO.seteTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("37");
		model.addAttribute("gubun37List", mesGubunService.selectMesGubunCodeList(vo));
		
		
		List assetList = mesEquipmentService.selectEquipmentList(mesEquipmentVO);
		int totCnt = mesEquipmentService.selectEquipmentListCnt(mesEquipmentVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("assetList", assetList);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "mes/equipment/kw_equipment_in_lf.tiles";
	}


	@RequestMapping(value = "/mes/equipment/kw_equipmentExcelListDwonload.do")
	public void equipmentExcelListDwonload(
			  HttpServletRequest request
			, HttpServletResponse response
			, @ModelAttribute("mesEquipmentVO") MesEquipmentVO mesEquipmentVO
			, ModelMap model) throws Exception {
			 
			    Map<String, List> beans = new HashMap<String, List>();
				

				Date nowDate = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				if(mesEquipmentVO.getTopStartDate().equals("") || mesEquipmentVO.getTopStartDate() == null){
					String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
					mesEquipmentVO.setTopStartDate(temp);
				}
				if(mesEquipmentVO.getTopEndDate().equals("") || mesEquipmentVO.getTopEndDate() == null){
					mesEquipmentVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
				}
				MesGubunVO vo = new MesGubunVO();
				 
				
				List infolist = mesEquipmentService.selectEquipmentExcelList(mesEquipmentVO);
				ArrayList<String> list = new ArrayList<>(infolist);
				beans.put("list", list);
		
		String Specification =  "equipmentExcelList.xls";
	    String templatePath = EgovProperties.getProperty("salesExcelTemplatePath");		//cms.properties에서 가져오는 파일 경로
		String templateFileName = templatePath + Specification;
		
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ("yyyyMMdd", Locale.KOREA);
		Date currentTime = new Date ();
		String mTime = mSimpleDateFormat.format ( currentTime );
		String titleName = "임시자산_반입출_";
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
	
	
	@RequestMapping(value = "/mes/equipment/kw_equipment_in_lfr.do")
	public String mesEquipmentInLfr(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesEquipmentVO") MesEquipmentVO mesEquipmentVO
			, ModelMap model) throws Exception{
		mesEquipmentService.mesSignEquipment(mesEquipmentVO);
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		// paging
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesEquipmentVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesEquipmentVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesEquipmentVO.getPageSize());
		
		mesEquipmentVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesEquipmentVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesEquipmentVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());


		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesEquipmentVO.geteTopStartDate().equals("") || mesEquipmentVO.geteTopStartDate() == null){
//			String temp = String.valueOf(dateFormat.format(nowDate));
//			temp = temp.substring(0,8);
//			temp += "01";
			String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
			mesEquipmentVO.seteTopStartDate(temp);
		}
		if(mesEquipmentVO.geteTopEndDate().equals("") || mesEquipmentVO.geteTopEndDate() == null){
			mesEquipmentVO.seteTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("37");
		model.addAttribute("gubun37List", mesGubunService.selectMesGubunCodeList(vo));
		
		
		List assetList = mesEquipmentService.selectEquipmentList(mesEquipmentVO);
		int totCnt = mesEquipmentService.selectEquipmentListCnt(mesEquipmentVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("assetList", assetList);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "mes/equipment/kw_equipment_in_lf.tiles";
	}
	//등록
	@RequestMapping(value = "/mes/equipment/kw_equipment_in_if.do")
	public String MesEquipmentIf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesEquipmentVO") MesEquipmentVO mesEquipmentVO
			, ModelMap model) throws Exception{
		String csrfToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("csrfToken", csrfToken);
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		redirectAttributes.addFlashAttribute("mesEquipmentVO", mesEquipmentVO);
		
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("37");
		model.addAttribute("gubun37List", mesGubunService.selectMesGubunCodeList(vo));
		
		
		return "mes/equipment/kw_equipment_in_if.tiles";
	}
	 
	
	@RequestMapping(value = "/mes/equipment/kw_equipment_in_i.do")
	public String MesEquipmentI(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesEquipmentVO") MesEquipmentVO mesEquipmentVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesEquipmentVO.setkStaffName(staffVO.getkStaffName());
		mesEquipmentVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
		
		mesEquipmentService.insertInfoEquipment(mesEquipmentVO);
		
		redirectAttributes.addFlashAttribute("mesEquipmentVO", mesEquipmentVO);		
		
		return "redirect:/mes/equipment/kw_equipment_in_lf.do";
	}
	
	@RequestMapping(value = "/mes/equipment/kw_equipment_in_vf.do")
	public String MesEquipmentVf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			,  @ModelAttribute("mesEquipmentVO") MesEquipmentVO mesEquipmentVO
			, ModelMap model) throws Exception{
		String csrfToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("csrfToken", csrfToken);
		
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		
		MesEquipmentVO info  = mesEquipmentService.selectEquipmentInfo(mesEquipmentVO);
		List assetList = mesEquipmentService.selectEquipmentRowItemList(mesEquipmentVO);
		model.addAttribute("info",info);
		model.addAttribute("assetList",assetList);
		
		MesSignVO mesSignVO = new MesSignVO();
		mesSignVO.setsSignTableKey(mesEquipmentVO.geteEquipmentInKey());
		mesSignVO.setsSignTableName("E_EQUIPMENT_IN");
		List signList = mesSignService.selectSignListTwo(mesSignVO);
		model.addAttribute("signList", signList);
		
		redirectAttributes.addFlashAttribute("mesEquipmentVO", mesEquipmentVO);
		return "mes/equipment/kw_equipment_in_vf.tiles";
	}
	
	@RequestMapping(value = "/mes/equipment/kw_equipment_in_vfr.do")
	public String MesEquipmentVfr(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			,  @ModelAttribute("mesEquipmentVO") MesEquipmentVO mesEquipmentVO
			, ModelMap model) throws Exception{
		String csrfToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("csrfToken", csrfToken);
		
		mesEquipmentService.mesSignEquipment(mesEquipmentVO);
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		
		MesEquipmentVO info  = mesEquipmentService.selectEquipmentInfo(mesEquipmentVO);
		List assetList = mesEquipmentService.selectEquipmentRowItemList(mesEquipmentVO);
		model.addAttribute("info",info);
		model.addAttribute("assetList",assetList);
		
		MesSignVO mesSignVO = new MesSignVO();
		mesSignVO.setsSignTableKey(mesEquipmentVO.geteEquipmentInKey());
		mesSignVO.setsSignTableName("E_EQUIPMENT_IN");
		List signList = mesSignService.selectSignListTwo(mesSignVO);
		model.addAttribute("signList", signList);
		
		redirectAttributes.addFlashAttribute("mesEquipmentVO", mesEquipmentVO);
		return "mes/equipment/kw_equipment_in_vf.tiles";
	}
	
	@RequestMapping(value = "/mes/equipment/kw_uploadSignEquipmentReasonAjax.do")
	public void mesAssetSignUpdate(HttpServletRequest request
			, @ModelAttribute("mesEquipmentVO") MesEquipmentVO mesEquipmentVO
			, RedirectAttributes redirectAttributes
			, HttpServletResponse response
			, ModelMap model) throws Exception {

		mesEquipmentService.mesUpdateSignStatus(mesEquipmentVO);

		JsonHelper helper = new JsonHelper();
		Map<String,Object> map = new HashMap<String,Object>();
		helper.printJsonObject(response, "result", map);
	}
	
	@RequestMapping(value = "/mes/equipment/kw_equipment_in_uf.do")
	public String MesEquipmentUf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			,  @ModelAttribute("mesEquipmentVO") MesEquipmentVO mesEquipmentVO
			, ModelMap model) throws Exception{
		String csrfToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("csrfToken", csrfToken);
		
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("37");
		model.addAttribute("gubun37List", mesGubunService.selectMesGubunCodeList(vo));
		
		MesEquipmentVO info  = mesEquipmentService.selectEquipmentInfo(mesEquipmentVO);
		List assetList = mesEquipmentService.selectEquipmentRowItemList(mesEquipmentVO);
		model.addAttribute("info",info);
		model.addAttribute("assetList",assetList);
		
		MesSignVO mesSignVO = new MesSignVO();
		mesSignVO.setsSignTableKey(mesEquipmentVO.geteEquipmentInKey());
		mesSignVO.setsSignTableName("E_EQUIPMENT_IN");
		List signList = mesSignService.selectSignListTwo(mesSignVO);
		model.addAttribute("signList", signList);
		
		redirectAttributes.addFlashAttribute("mesEquipmentVO", mesEquipmentVO);
		return "mes/equipment/kw_equipment_in_uf.tiles";
	}


	
	@RequestMapping(value = "/mes/equipment/kw_equipment_in_d.do")
	public String MesEquipmentD(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesEquipmentVO") MesEquipmentVO mesEquipmentVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesEquipmentVO.setkStaffName(staffVO.getkStaffName());
		mesEquipmentVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
		
		mesEquipmentService.updateEquipmentStatus(mesEquipmentVO);
		
		redirectAttributes.addFlashAttribute("mesEquipmentVO", mesEquipmentVO);		
		
		return "redirect:/mes/equipment/kw_equipment_in_lf.do";
	}
	
	
	
	
	@RequestMapping(value = "/mes/equipment/kw_equipment_in_u.do")
	public String MesEquipmentUpdate(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesEquipmentVO") MesEquipmentVO mesEquipmentVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesEquipmentVO.setkStaffName(staffVO.getkStaffName());
		mesEquipmentVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
		
		mesEquipmentService.updateEquipmentInfo(mesEquipmentVO);
		
		redirectAttributes.addFlashAttribute("mesEquipmentVO", mesEquipmentVO);		
		
		return "redirect:/mes/equipment/kw_equipment_in_lf.do";
	}
	

	@RequestMapping(value = "/mes/equipment/kw_equipment_out_lf.do")
	public String mesEquipmentOutLf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesEquipmentVO") MesEquipmentVO mesEquipmentVO
			, ModelMap model) throws Exception{

		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		// paging
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesEquipmentVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesEquipmentVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesEquipmentVO.getPageSize());
		
		mesEquipmentVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesEquipmentVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesEquipmentVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());


		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesEquipmentVO.geteTopStartDate().equals("") || mesEquipmentVO.geteTopStartDate() == null){
//			String temp = String.valueOf(dateFormat.format(nowDate));
//			temp = temp.substring(0,8);
//			temp += "01";
			String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
			mesEquipmentVO.seteTopStartDate(temp);
		}
		if(mesEquipmentVO.geteTopEndDate().equals("") || mesEquipmentVO.geteTopEndDate() == null){
			mesEquipmentVO.seteTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("37");
		model.addAttribute("gubun37List", mesGubunService.selectMesGubunCodeList(vo));
		
		
		List assetList = mesEquipmentService.selectEquipmentOutList(mesEquipmentVO);
		int totCnt = mesEquipmentService.selectEquipmentOutListCnt(mesEquipmentVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("assetList", assetList);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "mes/equipment/kw_equipment_out_lf.tiles";
	}
	

	//등록
	@RequestMapping(value = "/mes/equipment/kw_equipment_out_if.do")
	public String MesEquipmentOutIf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesEquipmentVO") MesEquipmentVO mesEquipmentVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		redirectAttributes.addFlashAttribute("mesEquipmentVO", mesEquipmentVO);
		
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("37");
		model.addAttribute("gubun37List", mesGubunService.selectMesGubunCodeList(vo));
		
		
		return "mes/equipment/kw_equipment_out_if.tiles";
	}
	
	@RequestMapping(value = "/mes/equipment/kw_equipment_box_lf.do")
	public String mesAssetInfoBoxPopup(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesEquipmentVO") MesEquipmentVO mesEquipmentVO
			, ModelMap model) throws Exception {
		
		
		// paging
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesEquipmentVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesEquipmentVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesEquipmentVO.getPageSize());
		
		mesEquipmentVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesEquipmentVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesEquipmentVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
//		List assetList = mesAssetService.selectMesAssetInfoList(mesAssetVO);
//		int totCnt = mesAssetService.selectMesAssetInfoListCnt(mesAssetVO);
		List assetList = mesEquipmentService.selectEquipmentPopList(mesEquipmentVO);
		int totCnt = mesEquipmentService.selectEquipmentPopListCnt(mesEquipmentVO);
		paginationInfo.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(10);
		
		model.addAttribute("assetList", assetList);
		model.addAttribute("paginationInfo", paginationInfo);
		
		redirectAttributes.addFlashAttribute("mesEquipmentVO", mesEquipmentVO);

		return "mesPopup/mes/equipment/popup/kw_equipment_box_lf";
	}
	
	@RequestMapping(value = "/mes/maintance/kw_getAssetInfoList.do")
	public void getAssetInfoList(HttpServletRequest request
			, HttpServletResponse response
			, @ModelAttribute("mesEquipmentVO") MesEquipmentVO mesEquipmentVO
			, ModelMap model) throws Exception {
		String result = "success";
		List dataList = null;
		try {
			dataList = mesEquipmentService.selectEquipmentInfoList(mesEquipmentVO);

		} catch (Exception e) {
			result = "error";
			e.printStackTrace();
		}
 
		JsonHelper helper = new JsonHelper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataList", dataList);
		helper.printJsonObject(response, "result", map);
	}

	
	@RequestMapping(value = "/mes/equipment/kw_equipment_out_i.do")
	public String MesEquipmentOutI(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesEquipmentVO") MesEquipmentVO mesEquipmentVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesEquipmentVO.setkStaffName(staffVO.getkStaffName());
		mesEquipmentVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
		
		mesEquipmentService.insertOutInfoEquipment(mesEquipmentVO);
		
		redirectAttributes.addFlashAttribute("mesEquipmentVO", mesEquipmentVO);		
		
		return "redirect:/mes/equipment/kw_equipment_out_lf.do";
	}
	 
	@RequestMapping(value = "/mes/equipment/kw_equipment_out_vf.do")
	public String MesEquipmentOutVf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			,  @ModelAttribute("mesEquipmentVO") MesEquipmentVO mesEquipmentVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		
		MesEquipmentVO info  = mesEquipmentService.selectEquipmentOutInfo(mesEquipmentVO);
		List assetList = mesEquipmentService.selectEquipmentOutRowItemList(mesEquipmentVO);
		model.addAttribute("info",info);
		model.addAttribute("assetList",assetList);
		redirectAttributes.addFlashAttribute("mesEquipmentVO", mesEquipmentVO);
		return "mes/equipment/kw_equipment_out_vf.tiles";
	}

	@RequestMapping(value = "/mes/equipment/kw_equipment_out_uf.do")
	public String MesEquipmentOutUf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			,  @ModelAttribute("mesEquipmentVO") MesEquipmentVO mesEquipmentVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("37");
		model.addAttribute("gubun37List", mesGubunService.selectMesGubunCodeList(vo));
		
		MesEquipmentVO info  = mesEquipmentService.selectEquipmentOutInfo(mesEquipmentVO);
		List assetList = mesEquipmentService.selectEquipmentOutRowItemList(mesEquipmentVO);
		model.addAttribute("info",info);
		model.addAttribute("assetList",assetList);
		redirectAttributes.addFlashAttribute("mesEquipmentVO", mesEquipmentVO);
		return "mes/equipment/kw_equipment_out_uf.tiles";
	}


	
	@RequestMapping(value = "/mes/equipment/kw_equipment_out_d.do")
	public String MesEquipmentOutD(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesEquipmentVO") MesEquipmentVO mesEquipmentVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesEquipmentVO.setkStaffName(staffVO.getkStaffName());
		mesEquipmentVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
		
		mesEquipmentService.updateEquipmentOutStatus(mesEquipmentVO);
		
		redirectAttributes.addFlashAttribute("mesEquipmentVO", mesEquipmentVO);		
		
		return "redirect:/mes/equipment/kw_equipment_out_lf.do";
	}
	
	
	
	
	@RequestMapping(value = "/mes/equipment/kw_equipment_out_u.do")
	public String MesEquipmentOutUpdate(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesEquipmentVO") MesEquipmentVO mesEquipmentVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesEquipmentVO.setkStaffName(staffVO.getkStaffName());
		mesEquipmentVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
		
		mesEquipmentService.updateEquipmentOutInfo(mesEquipmentVO);
		
		redirectAttributes.addFlashAttribute("mesEquipmentVO", mesEquipmentVO);		
		
		return "redirect:/mes/equipment/kw_equipment_out_lf.do";
	}

	@RequestMapping(value = "/mes/equipment/kw_eImport_update_pop.do")
	public String MesImportUpdatePop(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesEquipmentVO") MesEquipmentVO mesEquipmentVO
			, ModelMap model) throws Exception{
		
		
		
		model.addAttribute("eGubunPage", "Y");
		redirectAttributes.addFlashAttribute("mesEquipmentVO", mesEquipmentVO);		
		
		return "mesPopup/mes/equipment/popup/kw_eImport_update_pop";
	}
	
	@RequestMapping(value = "/mes/equipment/kw_eImport_update.do")
	public String MesImportUpdate(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesEquipmentVO") MesEquipmentVO mesEquipmentVO
			, ModelMap model) throws Exception {
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesEquipmentVO.setkStaffName(staffVO.getkStaffName());
		mesEquipmentVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
		mesEquipmentService.updateImportInfo(mesEquipmentVO);
//		mesAssetService.updateImportInfo(mesAssetVO);
		model.addAttribute("eGubunPage", "N");
		return "mesPopup/mes/equipment/popup/kw_eImport_update_pop";
	}
	
}
