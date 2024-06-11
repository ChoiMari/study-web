package com.itwill.spring2.repository;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//MVC아키텍쳐에서 Model 담당 클래스. 데이터베이스의 posts 테이블의 구조를 표현한 모델 클래스.
@Getter @Setter @ToString @EqualsAndHashCode
@NoArgsConstructor //기본생성자 만듬
@AllArgsConstructor //-> 모든 필드를 아규먼트로 갖는 생성자 만듬
@Builder //-> 생성자 대신 초기화하는 빌드패던 만듬.
public class Post {
	private Integer id;
	private String title;
	private String content;
	private String author;
	private LocalDateTime createdTime; //실제 데이터 베이스의 컬럼 이름에는 created_Time 이라고 언더스코어(밑줄 _)이 사용 되었지만 필드 이름은 camel표기사용(camel case) 
	//이것 때문에 post-mapper.xml파일에서 <setting name="mapUnderscoreToCamelCase" value="true" />로 설정함 
	private LocalDateTime modifiedTime;

}
