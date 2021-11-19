package controller.customer;

import java.net.URL;
import java.util.ResourceBundle;

import dao.BoardDao;
import domain.Board;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

public class Board_View_Controller implements Initializable {
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {// 각컬럼에  사용될 거 정함
		ObservableList<Board>boards = BoardDao.getBoardDao().boardlist();
		TableColumn tc = table_board.getColumns().get(0);
		tc.setCellValueFactory(new PropertyValueFactory<>("b_no"));
		tc = table_board.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("b_title"));
		tc = table_board.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("b_contents"));
		tc = table_board.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("b_writer"));
		tc = table_board.getColumns().get(4);
		tc.setCellValueFactory(new PropertyValueFactory<>("b_date"));
		tc = table_board.getColumns().get(5);
		tc.setCellValueFactory(new PropertyValueFactory<>("b_view"));
	
		table_board.setItems(boards);// 내가 만든 테이블 보드에 다넣기
	
		table_board.setOnMouseClicked( e->{
			if (e.getButton().equals(MouseButton.PRIMARY)) {
				board =table_board.getSelectionModel().getSelectedItem();
			Mainpage_Controller.getInstance().loadpage("member_board_comment_page");
			}
		});
	
	
	}
	public static Board board;


   
    
	@FXML
	private Button btn_back;

	@FXML
	private Button btn_write;
    @FXML
    private TableView<Board> table_board;
   
	
	@FXML
	    void btn_back(ActionEvent event) {
	    Mainpage_Controller.getInstance().loadpage("main_page_home");
	    }
	
	@FXML
	void btn_write(ActionEvent event) {
	Mainpage_Controller.getInstance().loadpage("member_board_register_page");
	}
	 

}
