<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL</title>
</head>
<body>
    <%@ include file="header.jspf" %>
    
    <main>
        <h1>EL(Expression Language)</h1>
        <%--
        EL: JSP의 expression(<%= ... %>을 대체해서 간단하게 쓰는 문법.
        EL 문법 : ${ 식 }
            -EL은 지시문 <%@ ... %> 안에서는 사용하지 못함.
            -선언문 <%! ... %> 안에서도 사용할 수 없음
            -스크립트릿 <% ... %> 안에서도 사용 불가
            -expression을 대체하는 거니까 식<%= ... %>안에서도 못씀.
            -JSP의 태그들과 같이 섞어서 사용 할 수 없음.
            -JSP태그와 별도로 사용해야 함.
            -JSP 태그들을 제외한 JSP 모든 코드 안에서는 언제든지 사용할 수 있음.
        사용은 주로
        HTML 태그의 컨텐트나 속성값을 사용 할때
        CSS 속성 값을 설정할 때나
        HTML 안에 <script> 태그에 포함된 JavaScript 코드 안에서 사용 하고
        JSTL 안에서 사용한다.
         --%>
         <p><%= 1 + 1 %></p> <%-- JSP expression 태그 사용.브라우저 화면에 보여지는 결과 2 --%>
         <p>${ 1 + 1 }</p> <%-- EL 사용 브라우저 화면에 보여지는 결과 2, 연산식 사용 가능.--%>
         
         <%-- 상태 저장에 사용되는 JSP 내장 객체: --%>
       <%--  o. pageContext: JSP 페이지가 유지되는 동안만 정보 저장.
         o. request: 요청 객체가 유지되는 동안 정보 저장.
         o. session: 세션이 유지되는 동안 정보 저장.
         o. application: 웹 애플리케이션이 동작하는 동안 정보 저장.
         o. 사용 범위: pageContext < request < session < application
         사용 범위는 변수가 메모리에 남아 있는 기간이라고 생각해도 됨.
         o. 상태 저장 메서드 : setAttribute("속성이름", 속성값)
         o. 상태 값을 읽는 메서드 : getAttribute("속성이름")
         내장객체 4개는 전부다 setAttribute 와 getAttribute를 가지고 있다고 한다.
          --%>
          
          <%
          pageContext.setAttribute("id", 1);
          request.setAttribute("id", 2);
          session.setAttribute("id", "admin");
          %>
          
         <h2>JSP Expression을 사용한 상태 정보 읽기</h2>
         <p>
         page: id = <%= pageContext.getAttribute("id") %> <br/>
         request: id = <%= request.getAttribute("id") %> <br/>
         session: id = <%= session.getAttribute("id") %> <br/>
         </p>
         
         <h2>EL을 사용한 상태 정보 읽기</h2>
         <p>
         page id = ${ pageScope.id } <br/>
         request: id = ${ requestScope.id } <br/>
         session: id = ${ sessionScope.id }  
         </p>
         <%--
         EL vs JSP 내장 객체
         o. pageScope - pageContext
         o. requestScope - request
         o. sessionScope - session
         o. applicationScope - application
         
         EL ${ attr }에서 상태 정보를 찾는 순서:
         (1) ${ pageScope.attr }
         (2) ${ requestScope.attr }
         (3) ${ sessionScope.attr }
         (4) ${ applicationScope.attr }
          --%>
          <p>EL: id = ${ id }</p> <%-- ${pageScope.id}와 동일 --%>
          
          <% request.setAttribute("username", "scott"); %>
          <p>EL: username = ${ username }</p>
          <%-- ${ requestScope.username }와 동일 --%> 
          
          <h2>EL 삼항 연산자</h2>
          <% pageContext.setAttribute("number", 120); %>
          <p>${ number } = ${(number % 2 == 1) ? '홀수' : '짝수' }</p>
          
          <% session.setAttribute("logInUser","admin"); %>
          <p>${ (logInUser != null) ? '안녕하세요,'  : '로그인 하세요.' } ${ logInUser }!</p>
                  
    </main>
</body>
</html>