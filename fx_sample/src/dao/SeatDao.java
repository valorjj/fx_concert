
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx_concert?serverTimezone=UTC",
					"root", "1234");
		} catch (Exception e) {
			System.out.println("DB연동 실패 : " + e);
		}
	}

	// 3.메소드
	public static SeatDao getSeatDao() {
		return seatDao;
	}

	// 1. 좌석 정보를 등록하는 메소드입니다.
	// 1. 여기서 등록이 되기 전까지는 DB 에는 정보가 없습니다.
	// 2. seat 테이블의 s_no 가 primary key 이지만 좌석 번호와는 관계가 없습니다.
	// 3. seat 테이블의 s_status 는 '0' 값으로 모두 초기화가 되어있습니다.
	// 4. s_status(좌석 예약 상태) 는
	// 0 : 예약 안됨
	// 1 : 예약됨
	// 5. 좌석 선택 화면에서는
	// 0, 1, 2 총 3가지의 상태가 존재합니다.
	// 0 : 아무 선택도 하지 않음
	// 1 : 현재 선택한 좌석 -> 화면에 초록색으로 표시
	// 2 : 이미 선택한 좌석 -> DB 에서는 1 의 상태 -> 화면에 빨간색으로 표시되며 선택 불가능 상태
	// 6.

	public boolean register(int s_unique_no, int c_no, String s_grade) {
		String sql = "INSERT INO seat(s_unique_no, c_no, s_grade) VALUES(?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, s_unique_no);
			preparedStatement.setInt(2, c_no);
			preparedStatement.setString(3, "s_grade");

			preparedStatement.executeUpdate();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Seat> get_seat_instance(int c_no, String s_grade, int c_unique_no) {

		ArrayList<Seat> seat_list = new ArrayList<>();

		String sql = "SELECT * FROM seat WHERE c_no=? and s_grade=? and c_unique_no=?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, c_no);
			preparedStatement.setString(2, s_grade);
			preparedStatement.setInt(3, c_unique_no);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Seat seat = new Seat(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4),
						resultSet.getString(5));

				seat_list.add(seat);

			}

			return seat_list;
		} catch (Exception e) {
		}

		return null;
	}
	
	public ArrayList<Integer> get_seat_status(int c_no, String s_grade, int c_unique_no) {
		String sql = "SELECT s_status FROM seat WHERE c_no=? and s_grade=? and c_unique_no=? order by s_unique_no asc";

		ArrayList<Integer> R_seat_status = new ArrayList<>(50);

		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, c_no);
			preparedStatement.setString(2, s_grade);
			preparedStatement.setInt(3, c_unique_no);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				R_seat_status.add(resultSet.getInt(1));
			}
			return R_seat_status;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//	public ArrayList<Integer> get_S_seat_status(int c_no, String s_grade, int c_unique_no) {
//		String sql = "SELECT s_status FROM seat WHERE c_no=? and s_grade=? and c_unique_no=?";
//
//		ArrayList<Integer> S_seat_status = new ArrayList<>();
//
//		try {
//
//			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setInt(1, c_no);
//			preparedStatement.setString(2, s_grade);
//			preparedStatement.setInt(3, c_unique_no);
//			resultSet = preparedStatement.executeQuery();
//
//			while (resultSet.next()) {
//				S_seat_status.add(resultSet.getInt(1));
//			}
//			return S_seat_status;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public ArrayList<Integer> get_D_seat_status(int c_no, String s_grade, int c_unique_no) {
//		String sql = "SELECT s_status FROM seat WHERE c_no=? and s_grade=? and c_unique_no=?";
//
//		ArrayList<Integer> D_seat_status = new ArrayList<>();
//
//		try {
//
//			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setInt(1, c_no);
//			preparedStatement.setString(2, s_grade);
//			preparedStatement.setInt(3, c_unique_no);
//			resultSet = preparedStatement.executeQuery();
//
//			while (resultSet.next()) {
//				D_seat_status.add(resultSet.getInt(1));
//			}
//			return D_seat_status;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public ArrayList<Integer> get_E_seat_status(int c_no, String s_grade, int c_unique_no) {
//		String sql = "SELECT s_status FROM seat WHERE c_no=? and s_grade=? and c_unique_no=?";
//
//		ArrayList<Integer> E_seat_status = new ArrayList<>();
//
//		try {
//
//			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setInt(1, c_no);
//			preparedStatement.setString(2, s_grade);
//			preparedStatement.setInt(3, c_unique_no);
//			resultSet = preparedStatement.executeQuery();
//
//			while (resultSet.next()) {
//				E_seat_status.add(resultSet.getInt(1));
//			}
//			return E_seat_status;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

}
