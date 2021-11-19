package controller.customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Reservation_Seat_Select_Controller implements Initializable {

	public static int[] R_status_check = R_Seat_Controller.getR_status_check();
	public static int[] S_status_check = S_Seat_Controller.getS_status_check();
	public static int[] D_status_check = D_Seat_Controller.getD_status_check();
	public static int[] E_status_check = E_Seat_Controller.getE_status_check();

	static boolean is_R_set;
	static boolean is_S_set;
	static boolean is_D_set;
	static boolean is_E_set;

	static int how_many_person = 0;
	static int seat_total = 0;

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

	//////////////////////////////////////////////////////////

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		btn_how_many_1.setVisible(false);
		btn_how_many_2.setVisible(false);
		btn_how_many_3.setVisible(false);
		btn_how_many_4.setVisible(false);

		load_page("reservation_page_seat_select_home");

	}

	//////////////////////////////////////////////////////////

	@FXML
	private BorderPane borderpane_payment;

	@FXML
	private static Button btn_D_select;

	@FXML
	private static Button btn_E_select;

	@FXML
	private static Button btn_R_select;

	@FXML
	private static Button btn_S_select;

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

	////////////////////////////////////////////////////////////////

	// 근데 이렇게 하면 매번 페이지가 바뀌어서 내가 이전에 선택한 다른 등급의 좌석은 다시는 볼 수가 없네...?
	// 클릭할 때 마다 페이지가 초기화 되어버리니까 ~ 이건 뭐 제대로 보려면 클릭할 때 마다 DB 에 반영이 되야 겠는걸 ????
	// 근데 그렇게 하자니, 고객이 취소를 누르면 db에 반영되었던 걸 다시 리셋해야하는데
	// 실제 영화관이나 예매 사이트에서는 어떻게 할까?

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

	//////////////////////////////////////////////////

	@FXML
	void btn_cancel(ActionEvent event) {
		how_many_person = 0;
		Reservation_Home_Controller.getinstance().reservation_loadpage("reservation_page_date_select");

	}
	///////////////////////////////////////////////////

	@FXML
	void btn_how_many_people(ActionEvent event) {

		btn_how_many_people.setVisible(false);
		btn_how_many_1.setVisible(true);
		btn_how_many_2.setVisible(true);
		btn_how_many_3.setVisible(true);
		btn_how_many_4.setVisible(true);

	}

	////////////////////////////////////////////////////////////////

	@FXML
	void btn_payment(ActionEvent event) {

		// 결제 진행 -> 최종적으로 현재까지 입력받은 모든 값을 DB 에 반영해야한다.
		// 여기서 든 생각은, concert db 에 매니저가 지정한 고정 좌석에 더해서 현재 남은 좌석 (변하는 값) 을 넣어야 하지 않나 그런
		// 생각이 든다.
		// 그럼 concert 하나에 필드에 엄청 많아지는데, 어쩔 수 없는 것 아닐까?

		try {
			Reservation_Home_Controller.getinstance().reservation_loadpage("reservation_payment_page");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	////////////////////////////////////////////////////////////////
	@FXML
	void btn_reset(ActionEvent event) {

		// 모든 선택을 무효화 시킨다.

		how_many_person = 0;
		seat_total = 0;
		btn_how_many_people.setVisible(true);
		btn_how_many_1.setVisible(false);
		btn_how_many_2.setVisible(false);
		btn_how_many_3.setVisible(false);
		btn_how_many_4.setVisible(false);

	}

	////////////////////////////////////////////////////////////////
	@FXML
	void btn_view_entire_seat(ActionEvent event) {

		// image 파일을 하나 불러서 새로운 창을 띄웁니다

	}

	////////////////////////////////////////////////////////////////

	// 버튼을 한번 선택하면 다른건 선택 못하게 막는다.

	@FXML
	void btn_how_many_1(ActionEvent event) {
		if (switch_btn_how_many_1) {
			how_many_person = 1;
			seat_total = how_many_person;
			switch_btn_how_many_1 = false;
			btn_how_many_2.setVisible(false);
			btn_how_many_3.setVisible(false);
			btn_how_many_4.setVisible(false);

		} else {
			how_many_person = 0;
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

}
