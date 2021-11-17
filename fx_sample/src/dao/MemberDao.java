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

	//1. �ʵ�
	private Connection connection;// db���� �������̽��� ����
	private PreparedStatement preparedstatement; // sql ���� �������̽�����
	private ResultSet resultSet; // ���� �°�� ������ ����
	
	// ���� Ŭ������ ��ü�����
	private static MemberDao memberdao = new MemberDao();
	//2. ������
	public MemberDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/fx_concert?serverTimezone=UTC","root","1234");
		}
		catch (Exception e) {System.out.println(" * DB ���� ���� : " + e);}
	}
	
	//3. �޼ҵ�
	public static MemberDao getMemberDao() {return memberdao;}
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
		} catch (Exception e) {} return false;
	}
	//2. �α��� �޼ҵ�
	public boolean login(String m_id , String m_pw) {
		// ���˻��� member���̺� ������ = m_id ��m_password ��������
		String sql = "select * from member where m_id=? and m_pw=?";
		try {
			// sql �� �����ͺ��̽����� �������°ɷμ���
			preparedstatement=connection.prepareStatement(sql);
			preparedstatement.setString(1,m_id );//
			preparedstatement.setString(2, m_pw);// dB���� ������ m_password�� �����Ѵ�
			
			resultSet = preparedstatement.executeQuery();
			if (resultSet.next()) {// ����� ���������������� true
				return true;
			}else {return false;}
		} catch (Exception e) {} return false;
	}
	
	//3. ���̵�ã��޼ҵ�
		public String find_id(String m_name ,String m_email) {
			String sql = "select m_id from member where m_name=? and m_email=?";
			try {
				preparedstatement= connection.prepareStatement(sql);
				preparedstatement.setString(1,m_name );
				preparedstatement.setString(2, m_email);
				resultSet=preparedstatement.executeQuery();
				//5.sql ���
				if(resultSet.next()) {
					return resultSet.getString(1);
				}else {return null;}
			}catch (Exception e) {} return null;
		}

		// 4. �н����� ã�� �޼ҵ�
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
	      
		
	// 5.ȸ������ �޼ҵ�
	
	// 6.ȸ��Ż�� �޼ҵ�
	
	// 7.ȸ����ȸ �޼ҵ�
	
	// 8. �̸��� ���� �޼ҵ�
	public void sendmail(String tomail, String msg) {
		// ������� ���� 
		String fromemail = "kimji1218@naver.com";
		String frompassword = "";
		
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.naver.com");
		properties.put("mail.smtp.port", 587);
		properties.put("mail.smtp.auth", true);
		
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			// �͸� ������ü
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromemail, frompassword);
			}
		});
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromemail));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(tomail));
			
			message.setSubject("ȸ������ ��й�ȣ ���");
			message.setText("ȸ������ ��й�ȣ : "+ msg);
			Transport.send(message);
		} catch (Exception e) {}
			
	}
		
	
	
	
}