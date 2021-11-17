package controller.customer;

import java.net.URL;
import java.util.ResourceBundle;

import dao.MemberDao;
import dao.ReservationDao;
import domain.Member;
import domain.Reservation;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;

public class Info_Page_Home_Controller implements Initializable {

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

		Member member = MemberDao.get_memberDao().get_id_member(Login_Controller.getinstance().get_login_id());
		info_update_id.setText(member.getM_id());
		info_update_name.setText(member.getM_name());
		info_update_email.setText(member.getM_email());

		int m_no = MemberDao.get_memberDao().get_m_no_member(Login_Controller.getinstance().get_login_id());

		ObservableList<Reservation> member_reservation_history = ReservationDao.get_reservationDao()
				.get_member_reservation(m_no);

		tableview_history.setItems(member_reservation_history);

		TableColumn<?, ?> tc = tableview_history.getColumns().get(0);
		tc.setCellValueFactory(new PropertyValueFactory<>("r_no"));
		
		tc = tableview_history.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("s_no"));
		
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
