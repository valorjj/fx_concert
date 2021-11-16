package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDao {

	// 1. 필드
	private Connection connection; // DB 연결 인터페이스 선언
	private PreparedStatement preparedStatement;	// SQL 연결 인터페이스 선언
	private ResultSet resultSet;  // 쿼리(검색결과) 연결 인터페이스 선언
	// 현재 클래스내 객체 만들기
	private static MemberDao memberDao = new MemberDao();
	// 2.생성자
	public MemberDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/fx_concert?serverTimezone=UTC","root","1234");
		} catch (Exception e) {
			System.out.println("DB연동 실패 : " + e);
		}
	}
	//3. 메소드
	public static MemberDao getMemberDao() {return memberDao;}
	
	// 기능메소드
	
	// 1.회원가입 메소드
	
	// 2.로그인 메소드
	
	// 3.아이디 찾기 메소드
	
	// 4.비밀번호 찾기 메소드
	
	// 5.회원수정 메소드
	
	// 6.회원탈퇴 메소드
	
	// 7.회원조회 메소드
	
	
	
}