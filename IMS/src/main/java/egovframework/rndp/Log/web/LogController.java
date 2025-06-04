package egovframework.rndp.Log.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.rndp.Log.service.LogService;
import egovframework.rndp.Log.service.LogVO;
import egovframework.rndp.com.cmm.service.EgovProperties;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import net.sf.jxls.transformer.XLSTransformer;

@Controller
public class LogController {
	@Resource(name="LogService")
	private LogService LogService;
	
	@RequestMapping(value = "/mes/log/kw_log_vf.do")
	public String mesApprovalLf(HttpServletRequest request
			, RedirectAttributes redirectAttributes
			, @ModelAttribute("logVO") LogVO logVO
			, ModelMap model) throws Exception {
		
		
		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(logVO.getTopStartDate().equals("") || logVO.getTopStartDate() == null){
			String temp = String.valueOf(dateFormat.format(nowDate));
			temp = temp.substring(0,8);
			temp += "01";
			logVO.setTopStartDate(temp);
		}
		if(logVO.getTopEndDate().equals("") || logVO.getTopEndDate() == null){
			logVO.setTopEndDate(String.valueOf(dateFormat.format(nowDate)));
		}
		
		/* pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(logVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(logVO.getRecordCountPerPage());
		paginationInfo.setPageSize(logVO.getPageSize());

		logVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		logVO.setLastIndex(paginationInfo.getLastRecordIndex());
		logVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List LogList = LogService.selectLogList(logVO);
		model.addAttribute("LogList", LogList);
		int totCnt = LogService.selectLogListCnt(logVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		
		redirectAttributes.addFlashAttribute("logVO", logVO);
		
		return "mes/log/kw_log_vf.tiles";
	}
	
	
	@RequestMapping(value = "/mes/Log/excelDownload.do")
	public void mesLogExcelDown(
			  @ModelAttribute("logVO") LogVO logVO
			, HttpServletRequest request
			, HttpServletResponse response
			, ModelMap model) throws Exception {
		
		List LogList = LogService.selectLogExcelList(logVO);
		model.addAttribute("LogList", LogList);
		
		String filePath = EgovProperties.getProperty("salesExcelTemplatePath");		//cms.properties에서 가져오는 파일 경로
		File saveFolder = new File(filePath);
		// 디렉토리 생성
		if (!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}
	    Map<String, List> beans = new HashMap<String, List>();
	    beans.put("LogList", LogList);
	   
		    
		String templatePath = EgovProperties.getProperty("salesExcelTemplatePath");		//cms.properties에서 가져오는 파일 경로
		String templateFileName = templatePath + "LogTemplate.xls";
		
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyyMMdd", Locale.KOREA );
		Date currentTime = new Date ();
		String mTime = mSimpleDateFormat.format ( currentTime );
		String titleName = "Log";
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
