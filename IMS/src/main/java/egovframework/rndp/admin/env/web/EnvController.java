package egovframework.rndp.admin.env.web;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rndp.admin.env.service.EnvHistoryVO;
import egovframework.rndp.admin.env.service.EnvPatentVO;
import egovframework.rndp.admin.env.service.EnvRecruitVO;
import egovframework.rndp.admin.env.service.EnvService;
import egovframework.rndp.admin.env.service.EnvVO;
import egovframework.rndp.com.cmm.service.EgovProperties;
import egovframework.rndp.com.utl.DownloadFileUtil;
import egovframework.rndp.com.utl.EgovDateUtil;
import egovframework.rndp.com.utl.JsonHelper;

/**
 * 기업정보 관리
 * Controller
 * @author DY
 *
 */
@Controller
public class EnvController {

	@Resource(name="envService")
	private EnvService envService;

	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;
	/**
	 * 기본정보 LIST
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/envInfo.do") 
	public String envInfo(ModelMap model) throws Exception{
		
		List envList = envService.envList();
		
		for(int i=0; i<envList.size(); i++){
			EnvVO eVO = (EnvVO)envList.get(i);	
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
			}else if("comLogImg".equals(eVO.getName())){		 	// 우편번호
				String comLogImg = eVO.getValue();
				model.addAttribute("comLogImg", comLogImg);
			}else if("comLogImgName".equals(eVO.getName())){		 	// 우편번호
				String comLogImgName = eVO.getValue();
				model.addAttribute("comLogImgName", comLogImgName);
			}else if("mainLogImg".equals(eVO.getName())){		 	// 우편번호
				String mainLogImg = eVO.getValue();
				model.addAttribute("mainLogImg", mainLogImg);
			}else if("mainLogImgName".equals(eVO.getName())){		 	// 우편번호
				String mainLogImgName = eVO.getValue();
				model.addAttribute("mainLogImgName", mainLogImgName);
			}
		}
		
		model.addAttribute("envList", envList);
		
		return "admin/env/env_List";	
//		return "admin/env/envUpdate";	// 기존은 IF(조건 방식)
	}
	
	/**
	 * 기본정보 수정(update)
	 * @param model
	 * @param envVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/envUpdate.do")
	public String envUpdate(ModelMap model,
			@ModelAttribute("envVO") EnvVO envVO) throws Exception{
		String[] name 	= EgovStringUtil.split(envVO.getName(), ",");
		String[] value 	= EgovStringUtil.split(envVO.getValue(), ",");
		EnvVO vo = new EnvVO();
		for(int i = 0; i < name.length; i++){
			vo.setName(name[i]);
			vo.setValue(value[i]);
			envService.updateEnv(vo);
		}
		 
		return "redirect:/admin/envInfo.do";
	}
	
	/**
	 * 기타관리 List
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/envEtcList.do")  
	public String envEtc(ModelMap model) throws Exception{
		
		List envList = envService.envList();
		for(int i=0; i<envList.size(); i++){
			EnvVO eVO = (EnvVO)envList.get(i);	
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
			}else if("comLogImg".equals(eVO.getName())){		 	// 우편번호
				String comLogImg = eVO.getValue();
				model.addAttribute("comLogImg", comLogImg);
			}else if("comLogImgName".equals(eVO.getName())){		 	// 우편번호
				String comLogImgName = eVO.getValue();
				model.addAttribute("comLogImgName", comLogImgName);
			}else if("mainLogImg".equals(eVO.getName())){		 	// 우편번호
				String mainLogImg = eVO.getValue();
				model.addAttribute("mainLogImg", mainLogImg);
			}else if("mainLogImgName".equals(eVO.getName())){		 	// 우편번호
				String mainLogImgName = eVO.getValue();
				model.addAttribute("mainLogImgName", mainLogImgName);
			}
		}
		model.addAttribute("envList", envList);
				
		
		return "admin/env/envEtcUpdate";
	}
	
	/**
	 * 기타관리 수정(update)
	 * @param model
	 * @param envVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/envEtcUpdate.do")
	public String envEtcUpdate(final MultipartHttpServletRequest multiRequest
			, EnvVO envVO) throws Exception{
		String[] name 	= EgovStringUtil.split(envVO.getName(), ",");
		String[] value 	= EgovStringUtil.split(envVO.getValue(), ",");
		EnvVO vo = new EnvVO();
		for(int i = 0; i < name.length; i++){
			vo.setName(name[i]);
			vo.setValue(value[i]);
			envService.updateEnv(vo);
		
		}
		
		return "redirect:/admin/envEtcList.do";
	}
	
// 연혁관리 =================================================================================
	
	/**
	 * 연혁관리 List
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/envHistory.do")  
	public String envHistory(ModelMap model) throws Exception{
		
		List envHistoryList = envService.envHistoryList();
		model.addAttribute("envHistoryList", envHistoryList);
		
		return "admin/env/envHistoryList";
	}
	
	/**
	 * 연혁관리 insertForm
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/HistoryInsertfrm.do")
	public String envHistoryInsertForm(ModelMap model) throws Exception{
		List yearList = EgovDateUtil.chkYearList(10);
		int year = EgovDateUtil.getYear();
		int month = EgovDateUtil.getMonth();
		List monthList = EgovDateUtil.getMonthList();
		
		model.addAttribute("yearList", yearList);
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("monthList", monthList);
		
		return "admin/env/envHistoryInsert";
	}
	/**
	 * 연혁관리 insert
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/HistoryInsert.do")
	public String envHistoryInsert(EnvHistoryVO vo) throws Exception{
		envService.insertEnvHistory(vo);	// key==step 자동생성
		return "redirect:/admin/envHistory.do";
	}
	
	/**
	 * 연혁관리 update Form
	 * @param model
	 * @param key
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/HistoryUpdatefrm.do")
	public String envHistoryUpdateForm(ModelMap model ,int key) throws Exception{
		EnvHistoryVO historyVO = envService.envHistoryInfo(key);
		List yearList = EgovDateUtil.chkYearList(10);
		List monthList = EgovDateUtil.getMonthList();
		
		model.addAttribute("history", historyVO);
		model.addAttribute("yearList", yearList);
		model.addAttribute("monthList", monthList);
		
		return "admin/env/envHistoryUpdate";
	}
	
	/**
	 * 연혁관리 update(E.수정)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/HistoryUpdate.do")
	public String envHiStoryUpdate(EnvHistoryVO vo) throws Exception{
		envService.updateEnvHistory(vo);
		return "redirect:/admin/envHistory.do";
	}
	
	/**
	 * 연혁관리 update(visible)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/HistoryVisibleUpt.do")
	public String uptHistoryVible(EnvHistoryVO vo) throws Exception{
		envService.uptHistoryVisible(vo);
		return "redirect:/admin/envHistory.do";
	}
	
	/**
	 * 연혁관리 delete
	 * @param historyVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/HistoryDelete.do")
	public String deleteHistory(@ModelAttribute("historyVO") EnvHistoryVO historyVO) throws Exception{
		
		int key = historyVO.getKey();
		envService.deleteHistory(key);
		return "redirect:/admin/envHistory.do";
	}
	
// 특허관리 ========================================================================================
	
	/**
	 * 특허관리 List
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/envPatentList.do")  
	public String envPatent(ModelMap model) throws Exception{
		
		List patentList = envService.patentList();
		model.addAttribute("patentList", patentList);
		
		return "admin/env/envPatentList";
	}
	
	/**
	 * 특허관리 InsertForm
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/envPatentInsertfrm.do") 
	public String patentInsertForm(ModelMap model) throws Exception{
		int maxRank = envService.getMaxRank();
		model.addAttribute("maxRank", maxRank);
		return "admin/env/envPatentInsert";
	}
	
	/**
	 * 특허관리 Insert
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/envPatentInsert.do")
	public String patentInsert(EnvPatentVO vo) throws Exception{
		envService.patentInsert(vo);
		return "redirect:/admin/envPatentList.do";
	}
	
	/**
	 * 특허관리 Update Form
	 * @param model
	 * @param key
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/envPatentUpdatefrm.do")
	public String patentUptForm(ModelMap model ,int key) throws Exception{
		EnvPatentVO envPatentInfo = envService.envPatentByInfo(key);
		model.addAttribute("envPatentInfo", envPatentInfo);
		return "admin/env/envPatentUpdate";
	}
	
	/**
	 * 특허관리 Update(E.수정)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/envPatentUpdate.do")
	public String patentUpdate(EnvPatentVO vo) throws Exception{
		envService.envPatentUpt(vo);
		return "redirect:/admin/envPatentList.do";
	}
	
	/**
	 * 특허관리 UpdateForm(M.순서변경)
	 * @param model
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/envPatentOrderfrm.do")
	public String patentOrderForm(ModelMap model, EnvPatentVO vo) throws Exception{
		
		List rankOfList = envService.patentOrderByRank();	// Rank 순서에 의한 List
		EnvPatentVO ktVO = envService.patentByKeyTitle(vo);	// Key, Title 검색조건
		model.addAttribute("rankOfList", rankOfList);
		model.addAttribute("ktVO", ktVO);
		
		return "admin/env/envPatentOrder";
	}
	@RequestMapping(value="/admin/envPatentOrder.do")
	public String movePatentUpt(EnvPatentVO vo) throws Exception{
		envService.movePatentUpt(vo);
		return "redirect:/admin/envPatentList.do";
	}
	
	/**
	 * 특허관리 Delete
	 * @param patentVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/envPatentDelete.do")
	public String envPatentDelete(@ModelAttribute("patentVO") EnvPatentVO patentVO) throws Exception{
		int key = patentVO.getKey();
		envService.envPatentDelete(key);
		return "redirect:/admin/envPatentList.do";
	}
	
// 채용정보 ========================================================================================
	
	/**
	 * 채용정보 List
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/envRecruit.do")  
	public String envRecruit(ModelMap model) throws Exception{
		List envRecruitList = envService.envRecruitList();
		model.addAttribute("envRecruitList", envRecruitList);

		return "admin/env/recruitList";
	}
	
	/**
	 * 채용정보 Insert Form
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/recruitInsertfrm.do")
	public String reecruitInsertForm(ModelMap model) throws Exception{
		List envList = envService.envList();
		model.addAttribute("envList", envList);
		return "admin/env/recruitInsert";
	}
	
	@RequestMapping(value="/admin/recruitInsert.do")
	public String reecruitInsert(final MultipartHttpServletRequest multiRequest
			, EnvRecruitVO vo) throws Exception{

		
		String uploadPath = EgovProperties.getProperty("board.env");        //cms.properties에서 가져오는 파일 경로
		File saveFolder = new File(uploadPath);
		
		// 디렉토리 생성
		if (!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}
		
		Iterator<String> itr = multiRequest.getFileNames();
		while (itr.hasNext()) {
			MultipartFile multiFile = multiRequest.getFile((String) itr.next());
			if(!"".equals(multiFile.getOriginalFilename())) {
				multiFile.transferTo(new File(uploadPath + "/" + multiFile.getOriginalFilename()));
				vo.setFileName(multiFile.getOriginalFilename());
			}
		}
		
		envService.reecruitInsert(vo);
		return "redirect:/admin/envRecruit.do";
	}
	
	/**
	 * 채용정보 Update Form
	 * @param model
	 * @param key
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/recruitUpdatefrm.do")
	public String recruitUpdateForm(ModelMap model, int key) throws Exception{
		EnvRecruitVO recruitInfo = envService.recruitInfo(key);
		model.addAttribute("recruitInfo", recruitInfo);
		return "admin/env/recruitUpdate";
	}
	
	/**
	 * 채용정보 Update(E.수정)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/recruitUpdate.do")
	public String recruitUpdate(final MultipartHttpServletRequest multiRequest
			, EnvRecruitVO vo) throws Exception{
		
		String uploadPath = EgovProperties.getProperty("board.env");        //cms.properties에서 가져오는 파일 경로
		File saveFolder = new File(uploadPath);
		
		// 디렉토리 생성
		if (!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}
		
		Iterator<String> itr = multiRequest.getFileNames();
		while (itr.hasNext()) {
			MultipartFile multiFile = multiRequest.getFile((String) itr.next());
			if(!"".equals(multiFile.getOriginalFilename())) {
				File oldFile = new File(uploadPath+"/"+vo.getFileName());
				if(oldFile.exists() && oldFile.isFile()){
					oldFile.delete();
				}
				multiFile.transferTo(new File(uploadPath + "/" + multiFile.getOriginalFilename()));
				vo.setFileName(multiFile.getOriginalFilename());
			}
		}
		
		envService.recruitUpdate(vo);
		return "redirect:/admin/envRecruit.do";
	}
	
	/**
	 * 채용정보 Update(visible)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/recruitActive.do")
	public String recruitActiveUpt(EnvRecruitVO vo) throws Exception{
		envService.recruitActiveUpt(vo);
		return "redirect:/admin/envRecruit.do";
	}
	
	/**
	 * 채용정보 Delete
	 * @param recruitVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/recruitDelete.do")
	public String recruitDelete(@ModelAttribute("recruitVO") EnvRecruitVO recruitVO) throws Exception{
		int key = recruitVO.getKey();
		EnvRecruitVO recruitInfo = envService.recruitInfo(key);
		if(recruitInfo.getFileName() != null && !"".equals(recruitInfo.getFileName())){
			String uploadPath = EgovProperties.getProperty("board.env");        //cms.properties에서 가져오는 파일 경로
			File oldFile = new File(uploadPath+"/"+recruitInfo.getFileName());
			if(oldFile.exists() && oldFile.isFile()){
				oldFile.delete();
			}
		}
		envService.envRecruitDelete(key);
		return "redirect:/admin/envRecruit.do";
	}

// 기타문의관리 ======================================================================	
	
	/**
	 * 기타문의관리 List
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/contactusList.do")  
	public String contactus(ModelMap model) throws Exception{
		
		List envList = envService.envList();
		model.addAttribute("envList", envList);
		
		return "admin/env/contactusUpdate";
	}
	/**
	 * 기타문의관리 수정(update)
	 * @param model
	 * @param envVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/contactusUpdate.do")
	public String contactusUpdate(ModelMap model,
			@ModelAttribute("envVO") EnvVO envVO) throws Exception{
		
		for(int i=0; i<envVO.getName().split(",").length; i++){
			EnvVO vo = new EnvVO();
			vo.setName(envVO.getName().split(",")[i]);
			vo.setValue(envVO.getValue().split(",")[i]);
			envService.updateEnv(vo);
		}
		
		return "redirect:/admin/contactusList.do";
	}
	
	@RequestMapping(value="/envDownload.do")
	public void adminDownload(HttpServletRequest request
			, String fileName
			, HttpServletResponse response) throws Exception{
		String filePath = EgovProperties.getProperty("board.env");        //cms.properties에서 가져오는 파일 경로

		String serverFilename = fileName;
		DownloadFileUtil downFile = new DownloadFileUtil();
		downFile.setServerFilename(serverFilename);
		downFile.setFileName(fileName);
		downFile.setFilePath(filePath);
		downFile.download(request, response);
	}
	
	@RequestMapping(value = "/admin/env/popup/mesIMGregAdd.do")
	public String mesIMGregAddPopup(HttpServletRequest request,
			@ModelAttribute("EnvVO") EnvVO envVO, ModelMap model) throws Exception {

		envVO.seteGubunPage("Y");

		 model.addAttribute("EnvVO", envVO);
		 model.addAttribute("eGubunPage",  envVO.geteGubunPage());
		return "adminPopup/admin/env/popup/kw_evn_IMGreg";
	}
	
	// 이미지 업로드 팝업창                  
	@RequestMapping(value = "/admin/env/popup/mesIMGregAdd_i.do")
	public String mesIMGregAddInsert(HttpServletRequest request,
			@ModelAttribute("envVO") EnvVO envVO, ModelMap model) throws Exception {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> files = multipartRequest.getFileMap();
    
		List<FileVO> result = null;
		String atchFileId = "";
		if (!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, "evn_", 0, "", "evnImagePath");
			atchFileId = fileMngService.insertFileInfs(result);
			envVO.setValue(atchFileId);
			envService.updateEnv(envVO);
		}
		envVO.seteGubunPage("N");
		return "adminPopup/admin/env/popup/kw_evn_IMGreg";
	}
	
	/*기업정보관리 화면에서 
	 * 파일일 x 눌러서 삭제하기
	 */
	@RequestMapping(value = "/admin/env/mesIMGregDelete.do")
	public void mesSetContentAjax(HttpServletRequest request
			, HttpServletResponse response
			, @ModelAttribute("EnvVO") EnvVO envVO) throws Exception{
 		
//			envService.deleteRealPathFile(envVO);

         	envService.updateEnv(envVO);
	
	}
}
