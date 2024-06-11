package com.itwill.lab04.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class CookieController
 */
@WebServlet(name = "cookieController", urlPatterns = {"/cookie" })
public class CookieController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("cookieController::doGet()메서드 호출");
		
		//쿠키 객체 생성
		Cookie cookie = new Cookie("hello","안녕하세요");
		
		//생성된 쿠키객체를 응답(response) 객체에 포함(추가)시킴 -> 뷰로 전달
		response.addCookie(cookie);
		
		int count = 1;//클라이언트가 서버를 방문한 횟수.
		
		//요청에 포함된 쿠키 읽어들이기
		//클라이언트(브라우저)에서 보낸 쿠키를 서버(WAS)에서 확인 하는 방법:
		Cookie[] cookies = request.getCookies();
		for(Cookie c : cookies) {
			//쿠키 이름과 쿠키에 저장된 값 출력
			System.out.println(c.getName() + " = " + c.getValue());
			//JSESSIONID = 47BE37A38ABD7885CAEB30699AA39EA9 이클립스 콘솔창에 뜸.
			
			if(c.getName().equals("cnt")) {
				//"cnt"라고 하는 이름의 쿠키가 있으면 실행되는 조건문.
				// 쿠키에 저장된 값으로 count 변수 변경.
				//getvalue메서드는 무조건 스트링을 리턴해서 변환
				count = Integer.parseInt(c.getValue());
			}
			
		}
		//count 변수의 값을 request 객체의 속성(Attribute)으로 추가함 --> JSP(뷰)로 전달할 수 있음.
		// 2번째 아규먼트에 어떤 타입이라도 다 넣을 수 있음.오브젝트 타입이라서
		request.setAttribute("visitCount", count);//리퀘스트의 애튜리뷰트를 설정하겠다
		
		count++;//방문 횟수 증가
		
		//방문 횟수를 저장한 쿠키를 response 객체에 포함.
		Cookie visitCookie = new Cookie("cnt",String.valueOf(count)); //count는 int타입이여서 String타입에 그냥 넣을 수 없음 변환
		//쿠키는 문자열만 전달 가능. 그래서 변환해서 넣는다.
		
		// 쿠키 만료 기간 설정. 단위 : 초(second).
		visitCookie.setMaxAge(24 * 60 * 60);// 이 쿠키는 하루동안만 쿠키를 유지하겠다 브라우저가 닫혀도. 
		//24시간 1시간은 60분 1분은 60초 //24시간이 지나면 그 쿠키는 만료됨.
		
		//쿠키의 만료기간(maxAge)를 설정하지 않으면, 브라우저가 닫힐 때 쿠키는 만료됨.
		response.addCookie(visitCookie); 
		
		//뷰로 요청을 전달.(/WEB-INF/views/cookie.jsp)경로로 이동해서 전달함.
		request.getRequestDispatcher("/WEB-INF/views/cookie.jsp") // 이 경로는 클라이언트가 브라우저에서 직접접근 불가.
		.forward(request, response); //포워드 방식으로 이동할때는 요청객체와 응답 객체가 그대로 유지된다.
		//-> 서버 재시작하기 ->/WEB-INF/views/경로의 cookie.jsp"만들기
		
		
		
		
	}

}
