
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
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/javafx_concert?serverTimezone=UTC",
					"root", "1234");
		} catch (Exception e) {
			System.out.println("DB연동 실패 : " + e);
		}
	}

	// 3.메소드
	public static SeatDao getSeatDao() {
		return seatDao;
	}

	// 기능 메소드

	// 1. 자리등록 메소드
	public boolean register(Seat seat) {
		// 1. SQL 작성
		String sql = "insert into seat(s_unique_no, s_grade) values(?,?)";
		// 2. SQL -> DB연결
		try {
			preparedStatement = connection.prepareStatement(sql);

			for (int i = 0; i < 50; i++) {

				preparedStatement.setInt(1, 1);
				preparedStatement.setString(2, "R");
			}

			preparedStatement.executeUpdate();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
