package com.itwill.lab04.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class SessionController
 */

@WebServlet(name = "SessionController", urlPatterns= {"/session"})
public class SessionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SessionController::doGet() 호출");
		
		//서버가 저장하는, 클라이언트(브라우저)마다 매핑 되어 있는 세션 객체 찾기.
		HttpSession session = request.getSession();
		System.out.println(session);//-> 세션 객체 프린트 해봄. 이클립스 콘솔에. 브라우저 마다 주소값이 다름.
		// 크롬에 매핑된 세션 객체 만듬. 같은 브라우저에서 요청 들어오면 계속해서 같은 세션 사용되는데
		// 같은 PC사용해도 브라우저가 다르면 거기에 맞는 세션을 다시 만든다고 함.
		// 세션이라는 객체는 클라이언트(브라우저,PC)마다 세션 객체가 만들어짐
		// 만약 1개의 서버에 10명이 동시에 접속하면 세션 객체가(메모리에) 10개가 만들어 진다고
		// 계속 메모리에 남겨둘수 없으니까 세션은 만료기간이 있음(자동으로 지워지는 시간) 기본이 아마 30분이라고...
		// 폰뱅킹 이용하면 자동 연장 안하면 세션 만료되었다고 다시 로그인해야되는데
		// 메모리에 생겨난 세션이 만료가 되서 더이상 없기 때문에
		// 다시 세션 만들어야된다고 다시 요청 보내라는 것.
		// 브라우저가 아니라 이클립스 콘솔에서 세션 보임. 서버 쪽에서 서버 메모리에서 관리하는 정보라서...
		//getSession()메서드를 호출하면 일어나는 일
		// (1) 클라이언트가 JSESSIONID 쿠키를 보낸 경우, 세션 아이디를 사용해서
		// 세션 객체를 찾음.
		// (2) 클라이언트가 JSESSIONID 쿠키를 보내지 않은 경우에는 새로운 세션 객체를 생성하게 됨. 
		//그리고 새로운 세션 객체가 생성 되었기 때문에 응답을 보낼때
		// JSESSIONID라는 쿠키가 클라이언트로 전송됨. 이건 따로 코드로 작성하는 것이 아니라 WAS(톰캣)에서 자동으로
		//이루어져서 신경 쓸 필요 없다고 함
		//
		//세션에다가 어떻게 정보 저장하는지 중요하다고..
		session.setAttribute("nickname", "관리자");// 세션에 저장한 정보.페이지가 바뀌어도 유지가 되는 정보.(새로운 요청에서도 계속 유지가능)
		// 어떤 파일에 저장 되는게 아니고 서버에 켜져있는 동안 서버의 메모리
		//에서만 생성 되었다가 삭제가 되는 거라고..세션의 만료시간이 되었을 때 자동으로..사라진다고...
		// 세션도 만료시간 지정 가능하다고 함.
		session.setMaxInactiveInterval(10); //세션 만료기간 설정. 단위 : 초 (초단위로 설정가능).아규먼트로 초단위로 값을 넣어주면 됨. 10초로 설정
		
		
		//뷰 이동.컨트롤러에서 JSP로 포워드. 이때 저장 가능한게 세션에다 setAttribute할수도 있고(보통 브라우저가 닫히지 전까지 유지되는 정보)
		//로그인 같은건 세션에다가 하나의 페이지에는 리퀘스트에다가 setAttribute하면 된다고..
		request.getRequestDispatcher("/WEB-INF/views/session.jsp")
		.forward(request, response);
	}

}
