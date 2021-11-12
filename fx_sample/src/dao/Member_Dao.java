package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.Member;

public class Member_Dao {

	//1.connection : db연결 인터페이스
	
	
	//1. 필드
	private Connection connection;// db연결 인터페이스를 선언
	private PreparedStatement preparedstatement; // sql 연결 인터페이스선언
	private ResultSet resultSet; // 쿼리 는결과 쿼리를 선언
	
	// 현재 클래스내 객체만들기
	private static Member_Dao member_dao = new Member_Dao();
	//2. 생성자
	public Member_Dao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // 이건 무조건적어야하는거
		connection= DriverManager.getConnection("jdbc:mysql://localhost:3307/javafx?serverTimezone=UTC","root","1234");
		
		
		} catch (Exception e) {
			System.out.println("*DB연동 실패:"+e);
			
		} 
	}
	
	//3. 메소드
	public static Member_Dao getMember_Dao() {return member_dao;}
	// 기능 메소드
	//1. 회원가입 메소드[인수를 member객체로 받아 db에저장하는곳]
	public boolean signup(Member member) {
	String sql = "insert into member(m_id,m_pw,m_name,m_email,m_age,m_sex)"+"values(?,?,?,?,?)";
		try {
			preparedstatement=connection.prepareStatement(sql);
			preparedstatement.setString(1, member.getM_id() );
			preparedstatement.setString(2, member.getM_pw());
			preparedstatement.setString(3,member.getM_name());
			preparedstatement.setString(4,member.getM_email());
			preparedstatement.setInt(5,member.getM_age() );
			preparedstatement.setString(6, member.getM_sex());
			preparedstatement.executeUpdate();
			return true;
			
			
			
		} catch (Exception e) {
			
		}
		return false;
	}
	//---------------------------------여기서부터시작해 로그인이야
	
	
	
	
	
	
}
