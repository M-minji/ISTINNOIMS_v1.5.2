package egovframework.rndp.admin.menu.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rndp.admin.admin.service.AdminVO;
import egovframework.rndp.admin.menu.service.AdminmenuService;
import egovframework.rndp.admin.menu.service.AdminmenuVO;

@Controller 
@SessionAttributes({"leftMenuResult"}) 
public class AdminmenuController {
	

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AdminmenuController.class);
	
	
	@Autowired
	private DefaultBeanValidator beanValidator;

	@Resource(name = "adminmenuService")
    private AdminmenuService adminmenuService;
	
	/**
	 * 상단 메뉴
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/topMenu.do")
	public String topMenu(HttpServletRequest request
			,ModelMap model) throws Exception{
		
		AdminVO adminUser = (AdminVO) request.getSession().getAttribute("adminUser");
		int groupkey = (Integer) request.getSession().getAttribute("groupKey");
		LOGGER.debug("groupkey = "+groupkey);
		
		
		AdminmenuVO vo = new AdminmenuVO();
		vo.setId(adminUser.getId());
		vo.setGroupKey(groupkey);
		List adminMenuTopList = adminmenuService.adminmenuTopList(vo);
		LOGGER.debug("adminMenuTopList = "+adminMenuTopList);

		model.addAttribute("adminMenuTopList", adminMenuTopList);
		model.addAttribute("groupKey", groupkey);
		return "admin/top";
	}
	/**
	 * 오른쪽 메뉴
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/rightMenu.do")
	public String rightMenu(ModelMap model) throws Exception{
		
		return "admin/main";
	}
	
	/**
	 * 왼쪽 메뉴
	 * @param rootKey
	 * @param menuKey
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/leftMenu.do")
	public String leftMenu(HttpServletRequest request
			, String rootKey
			, String menuKey
			, ModelMap model) throws Exception{ 
		
		

		if(request.getSession().getAttribute("groupKey") == null || "".equals(request.getSession().getAttribute("groupKey"))){
			request.getSession().setAttribute("groupKey", 1);
		}
		
		int rootKey1 = Integer.parseInt(rootKey);
		int menuKey1 = Integer.parseInt(menuKey);
		LOGGER.debug("rootKey1 = "+rootKey1);
		LOGGER.debug("menuKey1 = "+menuKey1);
		
		List leftMenuResult = adminmenuService.adminmenuLeftList(rootKey1);
		List adminMenuResultAll = adminmenuService.adminMenuAllList();
		
		
		String mainGroup = request.getSession().getAttribute("groupKey").toString();
		LOGGER.debug("mainGroup = "+mainGroup);
		
		AdminmenuVO vo = new AdminmenuVO();
		vo = adminmenuService.adminmenuInfo1(menuKey1); 
	
		model.addAttribute("leftMenuResult", leftMenuResult);
		model.addAttribute("adminMenuResultAll", adminMenuResultAll);
		model.addAttribute("menuSize", leftMenuResult.size());
		model.addAttribute("rootKey", rootKey1);
		model.addAttribute("menuKey", menuKey1);
		model.addAttribute("groupKey", Integer.parseInt(mainGroup));
		
		/**
		 * 2015 05 12 
		 * 전자정부 구조에 맞게 
		 * 메뉴값으로 포워딩하는 구조로 변경 
		 * *VO는 파라미터로 받아도 상관 없으나 POJO 는 파라미터가 선언되어도 
		 * URL에 없으면 에러를 내기 때문에 과거의 방식인 
		 * ?groupKey=${result.groupKey}&rootKey=${result.root}&menuKey=${result.key}
		 * 를 추가한다.
		 * 그외 파라미터가 필요한 부분이 발견되어 parm 분리.
		 * */
		/*
		 * 2015 05 13 이차장님 지시
		 * 일부 메뉴에 그룹 변경해 조회하는 것은 롤에맞지않는다.  
		 * 무조건 로그인시 권한이 있는 메뉴에 따라 그룹이 조회되어야 하고
		 * 권한이 한 그룹에만 있을 경우 셀렉트 박스 없어야 한다.
		 * 권한이 변경됨에 따라 셀렉트 박스가 생기거나 없어지며
		 * 현재 컨텐츠에 존재하는 그룹변경 기능은 전부 숨겨지고
		 * 무조건 탑메뉴에 존재하는 그룹셀렉트 박스에 따라 
		 * 컨텐츠가 조회되며 
		 * 
		 * 만일 탑메뉴의 셀렉트박스가 변경될때 같은 메뉴의 권한이 있으면 
		 * index 로 가지 않고 같은 메뉴로 자동 세팅되어야 한다.
		 * 
		 * 만일 권한이 하나도 없다면 메시지를 뿌리고 로그인 화면으로 가야 한다.
		 * 
		 * */
		String parm = "";
		if("/admin/sitelinkList.do".equals(vo.getUrl())){
			//사이트링크 관리
			parm = "?group=&groupKey="+mainGroup+"&rootKey="+rootKey1+"&menuKey="+menuKey1;
		}else if ("/admin/adminContentList.do".equals(vo.getUrl())) {
			//컨텐츠 관리
			parm = "?err=&groupKey="+mainGroup+"&rootKey="+rootKey1+"&menuKey="+menuKey1;
			//parm = "?err=&groupKey="+mainGroup+"&rootKey="+rootKey1;
		}else if ("/admin/adminAuthList.do".equals(vo.getUrl())) {
			//계정별권한 관리
			parm = "?key=&groupKey="+mainGroup+"&rootKey="+rootKey1+"&menuKey="+menuKey1;
		} else {
			parm = "?groupKey="+mainGroup+"&rootKey="+rootKey1+"&menuKey="+menuKey1;
		}
				
		LOGGER.debug("vo.getUrl()+parm = "+vo.getUrl().trim()+parm);
		return "forward:"+vo.getUrl().trim()+parm;
		//return "admin/left";
	}
	
	/**
	 * 관리자 메뉴 관리 리스트
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/adminMenuList.do")
	public String adminMenuList(HttpServletRequest request
			, ModelMap model) throws Exception{
		
		List result = adminmenuService.adminmenuList();
		model.addAttribute("result", result);
		return "admin/adminmenu/list";
		
	}
	
	/**
	 * 메뉴등록 폼
	 * @param request
	 * @param adminmenuVO
	 * @param key
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/adminMenuInsertfrm.do")
	public String adminMenuInsertfrm(HttpServletRequest request
			, @ModelAttribute("adminmenuVO") AdminmenuVO adminmenuVO 
//			, int key
			, ModelMap model) throws Exception{
		
		if(request.getSession().getAttribute("groupKey") == null || "".equals(request.getSession().getAttribute("groupKey"))){
			request.getSession().setAttribute("groupKey", 1);
		}
		
		int key = adminmenuVO.getKey();
		if(key == 0 ){
			key = 0;
		}
		
		int groupKey = Integer.parseInt(request.getSession().getAttribute("groupKey").toString());
		
		String category = "";
		if(adminmenuVO.getRef() != 0){
			category = adminmenuService.category(adminmenuVO.getRef(), adminmenuVO.getName());
		}

		if(adminmenuVO.getName() != null && !"".equals(adminmenuVO.getName())){
			category += adminmenuVO.getName() +" > ";
		}
		
		model.addAttribute("key", key);
		model.addAttribute("category", category);
		model.addAttribute("groupKey", groupKey);
		
		return "admin/adminmenu/insert";
	}
	
	/**
	 * 메뉴 등록
	 * @param request
	 * @param adminmenuVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/adminMenuInsert.do")
	public String adminMenuInsert(HttpServletRequest request
			, @ModelAttribute("adminmenuVO") AdminmenuVO adminmenuVO 
			, BindingResult bindingResult
			, ModelMap model) throws Exception{
		beanValidator.validate(adminmenuVO, bindingResult);
		if (bindingResult.hasErrors()){
			return "admin/adminmenu/insert";
		}
		adminmenuService.adminmenuInsert(adminmenuVO);
		
		return "redirect:/admin/adminMenuList.do?groupKey="+adminmenuVO.getGroupKey();
	}
	
	/**
	 * 메뉴 등록 폼
	 * @param request
	 * @param adminmenuVO
	 * @param key
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/adminMenuUpdatefrm.do")
	public String adminMenuUpdatefrm(HttpServletRequest request
			, @ModelAttribute("adminmenuVO") AdminmenuVO adminmenuVO
			, String key
			, ModelMap model) throws Exception{

		if(request.getSession().getAttribute("groupKey") == null || "".equals(request.getSession().getAttribute("groupKey"))){
			request.getSession().setAttribute("groupKey", "1");
		}
		
		int groupKey = Integer.parseInt(request.getSession().getAttribute("groupKey").toString());
		
		AdminmenuVO menuResult = adminmenuService.adminmenuInfo1(Integer.parseInt(key));
		String category = "";
		if(menuResult.getRef() != 0){
			category = adminmenuService.category2(menuResult.getRef());
		}
		
		menuResult.setPreRefKey(adminmenuVO.getRef());
		
		model.addAttribute("key", key);
		model.addAttribute("category", category);
		model.addAttribute("groupKey", groupKey);
		model.addAttribute("menu", menuResult);
		return "admin/adminmenu/update";
	}
	
	/**
	 * 메뉴 수정
	 * @param request
	 * @param adminmenuVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/adminMenuUpdate.do")
	public String adminMenuUpdate(HttpServletRequest request
			, @ModelAttribute("adminmenuVO") AdminmenuVO adminmenuVO
			, BindingResult bindingResult
			, ModelMap model) throws Exception{

		beanValidator.validate(adminmenuVO, bindingResult);
		if (bindingResult.hasErrors()){
			if(request.getSession().getAttribute("groupKey") == null || "".equals(request.getSession().getAttribute("groupKey"))){
				request.getSession().setAttribute("groupKey", "1");
			}
			
			int groupKey = Integer.parseInt(request.getSession().getAttribute("groupKey").toString());
			
			AdminmenuVO menuResult = adminmenuService.adminmenuInfo1(adminmenuVO.getKey());
			String category = "";
			if(menuResult.getRef() != 0){
				category = adminmenuService.category2(menuResult.getRef());
			}
			menuResult.setPreRefKey(adminmenuVO.getRef());
			
			model.addAttribute("key", adminmenuVO.getKey());
			model.addAttribute("category", category);
			model.addAttribute("groupKey", groupKey);
			model.addAttribute("menu", menuResult);
			return "admin/adminmenu/update";
		}
		adminmenuService.adminmenuUpdate(adminmenuVO);
		return "redirect:/admin/adminMenuList.do?groupKey="+adminmenuVO.getGroupKey();
	}
	
	/**
	 * 메뉴순서변경 폼
	 * @param request
	 * @param name
	 * @param root
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/adminMenuOrderfrm.do")
	public String adminMenuOrderForm(HttpServletRequest request
			, String name
			, String root
			, ModelMap model) throws Exception{
		
		List result = adminmenuService.adminmenuOrderList(Integer.parseInt(root));
		
		model.addAttribute("name", name);
		model.addAttribute("root", Integer.parseInt(root));
		model.addAttribute("result", result);
	
		return "admin/adminmenu/order";
	}
	
	/**
	 * 메뉴 순서 변경
	 * @param request
	 * @param adminmenuVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/adminMenuOrder.do")
	public String adminMenuOrder(HttpServletRequest request
			, @ModelAttribute("adminmenuVO") AdminmenuVO adminmenuVO 
			, BindingResult bindingResult
			, ModelMap model) throws Exception{
		
		beanValidator.validate(adminmenuVO, bindingResult);
		if (bindingResult.hasErrors()){
			return "forward:/admin/adminMenuOrderfrm.do?name="+adminmenuVO.getName()+"&root="+adminmenuVO.getRoot();
		}
//		adminmenuService.adminmenuOrder(adminmenuVO);

		return "forward:/admin/adminMenuList.do";
	}
	
	/**
	 * 서브메뉴순서변경 폼
	 * @param request
	 * @param root
	 * @param name
	 * @param depth
	 * @param step
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/adminMenuSubOrderfrm.do")
	public String adminMenuSubOrderList(HttpServletRequest request
			, String root
			, String name
			, String depth
			, String step
			, ModelMap model) throws Exception{
		
		AdminmenuVO vo = new AdminmenuVO();
		vo.setRoot(Integer.parseInt(root));
		vo.setName(name);
		vo.setDepth(Integer.parseInt(depth));
		vo.setStep(Integer.parseInt(step));
		
		List result = adminmenuService.adminmenuSubOrderList(vo);
		
		
		model.addAttribute("name", name);
		model.addAttribute("root", Integer.parseInt(root));
		model.addAttribute("depth", Integer.parseInt(depth));
		model.addAttribute("step", Integer.parseInt(step));
		
		model.addAttribute("result", result);
		
		return "admin/adminmenu/subOrder";
	}
	
	/**
	 * 서브메뉴순서변경
	 * @param request
	 * @param adminmenuVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/adminMenuSubOrder.do")
	public String adminMenuSubOrder(HttpServletRequest request
			, @ModelAttribute("adminmenuVO") AdminmenuVO adminmenuVO 
			, BindingResult bindingResult
			, ModelMap model) throws Exception{
		
		beanValidator.validate(adminmenuVO, bindingResult);
		if (bindingResult.hasErrors()){
			return "forward:/admin/adminMenuSubOrderfrm.do?name="+adminmenuVO.getName()+"&root="+adminmenuVO.getRoot()+"&step="+adminmenuVO.getStep()+"&depth="+adminmenuVO.getDepth();
		}
		
		adminmenuService.adminmenuSubOrder(adminmenuVO);
		return "forward:/admin/adminMenuList.do";
	}
	
	/**
	 * 관리자 메뉴 삭제
	 * @param request
	 * @param key
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/adminMenuDelete.do")
	public String adminMenuDelete(HttpServletRequest request
			, String key
			, ModelMap model) throws Exception{
		
		if(key != null && !"".equals(key)){
			adminmenuService.adminmenuDelete(Integer.parseInt(key));
		}
		return "forward:/admin/adminMenuList.do";
	}
	
}
