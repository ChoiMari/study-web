package com.itwill.lab04.listener;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class ListenerEx
 *
 */
public class ListenerEx implements HttpSessionListener, HttpSessionAttributeListener {
	//HttpSessionAttributeListener 인터 페이스는 디폴트 메서드로만 이루어짐...
	// 추상메서드들만 약속해 놓고 구현해라 하는게 인터페이스의 목적..
	// 그러다가 자바가 버전 업이 되면서 인터페이스도 바디를 가지면 좋겠다 해서 생겨남...
	// 그럼 추상메서드와 아닌것을 구분하려고 인터페이스의 바디를 가진 메서드들을 앞에 디폴트라고 선언해놓음
	//그래서 HttpSessionAttributeListener는 전부 디폴트로 되어있어서 인터페이스 구현 선언해도 반드시 구현하라는 메세지가 나오지 않음.
	

    /**
     * Default constructor. 
     */
    public ListenerEx() {
       System.out.println("ListenerEx 생성자 호출");
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    @Override //HttpSessionListener 인터페이스가 가지고 있는거 오버라이드
    public void sessionCreated(HttpSessionEvent se)  { 
         // 세션이 생성 됐을 때 WAS가 호출하는 메서드
    	//어떤 특정 상황이 발생 했을 때 WAS가 호출할 수 있는 메서드를 만들어주는게 백엔드가 하는 일이라고..
    	System.out.println("세션 생성: " + se.getSession());
    	
    }
    
	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    @Override //HttpSessionListener 인터페이스가 가지고 있는거 오버라이드
    public void sessionDestroyed(HttpSessionEvent se)  { 
         // 세션이 소멸 됐을 때 WAS가 호출하는 메서드.
    	System.out.println("세션 소멸: " + se.getSession());
    }
    
    /**
     * @see HttpSessionListener#attributeAdded(HttpSessionBindingEvent)
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
    	// TODO Auto-generated method stub
    	HttpSessionAttributeListener.super.attributeAdded(se);
    	System.out.println("세션 속성 추가됨 : " + se.getName());
    	//서버가 호출해주는 메서드들의 집합이 리스너. 세션에 애트리뷰트가 추가되었을때 발생..??
    	//이것도 필요한 경우에만...
    }
	
}
