package egovframework.rndp.com.utl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.springframework.stereotype.Service;

import egovframework.rndp.admin.intra.staff.service.StaffMenuAuthVO;
import egovframework.rndp.admin.menu.service.MenuBeanVO;

@Service("mUtil")
public class MatrixUtil {

	private static Vector matrix;

	/**
	 * 마지막 topmenu count
	 * 
	 * @param groupKey
	 * @return
	 */
	public static int topMenuLastIdx(int groupKey) {
		int lastIdx = 0;
		MenuBeanVO menu = new MenuBeanVO();
		for (int i = 0; i < matrix.size(); i++) {
			menu = (MenuBeanVO) matrix.get(i);
			if (menu.getDepth() == 0 && menu.getGroupKey() == groupKey && menu.getVisible().equals("T")) {
				lastIdx++;
			}
		}
		return lastIdx;
	}

	/**
	 * 서브메뉴 size
	 * 
	 * @param key
	 * @return
	 */
	public static int subMenuSize(int key) {
		int subIdx = 0;
		MenuBeanVO menu = new MenuBeanVO();
		
		for (int i = 0; i < matrix.size(); i++) {
			menu = (MenuBeanVO) matrix.get(i);
			
			if (menu.getRef() == key && menu.getVisible().equals("T")) {
				subIdx++;
			}
		}
		return subIdx;
	}

	/**
	 * 
	 * @param
	 * @return
	 */
	public static String getIntroMenuList(int groupKey, int key) {
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < matrix.size(); i++) {
			MenuBeanVO menu = new MenuBeanVO();
			menu = (MenuBeanVO) matrix.get(i);

			if (menu.getDepth() == 0 && menu.getGroupKey() == 2 && menu.getVisible().equals("T")) {
				result.append("<td class='menu'><a href='./webMenu.do?key=" + menu.getKey() + "&groupKey=" + groupKey + "'>" + menu.getName() + "</a></td>\n\n");
			}
		}

		return result.toString();
	}

	
	/**
	 * topmenu를 가져와서 arrayListdp 담는다. (홈페이지_모바일)
	 * @param groupKey
	 * @return
	 */
	public static String getRootMenuList(int groupKey, int key, String topLogo) {

		StringBuffer result = new StringBuffer();
		int seq = 1;

		result.append("<nav id='menu' class='menu'> <ul class='dropdown'>\r\n");

		for (int i = 0; i < matrix.size(); i++) {
			MenuBeanVO menu = new MenuBeanVO();
			menu = (MenuBeanVO) matrix.get(i);

			if (menu.getDepth() == 0 && menu.getGroupKey() == groupKey && menu.getVisible().equals("T")) {
				// startMenuList.add(menu);

				result.append("<li id='menu0" + String.valueOf(seq) + "' >\r\n");
				result.append("<a href='./webMenu.do?key=" + menu.getKey() + "&groupKey=" + groupKey + "'>" + menu.getName() + "</a>");

				result.append("<ul id='menu" + String.valueOf(seq) + String.valueOf(seq) + "'>\r\n");
				for (int j = 0; j < matrix.size(); j++) {
					MenuBeanVO subMenu = new MenuBeanVO();
					subMenu = (MenuBeanVO) matrix.get(j);
					
					if (subMenu.getRef() == menu.getKey() && subMenu.getVisible().equals("T")) {

						if (key == subMenu.getKey()) {
							result.append("<li class='active'>\r\n");
						} else {
							result.append("<li>\r\n");
						}

						result.append("<a href='/webMenu.do?key=" + subMenu.getKey() + "' />");
						result.append("" + subMenu.getName() + "");
						result.append("</a>\r\n");
						result.append("</li>");

					}
				}
				result.append("</ul>");
				result.append("</li>");

				seq++;
			}
		}
		result.append("</ul></nav>");

		return result.toString();
	}	
	
	/**
	 * topmenu를 가져와서 arrayListdp 담는다. (홈페이지_모바일)
	 * @param groupKey
	 * @return
	 */
