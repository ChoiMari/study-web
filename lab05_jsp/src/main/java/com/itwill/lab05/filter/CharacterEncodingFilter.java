package com.itwill.lab05.filter;

import java.io.IOException;

import javax.sound.sampled.AudioFormat.Encoding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.service.PostService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;


public class CharacterEncodingFilter extends HttpFilter {
	
	private static final long serialVersionUID = 1L;
	//로그 출력을 위한 로그 객체
	private static final Logger log = LoggerFactory.getLogger(CharacterEncodingFilter.class);
	
	private String encoding; // 여러가지 인코딩이 바뀔 수 있으니까 그때 그때 바뀌게하는 거라고..? 
	
	//WAS가 Filter 객체를 생성한 후, 필터의 초기화 작업을 하기 위해서 호출 하는 메서드
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//web.xml에서 <filter>의 <param-name> 값을 아규먼트로 전달하면,
		//<filter>의 <param-value> 값을 리턴해줌.
		encoding = filterConfig.getInitParameter("encoding"); //web.xml에 있는 <param-name>encoding</param-name> 이름과 같은걸로 아규먼트로 줘야함
		log.debug("init:encoding={}",encoding);
	}
	
	// 필드 체인(-> 서블릿)으로 진행하기 위해서 WAS가 호출하는 메서드.
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//요청(request) 객체의 문자열 인코딩 타입을(UTF-8)로 설정:
		request.setCharacterEncoding(encoding);
		
		//다음 필터 체인을 진행(->서블릿메서드(doGet이나 doPost) 호출)
		chain.doFilter(request, response);
	}

}
