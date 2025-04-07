package com.itwillbs.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

/**
 * jUnit 테스트 도구 라이브러리를 사용해서 테스트
 *
 */
public class MysqlConnectTest {
	
	//디비 연결정보
	private static final String DRIVER="com.mysql.cj.jdbc.Driver";
	private static final String DBURL="jdbc:mysql://localhost:3306/jspdb";
	private static final String DBID ="root";
	private static final String DBPW ="1234";
	
	//디비 연결 테스트 -> 매서드
	// @Test : 테스트를 수행하는 메서드 위에 작성
	//         jUnit이 실행시에 @Test 발변하면 테스트코드 라고 인식하고 실행
	
	@Test
	public void dbConnectTest() {
		
		try {
			// 1. 드라이버 로드
			Class.forName(DRIVER);
			// 2. 디비 연결
			Connection conn = DriverManager.getConnection(DBURL, DBID, DBPW);
			
			System.out.println(" 디비 연결 성공! ");
			System.out.println(conn);
			
			conn.close();// 자원해제
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 예외 발생여부와 상관없이
			// 반드시 1번 실행
			
			//conn.close(); 자원해제
		}
		
		// try-with 구문 (JDK 1.7 이후)
		// => try()안에 자원해제가 필요한 객체를 구현하는 코드를 작성하면
		//    구문이 끝난후 자동으로 자원해제를 수행함.
		// * AutoClosable 인터페이스를 상속하는 객체만 사용가능
		try(Connection conn = DriverManager.getConnection(DBURL, DBID, DBPW);) {
			// 1. 드라이버 로드
			Class.forName(DRIVER);
			// 2. 디비 연결
						
						
			System.out.println(" 디비 연결 성공! ");
			System.out.println(conn);
			
		} catch(Exception e) {
			
		}
		
	}
	
}
