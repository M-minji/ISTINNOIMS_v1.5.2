package egovframework.rndp.com.utl;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.rndp.com.cmm.service.EgovProperties;

@Controller
public class FileUploadController {
	

	private static final Logger LOGGER = LoggerFactory
			.getLogger(FileUploadController.class);
	
	@RequestMapping(method=RequestMethod.POST, value="/smartImageUpload.do")
	public void smartImageUpload(HttpServletRequest request
			, HttpServletResponse response) throws Exception{
		
		String oldFileNm = request.getParameter("oldFileNm");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		/**
		 * 년과 월로 폴더 생성
		 */
		String year = String.valueOf(EgovDateUtil.getYear());
		String mm = String.valueOf(EgovDateUtil.getMonth());
		
		
		
		/**
		 * 2015 05 20 과거에 컴파일된 경로 만 설정행을 경우 
		 * 서버를 다시 시작하면 이미지 사라짐
		 * 이차장님 논의결과
		 * 에디터 수정해서 따로 저장하도록 변경 
		 * */
		String filePath = EgovProperties.getProperty("smartImage");                    //cms.properties에서 가져오는 파일 경로(컴파일된 폴더 경로)
		String fileRealPath = EgovProperties.getProperty("smartImageRealpath");        //cms.properties에서 가져오는 파일 경로(작업용 폴더 경로)
		String fileSavePath = "";
		Iterator<String> itr = multipartRequest.getFileNames();
		File saveFolder = new File(filePath+"/"+year+"/"+mm);
		File realSaveFolder = new File(fileRealPath+"/"+year+"/"+mm);
		
		// 디렉토리 생성
		if (!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}
		if (!realSaveFolder.exists() || realSaveFolder.isFile()) {
			realSaveFolder.mkdirs();
		}

		while (itr.hasNext()) {
			MultipartFile multiFile = multipartRequest.getFile((String) itr.next());
			String saveServerFileName = System.currentTimeMillis()+"."+EgovStringUtil.extension(multiFile.getOriginalFilename()); 
			if(!"".equals(multiFile.getOriginalFilename())) {
				File oldFile = new File(filePath+"/"+year+"/"+mm+"/"+oldFileNm);
				if(oldFile.exists() && oldFile.isFile()){
					oldFile.delete();
				}

				fileSavePath = year+"/"+mm+"/"+saveServerFileName;
				multiFile.transferTo(new File(filePath+"/"+ fileSavePath));
				multiFile.transferTo(new File(fileRealPath+"/"+ fileSavePath));
			}
		}

		JsonHelper helper = new JsonHelper();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("fileName", fileSavePath);
		helper.printJsonObject(response, "result", map);
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/smartImage.do")
	public String smartImage(HttpServletRequest request
			, HttpServletResponse response ,String name
			, ModelMap model) throws Exception{
		
		String oldFileNm = request.getParameter("oldFileNm");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		/**
		 * 년과 월로 폴더 생성
		 */
		String year = String.valueOf(EgovDateUtil.getYear());
		String mm = String.valueOf(EgovDateUtil.getMonth());

		
		
		/**
		 * 2015 05 20 과거에 컴파일된 경로 만 설정행을 경우 
		 * 서버를 다시 시작하면 이미지 사라짐
		 * 이차장님 논의결과
		 * 에디터 수정해서 따로 저장하도록 변경 
		 * */
		String filePath = EgovProperties.getProperty("smartImage");                    //cms.properties에서 가져오는 파일 경로(컴파일된 폴더 경로)
		String fileRealPath = EgovProperties.getProperty("smartImageRealpath");        //cms.properties에서 가져오는 파일 경로(작업용 폴더 경로)
		String fileSavePath = "";
		Iterator<String> itr = multipartRequest.getFileNames();
		File saveFolder = new File(filePath+"/"+year+"/"+mm);
		File realSaveFolder = new File(fileRealPath+"/"+year+"/"+mm);
		
		LOGGER.debug("saveFolder = "+saveFolder);
		// 디렉토리 생성
		if (!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}
		if (!realSaveFolder.exists() || realSaveFolder.isFile()) {
			realSaveFolder.mkdirs();
		}

		while (itr.hasNext()) {
			MultipartFile multiFile = multipartRequest.getFile((String) itr.next());
			String saveServerFileName = System.currentTimeMillis()+"."+EgovStringUtil.extension(multiFile.getOriginalFilename()); 
			if(!"".equals(multiFile.getOriginalFilename())) {
				File oldFile = new File(filePath+"/"+year+"/"+mm+"/"+oldFileNm);
				if(oldFile.exists() && oldFile.isFile()){
					oldFile.delete();
				}

				fileSavePath = year+"/"+mm+"/"+saveServerFileName;
				multiFile.transferTo(new File(filePath+"/"+ fileSavePath));
				multiFile.transferTo(new File(fileRealPath+"/"+ fileSavePath));
				
				
				System.out.println("fileSavePath = " + fileSavePath);
				System.out.println("filePath = " + filePath+"/"+ fileSavePath);
				System.out.println("fileRealPath = " + fileRealPath+"/"+ fileSavePath);
			}
		}
		LOGGER.debug("contentName = "+name);
		model.addAttribute("filesaveName", fileSavePath);
		model.addAttribute("contentName", name);
		return "seUpload/imgUploadForm";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/smartImage2.do")
	public String smartImage2(HttpServletRequest request
			, HttpServletResponse response
			, ModelMap model) throws Exception{
		
		String oldFileNm = request.getParameter("oldFileNm");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		/**
		 * 년과 월로 폴더 생성
		 */
		String year = String.valueOf(EgovDateUtil.getYear());
		String mm = String.valueOf(EgovDateUtil.getMonth());

		
		
		/**
		 * 2015 05 20 과거에 컴파일된 경로 만 설정행을 경우 
		 * 서버를 다시 시작하면 이미지 사라짐
		 * 이차장님 논의결과
		 * 에디터 수정해서 따로 저장하도록 변경 
		 * */
		String filePath = EgovProperties.getProperty("smartImage");                    //cms.properties에서 가져오는 파일 경로(컴파일된 폴더 경로)
		String fileRealPath = EgovProperties.getProperty("smartImageRealpath");        //cms.properties에서 가져오는 파일 경로(작업용 폴더 경로)
		String fileSavePath = "";
		Iterator<String> itr = multipartRequest.getFileNames();
		File saveFolder = new File(filePath+"/"+year+"/"+mm);
		File realSaveFolder = new File(fileRealPath+"/"+year+"/"+mm);
		
		LOGGER.debug("saveFolder = "+saveFolder);
		// 디렉토리 생성
		if (!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}
		if (!realSaveFolder.exists() || realSaveFolder.isFile()) {
			realSaveFolder.mkdirs();
		}

		while (itr.hasNext()) {
			MultipartFile multiFile = multipartRequest.getFile((String) itr.next());
			String saveServerFileName = System.currentTimeMillis()+"."+EgovStringUtil.extension(multiFile.getOriginalFilename()); 
			if(!"".equals(multiFile.getOriginalFilename())) {
				File oldFile = new File(filePath+"/"+year+"/"+mm+"/"+oldFileNm);
				if(oldFile.exists() && oldFile.isFile()){
					oldFile.delete();
				}

				fileSavePath = year+"/"+mm+"/"+saveServerFileName;
				multiFile.transferTo(new File(filePath+"/"+ fileSavePath));
				multiFile.transferTo(new File(fileRealPath+"/"+ fileSavePath));
				
				
				System.out.println("fileSavePath = " + fileSavePath);
				System.out.println("filePath = " + filePath+"/"+ fileSavePath);
				System.out.println("fileRealPath = " + fileRealPath+"/"+ fileSavePath);
			}
		}

		model.addAttribute("filesaveName", fileSavePath);
		return "seUpload/imgUploadForm";
	}
	
	
}
