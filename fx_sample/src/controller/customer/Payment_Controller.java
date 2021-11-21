package controller.customer;

import java.net.URL;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.ConcertDao;
import dao.MemberDao;
import dao.ReservationDao;
import dao.SeatDao;
import domain.Concert;
import domain.Reservation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class Payment_Controller implements Initializable {

	// 1. 유저가 선택한 콘서트 번호
	int c_no = ConcertDao.getConcertDao().get_concert_c_no(Reservation_Date_Select_Controller.user_selected_date,
			Reservation_Date_Select_Controller.user_selected_time);

	boolean res1;
	boolean res2;
	boolean res3;
	boolean res4;
	boolean res5;
	boolean res6;
	boolean res7;
	boolean res8;

	Concert concert;

	int total_price = 0;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// 1. 선택된 정보들을 레이블로 출력시킵니다.

		try {

			if (Reservation_Seat_Select_Controller.getReseved_seat_map().get("R") != null) {

				String str = "";
				for (Entry<Integer, String> entry : Reservation_Seat_Select_Controller.getReseved_seat_map().get("R")
						.entrySet()) {
					str += entry.getKey() + "  ";
				}
				lbl_r_no_1.setText("R석 : " + str);
				lbl_r_no_1.setStyle("-fx-font-size : 30px");
				lbl_r_no_1.setStyle("-fx-text-fill : green");
			} else {
				lbl_r_no_1.setText("");
			}

			if (Reservation_Seat_Select_Controller.getReseved_seat_map().get("S") != null) {

				String str = "";
				for (Entry<Integer, String> entry : Reservation_Seat_Select_Controller.getReseved_seat_map().get("S")
						.entrySet()) {
					str += entry.getKey() + "  ";
				}
				lbl_r_no_2.setText("S석 : " + str);
				lbl_r_no_2.setStyle("-fx-font-size : 30px");
				lbl_r_no_2.setStyle("-fx-text-fill : green");
			} else {
				lbl_r_no_2.setText("");
			}

			if (Reservation_Seat_Select_Controller.getReseved_seat_map().get("D") != null) {
				String str = "";
				for (Entry<Integer, String> entry : Reservation_Seat_Select_Controller.getReseved_seat_map().get("D")
						.entrySet()) {
					str += entry.getKey() + "  ";
				}
				lbl_r_no_3.setText("D석 : " + str);
				lbl_r_no_3.setStyle("-fx-font-size : 30px");
				lbl_r_no_3.setStyle("-fx-text-fill : green");
			} else {
				lbl_r_no_3.setText("");
			}

			if (Reservation_Seat_Select_Controller.getReseved_seat_map().get("E") != null) {
				String str = "";
				for (Entry<Integer, String> entry : Reservation_Seat_Select_Controller.getReseved_seat_map().get("E")
						.entrySet()) {
					str += entry.getKey() + "  ";
				}
				lbl_r_no_4.setText("S석 : " + str);
				lbl_r_no_4.setStyle("-fx-font-size : 30px");
				lbl_r_no_4.setStyle("-fx-text-fill : green");
			} else {
				lbl_r_no_4.setText("");
			}

			// 2. 가격을 더해서 저장합니다.

			concert = ConcertDao.getConcertDao().get_concert_instance_by_date(
					Reservation_Date_Select_Controller.user_selected_date,
					Reservation_Date_Select_Controller.user_selected_time);

			int R_price = concert.getC_R_price() * Reservation_Seat_Select_Controller.R_count;
			int S_price = concert.getC_S_price() * Reservation_Seat_Select_Controller.S_count;
			int D_price = concert.getC_D_price() * Reservation_Seat_Select_Controller.D_count;
			int E_price = concert.getC_E_price() * Reservation_Seat_Select_Controller.E_count;

			total_price = R_price + S_price + D_price + E_price;

			lbl_total_price.setText(total_price + "");
			lbl_total_price.setStyle("-fx-font-size : 36px");
			lbl_total_price.setStyle("-fx-text-fill : red");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	private AnchorPane anchorpane_graph;

	@FXML
	private Button btn_cancel;

	@FXML
	private Button btn_graph_by_age;

	@FXML
	private Button btn_pay;

	@FXML
	private Button graph_by_sex;

	@FXML
	private Label lbl_reserve;

	@FXML
	private Label lbl_reserve_concert_date;

	@FXML
	private Label lbl_reserve_concert_time;

	@FXML
	private Label lbl_title;

	@FXML
	private Label lbl_total;

	@FXML
	private Label lbl_total_price;

	@FXML
	private Label lbl_r_no_1;

	@FXML
	private Label lbl_r_no_2;

	@FXML
	private Label lbl_r_no_3;

	@FXML
	private Label lbl_r_no_4;

	@FXML
	private BorderPane borderpane_chart;

	private static Payment_Controller instance;

	private Payment_Controller get_instance() {

		return instance;

	}

	public Payment_Controller() {
		instance = this;
	}

	public void load_page(String page) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/fxml/" + page + ".fxml"));
			borderpane_chart.setCenter(parent);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// 1. 현재까지 선택된 모든 정보를 초기화시켜야 하는데, 너무 귀찮습니다. 그래서 그냥 콘서트 선택 페이지로 보낼 것 입니다.

	@FXML
	void btn_cancel(ActionEvent event) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		Optional<ButtonType> optional = alert.showAndWait();
		alert.setHeaderText("취소하시면 콘서트 선택화면으로 돌아갑니다.");
		alert.setTitle("경고");

		if (optional.get() == ButtonType.OK) {
			Reservation_Home_Controller.getinstance().reservation_loadpage("reservation_page_concert_select");
		} else {

		}

	}

	@FXML
	void btn_graph_by_age(ActionEvent event) {
		// 1. 해당 콘서트 예약 현황을 나이에 따라 분류해서 Bar 그래프로 출력합니다.
		// 1.1 멤버를 나이별로 묶습니다.
		// 1.2 그 후
		
		load_page("chart_view_by_age");

	}

	@FXML
	void graph_by_sex(ActionEvent event) {
		// 1. 해당 콘서트 예약 현황을 성별에 따라 분류해서 Bar 그래프로 출력합니다.
		load_page("chart_view_by_sex");

	}

	@FXML
	void btn_pay(ActionEvent event) {
		// 1. 최종 결제 하는 버튼입니다. 이 버튼을 클릭하면 DB에 최종적으로 반영이 되야합니다.
		// 1.1 회원 예약 내역, 그리고 좌석 현황이 변동될 것 입니다.
		// 1.2 앞에서 부터 받아온 콘서트 고유번호, 날짜, 시간, 좌석 등급, 좌석 번호 등의 정보를 전부 이용해서 예약 내역에 반영시킵니다.

		Alert alert = new Alert(AlertType.CONFIRMATION);
		Optional<ButtonType> optional = alert.showAndWait();
		if (optional.get() == ButtonType.OK) {

			// 2. R 등급에 해당하는 좌석을 꺼내서 DB 에 등록시킵니다.
			// 2.1 등록시킨다는 것은, 이미 등록된 좌석의 상태를 0에서 1로 바꾸는 작업입니다.
			// 2.2 그리고 예약 현황에도 업데이트를 시킵니다.

			// 3. TreeMap 을 반복문으로 돌면서 TreeMap 안에 데이터가 존재하는 경우에만 db 에 등록을 해야합니다. (안하면 오류남)
			// 3.1
			if (!Reservation_Seat_Select_Controller.getReseved_seat_map().get("R").isEmpty()) {
				for (Entry<Integer, String> entry : Reservation_Seat_Select_Controller.getReseved_seat_map().get("R")
						.entrySet()) {
					res1 = SeatDao.getSeatDao().set_seat_status(entry.getKey(), entry.getValue(), c_no,
							Reservation_Concert_Select_Controller.concert_number);

					Reservation reservation = new Reservation(

							entry.getKey(), entry.getValue(), c_no,
							Reservation_Concert_Select_Controller.concert_number,
							MemberDao.getMemberDao().get_m_no_member(Login_Controller.getInstance().get_login_id())

					);

					res2 = ReservationDao.get_reservationDao().reservation_register(reservation);

				}
			}

			if (!Reservation_Seat_Select_Controller.getReseved_seat_map().get("S").isEmpty()) {
				for (Entry<Integer, String> entry : Reservation_Seat_Select_Controller.getReseved_seat_map().get("S")
						.entrySet()) {
					res3 = SeatDao.getSeatDao().set_seat_status(entry.getKey(), entry.getValue(), c_no,
							Reservation_Concert_Select_Controller.concert_number);

					Reservation reservation = new Reservation(

							entry.getKey(), entry.getValue(), c_no,
							Reservation_Concert_Select_Controller.concert_number,
							MemberDao.getMemberDao().get_m_no_member(Login_Controller.getInstance().get_login_id())

					);

					res4 = ReservationDao.get_reservationDao().reservation_register(reservation);
				}
			}

			if (!Reservation_Seat_Select_Controller.getReseved_seat_map().get("D").isEmpty()) {
				for (Entry<Integer, String> entry : Reservation_Seat_Select_Controller.getReseved_seat_map().get("D")
						.entrySet()) {
					res5 = SeatDao.getSeatDao().set_seat_status(entry.getKey(), entry.getValue(), c_no,
							Reservation_Concert_Select_Controller.concert_number);

					Reservation reservation = new Reservation(

							entry.getKey(), entry.getValue(), c_no,
							Reservation_Concert_Select_Controller.concert_number,
							MemberDao.getMemberDao().get_m_no_member(Login_Controller.getInstance().get_login_id())

					);

					res6 = ReservationDao.get_reservationDao().reservation_register(reservation);
				}

			}

			if (!Reservation_Seat_Select_Controller.getReseved_seat_map().get("E").isEmpty()) {
				for (Entry<Integer, String> entry : Reservation_Seat_Select_Controller.getReseved_seat_map().get("E")
						.entrySet()) {
					res7 = SeatDao.getSeatDao().set_seat_status(entry.getKey(), entry.getValue(), c_no,
							Reservation_Concert_Select_Controller.concert_number);

					Reservation reservation = new Reservation(

							entry.getKey(), entry.getValue(), c_no,
							Reservation_Concert_Select_Controller.concert_number,
							MemberDao.getMemberDao().get_m_no_member(Login_Controller.getInstance().get_login_id())

					);

					res8 = ReservationDao.get_reservationDao().reservation_register(reservation);
				}

			}

			// 1. 위 등록 과정 중 하나라도 성공 했다면 알림창을 띄웁니다.
			if ((res1 && res2) || (res3 && res4) || (res5 && res6) || (res7 && res8)) {

				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setHeaderText("예약에 성공하셨습니다. 감사합니다.");
				alert2.showAndWait();

			} else {
				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setHeaderText("DB에 오류가 발생했습니다. 관리자에게 문의하세요.");
				alert2.showAndWait();
			}

		}

	}

}
