package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDao {

	// 1. �ʵ�
	private Connection connection; // DB ���� �������̽� ����
	private PreparedStatement preparedStatement;	// SQL ���� �������̽� ����
	private ResultSet resultSet;  // ����(�˻����) ���� �������̽� ����
	// ���� Ŭ������ ��ü �����
	private static MemberDao memberDao = new MemberDao();
	// 2.������
	public MemberDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/fx_concert?serverTimezone=UTC","root","1234");
		} catch (Exception e) {
			System.out.println("DB���� ���� : " + e);
		}
	}
	//3. �޼ҵ�
	public static MemberDao getMemberDao() {return memberDao;}
	
	// ��ɸ޼ҵ�
	
	// 1.ȸ������ �޼ҵ�
	
	// 2.�α��� �޼ҵ�
	
	// 3.���̵� ã�� �޼ҵ�
	
	// 4.��й�ȣ ã�� �޼ҵ�
	
	// 5.ȸ������ �޼ҵ�
	
	// 6.ȸ��Ż�� �޼ҵ�
	
	// 7.ȸ����ȸ �޼ҵ�
	
	
	
}