/**
 * array_method.html 문서에 포함 시킴.
 */

 
 //자바스크립트 객체의 함수(메서드)들
 
const arr = [1,2,3];
console.log(arr);
//콘솔 출력 결과 (3) [1, 2, 3]
//앞에 (3)은 원소 개수를 뜻 함. 원소 개수가 3개라고

//배열의 push()메서드
//새로운 원소를 배열 끝에 추가.
arr.push(100); //push뜻 밀다. 안에다가 집어 넣다. 밀어 넣다.
console.log(arr);//브라우저 개발자 도구 콘솔에 배열 arr출력.
//출력 결과 (4) [1, 2, 3, 100]
//-> push()는 원본 배열의 끝에 새로운 원소를 추가해 주는데, 
// (중요) 원본의 배열이 바뀌는 것.
// 배열 arr을 const로 선언 했는데????
// arr의 저장된 주소값은 그대로 안의 원소 배열 내용이 바뀌는 것.
// const로 선언 된건 arr = [1,2,3,4]; 이렇게 재할당을 못하는 것.
// 배열들의 주소값을 저장하는 변수 arr
// arr의 주소값이 바뀌지 않는 다는 의미의 const로 생각해야 함.

//배열의 concat()메서드
// 이게 헷갈려서 나온 메서드가 있음 concat()이라는 메서드.
let result = arr.concat(200); //->concat 이어붙이다라는 뜻. 기본 배열 뒷쪽에 이어붙여주는 메서드
//concat 메서드는 
//  중요) 원본 배열을 변경하지 않고 원소가 추가된 새로운 배열을 리턴하는 메서드.
// 원본 배열을 그대로 놔두면서 새로운 배열을 리턴한다.
console.log(arr); //콘솔 출력 결과: (4) [1, 2, 3, 100]
console.log(result);//콘솔 출력 결과: (5) [1, 2, 3, 100, 200]

//배열의 pop()메서드
//배열의 마지막 원소를 삭제.
arr.pop(); //->아규먼트가 따로 없음.
console.log(arr);//출력 결과 (3) [1, 2, 3]
//arr.pop(); 실행 전 arr의 원소는 [1, 2, 3, 100]
//arr.pop(); 실행 후 arr의 원소 [1,2,3]


//배열의 slice()메서드 - 원본 배열은 변경 되지 않음.
//slice(start,end) : 배열에서 start 인덱스 부터 end 인덱스 까지를 잘라낸 새로운 배열을 리턴.(원본 배열의 원소가 바뀌는 건 아님.)
result = arr.slice(0,-1); //-1은 배열의 마지막 원소라는 뜻. 끝에서 첫번째 원소. -2는 끝에서 두번째 위치한 원소.
console.log(arr); //출력 : (3) [1, 2, 3] 원본 배열은 바뀌지 않고 그대로.
console.log(result);//출력 : (2) [1, 2] -> 가장 마지막 원소가 사라짐.
//->slice()메서드. 원본 배열의 원소는 바뀌지 않고 그 원본 배열을 이용해서 
//아규먼트로 준 값을 이용해 잘라내고 새로운 배열 만들어서 리턴값으로 줌.
//pop()메서드와 같은 동작을 하고 싶으면 배열이름.slice(0,-1); 으로 사용하면 됨.
//pop메서드와 다른 점은 원본 배열은 안 바뀐다는 점.


//배열의 toSorted() 메서드 - 원본 배열을 변경하지 않고 배열에 저장 되어있는 원소를 오름차순 정렬해주는 메서드.
//배열 arr2 선언 & 초기화
const arr2 = [10, 100, -1, 90];
result = arr2.toSorted(); //배열의 toSorted()메서드를 호출하고 메서드의 리턴값을 변수 result에 저장시킴
console.log(arr2); //(4) [10, 100, -1, 90]//toSorted()메서드는 원본 값을 변경하지 않는 걸 확인함.
console.log(result); //(4) [-1, 10, 100, 90] //-> 원소가 오름차순(작은 값부터)정렬됨.
//근데 90이 뒤에 출력됨.. 오름차순 제대로 안됨. 
//이유 :  브라우저 동작 -> 문자열로 변환 해서 콘솔에 보여줌. 문자 1보다 문자 9가 더 커서
//자바스크립트는 여러가지 타입을 배열에 저장 가능해서 그걸 일반적으로 만들고자 하면
//문자열로 변환해서 하는게 쉬워서 브라우저에서 그렇게 구현되어 있다고 함.//그래서 문자열 정렬로 생각하면 됨.

