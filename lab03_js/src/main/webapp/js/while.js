/**
 * while.html에 포함됨.
 */

 const list = document.querySelector('#list'); 
 //-> document.getElementById('list')와 같은 것.
 //-> CSS에서 사용하는 셀렉터와 동일하게 아규먼트로 주면 됨.
 //while.html 문서의 id가 list인것을 찾는 것.
 
 const tableBody = document.querySelector('#tableBody');
  //while.html 문서의 id가 tableBody인것을 찾아 변수 tableBody에 저장.
  
let html = '';// <ul></ul> 태그의 컨텐트로 작성할 HTML 코드.

let n = 1;
while (n <= 5) { //반복을 할지 말지 검사.
    html +=`<li>아이템 ${n} </li>`;
    n++;
}
list.innerHTML = html; 


//table 자동으로 5개 -> 내가 한 것.
let t = 1;
let html2 = '';
while (t <= 5) {
    html2 += `<tr> <td>${t}</td> <td>제목 ${t}</td> </tr>`;
    t++
}
tableBody.innerHTML = html2;

//선생님 풀이
n = 1;
html = '';
while(n <= 5) {
    html += `
    <tr>
        <td> ${n} </td>
        <td> 제목 ${n} </td>
    </tr>
    `;
    n++;
}
tableBody.innerHTML = html;





