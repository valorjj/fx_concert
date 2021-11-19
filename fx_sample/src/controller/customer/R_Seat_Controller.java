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

	////////////////////////////////////////////////////////

	@FXML
	private Button btn_select_done;

	@FXML
	void btn_select_done(ActionEvent event) {

		// 더 깔끔한 방법이 있다면 누가 알려주삼..

		// 좌석 선택을 픽스 시킨다.
		// static 을 통해서 모든 클래스가 공유
		sum = sum + R_count;

		Reservation_Seat_Select_Controller.seat_total = Reservation_Seat_Select_Controller.seat_total - sum;
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

	int seat_limit = Reservation_Seat_Select_Controller.how_many_person; // 몇 사람 선택했는지를 기준점으로 삼고, 그 수보다 많이 선택할 수
																			// 없게 제한해야합니다.

	// R, S, D, E 석 유저가 선택한 총 좌석의 합입니다.
	// static 영역에 저장해서 최종 결제할 때 까지 메모리에 할당해야 기준점으로 사용할 수 있습니다.
	int sum = 0;

	// 100개 입력하면 오류가 납니다. //

	private static int manager_input_R_seat_no = 50;
	private static int[] R_status_check = new int[manager_input_R_seat_no]; // R 좌석의 예약된 상태 또한 static 영역에 저장시켜서 다른 클래스에서
	static int R_count = 0;
	// 끌어다가 쓸 수 있게 합니다.

	static ArrayList<Button> R_buttons = new ArrayList<>();
	/*
	 * DB와 연동해서 [배열] 형태로 받던지, ArrayList 형태로 받아서 저장하고 그 후에 소비자가 선택하면 임시로 저장했다가 결제를
	 * 누르는 순간 DB에 실제로 반영되게 끔 해야합니다.
	 */

	/* 상태가 0이면 예약 가능, 상태가 1이면 현재 선택, 상태가 2이면 이미 예약 완료되어 선택 불가능 */
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

	// 좌석을 생성하는 메소드입니다. 관리자가 입력한 수만큼 생성이 됩니다. 100개까지 해봤으나 알수 없는 오류가 발생하여
	// 50개로 줄였으니 그 이하로 입력하시길 바랍니다.
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
					btn_disable();
				}

			});

			// 생성한 버튼을 버튼 리스트에 담아서 필요시 다른 메소드에서 제어해 할 생각입니다.
			R_buttons.add(button);

			sum = sum + R_count;

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

		sum = 0;

		Reservation_Seat_Select_Controller.how_many_person = 0;

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
		alert2.setHeaderText("총[" + sum + "]개 의 좌석이 선택되었습니다.\n" + (Reservation_Seat_Select_Controller.seat_total - sum)
				+ "개 좌석을 선택할 수 있습니다. ");
		alert2.showAndWait();

	}

	public static int[] getR_status_check() {
		return R_status_check;
	}

	public static void setR_status_check(int[] r_status_check) {
		R_status_check = r_status_check;
	}

}
