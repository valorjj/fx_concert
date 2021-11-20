package controller.customer;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.TreeMap;

import dao.ConcertDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class R_Seat_Controller implements Initializable {

//	ArrayList<Integer> R_seat_tmp = new ArrayList<>();
//	ArrayList<Boolean> R_seat_status_tmp = new ArrayList<>();

	// TreeMap 을 이용해 좌석 번호, 등급을 함께 저장합니다.

	// 각각의 좌석 컨트롤러에서는 임시값으로 받고, 모든 좌석이 선택되는 순간 좌석 선택 컨트롤러에 있는 값에

	TreeMap<Integer, String> R_seat_Map = new TreeMap<Integer, String>();

	@FXML
	private Button btn_select_done;

	@FXML
	void btn_select_done(ActionEvent event) {

		Reservation_Seat_Select_Controller.seat_total = Reservation_Seat_Select_Controller.seat_total - getR_count();
		Reservation_Seat_Select_Controller.is_R_set = true;

		Reservation_Seat_Select_Controller.getReseved_seat_map().put("R", R_seat_Map);

		btn_disable2();

	}

	//////////////////////////////////////////////////////////

	private static R_Seat_Controller instance;

	public static R_Seat_Controller get_instance() {

		return instance;
	}

	public R_Seat_Controller() {
		instance = this;
	}

	//////////////////////////////////////////////////////////

	// 배열의 갯수는 관리자가 입력한 값을 대입합니다 (매니저와 합칠 시)

	// 처음 컨트롤러가 실행되며 들어오는 현재 선택 가능한 인원 수 입니다.
	// how_many_person 은 유저가 선택한 선택 가능한 전체 인원수 (고정값)
	// 이므로 seat_limit 에는 다른 좌석에서 선택한다면, 줄어드는 가변적인 값이 들어와야합니다.
	// 처음엔 how_many_person 을 호출해서 오류가 발생했습니다.
	// 만일 선택 가능한 좌석이 0 이라면 오류 메시지를 처리하는 예외 처리를 해야합니다.
	int seat_limit = Reservation_Seat_Select_Controller.seat_total;

	// 매니저가 입력한 특정날짜, 특정 시간의 R 석 총 갯수를 불러옵니다.
	static int manager_input_R_seat_no = ConcertDao.getConcertDao().get_remaining_seat_R(
			Reservation_Date_Select_Controller.user_selected_date,
			Reservation_Date_Select_Controller.user_selected_time);
	// 버튼이 체크 되었는지 상태를 임시로 저장하는 배열입니다.
	//
	private static int[] R_status_check = new int[manager_input_R_seat_no];
	// R석 좌석 선택 수는 0으로 초기화시킵니다.
	private static int R_count = 0;

	// 생성될 버튼 객체를 저장할 리스트입니다.
	static ArrayList<Button> R_buttons = new ArrayList<Button>();

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

	//////////////////////////////////////////////////////////

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		left_block.setVisible(false);
		R_seat_create();

	}

	//////////////////////////////////////////////////////////

	public void R_seat_create() {

		// 만일 선택 가능한 좌석이 0 이라면 좌석 생성 코드라인 자체를 막을 것 입니다.

		if (seat_limit == 0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("모든 좌석이 선택되었습니다. 결제를 진행해주세요.");
			alert.showAndWait();
			Reservation_Seat_Select_Controller.get_instance().load_page("reservation_page_seat_select_home");

		}

		else {

			int row = 0;
			int col = 0;

			for (int i = 0; i < manager_input_R_seat_no; i++) {

				Button button = new Button();
				button.setPrefSize(42, 42);
				button.setId(i + "");
				button.setText((i + 1) + "");

				// 버튼 액션을 부여하기 전에, 특정 좌석이 클릭이 되었는지, 혹은 이미 예약 상태인지를 체크해서 상태별로 색깔을 다르게 출력합니다.

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

				// 1. 해당 버튼에 이벤트가 발생했을 때
				// 1. 버튼 : button
				// 2. 버튼 id : i, 출력되는 화면에는 (i+1) 로 버튼이 1번부터 시작하겠지만
				// 실제로 버튼의 id 값에는 i 값이 들어가서 배열 혹은 리스트에 0부터 저장될 것 입니다.

				button.setOnAction((ActionEvent) -> {

					setR_count(getR_count() + 1); // 버튼이 한번 눌렸다는 상태를 업데이트 합니다 (갯수에 +1)
					// R_count 는 clear 버튼을 누를 시에 0으로 바뀌어야 합니다.

					// TreeMap 을 사용해서 선택된 좌석의 번호, 그리고 등급이 저장되어 static 영역에 저장됩니다.
					// 위치 상 다음 페이지인 결제창에서 이 정보를 출력되게 끔 합니다.
					// 처음엔 무지성으로 tableview 를 사용했으나, 현재 고객이 선택한 날짜, 시간, 좌석 등은 DB 에 반영되기 전이므로
					// tableview 를 사용할 수 없습니다.
					// 모두 label 로 처리합니다. 어짜피 한 예약에 고객이 선택할 수 있는 좌석은 총 4좌석 이므로 테이블 형식으로 row 를 4개만 만들면
					// 됩니다.

					R_seat_Map.put(Integer.parseInt(button.getId()) + 1, "R");

					// 다시 한번 강조하지만, 버튼의 id 값은 0부터 시작입니다. 실제 표시되는 좌석, 즉 R석의 15번째 좌석을 클릭했다면 id 값은 14 로
					// 저장됩니다.
					// 배열 혹은 리스트가 0부터 시작하는 것과 통일하기 위함입니다. 나중에 보기 불편하면 수정바랍니다.

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

					// 선택한 좌석을 모두 채운 경우입니다.
					// 더 이상의 좌석 선택을 막습니다.

					if (getR_count() == seat_limit) {

						Reservation_Seat_Select_Controller.getReseved_seat_map().put("R", R_seat_Map);

						// 좌석을 모두 선택했다면, RSDE 좌석 선택 버튼을 모두 숨기려고
						// 해당 스위치 값을 모두 TRUE 로 바꿔줍니다.

						// 좌석의 id, 즉 좌석 번호를 리스트에 저장해서 넘겨줘야합니다.
						Reservation_Seat_Select_Controller.seat_total = 0; // 전체 선택 가능한 좌석을 0 으로 바꿉니다.
						Reservation_Seat_Select_Controller.is_R_set = true;
						Reservation_Seat_Select_Controller.is_S_set = true;
						Reservation_Seat_Select_Controller.is_D_set = true;
						Reservation_Seat_Select_Controller.is_E_set = true;

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

			}

		}

	}

	//////////////////////////////////////////////////////////

	@FXML
	public void btn_clear(ActionEvent event) {

		// 1. 카운트 된 좌석을 0 으로 다시 초기화 시킵니다.
		setR_count(0);
		// 2. 선택 가능한 좌석의 수를 맨 처음 들어온 값으로 초기화 시켜야합니다.
		Reservation_Seat_Select_Controller.seat_total = Reservation_Seat_Select_Controller.how_many_person;
		// 3. TreeMap 에 저장되어 있던 정보도 초기화시킵니다.
		R_seat_Map.clear();

		// 4. 버튼이 선택되었던 상태 정보도 모두 1에서 0으로 바꿉니다.
		for (int i = 0; i < manager_input_R_seat_no; i++) {
			if (getR_status_check()[i] == 1) {
				getR_status_check()[i] = 0;
			}
		}

		// 5. 위의 정보들을 바탕으로 다시 좌석을 생성합니다.

		R_seat_create();

	}

	//////////////////////////////////////////////////////////

	/*
	 * 해당 등급의 좌석 선택에서 선택한 인원수를 모두 채웠을 때 사용하는 메소드입니다.
	 * 
	 */

	public void btn_disable() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("가능한 모든 좌석을 선택하셨습니다.");
		alert.showAndWait();

		Reservation_Seat_Select_Controller.seat_total = 0;
		for (Button button : R_buttons) {
			button.setDisable(true);
		}

	}

	//////////////////////////////////////////////////////////

	/*
	 * btn_disable 과 호출되는 메소드와 내부에 알림 메시지 출력하는게 다르기 때문에 생성한 메소드입니다. 선택된 인원보다 더 적은
	 * 인원수를 선택하고 해당 등급의 좌석 선택을 끝낼 때 출력되는 메소드입니다.
	 */

	public void btn_disable2() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("R석 좌석 선택이 완료되었습니다. ");
		alert.showAndWait();
		for (Button button : R_buttons) {
			button.setDisable(true);
		}

		Alert alert2 = new Alert(AlertType.INFORMATION);
		alert2.setHeaderText("총 [" + getR_count() + "] 개 의 좌석이 선택되었습니다.\n"
				+ (Reservation_Seat_Select_Controller.seat_total - getR_count()) + "개 좌석을 선택할 수 있습니다. ");
		alert2.showAndWait();

	}

	//////////////////////////////////////////////////////////

	public static int getR_count() {
		return R_count;
	}

	public static void setR_count(int r_count) {
		R_count = r_count;
	}

	public static int[] getR_status_check() {
		return R_status_check;
	}

	public static void setR_status_check(int[] r_status_check) {
		R_status_check = r_status_check;
	}

}
