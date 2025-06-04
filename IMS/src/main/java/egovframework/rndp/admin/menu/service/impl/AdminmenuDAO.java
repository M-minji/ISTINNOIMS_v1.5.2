package egovframework.rndp.admin.menu.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rndp.admin.menu.service.AdminmenuVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("adminmenuDAO")
public class AdminmenuDAO extends EgovAbstractDAO{

	public List adminmenuTopList(AdminmenuVO vo) throws Exception{
		return list("adminmenuDAO.adminmenuTopList", vo);
	}
	
	public List adminmenuLeftList(int root) throws Exception{
		return list("adminmenuDAO.adminmenuLeftList", root);
	}
	
	public List adminmenuList() throws Exception{
		return list("adminmenuDAO.adminmenuList", null);
	}
	
	public AdminmenuVO category(int ref) throws Exception{
		return (AdminmenuVO)selectByPk("adminmenuDAO.category", ref);
	}
	
	public AdminmenuVO category2(int ref) throws Exception{
		return (AdminmenuVO)selectByPk("adminmenuDAO.category2", ref);
	}
	
	public void adminmenuInsert(AdminmenuVO vo) throws Exception{
		boolean plus = true;
		int max = (Integer)getSqlMapClientTemplate().queryForObject("adminmenuDAO.adminmenuMax", null);
		int maxStep = 1;
				
		if(vo.getKey() == 0){
			vo.setRoot(max);
						
		}else{
			AdminmenuVO info = (AdminmenuVO) selectByPk("adminmenuDAO.adminmenuInfo", vo.getKey());
			vo.setRoot(info.getRoot());
			vo.setRef(vo.getKey());
			vo.setDepth(info.getDepth()+1);
						
			int maxStep1 = 0;
			if((Integer)getSqlMapClientTemplate().queryForObject("adminmenuDAO.adminmenuMaxStep1", info) != null){
				maxStep1 = (Integer)getSqlMapClientTemplate().queryForObject("adminmenuDAO.adminmenuMaxStep1", info);
				maxStep = maxStep1;
			}else{
				maxStep = info.getStep()+1;
			}
			
			if((Integer)getSqlMapClientTemplate().queryForObject("adminmenuDAO.adminmenuMaxStep2", info) != null){
				maxStep = (Integer)getSqlMapClientTemplate().queryForObject("adminmenuDAO.adminmenuMaxStep2", info);
			}
			
			info.setStep(maxStep -1);
			
			
			if(plus){
				update("adminmenuDAO.adminmenuMoveStepPlus", info);
			}else{
				update("adminmenuDAO.adminmenuMoveStepMinus", info);
			}
			vo.setStep(maxStep);
			
		}
		vo.setKey(max);
		
		insert("adminmenuDAO.adminmenuInsert", vo);
	}
	
	public AdminmenuVO adminmenuInfo1(int key) throws Exception{
		return (AdminmenuVO)selectByPk("adminmenuDAO.adminmenuInfo1", key);
	}
	
	public void adminmenuUpdate(AdminmenuVO vo) throws Exception{
		update("adminmenuDAO.adminmenuUpdate", vo);
	}
	
	public List adminmenuOrderList(int root) throws Exception{
		return list("adminmenuDAO.adminmenuOrderList", root);
	}
	
	public void adminmenuOrder(AdminmenuVO vo) throws Exception{
		AdminmenuVO oVO = new AdminmenuVO();
		oVO.setStrRoot1(-1);
		oVO.setStrRoot2(vo.getRoot());
		update("adminmenuDAO.adminmenuOrder1", oVO);
		update("adminmenuDAO.adminmenuOrder2", oVO);
		oVO.setStrRoot1(1);
		oVO.setStrRoot2(vo.getRoot2());
		update("adminmenuDAO.adminmenuOrder2", oVO);
		oVO.setStrRoot1(vo.getRoot2());
		oVO.setStrRoot2(-1);
		update("adminmenuDAO.adminmenuOrder1", oVO);
	}
	
	public List adminmenuSubOrderList(AdminmenuVO vo) throws Exception{
		return list("adminmenuDAO.adminmenuSubOrderList", vo);
	}
	
	public void adminmenuSubOrder(AdminmenuVO vo) throws Exception{
		AdminmenuVO oVO = new AdminmenuVO();
		oVO.setStrStep1(-1);
		oVO.setRoot(vo.getRoot());
		oVO.setStrStep2(vo.getStep());
		update("adminmenuDAO.adminmenuSubOrder1", oVO);
		update("adminmenuDAO.adminmenuSubOrder2", oVO);
		oVO.setStrStep1(1);
		oVO.setRoot(vo.getRoot());
		oVO.setStrStep2(vo.getStep2());
		update("adminmenuDAO.adminmenuSubOrder2", oVO);
		oVO.setStrStep1(vo.getStep2());
		oVO.setRoot(vo.getRoot());
		oVO.setStrStep2(-1);
		update("adminmenuDAO.adminmenuSubOrder1", oVO);
	}
	
	public void adminmenuDelete(int key) throws Exception{
		int count = 0;

		AdminmenuVO info = (AdminmenuVO)selectByPk("adminmenuDAO.adminmenuInfo", key);
		
		if(info != null){
			List treeList = list("adminmenuDAO.deleteTree", info);
			
			if(treeList != null){
				delete("adminmenuDAO.adminmenuDelete", key);
				count++;
				for(int i=0; i<treeList.size(); i++){			
					AdminmenuVO tree = (AdminmenuVO)treeList.get(i);
					if(info.getDepth() < tree.getDepth()){
						
						delete("adminmenuDAO.adminmenuDelete", tree.getKey());
						count++;
					}
				}
				
				for(int i=0; i<count; i++){
					update("adminmenuDAO.adminmenuMoveStep", info);
				}
			}
			
			if(info.getDepth() == 0){
				update("adminmenuDAO.adminmenuStepUp", info.getRoot());
			}
		}
	}
	public List adminMenuAllList()  throws Exception{
		// TODO Auto-generated method stub
		return list("adminmenuDAO.adminMenuAllList");
	}
}
