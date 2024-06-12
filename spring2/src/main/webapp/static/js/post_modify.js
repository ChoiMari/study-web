/**
 * /post/modify.jsp에 포함.
 */

document.addEventListener('DOMContentLoaded', () => {
    //자바 스크립트에서 해야될일 버튼, input등 찾는 일 . HTML객체 만들어져 있어야 한다고...
    //자바스크립트 넣는 스크립트 태그 head안에 들어갈수도 (->권장하지 않음) 관습적으로 body 끝에다가 넣는다고
    //어느 곳에 넣든 HTML바디 만들어진 다음에 자바스크립트 함수 실행된다고
    //함수안에 있는 코드들 도큐먼트로드 끝난 다음 실행 -> 이벤트 리스너. 콜백(나중에 호출되는 함수)
    
    //필요한 것들 찾는 코드 modify.jsp에서 찾아오는 것. 태그의 id속성값에서 가져옴
    const modifyForm = document.querySelector('form#modifyForm'); //->form
    const inputId = document.querySelector('input#id'); // ->input 
    const inputTitle = document.querySelector('input#title');
    const textContent = document.querySelector('textarea#content');
    const btnDelete = document.querySelector('button#btnDelete');
    const btnUpdate = document.querySelector('button#btnUpdate'); //태그이름#아이디
    
    //삭제 버튼의 클릭 이벤트 리스너:
    btnDelete.addEventListener('click', () => {
        const result = confirm('정말 삭제하시겠습니까?'); //->삭제 버튼 누르면 정말 삭제하겠냐고 컨펌메세지 창 뜨게하는 코드
        if(result){
            //사용자가 확인을 눌렀을 때 실행됨.
            location.href = `delete?id=${inputId.value}`; //get방식의 delete요청을 서버로 보냄. ?뒤는 쿼리스트링
            
            //-> 확인 누르면 http://localhost:8080/spring2/post/delete?id=글번호로 요청 주소로 바뀜
            
        }
        console.log(result);
    });
    //업데이트 버튼의 클릭 이벤트 리스너:
    btnUpdate.addEventListener('click',() => {
        //제목과 내용이 비어있는 지 체크:
        if(inputTitle.value === '' || textContent.value === ''){
            //비어있으면 계속 진행하면 안됨.
            alert('제목과 내용은 반드시 입력하세요.');
            return; //-> 함수 종료
        }
        //비어있지 않으면 실행
        // 업데이트 내용 저장 확인:
        const result = confirm('변경 내용을 저장할까요?');
        if(result){//변경 컨펌 메세지에서 사용자가 확인을 누리면 실행됨
            modifyForm.action = 'update'; //요청주소
            modifyForm.method = 'POST'; //요청 방식
            modifyForm.submit();//폼 양식 데이터 제출(서버로 요청 보냄)
        }
        
    });
    
});
