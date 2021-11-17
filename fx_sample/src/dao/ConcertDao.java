package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import domain.Concert;
import javafx.collections.ObservableList;

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
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx_concert?serverTimezone=UTC",
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

	public String get_concert_date_list(int c_no) {

		String sql = " SELECT DISTINCT c_date FROM concert WHERE c_no=? ORDER BY c_no ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, c_no);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getString(1);
			}

		} catch (Exception e) {
			System.out.println("에러 \n" + e);
			e.printStackTrace();
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

	public int get_remaining_seat_R(String date, int time) {
		// 특정 날짜, 특정 시간의 콘서트 정보를 불러와야한다.
		String sql = "SELECT c_R_no FROM concert WHERE c_date=? and c_time=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, date);
			preparedStatement.setInt(2, time);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (Exception e) {
		}
		return 0;

	}

	public int get_remaining_seat_S(String date, int time) {
		// 특정 날짜, 특정 시간의 콘서트 정보를 불러와야한다.
		String sql = "SELECT c_S_no FROM concert WHERE c_date=? and c_time=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, date);
			preparedStatement.setInt(2, time);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (Exception e) {
		}
		return 0;

	}

	public int get_remaining_seat_D(String date, int time) {
		// 특정 날짜, 특정 시간의 콘서트 정보를 불러와야한다.
		String sql = "SELECT c_D_no FROM concert WHERE c_date=? and c_time=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, date);
			preparedStatement.setInt(2, time);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (Exception e) {
		}
		return 0;

	}

	public int get_remaining_seat_E(String date, int time) {
		// 특정 날짜, 특정 시간의 콘서트 정보를 불러와야한다.
		String sql = "SELECT c_E_no FROM concert WHERE c_date=? and c_time=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, date);
			preparedStatement.setInt(2, time);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (Exception e) {
		}
		return 0;

	}

	/* 시간, 날짜를 입력받고 해당하는 콘서트 정보를 DB에서 꺼내온다. */
	public Concert get_concert_info(String date, int time) {
		String sql = "SELECT * FROM concert WHERE c_date=? and c_time=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, date);
			preparedStatement.setInt(2, time);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				Concert concert = new Concert(

						resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
						resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8),
						resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11), resultSet.getInt(12),
						resultSet.getInt(13)

				);

				return concert;

			}
		} catch (Exception e) {
		}
		return null;

	}

	/*
	 * 시간, 날짜를 입력받고 해당하는 콘서트 정보를 DB에서 꺼내온다. 해당 프로젝트의 경우 고유번호 1번 콘서트에는 6개의 정보가 담겨있다.
	 */
	public ArrayList<Concert> get_concert_info(int c_unique_no) {

		ArrayList<Concert> concerts = new ArrayList<Concert>();
		String sql = "SELECT * FROM concert WHERE c_unique_no=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, c_unique_no);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Concert concert = new Concert(

						resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
						resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8), resultSet.getInt(9),
						resultSet.getInt(10), resultSet.getInt(11), resultSet.getInt(12), resultSet.getInt(13),
						resultSet.getInt(14)

				);
				concerts.add(concert);
				return concerts;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}



}
