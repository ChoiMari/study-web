package com.itwill.lab05.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "userSignOutController",urlPatterns = {"/user/signout"})
public class UserSignOutController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(UserSignOutController.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("doGet()");
		
		//로그아웃: 
		//(1) 세션에 저장된 signedInUser(로그인정보)를 삭제 
		//(2) 세션 객체를 무효화(삭제) - 세션 삭제
		//(2)만 실행하면 (1)번은 자동으로 실행됨. 세선이 없어졌는데 그 안에 정보가 있을리가 없다.
		//로그아웃은 2만 해도 됨. 그래도 1번을 알면 알아두면 좋다고..
		HttpSession session = req.getSession();//->(1)
		session.removeAttribute("signedInUser"); // 아규먼트 : setAttribute에서 사용한 속성 이름을 준다.
		
		session.invalidate();//(2) 세션 삭제(만료)
		
		//로그아웃 이후에 로그인 페이지로 이동.(하기 나름)
		String url = req.getContextPath() + "/user/signin"; //context root를 리턴하는 메서드 -> req.getContextPath()
		resp.sendRedirect(url);
		
	}
	

}
