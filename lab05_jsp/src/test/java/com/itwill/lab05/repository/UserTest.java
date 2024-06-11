package com.itwill.lab05.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserTest {
	private static final Logger log = LoggerFactory.getLogger(UserTest.class);
	
	private final UserDao userDao = UserDao.INSTANCE;
	
	//로그인 체크
	@Test //조건 public void , 파라미터 선언 X
	public void testSignIn() {
		//userid와 password가 모두 일치하는 경우(DB데이터에 있는걸로 테스트에 사용하라고 함): 
		User test = User.builder().userid("admin").password("admin").build();//로그인 시도하는 유저
		User user = userDao.selectByUseridAndPassword(test);
		
		//주장 유저가 null이 아님을 주장
		Assertions.assertNotNull(user);
		
		//userid 또는 password가 일치하지 않는 경우:
		User test2 = User.builder()
				.userid("test").password("1234").build();
		User user2 = userDao.selectByUseridAndPassword(test2);
		
		//유저가 null임을 주장
		Assertions.assertNull(user2); //-> 테스트 해보고 원하는 대로 되면 서비스
	}
}
