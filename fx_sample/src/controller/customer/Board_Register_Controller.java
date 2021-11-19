package controller.customer;

import dao.BoardDao;
import domain.Board;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Board_Register_Controller {

	@FXML
	private Button btn_back;

	@FXML
	private Button btn_register;

	@FXML
	private TextArea txt_contents;

	@FXML
	private TextField txt_title;

	@FXML
	void btn_back(ActionEvent event) {
		Mainpage_Controller.getInstance().loadpage("member_board_view_page");
	}

	@FXML
	void btn_register(ActionEvent event) {
		Board board = new Board(txt_title.getText(), Login_Controller.getInstance().get_login_id(),
				txt_contents.getText());
		boolean check = BoardDao.getBoardDao().board_register(board);
		Alert alert = new Alert(AlertType.INFORMATION);
		if (check) {
			alert.setHeaderText("등록 작성 완료");
			alert.showAndWait();
			Mainpage_Controller.getInstance().loadpage("member_board_view_page");
		} else {
			alert.setHeaderText("등록 작성 실패");
			alert.showAndWait();
		}

	}

}
