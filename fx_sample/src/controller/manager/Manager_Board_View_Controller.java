package controller.manager;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.BoardDao;
import domain.Board;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Manager_Board_View_Controller implements Initializable{
	
	Board board = Manager_Board_Management_Controller.board;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txt_reply.setVisible(false);
    	reply_list.setVisible(false);
    	//DB조회수 증가
    		BoardDao.getBoardDao().viewupdate(board.getB_no());
    	
    	txt_title.setText(board.getB_title());
    	txt_contents.setText(board.getB_contents());
    	lbl_writer.setText("Writer : "+ board.getB_writer());
    	lbl_date.setText("Date : " + board.getB_date().split(" ")[0]);
    	lbl_view.setText("View : " + (board.getB_view()+1));
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
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setHeaderText("게시물을 삭제하시겠습니까?");
    	alert.setTitle("알림");
    	Optional<ButtonType> optional = alert.showAndWait();
    	if(optional.get()==ButtonType.OK) {
    		boolean result = BoardDao.getBoardDao().delete(board.getB_no());
    		if(result) {Manager_Main_Controller.getInstance().loadpage("manager_board_management_page");}
    		Alert alert2 = new Alert(AlertType.INFORMATION);
    		alert2.setHeaderText("삭제되었습니다");
    		alert2.showAndWait();
    	}
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