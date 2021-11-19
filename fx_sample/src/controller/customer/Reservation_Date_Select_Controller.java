package controller.customer;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.ConcertDao;
import domain.Concert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Reservation_Date_Select_Controller implements Initializable {
	/*
	 * reservation_page_concert_select.fxml 에서 선택한 콘서트의 날짜 정보를 불러와서 날짜, 시간을 선택하는
	 * 페이지입니다.
	 * 
	 */

	// DB 에는 등록하지는 않지만 유저가 선택한 정보를 static 영역에 저장합니다.
	static int user_selected_day = 0;
	static String user_selected_date;
	static int user_selected_time = 0;

	// 버튼 상태를 제어하는 스위치 2개
	private boolean switch_2pm_btn = true;
	private boolean switch_6pm_btn = true;

	boolean switch_date_1;
	boolean switch_date_2;
	boolean switch_date_3;

	int user_selected_concert_unique_no = Reservation_Concert_Select_Controller.concert_number;

	ArrayList<String> concert_date_list = new ArrayList<String>();
	ArrayList<Concert> concert_info = new ArrayList<Concert>();

	// Button[] Buttons = new Button[50];
	ArrayList<Button> Buttons = new ArrayList<>();

	boolean select_switch = false;

	String[] day = new String[3];

	public void btn_disable() {

		for (Button button : Buttons) {
			button.setDisable(true);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// 콘서트 관련 정보 출력합니다.

		concert_date_list = (ConcertDao.getConcertDao().get_concert_date_list(user_selected_concert_unique_no));

		lbl_concert_date_1.setText(concert_date_list.get(0));
		lbl_concert_date_2.setText(concert_date_list.get(1));
		lbl_concert_date_3.setText(concert_date_list.get(2));


		Concert concert = ConcertDao.getConcertDao().get_concert_instance(user_selected_concert_unique_no);

		lbl_concert_tile.setText(concert.getC_title());
		lbl_R_price.setText(concert.getC_R_price() + "");
		lbl_S_price.setText(concert.getC_S_price() + "");
		lbl_D_price.setText(concert.getC_D_price() + "");
		lbl_E_price.setText(concert.getC_E_price() + "");


		create_calendar();
		set_btn_action();

	}

	@SuppressWarnings("static-access")
	public void create_calendar() {

		concert_date_list = (ConcertDao.getConcertDao().get_concert_date_list(user_selected_concert_unique_no));
		// 날짜만 빼옵니다.
		for (int i = 0; i < 3; i++) {
			day[i] = concert_date_list.get(i).split("-")[2];
		}

		Calendar calendar = Calendar.getInstance();
		String concert_date = ConcertDao.getConcertDao().get_concert_date(user_selected_concert_unique_no);
		System.out.println("test3");
		String[] tmp = concert_date.split(" ");
		user_selected_date = tmp[0];
		String year = tmp[0].split("-")[0];
		String month = tmp[0].split("-")[1];
		System.out.println("test4");
		int YEAR = Integer.parseInt(year);
		int MONTH = Integer.parseInt(month);



		/*
		 * int year = Integer.parseInt(concert_year); int month =
		 * Integer.parseInt(concert_month);
		 */
		

		calendar.set(YEAR, MONTH - 1, 1);

		int sweek = calendar.get(Calendar.DAY_OF_WEEK); // 달의 시작하는 날짜

		int eday = calendar.getActualMaximum(calendar.DAY_OF_MONTH);

		lbl_concert_duration.setText("180분");

		// DB에서 연, 월 데이터를 받아서 캘린더를 출력합니다.

		int idx = 1;

		for (int i = 0; i <= 42; i++) {
			if (i >= sweek - 1 && i < eday + sweek - 1) { // 해당 월의 시작날짜 ~ 끝나는 날짜
				Button button = new Button();
				button.setText(idx + "");

				button.setPrefSize(30, 30);
				button.setId(idx + "");

				if (i < 7) {
					gridpane_calendar.add(button, i, 0);
				} else if (i >= 7 && i < 14) {
					gridpane_calendar.add(button, i % 7, 1);
				} else if (i >= 14 && i < 21) {
					gridpane_calendar.add(button, i % 7, 2);
				} else if (i >= 21 && i < 28) {
					gridpane_calendar.add(button, i % 7, 3);
				} else if (i >= 28 && i < 35) {
					gridpane_calendar.add(button, i % 7, 4);
				} else {
					gridpane_calendar.add(button, i % 7, 5);
				}

				Buttons.add(button);

				idx++;

			}
		}

	}

	public void set_btn_action() {

		for (Button button : Buttons) {

			if (button.getId().equals(day[0])) {

				button.setStyle("-fx-background-color : #cccccc");

				button.setOnAction(e -> {
					if (!switch_date_1) {
						// 선택 안했을 때
						button.setStyle("-fx-background-color : #cccccc");
						switch_date_1 = true;
					} else {
						button.setStyle("-fx-background-color : green");
						user_selected_day = Integer.parseInt(day[0]);
						switch_date_1 = false;
					}
				});

			}

			else if (button.getId().equals(day[1])) {

				button.setStyle("-fx-background-color : #cccccc");

				button.setOnAction(e -> {
					if (!switch_date_2) {
						// 선택 안했을 때
						button.setStyle("-fx-background-color : #cccccc");

						switch_date_2 = true;
					} else {
						button.setStyle("-fx-background-color : green");
						user_selected_day = Integer.parseInt(day[1]);
						switch_date_2 = false;
					}
				});
			}

			else if (button.getId().equals(day[2])) {

				button.setStyle("-fx-background-color : #cccccc");

				button.setOnAction(e -> {

					if (!switch_date_3) {
						// 선택 안했을 때
						button.setStyle("-fx-background-color : #cccccc");

						switch_date_3 = true;
					} else {
						button.setStyle("-fx-background-color : green");
						user_selected_day = Integer.parseInt(day[2]);
						switch_date_3 = false;
					}

				});

			}

			else {
				button.setStyle("-fx-background-color : #f57b42");
			}

		}

	}

	///////////////////////////////////////////////////////////////////

	@FXML
	private Button btn_cancel;

	@FXML
	private Button btn_reservation_confirm;

	@FXML
	private RadioButton opt_1;

	@FXML
	private RadioButton opt_2;

	@FXML
	private ToggleGroup time_select;

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
	private Label lbl_concert_duration;

	@FXML
	private Label lbl_concert_tile;

	@FXML
	private Label lbl_small;

	@FXML
	private Label lbl_cal;

	@FXML
	private Button btn_2pm;

	@FXML
	private Button btn_6pm;
	@FXML
	private Label lbl_concert_date_1;
	@FXML
	private Label lbl_concert_date_2;
	@FXML
	private Label lbl_concert_date_3;

	///////////////////////////////////////////////////////////////////

	// 뒤로 가기 버튼 --> 콘서트 선택 페이지로 이동한다.
	@FXML
	void btn_cancel(ActionEvent event) {
		Reservation_Home_Controller.getinstance().reservation_loadpage("reservation_page_concert_select");

	}
	///////////////////////////////////////////////////////////////////

	/* 날짜와 시간을 입력받은 뒤 좌석 선택하는 페이지로 넘어갑니다. */

	@FXML
	void btn_reservation_confirm(ActionEvent event) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("선택하신 날짜가 맞습니까? \n    [" + user_selected_day + "] 일 오후 [" + user_selected_time + "] 시");

		Optional<ButtonType> optional = alert.showAndWait();

		if (optional.get() == ButtonType.OK) {
			Alert alert2 = new Alert(AlertType.INFORMATION);

			if (user_selected_time != 0 && user_selected_day != 0) {
				alert2.setHeaderText("좌석 선택 페이지로 이동합니다.");
				alert2.showAndWait();
				Reservation_Home_Controller.getinstance().reservation_loadpage("reservation_page_seat_select");
			} else {
				alert2.setHeaderText("날짜, 시간이 모두 선택되지 않았습니다.");
				alert2.showAndWait();
			}

		}

	}

	///////////////////////////////////////////////////////////////////

	@FXML
	public void btn_2pm(ActionEvent event) {
		if (switch_2pm_btn) {
			/* 버튼 아래 위치한 레이블에 잔여 좌석을 알려줍니다. */
			user_selected_time = 2;

			int R_remain = ConcertDao.getConcertDao().get_remaining_seat_R(user_selected_date, user_selected_time);
			lbl_R_remaining.setText(R_remain + "");

			int S_remain = ConcertDao.getConcertDao().get_remaining_seat_S(user_selected_date, user_selected_time);
			lbl_S_remaining.setText(S_remain + "");

			int D_remain = ConcertDao.getConcertDao().get_remaining_seat_D(user_selected_date, user_selected_time);
			lbl_D_remaining.setText(D_remain + "");

			int E_remain = ConcertDao.getConcertDao().get_remaining_seat_E(user_selected_date, user_selected_time);
			lbl_E_remaining.setText(E_remain + "");

			btn_6pm.setDisable(true);
			setSwitch_2pm_btn(false);

		} else {
			user_selected_time = 0;
			lbl_R_remaining.setText("");
			lbl_S_remaining.setText("");
			lbl_D_remaining.setText("");
			lbl_E_remaining.setText("");
			btn_6pm.setDisable(false);
			setSwitch_2pm_btn(true);

		}
	}

	@FXML
	public void btn_6pm(ActionEvent event) {
		if (switch_6pm_btn) {

			user_selected_time = 6;

			int R_remain = ConcertDao.getConcertDao().get_remaining_seat_R(user_selected_date, user_selected_time);
			lbl_R_remaining.setText(R_remain + "");

			int S_remain = ConcertDao.getConcertDao().get_remaining_seat_S(user_selected_date, user_selected_time);
			lbl_S_remaining.setText(S_remain + "");

			int D_remain = ConcertDao.getConcertDao().get_remaining_seat_D(user_selected_date, user_selected_time);
			lbl_D_remaining.setText(D_remain + "");

			int E_remain = ConcertDao.getConcertDao().get_remaining_seat_E(user_selected_date, user_selected_time);
			lbl_E_remaining.setText(E_remain + "");

			btn_2pm.setDisable(true);

			setSwitch_6pm_btn(false);

		} else {
			user_selected_time = 0;
			lbl_R_remaining.setText("");
			lbl_S_remaining.setText("");
			lbl_D_remaining.setText("");
			lbl_E_remaining.setText("");
			btn_2pm.setDisable(false);
			setSwitch_6pm_btn(true);
		}
	}

	public boolean isSwitch_2pm_btn() {
		return switch_2pm_btn;
	}

	public void setSwitch_2pm_btn(boolean switch_2pm_btn) {
		this.switch_2pm_btn = switch_2pm_btn;
	}

	public boolean isSwitch_6pm_btn() {
		return switch_6pm_btn;
	}

	public void setSwitch_6pm_btn(boolean switch_6pm_btn) {
		this.switch_6pm_btn = switch_6pm_btn;
	}

	public static void setUser_time_select(int user_time_select) {
		user_selected_time = user_time_select;
	}

}
