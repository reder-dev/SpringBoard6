package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SampleController4 {

	private static final Logger logger = LoggerFactory.getLogger(SampleController4.class);

	//http://localhost:8088/web/doD
	//http://localhost:8088/web/doD?msg=itwill
	@RequestMapping(value = "/doD",method = RequestMethod.GET)
	public String doD(RedirectAttributes rttr
			     /* @ModelAttribute("msg") String msg */) {
		logger.info("/doD -> doD() 호출");
		
		logger.info(" /doD -> /doE 주소 호출");
		//logger.info(" D : msg-"+msg);
		// RedirectAttributes 객체는 Model객체와 비슷한 동작을 한다
		// => 반드시! redirect 방식으로 이동할때만 사용가능
		
		
		//rttr.addAttribute("msg", "ITWILL BUSAN");
		// => model.addAttribute("msg", "ITWILL BUSAN");
		//   동일한 동작처리 / 주소줄에 데이터 표시O, F5 - 데이터 유지
		rttr.addFlashAttribute("msg", "ITWILL BUSAN@@");
		//   주소줄에 데이터 표시X, F5 - 데이터 유지 X  / 1회성 데이터
		
		
		
		//return "/doE"; // => view페이지
		
		//return "redirect:/doE"; 
		// response.sendRedirect()의 동작을 처리가능
		//return "forward:/doE"; 
		// forward()의 동작 처리 가능
		// => 주소를 호출
		
		
		return "redirect:/doE"; 
	}
	
	//http://localhost:8088/web/doE
	@RequestMapping(value = "/doE",method = RequestMethod.GET)
	public void doE(@ModelAttribute("msg") String msg) {
		logger.info("/doE -> doE() 호출");
		logger.info(" E : "+msg);
	}
	
	
	
}
