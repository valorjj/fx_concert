package controller.customer;

import java.net.URL;
import java.util.ResourceBundle;

import dao.ConcertDao;
import domain.Concert;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Concert_Info_Controller_1 implements Initializable {

	@FXML
	private AnchorPane anchorpane_main_page;

	@FXML
	private AnchorPane image_anchorpane1;

	@FXML
	private ImageView imageview1;

	@FXML
	private Label lbl_artist;

	@FXML
	private Label lbl_date;

	@FXML
	private Label lbl_title;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Concert concert = ConcertDao.get_concertDao().get_concert_info_single_item(1);

		lbl_artist.setText(concert.getC_artist());
		lbl_date.setText(concert.getC_date());
		lbl_title.setText(concert.getC_title());

	}

}
