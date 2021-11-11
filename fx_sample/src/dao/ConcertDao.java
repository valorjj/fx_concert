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
			Class.forName("com.mysql.cj,jdbc,Driver");
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
		String sql = "insert into concert(c_title,c_artist,c_info) values (?,?,?)";
		// 2. SQL -> DB����
		try {
			preparedStatement = connection.prepareStatement(sql);
			// 3. SQL ����
			preparedStatement.setString(1, concert.getC_title());
			preparedStatement.setString(2, concert.getC_artist());
			preparedStatement.setString(3, concert.getC_info());
			// 4. SQL ����
			preparedStatement.executeUpdate();
			// 5. SQL ��� ����
		}
		catch(Exception e) {} return false;
	}
	
	// 2.�ܼ�Ʈ �����޼ҵ�
	
	// 3.�ܼ�Ʈ ���� �޼ҵ�
	
	// 4.�ܼ�Ʈ ��ȸ �޼ҵ�
	
	// 5.�ܼ�Ʈ �¼��� ��ȯ�޼ҵ�(piechart��)
	
	
}
