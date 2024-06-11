package com.itwill.lab05.filter;

import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
public class AuthenticationFilter extends HttpFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);
	/**
	 * @see Filter#destroy()
	 */
	
	
	public void destroy() {
		//필터 객체가 소멸 될때 WAS가 호출하는 메서드
		//할일 없다면 껍데기만 두어도 됨
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		//중요하다고
		// place your code here
		//인증이 필요한 요청 주소들(예: 새 글, 상세보기 등) 로그인이 안되어있으면 못보게 하려고 설정하는 것.
		//로그인이 되어야 보여지게 설정.
		//로그인 여부를 확인하고 로그인이 되어있으면 서블릿(컨트롤러)으로 요청을 전달해서 
		//계속 요청을 처리함.
		//로그인이 되어있지 않으면 컨트롤러로 요청을 전달하지 않고 로그인페이지로 이동한다.
		//로그인 성공하면 SignIn컨트롤러(UserSignInController)에서 로그인 성공 후 최초 요청 주소로 이동.
		//서블릿이 가지고 있는 리퀘스트와 필터가 가지고 있는 리퀘스트 차이점.
		//서블릿은 HttpServletRequest 타입, 필터는 ServletRequest 
		//타입이 다르니까 가지고 있는 메서드도 다르다..
		// ServletRequest 클래스가 부모(상위)클래스. HttpServletRequest가 자식. 자식이 더 메서드가 많다고..
		//서블릿 메서드가 다 안보일때는 캐스팅(강제변환) 다형성 하면 된다고...
		//WAS가 request넣어줌 다형성
		HttpServletRequest req = ((HttpServletRequest)request);
		String reqUrl = req.getRequestURL().toString();
		log.debug("request URL = {}", reqUrl);
		
		String reqUri = req.getRequestURI();
		log.debug("request URI = {}", reqUri);
		
		//contextroot 참음
		String contextPath = req.getContextPath();
		log.debug("context path(root) = {}", contextPath);
		
		//쿼리스트링을 찾음(최초 요청주소의 쿼리스트링이라고 함)
		String qs= req.getQueryString();
		log.debug("query string = {}", qs);
		
		String target = ""; //로그인 성공했을 때 이동(redirect)할 요청 주소
		
		//encode메서드 1번째건 없어질수도 있는 메서드라서 아규먼트 2개짜리 메서드로 사용하기.
		// 2번째 넣는 아규먼트는 인코딩 타입넣어주면 됨.
		if(qs == null) { //쿼리스트링이 없는 경우
			target = URLEncoder.encode(reqUrl, "UTF-8"); //인코딩 값 "UTF-8" 특수문자들을 코드값으로 바꿔주기 위해서
		} else { //else -> 최초 요청 주소에 쿼리스트링이 있는 경우(상세보기인 경우 쿼리스트링이 있음)
			target = URLEncoder.encode(reqUrl + "?" + qs , "UTF-8");
		}
		
		log.debug("target = {}" , target);
		
		//세션에 로그인 정보(signedInUser)
		HttpSession session = req.getSession();
		Object signedInUser = session.getAttribute("signedInUser"); //리턴타입 오브젝트라고 함. String이 아니라고
		//다형성. 필요에 따라서 변환해서 사용(casting) 
		
		//값 비교할 필요도 없이 세션에 저장된 signedInUser객체가 있으면 로그인 상태 없으면 로그인 안된 상태라고 함.
		if(signedInUser == null) { //로그인 안 된 상태
			//로그인 안되어있으면 로그인 페이지로 이동 --> 로그인 성공 하면 원래 가려던 경로(페이지)로 이동시킴
			log.debug("로그아웃(NO 로그인)상태 --> 로그인 페이지로 이동시킴 --> 로그인 성공 후 target으로 이동 시킴");
			
			//-> 요청이 왔는데 로그인안했잖아?라고 로그인하려면 여기로 와. target값 붙여서 와 signin의 쿼리스트링 있으면 달고옴. 없으면 그냥 옴.
			String url = req.getContextPath() + "/user/signin?target=" + target;
			
			((HttpServletResponse) response).sendRedirect(url);
			//서블릿리스판스(ServletResponse)는 sendRedirect메서드 가지고 있지 않아서 HttpServletResponse로 변환시킴.
			//ServletResponse가 부모타입 , HttpServletResponse가 자식
		} else { //로그인 상태 ,signedInUser는 로그인 한 아이디
			log.debug("로그인 상태: {}",signedInUser);
			//요청을 계속 처리하겠다 (-> 요청을 그 요청을 처리하는 서블릿으로 전달하겠다)
			//로그인 상태에서는 원래 가려던 경로로 가면 됨
			chain.doFilter(req, response); //-> 이거 1개만 있어야 된다고 함.
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		//WAS가 필터 객체를 생성한 후 호출되는 메서드
		//초기화 작업이 필요하면 여기서 하면 된다고..
	}

}
