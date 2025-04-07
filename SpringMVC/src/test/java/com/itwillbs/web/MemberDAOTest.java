package com.itwillbs.web;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

/**
 * Action페이지에서 호출
 * JSP페이지에서 호출
 * => 대신수행 / DAO메서드를 실행
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class MemberDAOTest {
	
	// DAO 객체에 있는 서버 시간정보를 조회하는 동작 실행
	
	// MemberDao 객체를 생성(x) => 객체 주입(o)
	@Inject
	private MemberDAO mdao;
	
	// private static final Logger logger = LoggerFactory.getLogger(MemberDAOTest.class);
	
	// 출력 로그객체 생성 ('mylog' 단축어사용)
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOTest.class);
	
	
	//@Test
	public void daoTest() throws Exception {
		// * 반드시 Logger객체 생성후에만 사용가능
		// => 'mylog' 단축어 -> 'li/ld' 단축어 출력
		//ld 단축어
		logger.debug("(┬┬﹏┬┬) : ㅁㄴㅇㅁㄴ❤️❤️");
		//li 단축어
		logger.info("$$$$$$$$$$$$$$$$$$$$$$$");
		
		System.out.println("DAO 객체 생성여부 판단");
		System.out.println(mdao);
	}
	
	//@Test
	public void 서버시간정보_조회() throws Exception {
		System.out.println("DAO-시간정보를 조회하는 메서드 호출");
		String time = mdao.getSeverTime();
		
		System.out.println(time);
		
	}
	
	//@Test
	public void 회원가입테스트() throws Exception {
		logger.debug(" 테스트 시작 ");
		
		// 입력받은 회원정보(테스트용)
		MemberVO vo = new MemberVO();
		vo.setUserid("itwill6");
		vo.setUserpw("1234");
		vo.setUsername("사용자6");
		vo.setUseremail("itwill6@itwill.com");
		
		mdao.insertMember(vo);
		
		logger.debug(" 테스트 끝");
	}
	
	//@Test
	public void 로그인테스트() {
		logger.debug(" 로그인 테스트 시작");
		
		// 테스트용 로그인 정보
		MemberVO loginVO = new MemberVO();
		loginVO.setUserid("admin");
		loginVO.setUserpw("1234");
		
		// DAO를 사용해서 로그인 체크하는 메ㅅ드 호출
		//MemberVO resultVO =  mdao.loginMember(loginVO);
		MemberVO resultVO =  mdao.loginMember("admin","1234");
		if(resultVO == null) {
			logger.info("사용자 로그인정보 없음!");
		}else {
			logger.info("로그인 성공!");
		}
		
		logger.debug(" 로그인 테스트 끝");
	}
	
	//@Test
	public void 회원정보조회테스트() {
		
		MemberVO resultVO = mdao.getMemberInfo("admin");
		
		logger.info(" 조회된 아이디 : "+resultVO.getUserid());
		logger.info(" 조회된 비밀번호 : "+resultVO.getUserpw());
		//...
		
	}
	
	//@Test
	public void 회원정보수정_테스트() {
		
		//임시데이터
		MemberVO updateVO = new MemberVO();
		updateVO.setUserid("admin");
		updateVO.setUserpw("1234");
		updateVO.setUsername("수정한 이름2!");
		
		int result = mdao.updateMember(updateVO);
		
		if(result > 0) {
			logger.info(" 정보 수정 완료! ");
		}else {
			logger.info(" 정보 수정 실패! ");
		}
		
	}
	
	
	//@Test
	public void 회원정보삭제_테스트() {
		
		MemberVO deleteVO = new MemberVO();
		
		deleteVO.setUserid("admin");
		deleteVO.setUserpw("1234");
		
		int result = mdao.deleteMember(deleteVO);
		
		if(result > 0) {
			logger.info(" 정보 삭제 완료!");
		}else {
			logger.info(" 정보 삭제 실패! ");
		}
		
	}
	
	@Test
	public void 회원전체목록_테스트() {
		
		
		
		List<MemberVO> memberList
		 = mdao.getMemberList();
		
		logger.info(" 회원수 : "+memberList.size());
		logger.info(""+memberList);
		
		for(MemberVO vo : memberList) {
			logger.info(vo.getUserid()+":"+vo.getUsername());
		}
	}
	
	
	

}
