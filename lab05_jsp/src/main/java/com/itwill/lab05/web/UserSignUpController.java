package com.itwill.lab05.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.repository.User;
import com.itwill.lab05.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//이 서블릿 파일을 url주소에서 context root 다음 urlPatterns = { "/user/signup" }) 쓴 경로와 매핑
@WebServlet(name = "userSignUpController", urlPatterns = { "/user/signup" })
public class UserSignUpController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UserSignUpController.class);
    
    private final UserService userService = UserService.INSTANCE;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        log.debug("doGet()");
        //jsp만들어서 응답보내면 돼. jsp에 전달.
        req.getRequestDispatcher("/WEB-INF/views/user/signup.jsp")
            .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        log.debug("doPost()");
        
        // 회원가입 폼에서 제출된 userid, password, email 요청 파라미터 값을 읽음.
        String userid = req.getParameter("userid");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        User user = User.builder()
                .userid(userid).
                password(password)
                .email(email)
                .build();
        log.debug("{}", user);
        
        // 서비스 계층의 메서드를 호출해서 회원가입.
        userService.signUp(user);
        
        // 홈페이지로 이동(redirect)
        String url = req.getContextPath() + "/";
        resp.sendRedirect(url); //-> PRG방식
    }
    
}
//
//
//
//
//
//
//
//
//
//
//
//
//
////package com.itwill.lab05.web;
////
////import java.io.IOException;
////
////import org.slf4j.Logger;
////import org.slf4j.LoggerFactory;
////
////import com.itwill.lab05.repository.Post;
////import com.itwill.lab05.repository.User;
////import com.itwill.lab05.service.UserService;
////
////import jakarta.servlet.ServletException;
////import jakarta.servlet.annotation.WebServlet;
////import jakarta.servlet.http.HttpServlet;
////import jakarta.servlet.http.HttpServletRequest;
////import jakarta.servlet.http.HttpServletResponse;
////
////@WebServlet(name = "userSignUpControlle", urlPatterns = {"/user/signup"})
////public class UserSignUpController extends HttpServlet{
////
////	private static final long serialVersionUID = 1L;
////
////	private static final Logger log = LoggerFactory.getLogger(UserSignUpController.class);
////
////	private final UserService userService = UserService.INSTANCE;
////
////	//TODO : 회원가입에 필요한 요청 처리 메서드.
////
////	//브라우저에서 회원가입 클릭했을때 호출
////	@Override
////	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////		log.debug("doGet()");
////		//회원가입 하는 폼으로 이동함
////		//회원가입 폼(양식)을 작성하는 뷰(jsp)로 이동(forward).jsp에서 HTML작성하기가 더 쉽기때문에 이동하는 것.
////				req.getRequestDispatcher("/WEB-INF/views/user/signup.jsp")
////					.forward(req, resp);
////
////	}
////
////	@Override
////	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////		log.debug("doPost()");
////		//브라우저 회원가입 form에서 사용자가 작성한 데이터(제목 내용 작성자)를 받는다. 
////				//받으려면 일단 찾아야하는데 찾으려면 요청 req파라미터 사용해야.
////				//요청(request)에 포함된 정보들(id,userid,password,email,point)를 읽음.
////
////			//회원가입 폼에서 작성한 값 가져오는거 맞나???
////				//int id = Integer.parseInt(req.getParameter("id")); //->리턴타입은 항상 String 문자열. 서블릿에서 변환해서 사용해야 함. 
////				//req.getParameter("title"); 아규먼트 "title"라고 한 이유. input태그 속성에서 name을 title로 해놓아서.
////				//req.getParameter(arg 아규먼트); 메서드의 아규먼트는 요청 파라미터 이름(name속성의 값)
////				String userid = req.getParameter("userid");
////				String password = req.getParameter("password");
////				String email = req.getParameter("email");
////			//	int points =Integer.parseInt(req.getParameter("points"));
////
////				//생성자 대신에 빌드패턴으로 초기화.
////				User user = User.builder()
////						//.id(id)
////						.userid(userid)
////						.password(password)
////						.emain(email)
////						//.points(points)
////						.builder();
////
////
////
////				//로그 출력 해봄
////				log.debug("user={}",user);
////
////				//-> 서비스 객체의 메서드를 호출해서 브라우저에서 폼에 사용자가 작성한 작성 내용을 DB에 저장시킨다
////				userService.create(user);
////
////
////				//-> 로그인 페이지로 이동시키는 일 할것임
////				//String url = req.getContextPath() + "/user/signin";
////				
////				//홈으로 이동시킴
////				String url = req.getContextPath();
////				//->req.getContextPath()프로젝트만들때 세팅한 context root를 문자열로 리턴해줌.
////				//resp.sendRedirect("/lab05/post/list"); //주의 context root(/lab05)까지 같이 써주어야 한다고
////				//->String url = req.getContextPath() + "/post/list"; 로 변경.
////				//그래야 만약 context root 이름을 바꿔도 변동이 없이 안전하다고 함.
////				log.debug("redirect: " + url);
////				resp.sendRedirect(url);//-> 응답을 보냄 
////				//->클라이언트는 요청을 다시 보냄 
////				//Post요청 ->Redirect->Get => PRG방식
////
////	}
////
////}