package com.itwill.lab04;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//서블릿 URL(요청 주소) 매핑 방법:
//1.web.xml(배포 관리자, deployment descriptor)에서 <servlet>,<servlet-mapping>로 설정하기
//2. 서블릿 클래스에서 @WebServlet 애너테이션으로 설정.
// (주의) web.xml 또는 애너테이션 둘 중 하나만 설정.
//서블릿의 이름 설정, 서블릿이 처리하는 URL배열. 1개의 서블릿이 2개 이상의 주소 처리 가능.
@WebServlet(name="secondServlet",urlPatterns= {"/ex2"}) //name은 맘대로
//이름은 secondServlet이고 요청 주소는/ex2라고 설정한 것.
//web.xml에서 설정하지 않을거면 서블릿 클래스 안에서 지금 이렇게 하면 된다고 함.
/**
 * Servlet implementation class SecondServlet
 */
public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SecondServlet::doGet()메서드 호출");
		
		//WAS(웹서버 톰캣)가 클라이언트로 보내는 컨텐트 타입 설정:
		response.setContentType("text/html; charset=UTF-8");
		
		 PrintWriter out = response.getWriter();
		 out.append("<html>")
		 .append("<body>")
		 .append("<h1>두번째 Servlet</h1>")
		 .append("<a href='/lab04'>인덱스 페이지</a>")
		 .append("</body>")
		 .append("</html>");
	}

}
