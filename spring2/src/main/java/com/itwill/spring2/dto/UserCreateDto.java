package com.itwill.spring2.dto;

import com.itwill.spring2.repository.User;

import lombok.Data;

//이 클래스의 목적:
//회원가입 요청에서 요청 파라미터 들을 저장하기 위한 DTO
//회원가입 폼에서 보낸 것만 필드로 선언하겠다
@Data
public class UserCreateDto {
	//주의
	//(회원가입 뷰(jsp)의 form의 input name(요청파라미터)값과 똑같이 필드이름 설정해야 함) 그래야 초기화 할 수 있음
	private String userid;
	private String password;
	private String email;
	
	public User toEntity() {
		return User.builder()
				.userid(userid)
				.password(password)
				.email(email)
				.build();
	}
}
