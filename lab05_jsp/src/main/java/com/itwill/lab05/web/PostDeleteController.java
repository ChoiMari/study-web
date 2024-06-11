package com.itwill.lab05.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.service.PostService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//브라우저의 url주소와 매핑
@WebServlet(name = "postDeleteController", urlPatterns = {"/post/delete"}) //name은 클래스이름에 앞글자만 소문자로 바꿈.
public class PostDeleteController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PostDeleteController.class);
	
	private final PostService postService = PostService.INSTANCE; 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//특별히 지정해주지 않는 이상 요청의 기본은 doGet.
		log.debug("doGet()");
		
		//url ?뒤의 쿼리 문자열에 포함된 요청 파라미터 id의 값을 읽음.
		int id = Integer.parseInt(req.getParameter("id"));
		log.debug("id={}", id);
		
		//TODO : 서비스 계층의 메서드를 호출해서 글 삭제 서비스를 실행
		postService.delete(id); //-> postService 변수 필드로 선언
		
		//목록 페이지로 이동(redirect 사용).
		//url주소가 바뀔건지. 바뀌면 리다이렉트. 바뀌지 않으면 forward(보통 jsp로 가는 경우)사용
		String url = req.getContextPath() + "/post/list";
		log.debug("redirect to:{}",url);
		resp.sendRedirect(url);
	}

}
