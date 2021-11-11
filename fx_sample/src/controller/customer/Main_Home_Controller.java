package controller.customer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main_Home_Controller implements Initializable {

	/*
	 * main_page_home.fxml 이 로드된 상태이다. 여기서 anchorpane 안에 imageview 를 넣는다. anchorpane
	 * 을 load_page 메소드를 이용해서 pane 을 바꾼다.
	 */

	public static Main_Home_Controller instance;

	public static Main_Home_Controller getinstance() {
		return instance;
	}

	public Main_Home_Controller() {
		instance = this;
	}

	@FXML
	private AnchorPane anchorpane_main_page;
	@FXML
	private AnchorPane anchorpane_image;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
