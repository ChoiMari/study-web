package com.itwill.spring2.dto;

import com.itwill.spring2.repository.Post;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class PostCreateDto { // 새 글 작성에서 요청 파라미터들을 저장하기 위한 DTO
	//필드 이름을 요청 파라미터 이름과 같게 선언하고, 기본 생성자와 setter까지 만들면 @Data(final필드 없으면 기본생성자 대신함),@NoArgsConstructor 로 만듬
	//디스패쳐서블릿에서 리퀘스트 파라미터 분석해서 기본생성자로 setter메서드 사용해서 필드에 넣어준다고함
	private String title;
	private String content;
	private String author;
	
	//DTO를 Post타입으로 변환.//->포스트 서비스에서 호출함
	//static일 필요 없다함->DTO가 만들어져있는 상태에서 Dto가 가지고 있는 필드를 사용할거라서.
	public Post toEntity(){ //-> 이 메서드를 호출하면 Post타입의 객체 만들어주고 리턴.
		return Post.builder().title(title).content(content).author(author).build();
	}
}
