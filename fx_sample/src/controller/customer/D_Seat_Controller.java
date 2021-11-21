package controller.customer;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeMap;

import dao.ConcertDao;
import dao.SeatDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class D_Seat_Controller implements Initializable {

	@FXML
	private GridPane gridpane_D;
	@FXML
	private Rectangle left_block;
	@FXML
	private Rectangle right_block;
	@FXML
	private Rectangle top_block;
	@FXML
	private Rectangle bottom_block;
	@FXML
	private Button btn_select_done;
	@FXML
	private Button btn_clear;

	@FXML
	void btn_select_done(ActionEvent event) {

		Reservation_Seat_Select_Controller.seat_total = Reservation_Seat_Select_Controller.seat_total - D_count;
		Reservation_Seat_Select_Controller.is_D_set = true;
		btn_disable2();
		Reservation_Seat_Select_Controller.D_status_check = this.D_status_check;

	}

	private static D_Seat_Controller instance;

	public static D_Seat_Controller get_instance() {

		return instance;
	}

	public D_Seat_Controller() {
		instance = this;
	}

	int seat_limit = Reservation_Seat_Select_Controller.how_many_person;

	ArrayList<Button> D_buttons = new ArrayList<>();
	int c_no = ConcertDao.getConcertDao().get_concert_c_no(Reservation_Date_Select_Controller.user_selected_date,
			Reservation_Date_Select_Controller.user_selected_time);
	TreeMap<Integer, String> D_seat_Map = new TreeMap<Integer, String>();
	int manager_input_D_seat_no = ConcertDao.getConcertDao().get_remaining_seat_D(
			Reservation_Date_Select_Controller.user_selected_date,
			Reservation_Date_Select_Controller.user_selected_time);
	ArrayList<Integer> D_status_check = SeatDao.getSeatDao().get_seat_status(c_no, "D",
			Reservation_Concert_Select_Controller.concert_number);
	int D_count = 0;

	int button_status_check = 99999;
	int button_status_check2 = 99999;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		left_block.setVisible(false);
		bottom_block.setVisible(false);

		D_seat_create();

	}

	public void D_seat_create() {

		int row = 0;
		int col = 0;

		for (int i = 0; i < manager_input_D_seat_no; i++) {

			Button button = new Button();
			button.setPrefSize(42, 42);
			button.setId(i + "");
			button.setText((i + 1) + "");

			if (D_status_check != null && D_status_check.size() != 0) {
				button_status_check = D_status_check.get(i);
			}

			switch (button_status_check) {
			case 0: // 좌석이 예약 가능한 상태
				break;
			case 1: // 현재 선택한 좌석
				button.setStyle("-fx-background-color: green");
				break;
			case 2: // 예약이 불가능한 좌석 (이미 다른 사람이 예약했음)
				button.setStyle("-fx-background-color: red");
				button.setDisable(true);
				break;
			}

			button.setOnAction((ActionEvent) -> {

				int button_status_check2 = D_status_check.get(Integer.parseInt(button.getId()));
				switch (button_status_check2) {
				case 0: // 좌석이 예약 가능한 상태
					D_count = D_count + 1;
					D_status_check.set(Integer.parseInt(button.getId()), 1);
					button.setStyle("-fx-background-color: green");
					break;
				case 1:
					D_count = D_count - 1;
					button.setStyle("-fx-background-color: green");
					break;

				case 2: // 예약이 불가능한 좌석 (이미 다른 사람이 예약했음)
					button.setStyle("-fx-background-color: red");
					button.setDisable(true);
					break;
				}

				if (D_count == seat_limit) {
					Reservation_Seat_Select_Controller.is_R_set = true;
					Reservation_Seat_Select_Controller.is_S_set = true;
					Reservation_Seat_Select_Controller.is_D_set = true;
					Reservation_Seat_Select_Controller.is_E_set = true;
					btn_select_done.setVisible(false);
					btn_disable();
					Reservation_Seat_Select_Controller.D_status_check = this.D_status_check;

				}
			});

			D_buttons.add(button);

			if (i % 10 == 0) {
				row = 0;
				col++;
				gridpane_D.add(button, row++, col);
			} else {
				gridpane_D.add(button, row++, col);
			}

		}

	}

	@FXML
	public void btn_clear(ActionEvent event) {

		// 선택한 좌석 수 초기화
		D_count = 0;

		D_seat_Map.clear();
		seat_limit = Reservation_Seat_Select_Controller.how_many_person;

		for (int d : D_status_check) {
			if (d == 1) {
				d = 0;
			}

		}
		D_seat_create();

	}

	public void btn_disable() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("가능한 모든 좌석을 선택하셨습니다.");
		alert.showAndWait();
		Reservation_Seat_Select_Controller.seat_total = 0;

		for (Button button : D_buttons) {
			button.setDisable(true);
		}

	}

	public void btn_disable2() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("D석 좌석 선택이 완료되었습니다. ");
		alert.showAndWait();
		for (Button button : D_buttons) {
			button.setDisable(true);
		}

		Alert alert2 = new Alert(AlertType.INFORMATION);
		alert2.setHeaderText("총[" + D_count + "]개 의 좌석이 선택되었습니다.\n" + Reservation_Seat_Select_Controller.seat_total
				+ "개 좌석을 선택할 수 있습니다. ");
		alert2.showAndWait();

	}

}
