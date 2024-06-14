package com.itwill.spring2.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.itwill.spring2.dto.CommentCreateDto;
import com.itwill.spring2.dto.CommentItemDto;
import com.itwill.spring2.dto.CommentUpdateDto;
import com.itwill.spring2.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor//-> final변수만 초기화하는 생성자

@RestController //rest컨트롤러가 하는 일: 리턴하는 값은 뷰 이름(jsp가)이 아니라, 
//->클라이언트로 직접 전송(응답)되는 데이터를 보내준다. 
//만드는 방법: 클래스에 @RestController라고 붙이거나 클래스에 @컨트롤러라고 붙이고 메서드에 @ResponseBody라고 붙여주면됨

@RequestMapping("/api/comment")
public class CommentRestController { //@RestController가 붙은 Rest컨트롤러 :-> 클라이언트로 직접 전송(응답)되는 데이터를 보내준다. 

	private final CommentService commentService; //-> 생성자에 의한 의존성 주입
	
	@GetMapping("/all/{postId}") //->{postId}주소 때마다 바뀜
	//PathVariable요청 주소에 있는 변수. {postId}에 있는값과 똑같이 써주기
	//@PathVariable 요청주소의 일부가 변수처럼 변할 수 있는 값일 때,
	// 디스패쳐 서블릿이 요청 주소를 분석해서 메서드의 아규먼트로 전달을 해줌.(여기서는 postId을 말함.)
	//@PathVariable을 썼기 때문에 "/all/{postId}"로 매핑 가능.
	public ResponseEntity<List<CommentItemDto>>  getAllCommentByPostId(@PathVariable(name="postId") int postId){ //List<CommentItemDto>타입에서 바꿈
        // @PathVariable: 요청 주소의 일부가 변수처럼 변할 수 있는 값일 때,
        // 디스패쳐 서블릿이 요청 주소를 분석해서 메서드의 아규먼트로 전달.
        // 1. @PathVariable(name = "postId") 처럼 패스 변수의 이름을 명시하거나,
        // 2. 패스 변수의 이름을 명시하지 않고 메서드의 파라미터 이름으로 패스 변수를 찾으려면
        // (Eclipse) 프로젝트 이름 오른쪽 클릭 -> Properties -> Java Compiler ->
        // "Store information about method parameters (usable via reflection)" 항목을 체크.
		log.debug("getAllCommentByPostId(postId={})",postId);
		
		//서비스 컴포넌트의 메서드를 호출해서 해당 포스트의 댓글 목록을 가져옴.
		List<CommentItemDto> list = commentService.readByPostId(postId);
		
		//ResponseEntity<T>
		return ResponseEntity.ok(list);//-> ok: 응답 코드로 200을 보내겠다. 아규먼트(list)로 쓴 것-> 클라이언트로 보내는 데이터. 
		//서버가 클라이언트로 보내는 데이터와 응답코드를 함께 설정할 수 있는 타입
		//-> 200ok 응답코드와 함께 list를 클라이언트에게 전송.
		//->jackson-databind 라이브러리가 자바 객체를 JSON 문자열로 변환.
		
		//-> HTML(뷰)가 리턴된게 아님. 브라우저로 클라이언트로 직접 전송(응답)되는 데이터를 보내줌
        // REST 컨트롤러 메서드가 자바 객체를 리턴하면
        // jackson-databind 라이브러리가 자바 객체를 JSON 문자열로 변환을 담당하고,
        // JSON 문자열이 클라이언트로 전송(응답)됨.
        // jackson-databind 라이브러리의 역할:
        //   1. 직렬화(serialization): 자바 객체 -> JSON
        //   2. 역직렬화(de-serialization): JSON -> 자바 객체
        // jackson-databind 라이브러리에서 
        // Java 8 이후에 생긴 날짜/시간 타입(LocalDate, LocalDateTime)을 JSON으로 변환하기 위해서는
        // jackson-datatype-jsr310 모듈이 필요함.
	}
	
	//댓글 1개 보기
	@GetMapping("/{id}") //-> /api/comment/{id}
	public ResponseEntity<CommentItemDto> getReplyById(@PathVariable int id){
		log.debug("getReplyById(id={})",id);
		CommentItemDto dto = commentService.readById(id); // id로 댓글 1개보는 메서드
		
		return ResponseEntity.ok(dto);
		
	}
	
	@PostMapping //->/api/comment 뒤에 매핑 주소 없는 경우 그냥 애너테이션만 붙이고 끝내면 된다고
	public ResponseEntity<Integer> registerComment(@RequestBody CommentCreateDto dto){
		//@RequestBody : Ajax 요청의 요청 패킷 바디(몸통,내부){}에 포함된 데이터를 읽어서 자바 객체로 변환.
		//get방식의 쿼리스트링도 아니고 , 포스트 방식에서 form데이터도 아니고
		//에이작스 요청에서 패킷의 바디에 포함되어져있는 데이터를 읽을때 사용되는 것이 @RequestBody
		log.debug("registerComment({})",dto);
		
		int result = commentService.create(dto);
		
		return ResponseEntity.ok(result);
	}
	
//	@PutMapping("/{id}")
//	public ResponseEntity<Integer> updateComment(@RequestBody CommentUpdateDto dto){
//		
//		log.debug("updateComment({})",dto);
//		int result = commentService.update(dto);
//		
//		return ResponseEntity.ok(result);
//	}
	
    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateComment(@PathVariable int id,
            @RequestBody CommentUpdateDto dto) {
        log.debug("updateComment(id={}, dto={})", id, dto);
        
        dto.setId(id);
        int result = commentService.update(dto);
        
        return ResponseEntity.ok(result);
    }
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> deleteComment(@PathVariable int id){
		log.debug("deleteComment(id={})", id);
		int result = commentService.deleteById(id);
		return ResponseEntity.ok(result);
	}
}
