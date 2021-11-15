package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.Board;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BoardDao {

	// 1. 필드
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	
	// 객체
	private static BoardDao boardDao = new BoardDao();
	
	public BoardDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/fx_concert?serverTimezone=UTC","root","1234");
		} catch (Exception e) {
			System.out.println("Board DB 연동 실패 : " + e);
		}
	}
	
	// 객체 반환 메소드
	public static BoardDao getBoardDao() {
		return boardDao;
	}
	// 메소드[CRUD]
		// 1.게시물 등록 메소드
		// 2.게시물 조회 메소드
	public ObservableList<Board> boardlist() {
		// 리스트 선언
		ObservableList<Board> boards = FXCollections.observableArrayList();
		String sql = "select * from board order by b_no desc";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			// 2. 검색된[쿼리] 레코드 하나씩 객체화
			while(resultSet.next()) {
				// 쿼리 결과내 레코드가 없을때 까지 반복
				Board board = new Board(
						resultSet.getInt(1),
						resultSet.getString(2),
						resultSet.getString(3),
						resultSet.getString(4),
						resultSet.getString(5),
						resultSet.getInt(6),
						resultSet.getInt(7)
						);
				boards.add(board);
			}
		} catch (Exception e) {} return boards;
	}
	
	// 3. 게시무 조회수 증가 메소드
	public boolean viewupdate(int b_no) {
		String sql = "update board set b_view = b_view+1 where b_no=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, b_no);
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {} return false;
		
	}
	
	
	
}
