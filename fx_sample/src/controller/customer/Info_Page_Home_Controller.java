package controller.customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.ConcertDao;
import dao.MemberDao;
import dao.ReservationDao;
import domain.Concert;
import domain.Member;
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

	public static Concert concert;

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
	private TableView<Concert> tableview_history;
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

		Member member = MemberDao.get_memberDao().get_id_member(Login_Controller.getinstance().get_login_id());

		info_update_id.setText(member.getM_id());
		info_update_name.setText(member.getM_name());
		info_update_email.setText(member.getM_email());

		int m_no = MemberDao.get_memberDao().get_m_no_member(Login_Controller.getinstance().get_login_id());

		ObservableList<Concert> member_reservation_history_concert = ReservationDao.get_reservationDao()
				.get_concert_from_reservation(m_no);

		tableview_history.setItems(member_reservation_history_concert);

		TableColumn<?, ?> tc = tableview_history.getColumns().get(0);
		tc.setCellValueFactory(new PropertyValueFactory<>("c_title"));

		tc = tableview_history.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("c_time"));

		tc = tableview_history.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("c_date"));

		tableview_history.setOnMouseClicked(e -> {
			// 클릭 이벤트가 마우스 클릭과 같으면
			if (e.getButton().equals(MouseButton.PRIMARY)) {
				//
				concert = tableview_history.getSelectionModel().getSelectedItem();

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
		load_page_info("personal_info_page_update");
	}

	public void load_page_info(String page) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/fxml/" + page + ".fxml"));
			personal_info_borderpane.setCenter(parent);
		} catch (Exception e) {
		}
	}

}
