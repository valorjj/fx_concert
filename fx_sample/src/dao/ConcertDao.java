package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import domain.Concert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ConcertDao {

	// 1.필드
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	// 현재 클래스내 객체 만들기
	private static ConcertDao concertDao = new ConcertDao();
	// 2.생성자
	public ConcertDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/fx_concert?serverTimezone=UTC","root","1234");
		} catch (Exception e) {
			System.out.println("DB연동 실패 : " + e);
		}
	}
	
	// 3.메소드
	public static ConcertDao getConcertDao() {return concertDao;}
	
	// 기능 메소드
	
	// 1.콘서드 등록메소드
	public boolean register(Concert concert) {
		// 1. SQL 작성
		String sql = "insert into concert(c_title,c_artist,c_info,c_date,c_time,c_R_no,c_S_no,c_D_no,c_E_no,c_R_price,c_S_price,c_D_price,c_E_price) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		// 2. SQL -> DB연결
		try {
			preparedStatement = connection.prepareStatement(sql);
			// 3. SQL 설정
			preparedStatement.setString(1, concert.getC_title());
			preparedStatement.setString(2, concert.getC_artist());
			preparedStatement.setString(3, concert.getC_info());
			preparedStatement.setString(4, concert.getC_date());
			preparedStatement.setInt(5, concert.getC_time());
			preparedStatement.setInt(6, concert.getC_R_no());
			preparedStatement.setInt(7, concert.getC_S_no());
			preparedStatement.setInt(8, concert.getC_D_no());
			preparedStatement.setInt(9, concert.getC_E_no());
			preparedStatement.setInt(10, concert.getC_R_price());
			preparedStatement.setInt(11, concert.getC_S_price());
			preparedStatement.setInt(12, concert.getC_D_price());
			preparedStatement.setInt(13, concert.getC_E_price());
			// 4. SQL 실행
			preparedStatement.executeUpdate();
			// 5. SQL 결과 실행
			return true;
		}
		catch(Exception e) {System.out.println(e);} return false;
	}
	
	// 2.콘서트 수정메소드
	public boolean update(Concert concert) {
		String sql = "update concert set c_title =?, c_artist =? , c_info=? , c_date=?, c_time =? , c_R_no =?, c_S_no=? , c_D_no=?, c_E_no=?, c_R_price = ?, c_S_price=?,c_D_price =?, c_E_price=? where c_no=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, concert.getC_title());
			preparedStatement.setString(2, concert.getC_artist());
			preparedStatement.setString(3, concert.getC_info());
			preparedStatement.setString(4, concert.getC_date());
			preparedStatement.setInt(5, concert.getC_time());
			preparedStatement.setInt(6, concert.getC_R_no());
			preparedStatement.setInt(7, concert.getC_S_no());
			preparedStatement.setInt(8, concert.getC_D_no());
			preparedStatement.setInt(9, concert.getC_E_no());
			preparedStatement.setInt(10, concert.getC_R_price());
			preparedStatement.setInt(11, concert.getC_S_price());
			preparedStatement.setInt(12, concert.getC_D_price());
			preparedStatement.setInt(13, concert.getC_E_price());
			preparedStatement.setInt(14, concert.getC_no());
			preparedStatement.executeUpdate();
			return true;
		}catch(Exception e ) {} return false;
	}
	// 3.콘서트 삭제 메소드
	public boolean delete(int c_no) {
		String sql = "delete from concert where c_no=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, c_no);
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {} return false;
	}
	
	// 4.콘서트 조회 메소드
	public ObservableList<Concert> concertlist() {
		// 1.리스트선언
		ObservableList<Concert> concerts = FXCollections.observableArrayList();
		
		String sql = "select * from concert order by c_no desc";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Concert concert = new Concert(resultSet.getInt(1),
						resultSet.getString(2), 
						resultSet.getString(3),
						resultSet.getString(4),
						resultSet.getString(5),
						resultSet.getInt(6),
						resultSet.getInt(7), 
						resultSet.getInt(8),
						resultSet.getInt(9),
						resultSet.getInt(10),
						resultSet.getInt(11),
						resultSet.getInt(12),
						resultSet.getInt(13),
						resultSet.getInt(14));
				// 객체 리스트 저장
				concerts.add(concert);
			}
			return concerts;
		} catch (Exception e) {} return concerts;
		
	}
	// 5.콘서트 좌석수 반환메소드(piechart용)
	
	// 6. 콘서트 타이틀 반환 메소드
	public ArrayList<ConcertDate> titlelist() {
		// 1.리스트 선언
		ArrayList<ConcertDate> titles = new ArrayList<>();
		String sql = "select c_title from concert group by c_title ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				ConcertDate date = new ConcertDate(resultSet.getString(1));
				titles.add(date);
			}
			return titles;
		} catch (Exception e) {} return titles;
		
	}
	// 7. 콘서트 날짜 반환 메소드
	
	// 8. 콘서트 시간 반환 메소드
	
	
	
	
	
}