package com.itwill.lab05.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.datasource.DataSourceUtil;
import com.zaxxer.hikari.HikariDataSource;

//enum으로 생성자 만드는 법 : 1개만 선언하면 객체 생성 1개만 됨.
//MVC 아키텍쳐에서 영속성 계층(repository layer)을 담당하는 클래스
//DB에서 CRUD(Create, Read, Update, Delete) 작업을 담당.
//DAO(Data Access Object)
public enum PostDao {
	INSTANCE; //enum의 생성자는 반드시 private . enum을 이용한 싱글턴 패턴.

	private static final Logger log = LoggerFactory.getLogger(PostDao.class);
	//커넥션들을 가지고 있는 소스.
	private final HikariDataSource ds = DataSourceUtil.getInstance().getDataSource();
	
	//select()메서드에서 실행할 SQL:
	private static final String SQL_SELECT_ALL = "select * from posts order by id desc";
	
	public List<Post> select(){
		log.debug("select()");
		log.debug(SQL_SELECT_ALL);
		
		List<Post> list = new ArrayList<>(); //SELECT 결과를 저장할 리스트		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_ALL);//SQL문장에 ?가 없으니까 넘어감
			rs = stmt.executeQuery();
			
			while(rs.next()) {
//				int id = rs.getInt("id"); //컬럼이름 - DB에서 사용한 타입에 맞게. 
//				String title = rs.getString("title");
//				String content = rs.getString("content");
//				String author = rs.getString("author");
//				//sql의 타임스템프를 자바의 로컬데이트타임으로 변환하는 법 .toLocalDateTime(); 
//				LocalDateTime createdTime = rs.getTimestamp("created_time").toLocalDateTime();
//				LocalDateTime modifiedTime = rs.getTimestamp("modified_time").toLocalDateTime();
//				
//				Post post = Post.builder()
//						.id(id).title(title).content(content).author(author).createdTime(createdTime).modifiedTime(modifiedTime)
//						.build(); //생성자 대신 빌드 패턴으로 초기화
				Post post = fromResultSetToPost(rs); //위의 코드 메서드로 간단히 대체
				list.add(post);
			}//반복문 종료
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs); //아래에 리소스 닫는 문장. 메서드로 대체
		}
			
//			try {
//				rs.close();
//				stmt.close();
//				conn.close();
				
