package controller.manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class Manager_Update_Delete_Controller {


    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_update;

    @FXML
    private TableView<?> concert_list;

    @FXML
    void btn_delete(ActionEvent event) {
    	Stage stage = new Stage();
    	try {
			Parent parent = FXMLLoader.load(getClass().getResource("/fxml/concert_route_page.fxml"));
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("찾아오시는 길");
			stage.show();
			
		} catch (Exception e) {}
    }

    @FXML
    void btn_update(ActionEvent event) {
    	Manager_Main_Controller.getInstance().loadpage("manager_update_page");
    }
}
