<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%-- page 지시문 trimDirectiveWhitespaces 속성:
    jsp 파일이 java 코드로 변환되는 과정에서 JSP 태그들이 빈 줄로 대체되는데,
    빈 줄을 삭제할 것인 지, 아닌 지를 설정하는 속성. 속성값을 true로 바꾸면 삭제 되고
    false는 삭제 안하고 둠. false가 기본값. true로 따로 속성값을 바꿔놓지 않는 빈 줄로...
--%>    
    
    
 <%-- JSP 주석 
 1. Servlet(Server + Applet):  WAS(웹 애플리케이션 서버)에서 실행되는
 요청을 처리하고 응답을 보내는 작은 자바 프로그램.
 (1) 서블릿 객체의 생성과 관리, 서블릿 메서드 호출은 WAS가 담당.
 요청이 오면 그 요청은 WAS (와스)가 받는거고, 웹 서버는 요청에 매핑되어있는 생성된 객체를 찾고
 생성된 객체의 메서드 호출, 그게 WAS가 하는 일
 * 서블릿의 동작 방식.
 (2) 최초요청일 경우 -> WAS가 서블릿 객체 생성(생성자 호출) -> 메서드(doGet,doPost) 호출 - > 응답
    최초 요청 아닐 경우 -> 생성된 서블릿 객체의 메서드 호출해서 -> 응답을 보내게 됨.
 2. JSP(Java/Jakarta Server Page) : 오라클에서는 더이상 jsp 개발하지 않음. 이클립스에서는 프로젝트를 이제 자카르타로 바꿈.
 javax는 오라클만 사용가능해서. 이름을 자카르타로 바꿈.   
 (1) 서블릿은 순수 자바 코드이기 때문에 HTML을 작성하기가 힘듬.
 (2) 그래서 껍데기는 HTML 형식의 파일에서 자바 코드들이 실행 될 수 있도록 만든 문법-> server-side문법
 jsp라고 하는 파일 자체가 클라이언트로 전달 되는게 아님. 클라이언트로 전달되는 건 무조건 HTML
 그럼 jsp가 HTML코드들을 생성해 낼수 있어야 한다고. 그래서 jsp는 서버에서 실행 됨.
 (3)* JSP의 동작 원리 : 
    - 최초 요청인 경우 : jsp 파일을 java 파일로 변환함.(WAS가 함. 톰캣이. java로 변환하는 기능 가지고 있음)
    -> java 코드를 class로 컴파일을 함(이제 실행할수 있는 프로그램이 됨. 그럼 객체 생성 가능) ->객체 생성 
    -> (생성된 객체의) 메서드 호출 -> 응답을 보내 줌.
    
    - 최초 요청이 아닐 경우 :(생성된 객체 에서) 메서드 호출해서 -> 응답 보내 줌
    
    서버 쪽에서 실행 되는 HTML처럼 보이는 자바라고 생각하면 됨.
    
3. JSP의 구성 요소(문법)

(1) 주석(comment) : jsp 파일이 java 코드로 변환 될 때 무시되는 코드.
(2) 지시문(directive) : <%@ ... %> 
    jsp페이지 설정이나 컨텐트 타입 설정, 인코딩, 자바의 import문장(구문) 등의 여러가지 옵션들을 설정하는 
    용도로 사용된다.
(3) 선언문(declaration): <%! ... %>
    jsp 파일이 java 코드로 변환 될 때, 클래스의 필드/메서드 선언 부분이 선언문.
    java코드로 변환 된다는 말은 클래스가 생성 된다는 것.
    쓸일 거의 없다고 함.
(4) 스크립트릿(scriptlet): <% ... %>
    jsp 파일이 java코드로 변환 될 때, jspService(req,resp)라는 메서드 안에 포함 되는 자바 코드.
    메서드 안에서 할 수 있는 코드들인게 중요.
    지역 변수 선언 & 초기화, 메서드 호출, 조건문, 반복문, 등의 로직들을.
    <% ... %>안에서 작성 된다고 보면 됨.
    선생님이 jsp에서 스크립트릿을 제거하는게 목적이라고 함
(5) 식, 표현식(expression): <%= ... %>
    jsp 파일이 java 코드로 변환 될 때, out.write()메서드의 아규먼트로 전달되는 값.
    HTML 코드에 문자열을 삽입하는 것.
    가장 많이 사용.
 
 --%>   
<%! 
/* JSP declaration(선언문)*/
private static final String USER = "scott"; //static 상수(필드)선언

//메서드 선언:
private void printLog(String msg) {
    System.out.print("[intro.jsp]" + msg);   
}
%>
    
<% 
/* scriptlet */
printLog("intro.jsp 실행..."); //선언문에서 만든 메서드 호출. //서버쪽 디버깅 이클립스. 이클립스 콘솔에 출력됨
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
    <nav>
        <a href="/lab04">인덱스 페이지</a>
    </nav>
    <main>
        <h1>JSP 소개</h1>
        
        <% //scriptlet
        LocalDateTime now = LocalDateTime.now(); //지역 변수 선언 & 초기화 (지역변수에 붙일수 있는 수식어. final)
        // 앞에 접근 제한자 수식어 선언 못함.(여기서는 필드 선언 못함. 접근 제한자는 필드에 붙일수 있지만 지역변수엔 불가능)
        String date = String.format("%d-%02d-%02d",now.getYear(),now.getMonthValue(),now.getDayOfMonth());
        String time = String.format("%02d:%02d:%02d",now.getHour(),now.getMinute(),now.getSecond());
        %>
        <h2>날짜: <%= date %></h2> <!-- expression 값이 오는 부분-->
        <h2>시간: <%= time %></h2>
        <h2>USER: <%= USER %></h2>
        
        
        
    </main>
</body>
</html>