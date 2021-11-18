package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SeatDao {

	private Connection connection; // 연결 인터페이스 선언
	private PreparedStatement preparedStatement; // SQL 연결 인터페이스
	private ResultSet resultSet; // 쿼리(Query) (검색결과) 연결 인터페이스

	// 해당 클래스 객체 생성
	private static SeatDao seatDao = new SeatDao();

	public static SeatDao get_seatDao() {
		return seatDao;
	}

	public SeatDao() {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/javafx_concert?serverTimezone=UTC",
					"root", "1234");
			System.out.println("DB connection success ... ");

		} catch (Exception e) {
			System.out.println("DB connection failed... " + e);
			e.printStackTrace();
		}
	}

}
