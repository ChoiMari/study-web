package com.itwill.lab04.filter;

import jakarta.servlet.http.HttpFilter;
import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

/**
 * Servlet Filter implementation class FilterEx
 */

//필터 요청 주소 매핑 설정:
//(1) web.xml(deployment desciptor) 파일에서 <filter>, <filter-mapping> 태그에서
//설정하거나 (2) @WebFilter 애너테이션으로 설정.
// web.xml과 애너테이션을 중복으로 설정하면 안됨.
//필터 체인이 있을 때, 필터들이 실행되는 순서는
//web.xml에 설정된 순서대로 실행됨
public class FilterEx extends HttpFilter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public FilterEx() {
        //super(); 자동 호출 된다고 명시적으로 작성할 필요 없다고 삭제 함.
    	System.out.println("FilterEx 생성");
        
    }

	/**
	 * @see Filter#destroy()
	 */
    @Override
	public void destroy() { //소멸시키다... 얘는 객체가 소멸될때 호출되는 메서드(메모리에서 사라질때 호출)
    	// 서버가 종료될 때 호출. 서버 종료-> 메모리에서 사라짐.
    	//할일 없으면 빈껍데기로 두어도 상관 없다고함.
		System.out.println("FilterEx::des");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
    @Override 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
    	System.out.println("FilterEx chain.doFilter() 호출 전");
 //서블릿 호출전에 할일 -> 코드 작성 여기서

		// pass the request along the filter chain
		chain.doFilter(request, response);//-> 이게 없으면 서블릿으로 못간다고 함. 이게 있어야 서블릿까지 가게 된다고
		//이때 doGet/doPost가 끝나면 필터로 다시 들어옴.
		//필터 체인 순서대로 넘겨 준다고. web.xml에서 정의 되어있는 순서대로 필터들이 쭉 진행이 된다고 함.
		//가장 마지막 필터에서 서블릿의 메서드 호출 
		//필터에서 할 수 있는 일중에 하나가 로그인이 되어있는지 아닌지 확인하는 것.
		//필터에서 세션의 로그인 정보가 있는지 검사. 로그인 정보 있으면 doFilter호출
		//  로그인 안되어 있으면 리스판스객체가 가지고 있는 샌드어쩌고 호출하면 된다고...
		// 이코드가 반복되니까 모든 서블릿에서 반복하면 귀찮다고 필터로 만드는 거라고...
		//브라우저 웹사이트에서 로그인 해야지만 특정 페이지를 볼 수 있게 설정....하는 것
		
		
		System.out.println("FilterEx chain.doFilter() 호출 후");
		
		//서블릿 호출 후에 할일  -> 코드 작성 여기서(이 위치에서)
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
    @Override
	public void init(FilterConfig fConfig) throws ServletException { //추상 메서드이기 때문에 필요없어도 껍데기는 있어야..!!
    	//할일 없으면 그냥 빈껍데기로 해놓아도 된다고..
		System.out.println("FilterEx::init() 호출");
	}

}
