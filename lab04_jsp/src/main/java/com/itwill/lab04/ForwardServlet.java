package com.itwill.lab04;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ForwardServlet
 */
@WebServlet(name = "forwardServlet",urlPatterns = {"/ex3"})
public class ForwardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("ForwardServlet::doGet() 호출");
		
		// WAS(웹애플리케이션 서버. 웹서버. 톰캣)는 요청이 오면 web.xml 또는 @WebServlet 애너테이션 에서
		// 설정된 URL mapping에 따라서 요청을 처리할 수 있는 서블릿 클래스 객체의 메서드(doGet, doPost)를 호출
		// 서블릿에서는 HTML 코드를 작성해서 응답을 보내면 됨.
		// 서블릿에서 HTML을 작성하는 것은 너무 번거로움.
		// 서블릿이 JSP로 요청을 전달하고 JSP가 HTML코드를 작성하는 것이 더 쉽다.
		// 요청이 들어오면 어떻게 전달??
		// 전달 방법 : request(요청)객체가 필요 forward전달하다 라는 메서드. 아규먼트 2개
		// request 와 respons
		//반드시 request, respons 객체를 아규먼트로 주게되어있음
		//톰캣 웹서버가 doGet()메서드를 호출할 때 자기가 가지고 있는 request, respons객체를 아규먼트로 주고
		// 그대로 전달. 전달할 때 사용하는 메서드가 forward메서드
		//getRequestDispatcher메서드에는 아규먼트로 jsp파일 이름을 적으면 됨
		request.getRequestDispatcher("example.jsp").forward(request, response);
		//이동할 페이지를 아규먼트로 줌. 이동시킬때 요청과 응답 그대로 전달함.
		//(웹서버가 goGet메서드를 호출할때 아규먼트로 보내준 요청,응답을 그대로 전달하는 것)
		// "forward" 방식의 웹 페이지 이동:
		// - 같은 WAS(지금은 톰캣. 웹서버)의 같은 웹 애플리케이션 안에서만 페이지를 이동하는 방식.
		// - 최초 요청 주소가 바뀌지 않음.
		// - request, response 객체가 유지됨.
		// - 다른 WAS 또는 다른 웹 애플리케이션의 페이지로는 포워드(forward)할 수 없음.
	}

}
