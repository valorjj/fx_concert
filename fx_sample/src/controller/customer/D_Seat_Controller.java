package controller.customer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class D_Seat_Controller implements Initializable {

	@FXML
	private GridPane gridpane_D;

	// 배열의 갯수는 관리자가 입력한 값을 대입합니다 (매니저와 합칠 시)

	private int seat_limit = 4; // 한 예약건에 대해서 총 4자리 까지만 에약할 수 있음
	// 4번이 클릭하면 더 이상 클릭을 못하게 제한을 두어야함

	// 100개 입력하면 오류가 납니다.

	// 적당히 50좌석까지를 마지노선으로 잡으면 보기에도 괜찮고 오류도 없습니다.
	static int manager_input_R_seat_no = 50;
	static int[] status_check = new int[50]; // 여기도 DB 에서 가져와야해 근데 이부분에 꽤 어렵겠는데???? 지금 R S D E 로 나뉘어져 있는데 seat db 에서는
												// 한번에 쭉 이어져 있으니 구분을 잘 해야해
	/* 상태가 0이면 예약 가능, 상태가 1이면 현재 선택, 상태가 2이면 이미 예약 완료되어 선택 불가능 */

	@FXML
	private Button btn_clear;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		D_seat_create();

	}

	public void D_seat_create() {

		int row = 0;
		int col = 0;

		for (int i = 0; i < manager_input_R_seat_no; i++) {

			Button button = new Button();
			button.setPrefSize(42, 42);
			button.setId(i + "");
			button.setText((i + 1) + "");

			button.setOnAction((ActionEvent) -> {

				int button_status_check = status_check[Integer.parseInt(button.getId())];
				switch (button_status_check) {
				case 0: // 좌석이 예약 가능한 상태
					status_check[Integer.parseInt(button.getId())] = 1;
					button.setStyle("-fx-background-color: green");
				case 1: // 현재 선택한 좌석
						// 아무것도 하지 않습니다.

				case 2: // 예약이 불가능한 좌석 (이미 다른 사람이 예약했음)
					button.setStyle("-fx-background-color: red");
					button.setDisable(true);

				}
				status_check[Integer.parseInt(button.getId())] = 1;
				button.setStyle("-fx-background-color: green");

			});

			if (i % 10 == 0) {
				row = 0;
				col++;
				gridpane_D.add(button, row++, col);
			} else {
				gridpane_D.add(button, row++, col);
			}

		}

	}

	// 모든 버튼 상태를 초기화 시킨다.
	// 제대로 작동하는지 확인하려면 매니저가 등록한 DB 에서 좌석 상태를 불러와야한다.
	// 상태가 2, 즉 예약이 완료된 상태를 제외하고 1-> 현재 선택한 좌석이 있다면 0으로 바꾼다.
	// 1에서 2로 바꾸고 버튼을 다시 생성한다

	@FXML
	public void btn_clear(ActionEvent event) {

		for (int i = 0; i < manager_input_R_seat_no; i++) {
			if (status_check[i] == 1) {
				status_check[i] = 0;
			}
		}

		D_seat_create();

	}

}
