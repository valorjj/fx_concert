package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.Seat;

public class SeatDao {

	// 1. �ʵ�
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	// ���� Ŭ������ ��ü �����
	private static SeatDao seatDao = new SeatDao();
	
	// 2. ������
	public SeatDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/fx_concert?serverTimezone=UTC","root","1234");
		} catch (Exception e) {	
			System.out.println("DB���� ���� : " + e);
		}
	}
	// 3.�޼ҵ�
	public static SeatDao getSeatDao() {return seatDao;}
	
	// ��� �޼ҵ�
	
	// 1. �ڸ���� �޼ҵ�
//	public boolean register(Seat seat) {
//		// 1. SQL �ۼ�
//		String sql = "insert into seat(s_no,s_status,s_price) values(?,?,?)";
//		// 2. SQL -> DB����
//		try {
//			preparedStatement =connection.prepareStatement(sql);
//			// 3. SQL ����
//			preparedStatement.setInt(1, seat.getS_no());
//			preparedStatement.setInt(2, seat.getS_status());
//			preparedStatement.setInt(3, seat.getS_price());
//			// 4. SQL ����
//			preparedStatement.executeQuery();
//			// 5. SQL ��� ����
//			return true;
//		} catch (Exception e) {} return false;
//	}
//	
	// 2. 
	
}
