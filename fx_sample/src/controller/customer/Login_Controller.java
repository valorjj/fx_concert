package controller.customer;

import java.net.URL;
import java.util.ResourceBundle;

import dao.MemberDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Login_Controller implements Initializable {

	private static Login_Controller instance;

	private static Login_Controller getinstance() {
		return instance;
	}

	public Login_Controller() {

		instance = this;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public String get_login_id() {
		return txt_id.getText();
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
		boolean check = MemberDao.getMemberDao().login(txt_id.getText(), txt_password.getText());
		Alert alert = new Alert(AlertType.INFORMATION);
		if (check) {
			if (txt_id.getText().equals("admin")) {
				alert.setHeaderText("어서오세요 관리자님");
				alert.showAndWait();
				btn_login.getScene().getWindow().hide();
				window_shift1("manager_main_page");
			} else {
				alert.setHeaderText("로그인 성공");
				alert.showAndWait();
				btn_login.getScene().getWindow().hide();
				window_shift1("main_page");
			}
		} else {
			alert.setHeaderText("로그인 실패");
			alert.showAndWait();
		}
	}

	@FXML
	void signup(ActionEvent event) {
		loadpage("signup_page");
	}

	public void window_shift1(String page) {
		try {

			Parent parent = FXMLLoader.load(getClass().getResource("/fxml/" + page + ".fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setResizable(false); // 스테이지 크기 고정
			stage.show();

		} catch (Exception e) {
		}
	}

}