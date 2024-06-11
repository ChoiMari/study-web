<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cookie</title>
</head>
<body>
    <%@ include file="../../header.jspf" %>
    
    <main>
        <h1>Cookie</h1>
        
        <!-- EL  리퀘스트어쩌고 에서 찾는다고? 모름 -->
        <h2>방문 횟수: ${ visitCount }</h2>
    </main>
</body>
</html>