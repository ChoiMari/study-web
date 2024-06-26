<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Spring Legacy 2</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
        rel="stylesheet" 
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
        crossorigin="anonymous" />
</head>
<body>
    <div class="container-fluid">
        <c:set var="pageTitle" value="Post Details" /> <%-- scope의 기본값은 page --%>
        <%@ include file="../fragments/header.jspf" %>
    
        <main>
            <div class="mt-2 card">
                <div class="card-header">
                    <h2>포스트 상세보기</h2>
                </div>
                <div class="card-body">
                    <form>
                        <div class="mt-2">
                            <label for="id" class="form-label">번호</label>
                            <input name="id" id="id" class="form-control" type="text"
                                value="${post.id}" readonly />
                        </div>
                        <div class="mt-2">
                            <label for="title" class="form-label">제목</label>
                            <input name="title" id="title" class="form-control" type="text"
                                value="${post.title}" readonly />
                        </div>
                        <div class="mt-2">
                            <label for="content" class="form-label">내용</label>
                            <textarea name="content" id="content" class="form-control" 
                                rows="10" readonly>${post.content}</textarea>
                        </div>
                        <div class="mt-2">
                            <label for="author" class="form-label">작성자</label>
                            <input name="author" id="author" class="form-control" type="text"
                                value="${post.author}" readonly />
                        </div>
                        <div class="mt-2">
                            <label for="createdTime" class="form-label">작성 시간</label>
                            <input name="createdTime" id="createdTime" class="form-control" type="text"
                                value="${post.createdTime}" readonly />
                        </div>
                        <div class="mt-2">
                            <label for="modifiedTime" class="form-label">최종 수정 시간</label>
                            <input name="modifiedTime" id="modifiedTime" class="form-control" type="text"
                                value="${post.modifiedTime}" readonly />
                        </div>
                    </form>
                </div>
                
                <div class="card-footer">
                    <c:url var="postModifyPage" value="/post/modify">
                        <c:param name="id" value="${post.id}" />
                    </c:url>
                    <a class="btn btn-outline-primary"
                        href="${postModifyPage}">수정하기</a>
                </div>
            
            </div>
        </main>

        <section>
            <div class="mt-2 card">
                <div class="card-header d-inline-flex gap-1">
                    <!-- 부트스트랩에서 가져온 class값 -->
                    <!-- 댓글 접기/펼치기 기능 버튼 -->
                    <button class="btn btn-secondary"
                        id="btnToggleComment">댓글 보기</button>
                </div>

                <!-- 댓글 토글 버튼에 의해서 접기/펼치기를 할 영역 -->
                <div class="card-body collapse" id="collapseComments">
                    <!-- collapse 접힌 상태. 댓글 보기 버튼 클릭해야 보임 -->
                    <!-- 댓글 등록 하는 부분 -->
                    <div class="mt-2 card card-body">
                        <div class="mt-2 row">
                            <div class="col-10">
                                <!-- 댓글 입력 -->
                                <textarea class="form-control" rows="3"
                                    id="ctext" placeholder="댓글 내용"></textarea>

                                <!-- TODO : 댓글 작성자 아이디. 로그인한 사용자의 아이디로 설정 바꿔주어야 함 -->
                                <input class="mt-3" id="username"
                                    placeholder=" 댓글 작성자" />
                            </div>
                            <div class="col-2">
                                <button class="btn btn-outline-success"
                                    id="btnRegisterComment">등록</button>
                            </div>
                        </div>
                    </div>

                    <!-- 포스트에 달려 있는 댓글 목록을 보여줄 영역 -->
                    <div class="mt-2" id="comments"></div>
                </div>
            </div>
        </section>

        <!--댓글 업데이트 모달(다이얼로그) 시작 부분 -->
        <div id="commentModal" class="modal" tabindex="-1" > 
        <!--tabindex="-1"의 의미 : z축(3차원) 화면 앞쪽으로 튀어나오는 가상의 축(z)을 -1로 함. 
        tabindex="-1" 그럼 가려짐(뒷쪽에 숨겨진것) 다른 컨텐트 보다 앞쪽으로 올리면 보여지게됨-->
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">댓글 업데이트</h5>
                        <!-- x모양 버튼 data-bs-dismiss="modal"모달을 제거 한다.-->
                        <button type="button" class="btn-close"
                            data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- 수정할 댓글 아이디(번호) --> <!-- class="d-none" 안보이게 감춤 -->
                        <input class="d-none" id="modalCommentId" />
                        <!-- 수정할 댓글 내용 -->
                        <textarea class="mt-3 form-control" id="modalCommentText" ></textarea>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-secondary"
                            data-bs-dismiss="modal">취소</button>
                        <button id="btnUpdateComment" type="button" class="btn btn-primary">저장</button>
                    </div>
                </div>
            </div>
        </div>
`       <!--댓글 업데이트 모달(다이얼로그) 끝 -->
    </div>
     <!-- 부트스트랩 js라이브러리 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
        crossorigin="anonymous"></script>
        <!-- 우리가 만드는 js파일 앞에 두어야 함 순서중요! 
        Axio JS 라이브러리 -->
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
        <!-- 우리가 만드는 js파일 -->
    <c:url var="commentsJS" value="/js/comments.js" />
    <script src="${commentsJS}"></script>
  
</body>
</html>