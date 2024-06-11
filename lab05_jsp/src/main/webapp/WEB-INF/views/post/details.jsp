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
   <div class="container-fluid"> <%--jsp주석 사용해야 HTML에 포함 안됨. --%>
        <c:set var="pageTitle" value="Post Details"/> <%--scope의 기본값이 page여서 생략 가능 --%>
         <%@ include file="../fragments/header.jspf" %>
        <main>
            <div class="mt-2 card">
                <div class="card-header">
                    <h2>포스트 상세보기</h2>
                </div>
                <div class="card-body">
                    <form>
                        <div class="mt-2">
                            <label for="id" class="form-label">번호</label><!--for="id"는 다른 HTML의 id속성값 찾는것 -->
                            <input id="id" class="form-control" type="text" 
                                value="${post.id}" readonly/><!-- 여기에 쓴 id속성 label에서 찾음 -->
                                <!-- readonly는 편집 막음 --><!-- value="${post.id}"은 getter메서드 호출하는 것 EL로 작성 -->
                        </div>
                        <div class="mt-2">
                            <label for="title" class="form-label">제목</label><!--for="id"는 다른 HTML의 id속성값 찾는것 -->
                            <input id="title" class="form-control" type="text" 
                                value="${post.title}" readonly/><!-- 여기에 쓴 id속성 label에서 찾음 -->
                                <!-- readonly는 편집 막음 --><!-- value="${post.id}"은 getter메서드 호출하는 것 EL로 작성 -->
                                <!--label for="title"는 id가 title인 input을 위한 라벨입니다 라는 뜻 -->
                        </div>
                         <div class="mt-2">
                            <label for="content" class="form-label">내용</label><!--for="id"는 다른 HTML의 id속성값 찾는것 -->
                            <textarea id="contect" class="form-control" 
                            rows="5" readonly>${post.content}</textarea> <%--readonly : 편집 막음 --%>
                        </div>
                        <div class="mt-2">
                            <label for="author" class="form-label">작성자</label><!--for="id"는 다른 HTML의 id속성값 찾는것 -->
                            <input id="author" class="form-control" type="text" 
                                value="${post.author}" readonly/><!-- 여기에 쓴 id속성 label에서 찾음 -->
                                <!-- readonly는 편집 막음 --><!-- value="${post.id}"은 getter메서드 호출하는 것 EL로 작성 -->
                                <!--label for="title"는 id가 title인 input을 위한 라벨입니다 라는 뜻 -->
                        </div>
                        <div class="mt-2">
                            <label for="createdTime" class="form-label">작성 시간</label><!--for="id"는 다른 HTML의 id속성값 찾는것 -->
                            <input id="createdTime" class="form-control" type="text" 
                                value="${post.createdTime}" readonly/><!-- 여기에 쓴 id속성 label에서 찾음 -->
                                <!-- readonly는 편집 막음 --><!-- value="${post.id}"은 getter메서드 호출하는 것 EL로 작성 -->
                                <!--label for="title"는 id가 title인 input을 위한 라벨입니다 라는 뜻 -->
                        </div>
                        <div class="mt-2">
                            <label for="modifiedTime" class="form-label">최종 수정 시간</label><!--for="id"는 다른 HTML의 id속성값 찾는것 -->
                            <input id="modifiedTime" class="form-control" type="text" 
                                value="${post.modifiedTime}" readonly/><!-- 여기에 쓴 id속성 label에서 찾음 -->
                                <!-- readonly는 편집 막음 --><!-- value="${post.id}"은 getter메서드 호출하는 것 EL로 작성 -->
                                <!--label for="title"는 id가 title인 input을 위한 라벨입니다 라는 뜻 -->
                                <!-- 서블릿 파일에서 req.setAttribute("post", post); 되어있어야 EL사용가능-->
                        </div>
                    </form>
                </div>
                <c:if test="${post.author eq signedInUser}">
                <%--글 작성자 아이디와 로그인 사용자 아이디가 같으면 수정하기 버튼을 보여줌 --%>
                <%--EL에서는 == 와 eq 같다고 ==써서 비교해도 된다고 함 --%>
                <%--로그인 사용자(세션에 저장)와 글 작성자(post가 가지고있음 포스트객체가 셋에트리뷰트?) --%>
                <%--반복문,조건문 EL알면 jsp작성 할 수 있다고 웬만한거 --%>   
                    <div class="card-footer">
                        <c:url var="postModifyPage" value="/post/modify">
                            <%--질의 문자열을 만들겠다 --%>
                            <c:param name="id" value="${post.id}" />
                        </c:url>
                        <a class="btn btn-outline-primary"
                            href="${postModifyPage}">수정하기</a>
                        <%--class는 부트스트랩 속성주는거라고 생각 --%>
                    </div>
                </c:if>
            </div>
        </main>
   </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
    integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
    crossorigin="anonymous">
    </script>
</body>
</html>