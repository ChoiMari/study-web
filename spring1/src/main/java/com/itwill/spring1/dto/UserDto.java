package com.itwill.spring1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//class ExampleController 컨트롤러에서 이용하는 클래스.
//@붙이는 위치는 클래스 앞에만 있으면 된다고 함. @구분은 1개이상의 공백으로 구분하고 ,는 안쓴다고
@Data //->@Getter,@Setter,@ToString,@EqualsAndHashCode,@RequiredArgsConstructor 필요시에 각각 따로따로 써도 된다고 함. 
@NoArgsConstructor //기본 생성자
@AllArgsConstructor // 모든 필드를 초기화 할 수 있는 아규먼트들을 갖는 생성자.
@Builder  //-> 빌더 패턴 만듬
public class UserDto { //.jsp에서 주는 name속성과 이름을 같게 써야한다고 함.자동으로 해주는데 지켜야되야 할것이 있다!
	//jsp(뷰)의 name속성에 모델 클래스에서 선언한 필드이름과 같게 써야지 자동으로 찾아주는데 오류가 안 생긴다! 
	private String username;
	private Integer age; //-> int에서 Integer타입으로 변경.
	//->뷰에서 input태그의 name속성과 같은 이름으로 필드 선언.
} //생성자 getter&setter 등등 @으로 처리 롬복lombok이 자동으로 코드만들어줌. outline탭 보면 알수있음.
