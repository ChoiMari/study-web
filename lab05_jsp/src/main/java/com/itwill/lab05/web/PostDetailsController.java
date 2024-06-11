package com.itwill.lab05.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.repository.Post;
import com.itwill.lab05.service.PostService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "postDetailsController", urlPatterns = {"/post/details"}) //쿼리스트링 앞부분까지 URL매핑(?앞부분까지 작성)
public class PostDetailsController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PostDetailsController.class);
	//-> 아규먼트로 주는 클래스 이름이 있어야 이런 클래스에서 출력한 로그다 라고 알수 있음.
	
	private final PostService postService = PostService.INSTANCE;
	
	@Override //링크 클릭은 get방식이여서 doGet메서드 호출됨
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//실행 흐름 알려고 로그 출력
		log.debug("doGet()");
		
		//질의 문자열(쿼리스트링)에 포함된 요청 파라미터 id 값을 읽음.
		int id = Integer.parseInt(req.getParameter("id")); //getParameter는 항상 문자열리턴이라서 정수로 변환함.
		log.debug("id={}",id);//이클립스의 콘솔창에 로그 출력
		
		//서비스 계층의 메서드 호출해서 해당 id의 Post정보를 DB에서 읽음. 그 해당 아이디의 글이 뭐가있느냐 select
		Post post = postService.read(id);
		
		//검색 DB에서 select된 Post 객체를 뷰(jsp)에 전달 . 그래서 뷰에서 HTML 그릴수있게 함
		req.setAttribute("post", post);
		
		//뷰로 이동(forward)
		req.getRequestDispatcher("/WEB-INF/views/post/details.jsp") //->jsp만들면 됨 jsp에서 화면을 그림
			.forward(req, resp);
		
	}
	
	

}
