package controller.customer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
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
	private TableView<?> tableview_user_select;

	@FXML
	void btn_cancel(ActionEvent event) {

	}

	@FXML
	void btn_graph_by_age(ActionEvent event) {

	}

	@FXML
	void btn_pay(ActionEvent event) {

	}

	@FXML
	void graph_by_sex(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		lbl_title.setText("회원아이디 출력");
		
		
	}

}
