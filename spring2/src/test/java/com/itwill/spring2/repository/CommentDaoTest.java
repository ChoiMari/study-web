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
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
		locations = { "file:src/main/webapp/WEB-INF/application-context.xml" }
		)
public class CommentDaoTest {
	
	@Autowired //의존성 주입-스프링 컨테이너가 관리하는 빈(객체)을 주입받음. 
	//(final을 쓴 의존성 주입 방법은 여기서 사용 못한다고 함.)
	private CommentDao commentDao;
	
//	@Test
	public void testSelect() {
		Assertions.assertNotNull(commentDao);//->null이 아님을 주장. 객체 주입 잘 받았는지 확인. 
		List<Comment> list = commentDao.selectByPostId(74);
		for (Comment c : list) {
			log.debug(c.toString());
		}
	}
	
//	@Test
	public void testInsert() {
		Comment comment = Comment.builder().postId(74).username("호호").ctext("푸하하").build();
		int result = commentDao.insert(comment);
		Assertions.assertEquals(1, result);
	}

//	@Test
	public void testUpdate() {
		Comment comment = Comment.builder().ctext("앙뇽").id(7).build();
		int result = commentDao.update(comment);
		Assertions.assertEquals(1, result);
	}
	
//	@Test
	public void testDeleteById() {
		
		int result = commentDao.deleteById(7);
		Assertions.assertEquals(1, result);
	}
	
//	@Test
	public void testDeleteByPostId() {
		
		int result = commentDao.deleteByPostId(74);
		Assertions.assertEquals(0, result);
	}
	
//	@Test
	public void testSelectCommentCount() {
		int result = commentDao.selectCommentCount(74);
		log.debug("result={}",result);
		//테이블에 댓글이 22개 있는 경우
		Assertions.assertEquals(22, result); //결과가(댓글 수가) 22와 같음을 주장
		//콘솔 창 출력 -> Total: 1 행의 갯수 1개 나왔다.
	}
	
	@Test
	public void testSelectById() {
		//테이블에 아이디(PK)가 있는 경우
		Comment comment1 = commentDao.selectById(9);
		log.debug(comment1.toString());
		Assertions.assertNotNull(comment1); //댓글9번이 null이 아님을 주장
		
		//테이블에 댓글 아이디가 없는 경우
		Comment comment2 = commentDao.selectById(1);
		Assertions.assertNull(comment2);
	}

	
}