//			} catch (Exception e) {
//				e.printStackTrace();
//			} 
		
		return list;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////
	//posts 테이블에 insert하는 SQL 문장
	private static final String SQL_INSERT = 
			"insert into posts (title, content, author) values (?,?,?)";
	
	
	//insert의 결과 값 int (?행이 삽입되었습니다.) 에서 ?자리.
	public int insert(Post post) { //오라클 DB posts테이블에 저장(insert)시키고 몇개의 행을 삽입했는지 결과값을 리턴하는 메서드.
		//TODO : 해보기
		log.debug("insert({})", post);
		log.debug(SQL_INSERT);
		
	
		Connection conn = null;
		PreparedStatement stmt = null;
		//ResultSet rs = null; 이건 필요 없음..
		int result = 0;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);
		//	rs = stmt.executeQuery(); 이건 필요 없음
			
			//sql문장의 ?자리에 들어갈 값(오라클 DB테이블에 insert할 값)
			stmt.setString(1,post.getTitle());
			stmt.setString(2,post.getContent());
			stmt.setString(3,post.getAuthor());
            result = stmt.executeUpdate(); //성공시 1, 실패하면 0리턴
            
         //   System.out.println(result + "개 행이 삽입되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt); //아래에 리소스 닫는 문장. 메서드로 대체 . rs는 필요없음
		}
		
		return result; //insert가 된 행의 개수 리턴. -> PostTest.java파일에서 testInsert()메서드로 테스트해보기
	}
	
	//////////////////////////////////////////////////////////////////////
	//Delete문장 - 프라이머리키(고유키) id로 행 삭제 하기
	//TODO : posts 테이블에서 id(PK)로 행 1개를 삭제하는 SQL:
	// 오라클 데이터베이스 테이블에서 행을 삭제하고 삭제한 행의 개수를 리턴해주는 메서드.
	private static final String SQL_DELETE = "delete from posts where id = ?";
	
	public int delete(int id) {
		
		//TODO:
		//로그 출력해보는 것.
        log.debug("delete(id={})", id);
        log.debug(SQL_DELETE);
		
		int result = 0;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);
            result = stmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
            closeResources(conn, stmt);
        }
		
		
		return result; //삭제된 행의 개수 리턴
	}
	///////////////////////////////////////////////////////////////////////////
	
	// posts 테이블에서 id(PK)로 검색하는 SQL:
	//id가 일치하는 행 1개만 검색
	//아규먼트로 id를 넘겨주면 그 아이디가 일치하는 행을 Post타입으로 리턴.
	//일치하는 id가 없으면 null을 리턴.
	private static final String SQL_SELECT_BY_ID = "select * from posts where id = ?";
	
	public Post select(int id) {
		log.debug("select(id={}",id);
		log.debug(SQL_SELECT_BY_ID);
		
		//TODO: 
		//지역변수로 가지고 다니는 게 좋다... 필드선언 으로는 별로.. select하는 중간에 클라이언트에서 다른 요청이 들어오면...
		//값처리가 달라짐. 
		List<Post> list = new ArrayList<>();  
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Post post = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
			stmt.setInt(1,id); //sql문에서 1번째 ?에 들어갈 값
			
			rs = stmt.executeQuery();
			
			if(rs.next()) { //while(rs.next()) 반복문 필요 없음 어차피 결과가 있든가 없든가라서.. where조건에 맞든지 안맞든지 결과 1개
				post = fromResultSetToPost(rs); //메서드로 간단히 대체
				list.add(post);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
		
		return post;
	}
	
	
	
	
	//////////////////////////////////////////////////////////////////////////////
	private Post fromResultSetToPost(ResultSet rs) throws SQLException {
		//throws SQLException이유. while문안에 쓴 코드 대체하려고. while문은 어차피 try에 묶여질 코드들이여서.
		int id = rs.getInt("id");
		String title = rs.getString("title");
		String content = rs.getString("content");
		String author = rs.getString("author");
		LocalDateTime createTime = rs.getTimestamp("created_time").toLocalDateTime();
		LocalDateTime modifiedTime = rs.getTimestamp("modified_time").toLocalDateTime();
		
		return Post.builder()
				.id(id)
				.title(title)
				.content(content)
				.author(author)
				.createdTime(createTime)
				.modifiedTime(modifiedTime)
				.build();
		
		//return null;
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	
	//리소스 해제하는 메서드
	private void closeResources(Connection conn, Statement stmt, ResultSet rs) {
		try {
			//DB 지원들을 해제하는 순서는 생성된 순서의 반대로 해제한다.
			//ResultSet -> Statement -> Connection
			//ResultSet이 null일수도 있어서 null인데 메서드 호출하면 널포인트예외발생
			//그래서 검사한다고..
//			rs.close();
//			stmt.close();
//			conn.close();
			//null이면 실행하지 않음
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	//오버로딩 
	private void closeResources(Connection conn, Statement stmt) {
		closeResources(conn, stmt, null); //-> 2개의 리소스를 닫는 매서드. 위의 메서드 호출해서 2개만 닫음
	}
	
	////////////////////////////
	private static final String SQL_UPDATE =
			"update posts set title = ?, content = ?, modified_time = systimestamp "
			+ "where id =?";
	// title을 ?값으로 변경하겠다, content를 ?값으로 변경하겠다 modified_time컬럼을 시스템의 현재시간으로 변경하겠다.
	
	public int update(Post post) {
		log.debug("update({})",post);
		log.debug(SQL_UPDATE);
		
		//커넥션 생성
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE);
			stmt.setString(1,post.getTitle());//sql문장의 1번째 ? 채움
			stmt.setString(2,post.getContent());//sql문장의 2번째 ? 채움
			stmt.setInt(3,post.getId());//sql문장의 3번째 ? 채움
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			closeResources(conn, stmt);
		}
				
		
		return result;
	}
}
