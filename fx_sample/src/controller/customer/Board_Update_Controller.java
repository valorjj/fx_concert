package controller.customer;

import java.net.URL;
import java.util.ResourceBundle;

import dao.BoardDao;
import domain.Board;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Board_Update_Controller implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		BoardDao.getBoardDao().viewupdate(board.getB_no());
		txt_title.setText(board.getB_title());
		txt_contents.setText(board.getB_contents());
		
		txt_title.setEditable(false);
		txt_contents.setEditable(false);
	}
	Board board = Board_View_Controller.board;
	
    @FXML
    private Button btn_back;

    @FXML
    private Button btn_update;

    @FXML
    private TextArea txt_contents;

    @FXML
    private TextField txt_title;

    @FXML
    void btn_back(ActionEvent event) {
    	Mainpage_Controller.getInstance().loadpage("member_board_comment_page");
    }

    boolean upcheck = true;
    @FXML
    void btn_update(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	if(upcheck) {
	    	alert.setHeaderText("내용 수정후 다시 버튼 클릭시 수정완료 됩니다.");
	    	alert.showAndWait();
	    	txt_title.setEditable(true);
	    	txt_contents.setEditable(true);
	    	upcheck=false;
    	}
    	else {
    		BoardDao.getBoardDao().update(board.getB_no(), txt_title.getText(), txt_contents.getText());
    		alert.setHeaderText("게시물이 수정 되었습니다.");
    		alert.showAndWait();
    		upcheck = true;
    		txt_title.setEditable(false);
    		txt_contents.setEditable(false);
    		Mainpage_Controller.getInstance().loadpage("member_board_comment_page");
    	}
    }

}
