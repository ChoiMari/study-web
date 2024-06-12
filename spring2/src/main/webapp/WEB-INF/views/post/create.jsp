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
<title>Spring2 Lagercy</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
 rel="stylesheet" 
 integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
 crossorigin="anonymous">
 
 <!-- integrity 보안 -->
 
</head>

<body>
    <div class="container-fiuid">
        <c:set var="pageTitle" value="Post Create" />
        <%@ include file="../fragments/header.jspf" %>
        
    <main>
        <div class="mt-2 card">
            <div class="card-header">
                <h2>새 글 작성</h2>
            </div>
            <div class="card-body">
                <c:url var="postCreatePage" value="/post/create" />
                <form method="post" action="${postCreatePage}"> <!--method="post"요청방식 form에서 action속성을
                설정하지 않으면 현재 요청 주소로 다시 요청을 보내게 됨. 요청주소가 같으면 궅이 action줄 필요없다고 줄거면 c:url속성으로 주라고함.
                 -->
                    <div class="mt-2">
                        <input class="form-control" <%--class="form-control" 스타일 준 것 --%>
                            type="text" name="title" placeholder="제목 입력" required autofocus />
                    </div>
                    <div class="mt-2">
                        <textarea class="form-control"
                            rows="15" name="content" placeholder="내용 입력" required></textarea>
                    </div>
                    <div class="mt-2"> <%--class="mt-2" 스타일 줌 --%>
                        <input class="form-control" type="text" name="author" placeholder="작성자" required />
                    </div>
                    <div class="mt-2">
                        <input class="form-control btn btn-outline-success" type="submit" value="저장" /> <!-- form안의 버튼은 input역할 한다고 그래서 버튼으로 만들어도 된다고 함 -->
                    </div>
                </form>
            </div>
        
        </div>
    </main>
    
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
    integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
    crossorigin="anonymous">
    </script>
</body>
</html>