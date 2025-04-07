package com.itwillbs.web;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;
import com.itwillbs.persistence.MemberDAOImpl;
import com.itwillbs.service.MemberService;

// ./MemberJoin.me ./MemberLogin.me
// @RequestMapping("/member/*")
// -> 주소가 /member/~ 시작되는 모든 주소를 처리하겠다.

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	//@Inject
	//private MemberDAO mdao;
	@Inject
	private MemberService mService;
	
	
	// http://localhost:8088/web/MemberJoin.me (x)
	// http://localhost:8088/web/member/MemberJoin.me (o)
	// http://localhost:8088/web/member/join (o)
	// http://localhost:8088/member/join (o)
	// 회원가입 - 정보입력(GET)
	@RequestMapping(value = "/join",method = RequestMethod.GET)
	public void memberJoinGET() {
		logger.info("/member/join -> memberJoinGET() 호출");
		
		//연결된 JSP 뷰페이지로 이동
		logger.info(" /WEB-INF/views/member/join.jsp 페이지 이동  ");
	}
	// http://localhost:8088/web/member/MemberJoinAction.me (o)
	// http://localhost:8088/web/member/join (o)
	// 회원가입 - 정보처리(POST)
	@RequestMapping(value = "/join",method = RequestMethod.POST)
	public String memberJoinPOST(MemberVO vo) {
		logger.info("memberJoinPOST() 호출");
		
		//폼태그 전달정보 저장
		logger.info(" vo : "+vo);
		
		//전달받은 정보를 처리 => 디비에 정보를 저장
		//MemberDAO mdao = new MemberDAOImpl();(X)
		//mdao.insertMember(vo); (x)
		// 서비스 호출 (DAO 대신 호출)
		mService.memberJoin(vo);
		
		logger.info(" 회원가입 성공! ");
		
		// 로그인 페이지로 이동		
		return "redirect:/member/login";
	}
	
	
	// 로그인 GET
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String memberLoginGET() {
		logger.info(" /login -> memberLoginGET()호출");
		
		logger.info(" /member/login.jsp 페이지로 이동");
		return "/member/login";
	}
	
	// HTTP 상태 405 – 허용되지 않는 메소드
	// Request method 'POST' not supported
	// => GET방식의 주소는 있지만 POST방식의 주소 호출이 없는경우 에러
	
	//  ~~ /member/login 
	//  [ body ]
	//  userid = "....."
	//  userpw = "....."
	
	// 로그인 POST
	@PostMapping(value = "/login")
	public String memberLoginPOST( /*@ModelAttribute*/ MemberVO vo,HttpSession session
									 /* @ModelAttribute("userid") String userid,
									 * @RequestParam("userpw") String userpw
									 */) {
		logger.info("  memberLoginPOST() 호출 ");
		// 한글처리 인코딩 => 생략(web.xml-필터사용)
		// 전달된 정보 저장(id,pw)
		//logger.info(" userid : "+userid );
		//logger.info(" userpw : "+userpw );
		logger.info(" 로그인 vo : "+vo);
		
		// 서비스에 로그인체크 동작을 호출해서 확인
		MemberVO resultVO = mService.memberLoginCheck(vo);
		logger.info(" 결과 : {}",resultVO);
		
		// 로그인 실패
		if(resultVO == null) {
			logger.info(" 로그인 실패! ");
			return "redirect:/member/login";
		}

		// 세션 영역에 로그인 성공한 사용자의 아이디를 저장
		session.setAttribute("id",resultVO.getUserid());
		
		// 로그인 성공시 메인페이지로 이동
		return "redirect:/member/main";
	}
	
	// 메인페이지 - GET
	@RequestMapping(value = "/main",method = RequestMethod.GET)
	public String mainGET(HttpSession session,
			              @SessionAttribute(name = "id",required = false) String sid  ) {
		logger.info(" mainGET() ");
		
		logger.info(" sid : "+sid);
		
		// 로그인 여부에 따라서 페이지 이동처리
		String id = (String) session.getAttribute("id");
		if(id == null) {
			logger.info(" 로그인 정보가 없음! ");
			return "redirect:/member/login";
		}
		// 연결된 뷰페이지로 이동
		logger.info(" /member/main.jsp 페이지 이동 ");
		return "/member/main";
	}
	
	// 로그아웃 - GET
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public String memberLogoutGET(HttpSession session) {
		logger.info(" memberLogoutGET() 호출 ");
		logger.info(" 로그아웃 동작 처리(세션정보 초기화) ");
		
		// 세션정보 초기화
		session.invalidate();
		
		return "redirect:/member/main";
	}
	
	// 회원정보 조회 - GET
	@RequestMapping(value = "/info",method = RequestMethod.GET)
	public String memberInfoGET(Model model,@SessionAttribute(name = "id",required = false) String id) {
		logger.info(" memberInfoGET() 호출 ");
		
		// 사용자의 세션정보가 있는지 없는지 체크
		if(id == null) {
			logger.info(" 사용자 로그인 정보 없음! ");
			return "redirect:/member/login";
		}
		
		// 서비스 - 회원정보 조회동작 호출
		MemberVO resultVO = mService.memberInfo(id);
		logger.info(" result : "+resultVO);
		
		// 전달받은 결과를 뷰페이지로 전달 & 출력
		//=> Model 객체
		model.addAttribute(resultVO);
		
		logger.info(" /member/info.jsp 페이지로 이동 ");
		
		return "/member/info";
	}
	
	// 회원정보 수정 - GET
	@RequestMapping(value = "/update",method = RequestMethod.GET)
	public void memberUpdateGET(HttpSession session,Model model) {
		logger.info(" memberUpdateGET()호출 ");
		
		// 사용자의 아이디정보(세션)를 사용
		String id = (String) session.getAttribute("id");
		
		// 서비스 - 기존의 회원정보를 가져오기
		MemberVO resultVO = mService.memberInfo(id);
		
		// 정보를 연결된 뷰페이지에 전달
		model.addAttribute(resultVO);		
		
		logger.info(" /member/update.jsp 페이지이동 ");		
	}
	
	// 회원정보 수정 - POST
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public String memberUpdatePOST(MemberVO vo) {
		logger.info(" memberUpdatePOST() 호출 ");
		
		// 전달된 정보(수정할 데이터) 저장
		logger.info(" vo : {}",vo);
		
		// 서비스 - 수정할 정보를 가지고 디비에서 정보 수정 동작 호출
		int result = mService.memberUpdate(vo);
		
		if(result == 0) {
			// 다시 회원정보 수정페이지로 이동
			return "redirect:/member/update";
		}
		
		// 메인페이지로 이동
		return "redirect:/member/main";
	}
	
	// 회원정보 삭제 - GET
	@GetMapping(value="/delete")
	public void memberDeleteGET() {
		logger.info("memberDeleteGET() 호출");
		
		
		
		logger.info(" /member/delete.jsp 페이지로 이동 ");
		
	}
	
	// 회원정보 삭제 - POST
	@PostMapping(value = "/delete")
	public String MemberDeletePost(HttpSession session ,
									@RequestParam(name = "userpw", required = false) String userpw,
									@SessionAttribute(name = "id", required = false) String userid) {
		logger.info(" MemberDeletePost() 호출 ");
		
		// 전달된 비밀번호 정보 저장
		logger.info(" id : {}",userid);
		logger.info(" pw : {}",userpw);
		
		MemberVO vo = new MemberVO();
		vo.setUserid(userid);
		vo.setUserpw(userpw);
		
		
		// 서비스 - 회원정보 삭제기능 호출
		int result = mService.memberDelete(vo);
		
		if(result == 0) {
			logger.info(" 삭제 오류! ");
			return "redirect:/member/delete";
		}
		
		// 삭제 성송
		// 세션의 정보를 삭제
		session.invalidate();
		
		return "redirect:/member/main";
	}
	
	
	// 회원정보 목록조회 - GET
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String memberListGET(Model model ,@SessionAttribute(name= "id", required = false) String userid) {
		logger.info(" memberListGET() 호출 ");
		
		// 관리자(admin) 로그인일때만 실행
		// (세션정보)
		if(userid == null || !userid.equals("admin")) {
			return "redirect:/member/main";
		}
		
		// 서비스 - 회원정보 목록을 조회하는 동작 수행
		List<MemberVO> memberList = mService.memberList();
		
		// 가져온 결과를 뷰페이지로 전달(Model)
		model.addAttribute("memberList",memberList);
		
		// 연결된 뷰페이지에 출력
		
		return "/member/list";
	}
	
	
}// controller
