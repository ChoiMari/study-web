<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session</title>
</head>
<body>
    <%@ include file="../../header.jspf" %>

    <main>
        <h1>Session</h1>
        <h2>안녕, ${ nickname }!</h2> <!-- EL 에서 상태 정보 찾기. 순서 pageScope => requestScope => sessionScope
        => applicationScope EL은 간단하게 변수 이름만 써주면 된다고. pageScope.닉네임이라고 안찾아도 그냥 닉네임만 찾으면
        이 순서대로 찾는다고 함.-->
    </main>
</body>
</html>