package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.Board;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BoardDao {

	// 1. �ʵ�
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	
	// ��ü
	private static BoardDao boardDao = new BoardDao();
	
	public BoardDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/fx_concert?serverTimezone=UTC","root","1234");
		} catch (Exception e) {
			System.out.println("Board DB ���� ���� : " + e);
		}
	}
	
	// ��ü ��ȯ �޼ҵ�
	public static BoardDao getBoardDao() {
		return boardDao;
	}
	// �޼ҵ�[CRUD]
		// 1.�Խù� ��� �޼ҵ�
		// 2.�Խù� ��ȸ �޼ҵ�
	public ObservableList<Board> boardlist() {
		// ����Ʈ ����
		ObservableList<Board> boards = FXCollections.observableArrayList();
		String sql = "select * from board order by b_no desc";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			// 2. �˻���[����] ���ڵ� �ϳ��� ��üȭ
			while(resultSet.next()) {
				// ���� ����� ���ڵ尡 ������ ���� �ݺ�
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
	
	// 3. �Խù� ��ȸ�� ���� �޼ҵ�
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
