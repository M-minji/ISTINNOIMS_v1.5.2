package egovframework.rndp.com.interceptor.web;

import java.util.List;
import java.util.Vector;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.rndp.admin.menu.service.MenuBeanVO;
import egovframework.rndp.admin.menu.service.MenuService;
import egovframework.rndp.com.utl.MatrixUtil;

@Service
public class MatrixIntrceptor extends HandlerInterceptorAdapter{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MatrixIntrceptor.class);
	
	@Resource(name = "mUtil")
	private MatrixUtil mUtil;
	
	@Resource(name = "menuService") 
    private MenuService menuService;
	
	@Override
	public boolean preHandle(HttpServletRequest request
			, HttpServletResponse response
			, Object handler) throws Exception {

		LOGGER.debug("MatrixIntrceptor start");
		
		if(mUtil.getMatrix() == null){                                             //matrix의 값이 null이면 시작
			Vector matrix = new Vector();
			List matrixList = menuService.getAllMenuList();
			for(int i=0;i<matrixList.size(); i++){
				MenuBeanVO vo = new MenuBeanVO();
				vo = (MenuBeanVO)matrixList.get(i);
				matrix.add(vo);
			}
			mUtil.setMatrix(matrix);
		}

		LOGGER.debug("MatrixIntrceptor end");

		return true;
	}
}