package com.itwill.spring1.web;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwill.spring1.dto.UserDto;

import lombok.extern.slf4j.Slf4j;

//POCO(Plain Old C# Object)
//POJO(Plain Old Java Object) : 간단하고 오래된 자바 객체
//간단한 오브젝트. 특정 클래스를 상속하거나 특정 인터페이스를 구현(implements)할 필요가 없는
//(상위 타입의 특정 메서드들을 반드시 재정의(오버라이드) 할 필요가 없는) 평범한 자바 객체.
//스프링 MVC 프레임 워크에서는 POJO로 작성된 클래스를 컨트롤러로 사용할 수 있다!
//(비교) HttpServlet을 상속받는 클래스 에서는 doGet, doPost(res,resp)를
// 반드시 재정의(오버라이트) 해야 웹 서비스(요청 처리)가 가능.

//@ 애너테이션 import해야함. @으로 핸들러 매핑이 관리한다고 함.
@Slf4j //->private static final Logger log = LoggerFactory.getLogger(ExampleController.class); 코드를 삽입.롬복에서 제공하는 애너테이션
@Controller //스프링 프레임 워크에서 제공하는 애너테이션. 디스패쳐 서블릿에게 컨트롤러 컴포넌트임을 알려주는 애너테이션.
//-> 1.Servlet-context.xml 파일에서는 <context:component-scan ... /> 설정
//   2.컨트롤러 클래스 에서는 @Controller 애너테이션을 사용
// 1,2 둘 다 되어있어야 객체 생성이 됨.
//-> 디스패쳐 서블릿이 컨트롤러 객체를 생성하고 관리함. 
public class ExampleController {
	
	
	//디스팩쳐 서블릿이 요청이 들어오면 이 메서드를 호출해줌
	//디스팩쳐 서블릿이 모델객체 생성하고 메서드 호출하면서 아규먼트로 모델을 줌.
	//컨트롤러가 그 모델에가 시간이라는 값을 실음. 리턴이 됨.
	//처음에 만들어진 모델 객체에 시간이라는 정보를 add애트리뷰트 그럼 시간이라는 정보를 알고있는 모델 객체가됨
	//리턴하면서 문자열(home)을 리턴함 -> 이게 뷰가 됨.
	// 디스팩쳐 서블릿이 뷰리절버한테 물어본다 뷰가 어디에 저장되어있는지.
	//뷰리절버는 서블릿 컨텍스트.xml에 있음 거기에 설정시켜두어서 찾을수 있는 법을 알려주었음.
	//앞에 붙는 경로 /WEB-INF/views/, 뒤에 붙는 확장자 .jsp 설정해둠.
	@GetMapping("/") // 요청방식 Get, 요청주소 / ->컨텍스트루트로 들어오는 요청(컨트롤러의 / 의미)
	public String home(Model model) { //모델객체 : 뷰에 전달되는 데이터. 컨트롤러가 jsp(뷰)에 전달하는 데이터. MVC의 모델이 아님.
		//파라미터 선언을 안해도 없는대로 호출해줌
		
		log.debug("home()");//log변수 만든적 없지만 @Slf4j을 사용해서 사용 가능함.
		
		LocalDateTime now = LocalDateTime.now();
		model.addAttribute("now", now);
		//Model 객체는 컨트롤러에서 뷰로 데이터를 전달할 때 사용.
		//request.setAttribute(name,object)와 비슷한 기능.
		
		return "home";///WEB-INF/views/까지만 설정 되어있어서 만약 views에 폴더를 만들면 reture 만든폴더/home 이렇게 써야함.
		//-> 컨트롤러 메서드가 문자열을 리턴하면, 디스패쳐 서블릿이 뷰의 이름을 찾는 용도로 사용.
		//디스패처 서블릿이 뷰 리졸버를 이용해서 /WEB-INF/views/resturnValue.jsp 이용해서 경로를 찾을 수 있음.
		//서블릭-컨텍스트.xml파일에 포함시킴
		//앞뒤로 앞에 WEB-INF/views/ 접두사 접미사.jsp 붙여줌
	}
	
	//링크 클릭 Get방식 요청 ->@GetMapping("/example") @아규먼트로 컨텍스트루트를 제외한 요청 주소쓰면 됨.
	@GetMapping("/example")
	public void controllerExample() { //리턴 타입 void. 컨트롤러 메서드가 문자열을 리턴하지 않는 경우.
		//컨트롤러 메서드가 리턴값이 없는 경우(void로 선언) 요청주소가 뷰의 이름이 된다.
		//리턴값을 주지 않으면 뷰의 이름에 요청주소를 넣음
		log.debug("controllerExmaple()");
	}
	
