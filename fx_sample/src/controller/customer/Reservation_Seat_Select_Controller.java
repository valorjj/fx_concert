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
import javafx.scene.layout.BorderPane;

public class Reservation_Seat_Select_Controller implements Initializable {

	private static Reservation_Seat_Select_Controller instance;

	public Reservation_Seat_Select_Controller get_instance() {
		return instance;
	}

	public Reservation_Seat_Select_Controller() {
		instance = this;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	//////////////////////////////////////////////////////////

	public void load_page(String page) {

		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/fxml/" + page + ".fxml"));

			borderpane_payment.setCenter(parent);

		} catch (IOException e) {
			e.printStackTrace();
		}

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
	private Button btn_how_many_people;

	@FXML
	private Button btn_payment;

	@FXML
	private Button btn_reset;

	@FXML
	private Button btn_view_entire_seat;

	@FXML
	void btn_D_select(ActionEvent event) {

	}

	@FXML
	void btn_E_select(ActionEvent event) {

	}

	@FXML
	void btn_R_select(ActionEvent event) {

	}

	@FXML
	void btn_S_select(ActionEvent event) {

	}

	@FXML
	void btn_how_many_people(ActionEvent event) {

	}

	@FXML
	void btn_payment(ActionEvent event) {

	}

	@FXML
	void btn_reset(ActionEvent event) {

	}

	@FXML
	void btn_view_entire_seat(ActionEvent event) {

	}

}
