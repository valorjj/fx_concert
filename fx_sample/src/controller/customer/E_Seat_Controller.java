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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class E_Seat_Controller implements Initializable {

	@FXML
	private GridPane gridpane_E;
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
	@FXML
	private Button btn_select_done;
	private static E_Seat_Controller instance;

	ArrayList<Button> E_buttons = new ArrayList<Button>();

	static int c_no = ConcertDao.getConcertDao().get_concert_c_no(Reservation_Date_Select_Controller.user_selected_date,
			Reservation_Date_Select_Controller.user_selected_time);

	TreeMap<Integer, String> E_seat_Map = new TreeMap<Integer, String>();
	int seat_limit = Reservation_Seat_Select_Controller.seat_total;
	int manager_input_R_seat_no = ConcertDao.getConcertDao().get_remaining_seat_E(
			Reservation_Date_Select_Controller.user_selected_date,
			Reservation_Date_Select_Controller.user_selected_time);
	ArrayList<Integer> E_status_check = SeatDao.getSeatDao().get_seat_status(c_no, "E",
			Reservation_Concert_Select_Controller.concert_number);
	int E_count = 0;

	int button_status_check = 99999;
	int button_status_check2 = 99999;

	@FXML
	void btn_select_done(ActionEvent event) {

		Reservation_Seat_Select_Controller.seat_total = Reservation_Seat_Select_Controller.seat_total - E_count;
		Reservation_Seat_Select_Controller.is_E_set = true;
		Reservation_Seat_Select_Controller.getReseved_seat_map().put("E", E_seat_Map);
		btn_disable2();
		Reservation_Seat_Select_Controller.setE_status_check(this.E_status_check);
		Reservation_Seat_Select_Controller.E_count = this.E_count;

	}

	public static E_Seat_Controller get_instance() {

		return instance;
	}

	public E_Seat_Controller() {
		instance = this;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		left_block.setVisible(false);
		E_seat_create();

	}

	public void E_seat_create() {
		if (seat_limit == 0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("모든 좌석이 선택되었습니다. 결제를 진행해주세요.");
			alert.showAndWait();
			Reservation_Seat_Select_Controller.get_instance().load_page("reservation_page_seat_select_home");
		} else {

			int row = 0;
			int col = 0;

			for (int i = 0; i < manager_input_R_seat_no; i++) {

				Button button = new Button();
				button.setPrefSize(42, 42);

				// 1. 화면에 표시되는 버튼 번호와 버튼 id 가 다릅니다.
				// 1. 15번 좌석은 id=14 로 저장됩니다.
				// 2. 리스트 혹은 배열에서 사용할 시 인덱스가 0번 부터 시작하기 때문에 실수 방지를 위함입니다.
				button.setId(i + "");
				button.setText((i + 1) + "");

				// 2. 버튼 액션을 부여하기 전에, 특정 좌석이 클릭이 되었는지, 혹은 이미 예약 상태인지를 체크해서 상태별로 색깔을 다르게 출력합니다.

				button_status_check = E_status_check.get(i);
				switch (button_status_check) {
				case 0:
					break;
				case 1:
					button.setStyle("-fx-background-color: green");
					break;

				case 2:
					button.setStyle("-fx-background-color: red");
					button.setDisable(true);
					break;
				}

				// 3. 해당 버튼에 이벤트가 발생
				button.setOnAction((ActionEvent) -> {
					// 1. R석이 선택된 갯수를 증가시킵니다.
					// 2. 선택된 좌석 번호와 등급을 TreeMap 에 저장시킵니다.
					E_seat_Map.put(Integer.parseInt(button.getId()) + 1, "E");
					// 3. 버튼이 클릭되었을 때, 상태를 바꿉니다.
					button_status_check2 = E_status_check.get(Integer.parseInt(button.getId()));

					switch (button_status_check2) {
					case 0: // 1. 좌석이 예약 가능한 상태
						E_count = E_count + 1;
						E_status_check.set((Integer.parseInt(button.getId())), 1);
						button.setStyle("-fx-background-color: green");
						break;
					case 1: // 2. 현재 선택한 좌석
						E_count = E_count - 1;
						button.setStyle("-fx-background-color: green");
						break;

					case 2: // 3. 예약이 불가능한 좌석 (이미 다른 사람이 예약했음)
						button.setStyle("-fx-background-color: red");
						button.setDisable(true);
						break;

					}

					// 4. 선택한 좌석을 모두 채운 경우입니다.
					// 1. 더 이상의 좌석 선택을 막습니다.

					if (E_count == seat_limit) {
						// 1. 모든 좌석이 선택되었으므로 맵에 정보를 저장합니다.
						Reservation_Seat_Select_Controller.getReseved_seat_map().put("E", E_seat_Map);
						// 2. 선택가능한 좌석을 0 으로 바꿉니다.
						// 1. 초기화를 누른다면 seat_total 값을 다시 how_many_person 으로 바꿔야합니다.
						Reservation_Seat_Select_Controller.seat_total = 0;
						// 3. 등급별 좌석 선택 버튼을 가리기 위해 상태를 바꿉니다.
						Reservation_Seat_Select_Controller.is_R_set = true;
						Reservation_Seat_Select_Controller.is_S_set = true;
						Reservation_Seat_Select_Controller.is_D_set = true;
						Reservation_Seat_Select_Controller.is_E_set = true;
						Reservation_Seat_Select_Controller.setE_status_check(this.E_status_check);
						Reservation_Seat_Select_Controller.E_count = this.E_count;

						top_block.setVisible(false);
						right_block.setVisible(false);
						bottom_block.setVisible(false);
						btn_clear.setVisible(false);
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
	}

	@FXML
	public void btn_clear(ActionEvent event) {

		E_count = 0;
		E_seat_Map.clear();
		E_buttons.clear();

		seat_limit = Reservation_Seat_Select_Controller.seat_total;

		for (int e : E_status_check) {
			if (e == 1) {
				e = 0;
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
		alert2.setHeaderText("총 [" + E_count + "] 개 의 좌석이 선택되었습니다.\n" + Reservation_Seat_Select_Controller.seat_total
				+ "개 좌석을 선택할 수 있습니다. ");
		alert2.showAndWait();

	}

}
