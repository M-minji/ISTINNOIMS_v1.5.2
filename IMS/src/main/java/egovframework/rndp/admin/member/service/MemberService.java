package egovframework.rndp.admin.member.service;

import java.util.List;

public interface MemberService {

	public List memberList(MemberVO vo) throws Exception;
	public int memberListCnt(MemberVO vo) throws Exception;
	public MemberVO memberView(int key) throws Exception;
	public void memberDelete(int key) throws Exception;
	public void memberUpdate(MemberVO vo) throws Exception;
	
	//여기부터는 홈페이지
	public String webCheckid(String id) throws Exception;
	public List postList(String searchWord) throws Exception;
	public void memberInsert(MemberVO vo) throws Exception;
	public MemberVO getMemberInfo(MemberVO vo) throws Exception;
	public void updateLastdate(int key) throws Exception;
	public MemberVO memInfo(int key) throws Exception;
	public void memberWebUpdate(MemberVO memberVO) throws Exception;
	public void memberWeUpdatePass(MemberVO memberVO) throws Exception;
}
