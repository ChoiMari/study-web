package com.itwill.spring2.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.spring2.dto.PostCreateDto;
import com.itwill.spring2.dto.PostListDto;
import com.itwill.spring2.dto.PostSearchDto;
import com.itwill.spring2.dto.PostUpdateDto;
import com.itwill.spring2.repository.Post;
import com.itwill.spring2.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor // -> final필드로 선언된 필드들만 초기화 하는 생성자 만들어주는 애너테이션
@Slf4j
@Controller
@RequestMapping("/post") // ->PostController 클래스의 모든 컨트롤러 메서드의 메핑 주소는 "/post"로 시작.
//->이 클래스에서 만드는 모든 메서드는 /post로 시작하는 주소로 매핑됨. 그래서 매핑할 때 앞에 /post를 생략해도 됨.
//-> /post요청주소를 처리하는 클래스로 만듬
//->get방식 post방식 둘 다 처리 가능해야 되기 때문에 클래스 앞에는 그냥 @RequestMapping만 쓰는것.
//-> 클래스 안에 선언하는 메서드에서 get/post방식 구분해서 매핑하기
public class PostController {
	private final PostService postService; // 생성자에 의한 의존성 주입

	@GetMapping("/list") // @RequestMapping("/post") 를 클래스 앞에 붙이지 않았다면 원래는 @GetMapping("/post/list")라고 써야
							// 되었음.
	public void list(Model model) { // 뷰의 경로 /WEB-INF/views/post/list.jsp
		// [접두사 : /WEB-INF/views/] post/list [접미사 : .jsp]
		log.debug("list()");
		// -> 메서드가 void인 경우에는 요청 주소가 뷰의 이름이 됨. 요청 주소로 뷰의 이름(.jsp)을 찾음.
		// 서비스 컴포넌트의 메서드를 호출해서 포스트 목록을 읽어옴-> 읽어온 목록을 뷰에 전달 함
		// ->이걸하기 위해서 메서드에 Model model 파라미터 선언함 Model은 인터페이스라고 함

		// 서비스 컴포넌트의 메서드 호출, 포스트 목록을 읽어옴=> 뷰에 전달
		// List<Post> list = postService.read();
		List<PostListDto> list = postService.read();
		model.addAttribute("posts", list); // ->list.jsp파일의 <c:forEach items="${posts}" var="p"> 태그에 items="${posts}"
											// 부분에 list
		// 뷰(list.jsp)에서 "posts"로 지정된 list를 사용. list를 posts(뷰의 items="${posts}")로 추가해줌
		// 메서드 리턴타입이 void라서 요청주소가 뷰(jsp)의 이름이 됨. 요청주소로 뷰의 이름을 찾는다. list.jsp파일 만들어야 브라우저
		// 화면에 그려짐

	}

//	//: 과제-상세 보기
//	@GetMapping("/details") 
//	public void detailsReadById(@RequestParam(name = "id") Integer id, Model model) { //@RequestParam Integer id 쿼리스트링에서 id값 Integer로 변환해서 가져옴
//		//리턴값 void여서 요청 주소 /post/details.jsp가 뷰의 이름이 됨.
//		log.debug("detailsReadById()");
//		log.debug("id={}",id);
//		Post post = postService.readById(id);//id를 아규먼트로 주어서 해당 id만 select해서 상세보기
//		model.addAttribute("post", post);
//	}
	
//	@GetMapping("/details")
//	public void details(@RequestParam(name = "id") Integer id, Model model) {
//		log.debug("details(id={})", id);
//
//		Post post = postService.read(id);
//		model.addAttribute("post", post);
//	}
	
	//상세보기 + 수정하기 2가지 처리하는 메서드
    @GetMapping({ "/details", "/modify" }) //->1개의 메서드(컨트롤러)가 2개의 요청주소를 처리
    //-> GET 방식의 "/post/details", "/post/modify" 2개의 요청을 처리하는 메서드.
    public void details(@RequestParam(name = "id") int id, Model model) {
        log.debug("details(id={})", id);
        
        // 서비스 컴포넌트의 메서드를 호출해서 해당 id의 포스트를 검색(select).
        Post post = postService.read(id);
        
        // 뷰에 데이터를 전달하기 위해서 model 객체에 post를 속성으로 추가.
        model.addAttribute("post", post);
        
        // 리턴 타입이 void이므로 뷰의 이름은
        // (1) 요청 주소가 /post/details인 경우 /WEB-INF/views/post/details.jsp
        // (2) 요청 주소가 /post/modify인 경우 /WEB-INF/views/post/modify.jsp
    }

	@GetMapping("/create") // url매핑할 때 /post는 클래스 앞에 잡혀두었음 @RequestMapping("/post")으로
	public void create() {
		log.debug("create() GET");
	}

//    @PostMapping("/create")
//    public String create(@RequestParam(name = "title") String title, //애너테이션 쓰기 귀찮을때 DTO쓴다고
//    		@RequestParam(name = "content") String content,
//    		@RequestParam(name = "author") String author
//    		) {
//    	log.debug("POST: create(title={}, content={}, author={}", title,content,author);
//    	return "";
//    }

	@PostMapping("/create")
	public String create(PostCreateDto dto) { // 디스패쳐 서블릿이 이 안에 다가 클라이언트가 입력한 값 채워준다고함
		log.debug("POST: create(dto={})", dto);

		// 서비스 컴포넌트의 메서드를 호출해서 데이터 베이스에 새 글을 저장.
		postService.create(dto);

		return "redirect:/post/list"; // 포스트 목록 페이지로 리다이렉트

	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam(name = "id") int id){//삭제하고 리다이렉트(리스트로) 하려고 String으로 리턴함
		log.debug("delete(id={})",id);
		//서비스 컴포넌트의 메서드를 호출해서 데이터 베이스의 테이블에서 해당 아이디의 글을 삭제함
		postService.delete(id);
		//포스트 목록(post/list로 리다이렉트.url이동함)
		return "redirect:/post/list";
	}

	@PostMapping("/update")
	public String update(PostUpdateDto dto) {
		log.debug("POST: update(dto={})", dto);
		
		//서비스 컴포넌트의 메서드를 호출해서 데이터베이스 테이블 업데이트를 수행
		postService.update(dto);
		
		//해당 글번호(id)의 상세보기 페이지로 리다이렉트
		return "redirect:/post/details?id=" + dto.getId(); //상세보기 쿼리스트링 해당 글번호 dto.getId()로 이동
	}
	
	//TODO : 검색 해보기
	@GetMapping("/search")
	public String search(PostSearchDto dto, Model model) {
		log.debug("GET: search(dto={})",dto);
		
		List<PostListDto> list = postService.search(dto);
		
		model.addAttribute("posts", list);
		
		return "post/list";
	}
}
