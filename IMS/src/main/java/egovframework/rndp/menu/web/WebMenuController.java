package egovframework.rndp.menu.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.rndp.admin.env.service.EnvService;
import egovframework.rndp.admin.env.service.EnvVO;
import egovframework.rndp.admin.member.service.MemberService;
import egovframework.rndp.admin.member.service.MemberVO;
import egovframework.rndp.admin.menu.service.MenuBeanVO;
import egovframework.rndp.com.cmm.service.EgovProperties;
import egovframework.rndp.com.utl.EgovStringUtil;
import egovframework.rndp.com.utl.MatrixUtil;
import egovframework.rndp.login.service.UserInfoVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class WebMenuController {

	@Resource(name = "mUtil")
	private MatrixUtil mUtil;
	
	@Resource(name="envService")
	private EnvService envService;
	
	
	// member 추가 2014.07.27 21:00
	@Resource(name = "memberService")
	private MemberService memberService;
	
	@RequestMapping(value="/webMenu.do")
	public String webMenu(HttpServletRequest request
			, int key
			, ModelMap model) throws Exception{
		
		UserInfoVO user = (UserInfoVO) request.getSession().getAttribute("user"); 
		
		// DB에 저장되는 jsp페이지와 메핑되는 jsp를 생각해야됨 - 지금은 DB에 있는 jsp를 가지고와서 처리했
		String strUrl = "";
		String topMenu = "";
		String topMenu2 = "";
		String subMenu = "";
		String leftMenu = "";
		String mobileMenu = "";
		MenuBeanVO menu = null;
		
		//기업정보
//		List envList = envService.envList();
		
		List envList = envService.envList();
		for(int i=0; i<envList.size(); i++){
			EnvVO eVO = (EnvVO)envList.get(i);	
			System.err.println("name" + eVO.getName());
			System.err.println("file" + eVO.getAtchFileName());
			if("companyName".equals(eVO.getName())){				// 회사명
				String companyName = eVO.getValue();
				model.addAttribute("companyName", companyName);
			}else if("companyCeo".equals(eVO.getName())){			// 대표자
				String companyCeo = eVO.getValue();
				model.addAttribute("companyCeo", companyCeo);
			}else if("companyIncorpo".equals(eVO.getName())){		// 설립일
				String companyIncorpo = eVO.getValue();
				model.addAttribute("companyIncorpo", companyIncorpo);
			}else if("companyAddr".equals(eVO.getName())){		 	// 주소
				String companyAddr = eVO.getValue();
				model.addAttribute("companyAddr", companyAddr);
			}else if("companyTel".equals(eVO.getName())){		 	// 대표전화
				String companyTel = eVO.getValue();
				model.addAttribute("companyTel", companyTel);
			}else if("companyFax".equals(eVO.getName())){		 	// 팩스
				String companyFax = eVO.getValue();
				model.addAttribute("companyFax", companyFax);
			}else if("companyPost".equals(eVO.getName())){		 	// 우편번호
				String companyPost = eVO.getValue();
				model.addAttribute("companyPost", companyPost);
			}else if("comLogImg".equals(eVO.getName())){		 	
				String comLogImg = eVO.getValue();
				System.err.println(comLogImg);
				model.addAttribute("comLogImg", comLogImg);
			}else if("comLogImgName".equals(eVO.getName())){		 	
				String comLogImgName = eVO.getValue();
				model.addAttribute("comLogImgName", comLogImgName);
			}else if("mainLogImg".equals(eVO.getName())){		 	
				String mainLogImg = eVO.getValue();
				System.err.println(mainLogImg);
				model.addAttribute("mainLogImg", mainLogImg);
			}else if("mainLogImgName".equals(eVO.getName())){		 	
				String mainLogImgName = eVO.getValue();
				model.addAttribute("mainLogImgName", mainLogImgName);
			}
		} 
		
		model.addAttribute("envList", envList);
		
		HashMap<String, String> envMap = new HashMap<String, String>();
		EnvVO tmpVO = new EnvVO();
		String topMenuLogo = "";
		for(int i = 0; i< envList.size(); i++){
			tmpVO = (EnvVO)envList.get(i);
			envMap.put(tmpVO.getName(), tmpVO.getValue());
			if(tmpVO.getName().equals("companyImage1"))	topMenuLogo = tmpVO.getValue();
		}
		
		subMenu = mUtil.getSelectMenuList_left(key); 
		leftMenu = mUtil.getRootMenuList_left(1, 1, topMenuLogo);
		mobileMenu = mUtil.getRootMobileMenuList(1, 1, topMenuLogo);
		menu = mUtil.getMenuInfoEx(key); 
			

//		String menuCategory = mUtil.getMenuCategory(key);                      							//category 정보를 가저옴
		String menuCategory = mUtil.getHomeMenuCategory(key);

		
		model.addAttribute("envMap", envMap);                      //기업 정보
		
		
		model.addAttribute("user", request.getSession().getAttribute("user"));
		model.addAttribute("menuCategory", menuCategory);
		model.addAttribute("key", menu.getKey());
		model.addAttribute("groupKey", menu.getGroupKey());
//		model.addAttribute("topMenu", topMenu);
//		model.addAttribute("topMenu2", topMenu2);
		model.addAttribute("subMenu", subMenu);
		model.addAttribute("leftMenu", leftMenu);
		model.addAttribute("mobileMenu", mobileMenu);
		model.addAttribute("screenId", menu.getScreenId());
		
		MenuBeanVO menuBeanVO = mUtil.getMenuInfo(key);
		menuBeanVO.setKey(key);
		
		int menuDepth = menuBeanVO.getDepth();
		
		ArrayList<MenuBeanVO> tabMenu = new ArrayList<MenuBeanVO>();
		String menuNm = menu.getName();
		
		if(menuDepth == 0){
			MenuBeanVO tempMenuBeanVO = mUtil.getMenuInfoEx(key);
			tabMenu = mUtil.getRefMenuList(tempMenuBeanVO.getKey());
			
			if(tabMenu.size() > 0){
				MenuBeanVO tempMenu = (MenuBeanVO)tabMenu.get(0);
				menuNm = tempMenu.getName();
			}
		}
		if(menuDepth == 1){
			tabMenu = mUtil.getRefMenuList(key);
		}
		if(menuDepth == 2){
			tabMenu = mUtil.getRefMenuList(menuBeanVO.getRef());
		}
		
		model.addAttribute("menuNm", menuNm);		
	    model.addAttribute("tabMenu", tabMenu);
		
		return strUrl;
		
	}
	
	
}
