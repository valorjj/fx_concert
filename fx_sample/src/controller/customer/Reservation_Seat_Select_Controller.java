package controller.customer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TreeMap;

import dao.ConcertDao;
import dao.ReservationDao;
import dao.SeatDao;
import domain.Concert;
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
import javafx.scene.layout.BorderPane;

public class Reservation_Seat_Select_Controller implements Initializable {

	private static TreeMap<String, TreeMap<Integer, String>> reseved_seat_map = new TreeMap<String, TreeMap<Integer, String>>();

	static int c_no = ConcertDao.getConcertDao().get_concert_c_no(Reservation_Date_Select_Controller.user_selected_date,
			Reservation_Date_Select_Controller.user_selected_time);

	static boolean is_R_set;
	static boolean is_S_set;
	static boolean is_D_set;
	static boolean is_E_set;

	private static ArrayList<Integer> R_status_check = SeatDao.getSeatDao().get_seat_status(c_no, "R",
			Reservation_Concert_Select_Controller.concert_number);
	private static ArrayList<Integer> S_status_check = SeatDao.getSeatDao().get_seat_status(c_no, "S",
			Reservation_Concert_Select_Controller.concert_number);
	private static ArrayList<Integer> D_status_check = SeatDao.getSeatDao().get_seat_status(c_no, "D",
			Reservation_Concert_Select_Controller.concert_number);
	private static ArrayList<Integer> E_status_check = SeatDao.getSeatDao().get_seat_status(c_no, "E",
			Reservation_Concert_Select_Controller.concert_number);

	// 1. 해당 등급에서 선택된 좌석 수 입니다.
	// 2. 이 정보틀 바탕으로 몇가지 해보려고 했으나 보류 중
	static int R_count = 0;
	static int S_count = 0;
	static int D_count = 0;
	static int E_count = 0;

	static int how_many_person = 0;// 고객이 인원수를 선택하면 해당하는 숫자로 초기화됩니다.

	static int seat_total = 0; // 인원수와 동일하지만, 좌석을 선택할 때 마다 줄어들어서 0이 되면 선택을 막습니다.

	private static Reservation_Seat_Select_Controller instance;

	public static Reservation_Seat_Select_Controller get_instance() {
		return instance;
	}

	public Reservation_Seat_Select_Controller() {

		instance = this;
	}

