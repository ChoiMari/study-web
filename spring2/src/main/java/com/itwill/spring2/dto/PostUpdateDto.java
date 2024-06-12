package com.itwill.spring2.dto;

import com.itwill.spring2.repository.Post;

import lombok.Data;
import lombok.NoArgsConstructor;

//업데이트 요청의 요청 파라미터들을 저장하기 위한 DTO
@Data
@NoArgsConstructor 
public class PostUpdateDto {
	private Integer id;
	private String title;
	private String content;
	
	public Post toEntity() {
		return Post.builder().id(id).title(title).content(content).build();
	}

}