//그래서 이렇게 씀
//toSorted(callback) : 배열 원소들의 크기를 비교 할 때 사용할 콜백을 아규먼트로 전달.
result = arr2.toSorted((x , y) => x - y); // 콜백가지고 비교해라. 앞의 원소가 크냐 뒤에 있는 원소가 크냐 비교. 
//비교해서 오름차순 정렬해서 리턴함.
//x - y 결과가 양수이면 앞의 쓴 x가 크고, 결과가 음수이면 뒤에 쓴 y가 더 큰 값으로 인식하게 판별.  
// 결과가 만약 0이면 두 개는 같다라고 판별함. 
// - 빼기는 문자에서는 사용 할 수가 없으니까.
//콜백으로 준 함수(리턴값 x - y)의 결과가 양수냐 음수냐에 따라서 정렬한 자리가 바뀔지 말지가 결정 됨.
// 선생님이 문자열에만 쓰라고 함.

//결론 : toSorted()메서드는 배열의 원소들을 문자열로 변환해서 크기 비교를 해서 
// 오름차순으로 정렬 시켜서 새로운 배열을 리턴한다. 
//-> (원본 배열은 그대로 두고. 원본 배열을 이용해서 오름차순해서 호출한 곳으로 반환해줌)

//배열의 sort()메서드
//-> 배열의 원소들을 문자열로 변환해서 크기를 비교해서 
// 원본 배열의 원소를 오름차순으로 변경해서 리턴. 원본 배열의 원소를 오름차순 정렬로 바꿈.
//sort(callback) : 배열 원소의 크기 비교에서 사용하는 콜백을 아규먼트로 전달.
result = arr2.sort((x , y) => x - y);
console.log(arr2);//출력 결과 : (4) [-1, 10, 90, 100]
console.log(result);//출력 결과 : (4) [-1, 10, 90, 100] //-> 원본 배열의 원소를 변경시킨다는 걸 볼 수 있음.
 
 //-----------------------------------------------------------
// 새로운 배열 선언 & 초기화 
//filter, map, reduce;
// 자바에서 stream에 필터 줌. 필터의 조건에 맞는 건만 빠져나옴. 그 결과를 리스트로 묶음.
// 자바스크립트에서도 비슷한게 있다고..
//자바처럼 stream 스트림객체 따로 필요 없다고.
const numbers = [1,2,3,4,5,6];

//배열 numbers의 원소들 중에서 홀수들로만 이루어진 새로운 배열을 만드세요.
//배열 numbers는 원본 그대로 두어야 함.//-> 그래서 새로운 배열을 만들어 놓고 numbers의 원소를 하나씩 꺼내서 
//조건에 맞는 것만 새로 만든 배열에 저장시킴.

//배열 numbers의 원소들 중 홀수들만 저장할 새로운 배열을 선언하고 초기화 함.
const odds = []; //let odds = []; -> concact메서드 사용시 변수를 let으로 선언해야 함.
for (let x of numbers) {
    if(x % 2) { // 자바스크립트에서 0은 false로 인식되서 실행 안됨. x % 2값이 0이 아닌 다른 값이 나올 때 true로 인식 돼서 실행
        odds.push(x); //odds.concact(x);로 쓸 수 도 있다
    } // x % 2 는 x를 2로 나눈 나머지. 0이면 짝수 1이면 홀수. 0이면 false 다른값이면 true로 인식되서 실행 됨.
}
console.log(odds); // 출력 결과 : (3) [1, 3, 5]

