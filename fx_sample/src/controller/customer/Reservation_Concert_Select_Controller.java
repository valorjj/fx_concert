package controller.customer;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.ConcertDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

public class Reservation_Concert_Select_Controller implements Initializable {

	static Reservation_Concert_Select_Controller instance;

	public static Reservation_Concert_Select_Controller get_instance() {
		return instance;
	}

	public Reservation_Concert_Select_Controller() {
		instance = this;
	}

	// 1. concert_number 가 static 영역에 저장되어 콘서트 고유 번호로 계속 쓰일 것 입니다.
	// 1.1 호출하는 방법 : Reservation_Concert_Select_Controller.concert_number
	// 1.2 다른 페이지에서 호출해서 사용하면 됩니다.
	// 1.3 DAO 에 콘서트 고유 번호가 자주 쓰입니다.
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

		if (concert_number != 0) {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("선택한 콘서트로 예약을 진행하시겠습니까?");
			Optional<ButtonType> optional = alert.showAndWait();

			if (optional.get() == ButtonType.OK) {
				Reservation_Home_Controller.getinstance().reservation_loadpage("reservation_page_date_select");
			}

		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("콘서트가 선택되지 않았습니다. ");
			alert.showAndWait();

		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ConcertDao.getConcertDao().get_concert_title(1);
		opt_1.setText("");
		opt_2.setText("");
		opt_3.setText("");

	}

}
