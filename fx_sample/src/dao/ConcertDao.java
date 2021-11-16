package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConcertDao {

	private Connection connection; // 연결 인터페이스 선언
	private PreparedStatement preparedStatement; // SQL 연결 인터페이스
	private ResultSet resultSet; // 쿼리(Query) (검색결과) 연결 인터페이스

	// 해당 클래스 객체 생성
	private static ConcertDao concertDao = new ConcertDao();

	//
	public ConcertDao() {

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

	// [메소드]
	public static ConcertDao get_concertDao() {
		return concertDao;
	}

	//

	// 콘서트 날짜를 리턴하는 메소드

	public String get_concert_date(int c_no) {

		String sql = "SELECT c_date FROM concert WHERE c_no=?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, c_no);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getString(1);

			}
			return null;
		} catch (Exception e) {
		}
		return null;
	}

	public String get_concert_title(int c_unique_no) {

		String sql = "SELECT c_title FROM concert WHERE c_no=?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, c_unique_no);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getString(1);

			}
			return null;
		} catch (Exception e) {
		}
		return null;

	}

	public int get_remaining_seat_R(int c_no) {
		// 특정 날짜, 특정 시간의 콘서트 정보를 불러와야한다.
		String sql = "SELECT c_R_no FROM concert WHERE c_no=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, c_no);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (Exception e) {
		}
		return 0;

	}

	public int get_remaining_seat_S(int c_no) {
		// 특정 날짜, 특정 시간의 콘서트 정보를 불러와야한다.
		String sql = "SELECT c_S_no FROM concert WHERE c_no=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, c_no);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (Exception e) {
		}
		return 0;

	}

	public int get_remaining_seat_D(int c_no) {
		// 특정 날짜, 특정 시간의 콘서트 정보를 불러와야한다.
		String sql = "SELECT c_D_no FROM concert WHERE c_no=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, c_no);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (Exception e) {
		}
		return 0;

	}

	public int get_remaining_seat_E(int c_no) {
		// 특정 날짜, 특정 시간의 콘서트 정보를 불러와야한다.
		String sql = "SELECT c_E_no FROM concert WHERE c_no=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, c_no);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (Exception e) {
		}
		return 0;

	}

}