result = numbers.filter ((x) => x % 2); 
console.log(result); // 출력 결과 : (3) [1, 3, 5]

//익명 함수 사용
result = numbers.filter (function (x) {
    return x % 2;
});

// 배열 numbers의 원소들의 제곱을 원소로 갖는 새로운 배열을 만드세요.

// 배열 numbers의 원소들의 제곱을 저장할 배열 선언.
const squares = []; 

for (let x of numbers) {
    squares.push(x * x); //배열 numbers의 원소를 하나 씩 꺼내서 x에 담고 그걸 배열 square에 push저장시킴
}
console.log(squares);

result = numbers.map ((x) => x * x);
console.log(result);

numbers.forEach((x) => console.log(x));
// 배열의 원소 1개 x를 가지고서 
// 한 줄 씩(줄 바꿔서) 원소들을 출력

result.forEach((x) => console.log(x));

//----------------------------------------------------------
//배열 numbers의 모든 원소들의 합계
let sum = 0;
for(let value of numbers) {
    sum += value; //sum = sum + value;
}

//브라우저의 개발자도구 콘솔에 출력
console.log(`sum = ${sum}`);

// 배열에 있는 원소 처음부터 마지막까지 순서대로 꺼내서 할일(콜백)을 실행해서 리턴해줌.
sum = numbers.reduce((acc, cur) => acc + cur, 0);
//reduce(callback, initialValue)
//initialValue를 주어야 하는 이유. 첫번째는 initialValue값이 들어가고 두번째부터 콜백 실행해서 리턴해준값이 들어간다고?

//numbers의 모든 원소들의 곱 구하기(곱하기는 0값이 들어가면 뭘 곱해도 0나옴 시작 값이 0이면 안됨.)

//곱하기는 1로 시작. 0이면 뭘 곱해도 0이라서. 
result = 1;
for(let value of numbers) {
    result = result * value; //result *= value;
}
//콘솔 로그 출력
console.log(`result = ${result}`);
// 이 코드를 간단하게 -> 배열의 reduce메서드 사용.
result = numbers.reduce((acc, cur) => acc * cur, 1);
//콘솔 로그 출력
console.log(`result = ${result}`);


//numbers의 원소들 중에서 짝수들의 합 : 2 + 4 + 6

//문제 1) 짝수들만 걸러서 콘솔 로그 출력
// 선생님은 x % 2 ===0 이라고 콜백 주심.initialValue 써주지 않으면 원소의 첫번째 값이 1번째로 시작됨.
result = numbers.filter((x) => (x % 2 === 0)).reduce((acc, cur) => acc + cur);
console.log(`짝수 합 = ${result}`);
//filter는 콜백으로 넣은 함수에 만족하는 것만 리턴. 콜백을 처음 시작하는 값이 initialValue에 넣어준 값.
//reduce 원소의 처음부터 마지막까지 순서대로 꺼내서 콜백을 실행하고 최종결과를 리턴해줌.

//내가 푼 것.
result = numbers.filter((x) => !(x % 2)).reduce((acc, cur) => acc + cur, 0);
console.log(`evenAdd = ${result}`);

//문제 2) numbers의 원소들 제곱의 합
result = numbers.map((x) => (x * x)).reduce((acc, cur) => acc + cur, 0);
console.log(`제곱 합 = ${result}`);
//acc는 처음엔 initialValue로 준 값
// 0 + 첫번째 원소 이게 콜백의 리턴값인데 이게 다시 acc로 들어가고
// 다시 콜백 실행 0+첫번째+두번째 원소의 결과가 다시 acc로 들어가고
// (원소의 마지막 까지 계속 반복 실행)해서 최종 결과를 리턴해준다.
 
//문제 3) numbers의 원소들 중에서 짝수들의 제곱의 합
result = numbers.filter((x) => (x % 2 === 0)).map((x) => (x * x)).reduce((acc, cur) => acc + cur, 0);
console.log(`짝수 제곱 합 = ${result}`);





