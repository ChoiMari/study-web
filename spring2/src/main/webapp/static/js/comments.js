/**
 * /post/details.jsp에 포함
 */

 //console.log('comments.js'); //제대로 포함되었는지 확인하려고 
 
 document.addEventListener('DOMContentLoaded',() => { // HTML도큐먼트오브젝트들이 전부 다 로드가 되었을 때 이벤트 발생해서 실행할 함수.
    //button찾기/post/details.jsp에 id가 btnToggleComment 버튼 요소를 찾음.
    const btnToggleComment = document.querySelector('button#btnToggleComment'); //에이작스는 $('botton#btn)?? 이랑 똑같다고 함 
    
    ///post/details.jsp에 id가 collapseComments div요소 찾음. 요소를 부트스트랩의 Collapse객체로 만듬(생성) Collapse접었다 펼수있는 객체
    const bsCollapse = new bootstrap.Collapse('div#collapseComments',{toggle:false});
     //아규먼트 준 1번째는 컬랩스로 할 요소(문자열로 줌). html태그id, 2번째는 {toggle:false} 접혀져 있는 상태로 보여주겠다(객체를 줌)설정함
     //-> {toggle:true}면 펼쳐진 상태로 보여주겠다. 자바 스크립트에서 객체 만드는 법 {변수이름:값} toggle필드 이름 : toggle값
     //{toggle:false} 자바스크립트의 객체.
     
     //댓글 토글 버튼에 클릭 이벤트 리스너를 등록.
    btnToggleComment.addEventListener('click', () => {
        bsCollapse.toggle();
        
        if (btnToggleComment.innerHTML === '댓글 보기') {
            btnToggleComment.innerHTML = '댓글 감추기';
            //포스트에 달린 모든 댓글 목록 보여주는 메서드 호출
            getAllComments();
        } else {
            btnToggleComment.innerHTML = '댓글 보기';
        }
    });

    //버튼 요소 button찾기/post/details.jsp에 id가 btnRegisterComment 버튼 요소를 찾음
    const btnRegisterComment = document.querySelector('button#btnRegisterComment');
    
    //찾은 버튼의 클릭 이벤트 리스너를 등록 -> id가 btnRegisterComment버튼이 클릭되었을 때 실행됨.
    btnRegisterComment.addEventListener('click', registerComment); //내부에 코드가 길어서 익명함수 쓰지 않고  registerComment 라는 
    //함수를 만들어 씀
    
    //댓글 등록 이벤트 리스너 콜백(함수) -> 만드는 위치 중요하지 않다고 함.
    function registerComment() {
        ///post/details.jsp에서 id가 id,ctext,username 찾아서 가져옴
        //댓글이 달릴 포스트 번호를 찾음.
        const postId = document.querySelector('input#id').value;
        
        //댓글 내용을 찾음.
        const ctext = document.querySelector('textarea#ctext').value;
        
        //댓글 작성자 아이디를 찾음
        const username = document.querySelector('input#username').value;
        
       // console.log(postId,ctext,username);
       
       //댓글 내용, 댓글 작성자가 비어있는 지 체크하기
       if (ctext === '' || username ==='') {
            alert('댓글 내용과 작성자는 반드시 입력하십시오.');
            return; //-> 이벤트 리스너를 종료
       }
       //댓글 내용과 작성자가 비어져 있지 않은 경우
       //Ajax 요청에서 보낼 데이터 객체를 생성
    /*   const data = {
        postId: postId, //변수이름:값(위에 지역 변수사용 ->document.querySelector('input#id').value;)
        ctext: ctext,
        username: username
       }; */
       const data = {postId,ctext,username}; //->위의 주석 코드와 같음. 그냥 값만 {}안에 나열하면 됨.
       //->DTO에서 선언한 필드 이름과 {}에 나열한 프로퍼티 이름과 같아야 한다.
       console.log(data);//-> 보내기 전 데이터 제대로 만들어 졌는지 확인
       
       //POST 방식의 Ajax 요청을 서버로 보냄 . 응답 성공/실패 콜백을 등록
       axios
       .post('../api/comment', data) //'../api/comment' 요청 주소(..의 의미 url에서 spring2/api/comment), post방식의 요청을 보내겠다.(요청방식 get,put,delete방식으로 변경가능. 자바스크립트 객체 타입으로 아규먼트넘기기)
                                    //'../api/comment'요청주소를 처리하는 컨트롤러로 감.post방식으로 아규먼트로 data(자바스크립트의 객체)를 보냄.
       .then((response) => { //다른일 하면서 기다리고 있다가 응답오면 then실행됨(성공 콜백 등록 부분)
         //   console.log(response);
         console.log(response.data);//->응답의 데이터만 출력해봄 RestController 에서 보낸 응답 데이터 1이 리턴됨. 댓글 1개 insert되어서
         if(response.data === 1){ // 성공 시 1리턴 되어서 1과 비교해서 메세지창 띄움. 
            alert('댓글 1개 등록 성공');
            //댓글 성공 후 댓글 입력 칸 지우기
            document.querySelector('textarea#ctext').value = '';
            document.querySelector('input#username').value = '';
            
            //댓글 목록 갱신
            getAllComments();
            
         }
       }) //-> 성공 콜백 등록 return ResponseEntity.ok(result);로 설정해 놓아서 무조건 then부분이 온다고 함. return ResponseEntity.에러 였으면 .catch부분 실행? 맞나?
       .catch((error) => {
            console.log(error);
       }); //-> 실패했을 때 함수 등록하는 부분
       
    }
    
    //포스트에 달려 있는 모든 댓글 목록 가져오는 함수 만듬--> 댓글 보기 버튼 클릭시 해당 함수 주르륵 나오게 호출
    function getAllComments(){
        //댓글 목록을 요청하기 위한 포스트 번호를 찾아야함 input에 있음
        const postId = document.querySelector('input#id').value; //input#id찾아서 value를 읽겠다.
        //댓글 목록을 요청하기 위한 REST API URI
        const uri = `../api/comment/all/${postId}`; //``문자열 템플릿
        
        //Ajax 요청을 보냄.
        axios.get(uri) //요청방식 get
        .then((response)=>{
            console.log(response.data);
            //댓글 목록을 html로 작성해서 ->details.jsp의 div태그의 id="comments" 영역에 출력
            makeCommentElements(response.data); //-> makeCommentElements 함수를 만들어서 아규먼트로 댓글들의 배열을 아규먼트로 전달함. 
            //응답 객체가 가지고 있는 댓글의 data배열
        }) //성공 콜백. 성공했을 때 해야될 일
        .catch((error)=>{
            console.log(error);
        }); //실패 콜백. 실패 했을 때 해야 될 일
    }
    
    //댓글 목록(댓글 객체들의 배열)을 아규먼트로 전달 받아서 html을 작성하는 함수(댓글 목록을 브라우저화면에 보여주는 함수)
    function makeCommentElements(data){
        //댓글 목록 html이 삽입될 div를 먼저 찾음
        const divComments = document.querySelector('div#comments');
        
        //댓글 목록 HTML 코드
        let htmlStr = ''; //const와 let차이점 . const 할당되면 바뀌지 않음. let바뀜
        for(let comment of data){ //자바스크립트의 for문 3가지 of 순서대로 원소 꺼냄. in은 순서대로 인덱스를 꺼냄
        // 댓글 최종 수정 시간
            const modifiedTime = new Date(comment.modifiedTime).toLocaleString();
            console.log(modifiedTime);
            //card card-body카드 my-1 위아래 마진. fw-bold글자 두껍게. text-secondary 글자 회색
            //btnDeleteComment는 버튼 찾으려고 임의로 준 이름 btn btn-outline-danger은 부트 스트랩 스타일 class이름
            //data-id는 html표준에 정의되어있는 속성이 아님. 선생님이 만든 속성. 모든 태그에 원하는 대로 속성이름을 만들어서 값을 넣음.
            //관습 data 접두사 많이 붙인다고. 내가 만들어주는 속성이름 data-이름 붙인다고.. 그럼 속성값 읽을 수 있다고.
            // get애트리뷰트하면 속성 안에 값 읽을 수 있다고 함.
            htmlStr += `
            <div class="card card-body my-1">
                <div style="font-size: 0.85rem;">
                    <span>${comment.id}</span>
                    <span class="fw-bold">${comment.username}</span>
                    <span class="text-secondary">${modifiedTime}</span>
                </div>
                <div class="mt-2">${comment.ctext}</div>
                <div>
                    <button class="btnDeleteComment btn btn-outline-danger mt-2"
                        data-id="${comment.id}">삭제</button> 
                    <button class="btnModifyComment btn btn-outline-primary mt-2"
                        data-id="${comment.id}">수정</button>
                </div>
            </div>
            `;
        }
        //작성된 html코드를 div 영역에 삽입.
        divComments.innerHTML = htmlStr;
        
        //모든 삭제 버튼들을 찾아서 클릭 이벤트 리스너를 설정함. 코드 위치 중요하다고 divComments.innerHTML = htmlStr; 꼭 아래에 써야 한다고 함
        const btnDeletes = document.querySelectorAll('button.btnDeleteComment'); // 태그에 달려있는 클래스 속성일땐 .사용함
        for(let btn of btnDeletes){
            btn.addEventListener('click',deleteComment); //-> 화살표함수 대신 그냥 함수 사용
 //           btn.addEventListener('click', (e) => { //-> 버튼 하나하나마다 이벤트 등록함 for문이용해서
 //               alert(e.target.getAttribute('data-id'));
 //           });
            
        }
        //TODO : 모든 수정 버튼들을 찾아서 클릭 이벤트 리스너를 설정함.
        
    }
    
    function deleteComment(event) { //이벤트 리스너가 가지고 있는 event객체 
        //이벤트 리스너 콜백의 아규먼트 event 객체는 target 속성을 가지고 있음.
        console.log(event);//이벤트가 발생한 요소(타겟)
        //data-id= 속성 값 읽고 싶으면 이렇게
        const id = event.target.getAttribute('data-id'); //-> 이벤트가 발생한 요소(타겟)에서 data-id 뽑아내겠다.
        //html의 data-id요소의 속성값 찾기. 삭제하기 위한 글번호 
        
        //삭제 여부 확인
        const result = confirm('댓글을 정말 삭제 할까요?');
        if(!result) { //!result 사용자가 컨펌창에서 취소했을때 함수 종료.
            return;// 함수종료 . 삭제 확인 누르면 해당 if문 실행 되지 않아서 밑의 코드 진행됨. 
        }
        
        //Ajax 요청을 보낼 REST API URI
        const uri = `../api/comment/${id}`;
        
        //Ajax 요청을 보냄
        axios
        .delete(uri)
        .then((response) => {
            console.log(response.data); //-> 삭제 성공 시 response.data는 1
            if(response.data === 1){
                alert(`댓글(${id}) 삭제 성공`);
                getAllComments(); //댓글 목록 갱신
            } 
        })
        .catch((error) => {
            console.log(error);
        });
    }
});