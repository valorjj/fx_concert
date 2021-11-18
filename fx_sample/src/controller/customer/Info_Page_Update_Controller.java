package controller.customer;

import java.net.URL;
import java.util.ResourceBundle;

import controller.manager.Manager_Main_Controller;
import dao.MemberDao;
import domain.Member;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class Info_Page_Update_Controller implements Initializable {
	
	Member member = MemberDao.getMemberDao().get_id_member(Login_Controller.getInstance().get_login_id());
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txt_id.setText(member.getM_id());
		txt_name.setText(member.getM_name());
		txt_email.setText(member.getM_email());
		txt_password.setText(member.getM_pw());
		txt_id.setEditable(false);
	}

    @FXML
    private Button btn_cancel;

    @FXML
    private Button btn_update;

    @FXML
    private Label lbl_cancel;

    @FXML
    private Label lbl_email;

    @FXML
    private Label lbl_id;

    @FXML
    private Label lbl_name;

    @FXML
    private Label lbl_password;

    @FXML
    private Label lbl_update;

    @FXML
    private Label lbl_update_title;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_password;

    @FXML
    private Line update_line;


	@FXML
	public void cancel(ActionEvent event) {
		btn_cancel.getScene().getWindow().hide();

	}

	@FXML
	public void update(ActionEvent event) {
		
		
	}

}
