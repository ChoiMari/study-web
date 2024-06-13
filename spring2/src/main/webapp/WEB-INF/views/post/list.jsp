<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--initial-scale=1는 브라우저 기본 비율 이용 -->
<title>Spring2 Post List</title>
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
    crossorigin="anonymous">

<!-- integrity 보안 -->

</head>

<body>
    <div class="container-fluid">
        <%-- var="pageTitle"의미 :  header.jspf파일에서 <h1>${pageTitle}</h1> EL로 표현된 부분에 값을 value="Post List"  Post List로 채움--%>
        <c:set var="pageTitle" value="Post List" />
        <%@ include file="../fragments/header.jspf"%>
        <%--.. 상위폴더로 올라가서 찾음 --%>
        <main>
            <div class="mt-2 card">
                <div class="card-header text-center">
                    <%--검색에 쓸 폼 만듬 --%>
                    <c:url var="postSearchPage" value="/post/search" />
                    <form method="get" action="${postSearchPage}">
                        <div class="row"><%--부트스트랩이 화면 그릴때 기본적으로 세로로 화면을 12개로 나눠서 사용함.
                        12개인데(지금은"col-3"col-6""col-3"이렇게 div로 3:6:3 비율로 설정함. 1:2:1이라고 보면 됨. 12개의 컬럼으로 나눠서 사용하니까 12로만 맞춰주면 된다고.)
                         다시 화면 보고 바꿈 --%>
                            <div class="col-2">
                                <select class="form-control" name="category">
                                    <option value="t">제목</option>
                                    <option value="c">내용</option>
                                    <option value="tc">제목+내용</option><%--and조건 아니고 or조건으로 검색 --%>
                                    <option value="a">작성자</option>
                                </select>
                            </div>
                            <div class="col-8">
                                <input type="text"  class="form-control"
                                    name="keyword" placeholder="검색어 입력" required />
                            </div>
                            <div class="col-2">
                                <input type="submit" class="form-control btn btn-outline-secondary" value="검색" />
                            </div>
                        </div>
                    </form>
                </div>
                <div class="card-body">
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>작성자</th>
                                <th>수정 시간</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%--테이블 바디 --%>
                            <c:forEach items="${posts}" var="p">
                                <!-- Post컨트롤러에서 애트리뷰트한 이름"posts". ${posts} -->
                                <tr>
                                    <td>${p.id}</td>
                                    <!--posts객체에서 getId호출하는것 -->
                                    <td><c:url
                                            var="postDetailsPage"
                                            value="/post/details">
                                            <c:param name="id"
                                                value="${p.id}"></c:param>
                                        </c:url> <a href="${postDetailsPage}">${p.title}</a><!-- 제목을 클릭하면 /post/details로 url이 넘어감 -->
                                    </td>
                                    <td>${p.author}</td>
                                    <td>${p.modifiedTime}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </main>

    </div>



    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous">
    </script>
</body>
</html>