package controller.manager;

import java.net.URL;
import java.util.ResourceBundle;

import dao.BoardDao;
import domain.Board;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

public class Manager_Board_Management_Controller implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		// 1.DAO ȣ��
		ObservableList<Board> boards = BoardDao.getBoardDao().boardlist();
		//
		TableColumn tc = board_list.getColumns().get(0);
		tc.setCellValueFactory(new PropertyValueFactory<>("b_no"));
		
		tc = board_list.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("b_title"));
		
		tc = board_list.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("b_contents"));
		
		tc = board_list.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("b_writer"));
		
		tc = board_list.getColumns().get(4);
		tc.setCellValueFactory(new PropertyValueFactory<>("b_date"));
		
		// 3. ���̺�信 ����Ʈ �ֱ�
		board_list.setItems(boards);
		
		// Ŭ���� �������� ������ ������ ��ȯ
		board_list.setOnMouseClicked(e-> {
			if(e.getButton().equals(MouseButton.PRIMARY)) {
				board = board_list.getSelectionModel().getSelectedItem();
							// ���̺�信 ���õ� ���� ������
				// ������ ��ȯ
				Manager_Main_Controller.getInstance().loadpage("manager_board_view_page");
			}
		});
	}
	public static Board board;
	
	@FXML
	private TableView<Board> board_list;

}
