package egovframework.rndp.main;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.rndp.admin.env.service.EnvService;
import egovframework.rndp.admin.env.service.EnvVO;
import egovframework.rndp.com.utl.EgovDateUtil;
import egovframework.rndp.com.utl.EgovStringUtil;
import egovframework.rndp.com.utl.MatrixUtil;

@Controller
public class MainController {
	
	
	@Resource(name = "mUtil")
	private MatrixUtil mUtil;
	
	@Resource(name = "envService")
	private EnvService envService;
	
	/**
	 * smart 메인
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/main.do")
	public String main(HttpServletRequest request
			, ModelMap model) throws Exception{

		//기업정보
		List envList = envService.envList();
		HashMap<String, String> envMap = new HashMap<String, String>();
		EnvVO tmpVO = new EnvVO();
		String topMenuLogo = "";
		for(int i = 0; i< envList.size(); i++){
			tmpVO = (EnvVO)envList.get(i);
			envMap.put(tmpVO.getName(), tmpVO.getValue());
			if(tmpVO.getName().equals("companyImage1"))	topMenuLogo = tmpVO.getValue();
		}

//		String topMenu = mUtil.getRootMenuList(1, 1, topMenuLogo);
//		String topMenu2 = mUtil.getRootMenuList2(1, 1, topMenuLogo);
		String leftMenu = mUtil.getRootMenuList_left(1, 1, topMenuLogo);
		String mobileMenu = mUtil.getRootMobileMenuList(1, 1, topMenuLogo);
		int uniteKey = mUtil.getUniteMenuKey("통합검색",1);
		
//		model.addAttribute("topMenu", topMenu);
//		model.addAttribute("topMenu2", topMenu2);
		model.addAttribute("leftMenu", leftMenu);
		model.addAttribute("mobileMenu", mobileMenu);
		model.addAttribute("uniteKey", uniteKey);
		model.addAttribute("envMap", envMap);                      //기업 정보
		
		return "rndp/main/main.tiles";
	}
	
	/**
	 * intro 메인
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/introMain.do")
	public String mainIntro(HttpServletRequest request
			, ModelMap model) throws Exception{

		
		//기업정보
		List envList = envService.envList();
		HashMap<String, String> envMap = new HashMap<String, String>();
		EnvVO tmpVO = new EnvVO();
		String topMenuLogo = "";
		for(int i = 0; i< envList.size(); i++){
			tmpVO = (EnvVO)envList.get(i);
			envMap.put(tmpVO.getName(), tmpVO.getValue());
			if(tmpVO.getName().equals("companyImage1"))	topMenuLogo = tmpVO.getValue();
		}

		
		
		String topMenu = mUtil.getIntroMenuList(2, 1);
		int uniteKey = mUtil.getUniteMenuKey("통합검색", 2);
		
		
		
		model.addAttribute("topMenu", topMenu);
		model.addAttribute("uniteKey", uniteKey);                 //메인 팝업존 모든 갯수
		model.addAttribute("envMap", envMap);                      //기업 정보
		
		return "intro/intro/main.tiles";
	}
}
