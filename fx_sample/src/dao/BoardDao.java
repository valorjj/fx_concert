package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.Board;
import domain.Reply;
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
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/javafx_concert?serverTimezone=UTC","root","1234");
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
	public boolean board_register(Board board){
		String sql = "insert into board(b_title,b_writer,b_contents) values(?,?,?)";
		try {
			preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setString(1,board.getB_title());
			preparedStatement.setString(2, board.getB_writer());
			preparedStatement.setString(3, board.getB_contents());
			preparedStatement.executeUpdate();
			return true;
			
			
		} catch (Exception e) {
			
		}return false;
	}
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
						resultSet.getInt(6)
						);
				boards.add(board);
			}
		} catch (Exception e) {} return boards;
	}
	
	// 3. 게시물 조회수 증가 메소드
	public boolean viewupdate(int b_no) {
		String sql = "update board set b_view = b_view+1 where b_no=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, b_no);
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {} return false;
	}
	
	// 4. 게시물 삭제 메소드
	public boolean delete(int b_no) {
		String sql = "delete from board where b_no =?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, b_no);
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {} return false;
	}
	
	// 5. 게시물 수정 메소드
	public boolean update(int b_no, String b_title, String b_contents ) {
		String sql = "update board set b_title = ? , b_contents=? where b_no = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, b_title);
			preparedStatement.setString(2, b_contents);
			preparedStatement.setInt(3, b_no);
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {} return false;
	}
	
	// 6. 댓글등록 메소드
	public boolean replywrite(Reply reply) {
		String sql = "insert into reply(r_contents, r_writer,b_no) values (?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, reply.getR_contents());
			preparedStatement.setString(2, reply.getR_writer());
			preparedStatement.setInt(3, reply.getB_no());
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {} return false;
	}
	
	// 7. 댓글출력 메소드
	public ObservableList<Reply> replylist(int b_no) {
		ObservableList<Reply> replys = FXCollections.observableArrayList();
		String sql = "select * from reply where b_no=? order by r_no desc";
		try {
			preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setInt(1, b_no);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {		// 다음레코드가 없을때 까지 
				Reply reply = new Reply(resultSet.getInt(1),
										resultSet.getString(2),
										resultSet.getString(3),
										resultSet.getString(4),
										resultSet.getInt(5));
				replys.add(reply);
			}
			return replys;
		} catch (Exception e) {} return replys; 
	}
	
	
}