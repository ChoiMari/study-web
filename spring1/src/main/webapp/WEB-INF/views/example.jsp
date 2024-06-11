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
<title>Sping1</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
 rel="stylesheet" 
 integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
 crossorigin="anonymous">
 
 <!-- integrity 보안 -->
 
</head>

<body>
    <header>
        <h1>Example 페이지</h1>
    </header>

    <nav>
        <ul>
            <li>
                <c:url var="homePage" value="/" /> 
                <a href="${homePage}">홈페이지</a>
            </li>
        </ul>
    </nav>

    <main>
        <section>
            <h2>GET 방식 요청</h2>
            <c:url var="ex1" value="/ex1" />
            <form method="get" action="${ex1}">
                <input type="text" name="username" placeholder="이름 입력" /> 
                <!-- name속성주면 리퀘스트 파라미터로 찾을 수 있음. placeholder는 사용자에게 입력에 힌트주는것 -->
                <input type="number" name="age" placeholder="나이 입력"/>
                <input type="submit" value="제출" />
                <!-- 제출 누르면 value="/ex1" 에서 설정한 컨텍스트루트/ex1?쿼리스트링 url로 이동됨 -->
            </form>
        </section>
        <section>
            <h2>POST 방식 요청</h2>
            <c:url var="ex2" value="/ex2" />
            <form method="post" action="${ex2}"> <%-- method="post" post방식 요청이라는 뜻.action="${ex2}에는   <c:url 태그의 value="/ex2"가 들어감 /> --%>
                <input type="text" name="username" placeholder="이름 입력" /> 
                <!-- name속성주면 리퀘스트 파라미터로 찾을 수 있음. placeholder는 사용자에게 입력에 힌트주는것 -->
                <input type="number" name="age" placeholder="나이 입력"/>
                <input type="submit" value="제출" />
                <!-- 제출 누르면 value="/ex1" 에서 설정한 컨텍스트루트/ex1?쿼리스트링 url로 이동됨 -->
            </form>            
        </section>
    </main>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
    integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
    crossorigin="anonymous">
    </script>
</body>
</html>