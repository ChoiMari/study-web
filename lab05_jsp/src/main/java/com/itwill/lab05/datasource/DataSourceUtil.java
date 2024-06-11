package com.itwill.lab05.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSourceUtil {

	//singleton 구현
	private static DataSourceUtil instance = null;
	
	private HikariConfig config;
	private HikariDataSource ds;
	
	private DataSourceUtil() {
		// HikariCP의 설정(configutation)을 담당하는 객체.
		config = new HikariConfig(); //기본생성자 호출하며 객체 생성함
		
		//커넥션 풀(데이터소스) 환경 설정. setter메서드 호출
		config.setDriverClassName("oracle.jdbc.OracleDriver");
		config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
		config.setUsername("jspstudy"); //오라클 DB 계정의 아이디
		config.setPassword("jspstudy"); //오라클 DB 계정의 비밀번호
		
		//데이터 소스 객체 생성. 데이터 소스에서 커넥션 빌려서 씀
		ds = new HikariDataSource(config);
	}
	
	
	public static DataSourceUtil getInstance() {
		
		if(instance == null) {
			instance = new DataSourceUtil();
		}
		return instance;
	}
	
	//getter메서드라고 보면 됨. 데이터 소스에서 커넥션을 가져온다..
	public HikariDataSource getDataSource() {
		return ds;
	}
	
}


