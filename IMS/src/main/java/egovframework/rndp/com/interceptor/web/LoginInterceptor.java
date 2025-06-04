package egovframework.rndp.com.interceptor.web;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.rndp.Log.service.LogService;
import egovframework.rndp.Log.service.LogVO;
import egovframework.rndp.com.cmm.service.EgovProperties;
import egovframework.rndp.mes.login.service.MesK_StaffVo;

@Service
public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(LoginInterceptor.class);

	/**
	 * <pre>
	 * 2020 07 27
	 * pre 이므로 시작 전에 실행한다.
	 * intra 와 mes 는 같은 staff 정보로 login 하고 (직원용이므로..)
	 * homepage 와 admin은 세션정보가 각각이다. (회원과 관리자)
	 * shop과 sales 는 사용하지 않는다.
	 * </pre>
	 * */ 
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		LOGGER.debug("\n LoginInterceptor preHandle start");
		String urlPath = request.getServletPath();
		LOGGER.debug("urlPath = "+urlPath);
		
		if(urlPath.matches("/admin.*")){
			//넘어오는 주쇼 맨 앞에 admin이 있으면(관리자)
			if("/admin/loginfrm.do".equals(urlPath) || "/admin/login.do".equals(urlPath)){
				return true;
			}else{
				if(request.getSession().getAttribute("adminUser") == null){
					response.sendRedirect("/admin/loginfrm.do"); 
					return false;
				}else{
					return true;
				}
			}
		}else if (urlPath.matches("/intra.*")) {
			//넘어오는 주쇼 맨 앞에 intra이 있으면(직원)
			if("/intra/loginfrm.do".equals(urlPath) || "/intra/login.do".equals(urlPath)){
				if(request.getSession().getAttribute("staff") == null){
					return true;
				}else{
					response.sendRedirect("/intra/main.do");
					return false;
				}
			}else{
				if(request.getSession().getAttribute("staff") == null){
					response.sendRedirect("/intra/loginfrm.do");
					return false;
				}else{
					return true;
				}
			} 
		}else if(urlPath.matches("/mes.*")) {
			if("/mes/loginfrm.do".equals(urlPath) || "/mes/login.do".equals(urlPath)){
				return true;
			}else{
				if(request.getSession().getAttribute("staff") == null){
					response.sendRedirect("/mes/loginfrm.do");
					return false;
				}else{
					return true;
				}
			}  
		}else if(urlPath.matches("/main.*")) {
			if("/main.do".equals(urlPath)){
				return true;
			}else{
				if(request.getSession().getAttribute("staff") == null){
					response.sendRedirect("/main.do");					
					return false;
				}else{
					return true;
				}
			}
		}else if(urlPath.matches("/jsp.*")) {
			return true;
			
		}else if(urlPath.matches("/webMenu.*")) {
			return true;

		}else if(urlPath.matches("/popup.*")) {
			return true;

		}else if (
				urlPath.matches("/download.do") ||
				urlPath.matches("/fileKeyDelete.do") ||
				urlPath.matches("/fileSizeCheck.do") ||
				urlPath.matches("/imageDownload.do") ||
				urlPath.matches("/imageMDownload.do") ||
				urlPath.matches("/movieDownload.do") ||
				urlPath.matches("/smartImage.do") ||
				urlPath.matches("/smartImageUpload.do") ||

				urlPath.matches("/popup/mes/km_staff_req_if.do") ||
				urlPath.matches("/popup/mes/kw_checkid.do") ||
				urlPath.matches("/popup/mes/checkStaffId.do") ||
				
				urlPath.matches("/popup/km_staff_req_if.do") ||
				urlPath.matches("/popup/kw_checkid.do") ||
				urlPath.matches("/popup/checkStaffId.do") ||
				urlPath.matches("/Popup/kw_business_staff_lf.do") ||
				urlPath.matches("/post.do") ||
				urlPath.matches("/cmm/fms/getImage.do")||
				urlPath.matches("/cmm/fms/getPdf.do")||
				urlPath.matches("/cmm/fms/FileDown.do") ||
				urlPath.matches("/cmm/fms/PDFView.do") ||
				urlPath.matches("/cmm/fms/deleteFileInfs.do") ||
				urlPath.matches("/cmm/fms/deleteFileInfs2.do") ||
				urlPath.matches("/Popup/excelDown.do") ||
				urlPath.matches("/Popup/SenderexcelDown.do")||
				urlPath.matches("/admin/staff/kw_staff_iuf.do")||
				urlPath.matches("/admin/staff/kw_staff_i.do") || 
				urlPath.matches("/mail/download.do") || 
				urlPath.matches("/mail/bigFiledownload.do") || 
				urlPath.matches("/shAdminImageDownload.do") ||
				urlPath.matches("/shAdmin/saleSelect.do") ||
				urlPath.matches("/shopSmartImage.do") ||
				urlPath.matches("/join/km_shop_mem_if.do") ||
				urlPath.matches("/join/km_shop_mem_i.do") ||
				urlPath.matches("/popup/kw_checkMemid.do") ||
				urlPath.matches("/asset/qr_asset_info.do") ||
				urlPath.matches("/searchMyInfo.do") 
				) { 
			//게시판에서 사용하는 url 이면 무조건 통과
			return true; 
		} 

		/**
		 * 현재 인트라넷만 있으므로 
		 * 인트라넷도 아니고 어드민도 아니면 
		 * 일단 인트라셋 로그인 페이지로
		 * 
		 * */
		response.sendRedirect("/intra/loginfrm.do"); 
		return false;
		  
	} 

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
	
}
