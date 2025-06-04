package egovframework.rndp.mes.common.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rndp.mes.common.service.MesCommonApprovalVO;
import egovframework.rndp.mes.common.service.MesCommonBaljuVO;
import egovframework.rndp.mes.common.service.MesCommonCompanyVO;
import egovframework.rndp.mes.common.service.MesCommonGubunVO;
import egovframework.rndp.mes.common.service.MesCommonItemVO;
import egovframework.rndp.mes.common.service.MesCommonProductionVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("mesCommonDAO")
public class MesCommonDAO extends EgovAbstractDAO{

	/**
	 * 카테고리별 코드 리스트 조회 (공통사용)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List selectMesGubunCodeList(MesCommonGubunVO vo) throws Exception{
		return list("mesCommonDAO.selectMesGubunCodeList", vo);
	}
	
	/**
	 * <pre>
	 * 물품 카테고리 리스트 조회 (공통사용 ) 
	 * </pre>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List selectMesItemCateList(MesCommonItemVO vo) throws Exception{
		return list("mesCommonDAO.selectMesItemCateList", vo);
	}

	/**
	 * <pre>
	 * 물품 리스트 팝업 조회 (공통사용) 
	 * </pre>
	 * @param vo
	 * @return
	 * @throws Exception
	 */   
	public List selectPopupMesItemList(MesCommonItemVO vo) throws Exception{
		return list("mesCommonDAO.selectPopupMesItemList", vo);
	}

	/**
	 * <pre>
	 * 물품 리스트 팝업 조회시 페이징 위해 개수조회 (공통사용) 
	 * </pre>
	 * @param vo
	 * @return
	 * @throws Exception
	 */ 
	public int selectPopupMesItemListCnt(MesCommonItemVO vo) throws Exception{
		return (Integer) select("mesCommonDAO.selectPopupMesItemListCnt", vo);
	}
	
	 
	/**
	 * <pre>
	 * 거래처 리스트 팝업 조회 (공통사용) 
	 * </pre>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List selectMesCompanyList(MesCommonCompanyVO vo) throws Exception{
		return list("mesCommonDAO.selectMesCompanyList", vo);
	}
	 
	/**
	 * <pre>
	 * 거래처 리스트 팝업 조회시 페이징 위해 개수조회 (공통사용) 
	 * </pre>
	 * @param vo
	 * @return
	 * @throws Exception
	 */ 
	public int selectMesCompanyListCnt(MesCommonCompanyVO vo) throws Exception{
		return (Integer) select("mesCommonDAO.selectMesCompanyListCnt", vo);
	}


	 
	
	/**
	 * <pre>
	 * 결재 승인권자 리스트 조회 (공통사용) 
	 * </pre>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List selectApprovalStaffList(MesCommonBaljuVO vo) throws Exception{
		return list("mesCommonDAO.selectApprovalStaffList", vo);
	}

	/**
	 * <pre>
	 * 물품 리스트 조회 (공통사용) 
	 * </pre>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List cateListSerachType(MesCommonItemVO vo) throws Exception{
		return list("mesCommonDAO.cateListSerachType", vo);
	}
	

	/**
	 * <pre>
	 * 기본정보 (회사정보) 리스트 조회 (공통사용) 
	 * </pre> 
	 * @return
	 * @throws Exception
	 */
	public List envList() throws Exception{
		return list("mesCommonDAO.envList", null);
	} 


	/**
	 * <pre>
	 * 결제정보 제거 (공통사용) 
	 * </pre>
	 * @param vo
	 * @throws Exception
	 */
	public void deleteApproval(MesCommonApprovalVO vo) throws Exception{
		delete("mesCommonDAO.deleteApproval", vo);
	}

	/**
	 * <pre>
	 * 부서 리스트 조회 (공통사용) 
	 * </pre> 
	 * @return
	 * @throws Exception
	 */
	public List selectPositionList() throws Exception{
		return list("mesCommonDAO.selectPositionList");
	} 
	

	/**
	 * 결재정보 insert 발주에서 이전함 (공통사용) 
	 * @param vo
	 * @throws Exception
	 */
	public void insertMesBaljuApproval(MesCommonBaljuVO vo) throws Exception{
		insert("mesCommonDAO.insertMesBaljuApproval", vo);
	}
	


	/**
	 * 작업지시 상세 정보 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public MesCommonProductionVO selectProductionInfo(MesCommonProductionVO vo) throws Exception{
		return (MesCommonProductionVO) select("mesCommonDAO.selectProductionInfo", vo);
	}

	/**
	 * 작업지시 아이템 상세 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List selectProductionItemInfo(MesCommonProductionVO vo) throws Exception{
		return list("mesCommonDAO.selectProductionItemInfo", vo);
	}


	/**
	 * 품목조회 팝업  
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List inventoryListBacodePopList(MesCommonBaljuVO vo) throws Exception {
		return list("mesCommonDAO.inventoryListBacodePopList", vo);
	}

	/**
	 * 품목조회 팝업  페이징용 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int inventoryListBacodePopListCnt(MesCommonBaljuVO vo) throws Exception {
		return (Integer)select("mesCommonDAO.inventoryListBacodePopListCnt", vo);
	}

	/**
	 * 출고입력용 (공통사용)  
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public void inventoryOutInsert(MesCommonBaljuVO vo) throws Exception {
		insert("mesCommonDAO.inventoryOutInsert", vo);
	}

	/**
	 * 현재고 마이너스 용 (공통사용) 
	 *  inventoryOutInsert 를 MesProduction 에서 동시에 사용하고 있어 모듈화를 위해 이전
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public void inventoryMinus(MesCommonBaljuVO vo) throws Exception {
		insert("mesCommonDAO.inventoryMinus", vo);
	}
	 

	/**
	 * 품목분류 리스트 조회용  (공통사용)
	 *  MesItem 에서 이전 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List selectCateList(MesCommonItemVO vo) throws Exception{
		return list("mesCommonDAO.selectCateList", vo);
	}
 

	/**
	 * BOM 품목 리스트 조회용  (공통사용)
	 *  MesItem 에서 이전 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List selectBomInfoList(MesCommonItemVO vo) throws Exception{
		return list("mesCommonDAO.selectBomInfoList", vo);
	}

	/**
	 * 품목 정보 조회용  (공통사용)
	 *  MesItem 에서 이전  selectBomInfoList 와 동시에 사용하므로 같이 이전 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public MesCommonItemVO selectMesItemInfo(MesCommonItemVO vo) throws Exception{
		return (MesCommonItemVO) select("mesCommonDAO.selectMesItemInfo", vo);
	}
 
	/**
	 * 재고입력용  (공통사용)
	 *  Balju 에서 이전  
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public void inventoryInInsert(MesCommonBaljuVO vo) throws Exception {
		insert("mesCommonDAO.inventoryInInsert", vo);
	}
	
	/**
	 * 제품 재고 반영용  (공통사용)
	 *  Balju 에서 이전  
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public void inventoryPlus(MesCommonBaljuVO vo) throws Exception {
		insert("mesCommonDAO.inventoryPlus", vo);
	} 
	

	/**
	 * 제품 재고 조회용  (공통사용)
	 *  Balju 에서 이전  
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List inventoryList(MesCommonBaljuVO vo) throws Exception{
		return list("mesCommonDAO.inventoryList", vo);
	}
	/**
	 * 제품 재고 조회 카운트용  (공통사용)
	 *  Balju 에서 이전  
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int inventoryListCnt(MesCommonBaljuVO vo) throws Exception{
		return (Integer)select("mesCommonDAO.inventoryListCnt",vo);
	}
	
	
}
