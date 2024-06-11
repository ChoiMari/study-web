package com.itwill.lab04;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class RedirectServlet
 */
@WebServlet(name="redirectServlet",urlPatterns = {"/ex4"})
public class RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RedirectServlet::doGet() 호출");
		//response.sendRedirect("ex3"); //-> 같은 WAS(웹서버)의 페이지로 이동한 것
		response.sendRedirect("https://www.naver.com");//로컬호스트에서 네이버로 서버자체가 바뀜.
		//-> 다른 웹서버로 페이지 이동하게.
		//최소 요청의 리퀘스트와 리스판스가 이동하는 페이지로 전달되지 않는다.
		//최소 요청 주소가 이동하는 페이지로 바뀜
	}

}
