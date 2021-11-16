package controller.customer;

import java.net.URL;
import java.util.ResourceBundle;

import dao.ConcertDao;
import dao.MemberDao;
import dao.ReservationDao;
import domain.Reservation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

public class Reservation_Concert_Select_Controller implements Initializable {

	private static Reservation_Concert_Select_Controller instance;

	public static Reservation_Concert_Select_Controller get_instance() {
		return instance;
	}

	public Reservation_Concert_Select_Controller() {
		instance = this;
	}

	static int concert_number;

	@FXML
	private Button btn_reservation;

	@FXML
	private ImageView concert_select_image1;

	@FXML
	private ImageView concert_select_image2;

	@FXML
	private ImageView concert_select_image3;

	@FXML
	private ToggleGroup concert_selection;

	@FXML
	private RadioButton opt_1;

	@FXML
	private RadioButton opt_2;

	@FXML
	private RadioButton opt_3;

	@FXML
	void btn_reservation(ActionEvent event) {
		if (opt_1.isSelected()) {
			concert_number = 1;
		}
		if (opt_2.isSelected()) {
			concert_number = 2;
		}
		if (opt_3.isSelected()) {
			concert_number = 3;
		}
		int m_no = MemberDao.get_memberDao().get_m_no_member(Login_Controller.getinstance().get_login_id());
		System.out.println(Login_Controller.getinstance().get_login_id());

		// 멤버 아이디 번호를 받는다.
		// 아직 좌석을 선택하기 전이다. 그래서 좌석 정보는 0 으로 둔다. 다음 페이지에서 좌석 정보를 업데이트해서 DB 에 등록할 준비를 한다.
		// 최종 결제해야만 DB 가 업데이트 되야한다. 그 전까지는 이클립스 내 존재하는 임시값
		Reservation reservation = new Reservation(0, 0, m_no, concert_number);
		boolean res = ReservationDao.get_reservationDao().reservation_register(reservation);

		Alert alert = new Alert(AlertType.INFORMATION);
		if (res) {
			alert.setHeaderText("선택한 콘서트로 예약을 진행하시겠습니까?");
			alert.showAndWait();
			Reservation_Home_Controller.getinstance().reservation_loadpage("reservation_page");

		} else {
			System.out.println("DB 연동 실패");
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ConcertDao.get_concertDao().get_concert_title(1);
		opt_1.setText("");
		opt_2.setText("");
		opt_3.setText("");

	}

}
