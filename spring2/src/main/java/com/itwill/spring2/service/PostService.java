package com.itwill.spring2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.spring2.dto.PostListDto;
import com.itwill.spring2.repository.Post;
import com.itwill.spring2.repository.PostDao;
import com.itwill.spring2.web.PostController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service //-> 스프링 컨테이너에 서비스 컴포넌트로 등록함. 스프링 컨테이너가 PostService 객체 생성하고 필요한 곳에 넣어줌(의존성 주입인가? 그거 해준다고 )
@RequiredArgsConstructor//-> final 필드들을 초기화하는 아규먼트를 갖는 생성자를 자동으로 만들어 줌
//-> final로 선언된 필드만 초기화하는 생성자. 그냥 필드를 초기화하는 생성자가 아님. 
public class PostService {
//-> 서비스는 Repository(Dao)호출
	
//	private PostDao postDao = new PostDao(); -> 전통적인 개발 방식. 이렇게 해서 postDao. 해서 PostDao에 있는 메서드사용하는데
	
//	@Autowired //->@(애너테이션)을 사용한 의존성 주입
//	private PostDao postDao;
	//이 방법 말고도 의존성 주입의 다른 방법도 있음
	//의존성 주입(DI : Dependency Injection)의 또 다른 방식
	//-> 생성자에 의한 의존성 주입
	//방법 : 1. final 필드 선언 2. final 필드를 초기화 하는 생성자 작성.
//	private final PostDao postDao; //컴파일 에러. 이유: final변수는 반드시 초기화 되어야함. 그자리에서 바로 값을 할당 하거나, 생성자호출해서 초기화
	private final PostDao postDao; //-> 생성자로 초기화. 이렇게 초기화된 변수는 바뀔수 없음 final변수라서->  생성자에 의한 의존성 주입. 
	
//	public PostService(PostDao postDao) { //-> 필수 아규먼트가 필요한 생성자라고 말함. 
//		this.postDao = postDao;
//		근데 코드를 이렇게 작성할 필요 없다고 함. 클래스 앞에 @RequiredArgsConstructor으로 대체.
//	}//-> final필드를 초기화 해주는 생성자.
	
	public List<PostListDto> read(){ //원래는 List<Post>로 선언되었는데 변경함
		log.debug("read()");
		
	//	return postDao.selectOrderByIdDesc(); 
		//PostDao의 메서드 호출 -> mybatis가 mapper.xml 에서 작성해놓은 sql문을 실행하는 메서드를 구현함.
		//->  select * from posts order by id desc sql문장을 실행하는 메서드의 리턴값을(포스트 된 전체목록을)
		// 이 메서드를 호출한 곳으로 리턴해줌.(이 메서드 호출은 포스트 컨트롤러에서 했고 포스트 컨트롤러에서 모델.set애트리뷰트로 뷰에 전달함
		//뷰(jsp)에서 전체 목록을 테이블로 그림
		
		List<Post> list = postDao.selectOrderByIdDesc();
		
//		List<PostListDto> result = new ArrayList<>();
//		for(Post p : list) {
//			result.add(PostListDto.fromEntity(p));
//		}
		
		return list.stream( )//리스트의 스트림. 리스트의 원소들이 하나씩 통과하는 통로->스트림
				.map(PostListDto::fromEntity) //람다 표현식. map((x) -> PostListDto.fromEntity(x))를 간단히 쓴 것. 아규먼트를 전달하면 해야될일 리턴값
				//리스트에서 하나씩 하나씩 통과한 게 x. 스트림에 map이 있는데 Post타입의 객체가 지나감 맵을 통과하면 PostListDto로 만들어지는 것
				.toList();//Post타입의 객체 스트림을 통해 map을 통과하면서 PostListDto타입으로 만들어져서 그걸 리스트로 다시 묶음
//		리턴 값은 
// 		List<PostListDto> result = new ArrayList<>();
//		for(Post p : list) {
//			result.add(PostListDto.fromEntity(p));
//	} 코드와 같은 것.
		
	}
	
	//TODO : 과제 - 상세보기
	 public Post readById(Integer id){
		 log.debug("readById()");
		 Post post = postDao.selectById(id);
		 log.debug("{}",post);
		 
		 return post;
	 }
	
	
}
