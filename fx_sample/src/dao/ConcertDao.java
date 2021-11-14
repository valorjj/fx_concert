package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.mysql.cj.protocol.Resultset;

public class ConcertDao {

	//////////////////////////////////////////////////////////////////

	/*
	 * Connection : DB 연결 인터페이스 선언 
	 * PreparedStatement : SQL 연결 인터페이스 선언 
	 */
	
	private Connection conn;
	private PreparedStatement psmt;
	private Resultset res;

	//////////////////////////////////////////////////////////////////

	/*
	 * ConcertDao 객체를 가져온다. 불러올 때 마다 새로운 객체를 생성하는 것이 아니라 static 영역에 저장해두고 필요할 때 호출해서
	 * 사용한다.
	 */

	// 현재 클래스 내 객체를 생성한다. 
	private static ConcertDao concertDao = new ConcertDao();

	// getConcertDao 메소드를 호출하면 위에 생성한 객체가 리턴된다. 
	// 즉 한번 생성된 객체를 다른 클래스에서 새로운 메모리 할당없이 계속해서 사용하는 것이다. 
	public static ConcertDao getConcertDao() {
		return concertDao;
	}

	//////////////////////////////////////////////////////////////////

	// mysql 과 연동하는 메소드
	public ConcertDao() {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/javafx_1?serverTimezone=UTC", "root",
					"1234");
			System.out.println("connection success ...");

		} catch (Exception e) {
			System.out.println("connection failed ...");
		}

	}

}
