
package egovframework.rndp.mes.asset.web;

import java.io.File;
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
import egovframework.com.utl.sim.service.EgovNetworkState;
import egovframework.rndp.com.cmm.service.EgovProperties;
import egovframework.rndp.com.utl.JsonHelper;
import egovframework.rndp.mes.asset.service.MesAssetService;
import egovframework.rndp.mes.asset.service.MesAssetVO;
import egovframework.rndp.mes.gubun.service.MesGubunService;
import egovframework.rndp.mes.gubun.service.MesGubunVO;
import egovframework.rndp.mes.login.service.MesK_StaffVo;
import egovframework.rndp.mes.sign.service.MesSignService;
import egovframework.rndp.mes.sign.service.MesSignVO;
import egovframework.rndp.mes.user.service.MesUserService;
import egovframework.rndp.mes.user.service.MesUserVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import net.sf.jxls.transformer.XLSTransformer;

@Controller
public class MesAssetController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MesAssetController.class);

	/** EgovSampleService */
	@Resource(name = "mesAssetService")
	private MesAssetService mesAssetService;
	
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
	
	
	// 대상장비 정보관리
	
	@RequestMapping(value = "/mes/asset/kw_asset_lf.do")
	public String mesAssetLf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		mesAssetVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
		// paging
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesAssetVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesAssetVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesAssetVO.getPageSize());
		
		
		mesAssetVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesAssetVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesAssetVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());


		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
