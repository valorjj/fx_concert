package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.Concert;

public class ConcertDao {

	// 1.�ʵ�
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	// ���� Ŭ������ ��ü �����
	private static ConcertDao concertDao = new ConcertDao();
	// 2.������
	public ConcertDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/fx_concert?serverTimezone=UTC","root","1234");
		} catch (Exception e) {
			System.out.println("DB���� ���� : " + e);
		}
	}
	
	// 3.�޼ҵ�
	public static ConcertDao getConcertDao() {return concertDao;}
	
	// ��� �޼ҵ�
	
	// 1.�ܼ��� ��ϸ޼ҵ�
	public boolean register(Concert concert) {
		// 1. SQL �ۼ�
		String sql = "insert into concert(c_title,c_artist,c_info,c_date,c_time,c_R_no,c_S_no,c_D_no,c_E_no,c_R_price,c_S_price,c_D_price,c_E_price) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		// 2. SQL -> DB����
		try {
			preparedStatement = connection.prepareStatement(sql);
			// 3. SQL ����
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
			// 4. SQL ����
			preparedStatement.executeUpdate();
			// 5. SQL ��� ����
			return true;
		}
		catch(Exception e) {System.out.println(e);} return false;
	}
	
	// 2.�ܼ�Ʈ �����޼ҵ�
	
	// 3.�ܼ�Ʈ ���� �޼ҵ�
	
	// 4.�ܼ�Ʈ ��ȸ �޼ҵ�
	
	// 5.�ܼ�Ʈ �¼��� ��ȯ�޼ҵ�(piechart��)
	
	
}
