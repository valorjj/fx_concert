package controller.customer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Find_Password_Controller implements Initializable{
@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	// TODO Auto-generated method stub
	
}
@FXML
private Button btn_cancel;

@FXML
private Button btn_find_password;

@FXML
private Label lbl_send_email;

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

}
}


