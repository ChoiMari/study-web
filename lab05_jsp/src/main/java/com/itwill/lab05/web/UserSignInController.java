package com.itwill.lab05.web;

import java.io.IOException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.repository.User;
import com.itwill.lab05.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//애너테이션 name은 클래스 이름에 첫글자만 소문자로 바꿈. urlPatterns은 서블릿이 처리하는 요청주소 매핑.
@WebServlet(name = "userSignInController",urlPatterns = {"/user/signin"})
public class UserSignInController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	//로그 출력하기 위한 상수 선언.(아규먼트로 준 값과 같이 찍힘)
	private static final Logger log = LoggerFactory.getLogger(UserSignUpController.class);

	private final UserService userService = UserService.INSTANCE;

	//TODO : 로그인에 필요한 요청 처리 메서드.

	//브라우저에서 로그인 클릭했을때 호출( header.jspf에서 설정한 링크)
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("doGet()");
		//로그인 하는 폼으로 이동함(링크클릭->get방식)
		//로그인 폼(양식)을 작성하는 뷰(jsp)로 이동(forward).jsp에서 HTML작성하기가 더 쉽기때문에 이동하는 것.
				req.getRequestDispatcher("/WEB-INF/views/user/signin.jsp")//jsp에서 HTML코드로 사용자에게 브라우저에 보여줄 것을 그림
					.forward(req, resp); //> 전달 forward한 경로에 jsp파일 만들기
	}

	@Override //폼에서 로그인 버튼 클릭시 필요함. post방식으로 폼에서 작성한 데이터 전달
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("doPost()");
		
		//로그인 화면에서 사용자가 입력(전송)한 userid, password 값을 읽음
		//폼양식 데이터만 읽어들임. 2개의 input태그에서 submit된 값만 get파라미터 가능
		//input태그에 name속성 준 게 2개밖에 없음 -> 그래서 signin.jsp에  <input name="targer" value="${param.target}" readonly />
		//추가함.
		String userid = req.getParameter("userid");
		String password = req.getParameter("password");
		
		//서비스 계층의 메서드를 호출해서 로그인 성공 여부를 판단.
		User user = userService.signIn(userid, password); //->리턴값이 null이아니면 성공이라고 판단. null이면 로그인 실패.
		
		//로그인 성공이면 타겟(target)페이지, 그렇지 않으면 다시 로그인 페이지로 이동:
		String target = req.getParameter("target");
		log.debug("target = {}",target);
		
		if(user != null) { //데이터베이스 users 테이블에서 일치하는 사용자 정보가 있는 경우
			//세션에 로그인 정보 저장.(모든정보 저장할 필요는 없고, 필요한 정보만 저장)
			//가려고 하는 목적지(페이지를 이동)로 이동
			//세션 객체 찾기
			HttpSession session = req.getSession();
			//세션에 저장(세션이 만료되기 전까지는 유지됨). 페이지가 변해도 사용가능.
			session.setAttribute("signedInUser", user.getUserid());
			
			//페이지 이동 시키기.
			//로그인이 끝난 후 가야될 페이지.
			//일단, 나를 나중에 고쳐줘 FIXME: 타겟 목적지(URL)로 이동하는 코드
			//일단 고치기 전까지 홈페이지로 이동//->타겟의 값이 없는 경우
			if(target == null || target.equals("")) { //타겟이 null이거나 비어있는 문자열인 경우 -> 홈페이지로 이동
				String url = req.getContextPath() + "/"; //홈페이지로 이동
				resp.sendRedirect(url); 
			} else {
				resp.sendRedirect(target); //-> 타켓 값이 있으면 실행
			}
			
		}else {//테이블에서 일치하는 사용자 정보가 없는 경우(로그인에 실패했을 경우)아이디랑 비밀번호 틀려서 로그인실패
			//다시 로그인페이지로 가야함. 이동
		//	String url = req.getContextPath() + "/user/signin"; //->get방식이동 FIXME 로그인 페이지로 이동. 
			//근데 코드가 여기서 끝나면.. 원래 가려던 페이지의 정보가 사라져버림.
			//로그인 실패 몇번을 하더라도 로그인 성공 하면 가려던 페이지로 가야함 그래서 수정함.
            String url = req.getContextPath() 
                    + "/user/signin?result=f&target=" 
                    + URLEncoder.encode(target, "UTF-8"); //result=f 실패
			resp.sendRedirect(url);//-> 리다이렉트라고 요청을 주면 그 다음엔 무조건 get방식이라고 함 ->doGet메서드 호출 -> 로그인페이지로 이동
		}
		

	}

}