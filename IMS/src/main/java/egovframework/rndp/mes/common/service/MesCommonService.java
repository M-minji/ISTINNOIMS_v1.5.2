package egovframework.rndp.mes.common.service;

import java.util.List;

/**
 * <pre>
 * 사용빈도 많은 경우는 코드 유지보수성을 높이기 위해 common을 신규로 만들고
 * 공통사용 모듈을 모아놓되 기존 소스에서 import만 변경해서 적용되게 하기 위해 
 * commonVo를 만들지 않고 기존 vo 와 동일한 명으로 생성해 사용한다.
 * </pre>
 * @author rndp-21
 *
 */
public interface MesCommonService {
	/**
	 * 카테고리별 코드 리스트 조회 (공통사용)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List selectMesGubunCodeList(MesCommonGubunVO vo) throws Exception;
	
	/**
	 * <pre>
	 * 물품 카테고리 리스트 조회 (공통사용 ) 
	 * </pre>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List selectMesItemCateList(MesCommonItemVO vo) throws Exception;

	
	/**
	 * <pre>
	 * 물품 리스트 팝업 조회 (공통사용) 
	 * </pre>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List selectPopupMesItemList(MesCommonItemVO vo) throws Exception;

	/**
	 * <pre>
	 * 물품 리스트 팝업 조회시 페이징 위해 개수조회 (공통사용) 
	 * </pre>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int selectPopupMesItemListCnt(MesCommonItemVO vo) throws Exception;


	/**
	 * <pre>
	 * 거래처 리스트 팝업 조회 (공통사용) 
	 * </pre>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List selectMesCompanyList(MesCommonCompanyVO vo) throws Exception;

	/**
	 * <pre>
	 * 거래처 리스트 팝업 조회시 페이징 위해 개수조회 (공통사용) 
	 * </pre>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int selectMesCompanyListCnt(MesCommonCompanyVO vo) throws Exception;

	/**
	 * <pre>
	 * 결재 승인권자 리스트 조회 (공통사용) 
	 * </pre>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List selectApprovalStaffList(MesCommonBaljuVO vo) throws Exception;


	/**
	 * <pre>
	 * 품목분류 리스트 조회 (공통사용) 
	 * </pre>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List cateListSerachType(MesCommonItemVO vo) throws Exception;


	/**
	 * 기본정보, 기타관리 ,기타문의관리 List
	 * @return
	 * @throws Exception
	 */
	public List envList() throws Exception;
	

	/**
	 * 결재 삭제 
	 * @return
	 * @throws Exception
	 */
	public void deleteApproval(MesCommonApprovalVO vo) throws Exception;

	/**
	 * 부서 리스트 조회 (공통사용)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List selectPositionList() throws Exception;


	/**
	 * 작업지시 상세 정보 (공통사용)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public MesCommonProductionVO selectProductionInfo(MesCommonProductionVO vo) throws Exception;
	
	/**
	 * 작업지시 아이템 상세 정보 (공통사용)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List selectProductionItemInfo(MesCommonProductionVO vo) throws Exception;
 
 
	/**
	 * 품목조회 팝업 정보 (공통사용)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int inventoryListBacodePopListCnt(MesCommonBaljuVO vo) throws Exception;

	/**
	 * 품목조회 팝업 정보 (공통사용)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List inventoryListBacodePopList(MesCommonBaljuVO vo) throws Exception;


	/**
	 * 품목분류 리스트 조회 (공통사용)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List selectCateList(MesCommonItemVO vo) throws Exception;

	/**
	 * BOM 품목 리스트 조회 (공통사용)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List selectBomInfoList(MesCommonItemVO vo) throws Exception;
	
	/**
	 * 품목 조회 (공통사용)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public MesCommonItemVO selectMesItemInfo(MesCommonItemVO vo) throws Exception;

	/**
	 * 제품재고현황 리스트 조회 (공통사용)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List inventoryList(MesCommonBaljuVO vo) throws Exception;
	
	/**
	 * 제품재고현황 리스트 개수 조회 (공통사용)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int inventoryListCnt(MesCommonBaljuVO vo) throws Exception;

}
