package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.Member;

public class MemberDao {

	private Connection connection; // 연결 인터페이스 선언
	private PreparedStatement preparedStatement; // SQL 연결 인터페이스
	private ResultSet resultSet; // 쿼리(Query) (검색결과) 연결 인터페이스

	// 해당 클래스 객체 생성
	private static MemberDao memberDao = new MemberDao();

	//
	public MemberDao() {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/javafx_concert?serverTimezone=UTC",
					"root", "1234");
			System.out.println("DB connection success ... ");

		} catch (Exception e) {
			System.out.println("DB connection failed... " + e);
			e.printStackTrace();
		}
	}

	// 해당 클래스 (MemberDao) 를 호출할 때 사용할 메소드
	// 다른 클래스에서 이 메소드를 사용해서 MemberDao에 선언된 메소드에 접근한다.
	public static MemberDao get_memberDao() {
		return memberDao;
	}

	// id 만 가져오기

	public Member get_id_member(String id) {

		String sql = "SELECT * FROM member where m_id=?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Member member = new Member(
						// 패스워드를 제외한 회원 정보 출력
						resultSet.getString(2), "", resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6),
						resultSet.getString(7)

				);
				return member;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null; // DB 오류가 났을 경우

	}

	// 회원번호 리턴하는 메소드

	public int get_m_no_member(String id) {

		String sql = "SELECT m_no FROM member where m_id=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
			return 0;
		} catch (Exception e) {
		}
		return 0;

	}

	/*
	 * 인수로 아이디, 패스워드를 입력받아서 로그인 성공 / 실패 여부를 boolean 값으로 리턴하는 메소드입니다.
	 */
	public boolean log_in(String id, String password) {

		String sql = "SELECT * from member WHERE m_id=? and m_pw=?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false; // 여기서 오류 발생하면 DB 오류

	}
	
	

}