package controller.customer;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;

public class Image_Change_Controller extends Thread {

	@FXML
	private AnchorPane anchorpane_image;
	@FXML
	private ImageView img_main_page;

	@Override
	public void run() {
		for (int i = 1; i < 3; i++) {

			File file = new File("images/" + i + ".png");
			try {
				Image image = new Image(file.toURI().toString());
				img_main_page.setImage(image);
				Image_Change_Controller.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}
