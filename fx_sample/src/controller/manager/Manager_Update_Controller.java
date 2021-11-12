package controller.manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class Manager_Update_Controller {

	

    @FXML
    private Button btn_concert_info;

    @FXML
    private Button btn_concert_update;

    @FXML
    private ImageView img_concert_info;
    
	@FXML
	private TextField txt_concert_time;

	@FXML
	private TextField txt_concert_date;
	
    @FXML
    private Label lbl_concert_info_path;

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



}
