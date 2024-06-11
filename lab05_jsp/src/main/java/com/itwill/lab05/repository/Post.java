package com.itwill.lab05.repository;

import java.time.LocalDateTime;

// MVC 아키텍쳐에서 Model 역할 클래스. DB의 posts 테이블 구조와 같은 클래스.
public class Post {
	private Integer id;
	private String title;
	private String content;
	private String author;
	private LocalDateTime createdTime; //오라클 DB 테이블에서의 컬럼 이름 : created_time
	private LocalDateTime modifiedTime; //오라클 DB 테이블에서의 컬럼 이름 : modified_time
	
	//기본 생성자
	public Post() {}

	public Post(Integer id, String title, String content, String author, LocalDateTime createdTime,
			LocalDateTime modifiedTime) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.author = author;
		this.createdTime = createdTime;
		this.modifiedTime = modifiedTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	public LocalDateTime getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(LocalDateTime modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", content=" + content + ", author=" + author + ", createdTime="
				+ createdTime + ", modifiedTime=" + modifiedTime + "]";
	}
	
	// builder 패턴
	//객체 생성의 단점을 보완하기 위해서 만듬.
	//생성자가 아규먼트가 넘 많음.
	//스트링 아규먼트 여러개.. 순서가 중요함.. 순서 바꾸어 버리면...... 잘못 초기화 됨..
	//생성자는 파라미터가 선언된 순서대로만 전달해야되는데
	//그래서 아규먼트 전달 순서와 상관없이, 개발자의 맘대로 
	// 좀 어렵게 느껴진다고 함.....
	
	//메서드
	public static PostBuilder builder() {
		return new PostBuilder(); //메서드 호출해서 내부클래스에 있는 객체 생성
	}
	
	//빌더패턴은 내부 클래스로 설계
	public static class PostBuilder {
		//외부클래스 필드와 똑같이 선언
		private Integer id;
		private String title;
		private String content;
		private String author;
		private LocalDateTime createdTime; //오라클 DB 테이블에서의 컬럼 이름 : created_time
		private LocalDateTime modifiedTime; //오라클 DB 테이블에서의 컬럼 이름 : modified_time
		 
		//private로 감추어 있지만 외부클래스에서 호출 가능(91번코드)
		private PostBuilder() {}
		
			//getter의 역할
		public PostBuilder id(Integer id) {
			this.id = id;
			return this;// 메서드를 연쇄적으로 호출 가능함. 
		}
		
		public PostBuilder title(String title) {
			this.title = title;
			return this;
		}
		
		public PostBuilder content(String content) {
			this.content = content;
			return this;
		}
		
		public PostBuilder author(String author){
			this.author = author;
			return this;
		}
		
		public PostBuilder createdTime(LocalDateTime createdTime) {
			this.createdTime = createdTime;
			return this;
		}

		public PostBuilder modifiedTime(LocalDateTime modifiedTime) {
			this.modifiedTime = modifiedTime;
			return this;
		}
		
		public Post build() {
			return new Post(id,title,content,author,createdTime,modifiedTime);
		}
	}
	
	
}
