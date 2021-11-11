package controller.customer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Find_Id_Controller implements Initializable{
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

    @FXML
    private Button btn_cancel;

    @FXML
    private Button btn_find;

    @FXML
    private TextField btn_find_id;

    @FXML
    private TextField txt_name;

    @FXML
    void cancel(ActionEvent event) {
         Login_Controller.getinstance().loadpage("login_home_page");
    }

    @FXML
    void find_id(ActionEvent event) {

    }
}
