package com.itwill.lab05.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostTest {
	private static final Logger log = LoggerFactory.getLogger(PostTest.class);
	
	
	private PostDao dao = PostDao.INSTANCE; //싱글턴 객체를 가져옴.

	//JUnit(자바단위테스트) 모듈에서 단위 테스트를 하기 위해서 호출하는 메서드.
	//만드는 법(조건)
	//(1) public void으로 해야하고 (2) 아규먼트를 갖지 않음(파라미터선언이 없어야함).  이거 2개를 지켜주면서 @Test 애너테이션을 써주면 됨. 
	//메서드 이름은 상관없음. 여러개 만드는 것 가능. // @Test 애너테이션만 지워버리면 JUnit모듈은 그 메서드를 실행하지 않음.
	@Test
	public void test() {
		//Post 타입 객체 생성 - Builder 디자인 패턴.
		Post p = Post.builder()
				.title("테스트")
				.author("관리자")
				.content("builder design pattern")
				.id(1)
				.build();
		
		// assertNotNull(arg): arg가 null이 아니면 JUnit 테스트 성공, null이면 테스트 실패
		Assertions.assertNotNull(p); // 전부 static메서드로 되어있어서 객체생성 필요없이 클래스이름.으로 메서드 호출해서 사용가능.
		
		//Assertions.assertNull(arg) : arg가 null이면 단위 테스트 성공, null이 아니면 테스트 실패.
		//Assertions.assertNull(p); // p가 null이어야 성공.
		log.debug("p = {}" , p);
	}
	
	@Test
	public void testSelectDao() { //testPostDao()개명함
		Assertions.assertNotNull(dao);//dao가 null이 아님을 주장.
		//PostDao 타입 객체가 null이 아니면 단위 테스트 성공
		log.debug("dao = {}" , dao);
		
		List<Post> result = dao.select();//PostDao클래스의 select()메서드는 리턴값 무조건 null로 코드를 설정해놓음
		
		//단위 테스트를 하면서 2개의 값(아규먼트로 넣은)이 같으면 단위 테스트가 성공이다라고 주장하는 메서드.
		//(아규먼트로 넣은)두 개의 값이 다르면 단위 테스트는 실패.
		Assertions.assertEquals(3, result.size());//2번째 아규먼트는 자바코드에서 리턴해준 값을 넣음 . 리스트의 사이즈가 3이 되면 좋겠어. 그럼 성공 아니면 실패.
		//오라클DB posts테이블에 저장된 데이터 개수
		
		for (Post p : result) {
			log.debug(p.toString());
		}
		
		//Assertions.assertNull(result);//->이거 뭔지 모름
	}
	
	/////////////////////////////////////////////////////
	
	@Test
	public void testInsert() {
		//TODO : 
		//PostDao.insert 메섣 단위 테스트
		Post post = Post.builder()
				.title("insert 테스트")
				.content("JDBC, Connection Pool test")
				.author("admin")
				.build();
		int result = dao.insert(post); //PostDao의 insert메서드 호출
		Assertions.assertEquals(1, result);
		//assertEquals메서드는 아규먼트로 넣은 값 2개가 서로 같으면 성공. 다르면 실패.
		//-> insert 메서드의 리턴 값(삽입된 행의 개수)가 1이면 단위 테스트 성공
		//result값이 1과 같으면 단위 테스트 성공 (마우스 오른쪽 ->run as -> Junit테스트)
		//1과 다르면 실패.
		//1이면 오라클 DB에 행이 insert 된 것이고 0이면 실패한 것.
	}
	
	////////////////////////////////////////
	
	@Test
	public void testDelete() {
		//TODO:
        // PostDao.delete 메서드 단위 테스트
        int result = dao.delete(21); // id(PK)가 있는 경우
        Assertions.assertEquals(1, result);
        
        result = dao.delete(20); // id(PK)가 없는 경우
        Assertions.assertEquals(0, result);
	}
	
	//////////////////////////////////////////////////
	
	@Test //아이디로 검색하는거 테스트
	public void testSelectById() {
		Post post = dao.select(1); //id = 1(PK)가 테이블에 있는 경우
		Assertions.assertNotNull(post);
		log.debug(post.toString());//디버그 메서드는 String타입으로만 아규먼트 받음
		
		post = dao.select(0); //id=0(PK)가 테이블에 없는 경우
		Assertions.assertNull(post);
		
	}
}
