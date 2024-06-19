/**
 * /user/signup.jsp 파일에 포함.
 */

document.addEventListener('DOMContentLoaded', () => {
    let useridChecked = false; // 사용자 아이디 중복 체크 결과. true: 사용할 수 있는 아이디. 저장된 값이 바뀔 수 있어서 let타입으로 선언함.
    let passwordChecked = false; // 비밀번호 필드 작성 여부 체크.
    let emailChecked = false; // 이메일 필드 작성 여부 체크.
    
    const inputUserid = document.querySelector('input#userid');
    inputUserid.addEventListener('change', checkUserid);
    
    const inputPassword = document.querySelector('input#password');
    inputPassword.addEventListener('change', checkPassword);
    
    const inputEmail = document.querySelector('input#email');
    inputEmail.addEventListener('change', checkEmail);
    
    /* -------------------- 함수 선언 -------------------- */
    
    // 회원 가입 버튼 활성화/비활성화
    function changeButtonState() {
        //버튼 찾음
        const btnSignUp = document.querySelector('button#btnSignUp');
    
        // 3개의 값이 전부 true인 경우. 
        if (useridChecked && passwordChecked && emailChecked) {
            // 버튼의 class 속성 값들 중 'disabled'를 제거. ->버튼 비활성화(disabled. 버튼 클릭 못함)를 제거-> 사용하게 하겠다.
            btnSignUp.classList.remove('disabled');
        } else {
            // 버튼의 class 속성에 'disabled'를 추가.(-> 버튼을 비활성화 함. 버튼 클릭 못하게 함.)
            btnSignUp.classList.add('disabled');
        }
    }
    
    // userid 입력 필드의 change 이벤트 리스너. 
    function checkUserid(event) {  //-> 포커스가 바뀌면(+입력값에변경내용있을때) 이벤트 리스너 실행됨. change 이벤트. 
        const userid = event.target.value; //-> inputUserid.value 와 같음(위에 코드에서 이미 찾아서.)
     //   console.log(userid);//-> 로그로 값 출력해 봄
        //-> 아이디에서 포커스가 다른곳으로 옮겨 갔을 때 로그 출력됨
        
        const uri = `./checkid?userid=${userid}`;//-> 아이디 중복체크 REST API URI get방식으로 매핑해놓음
        // TODO: 중복 아이디 체크 Ajax 요청을 보내고, 응답을 받았을 때 처리.
        axios
        .get(uri) //get방식의 아규먼트로 보낸 uri에서
        .then((response) => {//->성공 일 때 실행.
            const checkUseridResult = document.querySelector('div#checkUseridResult');
            if(response.data === 'Y'){ //return ResponseEntity.ok("Y"); 컨트롤러에서 리턴값 이렇게 설정해서 Y로 비교함
                useridChecked = true;
                checkUseridResult.innerHTML = '멋진 아이디 입니다';
                checkUseridResult.classList.add('text-success'); //초록색 추가
                checkUseridResult.classList.remove('text-danger');//빨간색 제거
            } else {
                useridChecked = false;
                checkUseridResult.innerHTML = '사용 불가한 아이디 입니다.';
                checkUseridResult.classList.add('text-danger');//빨간색 추가
                checkUseridResult.classList.remove('text-success'); //초록색 제거
                
            }
            changeButtonState(); //버튼 활성화 여부를 변경.
        }) 
        .catch((error) => console.log(error)); //실패시 실행됨
    }
    
    //두 개의 필드값이 같은지 비교 -> 비밀번호 확인 -> 알아서 하라고..
    // 비밀번호 입력 필드의 change 이벤트 리스너
     //input#password 비어 있는 지를 체크
    function checkPassword(event) {
       if(event.target.value === '') { // inputPassword.value로 비교해도 됨
            passwordChecked = false;
       } else {
            passwordChecked = true;
       }
       changeButtonState(); //버튼의 활성화/비활성화 상태를 변경
    }
    
    // 이메일 입력 필드의 change 이벤트 리스너
    function checkEmail(event) {
        // TODO: input#email 비어 있는 지를 체크. 정규 표현식 검사해주어야 좋다고... 이메일 형식.. 
        if(event.target.value === ''){
            emailChecked = false;
        } else {
            emailChecked = true;
        }
        changeButtonState(); //버튼의 활성화/비활성화 상태를 변경
    }
    
});