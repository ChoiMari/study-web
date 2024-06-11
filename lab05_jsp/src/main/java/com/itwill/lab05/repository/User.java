package com.itwill.lab05.repository;



//Model, users테이블
public class User {

	private Integer id;
	private String userid;
	private String password;
	private String email;
	private Integer points;

	public User() {}

	public User(Integer id, String userid, String password, String email, Integer points) {
		this.id = id;
		this.userid = userid;
		this.password = password;
		this.email = email;
		this.points = points;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userid=" + userid + ", password=" + password + ", email=" + email + ", points="
				+ points + "]";
	}

	//TODO: builder 패턴 만들기. static으로 만들어야 user가 생기기전에 이미 만들어져있어야 호출이 가능
	//builder 메서드. 유저생성(객체)전 호출할거면 static을 붙여야!
	public static UserBuilder builder() {
		return new UserBuilder(); //메서드 호출해서 내부클래스에 있는 객체 생성
	}
	
	//내부 클래스 static으로 만들어야 user가 생기기전에 이미 만들어져있어야 호출이 가능
	public static class UserBuilder {
		private Integer id;
		private String userid;
		private String password;
		private String email;
		private Integer points;

		private UserBuilder() {}

		public UserBuilder id(Integer id) {
			this.id = id;
			return this; //메서드를 연쇄적으로 호출하기 위해서 return값을 this로 함 
		}
		public UserBuilder userid(String userid) {
			this.userid= userid;
			return this;
		}
		public UserBuilder password(String password) {
			this.password = password;
			return this;
		}
		public UserBuilder email(String email) {
			this.email= email;
			return this;
		}
		public UserBuilder points(Integer points) {
			this.points = points;
			return this;
		}
		public User build() {
			return new User(id,userid,password,email,points); //외부 클래스의 생성자 호출해서 초기화.
		}
	}
	//=> 이걸 이용한 Dao만들기 PostDao


}