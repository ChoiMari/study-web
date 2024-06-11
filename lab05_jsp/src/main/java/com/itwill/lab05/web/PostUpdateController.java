package com.itwill.lab05.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.repository.Post;
import com.itwill.lab05.repository.PostDao;
import com.itwill.lab05.service.PostService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//context root/post/update url요청 주소를 처리하는 서블릿 매핑
@WebServlet(name = "postUpdateController", urlPatterns = {"/post/update"})
public class PostUpdateController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PostUpdateController.class);
	
	private final PostService postService = PostService.INSTANCE;
//	private final PostDao postDao= PostDao.INSTANCE;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("doPost()");
		//폼에서 제출된 글 번호, 제목, 내용을 요청 파라미터에서 읽음.
		int id = Integer.parseInt(req.getParameter("id"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		Post post = Post.builder()
				.id(id)
				.title(title)
				.content(content)
				.build();
		log.debug("{}",post);
		
		//서비스 계층의 메서드를 호출해서 글의 제목과 내용을 업데이트
		 postService.update(post);
		
		// 상세보기 화면으로 이동.(redirect) // req.getContextPath()는 context root
		String url = req.getContextPath() + "/post/details?id=" + id; //id 글번호. ?쿼리스트링으로 //내용이 업데이트 되면
		//url 이 주소로 이동됨.  resp.sendRedirect(url);
		log.debug("redirect: {}",url);
		resp.sendRedirect(url);
		
	}

	
}
