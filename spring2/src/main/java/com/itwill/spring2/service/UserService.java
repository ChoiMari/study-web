package com.itwill.spring2.service;

import org.springframework.stereotype.Service;

import com.itwill.spring2.dto.UserCreateDto;
import com.itwill.spring2.dto.UserSignInDto;
import com.itwill.spring2.repository.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor //-> final필드 생성자로 의존성 주입하기 위해서...private final UserDao userDao;에 의존성 주입
@Service //-> 서비스 클래스는 반드시 써주어야 함. 그래야 스프링 컨테이너가 시작될 때 객체 생성해서 서비스 객체를 주입해줌
public class UserService {
    
	// 필요한 필드 선언.
	// 리파지토리(Dao) -> 서비스 import com.itwill.spring2.repository.*;
	private final UserDao userDao;
	
	//회원가입시 아이디 중복 체크 서비스. 리턴값 ture - 중복되지 않은 아이디(사용가능). 
	// 리턴 값 false - 중복된 아이디(사용불가)
	public boolean checkUserid(String userid) {
		log.debug("checkUserid(userid={})", userid);
		
		User user = userDao.selectByUserid(userid);
		//selectByUserid(userid)는
		//select * from users where userid = #{userid}를 실행하는 메서드.<!-- 검색해서 같은 아이디가 있으면 쓸 수 없음. 결과가 null이면 사용가능한 아이디 -->
		//->리턴값: 조건에 맞는 레코드를 찾으면 null이 아닌 유저객체(중복된아이디가있음), null이면 아이디 중복없는것(사용가능)
		
		if(user == null) { //userid가 일치하는 레코드가 없을 때(중복없음.아이디로 사용가능)
			return true;
		} else { //userid가 일치하는 레코드가 있을 때(아이디가 중복된 경우) 사용불가한 아이디.
			return false;
		}
		
	}
	
	//회원 가입 서비스
	//insert된 행의 개수 리턴
	public int create(UserCreateDto dto) {
		log.debug("create({}),dto");
		int result = userDao.insert(dto.toEntity()); //-> 아규먼트 타입 변환만 한 것 UserCreateDto타입을 User타입으로 변환.DB에 insert되면 1리턴
		return result; //return userDao.insert(dto.toEntity()); 해도 상관 없음.
	}
	
    // 로그인 서비스
    public User read(UserSignInDto dto) {
        log.debug("read({})", dto);
        
        User user = userDao.selectByUseridAndPassword(dto.toEntity());
        
        return user;
    }
	
}