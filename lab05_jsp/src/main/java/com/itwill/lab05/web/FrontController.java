package com.itwill.lab05.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class FrontServlet
 */

@WebServlet(name="frontController ", urlPatterns= { "" })
//context root(http://localhost::8080/lab05로 들어오는 요청을 처리하는 서블릿.context root뒤에 오는게 없으면 비어있는 문자열로 하라고?
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(FrontController.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FrontController#doGet");
		//log.debug("doGet");
		//log.info("doGet");
		//log.warn("doGet");
		//log.error("doGet");
		log.trace("doGet"); //->분명 코드는 있는데 출력 안되는 것이 정상. 
		//log4j2.xml 에서  <Root level="debug" additivity="false">를
		// <Root level="trace" additivity="false">로 바꾸면 출력됨.
		//14:15:35.097 TRACE [com.itwill.lab05.web.FrontController    ] doGet 이라고..
		//에러들을 다 잡고 디버깅도 끝나고 필요없는 로그들은 
		//log4j2.xml에서 <Root level="error" additivity="false"> 로 바꿔주면 된다고...
		
		
		
		request.getRequestDispatcher("/WEB-INF/views/home.jsp") //접근 못하도록 WEB-INF 폴더 밑에 만듬.
		.forward(request, response);
	}

}
