package com.itwill.spring2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.spring2.dto.CommentCreateDto;
import com.itwill.spring2.dto.CommentItemDto;
import com.itwill.spring2.dto.CommentUpdateDto;
import com.itwill.spring2.repository.Comment;
import com.itwill.spring2.repository.CommentDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service //-> 컨트롤러 클래스에는 @컨트롤러, 서비스클래스에는 @서비스 붙여주어야한다고
@RequiredArgsConstructor //-> final 변수만(아규먼트로 갖는) 초기화하는 생성자
public class CommentService { //-> 스프링 컨테이너가 이 객체 생성
	
	//Dao이용 변수 선언(필드)
	private final CommentDao commentDao; //생성자에 의한 의존성 주입
	
	//댓글 1개 읽어오는 메서드
	public CommentItemDto readById(Integer id) {
		log.debug("readById(id={})",id);
		
		//리포지토리 컴포넌트의 메서드를 호출해서 해당 아이디의 댓글 1개를 검색
		Comment comment = commentDao.selectById(id);
		return CommentItemDto.fromEntity(comment);//-> Comment타입을 CommentItemDto으로 변환해서 리턴함.
	}
	

	//서비스 클래스는 컨트롤러에서 호출
	public List<CommentItemDto> readByPostId(Integer postId){
		
		log.debug("readByPostId(postId={})",postId);
		
		//리포지토리(영속성) 계층의 메서드를 호출해서 comments 테이블의 데이터를 검색.
		List<Comment> list = commentDao.selectByPostId(postId);
		
		//List<Comment>를 List<CommentItemDto>로 변환해서 리턴
		return list.stream()
				.map(CommentItemDto::fromEntity)
				.toList();
//		//for문으로도 사용 가능.
//		List<CommentItemDto> result = new ArrayList<>();
//		for (Comment c : list) {
//			CommentItemDto dto = CommentItemDto.fromEntity(c);
//			result.add(dto);
//		}
//		return result;
	}
	
	public int create(CommentCreateDto dto) {
		log.debug("create({}",dto);
		
		//리포지토리 계층의 메서드를 호출해서 테이블에 comments 테이블에 insert
		int result = commentDao.insert(dto.toEntity()); //->insert할때는 dto타입 아니고 dto타입을 Comment타입으로 변환해서 insert
		//검색해서select 가져올때는 반대로 Comment타입을 dto로 변환해서 화면에 보여지게
		return result;
	}
	
	public int update(CommentUpdateDto dto) {
		
		log.debug("update({})",dto);
		
		//리포지토리 컴포넌트의 메서드를 호출해서 comments 테이블을 업데이트
		int result = commentDao.update(dto.toEntity());
		
		return result;
	}
	
	//삭제는 id 1개만 아규먼트로 필요해서 dto로 안만든다고함
	public int deleteById(Integer id) {
		log.debug("deleteById(id={})",id);
		//리포지토리 컴포넌트의 메서드를 호출해서 DB의 comments에서 댓글 1개를 삭제
		int result = commentDao.deleteById(id);
		
		return result;//-> 컨트롤러에 리턴해줌
	}
}
