package egovframework.rndp.admin.menu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rndp.admin.menu.service.AdminmenuVO;
import egovframework.rndp.admin.menu.service.MenuBeanVO;
import egovframework.rndp.admin.menu.service.MenuService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("menuService")
public class MenuServiceImpl extends EgovAbstractServiceImpl implements MenuService{

	@Resource(name="menuDAO")
	private MenuDAO menuDAO;
	
	@Override
	public List menuListOwn() throws Exception {
		// TODO Auto-generated method stub
		return menuDAO.menuListOwn();
	}

	@Override
	public List menuOwnList(AdminmenuVO vo) throws Exception {
		// TODO Auto-generated method stub
		List resultList = null;
		List menuList1 = menuDAO.menuOwnList(vo);
		for(int i=0; i<menuList1.size(); i++){
			AdminmenuVO resultVO = (AdminmenuVO)menuList1.get(i);
			vo.setRoot(resultVO.getRoot());
			List menuList2 = menuDAO.menuTwoList(vo);
			for(int j=0; j<menuList2.size(); j++){
				AdminmenuVO resultVO1 = (AdminmenuVO)menuList2.get(j);
				resultList.add(resultVO1);
			}

		}
		return resultList;
	}
	

	@Override
	public List menuListTwo(AdminmenuVO vo) throws Exception {
		// TODO Auto-generated method stub
		return menuDAO.menuListTwo(vo);
	}

	@Override
	public List moveMenuList(AdminmenuVO vo) throws Exception {
		// TODO Auto-generated method stub
		return menuDAO.moveMenuList(vo);
	}

	@Override
	public List moveSubMenuList(AdminmenuVO vo) throws Exception {
		// TODO Auto-generated method stub
		return menuDAO.moveSubMenuList(vo);
	}

	@Override
	public void deleteMenu(AdminmenuVO vo) throws Exception {
		// TODO Auto-generated method stub
		menuDAO.deleteMenu(vo);
	}

	@Override
	public void moveMenuOwn(AdminmenuVO vo) throws Exception {
		// TODO Auto-generated method stub
		menuDAO.moveMenuOwn(vo);
		menuDAO.updateRootStepOwn(vo);
		menuDAO.updateRootStepTwn(vo);
		menuDAO.moveMenuTwn(vo);
	}

	@Override
	public void moveSubMenuOwn(AdminmenuVO vo) throws Exception {
		// TODO Auto-generated method stub
		menuDAO.moveSubMenuOwn(vo);
		menuDAO.updateStepOwn(vo);
		menuDAO.updateStepTwn(vo);
		menuDAO.moveSubMenuTwn(vo);
	}

	@Override
	public String category(int ref) throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		String tmpStr = "";
		MenuBeanVO vo = new MenuBeanVO();
		while(ref != 0){
			vo = menuDAO.category(ref);
			ref = vo.getRef();
			tmpStr += vo.getName()+",";
		}
		if(tmpStr.split(",").length ==1){
			result += tmpStr.split(",")[0];
		}else{
			for(int i = tmpStr.split(",").length; i>0; i--){
				result += tmpStr.split(",")[i-1]+" > ";
			}
		}

		
		
