/**
 * event.html에 포함
 */


 //const output = document.querySelector('div#output');
 //button#btnInput 요소를 찾음
 const btnInput = document.querySelector('button#btnInput');
 
 //btnInput에 클릭 이벤트 리스너를 설정:
 btnInput.addEventListener('click',function (e) {
    //사용자가 클릭을 하면 웹 브라우저가 클릭을 감지해서 실행해줌. 
//    console.log(e); -> PointerEvent
    const itemInput = document.querySelector('input#itemInput');
//    const output = document.querySelector('div#output');
//    output.innerHTML = itemInput.value;
//    itemInput.value = '';
    
    //클릭 할 때 마다 List아이템들이 늘어나게.
    //input#itemInput에 입력된 내용을 ul 요소의 li로 추가
    const itemList = document.querySelector('ul#itemList');
    itemList.innerHTML += `<li> ${itemInput.value} </li>`;
    itemInput.value ='';
 });
 
 // TODO: input#itemInput2 요소에 'keydown' 이벤트 리스너를 등록:
// 엔터키가 눌렸을 때, input에 입력된 내용을 ul#itemList2의 리스트 아이템으로 추가.
// JS 모든 함수는 arguments 속성(property)을 가지고 있음.

//const enterInput = document.querySelector('input#itemInput2');

// enterInput.addEventListener('keydown',function (e) {
//  if (e.key === 'Enter') {
//    const itemList2 = document.querySelector('ul#itemList2');
//    itemList2.innerHTML += `<li> ${enterInput.value} </li>`;
//    enterInput.value ='';
//    }
// });

//선생님 풀이
const itemInput2 = document.querySelector('input#itemInput2');
itemInput2.addEventListener('keydown',function(e){ //keydown 키가 눌릴때마다 이벤트 발생.
   // console.log(e); ->keyboardEvent 키보드로 누른 키가 콘솔창에 나옴.
   if(e.key === 'Enter') {//Enter칠때마다 실행 되는 코드
    const itemList2 = document.querySelector('ul#itemList2'); //아이디가 itemList2인 ul태그를 찾음
    itemList2.innerHTML += '<li> ${itemInput2.value} </li>'; //->itemInput2의 입력내용.value 을 <li>태그에 추가
    itemInput2.value = '';//-> itemInput2.value를 비어있는 문자열로 만듬.
   }
});

// TODO: input#username 요소에 'change' 이벤트 리스너를 등록:
// input에 입력된 내용이 바뀔 때마다 div를 입력 내용으로 덮어씀.
//const userName = document.querySelector('input#username');

//userName.addEventListener('change', function (e) {
//    const div = document.querySelector('div#output');
//    div.innerHTML = userName.value;
//    userName.value = '';
// });

//선생님 풀이
//input 찾기. -> event.html보고.
const username = document.querySelector('input#username');
username.addEventListener('change',(e) => { //function(e){...});대신에 화살표 함수 사용
   // console.log(e);//->Event 이벤트 객체. 콘솔창으로 이벤트 확인해보기 위해 작성 해봤던 것.
    const output = document.querySelector('div#output');
    output.innerHTML = username.value;
    username.value = '';
});//'change'는 포커스를 잃었을 때, 안에 있는 내용이 바뀌어 있을 때 이벤트 발생
// -> 'change' 이벤트는 input이 편집상태가 아니고(포커스를 잃어버린 상태이고), 
// input에 입력된 값이 이전과 달라진 경우에 발생한다.


// TODO: img#bulb 요소에 'mouseenter' 이벤트 리스너를 등록:
// img의 src를 'images/bulb_on.gif'로 변경.
// const imgMouse = document.querySelector('img#bulb');
// imgMouse.addEventListener('mouseenter', function (e) {
//    imgMouse.src ="images/bulb_on.gif";
//});

//선생님 풀이
const bulb = document.querySelector('img#bulb');
bulb.addEventListener('mouseenter', function(e){
    bulb.src = 'images/bulb_on.gif';
    bulb.alt = 'bulb_on';
});


// TODO: img#bulb 요소에 'mouseleave' 이벤트 리스너를 등록:
// img의 src를 'images/bulb_off.gif'로 변경.
//imgMouse.addEventListener('mouseleave', function (e) {
//    imgMouse.src ="images/bulb_off.gif";

//});

//선생님 풀이 . 화살표 함수 사용 function(){});와 같음.
bulb.addEventListener('mouseleave', () =>{ // 아규먼트 필요 없을 시 선언 안해도 됨. 굳이 이벤트 종류 찾아내서 속성쓸 필요 없으면 선언 안해도 된다
    bulb.src = 'images/bulb_off.gif'; //이벤트가 콜백 내부에 사용되지 않는 경우 선언 안해도 된다. 어차피 선언해도 내부 코드에서 사용 안하니까.
    bulb.alt = 'bulb_off';
});


