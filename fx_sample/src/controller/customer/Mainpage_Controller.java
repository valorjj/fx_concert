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
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

public class Mainpage_Controller implements Initializable {

	int concert_pop_up = 1;

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
	@FXML
	private Button btn_home;
	@FXML
	private static ImageView img_main_page;
	@FXML
	private AnchorPane anchorpane_main_page;

	private static Mainpage_Controller instance;

	public static Mainpage_Controller getInstance() {
		return instance;
	}

	public Mainpage_Controller() {
		instance = this;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadpage("main_page_home_concert1");
		concert_pop_up++;
	}

	public void loadpage(String page) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/fxml/" + page + ".fxml"));
			main_page_boardpane.setCenter(parent);
		} catch (Exception e) {
		}
	}

	// 버튼을 누르면 예약 페이지로 이동
	@FXML
	public void btn_reservation(ActionEvent event) {
		btn_reservation.getScene().getWindow().hide();
		window_shift("reservation_page_home");
	}

	// 버튼을 누르면 내 정보 페이지로 이동
	@FXML
	public void btn_myinfo(ActionEvent event) {
		btn_home.setStyle("-fx-background-color : #494854");
		btn_board.setStyle("-fx-background-color : #494854");
		btn_myinfo.setStyle("-fx-background-color : #bfbadb");
		btn_route.setStyle("-fx-background-color : #494854");
		btn_logout.setStyle("-fx-background-color : #494854");
		loadpage("personal_info_page");
	}

	// 버튼을 누르면 게시판으로 이동
	@FXML
	public void btn_board(ActionEvent event) {

		btn_home.setStyle("-fx-background-color : #494854");
		btn_board.setStyle("-fx-background-color : #bfbadb");
		btn_myinfo.setStyle("-fx-background-color : #494854");
		btn_route.setStyle("-fx-background-color : #494854");
		btn_logout.setStyle("-fx-background-color : #494854");
		loadpage("member_board_view_page");
	}

	@FXML
	public void btn_route(ActionEvent event) {
		window_shift("concert_route_page");
	}

	// 버튼을 누르면 계정 로그아웃
	// 화면이 닫히고 로그인 페이지로 이동
	@FXML
	public void btn_logout(ActionEvent event) {
		btn_logout.getScene().getWindow().hide();
		window_shift("login_page");
	}

	// 버튼을 누르면 가장 첫 페이지로 이동
	@FXML
	public void btn_home(ActionEvent event) {

		btn_home.setStyle("-fx-background-color : #bfbadb");
		btn_board.setStyle("-fx-background-color : #494854");
		btn_myinfo.setStyle("-fx-background-color : #494854");
		btn_route.setStyle("-fx-background-color : #494854");
		btn_logout.setStyle("-fx-background-color : #494854");

		if (concert_pop_up == 1) {
			loadpage("main_page_home_concert1");
			concert_pop_up++;
		} else if (concert_pop_up == 2) {
			loadpage("main_page_home_concert2");
			concert_pop_up++;
		} else if (concert_pop_up == 3) {
			loadpage("main_page_home_concert3");
			concert_pop_up = 1;
		}
	}

	// 인수로 전달된 페이지로 이동한다.
	public void window_shift(String page) {
		try {
			Stage stage = new Stage();
			Parent parent = FXMLLoader.load(getClass().getResource("/fxml/" + page + ".fxml"));
			Scene scene = new Scene(parent);

			stage.setScene(scene);
			stage.show();

		} catch (Exception e) {
		}
	}

}