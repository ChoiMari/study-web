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
<title>Spring Legeacy 2</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
 rel="stylesheet" 
 integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
 crossorigin="anonymous">
 
 <!-- integrity 보안 -->
 
</head>

<body>
    <div class="container-fluid">
        <c:set var="pageTitle" value="회원가입" /><!-- 페이지 타이틀 -->
        <%@ include file="../fragments/header.jspf" %>
        
        <main> <!-- my-2 위아래 마진 2씩 준다는 의미 -->
            <div class="my-2 card card-body">
                <!-- 회원가입 양식 form -->
                <c:url var="signUpPage" value="/user/signup" />
                <form action="${signUpPage}" method="post">
                    <div class="my-2">
                        <input type="text" class="form-control" id="userid" 
                        name="userid" placeholder="사용자 아이디" required autofocus /> <!--요청 파라미터 이름 name. submit할때 해당 주소를 처리하는 곳(action에 넣은 주소)으로 데이터가 넘어감. 자바스크립트에서 찾으려고 id속성 줌  -->
                    </div>
                    
                    <!-- userid 중복 체크 결과 표시할 영역 -->
                    <div id="checkUseridResult"></div>
                    <!-- 비밀번호 입력 칸 -->
                    <div class="my-2">
                        <input type="password" class="form-control" id="password" name="password" required placeholder="비밀번호"/> <!-- required 필수입력항목 -->
                    </div>
                    <!--TODO 비밀번호 확인 칸 -->
                    <!-- 이메일 칸 -->
                    <div class="my-2">
                        <input type="email" class="form-control" id="email"
                        name="email" placeholder="이메일" required/>
                    </div>
                    <!--회원가입 완료 버튼 폼에 넣은 버튼은 submit 기능있다고..?맞나?-->
                    <div class="my-2"> <!-- disabled속성 버튼 사용할 수 없도록 기능을 막아버림. 부트스트랩 속성. -->
                        <!-- 아이디 중복체크 하고 3개의 칸 비어있지 않을 때 이 막아버린 속성 뺄거라고 함 -->
                        <button class="form-control btn btn-outline-success disabled" id="btnSignUp">완료</button>
                    </div>
                </form>
            </div>
        </main>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
    integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
    crossorigin="anonymous">
    </script>
        <!-- Axio JS 라이브러리 -->
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    
        <!-- 우리가 만드는 JS 파일 -->
    <c:url var="userSignUpJS" value="/js/user_signup.js" />
    <script src="${userSignUpJS}"></script>
    
</body>
</html>