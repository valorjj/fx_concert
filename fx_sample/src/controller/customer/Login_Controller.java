package controller.customer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class Login_Controller implements Initializable {

	private static Login_Controller instance;

	public Login_Controller() {
		// 2. 현재 클래스의 메모리 호출 = this
		instance = this; // [ 현재클래스의 멤버 포함 ]
	}

	public static Login_Controller getinstance() {
		return instance;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	public void loadpage(String page) {

		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/fxml/" + page + ".fxml"));

			login_page_boarderpane.setCenter(parent);
		} catch (Exception e) {
		}

	}

	@FXML
	private Button btn_login;

	@FXML
	private Button btn_signup;

	@FXML
	private Label lbl_find_id;

	@FXML
	private Label lbl_find_password;

	@FXML
	private BorderPane login_page_boarderpane;

	@FXML
	private TextField txt_id;

	@FXML
	private PasswordField txt_password;

	@FXML
	void find_id(MouseEvent event) {
		loadpage("find_id_page");
	}

	@FXML
	void find_password(MouseEvent event) {
		loadpage("find_password_page");
	}

	@FXML
	void login(ActionEvent event) {

	}

	@FXML
	void signup(ActionEvent event) {
		loadpage("signup_page");
	}

}
