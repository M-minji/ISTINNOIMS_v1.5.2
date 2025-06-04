package egovframework.rndp.admin.menu.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rndp.admin.admin.service.AdminService;
import egovframework.rndp.admin.admin.service.AdminVO;
import egovframework.rndp.admin.menu.service.MenuBeanVO;
import egovframework.rndp.admin.menu.service.MenuService;
import egovframework.rndp.com.utl.MatrixUtil;
import egovframework.rndp.mes.user.service.MesUserService;
import egovframework.rndp.mes.user.service.MesUserVO;
import egovframework.rte.fdl.property.EgovPropertyService;

@Controller
public class MenuController {
	
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	@Resource(name = "menuService")
    private MenuService menuService;
	
	@Resource(name = "adminService")
	private AdminService adminService;

	@Resource(name = "mesUserService")
	private MesUserService mesUserService;
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    /** MatrixUtil */
    @Resource(name = "mUtil")
    protected MatrixUtil mUtil;

	
	/**
	 * 메뉴리스트
	 * @param request
	 * @param groupKey
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/menuList.do")
	public String menuList(HttpServletRequest request
			, int groupKey 
			, ModelMap model) throws Exception{

		 
		if(request.getSession().getAttribute("groupKey") == null || "".equals(groupKey)){
			request.getSession().setAttribute("groupKey", "1");
		}
		
		groupKey = Integer.parseInt(request.getSession().getAttribute("groupKey").toString());
		
		AdminVO vo = (AdminVO) request.getSession().getAttribute("adminUser");

		List menuList = menuService.menuInfoList(groupKey);
		
		model.addAttribute("menuList", menuList);
		model.addAttribute("groupKey", groupKey);
		model.addAttribute("vo", vo);
		return "admin/menu/list";
	}
	
	/**
	 * 메뉴 등록 폼
	 * @param request
	 * @param groupKey
	 * @param key
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/menuInsertfrm.do")
	public String menuInsertfrm(HttpServletRequest request
			, int groupKey
			, int key
			, ModelMap model) throws Exception{
		
//		List groupList = groupService.listInfo();

		if(request.getSession().getAttribute("groupKey") == null || "".equals(groupKey)){
			request.getSession().setAttribute("groupKey", "1");
		}
		
		groupKey = Integer.parseInt(request.getSession().getAttribute("groupKey").toString());
		
		
		
		if("".equals(key)){
			key = 0;
		}

		String category =menuService.category(key);
		List adminList = adminService.adminList();
		MenuBeanVO menu = menuService.getMenuMiniInfo(groupKey);

		model.addAttribute("key", key);
		model.addAttribute("groupKey", groupKey);
//		model.addAttribute("groupList", groupList);
		model.addAttribute("adminList", adminList);
		model.addAttribute("menu", menu);
		model.addAttribute("category", category);
		return "admin/menu/insert";
	}
	
	/**
	 * 메뉴 등록
	 * @param request
	 * @param menuBeanVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/menuInsert.do")
	public String menuInsert(HttpServletRequest request
			, @ModelAttribute("menuBeanVO") MenuBeanVO menuBeanVO 
			, BindingResult bindingResult
			, ModelMap model) throws Exception{
		
		beanValidator.validate(menuBeanVO, bindingResult);
		if (bindingResult.hasErrors()){
			return "admin/menu/insert";
		}
		int adminKey = adminService.adminIdKey(menuBeanVO.getAdminStr().split(",")[0]);
		menuBeanVO.setAdminKey(adminKey);
		menuService.menuInsert(menuBeanVO);
		
		// 메뉴 추가 시 모든 유저에게 보여지도록 권한 부여
		int max = mesUserService.mesUserMenuAuthMaxCnt();
		MesUserVO mesUserVO = new MesUserVO();
		List mesUserList = mesUserService.selectUserffList(mesUserVO);
		List<MesUserVO> userList = new ArrayList(mesUserList);
		int totCnt = mesUserService.selectUserCount(mesUserVO);
		if(totCnt>0) {
			MesUserVO vo = new MesUserVO();
			vo.setkMenuAuthFlag("T");
			vo.setkMenuAuthC("T");			
			vo.setkMenuAuthM("T");			
			vo.setkMenuAuthD("T");			
			vo.setkMenuAuthW("T");			
			vo.setkMenuKey(String.valueOf(menuBeanVO.getKey()));
			vo.setkMenuAuthKey(String.valueOf(max));
			for(MesUserVO list : userList) {
				vo.setMesUserKey(list.getMesUserKey());
				mesUserService.mesUserMenuAuthUpdate(vo);
			}
		}
		
		
		
		return "redirect:/admin/menuList.do?groupKey="+menuBeanVO.getGroupKey()+"&rootKey="+menuBeanVO.getRoot()+"&menuKey="+menuBeanVO.getKey();
	}
	
	/**
	 * 메뉴 수정폼
	 * @param request
	 * @param groupKey
	 * @param key
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/menuUpdatefrm.do")
	public String menuUpdateForm(HttpServletRequest request
			, int groupKey
			, int key
			, ModelMap model) throws Exception{
		
		if(request.getSession().getAttribute("groupKey") == null || "".equals(groupKey)){
			request.getSession().setAttribute("groupKey", "1");
		}
		
		groupKey = Integer.parseInt(request.getSession().getAttribute("groupKey").toString()); 
		
		String menuName = menuService.category2(key);		
		MenuBeanVO menu = menuService.getMenuInfo(key);
		
		List adminList = adminService.adminList();
		
		
		model.addAttribute("key", key);
		model.addAttribute("groupKey", groupKey);
		model.addAttribute("menuName", menuName);
		model.addAttribute("menu", menu);
		model.addAttribute("adminList", adminList);
		
		return "admin/menu/update";
	}
	
	/**
	 * 메뉴 수정
	 * @param request
	 * @param menuBeanVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/menuUpdate.do")
	public String menuUpdate(HttpServletRequest request
			, @ModelAttribute("menuBeanVO") MenuBeanVO menuBeanVO 
			, BindingResult bindingResult
			, ModelMap model) throws Exception{
		
		//System.out.println("groupKey="+menuBeanVO.getGroupKey()+"&rootKey="+menuBeanVO.getRoot()+"&menuKey="+menuBeanVO.getKey());
		
		beanValidator.validate(menuBeanVO, bindingResult);
		if (bindingResult.hasErrors()){
			return "admin/menu/update";
		}
		
		menuService.menuUpdate(menuBeanVO);
		return "redirect:/admin/menuList.do?groupKey="+menuBeanVO.getGroupKey()+"&rootKey="+menuBeanVO.getRoot()+"&menuKey="+menuBeanVO.getKey();
	}
	
	/**
	 * 메뉴 활성 비활성
	 * @param request
	 * @param menuBeanVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/menuVisit.do")
	public String menuVisit(HttpServletRequest request
			, @ModelAttribute("menuBeanVO") MenuBeanVO menuBeanVO 
			, ModelMap model) throws Exception{
		
		menuService.menuVisibleUpdat(menuBeanVO);
		
		return "redirect:/admin/menuList.do?groupKey="+menuBeanVO.getGroupKey()+"&rootKey="+menuBeanVO.getRoot()+"&menuKey="+menuBeanVO.getKey();
	}
	
	/**
	 * 접속통계기능 활성 비활성
	 * @param request
	 * @param menuBeanVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/menuStatis.do")
	public String menuStatis(HttpServletRequest request
			, @ModelAttribute("menuBeanVO") MenuBeanVO menuBeanVO 
			, ModelMap model) throws Exception{
		
		menuService.menuStatisticUpdate(menuBeanVO);
		return "redirect:/admin/menuList.do?groupKey="+menuBeanVO.getGroupKey()+"&rootKey="+menuBeanVO.getRoot()+"&menuKey="+menuBeanVO.getKey();
	}
	
	/**
	 * 메뉴 순서 변경 폼
	 * @param request
	 * @param menuBeanVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/menuOrderfrm.do")
	public String menuOrderfrm(HttpServletRequest request
			, @ModelAttribute("menuBeanVO") MenuBeanVO menuBeanVO 
			, ModelMap model) throws Exception{
		
		List menuList = menuService.menuOrderList(menuBeanVO);
		
		model.addAttribute("menu", menuBeanVO);
		model.addAttribute("menuList", menuList);
		return "admin/menu/order";
	}
	
	/**
	 * 메뉴 순서변경
	 * @param request
	 * @param menuBeanVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/admin/menuOrder.do")
	public String menuOrder(HttpServletRequest request
			, @ModelAttribute("menuBeanVO") MenuBeanVO menuBeanVO 
			, ModelMap model) throws Exception{
		
		menuService.menuOrderUpdate(menuBeanVO);
		return "redirect:/admin/menuList.do?groupKey="+menuBeanVO.getGroupKey()+"&rootKey="+menuBeanVO.getRoot()+"&menuKey="+menuBeanVO.getKey();
	}
	
	/**
	 * 서브메뉴 순서변경폼
	 * @param request
	 * @param menuBeanVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/menuSubOrderfrm.do")
	public String menuSubOrderfrm(HttpServletRequest request
			, @ModelAttribute("menuBeanVO") MenuBeanVO menuBeanVO 
			, ModelMap model) throws Exception{
		
		
		//System.out.println("dddd");
		
		List menuList = menuService.menuSubOrderList(menuBeanVO);
		model.addAttribute("menu", menuBeanVO);
		model.addAttribute("menuList", menuList);
		return "admin/menu/subOrder";
	}
	
	/**
	 * 서브메뉴 순서변경
	 * @param request
	 * @param menuBeanVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/admin/menuSubOrder.do")
	public String menuSubOrder(HttpServletRequest request
			, @ModelAttribute("menuBeanVO") MenuBeanVO menuBeanVO 
			, ModelMap model) throws Exception{
		
		menuService.menuSubOrderUpdate(menuBeanVO);
		
		return "redirect:/admin/menuList.do?groupKey="+menuBeanVO.getGroupKey()+"&rootKey="+menuBeanVO.getRoot()+"&menuKey="+menuBeanVO.getKey();
	}
	
	/**
	 * 메뉴 삭제
	 * @param request
	 * @param menuBeanVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/menuDelete.do")
	public String menuDelete(HttpServletRequest request
			, @ModelAttribute("menuBeanVO") MenuBeanVO menuBeanVO 
			, ModelMap model) throws Exception{
		menuService.menuDelete(menuBeanVO);
		return "redirect:/admin/menuList.do?groupKey="+menuBeanVO.getGroupKey()+"&rootKey="+menuBeanVO.getRoot()+"&menuKey="+menuBeanVO.getKey();
	}
	
	/**
	 * 메뉴 matrix에 담기
	 * @param groupKey
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/matrixList.do")
	public String matrix(int groupKey) throws Exception{
		Vector matrix = new Vector();
		List matrixList = menuService.getAllMenuList();
		
		for(int i=0;i<matrixList.size(); i++){
			MenuBeanVO vo = new MenuBeanVO();
			vo = (MenuBeanVO)matrixList.get(i);
			matrix.add(vo);
		}
		mUtil.setMatrix(matrix);
		return "redirect:/admin/menuList.do?groupKey="+groupKey;
	}
	
	
}
