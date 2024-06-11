package com.itwill.spring2.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleDriver;


@Slf4j //log4j-slf4j2 라이브러리를 이용한 로그 출력. @Slf4j을 써주면 static final Logger log변수 자동으로 생성됨(outline탭에서 확인가능)
@ExtendWith(SpringExtension.class)//스프링 테스트 할때만 넣어주면 된다고함. SpringExtension.class는 스프링 프레임 워크에서 JUnit테스트를 사용할때 쓰는 클래스라고 함.
//Sprint JUnit 테스트를 실행하는 메인 클래스.

@ContextConfiguration(
		locations = { "file:src/main/webapp/WEB-INF/application-context.xml" }
		)//-> 스프링 컨텍스트(환경변수)파일(들)의 경로와 파일 이름을 알려주는것.

//@ContextConfiguration , @ContextConfiguration  JUnit테스트를 이용하기 위해서 사용함.
//스프링 프레임워크에서 단위테스트를 할 때 이 2개가 필수라고 함.
public class JdbcTest {
	
	@Test //테스트 메서드 - 단위 테스트에서 실행(호출)할 메서드
	public void testIracleJdbc() throws SQLException {
		// JDBC 1. OJDBC 라이브러리를 드라이버 매니저에게 등록한다.
		DriverManager.registerDriver(new OracleDriver());
		log.debug("오라클 JDBC 라이브러리 등록 성공");
		
		// JDBC 2. 커넥션 Connection 객체 생성하기 -> 변수 3개 필요함
		final String url = "jdbc:oracle:thin:@localhost:1521:xe"; //->접속할 url경로
		final String user = "jspstudy"; //-> 오라클 DB에서 만든 계정 user이름
		final String password = "jspstudy"; //->오라클 계정 만들때 설정한 비밀번호
	  //이 3개 있으면 커넥션 객체 생성 가능하다고 함.
		//커넥션 객체 생성함(url,계정 이름,비밀번호)(를 아규먼트로 주어)로 접속해서 커넥션 맺음
		Connection conn = DriverManager.getConnection(url,user,password);
		
		//커넥션이 null이 아님을 주장함
		Assertions.assertNotNull(conn);//conn이 null이 아니면 단위테스트 성공
		log.debug("conn = {}", conn);
		// 커넥션이 제대로 맺어졌는지 확인했고,
		//이제 사용했던 커넥션 제대로 해제 되는지 test
		//JDBC 3 . 사용했던 리소스 해제
		conn.close(); //예외 있으면 실패, 없으면 성공
		log.debug("오라클 연결 해제 성공");
		
	}
	
}
