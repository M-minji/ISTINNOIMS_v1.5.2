package egovframework.rndp.mes.main.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.rndp.admin.env.service.EnvService;
import egovframework.rndp.admin.env.service.EnvVO;
import egovframework.rndp.admin.intra.staff.service.StaffIntraService;
import egovframework.rndp.admin.intra.staff.service.StaffMenuAuthVO;
import egovframework.rndp.admin.menu.service.MenuBeanVO;
import egovframework.rndp.com.utl.MatrixUtil;
import egovframework.rndp.mes.asset.service.MesAssetService;
import egovframework.rndp.mes.asset.service.MesAssetVO;
import egovframework.rndp.mes.common.service.MesCommonService;
import egovframework.rndp.mes.equipment.service.MesEquipmentVO;
import egovframework.rndp.mes.issue.service.MesIssueService;
import egovframework.rndp.mes.issue.service.MesIssueVO;
import egovframework.rndp.mes.login.service.MesK_StaffVo;
import egovframework.rndp.mes.login.service.MesLoginService;
import egovframework.rndp.mes.staff.service.MesStaffVO;
import egovframework.rndp.mes.user.service.MesUserService;
import egovframework.rndp.mes.user.service.MesUserVO;


/**
 * 메인 
 * 필수 컨트롤러이므로 모듈화 예외 
 * @author rndp-21
 *
 */
