package controller.customer;

import java.net.URL;
import java.util.ResourceBundle;

import dao.MemberDao;
import domain.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Signup_Controller implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {// 처음 들어왔을는 경고문을 공백으로 보여준다
		lbl_confirm.setText("");
	}

	@FXML
	private Button btn_cancel;

	@FXML
	private Button btn_signup;

	@FXML
	private Label lbl_confirm;

	@FXML
	private RadioButton radio_btn_man;

	@FXML
	private RadioButton radio_btn_woman;

	@FXML
	private ToggleGroup sex;

	@FXML
	private TextField txt_age;

	@FXML
	private PasswordField txt_confirm_password;

	@FXML
	private TextField txt_email;

	@FXML
	private TextField txt_id;

	@FXML
	private TextField txt_name;

	@FXML
	private PasswordField txt_password;

	@FXML
	void cancel(ActionEvent event) {
		Login_Controller.getInstance().loadpage("login_home_page");
	}

	@FXML
	void man(ActionEvent event) {

	}

	@FXML
	void woman(ActionEvent event) {

	}

	@FXML
	void signup(ActionEvent event) {

		// 조건 넣는곳
		// id 아이디 조건 4자리이상12자리이하
		if (txt_id.getText().length() < 4 || txt_id.getText().length() > 12) {
			lbl_confirm.setText("아이디는 4~12 글자로 만 가능합니다.");
			return;
		}
		// 비밀번호 조건 6자리 이상 6자리이하
		if (txt_password.getText().length() < 6 || txt_password.getText().length() > 12) {
			lbl_confirm.setText("비밀번호는 6~12 글자로 만 가능합니다. ");
			return;
		} // txt_password와 txt_confirm_password와 동일한지

		if (!txt_password.getText().equals(txt_confirm_password.getText())) {
			lbl_confirm.setText("비밀번호 가 동일하지 않습니다. ");
			return;
		}
		// 이름은 두글자 이상으로만입력가능
		if (txt_name.getText().length() < 2) {
			return;
		}

		if (txt_email.getText().length() < 5 || !txt_email.getText().contains("@")) {
			lbl_confirm.setText("이메일 형식으로 입력해주세요.");
			return;
		}

		// 2. 중복체크
		// boolean idcheck = Member_Dao.getMember_Dao().id

		// db연동 하는곳
		Member member = new Member(txt_id.getText(), txt_password.getText(), txt_name.getText(), txt_email.getText(), 0,
				"man");
		boolean check = MemberDao.getMemberDao().signup(member);
		Alert alert = new Alert(AlertType.INFORMATION);
		if (check) {

			alert.setHeaderText("[[알림]]  회원가입 성공");
			alert.showAndWait();

		} else {

			alert.setHeaderText("[[알림]] 회원가입 실패");
			alert.showAndWait();

		}

	}

}