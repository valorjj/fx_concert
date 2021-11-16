package controller.customer;

import java.net.URL;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.ConcertDao;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class Main_Reservation_Controller implements Initializable {

	/*
	 * 해야할 일
	 * 
	 * 여기서 날짜와 시간을 선택하면, c_no 을 특정지을 수 있다! 날짜와 시간을 선택한 뒤에는 db 에 c_no 를 채울 수 있다.
	 * 
	 */

	private String user_selected_day;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		int a = Reservation_Concert_Select_Controller.get_instance().concert_number;

		Calendar calendar = Calendar.getInstance();

		// DB 에서 콘서트 날짜를 가져오는 메소드가 필요하다 (콘서트 연도, 월만 리턴하는 메소드)

		// calendar.set(year, month - 1, 1);

		// 앞전 concert_select 화면에서 radiobutton 에서 선택한 값이 넘어와야한다.

		// 회원 고유 번호를 통해서 예약 번호를 찾는다. 거기서 콘서트 번호로 다시 셀렉트?
		// int m_no =
		// MemberDao.get_memberDao().get_m_no_member(Login_Controller.getinstance().get_login_id());
		// int c_no = ReservationDao.get_reservationDao().reservation_c_no_check(m_no);
		String concert_date = ConcertDao.get_concertDao().get_concert_date(1);
		String[] tmp = concert_date.split(" ");
		String year = tmp[0].split("-")[0];
		int YEAR = Integer.parseInt(year);
		String month = tmp[0].split("-")[1];
		int MONTH = Integer.parseInt(month);
		/*
		 * int year = Integer.parseInt(concert_year); int month =
		 * Integer.parseInt(concert_month);
		 */

		calendar.set(YEAR, MONTH - 1, 1);

		int sweek = calendar.get(Calendar.DAY_OF_WEEK); // 달의 시작하는 날짜

		int eday = calendar.getActualMaximum(calendar.DAY_OF_MONTH);

		lbl_concert_tile.setText(""); // DB 에서 공연 타이틀 가져오기
		lbl_concert_date.setText(""); // DB 에서 공연 날짜 (공연이 지속되는 날짜) 가져오기
		lbl_concert_duration.setText("180분");

		int idx = 1;
		for (int i = 0; i <= 42; i++) {
			if (i >= sweek - 1 && i < eday + sweek - 1) { // 특정 달의 시작 날짜를 구한다.

				Button button = new Button();
				button.setText(idx + "");

				button.setPrefSize(30, 30);
				button.setId(idx + "");

				Alert alert = new Alert(AlertType.CONFIRMATION);
				button.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent e) {
						alert.setHeaderText("날짜선택");
						Optional<ButtonType> optional = alert.showAndWait();
						if (optional.get() == ButtonType.OK) {
							// Button Id 가 날짜 값이랑 동일하게 셋팅되어 있으므로 날짜 값을 저장한다.
							user_selected_day = button.getId();
							Alert alert2 = new Alert(AlertType.INFORMATION);
							alert2.setHeaderText("시간을 선택해주세요");
							alert2.showAndWait();
						}

					}

				});

				// button.setStyle("-fx-text-fill: #ffffff");
				// button.setStyle("-fx-border-color : #333333");
				// button.setStyle("-fx-background-color : #000000");
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

				idx++;

			} else {

			}

		}

	}

	private int user_select_time;

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
	private Label lbl_concert_date;

	@FXML
	private Label lbl_concert_duration;

	@FXML
	private Label lbl_concert_tile;

	@FXML
	private Label lbl_small;

	@FXML
	private Label lbl_cal;

	// 뒤로 가기 버튼 --> 콘서트 선택 페이지로 이동한다.
	@FXML
	void btn_cancel(ActionEvent event) {
		Reservation_Home_Controller.getinstance().reservation_loadpage("concert_select_page");

	}

	@FXML
	void btn_reservation_confirm(ActionEvent event) {

		if (opt_1.isSelected()) {
			// 2PM 선택했을 때

			// 잔여 좌석을 불러와야한다.

			// ConcertDao.get_concertDao().get_remaining_seat_D(c_no);

		}
		if (opt_2.isSelected()) {
			// 6PM 선택했을 때

		}

		Reservation_Home_Controller.getinstance().reservation_loadpage("payment_page");

	}

	public String getUser_selected_day() {
		return user_selected_day;
	}

	public void setUser_selected_day(String user_selected_day) {
		this.user_selected_day = user_selected_day;
	}

	public int getUser_select_time() {
		return user_select_time;
	}

	public void setUser_select_time(int user_select_time) {
		this.user_select_time = user_select_time;
	}

}
