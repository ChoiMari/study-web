/**
 * for.html 파일에 포함됨.
 */

// 아이디가 result인 HTMl 요소를 찾음.
 const result = document.getElementById('result');
 
 let html =''; //-> result 요소의 추가할 HTML 코드를 저장하기 위한 문자열 변수.
 
 for(let x = 0;x < 10; x++) {
    html += `2 x ${x} = ${2 * x} <br/>`; //$는 변수 x의 값을 $를 넣은 이 자리에 쓰겠다. {2 * x}를 계산한 값을 $이 자리에 쓰겠다
 }//``으로 작성한 부분을 문자열 템플릿이라고 함. {}안에 계산식을 쓸 수 있음
 result.innerHTML += html;
 
 result.innerHTML += '<hr/>';
 //+=를 안쓰고 그냥 =쓰면 값이 그거 1개로만 다 바뀜. +=는 기존 것에 코드를 더해서 쓴다는 의미.
 
 //연습문제 : result에 구구단 3단 ~ 9단 까지 덧붙임(append)
html ='';
 for(let x = 3; x < 10; x++) {
    for(let y = 1 ;y < 10; y++){
    html +=`${x} x ${y} = ${x * y} <br/>`;
    }
    html += '----------------<br/>';
 }
result.innerHTML += html;
result.innerHTML += '<hr/>'; 

// break문 사용 해서 2단은 2x2까지, 3단은 3x3까지 ...9x9까지 출석.
html ='';
for(let x = 2; x < 10; x++){
    for(let y = 1;y < 10; y++){
        html += `${x} x ${y} = ${x * y} <br/>`;
        if(x == y){
            break;
            }
    }
    html += '----------------<br/>';
}
result.innerHTML += html;
result.innerHTML += '<hr/>'; 




