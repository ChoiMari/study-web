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
<title>Spring1</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
 rel="stylesheet" 
 integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
 crossorigin="anonymous">
 
 <!-- integrity 보안 -->
 
</head>

<body>
    <header>
        <h1>Ex1.요청 처리 결과 페이지</h1>
    </header>
    
    <nav>
        <ul>
            <li>
                <c:url var="homePage" value="/" />
                <a href="${homePage}">홈 페이지</a>
            </li>
        </ul>
    </nav>
    
    <main>
        <h2>요청 처리 결과</h2>
        <div>${user}</div> <%--user.toString()호출 된것 --%><%-- 모델에 있는 유저 객체 찾음. Example컨트롤러에서 "user"로 설정해서?맞나? --%>
        <div>username: ${user.username}</div><%--user.getUsername()호출 된것 --%> <%--getter메서드 찾음 --%>
        <div>age: ${user.age}</div><%--user.getAge()호출 된것 --%>
    </main>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
    integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
    crossorigin="anonymous">
    </script>
</body>
</html>