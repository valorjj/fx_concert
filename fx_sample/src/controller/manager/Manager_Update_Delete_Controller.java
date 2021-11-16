package controller.manager;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.ConcertDao;
import domain.Concert;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

public class Manager_Update_Delete_Controller implements Initializable{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		concertlistrefresh();
	}

	public static Concert concert;
	
    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_update;

    @FXML
    private TableView<Concert> concert_list;

    @FXML
    void btn_delete(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setHeaderText("등록된 콘서트를 삭제하시겠습니까?");
    	Optional<ButtonType> optional = alert.showAndWait();
    	if(optional.get()==ButtonType.OK) {
    		ConcertDao.getConcertDao().delete(concert.getC_no());
    		Alert alert2 = new Alert(AlertType.INFORMATION);
    		alert2.setHeaderText("삭제 되었습니다.");
    		alert2.showAndWait();
    		Manager_Main_Controller.getInstance().loadpage("manager_update_delete_page");
    		concertlistrefresh();
    	}
    
    
    
    }

    @FXML
    void btn_update(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setHeaderText("수정하시겠습니까?");
    	alert.showAndWait();
    	Manager_Main_Controller.getInstance().loadpage("manager_update_page");
    }
    
    public void concertlistrefresh() {
    	// 1. DAO 호출
    			ObservableList<Concert> concerts = ConcertDao.getConcertDao().concertlist();
    			// 제품목록을 테이블류에 넣어주기
    			concert_list.setItems(concerts);
    			
    			TableColumn tc = concert_list.getColumns().get(0);
    			tc.setCellValueFactory(new PropertyValueFactory<>("c_title"));
    			
    			tc = concert_list.getColumns().get(1);
    			tc.setCellValueFactory(new PropertyValueFactory<>("c_artist"));
    			
    			tc = concert_list.getColumns().get(2);
    			tc.setCellValueFactory(new PropertyValueFactory<>("c_date"));
    			
    			tc = concert_list.getColumns().get(3);
    			tc.setCellValueFactory(new PropertyValueFactory<>("c_time"));
    			
    			tc = concert_list.getColumns().get(4);
    			tc.setCellValueFactory(new PropertyValueFactory<>("c_R_no"));
    			
    			tc = concert_list.getColumns().get(5);
    			tc.setCellValueFactory(new PropertyValueFactory<>("c_S_no"));
    			
    			tc = concert_list.getColumns().get(6);
    			tc.setCellValueFactory(new PropertyValueFactory<>("c_D_no"));
    			
    			tc = concert_list.getColumns().get(7);
    			tc.setCellValueFactory(new PropertyValueFactory<>("c_E_no"));
    			
    			
    			// 4. 클릭한 아이템이 마우스클릭과 같으면
    			concert_list.setOnMouseClicked(e-> {
    				if(e.getButton().equals(MouseButton.PRIMARY)) {
    					concert=concert_list.getSelectionModel().getSelectedItem();
    											// 테이블뷰에 선택된 모델의 아이템
    				}
    			});
    }
}