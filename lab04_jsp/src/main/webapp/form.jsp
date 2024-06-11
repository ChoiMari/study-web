<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%><!-- 계속 trim어쩌고 쓰려면 템플릿을 바꿔버리라고함.. 
    파일 만들때 마다 trim어쩌고도 포함되게.. -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>form</title>
</head>
<body>
    <%@ include file= "header.jspf" %>
    
    <main>
        <h1>Form</h1>
        
        <form method="get" action="form_result.jsp">
            <div>
                <input type="text" name="username" placeholder="사용자 이름" 
                required autofocus /> <!-- autofocus는 페이지를 들어가자 마자 자동으로 커서 깜빡거리면서 포커스를 주어라. -->
                <!-- required는 반드시(필수) 입력하게 하는 속성. 입력하지 않으면 제출(submit)이 되지 않음. -->
            </div>
            <div>
                <select name="color">
                    <option value="r">빨강</option>
                    <option value="b">파랑</option>
                    <option value="g">초록</option>
                </select>
            </div>
            <div>
                <input type="submit" value="제출" />
            </div>
        </form>    
    </main>
</body>
</html>