package com.itwillbs.web;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

// @RunWith(SpringJUnit4ClassRunner.class)
// => 스프링을 사용해서 Junit4와 함께 테스트 수행하겠다.
//@WebAppConfiguration
// => 스프링 MVC 기능을 사용하겠다
//@ContextConfiguration(
//		locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"}
//		)
// => 테스트 실행시 필요한 설정정보의 위치 설정


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"}
		)
public class ControllerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ControllerTest.class);
	
	@Inject
	private WebApplicationContext wac;
	// =>실행중인 프로젝트 정보를 가져오기
	
	private MockMvc mockMvc;
	
	// @Before : 테스트 동작 이전에 실행해야하는 동작
	// @After : 테스트 동작 이후에 실행해야하는 동작 
	
	@Before
	public void setup() {
		logger.info("setup 시작..................");
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		
		logger.info("setup 끝....................");
	}
	
	@Test
	public void testDoA() throws Exception{
		
		// SampleController1.java - doA() 메서드 호출
		mockMvc.perform(MockMvcRequestBuilders.get("/doD"));
		
	}
	
	
	
	
}
