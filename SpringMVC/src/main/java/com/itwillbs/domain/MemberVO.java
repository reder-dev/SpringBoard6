package com.itwillbs.domain;

import java.sql.Timestamp;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO(Data Transfer Object)
 * V0(Value Object) 
 * 
 * => tbl_number 테이블의 정보를 저장하기위한 객체
 *
 */

//@Data
// => lombok라이브러리를 사용해서
// DTO/VO객체에 자동으로 set/get() 만들어줌
// (코드가 없어도 있는것처럼 동작)
// 키보드 F4번을 클릭해서 확인 할것!

@Data
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@ToString
public class MemberVO {
	
	//@Getter
	private String userid;
	private String userpw;
	private String username;
	private String useremail;
	private Timestamp regdate;
	private Timestamp updatedate;
	
	// alt shift s + r
	
	
	
	
	// alt shift s + s
	
	
	
	
	
	
	
	
	
}
