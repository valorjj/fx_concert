package controller.customer;

import java.net.URL;
import java.util.ResourceBundle;

import dao.MemberDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Find_Id_Controller implements Initializable {
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@FXML
	private Button btn_cancel;

	@FXML
	private Button btn_find_id;

	@FXML
	private TextField txt_email;

	@FXML
	private TextField txt_name;

	@FXML
	void cancel(ActionEvent event) {
		Login_Controller.getinstance().loadpage("login_home_page");
	}

	@FXML
	void find_id(ActionEvent event) {

		String result = MemberDao.getMemberDao().find_id(txt_name.getText(), txt_email.getText());
		if (result == null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("id 찾기 실패");
			alert.showAndWait();

		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("id 찾기 성공 ");
			alert.setContentText("회원님의 아이디는 : " + result);
			alert.showAndWait();
		}

	}
}// ce
