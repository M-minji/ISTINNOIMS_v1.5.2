package egovframework.rndp.admin.member.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rndp.admin.member.service.MemberService;
import egovframework.rndp.admin.member.service.MemberVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("memberService")
public class MemberServiceImpl extends EgovAbstractServiceImpl implements MemberService{

	@Resource(name="memberDAO")
	private MemberDAO memberDAO;

	@Override
	public List memberList(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return memberDAO.memberList(vo);
	}

	@Override
	public int memberListCnt(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return memberDAO.memberListCnt(vo);
	}

	@Override
	public MemberVO memberView(int key) throws Exception {
		// TODO Auto-generated method stub
		return memberDAO.memberView(key);
	}

	@Override
	public void memberDelete(int key) throws Exception {
		// TODO Auto-generated method stub
		memberDAO.memberDelete(key);
	}

	@Override
	public void memberUpdate(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		memberDAO.memberUpdate(vo);
	}

	//여기부터는 홈페이지
	@Override
	public String webCheckid(String id) throws Exception {
		// TODO Auto-generated method stub
		return memberDAO.webCheckid(id);
	}

	@Override
	public List postList(String searchWord) throws Exception {
		// TODO Auto-generated method stub
		return memberDAO.postList(searchWord);
	}

	@Override
	public void memberInsert(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		memberDAO.memberInsert(vo);
	}

	@Override
	public MemberVO getMemberInfo(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return memberDAO.getMemberInfo(vo);
	}

	@Override
	public void updateLastdate(int key) throws Exception {
		// TODO Auto-generated method stub
		memberDAO.updateLastdate(key);
	}

	@Override
	public MemberVO memInfo(int key) throws Exception {
		return memberDAO.memInfo(key);
	}
	
	@Override
	public void memberWebUpdate(MemberVO memberVO) throws Exception{
		memberDAO.memberWebUpdate(memberVO);
	}
	
	@Override
	public void memberWeUpdatePass(MemberVO memberVO) throws Exception{
		memberDAO.memberWeUpdatePass(memberVO);
	}
}