//	public static String getRootMenuList2(int groupKey, int key, String topLogo){
//	
//		StringBuffer result = new StringBuffer();		
//		int seq=1;		
//		
//		result.append("<nav id='menu' class='menu'> <ul class='topmenu2'>\r\n");
//		
//		for(int i=0; i<matrix.size(); i++){
//			MenuBeanVO menu = new MenuBeanVO();
//			menu = (MenuBeanVO)matrix.get(i);
//			
//			if (menu.getDepth() == 0 && menu.getGroupKey() == groupKey && menu.getVisible().equals("T")){
//		//		startMenuList.add(menu);
//								
//				result.append("<li>\r\n");	
//				result.append("<a href='./webMenu.do?key="+menu.getKey()+"&groupKey="+groupKey+"'>"+menu.getName()+"</a>");
//				
//				result.append("<ul>\r\n");
//				for (int j = 0; j < matrix.size(); j++){
//					MenuBeanVO subMenu = new MenuBeanVO();
//					subMenu = (MenuBeanVO)matrix.get(j);
//					if (subMenu.getRef() == menu.getKey() && subMenu.getVisible().equals("T")){
//												
//						if(key == subMenu.getKey()){
//							result.append("<li class='active'>\r\n");
//						}else{
//							result.append("<li>\r\n");
//						}
//						
//						result.append("<a href='/webMenu.do?key="+subMenu.getKey()+"' />");
//						result.append(""+subMenu.getName()+"");
//						result.append("</a>\r\n");
//						result.append("</li>");
//	
//					}
//				}
//				result.append("</ul>");
//				result.append("</li>");
//
//				seq++;
//			}
//		}
//		result.append("</ul></nav>");
//		
//		return result.toString();
//	}	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * menu를 가져와서 arrayListdp 담는다. (홈페이지_모바일용 메뉴)
	 * @param groupKey
	 * @return
	 */
	public static String getRootMobileMenuList(int groupKey, int key, String topLogo){
	
		StringBuffer result = new StringBuffer();		
		int seq=1;		

		result.append("<nav id='menu' class='menu'> <ul class='dropdown'>\r\n");
		
		for(int i=0; i<matrix.size(); i++){
			MenuBeanVO menu = new MenuBeanVO();
			menu = (MenuBeanVO)matrix.get(i);
			
			if (menu.getDepth() == 0 && menu.getGroupKey() == groupKey && menu.getVisible().equals("T")){
								
				result.append("<li>\r\n");	
				result.append("<a href='./webMenu.do?key="+menu.getKey()+"&groupKey="+groupKey+"'>"+menu.getName()+"</a>");
				
				result.append("<ul class='sub_menu1 mob1_"+i+"' >\r\n");
				
				for (int j = 0; j < matrix.size(); j++){
					MenuBeanVO subMenu = new MenuBeanVO();
					subMenu = (MenuBeanVO)matrix.get(j);
					if (subMenu.getRef() == menu.getKey() && subMenu.getVisible().equals("T")){
						
						if(key == subMenu.getKey()){
							result.append("<li class='active1'>\r\n");
						}else{
							result.append("<li class='active2'>\r\n");
						}
						
						result.append("<a href='/webMenu.do?key="+subMenu.getKey()+"' />"+subMenu.getName()+"</a>");
						
						int test01 = 0;
							for (int h = 0; h < matrix.size(); h++){
								MenuBeanVO subMenu2 = new MenuBeanVO();
								subMenu2 = (MenuBeanVO)matrix.get(h);
	
								int temMum = matrix.size()-1;
								
								
								if(subMenu.getKey() == subMenu2.getRef()){
									
									if (subMenu2.getRef() == subMenu.getKey() && subMenu2.getVisible().equals("T")){
										
										if(test01 == 0){
											result.append("<ul class='sub_menu11 mob2_"+j+"'>\r\n");
										}
										
										test01++;
										
										if(key == subMenu2.getKey()){
											result.append("<li class='active11'>\r\n");
										}else{
											result.append("<li class='active22'>\r\n");
										}
										
										result.append("<a href='/webMenu.do?key="+subMenu2.getKey()+"' />"+subMenu2.getName()+"</a>\r\n");
										result.append("</li>");
										
										List refDepth1MenuList = getRefMenuList(subMenu2.getRef());
										
										if(refDepth1MenuList.size() > 0){
											if(refDepth1MenuList.size() == test01){
												result.append("</ul>");
											}
										}
									}
								}
								
							}
						
						result.append("</li>");
					}
				}
				result.append("</ul>");
				result.append("</li>");

				seq++;
			}
		}
		result.append("</ul></nav>");
		
		return result.toString();
	}
	
	

	
	/**
	 * 좌측 형태의 1뎁스 메뉴
	 * @param groupKey
	 * @return
	 */
	public static String getRootMenuList_left(int groupKey, int key, String topLogo) {

		StringBuffer result = new StringBuffer();
		int seq = 1;

		result.append("<ul class='hs_menu1'>\r\n");

		for (int i = 0; i < matrix.size(); i++) {
			MenuBeanVO menu = new MenuBeanVO();
			menu = (MenuBeanVO) matrix.get(i);

			if (menu.getDepth() == 0 && menu.getGroupKey() == groupKey && menu.getVisible().equals("T")) {

				result.append("<li id='hover" + String.valueOf(seq) + "' >\r\n");
				result.append("<a href='./webMenu.do?key=" + menu.getKey() + "&groupKey=" + groupKey + "'>" + menu.getName() + "</a>");

				seq++;
			}
		}
		result.append("</ul>");

		return result.toString();
	}

	/**
	 * 좌측 형태의 2,3뎁스 메뉴
	 * 
	 * @param groupKey
	 * @return
	 */
	public static String getSelectMenuList_left(int key) {

		StringBuffer result = new StringBuffer();
		MenuBeanVO subMenu = new MenuBeanVO();
		MenuBeanVO menu = getMenuInfo(key);
		int startStep, endStep, root;
		int idx = 0;

		// 메뉴정보가 없으면 틀린 메뉴 key
		if (menu == null || "".equals(menu)) {
			return "";
		}

		root = menu.getRoot();

		// 최상위 메뉴라면
		if (menu.getDepth() == 0) {

			List refDepth1MenuList = getRefMenuList(key);

			if (refDepth1MenuList.size() > 0) {
				for (int i = 0; i < refDepth1MenuList.size(); i++) {

					MenuBeanVO tempMenu1 = (MenuBeanVO) refDepth1MenuList.get(i);

					if (i == 0) {
						result.append("<ul class='hs_menu2'>\r\n");
						result.append("<li class='active'>");
					} else {
						result.append("<li class='active2'>");
					}

					result.append("<a href='./webMenu.do?key=" + tempMenu1.getKey() + "&groupKey=" + tempMenu1.getGroupKey() + "' />" + tempMenu1.getName() + "</a>");
					result.append("</li>");

					if (i == 0) {
						List refDepth2MenuList = getRefMenuList(tempMenu1.getKey());

						if (refDepth2MenuList.size() > 0) {
							for (int j = 0; j < refDepth2MenuList.size(); j++) {
								MenuBeanVO tempMenu2 = (MenuBeanVO) refDepth2MenuList.get(j);

								if (j == 0) {
									result.append("<li class='active_sub1'>");
								} else {
									result.append("<li class='active_sub2'>");
								}

								result.append("<a href='./webMenu.do?key="+ tempMenu2.getKey() + "&groupKey="+ tempMenu2.getGroupKey() + "' />"+ tempMenu2.getName() + "</a>");
								result.append("</li>\r\n");
							}
						}
					}
				}

				result.append("</ul>\r\n");
			}

			// idx = 0;
			// for(int i=0; i<matrix.size(); i++){
			//
			// subMenu = (MenuBeanVO)matrix.get(i);
			//
			// if (root == subMenu.getRoot() && subMenu.getDepth() <= 1 &&
			// "T".equals(subMenu.getVisible())){
			//
			// if(idx == 0){
			//
			// result.append("<ul class='hs_menu2'>\r\n");
			//
			// }else{
			// if(subMenu.getDepth() != 2){
			// if(subMenu.getKey() == key || idx== 1){
			// result.append("<li class='active'>");
			//
			// }else{
			// result.append("<li class='active2'>");
			// }
			// result.append("<a href='./webMenu.do?key="+subMenu.getKey()+"&groupKey="+subMenu.getGroupKey()+"' />"+subMenu.getName()+"</a>");
			// result.append("</li>\r\n");
			// }
			// }
			// idx++;
			// }
			// }
			// result.append("</ul>\r\n");

		} else { // menu.getDepth가 1일때

			idx = 0;
			if (menu.getDepth() != 1) {

				menu = getDepthRootMenu(menu, 1);
			}

			startStep = menu.getStep();

			menu = getFirstDepthMenu(menu);
			if (menu == null) {
				endStep = 10000;
			} else {
				endStep = menu.getStep();
			}

			MenuBeanVO vo1 = new MenuBeanVO();
			for (int i = 0; i < matrix.size(); i++) {

				vo1 = (MenuBeanVO) matrix.get(i);
				if (vo1.getRoot() == root
						&& (vo1.getDepth() <= 1 || vo1.getStep() > startStep
								&& vo1.getStep() < endStep)) {
					if (vo1.getDepth() == 0 || vo1.getVisible().equals("T")) {
						if (idx == 0) {

							result.append("<ul class='hs_menu2'>\r\n");
						} else {

							if (vo1.getDepth() != 2) { // vo1.getDepth()가 1일때

								if (vo1.getKey() == key) {
									result.append("<li class='active'>");

								} else { // subMenu.getDepth가 0일때
									result.append("<li class='active2'>");

								}
								result.append("<a href='./webMenu.do?key=" + vo1.getKey() + "&groupKey=" + vo1.getGroupKey() + "' />" + vo1.getName() + "</a>");
								result.append("</li>\r\n");

							} else if (vo1.getDepth() == 2) { // 3뎁스 메뉴일때
								if (vo1.getKey() == key) {
									result.append("<li class='active_sub1'>");

								} else { // subMenu.getDepth가 0일때
									result.append("<li class='active_sub2'>");

								}
								result.append("<a href='./webMenu.do?key=" + vo1.getKey() + "&groupKey=" + vo1.getGroupKey() + "' />" + vo1.getName() + "</a>");
								result.append("</li>\r\n");
							}
						}
						idx++;
					}
				}
			}
			result.append("</ul>\r\n");
		}
		return result.toString();
	}

	/**
	 * 
	 * @param groupKey
	 * @return
	 */
	public static String getSelectMenuList(int key) {

		StringBuffer result = new StringBuffer();
		MenuBeanVO subMenu = new MenuBeanVO();
		MenuBeanVO menu = getMenuInfo(key);
		int startStep, endStep, root;
		int idx = 0;

		// 메뉴정보가 없으면 틀린 메뉴 key
		if (menu == null || "".equals(menu)) {
			return "";
		}

		root = menu.getRoot();

		result.append("<div class='lnb' id='lnb'>\r\n");
		// 최상위 메뉴라면
		if (menu.getDepth() == 0) {

			idx = 0;
			for (int i = 0; i < matrix.size(); i++) {

				subMenu = (MenuBeanVO) matrix.get(i);

				if (root == subMenu.getRoot() && subMenu.getDepth() <= 1
						&& "T".equals(subMenu.getVisible())) {

					if (idx == 0) {
						result.append("<div id='lnb_tit'>\r\n");
						result.append("<ul>\r\n");
						result.append("<li>" + subMenu.getName() + "</li>\r\n");
						result.append("</ul>\r\n");
						result.append("</div>\r\n");
						result.append("<ul>\r\n");

					} else {
						if (subMenu.getDepth() != 2) {
							if (subMenu.getKey() == key || idx == 1) {
								result.append("<li class='active'>");

							} else {
								result.append("<li>");
							}
							result.append("<a href='./webMenu.do?key=" + subMenu.getKey() + "&groupKey=" + subMenu.getGroupKey() + "' />" + subMenu.getName() + "</a>");
							result.append("</li>\r\n");
						}
					}
					idx++;
				}
			}
			result.append("</ul>\r\n");

		} else { // menu.getDepth가 1일때

//			int idx = 0;
			if (menu.getDepth() != 1) {

				menu = getDepthRootMenu(menu, 1);
			}

			startStep = menu.getStep();

			menu = getFirstDepthMenu(menu);
			if (menu == null) {
				endStep = 10000;
			} else {
				endStep = menu.getStep();
			}

			MenuBeanVO vo1 = new MenuBeanVO();
			for (int i = 0; i < matrix.size(); i++) {

				vo1 = (MenuBeanVO) matrix.get(i);
				if (vo1.getRoot() == root && (vo1.getDepth() <= 1 || vo1.getStep() > startStep && vo1.getStep() < endStep)) {
					if (vo1.getDepth() == 0 || vo1.getVisible().equals("T")) {
						if (idx == 0) {
							result.append("<div id='lnb_tit'>\r\n");
							result.append("<ul>\r\n");
							result.append("<li>" + vo1.getName() + "</li>\r\n");
							result.append("</ul>\r\n");
							result.append("</div>\r\n");
							result.append("<ul>\r\n");
						} else {

							if (vo1.getDepth() != 2) { // vo1.getDepth()가 1일때

								if (vo1.getKey() == key) {
									result.append("<li class='active'>");

								} else { // subMenu.getDepth가 0일때
									result.append("<li class='active2'>");

								}
								result.append("<a href='./webMenu.do?key=" + vo1.getKey() + "&groupKey=" + vo1.getGroupKey() + "' />" + vo1.getName() + "</a>");
								result.append("</li>\r\n");

							} else if (vo1.getDepth() == 2) { // 3뎁스 메뉴일때
								if (vo1.getKey() == key) {
									result.append("<li class='active_sub1'>");

								} else { // subMenu.getDepth가 0일때
									result.append("<li class='active_sub2'>");

								}
								result.append("<a href='./webMenu.do?key=" + vo1.getKey() + "&groupKey=" + vo1.getGroupKey() + "' />" + vo1.getName() + "</a>");
								result.append("</li>\r\n");
							}
						}
						idx++;
					}
				}
			}
			result.append("</ul>\r\n");
		}
		result.append("</div>");

		return result.toString();
	}

	// 처음 원본
	// public static String getSelectMenuList(int key){
	// StringBuffer result = new StringBuffer();
	// MenuBeanVO subMenu = new MenuBeanVO();
	// MenuBeanVO menu = getMenuInfo(key);
	// int startStep, endStep, root;
	// int idx = 0;
	//
	// //메뉴정보가 없으면 틀린 메뉴 key
	// if(menu == null || "".equals(menu)){
	// return "";
	// }
	//
	// root = menu.getRoot();
	//
	// result.append("<div class='lnb' id='lnb'>\r\n");
	// //최상위 메뉴라면
	// if(menu.getDepth() == 0){
	// idx = 0;
	// for(int i=0; i<matrix.size(); i++){
	// subMenu = (MenuBeanVO)matrix.get(i);
	// if (root == subMenu.getRoot() && subMenu.getDepth() <= 1 &&
	// "T".equals(subMenu.getVisible())){
	// if(idx == 0){
	// result.append("<div id='lnb_tit'>\r\n");
	// result.append("<ul>\r\n");
	// result.append("<li>"+subMenu.getName()+"</li>\r\n");
	// result.append("</ul>\r\n");
	// result.append("</div>\r\n");
	// result.append("<ul>\r\n");
	// }else{
	// if(subMenu.getDepth() != 2){
	// if(subMenu.getKey() == key || idx== 1){
	// result.append("<li class='active'>");
	// }else{
	// result.append("<li>");
	// }
	// result.append("<a href='./webMenu.do?key="+subMenu.getKey()+"&groupKey="+subMenu.getGroupKey()+"' />"+subMenu.getName()+"</a>");
	// result.append("</li>\r\n");
	// }
	// }
	// idx++;
	// }
	// }
	// result.append("</ul>\r\n");
	//
	// }else{
	// idx = 0;
	// if(menu.getDepth() != 1){
	//
	// menu = getDepthRootMenu(menu, 1);
	// }
	//
	// startStep = menu.getStep();
	//
	// menu = getFirstDepthMenu(menu);
	// if(menu == null){
	// endStep = 10000;
	// }else{
	// endStep = menu.getStep();
	// }
	//
	// MenuBeanVO vo1 = new MenuBeanVO();
	// for(int i=0; i<matrix.size(); i++){
	// vo1 = (MenuBeanVO)matrix.get(i);
	// if (vo1.getRoot() == root && (vo1.getDepth() <= 1 || vo1.getStep() >
	// startStep && vo1.getStep() < endStep)){
	// if (vo1.getDepth() == 0 || vo1.getVisible().equals("T")){
	// if(idx == 0){
	// result.append("<div id='lnb_tit'>\r\n");
	// result.append("<ul>\r\n");
	// result.append("<li>"+vo1.getName()+"</li>\r\n");
	// result.append("</ul>\r\n");
	// result.append("</div>\r\n");
	// result.append("<ul>\r\n");
	// }else{
	//
	// if(vo1.getDepth() != 2){
	// if(vo1.getKey() == key){
	// result.append("<li class='active'>");
	// }else{
	// result.append("<li>");
	// }
	// result.append("<a href='./webMenu.do?key="+vo1.getKey()+"&groupKey="+vo1.getGroupKey()+"' />"+vo1.getName()+"</a>");
	// result.append("</li>\r\n");
	// }
	//
	// }
	// idx++;
	//
	// }
	//
	// }
	//
	// }
	// result.append("</ul>\r\n");
	// }
	// result.append("</div>");
	//
	// return result.toString();
	// }

	/**
	 * 메뉴 key값으로 개별 메뉴정보 세팅
	 * 
	 * @param key
	 * @return
	 */
	public static MenuBeanVO getMenuInfo(int key) {
		MenuBeanVO menu = new MenuBeanVO();
		for (int i = 0; i < matrix.size(); i++) {
			menu = (MenuBeanVO) matrix.get(i);
			if (menu.getKey() == key) {
				return menu;
			}
		}
		return null;
	}
	 
	/**
	 * 메뉴명으로 키값을 가져옴
	 * 
	 * @param menuName
	 * @return
	 */
	public static int getUniteMenuKey(String menuName, int groupKey) {
		int key = 0;
		MenuBeanVO menu = new MenuBeanVO();
		for (int i = 0; i < matrix.size(); i++) {
			menu = (MenuBeanVO) matrix.get(i);
			if (menu.getName().equals(menuName)
					&& menu.getGroupKey() == groupKey) {
				key = menu.getKey();
			}
		}

		return key;
	}

	/**
	 * 페이지가 존재하지 않고 최상의 메뉴면
	 * 
	 * @param key
	 * @return
	 */
	public static MenuBeanVO getMenuInfoEx(int key) {
		MenuBeanVO menu = getMenuInfo(key);
		if (menu != null && menu.getExist().equals("F")) {
			List list = getRefMenuList(menu.getKey());
			if (list.size() > 0) {
				menu = (MenuBeanVO) list.get(0);
				return menu;
			}
		}
		return menu;
	}
	
	/**
	 * 페이지가 존재하지 않고 최상의 메뉴면
	 * 로그인 권한체크
	 * @param key
	 * @return
	 */
	public static MenuBeanVO getMenuInfoEx(int key, List staffMenuList) {
		MenuBeanVO menu = getMenuInfo(key);
		if (menu != null && menu.getExist().equals("F")) {
			List list = getRefMenuList2(menu.getKey(), staffMenuList);
			if (list.size() > 0) {
				menu = (MenuBeanVO) list.get(0);
				return menu;
			}
		}
		return menu;
	}	
	 

	/**
	 * depth 가 입력값보다 크고 Ref 가 있다면 Ref 가 가르키는 메뉴의 정보를 리턴한다.
	 * 
	 * @param menu
	 * @param depth
	 * @return
	 */
	public static MenuBeanVO getDepthRootMenu(MenuBeanVO menu, int depth) {
		while (menu.getDepth() > depth && menu.getRef() != 0) {
			menu = getMenuInfo(menu.getRef());

			if (menu == null) {
				return null;
			}
		}
		return menu;
	}

	/**
	 * 
	 * @param groupKey
	 * @return
	 */
	public static MenuBeanVO getFirstDepthMenu(MenuBeanVO vo) {
		MenuBeanVO menu = new MenuBeanVO();

		for (int i = 0; i < matrix.size(); i++) {
			menu = (MenuBeanVO) matrix.get(i);

			if (menu.getRoot() == vo.getRoot()
					&& menu.getDepth() == vo.getDepth()
					&& menu.getStep() > vo.getStep()
					&& menu.getVisible().equals("T")) {
				return menu;
			}
		}
		return null;
	}

	/**
	 * matrix 에서 최상위 menu 의 하위 메뉴 list 를 반환한다.
	 * 
	 * @param root
	 * @param depth
	 * @return
	 */
	public static ArrayList getDepthMenuList(int root, int depth) {
		ArrayList result = new ArrayList();
		MenuBeanVO menu = new MenuBeanVO();

		for (int i = 0; i < matrix.size(); i++) {
			menu = (MenuBeanVO) matrix.get(i);

			if (menu.getRoot() == root && menu.getDepth() == depth
					&& menu.getVisible().equals("T")) {
				result.add(menu);
			}
		}

		return result;
	}

	/**
	 * 해당 메뉴의 하위 메뉴 목록을 넘겨줌
	 * 
	 * @param key
	 * @return
	 */
	public static ArrayList getRefMenuList(int key) {

		ArrayList result = new ArrayList();
		MenuBeanVO menu = new MenuBeanVO();

		for (int i = 0; i < matrix.size(); i++) {
			menu = (MenuBeanVO) matrix.get(i);

			if (menu.getRef() == key && menu.getVisible().equals("T")) {
				result.add(menu);
			}
		}

		return result;
	}
	
	/**
	 * 해당 메뉴의 하위 메뉴 목록을 넘겨줌
	 * 로그인별 권한 체크
	 * @param key
	 * @return
	 */
	public static ArrayList getRefMenuList(int key, List staffMenuList) {

		ArrayList result = new ArrayList();
		MenuBeanVO menu = new MenuBeanVO();
	

		for (int i = 0; i < matrix.size(); i++) {
			menu = (MenuBeanVO) matrix.get(i);
			
			//로그인 메뉴권한 체크
			for(int k=0; k < staffMenuList.size(); k++){
				StaffMenuAuthVO staffMenu = new StaffMenuAuthVO();
				staffMenu = (StaffMenuAuthVO) staffMenuList.get(k);
				
				if( Integer.parseInt(staffMenu.getkMenuKey()) == menu.getKey()){
					menu.setStaffMenuFlag(staffMenu.getkMenuAuthFlag());
				}
			}
			if (menu.getRef() == key && menu.getVisible().equals("T") && menu.getStaffMenuFlag().equals("T")) {
				result.add(menu);
			}
		}

		return result;
	}
	
	
	/**
	 * 해당 메뉴의 하위 메뉴 목록을 넘겨줌
	 * 사용자의 권한에 적용받는경우
	 * @param key
	 * @param staffMenuList2
	 * @return
	 */
	public static ArrayList getRefMenuList2(int key, List staffMenuList2) {

		ArrayList result = new ArrayList();
		MenuBeanVO menu = new MenuBeanVO();

		for (int i = 0; i < matrix.size(); i++) {
			menu = (MenuBeanVO) matrix.get(i);
			if (menu.getRef() == key  && menu.getVisible().equals("T")) {
				for(int k=0; k < staffMenuList2.size(); k++){
					StaffMenuAuthVO staffMenu = new StaffMenuAuthVO();
					staffMenu = (StaffMenuAuthVO) staffMenuList2.get(k);
					
					//메뉴권한이 있으면
					if(menu.getKey() == Integer.parseInt(staffMenu.getkMenuKey())
							&& staffMenu.getkMenuAuthFlag().equals("T")){
						
						menu.setkMenuAuthC(staffMenu.getkMenuAuthC());
						menu.setkMenuAuthM(staffMenu.getkMenuAuthM());
						menu.setkMenuAuthD(staffMenu.getkMenuAuthD());
						menu.setkMenuAuthW(staffMenu.getkMenuAuthW());
						menu.setkMenuAuthFlag(staffMenu.getkMenuAuthFlag());
						menu.setScreenId(staffMenu.getScreenId());
						menu.setScreenHistory(staffMenu.getScreenHistory());
						
						result.add(menu);
						continue;
					}
				}			
			}
		}

		return result;
	}


	/**
	 * 
	 * @param groupKey
	 * @return
	 */
	public static String getMenuCategory(int key) {
		MenuBeanVO menu = new MenuBeanVO();
		StringBuffer result = new StringBuffer();

		menu = getMenuInfo(key);

		if (menu != null) {
			result.append(" > <a href='/mes/webMenu.do?key=");
			result.append(menu.getKey());
			result.append("&root=");
			result.append(menu.getRoot());
			result.append("'>");
			result.append("<font color='#color'><b>");
			result.append(menu.getName());
			result.append("</b></font>");
			result.append("</a>");

			while (menu.getDepth() != 0 && menu.getRef() != 0) {
				StringBuffer sub = new StringBuffer();
				int ref = menu.getRef();

				menu = getMenuInfo(menu.getRef());

				if (menu == null) {

					return result.toString();
				}

				sub.append(" > <a href='/mes/webMenu.do?key=");
				sub.append(ref);
				sub.append("&root=");
				sub.append(menu.getRoot());
				sub.append("'>");
				sub.append(menu.getName());
				sub.append("</a>");

				result.insert(0, sub.toString());
			}

		}

		return result.toString();
	}

	/**
	 * 
	 * @param groupKey
	 * @return
	 */
	public static String getHomeMenuCategory(int key) {
		MenuBeanVO menu = new MenuBeanVO();
		StringBuffer result = new StringBuffer();

		menu = getMenuInfo(key);

		if (menu != null) {

			while (true) {
				List refMenuList = getRefMenuList(key);

				if (refMenuList.size() == 0) {

					menu = getMenuInfo(key);

					result.append(" > <a href='./webMenu.do?key=");
					result.append(menu.getKey());
					result.append("'>");
					result.append("<font color='#color'><b>");
					result.append(menu.getName());
					result.append("</b></font>");
					result.append("</a>");

					while (menu.getDepth() != 0 && menu.getRef() != 0) {
						StringBuffer sub = new StringBuffer();

						menu = getMenuInfo(menu.getRef());

						if (menu == null) {

							return result.toString();
						}

						sub.append(" > <a href='./webMenu.do?key=");
						sub.append(menu.getKey());
						sub.append("'>");
						sub.append(menu.getName());
						sub.append("</a>");

						result.insert(0, sub.toString());
					}

					break;

				} else {
					MenuBeanVO refFirstMenu = (MenuBeanVO) refMenuList.get(0);
					key = refFirstMenu.getKey();
				}

			}
		}

		return result.toString();
	}

	/**
	 * 
	 * @param groupKey
	 * @return
	 */
	public static MenuBeanVO getIntroMenuInfoEx(int key) {
		MenuBeanVO menu = getMenuInfo(key);
		MenuBeanVO result = null;
		if (menu != null && menu.getExist().equals("F")) {
			int j = 0;
			for (int i = 0; i < matrix.size(); i++) {
				if (j == 0) {
					result = (MenuBeanVO) matrix.get(i);
					if (result.getGroupKey() == 2
							&& result.getVisible().equals("T")) {
						menu = result;
						j++;
						;
					}
				}

			}
		}
		return menu;
	}

	/**
	 * topmenu를 가져와서 arrayList 반환한다. (인트라넷) 디자인 요소를 java 에서 제거하기 위해 객체 list 반환하는
	 * 형식을 택함
	 * 
	 * @param groupKey
	 * @return ArrayList<MenuBeanVO>
	 */
	public static ArrayList<MenuBeanVO> getIntraRootMenuList(int groupKey) {
		ArrayList<MenuBeanVO> resultList = new ArrayList<MenuBeanVO>();

		for (int i = 0; i < matrix.size(); i++) {
			MenuBeanVO menu = new MenuBeanVO();
			menu = (MenuBeanVO) matrix.get(i);
			// 최상위이고 groupKey 같고 관리자에서 visible 이면 추가
			if (menu.getDepth() == 0 && menu.getGroupKey() == groupKey
					&& menu.getVisible().equals("T")) {
				resultList.add(menu);
			}
		}
		return resultList;
	}
	

	
	/**
	 * 로그인별 메뉴 리스트 조회 
	 * topmenu를 가져와서 arrayList 반환한다. (인트라넷) 디자인 요소를 java 에서 제거하기 위해 객체 list 반환하는
	 * 형식을 택함
	 * 
	 * @param groupKey
	 * @return ArrayList<MenuBeanVO>
	 */
	public static ArrayList<MenuBeanVO> getIntraRootMenuList(int groupKey, List staffMenuList) {
		ArrayList<MenuBeanVO> resultList = new ArrayList<MenuBeanVO>();

		for (int i = 0; i < matrix.size(); i++) {
			MenuBeanVO menu = new MenuBeanVO();
			menu = (MenuBeanVO) matrix.get(i);
			// 최상위이고 groupKey 같고 관리자에서 visible 이면 추가
			if (menu.getDepth() == 0 
					&& menu.getGroupKey() == groupKey
					&& menu.getVisible().equals("T")) {
				
				for(int k=0; k < staffMenuList.size(); k++){
					StaffMenuAuthVO staffMenu = new StaffMenuAuthVO();
					staffMenu = (StaffMenuAuthVO) staffMenuList.get(k);
					
					//메뉴권한이 있으면
					if(menu.getKey() == Integer.parseInt(staffMenu.getkMenuKey())
							&& staffMenu.getkMenuAuthFlag().equals("T")){
						resultList.add(menu);
						continue;
					}
				}			
			}
		}
		return resultList;
	}	
	
	
	public Vector getMatrix() {
		return matrix;
	}

	public void setMatrix(Vector matrix) {
		this.matrix = matrix;
	}

	public static ArrayList getAllRefMenuList(int groupKey) {
		ArrayList resultList = new ArrayList();

		for (int i = 0; i < matrix.size(); i++) {
			MenuBeanVO menu = new MenuBeanVO();
			menu = (MenuBeanVO) matrix.get(i);

			if (menu.getDepth() == 0 && menu.getGroupKey() == groupKey
					&& menu.getVisible().equals("T")) {

				MenuBeanVO menu2 = new MenuBeanVO();
				for (int j = 0; j < matrix.size(); j++) {
					menu2 = (MenuBeanVO) matrix.get(j);

					if (menu2.getRef() == menu.getKey()
							&& menu2.getVisible().equals("T")) {
						resultList.add(menu2);
					}
				}
			}
		}

		return resultList;
	}
	
	/**
	 * 로그인한 스테프의 메뉴를 리턴한다.
	 * @param groupKey
	 * @param staffMenuList
	 * @return
	 */
	public static ArrayList getAllRefStaffMenuList(int groupKey, List staffMenuList) {
		ArrayList resultList = new ArrayList();

		for (int i = 0; i < matrix.size(); i++) {
			MenuBeanVO menu = new MenuBeanVO();
			menu = (MenuBeanVO) matrix.get(i);

			if (menu.getDepth() == 0 && menu.getGroupKey() == groupKey
					&& menu.getVisible().equals("T")) {

				MenuBeanVO menu2 = new MenuBeanVO();
				for (int j = 0; j < matrix.size(); j++) {
					menu2 = (MenuBeanVO) matrix.get(j);

					if (menu2.getRef() == menu.getKey()
							&& menu2.getVisible().equals("T")) {
						
						
						for(int k=0; k < staffMenuList.size(); k++){
							StaffMenuAuthVO staffMenu = new StaffMenuAuthVO();
							staffMenu = (StaffMenuAuthVO) staffMenuList.get(k);
							
							//메뉴권한이 있으면
							if(menu2.getRef() == Integer.parseInt(staffMenu.getkMenuKey())
									&& staffMenu.getkMenuAuthFlag().equals("T")){
						
								resultList.add(menu2);
								continue;
							}
						}
					}
				}
			}
		}

		return resultList;
	}
	
	public ArrayList getIntraRootMenuList3(int groupKey, List staffMenuList2) {
		ArrayList resultList = new ArrayList();

		for (int i = 0; i < matrix.size(); i++) {
			MenuBeanVO menu = new MenuBeanVO();
			menu = (MenuBeanVO) matrix.get(i);

			if (menu.getDepth() == 0 && menu.getGroupKey() == groupKey
					&& menu.getVisible().equals("T")) {

				MenuBeanVO menu2 = new MenuBeanVO();
				for (int j = 0; j < matrix.size(); j++) {
					menu2 = (MenuBeanVO) matrix.get(j);

					if (menu2.getRef() == menu.getKey()
							&& menu2.getVisible().equals("T")) {
						//resultList.add(menu2);
						for(int k=0; k < staffMenuList2.size(); k++){
							StaffMenuAuthVO staffMenu = new StaffMenuAuthVO();
							staffMenu = (StaffMenuAuthVO) staffMenuList2.get(k);
							
							//메뉴권한이 있으면
							if(menu2.getKey() == Integer.parseInt(staffMenu.getkMenuKey())
									&& staffMenu.getkMenuAuthFlag().equals("T")){
								resultList.add(menu2);
								continue;
							}
						}
					}
				}
			}
		}

		return resultList;
	}


}
