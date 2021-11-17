package controller.customer;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class R_Seat_Controller implements Initializable {

	// 배열의 갯수는 관리자가 입력한 값을 대입합니다 (매니저와 합칠 시)

	static int manager_input_R_seat_no = 100;
	static int[] status_check = new int[100];

	@FXML
	GridPane gridpane_R;

	/* 상태가 0이면 예약 가능, 상태가 1이면 현재 선택, 상태가 2이면 이미 예약 완료되어 선택 불가능 */

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void seat_refresh() {

		int row = 0;
		int col = 0;

		for (int i = 0; i < manager_input_R_seat_no; i++) {

			Button button = new Button();
			button.setPrefSize(30, 30);
			button.setId((i + 1) + "");
			button.setText((i + 1) + "");

			button.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					status_check[Integer.parseInt(button.getId())] = 1;
					button.setStyle("-fx-background-color: green"); //
				}

			});

			if (i % 10 == 0) {
				row = 0;
				col++;
				gridpane_R.add(button, row++, col);
			} else {
				gridpane_R.add(button, row++, col);
			}

		}

	}

}
