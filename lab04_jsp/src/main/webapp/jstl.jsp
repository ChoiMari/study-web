<%@ page import="com.itwill.model.Contact"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"
    %>
    
    <!--core말고도 FNT?도 많이 사용한다고.. 나라별 언어 선택.. -->
<%@ taglib prefix="c"  uri="jakarta.tags.core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL</title>
</head>
<body>
    <%@ include file="header.jspf" %>
    
    <main>
        <h1>JSTL(JSP Standard Tag Library)</h1>
        <%-- JSTL 라이브러리 사용하기 
        1. pom.xml 파일에 의존성(dependency)을 추가
            - jakarta.sevlet.jsp.jstl:jakarta.servlet.jsp.jstl-api:3.0.0
            - org.glassfish.web:jakarta.servlet.jsp.jstl:3.0.1
        2. JSTL을 사용하는 JSP 파일에서 <%@ taglib prefix="" uri="" %> 지시문을 설정.
        --%>
        
        
        <%
        String[] sns = {"인*","싸이월드","얼굴책","X"};
        pageContext.setAttribute("sns", sns);
        %>
        <h2>스크립틀릿, 식 사용한 리스트</h2>
        <ul>
        <% for(String s : sns) { %> 
            <li><%= s %></li>
        <% } %>
        </ul>
        
        <h2>JSTL, EL을 사용한 리스트</h2>
        <ul>
        <!-- prefix="c" 를 c로 해서 c:로 시작. 보통 core를 줄여서 c라고 한다고.. -->
            <c:forEach items = "${ sites }" var="s">
                <li>${ s }</li>
            </c:forEach>
        </ul>
        
        <%
        ArrayList<Contact> data = new ArrayList<>();
        for(int i = 1;i <= 5 ; i++ ) {
         data.add(new Contact(i, "name_" + i, "phone_" + i, "email_" + i));
        }
        pageContext.setAttribute("contactList", data);
        %>
        
        <h2>scriptlet, expression 사용한 테이블</h2>
        <table>
            <thead>
                <tr>
                    <th>아이디</th>
                    <th>이름</th>
                    <th>전화번호</th>
                    <th>이메일</th>
                </tr>
            </thead>
            <tbody>
            <% for(Contact c : data) { %>
                <tr>
                    <td><%= c.getId() %></td>
                    <td><%= c.getName() %></td>
                    <td><%= c.getPhone() %></td>
                    <td><%= c.getEmail() %></td>
                </tr>
             <% } %> 
            </tbody>
        </table>
        
        <h2>JSTL, EL 사용한 테이블</h2>
        <table>
            <thead>
                <tr>
                    <th>아이디</th>
                    <th>이름</th>
                    <th>전화번호</th>
                    <th>이메일</th>
                </tr>
            </thead>
            <tbody>
            <!-- 반복문 forEach. -->
            <c:forEach items = "${ contactList }" var="c">
                <tr>
                    <td>${c.id}</td> <!--c.id가 getter메서드 호출인데 저렇게만 쓰면 된다고 함 -->
                    <td>${c.name}</td> <%-- EL은 프로퍼티 이름으로 getter 메서드를 찾음. 그래서 관습을 지켜서 만드는게 중요하다고 함. --%>
                    <td>${c.phone}</td>
                    <td>${c.email}</td>
                </tr>
               
            </c:forEach>
            </tbody>
        </table>        
        
        <h2>URL 태그</h2>
           <a href="result.jsp?username=gu&est&color=crimson">클릭1</a> <!-- &를 문자열로 보내고 싶을때는 이렇게 쓰면 안됨. -->
      
      
      <%-- URL 쿼리스트링의(질의 문자열) 요청 파라미터 값에 &같은 특수 문자 기호가 포함 될 때 이렇게 사용하기!  --%>
      <c:url value="result.jsp" var="url"> <!-- 질의 문자열 전까지의 경로를 적으면 됨. var는 변수이름 -->
        <c:param name="username" value="admin" />
        <c:param name="color" value="crimson" />
      </c:url>
      <a href="${ url }">클릭2</a> <!-- 변수이름 var를 {}안에 써주면 됨. ?와 &는 자기가 알아서 붙인다고 함.-->
        
    </main>
</body>
</html>