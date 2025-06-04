package egovframework.rndp.mes.machine.web;

import egovframework.rndp.mes.login.service.MesK_StaffVo;
import egovframework.rndp.mes.machine.service.MesMachineService;
import egovframework.rndp.mes.machine.service.MesMachineVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class MesMachineControllere {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MesMachineControllere.class);
	
	@Resource(name = "mesMachineService")
	private MesMachineService mesMachineService;
	
	@RequestMapping(value = "/mes/machine/kw_machine_lf.do")
	public String mesMachineLf(HttpServletRequest request
			, @ModelAttribute("mesMachineVO") MesMachineVO mesMachineVO
			, ModelMap model) throws Exception {
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");	
		model.addAttribute("staffVo", staffVo);
		
		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesMachineVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesMachineVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesMachineVO.getPageSize());
		
		mesMachineVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesMachineVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesMachineVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		
		Date d = new Date();
		
		int year = d.getYear();
		int month = d.getMonth();
		
		Date firstDay = new Date(year,month,1);
		Date lastDay = new Date(year,month+1,0);
		
		SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");

		
		if(mesMachineVO.getTopStartDate().equals("")
			|| mesMachineVO.getTopStartDate()==null){
			mesMachineVO.setTopStartDate(String.valueOf(today.format(firstDay)));
		}
		if(mesMachineVO.getTopEndDate().equals("")
			|| mesMachineVO.getTopEndDate()==null){
			mesMachineVO.setTopEndDate(String.valueOf(today.format(lastDay)));
		}

		if(("").equals(mesMachineVO.getAdGubun())|| null ==mesMachineVO.getAdGubun()){
			mesMachineVO.setAdGubun("ad0");
		}
		List list = mesMachineService.selectYonjupProd(mesMachineVO);
		model.addAttribute("list", list);

		int totCnt = mesMachineService.selectYonjupProdCnt(mesMachineVO);
		paginationInfo.setTotalRecordCount(totCnt);

		
		
		
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "mes/machine/kw_machine_lf.tiles";
	}



	@RequestMapping(value = "/mes/machine/kw_machine_vf.do")
	public String mesMachineVf(HttpServletRequest request
			, @ModelAttribute("mesMachineVO") MesMachineVO mesMachineVO
			, ModelMap model) throws Exception {

		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVo", staffVo);

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesMachineVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesMachineVO.getRecordCountPerPage());
		// int 타입인데 ad0을 어떻게 넣음
		paginationInfo.setPageSize(mesMachineVO.getPageSize());

		mesMachineVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesMachineVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesMachineVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());


		//일별생산수량집계현황-그래프
		List list = mesMachineService.selectAfterprocessPerDay(mesMachineVO);
		List list2 = mesMachineService.selectAfterprocessPerDay2(mesMachineVO);
		List list3 = mesMachineService.selectAfterprocessPerDay3(mesMachineVO);
		model.addAttribute("list", list);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);

		
		model.addAttribute("paginationInfo", paginationInfo);

		return "mes/machine/kw_machine_vf.tiles";
	}

}
