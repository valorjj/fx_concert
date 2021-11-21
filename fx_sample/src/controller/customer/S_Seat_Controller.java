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

public class S_Seat_Controller implements Initializable {

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
	@FXML
	private GridPane gridpane_S;
	@FXML
	private Button btn_clear;

	private static S_Seat_Controller instance;

	ArrayList<Button> S_buttons = new ArrayList<>();

	int c_no = ConcertDao.getConcertDao().get_concert_c_no(Reservation_Date_Select_Controller.user_selected_date,
			Reservation_Date_Select_Controller.user_selected_time);

	TreeMap<Integer, String> S_seat_Map = new TreeMap<Integer, String>();

	int seat_limit = Reservation_Seat_Select_Controller.seat_total;

	// 매니저가 입력한 특정날짜, 특정 시간의 R 석 총 갯수를 불러옵니다.
	int manager_input_S_seat_no = ConcertDao.getConcertDao().get_remaining_seat_S(
			Reservation_Date_Select_Controller.user_selected_date,
			Reservation_Date_Select_Controller.user_selected_time);
	ArrayList<Integer> S_status_check = SeatDao.getSeatDao().get_seat_status(c_no, "S",
			Reservation_Concert_Select_Controller.concert_number);
	int S_count = 0;
	int button_status_check = 99999;
	int button_status_check2 = 99999;

	/////////////////////////////////////////////////////////

	@FXML
	void btn_select_done(ActionEvent event) {

		top_block.setVisible(false);
		right_block.setVisible(false);
		bottom_block.setVisible(false);

		// 전체 갯수인 seat_total 에서 좌석 갯수를 뺍니다.
		Reservation_Seat_Select_Controller.seat_total = Reservation_Seat_Select_Controller.seat_total - S_count;
		Reservation_Seat_Select_Controller.is_S_set = true;
		Reservation_Seat_Select_Controller.getReseved_seat_map().put("S", S_seat_Map);
		btn_disable2();
		Reservation_Seat_Select_Controller.S_status_check = this.S_status_check;

	}

	/////////////////////////////////////////////////////////

	public static S_Seat_Controller get_instance() {
		return instance;
	}

	public S_Seat_Controller() {
		instance = this;
	}

	/////////////////////////////////////////////////////

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println(c_no + ": c_no");
		right_block.setVisible(false);
		S_seat_create();
	}

	public void S_seat_create() {
		try {
			if (seat_limit == 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("모든 좌석이 선택되었습니다. 결제를 진행해주세요.");
				alert.showAndWait();
				Reservation_Seat_Select_Controller.get_instance().load_page("reservation_page_seat_select_home");
			} else {

				int row = 0;
				int col = 0;

				for (int i = 0; i < manager_input_S_seat_no; i++) {

					Button button = new Button();
					button.setPrefSize(42, 42);
					button.setId(i + "");
					button.setText((i + 1) + "");

					if (S_status_check != null && S_status_check.size() != 0) {

						button_status_check = S_status_check.get(i);

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

							S_seat_Map.put(Integer.parseInt(button.getId()) + 1, "S");

							button_status_check2 = S_status_check.get(Integer.parseInt(button.getId()));
							switch (button_status_check2) {
							case 0: // 좌석이 예약 가능한 상태
								++S_count;
								S_status_check.set(Integer.parseInt(button.getId()), 1);
								button.setStyle("-fx-background-color: green");
								break;
							case 1: // 현재 선택한 좌석
								--S_count;
								button.setStyle("-fx-background-color: #eeeeee");
								break;

							case 2: // 예약이 불가능한 좌석 (이미 다른 사람이 예약했음)
								button.setStyle("-fx-background-color: red");
								button.setDisable(true);
								break;

							}
							if (S_count == seat_limit) {

								Reservation_Seat_Select_Controller.getReseved_seat_map().put("S", S_seat_Map);
								Reservation_Seat_Select_Controller.seat_total = 0;
								Reservation_Seat_Select_Controller.is_R_set = true;
								Reservation_Seat_Select_Controller.is_S_set = true;
								Reservation_Seat_Select_Controller.is_D_set = true;
								Reservation_Seat_Select_Controller.is_E_set = true;
								top_block.setVisible(false);
								right_block.setVisible(false);
								bottom_block.setVisible(false);
								btn_clear.setVisible(false);
								btn_select_done.setVisible(false);
								btn_disable();
								Reservation_Seat_Select_Controller.S_status_check = this.S_status_check;
							}
						});

						S_buttons.add(button);

						if (i % 10 == 0) {
							row = 0;
							col++;
							gridpane_S.add(button, row++, col);
						} else {
							gridpane_S.add(button, row++, col);
						}
					} else {
						System.out.println("정보 불러오기 실패");
					}
				}

			}

		} catch (Exception e) {

		}

	}

	@FXML
	public void btn_clear(ActionEvent event) {

		S_count = 0;

		seat_limit = Reservation_Seat_Select_Controller.how_many_person;
		S_seat_Map.clear();

		for (int s : S_status_check) {
			if (s == 1) {
				s = 0;
			}
		}

		S_seat_create();

	}

	public void btn_disable() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("가능한 모든 좌석을 선택하셨습니다.");
		alert.showAndWait();
		Reservation_Seat_Select_Controller.seat_total = 0;
		for (Button button : S_buttons) {
			button.setDisable(true);
		}

	}

	////////////////////////////////////////////////////////

	public void btn_disable2() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("S석 좌석 선택이 완료되었습니다. ");
		alert.showAndWait();
		for (Button button : S_buttons) {
			button.setDisable(true);
		}

		Alert alert2 = new Alert(AlertType.INFORMATION);
		alert2.setHeaderText("총[" + S_count + "]개 의 좌석이 선택되었습니다.\n" + Reservation_Seat_Select_Controller.seat_total
				+ "개 좌석을 선택할 수 있습니다. ");
		alert2.showAndWait();

	}

}