@Controller
@SessionAttributes({"mesTopMenu", "mesAllRefMenu"})
public class MesMainController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MesMainController.class);

	
	
	/*공통사용 서비스 common 및  Egov 공용 */
	@Resource(name = "mesCommonService")
	private MesCommonService mesCommonService;
	
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;
	
	@Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;
	/*공통사용 서비스 common 및  Egov 공용 */
	
	@Resource(name = "mUtil")
	private MatrixUtil mUtil;
	
	@Resource(name="staffIntraService")
	private StaffIntraService staffIntraService;
	/*
	@Resource(name="mesApprovalService")
	private MesApprovalService mesApprovalService;
	*/
	@Resource(name="mesLoginService")
	private MesLoginService mesLoginService;
	
	@Resource(name="envService")
	private EnvService envService;

	
	@Resource(name = "mesIssueService")
	private MesIssueService mesIssueService;
	
	@Resource(name = "mesAssetService")
	private MesAssetService mesAssetService;
	
	
	@RequestMapping(value = "/mes/main.do")
	public String mesMain(HttpServletRequest request
			, @ModelAttribute("staffVo") MesStaffVO vo
			,  RedirectAttributes redirectAttributes
			, ModelMap model)
			throws Exception {

		LOGGER.debug("MesMainController log");
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("staff");
		
		StaffMenuAuthVO staffMenuAuthVO = new StaffMenuAuthVO();
		staffMenuAuthVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
		List staffTopMenuList = staffIntraService.selectStaffTopMenuList(staffMenuAuthVO);
		List staffMenuList = staffIntraService.selectStaffMenuList(staffMenuAuthVO);
		List staffMenuList2 = staffIntraService.selectStaffTopMenuList2(staffMenuAuthVO);
		 
		//mes 그룹
		int groupKey = 1;
				
		ArrayList<MenuBeanVO> mesTopMenu = new ArrayList<MenuBeanVO>();
		String topMenu = "";
		//로그인별 메뉴 조회
		mesTopMenu = mUtil.getIntraRootMenuList(groupKey, staffTopMenuList);
		// 최상위 메뉴정보 세션에 저장 (항상 사용하는 정보이니)
		request.getSession().setAttribute("mesTopMenu", mesTopMenu);
		model.addAttribute("mesTopMenu", mesTopMenu);
		model.addAttribute("mesLeftMenu", mesTopMenu);

	
		ArrayList<MenuBeanVO> mesAllRefMenu = new ArrayList<MenuBeanVO>();
		mesAllRefMenu = mUtil.getIntraRootMenuList3(groupKey, staffMenuList2);
		request.getSession().setAttribute("mesAllRefMenu", mesAllRefMenu);
		model.addAttribute("mesAllRefMenu", mesAllRefMenu);

		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		String formatDate = dateFormat.format(nowDate); 
		model.addAttribute("formatDate", formatDate);


		Date newNowDate = new Date();
		SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		if(vo.getTopStartDate().equals("") || vo.getTopStartDate() == null){
//			String temp = String.valueOf(newDateFormat.format(nowDate));
//			temp = temp.substring(0,8);
//			temp += "01";
			// 이번달 1일부터
//			String temp = newDateFormat.format(newNowDate).substring(0, 8) + "01";
			// 이번년도 1월 1일부터
			String temp = newDateFormat.format(newNowDate).substring(0, 5) + "01-01";
			vo.setTopStartDate(temp);
		}
		if(vo.getTopEndDate().equals("") || vo.getTopEndDate() == null){
			vo.setTopEndDate(String.valueOf(newDateFormat.format(nowDate)));
		}

		model.addAttribute("vo", vo);
		staffVo.setkMenuKey("0");
		staffVo.setkMenuRoot("0");
		staffVo.setkLogGubun("Main login");
		staffVo.setkLogIp(request.getRemoteAddr());
		mesLoginService.mesLogInsert(staffVo);
		 
		
		//현황 
		//장애관리 통계
		MesIssueVO mesIssueVO = new MesIssueVO();
		mesIssueVO.setTopStartDate(vo.getTopStartDate());
		mesIssueVO.setTopEndDate(vo.getTopEndDate());
		List mainIssueList = mesIssueService.eMainIssueInfoList(mesIssueVO);
		model.addAttribute("mainIssueList", mainIssueList);
		MesIssueVO mainIssueInfo = mesIssueService.eMainIssueInfo(mesIssueVO);
		model.addAttribute("mainIssueInfo", mainIssueInfo);
		String tData = newDateFormat.format(newNowDate).substring(0, 7);
		String year = tData.substring(0, 4); // 연도 부분 추출
        String month = tData.substring(5, 7); // 월 부분 추출
        String displayDate  = year + "년 " + month + "월";
		model.addAttribute("displayDate", displayDate); 
		
		mesIssueVO.seteRequestDate(tData);
		MesIssueVO mainIssueTotal = mesIssueService.eMainTotalsInfo(mesIssueVO);
		model.addAttribute("mainIssueTotal", mainIssueTotal); 
		
		
		
		
		//대상장비 등록현황 그래프 유형별
		MesAssetVO mesAssetVO = new MesAssetVO();
		mesAssetVO.setTopStartDate(vo.getTopStartDate());
		mesAssetVO.setTopEndDate(vo.getTopEndDate());
		mesAssetVO.setkStaffKey(String.valueOf(staffVo.getkStaffKey()));
		List assetMakerList = mesAssetService.eMainAssetMakerInfoList(mesAssetVO);
		model.addAttribute("assetMakerList", assetMakerList);
		List assetTypeList = mesAssetService.eMainAssetTypeInfoList(mesAssetVO);
		model.addAttribute("assetTypeList", assetTypeList);
		
	 
		//EoS/EoL/내구년수
		List assetEosList = mesAssetService.mainAssetEosList(mesAssetVO);
		model.addAttribute("assetEosList", assetEosList);
		List assetEolList = mesAssetService.mainAssetEolList(mesAssetVO);
		model.addAttribute("assetEolList", assetEolList);
		
		//라이선스
		List mainSoftwareList = mesAssetService.mainSoftwareList(mesAssetVO);
		model.addAttribute("mainSoftwareList", mainSoftwareList);
		//노후장비 이전
//		List mainLifeStatusList = mesAssetService.mainAssetLifeStatusList(mesAssetVO);
		//노후장비 변경
		List mainLifeStatusList = mesAssetService.mainAssetLifeStatusNewList(mesAssetVO);
		model.addAttribute("mainLifeStatusList", mainLifeStatusList);
		
		List accessInfoList = mesAssetService.accessInfoList(mesAssetVO);
		
		for(int i=0; i<accessInfoList.size(); i++){
			MesAssetVO eVO = (MesAssetVO)accessInfoList.get(i);	
			if("766".equals(eVO.getSearchTypeSet1())){				 
				model.addAttribute("ass766", eVO.getSearchTypeSet2() );
			}else if("763".equals(eVO.getSearchTypeSet1())) {			 
				model.addAttribute("ass763", eVO.getSearchTypeSet2() );
			}else if("758".equals(eVO.getSearchTypeSet1())) {			 
				model.addAttribute("ass758", eVO.getSearchTypeSet2() );
			}else if("746".equals(eVO.getSearchTypeSet1())) {			 
				model.addAttribute("ass746", eVO.getSearchTypeSet2() );
			}else if("764".equals(eVO.getSearchTypeSet1())) {			 
				model.addAttribute("ass764", eVO.getSearchTypeSet2() );
			}else if("742".equals(eVO.getSearchTypeSet1())) {			 
				model.addAttribute("ass742", eVO.getSearchTypeSet2() );
			}else if("755".equals(eVO.getSearchTypeSet1())) {			 
				model.addAttribute("ass755", eVO.getSearchTypeSet2() );
			}else if("754".equals(eVO.getSearchTypeSet1())) {			 
				model.addAttribute("ass754", eVO.getSearchTypeSet2() );
			}else if("756".equals(eVO.getSearchTypeSet1())) {			 
				model.addAttribute("ass756", eVO.getSearchTypeSet2() );
			}else if("767".equals(eVO.getSearchTypeSet1())) {			 
				model.addAttribute("ass767", eVO.getSearchTypeSet2() );
			}
			
		}
		
		
		return "mes/main.tiles";
	}
	
	/**
	 * @param request
	 * @param staffVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mes/eLog.do")
	public String mesLog(HttpServletRequest request
			, @ModelAttribute("staffVo") MesK_StaffVo staffVo
			, ModelMap model)
			throws Exception {
		boolean eIdCheck = true;
//		if(staffVo.getkStaffId().equals("dong")  
//				|| staffVo.getkStaffId().equals("rndp") ){
//			eIdCheck =false;
//		}
//		
//		if(eIdCheck){
//			return "mes/main.tiles";
//		}
		
		
		Date d = new Date();
		SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");
	 
		if(staffVo.getePageGubun().equals("")
			|| staffVo.getePageGubun()==null){
			staffVo.setePageGubun("1");
		}
		if(staffVo.geteTopStartDate().equals("")
			|| staffVo.geteTopStartDate()==null){
			Date newD = new Date ( d.getTime ( ) - (long) ( 1000 * 60 * 60 * 24 * 10 ));
			staffVo.seteTopStartDate(String.valueOf(today.format(newD)));
		}
		if(staffVo.geteTopEndDate().equals("")
				|| staffVo.geteTopEndDate()==null){
			staffVo.seteTopEndDate(String.valueOf(today.format(d)));
		}
		
		//조회기간 만들기
		MesK_StaffVo vo = new MesK_StaffVo();
		vo.seteTopStartDate(staffVo.geteTopStartDate());
		vo.seteTopEndDate(staffVo.geteTopEndDate());
		ArrayList<String> dateList = new ArrayList<String>();
		//List<MesJejikVO> dateList = new ArrayList<MesJejikVO>();
		SimpleDateFormat dateInfo = new SimpleDateFormat("yyyy-MM-dd");
			Date eStartDate = dateInfo.parse(vo.geteTopStartDate()); 
			Date eEndDate = dateInfo.parse(vo.geteTopEndDate()); 
			Calendar cSdate = Calendar.getInstance() ;
			Calendar cEdate = Calendar.getInstance() ;
			cSdate.setTime(eStartDate);
			cEdate.setTime(eEndDate);
		//조회기간 List
		while(cSdate.compareTo(cEdate)!=1){
			vo.seteTopStartDate(dateInfo.format(cSdate.getTime()));
			dateList.add(dateInfo. format(cSdate.getTime()));
			cSdate.add(Calendar.DATE,1);
		}
		model.addAttribute("dateList", dateList);	
		
		List wIdText = mesLoginService.inLogWorkerIdTextList(staffVo);
		List wIdList = mesLoginService.inLogWorkerIdList(staffVo);
		List wMenuList = mesLoginService.inLogWorkerMenuList(staffVo);

		List eALLofMenuList = mesLoginService.eALLofMenuList();
		
		List eMenuLogDataList = mesLoginService.eMenuLogDataList(staffVo);
		
		model.addAttribute("wIdText", wIdText);
		model.addAttribute("wIdList", wIdList);
		model.addAttribute("wMenuList", wMenuList);
		model.addAttribute("eALLofMenuList", eALLofMenuList);
		model.addAttribute("eMenuLogDataList", eMenuLogDataList);
		model.addAttribute("eMenuLogDataCnt", eMenuLogDataList.size());
		
		//그래프  사용자 합계 기간
		List eMenuLog_StaffSum = mesLoginService.eMenuLogStaffSum(staffVo);
		List eALLofMenuSumList = mesLoginService.eALLofMenuAndSumList(staffVo);
		model.addAttribute("eMenuLog_StaffSum", eMenuLog_StaffSum);
		model.addAttribute("eALLofMenuListCnt", eALLofMenuList.size());
		model.addAttribute("eALLofMenuSumList", eALLofMenuSumList);
		return "mes/eLog.tiles";
	}
	
	
	
}

