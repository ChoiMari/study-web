/**
 * object.html에 포함.
 */

 //JSON(JavaScript Object Notaion) : 자바스크립트 객체 표기법.
 //{property: value, …}
 
 const person ={
    name:'홍길동',
    age:16,
    phone:['010-0000-0000','02-0000-0000'] //phone은 배열
 };
 
 console.log(person);
 
 // 객체가 가지고 있는 프로퍼티 접근법 2가지
 //1. 참조 연산자를 사용 하는 방법
 console.log(person.name); //-> . 참조 연산자 사용 : object.propertyName
 
 //2. 인덱스 연산자를 사용 하는 방법
 console.log(person['age']);//-> 인덱스 연산자 사용. : object['propertyName']
 //(주의) []안에 문자열로 써야한다고.. 그래서 propertyName을 ''로 감쌈.
 
 console.log(person.phone[0]);//person객체의 phone 배열의 인덱스 0번.
 //person['phone'][0] 과 같음.
 
 person.age = 17; //객체의 프로퍼티 값 변경.
 console.log(person);
 
 //자바스크립트의 객체는, 객체가 생성된 이후에 프로퍼티를 추가할 수 있음.
 person.email = 'hgd@itwill.com'; //person객체에 email 프로퍼티를 추가함.
 console.log(person);
 
 //person 객체 const로 만듬. 객체는 그대로고 객체 안의 프로퍼티가 변경 된 것.
 // 객체 자체가 변한 건 아님.
 //person 변수 자체에 새로운 값 할당은 안됨.
 
 // 메서드를 갖는 객체 :
 const score = {
   html: 100,
   css: 90,
   js: 82,
   sum: function() {
    // 객체의 프로퍼티를 접근할 때는 this 키워드를 사용.
    return this.html + this.css + this.js; //score객체의 html프로퍼티라는 뜻.
   } ,
   mean : function() {
    return this.sum() / 3;
   }
 };
 
 //객체에서는 값을 :으로 구분함.
 //score이라는 객체 안에 프로퍼티 sum과 mean 안에 function(){}이라는 메서드가 들어감.
 
 console.log(score);
 console.log(score.sum()); //메서드 호출. 프로퍼티이름뒤에()붙이면 됨.
 console.log(score.mean());
 
 //문제. 이 방식으로는 코드 재활용 못 한다고
 
 //-> 그래서 생성자 함수가 나옴.
 
 //생성자 함수(constructor function) : this 키워드를 사용해서 프로퍼티를 선언한 함수.
 function Score (html, css, js) { //호출시 넣는 아규먼트로 프로퍼티를 초기화 한다는 뜻 
    //파라미터와 구분하기 위해 this 사용.
    //필드
    this.html = html;
    this.css = css;
    this.js = js;
    
    //메서드
    this.sum = function() {
       return this.html + this.css + this.js; 
    };  
    
    this.mean = function () {
        return this.sum() / 3;    
    };
 }
 
 // 생성자 함수를 사용한 객체 생성 : new 생성자함수();
 const score1 = new Score(1,2,3);
 console.log(score1); 
 console.log(score1.sum());  
 console.log(score1.mean());  
 
 const score2 = new Score(); //-> 모든 필드는 undefined가 된다. 생성자 호출시 아규먼트 안 주어서
 console.log(score2); //-> 필드 3개 다 undefined. 생성자 호출시 아규먼트를 안 주었음.
 console.log(score2.sum); //-> NaN 숫자가 아니다.  undefined + undefined = NaN(Not a Number)
 
 //자바스크립트 객체는 for-in 구문에서 사용할 수 있음 ->  프로퍼티 이름들을 interation
 const student = {
    no: 101,
    name: '오쌤',
    classNo: 10
 };
 
 for (let x in student) {
    console.log(x);//-> student 객체가 가지고 있는 프로퍼티 이름이 출력됨.(no,name,classNo)
    console.log(x,':',student[x]); //let x는 문자열을 꺼낸 것. student.x는 안됨. student객체의 x가 있다는 말이여서..
    //문자열을 꺼내는 거라서..[]대괄호를 사용하는 인덱스로 사용해야함. 참조 연산자로 접근 X 
    //let x -> 'No' 가 들어가고 'name','classNo'이렇게 문자열이 들어가는 거라고..
 } 
 
 
 //자바스크립트의 클래스 :
 //클래스 선언 방법
 class Rectangle {
    // 생성자의 역할 : 필드 초기화
    constructor(width, height) { //constructor라는 키워드로 생성자 만들면 됨.
        this.width = width;
        this.height = height;
    } 
    //메서드
    //메서드이름() {}형식으로 만듬. 클래스 안에서 메서드를 만들 때는 function 키워드를 사용하지 않는다.
       area() { //function area라고 선언하면 문법 오류. 변수에 함수 저장하고 그런거 없다고..
        return this.width * this.height; //this생략 불가.
       }
       
       perimeter() {
        return (this.width * this.height) * 2;
       }
       
 }
 
 //클래스를 사용한 객체 생성
 const rect1 = new Rectangle(2, 3);
 console.log(rect1);
 console.log(`넓이 = ${rect1.area()}`);
 console.log(`둘레 = ${rect1.perimeter()}`);
 
 
 const rect2 = new Rectangle();
 console.log(rect2);
 
 //원(Circle) 클래스 선언 :
 //- 필드 : radius. 기본값 0 //-> 생성자를 호출할 때 아규먼트를 넘기지 않아도 undefined가 되지 않고 
 // 기본값으로 0이 나오게 해보자.
 //- 메서드 : area(넓이), 둘레 길이(perimeter)
 
 class Circle {
    constructor(radius = 0) {
    this.radius = radius;
    }
    area() {
        return this.radius * this.radius * 3.14;
    }
    perimeter() {
        return 2 * 3.14 * this.radius;
    }
 }
 
 const c1 = new Circle();//-> 생성자의 default parameter가 사용되는 경우
 console.log(c1); 
 
 const c2 = new Circle(10); 
 console.log(c2.area());
 console.log(c2.perimeter());
 
 
 

 
 
 
 
 
 
 
 
 
 
 
 