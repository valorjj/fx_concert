package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import domain.Concert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ConcertDao {

	// 1.�븘�뱶
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	// �쁽�옱 �겢�옒�뒪�궡 媛앹껜 留뚮뱾湲�
	private static ConcertDao concertDao = new ConcertDao();

	// 2.�깮�꽦�옄
	public ConcertDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/fx_concert?serverTimezone=UTC",
					"root", "1234");
		} catch (Exception e) {
			System.out.println("DB�뿰�룞 �떎�뙣 : " + e);
		}
	}

	// 3.硫붿냼�뱶
	public static ConcertDao getConcertDao() {
		return concertDao;
	}

	// 湲곕뒫 硫붿냼�뱶

	// 1.肄섏꽌�뱶 �벑濡앸찓�냼�뱶
	public boolean register(Concert concert) {
		// 1. SQL �옉�꽦
		String sql = "insert into concert(c_title,c_artist,c_info,c_date,c_time,c_R_no,c_S_no,c_D_no,c_E_no,c_R_price,c_S_price,c_D_price,c_E_price) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		// 2. SQL -> DB�뿰寃�
		try {
			preparedStatement = connection.prepareStatement(sql);
			// 3. SQL �꽕�젙
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
			// 4. SQL �떎�뻾
			preparedStatement.executeUpdate();
			// 5. SQL 寃곌낵 �떎�뻾
			return true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	// 2.肄섏꽌�듃 �닔�젙硫붿냼�뱶
	public boolean update(Concert concert) {
		String sql = "update concert set c_title =?, c_artist =? , c_info=? , c_date=?, c_time =? , c_R_no =?, c_S_no=? , c_D_no=?, c_E_no=?, c_R_price = ?, c_S_price=?,c_D_price =?, c_E_price=?, c_unique_no=? where c_no=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
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
			preparedStatement.setInt(14, concert.getC_no());
			preparedStatement.setInt(15, concert.getC_unique_no());
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	// 3.肄섏꽌�듃 �궘�젣 硫붿냼�뱶
	public boolean delete(int c_no) {
		String sql = "delete from concert where c_no=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, c_no);
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	// 4.肄섏꽌�듃 議고쉶 硫붿냼�뱶
	public ObservableList<Concert> concertlist() {
		// 1.由ъ뒪�듃�꽑�뼵
		ObservableList<Concert> concerts = FXCollections.observableArrayList();

		String sql = "select * from concert order by c_no desc";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Concert concert = new Concert(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5).split(" ")[0], resultSet.getString(6),
						resultSet.getInt(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10),
						resultSet.getInt(11), resultSet.getInt(12), resultSet.getInt(13), resultSet.getInt(14),
						resultSet.getInt(15));
				// 媛앹껜 由ъ뒪�듃 ���옣
				concerts.add(concert);
			}
			return concerts;
		} catch (Exception e) {
		}
		return concerts;

	}
	// 5.肄섏꽌�듃 醫뚯꽍�닔 諛섑솚硫붿냼�뱶(piechart�슜)

	// 6. 肄섏꽌�듃 ���씠�� 諛섑솚 硫붿냼�뱶
	public ObservableList<Concert> titlelist() {
		// 1.由ъ뒪�듃 �꽑�뼵
		ObservableList<Concert> titles = FXCollections.observableArrayList();
		String sql = "select c_title from concert group by c_title";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Concert concert = new Concert(resultSet.getString(1));
				titles.add(concert);
			}
			return titles;
		} catch (Exception e) {
		}
		return titles;
	}

	// 7. 肄섏꽌�듃 �궇吏� 諛섑솚 硫붿냼�뱶
	public ObservableList<Concert> datelist(String c_title) {
		// 1. 由ъ뒪�듃 �꽑�뼵
		ObservableList<Concert> dates = FXCollections.observableArrayList();
		String sql = "select distinct c_date from concert where c_title in (select c_title from concert where c_title=?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, c_title);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Concert concert_date = new Concert(resultSet.getString(1));
				dates.add(concert_date);
			}
			return dates;
		} catch (Exception e) {
		}
		return dates;
	}

	// 8. 肄섏꽌�듃 �떆媛� 諛섑솚 硫붿냼�뱶
	public ObservableList<Concert> timelist(String c_title, String c_date) {
		ObservableList<Concert> times = FXCollections.observableArrayList();
		String sql = "select c_time from concert where c_date=? and c_title=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, c_date);
			preparedStatement.setString(2, c_title);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Concert time = new Concert(resultSet.getString(1));
				times.add(time);
			}
			return times;
		} catch (Exception e) {
		}
		return times;

	}

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

	public String get_concert_date_list(int c_unique_no) {

		String sql = " SELECT DISTINCT c_date FROM concert WHERE c_unique_no=? ORDER BY c_no ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, c_unique_no);
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
//	public Concert get_concert_info(String date, int time) {
//		String sql = "SELECT * FROM concert WHERE c_date=? and c_time=?";
//		try {
//			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setString(1, date);
//			preparedStatement.setInt(2, time);
//			resultSet = preparedStatement.executeQuery();
//
//			if (resultSet.next()) {
//
//				Concert concert = new Concert(
//
//						resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
//						resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8),
//						resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11), resultSet.getInt(12),
//						resultSet.getInt(13)
//
//				);
//
//				return concert;
//
//			}
//		} catch (Exception e) {
//		}
//		return null;
//
//	}

	/*
	 * 시간, 날짜를 입력받고 해당하는 콘서트 정보를 DB에서 꺼내온다. 해당 프로젝트의 경우 고유번호 1번 콘서트에는 6개의 정보가 담겨있다.
	 */
//	public ArrayList<Concert> get_concert_list(int c_unique_no) {
//
//		ArrayList<Concert> concerts = new ArrayList<Concert>();
//		String sql = "SELECT * FROM concert WHERE c_unique_no=?";
//		try {
//			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setInt(1, c_unique_no);
//			resultSet = preparedStatement.executeQuery();
//
//			while (resultSet.next()) {
//
//				Concert concert = new Concert(
//
//						resultSet.getString(2), resultSet.getString(3), "", resultSet.getString(5), resultSet.getInt(6),
//						resultSet.getInt(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10),
//						resultSet.getInt(11), resultSet.getInt(12), resultSet.getInt(13), resultSet.getInt(14)
//
//				);
//				concerts.add(concert);
//				return concerts;
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//
//	}

	public int get_c_unique_no(String title) {

		String sql = " SELECT c_unique_no FROM concert WHERE c_title=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, title);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

//	public Concert get_concert_info_single_item(int c_unique_no) {
//		String sql = "SELECT * FROM concert WHERE c_unique_no=?";
//		try {
//			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setInt(1, c_unique_no);
//			resultSet = preparedStatement.executeQuery();
//
//			if (resultSet.next()) {
//
//				Concert concert = new Concert(
//
//						resultSet.getString(2), resultSet.getString(3), resultSet.getString(5), resultSet.getInt(6)
//
//				);
//
//				return concert;
//
//			}
//		} catch (Exception e) {
//		}
//		return null;
//
//	}

}