	//리쿼스트 파라미터에서 아규먼트로 age를 줘 라는 의미(.jsp파일에서 input태그에 name속성 준것에 대해서). 리퀘스트 파라미터에서 왔다고 치고 할일 코드 작성하면 된다고.
	//리퀘스트 파라미터 안에 age가 있을건데 그걸 age라는 파라미터 안에 넣어달라는 의미.
	//그래서 리퀘스트.겟 파라미터 할 필요가 없다고 함.
	//리쿼스트, 포워드 디스패쳐 서블릿이 다 해준다고..
	//get방식에서도 Dto써도 된다고 함.
	//defaultValue = "0" 사용자가 브라우저에서 빈값으로 넣어주면 예외 발생해서... 빈 문자열을 int타입으로 변환 불가.
	//defaultValue = "0"로 하면 단점이 defaultValue = "0"로 넣어줄 상황이 많아지면 파라미터 선언이 길어진다고함.. 그래서 밑의 Post방식에서 처리한것 처럼 사용..?? 
	@GetMapping("/ex1")  // 파라미터 선언 username이 String이 되길 바래서 String username, age가 int타입이 되길 바래서 int age로 선언함
	public void example1(@RequestParam(name = "username") String username,
			@RequestParam(name="age", defaultValue = "0") int age,
			Model model) { //리턴값없음 -> 요청주소가 뷰의 이름이 됨. 그럼 ex1.jsp로 만들어야 브라우저 화면에 보임
		log.info("example1(username={}, age={})", username, age); 
		// 컨트롤러 메서드 파라미터를 선언할 때 @RequestParam 애너테이션을 사용하면,
		// 디스패쳐 서블릿이 이 컨트롤러 메서드를 호출할 때, 
		// (1) request.getParameter("username"), request.getParameter("age")호출해서
		// 요청 파라미터 값들을 읽고,
		// (2) 컨트롤러 메서드의 아규먼트로 전달해 줌.
		// 리퀘스트.겟파라미터 -> 늘 String 타입으로 리턴해주는데 선언만 하면 타입 변환까지 디스패쳐서블릿이 자동으로 해준다고 함.
		//속성이 name뿐만 아니라 디폴트 벨류도 있다고 함. 
		
		//요청 파라미터 값들로 UserDto 객체를 생성 (생성자 호출 대신 빌드 패턴 사용)
		UserDto user = UserDto.builder()
						.username(username)
						.age(age)
						.build();
		
		//UserDto객체를 뷰(jsp)로 전달(하기 위해서 메서드에 파라미터로 Model model선언 추가함)
		model.addAttribute("user", user); //아규먼트에 넣은 객체(user)가 뷰로 전달이 됨.
		
	}
	
	@PostMapping("/ex2") //post방식으로 매핑 post방식이면 이 메서드로 처리됨.
	public String ex2(@ModelAttribute(name = "user") UserDto dto) { 
		//원래 파라미터 선언  public String ex2(UserDto dto, Model model)이었음.
		//메서드의 아규먼트로 클래스 타입을 줌. 
		//디스펙쳐 서블릿이 메서드를 호출하려면 아규먼트로 그 클래스의 객체(UserDto)가 있어야함.
		//그래서 객체를 생성해야 하는데 기본 생성자가 있어야 함. 
		//아규먼트 여러개 있는 생성자의 문제점. 생성자 호출하는 입장에서는 타입만 알지 타입이 같은게 여러개인 경우에는 구분을 못함....
		//그래서 대부분의 프레임 워크에서는 기본생성자로 호출한다고 함. 그리고 필드값을 채우기 위해서 setter를 호출한다고...
		//변수이름을 자바의 관습에 맞춰서 찾기 때문에. 리퀘스트 파라미터 이름으로 찾는다고.
		//그래서 디스패쳐서블릿이 메서드 호출 할 때 UserDto의 기본생성자 호출하고 리퀘스트 파라미터에서
		//앞에 set(을 붙여서) +  username 이라고 호출한다고
		//name속성을 UserDto에서 선언한 필드 이름과 같게 주어야 함. 
		//지켜야 될것 
		// 1. 기본 생성자가 있어야 함.
		// 2. setter메서드가 있어야 하고 이름을 name속성(겟파라미터)과 같게 해야 자동으로 잘 찾아준다고 함.
		log.debug("ex2(dto={})",dto);
		//디스패쳐 서블릿은 컨트롤러 메서드를 호출하기 위해서
		//UserDto 클래스의 기본 생성자를 호출하고, 요청 파라미터 이름으로
		//setter를 찾아서 호출해줌.
		//생성된 객체를 컨트롤러 메서드의 아규먼트로 전달.
		//model.addAttribute("user", dto);//jsp에서 EL을 user라고 주어서 ,디스패쳐 서블릿한테 UserDto객체를 만들라는 뜻 만들어서 아규먼트 넣어서 호출해달라.
		//-> 메서드 파라미터 선언에서(@ModelAttribute(name = "user") UserDto dto)로 대체함.
		//model.addAttribute("user", dto); 와 메서드 파라미터 선언의 (@ModelAttribute(name = "user") UserDto dto);는 같은 효과
		//컨트롤러에서 뷰로 전달하는 데이터
		
		return "ex1"; //->뷰로 ex1.jsp를 사용하겠다 //-> 뷰 이름을 ex1.jsp로 찾을 수 있게 리턴값을 줌
	}
	
