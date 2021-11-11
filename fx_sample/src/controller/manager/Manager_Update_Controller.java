package controller.manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Manager_Update_Controller {

	

    @FXML
    private Button btn_concert_info;

    @FXML
    private Button btn_concert_route;

    @FXML
    private Button btn_concert_update;

    @FXML
    private CheckBox ch_day1;

    @FXML
    private CheckBox ch_day2;

    @FXML
    private CheckBox ch_day3;

    @FXML
    private CheckBox ch_day4;

    @FXML
    private CheckBox ch_day5;

    @FXML
    private CheckBox ch_day6;

    @FXML
    private CheckBox ch_time1;

    @FXML
    private CheckBox ch_time2;

    @FXML
    private CheckBox ch_time3;

    @FXML
    private Label lbl_concert_info_path;

    @FXML
    private Label lbl_concert_route_path;

    @FXML
    private TextField txt_concert_aritist;

    @FXML
    private TextField txt_concert_name;

    @FXML
    private TextField txt_d_seat_no;

    @FXML
    private TextField txt_d_seat_price;

    @FXML
    private TextField txt_e_seat_no;

    @FXML
    private TextField txt_e_seat_price;

    @FXML
    private TextField txt_r_seat_no;

    @FXML
    private TextField txt_r_seat_price;

    @FXML
    private TextField txt_s_seat_no;

    @FXML
    private TextField txt_s_seat_price;

    @FXML
    void btn_concert_info(ActionEvent event) {

    }

    @FXML
    void btn_concert_route(ActionEvent event) {

    }

    @FXML
    void btn_concert_update(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setHeaderText("콘서트 수정 완료");
    	alert.showAndWait();
    	Manager_Main_Controller.getInstance().loadpage("manager_update_delete_page");;
    }

    @FXML
    void ch_day1(ActionEvent event) {

    }

    @FXML
    void ch_day2(ActionEvent event) {

    }

    @FXML
    void ch_day3(ActionEvent event) {

    }

    @FXML
    void ch_day4(ActionEvent event) {

    }

    @FXML
    void ch_day5(ActionEvent event) {

    }

    @FXML
    void ch_day6(ActionEvent event) {

    }

    @FXML
    void ch_time1(ActionEvent event) {

    }

    @FXML
    void ch_time2(ActionEvent event) {

    }

    @FXML
    void ch_time3(ActionEvent event) {

    }

}
