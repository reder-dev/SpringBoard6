package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

//@Service : 해당 객체를 서비스 처리객체로 인지
//           root-context.xml에서 객체(빈)으로 등록


@Service
public class MemberServiceImpl implements MemberService {
	// DAO동작을 호출
	private static final Logger logger 
	   = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Inject
	private MemberDAO mdao;
	
	@Override
	public void memberJoin(MemberVO vo) {
		logger.info(" 회원가입 실행 -> DAO 메서드 호출 ");
		mdao.insertMember(vo);
	}

	@Override
	public MemberVO memberLoginCheck(MemberVO loginVO) {
		logger.info(" memberLoginCheck() 실행 ");
		logger.info(" DAO메서드 호출");
		
		MemberVO resultVO = mdao.loginMember(loginVO);
		logger.info(" SQL 구문 실행완료! ");
		
		return resultVO;
		
	}

	@Override
	public MemberVO memberInfo(String userid) {
		logger.info("memberInfo(String userid) 호출");
		//DAO동작을 호출
		
		
		
		return mdao.getMemberInfo(userid);
	}

	@Override
	public int memberUpdate(MemberVO updateVO) {
		logger.info(" memberUpdate(MemberVO updateVO) 호출 ");
		
		
		return mdao.updateMember(updateVO);
	}

	@Override
	public int memberDelete(MemberVO deleteVO) {
		logger.info(" (MemberVO deleteVO) 호출");
		
		
		return mdao.deleteMember(deleteVO);
	}

	@Override
	public List<MemberVO> memberList() {
		logger.info(" memberList() 호출 ");
		return mdao.getMemberList();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
