package egovframework.rndp.admin.menu.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rndp.admin.menu.service.AdminmenuVO;
import egovframework.rndp.admin.menu.service.MenuBeanVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("menuDAO")
public class MenuDAO extends EgovAbstractDAO{
	
	public List menuListOwn() throws Exception{
		return list("menuDAO.menuListOwn", null);
	}
	
	
	
	public List menuOwnList(AdminmenuVO vo) throws Exception{
		return list("menuDAO.menuOwnList",vo);
	}
	
	public List menuTwoList(AdminmenuVO vo) throws Exception{
		return list("menuDAO.menuTwoList", vo);
	}
	
	public List menuListTwo(AdminmenuVO vo) throws Exception{
		return list("menuDAO.menuListTwo",vo);
	}
	
	public List moveMenuList(AdminmenuVO vo) throws Exception{
		return list("menuDAO.moveMenuList", vo);
	}
	
	public List moveSubMenuList(AdminmenuVO vo) throws Exception{
		return list("menuDAO.moveSubMenuList", vo);
	}
	
	public void insertMenu(AdminmenuVO vo) throws Exception{
		insert("menuDAO.insertMenu", vo);
	}
	
	public void updateMenu(AdminmenuVO vo) throws Exception{
		update("menuDAO.updateMenu", vo);
	}
	
	public void deleteMenu(AdminmenuVO vo) throws Exception{
		delete("menuDAO.deleteMenu", vo);
	}
	
	public void moveMenuOwn(AdminmenuVO vo) throws Exception{
		update("menuDAO.moveMenuOwn", vo);
	}
	
	public void moveMenuTwn(AdminmenuVO vo) throws Exception{
		update("menuDAO.moveMenuTwn", vo);
	}
	
	public void updateRootStepOwn(AdminmenuVO vo) throws Exception{
		update("menuDAO.updateRootStepOwn", vo);
	}
	
	public void updateRootStepTwn(AdminmenuVO vo) throws Exception{
		update("menuDAO.updateRootStepTwn", vo);
	}
	
	public void moveSubMenuOwn(AdminmenuVO vo) throws Exception{
		update("menuDAO.moveSubMenuOwn", vo);
	}
	
	public void moveSubMenuTwn(AdminmenuVO vo) throws Exception{
		update("menuDAO.moveSubMenuTwn", vo);
	}
	
	public void updateStepOwn(AdminmenuVO vo) throws Exception{
		update("menuDAO.updateStepOwn", vo);
	}
	
	public void updateStepTwn(AdminmenuVO vo) throws Exception{
		update("menuDAO.updateStepTwn", vo);
	}
	
	public void stepUp(AdminmenuVO vo) throws Exception{
		update("menuDAO.stepUp", vo);
	}
	

	
	public List isLastNode(AdminmenuVO vo) throws Exception{
		return list("menuDAO.isLastNode", vo);
	}
	
	
	
	
	
	
	
	
	
	
	public List menuInfoList(int groupKey) throws Exception{
		return list("menuDAO.menuInfoList", groupKey);
	}
	
	public List contentList(MenuBeanVO vo) throws Exception{
		return list("menuDAO.contentList", vo);
	}
	
	public List inheritId(int key) throws Exception{
		return list("menuDAO.inheritId", key);
	}
	
	public String inheritName(int menuKey) throws Exception{
		return (String)selectByPk("menuDAO.inheritName", menuKey);
	}
	
	public MenuBeanVO getMenuMiniInfo(int key) throws Exception{
		return (MenuBeanVO)selectByPk("menuDAO.getMenuMiniInfo",key);
	}
	
	public MenuBeanVO getMenuInfo(int key) throws Exception{
		return (MenuBeanVO) selectByPk("menuDAO.getMenuInfo", key);
	}
	
	
	
	public int menuMaxCount() throws Exception{
		return (Integer)getSqlMapClientTemplate().queryForObject("menuDAO.menuMaxCount", null);
	}
	
	public MenuBeanVO category(int ref) throws Exception{
		return (MenuBeanVO)selectByPk("menuDAO.category", ref);
	}
	
	public MenuBeanVO category2(int ref) throws Exception{
		return (MenuBeanVO)selectByPk("menuDAO.category2", ref);
	}
	
	public MenuBeanVO menuInfo(int key) throws Exception{
		return (MenuBeanVO)selectByPk("menuDAO.menuInfo", key);
	}
	
	public int maxStep(MenuBeanVO vo) throws Exception{
		int maxStep = 0;
		if((Integer)getSqlMapClientTemplate().queryForObject("menuDAO.maxStep", vo) == null){
			maxStep = vo.getStep()+1;
		}else{
			maxStep = (Integer)getSqlMapClientTemplate().queryForObject("menuDAO.maxStep", vo);
		}
		return maxStep;
	}
	
