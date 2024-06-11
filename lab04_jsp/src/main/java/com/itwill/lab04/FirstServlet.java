package com.itwill.lab04;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class FirstServlet
 */
public class FirstServlet extends HttpServlet { //HttpServlet 클래스를 상속하고 있음.  HttpServlet클래스가 부모클래스
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServlet() {
    	System.out.println("FirstServlet 생성...");
       // super(); -> 자동으로 호출해서 딱히 필요 없는 코드라고 함. 
    	//멤버변수(필드) 초기화 할게 없어서 기본생성자 없어도 되는 코드인데 자동으로만들어진 부분이라고
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //WAS(Web Application Server):(=웹서버) 웹 요청(request)/응답(response)을 처리하는 프로그램.
    //doGet():get 방식의 요청이 왔을 때 WAS(웹서버.지금은 톰캣 사용)가 호출하는 메서드.
    //doPost():post 방식의 요청이 왔을 때 WAS가 호출하는 메서드
    // 두 메서드 전부 요청(request)/응답(response)이라는 파라미터를 가지고 있음
    // 파라미터 request : 클라이언트가 서버로 보낸 요청의 정보, 기능들을 갖는 객체.
    // 파라미터 response : 서버가 클라이언트로 보낼 응답의 정보나 기능들을 갖는 객체
    //요청을 보낼때는 request객체가 가지고 있는 메서드를 이용하면 된다고..
    //응답을 보낼때는 response객체가 가지고 있는 메서드를 이용하면 된다고..
    //폼양식을 제출하면 폼안에 있는 input태그들 request라는 객체가 가지고 있다고
    @Override //->애니테이션 붙여도 오류 안남 오버라이드가 맞아서. 부모클래스가 가지고 있는 메서드를 오버라이드.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("FirstServlet::doGet() 호출");
    	response.setContentType("text/html; sharset=UTF-8");
    	
		response.getWriter()
		.append("<!doctype html>")
		.append("<html>")
		.append("<head>")
		.append("<meta charset='UTF-8' />") 
		//HTML은 큰따옴표, 작은 따옴표 구분하지 않아서 둘 다 사용 가능한데, 
		//여기서는 UTF-8을 작은 따옴표로 감쌈. 
		//자바에서는 큰따옴표와 작은 따옴표의 역할이 다르기 때문에.... 
		// 굳이 큰따옴표로 감싸고 싶으면 \"로 쓰라고..
		.append("<title>Servlet</title>")
		.append("</head>")
		.append("<body>")
		.append("<h1>첫번째 Servlet</h1>")
		.append("<a href='/lab04'>인덱스 페이지</a>")
		.append("</body>") 
		.append("</html>");
		//자바 코드 안에 HTML코드를 작성 하는 것. 
	} //-> 부모클래스가 가지고 있는 메서드를 오버라이드 한 것. throws하고 있기 때문에 try - catch필요 없다함.

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 */ 
    @Override //---> 부모클래스의 메서드를 오버라이드. 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("FirstServlet::doPost() 호출");
		doGet(request, response); //doGet메서드 호출하고 있음 doGet이 하고 있는 일과 같은 것이라고.
	}

}
