package com.itwill.spring2.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.spring2.dto.UserCreateDto;
import com.itwill.spring2.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor //-> final 필드만 초기화하는 생성자 -> 의존성 주입
@Controller //rest방식으로 쓸거면 @RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	
	@GetMapping("/signup") // 매핑된 uri는 /user/signup Get 방식의 요청을 처리하는 컨트롤러 메서드
	public void signup() {
		log.debug("GET signup()"); 
	}
	
	@PostMapping("/signup") //POST 방식의 /user/signup 요청을 처리하는 컨트롤러 메서드
	public String signup(UserCreateDto dto) { //오버로딩
		log.debug("POST signup({})",dto);
		userService.create(dto);
		
		return "redirect:/"; //홈페이지로 이동 또는 로그인 페이지로 이동
	}
	
	
	//사용자 아이디 중복체크 REST 컨트롤러
	//리턴값 클라이언트로 전달되는 값임을 알려주어야함. @ResponseBody
	@ResponseBody //-> 메서드의 리턴값이 클라이언트로 전달되는 데이터임을 알려주는 애너테이션.클래스가 @RestController인 경우엔 쓸 필요 없음
	//-> 거기서 만드는 메서드는 전부 클라이언트로 전달되는 값.
	@GetMapping("/checkid")
	public ResponseEntity<String> checkId(@RequestParam(name="userid") String userid) { //jsp(뷰) input의 name속성 준(사용자가 입력한 값) String 타입으로 가져옴
		//스프링프레임워크가 매핑된 uri의 Get방식의 요청이 들어올 때 이 메서드 호출해주면서 아규먼트로 넣어준다.
		//jsp(뷰)에서 name속성 주어야지 찾아올 수 있음.
		log.debug("checkId(userid={})", userid);
		
		boolean result = userService.checkUserid(userid); //리턴값 id가 DB에 중복없으면 true, 있으면 false리턴.
		if(result) {
			return ResponseEntity.ok("Y"); //->중복아이디없음. 사용가능 자바스크립스에서 이용할 수 있도록 에이작스 만들면 된다고 함
		} else {
			return ResponseEntity.ok("N"); //->중복있음. 사용불가 자바스크립스에서 이용할 수 있도록 에이작스 만들면 된다고 함
		}
		 
	}
	
}