	public void load_page(String page) {

		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/fxml/" + page + ".fxml"));
			borderpane_payment.setCenter(parent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// 1. 결제 화면에서, 종료 누르고 다시 들어왔을 때 좌석 선택 정보가 초기화되야 하므로 db 값을 불러와서 초기화시킵니다.
		R_status_check = SeatDao.getSeatDao().get_seat_status(c_no, "R",
				Reservation_Concert_Select_Controller.concert_number);
		S_status_check = SeatDao.getSeatDao().get_seat_status(c_no, "S",
				Reservation_Concert_Select_Controller.concert_number);
		D_status_check = SeatDao.getSeatDao().get_seat_status(c_no, "D",
				Reservation_Concert_Select_Controller.concert_number);
		E_status_check = SeatDao.getSeatDao().get_seat_status(c_no, "E",
				Reservation_Concert_Select_Controller.concert_number);

		int c_no = ConcertDao.getConcertDao().get_concert_c_no(Reservation_Date_Select_Controller.user_selected_date, Reservation_Date_Select_Controller.user_selected_time);
		Concert concert = ConcertDao.getConcertDao().get_concert_instance_by_date(
				Reservation_Date_Select_Controller.user_selected_date,
				Reservation_Date_Select_Controller.user_selected_time);

		int R_total = ConcertDao.getConcertDao().get_remaining_seat_R(
				Reservation_Date_Select_Controller.user_selected_date,
				Reservation_Date_Select_Controller.user_selected_time);
		int R_selected = ReservationDao.get_reservationDao().get_reservation1(c_no, "R",
				Reservation_Concert_Select_Controller.concert_number);
		lbl_R_remain.setText((R_total - R_selected) + "");

		int S_total = ConcertDao.getConcertDao().get_remaining_seat_S(
				Reservation_Date_Select_Controller.user_selected_date,
				Reservation_Date_Select_Controller.user_selected_time);
		int S_selected = ReservationDao.get_reservationDao().get_reservation1(c_no, "S",
				Reservation_Concert_Select_Controller.concert_number);
		lbl_S_remain.setText((S_total - S_selected) + "");

		int D_total = ConcertDao.getConcertDao().get_remaining_seat_D(
				Reservation_Date_Select_Controller.user_selected_date,
				Reservation_Date_Select_Controller.user_selected_time);
		int D_selected = ReservationDao.get_reservationDao().get_reservation1(c_no, "D",
				Reservation_Concert_Select_Controller.concert_number);
		lbl_D_remain.setText((D_total - D_selected) + "");

		int E_total = ConcertDao.getConcertDao().get_remaining_seat_E(
				Reservation_Date_Select_Controller.user_selected_date,
				Reservation_Date_Select_Controller.user_selected_time);
		int E_selected = ReservationDao.get_reservationDao().get_reservation1(c_no, "E",
				Reservation_Concert_Select_Controller.concert_number);
		lbl_E_remain.setText((E_total - E_selected) + "");

		lbl_R_total.setText(concert.getC_R_no() + "");
		lbl_S_total.setText(concert.getC_S_no() + "");
		lbl_D_total.setText(concert.getC_D_no() + "");
		lbl_E_total.setText(concert.getC_E_no() + "");
		btn_how_many_1.setVisible(false);
		btn_how_many_2.setVisible(false);
		btn_how_many_3.setVisible(false);
		btn_how_many_4.setVisible(false);

		load_page("reservation_page_seat_select_home");

	}

	@FXML
	private Button btn_done;
	@FXML
	private BorderPane borderpane_payment;
	@FXML
	private Button btn_D_select;
	@FXML
	private Button btn_E_select;
	@FXML
	private Button btn_R_select;
	@FXML
	private Button btn_S_select;
	@FXML
	private Button btn_cancel;
	@FXML
	private Button btn_how_many_1;
	@FXML
	private Button btn_how_many_2;
	@FXML
	private Button btn_how_many_3;
	@FXML
	private Button btn_how_many_4;

	boolean switch_btn_how_many_1 = true;
	boolean switch_btn_how_many_2 = true;
	boolean switch_btn_how_many_3 = true;
	boolean switch_btn_how_many_4 = true;

	@FXML
	private Button btn_how_many_people;

	@FXML
	private Button btn_payment;

	@FXML
	private Button btn_reset;

	@FXML
	private Button btn_view_entire_seat;

	@FXML
	private Label lbl_D_remain;

	@FXML
	private Label lbl_D_total;

	@FXML
	private Label lbl_E_remain;

	@FXML
	private Label lbl_E_total;

	@FXML
	private Label lbl_R_remain;

	@FXML
	private Label lbl_R_total;

	@FXML
	private Label lbl_S_remain;

	@FXML
	private Label lbl_S_total;

	@FXML
	void btn_R_select(ActionEvent event) {
		if (!is_R_set) {
			load_page("R_seat");
			is_R_set = true;
		} else {
			load_page("reservation_page_seat_select_home");
			is_R_set = false;
		}

	}

	@FXML
	void btn_S_select(ActionEvent event) {
		if (!is_S_set) {
			load_page("S_seat");
			is_S_set = true;
		} else {
			load_page("reservation_page_seat_select_home");
			is_S_set = false;
		}
	}

	@FXML
	void btn_D_select(ActionEvent event) {
		if (!is_D_set) {
			load_page("D_seat");
			is_D_set = true;
		} else {
			load_page("reservation_page_seat_select_home");
			is_D_set = false;
		}
	}

	@FXML
	void btn_E_select(ActionEvent event) {
		if (!is_E_set) {
			load_page("E_seat");
			is_E_set = true;
		} else {
			load_page("reservation_page_seat_select_home");
			is_E_set = false;
		}
	}

	@FXML
	void btn_cancel(ActionEvent event) {

		how_many_person = 0;
		seat_total = 0;
		R_count = 0;
		S_count = 0;
		D_count = 0;
		E_count = 0;
		Reservation_Home_Controller.getinstance().reservation_loadpage("reservation_page_date_select");

	}

	@FXML
	void btn_how_many_people(ActionEvent event) {

		btn_how_many_people.setVisible(false);
		btn_how_many_1.setVisible(true);
		btn_how_many_2.setVisible(true);
		btn_how_many_3.setVisible(true);
		btn_how_many_4.setVisible(true);

	}

	@FXML
	void btn_payment(ActionEvent event) {
		try {

			// 1. 결제 창으로 진행하기 전 예외 처리입니다.
			// 1.1 인원수를 무조건 선택해야 하고, 선택 가능한 좌석수가 0 이어야합니다.
			if (how_many_person != 0 && seat_total == 0) {

				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setHeaderText("결제 페이지로 진행하시겠습니까?");
				Optional<ButtonType> optional = alert.showAndWait();

				if (optional.get() == ButtonType.OK) {
					Reservation_Home_Controller.getinstance().reservation_loadpage("payment_page");
				}
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("좌석이 모두 선택되지 않았습니다.");
				alert.showAndWait();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 1. 전체 좌석의 조감도를 띄웁니다.
	@FXML
	void btn_view_entire_seat(ActionEvent event) {

		// image 파일을 하나 불러서 새로운 창을 띄웁니다
		// fxml 을 새로 만들어서 image 파일을 anchorpane 안에 넣어서 새로운 Stage 를 띄웁니다.

	}

	// 1. 버튼을 한번 선택하면 다른건 선택 못하게 막는다.

	@FXML
	void btn_how_many_1(ActionEvent event) {

		// 1. 1번 버튼을 누르면 총 1개의 좌석을 선택할 수 있게 합니다.
		if (switch_btn_how_many_1) {
			// 1. 한번 누르면 총 선택 가능한 좌석이 1 로 초기화됩니다.
			how_many_person = 1;
			seat_total = how_many_person;
			switch_btn_how_many_1 = false;
			btn_how_many_2.setVisible(false);
			btn_how_many_3.setVisible(false);
			btn_how_many_4.setVisible(false);

		} else {
			// 1. 한번 더 누르면 총 선택 가능한 좌석을 0 으로 초기화시킵니다.
			how_many_person = 0;
			// 2. 현재 선택한 좌석 수 또한 0으로 초기화 시킵니다.
			seat_total = how_many_person;
			switch_btn_how_many_1 = true;
			btn_how_many_2.setVisible(true);
			btn_how_many_3.setVisible(true);
			btn_how_many_4.setVisible(true);

		}

	}

	@FXML
	void btn_how_many_2(ActionEvent event) {

		if (switch_btn_how_many_2) {
			how_many_person = 2;
			seat_total = how_many_person;
			switch_btn_how_many_2 = false;
			btn_how_many_1.setVisible(false);
			btn_how_many_3.setVisible(false);
			btn_how_many_4.setVisible(false);

		} else {
			how_many_person = 0;
			switch_btn_how_many_2 = true;
			btn_how_many_1.setVisible(true);
			btn_how_many_3.setVisible(true);
			btn_how_many_4.setVisible(true);

		}
	}

	@FXML
	void btn_how_many_3(ActionEvent event) {

		if (switch_btn_how_many_3) {
			how_many_person = 3;
			seat_total = how_many_person;
			switch_btn_how_many_3 = false;
			btn_how_many_1.setVisible(false);
			btn_how_many_2.setVisible(false);
			btn_how_many_4.setVisible(false);

		} else {
			how_many_person = 0;
			seat_total = how_many_person;
			switch_btn_how_many_3 = true;
			btn_how_many_1.setVisible(true);
			btn_how_many_2.setVisible(true);
			btn_how_many_4.setVisible(true);

		}

	}

	@FXML
	void btn_how_many_4(ActionEvent event) {

		if (switch_btn_how_many_4) {
			how_many_person = 4;
			seat_total = how_many_person;
			switch_btn_how_many_4 = false;
			btn_how_many_1.setVisible(false);
			btn_how_many_2.setVisible(false);
			btn_how_many_3.setVisible(false);

		} else {
			how_many_person = 0;
			seat_total = how_many_person;
			switch_btn_how_many_4 = true;
			btn_how_many_1.setVisible(true);
			btn_how_many_2.setVisible(true);
			btn_how_many_3.setVisible(true);

		}

	}

	@FXML
	void btn_done(ActionEvent event) {
		try {

			lbl_R_remain.setVisible(false);
			lbl_R_total.setVisible(false);
			lbl_S_remain.setVisible(false);
			lbl_S_total.setVisible(false);
			lbl_D_remain.setVisible(false);
			lbl_D_total.setVisible(false);
			lbl_E_remain.setVisible(false);
			lbl_E_total.setVisible(false);

			lbl_D_remain.setVisible(false);
			lbl_D_total.setVisible(false);

			lbl_E_remain.setVisible(false);
			lbl_E_total.setVisible(false);

			lbl_R_remain.setVisible(false);
			lbl_R_total.setVisible(false);

			lbl_S_remain.setVisible(false);
			lbl_S_total.setVisible(false);

			btn_R_select.setVisible(false);
			btn_S_select.setVisible(false);
			btn_D_select.setVisible(false);
			btn_E_select.setVisible(false);

			btn_how_many_1.setVisible(false);
			btn_how_many_2.setVisible(false);
			btn_how_many_3.setVisible(false);
			btn_how_many_4.setVisible(false);

			btn_view_entire_seat.setVisible(false);
			btn_how_many_people.setVisible(false);

			btn_done.setVisible(false);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	void btn_reset(ActionEvent event) {

		// 1. 선택한 인원수를 0 으로 초기화 시킵니다.
		how_many_person = 0;
		seat_total = 0;
		// 2. 각종 버튼과 라벨들을 안보이게 합니다.
		lbl_R_remain.setVisible(true);
		lbl_R_total.setVisible(true);
		lbl_S_remain.setVisible(true);
		lbl_S_total.setVisible(true);
		lbl_D_remain.setVisible(true);
		lbl_D_total.setVisible(true);
		lbl_E_remain.setVisible(true);
		lbl_E_total.setVisible(true);

		lbl_D_remain.setVisible(true);
		lbl_D_total.setVisible(true);

		lbl_E_remain.setVisible(true);
		lbl_E_total.setVisible(true);

		lbl_R_remain.setVisible(true);
		lbl_R_total.setVisible(true);

		lbl_S_remain.setVisible(true);
		lbl_S_total.setVisible(true);

		btn_R_select.setVisible(true);
		btn_S_select.setVisible(true);
		btn_D_select.setVisible(true);
		btn_E_select.setVisible(true);

		btn_how_many_people.setVisible(true);
		btn_how_many_1.setVisible(false);
		btn_how_many_2.setVisible(false);
		btn_how_many_3.setVisible(false);
		btn_how_many_4.setVisible(false);

		// 1. 리셋 버튼을 누르면, db에서 다시 좌석 상태 정보를 불러와서 초기화시킵니다.
		// 1.1 유저가 선택하기 이전 상태로 되돌린다는 뜻 입니다.
		R_status_check = SeatDao.getSeatDao().get_seat_status(c_no, "R",
				Reservation_Concert_Select_Controller.concert_number);
		S_status_check = SeatDao.getSeatDao().get_seat_status(c_no, "S",
				Reservation_Concert_Select_Controller.concert_number);
		D_status_check = SeatDao.getSeatDao().get_seat_status(c_no, "D",
				Reservation_Concert_Select_Controller.concert_number);
		E_status_check = SeatDao.getSeatDao().get_seat_status(c_no, "E",
				Reservation_Concert_Select_Controller.concert_number);

		R_count = 0;
		S_count = 0;
		D_count = 0;
		E_count = 0;

	}

	public static TreeMap<String, TreeMap<Integer, String>> getReseved_seat_map() {
		return reseved_seat_map;
	}

	public static void setReseved_seat_map(TreeMap<String, TreeMap<Integer, String>> reseved_seat_map) {
		Reservation_Seat_Select_Controller.reseved_seat_map = reseved_seat_map;
	}

	public static ArrayList<Integer> getR_status_check() {
		return R_status_check;
	}

	public static void setR_status_check(ArrayList<Integer> r_status_check) {
		R_status_check = r_status_check;
	}

	public static ArrayList<Integer> getS_status_check() {
		return S_status_check;
	}

	public static void setS_status_check(ArrayList<Integer> s_status_check) {
		S_status_check = s_status_check;
	}

	public static ArrayList<Integer> getD_status_check() {
		return D_status_check;
	}

	public static void setD_status_check(ArrayList<Integer> d_status_check) {
		D_status_check = d_status_check;
	}

	public static ArrayList<Integer> getE_status_check() {
		return E_status_check;
	}

	public static void setE_status_check(ArrayList<Integer> e_status_check) {
		E_status_check = e_status_check;
	}

}
