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
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/javafx_concert?serverTimezone=UTC",
					"root", "1234");
			System.out.println("DB connection success ... ");

		} catch (Exception e) {
			System.out.println("DB connection failed... " + e);
			e.printStackTrace();
		}
	}

	// reservation 객체를 DB에 추가하는 메소드 (입력받은 데이터를 데이터 베이스에 저장합니다)
	public boolean reservation_register(Reservation reservation) {

		String sql = "insert into reservation(s_unique_no, s_grade, c_no, c_unique_no, m_no) values(?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, reservation.getS_unique_no());
			preparedStatement.setString(2, reservation.getS_grade());
			preparedStatement.setInt(3, reservation.getC_no());
			preparedStatement.setInt(4, reservation.getC_unique_no());
			preparedStatement.setInt(5, reservation.getM_no());
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

		String sql = "SELECT c_no, s_grade, s_unique_no FROM reservation WHERE m_no=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, m_no);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				Reservation reservation = new Reservation(

						resultSet.getInt(3), resultSet.getString(2), resultSet.getInt(1), resultSet.getInt(4)

				);
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
				Concert concert = new Concert(resultSet.getString(2), resultSet.getString(3), resultSet.getString(5),
						resultSet.getString(6)

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

	// 1. 성별을 기준으로 예약 데이터를 불러옵니다.
	public ObservableList<Reservation> get_reservation(String m_sex) {

		ObservableList<Reservation> reservation_list_by_sex = FXCollections.observableArrayList();
		String sql = "select count(s_unique_no) from reservation where m_no IN (select m_no from member where m_sex=?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, m_sex);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				Reservation reservation = new Reservation(resultSet.getInt(1));
				reservation_list_by_sex.add(reservation);
			}
			return reservation_list_by_sex;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int get_reservation1(String m_sex, int c_no) {

		String sql = "select count(s_unique_no) from reservation where m_no IN (select m_no from member where m_sex=?) and c_no =?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, m_sex);
			preparedStatement.setInt(2, c_no);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// 연령별 예약데이터 출력
	public int get_reservation_by_age_10(int c_no) {
		String sql = "select count(s_unique_no) from javafx_concert.reservation where m_no IN (select m_no from javafx_concert.member where m_age between 10 and 19) and c_no=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, c_no);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int get_reservation_by_age_20(int c_no) {

		String sql = "select count(s_unique_no) from javafx_concert.reservation where m_no IN (select m_no from javafx_concert.member where m_age between 20 and 29) and c_no=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, c_no);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int get_reservation_by_age_30(int c_no) {

		String sql = "select count(s_unique_no) from javafx_concert.reservation where m_no IN (select m_no from javafx_concert.member where m_age between 30 and 39) and c_no=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, c_no);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int get_reservation_by_age_40(int c_no) {

		String sql = "select count(s_unique_no) from javafx_concert.reservation where m_no IN (select m_no from javafx_concert.member where m_age between 40 and 49) and c_no=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, c_no);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int get_reservation1(int c_no, String s_grade, int c_unique_no) {
		String sql = "select count(s_unique_no) from reservation where c_no=? and s_grade=? and c_unique_no=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, c_no);
			preparedStatement.setString(2, s_grade);
			preparedStatement.setInt(3, c_unique_no);

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	// 파이차트용 콘서트 자리별 R석 예약좌석 호출 메소드
	public int r_seat_selectlist(int c_no , int c_unique_no){
		String sql = "select count(s_unique_no) from reservation where c_no= ? and c_unique_no=? and s_grade='R'";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, c_no);
			preparedStatement.setInt(2, c_unique_no);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (Exception e) {} return 0;
		
		
	}
	
	// 파이차트용 콘서트 자리별 S석 예약좌석 호출 메소드
	public int s_seat_selectlist(int c_no , int c_unique_no){
		String sql = "select count(s_unique_no) from reservation where c_no= ? and c_unique_no=? and s_grade='S'";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, c_no);
			preparedStatement.setInt(2, c_unique_no);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (Exception e) {} return 0;
		
		
	}
	
	// 파이차트용 콘서트 자리별 D석 예약좌석 호출 메소드
	public int d_seat_selectlist(int c_no , int c_unique_no){
		String sql = "select count(s_unique_no) from reservation where c_no= ? and c_unique_no=? and s_grade='D'";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, c_no);
			preparedStatement.setInt(2, c_unique_no);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (Exception e) {} return 0;
		
		
	}
	
	// 파이차트용 콘서트 자리별 E석 예약좌석 호출 메소드
	public int e_seat_selectlist(int c_no , int c_unique_no){
		String sql = "select count(s_unique_no) from reservation where c_no= ? and c_unique_no=? and s_grade='E'";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, c_no);
			preparedStatement.setInt(2, c_unique_no);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (Exception e) {} return 0;
			
			
		}
	
	public ObservableList<Reservation> get_member_reservation2(int m_no) {

	      ObservableList<Reservation> member_reservation_history2 = FXCollections.observableArrayList();

	      String sql = "SELECT c_title, c_date, c_artist, s_grade, s_unique_no from concert as a join reservation as b on a.c_no = b.c_no where m_no=? order by c_title asc";
	      try {
	         preparedStatement = connection.prepareStatement(sql);
	         preparedStatement.setInt(1, m_no);
	         resultSet = preparedStatement.executeQuery();
	         while (resultSet.next()) {

	            Reservation reservation = new Reservation(

	                  resultSet.getInt(5), resultSet.getString(4), resultSet.getString(1), resultSet.getString(2),
	                  resultSet.getString(3)

	            );
	            member_reservation_history2.add(reservation);

	         }

	         return member_reservation_history2;

	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return null;
	   }
	
	

}
