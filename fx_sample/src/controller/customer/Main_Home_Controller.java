package controller.customer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

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
	private Label lbl_ID;

	@FXML
	private Label lbl_title;

	@FXML
	private Label lbl_artist;

	@FXML
	private Label lbl_date;

	@FXML
	private BorderPane main_page_image_borderpane;

	@FXML
	private AnchorPane image_anchorpane;

	@FXML
	private ImageView imageview;

	@FXML
	private AnchorPane anchorpane_main_page;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		File file = new File("src/images/1.png");
//		Image image = new Image(file.toURI().toString());
//		image_test.setImage(image);

		lbl_title.setText("");
		lbl_date.setText("");
		lbl_artist.setText("");
	}

	public void main_image_loadpage(String page) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/fxml/" + page + ".fxml"));
			main_page_image_borderpane.setCenter(parent);
		} catch (Exception e) {
		}
	}
}
