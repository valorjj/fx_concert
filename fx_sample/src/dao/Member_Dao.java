package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.Member;

public class Member_Dao {

	//1.connection : db���� �������̽�
	
	
	//1. �ʵ�
	private Connection connection;// db���� �������̽��� ����
	private PreparedStatement preparedstatement; // sql ���� �������̽�����
	private ResultSet resultSet; // ���� �°�� ������ ����
	
	// ���� Ŭ������ ��ü�����
	private static Member_Dao member_dao = new Member_Dao();
	//2. ������
	public Member_Dao() {
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver"); // �̰� ������������ϴ°�
//		connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/concert?serverTimezone=UTC","root","1234");
//		
//		
//		} catch (Exception e) {
//			System.out.println("*DB���� ����:"+e);
//			
//		} 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/concert?serverTimezone=UTC" , 
					"root" , "1234");
		}
		catch (Exception e) {
			System.out.println(" * DB ���� ���� : " + e);
		}
		
	}
	
	//3. �޼ҵ�
	public static Member_Dao getMember_Dao() {return member_dao;}
	// ��� �޼ҵ�
	//1. ȸ������ �޼ҵ�[�μ��� member��ü�� �޾� db�������ϴ°�]
	public boolean signup(Member member) {
	String sql = "insert into member(m_id,m_pw,m_name,m_email,m_age,m_sex) values(?,?,?,?,?,?)";
		try {
			preparedstatement=connection.prepareStatement(sql);
			preparedstatement.setString(1, member.getM_id() );
			preparedstatement.setString(2, member.getM_pw());
			preparedstatement.setString(3,member.getM_name());
			System.out.println("name");
			preparedstatement.setString(4,member.getM_email());
			preparedstatement.setInt(5,member.getM_age() );
			preparedstatement.setString(6, member.getM_sex());
			preparedstatement.executeUpdate();
			System.out.println("update");
			return true;
			
			
			
		} catch (Exception e) {
			System.out.println("db member");
		}
		return false;
	}
	public boolean signup1(Member member) {
		String sql = "insert into member(m_id,m_pw,m_name,m_email,m_age,m_sex) values(?,?,?,?,?,?)";
			try {
				preparedstatement=connection.prepareStatement(sql);
				preparedstatement.setString(1, "a" );
				preparedstatement.setString(2,"a" );
				preparedstatement.setString(3,"a" );
				System.out.println("name");
				preparedstatement.setString(4,"a" );
				preparedstatement.setInt(5,1);
				preparedstatement.setString(6, "a" );
				preparedstatement.executeUpdate();
				System.out.println("update");
				return true;
				
				
				
			} catch (Exception e) {
				System.out.println("db member");
			}
			return false;
		}
	
	//---------------------------------���⼭���ͽ����� �α����̾�
	
	
	
	
	
	
}
