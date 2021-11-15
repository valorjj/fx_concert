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
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3307/javafx?serverTimezone=UTC" , 
					"root" , "1234");
		}
		catch (Exception e) {
			System.out.println(" * DB 연동 실패 : " + e);
		}
		
	}
	
	//3. 메소드
	public static Member_Dao getMember_Dao() {return member_dao;}
	// 기능 메소드
	//1. 회원가입 메소드[인수를 member객체로 받아 db에저장하는곳]
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
	
	//2. 로그인 메소드
	public boolean login(String m_id , String m_password) {
		// 모든검색에 member테이블에 조건이 = m_id 와m_password 가져오기
		String sql = "select * from member where m_id=? and m_password=?";
		try {
			// sql 을 데이터베이스에서 자저오는걸로설정
			preparedstatement=connection.prepareStatement(sql);
			preparedstatement.setString(1,m_id );//
			preparedstatement.setString(2, m_password);// dB에서 가져온 m_password를 설정한다
			
			resultSet = preparedstatement.executeQuery();
			if (resultSet.next()) {// 결과에 다음내용이있으면 true
				return true;
			}else {
				return false;
			}
	
		} catch (Exception e) {}

		return false;
	}
	
	
	//3. 아이디찾기메소드
		public String find_id(String m_name ,String m_email) {
			
			String sql = "select m_id from member where m_name=? and m_emaile=?";
			try {
				preparedstatement= connection.prepareStatement(sql);
				preparedstatement.setString(1,m_name );
				preparedstatement.setString(2, m_email);
				resultSet=preparedstatement.executeQuery();
				
				
				
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		
			return m_email;
			
			
			
			
		
			
		}
	
	
	
	
	
	
}
