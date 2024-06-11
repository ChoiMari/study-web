/**
 * element.html에 포함.
 */

 //button#btn1인 요소를 찾음:
 const btn1 = document.querySelector('button#btn1');//css에서 id를 찾을 때의 문법.아규먼트로 넣어줌
 //-> const btn2 = document.getElementById('btn1')와 같은 결과
 
 //btn1 요소에 클릭 이벤트 리스너를 설정:
 btn1.addEventListener('click', function(){
    //console.log('btn1');
    
    //document.getElementById(id)사용. id가 "id1"인 요소를 찾음
    //같은 아이디를 갖는 요소가 여러개 있으면 가장 먼저 찾은 요소 1개만을 리턴.
    const div1 = document.getElementById('id1');//순수하게 id이름만 주는 것. 
    //console.log('div1');
    
    //div1 요소의 바탕색을 변경 :(id로 찾기 버튼 클릭시 변경됨.)
    div1.style.backgroundColor = 'lime';
 });
 
 //button#btn2인 요소를 찾음. 방법 2가지.
 const btn2 = document.querySelector('button#btn2');
//-> const btn2 = document.getElementById('btn2');와 같은 결과.

 //btn2에 클릭 이벤트 리스너를 설정.
 btn2.addEventListener('click', function(){
     //이벤트 리스너는 class 속성 값이 "c1"인 요소들의 바탕색을 변경.
     const divs = document.getElementsByClassName('c1'); //->HTMLCollection(배열과 비슷)을 리턴.
     //클래스 이름은 1개 이상일 수 있기 때문에 메서드 이름이 getElements
     //-> 배열을 리턴함.
     //console.log(divs); 
     
     //반복문 사용
     for (let e of divs) { //2번 실행되서 2개가 바뀜.
        e.style.backgroundColor = 'tomato';
     }
     //divs.stlye.은 안됨. divs는 배열이라서.. 배열에 스타일 속성이 없음.
     //->for문 사용해야!
 });

 // button#btn3 요소 찾음 :
 const btn3 = document.querySelector('button#btn3');
 //btn3에 클릭 이벤트 리스너를 설정:
// btn3.addEventListener('click', function() {});
 //화살표 함수도 가능
  btn3.addEventListener('click', () => {
    //태그 이름이 div인 모든 요소드을 찾아서 바탕색을 변경
    const divs = document.getElementsByTagName('div');
//    console.log(divs);
    for(const e of divs) {
        e.style.backgroundColor = 'slateblue';
    }
  });
 
 //document.querySelector(),
 //document.querySelectorAll(); -> 전부를 다 찾아줌
 //CSS 선택자 문법으로 아규먼트를 전달.
 //tagname, #id, .classname, tagname#id.classname
 //parent > child
 //ancestor descendent 
 //element:pseudo-selector
 
 const btn4 = document.querySelector('button#btn4');
 btn4.addEventListener('click', function(){
    const div4 = document.querySelector('#id4'); //querySeletor('div#id4')
    // console.log(div4);
    div4.style.backgroundColor = 'lightgray';
 });
 
 const btn5 = document.querySelector('button#btn5');
 btn5.addEventListener('click',() => {
    const divs = document.querySelectorAll('div.c2'); //-> NodeList(배열과 비슷)를 리턴.forEach 사용가능.
//    console.log(divs);

    for(let e of divs) {
        e.style.backgroundColor = 'dodgerblue';
    }
    divs.forEach((x)=> x.style.backgroundColor = 'violet');
 });
 
 //자바스크립트 속성에  - 가 들어가면 안됨. 빼기라고 생각함.
 //CSS에서 이름에 자바스크립트에서는 그래서 카멜 표기법으로 되어있음.
 //그래서 CSS에서 찾고 -를 제외하고 카멜표기법으로 찾으면 된다고 함.
 
 
 
 
 
 
 
 
 
 
 
 
 
 