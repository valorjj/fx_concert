package controller.customer;

import java.net.URL;
import java.util.ResourceBundle;

import domain.Board;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Board_Comment_Controller implements Initializable {

	Board board = Board_View_Controller.board;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txt_title.setText(board.getB_title());
		txt_contents.setText(board.getB_contents());
		lbl_writer.setText("작성자 : "+ board.getB_writer());
		lbl_date.setText("작성일 : " + board.getB_date());
		lbl_view.setText("조회수 : " +  (board.getB_view()+ 1));
		if(!Login_Controller.getInstance().get_login_id().equals(board.getB_writer())) {
			btn_update.setVisible(false);
			btn_delete.setVisible(false);
		}
	}
	@FXML
	private Button btn_update;
	
	@FXML
	private Button btn_back;

	@FXML
	private Button btn_delete;

	@FXML
	private Button btn_write_reply;

	@FXML
	private Label lbl_date;

	@FXML
	private Label lbl_view;

	@FXML
	private Label lbl_writer;

	@FXML
	private TableView<?> reply_list;

	@FXML
	private TextArea txt_contents;

	@FXML
	private TextField txt_reply;

	@FXML
	private TextField txt_title;

	@FXML
	void btn_back(ActionEvent event) {
		Mainpage_Controller.getInstance().loadpage("member_board_view_page");
	}

	@FXML
	void btn_delete(ActionEvent event) {

	}

	@FXML
	void btn_write_reply(ActionEvent event) {

	}

	@FXML
	void btn_update(ActionEvent event) {
		Mainpage_Controller.getInstance().loadpage("member_board_update_page");
	}
}
