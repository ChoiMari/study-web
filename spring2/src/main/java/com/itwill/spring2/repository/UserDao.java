package com.itwill.spring2.repository;

public interface UserDao {
    
    User selectByUserid(String userid); //user-mapper.xml에서 쓴(sql) id와 같은 이름으로 추상 메서드 선언. 몸체는 mybatis가 구현해줌.
    int insert(User user);
    User selectByUseridAndPassword(User user);
}