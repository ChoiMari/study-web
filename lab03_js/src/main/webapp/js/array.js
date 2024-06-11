/**
 * array.html에 포함됨.
 * 
 * 자바스크립트 배열(array): 여러 개의 원소(아이템)들을 하나의 변수 이름으로 저장하기 위한 타입.
 *  -타입에 관계 없이 여러개 저장 가능함.
 *  -자바스크립트 배열에서는 다른 타입의 값들이 저장될 수 있음. 
 * 
 * 자바 배열 : "한가지" 타입의 값들 여러 개를 저장하는 타입. 
 *  예)String 배열이다 하면 문자열 값만 저장 가능. 다른 타입은 저장 할 수 없다.
*/



//array.html에 있는 div중에서 아이디가 output인 요소를 찾음:
const output = document.querySelector('div#output');

//배열 선언 & 초기화. [] 대괄호 사용. 배열의 원소는 타입에 관계 없이 저장 가능
const arr = [1,2,30.1,-5,'안녕']; //자바라면 int[] arr = {1,2,3}; 중괄호 사용

//배열 arr의 내용을 output 영역에 씀.
let html='';
for(let i = 0; i < arr.length; i++) { //arr.length(=배열의 길이) 보다는 작다.
    html += `${arr[i]}, `; //배열의 i번째 원소를 $를 넣은 이 자리에 쓰겠다라는 의미
}
output.innerHTML += html + '<br/>';

// 자바의 향상된 for 문장 : for (변수 선언 : 배열 이름) {반복할 코드들 작성}

// 자바 스크립트. 2가지

//1. 배열의 값들을 사용할 때 사용
//for-of 문장 : 배열의 원소들을 iteration(순회)
//arr배열의 원소를 하나씩 꺼내서 원소 val에 저장해서 for문의 {}내부를 실행. 자바의 향상된 for문과 동일하게 진행.
html = '';
for (let val of arr) { //자바에서는 : 사용하고, 자바스크립트에서는 of 키워드 사용. arr 배열의 원소들 중에서 반복하겠다.
    html += `${val}, `;
}
output.innerHTML += html + '<br/>'; //원래 출력 문장에 추가되서 출력되기 원하면 +=사용. 
// =로 써버리면 기존의 출력 코드는 사라지고 새로 저장하는 코드만 화면에 출력 되어 버림

//2. 배열의 인덱스 반복
//for-in 문장 : 배열의 인덱스들을 interation(순회.반복) -> `${idx}`인덱스 번호가 출력되서 화면에 보여짐.
html = '';
for (let idx in arr) {
    html += `${idx}, : ${arr[idx]}, `;   //`${arr[idx]}`는 배열의 값이 나옴.
}
output.innerHTML += html + '<br/>';


//배열 arr의 원소들의 합계, 평균을 output에 출력.
const arr1 = [1,2,30,40,-5];
//합계 계산
let sum = 0;
for(let val of arr1) {
    sum+=val;
}

const avg = sum / arr1.length; //자바스크립트에서 나누기 연산자는 소수점 이하 까지 계산 수행함.
//자바와의 차이 : 자바에서는 나눈 몫만 계산 됨. 

//화면에 계산 된 걸 보이게 출력 하는 코드
output.innerHTML += `sum = ${sum}, average = ${avg} <br/>`;

// 배열의 원소들을 ul의 li 요소로 만듬.
const drama = ['삼식이 삼촌','동조자','삼체'];

//array.html의 id가 list인 <ul>태그에 drama 배열의 원소들 넣기. 반복문 사용.

//내가 한 것
/* html = '';
for(let val of drama) {
    html += `${val}, `;        
}

list.innerHTML = html; */

//선생님 풀이
const list = document.querySelector('ul#list'); //아이디가 list인 것을 찾음.
html = '';
for (let val of drama) {
    html += `<li> ${val} </li>`;
}
list.innerHTML = html; //안에 아무것도 없기 때문에 굳이 += 쓸 필요없다.








