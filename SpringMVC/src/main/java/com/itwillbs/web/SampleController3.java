package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwillbs.domain.MemberVO;

@Controller
public class SampleController3 {

	private static final Logger logger = LoggerFactory.getLogger(SampleController3.class);

	//http://localhost:8088/web/doC
	@RequestMapping(value = "/doC",method =RequestMethod.GET)
	public String doC(Model model) {
		logger.info(" /doC -> doC() 호출 ");
		
		// MemberVO 객체 생성 => DB에서 받아온 정보 
		MemberVO vo = new MemberVO();
		vo.setUserid("admin");
		vo.setUserpw("1234");
		vo.setUsername("관리자");
		vo.setUseremail("admin@admin.com");
		// 영역객체에 저장 -> 뷰페이지로 전달가능
		// request.setAttribute(); (x)
		// => 'Model'객체를 사용해서 동작을 처리
		//    Model:스프링MVC에서 제공하는 객체(프로젝트에서 하나)
		//         컨트롤러의 데이터를 뷰페이지로 전달(택배아저씨)
		
		//model.addAttribute("속성명",속성값);
		//model.addAttribute("vo", vo);

		//model.addAttribute(속성값);
		// => 이름이 없는경우, 전달되는 객체의 클래스명을 이름으로 사용
		//    그때 클래스명의 첫글자를 소문자로 변경해서 사용!
		model.addAttribute(vo);
		
		logger.info(" ITWILL.jsp 페이지로 이동");
		return "ITWILL";
	}
	
	//http://localhost:8088/web/doC1
	//http://localhost:8088/web/doC1?hobby=게임&hobby=운동&hobby=요리
	@RequestMapping(value = "doC1", method = RequestMethod.GET)
	public String doC1(@ModelAttribute("hobby") String[] hobby) {
		logger.info("/doC1 -> doC1 호출");
		
		logger.info("hobby : "+hobby[0]);
		//logger.info("hobby : "+hobby[0]);
		//logger.info("hobby : "+hobby[0]);
		// 한번에 동일한 이름의 파라메터 정보를 저장X
		
		return "ITWILL";
	}
	
	//http://localhost:8088/web/doC2?hobby=게임&hobby=운동&hobby=요리
		@RequestMapping(value = "doC2", method = RequestMethod.GET)
		public String doC2(@RequestParam("hobby") String hobby) {
			logger.info("/doC2 -> doC2() 호출");
			
			//logger.info("hobby : "+hobby);	// String - 게임,운동,요리
			//logger.info("hobby : "+hobby[0]);	// String[] - [게임,운동 요리]
			//logger.info("hobby : "+hobby[1]);
			//logger.info("hobby : "+hobby[2]);
			//logger.info("hobby : "+hobby[0]);
			logger.info("hobby : "+hobby);	// ArrayList - [게임, 운동, 요리]
			
			
			return "ITWILL";
		}
		
		// * 파라메터 데이터의 정보가 특정객체(VO)에 저장가능하다면
		// 메서드 매개변수로 사용시 파라메터 자동 수집 가능
		
		//http://localhost:8088/web/doC3?userid=admin&userpw=1234&tel=010-1234-1234
			@RequestMapping(value = "doC3", method = RequestMethod.GET)
			public String doC3(/*ModelAttribute */MemberVO vo
								/*@ModelAttribute("userid") String userid,
								@RequestParam("userpw") String userpw*/) {
				logger.info("/doC3 -> doC3() 호출");
				
				//logger.info("ID : "+userid);
				//logger.info("PW : "+userpw);
				
				logger.info("vo :"+vo);	
					
					
				return "ITWILL";
			}
		
	
}
