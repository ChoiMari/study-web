package com.itwill.spring2.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)//스프링 테스트 할때만 넣어주면 된다고함. SpringExtension.class는 스프링 프레임 워크에서 JUnit테스트를 사용할때 쓰는 클래스라고 함.
//Sprint JUnit 테스트를 실행하는 메인 클래스.

@ContextConfiguration(
		locations = { "file:src/main/webapp/WEB-INF/application-context.xml" }
		)//-> 스프링 컨텍스트(환경변수)파일(들)의 경로와 파일 이름을 알려주는것.
public class PostDaoTest {

	//주입 받겠다@Autowired PostDao타입의 객체 주입해주세요
	@Autowired//->이렇게 선언하면 자기가 관리하고 있었던 빈을 넣어준다고함
	private PostDao postDao; //-> 변수를 인터페이스로 선언. 
	
//	@Test
	public void testSelectAll() {
		Assertions.assertNotNull(postDao); //객체 생성되어있다 주장 -->단위 테스트 성공함.
		
		List<Post> list = postDao.selectOrderByIdDesc();
		for(Post p : list) {
			System.out.println("\t" + p);
		}
	}
	
	@Test
  public void testSelectById() {
      Post post1 = postDao.selectById(2); // DB 테이블에 id가 있는 경우
      Assertions.assertNotNull(post1);
      log.debug(post1.toString());
      
      Post post2 = postDao.selectById(1); // DB 테이블에 id가 없는 경우
      Assertions.assertNull(post2);
  }
//	public void testSelectById() {
//		Post post = postDao.selectById(2); //sql문에 where id = ?의 ?값을 아규먼트로 넣음
//		//->DB 테이블에 아규먼트로 준 값에 해당하는 id가 있는 경우
//		Assertions.assertNotNull(post); //null이 아님을 주장
//		log.debug(post.toString());
//		
//		Post post2 = postDao.selectById(100); //DB 테이블에 아규먼트로 준 값에 해당하는 id가 없는 경우
//		Assertions.assertNull(post2);//null임을 주장
//		log.debug(post2.toString());
//	}
	
//	@Test
	public void testInsert() {
		//insert할 데이터
		Post post = Post.builder()
				.title("MyBatis테스트")
				.content("MyBatis-Spring insert 테스트")
				.author("admin")
				.build();
		int result = postDao.insertPost(post);
		Assertions.assertEquals(1, result); // result(postDao.insertPost(post)메서드 리턴값)이 1과 같음을 주장(insert에 성공하면 1리턴)
	}
	
//	@Test 
	public void testUpdate() {
		//DB에 update할 데이터 포스트 객체
		Post post = Post.builder()
				.id(1)
				.title("안눙")
				.content("하이하이")
				.build();
		int result = postDao.updatePost(post);
		Assertions.assertEquals(1, result); //업데이트된 행이 있으면 1리턴. 1과 같은지 주장.
	}
	
//	@Test 
	public void testDelete() {
		int result = postDao.deletePost(1);
		Assertions.assertEquals(1, result); //삭제된 행이 있으면 1리턴. 1과 같다 주장함.
	}
}
