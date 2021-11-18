
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import domain.Member;

public class MemberDao {
	//1. 필드
	private Connection connection;// db연결 인터페이스를 선언
	private PreparedStatement preparedstatement; // sql 연결 인터페이스선언
	private ResultSet resultSet; // 쿼리 는결과 쿼리를 선언
	
	// 현재 클래스내 객체만들기
	private static MemberDao memberdao = new MemberDao();
	//2. 생성자

	public MemberDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/javafx_concert?serverTimezone=UTC","root","1234");
		}

		catch (Exception e) {System.out.println(" * DB 연동 실패 : " + e);}
	}
	
	//3. 메소드
	public static MemberDao getMemberDao() {return memberdao;}
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
		} catch (Exception e) {} return false;
	}

	//2. 로그인 메소드
	public boolean login(String m_id , String m_pw) {
		// 모든검색에 member테이블에 조건이 = m_id 와m_password 가져오기
		String sql = "select * from member where m_id=? and m_pw=?";
		try {
			// sql 을 데이터베이스에서 자저오는걸로설정
			preparedstatement=connection.prepareStatement(sql);
			preparedstatement.setString(1,m_id );//
			preparedstatement.setString(2, m_pw);// dB에서 가져온 m_password를 설정한다
			
			resultSet = preparedstatement.executeQuery();
			if (resultSet.next()) {// 결과에 다음내용이있으면 true

				return true;
			}else {return false;}
		} catch (Exception e) {} return false;
	}

	//3. 아이디찾기메소드

		public String find_id(String m_name ,String m_email) {
			String sql = "select m_id from member where m_name=? and m_email=?";
			try {
				preparedstatement= connection.prepareStatement(sql);
				preparedstatement.setString(1,m_name );
				preparedstatement.setString(2, m_email);
				resultSet=preparedstatement.executeQuery();

				//5.sql 결과

				if(resultSet.next()) {
					return resultSet.getString(1);
				}else {return null;}
			}catch (Exception e) {} return null;
		}


		// 4. 패스워드 찾기 메소드

		public String find_pw(String m_id, String m_email) {
			String sql = "select m_pw from member where m_id=? and m_email=?";
			try {
				preparedstatement = connection.prepareStatement(sql);
				preparedstatement.setString(1, m_id);
				preparedstatement.setString(2, m_email);
				resultSet = preparedstatement.executeQuery();
				if (resultSet.next()) {
					return resultSet.getString(1);
				} else {return null;}
			} catch (Exception e) {} return null;
		}
	      
		

	// 5.회원수정 메소드
	
	// 6.회원탈퇴 메소드
	
	// 7.회원조회 메소드
	
	// 8. 이메일 전송 메소드
	public void sendmail(String tomail, String msg) {
		// 보낸사람 정보 

		String fromemail = "kimji1218@naver.com";
		String frompassword = "";
		
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.naver.com");
		properties.put("mail.smtp.port", 587);
		properties.put("mail.smtp.auth", true);
		
		Session session = Session.getDefaultInstance(properties, new Authenticator() {

			// 익명 구현객체

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromemail, frompassword);
			}
		});
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromemail));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(tomail));
			

			message.setSubject("회원님의 비밀번호 결과");
			message.setText("회원님의 비밀번호 : "+ msg);
			Transport.send(message);
		} catch (Exception e) {}
			
	}
	

	// id 만 가져오기

	public Member get_id_member(String id) {

		String sql = "SELECT * FROM member where m_id=?";

		try {
			preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setString(1, id);
			resultSet = preparedstatement.executeQuery();
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
			preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setString(1, id);
			resultSet = preparedstatement.executeQuery();
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
			preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setString(1, id);
			preparedstatement.setString(2, password);
			resultSet = preparedstatement.executeQuery();
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