
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.Seat;

public class SeatDao {

	// 1. 필드
		private Connection connection;
		private PreparedStatement preparedStatement;
		private ResultSet resultSet;
		// 현재 클래스내 객체 만들기
		private static SeatDao seatDao = new SeatDao();
		
		// 2. 생성자
		public SeatDao() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/javafx_concert?serverTimezone=UTC","root","1234");
			} catch (Exception e) {	
				System.out.println("DB연동 실패 : " + e);
			}
		}
		// 3.메소드
		public static SeatDao getSeatDao() {return seatDao;}
		
		// 기능 메소드
		
		// 1. 자리등록 메소드
//		public boolean register(Seat seat) {
//			// 1. SQL 작성
//			String sql = "insert into seat(s_no,s_status,s_price) values(?,?,?)";
//			// 2. SQL -> DB연결
//			try {
//				preparedStatement =connection.prepareStatement(sql);
//				// 3. SQL 설정
//				preparedStatement.setInt(1, seat.getS_no());
//				preparedStatement.setInt(2, seat.getS_status());
//				preparedStatement.setInt(3, seat.getS_price());
//				// 4. SQL 실행
//				preparedStatement.executeQuery();
//				// 5. SQL 결과 실행
//				return true;
//			} catch (Exception e) {} return false;
//		}
	//	
		// 2. 
		


}

