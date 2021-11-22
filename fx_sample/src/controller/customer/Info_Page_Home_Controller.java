package controller.customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.MemberDao;
import dao.ReservationDao;
import domain.Concert;
import domain.Member;
import domain.Reservation;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Info_Page_Home_Controller implements Initializable {

	/*
	 * 회원 정보를 불러오는 페이지 입니다. 기본적인 회원 정보, 그리고 예약 내역을 불러오는 창입니다. 현재 각종 정보가 DB에 흩어져 있어서
	 * 콘서트 정보, 회원 정보를 불러서 한 table 로 그냥 합치는 것은 불가능합니다. 대신 sql 쿼리 문에서 inner join 을
	 * 사용해서 테이블을 마치 한개로 보이게끔 합칠 수 있는 것 같으니 몇가지 시도를 해볼 예정입니다.
	 * 
	 */

	public static Concert concert;

	Reservation reservation;

	@FXML
	private Label lbl_info_id;
	@FXML
	private Label lbl_info_name;
	@FXML
	private Label lbl_info_email;
	@FXML
	private TextField info_update_id;
	@FXML
	private TextField info_update_name;
	@FXML
	private TextField info_update_email;
	@FXML
	private Label lbl_history;
	@FXML
	private TableView<Reservation> tableview_history;
	@FXML
	private Button btn_update_account;
	@FXML
	private Button btn_delete_account;
	@FXML
	private Button btn_cancel;
	@FXML
	private BorderPane personal_info_borderpane;
	@FXML
	private AnchorPane personal_info_histroy;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Member member = MemberDao.getMemberDao().get_id_member(Login_Controller.getInstance().get_login_id());

		info_update_id.setText(member.getM_id());
		info_update_name.setText(member.getM_name());
		info_update_email.setText(member.getM_email());

		int m_no = MemberDao.getMemberDao().get_m_no_member(Login_Controller.getInstance().get_login_id());

		ObservableList<Reservation> member_reservation_history_concert = ReservationDao.get_reservationDao()
				.get_member_reservation2(m_no);

		TableColumn<?, ?> tc = tableview_history.getColumns().get(0);
		tc.setCellValueFactory(new PropertyValueFactory<>("c_title"));

		tc = tableview_history.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("c_date"));
		
		tc = tableview_history.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("c_artist"));

		tc = tableview_history.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("s_grade"));

		tc = tableview_history.getColumns().get(4);
		tc.setCellValueFactory(new PropertyValueFactory<>("s_unique_no"));

		
		
		tableview_history.setItems(member_reservation_history_concert);

		tableview_history.setOnMouseClicked(e -> {
			// 클릭 이벤트가 마우스 클릭과 같으면
			if (e.getButton().equals(MouseButton.PRIMARY)) {
				reservation = tableview_history.getSelectionModel().getSelectedItem();
				int concert_order = ReservationDao.get_reservationDao().get_c_no(m_no);
				Stage stage = new Stage();
				try {
					Parent parent = FXMLLoader
							.load(getClass().getResource("/fxml/main_page_home_concert" + concert_order + ".fxml"));
					Scene scene = new Scene(parent);
					stage.setScene(scene);
					stage.show();

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	@FXML
	public void delete_account(ActionEvent event) {
		// db에서 정보를 불러와서
	}

	@FXML
	public void cancel(ActionEvent event) {
		Mainpage_Controller.getInstance().loadpage("main_page_home");
	}

	@FXML
	public void update_account(ActionEvent event) {
		// 수정하는 창으로 이동 borderpane 안에 있는 anchorpane 만 이동시킨다.
		Mainpage_Controller.getInstance().window_shift("personal_page_info_update");
	}

	public void load_page_info(String page) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/fxml/" + page + ".fxml"));
			personal_info_borderpane.setCenter(parent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
