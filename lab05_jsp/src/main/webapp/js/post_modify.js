/**
 * /post/modify.jsp에 포함. 
 * 자바스크립트는 브라우저가 실행해주는 코드
 */

 //잘 포함되었는지 확인하기 위한 로그 출력
 //console.log('post modify');
 
 //이벤트 리스너 -> 해당 이벤트가 발생했을 때만 호출되어 실행되는 코드
 //'DOMContentLoaded' : HTML로딩이 끝났을 때 이벤트 발생
 //HTML DOM(Document Object Model) 컨텐트 로딩이 끝났을 때, 이벤트 리스너를 실행
 document.addEventListener('DOMContentLoaded',() => { //화살표 익명함수
    //modify.jsp파일에 있는 form 요소를 찾음:
    const modifyForm = document.querySelector('form#modifyForm');
    
    //modify.jsp파일에 있는 글 번호가 입력된 input#id 요소를 찾음: (삭제 할땐 글 번호만 있음 된다고 함.)
    const inputId = document.querySelector('input#id');
    
    //modify.jsp파일에 있는 글 제목이 입력된 input#title 요소를 찾음:
    const inputTitle = document.querySelector('input#title');
    
    //modify.jsp파일에 있는 글 내용이 작성된 textarea#content 요소를 찾음:
    const textareaContent = document.querySelector('textarea#content');
    
    //modify.jsp파일에 있는 삭제 버튼 찾음:
    const btnDelete = document.querySelector('button#btnDelete');
    
    //업데이트 버튼 찾음:
    const btnUpdate= document.querySelector('button#btnUpdate');
    
    //삭제 버튼에 클릭 이벤트 리스너를 설정.
    btnDelete.addEventListener('click', () => { //이벤트가 필요하면 파라미터 선언 (e) =>
        const result = confirm('정말 삭제할까요?'); //삭제 버튼 클릭시 컨펌창을 띄움. 아규먼트로 넣은 문자가 컨펌메세지로 뜸.
        //console.log(result);//-> confirm()의 리턴 값은 true 또는 fales .브라우저의 컨펌창에서 사용자가 확인 누르면 true, 취소 하면 false
        if(result){ //=> result가 true이면 실행
            //get방식의 요청 보내는 간단한 방법.location객체 이용
            //삭제(GET방식) 요청을 서버로 보냄
            location.href = `delete?id=${inputId.value}`; //->이건 EL아님. EL은 jsp에서 사용 되는 것.
            //지금은 자바 스크립트의 변수를 찾아갈때 사용하는 것이 ${} 뺵팁(문자열을 만들어주는 형식(템플릿))사용. 키보드 ~밑에있는 기호
            //헷갈리지 않도록 주의하기
            //최근 추세 J쿼리 적게 쓰려고 한다고...    
            //현재 주소를 다른 주소로 바꿔주는게 location.href
            //상대경로로 만듬 `delete?id=${inputId.value}`;
            //url 주소 http://localhost:8080/lab05/post/delete?id=6 로 바뀜
            //-> 서블릿에서 할일 리퀘스트.겟파라미터 블라블라
        }      
    });
    
    //업데이트 버튼에 클릭 이벤트 리스너 설정. 업데이트 버튼을 클릭하면 호출되어 실행되는 코드
    btnUpdate.addEventListener('click', () => {
        //제목과 내용이 비어있는 지를 체크
        //비어있으면 submit 안함
         const title = inputTitle.value; //input 요소에 입력된 값.
         const content = textareaContent.value; //textarea 요소에 입력된 값.
        if (title === '' || content === '') {
            alert('제목과 내용은 반드시 입력해야 합니다!');
            return; // 함수 종료
        }
         
         const result = confirm('변경된 내용을 저장할까요?');
         if(result){ //브라우저 confirm 창에서 확인 누르면 실행되는 코드
            modifyForm.method = 'post'; //폼 제출 방식 설정.
            modifyForm.action = 'update';//폼 제출 요청 주소 설정.
            modifyForm.submit(); // 폼 제출(서버로 요청을 보냄).
         }
    });
    
 });