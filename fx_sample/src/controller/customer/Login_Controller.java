package controller.customer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login_Controller implements Initializable {

	@FXML
	private Button btn_find_info;

	@FXML
	private Button btn_log_in;

	@FXML
	private Button btn_sign_up;

	@FXML
	private TextField txt_id;

	@FXML
	private PasswordField txt_password;

	@FXML
	void btn_find_info(ActionEvent event) {

	}

	@FXML
	void btn_log_in(ActionEvent event) {

	}

	@FXML
	void btn_sign_up(ActionEvent event) {

	}

	private static Login_Controller instance;

	public Login_Controller() {
		// 2. 현재 클래스의 메모리 호출 = this
		instance = this; // [ 현재클래스의 멤버 포함 ]
	}

	// 3. private instance반환
	public static Login_Controller getinstance() {
		return instance;
	}

	// 1. 맨 처음 화면, 로그인 화면

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	public String get_login_id() {
		return txt_id.getText();
	}

}
