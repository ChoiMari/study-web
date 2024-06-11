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

//어떤 요청 주소 처리하는지 설정하는게 @WebServlet 이것도 import필요하다고 함.
//name은 뭐라고 하든지 상관없지만 중요한건 urlPatterns 잘 매핑시켜야!
@WebServlet(name = "postCreateController", urlPatterns = {"/post/create"})
public class PostCreateController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//getLogger에는 클래스이름.class를 아규먼트로 주는 것. 로그선언
	private static final Logger log = LoggerFactory.getLogger(PostCreateController.class);
	
	//객체 가져옴
	private final PostService postService = PostService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("doGet()");
		
		//새 글 작성 폼(양식)을 작성하는 뷰(jsp)로 이동(forward).HTML작성하기가 jsp가 더 쉽기때문에 이동하는 것.
		req.getRequestDispatcher("/WEB-INF/views/post/create.jsp")
			.forward(req, resp);
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		//메서드 언제 호출되는지 보려고 로그 출력해봄
		log.debug("doPost()");
		
		//브라우저 form에서 사용자가 작성한 데이터(제목 내용 작성자)를 받는다. 
		//받으려면 일단 찾아야하는데 찾으려면 요청 req파라미터 사용해야.
		//요청(request)에 포함된 정보들(제목, 내용, 작성자)를 읽음.
		String title = req.getParameter("title"); //->리턴타입은 항상 String 문자열. 서블릿에서 변환해서 사용해야 함. 
		//req.getParameter("title"); 아규먼트 "title"라고 한 이유. input태그 속성에서 name을 title로 해놓아서.
		//req.getParameter(arg 아규먼트); 메서드의 아규먼트는 요청 파라미터 이름(name속성의 값)
		String content = req.getParameter("content");
		String author = req.getParameter("author");
		
		//생성자 대신에 빌드패턴으로 초기화.
		Post post = Post.builder()
				.title(title)
				.content(content)
				.author(author)
				.build();
		
		//로그 출력 해봄
		log.debug("post={}",post);
		
		//-> 서비스 객체의 메서드를 호출해서 브라우저에서 폼에 사용자가 작성한 작성 내용을 DB에 저장시킨다
		postService.create(post);
		
		
		//-> 포스트 목록 페이지로 이동시키는 일 할것임
		String url = req.getContextPath() + "/post/list";
		//->req.getContextPath()프로젝트만들때 세팅한 context root를 문자열로 리턴해줌.
		//resp.sendRedirect("/lab05/post/list"); //주의 context root(/lab05)까지 같이 써주어야 한다고
		//->String url = req.getContextPath() + "/post/list"; 로 변경.
		//그래야 만약 context root 이름을 바꿔도 변동이 없이 안전하다고 함.
		log.debug("redirect: " + url);
		resp.sendRedirect(url);//-> 응답을 보냄 
		//->클라이언트는 요청을 다시 보냄 
		//Post요청 ->Redirect->Get => PRG방식
		
	}
}