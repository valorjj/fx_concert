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
			Class.forName("com.mysql.cj.jdbc.Driver");
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
		String sql = "insert into concert(c_title,c_artist,c_info,c_date,c_time,c_R_no,c_S_no,c_D_no,c_E_no,c_R_price,c_S_price,c_D_price,c_E_price) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		// 2. SQL -> DB연결
		try {
			preparedStatement = connection.prepareStatement(sql);
			// 3. SQL 설정
			preparedStatement.setString(1, concert.getC_title());
			preparedStatement.setString(2, concert.getC_artist());
			preparedStatement.setString(3, concert.getC_info());
			preparedStatement.setString(4, concert.getC_date());
			preparedStatement.setString(5, concert.getC_time());
			preparedStatement.setInt(6, concert.getC_R_no());
			preparedStatement.setInt(7, concert.getC_S_no());
			preparedStatement.setInt(8, concert.getC_D_no());
			preparedStatement.setInt(9, concert.getC_E_no());
			preparedStatement.setInt(10, concert.getC_R_price());
			preparedStatement.setInt(11, concert.getC_S_price());
			preparedStatement.setInt(12, concert.getC_D_price());
			preparedStatement.setInt(13, concert.getC_E_price());
			// 4. SQL 실행
			preparedStatement.executeUpdate();
			// 5. SQL 결과 실행
			return true;
		}
		catch(Exception e) {System.out.println(e);} return false;
	}
	
	// 2.콘서트 수정메소드
	
	// 3.콘서트 삭제 메소드
	
	// 4.콘서트 조회 메소드
	
	// 5.콘서트 좌석수 반환메소드(piechart용)
	
	
}
