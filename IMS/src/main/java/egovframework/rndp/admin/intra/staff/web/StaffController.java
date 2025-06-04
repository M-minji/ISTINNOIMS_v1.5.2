package egovframework.rndp.admin.intra.staff.web;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.rndp.admin.intra.staff.service.StaffIntraService;
import egovframework.rndp.admin.intra.staff.service.StaffIntraVO;
import egovframework.rndp.admin.intra.staff.service.StaffMenuAuthVO;
import egovframework.rndp.admin.member.service.MemberService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class StaffController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(StaffController.class);
	
	@Resource(name="staffIntraService")
	private StaffIntraService staffIntraService;
	
	@Resource(name = "memberService")
	private MemberService memberService;
	/*
	@Resource(name="intraStaffService")
	private IntraStaffService intraStaffService;
	*/
	@Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;
	
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;
	
	private int page = 0;
	private int pageLength = 15;
	
	/*직원신청 승인 리스트*/
	@RequestMapping(value = "/admin/intra/staff/staffReqList.do")
	public String staffReqList(HttpServletRequest request
			, @ModelAttribute("staffIntraVO") StaffIntraVO staffIntraVO
			, ModelMap model) throws Exception {
		
		LOGGER.debug("staffReqList start");
		
		model.addAttribute("page", page);
		model.addAttribute("pageLength", pageLength);

		staffIntraVO.setStartNum(pageLength);
		staffIntraVO.setEndNum(page * pageLength);
		
		List staffReuestList = staffIntraService.selectStaffReuestList(staffIntraVO);
		model.addAttribute("staffReuestList", staffReuestList);
		
		/*페이징*/
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(staffIntraVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(staffIntraVO.getRecordCountPerPage());
		paginationInfo.setPageSize(staffIntraVO.getPageSize());

		staffIntraVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		staffIntraVO.setLastIndex(paginationInfo.getLastRecordIndex());
		staffIntraVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		int totCnt = staffIntraService.selectCount();
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("paginationInfo", paginationInfo);	
	
		return "admin/intra/staff/km_staff_req_lf";
	}
	
	/*활성상태 페이지로*/
	@RequestMapping(value = "/admin/intra/staff/activationStaff.do")
	public String activationStaff(HttpServletRequest request
			, @ModelAttribute("staffIntraVO") StaffIntraVO staffIntraVO
			, ModelMap model) throws Exception {

		int kStaffRequestKey = Integer.parseInt(staffIntraVO.getkStaffRequestKey());
		
		staffIntraVO = staffIntraService.selectStaffInfoForView(kStaffRequestKey);
		model.addAttribute("staffIntraVO", staffIntraVO);	
		
		return "admin/intra/staff/km_staff_req_uf";
	}
	
	@RequestMapping(value = "/admin/intra/staff/updateStaffNum.do")
	public String updateStaffNum (HttpServletRequest request
			, @ModelAttribute("staffIntraVO") StaffIntraVO staffIntraVO
			, ModelMap model) throws Exception {
	
		staffIntraVO.setkStaffStateFlag("1"); // 직원산청 승인시 재직 상태로 초기값 설정
		staffIntraService.updateStaffNum(staffIntraVO);

		return "redirect:/admin/intra/staff/staffReqList.do";
	}
	
	@RequestMapping(value = "/admin/intra/staff/previewStaff.do")
	public String previewStaff(HttpServletRequest request
			, @ModelAttribute("staffIntraVO") StaffIntraVO staffIntraVO
			, ModelMap model) throws Exception {
	
		int kStaffRequestKey = Integer.parseInt(staffIntraVO.getkStaffRequestKey());
		
		staffIntraVO = staffIntraService.selectStaffInfoForView(kStaffRequestKey);
		model.addAttribute("staffIntraVO", staffIntraVO);
		
		
		return "admin/intra/staff/km_staff_req_vf";
	}
	
	@RequestMapping(value = "/admin/intra/staff/deleteStaff.do")
	public String deleteStaff(HttpServletRequest request
			, @ModelAttribute("staffIntraVO") StaffIntraVO staffIntraVO
			, ModelMap model) throws Exception {
		
		staffIntraService.deleteStaffReuest(staffIntraVO);
		
		return "redirect:/admin/intra/staff/staffReqList.do";
	}
	
	/* 작원정보관리 리스트 */
	@RequestMapping(value = "/admin/intra/staff/staffList.do")
	public String staffList(HttpServletRequest request
			, @ModelAttribute("staffIntraVO") StaffIntraVO staffIntraVO
			, ModelMap model)throws Exception {

		LOGGER.debug("staffList start");
		
		String message = staffIntraVO.getMessage();
		model.addAttribute("message", message);

		model.addAttribute("page", page);
		model.addAttribute("pageLength", pageLength);
		
		staffIntraVO.setStartNum(pageLength);
		staffIntraVO.setEndNum(page * pageLength);
		
		List StaffList = staffIntraService.selectStaffList(staffIntraVO);
		model.addAttribute("StaffList", StaffList);
		
		/*페이징*/
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(staffIntraVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(staffIntraVO.getRecordCountPerPage());
		paginationInfo.setPageSize(staffIntraVO.getPageSize());

		staffIntraVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		staffIntraVO.setLastIndex(paginationInfo.getLastRecordIndex());
		staffIntraVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		int totCnt = staffIntraService.selectCount();
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "admin/intra/staff/km_staff_lf";
	}
	
	/* 작원정보관리 직원 추가 페이지로*/
	@RequestMapping(value = "/admin/intra/staff/addStaff.do")
	public String staffInfo(HttpServletRequest request
			, @ModelAttribute("staffIntraVO") StaffIntraVO staffIntraVO
			, ModelMap model)throws Exception {
		
		List sectorList = staffIntraService.selectSectorList();
		model.addAttribute("sectorList", sectorList);
		
		List classList = staffIntraService.selectClassList();
		model.addAttribute("classList", classList);
		
		List positionList = staffIntraService.selectPositionList();
		model.addAttribute("positionList", positionList);
//		
//		List staffInfo = staffIntraService.selectStaffInfo(staffIntraVO);
//		model.addAttribute("staffInfo", staffInfo);
		
		return "admin/intra/staff/km_staff_if";
	}
	
	/*비밀번호 md5 로 암호화  */
	public static String pwEncryption(String pwstr){
		
		MessageDigest md;
		String hashText = "";
		
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] pwByte = md.digest(pwstr.getBytes());
			BigInteger num = new BigInteger(1, pwByte);
			hashText = num.toString(16);
			while(hashText.length() < 32){
				hashText = "0" + hashText;
			}
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hashText;
	}

	/*직원추가 내용 인서트*/
	@RequestMapping(value = "/admin/intra/staff/insertStaff.do")
	public String insertStaff (HttpServletRequest request
			, @ModelAttribute("staffIntraVO") StaffIntraVO staffIntraVO
			, ModelMap model)throws Exception {
		
		//MD5적용
		String pass = staffIntraVO.getkStaffPassword();
		pass = pwEncryption(pass);
		
		staffIntraVO.setkStaffPassword(pass);
		staffIntraVO.setkMailUse("Y");
		

		staffIntraService.insertStaff(staffIntraVO);
		
		return "redirect:/admin/intra/staff/staffList.do";
	}

	/*직원추가 수정 페이지로*/
	@RequestMapping(value = "/admin/intra/staff/staffInfo2.do")
	public String selectStaffInfo2 (HttpServletRequest request
			, @ModelAttribute("staffIntraVO") StaffIntraVO staffIntraVO
			, ModelMap model)throws Exception {
		
		model.addAttribute("staffIntraVO", staffIntraVO);
		
		List sectorList = staffIntraService.selectSectorList();
		model.addAttribute("sectorList", sectorList);
		
		List classList = staffIntraService.selectClassList();
		model.addAttribute("classList", classList);
		
		List positionList = staffIntraService.selectPositionList();
		model.addAttribute("positionList", positionList);
		
		StaffIntraVO staffInfo = staffIntraService.selectStaffInfo(staffIntraVO);
		model.addAttribute("staffInfo", staffInfo);

		return "admin/intra/staff/km_staff_uf";
	}
	
	/*직원추가 수정내용 업데이트*/
	@RequestMapping(value = "/admin/intra/staff/updateStaff.do")
	public String updateStaff (HttpServletRequest request
			, @ModelAttribute("staffIntraVO") StaffIntraVO staffIntraVO
			, ModelMap model)throws Exception {
		
		staffIntraService.updateStaff(staffIntraVO);
		
		return "redirect:/admin/intra/staff/staffList.do";
	}

	/*직원추가 : 삭제*/
	@RequestMapping(value = "/admin/intra/staff/deleteStaff2.do")
	public String deleteStaff2(HttpServletRequest request
			, @ModelAttribute("staffIntraVO") StaffIntraVO staffIntraVO
			, ModelMap model)throws Exception {
		
		String message = staffIntraService.deleteStaff(staffIntraVO);
		staffIntraVO.setMessage(message);
		model.addAttribute("staffIntraVO", staffIntraVO);

		return "redirect:/admin/intra/staff/staffList.do?message=" + message;
	}
	
	/*직원추가 : 미리보기*/
	@RequestMapping(value = "/admin/intra/staff/StaffView2.do")
	public String selectStaffInfoForView2 (HttpServletRequest request
			, @ModelAttribute("staffIntraVO") StaffIntraVO staffIntraVO
			, ModelMap model)throws Exception {
		
		staffIntraVO = staffIntraService.selectStaffView2(staffIntraVO);
		model.addAttribute("staffIntraVO", staffIntraVO);

		return "admin/intra/staff/km_staff_vf";
	}
	
	@RequestMapping(value="/admin/staff/kw_staff_iuf.do")
	public String intraKwStaffIuf(HttpServletRequest request
			, @ModelAttribute("kStaffvo") StaffIntraVO kStaffvo
			, ModelMap model) throws Exception{
		 
		
		model.addAttribute("kStaffvo", kStaffvo);
		
		return "adminPopup/admin/intra/staff/kw_admin_staff_iuf";
	}
	
	@RequestMapping(value="/admin/staff/kw_staff_i.do")
	public String intraKwStaffI(HttpServletRequest request
			, @ModelAttribute("kStaffvo") StaffIntraVO kStaffvo
			, ModelMap model) throws Exception{
		
		System.out.println("key2 : "+kStaffvo.getkStaffKey() );
		
		kStaffvo.setkStaffKey(kStaffvo.getkStaffKey());

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		
		Map<String, MultipartFile> files = multipartRequest.getFileMap();
		
		List<FileVO> result = null;
		
		String atchFileId = "";
		
		if(!files.isEmpty()){
			result = fileUtil.parseFileInf(files, "Staff_", 0, "", "staffImagePath");
			atchFileId = fileMngService.insertFileInfs(result);
			
			if("1".equals(kStaffvo.getkParamImage())){
				kStaffvo.setkStaffImagefile(atchFileId);
				
			}
			if("2".equals(kStaffvo.getkParamImage())){
				kStaffvo.setkStaffSignImage(atchFileId);
			}
			
			//this.intraStaffService.updateStaffImageFile(kStaffvo);
			staffIntraService.updateStaffImageFile(kStaffvo);
		}
		model.addAttribute("del", 1);
		
		return "adminPopup/admin/intra/staff/kw_admin_staff_iuf";
	}
	
	
	
	/**
	 * 직원별 메뉴 접근 권한 설정
	 * 
	 * @param request
	 * @param staffIntraVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value = "/admin/intra/staff/menuUpdateFrm.do")
	public String selectStaffmenuUpdateFrm (HttpServletRequest request
			, @ModelAttribute("staffIntraVO") StaffIntraVO staffIntraVO
			, ModelMap model)throws Exception {
		
		StaffMenuAuthVO  staffMenuAuthVO = new StaffMenuAuthVO();
		staffMenuAuthVO.setkStaffKey(staffIntraVO.getkStaffKey());
		
		//메뉴리스트		
		List staffMenuList = staffIntraService.selectStaffMenuList(staffMenuAuthVO);
		model.addAttribute("staffMenuList", staffMenuList);
		
		return "admin/intra/staff/km_staff_menu_vf";
	}

	
	@RequestMapping(value = "/admin/intra/staff/menuUpdate.do")
	public String selectStaffmenuUpdate(HttpServletRequest request
			, @ModelAttribute("staffMenuAuthVO") StaffMenuAuthVO  staffMenuAuthVO
			, ModelMap model)throws Exception {
		
		staffIntraService.deleteStaffMenuAuth(staffMenuAuthVO);
		
		for(int i=0; i<staffMenuAuthVO.getkMenuKey().split(",").length; i++){
			
			int max = staffIntraService.staffMenuAuthMaxCnt();
			
			StaffMenuAuthVO authVO = new StaffMenuAuthVO();
			authVO.setkMenuAuthFlag(request.getParameter("flag"+staffMenuAuthVO.getkMenuKey().split(",")[i]));			
			authVO.setkMenuKey(staffMenuAuthVO.getkMenuKey().split(",")[i]);
			authVO.setkStaffKey(staffMenuAuthVO.getkStaffKey());
			authVO.setkMenuAuthKey(String.valueOf(max));

			staffIntraService.staffMenuAuthUpdate(authVO);
		}
		
		return "redirect:/admin/intra/staff/staffList.do";
	}		
}






