package com.itwill.spring2.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.spring2.dto.PostListDto;
import com.itwill.spring2.repository.Post;
import com.itwill.spring2.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@RequiredArgsConstructor//-> final필드로 선언된 필드들만 초기화 하는 생성자 만들어주는 애너테이션
@Slf4j
@Controller
@RequestMapping("/post") //->PostController 클래스의 모든 컨트롤러 메서드의 메핑 주소는 "/post"로 시작.
//->이 클래스에서 만드는 모든 메서드는 /post로 시작하는 주소로 매핑됨. 그래서 매핑할 때 앞에 /post를 생략해도 됨.
//-> /post요청주소를 처리하는 클래스로 만듬
//->get방식 post방식 둘 다 처리 가능해야 되기 때문에 클래스 앞에는 그냥 @RequestMapping만 쓰는것.
//-> 클래스 안에 선언하는 메서드에서 get/post방식 구분해서 매핑하기
public class PostController {
	private final PostService postService; //생성자에 의한 의존성 주입
	
	@GetMapping("/list") //@RequestMapping("/post") 를 클래스 앞에 붙이지 않았다면 원래는 @GetMapping("/post/list")라고 써야 되었음.
	public void list(Model model) { //뷰의 경로 /WEB-INF/views/post/list.jsp
	//	[접두사 : /WEB-INF/views/] post/list [접미사 : .jsp]
		log.debug("list()");
		//-> 메서드가 void인 경우에는 요청 주소가 뷰의 이름이 됨. 요청 주소로 뷰의 이름(.jsp)을 찾음.
		//서비스 컴포넌트의 메서드를 호출해서 포스트 목록을 읽어옴-> 읽어온 목록을 뷰에 전달 함
		//->이걸하기 위해서 메서드에 Model model 파라미터 선언함 Model은 인터페이스라고 함
		
		//서비스 컴포넌트의 메서드 호출, 포스트 목록을 읽어옴=> 뷰에 전달
	//	List<Post> list = postService.read();
		List<PostListDto> list = postService.read();
		model.addAttribute("posts",list); //->list.jsp파일의 <c:forEach items="${posts}" var="p"> 태그에 items="${posts}" 부분에 list
		// 뷰(list.jsp)에서 "posts"로 지정된 list를 사용. list를 posts(뷰의 items="${posts}")로 추가해줌
		//메서드 리턴타입이 void라서 요청주소가 뷰(jsp)의 이름이 됨. 요청주소로 뷰의 이름을 찾는다. list.jsp파일 만들어야 브라우저 화면에 그려짐
		
	}
	
	//TODO: 과제-상세 보기
	@GetMapping("/details") 
	public void detailsReadById(@RequestParam(name = "id") Integer id, Model model) { //@RequestParam Integer id 쿼리스트링에서 id값 Integer로 변환해서 가져옴
		//리턴값 void여서 요청 주소 /post/details.jsp가 뷰의 이름이 됨.
		log.debug("detailsReadById()");
		log.debug("id={}",id);
		Post post = postService.readById(id);//id를 아규먼트로 주어서 해당 id만 select해서 상세보기
		model.addAttribute("post", post);
	}

}