//		if(mesAssetVO.getTopStartDate().equals("") || mesAssetVO.getTopStartDate() == null){
//			String temp = String.valueOf(dateFormat.format(nowDate));
//			temp = temp.substring(0,8);
//			temp += "01";
//			mesAssetVO.setTopStartDate(temp);
//		}
//		if(mesAssetVO.getTopEndDate().equals("") || mesAssetVO.getTopEndDate() == null){
//			mesAssetVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
//		}
		mesAssetVO.setTopStartDate("");
		mesAssetVO.setTopEndDate("");
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("36");	// 자산유형
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("37");	// 자산상태
		model.addAttribute("gubun37List", mesGubunService.selectMesGubunCodeList(vo));
		
		
		List assetList = mesAssetService.selectMesAssetList(mesAssetVO);
		int totCnt = mesAssetService.selectMesAssetListCnt(mesAssetVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		
		model.addAttribute("paginationInfo", paginationInfo);
		
		model.addAttribute("assetList", assetList);
		return "mes/asset/kw_asset_lf.tiles";
	}
	
	@RequestMapping(value = "/mes/asset/kw_asset_lfr.do")
	public String mesAssetLfr(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		mesAssetService.mesSignAsset(mesAssetVO);
		

		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		mesAssetVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
		// paging
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesAssetVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesAssetVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesAssetVO.getPageSize());
		
		mesAssetVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesAssetVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesAssetVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());


		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("37");
		model.addAttribute("gubun37List", mesGubunService.selectMesGubunCodeList(vo));
		
		
		List assetList = mesAssetService.selectMesAssetList(mesAssetVO);
		int totCnt = mesAssetService.selectMesAssetListCnt(mesAssetVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		
		model.addAttribute("paginationInfo", paginationInfo);
		
		model.addAttribute("assetList", assetList);	
		return "mes/asset/kw_asset_lf.tiles";
	}
	//상세페이지
	@RequestMapping(value = "/mes/asset/kw_asset_vf.do")
	public String mesAssetVf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		MesAssetVO assetInfo = 	mesAssetService.selectMesAssetInfo(mesAssetVO);
		model.addAttribute("assetInfo", assetInfo);

		
		List assetList = mesAssetService.selectMesAssetLicenseList(mesAssetVO);
		model.addAttribute("assetList",assetList);
		
		MesSignVO mesSignVO = new MesSignVO();
		mesSignVO.setsSignTableKey(mesAssetVO.geteAssetKey());
		mesSignVO.setsSignTableName("A_ASSET");
		List signList = mesSignService.selectSignListTwo(mesSignVO);
		model.addAttribute("signList", signList);
		
		
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("37");
		model.addAttribute("gubun37List", mesGubunService.selectMesGubunCodeList(vo));
		 
		
		return "mes/asset/kw_asset_vf.tiles";
	}
	
	//등록
	@RequestMapping(value = "/mes/asset/kw_asset_if.do")
	public String MesAssetIf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		String csrfToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("csrfToken", csrfToken);
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		List eAssetCountList = mesAssetService.eAssetCountList(mesAssetVO);
		model.addAttribute("eAssetCountList",eAssetCountList);
		
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("37");
		model.addAttribute("gubun37List", mesGubunService.selectMesGubunCodeList(vo));
		
		redirectAttributes.addFlashAttribute("mesAssetVO", mesAssetVO);
		
		return "mes/asset/kw_asset_if.tiles";
	}
	
	@RequestMapping(value = "/mes/asset/kw_asset_i.do")
	public String MesAssetI(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, MultipartHttpServletRequest requestM
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesAssetVO.setkStaffName(staffVO.getkStaffName());
		mesAssetVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
	 
//		mesAssetService.insertMesAsset(mesAssetVO, request);
		mesAssetService.insertInfoAsset(mesAssetVO);
		
		redirectAttributes.addFlashAttribute("mesassetVO", mesAssetVO);		
		
		return "redirect:/mes/asset/kw_asset_lf.do";
	}
	
	@RequestMapping(value = "/mes/asset/kw_asset_updateProcess.do")
	public String MesAssetUpdateProcess(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesAssetVO.setkStaffName(staffVO.getkStaffName());
		mesAssetVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
	 
		mesAssetService.updateProcessAssetInfo(mesAssetVO);
		
		redirectAttributes.addFlashAttribute("mesassetVO", mesAssetVO);		
		
		return "redirect:/mes/asset/kw_asset_lf.do";
	}
	
	// 자산엑셀 등록
	@RequestMapping(value = "/mes/asset/kw_uploadAssetAjax.do")
	public void uploadAssetAjax(HttpServletRequest request
			, HttpServletResponse response
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, RedirectAttributes redirectAttributes
			, ModelMap model) throws Exception {
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesAssetVO.setkStaffName(staffVO.getkStaffName());
		mesAssetVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
		
		String result = "success";
		mesAssetService.insertMesAssetAjax(mesAssetVO);	
		
		JsonHelper helper = new JsonHelper();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", result);
		helper.printJsonObject(response, "result", map); 
		redirectAttributes.addFlashAttribute("mesCompanyVO", mesAssetVO);
	}
	
	//수정
	@RequestMapping(value = "/mes/asset/kw_asset_uf.do")
	public String MesAssetUf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		String csrfToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("csrfToken", csrfToken);
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		MesAssetVO assetInfo = mesAssetService.selectMesAssetInfo(mesAssetVO);
		model.addAttribute("assetInfo", assetInfo);

		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("37");
		model.addAttribute("gubun37List", mesGubunService.selectMesGubunCodeList(vo));
		
		List assetList = mesAssetService.selectMesAssetLicenseList(mesAssetVO);
		model.addAttribute("assetList",assetList);
		
		MesSignVO mesSignVO = new MesSignVO();
		mesSignVO.setsSignTableKey(mesAssetVO.geteAssetKey());
		mesSignVO.setsSignTableName("A_ASSET");
		List signList = mesSignService.selectSignListTwo(mesSignVO);
		model.addAttribute("signList", signList);
		
		
		redirectAttributes.addFlashAttribute("mesAssetVO", mesAssetVO);
		
		return "mes/asset/kw_asset_uf.tiles";
	}
	
	@RequestMapping(value = "/mes/asset/kw_asset_u.do")
	public String MesAssetU(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, @ModelAttribute("fileVO") FileVO fileVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		mesAssetService.updateMesAsset(mesAssetVO, request, fileVO);
		
		redirectAttributes.addFlashAttribute("mesAssetVO", mesAssetVO);
		
		return "redirect:/mes/asset/kw_asset_lf.do";
	}
	
	//삭제
	@RequestMapping(value = "/mes/asset/kw_asset_d.do")
	public String MesAssetD(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		mesAssetVO.setsSignStaffKey(Integer.toString(staffVO.getkStaffKey()));
		mesAssetVO.setsSignStaffName(staffVO.getkStaffName());
		mesAssetService.deleteMesAsset(mesAssetVO);
		
		return "redirect:/mes/asset/kw_asset_lf.do";
	}
	
	
	
	/**
	 * 파일삭제
	 * @param request
	 * @param response
	 * @param mesSujuVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/mes/asset/delFileSN.do")
	public void delFileSN(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO) throws Exception {

		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(mesAssetVO.getaAssetImage());
		fileVO.setFileSn(mesAssetVO.getFileSn());
		
		//파일삭제		
		fileService.deleteFileInf(fileVO);
		
		//파일명과 순번 null 처리 
		mesAssetService.updateNullFileInfo(mesAssetVO);

		JsonHelper helper = new JsonHelper();
		Map<String, Object> map = new HashMap<String, Object>();
		helper.printJsonObject(response, "result", map);
	}
	
	
	
	
	
	
	/**
	 * 대상장비 이력현황
	 * @param request
	 * @param redirectAttributes
	 * @param mesAssetVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mes/asset/kw_asset_use_lf.do")
	public String MesAssetUseLf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		// paging
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesAssetVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesAssetVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesAssetVO.getPageSize());
		
		mesAssetVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesAssetVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesAssetVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());


		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesAssetVO.getTopStartDate().equals("") || mesAssetVO.getTopStartDate() == null){
			String temp = String.valueOf(dateFormat.format(nowDate));
			temp = temp.substring(0,8);
			temp += "01";
			mesAssetVO.setTopStartDate(temp);
		}
		if(mesAssetVO.getTopEndDate().equals("") || mesAssetVO.getTopEndDate() == null){
			mesAssetVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		
		List assetUseList = mesAssetService.selectMesAssetUseList(mesAssetVO);
		model.addAttribute("assetUseList", assetUseList);
		
		int totCnt = mesAssetService.selectMesAssetUseListCnt(mesAssetVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("paginationInfo", paginationInfo);		
		
		return "mes/asset/kw_asset_use_lf.tiles";
	}
	
	
	
	/**
	 * 대상장비 이력
	 * @param request
	 * @param redirectAttributes
	 * @param mesAssetVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mes/asset/kw_asset_use_if.do")
	public String MesAssetUseIf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		MesAssetVO assetInfo = mesAssetService.selectMesAssetInfo(mesAssetVO);
		model.addAttribute("assetInfo", assetInfo);
				
		//장비이력
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("35");
		model.addAttribute("gubun35List", mesGubunService.selectMesGubunCodeList(vo));
		
		
		redirectAttributes.addFlashAttribute("mesAssetVO", mesAssetVO);
		
		
		return "mes/asset/kw_asset_use_if.tiles";
	}
	
	@RequestMapping(value = "/mes/asset/kw_asset_use_i.do")
	public String MesAssetUseI(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesAssetVO.setkStaffName(staffVO.getkStaffName());
		
		mesAssetService.insertMesAssetUse(mesAssetVO);
		
		redirectAttributes.addFlashAttribute("mesAssetVO", mesAssetVO);
	
		return "redirect:/mes/asset/kw_asset_use_lf.do";
	}
	
	
	/**
	 * 대상장비 상세
	 * @param request
	 * @param redirectAttributes
	 * @param mesAssetVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mes/asset/kw_asset_use_vf.do")
	public String MesAssetUseVf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		MesAssetVO assetUseInfo = 	mesAssetService.selectMesAssetUseInfo(mesAssetVO);
		model.addAttribute("assetUseInfo", assetUseInfo);
		
		List assetUseList = mesAssetService.selectMesAssetUseItemList(mesAssetVO);
		model.addAttribute("assetUseList", assetUseList);
		
		List signList = mesSignService.selectSignList(assetUseInfo.getsSignKey());
		model.addAttribute("signList", signList);
		
		return "mes/asset/kw_asset_use_vf.tiles";
	}
	
	@RequestMapping(value = "/mes/asset/kw_asset_use_d.do")
	public String MesAssetUseD(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		mesAssetService.deleteMesAssetUse(mesAssetVO);
		
		redirectAttributes.addFlashAttribute("mesAssetVO", mesAssetVO);
		
		/*mesAssetService.insertMesAssetUse(mesAssetVO);*/
		
		return "redirect:/mes/asset/kw_asset_use_lf.do";
	}
	
	@RequestMapping(value = "/mes/asset/kw_asset_use_uf.do")
	public String MesAssetUseUf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);

		MesAssetVO assetUseInfo = 	mesAssetService.selectMesAssetUseInfo(mesAssetVO);
		model.addAttribute("assetUseInfo", assetUseInfo);
		
		List assetUseList = mesAssetService.selectMesAssetUseItemList(mesAssetVO);
		model.addAttribute("assetUseList", assetUseList);
		
		List signList = mesSignService.selectSignList(assetUseInfo.getsSignKey());
		model.addAttribute("signList", signList);
		
		//장비이력
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("35");
		model.addAttribute("gubun35List", mesGubunService.selectMesGubunCodeList(vo));
		

		return "mes/asset/kw_asset_use_uf.tiles";
	}
	
	@RequestMapping(value = "/mes/asset/kw_asset_use_u.do")
	public String MesAssetUseU(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesAssetVO.setkStaffName(staffVO.getkStaffName());
		
		mesAssetService.updateMesAssetUse(mesAssetVO);
		
		redirectAttributes.addFlashAttribute("mesAssetVO", mesAssetVO);
				
		return "redirect:/mes/asset/kw_asset_use_lf.do";
	}
	
	
	/**
	 * 승인요청
	 * @param request
	 * @param redirectAttributes
	 * @param mesAssetVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mes/asset/kw_asset_use_r.do")
	public String MesAssetUseR(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		mesAssetService.requestMesAssetUse(mesAssetVO);
		
		redirectAttributes.addFlashAttribute("mesAssetVO", mesAssetVO);
		
		return "redirect:/mes/asset/kw_asset_use_lf.do";
	}
	

	
	@RequestMapping(value = "/mes/asset/kw_asset_use_sf.do")
	public String MesAssetUseSf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		MesAssetVO assetInfo = 	mesAssetService.selectMesAssetInfo(mesAssetVO);
		model.addAttribute("assetInfo", assetInfo);
		
		MesAssetVO assetUseInfo = mesAssetService.selectMesAssetUseInfo(mesAssetVO);
		model.addAttribute("assetUseInfo", assetUseInfo);

		List signList = mesSignService.selectSignList(assetUseInfo.getsSignKey());
		model.addAttribute("signList", signList);
		
		return "mes/asset/kw_asset_use_sf.tiles";
	}
	


	@RequestMapping(value = "/mes/asset/kw_asset_s.do")
	public String mesAssetS(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVo);
		
		mesAssetService.setSignAsset(mesAssetVO);		
		
		return "redirect:/mes/asset/kw_asset_use_lf.do";
	}
	
	

    



	@RequestMapping(value = "/mes/asset/kw_assetExcelForm.do")
	public void assetExcelForm(
			  HttpServletRequest request
			, HttpServletResponse response
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, RedirectAttributes redirectAttributes
			, ModelMap card) throws Exception {
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("staff");
		String staffKey = Integer.toString(staffVo.getkStaffKey());
			
		String filePath = EgovProperties.getProperty("salesExcelTemplatePath");		//cms.properties에서 가져오는 파일 경로
		File saveFolder = new File(filePath);
		// 디렉토리 생성
		if (!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}
		
		String templatePath = EgovProperties.getProperty("salesExcelTemplatePath");		//cms.properties에서 가져오는 파일 경로
		String templateFileName = templatePath + "assetExcelSmaple.xlsx";
		
		File templateFile = new File(templateFileName);
	    if (!templateFile.exists()) {
	    	response.reset();
	        response.setContentType("text/html; charset=UTF-8");
	        response.getWriter().write(  "<script>" +
	                "alert('다운로드할 엑셀 템플릿 파일이 존재하지 않습니다. 관리자에게 문의하세요.');" +
	                "window.location.href = '/mes/asset/kw_asset_lf.do';" +
	                "</script>");
	        response.getWriter().flush();
	        return;// 파일 다운로드 중단
	    }
		
	    String destFileName = "자산업로드양식" + ".xlsx";
	    response.setContentType("application/vnd.ms-excel");
	    response.setHeader("Content-Disposition", "attachment; filename="+ java.net.URLEncoder.encode(destFileName, "UTF-8") + ";");
		
	    
	    Map<String, List> beans = new HashMap<String, List>();		
	    	    
	    
	   try {
		    	XLSTransformer transformer = new XLSTransformer();           
		    	
		    	Workbook resultWorkbook = transformer.transformXLS(new FileInputStream(templateFileName), beans);      		//엑셀다운이 오류나는 구간      
		    	OutputStream os = response.getOutputStream();
		    	
		    	resultWorkbook.write(os);
	   } catch (IOException e) {
	       e.printStackTrace();
	   }
	
	   redirectAttributes.addFlashAttribute("mesAssetVO", mesAssetVO);
	}
	
	
	/**
	 * 대상장비 정보 조회 팝업
	 * @param request
	 * @param mesAssetVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mes/asset/kw_asset_info_lf.do")
	public String mesAssetInfoPopup(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception {
		
		
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
		
		model.addAttribute("assetList", assetList);
		model.addAttribute("paginationInfo", paginationInfo);
		
		redirectAttributes.addFlashAttribute("mesAssetVO", mesAssetVO);

		return "mesPopup/mes/asset/popup/kw_asset_info_lf";
	}
	
	@RequestMapping(value = "/mes/asset/kw_asset_box_lf.do")
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

		return "mesPopup/mes/asset/popup/kw_asset_info_box";
	}
	
	
	
	
	
	
	@RequestMapping(value = "/mes/asset/kw_assetInfoPop_lf.do")
	public void mesAssetInfoPopupAjax(HttpServletRequest request
			, HttpServletResponse response
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception {
		
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
		
		//model.addAttribute("assetList", assetList);
		//model.addAttribute("paginationInfo", paginationInfo);
		
		JsonHelper helper = new JsonHelper();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", assetList);
		map.put("paginationInfo", paginationInfo);
		helper.printJsonObject(response, "result", map);
	}
	

	@RequestMapping(value = "/mes/asset/kw_Software_Register_lf.do")
	public String mesSoftwareAssetRegisterLf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		mesAssetVO.setkStaffKey(String.valueOf(staffVO.getkStaffKey()));
		
		// paging
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesAssetVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesAssetVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesAssetVO.getPageSize());
		
		mesAssetVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesAssetVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesAssetVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());


		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesAssetVO.getTopStartDate().equals("") || mesAssetVO.getTopStartDate() == null){
//			String temp = String.valueOf(dateFormat.format(nowDate));
//			temp = temp.substring(0,8);
//			temp += "01";
			String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
			mesAssetVO.setTopStartDate(temp);
		}
		if(mesAssetVO.getTopEndDate().equals("") || mesAssetVO.getTopEndDate() == null){
			mesAssetVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		
		List assetList = mesAssetService.mesSoftwareList(mesAssetVO);
		int totCnt = mesAssetService.mesSoftwareListCnt(mesAssetVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("assetList", assetList);
		model.addAttribute("paginationInfo", paginationInfo);		
		return "mes/asset/kw_Software_Register_lf.tiles";
	}
	

	@RequestMapping(value = "/mes/asset/kw_Software_Register_lfr.do")
	public String mesSoftwareAssetRegisterLfr(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		mesAssetService.mesSignSoftwareAsset(mesAssetVO);
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		// paging
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesAssetVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesAssetVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesAssetVO.getPageSize());
		
		mesAssetVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesAssetVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesAssetVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());


		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesAssetVO.getTopStartDate().equals("") || mesAssetVO.getTopStartDate() == null){
//			String temp = String.valueOf(dateFormat.format(nowDate));
//			temp = temp.substring(0,8);
//			temp += "01";
			String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
			mesAssetVO.setTopStartDate(temp);
		}
		if(mesAssetVO.getTopEndDate().equals("") || mesAssetVO.getTopEndDate() == null){
			mesAssetVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		
		List assetList = mesAssetService.mesSoftwareList(mesAssetVO);
		int totCnt = mesAssetService.mesSoftwareListCnt(mesAssetVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("assetList", assetList);
		model.addAttribute("paginationInfo", paginationInfo);		
		return "mes/asset/kw_Software_Register_lf.tiles";
	}
	
	
	@RequestMapping(value = "/mes/asset/kw_Software_Register_if.do")
	public String mesSoftwareAssetRegisterif(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		String csrfToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("csrfToken", csrfToken);
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		 	
		return "mes/asset/kw_Software_Register_if.tiles";
	}
	
	@RequestMapping(value = "/mes/asset/kw_Software_Register_i.do")
	public String mesSoftwareAssetRegisterinsert(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesAssetVO.setkStaffName(staffVO.getkStaffName());
		mesAssetVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
		model.addAttribute("staffVO", staffVO);
		mesAssetService.insertAssetSoftwareInfo(mesAssetVO);
		return "redirect:/mes/asset/kw_Software_Register_lf.do";
	}
	
	
	@RequestMapping(value = "/mes/asset/kw_Software_Register_d.do")
	public String mesSoftwareAssetRegisterDelete(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		mesAssetService.mesSoftwareAssetDelete(mesAssetVO);
		return "redirect:/mes/asset/kw_Software_Register_lf.do";
	}
	@RequestMapping(value = "/mes/asset/kw_Software_Register_u.do")
	public String mesSoftwareAssetRegisterUdate(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		mesAssetService.mesSoftwareAssetUdate(mesAssetVO);
		return "redirect:/mes/asset/kw_Software_Register_lf.do";
	}
	
	@RequestMapping(value = "/mes/asset/kw_Software_Log_uf.do")
	public String mesSoftwareLogVf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		 
		mesAssetService.mesSoftwareStatusUpdate(mesAssetVO);
		
		return "redirect:/mes/asset/kw_Software_Register_vf.do";
	}
	
	@RequestMapping(value = "/mes/asset/kw_Software_Register_vf.do")
	public String mesSoftwareAssetRegisterVf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		String csrfToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("csrfToken", csrfToken);
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		 	
		MesAssetVO vo = mesAssetService.mesSoftwareInfo(mesAssetVO);
		model.addAttribute("swInfo", vo);
		
		//갱신정보 리스트
		List updateList = mesAssetService.mesSoftwareUpdateList(mesAssetVO);
		model.addAttribute("updateList", updateList);
		

		MesSignVO mesSignVO = new MesSignVO();
		mesSignVO.setsSignTableKey(mesAssetVO.geteSWRegisterKey());
		mesSignVO.setsSignTableName("A_ASSET_SW");
		List signList = mesSignService.selectSignListTwo(mesSignVO);
		model.addAttribute("signList", signList);
		
		// 파일
		List aFileList = mesAssetService.eSelectFileInfoList(mesAssetVO);
		model.addAttribute("aFileList", aFileList);
		
		
		
		
		//자산등록 리스트
//		List assetList = mesAssetService.mesAssetSoftwareList(mesAssetVO);
//		model.addAttribute("assetList", assetList);
		
		return "mes/asset/kw_Software_Register_vf.tiles";
	}

	@RequestMapping(value = "/mes/asset/kw_Software_Register_vfr.do")
	public String mesSoftwareAssetRegisterVfr(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		mesAssetService.mesSignSoftwareAsset(mesAssetVO);
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		 	
		MesAssetVO vo = mesAssetService.mesSoftwareInfo(mesAssetVO);
		model.addAttribute("swInfo", vo);
		
		//갱신정보 리스트
		List updateList = mesAssetService.mesSoftwareUpdateList(mesAssetVO);
		model.addAttribute("updateList", updateList);
		

		MesSignVO mesSignVO = new MesSignVO();
		mesSignVO.setsSignTableKey(mesAssetVO.geteSWRegisterKey());
		mesSignVO.setsSignTableName("A_ASSET_SW");
		List signList = mesSignService.selectSignListTwo(mesSignVO);
		model.addAttribute("signList", signList);
		
		
		 
		return "mes/asset/kw_Software_Register_vf.tiles";
	}
	@RequestMapping(value = "/mes/asset/kw_Software_Register_uf.do")
	public String mesSoftwareAssetRegisterUf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		String csrfToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("csrfToken", csrfToken);
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		MesAssetVO vo = mesAssetService.mesSoftwareInfo(mesAssetVO);
		model.addAttribute("swInfo", vo);
		
		MesSignVO mesSignVO = new MesSignVO();
		mesSignVO.setsSignTableKey(mesAssetVO.geteSWRegisterKey());
		mesSignVO.setsSignTableName("A_ASSET_SW");
		List signList = mesSignService.selectSignListTwo(mesSignVO);
		model.addAttribute("signList", signList);
		// 파일
				List aFileList = mesAssetService.eSelectFileInfoList(mesAssetVO);
				model.addAttribute("aFileList", aFileList);
		
		return "mes/asset/kw_Software_Register_uf.tiles";
	}
	

	@RequestMapping(value = "/mes/asset/kw_assetExcelFormSample.do")
	public void assetExcelFormDwonload(
			  HttpServletRequest request
			, HttpServletResponse response
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, RedirectAttributes redirectAttributes
			, ModelMap card) throws Exception {
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("staff");
		String staffKey = Integer.toString(staffVo.getkStaffKey());
			
		String filePath = EgovProperties.getProperty("salesExcelDownloadPath");		//cms.properties에서 가져오는 파일 경로
		File saveFolder = new File(filePath);
		
		// 디렉토리 생성
		if (!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}
		
		String templatePath = EgovProperties.getProperty("salesExcelTemplatePath");		//cms.properties에서 가져오는 파일 경로
		String templateFileName = templatePath + "assetSWExcelsmaple.xls";
		
	    String destFileName = "소프트웨어관리대장 엑셀 업로드 양식" + ".xls";
	    response.setContentType("application/vnd.ms-excel");
	    response.setHeader("Content-Disposition", "attachment; filename="+ java.net.URLEncoder.encode(destFileName, "UTF-8") + ";");
		
	    
	    Map<String, List> beans = new HashMap<String, List>();		
	    	    
	    
	 // 별도로 엑셀 생성후 다운 받게 
	   try {
	
	       XLSTransformer transformer = new XLSTransformer();           
	       
	       Workbook resultWorkbook = transformer.transformXLS(new FileInputStream(templateFileName), beans);      		//엑셀다운이 오류나는 구간      
	       OutputStream os = response.getOutputStream();
	       
	       resultWorkbook.write(os);
	   } catch (IOException e) {
	       e.printStackTrace();
	   }
	
	   redirectAttributes.addFlashAttribute("mesAssetVO", mesAssetVO);
	}
	
	

	@RequestMapping(value = "/mes/asset/kw_assetExcelListDwonload.do")
	public void mesExcelDownload(
			  HttpServletRequest request
			, HttpServletResponse response
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception {
			 
			    Map<String, List> beans = new HashMap<String, List>();
				 
				List eSoftwarelist =  mesAssetService.mesSoftwareExcelList(mesAssetVO);
				ArrayList<String> list = new ArrayList<>(eSoftwarelist);
				beans.put("list", list);
		
		String Specification =  "assetSWExcelList.xls";
	    String templatePath = EgovProperties.getProperty("salesExcelTemplatePath");		//cms.properties에서 가져오는 파일 경로
		String templateFileName = templatePath + Specification;
		
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ("yyyyMMdd", Locale.KOREA);
		Date currentTime = new Date ();
		String mTime = mSimpleDateFormat.format ( currentTime );
		String titleName = "소프트웨어_라이선스_관리_";
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
	
	// 자산엑셀 등록
	@RequestMapping(value = "/mes/asset/kw_eAssetSWinsertAjax.do")
	public void eAssetSWinsertAjax(HttpServletRequest request
			, HttpServletResponse response
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, RedirectAttributes redirectAttributes
			, ModelMap model) throws Exception {

		String result = "success";
		mesAssetService.insertAssetSoftwareInfo(mesAssetVO);	
		
		JsonHelper helper = new JsonHelper();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", result);
		helper.printJsonObject(response, "result", map); 
		redirectAttributes.addFlashAttribute("mesCompanyVO", mesAssetVO);
	}
	

	@RequestMapping(value = "/mes/asset/ePDFViewer.do")
	public String mesPDFPrint(HttpServletRequest request
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, RedirectAttributes redirectAttributes
			, ModelMap model) throws Exception {
	 model.addAttribute("eDeviceImgExtension", mesAssetVO.geteDeviceImgExtension());
	 model.addAttribute("eDeviceImgId", mesAssetVO.geteDeviceImgId());
		
		redirectAttributes.addFlashAttribute("mesAssetVO", mesAssetVO);
		
		return "mesPopup/mes/asset/popup/kw_PDF_viewer_lf";
	}
	
	

	@RequestMapping(value = "/mes/asset/popup/mesPDFAdd.do")
	public String mesIMGregAddPopup(HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO, ModelMap model) throws Exception {
		 String pageFileGubun = "N";
		 if (!"N".equals(mesAssetVO.getePageGubun()) && !"Y".equals(mesAssetVO.getePageGubun())) {
			 pageFileGubun = "N";
	        }else {
	        	pageFileGubun = mesAssetVO.getePageGubun();
			}
		String sessionToken = (String) request.getSession().getAttribute("csrfToken");
        String requestToken = request.getParameter("csrfToken");

        if (sessionToken == null || !sessionToken.equals(requestToken)) {
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("staff");
		model.addAttribute("staffVO", staffVo);
		
		 model.addAttribute("eGubunPage", "Y");
		 model.addAttribute("pageFileGubun", pageFileGubun);
		return "mesPopup/mes/asset/popup/kw_PDF_add";
	}

	// 이미지 업로드 팝업창
	@RequestMapping(value = "/mes/asset/popup/mesPDFAdd_insert.do")
	public String mesIMGregAddInsert(HttpServletRequest request,
			@ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO, ModelMap model) throws Exception {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> files = multipartRequest.getFileMap();
		
		List<FileVO> result = null;
		String atchFileId = "";
		if (!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, "Asset_", 0, "", "assetUploadPath");
			atchFileId = fileMngService.insertFileInfs(result);
			mesAssetVO.seteDeviceImgId(atchFileId);

		}
		 model.addAttribute("eGubunPage", "N");
		return "mesPopup/mes/asset/popup/kw_PDF_add";
	}
	
	// 소프트웨어 라이선스 파일 첨부
	@RequestMapping(value = "/mes/asset/popup/sw_file_add.do")
	public String mesSWFileAddPop(HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO, ModelMap model) throws Exception {
		 String pageFileGubun = "N";
		 if (!"N".equals(mesAssetVO.getePageGubun()) && !"Y".equals(mesAssetVO.getePageGubun())) {
//			 pageFileGubun = "N";
			 pageFileGubun = "Y";
	        }else {
//	        	pageFileGubun = mesAssetVO.getePageGubun();
				 pageFileGubun = "Y";
			}
		String sessionToken = (String) request.getSession().getAttribute("csrfToken");
        String requestToken = request.getParameter("csrfToken");

        if (sessionToken == null || !sessionToken.equals(requestToken)) {
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("staff");
		model.addAttribute("staffVO", staffVo);
		
		 model.addAttribute("eGubunPage", "Y");
		 model.addAttribute("pageFileGubun", pageFileGubun);
		return "mesPopup/mes/asset/popup/kw_Software_file_pop";
	}

	// sw 파일첨부
	@RequestMapping(value = "/mes/asset/popup/sw_file_insert.do")
	public String mesSWFileInsertPop(HttpServletRequest request,
			@ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO, ModelMap model) throws Exception {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> files = multipartRequest.getFileMap();
		
		List<FileVO> result = null;
		String atchFileId = "";
		if (!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, "Asset_", 0, "", "assetUploadPath");
			atchFileId = fileMngService.insertFileInfs(result);
			mesAssetVO.seteDeviceImgId(atchFileId);

		}
		 model.addAttribute("eGubunPage", "N");
		return "mesPopup/mes/asset/popup/kw_Software_file_pop";
	}
	

	@RequestMapping(value = "/mes/asset/kw_Hardware_lf.do")
	public String mesHardwareAssetLf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{

		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		// paging
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesAssetVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesAssetVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesAssetVO.getPageSize());
		
		mesAssetVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesAssetVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesAssetVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());


		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesAssetVO.getTopStartDate().equals("") || mesAssetVO.getTopStartDate() == null){
//			String temp = String.valueOf(dateFormat.format(nowDate));
//			temp = temp.substring(0,8);
//			temp += "01";
//			mesAssetVO.setTopStartDate(temp);
//			mesAssetVO.setTopStartDate(String.valueOf(dateFormat.format(nowDate)));
		}
		if(mesAssetVO.getTopEndDate().equals("") || mesAssetVO.getTopEndDate() == null){
//			mesAssetVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("37");
		model.addAttribute("gubun37List", mesGubunService.selectMesGubunCodeList(vo));
		
		
		List assetList = mesAssetService.selectMesAssetList(mesAssetVO);
		int totCnt = mesAssetService.selectMesAssetListCnt(mesAssetVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("assetList", assetList);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "mes/asset/kw_Hardware_lf.tiles";
	}
	
	@RequestMapping(value = "/mes/asset/kw_eCondition_lf.do")
	public String mesConditionLf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{

		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		mesAssetVO.setkStaffKey(String.valueOf(staffVO.getkStaffKey()));
		
		// paging
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesAssetVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesAssetVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesAssetVO.getPageSize());
		
		mesAssetVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesAssetVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesAssetVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());


		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesAssetVO.getTopStartDate().equals("") || mesAssetVO.getTopStartDate() == null){
			String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
			mesAssetVO.setTopStartDate(temp);
		}
		if(mesAssetVO.getTopEndDate().equals("") || mesAssetVO.getTopEndDate() == null){
			mesAssetVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("37");
		model.addAttribute("gubun37List", mesGubunService.selectMesGubunCodeList(vo));
		
		
		List assetList = mesAssetService.selectConditionList(mesAssetVO);
		int totCnt = mesAssetService.selectConditionCnt(mesAssetVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("assetList", assetList);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "mes/asset/kw_Condition_lf.tiles";
	}
	
	

	@RequestMapping(value = "/mes/asset/kw_eConditionExcelListDwonload.do")
	public void kwConditionExcelListDwonload(
			  HttpServletRequest request
			, HttpServletResponse response
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception {
			 
			    Map<String, List> beans = new HashMap<String, List>();
				

				Date nowDate = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				if(mesAssetVO.getTopStartDate().equals("") || mesAssetVO.getTopStartDate() == null){
					String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
					mesAssetVO.setTopStartDate(temp);
				}
				if(mesAssetVO.getTopEndDate().equals("") || mesAssetVO.getTopEndDate() == null){
					mesAssetVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
				}
				MesGubunVO vo = new MesGubunVO();
				 
				
				
//				List eSoftwarelist =  mesAssetService.mesSoftwareExcelList(mesAssetVO);
				List infolist =  mesAssetService.selectConditionExcelList(mesAssetVO);
				
				ArrayList<MesAssetVO> listForDate = new ArrayList<>(infolist);
				
				for(MesAssetVO Avo : listForDate) {
					mesAssetService.selectConditionDate(Avo);
				}
				
				
				ArrayList<String> list = new ArrayList<>(infolist);
				beans.put("list", list);
		
		String Specification =  "conditionExcelList.xls";
	    String templatePath = EgovProperties.getProperty("salesExcelTemplatePath");		//cms.properties에서 가져오는 파일 경로
		String templateFileName = templatePath + Specification;
		
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ("yyyyMMdd", Locale.KOREA);
		Date currentTime = new Date ();
		String mTime = mSimpleDateFormat.format ( currentTime );
		String titleName = "보유자산_반출입_";
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
	
	
	@RequestMapping(value = "/mes/asset/kw_eCondition_lfr.do")
	public String mesConditionLfr(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		mesAssetService.mesSignCondition(mesAssetVO);
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		// paging
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesAssetVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesAssetVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesAssetVO.getPageSize());
		
		mesAssetVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesAssetVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesAssetVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());


		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesAssetVO.getTopStartDate().equals("") || mesAssetVO.getTopStartDate() == null){
//			String temp = String.valueOf(dateFormat.format(nowDate));
//			temp = temp.substring(0,8);
//			temp += "01";
			String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
			mesAssetVO.setTopStartDate(temp);
		}
		if(mesAssetVO.getTopEndDate().equals("") || mesAssetVO.getTopEndDate() == null){
			mesAssetVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("37");
		model.addAttribute("gubun37List", mesGubunService.selectMesGubunCodeList(vo));
		
		
		List assetList = mesAssetService.selectConditionList(mesAssetVO);
		int totCnt = mesAssetService.selectConditionCnt(mesAssetVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("assetList", assetList);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "mes/asset/kw_Condition_lf.tiles";
	}
	

	//등록
	@RequestMapping(value = "/mes/asset/kw_eCondition_if.do")
	public String MesConditionIf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		String csrfToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("csrfToken", csrfToken);
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		redirectAttributes.addFlashAttribute("mesAssetVO", mesAssetVO);
		
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("37");
		model.addAttribute("gubun37List", mesGubunService.selectMesGubunCodeList(vo));
		
		
		return "mes/asset/kw_Condition_if.tiles";
	}
	
	@RequestMapping(value = "/mes/asset/kw_getAssetInfoList.do")
	public void getAssetInfoList(HttpServletRequest request
			, HttpServletResponse response
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception {
		String result = "success";
		List dataList = null;
		try {
			dataList = mesAssetService.selectAssetInfoList(mesAssetVO);

		} catch (Exception e) {
			result = "error";
			e.printStackTrace();
		}
 
		JsonHelper helper = new JsonHelper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataList", dataList);
		helper.printJsonObject(response, "result", map);
	}
	
	@RequestMapping(value = "/mes/asset/kw_asset_barcode.do")
	public String mesBacodePop(HttpServletRequest request,
			@ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO, ModelMap model) throws Exception {

		EgovNetworkState newStatus = new EgovNetworkState();
		String eIPaddress = newStatus.getMyIPaddress();
		List envList = mesUserService.envList();
		String eIpInfo = "";
		for(int i=0; i<envList.size(); i++){ 
			MesUserVO eVO = (MesUserVO)envList.get(i);	
			 if ("sDomain".equals(eVO.getEnvName()) && (eVO.getEnvVal() != null && !eVO.getEnvVal().isEmpty())) {
				 eIpInfo = eVO.getEnvVal();
		        break;
		    } else if ("sPublicIp".equals(eVO.getEnvName()) && (eVO.getEnvVal() != null && !eVO.getEnvVal().isEmpty())) {
		    	eIpInfo = eVO.getEnvVal();
		    }
		}
		if (eIpInfo == null || eIpInfo.isEmpty()) {
			eIpInfo = eIPaddress + ":8080";
		}
		//최종 
		MesAssetVO assetInfo = mesAssetService.selectMesAssetInfo(mesAssetVO);
		model.addAttribute("assetInfo", assetInfo);
		
		 
		String assetKey = assetInfo.geteAssetKey();  // Assuming you have a getter for eAssetKey
		String finalUrl = "http://" + eIpInfo + "/asset/qr_asset_info.do?eAssetKey=" + assetKey;
		model.addAttribute("eIPaddress", finalUrl);
		
		return "mesPopup/mes/asset/popup/kw_asset_barcode";
	}
	
	
	@RequestMapping(value = "/mes/asset/kw_eCondition_i.do")
	public String MesConditionI(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesAssetVO.setkStaffName(staffVO.getkStaffName());
		mesAssetVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
		
		mesAssetService.insertInfoCondition(mesAssetVO);
		
		redirectAttributes.addFlashAttribute("mesassetVO", mesAssetVO);		
		
		return "redirect:/mes/asset/kw_eCondition_lf.do";
	}
	
	@RequestMapping(value = "/mes/asset/kw_eCondition_out_vf.do")
	public String MesConditionOut(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		String csrfToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("csrfToken", csrfToken);
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesAssetVO.setkStaffName(staffVO.getkStaffName());
		mesAssetVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
		model.addAttribute("staffVO", staffVO);
		List assetInfoList = mesAssetService.selectConditionInfo(mesAssetVO);
		model.addAttribute("assetList", assetInfoList);
		
		MesAssetVO assetInfo = mesAssetService.selectConditionStatusInfo(mesAssetVO);
		model.addAttribute("assetInfo", assetInfo);
		
		MesSignVO mesSignVO = new MesSignVO();
		mesSignVO.setsSignTableKey(mesAssetVO.geteEntryExitKey());
		mesSignVO.setsSignTableName("E_ENTRY");
		List signList = mesSignService.selectSignListTwo(mesSignVO);
		model.addAttribute("signList", signList);
		
		
		
		
		redirectAttributes.addFlashAttribute("mesassetVO", mesAssetVO);		
		
		return "mes/asset/kw_Condition_out_vf.tiles";
	}
	@RequestMapping(value = "/mes/asset/kw_eCondition_out_vfr.do")
	public String MesConditionOutr(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		String csrfToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("csrfToken", csrfToken);
		
//		mesAssetService.mesSignSoftwareAsset(mesAssetVO);
		mesAssetService.mesSignConditionOut(mesAssetVO);
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesAssetVO.setkStaffName(staffVO.getkStaffName());
		mesAssetVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
		model.addAttribute("staffVO", staffVO);
		List assetInfoList = mesAssetService.selectConditionInfo(mesAssetVO);
		model.addAttribute("assetList", assetInfoList);
		
		MesAssetVO assetInfo = mesAssetService.selectConditionStatusInfo(mesAssetVO);
		model.addAttribute("assetInfo", assetInfo);
		
		MesSignVO mesSignVO = new MesSignVO();
		mesSignVO.setsSignTableKey(mesAssetVO.geteEntryExitKey());
		mesSignVO.setsSignTableName("E_ENTRY");
		List signList = mesSignService.selectSignListTwo(mesSignVO);
		model.addAttribute("signList", signList);
		
		
		
		
		redirectAttributes.addFlashAttribute("mesassetVO", mesAssetVO);		
		
		return "mes/asset/kw_Condition_out_vf.tiles";
	} 
	@RequestMapping(value = "/mes/asset/kw_eCondition_out_uf.do")
	public String MesConditionUpdatePage(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		String csrfToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("csrfToken", csrfToken);
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesAssetVO.setkStaffName(staffVO.getkStaffName());
		mesAssetVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
		model.addAttribute("staffVO", staffVO);
		List assetInfoList = mesAssetService.selectConditionInfo(mesAssetVO);
		model.addAttribute("assetList", assetInfoList);
		
		
		MesSignVO mesSignVO = new MesSignVO();
		mesSignVO.setsSignTableKey(mesAssetVO.geteEntryExitKey());
		mesSignVO.setsSignTableName("E_ENTRY");
		List signList = mesSignService.selectSignListTwo(mesSignVO);
		model.addAttribute("signList", signList);
		
		
		MesAssetVO assetInfo = mesAssetService.selectConditionStatusInfo(mesAssetVO);
		model.addAttribute("assetInfo", assetInfo);
		
		redirectAttributes.addFlashAttribute("mesassetVO", mesAssetVO);			
		
		return "mes/asset/kw_Condition_out_uf.tiles";
	}
	
	@RequestMapping(value = "/mes/asset/kw_eCondition_out_d.do")
	public String MesConditionDelete(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesAssetVO.setkStaffName(staffVO.getkStaffName());
		mesAssetVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
		
		mesAssetService.eDeleteInfoCondition(mesAssetVO);
		
		redirectAttributes.addFlashAttribute("mesassetVO", mesAssetVO);		
		
		return "redirect:/mes/asset/kw_eCondition_lf.do";
	}
	@RequestMapping(value = "/mes/asset/kw_eCondition_out_u.do")
	public String MesConditionU(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesAssetVO.setkStaffName(staffVO.getkStaffName());
		mesAssetVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
		
		mesAssetService.eUpdateInfoCondition(mesAssetVO);
		
		redirectAttributes.addFlashAttribute("mesassetVO", mesAssetVO);		
		
		return "redirect:/mes/asset/kw_eCondition_lf.do";
	}
	
	@RequestMapping(value = "/mes/asset/kw_eImport_update_pop.do")
	public String MesImportUpdatePop(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		
		
		model.addAttribute("eGubunPage", "Y");
		redirectAttributes.addFlashAttribute("mesassetVO", mesAssetVO);		
		
		return "mesPopup/mes/asset/popup/kw_eImport_update_pop";
	}
	
	@RequestMapping(value = "/mes/asset/kw_eImport_update.do")
	public String MesImportUpdate(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception {
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesAssetVO.setkStaffName(staffVO.getkStaffName());
		mesAssetVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
		mesAssetService.updateImportInfo(mesAssetVO);
		model.addAttribute("eGubunPage", "N");
		return "mesPopup/mes/asset/popup/kw_eImport_update_pop";
	}
	
	
	@RequestMapping(value = "/mes/asset/kw_eReplacement_lf.do")
	public String mesReplacementLf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{

		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		mesAssetVO.setkStaffKey(String.valueOf(staffVO.getkStaffKey()));
		
		// paging
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesAssetVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesAssetVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesAssetVO.getPageSize());
		
		mesAssetVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesAssetVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesAssetVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());


		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesAssetVO.getTopStartDate().equals("") || mesAssetVO.getTopStartDate() == null){
			String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
			mesAssetVO.setTopStartDate(temp);
		}
		if(mesAssetVO.getTopEndDate().equals("") || mesAssetVO.getTopEndDate() == null){
			mesAssetVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("37");
		model.addAttribute("gubun37List", mesGubunService.selectMesGubunCodeList(vo));
		
		
		List assetList = mesAssetService.selectReplacementList(mesAssetVO);
		int totCnt = mesAssetService.selectReplacementCnt(mesAssetVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("assetList", assetList);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "mes/asset/kw_Replacement_lf.tiles";
	}

	@RequestMapping(value = "/mes/asset/kw_eReplacementExcelListDwonload.do")
	public void eReplacementExcelListDwonload(
			  HttpServletRequest request
			, HttpServletResponse response
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, RedirectAttributes redirectAttributes
			, ModelMap card) throws Exception {
		
		  Map<String, List> beans = new HashMap<String, List>();
			 
		  	Date nowDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			if(mesAssetVO.getTopStartDate().equals("") || mesAssetVO.getTopStartDate() == null){
				String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
				mesAssetVO.setTopStartDate(temp);
			}
			if(mesAssetVO.getTopEndDate().equals("") || mesAssetVO.getTopEndDate() == null){
				mesAssetVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
			}
			
			List assetList = mesAssetService.selectReplacementExcelList(mesAssetVO);
			ArrayList<String> list = new ArrayList<>(assetList);
			beans.put("list", list);
	
			String Specification =  "replacementExcelList.xls";
		    String templatePath = EgovProperties.getProperty("salesExcelTemplatePath");		//cms.properties에서 가져오는 파일 경로
			String templateFileName = templatePath + Specification;
			
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ("yyyyMMdd", Locale.KOREA);
			Date currentTime = new Date ();
			String mTime = mSimpleDateFormat.format ( currentTime );
			String titleName = "부품교체_이력관리_";
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
	
	
	
	
	@RequestMapping(value = "/mes/asset/kw_eReplacement_lfr.do")
	public String mesReplacementLfr(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
//		mesAssetService.mesSignAsset(mesAssetVO);	
		mesAssetService.mesSignReplacement(mesAssetVO);	
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		// paging
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesAssetVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesAssetVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesAssetVO.getPageSize());
		
		mesAssetVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesAssetVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesAssetVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());


		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesAssetVO.getTopStartDate().equals("") || mesAssetVO.getTopStartDate() == null){
			String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
			mesAssetVO.setTopStartDate(temp);
		}
		if(mesAssetVO.getTopEndDate().equals("") || mesAssetVO.getTopEndDate() == null){
			mesAssetVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("37");
		model.addAttribute("gubun37List", mesGubunService.selectMesGubunCodeList(vo));
		
		
		List assetList = mesAssetService.selectReplacementList(mesAssetVO);
		int totCnt = mesAssetService.selectReplacementCnt(mesAssetVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("assetList", assetList);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "mes/asset/kw_Replacement_lf.tiles";
	}
	
	//등록
	@RequestMapping(value = "/mes/asset/kw_eReplacement_if.do")
	public String MesReplacementIf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		String csrfToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("csrfToken", csrfToken);
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		redirectAttributes.addFlashAttribute("mesAssetVO", mesAssetVO);
		
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("37");
		model.addAttribute("gubun37List", mesGubunService.selectMesGubunCodeList(vo));
		
		
		return "mes/asset/kw_Replacement_if.tiles";
	}
	
	
	
	@RequestMapping(value = "/mes/asset/kw_eReplacement_i.do")
	public String MesReplacementI(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesAssetVO.setkStaffName(staffVO.getkStaffName());
		mesAssetVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
		
		mesAssetService.insertInfoReplacement(mesAssetVO);
		
		redirectAttributes.addFlashAttribute("mesassetVO", mesAssetVO);		
		
		return "redirect:/mes/asset/kw_eReplacement_lf.do";
	}
	
	@RequestMapping(value = "/mes/asset/kw_eReplacement_vf.do")
	public String MesReplacementVf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		String csrfToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("csrfToken", csrfToken);
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		
		MesAssetVO info  = mesAssetService.selectReplacementInfo(mesAssetVO);
		List assetList = mesAssetService.selectReplacementItemList(mesAssetVO);
		model.addAttribute("info",info);
		model.addAttribute("assetList",assetList);
		
		MesSignVO mesSignVO = new MesSignVO();
		mesSignVO.setsSignTableKey(mesAssetVO.geteReplacedKey());
		mesSignVO.setsSignTableName("E_REPLACEMENT");
		
		List signList = mesSignService.selectSignListTwo(mesSignVO);
		model.addAttribute("signList", signList);
		
		redirectAttributes.addFlashAttribute("mesAssetVO", mesAssetVO);
		return "mes/asset/kw_Replacement_vf.tiles";
	}
	@RequestMapping(value = "/mes/asset/kw_eReplacement_vfr.do")
	public String MesReplacementVfr(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		String csrfToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("csrfToken", csrfToken);
		
		mesAssetService.mesSignReplacement(mesAssetVO);
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		
		MesAssetVO info  = mesAssetService.selectReplacementInfo(mesAssetVO);
		List assetList = mesAssetService.selectReplacementItemList(mesAssetVO);
		
		model.addAttribute("info",info);
		model.addAttribute("assetList",assetList);
		
		MesSignVO mesSignVO = new MesSignVO();
		mesSignVO.setsSignTableKey(mesAssetVO.geteReplacedKey());
		mesSignVO.setsSignTableName("E_REPLACEMENT");
		
		List signList = mesSignService.selectSignListTwo(mesSignVO);
		model.addAttribute("signList", signList);
		redirectAttributes.addFlashAttribute("mesAssetVO", mesAssetVO);
		return "mes/asset/kw_Replacement_vf.tiles";
	}
	
	@RequestMapping(value = "/mes/asset/kw_eReplacement_d.do")
	public String MesReplacementD(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesAssetVO.setkStaffName(staffVO.getkStaffName());
		mesAssetVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
		
		mesAssetService.deleteInfoReplacement(mesAssetVO);
		
		redirectAttributes.addFlashAttribute("mesassetVO", mesAssetVO);		
		
		return "redirect:/mes/asset/kw_eReplacement_lf.do";
	}
	@RequestMapping(value = "/mes/asset/kw_eReplacement_uf.do")
	public String MesReplacementUf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
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
		MesAssetVO info  = mesAssetService.selectReplacementInfo(mesAssetVO);
		List assetList = mesAssetService.selectReplacementItemList(mesAssetVO);
		model.addAttribute("info",info);
		model.addAttribute("assetList",assetList);
		
		MesSignVO mesSignVO = new MesSignVO();
		mesSignVO.setsSignTableKey(mesAssetVO.geteReplacedKey());
		mesSignVO.setsSignTableName("E_REPLACEMENT");
		
		List signList = mesSignService.selectSignListTwo(mesSignVO);
		model.addAttribute("signList", signList);
		
		redirectAttributes.addFlashAttribute("mesAssetVO", mesAssetVO);
		return "mes/asset/kw_Replacement_uf.tiles";
	}
	
	@RequestMapping(value = "/mes/asset/kw_eReplacement_u.do")
	public String MesReplacementU(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesAssetVO.setkStaffName(staffVO.getkStaffName());
		mesAssetVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
		
//		mesAssetService.insertInfoReplacement(mesAssetVO);
		mesAssetService.updateInfoReplacement(mesAssetVO);
		
		redirectAttributes.addFlashAttribute("mesassetVO", mesAssetVO);		
		
		return "redirect:/mes/asset/kw_eReplacement_lf.do";
	}
	//상세페이지
	@RequestMapping(value = "/asset/qr_asset_info.do")
	public String mesAssetQRvf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		MesAssetVO assetInfo = 	mesAssetService.selectMesAssetInfo(mesAssetVO);
		model.addAttribute("assetInfo", assetInfo);

		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("37");
		model.addAttribute("gubun37List", mesGubunService.selectMesGubunCodeList(vo));
		 
		
		return "mesPopup/mes/asset/qr_asset_vf";
	}
	
	
	@RequestMapping(value = "/mes/asset/kw_Condition_Pop.do")
	public String mesAssetConditionPopup(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception {
		
		
		// paging
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesAssetVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesAssetVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesAssetVO.getPageSize());
		
		mesAssetVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesAssetVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesAssetVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		 

		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesAssetVO.getTopStartDate().equals("") || mesAssetVO.getTopStartDate() == null){
			String temp = String.valueOf(dateFormat.format(nowDate));
			temp = temp.substring(0,8);
			temp += "01";
			mesAssetVO.setTopStartDate(temp);
		}
		if(mesAssetVO.getTopEndDate().equals("") || mesAssetVO.getTopEndDate() == null){
			mesAssetVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("37");
		model.addAttribute("gubun37List", mesGubunService.selectMesGubunCodeList(vo));
		
		
		List assetList = mesAssetService.selectConditionPopList(mesAssetVO);
		int totCnt = mesAssetService.selectConditionPopCnt(mesAssetVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("assetList", assetList);
		model.addAttribute("paginationInfo", paginationInfo);
		
		redirectAttributes.addFlashAttribute("mesAssetVO", mesAssetVO);

		return "mesPopup/mes/asset/popup/kw_Condition_Pop";
	}
	
	@RequestMapping(value = "/mes/asset/kw_Replacement_Pop.do")
	public String mesAssetReplacementPopup(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception {
	
		// paging
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesAssetVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesAssetVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesAssetVO.getPageSize());
		
		mesAssetVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesAssetVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesAssetVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesAssetVO.getTopStartDate().equals("") || mesAssetVO.getTopStartDate() == null){
			String temp = String.valueOf(dateFormat.format(nowDate));
			temp = temp.substring(0,8);
			temp += "01";
			mesAssetVO.setTopStartDate(temp);
		}
		if(mesAssetVO.getTopEndDate().equals("") || mesAssetVO.getTopEndDate() == null){
			mesAssetVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("37");
		model.addAttribute("gubun37List", mesGubunService.selectMesGubunCodeList(vo));
		
		
		List assetList = mesAssetService.selectReplacementPopList(mesAssetVO);
		int totCnt = mesAssetService.selectReplacementPopCnt(mesAssetVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("assetList", assetList);
		model.addAttribute("paginationInfo", paginationInfo);
		
		redirectAttributes.addFlashAttribute("mesAssetVO", mesAssetVO);

		return "mesPopup/mes/asset/popup/kw_Replacement_Pop";
	}
	
	@RequestMapping(value = "/mes/asset/kw_Maintance_Pop.do")
	public String mesAssetMaintancePopup(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception {
		
		
		// paging
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesAssetVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesAssetVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesAssetVO.getPageSize());
		
		mesAssetVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesAssetVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesAssetVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesAssetVO.getTopStartDate().equals("") || mesAssetVO.getTopStartDate() == null){
			String temp = String.valueOf(dateFormat.format(nowDate));
			temp = temp.substring(0,8);
			temp += "01";
			mesAssetVO.setTopStartDate(temp);
		}
		if(mesAssetVO.getTopEndDate().equals("") || mesAssetVO.getTopEndDate() == null){
			mesAssetVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		List assetList = mesAssetService.selectMaintancePopList(mesAssetVO);
		int totCnt = mesAssetService.selectMaintancePopListCnt(mesAssetVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("assetList", assetList);
		model.addAttribute("paginationInfo", paginationInfo);
		
		redirectAttributes.addFlashAttribute("mesAssetVO", mesAssetVO);

		return "mesPopup/mes/asset/popup/kw_Maintance_Pop";
	}
	
	

	@RequestMapping(value = "/mes/asset/kw_Software_update_pop.do")
	public String mesSoftwareUpdatePopup(HttpServletRequest request,
			@ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO, ModelMap model) throws Exception {
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("staff");
		model.addAttribute("staffVO", staffVo);
		
		MesAssetVO vo = mesAssetService.mesSoftwareInfo(mesAssetVO);
		model.addAttribute("swInfo", vo);	
		
		 model.addAttribute("eGubunPage", "Y");
		return "mesPopup/mes/asset/popup/kw_date_update";
	}

	// 이미지 업로드 팝업창
	@RequestMapping(value = "/mes/asset/kw_Software_update.do")
	public String mesSoftwareUpdate(HttpServletRequest request,
			@ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO, ModelMap model) throws Exception {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> files = multipartRequest.getFileMap();
		
		mesAssetService.mesSoftwareDateUpdate(mesAssetVO);
		 
		 model.addAttribute("eGubunPage", "N");
		return "mesPopup/mes/asset/popup/kw_date_update";
	}
	
	@RequestMapping(value = "/mes/asset/popup/kw_license_pop.do")
	public String mesLicenseInfoBoxPopup(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception {
		
		
		// paging
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesAssetVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesAssetVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesAssetVO.getPageSize());
		
		mesAssetVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesAssetVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesAssetVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());


		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesAssetVO.getTopStartDate().equals("") || mesAssetVO.getTopStartDate() == null){
			String temp = String.valueOf(dateFormat.format(nowDate));
			temp = temp.substring(0,8);
			temp += "01";
			mesAssetVO.setTopStartDate(temp);
		}
		if(mesAssetVO.getTopEndDate().equals("") || mesAssetVO.getTopEndDate() == null){
			mesAssetVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		
		List assetList = mesAssetService.mesSoftwareList(mesAssetVO);
		int totCnt = mesAssetService.mesSoftwareListCnt(mesAssetVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("assetList", assetList);
		model.addAttribute("paginationInfo", paginationInfo);		

		return "mesPopup/mes/asset/popup/kw_license_pop";
	}
	
	@RequestMapping(value = "/mes/asset/kw_licenseInfoList.do")
	public void getLicenseInfoList(HttpServletRequest request
			, HttpServletResponse response
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception {
		String result = "success";
		List dataList = null;
		try {
			dataList = mesAssetService.selectLicenseInfoList(mesAssetVO);

		} catch (Exception e) {
			result = "error";
			e.printStackTrace();
		}
 
		JsonHelper helper = new JsonHelper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataList", dataList);
		helper.printJsonObject(response, "result", map);
	}
	@RequestMapping(value = "/mes/asset/kw_asset_model_check.do")
	public void eModelInfoCheck(HttpServletRequest request
			, HttpServletResponse response
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception {
		String result = "";
		MesAssetVO  checkInfo = null;
		try {
			checkInfo = mesAssetService.eModelInfoCheck(mesAssetVO);

		} catch (Exception e) {
			result = "error";
			e.printStackTrace();
		}
 
		JsonHelper helper = new JsonHelper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataAdd", checkInfo);
		helper.printJsonObject(response, "result", map);
	}
	
	@RequestMapping(value = "/mes/asset/kw_asset_sNumber_check.do")
	public void eAssetSNumberInfoCheck(HttpServletRequest request
			, HttpServletResponse response
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception {
		String result = "";
		int eDuplicateCont= 0;
		MesAssetVO  checkInfo = null;
		try {
			eDuplicateCont = mesAssetService.eAssetSNumberInfoCheck(mesAssetVO);

		} catch (Exception e) {
			result = "error";
			e.printStackTrace();
		}
 
		JsonHelper helper = new JsonHelper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataAdd", eDuplicateCont);
		helper.printJsonObject(response, "result", map);
	}
	

	@RequestMapping(value = "/mes/asset/kw_eAssetNumberCheck.do")
	public void eAssetNumberCheck(HttpServletRequest request
			, HttpServletResponse response
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception {
		String result = "";
		int eDuplicateCont= 0;
		MesAssetVO  checkInfo = null;
		
		System.out.println("****************************************************************");
		System.out.println(mesAssetVO.geteAssetSNumber());
		System.out.println("****************************************************************");
		try {
			eDuplicateCont = mesAssetService.eAssetNumberCheck(mesAssetVO);

		} catch (Exception e) {
			result = "error";
			e.printStackTrace();
		}
 
		JsonHelper helper = new JsonHelper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataAdd", eDuplicateCont);
		helper.printJsonObject(response, "result", map);
	}
	

	@RequestMapping(value = "/mes/asset/kw_asset_rv.do")
	public String mesAssetRV(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVo);
		
//		mesAssetService.setSignAsset(mesAssetVO);	
		mesAssetService.mesSignAsset(mesAssetVO);		
		 
		
		MesAssetVO assetInfo = 	mesAssetService.selectMesAssetInfo(mesAssetVO);
		model.addAttribute("assetInfo", assetInfo);

		
		List assetList = mesAssetService.selectMesAssetLicenseList(mesAssetVO);
		model.addAttribute("assetList",assetList);
		
		MesSignVO mesSignVO = new MesSignVO();
		mesSignVO.setsSignTableKey(mesAssetVO.geteAssetKey());
		mesSignVO.setsSignTableName("A_ASSET");
		
		List signList = mesSignService.selectSignListTwo(mesSignVO);
		model.addAttribute("signList", signList);
		
		
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("37");
		model.addAttribute("gubun37List", mesGubunService.selectMesGubunCodeList(vo));
		 
		return "mes/asset/kw_asset_vf.tiles";
	}
	
 
	
	@RequestMapping(value = "/mes/asset/kw_asset_License_lf.do")
	public String mesAssetAndLicenseInfoPopup(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception {
		
		
		// paging
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesAssetVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesAssetVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesAssetVO.getPageSize());
		
		mesAssetVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesAssetVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesAssetVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List assetList = mesAssetService.selectMesAssetAndLicenseInfoList(mesAssetVO);
		int totCnt = mesAssetService.selectMesAssetAndLicenseInfoListCnt(mesAssetVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("assetList", assetList);
		model.addAttribute("paginationInfo", paginationInfo);
		
		redirectAttributes.addFlashAttribute("mesAssetVO", mesAssetVO);

		return "mesPopup/mes/asset/popup/kw_asset_license_lf";
	}
	
	
	@RequestMapping(value = "/mes/asset/kw_uploadSignRejectionReasonAjax.do")
	public void mesAssetSignUpdate(HttpServletRequest request
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, RedirectAttributes redirectAttributes
			, HttpServletResponse response
			, ModelMap model) throws Exception {

		mesAssetService.mesUpdateSignStatus(mesAssetVO);

		JsonHelper helper = new JsonHelper();
		Map<String,Object> map = new HashMap<String,Object>();
		helper.printJsonObject(response, "result", map);
	}
	
	@RequestMapping(value = "/mes/asset/kw_uploadSignConditionReasonAjax.do")
	public void mesConditionSignUpdate(HttpServletRequest request
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, RedirectAttributes redirectAttributes
			, HttpServletResponse response
			, ModelMap model) throws Exception {

		mesAssetService.mesConditionSignStatus(mesAssetVO);

		JsonHelper helper = new JsonHelper();
		Map<String,Object> map = new HashMap<String,Object>();
		helper.printJsonObject(response, "result", map);
	}
    //반입 추가 
	@RequestMapping(value = "/mes/asset/kw_eReceiving_lf.do")
	public String mesReceivingLf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		// paging
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mesAssetVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mesAssetVO.getRecordCountPerPage());
		paginationInfo.setPageSize(mesAssetVO.getPageSize());
		
		mesAssetVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mesAssetVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mesAssetVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		
		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(mesAssetVO.getTopStartDate().equals("") || mesAssetVO.getTopStartDate() == null){
//			String temp = String.valueOf(dateFormat.format(nowDate));
//			temp = temp.substring(0,8);
//			temp += "01";
			String temp = dateFormat.format(nowDate).substring(0, 4) + "-01-01";
			mesAssetVO.setTopStartDate(temp);
		}
		if(mesAssetVO.getTopEndDate().equals("") || mesAssetVO.getTopEndDate() == null){
			mesAssetVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		MesGubunVO vo = new MesGubunVO();
		vo.setsGubunCateKey("36");
		model.addAttribute("gubun36List", mesGubunService.selectMesGubunCodeList(vo));
		
		vo.setsGubunCateKey("37");
		model.addAttribute("gubun37List", mesGubunService.selectMesGubunCodeList(vo));
		
		
		List assetList = mesAssetService.selectReceivingInfoList(mesAssetVO);
		int totCnt = mesAssetService.selectReceivingInfoListCnt(mesAssetVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("assetList", assetList);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "mes/asset/kw_Receiving_lf.tiles";
	}
	

	//등록
	@RequestMapping(value = "/mes/asset/kw_eReceiving_if.do")
	public String MesReceivingIf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		model.addAttribute("staffVO", staffVO);
		
		redirectAttributes.addFlashAttribute("mesAssetVO", mesAssetVO);
		
		
		return "mes/asset/kw_Receiving_if.tiles";
	}
	
	@RequestMapping(value = "/mes/asset/kw_eReceiving_i.do")
	public String MesReceivingI(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
			, ModelMap model) throws Exception{
		
		MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
		mesAssetVO.setkStaffName(staffVO.getkStaffName());
		mesAssetVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
		
		mesAssetService.insertInfoReceiving(mesAssetVO);
		
		redirectAttributes.addFlashAttribute("mesassetVO", mesAssetVO);		
		
		return "redirect:/mes/asset/kw_eReceiving_lf.do";
	}
	
	//상세
		@RequestMapping(value = "/mes/asset/kw_eReceiving_vf.do")
		public String MesReceivingVf(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
				, ModelMap model) throws Exception{
			
			MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			model.addAttribute("staffVO", staffVO);
			
			redirectAttributes.addFlashAttribute("mesAssetVO", mesAssetVO);
			List assetList = mesAssetService.selectReceivingList(mesAssetVO);
			model.addAttribute("assetList", assetList);
			MesSignVO mesSignVO = new MesSignVO();
			mesSignVO.setsSignTableKey(mesAssetVO.geteEntryExitKey());
			mesSignVO.setsSignTableName("E_IMPORT");
			
			List signList = mesSignService.selectSignListTwo(mesSignVO);
			model.addAttribute("signList", signList);
			return "mes/asset/kw_Receiving_vf.tiles";
		}
		//상세
		@RequestMapping(value = "/mes/asset/kw_eReceiving_uf.do")
		public String MesReceivingUf(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
				, ModelMap model) throws Exception{
			
			MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			model.addAttribute("staffVO", staffVO);
			
			redirectAttributes.addFlashAttribute("mesAssetVO", mesAssetVO);
			List assetList = mesAssetService.selectReceivingList(mesAssetVO);
			model.addAttribute("assetList", assetList);
			MesSignVO mesSignVO = new MesSignVO();
			mesSignVO.setsSignTableKey(mesAssetVO.geteEntryExitKey());
			mesSignVO.setsSignTableName("E_IMPORT");
			
			List signList = mesSignService.selectSignListTwo(mesSignVO);
			model.addAttribute("signList", signList);
			return "mes/asset/kw_Receiving_uf.tiles";
		}
		
		@RequestMapping(value = "/mes/asset/kw_eReceivingout_d.do")
		public String MesReceivingD(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
				, ModelMap model) throws Exception{
			
			MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			mesAssetVO.setkStaffName(staffVO.getkStaffName());
			mesAssetVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
			
			mesAssetService.eDeleteInfoReceiving(mesAssetVO);
			
			redirectAttributes.addFlashAttribute("mesassetVO", mesAssetVO);		
			
			return "redirect:/mes/asset/kw_eReceiving_lf.do";
		}
		
		@RequestMapping(value = "/mes/asset/kw_eReceiving_u.do")
		public String MesReceivingU(HttpServletRequest request
				, RedirectAttributes redirectAttributes
				, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
				, ModelMap model) throws Exception{
			
			MesK_StaffVo staffVO = (MesK_StaffVo) request.getSession().getAttribute("mesStaff");
			mesAssetVO.setkStaffName(staffVO.getkStaffName());
			mesAssetVO.setkStaffKey(Integer.toString(staffVO.getkStaffKey()));
			
			mesAssetService.updateInfoReceiving(mesAssetVO);
			
			redirectAttributes.addFlashAttribute("mesassetVO", mesAssetVO);		
			
			return "redirect:/mes/asset/kw_eReceiving_lf.do";
		}
		
		@RequestMapping(value = "/mes/asset/kw_uploadSignSWReasonAjax.do")
		public void mesAssetSWSignUpdate(HttpServletRequest request
				, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
				, RedirectAttributes redirectAttributes
				, HttpServletResponse response
				, ModelMap model) throws Exception {

			mesAssetService.mesUpdateAssetSWSignStatus(mesAssetVO);

			JsonHelper helper = new JsonHelper();
			Map<String,Object> map = new HashMap<String,Object>();
			helper.printJsonObject(response, "result", map);
		}
		
		@RequestMapping(value = "/mes/asset/kw_uploadSignImgAjax.do")
		public void mesAssetuploadSignImgAjax(HttpServletRequest request
				, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
				, RedirectAttributes redirectAttributes
				, HttpServletResponse response
				, ModelMap model) throws Exception {

			
			//자상정보관리, 소프트웨어 메뉴 처리, 보유장비 입출, 부품교체
			mesAssetService.mesUpdateAssetUploadSignImgAjax(mesAssetVO);

			JsonHelper helper = new JsonHelper();
			Map<String,Object> map = new HashMap<String,Object>();
			helper.printJsonObject(response, "result", map);
		}
		
		@RequestMapping(value = "/mes/asset/kw_uploadSignReplacedReasonAjax.do")
		public void mesReplacedSignUpdate(HttpServletRequest request
				, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
				, RedirectAttributes redirectAttributes
				, HttpServletResponse response
				, ModelMap model) throws Exception {

			mesAssetService.mesReplacedUpdateSignStatus(mesAssetVO);

			JsonHelper helper = new JsonHelper();
			Map<String,Object> map = new HashMap<String,Object>();
			helper.printJsonObject(response, "result", map);
		}
		
		@RequestMapping(value = "/mes/asset/kw_assetExcelInfoDwonload.do")
		public void assetExcelInfoDwonload(
				  HttpServletRequest request
				, HttpServletResponse response
				, @ModelAttribute("mesAssetVO") MesAssetVO mesAssetVO
				, RedirectAttributes redirectAttributes
				, ModelMap card) throws Exception {
			
			  Map<String, List> beans = new HashMap<String, List>();
				 
//				List eSoftwarelist =  mesAssetService.mesSoftwareExcelList(mesAssetVO);
				List assetList = mesAssetService.selectMesAssetExcelList(mesAssetVO);
				ArrayList<MesAssetVO> assetlist = new ArrayList<>(assetList);
				for(MesAssetVO vo : assetlist) {
					if(vo.geteLifespan() != null && vo.geteLifespan() != "") {
						String str = String.format("%s년 : %s", vo.geteLifespan(), vo.geteLifeType());
						vo.seteLifespan(str);
					} else {
						vo.seteLifespan(vo.geteLifeType());
					}
				}
				
				
				ArrayList<String> list = new ArrayList<>(assetList);
				beans.put("list", list);
		
				String Specification =  "assetExcelList.xls";
			    String templatePath = EgovProperties.getProperty("salesExcelTemplatePath");		//cms.properties에서 가져오는 파일 경로
				String templateFileName = templatePath + Specification;
				
				SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ("yyyyMMdd", Locale.KOREA);
				Date currentTime = new Date ();
				String mTime = mSimpleDateFormat.format ( currentTime );
				String titleName = "정보_관리_대장_";
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
		
	
}
