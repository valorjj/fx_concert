package controller.customer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Signup_Controller implements Initializable{
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
		

	

	 @FXML
	    private Button btn_cancel;

	    @FXML
	    private Button btn_signup;

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
	    	Login_Controller.getinstance().loadpage("login_home_page");
	    }

	    @FXML
	    void man(ActionEvent event) {

	    }

	    @FXML
	    void signup(ActionEvent event) {

	    }

	    @FXML
	    void woman(ActionEvent event) {

	    }
}