	public int maxStep1(MenuBeanVO vo) throws Exception{
		int maxStep1 = 0;
		if((Integer)getSqlMapClientTemplate().queryForObject("menuDAO.masStep1", vo) == null){
			maxStep1 = 0;
		}else{
			maxStep1 = (Integer)getSqlMapClientTemplate().queryForObject("menuDAO.masStep1", vo);
		}
		return maxStep1;
	}
	
	public void moveStep(MenuBeanVO vo) throws Exception{
		update("menuDAO.moveStep", vo);
	}
	
	public void menuInsert(MenuBeanVO vo) throws Exception{
		insert("menuDAO.menuInsert", vo);
	}
	
	public void menuUpdate(MenuBeanVO vo) throws Exception{
		update("menuDAO.menuUpdate", vo);
	}
	
	public int menuFirstAdminKey(String adminId) throws Exception{
		return (Integer)getSqlMapClientTemplate().queryForObject("menuDAO.menuFirstAdminKey", adminId);
	}
	
	public void menuVisibleUpdat(MenuBeanVO vo) throws Exception{
		update("menuDAO.menuVisibleUpdate", vo);
	}
	
	public void menuStatisticUpdate(MenuBeanVO vo) throws Exception{
		update("menuDAO.menuStatisticUpdate", vo);
	}
	
	public List menuOrderList(MenuBeanVO vo) throws Exception{
		return list("menuDAO.menuOrderList", vo);
	}
	
	public void menuOrderUpdate(MenuBeanVO vo) throws Exception{
		MenuBeanVO uVO = new MenuBeanVO();
		uVO.setStrRoot(-1);
		uVO.setStrRoot2(vo.getRoot());
		uVO.setGroupKey(vo.getGroupKey());
		update("menuDAO.menuOrderInfoUpdate", uVO);
		uVO.setStrRoot(-1);
		uVO.setStrRoot2(vo.getRoot());
		uVO.setGroupKey(vo.getGroupKey());
		update("menuDAO.menuOrderRootStepUpdate", uVO);
		uVO.setStrRoot(1);
		uVO.setStrRoot2(vo.getRoot2());
		uVO.setGroupKey(vo.getGroupKey());
		update("menuDAO.menuOrderRootStepUpdate", uVO);
		uVO.setStrRoot(vo.getRoot2());
		uVO.setStrRoot2(-1);
		uVO.setGroupKey(vo.getGroupKey());
		update("menuDAO.menuOrderInfoUpdate", uVO);
	}
	
	public List menuSubOrderList(MenuBeanVO vo) throws Exception{
		return list("menuDAO.menuSubOrderList", vo);
	} 
	
	public void menuSubOrderUpdate(MenuBeanVO vo) throws Exception{
		MenuBeanVO uVO = new MenuBeanVO();
		
		uVO.setStrStep(-1);
		uVO.setStrRoot(vo.getRoot());
		uVO.setStrStep2(vo.getStep());
		uVO.setGroupKey(vo.getGroupKey());
		update("menuDAO.menuSubOrderInfo", uVO);
		
		uVO.setStrStep(-1);
		uVO.setStrRoot(vo.getRoot());
		uVO.setStrStep2(vo.getStep());
		uVO.setGroupKey(vo.getGroupKey());
		update("menuDAO.menuSubOrderStepUpdate", uVO);
		
		uVO.setStrStep(1);
		uVO.setStrRoot(vo.getRoot());
		uVO.setStrStep2(vo.getRoot2());
		uVO.setGroupKey(vo.getGroupKey());
		update("menuDAO.menuSubOrderStepUpdate", uVO);
		
		uVO.setStrStep(vo.getRoot2());
		uVO.setStrRoot(vo.getRoot());
		uVO.setStrStep2(-1);
		uVO.setGroupKey(vo.getGroupKey());
		update("menuDAO.menuSubOrderInfo", uVO);
	}
	
	public List deleteTree(MenuBeanVO vo) throws Exception{
		return list("menuDAO.deleteTree", vo);
	}
	
	public void menuMoveStepMinus(MenuBeanVO vo) throws Exception{
		update("menuDAO.menuMoveStepMinus", vo);
	}
	
	public void menuMoveStepPlus(MenuBeanVO vo) throws Exception{
		update("menuDAO.menuMoveStepPlus", vo);
	}
	
	public void menuDelete(int key) throws Exception{
		delete("menuDAO.menuDelete", key);
	}
	
	
	public List getAllMenuList() throws Exception{
		return list("menuDAO.getAllMenuList", null);
	}
}
