package controller.customer;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class R_Seat_Controller implements Initializable {
	
	ArrayList<Integer> R_seat_tmp = new ArrayList<>();

	@FXML
	private Button btn_select_done;

	@FXML
	void btn_select_done(ActionEvent event) {

		//

		Reservation_Seat_Select_Controller.seat_total = Reservation_Seat_Select_Controller.seat_total - R_count;

		// R 좌석 선택이 끝났다는 정보를 넘겨줍니다.
		Reservation_Seat_Select_Controller.is_R_set = true;

		btn_disable2();

	}

	///////////////////////////////////////////////////////

	private static R_Seat_Controller instance;

	public static R_Seat_Controller get_instance() {

		return instance;
	}

	public R_Seat_Controller() {
		instance = this;
	}

	// 배열의 갯수는 관리자가 입력한 값을 대입합니다 (매니저와 합칠 시)

	int seat_limit = Reservation_Seat_Select_Controller.how_many_person;

	static int manager_input_R_seat_no = 50;
	private static int[] R_status_check = new int[manager_input_R_seat_no];
	static int R_count = 0;

	static ArrayList<Button> R_buttons = new ArrayList<>();

	@FXML
	private GridPane gridpane_R;

	@FXML
	private Button btn_clear;

	@FXML
	private Rectangle bottom_block;

	@FXML
	private Rectangle left_block;

	@FXML
	private Rectangle right_block;

	@FXML
	private Rectangle top_block;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		left_block.setVisible(false);
		R_seat_create();

	}

	public void R_seat_create() {

		int row = 0;
		int col = 0;

		for (int i = 0; i < manager_input_R_seat_no; i++) {

			Button button = new Button();
			button.setPrefSize(42, 42);
			button.setId(i + "");
			button.setText((i + 1) + "");

			int button_status_check = getR_status_check()[i];
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

				++R_count;
				int button_status_check2 = getR_status_check()[Integer.parseInt(button.getId())];
				switch (button_status_check2) {
				case 0: // 좌석이 예약 가능한 상태
					getR_status_check()[Integer.parseInt(button.getId())] = 1;
					button.setStyle("-fx-background-color: green");
					break;
				case 1: // 현재 선택한 좌석
					button.setStyle("-fx-background-color: green");
					break;

				case 2: // 예약이 불가능한 좌석 (이미 다른 사람이 예약했음)
					button.setStyle("-fx-background-color: red");
					button.setDisable(true);
					break;

				}
				if (R_count == seat_limit) {

					// 좌석을 모두 선택했다면, RSDE 좌석 선택 버튼을 모두 숨기려고
					// 해당 스위치 값을 모두 TRUE 로 바꿔줍니다.

					// 좌석의 id, 즉 좌석 번호를 리스트에 저장해서 넘겨줘야합니다.

					Reservation_Seat_Select_Controller.is_R_set = true;
					Reservation_Seat_Select_Controller.is_S_set = true;
					Reservation_Seat_Select_Controller.is_D_set = true;
					Reservation_Seat_Select_Controller.is_E_set = true;
					btn_select_done.setVisible(false);
					btn_disable();
				}

			});

			// 생성한 버튼을 버튼 리스트에 담아서 필요시 다른 메소드에서 제어해 할 생각입니다.
			R_buttons.add(button);

			if (i % 10 == 0) {
				row = 0;
				col++;
				gridpane_R.add(button, row++, col);
			} else {
				gridpane_R.add(button, row++, col);
			}

		}

	}

	// 모든 버튼 상태를 초기화 시킨다.
	// 제대로 작동하는지 확인하려면 매니저가 등록한 DB 에서 좌석 상태를 불러와야한다.
	// 상태가 2, 즉 예약이 완료된 상태를 제외하고 1-> 현재 선택한 좌석이 있다면 0으로 바꾼다.
	// 1에서 2로 바꾸고 버튼을 다시 생성한다

	@FXML
	public void btn_clear(ActionEvent event) {

		R_count = 0;

		Reservation_Seat_Select_Controller.seat_total = Reservation_Seat_Select_Controller.how_many_person;

		// 1로 선택하면 초록색으로 바뀌니까, 상태를 0 으로 바꾼다음에 다시 생성합니다.
		for (int i = 0; i < manager_input_R_seat_no; i++) {
			if (getR_status_check()[i] == 1) {
				getR_status_check()[i] = 0;
			}
		}

		R_seat_create();

	}

	public void btn_disable() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("가능한 모든 좌석을 선택하셨습니다.");
		alert.showAndWait();

		Reservation_Seat_Select_Controller.seat_total = 0;
		for (Button button : R_buttons) {
			button.setDisable(true);
		}

	}

	public void btn_disable2() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("R석 좌석 선택이 완료되었습니다. ");
		alert.showAndWait();
		for (Button button : R_buttons) {
			button.setDisable(true);
		}

		Alert alert2 = new Alert(AlertType.INFORMATION);
		alert2.setHeaderText("총[" + R_count + "]개 의 좌석이 선택되었습니다.\n"
				+ (Reservation_Seat_Select_Controller.seat_total - R_count) + "개 좌석을 선택할 수 있습니다. ");
		alert2.showAndWait();

	}

	public static int[] getR_status_check() {
		return R_status_check;
	}

	public static void setR_status_check(int[] r_status_check) {
		R_status_check = r_status_check;
	}

}
