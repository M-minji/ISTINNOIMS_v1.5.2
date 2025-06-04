package egovframework.rndp.com.utl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.rndp.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;

@Controller
public class FileDownloadController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(FileDownloadController.class);
	
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;
	
	/**
	 * 파일 다운로드
	 * @param request
	 * @param response
	 * @param fileName
	 * @param type
	 * @throws Exception
	 */
	@RequestMapping(value="/download.do")
	public void fileDownload(HttpServletRequest request
			, HttpServletResponse response
			, String type
			, int fileKey) throws Exception{
		
		String filePath = "";
		/*if("T".equals(type)){
			filePath = EgovProperties.getProperty("board.tboard");        //cms.properties에서 가져오는 파일 경로
		}else if("I".equals(type)){
			filePath = EgovProperties.getProperty("board.iboard");			
		}*/
		filePath = EgovProperties.getProperty("board."+type.toLowerCase()+"board");
		
		DownloadFileUtil downFile = new DownloadFileUtil();
		downFile.setFilePath(filePath);
		downFile.download(request, response);
	}
	
	
	
	private void close(BufferedOutputStream bos, BufferedInputStream bis)
	{
		try
		{
			if (bos != null)
			{
				bos.close();
			}
		}
		catch (Exception ex)
		{

		}

		try
		{
			if (bis != null)
			{
				bis.close();
			}
		}
		catch (Exception ex)
		{

		}
	}
	
	/**
	 * 회원승인 이미지 다운로드
	 * @param request
	 * @param response
	 * @param type
	 * @param boardKey
	 * @throws Exception
	 */
	@RequestMapping(value="/shAdminMemImageDownload.do")
	public void shopMemImageFileDownload(HttpServletRequest request
			, HttpServletResponse response
			, String atchFileId
			, int fileSn) throws Exception{
		
		
		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(atchFileId);
		fileVO.setFileSn(Integer.toString(fileSn));
//		List fileList = fileMngService.selectFileInfs(fileVO);
		FileVO fVO = fileMngService.selectFileInf(fileVO);
		String fileStorePath = "";
		String filePath = "";
		
		fileStorePath = EgovProperties.getProperty("fileStorePath");
		filePath = EgovProperties.getProperty("vouchersUploadPath");
		
		if(fVO != null){
			if(fVO.getFileSize() != null){
				
				DownloadFileUtil downFile = new DownloadFileUtil();
				downFile.setFileName(fVO.getOrignlFileNm());
				downFile.setServerFilename(fVO.getStreFileNm());
				downFile.setFilePath(fileStorePath + filePath);
				
				downFile.download(request, response);
			}
			
		}
	}
	
}
