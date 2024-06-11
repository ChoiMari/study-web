package com.itwill.spring2.datasource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
		locations = { "file:src/main/webapp/WEB-INF/application-context.xml" } //-> 이 파일 읽으면 bean생성 됨.
		)//->웹서버를 실행하지 않고서도 test하기 위해서 SpringExtension.class한테 리스너 역할을 해서 file:src/main/webapp/WEB-INF/application-context.xml읽어서 단위테스트 해주라 
public class DataSourceTest { 
	
	/*
	 * 의존성 주입(DI: dependency injection): 같은 개념의 용어는 제어의 역전(IoC: Inversion of Control). 객체 생성의 제어권 내가 가지고 있지 않다.
	 * 전통적인 자바 개발 방법에서는 객체를 사용하는 곳에서 객체를 생성하고, 그 기능을 이용하는데,
	 * 스프링 프레임 워크에서는 스프링 컨테이너가 필요한 객체들을 미리 객체를 메모리에 생성해두고,
	 * 객체를 필요로 하는 곳에서는 변수 선언과 애너테이션만 사용하면
	 * 스프링 컨테이너가 관리하는 빈을 필요한 곳에 주입을 해준다. 의존성 주입은 유지보수가 쉽다.다른 데이터베이스로 바꾸어도 xml만 수정해주면 된다고함
	 * 의존성 주입을 위해서 @Autowired 사용함
	 * application-context.xml : 스프링 컨테이너가 생성/관리하는 빈들을 설정하는 파일
	 * 제어권이 역전이 되었다. 굉장히 중요한 개념이라고 함.
	 * 
	 * 스프링 프레임 워크 : model2 MVC(위임 패턴) 아키텍쳐를 제공하고 의존성 주입을 제공하는 프레임 워크
	 */
	//필드변수 선언 
	@Autowired //-> 자동으로 묶어주어라~~~ 생성된 객체를 변수에 다 자동으로 묶어! xml에서 생성된 자바객체를 필수 변수에 넣어준다고 함.
	private HikariDataSource ds;
	//의존성 주입. 제어의 역전
	//-> 스프링 컨테이너. 객체들을 담고있는 박스.
	//application-context.xml에 설정되어있는 bean을 변수에 자동으로 할당해달라는 애너테이션.
	//스프링 컨테이너가 생성,관리하는 빈을 변수에 자동 할당(주입)
	//@Autowired를 사용해서 생성된 객체 받아서 쓰겠다.
	
	
	@Autowired//-> 주입받겠다
	private SqlSessionFactoryBean session;
	
	@Test
	public void test() {
		Assertions.assertNotNull(ds);
		log.debug("ds = {}",ds);
		
		Assertions.assertNotNull(session);//세션이 null이 아님을 주장
		log.debug("session = {}",session);
	}
}
