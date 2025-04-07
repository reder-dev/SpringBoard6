package com.itwillbs.web;

import java.sql.Connection;

import javax.inject.Inject;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)

public class MyBatisTest {
	
	// 디비연결 테스트
	// SqlSessionFactory객체를 주입받아서 사용
	// private SqlSessionFactory sqlFactory = new SqlSessionFactoryBean(); 
	@Inject	
	private	SqlSessionFactory sqlFactory;
	
	@Test
	public void factoryTest() throws Exception {
		System.out.println("SqlSessionFactory 객체가 있는지 체크 ");
		System.out.println(sqlFactory);
		
		// 디비 연결
		SqlSession sqlSession = sqlFactory.openSession();
		
		System.out.println(sqlSession);
		// SQL구문 실행
		//sqlSession.insert(statement);
		//sqlSession.update(statement);
		//sqlSession.selectOne(statement);
		//sqlSession.delete(statement);
		
	}

}
