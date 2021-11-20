package controller.customer;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.ConcertDao;
import domain.Concert;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class Payment_Controller implements Initializable {

	@FXML
	private AnchorPane anchorpane_graph;

	@FXML
	private AnchorPane anchorpane_table;

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

///////////////////////////////////////////////////////

	// 뒤로가기 -> 좌석 선택 페이지로 이동
	@FXML
	void btn_cancel(ActionEvent event) {

		Reservation_Home_Controller.getinstance().reservation_loadpage("reservation_page_seat_select");

	}

	/////////////////////////////////////////////////////

	@FXML
	void btn_graph_by_age(ActionEvent event) {
		// 해당 콘서트 예약 현황을 나이에 따라 분류해서 Bar 그래프로 출력합니다.

	}

	@FXML
	void graph_by_sex(ActionEvent event) {
		// 해당 콘서트 예약 현황을 성별에 따라 분류해서 Bar 그래프로 출력합니다.

	}

	///////////////////////////////////////////////////////

	@FXML
	void btn_pay(ActionEvent event) {

		// 최종 결제 하는 버튼입니다. 이 버튼을 클릭하면 DB에 최종적으로 반영이 되야합니다.
		// 회원 예약 내역, 그리고 좌석 현황이 변동될 것 입니다.

		Alert alert = new Alert(AlertType.CONFIRMATION);
		Optional<ButtonType> optional = alert.showAndWait();
		if (optional.get() == ButtonType.OK) {
			// YES 클릭하면 결제 진행
			// 앞에서 받아온 날짜, 시간, 좌석 정보를 모두 넘겨서 DB 에 반영합니다.
			// concert 에 좌석 반영, 좌석에 status 변경, 예약정보에 업데이트 이렇게 총 3개 테이블에서 업데이트가 이루어져야 합니다.
		} else {

		}

	}

	///////////////////////////////////////////////////////

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		lbl_r_no_1.setText(Reservation_Seat_Select_Controller.getReseved_seat_map().get("R") + "");

	}

}
