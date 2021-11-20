package controller.customer;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.BoardDao;
import domain.Board;
import domain.Reply;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;

public class Board_Comment_Controller implements Initializable {

	Board board = Board_View_Controller.board;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// 댓글 로드
		replytableload();
		BoardDao.getBoardDao().viewupdate(board.getB_no());
		txt_title.setText(board.getB_title());
		txt_contents.setText(board.getB_contents());
		lbl_writer.setText("작성자 : "+ board.getB_writer());
		lbl_date.setText("작성일 : " + board.getB_date());
		lbl_view.setText("조회수 : " +  (board.getB_view()+ 1));
		if(!Login_Controller.getInstance().get_login_id().equals(board.getB_writer())) {
			btn_update.setVisible(false);
			btn_delete.setVisible(false);
		}
	}
	private static Board_Comment_Controller instance;
	
	public static Board_Comment_Controller getInstance() {
		return instance;
	}
	
	public Board_Comment_Controller() {
		instance = this;
	}
	
	
	@FXML
	private Button btn_update;
	
	@FXML
	private Button btn_back;

	@FXML
	private Button btn_delete;

	@FXML
	private Button btn_write_reply;

	@FXML
	private Label lbl_date;

	@FXML
	private Label lbl_view;

	@FXML
	private Label lbl_writer;

	@FXML
	private TableView<Reply> reply_list;

	@FXML
	private TextArea txt_contents;

	@FXML
	private TextField txt_reply;

	@FXML
	private TextField txt_title;

	@FXML
	void btn_back(ActionEvent event) {
		Mainpage_Controller.getInstance().loadpage("member_board_view_page");
	}

	@FXML
	void btn_delete(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setHeaderText("정말 게시물을 삭제하시겠습니까?");
    	alert.setTitle("알림");
    	Optional<ButtonType> optional = alert.showAndWait();
    	if(optional.get()==ButtonType.OK) {
    		boolean result = BoardDao.getBoardDao().delete(board.getB_no()) ;
    		if(result) {Mainpage_Controller.getInstance().loadpage("member_board_view_page");}
    	}
	}

	@FXML
	void btn_write_reply(ActionEvent event) {
		Reply reply = new Reply(txt_reply.getText(),Login_Controller.getInstance().get_login_id(),board.getB_no());
		boolean result = BoardDao.getBoardDao().replywrite(reply);
		if(result) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("댓글이 등록되었습니다.");
			alert.setTitle("알림");
			alert.showAndWait();
			// 댓글리스트 로드
			replytableload();
			// 댓글 초기화
			txt_reply.setText("");
			
		}
	}

	@FXML
	void btn_update(ActionEvent event) {
		Mainpage_Controller.getInstance().loadpage("member_board_update_page");
	}
	
	public void replytableload() { // 댓글 테이블 뷰 만 새로고침하는 메소드
		ObservableList<Reply> replys = BoardDao.getBoardDao().replylist(board.getB_no());
		
		TableColumn tc = reply_list.getColumns().get(0);
		tc.setCellValueFactory(new PropertyValueFactory<>("r_no"));
		
		tc = reply_list.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("r_contents"));
		
		tc = reply_list.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("r_writer"));
		
		tc = reply_list.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("r_date"));
 		
		reply_list.setItems(replys);
	}
	
}
