package com.itwill.lab04.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.itwill.model.Contact;

/**
 * Servlet implementation class ContactController
 */
@WebServlet(name = "contactController", urlPatterns = {"/mvc"})
public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ContactController::doGet() 호출"); //흐름 보기위한 로그 출력. doGet메서드 호출 되었는지 확인하려고
		
		//컨트롤러에 뷰(views폴더의-> JSP파일. contact.jsp)로 이동(forward이용해서 전달)
		//여기에서 HTML만드려면 out변수로 프린트 하기 번거롭다고 jsp로 이동(전달)시킨다고
		request.getRequestDispatcher("/WEB-INF/views/contact.jsp") //파일 만든다고 함. 아규먼트로 넣은 해당 경로에.
		.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ContactController::doPost() 호출");
		//흐름 보기위한 로그 출력. doPost()메서드 호출 되었는지 확인하려고
		
		//요청 파라미터 값 읽음 request.getParameter메서드 이용 
		//contact.jsp 파일의 input태그 name 속성에서 작성했던 이름 사용. 
		int id = Integer.parseInt(request.getParameter("id"));//-> 클라이언트가 POST방식으로 보낸 요청 파라미터 값을 읽음.
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		//제대로 읽었는지 확인 위해서 로그 출력해보는 것
		System.out.printf("id=%d, name=%s, phone=%s, email=%s\n"
				,id,name,phone,email);
		
		//모델 패키지에 있는 Contact 객체 생성.
		//요청 파라미터 값들을 이용해서 서비스에 필요한 작업	
		//연락처를 DB에 저장하겠다 하면 여기서 하면 된다고(컨트롤러에서)
		Contact c = new Contact(id,name,phone,email);
		System.out.println(c);
		//그런 작업들을 다 마쳤다고 생각하고 그럼 클라이언트에게 HTML을 보여줄건데
		//여기서는 main페이지로 가보자.
		//인덱스 페이지로 리다이렉트(redirect) 해보기
		response.sendRedirect("/lab04");
		//PRG(Post 요청- Redirect - get방식 요청)패턴
	}

}
