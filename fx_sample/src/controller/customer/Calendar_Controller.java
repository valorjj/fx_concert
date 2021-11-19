package controller.customer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Calendar_Controller implements Initializable {

	@FXML
	private Button btn_2pm;

	@FXML
	private Button btn_6pm;

	@FXML
	private Button btn_cancel;

	@FXML
	private Button btn_reservation_confirm;

	@FXML
	private Button btn_reset;

	@FXML
	private ImageView concert_image_view;

	@FXML
	private GridPane gridpane_calendar;

	@FXML
	private Label lbl_D_price;

	@FXML
	private Label lbl_D_remaining;

	@FXML
	private Label lbl_E_price;

	@FXML
	private Label lbl_E_remaining;

	@FXML
	private Label lbl_R_price;

	@FXML
	private Label lbl_R_remaining;

	@FXML
	private Label lbl_S_price;

	@FXML
	private Label lbl_S_remaining;

	@FXML
	private Label lbl_cal;

	@FXML
	private Label lbl_concert_date_1;

	@FXML
	private Label lbl_concert_date_2;

	@FXML
	private Label lbl_concert_date_3;

	@FXML
	private Label lbl_concert_duration;

	@FXML
	private Label lbl_concert_tile;

	@FXML
	private Label lbl_small;

	@FXML
	Button btn_1;

	@FXML
	Button btn_2;

	@FXML
	void btn_2pm(ActionEvent event) {

	}

	@FXML
	void btn_6pm(ActionEvent event) {

	}

	@FXML
	void btn_cancel(ActionEvent event) {

	}

	@FXML
	void btn_reservation_confirm(ActionEvent event) {

	}

	@FXML
	void btn_reset(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void set_btn_id() {

		for (int i = 1; i <= 42; i++) {

		}

	}

}
