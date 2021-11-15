package controller.customer;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Main_Reservation_Controller implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Calendar calendar = Calendar.getInstance();

		// DB 에서 콘서트 날짜를 가져오는 메소드가 필요하다 (콘서트 연도, 월만 리턴하는 메소드)

		// calendar.set(year, month - 1, 1);

		// 앞전 concert_select 화면에서 radiobutton 에서 선택한 값이 넘어와야한다.
		
//		int m_no = MemberDao.get_memberDao().get_m_no_member(Login_Controller.getinstance().get_login_id());
//		int c_no = ReservationDao.get_reservationDao().reservation_c_no_check(m_no);
//		String concert_date = ConcertDao.get_concertDao().get_concert_date(c_no);
//
//		String concert_year = concert_date.substring(0, 5);
//		String concert_month = concert_date.substring(6, 8);
//
//		int year = Integer.parseInt(concert_year);
//		int month = Integer.parseInt(concert_month);

		calendar.set(2021, 10, 1);

		int sweek = calendar.get(Calendar.DAY_OF_WEEK); // 달의 시작하는 날짜

		int eday = calendar.getActualMaximum(calendar.DAY_OF_MONTH);

		lbl_concert_tile.setText(""); // DB 에서 공연 타이틀 가져오기
		lbl_concert_date.setText(""); // DB 에서 공연 날짜 (공연이 지속되는 날짜) 가져오기
		lbl_concert_duration.setText("180분");

		int idx = 1;
		for (int i = 0; i <= 35; i++) {
			if (i >= sweek - 1 && i <= eday) { // 특정 달의 시작 날짜를 구한다.

				Button button = new Button();
				button.setText(idx + "");

				button.setPrefSize(30, 30);
				button.setId(idx + "");

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
				} else {
					gridpane_calendar.add(button, i % 7, 4);
				}

				idx++;

			} else {

			}

		}

	}

	@FXML
	private Button btn_cancel;

	@FXML
	private Button btn_reservation_confirm;

	@FXML
	private Button btn_time_select_2pm;

	@FXML
	private Button btn_time_select_6pm;

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
		Concert_Select_Controller.getinstance().reservation_loadpage("concert_select_page");

	}

	@FXML
	void btn_reservation_confirm(ActionEvent event) {
		Concert_Select_Controller.getinstance().reservation_loadpage("payment_page");

	}

}
