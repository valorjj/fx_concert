package controller.customer;

import java.net.URL;
import java.util.ResourceBundle;

import dao.MemberDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Find_Password_Controller implements Initializable {
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	private Button btn_cancel;

	@FXML
	private Button btn_find_password;


	@FXML
	private TextField txt_email;

	@FXML
	private TextField txt_id;

	@FXML
	void cancel(ActionEvent event) {
		Login_Controller.getinstance().loadpage("login_home_page");
	}

	@FXML
	void find_password(ActionEvent event) {
		String result = MemberDao.getMemberDao().find_pw(txt_id.getText(),txt_email.getText());
		   
		   Alert alert = new Alert(AlertType.INFORMATION);
		   if(result==null) {
		   
		      alert.setHeaderText("pw 찾기 실패");
		      alert.showAndWait();
		   }else { 
		      
		      alert.setHeaderText("pw찾기 성공");
		      alert.setContentText("회원님의 pw는 : "+result);
		      alert.showAndWait();
		      
		   }

	}
}