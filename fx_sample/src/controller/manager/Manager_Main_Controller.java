package controller.manager;

import java.net.URL;
import java.util.ResourceBundle;

import controller.customer.Mainpage_Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class Manager_Main_Controller implements Initializable{

	///// 페이지 자체를 객체화 해버림
	private static Manager_Main_Controller instance;
	
	public Manager_Main_Controller() {
		instance = this;
	}
	
	public static Manager_Main_Controller getInstance() {
		return instance;
	}
	/////
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	@FXML
    private Button btn_board_management;

    @FXML
    private Button btn_logout;

    @FXML
    private Button btn_register;

    @FXML
    private Button btn_reservation_status;

    @FXML
    private Button btn_update_delete;
    
    @FXML
    private BorderPane manager_page_boardpane;

    @FXML
    void btn_board_management(ActionEvent event) {

    }

    @FXML
    void btn_logout(ActionEvent event) {

    }

    @FXML
    void btn_register(ActionEvent event) {
    	loadpage("manager_register_page");
    }

    @FXML
    void btn_reservation_status(ActionEvent event) {

    }

    @FXML
    void btn_update_delete(ActionEvent event) {
    	loadpage("manager_update_delete_page");
    }

    public void loadpage(String page) {

		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/fxml/" + page + ".fxml"));
			manager_page_boardpane.setCenter(parent);
		} catch (Exception e) {
		}

	}
}
