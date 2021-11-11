package controller.manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class Manager_Update_Delete_Controller {


    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_update;

    @FXML
    private TableView<?> concert_list;

    @FXML
    void btn_delete(ActionEvent event) {
    	
    }

    @FXML
    void btn_update(ActionEvent event) {
    	Manager_Main_Controller.getInstance().loadpage("manager_update_page");
    }
}
