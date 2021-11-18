package controller.customer;

import java.net.URL;
import java.util.ResourceBundle;

import dao.MemberDao;
import dao.ReservationDao;
import domain.Reservation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Reservation_Home_Controller implements Initializable {

	/*
	 * 메인페이지에서 '예약' 버튼을 누르면 표시되는 화면입니다. 여기서 콘서트 선택 -> 날짜 및 시간 선택 -> 좌석 선택 -> 선택한 옵션을
	 * 출력하고 결제진행 각각의 페이지는 하단 anchorpane 에 표시가 되며, borderpane center 에 위치한 anchorpane
	 * 만 바뀌며 loadpage 를 사용해서 화면 전환을 하게 됩니다. 각각의 페이지가 넘어갈 때 마다,
	 * Concert_Select_Controller 에 있는 버튼의 상태를 바꿔주어야 합니다. 보는 사람으로 하여금 해당 페이지가 활성화되었다는
	 * 시각적인 알림을 주어야 합니다.
	 */

	//////////////////////////////////////////////////////////////////

	private static Reservation_Home_Controller instance;

	public static Reservation_Home_Controller getinstance() {
		return instance;
	}

	public Reservation_Home_Controller() {
		instance = this;
	}

	//////////////////////////////////////////////////////////////////

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		reservation_loadpage("reservation_page_concert_select");

	}

	//////////////////////////////////////////////////////////////////

	public void reservation_loadpage(String page) {
		try {

			Parent parent = FXMLLoader.load(getClass().getResource("/fxml/" + page + ".fxml"));
			reservation_borderpane.setCenter(parent);

		} catch (Exception e) {
		}

	}

	//////////////////////////////////////////////////////////////////

	@FXML
	private Button btn_cancel;

	@FXML
	private Button btn_reservation_confirm;

	@FXML
	private Button btn_reservation_date;

	@FXML
	private Button btn_reservation_home;

	@FXML
	private Button btn_reservation_payment;

	@FXML
	private Button btn_reservation_seat;

	@FXML
	private AnchorPane reservation_menubar_anchorpane;

	@FXML
	private BorderPane reservation_borderpane;

	@FXML
	private Button btn_reservation;

	//////////////////////////////////////////////////////////////////

	@FXML
	void btn_cancel(ActionEvent event) {
		btn_cancel.getScene().getWindow().hide();
		window_shift("main_page");
	}

	@FXML
	void btn_reservation_confirm(ActionEvent event) {
		//
		reservation_loadpage("");

	}

	@FXML
	void btn_reservation_date(ActionEvent event) {
		reservation_loadpage("reservation_page");

	}

	@FXML
	void btn_reservation_home(ActionEvent event) {
		reservation_loadpage("reservation_concert_select_page");

	}

	@FXML
	void btn_reservation_payment(ActionEvent event) {
		reservation_loadpage("payment_page");

	}

	@FXML
	void btn_reservation_seat(ActionEvent event) {
		reservation_loadpage("seat_select_page");

	}

	@FXML
	public void btn_reservation(ActionEvent event) {
		reservation_loadpage("reservation_concert_select_page");

	}

	//////////////////////////////////////////////////////////////////

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
