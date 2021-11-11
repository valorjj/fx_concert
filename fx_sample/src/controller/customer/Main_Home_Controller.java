package controller.customer;

import java.io.File;

import javafx.scene.image.Image;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;

public class Main_Home_Controller extends Thread {

	/*
	 * main_page_home.fxml 이 로드된 상태이다. 여기서 anchorpane 안에 imageview 를 넣는다. anchorpane
	 * 을 load_page 메소드를 이용해서 pane 을 바꾼다.
	 */

	boolean stop;
	@FXML
	AnchorPane anchorpane_main_page;
	@FXML
	AnchorPane anchorpane_image;
	@FXML
	ImageView img_main_page;

	@Override
	public void run() {

		while (!stop) {
			for (int i = 1; i < 3; i++) {

				File file = new File("/src/images/" + i + ".png");
				System.out.println(file);
				Image image = new Image(file.toURI().toString());
				ImageView imageView = new ImageView();
				imageView.setImage(image);
				anchorpane_image.getChildren().add(imageView);
				try {
					Main_Home_Controller.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		}

	}

}