	@GetMapping("/test")
	public void test() { //리턴 타입 void. 그럼 요청주소가 뷰이름이 되서 뷰를 test.jsp로 만들면 됨
		log.debug("test()"); //void면 뷰(뷰이름을)를 요청주소로 찾음.
	}
	
	//포워드방식의 페이지 이동 방법
	@GetMapping("/test2") //Get방식의 컨텍스트루트/test2 의 url 요청을 처리하는 메서드
	public String forward() {
		log.debug("forward()");
		
		return "forward:/test"; // 앞에 접두사가 있어서 이건 뷰의 이름이 아니야 라고 생각해서 /test로 포워드한다고 함
		//-> (해당)컨트롤러 메서드가 "forward:"으로 시작하는 문자열을 리턴하면(뷰(jsp)를 찾는것이 아니라)  -> 포워드 방식으로 이동이 된다 /test로 이동함
		//포워드 방식의 페이지 이동은 최초 요청 주소가 바뀌지 않는다
		//앞에 접두사로 forward:/요청주소를 붙이면 디스패처 서블릿이 해당 요청주소로 포워드를 해버림. test라는 이름으로 포워딩 됨.
		//최초 요청 주소가 바뀌지 않아서 url은 /test2이지만 화면은 포워드해서 /test가 처리할거야 해서 /test화면(test.jsp)으로 보임
	}
	
	//리다이렉트방식의 페이지 이동 방법
	@GetMapping("/test3") //원래 /test3 의 url 요청을 처리하는 메서드지만 return "redirect:/test"; 로 처리해서 리다이렉트 됨. /test로 페이지 이동
	public String redirect() {
		log.debug("redirect()");
		
		return "redirect:/test"; // 컨트롤러 메서드가 "redirect:으로 시작하는 문자열을 리턴하면 리다이렉트 이동이 된다. 
		//리다이렉트방식의 페이지 이동은 요청 주소가 바뀜.
		//최초 요청 주소가 리다이렉트 되는 주소로 바뀜.
		//새 글 작성이나, 등에서 사용함 (/post/create) ->리다이렉트:/post/list 로 리턴 
		//요청 /test3 302-> 응답 redirect:/test -> 요청 /test -> 응답
		//리턴값으로 접두사 redirect:를 붙여서 redirect:/요청주소라고 쓰면 해당 요청주소로 리다이렉트 됨.
		//요청주소가 /test3으로 매핑했지만 리다이렉트해서 최초 요청주소가 바뀌어서 /test로 요청주소가 바뀌었음.
	}
	
	@GetMapping("/rest1")
	@ResponseBody //-> 컨트롤러 메서드가 리턴하는 값이 뷰를 찾기 위한 문자열이 아니라,
	//-> 클라이언트로 직접 응답 되는 데이터라는 의미일 때 이 애너테이션을 사용함.
	//응답 패킷(response packet)의 몸통(body)에 포함되는 데이터.
	public String rest1() {
		log.debug("rest1()");
		
		return "Hello. 안녕하세요!"; 
		//하고 싶은 것.
		//-> jsp를 거지치 않고 문자열 자체를 클라이언트로 보내고 싶음
		
	}
	
	@GetMapping("/rest2")
	@ResponseBody //-> 리턴값이 클라이언트로 직접 응답 되는 객체라는 뜻.
	public UserDto rest2() { //리턴 타입 UserDto타입 넘기겠다
		log.debug("rest2()");
		return UserDto.builder().username("홍길동").age(16).build(); 
		//자바 객체를 제이슨 형식으로 변환해서 클라이언트로 전달?
		//->자바 객체를 리턴값으로 주었지만 클라이언트로 내려가는건 문자열.
		//편의상 rest컨트롤러라고 부른다고 함.
		//REST 컨트롤러가 리턴한 자바 객체를 우리가 포함시킨 라이브러리(pom.xml에서 포함시킴)
		//jackson-databind 라이브러리에서
		//JSON(JavaScript Object Notation)형식의 문자열로 변환하고,
		//클라이언트로 응답을 보냄.
		//제이슨 형식의 문자열을 클라이언트로 보내게 되면
		//자바 스크립트객체로 만들어낸다고 자바스크립트가 화면에 보여주기 위한 HTML을 그릴수 있다고 함.
		//->컨트롤러 메서드 앞에 @ResponseBody사용하면 리턴 값으로 뷰를 찾지 않음.클라이언트로 직접 문자열로 응답함.
		//이런 방식을 rest라고 한다고.. 최근에 많이 사용한다고.
		//어떻게 리턴해야하는 지 알아야한다고 함. 컨트롤러 @ResponseBody붙여주면 제이슨형식의 문자열로 내려간다고
		//pom.xml에 라이브러리를 추가해야 이렇게 쓸 수 있음.
	}
	

}
