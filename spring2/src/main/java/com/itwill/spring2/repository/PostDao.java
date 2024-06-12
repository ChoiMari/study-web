package com.itwill.spring2.repository;

import java.util.List;

import com.itwill.spring2.dto.PostSearchDto;

public interface PostDao {

	//post-mapper.xml에서 id="selectOrderByIdDesc"인 SQL을 실행하는 메서드
	List<Post> selectOrderByIdDesc(); //-> post-mapper.xml 에서 설정한 id이름과 같은 메서드
	//->posts 테이블 전체 검색.목록보기에 이용 select * from posts order by id desc를 실행하는 메서드(이렇게 인터페이스 안에 선언만 하면 마이바티스가 자동으로 몸체 구현해준다)
	Post selectById(Integer id);
	int insertPost(Post post); 
	//-> insert성공시 1개 행이 업데이트 되었습니다 실패하면 0개 행이 업데이트 되었습니다 라서
	//-> sql insert문의 리턴 타입은 int이고 0또는 1이 리턴됨. 그래서 메서드의 리턴 타입 int로 메서드 선언해놓으면
	//MyBatis가 post-mapper.xml에 작성한 sql문을 읽고 id속성으로 준 이름과 같은 메서드를 이 인터페이스에 선언해놓으면
	//그 id에 해당하는 sql문을 실행하는 메서드를 자동으로 구현해준다.
	
	int updatePost(Post post); //#{}자리로 채워야 할 게 3개 -> Post타입의 객체 1개로 대체
	int deletePost(Integer id); //파라미터 id 1개만 필요함
	
	// 검색하는 sql문을 실행하는 메서드 선언 --sql문은 post-mapper.xml에 설정해놓음. 여기(인터페이스)에 선언만하면 몸체는 mybatis가 구현함
	List<Post> search(PostSearchDto dto);
}
