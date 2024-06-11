<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
   
    trimDirectiveWhitespaces="true" 
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
    <%-- 
    include 지시문:
    여러개의 페이지들이 공통된 컨텐트를 가지고 있는 경우(예: 헤더,푸터)를 포함하는 경우,
    공통된 컨텐트를 별도의 (확장자가) jsp/jspf인 파일로 작성하고,
    공통된 컨텐트가 필요한 페이지(예:main)에서 jsp/jspf 파일을 포함해서 사용.
    include 지시문이 사용된 위치에 jsp/jspf 파일의 내용이 삽입되고,
    하나의 java 클래스 코드(.java 파일)로 변환 되고 컴파일 됨.
    jspf(jsp fragment)는 JSP조각이라고 생각하면 됨. 하나의 완전한 jsp가 아니라
    다른 파일에 포함되는 jsp의 일부분이다라고 표현하기 위해서 확장자를 jspf라고..
    굳이 써주는 이유는 얘는 단독으로 서비스하는 jsp가 아니라 다른 파일에 포함되는 jsp의 조각이라고 명시해 주기 위해서.
    --%>
    <%@ include file = "header.jspf" %>
    
    <main>
        <h1>메인 페이지</h1>
    </main>
    
    <%@ include file = "footer.jsp" %> 
</body>
</html>