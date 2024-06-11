package com.itwill.spring2.dto;

import java.time.LocalDateTime;

import com.itwill.spring2.repository.Post;
import com.itwill.spring2.service.PostService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

//DTO(Data Transfer Object) 데이터 전달에 사용하는 객체. 
// 보통 뷰와 컨트롤러 사이에서 또는 컨트롤러와 서비스 사이에서 데이터를 주고 받을 때 사용하는 객체
@Data//->@Getter,@Setter,@ToString,@EqulsAndHachCode,@RequiredArgsConstructor를 합친 것
//@RequiredArgsConstructor(final로 선언된 필드만 초기화 해주는 생성자이지만 final 필드가 클래스 안에 하나도 없는 경우 그냥 기본 생성자 역할 한다고 보면 됨)를
//-> final필드 있을 경우 기본생성자의 역할을 안함. 그래서 그냥 따로 @NoArgsConstructor써주는 것이 낫다

@NoArgsConstructor //-> 기본 생성자 자동으로 만들어주는 애너테이션
@AllArgsConstructor
@Builder //->@AllArgsConstructor 모든 필드를 아규먼트로 갖는 생성자가 있어야 @Builder 빌드 패턴 가능.
public class PostListDto {

	//컨트롤러가 뷰에게 주려고 하는것
	private Integer id;
	private String title;
	private String author;
	private LocalDateTime modifiedTime;
	
	//메서드 설명 : PostListDto 객체를 리턴함.
	//PostListDto객체를 만들어서 리턴해줌
	//객체가 만들어지기 전에 부르는 메서드는 static메서드로 선언되어 있어야..
	//post에서 필요한것만 뽑아서 DTO를 만들어주는 메서드
	//필요한 컬럼들만 갖는 메서드
	public static PostListDto fromEntity(Post post) {
		
		return PostListDto.builder()
				.id(post.getId())
				.title(post.getTitle())
				.author(post.getAuthor())
				.modifiedTime(post.getModifiedTime())
				.build();
	}
	

	
}
