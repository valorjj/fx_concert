package controller.customer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Mainpage_Controller implements Initializable {

	private static Mainpage_Controller instance;

	@FXML
	private BorderPane main_page_boardpane;

	@FXML
	private Button btn_reservation;
	
	@FXML
	private Button btn_myinfo;
	
	@FXML
	private Button btn_board;
	
	@FXML
	private Button btn_route;
	
	@FXML 
	private Button btn_logout;

	public static Mainpage_Controller getInstance() {
		return instance;
	}

	public Mainpage_Controller() {
		instance = this;

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	public void loadpage(String page) {

		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/fxml/" + page + ".fxml"));
			main_page_boardpane.setCenter(parent);
		} catch (Exception e) {
		}

	}

	@FXML
	public void btn_reservation(ActionEvent event) {
		btn_reservation.getScene().getWindow().hide();
		window_shift("concert_select_page");

	}

	@FXML
	public void btn_myinfo(ActionEvent event) {
		loadpage("personal_info_page");
	}
	
	@FXML
	public void btn_board(ActionEvent event) {
		loadpage("manager_register_view_page");
	}
	
	
	@FXML
	public void btn_route(ActionEvent event) {
		
	}
	
	@FXML
	public void btn_logout(ActionEvent event) {
		btn_logout.getScene().getWindow().hide();
		window_shift("login_page");
	}
	public void window_shift(String page) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/fxml/" + page + ".fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();

		} catch (Exception e) {
		}
	}

}