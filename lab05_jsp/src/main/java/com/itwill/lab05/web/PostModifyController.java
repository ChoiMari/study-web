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

@WebServlet(name="postModifyController", urlPatterns = {"/post/modify"})
public class PostModifyController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(PostModifyController.class);
	
	private final PostService postService = PostService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("doGet()"); 
		
		//쿼리스트링에 포함된 요청 파라미터 id값을 읽음.
		int id = Integer.parseInt(req.getParameter("id"));
		log.debug("id={}",id);
		
		//서비스 계층의 메서드를 호출 해서 수정하기 전의 Post객체를 읽음
		Post post = postService.read(id);
		
		req.setAttribute("post", post);
		
		//뷰로 이동(forward)
		req.getRequestDispatcher("/WEB-INF/views/post/modify.jsp")
			.forward(req, resp);
		
	}

}
