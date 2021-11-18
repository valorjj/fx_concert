
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.Seat;

public class SeatDao {

	// 1. �븘�뱶
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	// �쁽�옱 �겢�옒�뒪�궡 媛앹껜 留뚮뱾湲�
	private static SeatDao seatDao = new SeatDao();
	
	// 2. �깮�꽦�옄
	public SeatDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/javafx_concert?serverTimezone=UTC","root","1234");
		} catch (Exception e) {	
			System.out.println("DB�뿰�룞 �떎�뙣 : " + e);
		}
	}
	// 3.硫붿냼�뱶
	public static SeatDao getSeatDao() {return seatDao;}
	
	// 湲곕뒫 硫붿냼�뱶
	
	// 1. �옄由щ벑濡� 硫붿냼�뱶
//	public boolean register(Seat seat) {
//		// 1. SQL �옉�꽦
//		String sql = "insert into seat(s_no,s_status,s_price) values(?,?,?)";
//		// 2. SQL -> DB�뿰寃�
//		try {
//			preparedStatement =connection.prepareStatement(sql);
//			// 3. SQL �꽕�젙
//			preparedStatement.setInt(1, seat.getS_no());
//			preparedStatement.setInt(2, seat.getS_status());
//			preparedStatement.setInt(3, seat.getS_price());
//			// 4. SQL �떎�뻾
//			preparedStatement.executeQuery();
//			// 5. SQL 寃곌낵 �떎�뻾
//			return true;
//		} catch (Exception e) {} return false;
//	}
//	
	// 2. 
	
}

