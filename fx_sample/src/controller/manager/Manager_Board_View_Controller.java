package controller.manager;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Manager_Board_View_Controller implements Initializable{
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txt_reply.setVisible(false);
    	reply_list.setVisible(false);
	}


    @FXML
    private Button btn_back;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_show_reply;

    @FXML
    private Label lbl_date;

    @FXML
    private Label lbl_view;

    @FXML
    private Label lbl_writer;

    @FXML
    private TextArea txt_contents;

    @FXML
    private TextArea txt_reply;

    @FXML
    private TableView<?> reply_list;

    @FXML
    private TextField txt_title;

    @FXML
    void btn_back(ActionEvent event) {
    	Manager_Main_Controller.getInstance().loadpage("manager_board_management_page");
    }

    @FXML
    void btn_delete(ActionEvent event) {

    }

    boolean upcheck = true;
    @FXML
    void btn_show_reply(ActionEvent event) {
    	
    	if(upcheck){
    		txt_reply.setVisible(true);
    		reply_list.setVisible(true);
    		upcheck=false;
    	}else {
    		upcheck=true;
    		txt_reply.setVisible(false);
    		reply_list.setVisible(false);
    	}
    	
    	
    	
    	
    	
    }
}
