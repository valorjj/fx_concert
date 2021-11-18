package controller.manager;

import java.net.URL;
import java.util.ResourceBundle;

import controller.customer.Mainpage_Controller;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Manager_Main_Controller implements Initializable{
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	rec_register.setVisible(false);
    	rec_board_management.setVisible(false);
    	rec_reservation_status.setVisible(false);
    	rec_update_delete.setVisible(false);

		loadpage("manager_main_home_page");
	}
	///// 페이지 자체를 객체화 해버림
	private static Manager_Main_Controller instance;
	
	public Manager_Main_Controller() {
		instance = this;
	}
	
	public static Manager_Main_Controller getInstance() {
		return instance;
	}
	/////

    @FXML
    private Rectangle rec_board_management;

    @FXML
    private Rectangle rec_register;

    @FXML
    private Rectangle rec_reservation_status;

    @FXML
    private Rectangle rec_update_delete;
	
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
    private Label lbl_manager_page;
    
    @FXML
    private BorderPane manager_page_boardpane;

    @FXML
    void btn_board_management(ActionEvent event) {
    	rec_board_management.setVisible(true);
    	rec_register.setVisible(false);
    	rec_reservation_status.setVisible(false);
    	rec_update_delete.setVisible(false);
    	loadpage("manager_board_management_page");
    }

    @FXML
    void btn_logout(ActionEvent event) {
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setHeaderText("수고하셨습니다. 관리자님");
    	alert.showAndWait();
    	btn_logout.getScene().getWindow().hide();
    	window_shift("login_page");
    	
    }

    @FXML
    void btn_register(ActionEvent event) {
    	rec_register.setVisible(true);
    	rec_board_management.setVisible(false);
    	rec_reservation_status.setVisible(false);
    	rec_update_delete.setVisible(false);
    	loadpage("manager_register_page");
    }

    @FXML
    void btn_reservation_status(ActionEvent event) {
    	rec_reservation_status.setVisible(true);
    	rec_register.setVisible(false);
    	rec_board_management.setVisible(false);
    	rec_update_delete.setVisible(false);
    	loadpage("manager_chart_view_page");
    }

    @FXML
    void btn_update_delete(ActionEvent event) {
    	rec_update_delete.setVisible(true);
    	rec_register.setVisible(false);
    	rec_board_management.setVisible(false);
    	rec_reservation_status.setVisible(false);
    	loadpage("manager_update_delete_page");
    }

    @FXML
    void lbl_manager_page(MouseEvent event) {
    	rec_register.setVisible(false);
    	rec_board_management.setVisible(false);
    	rec_reservation_status.setVisible(false);
    	rec_update_delete.setVisible(false);
    	loadpage("manager_main_home_page");
    }
    public void loadpage(String page) {

		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/fxml/" + page + ".fxml"));
			manager_page_boardpane.setCenter(parent);
		} catch (Exception e) {
		}

	}
    
    public void window_shift(String page) {
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