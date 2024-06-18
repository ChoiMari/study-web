package com.itwill.spring2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller //rest방식으로 쓸거면 @RestController
@RequestMapping("/user")
public class UserController {

	@GetMapping("/signup") // 매핑된 uri는 /user/signup Get 방식의 요청을 처리하는 컨트롤러 메서드
	public void signup() {
		log.debug("GET signup()");
	}
}