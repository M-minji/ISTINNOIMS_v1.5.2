package egovframework.rndp.com.interceptor.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.rndp.Log.service.LogService;
import egovframework.rndp.Log.service.LogVO;
import egovframework.rndp.com.cmm.service.EgovProperties;
import egovframework.rndp.mes.login.service.MesK_StaffVo;

public class LogInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);

	// DB LOG테이블 추가
	// egov-com-servlet.xml에 mvc interceptor 추가

	@Resource(name = "LogService")
	private LogService logService;

	int num = 0;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		LOGGER.debug("\n LogInterceptor postHandle start");
		String urlPath = request.getServletPath();
		LOGGER.debug("urlPath = " + urlPath);

		if (urlPath.equals("/mes/main.do") || urlPath.equals("/intra/main.do") || urlPath.equals("/admin/login.do")) {
			num++;
		}
		if (urlPath.equals("/mes/logout.do") || request.getSession().getAttribute("staff") == null
				|| urlPath.equals("/intra/logout.do") || urlPath.equals("/admin/logout.do")) {
			num = 0;
		}

		// ajax체크
		if (!"XMLHttpRequest".equals(request.getHeader("x-requested-with"))) {
			// 로그 등록
			if (request.getSession().getAttribute("staff") != null) {
				MesK_StaffVo staffVo = (MesK_StaffVo) request.getSession().getAttribute("staff");
				LogVO VO = new LogVO();
				VO.setUrl(urlPath);
				VO.setStaffKey(Integer.toString(staffVo.getkStaffKey()));
				VO.setStaffId(staffVo.getkStaffId());
				VO.setStaffName(staffVo.getkStaffName());
				VO.setLogIp(getClientIP(request));
				VO.setLogAPI(EgovProperties.getProperty("APIPath")); // cms 에서
																		// 바꿈
				if (urlPath.equals("/mes/main.do") || urlPath.equals("/intra/main.do")) {
					if (num == 1) {
						VO.setPage("로그인");
					} else if (num != 1) {
						VO.setPage("메인페이지");
					}
				}
				if (urlPath.equals("/mes/logout.do") || urlPath.equals("/intra/logout.do")) {
					VO.setPage("로그아웃");
				}
				if (urlPath.equals("/mes/login.do") || urlPath.equals("/intra/login.do")) {
					VO.setPage("로그인페이지");
				}
				logService.insertLog(VO);
			}
		}

		LOGGER.debug("\n LogInterceptor postHandle end");
	}

	public static String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}