package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Controller : 해당클래스가 컨트롤러의 동작을 수행(등록)
//              servlet-context.xml에 빈으로 등록
//              * <annotation-driven /> 태그가 있어서 
//                @Controller 사용하면 컨트롤러처럼 동작 가능하다


@Controller
public class SampleController1 {

	private static final Logger logger
      = LoggerFactory.getLogger(SampleController1.class);
	
	// 내가 수행할(처리할) 동작을 메서드로 구현
	//http://localhost:8088/web/doA
	// @RequestMapping("/doA")
	// => /doA 주소가 호출될때  연결된 메서드를 실행후
	//    연결된 뷰페이지(jsp)를 사용해서 이동
	
	// * 메서드의 리턴타입이 void인경우 주소.jsp 뷰페이지를 찾아서 연결&이동
	
	//@RequestMapping("/doA")
	//@RequestMapping(value = "/doA",method = RequestMethod.GET)
	//=> 사용자가 /doA주소를 GET방식으로 요청할때 실행하는 메서드  
	//@RequestMapping(value = "/doA",method = RequestMethod.GET)
	//@GetMapping(value = "/doA") (스프링 4.3 버전 이상사용가능)
	
	@GetMapping(value = "/doA")
	public void doA() {
		logger.info(" /doA -> doA() 호출 ");
		logger.info(" doA.jsp 페이지로 이동(생략) ");
	}
	
	//  /doA1 주소로 호출되는 동작 구현
	//  연결되는 뷰페이지를 생성해서 확인
	//http://localhost:8088/web/doA1
	@RequestMapping(value = "/doA1",method = RequestMethod.GET)
	public void doA2() {
		logger.info(" /doA1 -> doA2() 호출 ");
		logger.info(" doA1.jsp 페이지로 이동 ");		
	}
	
	
	

} 