		return result;
	}

	@Override
	public String category2(int ref) throws Exception {
		// TODO Auto-generated method stub
		String result ="";
		MenuBeanVO vo = menuDAO.category2(ref);
		result = vo.getName();
		return result;
	}

	@Override
	public boolean isLastNode(AdminmenuVO vo) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		List resultList = menuDAO.isLastNode(vo);
		if(resultList.size() == 0){
			flag = false;
		}else{
			flag = true;
		}
		return flag;
	}

	@Override
	public int menuMaxCount() throws Exception {
		// TODO Auto-generated method stub
		return menuDAO.menuMaxCount();
	}

	@Override
	public List menuLeft(AdminmenuVO vo) throws Exception {
		// TODO Auto-generated method stub
		return menuDAO.menuTwoList(vo);
	}

	@Override
	public List menuInfoList(int groupKey) throws Exception {
		// TODO Auto-generated method stub
		return menuDAO.menuInfoList(groupKey);
	}

	@Override
	public List contentList(MenuBeanVO vo) throws Exception {
		// TODO Auto-generated method stub
		return menuDAO.contentList(vo);
	}
	
	@Override
	public List inheritId(int key) throws Exception {
		// TODO Auto-generated method stub
		return menuDAO.inheritId(key);
	}

	@Override
	public MenuBeanVO getMenuMiniInfo(int key) throws Exception {
		// TODO Auto-generated method stub
		return menuDAO.getMenuMiniInfo(key);
	}
	
	@Override
	public MenuBeanVO menuInfo(int key) throws Exception {
		// TODO Auto-generated method stub
		return menuDAO.menuInfo(key);
	}

	
	
	@Override
	public void menuInsert(MenuBeanVO vo) throws Exception {
		// TODO Auto-generated method stub
		int key = menuDAO.menuMaxCount();
		if(vo.getKey() == 0){
			vo.setKey(key);
			vo.setRoot(key);
			vo.setRef(0);
			vo.setDepth(0);
			vo.setStep(0);
			
			if(vo.getTitlefile() == null){
				vo.setTitlefile("''");
			}
			if(vo.getImage1() == null){
				vo.setImage1("''");
			}
			if(vo.getImage2() == null){
				vo.setImage2("''");
			}
			if(vo.getImage3() == null){
				vo.setImage3("''");
			}
			if(vo.getImage4() == null){
				vo.setImage4("''");
			}
			if(vo.getProgram() == null){
				vo.setProgram("''");
			}
			if(vo.getColor() == null){
				vo.setColor("''");
			}
			if(vo.getLink() == null){
				vo.setLink("''");
			}
			
			menuDAO.menuInsert(vo);
		}else{
			MenuBeanVO infoVO = menuDAO.menuInfo(vo.getKey());
			vo.setRoot(infoVO.getRoot());
			vo.setRef(vo.getKey());
			vo.setDepth(infoVO.getDepth()+1);
			int max = menuDAO.maxStep(infoVO);
			if(max == 0){
				max = infoVO.getStep()+1;
			}
			
			int max1 = menuDAO.maxStep1(infoVO);
			if(max1 != 0){
				max = max1;
			}
			
			vo.setStep(max);
			
	//		menuDAO.moveStep(infoVO);  왜 update 하지 ?
			if(vo.getTitlefile() == null){
				vo.setTitlefile("''");
			}
			if(vo.getImage1() == null){
				vo.setImage1("''");
			}
			if(vo.getImage2() == null){
				vo.setImage2("''");
			}
			if(vo.getImage3() == null){
				vo.setImage3("''");
			}
			if(vo.getImage4() == null){
				vo.setImage4("''");
			}
			if(vo.getProgram() == null){
				vo.setProgram("''");
			}
			if(vo.getColor() == null){
				vo.setColor("''");
			}
			if(vo.getLink() == null){
				vo.setLink("''");
			}
			vo.setKey(key);
			menuDAO.menuInsert(vo);
		}
	}

	@Override
	public void menuUpdate(MenuBeanVO vo) throws Exception {
		// TODO Auto-generated method stub
		if(vo.getTitlefile() == null){
			vo.setTitlefile("''");
		}
		if(vo.getImage1() == null){
			vo.setImage1("''");
		}
		if(vo.getImage2() == null){
			vo.setImage2("''");
		}
		if(vo.getImage3() == null){
			vo.setImage3("''");
		}
		if(vo.getImage4() == null){
			vo.setImage4("''");
		}
		if(vo.getProgram() == null){
			vo.setProgram("''");
		}
		if(vo.getColor() == null){
			vo.setColor("''");
		}
		if(vo.getLink() == null){
			vo.setLink("''");
		}
		
		int adminKeyInt =  firstAdminKey(vo.getAdminStr().split(",")[0].toString());
		vo.setAdminKey(adminKeyInt);
		menuDAO.menuUpdate(vo);
	}
	
	
	public int firstAdminKey(String adminKey) throws Exception{
		int paramKey = 0;
		if(adminKey != null && !adminKey.equals("")){
			paramKey = menuDAO.menuFirstAdminKey(adminKey.split(",")[0].toString());			
		}	
		return paramKey;
	}

	@Override
	public void menuVisibleUpdat(MenuBeanVO vo) throws Exception {
		// TODO Auto-generated method stub
		menuDAO.menuVisibleUpdat(vo);
	}

	@Override
	public void menuStatisticUpdate(MenuBeanVO vo) throws Exception {
		// TODO Auto-generated method stub
		menuDAO.menuStatisticUpdate(vo);
	}

	@Override
	public List menuOrderList(MenuBeanVO vo) throws Exception {
		// TODO Auto-generated method stub
		return menuDAO.menuOrderList(vo);
	}
	
	@Override
	public void menuOrderUpdate(MenuBeanVO vo) throws Exception {
		// TODO Auto-generated method stub
		menuDAO.menuOrderUpdate(vo);
	}


	@Override
	public List menuSubOrderList(MenuBeanVO vo) throws Exception {
		// TODO Auto-generated method stub
		return menuDAO.menuSubOrderList(vo);
	}

	@Override
	public void menuSubOrderUpdate(MenuBeanVO vo) throws Exception {
		// TODO Auto-generated method stub
		menuDAO.menuSubOrderUpdate(vo);
	}

	@Override
	public void menuDelete(MenuBeanVO vo) throws Exception {
		// TODO Auto-generated method stub
	
		int count = 0;
		MenuBeanVO info = menuDAO.menuInfo(vo.getKey());
		
		List treeList = menuDAO.deleteTree(info);
		menuDAO.menuDelete(vo.getKey());
		count++;
		if(treeList != null){
			for(int i=0; i<treeList.size(); i++){
				MenuBeanVO dVO = (MenuBeanVO)treeList.get(i);
				if(info.getDepth() < dVO.getDepth()){
					menuDAO.menuDelete(dVO.getKey());
					count++;
				}
			}
			
		}
		
		for(int i=0; i<count; i++){
			menuDAO.menuMoveStepMinus(info);
		}

	}

	@Override
	public List getAllMenuList() throws Exception {
		// TODO Auto-generated method stub
		return menuDAO.getAllMenuList();
	}

	@Override
	public MenuBeanVO getMenuInfo(int key) throws Exception {
		// TODO Auto-generated method stub
		return menuDAO.getMenuInfo(key);
	}

	@Override
	public String inheritName(int menuKey) throws Exception {
		// TODO Auto-generated method stub
		return menuDAO.inheritName(menuKey);
	}
}
