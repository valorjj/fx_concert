package controller.customer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.ConcertDao;
import domain.Concert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Reservation_Seat_Select_Controller implements Initializable {

	////////////////////////////////////////////////////////////////////////

	/*
	 * R, S, D, E 각각의 페이지에서 받아온 스위치 값(boolean) 을 이용해서 이 클래스에 속한 버튼의 상태를 제어해야 합니다.
	 * (달리 방법이 안떠오름) 하지만 initialize 는 딱 한번 실행됩니다. 정보는 바뀌었는데, 해당하는 정보로 기존에 존재하는 버튼을
	 * 새롭게 생긴해야합니다. 그러니 새로운 버튼을 만들어야합니다. 새로운 버튼 -> 새로운 버튼 액션을 통해서 제어합니다. 보기 좀 흉하지만
	 * 이렇게밖에 할 수 없을 것 같습니다.
	 * 
	 * 
	 */

	////////////////////////////////////////////////////////////////////////

	static int[] R_status_check = R_Seat_Controller.getR_status_check();
	static int[] S_status_check = S_Seat_Controller.getS_status_check();
	static int[] D_status_check = D_Seat_Controller.getD_status_check();
	static int[] E_status_check = E_Seat_Controller.getE_status_check();
	
	
	////////////////////////////////////////////////////////////////////////
	
	/* 선택된 좌석 id 를 리스트에 전달합니다. */

	static ArrayList<Integer> R_seat_selected_no = new ArrayList<>();
	static ArrayList<Integer> S_seat_selected_no = new ArrayList<>();
	static ArrayList<Integer> D_seat_selected_no = new ArrayList<>();
	static ArrayList<Integer> E_seat_selected_no = new ArrayList<>();

	static boolean is_R_set; // boolean 의 초기값은 false 입니다.
	static boolean is_S_set;
	static boolean is_D_set;
	static boolean is_E_set;

	//////////////////////////////////////////////////////////////////

	static int how_many_person = 0;// 고객이 인원수를 선택하면 해당하는 숫자로 초기화됩니다.

	static int seat_total = 0; // 인원수와 동일하지만, 좌석을 선택할 때 마다 줄어들어서 0이 되면 선택을 막습니다.

	///////////////////////////////////////////////////////////////////

	private static Reservation_Seat_Select_Controller instance;

	public static Reservation_Seat_Select_Controller get_instance() {

		return instance;

	}

	public Reservation_Seat_Select_Controller() {

		instance = this;
	}

	/////////////////////////////////////////////////////////////////

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

		// 앞 페이지 (날짜선택) 에서 저장된 date, time 을 인수로 받아서
		// 해당하는 concert 객체를 반환하는 메소드를 concertDao 에 선언한 뒤 호출합니다.

		Concert concert = ConcertDao.getConcertDao().get_concert_instance_by_date(
				Reservation_Date_Select_Controller.user_selected_date,
				Reservation_Date_Select_Controller.user_selected_time);

		// 특정 날짜, 특정 시간에 해당하는 콘서트 객체를 불러왔으니 정보를 lable 을 이용해 출력합니다.

		lbl_R_total.setText(concert.getC_R_no() + "");
		lbl_S_total.setText(concert.getC_S_no() + "");
		lbl_D_total.setText(concert.getC_D_no() + "");
		lbl_E_total.setText(concert.getC_E_no() + "");

		// 그럼 남은 좌석은 어디서 불러와야 할까요?

		// R_seat, S_seat, D_seat, E_seat 에서 각각 상태가 1로 체크되어 있는 좌석을 count(*) 해서 총 갯수에서
		// 빼주면 될 것 같습니다.

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

	////////////////////////////////////////////////////////////////

	// 근데 이렇게 하면 매번 페이지가 바뀌어서 내가 이전에 선택한 다른 등급의 좌석은 다시는 볼 수가 없네...?
	// 클릭할 때 마다 페이지가 초기화 되어버리니까 ~ 이건 뭐 제대로 보려면 클릭할 때 마다 DB 에 반영이 되야 겠는걸 ????
	// 근데 그렇게 하자니, 고객이 취소를 누르면 db에 반영되었던 걸 다시 리셋해야하는데
	// 실제 영화관이나 예매 사이트에서는 어떻게 할까?

	@FXML
	void btn_R_select(ActionEvent event) {

		if (!is_R_set) {

			load_page("R_seat");
			is_R_set = true;
		}

		else {

			load_page("reservation_page_seat_select_home");
			is_R_set = false;

		}

	}

	@FXML
	void btn_S_select(ActionEvent event) {

		if (!is_S_set) {
			load_page("S_seat");
			is_S_set = true;

		}

		else {
			load_page("reservation_page_seat_select_home");
			is_S_set = false;
		}

	}

	@FXML
	void btn_D_select(ActionEvent event) {

		if (!is_D_set) {
			load_page("D_seat");
			is_D_set = true;

		}

		else {
			load_page("reservation_page_seat_select_home");
			is_D_set = false;

		}

	}

	@FXML
	void btn_E_select(ActionEvent event) {

		if (!is_E_set) {
			load_page("E_seat");
			is_E_set = true;

		}

		else {
			load_page("reservation_page_seat_select_home");
			is_E_set = false;

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
			Reservation_Home_Controller.getinstance().reservation_loadpage("payment_page");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	////////////////////////////////////////////////////////////////
	@FXML
	void btn_reset(ActionEvent event) {

		// 모든 선택을 무효화 시킨다.
		// 인원수, 그리고 선택한 좌석도 모두 초기화 시킵니다.
		// DB 와 연동이 된다면, DB에서 각 등급별 좌석 상태를 가져와서 리스트에 집어넣습니다.
		// 좌석을 50개로 고정할 예정이기 때문에, 배열이 더 편할 것 입니다.
		// { '좌석번호' : '해당 좌석 예약 상태' }
		// 이런 식으로 TreeMap 에 저장해서 사용해도 좋을 것 같습니다.
		// 머리가 돌아간다면 TreeMap 을 이용한 제어를 주말에 생각해 볼 예정입니다.

		how_many_person = 0;
		seat_total = 0;

		// 현재는 좌석간에 유기적으로 연결되어있지 않아서
		// 리셋 버튼을 누른다고 해도 좌석 정보를 모두 초기화 시키지 못합니다.
		// DB와 연동되어 있지 않기 때문입니다.
		// DB와 좌석 예약 상태가 연동되어 있다면 예약된 좌석의 상태(=2) 를 제외하곤
		// 모두 아직 예약되지 않은 상태 '0' 으로 초기화 시켜야합니다.
		// 각각 R_Controller, S_Controller, D_Controller, E_Controller
		// 에 static 값을 호출해서 초기화 시키면 될 것 같습니다.

		// 인원 수 선택 좌석을 보이게 하고, 인원 버튼은 숨깁니다.
		btn_how_many_people.setVisible(true);
		btn_how_many_1.setVisible(false);
		btn_how_many_2.setVisible(false);
		btn_how_many_3.setVisible(false);
		btn_how_many_4.setVisible(false);

		// 일단 시연을 위해 모든 좌석의 예약 상태를 '0' 으로 만들었습니다.
		// DB 와 연동한다면, 각 등급별 좌석의 예약 현황을 불러서 '2' 값은 그대로 패스
		// 2가 아니면 0으로 바꾸면 됩니다.
		for (int i = 0; i < R_status_check.length; i++) {
			R_status_check[i] = 0;
		}
		for (int i = 0; i < S_status_check.length; i++) {
			S_status_check[i] = 0;
		}
		for (int i = 0; i < D_status_check.length; i++) {
			D_status_check[i] = 0;
		}
		for (int i = 0; i < E_status_check.length; i++) {
			E_status_check[i] = 0;
		}

	}

	////////////////////////////////////////////////////////////////
	@FXML
	void btn_view_entire_seat(ActionEvent event) {

		// image 파일을 하나 불러서 새로운 창을 띄웁니다
		// fxml 을 새로 만들어서 image 파일을 anchorpane 안에 넣어서 새로운 Stage 를 띄웁니다.

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

	@FXML
	Button btn_done;

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

			btn_R_select.setVisible(false);
			btn_S_select.setVisible(false);
			btn_D_select.setVisible(false);
			btn_E_select.setVisible(false);

		} catch (Exception e) {
			e.printStackTrace();
		}

//		if (isIs_R_set()) {
//			btn_R_select.setVisible(false);
//		} else {
//
//		}
//		if (isIs_S_set()) {
//			btn_S_select.setVisible(false);
//		} else {
//
//		}
//		if (isIs_D_set()) {
//			btn_D_select.setVisible(false);
//		} else {
//
//		}
//		if (isIs_E_set()) {
//			btn_E_select.setVisible(false);
//		} else {
//
//		}

	}

}
