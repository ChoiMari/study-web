package com.itwill.lab05.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.datasource.DataSourceUtil;
import com.zaxxer.hikari.HikariDataSource;

// DAO(Data Access Object). 데이터베이스 CRUD.
public enum UserDao {
    INSTANCE; //-> 싱글턴 enum으로 선언.변수1개만 선언. 객체 1개만 생성됨.
    
    private static final Logger log = LoggerFactory.getLogger(UserDao.class);
    
    private final HikariDataSource ds = DataSourceUtil.getInstance().getDataSource();

    private static final String SQL_INSERT = 
            "insert into users (userid, password, email) values (?, ?, ?)";
    
    public int insert(User user) {
        log.debug("insert({})", user);
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int result = 0;
        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, user.getUserid());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            result = stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
        
        return result;
    }
    
    
    private static final String SQL_SIGN_IN =
    		"select * from users where userid = ? and password = ?"; //2개 다 같아야지 리턴되는 행이 있음.(아이디와 패스워드)
    /////////////////////
    public User selectByUseridAndPassword(User user) { //파라미터 선언 String userid, String password해도 됨  (선생님은 이 2개만 저장하는 클래스를 만들것 같다고 하심)
    	log.debug("selectByUseridAndPassword({})",user); //실행흐름을 보기위한 로그 출력
    	log.debug(SQL_SIGN_IN);
    	
    	Connection conn = null;
    	PreparedStatement stmt = null;
    	ResultSet rs = null; //->select시에만 사용
    	
    	User result = null;//->리턴값으로 줄 지역변수 선언&초기화(타입의 기본값으로)
    	try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_SIGN_IN);
			
			stmt.setString(1, user.getUserid());
			stmt.setString(2, user.getPassword());
			
			rs = stmt.executeQuery(); 
			//resultset에 다음 행이 있느냐 없느냐 검사 next 
			//아이디 비밀번호는 1개. 특정인거여서 if 
			//전체에서 찾을땐 반복문
			if(rs.next()) { //행이 있으면 실행되는 문장.오라클 DB에 sql문장을 실행해서 행이 있으면 true 실행. 없으면 false 실행 안함.
				result = fromResultSetToUser(rs);//메서드를 호출. 실행해서 그 리턴값을 User타입의 변수 result에 담음
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt,rs); //리소스 닫는 메서드 만들었던걸로 대체
		}
    	
    	return result;
    }
    
    // USERS.POINTS 업데이트 SQL 문장:
    private static final String SQL_UPDATE_POINTS = 
            "update users set points = points + ? where userid = ?";
    
    public int updatePoints(String userid, int points) {
        log.debug("updatePoints(userid={}, points={})", userid, points);
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int result = 0;
        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_POINTS);
            stmt.setInt(1, points);
            stmt.setString(2, userid);
            result = stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
        
        return result;
    }
    
    //TODO : 로그인한 아이디로 내정보 DB에서 읽어오는 메서드
    private static final String SQL_SELECT_BY_USERID = 
            "select * from users where userid = ?";
    
    public User selectByUserid(String userid) {
        log.info("selectByUserid(userid={})", userid);
        log.info(SQL_SELECT_BY_USERID);
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_USERID);
            stmt.setString(1, userid);
            rs = stmt.executeQuery();
            if (rs.next()) {
                user = fromResultSetToUser(rs);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        
        return user;
    }
    
    
    /**
     * 로그인할 때 필요한 메서드
     * @param user 로그인을 시도한 userid,password를 저장한 객체.
     * @return 데이터베이스의 users 테이블에서 userid와 password가(둘 다) 일치하는 레코드가
     * 있으면 null이 아닌 User 타입 객체를 리턴하고 userid 또는 password가 일치하지 않으면(둘 중 1개라도 틀리면)
     * null을 리턴. 아이디, 비밀번호 둘 다 일치해야 로그인 하니깐!!!
     * @throws SQLException
     */
    private User fromResultSetToUser(ResultSet rs) throws SQLException {
    	int id = rs.getInt("id"); //컬럼이름을 아규먼트로, throws SQLException로 묶음 어차피 이 메서드 호출될때 try로 묶인다고
    	String userid = rs.getString("userid");
    	String password = rs.getString("password");
    	String email = rs.getString("email");
    	int point = rs.getInt("points");
    	
    	return User.builder() //빌더패턴으로 초기화(static으로 선언되어있어서 new할 필요없음)
    			.id(id)
    			.userid(userid)
    			.password(password)
    			.email(email)
    			.points(point)
    			.build(); //->User타입으로 new User모델객체 생성자 호출(초기화)하며 리턴.
    }
    
    /////////////////
    
    private void closeResources(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void closeResources(Connection conn, Statement stmt) {
        closeResources(conn, stmt, null);
    }
}

 
//내가 한 것 
//package com.itwill.lab05.repository;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.itwill.lab05.datasource.DataSourceUtil;
//import com.zaxxer.hikari.HikariDataSource;
//
////DAO(Data Access Object) 데이터 베이스 CRUD.
//public enum UserDao {
//	INSTANCE;
//
//	private static final Logger log = LoggerFactory.getLogger(UserDao.class);
//
//	//여기서 커넥션 맺음 된다고.. get커넥션..
//	private final HikariDataSource ds = DataSourceUtil.getInstance().getDataSource();
//
//	//TODO: users 테이블에 insert SQL 문장 & 메서드
//	private static final String SQL_INSERT =
//			"insert into users (userid,password,email) values (?,?,?)";
////			"insert into users (id,userid,password,email,points) values (?,?,?,?,?)";
//
//	//insert 시키는 메서드
//	//insert의 결과 값 int (?행이 삽입되었습니다.) 에서 ?자리.
//	public int insert(User user) { //오라클 DB posts테이블에 저장(insert)시키고 몇개의 행을 삽입했는지 결과값을 리턴하는 메서드.
//		//TODO : 해보기
//		log.debug("insert({})", user);
//		log.debug(SQL_INSERT);
//
//
//		Connection conn = null;
//		PreparedStatement stmt = null;
//		//ResultSet rs = null; 이건 필요 없음..
//		int result = 0;
//
//		try {
//			conn = ds.getConnection();
//			stmt = conn.prepareStatement(SQL_INSERT);
//		//	rs = stmt.executeQuery(); 이건 필요 없음
//
//			//sql문장의 ?자리에 들어갈 값(오라클 DB테이블에 insert할 값)
//			//stmt.setInt(1,user.getId());
//			stmt.setString(1,user.getUserid());
//			stmt.setString(2,user.getPassword());
//			stmt.setString(3,user.getEmail());
//			//stmt.setInt(5,user.getPoints());
//            result = stmt.executeUpdate(); //성공시 1, 실패하면 0리턴
//
//         //   System.out.println(result + "개 행이 삽입되었습니다.");
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			closeResources(conn, stmt); //아래에 리소스 닫는 문장. 메서드로 대체 . rs는 필요없음
//		}
//
//		return result; //insert가 된 행의 개수 리턴. -> PostTest.java파일에서 testInsert()메서드로 테스트해보기
//	}
//
//
//
//
//
//	//////////////////////////////////////////////////////////////////////////////
//	private User fromResultSetToPost(ResultSet rs) throws SQLException {
//		//throws SQLException이유. while문안에 쓴 코드 대체하려고. while문은 어차피 try에 묶여질 코드들이여서.
//		//int id = rs.getInt("id");
//		String userid = rs.getString("userid");
//		String password = rs.getString("password");
//		String email = rs.getString("email");
//		//int points = rs.getInt("points");
//
//		return User.builder()
//			//	.id(id)
//				.userid(userid)
//				.password(password)
//				.emain(email)
//			//	.points(points)
//				.builder();
//
//		//return null;
//	}
//
//	///////////////////////////////////////////////////////////////////////////////////
//
//	//리소스 해제하는 메서드
//	private void closeResources(Connection conn, Statement stmt, ResultSet rs) {
//		try {
//			//DB 지원들을 해제하는 순서는 생성된 순서의 반대로 해제한다.
//			//ResultSet -> Statement -> Connection
//			//ResultSet이 null일수도 있어서 null인데 메서드 호출하면 널포인트예외발생
//			//그래서 검사한다고..
////			rs.close();
////			stmt.close();
////			conn.close();
//			//null이면 실행하지 않음
//			if(rs != null) rs.close();
//			if(stmt != null) stmt.close();
//			if(conn != null) conn.close();
//
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		}
//	}
//
//	//오버로딩 
//	private void closeResources(Connection conn, Statement stmt) {
//		closeResources(conn, stmt, null); //-> 2개의 리소스를 닫는 매서드. 위의 메서드 호출해서 2개만 닫음
//	}
//}