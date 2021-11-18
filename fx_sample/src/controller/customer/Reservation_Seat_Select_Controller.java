package controller.customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Reservation_Seat_Select_Controller implements Initializable {

	static int how_many_person = 0;

	private static Reservation_Seat_Select_Controller instance;

	private static Reservation_Seat_Select_Controller get_instance() {

		return instance;

	}

	public Reservation_Seat_Select_Controller() {

		instance = this;
	}

	public void load_page(String page) {

		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/fxml/" + page + ".fxml"));
			borderpane_payment.setCenter(parent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//////////////////////////////////////////////////////////

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		btn_how_many_1.setVisible(false);
		btn_how_many_2.setVisible(false);
		btn_how_many_3.setVisible(false);
		btn_how_many_4.setVisible(false);

	}

	//////////////////////////////////////////////////////////

	@FXML
	private BorderPane borderpane_payment;

	@FXML
	private Button btn_D_select;

	@FXML
	private Button btn_E_select;

	@FXML
	private Button btn_R_select;

	@FXML
	private Button btn_S_select;

	@FXML
	private Button btn_cancel;

	@FXML
	private Button btn_how_many_1;

	@FXML
	private Button btn_how_many_2;

	@FXML
	private Button btn_how_many_3;

	@FXML
	private Button btn_how_many_4;

	@FXML
	private Button btn_how_many_people;

	@FXML
	private Button btn_payment;

	@FXML
	private Button btn_reset;

	@FXML
	private Button btn_view_entire_seat;

	@FXML
	private Label lbl_D_remain;

	@FXML
	private Label lbl_D_total;

	@FXML
	private Label lbl_E_remain;

	@FXML
	private Label lbl_E_total;

	@FXML
	private Label lbl_R_remain;

	@FXML
	private Label lbl_R_total;

	@FXML
	private Label lbl_S_remain;

	@FXML
	private Label lbl_S_total;

	////////////////////////////////////////////////////////////////

	@FXML
	void btn_D_select(ActionEvent event) {

		load_page("D_seat");

	}

	@FXML
	void btn_E_select(ActionEvent event) {

		load_page("E_seat");

	}

	@FXML
	void btn_R_select(ActionEvent event) {

		load_page("R_seat");

	}

	@FXML
	void btn_S_select(ActionEvent event) {

		load_page("S_seat");

	}

	//////////////////////////////////////////////////

	@FXML
	void btn_cancel(ActionEvent event) {

		Reservation_Home_Controller.getinstance().reservation_loadpage("reservation_page_date_select");

	}
	///////////////////////////////////////////////////

	@FXML
	void btn_how_many_people(ActionEvent event) {

		btn_how_many_people.setVisible(false);
		btn_how_many_1.setVisible(true);
		btn_how_many_2.setVisible(true);
		btn_how_many_3.setVisible(true);
		btn_how_many_4.setVisible(true);

	}

	////////////////////////////////////////////////////////////////

	@FXML
	void btn_payment(ActionEvent event) {

	}

	////////////////////////////////////////////////////////////////
	@FXML
	void btn_reset(ActionEvent event) {

		// 모든 선택을 무효화 시킨다.

		btn_how_many_people.setVisible(true);
		btn_how_many_1.setVisible(false);
		btn_how_many_2.setVisible(false);
		btn_how_many_3.setVisible(false);
		btn_how_many_4.setVisible(false);

	}

	////////////////////////////////////////////////////////////////
	@FXML
	void btn_view_entire_seat(ActionEvent event) {

		// image 파일을 하나 불러서 새로운 창을 띄웁니다

	}

	////////////////////////////////////////////////////////////////

	@FXML
	void btn_how_many_1(ActionEvent event) {

	}

	@FXML
	void btn_how_many_2(ActionEvent event) {

	}

	@FXML
	void btn_how_many_3(ActionEvent event) {

	}

	@FXML
	void btn_how_many_4(ActionEvent event) {

	}
}
