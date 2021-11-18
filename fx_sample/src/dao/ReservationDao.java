package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.Concert;
import domain.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReservationDao {

	private Connection connection; // 연결 인터페이스 선언
	private PreparedStatement preparedStatement; // SQL 연결 인터페이스
	private ResultSet resultSet; // 쿼리(Query) (검색결과) 연결 인터페이스

	// 해당 클래스 객체 생성
	private static ReservationDao reservationDao = new ReservationDao();

	public static ReservationDao get_reservationDao() {
		return reservationDao;
	}

	//
	public ReservationDao() {

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

	// reservation 객체를 DB에 추가하는 메소드 (입력받은 데이터를 데이터 베이스에 저장합니다)
	public boolean reservation_register(Reservation reservation) {

		String sql = "insert into reservation(s_no, c_no, m_no, c_unique_no) values(?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, reservation.getS_no());
			preparedStatement.setInt(2, reservation.getC_no());
			preparedStatement.setInt(3, reservation.getM_no());
			preparedStatement.executeUpdate();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false; // DB 오류

	}

	// 예약된 번호를 이용해서 연관되어있는 c_no 를 리턴하는 메소드
	public int reservation_c_no_check(int m_no) {
		String sql = "SELECT c_no FROM reservation WEHRE m_no=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, m_no);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
			return 0;
		} catch (Exception e) {
		}
		return 0;

	}

	/* 마지막에 고객이 결제를 취소하면 DB에서 삭제시킵니다. */

	public boolean reservation_delete(int r_no) {

		String sql = "delete from reservation where r_no=?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, r_no);
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	// 예약 히스토리 가져오기

	// MemberDao 에서 m_no 가져와서 예약된 내역을 Observablelist에 저장한다.

	public ObservableList<Reservation> get_member_reservation(int m_no) {

		ObservableList<Reservation> member_reservation_history = FXCollections.observableArrayList();

		String sql = "SELECT * FROM reservation WHERE m_no=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				Reservation reservation = new Reservation(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
						resultSet.getInt(4));
			member_reservation_history.add(reservation);

			}

			return member_reservation_history;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 예약 목록에 있는 콘서트 정보만 빼와야 합니다. 그래서 sub query 로 2중 select 문을 사용했습니다.

		public ObservableList<Concert> get_concert_from_reservation(int m_no) {

			ObservableList<Concert> concert_reserved = FXCollections.observableArrayList();

			String sql = "select * from concert where c_no=(select c_no from reservation where m_no=?)";

			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, m_no);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Concert concert = new Concert(resultSet.getString(2), resultSet.getString(3), resultSet.getString(5), resultSet.getString(6)

					);

					concert_reserved.add(concert);

				}
				return concert_reserved;

			} catch (Exception e) {
				e.printStackTrace();
			}
			return concert_reserved;

		}

	public int get_c_no(int m_no) {
		String sql = "SELECT c_no FROM reservation WHERE m_no=?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, m_no);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
