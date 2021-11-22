package controller.customer;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.TreeMap;

import dao.ConcertDao;
import dao.SeatDao;
import domain.Seat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class R_Seat_Controller implements Initializable {

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
	@FXML
	private Button btn_select_done;

	private static R_Seat_Controller instance;

	// 1. 생성되는 버튼이 저장될 리스트입니다.
	// 1.1 초기화 버튼을 누른면, 해당 리스트로 초기화 되어야합니다.
	ArrayList<Button> R_buttons = new ArrayList<Button>();

	// 1. 앞에서 선택한 날짜, 시간을 기준으로 콘서트 번호를 저장합니다.
	int c_no = ConcertDao.getConcertDao().get_concert_c_no(Reservation_Date_Select_Controller.user_selected_date,
			Reservation_Date_Select_Controller.user_selected_time);
	// 1.2 R 등급에서 선택한 좌석 정보가 저장될 트리맵입니다.
	// 1.3 트리맵에는 R등급에서 좌석 선택이 완료되었을 때 데이터가 들어갑니다.
	// 1.4 선택한 좌석인원수가 모두 차서 선택이 종료되는 경우 + 다른 등급의 좌석을 선택하려고 완료 버튼을 누르는 경우 2가지 경우가
	// 있습니다.
	TreeMap<Integer, String> R_seat_Map = new TreeMap<Integer, String>();
	// 1. 좌석 컨트롤러에서 받아온 현재 선택 가능한 좌석 수 입니다.
	// 1.1 좌석 컨트롤러에서 입력받은 인원수는 변하지 않는 값 입ㄴ디ㅏ.
	// 1.2 seat_total 값은 다른 등급(R, S, D, E) 에서 좌석을 선택하면 그 수가 줄어듭니다.
	// 1.3 seat_total 을 저장한 seat_limit 변수는 현재 클래스 내부에서만 사용합니다.
	// 1.4 좌석 컨트롤러에 존재하는 seat_total 변수에는 특정한 조건에만 값이 변하게 해야합니다.
	int seat_limit = Reservation_Seat_Select_Controller.seat_total;

	// 1. db concert table 에서 입력된 콘서트의 R 석 총 좌석 수를 저장합니다.
	int manager_input_R_seat_no = ConcertDao.getConcertDao().get_remaining_seat_R(
			Reservation_Date_Select_Controller.user_selected_date,
			Reservation_Date_Select_Controller.user_selected_time);
	// 1. db seat table 에서 s_staus 를 받아서 저장하는 리스트입니다.
	ArrayList<Integer> R_status_check = SeatDao.getSeatDao().get_seat_status(c_no, "R",
			Reservation_Concert_Select_Controller.concert_number);
	// 1. R석에서 선택한 좌석수를 임시로 저장하는 변수입니다.
	// 1.1 좌석에 이벤트가 발생할 때마다 증가하거나 감소시킵니다.
	// 1.2 좌석 컨트롤러에서 받아온 seat_total 값을 기준으로 좌석 선택이 종료되는 시점을 조건으로 만들 때 사용합니다.
	int R_count = 0;

	// 1. 버튼의 예약 상태를 초기화 시킵니다. 99999 는 아무런 의미가 없는 숫자입니다.
	int button_status_check = 99999;
	int button_status_check2 = 99999;

	// 1. R등급에서의 좌석 선택을 마치고 싶을 때 선택하는 버튼이 작동하는 메소드입니다.
	@FXML
	void btn_select_done(ActionEvent event) {

		top_block.setVisible(false);
		right_block.setVisible(false);
		bottom_block.setVisible(false);
		System.out.println("전" + Reservation_Seat_Select_Controller.seat_total);
		// 1.1 선택가능한 좌석에서 선택한 좌석의 갯수를 빼줍니다.
		Reservation_Seat_Select_Controller.seat_total = Reservation_Seat_Select_Controller.seat_total - R_count;
		System.out.println("후" + Reservation_Seat_Select_Controller.seat_total);

		// 1.2 R등급의 좌석 선택이 종료되었음을 알려주는 값입니다.
		Reservation_Seat_Select_Controller.is_R_set = true;
		// 1.3 트리맵에 현재까지 선택한 좌석 정보를 저장합니다.
		Reservation_Seat_Select_Controller.getReseved_seat_map().put("R", R_seat_Map);

		// 1.4 좌석 선택이 종료시킵니다.
		// 1.5 모든 좌석을 disable 시키고, 알림창을 출력시킵니다.
		btn_disable2();

		// 1.6 좌석 컨트롤러에 있는 static 리스트에 저장합니다.
		Reservation_Seat_Select_Controller.R_status_check = this.R_status_check;
		Reservation_Seat_Select_Controller.R_count = this.R_count;

	}

	public static R_Seat_Controller get_instance() {
		return instance;
	}

	public R_Seat_Controller() {
		instance = this;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		left_block.setVisible(false);
		R_seat_create();
	}

	// 1. 좌석을 생성하는 메소드입니다.
	// 1.1
	public void R_seat_create() {
		try {
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
					// 1.1 15번 좌석은 id=14 로 저장됩니다.
					// 1.2 리스트 혹은 배열에서 사용할 시 인덱스가 0번 부터 시작하기 때문에 실수 방지를 위함입니다.
					button.setId(i + "");
					button.setText((i + 1) + "");

					// 2. 버튼 액션을 부여하기 전에, 특정 좌석이 클릭이 되었는지, 혹은 이미 예약 상태인지를 체크해서 상태별로 색깔을 다르게 출력합니다.
					// 2.1 혹시 배열에 문제가 발생한 경우 예외처리입니다.
					if (R_status_check != null && R_status_check.size() != 0) {
						button_status_check = R_status_check.get(i);

						switch (button_status_check) {
						case 0:
							button.setStyle("-fx-background-color : #eeeeee");
							break;
						case 1:
							button.setStyle("-fx-background-color: green");
							break;
						case 2:
							button.setDisable(true);
							button.setStyle("-fx-background-color: red");
							break;
						}
						// 3. 해당 버튼에 이벤트가 발생
						button.setOnAction((ActionEvent) -> {
							// 1. 선택된 좌석 번호와 등급을 TreeMap 에 저장시킵니다.
							R_seat_Map.put(Integer.parseInt(button.getId()) + 1, "R");
							// 2. 버튼이 클릭되었을 때, 상태를 바꿉니다.
							button_status_check2 = R_status_check.get(Integer.parseInt(button.getId()));

							switch (button_status_check2) {
							case 0: // 1. 좌석이 예약 가능한 상태
								R_count = R_count + 1; // 1. 현재 선택한 좌석 갯수를 1 증가시킵니다.
								R_status_check.set(Integer.parseInt(button.getId()), 1); // 2. 체크 되었다고 1 로 상태를 변경합니다.
								button.setStyle("-fx-background-color: green");
								break;
							case 1: // 2. 현재 선택한 좌석
								--R_count; // 1. 동일한 좌석을 누른다는 것은 취소의 의미이므로 1 감소시킵니다.
								R_status_check.set(Integer.parseInt(button.getId()), 0);
								button.setStyle("-fx-background-color: #eeeeee");
								break;
							case 2: // 3. 예약이 불가능한 좌석 (이미 다른 사람이 예약했음)
								button.setDisable(true);
								button.setStyle("-fx-background-color: red");
								break;
							}

							// 4. 선택한 좌석을 모두 채운 경우입니다.
							// 1. 더 이상의 좌석 선택을 막습니다.

							if (R_count == seat_limit) {
								// 1. 모든 좌석이 선택되었으므로 맵에 정보를 저장합니다.
								Reservation_Seat_Select_Controller.getReseved_seat_map().put("R", R_seat_Map);
								// 2. 선택가능한 좌석을 0 으로 바꿉니다.
								// 1. 초기화를 누른다면 seat_total 값을 다시 how_many_person 으로 바꿔야합니다.
								Reservation_Seat_Select_Controller.seat_total = 0;
								// 3. 등급별 좌석 선택 버튼을 가리기 위해 상태를 바꿉니다.
								Reservation_Seat_Select_Controller.is_R_set = true;
								Reservation_Seat_Select_Controller.is_S_set = true;
								Reservation_Seat_Select_Controller.is_D_set = true;
								Reservation_Seat_Select_Controller.is_E_set = true;
								// 4. 좌석 컨트롤러에 있는 static 영역에 저장된 리스트에 좌석 예약 상태 정보를 전달합니다.
								Reservation_Seat_Select_Controller.R_status_check = this.R_status_check;
								Reservation_Seat_Select_Controller.R_count = this.R_count;

								top_block.setVisible(false);
								right_block.setVisible(false);
								bottom_block.setVisible(false);
								btn_clear.setVisible(false);
								btn_select_done.setVisible(false);
								btn_disable();
							}
						});

						R_buttons.add(button);

						if (i % 10 == 0) {
							row = 0;
							col++;
							gridpane_R.add(button, row++, col);
						} else {
							gridpane_R.add(button, row++, col);
						}
					} else {
						System.out.println("좌석 상태 리스트를 불러오지 못 했습니다. ");
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	@FXML
	public void btn_clear(ActionEvent event) {

		// 1. 카운트 된 좌석을 0 으로 다시 초기화 시킵니다.
		R_count = 0;
		// 2. 선택 가능한 좌석의 수를 맨 처음 들어온 값으로 초기화 시켜야합니다.
		seat_limit = Reservation_Seat_Select_Controller.seat_total;
		// 3. TreeMap 에 저장되어 있던 정보도 초기화시킵니다.
		R_seat_Map.clear();
		// 4. buttons 리스트에 저장되어 있던 버튼 정보도 모두 초기화시킵니다.
		R_buttons.clear();
		// 5. 주변 블록들도 안보이게 합니다.
		top_block.setVisible(true);
		right_block.setVisible(true);
		bottom_block.setVisible(true);

		// 5. 버튼이 선택되었던 상태 정보도 1에서 0으로 바꿉니다.
		for (int r : R_status_check) {
			if (r == 1) {
				r = 0;
			}
		}
		// 6. 위의 정보들을 바탕으로 다시 좌석을 생성합니다.
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

		alert2.setHeaderText("총 [" + R_count + "] 개 의 좌석이 선택되었습니다.\n" + Reservation_Seat_Select_Controller.seat_total
				+ "개 좌석을 선택할 수 있습니다. ");
		alert2.showAndWait();

	}

}
