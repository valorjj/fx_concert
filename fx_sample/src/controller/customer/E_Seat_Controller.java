package controller.customer;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class E_Seat_Controller implements Initializable {
	
	/////////////////////////////////////////////////////////

	@FXML
	private Rectangle left_block;

	@FXML
	private Rectangle right_block;

	@FXML
	private Rectangle top_block;

	@FXML
	private Rectangle bottom_block;

	///////////////////////////////////////

	@FXML
	private Button btn_select_done;

	@FXML
	void btn_select_done(ActionEvent event) {

		Reservation_Seat_Select_Controller.seat_total = Reservation_Seat_Select_Controller.seat_total - E_count;
		Reservation_Seat_Select_Controller.is_E_set = true;
		btn_disable2();

	}

	////////////////////////////////////

	private static E_Seat_Controller instance;

	public static E_Seat_Controller get_instance() {

		return instance;
	}

	public E_Seat_Controller() {
		instance = this;
	}

	@FXML
	private GridPane gridpane_E;

	public int seat_limit = Reservation_Seat_Select_Controller.how_many_person;

	int sum = 0;

	public static ArrayList<Button> E_buttons = new ArrayList<>();

	private static int manager_input_E_seat_no = 50;
	private static int[] E_status_check = new int[manager_input_E_seat_no];
	public static int E_count = 0;

	@FXML
	private Button btn_clear;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		right_block.setVisible(false);
		bottom_block.setVisible(false);

		E_seat_create();

	}

	public void E_seat_create() {

		int row = 0;
		int col = 0;

		for (int i = 0; i < manager_input_E_seat_no; i++) {

			Button button = new Button();
			button.setPrefSize(42, 42);
			button.setId(i + "");
			button.setText((i + 1) + "");

			int button_status_check = getE_status_check()[i];
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

				++E_count;

				int button_status_check2 = getE_status_check()[Integer.parseInt(button.getId())];
				switch (button_status_check2) {
				case 0: // 좌석이 예약 가능한 상태
					getE_status_check()[Integer.parseInt(button.getId())] = 1;
					button.setStyle("-fx-background-color: green");
					break;
				case 1:
					button.setStyle("-fx-background-color: green");
					break;

				case 2: // 예약이 불가능한 좌석 (이미 다른 사람이 예약했음)
					button.setStyle("-fx-background-color: red");
					button.setDisable(true);
					break;
				}

				if (E_count == seat_limit) {
					Reservation_Seat_Select_Controller.is_R_set = true;
					Reservation_Seat_Select_Controller.is_S_set = true;
					Reservation_Seat_Select_Controller.is_D_set = true;
					Reservation_Seat_Select_Controller.is_E_set = true;
					btn_select_done.setVisible(false);
					btn_disable();
				}
			});

			E_buttons.add(button);

			if (i % 10 == 0) {
				row = 0;
				col++;
				gridpane_E.add(button, row++, col);
			} else {
				gridpane_E.add(button, row++, col);
			}

		}

	}

	// 모든 버튼 상태를 초기화 시킨다.
	// 제대로 작동하는지 확인하려면 매니저가 등록한 DB 에서 좌석 상태를 불러와야한다.
	// 상태가 2, 즉 예약이 완료된 상태를 제외하고 1-> 현재 선택한 좌석이 있다면 0으로 바꾼다.
	// 1에서 2로 바꾸고 버튼을 다시 생성한다

	@FXML
	public void btn_clear(ActionEvent event) {

		E_count = 0;
		Reservation_Seat_Select_Controller.seat_total = Reservation_Seat_Select_Controller.how_many_person;

		for (int i = 0; i < manager_input_E_seat_no; i++) {
			if (getE_status_check()[i] == 1) {
				getE_status_check()[i] = 0;
			}
		}

		E_seat_create();

	}

	public void btn_disable() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("가능한 모든 좌석을 선택하셨습니다.");
		alert.showAndWait();
		Reservation_Seat_Select_Controller.seat_total = 0;

		for (Button button : E_buttons) {
			button.setDisable(true);
		}

	}

	public void btn_disable2() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("E석 좌석 선택이 완료되었습니다. ");
		alert.showAndWait();
		for (Button button : E_buttons) {
			button.setDisable(true);
		}

		Alert alert2 = new Alert(AlertType.INFORMATION);
		alert2.setHeaderText("총[" + E_count + "]개 의 좌석이 선택되었습니다.\n"
				+ (Reservation_Seat_Select_Controller.seat_total - E_count) + "개 좌석을 선택할 수 있습니다. ");
		alert2.showAndWait();

	}

	public static int[] getE_status_check() {
		return E_status_check;
	}

	public static void setE_status_check(int[] e_status_check) {
		E_status_check = e_status_check;
	}

}
