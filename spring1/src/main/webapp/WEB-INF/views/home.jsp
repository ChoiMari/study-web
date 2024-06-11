<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" 
    %>
    
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1"><!--initial-scale=1는 브라우저 기본 비율 이용 -->
<title>Insert title here</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
 rel="stylesheet" 
 integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
 crossorigin="anonymous">
 
 <!-- integrity 보안 -->
 
</head>

<body>
    <header>
        <h1>Home</h1>
        <h2>${now}</h2><!-- 애트리뷰트이름을 EL로 사용하면 된다고 함.model.addAttribute("now", now); -->
        <%--방법 1 --%>
        <%-- <img alt="재훈" src="/spring1/images/재훈.jpg" /> 앞에 서버주소와 포트가 생략된 것.같은 서버에서만 동작함 --%>
        <%--src="/spring1/images/재훈.jpg"라고 넣었는데 이렇게 컨텍스트루트 직접쓰는 방법 위험하다고.. 서버에 대한 상대경로--%>
        <%--문제 : 컨텍스트루트 언제든 바꿔버릴수가 있음.. --%>
        
        <%--방법 2 --%> 
       <%-- <img alt="재훈" src="./images/재훈.jpg" /> --%> 
       <%-- 현재 위치(현재 경로 url)에서 상대경로 사용함. 현재 주소에 대한 상대경로 --%>
        <%--http://localhost:8080/spring1경로에 /images/재훈.jpg 붙이겠다는 의미 --%>
        <%--웹에서 absolute path,relative path등이 있는데, --%>
        <%--단점 : 경로 줄지 고민해야 됨. --%>
        
        <%--방법 3 : 위의 방법1,2의 단점을 보완 --%>
        <%--그래서 사용하는 것 ! c:url태그 사용함 --%>
        <c:url var="hi" value="/images/hi.jpg" /> <%--자동으로 컨텍스트루트 붙여주는 태그라고 함 --%>
        <img alt="재훈" src="${hi}" />
       <%-- <img src="http://localhost:8080/spring1/images/재훈.jpg" />--%> <%--절대경로 --%>
       <%--이미지가 근데 한글이름이라서 에러뜨는듯..???재훈.jpg가 %EC%9E%AC%ED%9B%88.jpg로 표시됨. --%>
        <img src="http://localhost:8080/spring1/images/test.jpg" />
        <img alt="브이" src="https://i.pinimg.com/564x/74/bc/8c/74bc8c84fb32be133e9925298251c56d.jpg">
    </header>
    
    <main>
        <h1>Contents</h1>
        <nav>
            <ul>
                <li>
                    <c:url var="exPage" value="/example" /><%--변수이름 var , value= url 경로. 컨텍스트루트/ --%>
                    <a href="${exPage}">컨트롤러 예제</a>
                </li>
                <li>
                    <c:url var="testPage" value="/test" />
                    <a href="${testPage}">테스트 페이지</a>
                </li>
                <li>
                    <c:url var="forwardPage" value="/test2" />
                    <a href="${forwardPage}">포워드</a>
                </li>
                <li>
                    <c:url var="redirectPage" value="/test3" />
                    <a href="${redirectPage}">리다이렉트</a>
                </li>
                <li>
                    <c:url var="rest1" value="/rest1" /> <!--변수 이름 var="rest1"  EL대신 들어갈 값 설정-> value="/rest1"-->
                    <a href="${rest1}">REST 1</a><!-- EL${"var써주면 됨. 그럼 value값이 들어감"} -->
                </li>
                <li>
                    <c:url var="rest2" value="/rest2" />
                    <a href="${rest2}">REST 2</a>
                </li>    
                <li>
                    <c:url var="rest3" value="/rest3" />
                    <a href="${rest3}">REST Controller 3</a>
                </li>
                <li>
                    <c:url var="rest4" value="/rest4" />
                    <a href="${rest4}">REST Controller 4</a>
                </li>                                                               
            </ul>
            
        </nav>
    </main>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
    integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
    crossorigin="anonymous">
    </script>
</body>
</html>