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
		
		
		// 1.DAO 호출
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
		
		// 3. 테이블뷰에 리스트 넣기
		board_list.setItems(boards);
		
		// 클릭한 아이템을 가지고 페이지 전환
		board_list.setOnMouseClicked(e-> {
			if(e.getButton().equals(MouseButton.PRIMARY)) {
				board = board_list.getSelectionModel().getSelectedItem();
							// 테이블뷰에 선택된 모델의 아이템
				// 페이지 전환
				Manager_Main_Controller.getInstance().loadpage("manager_board_view_page");
			}
		});
	}
	public static Board board;
	
	@FXML
	private TableView<Board> board_list;

}