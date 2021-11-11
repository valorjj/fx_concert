package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.Concert;

public class ConcertDao {

	// 1.필드
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	// 현재 클래스내 객체 만들기
	private static ConcertDao concertDao = new ConcertDao();
	// 2.생성자
	public ConcertDao() {
		try {
			Class.forName("com.mysql.cj,jdbc,Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/fx_concert?serverTimezone=UTC","root","1234");
		} catch (Exception e) {
			System.out.println("DB연동 실패 : " + e);
		}
	}
	
	// 3.메소드
	public static ConcertDao getConcertDao() {return concertDao;}
	
	// 기능 메소드
	
	// 1.콘서드 등록메소드
	public boolean register(Concert concert) {
		// 1. SQL 작성
		String sql = "insert into concert(c_title,c_artist,c_info) values (?,?,?)";
		// 2. SQL -> DB연결
		try {
			preparedStatement = connection.prepareStatement(sql);
			// 3. SQL 설정
			preparedStatement.setString(1, concert.getC_title());
			preparedStatement.setString(2, concert.getC_artist());
			preparedStatement.setString(3, concert.getC_info());
			// 4. SQL 실행
			preparedStatement.executeUpdate();
			// 5. SQL 결과 실행
		}
		catch(Exception e) {} return false;
	}
	
	// 2.콘서트 수정메소드
	
	// 3.콘서트 삭제 메소드
	
	// 4.콘서트 조회 메소드
	
	// 5.콘서트 좌석수 반환메소드(piechart용)
	
	
}
