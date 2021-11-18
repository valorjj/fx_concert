
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

	//1. �븘�뱶
	private Connection connection;// db�뿰寃� �씤�꽣�럹�씠�뒪瑜� �꽑�뼵
	private PreparedStatement preparedstatement; // sql �뿰寃� �씤�꽣�럹�씠�뒪�꽑�뼵
	private ResultSet resultSet; // 荑쇰━ �뒗寃곌낵 荑쇰━瑜� �꽑�뼵
	
	// �쁽�옱 �겢�옒�뒪�궡 媛앹껜留뚮뱾湲�
	private static MemberDao memberdao = new MemberDao();
	//2. �깮�꽦�옄
	public MemberDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/javafx_concert?serverTimezone=UTC","root","1234");
		}
		catch (Exception e) {System.out.println(" * DB �뿰�룞 �떎�뙣 : " + e);}
	}
	
	//3. 硫붿냼�뱶
	public static MemberDao getMemberDao() {return memberdao;}
	// 湲곕뒫 硫붿냼�뱶
	//1. �쉶�썝媛��엯 硫붿냼�뱶[�씤�닔瑜� member媛앹껜濡� 諛쏆븘 db�뿉���옣�븯�뒗怨�]
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
	//2. 濡쒓렇�씤 硫붿냼�뱶
	public boolean login(String m_id , String m_pw) {
		// 紐⑤뱺寃��깋�뿉 member�뀒�씠釉붿뿉 議곌굔�씠 = m_id ��m_password 媛��졇�삤湲�
		String sql = "select * from member where m_id=? and m_pw=?";
		try {
			// sql �쓣 �뜲�씠�꽣踰좎씠�뒪�뿉�꽌 �옄���삤�뒗嫄몃줈�꽕�젙
			preparedstatement=connection.prepareStatement(sql);
			preparedstatement.setString(1,m_id );//
			preparedstatement.setString(2, m_pw);// dB�뿉�꽌 媛��졇�삩 m_password瑜� �꽕�젙�븳�떎
			
			resultSet = preparedstatement.executeQuery();
			if (resultSet.next()) {// 寃곌낵�뿉 �떎�쓬�궡�슜�씠�엳�쑝硫� true
				return true;
			}else {return false;}
		} catch (Exception e) {} return false;
	}
	
	//3. �븘�씠�뵒李얘린硫붿냼�뱶
		public String find_id(String m_name ,String m_email) {
			String sql = "select m_id from member where m_name=? and m_email=?";
			try {
				preparedstatement= connection.prepareStatement(sql);
				preparedstatement.setString(1,m_name );
				preparedstatement.setString(2, m_email);
				resultSet=preparedstatement.executeQuery();
				//5.sql 寃곌낵
				if(resultSet.next()) {
					return resultSet.getString(1);
				}else {return null;}
			}catch (Exception e) {} return null;
		}

		// 4. �뙣�뒪�썙�뱶 李얘린 硫붿냼�뱶
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
	      
		
	// 5.�쉶�썝�닔�젙 硫붿냼�뱶
	
	// 6.�쉶�썝�깉�눜 硫붿냼�뱶
	
	// 7.�쉶�썝議고쉶 硫붿냼�뱶
	
	// 8. �씠硫붿씪 �쟾�넚 硫붿냼�뱶
	public void sendmail(String tomail, String msg) {
		// 蹂대궦�궗�엺 �젙蹂� 
		String fromemail = "kimji1218@naver.com";
		String frompassword = "";
		
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.naver.com");
		properties.put("mail.smtp.port", 587);
		properties.put("mail.smtp.auth", true);
		
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			// �씡紐� 援ы쁽媛앹껜
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromemail, frompassword);
			}
		});
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromemail));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(tomail));
			
			message.setSubject("�쉶�썝�떂�쓽 鍮꾨�踰덊샇 寃곌낵");
			message.setText("�쉶�썝�떂�쓽 鍮꾨�踰덊샇 : "+ msg);
			Transport.send(message);
		} catch (Exception e) {}
			
	}
}