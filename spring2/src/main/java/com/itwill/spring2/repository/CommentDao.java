package com.itwill.spring2.repository;

import java.util.List;

//mapper.xml파일과 연결됨. mapper.xml에서 설정한 sql문 id와 
//동일한 이름으로 메서드 선언하면 mybatis가 자동으로 몸체 구현해 줌.
public interface CommentDao {
	
	//포스트에 달려 있는 모든 댓글들을 검색 하는 기능(메서드)  - 호출 시 아규먼트로 넣어주는 글번호로 검색 
	List<Comment> selectByPostId(Integer postId);
	
	//포스트에 새로운 댓글을 추가(insert하기)하는 메서드
	int insert(Comment comment);//Comment타입의 객체를 주면 거기에 sql문 ? 자리에 넣을 id, username, ctext 포함되어있으니까 
	
	//댓글 내용, 수정 시간을 업데이트 하는 메서드
	int update(Comment comment);
	
	//댓글 아이디를 아규먼트로 받아서 댓글 아이디로 삭제하는 메서드
	int deleteById(Integer id);
	
	//포스트에 달려 있는 모든 댓글을 삭제하는 메서드(한꺼번에 지우기)
	int deleteByPostId(Integer postId);
	
	// 포스트에 달려있는 댓글 개수를 검색하는 메서드
	Integer selectCommentCount(Integer postId);
	
	//댓글 아이디(PK)로 검색하는 메서드
	Comment selectById(Integer id);

}
