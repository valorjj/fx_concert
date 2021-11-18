package controller.customer;

import java.net.URL;
import java.util.ResourceBundle;

import controller.manager.Manager_Main_Controller;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class Info_Page_Update_Controller implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	@FXML
	public void cancel(ActionEvent event) {

		Mainpage_Controller.getInstance().loadpage("personal_info_page");

	}

	@FXML
	public void update(ActionEvent event) {
	}

}
