package controller.customer;

import java.net.URL;
import java.util.ResourceBundle;

import dao.Member_Dao;
import domain.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Signup_Controller implements Initializable{
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {// ó�� ���������� ����� �������� �����ش�
		lbl_confirm_id.setText("");
		lbl_confirm_password.setText("");
		lbl_confirm_confirm_password.setText("");
		lbl_confirm_email.setText("");
	}
		

	
	   @FXML
	    private Button btn_cancel;

	    @FXML
	    private Button btn_signup;

	    @FXML
	    private Label lbl_confirm_confirm_password;

	    @FXML
	    private Label lbl_confirm_email;

	    @FXML
	    private Label lbl_confirm_id;

	    @FXML
	    private Label lbl_confirm_password;

	    @FXML
	    private RadioButton radio_btn_man;

	    @FXML
	    private RadioButton radio_btn_woman;

	    @FXML
	    private ToggleGroup sex;

	    @FXML
	    private TextField txt_age;

	    @FXML
	    private PasswordField txt_confirm_password;

	    @FXML
	    private TextField txt_email;

	    @FXML
	    private TextField txt_id;

	    @FXML
	    private TextField txt_name;

	    @FXML
		private PasswordField txt_password;

		@FXML
		void cancel(ActionEvent event) {
			Login_Controller.getinstance().loadpage("login_home_page");
		}

		@FXML
		void man(ActionEvent event) {

		}
		@FXML
		void woman(ActionEvent event) {

		}

		@FXML
		void signup(ActionEvent event) {

			// ���� �ִ°�
			//id ���̵� ���� 4�ڸ��̻�12�ڸ�����
			if (txt_id.getText().length() < 4 || txt_id.getText().length() > 12) {
				lbl_confirm_id.setText("���̵�� 4~12 ���ڷ� �� �����մϴ�.");
				return;
			}
			// ��й�ȣ ���� 6�ڸ� �̻� 6�ڸ�����
			if (txt_password.getText().length() < 6 || txt_password.getText().length() > 12) {
				lbl_confirm_password.setText("��й�ȣ�� 6~12 ���ڷ� �� �����մϴ�. ");
				return;
			}//txt_password�� txt_confirm_password�� ��������
			
			if(!txt_password.getText().equals(txt_confirm_password.getText())) {
			lbl_confirm_confirm_password.setText("��й�ȣ �� �������� �ʽ��ϴ�. ");	
			return;
			}
			//�̸��� �α��� �̻����θ��Է°���
			if(txt_name.getText().length()<2) {return;}
			
			
			if (txt_email.getText().length()<5 || !txt_email.getText().contains("@")) {
				lbl_confirm_email.setText("�̸��� �������� �Է����ּ���.");
				return;
			}
			
			//2. �ߺ�üũ
			//boolean idcheck = Member_Dao.getMember_Dao().id
			
    	
    	
    	
    	
    	
    	
			// db���� �ϴ°�
			Member member = new Member(txt_id.getText(), txt_password.getText(), txt_name.getText(),
					txt_email.getText(), 0, "man");
			boolean check = Member_Dao.getMember_Dao().signup(member);
			Alert alert = new Alert(AlertType.INFORMATION);
			if (check) {

				alert.setHeaderText("[[�˸�]]  ȸ������ ����");
				alert.showAndWait();
			
			} else {

				alert.setHeaderText("[[�˸�]] ȸ������ ����");
				alert.showAndWait();

			}
  

		}

		
	}