package egovframework.com.cmm.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovBasicLogger;
import egovframework.com.cmm.util.EgovResourceCloseHelper;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rndp.admin.menu.service.MenuVO;
import egovframework.rndp.com.cmm.service.EgovProperties;
import egovframework.rndp.mes.user.service.MesUserService;
import egovframework.rndp.mes.user.service.MesUserVO;

/**
 * 파일 다운로드를 위한 컨트롤러 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일      	수정자           수정내용
 *  ------------   --------    ---------------------------
 *   2009.03.25  	이삼섭          최초 생성
 *   2014.02.24		이기하          IE11 브라우저 한글 파일 다운로드시 에러 수정
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */

@Controller
public class EgovFileDownloadController {

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;

	@Resource(name = "mesUserService")
	private MesUserService mesUserService;
	
	/**
	 * 브라우저 구분 얻기.
	 *
	 * @param request
	 * @return
	 */
	private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Trident") > -1) { // IE11 문자열 깨짐 방지
			return "Trident";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		}
		return "Firefox";
	}

	/**
	 * Disposition 지정하기.
	 *
	 * @param filename
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String browser = getBrowser(request);

		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;

		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Trident")) { // IE11 문자열 깨짐 방지
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else {
			throw new IOException("Not supported browser");
		}

		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

		if ("Opera".equals(browser)) {
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}

	/**
	 * 첨부파일로 등록된 파일에 대하여 다운로드를 제공한다.
	 *
	 * @param commandMap
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmm/fms/FileDown.do")
	public void cvplFileDownload(@RequestParam Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception {

		//메뉴구분자 추가
		String atchFileId = "";
		String fileSn = "";
 
		atchFileId = (String) commandMap.get("atchFileId");
		fileSn = (String) commandMap.get("fileSn");
		
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if (isAuthenticated) {

			FileVO fileVO = new FileVO();
			fileVO.setAtchFileId(atchFileId);
			fileVO.setFileSn(fileSn);
			FileVO fvo = fileService.selectFileInf(fileVO);
			
			String fileStorePath = EgovProperties.getProperty("fileStorePath");

			File uFile = new File(fileStorePath + fvo.getFileStreCours(), fvo.getStreFileNm()+"."+fvo.getFileExtsn());
			int fSize = (int) uFile.length();

			if (fSize > 0) {
				String mimetype = "application/x-msdownload";

				//response.setBufferSize(fSize);	// OutOfMemeory 발생
				response.setContentType(mimetype);
				//response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fvo.getOrignlFileNm(), "utf-8") + "\"");
				setDisposition(fvo.getOrignlFileNm(), request, response);
				response.setContentLength(fSize);

				/*
				 * FileCopyUtils.copy(in, response.getOutputStream());
				 * in.close();
				 * response.getOutputStream().flush();
				 * response.getOutputStream().close();
				 */
				BufferedInputStream in = null;
				BufferedOutputStream out = null;

				try {
					in = new BufferedInputStream(new FileInputStream(uFile));
					out = new BufferedOutputStream(response.getOutputStream());

					FileCopyUtils.copy(in, out);
					out.flush();
				} catch (IOException ex) {
					// 다음 Exception 무시 처리
					// Connection reset by peer: socket write error
					EgovBasicLogger.ignore("IO Exception", ex);
				} finally {
					EgovResourceCloseHelper.close(in, out);
				}

			} else {
				response.setContentType("application/x-msdownload");

				PrintWriter printwriter = response.getWriter();
				
				printwriter.println("<html>");
				printwriter.println("<br><br><br><h2>Could not get file name:<br>" + fvo.getOrignlFileNm() + "</h2>");
				printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
				printwriter.println("<br><br><br>&copy; webAccess");
				printwriter.println("</html>");
				
				printwriter.flush();
				printwriter.close();
			}
		}
	}
	

	 @RequestMapping(value = "/cmm/fms/PDFView.do")
	    public void viewPDF(@RequestParam Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception {

	        String atchFileId = (String) commandMap.get("atchFileId");
	        String fileSn = (String) commandMap.get("fileSn");

	        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

	        if (isAuthenticated) {

	        	FileVO fileVO = new FileVO();
	            fileVO.setAtchFileId(atchFileId);
	            fileVO.setFileSn(fileSn);
	            FileVO fvo = fileService.selectFileInf(fileVO);
	            fvo.setOrignlFileNm(fvo.getOrignlFileNm().replaceAll(",", "_"));
	            String fileStorePath = EgovProperties.getProperty("fileStorePath");

	            File uFile = new File(fileStorePath + fvo.getFileStreCours(), fvo.getStreFileNm() + "." + fvo.getFileExtsn());
	            int fSize = (int) uFile.length();

	            if (fSize > 0) {
	                response.setContentType("application/pdf");
	                String encodedFileName = URLEncoder.encode(fvo.getOrignlFileNm(), "UTF-8").replace("+", "%20");
	                response.setHeader("Content-Disposition", "inline; filename=\"" + encodedFileName + "\"");

	                BufferedInputStream in = null;
	                BufferedOutputStream out = null;

	                try {
	                    in = new BufferedInputStream(new FileInputStream(uFile));
	                    out = new BufferedOutputStream(response.getOutputStream());

	                    byte[] buffer = new byte[1024];
	                    int bytesRead;
	                    while ((bytesRead = in.read(buffer)) != -1) {
	                        out.write(buffer, 0, bytesRead);
	                    }
	                    out.flush();
	                } catch (IOException ex) {
	                    EgovBasicLogger.ignore("IO Exception", ex);
	                } finally {
	                    try {
	                        if (in != null) in.close();
	                        if (out != null) out.close();
	                    } catch (IOException ex) {
	                        EgovBasicLogger.ignore("IO Exception", ex);
	                    }
	                }

	            } else {
	                // 파일이 존재하지 않는 경우 처리
	            }
	            
	            
	            
	        }
	    }
	
	 
	
}
