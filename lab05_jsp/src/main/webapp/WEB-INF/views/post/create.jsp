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
   <div class="container-fluid">
   
    <!-- EL에서 찾을수 있는 변수선언 var변수이름 , 값 , scope . 변수이름 pageTitle로 한 이유 pageTitle로 가기 위해서-->
    <!-- scope은 대소문자 구분해서 전부 소문자로 작성. -->
    <c:set var="pageTitle" value="New Post" scope="page"/>
    <%@ include file = "../fragments/header.jspf" %>  <!-- 올라가겠다는 것. -->
   </div>
    
    <main>
    <!-- 카드만들기 class이름들은 부트스트랩 문서에서 찾은 것 -->
        <div class="mt-2 card">
            <div class="card-header">
                <h2>새 글 작성</h2>
            </div>
            <div class="card-body">
                <!-- form 만들기 post방식 action=""은 주소?맞나? 모름..-->
                <c:url var="newPostPage" value="/post/create"/>
                <form method="post" action="${newPostPage}">
                    <div class = "mt-2">
                    <input class="form-control" type="text" name="title" placeholder="제목" 
                    required autofocus/> <!-- required : 필수 입력 , autofocus : 커서 깜박깜박 설정하는 것  -->
                </div>
                <!-- textarea rows="5" 5줄이 기본으로 보이고 넘어가면 스크롤처리 -->
                <div class="mt-2">    
                    <textarea class="form-control" rows="5" name="content" placeholder="내용" required></textarea>
                </div>
                <div class="mt-2 d-none"><!--태그 속성에서 name은 posts 클래스(모델)에 있는 필드이름을 쓰자. 그럼 찾기도 쉽다고 -->
                    <input class="form-control"  type = "text" name="author"  value="${signedInUser}" readonly />
                    <!-- placeholder="작성자" required> -->
                </div>
                <div class="mt-2"> <!-- btn-success는 초록색 버튼. 버튼에 초록색을 준 것. btn-outline-success는 테두리만 초록색 마우스 올리면 전체 초록 -->
                    <input class="form-control btn btn-outline-success" type="submit" value="저장" />
                </div>
                </form>
                </div>
        </div>
    </main>
    
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
    integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
    crossorigin="anonymous">
    </script>
</body>
</html>