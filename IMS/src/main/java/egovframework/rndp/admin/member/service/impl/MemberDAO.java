package egovframework.rndp.admin.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rndp.admin.member.service.MemberVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("memberDAO")
public class MemberDAO extends EgovAbstractDAO{

	/**
	 * 회원 리스트
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List memberList(MemberVO vo) throws Exception{
		return list("memberDAO.memberList", vo);
	}
	
	/**
	 * 회원 총수 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int memberListCnt(MemberVO vo) throws Exception{
		return (Integer)getSqlMapClientTemplate().queryForObject("memberDAO.memberListCnt", vo);
	}
	
	/**
	 * 회원정보 상세
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public MemberVO memberView(int key) throws Exception{
		return (MemberVO) selectByPk("memberDAO.memberView", key);
	}
	
	/**
	 * 회원 정보 수정
	 * @param vo
	 * @throws Exception
	 */
	public void memberUpdate(MemberVO vo) throws Exception{
		update("memberDAO.memberUpdate", vo);
	}
	
	/**
	 * 횡뤈삭제
	 * @param key
	 * @throws Exception
	 */
	public void memberDelete(int key) throws Exception{
		delete("memberDAO.memberDelete", key);
	}
	
	
	//여기부터는 홈페이지
	
	/**
	 * 아이디 체크
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String webCheckid(String id) throws Exception{
		return (String)getSqlMapClientTemplate().queryForObject("memberDAO.webCheckid", id);
	}
	
	/**
	 * 우편번호 검색
	 * @param searchWord
	 * @return
	 * @throws Exception
	 */
	public List postList(String searchWord) throws Exception{
		return list("memberDAO.postList", searchWord);
	}
	
	/**
	 * 회원가입
	 * @param vo
	 * @throws Exception
	 */
	public void memberInsert(MemberVO vo) throws Exception{
		int key = (Integer)getSqlMapClientTemplate().queryForObject("memberDAO.memberMaxKey", vo);
		int levelKey = (Integer)getSqlMapClientTemplate().queryForObject("levelDAO.memberMaxLevel", vo);
		vo.setKey(key);
		vo.setLevelKey(levelKey);
		
		insert("memberDAO.memberInsert", vo);
	}
	
	/**
	 * 아이디와 비번으로 회원정보를 가져옴
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public MemberVO getMemberInfo(MemberVO vo) throws Exception{
		return (MemberVO) selectByPk("memberDAO.getMemberInfo", vo);
	}
	
	
	public void updateLastdate(int key) throws Exception{
		update("memberDAO.updateLastdate", key);
	}

	public MemberVO memInfo(int key) throws Exception{
		// TODO Auto-generated method stub
		return (MemberVO) selectByPk("memberDAO.memInfo", key);
	}

	public void memberWebUpdate(MemberVO memberVO) throws Exception{
		update("memberDAO.memberWebUpdate", memberVO);
	}
	
	public void memberWeUpdatePass(MemberVO memberVO) throws Exception{
		update("memberDAO.memberWeUpdatePass", memberVO);
	}
}
