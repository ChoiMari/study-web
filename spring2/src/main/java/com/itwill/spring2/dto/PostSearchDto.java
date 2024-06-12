package com.itwill.spring2.dto;



import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PostSearchDto {
	private String category;
	private String keyword;
}
