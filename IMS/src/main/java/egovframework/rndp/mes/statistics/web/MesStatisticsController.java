package egovframework.rndp.mes.statistics.web;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.rndp.com.cmm.service.DefultVO;
import egovframework.rndp.mes.statistics.service.MesStatisticsVO;
import egovframework.rndp.mes.gubun.service.MesGubunService;
import egovframework.rndp.mes.gubun.service.MesGubunVO;
import egovframework.rndp.mes.login.service.MesK_StaffVo;
import egovframework.rndp.mes.statistics.service.MesStatisticsService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class MesStatisticsController {
	
	@Resource(name = "mesStatisticsService")
	private MesStatisticsService mesStatisticsService;
	
	@Resource(name = "mesGubunService")
	private MesGubunService mesGubunService;

	
	@RequestMapping(value = "/mes/statistics/kw_statistics_lf.do")
	public String mesStatisticsLf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesStatisticsVO") MesStatisticsVO mesStatisticsVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
		model.addAttribute("staffVo", staffVo);
		
		/* paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesStatisticsVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesStatisticsVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesStatisticsVO.getPageSize());

		mesStatisticsVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesStatisticsVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesStatisticsVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		mesStatisticsVO = (MesStatisticsVO) DefultVO.getSearch(mesStatisticsVO);
		
		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesStatisticsVO.getTopStartDate().equals("") || mesStatisticsVO.getTopStartDate() == null){
//			String temp = String.valueOf(dateFormat.format(nowDate));
//			temp = temp.substring(0,8);
//			temp += "01";
			String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
			mesStatisticsVO.setTopStartDate(temp);
		}
		if(mesStatisticsVO.getTopEndDate().equals("") || mesStatisticsVO.getTopEndDate() == null){
			mesStatisticsVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		
//		List maintanceMemList = mesStatisticsService.selectMesMaintanceMemList(mesStatisticsVO);
//		model.addAttribute("maintanceMemList", maintanceMemList);

//		List maintanceSrList = mesStatisticsService.selectMesMaintanceSrList(mesStatisticsVO);
//		model.addAttribute("maintanceSrList", maintanceSrList);

//		List statisticsList = mesStatisticsService.selectMesStatisticsList(mesStatisticsVO);
//		model.addAttribute("statisticsList", statisticsList);
		
		List firstValuList  = mesStatisticsService.firstValuList(mesStatisticsVO);
		model.addAttribute("firstValuList", firstValuList);
		
		  String startDateStr = mesStatisticsVO.getTopStartDate();
	      String endDateStr = mesStatisticsVO.getTopEndDate();
	      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
	        LocalDate endDate = LocalDate.parse(endDateStr, formatter);
         long monthsBetween = ChronoUnit.MONTHS.between(startDate, endDate);
         List secondValuList  = mesStatisticsService.secondValuList(mesStatisticsVO);
         
         model.addAttribute("secondValuList", secondValuList);
         
         
         
         
         
         
		model.addAttribute("paginationInfo", paginationInfo);
		
		redirectAttributes.addFlashAttribute("mesStatisticsVO", mesStatisticsVO);
		
		return "mes/statistics/kw_mstatistics_lf.tiles";
	}

	@RequestMapping(value = "/mes/statistics/kw_asset_lf.do")
	public String mesAssetLf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesStatisticsVO") MesStatisticsVO mesStatisticsVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
		model.addAttribute("staffVo", staffVo);
		
		/* paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesStatisticsVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesStatisticsVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesStatisticsVO.getPageSize());
		
		mesStatisticsVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesStatisticsVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesStatisticsVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		mesStatisticsVO = (MesStatisticsVO) DefultVO.getSearch(mesStatisticsVO);
		
		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		

		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("37");
		model.addAttribute("gubun37List", mesGubunService.selectMesGubunCodeList(vo));
		
		if(mesStatisticsVO.getTopStartDate().equals("") || mesStatisticsVO.getTopStartDate() == null){
//			String temp = String.valueOf(dateFormat.format(nowDate));
//			temp = temp.substring(0,8);
//			temp += "01";
			String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
			mesStatisticsVO.setTopStartDate(temp);
		}
		if(mesStatisticsVO.getTopEndDate().equals("") || mesStatisticsVO.getTopEndDate() == null){
			mesStatisticsVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		
		
		List assetList = mesStatisticsService.selectAssetList(mesStatisticsVO);
		model.addAttribute("assetList", assetList);
		
		List assetmcList = mesStatisticsService.selectAssetMcList(mesStatisticsVO);
		model.addAttribute("assetmcList", assetmcList);
		
		List ieList = mesStatisticsService.selectIEList(mesStatisticsVO);
		model.addAttribute("ieList", ieList);
		
	
		
		model.addAttribute("paginationInfo", paginationInfo);
		
		redirectAttributes.addFlashAttribute("mesStatisticsVO", mesStatisticsVO);
		
		return "mes/statistics/kw_asset_lf.tiles";
	}

	// 보유자산 통계
	@RequestMapping(value = "/mes/statistics/kw_asset_us_lf.do")
	public String mesAssetUsLf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesStatisticsVO") MesStatisticsVO mesStatisticsVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
		model.addAttribute("staffVo", staffVo);
		
		/* paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesStatisticsVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesStatisticsVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesStatisticsVO.getPageSize());
		
		mesStatisticsVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesStatisticsVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesStatisticsVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		mesStatisticsVO = (MesStatisticsVO) DefultVO.getSearch(mesStatisticsVO);
		
		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesStatisticsVO.getTopStartDate().equals("") || mesStatisticsVO.getTopStartDate() == null){
//			String temp = String.valueOf(dateFormat.format(nowDate));
//			temp = temp.substring(0,8);
//			temp += "01";
			String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
			mesStatisticsVO.setTopStartDate(temp);
		}
		if(mesStatisticsVO.getTopEndDate().equals("") || mesStatisticsVO.getTopEndDate() == null){
			mesStatisticsVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		
//		List assetList = mesStatisticsService.selectAssetUsList(mesStatisticsVO);
//		model.addAttribute("assetList", assetList);
//		
//		List assetmcList = mesStatisticsService.selectAssetUsMcList(mesStatisticsVO);
//		model.addAttribute("assetmcList", assetmcList);
//		
//		List ieList = mesStatisticsService.selectIEUsList(mesStatisticsVO);
//		model.addAttribute("ieList", ieList);
		List iMakerList = mesStatisticsService.eAssetMakerList(mesStatisticsVO);
		model.addAttribute("iMakerList", iMakerList);
		
		List iTypeList = mesStatisticsService.eAssetTypeList(mesStatisticsVO);
		model.addAttribute("iTypeList", iTypeList);
		
		model.addAttribute("paginationInfo", paginationInfo);
		
		redirectAttributes.addFlashAttribute("mesStatisticsVO", mesStatisticsVO);
		
		return "mes/statistics/kw_asset_us_lf.tiles";
	}
	
	// 임시자산 통계
	@RequestMapping(value = "/mes/statistics/kw_asset_tmp_lf.do")
	public String mesAssetUsTmpLf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesStatisticsVO") MesStatisticsVO mesStatisticsVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
		model.addAttribute("staffVo", staffVo);
		
		/* paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesStatisticsVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesStatisticsVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesStatisticsVO.getPageSize());
		
		mesStatisticsVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesStatisticsVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesStatisticsVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		mesStatisticsVO = (MesStatisticsVO) DefultVO.getSearch(mesStatisticsVO);
		
		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesStatisticsVO.getTopStartDate().equals("") || mesStatisticsVO.getTopStartDate() == null){
//			String temp = String.valueOf(dateFormat.format(nowDate));
//			temp = temp.substring(0,8);
//			temp += "01";
			String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
			mesStatisticsVO.setTopStartDate(temp);
		}
		if(mesStatisticsVO.getTopEndDate().equals("") || mesStatisticsVO.getTopEndDate() == null){
			mesStatisticsVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		
//		List assetList = mesStatisticsService.selectAssetUsList(mesStatisticsVO);
//		model.addAttribute("assetList", assetList);
//		
//		List assetmcList = mesStatisticsService.selectAssetUsMcList(mesStatisticsVO);
//		model.addAttribute("assetmcList", assetmcList);
//		
//		List ieList = mesStatisticsService.selectIEUsList(mesStatisticsVO);
//		model.addAttribute("ieList", ieList);
		List iMakerList = mesStatisticsService.eAssetTmpMakerList(mesStatisticsVO);
		model.addAttribute("iMakerList", iMakerList);
		
		List iTypeList = mesStatisticsService.eAssetTmpTypeList(mesStatisticsVO);
		model.addAttribute("iTypeList", iTypeList);
		
		model.addAttribute("paginationInfo", paginationInfo);
		
		redirectAttributes.addFlashAttribute("mesStatisticsVO", mesStatisticsVO);
		
		return "mes/statistics/kw_asset_tmp_lf.tiles";
	}

	@RequestMapping(value = "/mes/statistics/kw_outputs_lf.do")
	public String mesOutputsLf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesStatisticsVO") MesStatisticsVO mesStatisticsVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");        
		model.addAttribute("staffVo", staffVo);
		
		/* paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesStatisticsVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesStatisticsVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesStatisticsVO.getPageSize());
		
		mesStatisticsVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesStatisticsVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesStatisticsVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		mesStatisticsVO = (MesStatisticsVO) DefultVO.getSearch(mesStatisticsVO);
		
		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesStatisticsVO.getTopStartDate().equals("") || mesStatisticsVO.getTopStartDate() == null){
//			String temp = String.valueOf(dateFormat.format(nowDate));
//			temp = temp.substring(0,8);
//			temp += "01";
			String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
			mesStatisticsVO.setTopStartDate(temp);
		}
		if(mesStatisticsVO.getTopEndDate().equals("") || mesStatisticsVO.getTopEndDate() == null){
			mesStatisticsVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		
//		List outputsyList = mesStatisticsService.selectOutputYsList(mesStatisticsVO);
		model.addAttribute("outputsyList", null);
		
//		List outputsmList = mesStatisticsService.selectOutputMsList(mesStatisticsVO);
		model.addAttribute("outputsmList", null);
		
//		List outputsmemList = mesStatisticsService.selectOutputsMemList(mesStatisticsVO);
		model.addAttribute("outputsmemList", null);
		List outputList = mesStatisticsService.selectOutputList(mesStatisticsVO);
		model.addAttribute("outputList", outputList);
		List projectList = mesStatisticsService.selectProjectList(mesStatisticsVO);
		model.addAttribute("projectList", projectList);
		
		
		model.addAttribute("paginationInfo", paginationInfo);
		
		redirectAttributes.addFlashAttribute("mesStatisticsVO", mesStatisticsVO);
		
		return "mes/statistics/kw_output_lf.tiles";
	}
}
