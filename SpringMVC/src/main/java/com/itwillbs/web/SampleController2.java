package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SampleController2 {

	private static final Logger logger = LoggerFactory.getLogger(SampleController2.class);

	// * 메서드의 리턴타입이 String일때
	// 리턴문자열.jsp 페이지로 연결됨
	// => 주소이름과 다른 뷰페이지를 연결하기위함

	// http://localhost:8088/web/doB
	@RequestMapping("/doB")
	public String doB() {
		logger.info("/doB -> doB() 호출");
		logger.info(" 리턴문자.jsp 페이지로 이동 ");
		return "ITWILL";
	}

	// http://localhost:8088/web/doB1
	@RequestMapping("/doB1")
	public String doB1() {
		logger.info("/doB1 -> doB1() 호출");
		logger.info(" 리턴문자.jsp 페이지로 이동 ");
		return "ITWILL";
	}

	// * void/String 두가지 리턴타입의 형태처리만 가능
	//   그외 나머지 타입의 정보를 처리 불가능
	// (단, REST방식 - 기본형타입 사용, 참조형타입 사용)
	
	// http://localhost:8088/web/doB2
	/*
	 * @RequestMapping("/doB2") public double int doB2() {
	 * logger.info("/doB2 -> doB2() 호출"); logger.info(" 리턴문자.jsp 페이지로 이동 ");
	 * 
	 * //return 1000; return 1.11; }
	 */
	
	

	// http://localhost:8088/web/doB3
	// http://localhost:8088/web/doB3?msg=Hello
	
	// @ModelAttribute("파라메터") 타입 변수명
	// -> 파라메터 정보를 변수에 담아서 (controller)사용가능
	//    & 뷰페이지에서 사용가능 $ {파라메터명}
	//     => Model 객체를 사용해서 정보를 저장 
	//        model.addAttribute("이름",값);
	//    [스프링이 대신처리 => request 영역에 정보를 저장해서 전달
	//        request.setAttribute("이름",값);  ]
	
	
	@RequestMapping("/doB3")
	public String doB3(@ModelAttribute("msg") String msg) {
		logger.info("/doB3 -> doB3() 호출");
		// 파라메터 정보 출력
		logger.info(" msg : "+msg);
		
		logger.info(" 리턴문자.jsp 페이지로 이동 ");
		return "ITWILL";
	}
	
	
	// @RequestParam("파라메터명") 타입 변수명
	// => 전달된 파라메터 정보를 저장 (request 영역에 저장X)
	// => 전달된 파라메터정보가 없을경우 예외 발생(HTTP-400) 
	//    1) defaultValue = "itwill" 속성사용 2) required = false 속성사용
	
	
	
	
	// http://localhost:8088/web/doB4
	// http://localhost:8088/web/doB4?msg=Hello
	// http://localhost:8088/web/doB4?msg=Hello&id=admin
	// http://localhost:8088/web/doB4?msg=Hello&userid=admin
	@RequestMapping("/doB4")
	public String doB4(@RequestParam(required = false) String msg,
			           @RequestParam(required = false) String userid) {
		logger.info("/doB4 -> doB4() 호출");
		// 파라메터 정보 출력
		logger.info(" msg : "+msg);
		//logger.info(" id : "+id);
		logger.info(" userid : "+userid);// 다른 파라메터정보를 받을수 없음
		
		logger.info(" 리턴문자.jsp 페이지로 이동 ");
		return "ITWILL";
	}
	
	

}//controller
